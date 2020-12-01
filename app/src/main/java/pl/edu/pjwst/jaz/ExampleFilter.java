package pl.edu.pjwst.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ExampleFilter extends HttpFilter {
    private final UserSession userSession;

    public ExampleFilter(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

       //userSession.isLoggedIn();

        System.out.println(userSession.isLoggedIn());
     //   System.out.println(request.getSession().getId());

       if (isUserLogged() && isSiteAllowed(request)) {
        //chain.doFilter(request,response);
          // response.sendRedirect("/api/is-ready");
           //response.reset();

           //response.getWriter().append("U already Logged in !");


            //  response.getOutputStream().flush();
            //  response.getOutputStream().println("U already Logged in !");



       } else {
           response.setStatus(HttpStatus.UNAUTHORIZED.value());


       }

     //   System.out.println(request.getContextPath());
        //response.setStatus(200);
        super.doFilter(request, response, chain);

    }

    private boolean isSiteAllowed(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().equals("/api/auth0/is-ready")) {
            return true;
        }
         else if (request.getRequestURI().equals("/api/auth0/kate")) {
            return false;
        }
        return false;
    }


    private boolean isUserLogged() {

        return userSession.isLoggedIn();
    }
}
