/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
////import loginServlets.LoginCookieServlet;

/**
 *
 * @author B2005
 */
public class readThread extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userName")) {
                    userName = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String title = request.getParameter("title");
        String keyword = request.getParameter("keyword");
        String threadTitle = "";
        ForumThread ft = null;
        HttpSession mySession = request.getSession();
        int index = -1;
        if (title != null) {
            for (index = 0; index < Database.getNumberOfThreads(); index++) {
                ft = Database.getThread(index);
                threadTitle = ft.getTitle();
                if (threadTitle.equalsIgnoreCase(title)) {
                    break;
                }
            }
        } else {
            // attempt to get thread form session object
            ft = (ForumThread) mySession.getAttribute("Lab4ForumThread");
            if (ft != null) {
                index = 0;
            }

        }
        out.println("<a href=index.jsp>Take me back to the start page ...</a><br>");
        if (index >= 0 && index < Database.getNumberOfThreads()) {
            // thread found - deal with it
            // add thread as session object
            mySession.setAttribute("Lab4ForumThread", ft);
            out.println(ft.getAllMessages());

////            if (LoginCookieServlet.login(userName, password)) {
////                out.println("<form action=addToThread method=POST>");
////                out.println("<textarea name=message rows=10 cols=80></textarea></td></tr>");
////                out.println("<br><input type=submit value='Submit Comment'></form>");
////            }
        } else {
            out.println("<h1>Thread not found </h1>");
        }
        out.println("<br><a href=index.jsp>Take me back to the start page ...</a>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
