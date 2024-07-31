package com.leis.bear.interceptor;

import cn.hutool.core.util.StrUtil;
import com.leis.bear.annonations.NeedPermissions;
import com.leis.bear.domain.Menu;
import com.leis.bear.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NeedPermissions methodAnnotation = handlerMethod.getMethodAnnotation(NeedPermissions.class);
            if (methodAnnotation == null) {
                return true;
            }
            String permission = methodAnnotation.permission();
            if (StrUtil.isBlank(permission)) {
                return true;
            }
            List<Menu> menus = userService.selectMenuByUserId(1);
            Set<String> collect = menus.stream().map(Menu::getPerms).collect(Collectors.toSet());
            log.info("请求需要的权限:{}", permission);
            if (collect.contains(permission)) {
                return true;
            }
            setResponseData(response, "权限不足");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void setResponseData(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"status\":401,\"message\":\"" + message + "\"}");
    }
}
