package com.swp391.maid4uni.ulti;

import com.swp391.maid4uni.model.TokenPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The type Jwt token util.
 */
@Component
public class JwtTokenUtil {
    @Value("${JWT_SECRET_KEY}")
    private String secret;

    /**
     * Generate token string.
     *
     * @param tokenPayload the token payload
     * @param expiredDate  the expired date
     * @return the string
     */
    public String generateToken(TokenPayload tokenPayload, long expiredDate) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("payload", tokenPayload);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * Gets token payload.
     *
     * @param token the token
     * @return the token payload
     */
    public TokenPayload getTokenPayload(String token) {
        return getClaimsFromToken(token, (Claims claim) -> {
            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
            return TokenPayload.builder()
                    .accountId((Integer) mapResult.get("acountId"))
                    .username((String) mapResult.get("username"))
                    .build();
        });
    }

    /**
     * Gets claims from token.
     *
     * @param <T>           the type parameter
     * @param token         the token
     * @param claimResolver the claim resolver
     * @return the claims from token
     */
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);
    }

    /**
     * Is valid boolean.
     *
     * @param token               the token
     * @param tokenPayFromAccount the token pay from account
     * @return the boolean
     */
    public boolean isValid(String token, TokenPayload tokenPayFromAccount) {
        if (isTokenExpired(token)) {
            return false;
        }
        TokenPayload tokenPayload = getTokenPayload(token);
        return tokenPayload.getAccountId() == tokenPayFromAccount.getAccountId()
                && tokenPayload.getUsername().equals(tokenPayFromAccount.getUsername());
    }

    private boolean isTokenExpired(String token) {
        Date expiredTime = getClaimsFromToken(token, Claims::getExpiration);
        return expiredTime.before(new Date());
    }
}
