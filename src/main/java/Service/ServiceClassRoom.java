package Service;

import Repository.RepositoryClassRoom;
import Repository.RepositorySchoolSession;
import domain.ClassRoom;
import domain.SchoolSession;

import java.util.LinkedList;
import java.util.List;

public class ServiceClassRoom {
    private RepositoryClassRoom repositoryClassRoom;

    public ServiceClassRoom() {
        repositoryClassRoom=new RepositoryClassRoom();
    }

    public ServiceClassRoom(RepositoryClassRoom repositoryClassRoom) {
        this.repositoryClassRoom = new RepositoryClassRoom();
    }

    public enum Availability{
        AVAILABLE("AVAILABLE"), CLOSED("CLOSED"), UNAVAILABLE("UNAVAILABLE"), NOTEXISTING("NOT EXISTING");
        private String status;
        Availability(String status) {
            this.status = status;
        }
        public String status() {
            return status;
        }
    }

    public Availability checkRoomAvailable(Integer sessionid, String roomid, String date, String from, String to) throws Exception{
        Availability status= Availability.UNAVAILABLE;
        try {
            RepositorySchoolSession repositoryschoolSession = new RepositorySchoolSession();
            RepositoryClassRoom repositoryClassRoom = new RepositoryClassRoom();

            String mydate;
            String fromtime;
            String totime;
            String SQLString = " SELECT * FROM SCHOOLSESSION WHERE ROOMID='" + roomid + "'" +
                    " AND sessiondate >= '" + date + "' AND sessiondate<ADDDATE('" + date + "', INTERVAL 1 DAY)" +
                    " AND ((starttime>='" + from + "' AND endtime<='" + to + "')" +
                    " OR (starttime<'" + from + "' AND endtime>'" + to + "')" +
                    " OR (starttime>='" + from + "' AND starttime<'" + to + "')" +
                    " OR (endtime>'" + from + "' AND endtime<='" + to + "'))";
            if (sessionid != 0) {
                SQLString = SQLString + " AND SESSIONID!=" + sessionid;
            }
            System.out.println(SQLString);
            LinkedList<SchoolSession> schoolSessionLinkedList = new LinkedList<SchoolSession>(repositoryschoolSession.query(SQLString, true));
            if (schoolSessionLinkedList.isEmpty()) {
                LinkedList<ClassRoom> classRoomLinkedList = new LinkedList<ClassRoom>(repositoryClassRoom.query("SELECT * FROM CLASSROOM WHERE ROOMID='" + roomid + "'", true));
                if (classRoomLinkedList.isEmpty()) {
                    status = Availability.NOTEXISTING;
                } else {
                    if (classRoomLinkedList.get(0).getUnavailabledatelistString("yyyy-MM-dd").indexOf(date) != -1) {
                        status = Availability.CLOSED;
                    } else {
                        status = Availability.AVAILABLE;
                    }
                }
            } else {
                status = Availability.UNAVAILABLE;
            }
        }catch (Exception e){e.printStackTrace();}
        return status;
    }

    public ClassRoom findbyID(String id) throws Exception {
        ClassRoom classRoom=null;
        try {
            LinkedList<ClassRoom> classRoomLinkedList =
                    new LinkedList<ClassRoom>(repositoryClassRoom.query("SELECT * FROM CLASSROOM " +
                            "WHERE ROOMID='" + id +"'", true));
            if(!classRoomLinkedList.isEmpty()){classRoom=classRoomLinkedList.get(0);}
            else{throw new Exception("The classRoom doesn't exist");}
        } catch (Exception e) { e.printStackTrace();
        }
        return classRoom;
    }

    public List<ClassRoom> findAll() throws Exception{
        try {
            return repositoryClassRoom.query("SELECT * FROM CLASSROOM", true);
        }catch (Exception e){throw e;}
    }

    private String checkvalidation(ClassRoom classRoom) throws Exception{
        String validationstring="";
        try{
            if(classRoom.getRoomid().isEmpty()){validationstring = validationstring + "<br/>" + "ClassRoom ID must not be empty";}
            if(classRoom.getRoomnumber().isEmpty()){validationstring = validationstring + "<br/>" + "Room number must not be empty";}
            if(classRoom.getAddress().isEmpty()){validationstring = validationstring + "<br/>" + "Address must not be empty";}
            if(classRoom.getNumberofseats()<=0){validationstring = validationstring + "<br/>" + "Capacity must not be empty";}
            //if(!Optional.ofNullable(classRoom.getUnavailabledatelist()).isPresent()){validationstring = validationstring + "<br/>" + "Start date must not be empty";}
            if(classRoom.getActive().toString()==""){validationstring = validationstring + "<br/>" + "Active must not be empty";}
            if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){throw e;}
        return validationstring;
    }

    public void add(ClassRoom classRoom) throws Exception{
        String validationstring = checkvalidation(classRoom);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryClassRoom.insert(classRoom, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry '" + classRoom.getRoomid()+ "'")>=0){
                throw new Exception("The class room with id " + classRoom.getRoomid()+ " is already existing. Please choose update function if you still want to update this person!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void delete(ClassRoom classRoom) throws Exception{
        try {
            if(repositoryClassRoom.delete(classRoom, true)==0){
                throw new Exception("The class Room with room id " + classRoom.getRoomid() +" doesn't exist");
            }
        }catch(Exception e){
            if(e.toString().indexOf("foreign key constraint fails")>=0){
                throw new Exception("Class room with room id " +classRoom.getRoomid()+ " is beeing used for some school sessions.");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");}
    }

    public void update(ClassRoom classRoom) throws Exception{
        String validationstring = checkvalidation(classRoom);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryClassRoom.update(classRoom, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The class room is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }
}
