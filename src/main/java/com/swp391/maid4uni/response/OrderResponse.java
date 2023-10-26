package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Payment;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class OrderResponse {
    int id;
    double price;
    String address;
    Date time;
    int duration;
    String periodType;
    short logicalDeleteStatus;
    @ManyToOne
    @JoinColumn(name = "cutomer_id")
    Account customer;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetailList;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment paymentMethod;
}
