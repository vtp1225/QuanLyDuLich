package com.example.QuanLyDuLich.Service;

import com.example.QuanLyDuLich.Entity.PaymentInformationModel;
import com.example.QuanLyDuLich.dto.Respone.PaymentResponseModel;
import com.example.QuanLyDuLich.utils.VnPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VnPayService {

    @Autowired
    private Environment env;

    @Autowired
    private VnPayUtils utils;

    public String createPaymentUrl(PaymentInformationModel model, HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<>();
        LocalDateTime now = LocalDateTime.now(ZoneId.of(env.getProperty("timezone.id")));

        String txnRef = String.valueOf(System.currentTimeMillis());
        String ipAddress = utils.getIpAddress(request);

        params.put("vnp_Version", env.getProperty("vnpay.version"));
        params.put("vnp_Command", env.getProperty("vnpay.command"));
        params.put("vnp_TmnCode", env.getProperty("vnpay.tmn-code"));
//        params.put("vnp_Amount", String.valueOf((int)(model.getAmount() * 100)));
        BigDecimal amount = model.getAmount(); // BigDecimal
        int amountInt = amount.multiply(BigDecimal.valueOf(100)).intValue();

        params.put("vnp_CreateDate", now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        params.put("vnp_CurrCode", env.getProperty("vnpay.curr-code"));
        params.put("vnp_IpAddr", ipAddress);
        params.put("vnp_Locale", env.getProperty("vnpay.locale"));
        params.put("vnp_OrderInfo", model.getName() + " " + model.getOrderDescription());
        params.put("vnp_OrderType", model.getOrderType());
        params.put("vnp_ReturnUrl", env.getProperty("vnpay.return-url"));
        params.put("vnp_TxnRef", txnRef);

        String query = utils.buildQuery(params);
        String hash = utils.hmacSHA512(env.getProperty("vnpay.hash-secret"), query);

        return env.getProperty("vnpay.base-url") + "?" + query + "&vnp_SecureHash=" + hash;

//        return "https://sandbox.vnpayment.vn" + env.getProperty("vnpay.base-url") + "?" + query + "&vnp_SecureHash=" + hash;
    }

    public PaymentResponseModel paymentExecute(Map<String, String> vnpParams) throws Exception {
        System.out.println("VNPAY callback params: " + vnpParams);
        String inputHash = vnpParams.remove("vnp_SecureHash");
        vnpParams.remove("vnp_SecureHashType");

        String hashData = utils.buildQuery(vnpParams);
        String calculatedHash = utils.hmacSHA512(env.getProperty("vnpay.hash-secret"), hashData);

        boolean isValid = calculatedHash.equalsIgnoreCase(inputHash);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime time = LocalDateTime.parse(vnpParams.get("vnp_PayDate"), formatter);

        PaymentResponseModel response = new PaymentResponseModel();
        response.setId(UUID.randomUUID().toString());
        response.setSuccess(isValid);
        response.setPaymentMethod("VnPay");
        response.setToken(inputHash);
        response.setOrderId(vnpParams.get("vnp_TxnRef"));
        response.setTransactionId(vnpParams.get("vnp_TransactionNo"));
        response.setPaymentId(vnpParams.get("vnp_TransactionNo"));
        response.setOrderDescription(vnpParams.get("vnp_OrderInfo"));
        response.setVnPayResponseCode(vnpParams.get("vnp_ResponseCode"));
        response.setTransactionTime(time);

        return response;
    }
}

