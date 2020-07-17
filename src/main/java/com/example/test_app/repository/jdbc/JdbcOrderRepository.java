package com.example.test_app.repository.jdbc;

import com.example.test_app.dto.CustomerDto;
import com.example.test_app.dto.OrderDto;
import com.example.test_app.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcOrderRepository.class);
    private static final String TESTSQL = "SELECT \"order\".id                  as id,\n" +
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

    @Override
    public List<OrderDto> getOrdersByUserName(String userName) {
        List<OrderDto> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql:remontpc", "postgres", "postgres")) {
            try (PreparedStatement statement = connection.prepareStatement(TESTSQL)) {
                statement.setString(1, userName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    OrderDto order1 = new OrderDto();
                    CustomerDto customer1 = new CustomerDto();
                    customer1.setId(UUID.fromString(resultSet.getString("customerid")));
                    customer1.setName(resultSet.getString("customername"));
                    customer1.setEmail(resultSet.getString("customeremail"));
                    customer1.setCustomerType(resultSet.getString("customertype"));
                    customer1.setLegalType(resultSet.getString("legaltype"));
                    customer1.setComment(resultSet.getString("customercomment"));
                    order1.setId(UUID.fromString(resultSet.getString("id")));
                    order1.setCustomer(customer1);
                    order1.setDateCreated(resultSet.getTimestamp("datecreated").toLocalDateTime());
                    order1.setProblemDescription(resultSet.getString("problem"));
                    order1.setUserName(userName);
                    result.add(order1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;

    }
}
