package Repository;

import domain.Instructor;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RepositoryInstructor extends DBIntegration {
    public LinkedList<Instructor> query(String SQLString, Boolean closeconnection) {
        LinkedList<Instructor> tmpList = new LinkedList<Instructor>();
        ResultSet rs;
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {
                try {
                    Instructor tmpInstructor = new Instructor();
                    tmpInstructor.setInstructorid(rs.getObject("instructorid").toString());
                    tmpInstructor.setFirstname(rs.getObject("firstname").toString());
                    tmpInstructor.setLastname(rs.getObject("lastname").toString());
                    tmpInstructor.setAddress(rs.getObject("address").toString());
                    tmpInstructor.setEmailaddress(rs.getObject("emailaddress").toString());
                    tmpInstructor.setStartdate(((Date) rs.getObject("startdate")));
                    tmpInstructor.setPhone(rs.getObject("phone").toString());
                    tmpInstructor.setActive((Boolean) rs.getObject("Active"));
                    tmpList.add(tmpInstructor);
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

    public int update(Instructor instructor, Boolean closeconnection) {
        Integer rslt=0;
        String SQLString="UPDATE INSTRUCTOR SET " +
                " ADDRESS='" + instructor.getAddress() +"'," +
                " EMAILADDRESS='" +instructor.getEmailaddress() +"'," +
                " FIRSTNAME='" + instructor.getFirstname() +"'," +
                " LASTNAME='" + instructor.getLastname() + "'," +
                " STARTDATE='" + instructor.getStartdateUTCString()+ "'," +
                " PHONE ='" + instructor.getPhone() + "'," +
                " ACTIVE=" + instructor.getActive() +
                " WHERE INSTRUCTORID='" + instructor.getInstructorid()+ "'";
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

    public int insert(Instructor instructor, Boolean closeconnection) throws Exception {
        int rslt=0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="INSERT INSTRUCTOR ( INSTRUCTORID, FIRSTNAME, LASTNAME, ADDRESS, PHONE, STARTDATE, EMAILADDRESS, ACTIVE) " +
                " VALUES('" + instructor.getInstructorid() +"'," +
                "'" +instructor.getFirstname() +"'," +
                "'"+instructor.getLastname() +"'," +
                "'" + instructor.getAddress() + "'," +
                " '"+ instructor.getPhone() + "'," +
                " '"+ instructor.getStartdateUTCString() + "',"  +
                "'"+ instructor.getEmailaddress() + "',"
                + instructor.getActive() +")";
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

    public int delete(Instructor instructor, Boolean closeconnection){
        //This method should be changed to ensure relating data won't be affected by a deleting action of course
        int rslt=0;
        String SQLString="DELETE FROM INSTRUCTOR WHERE INSTRUCTORID='" + instructor.getInstructorid() +"'";
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

    public int[] updateBath(List<Instructor> listinstructor, Boolean closeconnection){
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listinstructor.size();i++){
            SQLstring.add(" UPDATE INSTRUCTOR SET " +
                    " ADDRESS='" + listinstructor.get(i).getAddress() +"'," +
                    " EMAILADDRESS='" +listinstructor.get(i).getEmailaddress() +"'," +
                    " FIRSTNAME='" + listinstructor.get(i).getFirstname() +"'," +
                    " LASTNAME='" + listinstructor.get(i).getLastname() + "'," +
                    " STARTDATE='" + listinstructor.get(i).getStartdateUTCString() + "'," +
                    " PHONE ='" + listinstructor.get(i).getPhone() + "'," +
                    " ACTIVE=" + listinstructor.get(i).getActive() +
                    " WHERE INSTRUCTORID='" + listinstructor.get(i).getInstructorid()+ "'");
        }
        try {
            super.SetConnection();
            rslt = super.sqlUpdateBatch(SQLstring);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }
}
