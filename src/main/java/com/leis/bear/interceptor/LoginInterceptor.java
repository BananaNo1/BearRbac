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

import java.io.IOException;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     *  在请求处理之前被调用。
     * @param request
     * @param response
     * @param handler
     * @return  返回 true 表示继续处理请求，返回 false 表示请求被拦截。
     * @throws Exception
     */
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
                setResponseData(response, "token不存在");
                return false;
            }
            try {
                Long userId = jwtUtil.parseToken(token);
                log.info("*************userId:::" + userId);
            } catch (Exception e) {
                setResponseData(response, "非法token");
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * 在整个请求完成之后被调用，即视图渲染之后。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private void setResponseData(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"status\":401,\"message\":\"" + message + "\"}");
    }
}
