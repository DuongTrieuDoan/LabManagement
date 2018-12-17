package domain;
import java.util.List;
public class Role {
    private String rolename;
    private List<Function> funcionList;

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role(String rolename, List<Function> funcionList) {
        this.rolename = rolename;
        this.funcionList = funcionList;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Function> getFuncionList() {
        return funcionList;
    }

    public void setFuncionList(List<Function> funcionList) {
        this.funcionList = funcionList;
    }
}
