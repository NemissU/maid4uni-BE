package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.OrderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {

    private OrderService orderService;

    @GetMapping(API_PARAMS.GET_ORDER_INFO_BY_CUSTOMER)
    public ResponseEntity<ResponseObject> getOrderInfoByCustomer(@PathVariable int id) {
        log.info("Start getOrderInfoByCustomer");
        return ResponseEntity.ok().body(
                new ResponseObject("OK", "GET ORDER INFO BY CUSTOMER ID SUCCESSFULLY", orderService.getOrderInfoByCustomer(id)));
    }
}
