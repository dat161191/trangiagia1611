package com.shoppingbe.jwt.jwt;

import com.shoppingbe.jwt.userprincal.AccountPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);// ghi log trong class jwtprovider
    private String jwtSecret = "quocdat.tran453@gmail.com"; // chữ ký của token
    private int jwtExpiration = 86400; // thời gian sống token 1 ngày

    /**
     * Phương thức createToken() được sử dụng để tạo mã thông báo xác thực (JWT) sau khi người dùng đăng nhập thành công.
     * Sau khi tạo đối tượng AccountPrinciple, phương thức createToken() sử dụng thư viện Jwts của JWT để tạo ra một JWT
     * Phương thức setSubject() để thiết lập tên người dùng (username) là thông tin chủ đề của JWT.
     * Phương thức gọi setIssuedAt() và setExpiration() để thiết lập thời gian phát hành và thời gian hết hạn của JWT.
     * Phương thức signWith() để ký JWT bằng thuật toán mã hóa HS512 và khóa bí mật (jwtSecret).
     * Phương thức compact() được gọi để tạo chuỗi JWT và trả về chuỗi JWT được tạo.
     *
     * @param authentication Đối tượng Authentication được truyền vào phương thức này để truy xuất đối tượng AccountPrinciple của người dùng đã xác thực.
     * @return mã thông báo xác thực (JWT).
     */
    public String createToken(Authentication authentication) {
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(accountPrinciple.getUsername()) // thêm vào
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Giải mã token và lấy ra các claims (thông tin chứa trong token) được đính kèm.
     * Sau đó, phương thức getBody() trả về một đối tượng chứa các claims đã được giải mã và có thể sử dụng để xác thực người dùng.
     * Nếu quá trình giải mã và lấy thông tin thành công, phương thức trả về giá trị true, ngược lại nó sẽ ném ra một exception và phương thức sẽ trả về giá trị false
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) { // valid token
        try {

            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;

        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {Token không hộ trợ}", e);
        } catch (MalformedJwtException e) {
            logger.error("The token invalid format -> Message: {Token không đúng định dạng}", e);

        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature -> Message: {Token không hợp lệ}", e);

        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {Token có khoảng trắng}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {thời gian sống}", e);
        }
        return false;
    }

    /*Lấy ra username từ token mới từ server gởi lên*/
    public String getUserNameFromToken(String token) {
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return username;
    }
}
