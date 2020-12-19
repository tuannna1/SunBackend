package me.sunrise.security.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    @Value("${jwtSecret}")
    private String jwtSecret;

    //Thời gian có hiệu lực của chuỗi jwt
    @Value("${jwtExpiration}")
    private int jwtExpiration;

    //Tạo ra token từ chuỗi authentication!
    public String generate(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Tạo chuỗi json web token từ username của user.
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //check token
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error("JWT Authentication Failed");
        }
        return false;
    }

    //Lấy UserAccount từ token đã được mã hóa
    public String getUserAccount(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody().getSubject();
    }
}
