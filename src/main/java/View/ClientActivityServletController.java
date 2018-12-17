package View;

import Service.ServiceClientActivity;
import Service.ServiceClient;
import Service.ServiceInstructor;
import Service.ServiceSchoolSession;
import Service.ServiceCourse;
import Service.ServiceClassRoom;
import domain.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ClientActivityServletController extends HttpServlet {
    private ServiceSchoolSession serviceSchoolSession;
    private ServiceClientActivity serviceClientActivity;
    private ServiceClient serviceClient;
    private ServiceInstructor serviceInstructor;
    private ServiceCourse serviceCourse;
    private ServiceClassRoom serviceClassRoom;
    @Override

    public void init() {
        serviceSchoolSession = new ServiceSchoolSession();
        serviceClientActivity = new ServiceClientActivity();
        serviceClient = new ServiceClient();
        serviceInstructor = new ServiceInstructor();
        serviceClassRoom = new ServiceClassRoom();
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
                    addClientActivity(request, response);
                    break;
                case "/update":
                    System.out.println("update");
                    updateClientActivity(request, response);
                    break;
                case "/edit":
                    System.out.println("edit");
                    editActivity(request, response);
                    break;
                case "/delete":
                    System.out.println("delete");
                    deleteClientActivity(request, response);
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
            String sessionid = request.getParameter("sessionid");
            SchoolSession existingSchoolSession = serviceSchoolSession.findbyID(sessionid);
            request.setAttribute("schoolsession", existingSchoolSession);

            String clientid = request.getParameter("clientid");
            Client client = serviceClient.findbyID(clientid);
            request.setAttribute("client", client);

            List<ClientActivity> clientActivityList=serviceClientActivity.findAllByClientIDSession(clientid, sessionid);
            request.setAttribute("clientactitivitylist", clientActivityList);

            List<Instructor> listInstructor = existingSchoolSession.getInstructorlist();
            request.setAttribute("listInstructor", listInstructor);

            List<Instructor> instructorlist = serviceInstructor.findAll();
            request.setAttribute("instructorlist",instructorlist);

            List<Course> courseList = serviceCourse.findAll();
            request.setAttribute("courselist", courseList);

            ClassRoom classRoom = serviceClassRoom.findbyID(existingSchoolSession.getRoomid());
            request.setAttribute("classroom", classRoom);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ClientActivity/ClientActivityForm.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void editActivity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            String sessionid = request.getParameter("sessionid");
            SchoolSession existingSchoolSession = serviceSchoolSession.findbyID(sessionid);
            request.setAttribute("schoolsession", existingSchoolSession);

            String clientid = request.getParameter("clientid");
            Client client = serviceClient.findbyID(clientid);
            request.setAttribute("client", client);

            List<ClientActivity> clientActivityList=serviceClientActivity.findAllByClientIDSession(clientid, sessionid);
            request.setAttribute("clientactitivitylist", clientActivityList);

            List<Instructor> listInstructor = existingSchoolSession.getInstructorlist();
            request.setAttribute("listInstructor", listInstructor);

            List<Instructor> instructorlist = serviceInstructor.findAll();
            request.setAttribute("instructorlist",instructorlist);

            List<Course> courseList = serviceCourse.findAll();
            request.setAttribute("courselist", courseList);

            ClassRoom classRoom = serviceClassRoom.findbyID(existingSchoolSession.getRoomid());
            request.setAttribute("classroom", classRoom);

            String activityid = request.getParameter("activityid");
            ClientActivity clientactivity =serviceClientActivity.findbyID(activityid);
            clientactivity.getInstructorlist().get(0).show();
            request.setAttribute("clientactivity",clientactivity);
            request.setAttribute("clientinstructorlist",clientactivity.getInstructorlist());
            request.setAttribute("instructorstring", clientactivity.getInstructorlistToString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ClientActivity/ClientActivityForm.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void addClientActivity(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String activityid = request.getParameter("activityid");
        String clientid = request.getParameter("clientid");
        String sessionid = request.getParameter("sessionid");
        String courseid = request.getParameter("courseid");
        String tmpinstructor = request.getParameter("instructorid");
        String notes = request.getParameter("notes");
        Optional<String[]> instructorid = Optional.ofNullable(request.getParameterValues("instructorid"));
        LinkedList<Instructor> instructorLinkedList=new LinkedList<Instructor>();
        instructorid.ifPresent(s->{
            List<String> instructorlist= Arrays.asList(s);
                instructorlist.parallelStream().forEach(st->
                instructorLinkedList.add(new Instructor(st)));
        });
        ClientActivity clientActivity = new ClientActivity(clientid,courseid,instructorLinkedList,Integer.parseInt(sessionid),notes);
       clientActivity.show();
        serviceClientActivity.add(clientActivity);
        response.sendRedirect("show?clientid="+clientid+"&sessionid="+sessionid);
    }

    private void updateClientActivity(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String activityid = request.getParameter("activityid");
        String clientid = request.getParameter("clientid");
        String sessionid = request.getParameter("sessionid");
        String courseid = request.getParameter("courseid");
        String tmpinstructor = request.getParameter("instructorid");
        String notes = request.getParameter("notes");
        Optional<String[]> instructorid = Optional.ofNullable(request.getParameterValues("instructorid"));
        LinkedList<Instructor> instructorLinkedList=new LinkedList<Instructor>();
        instructorid.ifPresent(s->{
            List<String> instructorlist= Arrays.asList(s);
            instructorlist.parallelStream().forEach(st->
                    instructorLinkedList.add(new Instructor(st)));
        });
        ClientActivity clientActivity = new ClientActivity(activityid,clientid,courseid,instructorLinkedList,Integer.parseInt(sessionid),notes);
        clientActivity.show();
        serviceClientActivity.update(clientActivity);
        response.sendRedirect("show?clientid="+clientid+"&sessionid="+sessionid);
    }

    private void deleteClientActivity(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("activityid");
        String clientid = request.getParameter("clientid");
        String sessionid = request.getParameter("sessionid");
        ClientActivity clientActivity = new ClientActivity();
        clientActivity.setActivityid(id);
        serviceClientActivity.delete(clientActivity);
        response.sendRedirect("show?clientid="+clientid+"&sessionid="+sessionid);
    }


}
