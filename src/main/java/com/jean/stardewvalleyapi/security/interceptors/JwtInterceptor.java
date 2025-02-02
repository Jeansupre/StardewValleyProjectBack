package com.jean.stardewvalleyapi.security.interceptors;

import com.jean.stardewvalleyapi.security.annotations.RequireJwtVerification;
import com.jean.stardewvalleyapi.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final AdminServiceImpl adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler instanceof HandlerMethod handlerMethod) {
            RequireJwtVerification annotation = handlerMethod.getMethodAnnotation(RequireJwtVerification.class);

            if (annotation == null) {
                annotation = handlerMethod.getBeanType().getAnnotation(RequireJwtVerification.class);
            }

            if (annotation != null) {
                adminService.verificacionAdmin();
            }
        }
        return true;
    }
}

