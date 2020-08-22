package com.example.test_app.service;

import com.example.test_app.dto.*;
import com.example.test_app.persistence.dao.tables.OrderDao;
import com.example.test_app.persistence.entity.tables.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getOrderByUserName(String userName){
        return orderDao.findAllByUserName(userName).stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    private OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDateCreated(order.getDateCreated());
        orderDto.setCustomer(toCustomerDto(order.getCustomer()));
        orderDto.setUserName(order.getUser().getName());
        orderDto.setProblemDescription(order.getProblemDescription());

        orderDto.setOrderStatuses(order.getOrderStatuses().stream()
        .map(this::toOrderStatusDto).collect(Collectors.toList()));

        orderDto.setExecutedWorks(order.getExecutedWorks().stream()
        .map(this::toExecutedWorkDto).collect(Collectors.toList()));

        return orderDto;
    }

    private ExecutedWorkDto toExecutedWorkDto(ExecutedWork executedWork) {
        ExecutedWorkDto dto = new ExecutedWorkDto();
        dto.setDateTime(executedWork.getDateTime());
        dto.setCost(executedWork.getCost());
        dto.setWorkName(executedWork.getName());
        dto.setUserName(executedWork.getUser().getName());
        return dto;
    }

    private OrderStatusDto toOrderStatusDto(OrderStatus orderStatus) {
        OrderStatusDto dto = new OrderStatusDto();
        dto.setDateTime(orderStatus.getDateTime());
        dto.setUserName(orderStatus.getUser().getName());
        dto.setStatusType(orderStatus.getStatusType().getValue());
        return dto;
    }

    private CustomerDto toCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setComment(customer.getComment());
        dto.setLegalType(customer.getLegalType().getValue());
        dto.setCustomerType(customer.getCustomerType().getValue());
        dto.setEmail(customer.getEmail());

        dto.setAddresses(customer.getAddresses().stream()
        .map(this::toAddressDto).collect(Collectors.toList()));

        dto.setContacts(customer.getContacts().stream()
        .map(this::toContactDto).collect(Collectors.toList()));

        return dto;
    }

    private ContactDto toContactDto(Contact contact) {
        ContactDto dto = new ContactDto();
        dto.setNumber(contact.getNumber());
        dto.setContactType(contact.getContactType().getValue());
        return dto;
    }

    private AddressDto toAddressDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setBuildingNumber(address.getBuildingNumber());
        dto.setFlatNumber(address.getFlatNumber());
        dto.setOffice(address.getOffice());
        dto.setComment(address.getComment());
        return dto;
    }
}
