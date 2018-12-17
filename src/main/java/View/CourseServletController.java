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

public class CourseServletController extends HttpServlet {
    private ServiceCourse serviceCourse;
    private ServiceInstructor serviceInstructor;
    private ServiceClassRoom serviceClassRoom;
    private ServiceProgram serviceProgram;
    @Override

    public void init() {
        serviceCourse = new ServiceCourse();
        serviceInstructor = new ServiceInstructor();
        serviceProgram = new ServiceProgram();
        serviceCourse = new ServiceCourse();
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
                    addCourse(request, response);
                    break;
                case "/update":
                    System.out.println("update");
                    updateCourse(request, response);
                    break;
                case "/edit":
                    System.out.println("edit");
                    editCourse(request, response);
                    break;
                case "/delete":
                    System.out.println("delete");
                    deleteCourse(request, response);
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
            String programid = request.getParameter("programid");
            Program existingProgram = serviceProgram.findbyID(programid);
            request.setAttribute("program", existingProgram);

            List<Course> courseList=serviceCourse.findAllByProgramID(programid);
            request.setAttribute("courselist", courseList);

            List<Instructor> instructorlist = serviceInstructor.findAll();
            request.setAttribute("instructorlist",instructorlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Course/CourseForm.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void editCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            String courseid = request.getParameter("courseid");
            Course course = serviceCourse.findbyID(courseid);
            request.setAttribute("course",course);

            String programid = course.getProgramid();
            Program existingProgram = serviceProgram.findbyID(programid);
            request.setAttribute("program", existingProgram);

            List<Course> courseList=serviceCourse.findAllByProgramID(programid);
            request.setAttribute("courselist", courseList);

            List<Instructor> instructorlist = serviceInstructor.findAll();
            request.setAttribute("instructorlist",instructorlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Course/CourseForm.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String courseid = request.getParameter("courseid");
            String programid = request.getParameter("programid");
            String coursename = request.getParameter("coursename");
            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");
            String syllabus = request.getParameter("syllabus");
            String active = request.getParameter("active");
            Optional<String[]> instructorid = Optional.ofNullable(request.getParameterValues("instructorid"));
            StringBuffer mylist = new StringBuffer();
            instructorid.ifPresent(s -> {
                List<String> instructorlist = Arrays.asList(s);
                instructorlist.stream().forEach(st ->
                        mylist.append(st + ":"));
            });
            String listinstructorid = mylist.toString();
            Course course = new Course(courseid, programid, coursename, listinstructorid, startdate, enddate, syllabus, active.contains("true"));
            course.show();
            serviceCourse.add(course);
            response.sendRedirect("show?programid=" + programid);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
                showForm(request,response);}
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String courseid = request.getParameter("courseid");
            String programid = request.getParameter("programid");
            String coursename = request.getParameter("coursename");
            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");
            String syllabus = request.getParameter("syllabus");
            String active = request.getParameter("active");
            Optional<String[]> instructorid = Optional.ofNullable(request.getParameterValues("instructorid"));
            StringBuffer mylist = new StringBuffer();
            instructorid.ifPresent(s -> {
                List<String> instructorlist = Arrays.asList(s);
                instructorlist.stream().forEach(st ->
                        mylist.append(st + ":"));
            });
            String listinstructorid = mylist.toString();
            Course course = new Course(courseid, programid, coursename, listinstructorid, startdate, enddate, syllabus, active.contains("true"));
            course.show();
            serviceCourse.update(course);
            response.sendRedirect("show?programid=" + programid);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }


    private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String id = request.getParameter("courseid");
            String programid = request.getParameter("programid");
            String courseid = request.getParameter("course.courseid");
            Course course = new Course();
            course.setCourseid(id);
            serviceCourse.delete(course);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }


}
