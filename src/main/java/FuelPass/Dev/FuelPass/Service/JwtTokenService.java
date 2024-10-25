package FuelPass.Dev.FuelPass.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.security.SignatureException;

@Service
public class JwtTokenService {


    public String getUsernameFromToken(String token) {
        try {
            String secretKey = "6C4543356736366869784D4146597141767350654149505770466237696E3864";
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {

            return null;
        }
    }
}