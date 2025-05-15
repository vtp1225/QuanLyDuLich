package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.VnPayService;
import com.example.QuanLyDuLich.dto.Request.PaymentInformationModel;
import com.example.QuanLyDuLich.dto.Respone.PaymentResponseModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private VnPayService vnPayService;

@PostMapping("/create-vnpay")
public ResponseEntity<?> createPayment(@RequestBody PaymentInformationModel model,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    try {
        // Validate input model
        if (model.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Số tiền thanh toán phải lớn hơn 0");
        }

        // Create payment URL
        String paymentUrl = vnPayService.createPaymentUrl(model, request);

        // Option 1: Return URL as JSON (recommended for API clients)
        Map<String, String> responseData = new HashMap<>();
        responseData.put("paymentUrl", paymentUrl);
        responseData.put("method", "GET");
        return ResponseEntity.ok(responseData);

    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        System.out.println("Failed to create VNPAY payment URL " + e.getMessage());
        return ResponseEntity.internalServerError().body("Lỗi hệ thống khi tạo URL thanh toán");
    }
}


@GetMapping("/vnpay-callback")
public ResponseEntity<?> vnpayCallback(
        @RequestParam Map<String, String> vnpParams) throws Exception {
    try {
        System.out.println("VNPAY callback params: " + vnpParams);
        PaymentResponseModel result = vnPayService.paymentExecute(vnpParams);
        if (result.isSuccess() && "00".equals(result.getVnPayResponseCode())){
            return ResponseEntity.ok(result);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return ResponseEntity.ok().build();
}

//    @GetMapping("/vnpay-callback")
//public ResponseEntity<?> vnpayCallback(
//        @RequestParam Map<String, String> vnpParams,
//        HttpServletResponse response) throws IOException {
//
//    try {
//        PaymentResponseModel result = vnPayService.paymentExecute(vnpParams);
//
//        // Nếu thành công (code "00")
//        if (result.isSuccess() && "00".equals(result.getVnPayResponseCode())) {
//            // Chuyển hướng đến trang thành công với thông tin thanh toán
//            String redirectUrl = String.format(
////                    "https://your-frontend-domain.com/payment/success?orderId=%s&amount=%s",
//                    "/vnpay/success.html?orderId=%s&amount=%s&transactionTime=%s",
//                    result.getOrderId(),
//                    vnpParams.get("vnp_Amount"),
//                    result.getTransactionTime()
//            );
//            response.sendRedirect(redirectUrl);
//            return null;
//        } else {
//            // Chuyển hướng đến trang thất bại
////            response.sendRedirect("https://your-frontend-domain.com/payment/failed");
//            response.sendRedirect("/vnpay/failed.html?error=" + URLEncoder.encode("Giao dịch thất bại", "UTF-8"));
//            return null;
//        }
//
//    } catch (Exception e) {
//        System.out.println("VNPAY callback error " + e.getMessage());
////        response.sendRedirect("https://your-frontend-domain.com/payment/error");
//        response.sendRedirect("/vnpay/error.html");
//        return null;
//
//    }
//}


}
