package domain;

public class UserProfile {
    private Person user;
    private String uid;
    private String password;
    private String loginstatus;
    private String loginhistory;
    private Role role;

    public UserProfile(Person user, String uid, String password, Role role) {
        this.user = user;
        this.uid = uid;
        this.password = password;
        this.role=role;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(String loginstatus) {
        this.loginstatus = loginstatus;
    }

    public String getLoginhistory() {
        return loginhistory;
    }

    public void setLoginhistory(String loginhistory) {
        this.loginhistory = loginhistory;
    }
}
