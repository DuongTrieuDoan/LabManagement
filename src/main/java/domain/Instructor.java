package domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Instructor extends Person{
    Date startdate;
    String instructorid;
    Boolean active;

    public Instructor() {
    }

    public Instructor(String instructorid) {
        this.instructorid=instructorid;
    }

    public Instructor(String instructorid, String firstname, String lastname, String phone, String address, String emailaddress, Date startdate,  Boolean active) {
        super(firstname, lastname, phone, address, emailaddress);
        this.startdate = startdate;
        this.instructorid = instructorid;
        this.active = active;
    }
    public Instructor(String instructorid, String firstname, String lastname, String phone, String address, String emailaddress, String startdate,  Boolean active) {
        super(firstname, lastname, phone, address, emailaddress);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            this.startdate = format.parse(startdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.instructorid = instructorid;
        this.active = active;
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

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getInstructorid() {
        return instructorid;
    }

    public void setInstructorid(String instructorid) {
        this.instructorid = instructorid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void show(){
        System.out.println(this.instructorid);
        System.out.println(this.firstname);
        System.out.println(this.lastname);
        System.out.println(this.address);
        System.out.println(this.emailaddress);
        System.out.println(this.startdate);
        System.out.println(this.phone);
        System.out.println(this.active);
    }
}