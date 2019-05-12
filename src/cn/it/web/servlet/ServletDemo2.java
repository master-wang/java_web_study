package cn.it.web.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * servlet 的 继承子类  只有一个 service 的用法，这样便捷
 * GenericServlet 将其他方法做了默认实现
 */
@WebServlet(urlPatterns = "/demo2")
public class ServletDemo2 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet 的继承类");
    }
}
