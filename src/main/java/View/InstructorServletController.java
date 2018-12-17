package View;

import Service.*;
import domain.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class InstructorServletController extends HttpServlet {
    private ServiceInstructor serviceInstructor;
    @Override

    public void init() {

        serviceInstructor = new ServiceInstructor();

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
                    addInstructor(request, response);
                    break;
                case "/update":
                    System.out.println("update");
                    updateInstructor(request, response);
                    break;
                case "/edit":
                    System.out.println("edit");
                    editInstructor(request, response);
                    break;
                case "/delete":
                    System.out.println("delete");
                    deleteInstructor(request, response);
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
            List<Instructor> instructorlist = serviceInstructor.findAll();
            request.setAttribute("instructorlist",instructorlist);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Instructor/InstructorForm.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void editInstructor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            String instructorid = request.getParameter("instructorid");
            Instructor existingInstructor = serviceInstructor.findbyID(instructorid);
            request.setAttribute("instructor", existingInstructor);
            List<Instructor> instructorlist = serviceInstructor.findAll();
            request.setAttribute("instructorlist",instructorlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Instructor/InstructorForm.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void addInstructor(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
try {
    String instructorid = request.getParameter("instructorid");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    String email = request.getParameter("emailaddress");
    String address = request.getParameter("address");
    String active = request.getParameter("active");
    String startdate = request.getParameter("startdate");
    String phone = request.getParameter("phone");

    Instructor instructor = new Instructor(instructorid, firstname, lastname, phone, address, email, startdate, active.contains("true"));
    instructor.show();
    serviceInstructor.add(instructor);
    response.sendRedirect("show");
}catch (Exception e){request.setAttribute("errormessage", e.getMessage());
    showForm(request,response);}
    }

    private void updateInstructor(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String instructorid = request.getParameter("instructorid");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("emailaddress");
            String address = request.getParameter("address");
            String active = request.getParameter("active");
            String startdate = request.getParameter("startdate");
            String phone = request.getParameter("phone");

            Instructor instructor = new Instructor(instructorid, firstname, lastname, phone, address, email, startdate, active.contains("true"));
            instructor.show();
            serviceInstructor.update(instructor);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            editInstructor(request,response);
            //showForm(request,response);
            }
    }

    private void deleteInstructor(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String instructorid = request.getParameter("instructorid");

            //String instructorid = request.getParameter("instructor.instructorid");
            Instructor instructor = new Instructor();
            instructor.setInstructorid(instructorid);
            serviceInstructor.delete(instructor);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }


}
