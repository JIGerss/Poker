package com.steveny.web.servlet;

import com.steveny.config.SpringConfig;
import com.steveny.pojo.User;
import com.steveny.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService;

    @Override
    public void init() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        userService = ac.getBean(UserService.class);
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        List<User> userList = userService.loginUser(username, password);
        System.out.println(username + " " + password + " " + remember);

        if (!userList.isEmpty()) {

            if ("1".equals(remember)) {

                //添加用户名Cookie
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(600);
                response.addCookie(cookie);
            } else {

                //未点击记住账号，则删除对应Cookie
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("username".equals(cookie.getName())) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                            break;
                        }
                    }
                }
            }

            //登录成功，则设置已登录的Session
            session.setAttribute("userLogin", "y");
            writer.write("User with uuid:" + userList.get(0).getUuid() + " logins in!");
        } else {

            //登陆失败，返回提示信息
            response.setStatus(555);
            response.setContentType("text/plain");
            writer.write("Username or password is incorrect!");
        }
    }

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        String verifyCode = (String) session.getAttribute("verifyCode");

        if (verifyCode != null) {
            if (verifyCode.equalsIgnoreCase(checkCode)) {

                //验证码正确，则判断用户是否存在
                List<User> userList = userService.selectUserByName(username);

                if (userList.isEmpty()) {

                    //用户不存在，则创建用户
                    userService.addUser(username, password);
                    writer.write("Register successfully!");
                } else {

                    //用户已存在，则返回提示信息
                    response.setStatus(555);
                    writer.write("User:" + username + " already exists!");
                }

            } else {

                //验证码错误，则返回提示信息
                response.setStatus(556);
                writer.write("Verify code is incorrect!");
            }
        } else {
            //验证码为空，则返回提示信息
            response.setStatus(556);
            writer.write("Verify code is incorrect!");
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
