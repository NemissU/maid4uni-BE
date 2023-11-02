package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.OrderConverter;
import com.swp391.maid4uni.dto.*;
import com.swp391.maid4uni.entity.*;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.enums.OrderStatus;
import com.swp391.maid4uni.enums.PeriodType;
import com.swp391.maid4uni.enums.Role;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.*;
import com.swp391.maid4uni.request.UpdateOrderRequest;
import com.swp391.maid4uni.response.OrderResponse;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.OrderService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private AccountRepository accountRepository;
    private PackageRepository packageRepository;
    private TaskRepository taskRepository;
    private OrderDetailRepository orderDetailRepository;
    private OrderConverter converter = OrderConverter.INSTANCE;

    @Override
    public List<OrderResponse> getOrderInfoByCustomer(int id) {
        List<Order> orderList = orderRepository.findAllByCustomerIdAndLogicalDeleteStatus(id, (short) 0);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)) {
            orderResponseList = orderList.stream()
                    .map(converter::fromOrderToOrderResponse)
                    .toList();
        }
        return orderResponseList;
    }

    @Override
    public OrderResponse createOrder(OrderDto dto) {
        Account getCustomer = accountRepository
                .findAccountByIdAndLogicalDeleteStatus(dto.getCustomer().getId(), 0);
        if (getCustomer == null) {
            throw Maid4UniException.notFound("Not found Customer info.");
        }
        dto.setCreatedAt(LocalDateTime.now()); //có thể không cần thiết vì đã dùng @CreatedTimeStamp
        Package pkg = packageRepository.findByIdAndLogicalDeleteStatus(dto.getPackageDto().getId(), 0);
        dto.setPrice(pkg.getPrice());
        if (dto.getPeriodType().equals(PeriodType.ONE_MONTH)) {
            dto.setEndDay(dto.getStartDay().atStartOfDay().toLocalDate().plus(Period.ofDays(30))); // fix cứng, logic tính sau
        } else {
            dto.setEndDay(dto.getStartDay().atStartOfDay().toLocalDate().plus(Period.ofDays(60)));
        }
        Order order = converter.fromDtoToEntity(dto);

        // convert từ array qua string
        String workDay = dto.getWorkDay().toString();
        workDay = workDay.substring(1, workDay.length() - 1);

        order.setWorkDay(workDay);
        order.setAPackage(pkg);
        order.setCustomer(getCustomer);
        order.setOrderStatus(OrderStatus.WAITING_FOR_APPROVAL);
        orderRepository.save(order);

        OrderResponse response = OrderConverter.INSTANCE.fromOrderToOrderResponse(order);
        // payment set sau khi thanh toán thành công
        return response;
    }

    @Override
    public ResponseObject updateOrderStatus(UpdateOrderRequest request) {
        Order order = orderRepository.findByIdAndLogicalDeleteStatus(request.getId(), 0);
        OrderStatus status = request.getStatus();
        String paymentStatus = order.getPayment().getPaymentStatus();
        // truong hop chua thanh toan
        if (paymentStatus.equals("Failed")) {
            order.setOrderStatus(OrderStatus.DECLINED);
            return new ResponseObject("FAILED", "PAYMENT STATUS IS `FAILED`", OrderConverter.INSTANCE.fromOrderToOrderResponse(order));
        }
        // truong hop da thanh toan && duyet thanh cong
        if (status.equals(OrderStatus.APPROVED) && paymentStatus.equals("Success")) {
            createOrderDetail(order);
            order.setOrderStatus(status);
            return new ResponseObject("OK", "SUCCESSFULLY CREATE ORDER", OrderConverter.INSTANCE.fromOrderToOrderResponse(order));
        }
        // truong hop thanh toan nhung huy order
        order.setOrderStatus(OrderStatus.DECLINED);
        return new ResponseObject("OK", "ORDER IS DECLINED, CONTACT HOTLINE FOR REFUND INFO", OrderConverter.INSTANCE.fromOrderToOrderResponse(order));
    }

    private List<OrderDetail> getWorkDay(Order order, ArrayList<Integer> workDayList, List<OrderDetail> orderDetailList, LocalDate workDay) {
        Duration d = Duration.ofHours(order.getDuration());
        for (int j = 0; j < workDayList.size(); j++) {
            OrderDetail detail = OrderDetail
                    .builder()
                    .order(order)
                    .status(false)
                    .startTime(order.getStartTime())
                    .endTime(order.getStartTime().plus(d))
                    .build();
            while (workDay.getDayOfWeek().getValue() != workDayList.get(j) ){
                workDay = workDay.atStartOfDay().toLocalDate().plus(Period.ofDays(1));
            }

            detail.setWorkDay(workDay);
            orderDetailList.add(detail);
        }
        return orderDetailList;
    }

    public void createOrderDetail(Order order) {
        ArrayList<String> workDayArr = new ArrayList<>(Arrays.asList(order.getWorkDay().split(", ")));
        ArrayList<Integer> workDayList = new ArrayList<>();
        for (int i = 0; i < workDayArr.size(); i++) {
            workDayList.add(Integer.parseInt(workDayArr.get(i)));
        }
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Collections.sort(workDayList);
        LocalDate currentDate = order.getStartDay().atStartOfDay().toLocalDate();
        LocalDate actualStartDate = currentDate;
        int firstWorkDay = workDayList.get(0);
        DayOfWeek day = actualStartDate.getDayOfWeek();
        while ( day.getValue()+1 != firstWorkDay) {
            firstWorkDay += 1;
        }
        actualStartDate = actualStartDate.plus(Period.ofDays(firstWorkDay));
        LocalDate workDay = actualStartDate;
        Duration d = Duration.ofHours(order.getDuration());
        if (order.getPeriodType().equals(PeriodType.ONE_MONTH)) {
            for (int i = 0; i < 4; i++) {
                workDay = workDay.atStartOfDay().toLocalDate().plusDays(7-workDayList.get(0)-1);
                orderDetailList = getWorkDay(order, workDayList, orderDetailList, workDay);
            }
        } else {
            for (int i = 0; i < 8; i++) {
                workDay = workDay.atStartOfDay().toLocalDate().plusDays(7-workDayList.get(0)-1);
                orderDetailList = getWorkDay(order, workDayList, orderDetailList, workDay);
            }
        }
        orderDetailRepository.saveAll(orderDetailList);
        //TODO: DONE ORDER DETAIL


        //TODO: XỬ LÝ STAFF - XẾP LỊCH
        for (OrderDetail ord:orderDetailList) {
            for (com.swp391.maid4uni.entity.Service item: order.getAPackage().getServiceList()) {
                List<Account> staffList = accountRepository.findByRoleAndLogicalDeleteStatus(Role.STAFF, (short) 0);
                List<Tracker> trackerList = new ArrayList<>();
                for (Account staff: staffList) {
                    trackerList.add(staff.getTracker());
                }
                Task task = Task
                        .builder()
                        .status(false)
                        .service(item)
                        .orderDetail(ord)
                        //todo handle list
                        .staffs(staffList)
                        .belongedTrackers(trackerList)
                        .build();
                //todo
                // sửa cái mớ này lại
                taskRepository.save(task);

            }
        }

//        orderDetailRepository.save(detail);
    }
    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                log.info("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }

    private String convertToString(ArrayList<Integer> inputList) {
        StringBuilder sb = new StringBuilder();
        for (Integer element : inputList) {
            sb.append(element).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);  // Xóa dấu ',' cuối cùng
        }
        return sb.toString();
    }


}
