package View;

import Service.ServiceClassRoom;
import Service.ServiceClient;
import Service.ServiceInstructor;
import Service.ServiceSchoolSession;
import domain.ClassRoom;
import domain.Client;
import domain.Instructor;
import domain.SchoolSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SchoolSessionServletController extends HttpServlet {
    private ServiceSchoolSession serviceSchoolSession;
    private ServiceClassRoom serviceClassRoom;
    private ServiceInstructor serviceInstructor;
    private ServiceClient serviceClient;
    @Override

    public void init() {
        serviceSchoolSession = new ServiceSchoolSession();
        serviceClassRoom = new ServiceClassRoom();
        serviceInstructor=new ServiceInstructor();
        serviceClient = new ServiceClient();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = extractAction(request);
       System.out.println(action);
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertSchoolSession(request, response);
                    break;
                case "/delete":
                    deleteSchoolSession(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateSchoolSession(request, response);
                    break;
                case "/addinstructor":
                     insertInstructor(request, response);
                    break;
                case "/addclient":
                    insertClient(request, response);
                    break;
                case "/deleteclient":
                    deleteClient(request, response);
                    break;
                case "/deleteinstructor":
                    deleteInstructor(request, response);
                    break;
                default:
                    listSchoolSession(request, response);
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
    private void listSchoolSession(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<SchoolSession> listSchoolSession = serviceSchoolSession.findAll();
        request.setAttribute("listSchoolSession", listSchoolSession);
        List<ClassRoom> classRoomList = serviceClassRoom.findAll();
        request.setAttribute("classroomlist",classRoomList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/SchoolSession/SchoolSessionList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/SchoolSession/SchoolSessionForm.jsp");
            List<ClassRoom> classRoomList = serviceClassRoom.findAll();
            request.setAttribute("classroomlist",classRoomList);
            request.setAttribute("status", SchoolSession.StatusTypes.values());
            dispatcher.forward(request, response);
        }catch (Exception e){e.printStackTrace();}
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/SchoolSession/SchoolSessionForm.jsp");
            List<ClassRoom> classRoomList = serviceClassRoom.findAll();
            request.setAttribute("classroomlist",classRoomList);

            request.setAttribute("status", SchoolSession.StatusTypes.values());
            String sessionid = request.getParameter("sessionid");

            SchoolSession existingSchoolSession = serviceSchoolSession.findbyID(sessionid);
            request.setAttribute("schoolsession", existingSchoolSession);

            List<Instructor> listInstructor = existingSchoolSession.getInstructorlist();
            request.setAttribute("listInstructor", listInstructor);

            List<Instructor> allinstructor= serviceInstructor.findAll();
            request.setAttribute("allinstructor",allinstructor);

            List<Client> listClient = existingSchoolSession.getClientlist();
            request.setAttribute("listClient", listClient);

            List<Client> allclient= serviceClient.findAll();
            request.setAttribute("allclient",allclient);

            String instructorlist = existingSchoolSession.getInstructorlistToString();
            request.setAttribute("instructorlist", instructorlist);

            String clientlist = existingSchoolSession.getClientlistToString();
            request.setAttribute("clientlist", clientlist);

            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void insertSchoolSession(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String sessiondate = request.getParameter("sessiondate");
            String starttime = request.getParameter("starttime");
            String endtime = request.getParameter("endtime");
            String roomid = request.getParameter("roomid");
            String status = request.getParameter("status");
            String remark = request.getParameter("remark");
            SchoolSession schoolSession = new SchoolSession(
                    sessiondate, starttime, endtime, roomid, status, remark);
            serviceSchoolSession.add(schoolSession);
            response.sendRedirect("list");
        }catch (Exception e){e.printStackTrace(); throw e;}
    }

    private void updateSchoolSession(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
        String sessionid = request.getParameter("sessionid");
        String sessiondate = request.getParameter("sessiondate");
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String roomid = request.getParameter("roomid");
        String status = request.getParameter("status");
        String remark = request.getParameter("remark");
        String instructorlist=request.getParameter("instructorlist");
        String clientlist=request.getParameter("clientlist");
        SchoolSession schoolSession = new SchoolSession(
                sessiondate, starttime, endtime, roomid, status,remark);
        if(instructorlist!=""){schoolSession.setInstructorlistFromString(instructorlist);}
        if(clientlist!=""){schoolSession.setClientlistFromString(clientlist);}
        schoolSession.setSessionid(Integer.parseInt(sessionid));
            serviceSchoolSession.modify(schoolSession);
        response.sendRedirect("list");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showEditForm(request,response);}
    }

    private void deleteSchoolSession(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sessionid = request.getParameter("sessionid");
        try {
            SchoolSession schoolSession = new SchoolSession();
            schoolSession.setSessionid(Integer.parseInt(sessionid));
            serviceSchoolSession.delete(schoolSession);
            response.sendRedirect("list");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showEditForm(request,response);}
    }

    private void insertInstructor(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String sessionid = request.getParameter("sessionid");
            String instructorid = request.getParameter("instructorid");
            if((instructorid==null)||(instructorid=="")){throw new Exception("Please choose instructor to add!");}
            String sessiondate = request.getParameter("sessiondate");
            String starttime = request.getParameter("starttime");
            String endtime = request.getParameter("endtime");
            String roomid = request.getParameter("roomid");
            String status = request.getParameter("status");
            String remark = request.getParameter("remark");
            String instructorlist=request.getParameter("instructorlist");
            String clientlist=request.getParameter("clientlist");
            SchoolSession schoolSession = new SchoolSession(
                    sessiondate, starttime, endtime, roomid, status,remark);
            Instructor instructor = new Instructor();
            instructor.setInstructorid(instructorid);
            schoolSession.setSessionid(Integer.parseInt(sessionid));
            serviceSchoolSession.addInstructor(schoolSession,instructor);
            response.sendRedirect("edit?sessionid=" + sessionid);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showEditForm(request,response);}
    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String sessionid = request.getParameter("sessionid");
            String clientid = request.getParameter("clientid");
            if((clientid==null)||(clientid=="")){throw new Exception("Please choose client to add!");}
            String sessiondate = request.getParameter("sessiondate");
            String starttime = request.getParameter("starttime");
            String endtime = request.getParameter("endtime");
            String roomid = request.getParameter("roomid");
            String status = request.getParameter("status");
            String remark = request.getParameter("remark");
            String instructorlist=request.getParameter("instructorlist");
            String clientlist=request.getParameter("clientlist");
            SchoolSession schoolSession = new SchoolSession(
                    sessiondate, starttime, endtime, roomid, status,remark);
            Client client = new Client();
            client.setClientid(clientid);
            schoolSession.setSessionid(Integer.parseInt(sessionid));
            serviceSchoolSession.addClient(schoolSession,client);
            response.sendRedirect("edit?sessionid=" + sessionid);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showEditForm(request,response);}
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String clientid = request.getParameter("clientid");
            String sessionid = request.getParameter("sessionid");
            String instructorlist=request.getParameter("instructorlist");
            String clientlist=request.getParameter("clientlist");
            SchoolSession schoolSession = new SchoolSession();
            Client client = new Client();
            client.setClientid(clientid);
            schoolSession.setSessionid(Integer.parseInt(sessionid));

            serviceSchoolSession.deleteClient(schoolSession,client);
            response.sendRedirect("edit?sessionid=" + sessionid);
        }catch (Exception e){e.printStackTrace();
            request.setAttribute("errormessage", e.getMessage());
            showEditForm(request,response);
        }
    }

    private void deleteInstructor(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sessionid = request.getParameter("sessionid");
        try {
            String instructorid = request.getParameter("instructorid");

            String instructorlist=request.getParameter("instructorlist");
            String clientlist=request.getParameter("clientlist");
            SchoolSession schoolSession = new SchoolSession();
            Instructor instructor = new Instructor();
            instructor.setInstructorid(instructorid);
            schoolSession.setSessionid(Integer.parseInt(sessionid));

            serviceSchoolSession.deleteInstructor(schoolSession,instructor);
            response.sendRedirect("edit?sessionid=" + sessionid);
        }catch (Exception e){
            request.setAttribute("errormessage", e.getMessage());
            showEditForm(request,response);
        }
    }

}
