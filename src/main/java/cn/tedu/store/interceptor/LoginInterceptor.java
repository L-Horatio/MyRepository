package cn.tedu.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author L-Horatio
 * @date 2020/6/1
 * @time 11:13
 */

/**
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取Session
        HttpSession session = request.getSession();
        // 判断Session中是否存在uid
        if (session.getAttribute("uid") == null) {
            // 为Null即没有uid，没有登陆，重定向到登陆页面
            response.sendRedirect("../web/login.html");
            // 拦截
            return false;
        } else {
            // 非null，即存在uid，已经登陆
            return true;
        }
    }
}
