package domain;

import jdk.nashorn.internal.runtime.options.Option;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class SchoolSession {
    private Integer sessionid;
    private Date sessiondate;
    private Date starttime;
    private Date endtime;
    private LinkedList<Client> clientlist;
    private LinkedList<Instructor> instructorlist;
    private String roomid;
    private String status; //set of: canceled, active, booked.
    private String remark;

    public enum StatusTypes{
        CANCELED("CANCELED"),AVAILABLE("AVAILABLE"),BOOKED("BOOKED"),OVERLOADED("OVERLOADED"),DONE("DONE");
        private String status;
        StatusTypes(String status) {
            this.status = status;
        }
        public String status() {
            return status;
        }
    }

    public SchoolSession() {
    }

    public SchoolSession(int sessionid, Date sessiondate, Date starttime, Date endtime, LinkedList<Client> clientlist, LinkedList<Instructor> instructorlist, String roomid, String status, String remark) {
        this.sessionid = sessionid;
        this.sessiondate = sessiondate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.clientlist = clientlist;
        this.instructorlist = instructorlist;
        this.roomid = roomid;
        this.status = status;
        this.remark = remark;
    }

    public SchoolSession(Date sessiondate, Date starttime, Date endtime, LinkedList<Client> clientlist, LinkedList<Instructor> instructorlist, String roomid, String status, String remark) {
        this.sessiondate = sessiondate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.clientlist = clientlist;
        this.instructorlist = instructorlist;
        this.roomid = roomid;
        this.status = status;
        this.remark = remark;
    }

    public SchoolSession(String sessiondate, String starttime, String endtime, String roomid, String status, String remark) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            this.sessiondate = format.parse(sessiondate);
        }catch (Exception e){e.printStackTrace();}

        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String dateString = format.format(new Date());
            this.starttime = format.parse(starttime);;
            this.endtime = format.parse(endtime);;
        }catch (Exception e){e.printStackTrace();}
        this.sessionid=0;
        this.roomid = roomid;
        this.status = status;
        this.remark = remark;
    }

    public Integer getSessionid() {
        return sessionid;
    }

    public Date getSessiondate() {
       return this.sessiondate;
    }

    public String getSessiondateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.sessiondate);
    }

    public String getSessiondateUTCString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(this.sessiondate);
    }

    public Date getStarttime() {
        return starttime;
    }

    public String getStarttimeString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(this.starttime);
    }

    public String getStarttimeUTCString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(this.starttime);
    }

    public Date getEndtime() {
        return endtime;
    }

    public String getEndtimeString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(this.endtime);
    }

    public String getEndtimeUTCString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(this.endtime);
    }

    public LinkedList<Client> getClientlist() {
        return clientlist;
    }

    public LinkedList<Instructor> getInstructorlist() {
        return instructorlist;
    }

    public String getRoomid() {
        return roomid;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public String getClientlistToString(){
        if (clientlist==null){return "";}
        String clientstring="";
        for(int i=0;i<clientlist.size();i++){
            clientstring=clientstring+ clientlist.get(i).getClientid()+":";
        }
        return clientstring;
    }

    public void setClientlistFromString(String clientstring){
        LinkedList<Client> clientList = new LinkedList<Client>();
        String myclient="";
        Integer index=-1;
        while(clientstring.length()>0 & index!=0 & clientstring!=""){
            try{
                index = clientstring.indexOf(":");
                if(index>0){
                    myclient=clientstring.substring(0,index);
                    clientstring=clientstring.substring(index+1);
                    Client newclient = new Client();
                    newclient.setClientid(myclient);
                    clientList.add(newclient);
                }
            }catch (Exception e){}
        }
        this.clientlist=clientList;
    }

    public void setInstructorlistFromString(String instructorstring){
        LinkedList<Instructor> instructorList = new LinkedList<Instructor>();
        String myinstructor="";
        Integer index=-1;
        while(instructorstring.length()>0 & index!=0 & instructorstring!=""){
            try{
                index = instructorstring.indexOf(":");
                if(index>0){
                    myinstructor=instructorstring.substring(0,index);
                    instructorstring=instructorstring.substring(index+1);
                    Instructor newinstructor = new Instructor();
                    newinstructor.setInstructorid(myinstructor);
                    instructorList.add(newinstructor);
                }
            }catch (Exception e){}
        }
        this.instructorlist=instructorList;
    }

    public String getInstructorlistToString(){
        if (instructorlist==null){return "";}
        String instructorstring="";
        for(int i=0;i<instructorlist.size();i++){
            instructorstring=instructorstring+ instructorlist.get(i).getInstructorid()+":";
        }
        return instructorstring;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

    public void setSessiondate(Date sessiondate) {
        this.sessiondate= sessiondate;
    }

    public void setSessiondate(String sessiondate) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date mydate=new Date();
        try{
            mydate=dateFormat.parse(sessiondate);
        }catch (Exception e){}
        this.sessiondate=mydate;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public void setClientlist(LinkedList<Client> clientlist) {
        this.clientlist = clientlist;
    }

    public void setInstructorlist(LinkedList<Instructor> instructorlist) {
        this.instructorlist = instructorlist;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void addInstructor(Instructor instructor){
        this.instructorlist.add(instructor);
    }

    public void addClient(Client client){
        this.clientlist.add(client);
    }

    public void show(){
        System.out.println(this.sessionid);
        System.out.println(this.roomid);
        System.out.println(this.getSessiondateUTCString());
        System.out.println(this.getStarttimeUTCString());
        System.out.println(this.getEndtimeUTCString());
        System.out.println(this.getInstructorlistToString());
        System.out.println(this.getClientlistToString());
        System.out.println(this.remark);
    }
}
