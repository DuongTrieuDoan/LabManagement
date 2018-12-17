package domain;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Client extends Person {
    private Date registerdate;
    private String clientid;
    private Boolean active;

    public Client(String clientid, String firstname, String lastname, String phone, String address, String emailaddress, Date registerdate,  Boolean active) {
        super(firstname, lastname, phone, address, emailaddress);
        this.registerdate = registerdate;
        this.clientid = clientid;
        this.active = active;
    }
    public Client(String clientid, String firstname, String lastname, String phone, String address, String emailaddress, String registerdate,  Boolean active) {
        super(firstname, lastname, phone, address, emailaddress);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            this.registerdate = format.parse(registerdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clientid = clientid;
        this.active = active;
    }
    public Client() {
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public String getRegisterdateUTCString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(this.registerdate);
    }

    public String getRegisterdateString(){
        String mydate=registerdate.toString();
        return mydate;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void show(){
        System.out.println(this.clientid);
        System.out.println(this.firstname);
        System.out.println(this.lastname);
        System.out.println(this.address);
        System.out.println(this.emailaddress);
        System.out.println(this.registerdate);
        System.out.println(this.phone);
        System.out.println(this.active);
    }
}
