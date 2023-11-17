package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.OrderConverter;
import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.dto.OrderDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.request.UpdateOrderRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.OrderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderController {
    OrderService orderService;

    @GetMapping(API_PARAMS.GET_ORDER_LIST_BY_CUSTOMER)
    public ResponseEntity<ResponseObject> getOrderListByCustomer(@PathVariable int id) {
        log.info("Start getOrderInfoByCustomer");
        return ResponseEntity.ok().body(
                new ResponseObject("OK", "GET ORDER LIST BY CUSTOMER ID SUCCESSFULLY", orderService.getOrderInfoByCustomer(id)));
    }

    @PostMapping(API_PARAMS.CREATE_ORDER)
    public ResponseEntity<ResponseObject> createOrder(@RequestBody OrderRequest request){
        log.info("Start createOrder");
        OrderDto dto = OrderConverter.INSTANCE.fromOrderRequestToOrderDto(request);
        dto.setPackageDto(PackageConverter.INSTANCE.map(request.getPkgDuty()));
        return ResponseEntity.ok().body(
                new ResponseObject("OK", "CREATE ORDER", orderService.createOrder(dto)));
    }

    @PutMapping(API_PARAMS.UPDATE_ORDER_STATUS)
    public ResponseEntity<ResponseObject> updateOrderStatus(@RequestBody UpdateOrderRequest request){
        log.info("Start updateOrderStatus");
        return ResponseEntity.ok().body(
                orderService.updateOrderStatus(request));
    }

    @GetMapping(API_PARAMS.GET_ALL_ORDER)
    public ResponseEntity<ResponseObject> getAllOrder(@PathVariable int page) {
        log.info("Start get all order");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        , "GET ALL PACKAGE SUCCESSFULLY"
                        , orderService.getAllOrder(page))
        );
    }
}
