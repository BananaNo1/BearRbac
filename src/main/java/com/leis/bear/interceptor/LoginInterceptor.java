package com.leis.bear.interceptor;

import cn.hutool.core.util.StrUtil;
import com.leis.bear.annonations.NeedLogin;
import com.leis.bear.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("*************进入拦截器*****************");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NeedLogin methodAnnotation = handlerMethod.getMethodAnnotation(NeedLogin.class);
            if (methodAnnotation == null) {
                return true;
            }
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                return false;
            }
            Long userId = jwtUtil.parseToken(token);
            log.info("*************userId:::" + userId);
            return true;
        }
        return true;
    }
}
