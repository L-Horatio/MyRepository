package cn.tedu.store.configurer;

import cn.tedu.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/1
 * @time 11:49
 */

/**
 * 拦截器注册
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 黑名单
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/user/**");

        // 白名单
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/reg.do");
        excludePathPatterns.add("/login.do");

        // 注册
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(pathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }
}