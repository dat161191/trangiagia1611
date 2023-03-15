package com.shoppingbe.jwt.jwt;

import com.shoppingbe.jwt.userprincal.AccountDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {// tìm kím token trong request
    /*Ghi log lỗi vô file*/
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AccountDetailService accountDetailService;

    /**
     * Đây là phương thức thực hiện việc kiểm tra token được gửi trong header của request và xác thực người dùng dựa trên token này.
     * Nếu token hợp lệ, phương thức sẽ lấy tên người dùng từ token, tải chi tiết người dùng từ database bằng cách sử dụng AccountDetailService.
     * Tạo một đối tượng UsernamePasswordAuthenticationToken và lưu trữ nó trong SecurityContextHolder để xác thực người dùng trong toàn bộ phiên làm việc.
     * Sau đó, phương thức gọi filterChain để cho request được tiếp tục đi qua các filter khác.
     * Nếu có bất kỳ lỗi nào xảy ra, phương thức sẽ ghi log lỗi và cho phép request tiếp tục đi qua các filter khác mà không được xác thực.
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwt(request);// lấy token trong rq
            System.out.println("====>Token" + token);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUserNameFromToken(token);
                System.out.println("=====>Username " +username);
                UserDetails userDetails = accountDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.error("Can't set user authentication -> Message: {Không thể set account vào bảo mật}", e);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Phương thức này có chức năng lấy token JWT từ header của request.
     *
     * @param request
     * @return
     */
    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("==>>" + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            return authHeader.replace("Bearer", "");
        }
        return null;
    }
}
