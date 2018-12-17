package Repository;

import domain.ClassRoom;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RepositoryClassRoom extends Repository.DBIntegration {

    public LinkedList<ClassRoom> query(String SQLString, Boolean closeconnection) {
        LinkedList<ClassRoom> tmpList = new LinkedList<ClassRoom>();
        ResultSet rs;
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {

                    ClassRoom tmpClassRoom = new ClassRoom();
                    tmpClassRoom.setRoomid(rs.getObject("roomid").toString());
                    tmpClassRoom.setRoomnumber(rs.getObject("roomnumber").toString());
                    tmpClassRoom.setAddress(rs.getObject("address").toString());
                    tmpClassRoom.setUnavailabledatelist(rs.getObject("unavailabledate").toString(),"yyMMdd");
                    tmpClassRoom.setNumberofseats((Integer) rs.getObject("NumberofSeat"));
                    tmpClassRoom.setActive((Boolean) rs.getObject("Active"));
                    tmpList.add(tmpClassRoom);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return tmpList;
    }

    public int update(ClassRoom classroom, Boolean closeconnection) throws Exception {
        Integer rslt=0;
        String SQLString="UPDATE CLASSROOM SET " +
                " ADDRESS='" + classroom.getAddress() +"'," +
                " ROOMNUMBER='" +classroom.getRoomnumber() +"'," +
                " NUMBEROFSEAT=" + classroom.getNumberofseats() +"," +
                " UNAVAILABLEDATE='" + classroom.getUnavailabledatelistString("yyMMdd") + "'," +
                " ACTIVE=" + classroom.getActive() +
                " WHERE ROOMID='" + classroom.getRoomid()+ "'";
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

    public int insert(ClassRoom classroom, Boolean closeconnection) throws Exception {
        int rslt=0;
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="INSERT CLASSROOM ( ROOMID, ROOMNUMBER, ADDRESS, NUMBEROFSEAT, UNAVAILABLEDATE, ACTIVE) " +
                " VALUES('" + classroom.getRoomid() +"'," +
                "'" +classroom.getRoomnumber() +"'," +
                "'"+classroom.getAddress() +"'," +
                " "+ classroom.getNumberofseats() + "," +
                " '"+ classroom.getUnavailabledatelistString("yyMMdd") + "',"  +
                classroom.getActive() +")";
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

    public int delete(ClassRoom classroom, Boolean closeconnection) throws Exception{
        //This method should be changed to ensure relating data won't be affected by a deleting action of course
        int rslt=0;
        String SQLString="DELETE FROM CLASSROOM WHERE ROOMID='" + classroom.getRoomid() +"'";
        try {
            super.SetConnection();
            rslt = super.sqlUpdate(SQLString);
            super.CloseConnection();
        } catch (Exception e) {
            e.printStackTrace(); throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int[] updateBath(List<ClassRoom> listclassroom, Boolean closeconnection){
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listclassroom.size();i++){
            SQLstring.add(" UPDATE CLASSROOM SET " +
                    " ROOMNUMBER='" + listclassroom.get(i).getRoomnumber() +"'," +
                    " ADDRESS='" +listclassroom.get(i).getAddress() +"'," +
                    " NUMBEROFSEAT=" + listclassroom.get(i).getNumberofseats() +"," +
                    " UNAVAILABLEDATE='" + listclassroom.get(i).getUnavailabledatelistString("yyMMdd") + "'," +
                    " ACTIVE=" + listclassroom.get(i).getActive() +
                    " WHERE ROOMID='" + listclassroom.get(i).getRoomid()+ "'");
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
