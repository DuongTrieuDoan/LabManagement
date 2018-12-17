package domain;

public class Program {
    private String programid;
    private String programname;
    private String description;
    private Boolean active;

    public Program(){}
    public Program(String programid, String programname){
        this.programid=programid;
        this.programname=programname;
    }
    public Program(String programid, String programname, String description, Boolean active){
        this.programid=programid;
        this.programname=programname;
        this.description=description;
        this.active=active;
    }
//    get method
    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }
//set method
    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProgramid() {
        return programid;
    }

    public String getProgramname() {
        return programname;
    }

    public String getDescription() {
        return description;
    }

    public void show(){
        System.out.println(programid);
        System.out.println(programname);
        System.out.println(description);
        System.out.println(active);
    }
}
