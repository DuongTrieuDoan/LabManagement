package domain;

public class Function {
    private String functionid;
    private String functioname;

    public Function(String functionid, String functioname) {
        this.functionid = functionid;
        this.functioname = functioname;
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public String getFunctioname() {
        return functioname;
    }

    public void setFunctioname(String functioname) {
        this.functioname = functioname;
    }
}
