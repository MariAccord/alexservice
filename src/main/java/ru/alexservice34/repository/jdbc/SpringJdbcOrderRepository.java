package ru.alexservice34.repository.jdbc;

import ru.alexservice34.dto.*;
import ru.alexservice34.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository("springjdbc")
public class SpringJdbcOrderRepository implements OrderRepository {

    private static final String ORDER_WITH_CUSTOMERS_QUERY = "SELECT \"order\".id                  as id,\n" +
            "       customer.id                 as customerid,\n" +
            "       customer.name               as customername,\n" +
            "       customer.email              as customeremail,\n" +
            "       customertype.value          as customertype,\n" +
            "       legaltype.value             as legaltype,\n" +
            "       customer.comment            as customercomment,\n" +
            "       \"order\".date_created        as datecreated,\n" +
            "       \"order\".problem_description as problem,\n" +
            "       \"user\".name                 as username\n" +
            "from remontpc.tables.\"user\" as \"user\"\n" +
            "         join remontpc.tables.\"order\" as \"order\"\n" +
            "              on \"user\".id = \"order\".user_id\n" +
            "         join remontpc.tables.customer as customer\n" +
            "              on \"order\".customer_id = customer.id\n" +
            "         join remontpc.dictionaries.customer_type as customertype\n" +
            "              on customer.\"customer_type_id\" = customertype.\"id\"\n" +
            "         join remontpc.dictionaries.legal_type as legaltype\n" +
            "              on customer.\"legal_type_id\" = legaltype.\"id\"\n" +
            "where \"user\".name LIKE ?";

    private static final String ORDER_STATUSES_QUERY = "SELECT statustable.order_id as id,\n" +
            "       statusdict.value      as statustype,\n" +
            "       \"user\".name           as username,\n" +
            "       statustable.date_time as datetime\n" +
            "from remontpc.tables.order_status as statustable\n" +
            "         join remontpc.dictionaries.order_status_type as statusdict\n" +
            "              on statustable.status_type_id = statusdict.id\n" +
            "         join remontpc.tables.\"user\" as \"user\"\n" +
            "              on statustable.\"user_id\" = \"user\".\"id\"\n" +
            "where statustable.order_id = ANY(?::uuid[])";

    private static final String EXECUTED_WORKS_QUERY = "SELECT exwork.order_id as id,\n" +
            "       \"user\".name      as username,\n" +
            "       exwork.name      as workname,\n" +
            "       exwork.cost      as cost,\n" +
            "       exwork.date_time as datetime\n" +
            "from remontpc.tables.executed_work as exwork\n" +
            "         join remontpc.tables.\"user\" as \"user\"\n" +
            "              on exwork.user_id = \"user\".id\n" +
            "where exwork.order_id = ANY (?::uuid[])";

    private static final String ADDRESSES_QUERY = "SELECT \"order\".id              as id,\n" +
            "       address.city            as city,\n" +
            "       address.street          as street,\n" +
            "       address.building_number as building,\n" +
            "       address.flat_number     as flat,\n" +
            "       address.office          as office,\n" +
            "       address.comment         as comment\n" +
            "from remontpc.tables.\"order\" as \"order\"\n" +
            "         join remontpc.tables.address as address\n" +
            "              on \"order\".customer_id = address.customer_id\n" +
            "where \"order\".id = ANY (?)";

    private static final String C0NTACTS_QUERY = "SELECT \"order\".id        as id,\n" +
            "       contacttype.value as type,\n" +
            "       contact.number    as number\n" +
            "from remontpc.tables.\"order\" as \"order\"\n" +
            "         join remontpc.tables.contact as contact\n" +
            "              on \"order\".customer_id = contact.customer_id\n" +
            "         join remontpc.dictionaries.contact_type as contacttype\n" +
            "              on contact.\"contact_type_id\" = \"contacttype\".\"id\"\n" +
            "where \"order\".id = ANY (?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderDto> getOrdersByUserName(String userName) {
        List<OrderDto> result = getOrdersWithCustomers(userName);

        Map<UUID, OrderDto> orderMap = result.stream()
                .collect(Collectors.toMap(OrderDto::getId, Function.identity()));

        fillOrderStatuses(orderMap);
        fillExecutedWorks(orderMap);
        fillAddresses(orderMap);
        fillContacts(orderMap);
        return result;
    }

    private List<OrderDto> getOrdersWithCustomers(String userName) {
        return jdbcTemplate.query(ORDER_WITH_CUSTOMERS_QUERY,
                ps -> ps.setString(1, userName),
                (rs, i) -> {
                    OrderDto order = new OrderDto();
                    CustomerDto customer = new CustomerDto();
                    customer.setId(UUID.fromString(rs.getString("customerid")));
                    customer.setName(rs.getString("customername"));
                    customer.setEmail(rs.getString("customeremail"));
                    customer.setCustomerType(rs.getString("customertype"));
                    customer.setLegalType(rs.getString("legaltype"));
                    customer.setComment(rs.getString("customercomment"));
                    order.setId(UUID.fromString(rs.getString("id")));
                    order.setCustomer(customer);
                    order.setDateCreated(rs.getTimestamp("datecreated").toLocalDateTime());
                    order.setProblemDescription(rs.getString("problem"));
                    order.setUserName(userName);
                    return order;
                }
        );
    }

    private void fillOrderStatuses(Map<UUID, OrderDto> orderMap) {
        jdbcTemplate.query(ORDER_STATUSES_QUERY,
                ps -> ps.setArray(1, ps.getConnection()
                        .createArrayOf("UUID", orderMap.keySet().toArray())),
                (rs, i) -> {
                    OrderStatusDto orderStatus = new OrderStatusDto();
                    orderStatus.setStatusType(rs.getString("statustype"));
                    orderStatus.setUserName(rs.getString("username"));
                    orderStatus.setDateTime(rs.getTimestamp("datetime").toLocalDateTime());
                    orderMap.get(UUID.fromString(rs.getString("id")))
                            .getOrderStatuses().add(orderStatus);
                    return null;
                });
    }

    private void fillExecutedWorks(Map<UUID, OrderDto> orderMap) {
        jdbcTemplate.query(EXECUTED_WORKS_QUERY,
                ps -> ps.setArray(1, ps.getConnection()
                        .createArrayOf("UUID", orderMap.keySet().toArray())),
                (rs, i) -> {
                    ExecutedWorkDto executedWork = new ExecutedWorkDto();
                    executedWork.setUserName(rs.getString("username"));
                    executedWork.setWorkName(rs.getString("workname"));
                    executedWork.setCost(rs.getBigDecimal("cost"));
                    executedWork.setDateTime(rs.getTimestamp("datetime")
                            .toLocalDateTime());
                    orderMap.get(UUID.fromString(rs.getString("id")))
                            .getExecutedWorks().add(executedWork);
                    return null;
                });
    }

    private void fillAddresses(Map<UUID, OrderDto> orderMap) {
        jdbcTemplate.query(ADDRESSES_QUERY,
                ps -> ps.setArray(1, ps.getConnection()
                        .createArrayOf("UUID", orderMap.keySet().toArray())),
                (rs, i) -> {
                    AddressDto address = new AddressDto();
                    address.setCity(rs.getString("city"));
                    address.setStreet(rs.getString("street"));
                    address.setBuildingNumber(rs.getString("building"));
                    address.setFlatNumber(rs.getInt("flat"));
                    address.setOffice(rs.getString("office"));
                    address.setComment(rs.getString("comment"));
                    orderMap.get(UUID.fromString(rs.getString("id")))
                            .getCustomer().getAddresses().add(address);
                    return null;
                });
    }

    private void fillContacts(Map<UUID, OrderDto> orderMap) {
        jdbcTemplate.query(C0NTACTS_QUERY,
                ps -> ps.setArray(1, ps.getConnection()
                        .createArrayOf("UUID", orderMap.keySet().toArray())),
                (rs, i) -> {
                    ContactDto contact = new ContactDto();
                    contact.setNumber(rs.getString("number"));
                    contact.setContactType(rs.getString("type"));
                    orderMap.get(UUID.fromString(rs.getString("id")))
                            .getCustomer().getContacts().add(contact);
                    return null;
                });
    }

}
