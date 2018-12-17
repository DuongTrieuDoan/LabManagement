package domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;

public class Course {
    private String courseid;
    private String programid;
    private String coursename;
    private String listinstructorid; //instructors' ids are separeated by semicolon. Example: KYLE01;FAYE01;DUONG01
    private Date startdate;
    private Date enddate;
    private String syllabus;
    private Boolean active;

    public Course() {
    }
    public Course(String courseid, String programid, String coursename, String listinstructorid, Date startdate, Date enddate, String syllabus, Boolean active) {
        this.courseid = courseid;
        this.programid = programid;
        this.coursename = coursename;
        this.listinstructorid = listinstructorid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.syllabus = syllabus;
        this.active=active;
    }
    public Course(String courseid, String programid, String coursename, String listinstructorid, String startdate, String enddate, String syllabus, Boolean active) {
        this.courseid = courseid;
        this.programid = programid;
        this.coursename = coursename;
        this.listinstructorid = listinstructorid;

        try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        this.startdate = format.parse(startdate);
        this.enddate = format.parse(enddate);
        }catch (Exception e){e.printStackTrace();}

        this.syllabus = syllabus;
        this.active=active;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setListinstructorid(String listinstructorid) {
        this.listinstructorid = listinstructorid;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setStartdate(String startdate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            this.startdate = format.parse(startdate);
        }catch (Exception e){e.printStackTrace();}
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setEnddate(String enddate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            this.enddate = format.parse(enddate);
        }catch (Exception e){e.printStackTrace();}
    }

    public void setSyllabus(String syslabus) {
        this.syllabus = syslabus;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCourseid() {
        return courseid;
    }

    public String getProgramid() {
        return programid;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getListinstructorid() {
        return listinstructorid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public String getStartdateUTCString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(this.startdate);
    }

    public String getStartdateString(){
        String mydate=startdate.toString();
        return mydate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public String getEnddateUTCString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(this.enddate);
    }

    public String getEnddateString(){
       String mydate=enddate.toString();
        return mydate;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public Boolean getActive() {
        return active;
    }

    public void show(){
        System.out.println(this.courseid);
        System.out.println(this.coursename);
        System.out.println(this.programid);
        System.out.println(this.listinstructorid);
        System.out.println(this.syllabus);
        System.out.println(this.startdate);
        System.out.println(this.enddate);
        System.out.println(this.active);
    }
}
