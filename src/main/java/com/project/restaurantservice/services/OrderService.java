package com.project.restaurantservice.services;

import com.project.restaurantservice.models.Courier;
import com.project.restaurantservice.models.Order;
import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Long addNewOrder(Long userId, String street, String city, String zip, String dateStr) {
        Order order = new Order(userId, street, city, zip, dateStr);
        System.out.println(order);
        return orderRepository.save(order).getOrderNumber();
    }

    public void assignCourier(String zip, Long orderNumber) {
        orderRepository.assignCourierByZipAndOrderNumber(zip, orderNumber);
    }

    public void assignOrderProducts(List<Product> chosenProducts, Long orderNumber) {
        for (Product p : chosenProducts) {
            orderRepository.assignOrderProducts(p.getProductId(), orderNumber);
        }
    }
}
