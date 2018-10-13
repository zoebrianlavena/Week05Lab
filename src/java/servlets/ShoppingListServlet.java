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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if(session.getAttribute("username") != null){
            if(action != null){
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }else{
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null || action.equals("")) {
            return;
        }
        switch (action) {
            case "register":
                if(session.getAttribute("username") == null){
                    String username = request.getParameter("username");
                    if (username == null || username.equals("")) {
                        request.setAttribute("error", "Please provide a username.");
                        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                        return;
                    } else {
                        session.setAttribute("username", username);
                    }
                }
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);        
                break;
            case "add":
                ArrayList<String> additems = (ArrayList<String>) session.getAttribute("items");
                String item = request.getParameter("itemtoadd");
                if (additems == null) {
                    additems = new ArrayList<>();
                }
                if (item == null || item.equals("")) {
                    request.setAttribute("message", "Please enter an item to add.");
                } else {
                    additems.add(item);
                    session.setAttribute("items", additems);
                }
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                break;
            case "delete":
                ArrayList<String> deleteitem = (ArrayList<String>) session.getAttribute("items");
                String thisitem = request.getParameter("thisitem");
                if (thisitem == null || thisitem.equals("")) {
                    request.setAttribute("message", "No item has been selected.");
                } else {
                    deleteitem.remove(thisitem);
                    session.setAttribute("items", deleteitem);
                }
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                break;
            default:
                HttpSession checksession = request.getSession();
                if (checksession.getAttribute("username") != null) {
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

}
