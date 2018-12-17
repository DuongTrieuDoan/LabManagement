package Repository;
import java.sql.*;

import domain.Program;

import java.util.*;

public class RepositoryProgram extends DBIntegration {

    public List<Program> query(String SQLString, Boolean closeconnection) {
        List<Program> tmpList = new LinkedList<Program>();
        ResultSet rs;
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {
                try {
                    Program tmpProgram = new Program();
                    tmpProgram.setProgramid(rs.getObject("programid").toString());
                    tmpProgram.setProgramname(rs.getObject("programname").toString());
                    tmpProgram.setDescription(rs.getObject("description").toString());
                    tmpProgram.setActive(((Boolean) rs.getObject("active")));
                    tmpList.add(tmpProgram);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(closeconnection){CloseConnection();}
        }
            return tmpList;
    }

    public Boolean execute(String SQLString, Boolean closeconnection) {
            Boolean rslt=false;
            try {
                super.SetConnection();
                rslt = super.sqlExecution(SQLString);
                super.CloseConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(closeconnection){CloseConnection();}
            }
            return rslt;
        }

    public int update(Program program, Boolean closeconnection) {
        Integer rslt=0;
        String SQLString="UPDATE PROGRAM SET " +
                " PROGRAMNAME ='" + program.getProgramname() + "'," +
                " DESCRIPTION ='" + program.getDescription()+ "',"
                + " ACTIVE=" + program.getActive()
                +" WHERE PROGRAMID='" + program.getProgramid() +"'";
        try {
            super.SetConnection();
            rslt = super.sqlUpdate(SQLString);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int insert(Program program, Boolean closeconnection) throws Exception {
        Integer rslt=0;
        String SQLString="INSERT PROGRAM (PROGRAMID, PROGRAMNAME, DESCRIPTION, ACTIVE) " +
                " VALUES('" + program.getProgramid() +"','"+program.getProgramname() +"','" + program.getDescription() +"'," + program.getActive().booleanValue()+")";
        try {
            super.SetConnection();
            rslt = super.sqlUpdate(SQLString);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int delete(Program program, Boolean closeconnection) throws Exception {
        //This method should be change to ensure there is no effect on other data when deleting a program
        int rslt=0;
        String SQLString="DELETE FROM PROGRAM WHERE PROGRAMID='" + program.getProgramid() +"'";
        try {
            super.SetConnection();
            rslt = super.sqlUpdate(SQLString);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int[] update(List<Program> listprogram, Boolean closeconnection){
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listprogram.size();i++){
            SQLstring.add(" UPDATE PROGRAM SET PROGRAMNAME ='"+listprogram.get(i).getProgramname()+"', DESCRIPTION='" + listprogram.get(i).getDescription() + "' WHERE PROGRAMID='" + listprogram.get(i).getProgramid() +"'\n");
        }
        System.out.println(SQLstring.toString());
        try {
            super.SetConnection();
            rslt = super.sqlUpdateBatch(SQLstring);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseConnection();
        }
        return rslt;
    }
}
