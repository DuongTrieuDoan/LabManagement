package Service;

import Repository.RepositoryClientActivity;
import Repository.RepositorySchoolSession;
import domain.Client;
import domain.ClientActivity;
import domain.Instructor;
import domain.SchoolSession;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceSchoolSession {

    private RepositorySchoolSession repositorySchoolSession;

    public ServiceSchoolSession() {
        this.repositorySchoolSession=new RepositorySchoolSession();
    }

    private ServiceClassRoom.Availability checkavailabilitySchoolSession(SchoolSession schoolSession) throws Exception{
        ServiceClassRoom serviceClassRoom = new ServiceClassRoom();
        return serviceClassRoom.checkRoomAvailable(schoolSession.getSessionid(),schoolSession.getRoomid(),
                schoolSession.getSessiondateUTCString(),schoolSession.getStarttimeUTCString(),
                schoolSession.getEndtimeUTCString());
    }

    private String checkvalidation(SchoolSession schoolSession) throws Exception{
        String validationstring="";
        String Stringmindate="2018-09-01";
        String Stringmaxdate="2030-12-31";
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date mindate = dateFormat.parse(Stringmindate);
            Date maxdate = dateFormat.parse(Stringmaxdate);

        if(schoolSession.getSessiondate()==null) {
            validationstring = validationstring + "<br/>" + "Session date must not be empty";
        }else {
            if (schoolSession.getSessiondate().before(mindate)) {
                validationstring = validationstring + "<br/>" + "Session date must not be befor " + Stringmindate;
            }
            if (schoolSession.getSessiondate().after(maxdate)) {
                validationstring = validationstring + "<br/>" + "Session date must not be befor " + Stringmaxdate;
            }
        }
        if(schoolSession.getStarttime()==null){validationstring = validationstring + "<br/>" + "Session start time must not be empty";}
        if(schoolSession.getEndtime()==null){validationstring = validationstring + "<br/>" + "Session end time must not be empty";}
        if(schoolSession.getStarttime()!=null && schoolSession.getEndtime()!=null) {
            if (schoolSession.getEndtime().before(schoolSession.getStarttime())){
                validationstring = validationstring + "<br/>" + "Session end time must not be earlier than the start time";}
        }
        if(schoolSession.getStatus()==null || schoolSession.getStatus()==""){validationstring = validationstring + "<br/>" + "The status must not be empty";}
        if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){throw e;}
        return validationstring;
    }

    public void add(SchoolSession schoolSession) throws Exception{
        String validationstring = checkvalidation(schoolSession);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        switch (checkavailabilitySchoolSession(schoolSession)){
            case CLOSED:
                throw new Exception("The room is closed in the time slot you are choosing");
            case UNAVAILABLE:
                throw new Exception("The room is unavailable in the time slot you are choosing");
            case NOTEXISTING:
                throw new Exception("The room does not exist");
        }
        try {
            repositorySchoolSession.insert(schoolSession, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The session is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void modify(SchoolSession schoolSession) throws Exception{
        String validationstring = checkvalidation(schoolSession);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        switch (checkavailabilitySchoolSession(schoolSession)){
            case CLOSED:
                throw new Exception("The room is closed in the time slot you are choosing");
            case UNAVAILABLE:
                throw new Exception("There is another session scheduled for the room in the time slot you are choosing");
            case NOTEXISTING:
                throw new Exception("The room does not exist");
        }
        try {
            if(repositorySchoolSession.update(schoolSession, true)==0){
                throw new Exception("The session doesn't exist");
            }
        }catch(Exception e){throw e;}
    }

    public void delete(SchoolSession schoolSession) throws Exception{
            try {
                if(repositorySchoolSession.delete(schoolSession, true)==0){
                    throw new Exception("The session doesn't exist");
                }
            }catch(Exception e){
                if(e.toString().indexOf("foreign key constraint fails")>=0){
                    throw new Exception("Cannot delete the School session with id " +schoolSession.getSessionid()+ ". Please delete all client activities relating to the session first.");
                }
                System.out.println(e.toString());
                throw new Exception("Technical issue. Please try agian!");
            }
    }

    public List<SchoolSession> findAll() throws Exception{
        try {
            return repositorySchoolSession.query("SELECT SCHOOLSESSION.*, CLASSROOM.ROOMNUMBER, CLASSROOM.ADDRESS FROM SCHOOLSESSION,CLASSROOM WHERE SCHOOLSESSION.ROOMID = CLASSROOM.ROOMID ORDER BY CLASSROOM.ADDRESS, CLASSROOM.ROOMNUMBER, SCHOOLSESSION.SESSIONDATE", true);
        }catch (Exception e){throw e;}
    }

    public SchoolSession findbyID(String id) throws Exception {
        SchoolSession schoolSession=null;
        try {
            LinkedList<SchoolSession> schoolSessionLinkedList =
                    new LinkedList<SchoolSession>(repositorySchoolSession.query("SELECT * FROM SCHOOLSESSION WHERE SESSIONID=" + id, true));
            if(!schoolSessionLinkedList.isEmpty()){schoolSession=schoolSessionLinkedList.get(0);}
            else{throw new Exception("The session doesn't exist");}
        } catch (Exception e) { e.printStackTrace(); throw e;
        }
        return schoolSession;
    }

    public void addInstructor(SchoolSession schoolSession, Instructor instructor) throws Exception{
        Integer result=0;
        try {
            result=repositorySchoolSession.insertInstructor(schoolSession, instructor,true);
            if(result==0){throw new Exception("Session does not exist");}
        }catch(Exception e){
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void addClient(SchoolSession schoolSession, Client client) throws Exception{
        Integer result=0;
        try {
            result=repositorySchoolSession.insertClient(schoolSession, client,true);
            if(result==0){throw new Exception("Session does not exist");}
        }catch(Exception e){
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void deleteClient(SchoolSession schoolSession, Client client) throws Exception{
        try {
            RepositoryClientActivity repositoryClientActivity=new RepositoryClientActivity();
//            Map<String, Object> parameterstring = new HashMap<String, Object>();
//            parameterstring.put("CLIENTID", client.getClientid());
//            parameterstring.put("SESSIONID",schoolSession.getSessionid());
//        Optional.ofNullable(repositoryClientActivity.findbyParameter(parameterstring).getFirst())
//                .ifPresent(x->{throw new RuntimeException("Existing the client's activities in this session.");});
            Optional.ofNullable(repositoryClientActivity.existingClientInClientActivity(schoolSession, client) )
                    .ifPresent(x->{
                        throw new RuntimeException("The client exists in client activities of this session.");
                    });
            schoolSession = findbyID(schoolSession.getSessionid().toString());
            if(Optional.ofNullable(schoolSession).isPresent()) {
                schoolSession.setClientlistFromString(schoolSession.getClientlistToString().replace(client.getClientid() + ":", ""));
            }else{throw new Exception("Session does not exist");}
            if(repositorySchoolSession.update(schoolSession,true)==0){throw new Exception("Session does not exist");}
        }catch(Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    public void deleteInstructor(SchoolSession schoolSession, Instructor instructor) throws Exception{
        try {
            RepositoryClientActivity repositoryClientActivity=new RepositoryClientActivity();
//            Map<String, Object> parameterstring = new HashMap<String, Object>();
//            parameterstring.put("INSTR(INSTRUCTORS,'<?parameter>')>0", instructor.getInstructorid());
//            parameterstring.put("SESSIONID",schoolSession.getSessionid());
//        Optional.ofNullable(repositoryClientActivity.findbyParameter(parameterstring))
//                .ifPresent(x->{
//                    throw new RuntimeException("The instructor exists in client activities of this session.");
//                });
            //repositoryClientActivity.existingInstructorInClientActivity(Optional.ofNullable(null), instructor);
            Optional.ofNullable(repositoryClientActivity.existingInstructorInClientActivity(schoolSession, instructor) )
                    .ifPresent(x->{
                    throw new RuntimeException("The instructor exists in client activities of this session.");
                });
            schoolSession = findbyID(schoolSession.getSessionid().toString());
            if(Optional.ofNullable(schoolSession).isPresent()){
            schoolSession.setInstructorlistFromString(schoolSession.getInstructorlistToString().replace(instructor.getInstructorid()+":",""));}
            else{throw new Exception("Session does not exist");}
            if(repositorySchoolSession.update(schoolSession,true)==0){throw new Exception("Session does not exist");}
        }catch(Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

}
