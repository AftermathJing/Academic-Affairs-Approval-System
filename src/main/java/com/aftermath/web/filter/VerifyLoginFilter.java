package com.aftermath.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class VerifyLoginFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("VerifyLoginFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::" + uri);

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("login");

        if ((user != null) || (uri.endsWith("index.jsp")) || (uri.endsWith("login"))) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("login_msg", "您还未登陆！");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    public void destroy() {
        //close any resources here
    }

}

