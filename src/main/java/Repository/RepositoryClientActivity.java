package Repository;

import domain.Client;
import domain.ClientActivity;
import domain.Instructor;
import domain.SchoolSession;
import jdk.nashorn.internal.runtime.options.Option;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RepositoryClientActivity extends DBIntegration {

    public LinkedList<ClientActivity> query(String SQLString, Boolean closeconnection) {
        LinkedList<ClientActivity> tmpList = new LinkedList<ClientActivity>();
        ResultSet rs;
        String clientstring="";
        String instructorstring="";
        RepositoryClient repositoryClient= new RepositoryClient();
        RepositoryInstructor repositoryInstructor = new RepositoryInstructor();
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {
                try {
                    ClientActivity tmpClientActivity = new ClientActivity();
                    tmpClientActivity.setSessionid((Integer) rs.getObject("sessionid"));
                    tmpClientActivity.setActivityid(rs.getObject("activityid").toString());
                    instructorstring=rs.getObject("instructors").toString();
                    if(!(instructorstring.equals(null)||instructorstring.equals(""))){
                        instructorstring = instructorstring.substring(0, instructorstring.length() - 1).replace(":", ",");
                        tmpClientActivity.setInstructorlist(repositoryInstructor.query("SELECT * FROM INSTRUCTOR WHERE INSTRUCTORID IN (" + instructorstring + ")", false));
                    }
                    tmpClientActivity.setCourseid(rs.getObject("courseid").toString());
                    tmpClientActivity.setClientid( rs.getObject("clientid").toString());
                    tmpClientActivity.setNotes(rs.getObject("Notes").toString());
                    tmpList.add(tmpClientActivity);
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

    public int update(ClientActivity clientactivity, Boolean closeconnection) {
        Integer rslt=0;
        String SQLString="UPDATE CLIENTACTIVITY SET " +
                " CLIENTID='" + clientactivity.getClientid() +"'," +
                " SESSIONID='" + clientactivity.getSessionid() + "'," +
                " COURSEID='" + clientactivity.getCourseid() + "'," +
                " INSTRUCTORS='" + clientactivity.getInstructorlistToString() +"'," +
                " NOTES ='" + clientactivity.getNotes() + "'" +
                " WHERE ACTIVITYID='" + clientactivity.getActivityid()+ "'";
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

    public int insert(ClientActivity clientactivity, Boolean closeconnection){
        int rslt=0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="INSERT CLIENTACTIVITY ( ACTIVITYID, CLIENTID, SESSIONID, COURSEID, INSTRUCTORS, NOTES) " +
                " VALUES('" + clientactivity.getActivityid()+"'," +
                "'" +clientactivity.getClientid() +"'," +
                ""+clientactivity.getSessionid() +"," +
                "'" + clientactivity.getCourseid() + "'," +
                "'"+ clientactivity.getInstructorlistToString() + "'," +
                "'"+ clientactivity.getNotes() + "')";
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

    public int delete(ClientActivity clientactivity, Boolean closeconnection){
        //This method should be changed to ensure relating data won't be affected by a deleting action of clientactivity
        int rslt=0;
        String SQLString="DELETE FROM CLIENTACTIVITY WHERE ACTIVITYID='" + clientactivity.getActivityid() +"'";
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

    public int[] UpdateBath(List<ClientActivity> listclientactivity, Boolean closeconnection){
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listclientactivity.size();i++){
            SQLstring.add(" UPDATE CLIENTACTIVITY SET " +
                    " CLIENTID='" + listclientactivity.get(i).getClientid() +"'," +
                    " SESSIONID='" + listclientactivity.get(i).getSessionid() + "'," +
                    " COURSEID='" + listclientactivity.get(i).getCourseid() + "'," +
                    " INSTRUCTORS='" + listclientactivity.get(i).getInstructorlistToString() +"'," +
                    " NOTES ='" + listclientactivity.get(i).getNotes() + "'" +
                    " WHERE ACTIVITYID='" + listclientactivity.get(i).getActivityid()+ "'");
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

    public LinkedList<ClientActivity> findbyParameter(Map<String, Object> parameterlist){
        StringBuffer sqlstring=new StringBuffer();
       Optional.ofNullable(parameterlist).ifPresent(s ->{
           s.forEach((k,v)->{if(k.indexOf("<?parameter>")>=0){sqlstring.append(" " +k.replace("<?parameter>",v.toString())+ " AND");}
               else if(v instanceof String){sqlstring.append(" " + k +"='" + v + "' AND");}
                                else if(v instanceof Date){sqlstring.append(" " + k +"='" + v.toString() + "' AND");}
                                else{sqlstring.append(" " + k +"=" + v + " AND ");}
                            });
       });
       if(sqlstring.toString().isEmpty()){return null;}
       else {
           sqlstring.insert(0, "SELECT * FROM CLIENTACTIVITY WHERE");
           sqlstring.delete(sqlstring.length() - 4, sqlstring.length());
           LinkedList<ClientActivity> myCollecion = query(sqlstring.toString(), true);
           if (myCollecion.isEmpty()) {
               return null;
           } else {
               return query(sqlstring.toString(), true);
           }
       }
   }

    public LinkedList<ClientActivity> existingInstructorInClientActivity(SchoolSession schoolSession, Instructor instructor) throws Exception{
        try {
            Map<String, Object> parameterstring = new HashMap<String, Object>();
            parameterstring.put("INSTR(INSTRUCTORS,'<?parameter>')>0", instructor.getInstructorid());
            if (Optional.ofNullable(schoolSession).isPresent()) {
                parameterstring.put("SESSIONID", schoolSession.getSessionid());
            }
            return findbyParameter(parameterstring);
        }catch (Exception e){e.printStackTrace(); throw e;}
    }

    public LinkedList<ClientActivity> existingClientInClientActivity(SchoolSession schoolSession, Client client) throws Exception{
        try {
            Map<String, Object> parameterstring = new HashMap<String, Object>();
            parameterstring.put("CLIENTID", client.getClientid());
            if (Optional.ofNullable(schoolSession).isPresent()) {
                parameterstring.put("SESSIONID", schoolSession.getSessionid());
            }
            return findbyParameter(parameterstring);
        }catch (Exception e){e.printStackTrace(); throw e;}
    }
}
