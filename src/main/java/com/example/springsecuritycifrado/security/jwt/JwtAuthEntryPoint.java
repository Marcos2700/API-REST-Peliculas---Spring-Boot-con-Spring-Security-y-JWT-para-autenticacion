package com.example.springsecuritycifrado.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Permite rechazar peticiones no autorizadas
 * devolviendo un error code 401 unauthorized
 */

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
                            throws IOException, ServletException {
        log.error("Unauthorized error: {}", e.getMessage());

        httpServletResponse.sendError(httpServletResponse.SC_UNAUTHORIZED, "Error: unauthorized");
    }
}
