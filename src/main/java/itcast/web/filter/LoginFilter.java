package itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        //  强制转换
       HttpServletRequest request =  (HttpServletRequest) req;

       // 获取资源请求路径
        String uri = request.getRequestURI();
        // 判断是否包含登录相关的资源路径，要注意排除 css/js/图片/验证码等资源
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/")|| uri.contains("/fonts/")|| uri.contains("/js/")|| uri.contains("/checkCodeServlet")){
            //包含，用户想登录，放行
            filterChain.doFilter(req,resp);
        }else {
            //不包含 需要验证用户是否登录
            //获取session中获取user
            Object user =request.getSession().getAttribute("user");
            if (user != null){
                //不为空。登录了，放行
                filterChain.doFilter(req,resp);
            }else {
                //没登录，跳转登录页面
                req.setAttribute("login_msg","你尚未登录，请登录");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
    }
}
