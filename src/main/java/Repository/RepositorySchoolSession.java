package Repository;

import domain.Client;
import domain.Instructor;
import domain.SchoolSession;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RepositorySchoolSession extends DBIntegration {

    public LinkedList<SchoolSession> query(String SQLString, Boolean closeconnection) throws Exception {
        LinkedList<SchoolSession> tmpList = new LinkedList<SchoolSession>();
        ResultSet rs;
        String clientstring=null;
        String instructorstring=null;
        RepositoryClient repositoryClient= new RepositoryClient();
        RepositoryInstructor repositoryInstructor = new RepositoryInstructor();
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {
                try {
                    SchoolSession tmpSchoolSession = new SchoolSession();
                    tmpSchoolSession.setSessionid((Integer)  rs.getObject("sessionid"));
                    tmpSchoolSession.setRoomid(rs.getObject("roomid").toString());
                    clientstring=rs.getObject("clients").toString();
                    if(!(clientstring.equals(null)||clientstring.equals(""))) {
                        clientstring = clientstring.substring(0, clientstring.length() - 1).replace(":", ",");
                        tmpSchoolSession.setClientlist(repositoryClient.QueryClient("SELECT * FROM CLIENT WHERE CLIENTID IN (" + clientstring + ")", false));
                    }
                    instructorstring=rs.getObject("instructors").toString();
                    if(!(instructorstring.equals(null)||instructorstring.equals(""))){
                        instructorstring = instructorstring.substring(0, instructorstring.length() - 1).replace(":", ",");
                        tmpSchoolSession.setInstructorlist(repositoryInstructor.query("SELECT * FROM INSTRUCTOR WHERE INSTRUCTORID IN (" + instructorstring + ")", false));
                    }
                    tmpSchoolSession.setSessiondate((Date)rs.getObject("sessiondate"));
                    tmpSchoolSession.setStarttime(((Date) rs.getObject("starttime")));
                    tmpSchoolSession.setEndtime(((Date) rs.getObject("endtime")));
                    tmpSchoolSession.setRemark(rs.getObject("remark").toString());
                    tmpSchoolSession.setStatus(rs.getObject("status").toString());
                    tmpList.add(tmpSchoolSession);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
             throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return tmpList;
    }

    public int update(SchoolSession schoolsession, Boolean closeconnection) throws Exception {
        Integer rslt=0;
        String SQLString="UPDATE SCHOOLSESSION SET " +
                " ROOMID='" + schoolsession.getRoomid() +"'," +
                " SESSIONDATE='" + schoolsession.getSessiondateUTCString() + "'," +
                " STARTTIME='" + schoolsession.getStarttimeUTCString() + "'," +
                " ENDTIME='" + schoolsession.getEndtimeUTCString() + "'," +
                " CLIENTS='" +schoolsession.getClientlistToString() +"'," +
                " INSTRUCTORS='" + schoolsession.getInstructorlistToString() +"'," +
                " REMARK ='" + schoolsession.getRemark() + "'," +
                " STATUS='" + schoolsession.getStatus() + "'" +
                " WHERE SESSIONID=" + schoolsession.getSessionid();
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

    public int insert(SchoolSession schoolsession, Boolean closeconnection) throws Exception{
        int rslt=0;
        String SQLString="INSERT SCHOOLSESSION ( SESSIONID, ROOMID, SESSIONDATE, STARTTIME, ENDTIME, CLIENTS, INSTRUCTORS, STATUS,REMARK) " +
                " VALUES('" + schoolsession.getSessionid() +"'," +
                "'" +schoolsession.getRoomid() +"'," +
                "'"+schoolsession.getSessiondateUTCString() +"'," +
                "'" + schoolsession.getStarttimeUTCString() + "'," +
                " '"+ schoolsession.getEndtimeUTCString() + "'," +
                " '"+ schoolsession.getClientlistToString() + "',"  +
                "'"+ schoolsession.getInstructorlistToString() + "'," +
                "'"+ schoolsession.getStatus() + "'," +
                "'"+ schoolsession.getRemark() + "')";
        try {
            super.SetConnection();
            rslt = super.sqlUpdate(SQLString);
            if (rslt==0)
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int delete(SchoolSession schoolsession, Boolean closeconnection) throws Exception{
        //This method should be changed to ensure relating data won't be affected by a deleting action of schoolsession
        int rslt=0;
        String SQLString="DELETE FROM schoolsession WHERE sessionID='" + schoolsession.getSessionid() +"'";
        System.out.println(SQLString);
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

    public int[] updateBath(List<SchoolSession> listschoolsession, Boolean closeconnection) throws Exception{
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listschoolsession.size();i++){
            SQLstring.add(" UPDATE SCHOOLSESSION SET " +
                    " ROOMID='" + listschoolsession.get(i).getRoomid() +"'," +
                    " SESSIONDATE='" + listschoolsession.get(i).getSessiondateUTCString() + "'," +
                    " STARTTIME='" + listschoolsession.get(i).getStarttimeUTCString() + "'," +
                    " ENDTIME='" + listschoolsession.get(i).getEndtimeUTCString() + "'," +
                    " CLIENTS='" +listschoolsession.get(i).getClientlistToString() +"'," +
                    " INSTRUCTORS='" + listschoolsession.get(i).getInstructorlistToString() +"'," +
                    " REMARK ='" + listschoolsession.get(i).getRemark() + "'," +
                    " STATUS='" + listschoolsession.get(i).getStatus() + "'"+
                    " WHERE SESSIONID=" + listschoolsession.get(i).getSessionid());
        }
        try {
            super.SetConnection();
            rslt = super.sqlUpdateBatch(SQLstring);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int insertInstructor(SchoolSession schoolsession, Instructor instructor, Boolean closeconnection) throws Exception {
        Integer rslt=0;

        String SQLString="UPDATE SCHOOLSESSION SET " +
                " INSTRUCTORS=CONCAT(INSTRUCTORS, + '" + instructor.getInstructorid() +":')" +
                " WHERE SESSIONID=" + schoolsession.getSessionid();
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

    public int insertClient(SchoolSession schoolsession, Client client, Boolean closeconnection) throws Exception {
        Integer rslt=0;

        String SQLString="UPDATE SCHOOLSESSION SET " +
                " CLIENTS=CONCAT(CLIENTs, + '" + client.getClientid() +":')" +
                " WHERE SESSIONID=" + schoolsession.getSessionid();
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
}
