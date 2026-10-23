package com.glovoCall.demo.JWT;


import com.glovoCall.demo.UsersPack.CustomUserDetails;
import com.glovoCall.demo.UsersPack.CustomUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserService customUserService;
    private final ServletRequest httpServletRequest;

    public JWTFilter(JWTService jwtService, CustomUserService customUserService, ServletRequest httpServletRequest) {
        this.jwtService = jwtService;
        this.customUserService = customUserService;
        this.httpServletRequest = httpServletRequest;
    }

    private String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if("jwt".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;

    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        String token=getTokenFromCookie(request);
        if(token!=null&&jwtService.validationOfJwtToken(token)){
            CustomUserDetails customUserDetails= customUserService.loadUserByUsername
                    (jwtService.getEmailFromToken(token));
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(customUserDetails,null,
                            customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);
    }
}
