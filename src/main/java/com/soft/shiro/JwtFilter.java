package com.soft.shiro;

import cn.hutool.json.JSONUtil;
import com.soft.util.JwtUtils;
import com.soft.util.Result;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器JwtFilter
 * @author suphowe
 */
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 生成自定义 JwtToken
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 获取 token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)) {
            return null;
        }

        return new JwtToken(jwt);
    }

    /**
     * 拦截校验
     * 当头部没有Authorization时候，我们直接通过，不需要自动登录；
     * 当带有的时候，首先我们校验jwt的有效性，没问题我们就直接执行executeLogin方法实现自动登录
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            // 校验jwt,判断是否已过期
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if(claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
                throw new ExpiredCredentialsException("token已失效，请重新登录");
            }

            // 执行登录
            return executeLogin(servletRequest, servletResponse);
        }
    }

    /**
     * 登录异常时候进入的方法
     * 我们直接把异常信息封装然后抛出
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Throwable throwable = e.getCause() == null ? e : e.getCause();
        Result result = Result.fail(throwable.getMessage());
        String json = JSONUtil.toJsonStr(result);
        //处理登录失败的异常
        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ioException) {

        }
        return false;
    }

    /**
     * 对跨域提供支持
     * 拦截器的前置拦截
     * 因为我们是前后端分析项目，项目中除了需要跨域全局配置之外，我们再拦截器中也需要提供跨域支持。
     * 这样，拦截器才不会在进入Controller之前就被限制了
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

}
