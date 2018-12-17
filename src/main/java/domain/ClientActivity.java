package domain;

import java.text.SimpleDateFormat;
import java.util.*;

public class ClientActivity {
    private String activityid;
    private String clientid;
    private String courseid;
    private LinkedList<Instructor> instructorlist;//id:id in database
    private Integer sessionid;
    private String notes;

    public ClientActivity() {
    }

    public ClientActivity(String activityid, String clientid, String courseid, LinkedList<Instructor> instructorlist, Integer sessionid, String notes) {
        this.activityid = activityid;
        this.clientid = clientid;
        this.courseid = courseid;
        this.instructorlist = instructorlist;
        this.sessionid = sessionid;
        this.notes = notes;
    }

    public ClientActivity(String clientid, String courseid, LinkedList<Instructor> instructorlist, Integer sessionid, String notes) {
        this.clientid = clientid;
        this.courseid = courseid;
        this.instructorlist = instructorlist;
        this.sessionid = sessionid;
        this.notes = notes;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date mydate = new Date();
        this.activityid = clientid + dateFormat.format(mydate);
    }

    public ClientActivity(String clientid, String courseid,Instructor instructor, Integer sessionid, String notes) {
        this.clientid = clientid;
        this.courseid = courseid;
        this.instructorlist.add(instructor);
        this.sessionid = sessionid;
        this.notes = notes;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date mydate = new Date();
        this.activityid = clientid + dateFormat.format(mydate);
    }

    public ClientActivity(String clientid, String courseid, Integer sessionid, String notes) {
        this.clientid = clientid;
        this.courseid = courseid;
        this.sessionid = sessionid;
        this.notes = notes;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date mydate = new Date();
        this.activityid = clientid + dateFormat.format(mydate);
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getInstructorlistToString(){
        String instructorstring="";
        if (this.instructorlist==null){return "";}
        for(int i=0;i<instructorlist.size();i++){
            instructorstring=instructorstring+ instructorlist.get(i).getInstructorid()+":";
        }
        return instructorstring;
    }

//    public LinkedList<Instructor> getInstructorlist() {
//        return instructorlist;
//    }

    public List<Instructor> getInstructorlist() {
        List<Instructor> myList = new ArrayList<Instructor>(instructorlist);
        return instructorlist;
    }

    public void setInstructorlist(LinkedList<Instructor> instructorlist) {
        this.instructorlist = instructorlist;
    }

    public Integer getSessionid() {
        return sessionid;
    }

    public void setSessionid(Integer sessionid) {
        this.sessionid = sessionid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addInstructor(Instructor instructor){
        if (this.instructorlist==null){instructorlist = new LinkedList<Instructor>();}
        this.instructorlist.add(instructor);
    }

    public void show(){
        System.out.println("id:" +this.activityid);
        System.out.println("sessionid:" +this.sessionid);
        System.out.println("clientid:" +this.clientid);
        System.out.println("courseid:" +this.courseid);
        System.out.println("getInstructorlistToString:" +this.getInstructorlistToString());
        System.out.println("notes:" +this.notes);
    }
}
