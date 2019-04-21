package com.hellodoctor.account.config;

import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.user.UserDTO;
import com.hellodoctor.common.response.GenericResponse;
import com.hellodoctor.common.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

/**
 * @author Khoa
 * @created 4/21/2019
 */
@Component
@Slf4j
public class CustomTokenFilter extends GenericFilterBean {
    private static final String TOKEN_KEY = "Authorization";
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ApiRuntimeException(ReturnCode.BAD_REQUEST, "Expecting a HTTP request");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(TOKEN_KEY);


        if (token != null && !token.isEmpty() && token.startsWith("Bearer ")) {
            token = token.substring(7);
            UserDTO userDTO;
            try {

                userDTO = jwtUtils.decode(token);
            } catch (Exception e) {
                logger.warn("Invalid token: {}", e);
                GenericResponse error = buildInvalidTokenError();

                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(401);
                res.setContentType("application/json");

                PrintWriter writer = res.getWriter();
                writer.print(error.getCode() + ": " + error.getMessage());
                writer.flush();

                return;
            }

            if (userDTO != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDTO, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private GenericResponse buildInvalidTokenError() {
        GenericResponse response = new GenericResponse(ReturnCode.INVALID_TOKEN, ReturnCode.INVALID_TOKEN.name() );
        return response;
    }
}
