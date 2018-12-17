package View;

import Service.ServiceClassRoom;
import domain.ClassRoom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClassRoomServletController extends HttpServlet {
    private ServiceClassRoom serviceClassRoom;
    private List<String> capacitylist;
    @Override

    public void init() {
        serviceClassRoom = new ServiceClassRoom();
        capacitylist= Arrays.asList("5","10","15","20","25");
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
                    addClassRoom(request, response);
                    break;
                case "/update":
                    System.out.println("update");
                    updateClassRoom(request, response);
                    break;
                case "/edit":
                    System.out.println("edit");
                    editClassRoom(request, response);
                    break;
                case "/delete":
                    System.out.println("delete");
                    deleteClassRoom(request, response);
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
            capacitylist= Arrays.asList("5","10","15","20","25");
            List<ClassRoom> classRoomlist = serviceClassRoom.findAll();
            classRoomlist.get(0).show();
            request.setAttribute("classroomlist",classRoomlist);
            request.setAttribute("capacitylist",capacitylist);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ClassRoom/ClassRoomForm.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){e.printStackTrace();}
    }

    private void editClassRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            String classRoomid = request.getParameter("roomid");
            ClassRoom existingClassRoom = serviceClassRoom.findbyID(classRoomid);
            request.setAttribute("classroom", existingClassRoom);
            String unavailabledatelist ="";
            unavailabledatelist=existingClassRoom.getUnavailabledatelistString("yyyy-MM-dd").replace(":",",");

            existingClassRoom.show();
            System.out.println(unavailabledatelist);
            request.setAttribute("unavailabledatelist",unavailabledatelist.substring(0,unavailabledatelist.length()-1));
            List<ClassRoom> classRoomlist = serviceClassRoom.findAll();
            request.setAttribute("classroomlist",classRoomlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ClassRoom/ClassRoomForm.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void addClassRoom(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String roomid = request.getParameter("roomid");
            String roomnumber = request.getParameter("roomnumber");
            String address = request.getParameter("address");
            String active = request.getParameter("active");
            String unavailabledatelist = request.getParameter("unavailabledatelist").replace(",",":")+":";
            System.out.println(unavailabledatelist);
            String numberofseats = request.getParameter("numberofseats");
            ClassRoom classRoom = new ClassRoom(roomid, roomnumber, address, Integer.parseInt("0"+ numberofseats),  "", active.contains("true"));
            classRoom.setUnavailabledatelist(unavailabledatelist,"yyyy-MM-dd");
            classRoom.show();
            serviceClassRoom.add(classRoom);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void updateClassRoom(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String roomid = request.getParameter("roomid");
            String roomnumber = request.getParameter("roomnumber");
            String address = request.getParameter("address");
            String active = request.getParameter("active");
            String unavailabledatelist = request.getParameter("unavailabledatelist").replace(",", ":") + ":";
            System.out.println(unavailabledatelist);
            String numberofseats = request.getParameter("numberofseats");
            ClassRoom classRoom = new ClassRoom(roomid, roomnumber, address, Integer.parseInt("0" + numberofseats), "", active.contains("true"));
            classRoom.setUnavailabledatelist(unavailabledatelist, "yyyy-MM-dd");
            classRoom.show();
            serviceClassRoom.update(classRoom);
            response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
            showForm(request,response);}
    }

    private void deleteClassRoom(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("roomid");
        try {
             String classRoomid = request.getParameter("classRoom.Roomid");
             ClassRoom classRoom = new ClassRoom();
             classRoom.setRoomid(id);
             serviceClassRoom.delete(classRoom);
             response.sendRedirect("show");
        }catch (Exception e){request.setAttribute("errormessage", e.getMessage());
             showForm(request,response);}
            }


}
