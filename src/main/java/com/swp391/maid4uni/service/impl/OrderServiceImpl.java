package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.AccountConverter;
import com.swp391.maid4uni.converter.OrderConverter;
import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.converter.TaskConverter;
import com.swp391.maid4uni.dto.*;
import com.swp391.maid4uni.entity.*;
import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.enums.PeriodType;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.*;
import com.swp391.maid4uni.request.OrderRequest;
import com.swp391.maid4uni.response.OrderResponse;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        dto.setCreatedAt(LocalDateTime.now());
        Package pkg = packageRepository.findByIdAndLogicalDeleteStatus(dto.getPackageDto().getId(), 0);
        dto.setPrice(pkg.getPrice());
        if (dto.getPeriodType().equals(PeriodType.ONE_MONTH)) {
            dto.setEndDay(dto.getStartDay().atStartOfDay().toLocalDate().plus(Period.ofDays(30))); // fix cứng, logic tính sau
        } else {
            dto.setEndDay(dto.getStartDay().atStartOfDay().toLocalDate().plus(Period.ofDays(60)));
        }
        // workday sẽ handle = cách chạy for rồi cộng thêm dần dần vào
        // for

        Order order = converter.fromDtoToEntity(dto);

        order.setAPackage(pkg);
        order.setCustomer(getCustomer);
        orderRepository.save(order);

        ArrayList<Integer> workDayList = dto.getWorkDay();
        List<OrderDetail> orderDetailList = new ArrayList<>();

        LocalDate currentDate = order.getStartDay().atStartOfDay().toLocalDate();
        LocalDate actualStartDate = currentDate;
        int firstWorkDay = workDayList.get(0);
        DayOfWeek day = actualStartDate.getDayOfWeek();
        while ( day.getValue()+1 != firstWorkDay) {
            firstWorkDay += 1;
        }
        actualStartDate = actualStartDate.plus(Period.ofDays(firstWorkDay));
        LocalDate workDay = actualStartDate;
        if (order.getPeriodType().equals(PeriodType.ONE_MONTH)) {
            for (int i = 0; i < 4; i++) {
                workDay = getWorkDay(order, workDayList, orderDetailList, workDay);
            }
        } else {
            for (int i = 0; i < 8; i++) {
                workDay = getWorkDay(order, workDayList, orderDetailList, workDay);
            }
        }
        orderDetailRepository.saveAll(orderDetailList);
        OrderResponse response = OrderConverter.INSTANCE.fromOrderToOrderResponse(order);
        // payment set sau khi thanh toán thành công
        return response;
    }

    private LocalDate getWorkDay(Order order, ArrayList<Integer> workDayList, List<OrderDetail> orderDetailList, LocalDate workDay) {
        for (int j = 0; j < workDayList.size(); j++) {
            OrderDetail od = createOrderDetail(order);
            while (workDay.getDayOfWeek().getValue() != workDayList.get(j) ){
                workDay = workDay.atStartOfDay().toLocalDate().plus(Period.ofDays(1));
            }
            od.setWorkDay(workDay);
            orderDetailList.add(od);
        }
        workDay = workDay.atStartOfDay().toLocalDate().plusDays(7-workDayList.get(0)-1);
        return workDay;
    }

    public OrderDetail createOrderDetail(Order order) {
        Duration d = Duration.ofHours(order.getDuration());
        OrderDetail detail = OrderDetail
                .builder()
                .order(order)
                //todo handle logic sau
                .status(false)
                .startTime(order.getStartTime())
                //todo handle plus
                .endTime(order.getStartTime().plus(d))
                .build();
        List<Task> taskList = new ArrayList<>();
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
                    //todo handle list
                    .staffs(staffList)
                    .belongedTrackers(trackerList)
                    .build();
            //todo
            // sửa cái mớ này lại
            taskRepository.save(task);
        }
//        orderDetailRepository.save(detail);
        return detail;
    }



}
