package View;

import Service.ServiceClient;
import domain.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientServletController extends HttpServlet {
    private ServiceClient serviceClient;
    @Override

    public void init() {

        serviceClient = new ServiceClient();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = extractAction(request);
        try {
            switch (action) {
                case "/add":
                    System.out.println("add");
                    addClient(request, response);
                    break;
                case "/update":
                    System.out.println("update");
                    updateClient(request, response);
                    break;
                case "/edit":
                    System.out.println("edit");
                    editClient(request, response);
                    break;
                case "/delete":
                    System.out.println("delete");
                    deleteClient(request, response);
                    break;
                case "/show":
                    showForm(request, response);
                    break;
                default:
                    System.out.println("default");
                    showForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        } else {
            return pathInfo;
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            List<Client> clientlist = serviceClient.findAll();
            request.setAttribute("clientlist",clientlist);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Client/ClientForm.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void editClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            String clientid = request.getParameter("clientid");
            Client existingClient = serviceClient.findbyID(clientid);
            request.setAttribute("client", existingClient);
            existingClient.show();
            List<Client> clientlist = serviceClient.findAll();
            request.setAttribute("clientlist",clientlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Client/ClientForm.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void addClient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String clientid = request.getParameter("clientid");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("emailaddress");
            String address = request.getParameter("address");
            String active = request.getParameter("active");
            String registerdate = request.getParameter("registerdate");
            String phone = request.getParameter("phone");

            Client client = new Client(clientid, firstname, lastname, phone, address, email, registerdate, active.contains("true"));
            client.show();
            serviceClient.add(client);
            response.sendRedirect("show");
        }catch (Exception e){            request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
        String clientid = request.getParameter("clientid");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("emailaddress");
        String address = request.getParameter("address");
        String active = request.getParameter("active");
        String registerdate = request.getParameter("registerdate");
        String phone=request.getParameter("phone");

        Client client = new Client(clientid,firstname,lastname, phone, address,email,registerdate, active.contains("true"));
        client.show();
        serviceClient.update(client);
        response.sendRedirect("show");
        }catch (Exception e){            request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String clientid = request.getParameter("clientid");
           // String clientid = request.getParameter("client.clientid");
            Client client = new Client();
            client.setClientid(clientid);
            serviceClient.delete(client);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }


}
