package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.OrderDetailConverter;
import com.swp391.maid4uni.dto.OrderDetailDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.OrderDetailRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PutMapping(API_PARAMS.UPDATE_ORDER_DETAIL)
    public ResponseEntity<ResponseObject> updateOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest){
        log.info("Start update order detail by id");
        OrderDetailDto dto = OrderDetailConverter.INSTANCE.fromOrderDetailRequestToOrderDetailDto(orderDetailRequest);
        return ResponseEntity.ok().body(new ResponseObject("OK"
                ,"UPDATE ORDER DETAIL BY ID SUCCESSFULLY"
                , orderDetailService.updateOrderDetail(dto)));
    }

    @GetMapping(API_PARAMS.GET_ORDER_DETAIL_BY_ORDERID)
    public ResponseEntity<ResponseObject> getOrderDetailByOrderId(@PathVariable int id){
        log.info("Start get order detail by orderId");
        return ResponseEntity.ok().body(new ResponseObject("OK"
                , "GET ORDER DETAIL BY ORDER ID SUCCESSFULLY"
                , orderDetailService.getOrderDetailByOrderId(id)));
    }
}
