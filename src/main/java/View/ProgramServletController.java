package View;

import Service.ServiceProgram;
import domain.Program;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProgramServletController extends HttpServlet {
    private ServiceProgram serviceProgram;
    @Override

    public void init() {

        serviceProgram = new ServiceProgram();

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
                    addProgram(request, response);
                    break;
                case "/update":
                    System.out.println("update");
                    updateProgram(request, response);
                    break;
                case "/edit":
                    System.out.println("edit");
                    editProgram(request, response);
                    break;
                case "/delete":
                    System.out.println("delete");
                    deleteProgram(request, response);
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
            List<Program> programlist = serviceProgram.findAll();
            request.setAttribute("programlist",programlist);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Program/ProgramForm.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void editProgram(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            String programid = request.getParameter("programid");
            Program existingProgram = serviceProgram.findbyID(programid);
            request.setAttribute("program", existingProgram);
            existingProgram.show();
            List<Program> programlist = serviceProgram.findAll();
            request.setAttribute("programlist",programlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Program/ProgramForm.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void addProgram(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String programid = request.getParameter("programid");
            String programname = request.getParameter("programname");
            String description = request.getParameter("description");
            String active = request.getParameter("active");

            Program program = new Program(programid, programname, description, active.contains("true"));
            program.show();
            serviceProgram.add(program);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void updateProgram(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String programid = request.getParameter("programid");
        String programname = request.getParameter("programname");
        String description = request.getParameter("description");
        String active = request.getParameter("active");

        Program program = new Program(programid,programname,description, active.contains("true"));
        program.show();
        serviceProgram.update(program);
        response.sendRedirect("show");
    }

    private void deleteProgram(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("programid");
        try {
            String programid = request.getParameter("program.programid");
            Program program = new Program();
            program.setProgramid(id);
            serviceProgram.delete(program);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }


}
