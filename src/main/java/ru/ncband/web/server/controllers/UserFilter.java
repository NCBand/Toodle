package ru.ncband.web.server.controllers;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.Property;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements HandlerInterceptor {

    private boolean isUserRequest(String uri){
        return  uri.contains("settings")||
                uri.contains("task") ||
                uri.contains("stat") ||
                uri.contains("sign_out")||
                uri.contains("message");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(isUserRequest(uri)) {
            HttpSession session = request.getSession();
            Id id = (Id) session.getAttribute(Property.sessionName());
            return id != null && id.getHash().equals(Salt.sha3(id.getId()));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o,
                                Exception e) throws Exception {

    }

}
