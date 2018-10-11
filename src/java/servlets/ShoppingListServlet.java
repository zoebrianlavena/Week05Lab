package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("value of action link: " + request.getParameter("action"));
        HttpSession session = request.getSession();
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        String action = request.getParameter("action");
        
        if(session.getAttribute("username") != null){
            if (action.equals("logout")) {
                HttpSession logout = request.getSession();
                logout.invalidate();
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("")) {
            return;
        }
        switch (action) {
            case "register":
                String username = request.getParameter("username");
                if (username == null || username.equals("")) {
                    request.setAttribute("error", "Please provide a username.");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                }
                HttpSession register = request.getSession();
                register.setAttribute("username", username);
                break;
            case "add":
                HttpSession add = request.getSession();
                ArrayList<String> additems = (ArrayList<String>) add.getAttribute("items");
                if (additems == null) {
                    additems = new ArrayList<>();
                }
                String item = request.getParameter("itemtoadd");
                additems.add(item);
                add.setAttribute("items", additems);
                break;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }

}
