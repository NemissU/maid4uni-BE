package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.PaymentConverter;
import com.swp391.maid4uni.converter.VNPayConverter;
import com.swp391.maid4uni.dto.PaymentDto;
import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.repository.OrderRepository;
import com.swp391.maid4uni.repository.PaymentRepository;
import com.swp391.maid4uni.response.PaymentResponse;
import com.swp391.maid4uni.response.VNPayResponse;
import com.swp391.maid4uni.service.VNPayService;
import com.swp391.maid4uni.ulti.VNPayConfig;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class VNPayServiceImpl implements VNPayService {
    OrderRepository orderRepository;
    PaymentRepository paymentRepository;
    @Override
    public String createPayment(int orderTotal, String orderInfo) {

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);

        String vnp_HashSecret =
                "ERSLGMSNNZNDWPOQLJZWABJHKXTCMEEI";
        String vnp_TmnCode =
                "L801F6ZJ";

        String orderType = "250006";
        orderTotal = Math.round(orderTotal * 100);
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(orderTotal));
        vnp_Params.put("vnp_CurrCode", VNPayConfig.vnp_CurrCode);
        vnp_Params.put("vnp_BankCode", VNPayConfig.vnp_BankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", VNPayConfig.vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        cld.add(Calendar.DATE, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.DATE, 2);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    @Override
    public VNPayResponse getVNPayPayment(PaymentDto dto, int orderId) {
        Payment p = PaymentConverter.INSTANCE.fromDtoToEntity(dto);
        Order o = orderRepository.findByIdAndLogicalDeleteStatus(orderId,0);
        p.setOrder(o);
        o.setPayment(p);
        paymentRepository.save(p);
        orderRepository.save(o);
        VNPayResponse res = VNPayConverter.INSTANCE.fromPayMentToVNPayResponse(p);
        return res;
    }
}
