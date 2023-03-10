package com.shoppingbe.jwt.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {// check xem token còn sống ko

    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    /*Nhận vào một đối tượng HttpServletRequest, HttpServletResponse, và AuthenticationException để xử lý lỗi xác thực.
    Sử dụng lớp Logger để ghi lại thông báo lỗi không xác thực và chi tiết lỗi được cung cấp bởi AuthenticationException.
    Sử dụng phương thức sendError của HttpServletResponse để trả về mã lỗi HTTP 401 và thông báo lỗi "Error -> Unauthorized".*/
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error Message: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_ACCEPTED, "Error -> Unauthorized");
    }
}
