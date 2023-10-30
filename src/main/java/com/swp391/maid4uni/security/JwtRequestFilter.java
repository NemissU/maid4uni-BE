package com.swp391.maid4uni.security;

import com.swp391.maid4uni.converter.AccountConverter;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.model.TokenPayload;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.ulti.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Jwt request filter.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtRequestFilter extends OncePerRequestFilter {
    JwtTokenUtil jwtTokenUtil;
    AccountRepository accountRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        // kiểm tra xem header authorization có chứa thông tin jwt k
        String token = null;
        TokenPayload tokenPayload = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.split(" ")[1].trim();
            try {
                tokenPayload = jwtTokenUtil.getTokenPayload(token);
            } catch (ExpiredJwtException ex) {
                System.out.println("Token is expired");
            }

        } else {
            System.out.println("JWT not start with Bearer");
        }

        if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = accountRepository.findById(tokenPayload.getAccountId());
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                if (jwtTokenUtil.isValid(token, AccountConverter.INSTANCE.fromAccountToTokenPayload(account))) {
                    // Tạo user detail -> lưu vào context holder
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    UserDetails userDetails = new User(account.getUsername(), account.getPassword(), authorities);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);// đánh dấu người dùng đã đăng nhập
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
