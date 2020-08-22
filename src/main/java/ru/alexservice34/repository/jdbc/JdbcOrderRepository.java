package ru.alexservice34.repository.jdbc;

import ru.alexservice34.dto.*;
import ru.alexservice34.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository("jdbc")
public class JdbcOrderRepository implements OrderRepository {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcOrderRepository.class);
    private static final String TESTSQL1 = "SELECT \"order\".id                  as id,\n" +
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

    private static final String TESTSQL2 = "SELECT statustable.order_id as id,\n" +
            "       statusdict.value      as statustype,\n" +
            "       \"user\".name           as username,\n" +
            "       statustable.date_time as datetime\n" +
            "from remontpc.tables.order_status as statustable\n" +
            "         join remontpc.dictionaries.order_status_type as statusdict\n" +
            "              on statustable.status_type_id = statusdict.id\n" +
            "         join remontpc.tables.\"user\" as \"user\"\n" +
            "              on statustable.\"user_id\" = \"user\".\"id\"\n" +
            "where statustable.order_id = ANY(?::uuid[])";

    private static final String TESTSQL3 = "SELECT exwork.order_id as id,\n" +
            "       \"user\".name      as username,\n" +
            "       exwork.name      as workname,\n" +
            "       exwork.cost      as cost,\n" +
            "       exwork.date_time as datetime\n" +
            "from remontpc.tables.executed_work as exwork\n" +
            "         join remontpc.tables.\"user\" as \"user\"\n" +
            "              on exwork.user_id = \"user\".id\n" +
            "where exwork.order_id = ANY (?::uuid[])";

    private static final String TESTSQL4 = "SELECT \"order\".id              as id,\n" +
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

    private static final String TESTSQL5 = "SELECT \"order\".id        as id,\n" +
            "       contacttype.value as type,\n" +
            "       contact.number    as number\n" +
            "from remontpc.tables.\"order\" as \"order\"\n" +
            "         join remontpc.tables.contact as contact\n" +
            "              on \"order\".customer_id = contact.customer_id\n" +
            "         join remontpc.dictionaries.contact_type as contacttype\n" +
            "              on contact.\"contact_type_id\" = \"contacttype\".\"id\"\n" +
            "where \"order\".id = ANY (?)";



    @Override
    public List<OrderDto> getOrdersByUserName(String userName) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql:remontpc"
                , "postgres", "postgres")) {
            List<OrderDto> result = new ArrayList<>();
            result.addAll(getOrdersWithCustomers(connection, userName));

            Map<UUID, OrderDto> orderMap = result.stream()
                    .collect(Collectors.toMap(OrderDto::getId, Function.identity()));

            fillOrderStatuses(connection, orderMap);
            fillExecutedWorks(connection, orderMap);
            fillAddresses(connection, orderMap);
            fillContacts(connection, orderMap);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.EMPTY_LIST;

    }

    private List<OrderDto> getOrdersWithCustomers(Connection connection, String userName) throws SQLException {
        List<OrderDto> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(TESTSQL1)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                result.add(order);
            }
        }
        return result;
    }

    private void fillOrderStatuses(Connection connection, Map<UUID, OrderDto> orderMap) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(TESTSQL2)) {
            ps.setArray(1,
                    connection.createArrayOf("UUID", orderMap.keySet().toArray()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderStatusDto orderStatus = new OrderStatusDto();
                orderStatus.setStatusType(rs.getString("statustype"));
                orderStatus.setUserName(rs.getString("username"));
                orderStatus.setDateTime(rs.getTimestamp("datetime").toLocalDateTime());
                orderMap.get(UUID.fromString(rs.getString("id")))
                        .getOrderStatuses().add(orderStatus);
            }
        }
    }

    private void fillExecutedWorks(Connection connection, Map<UUID, OrderDto> orderMap) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(TESTSQL3)) {
            ps.setArray(1,
                    connection.createArrayOf("UUID", orderMap.keySet().toArray()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExecutedWorkDto executedWork = new ExecutedWorkDto();
                executedWork.setUserName(rs.getString("username"));
                executedWork.setWorkName(rs.getString("workname"));
                executedWork.setCost(rs.getBigDecimal("cost"));
                executedWork.setDateTime(rs.getTimestamp("datetime").toLocalDateTime());
                orderMap.get(UUID.fromString(rs.getString("id")))
                        .getExecutedWorks().add(executedWork);
            }
        }
    }

    private void fillAddresses(Connection connection, Map<UUID, OrderDto> orderMap) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(TESTSQL4)) {
            ps.setArray(1,
                    connection.createArrayOf("UUID", orderMap.keySet().toArray()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddressDto address = new AddressDto();
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setBuildingNumber(rs.getString("building"));
                address.setFlatNumber(rs.getInt("flat"));
                address.setOffice(rs.getString("office"));
                address.setComment(rs.getString("comment"));
                orderMap.get(UUID.fromString(rs.getString("id")))
                        .getCustomer().getAddresses().add(address);
            }
        }
    }

    private void fillContacts(Connection connection, Map<UUID, OrderDto> orderMap) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(TESTSQL5)) {
            ps.setArray(1,
                    connection.createArrayOf("UUID", orderMap.keySet().toArray()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ContactDto contact = new ContactDto();
                contact.setNumber(rs.getString("number"));
                contact.setContactType(rs.getString("type"));
                orderMap.get(UUID.fromString(rs.getString("id")))
                        .getCustomer().getContacts().add(contact);
            }
        }
    }
}
