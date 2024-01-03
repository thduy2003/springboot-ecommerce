package com.thduy2003.ecommerce.dto;

import com.thduy2003.ecommerce.entity.Address;
import com.thduy2003.ecommerce.entity.Customer;
import com.thduy2003.ecommerce.entity.Order;
import com.thduy2003.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
