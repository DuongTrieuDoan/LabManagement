package domain;
public abstract class Person {
    protected String firstname;
    protected String lastname;
    protected String emailaddress;
    protected String phone;
    protected String address;

    Person(){
    }
    Person(String firstname, String lastname, String phone, String address, String emailaddress){
        this.firstname=firstname;
        this.lastname=lastname;
        this.phone=phone;
        this.address=address;
        this.emailaddress=emailaddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
