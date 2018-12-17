package Repository;

import domain.Course;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class RepositoryCourse extends DBIntegration {

    public LinkedList<Course> query(String SQLString, Boolean closeconnection) {
        LinkedList<Course> tmpList = new LinkedList<Course>();
        ResultSet rs;
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {
                try {
                    Course tmpCourse = new Course();
                    tmpCourse.setCourseid(rs.getObject("courseid").toString());
                    tmpCourse.setProgramid(rs.getObject("programid").toString());
                    tmpCourse.setCoursename(rs.getObject("coursename").toString());
                    tmpCourse.setListinstructorid(rs.getObject("listinstructorid").toString());
                    tmpCourse.setSyllabus(rs.getObject("syllabus").toString());
                    tmpCourse.setStartdate(((Date) rs.getObject("startdate")));
                    tmpCourse.setEnddate(((Date) rs.getObject("enddate")));
                    tmpCourse.setActive((Boolean) rs.getObject("Active"));
                    tmpList.add(tmpCourse);
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

    public int update(Course course, Boolean closeconnection) {
        Integer rslt=0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="UPDATE COURSE SET COURSENAME ='" + course.getCoursename() + "', " +
                                           " LISTINSTRUCTORID ='" + course.getListinstructorid() +"'," +
                                            " SYLLABUS='" + course.getSyllabus() + "',"+
                                            " PROGRAMID='" + course.getProgramid() + "'," +
                                            " STARTDATE='" + course.getStartdateUTCString() + "'," +
                                            " ENDDATE='" + course.getEnddateUTCString() + "'," +
                                            " ACTIVE=" + course.getActive() +
                " WHERE courseID='" + course.getCourseid() +"'";
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

    public int insert(Course course, Boolean closeconnection){
        int rslt=0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="INSERT course ( PROGRAMID, COURSEID, COURSENAME, LISTINSTRUCTORID, STARTDATE, ENDDATE, SYlLABUS, ACTIVE) " +
                " VALUES('" + course.getProgramid() +"'," +
                "'" +course.getCourseid() +"'," +
                "'"+course.getCoursename() +"'," +
                "'" + course.getListinstructorid() + "'," +
                " '"+ course.getStartdateUTCString() + "'," +
                " '"+ course.getEnddateUTCString() + "',"  +
                "'"+ course.getSyllabus() + "',"
                + course.getActive() +")";
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

    public int delete(Course course, Boolean closeconnection) throws Exception{
        //This method should be changed to ensure relating data won't be affected by a deleting action of course
        int rslt=0;
        String SQLString="DELETE FROM course WHERE courseID='" + course.getCourseid() +"'";
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

    public int[] updateBath(List<Course> listcourse, Boolean closeconnection){
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listcourse.size();i++){
            SQLstring.add(" UPDATE COURSE SET " +
                    " PROGRAMID='" + listcourse.get(i).getProgramid() +"'," +
                    " COURSENAME='" +listcourse.get(i).getCoursename() +"'," +
                    " LISTINSTRUCTORID='" + listcourse.get(i).getListinstructorid() +"'," +
                    " STARTDATE='" + listcourse.get(i).getStartdateUTCString() + "'," +
                    " ENDDATE='" + listcourse.get(i).getEnddateUTCString() + "'," +
                    " SYLLABUS ='" + listcourse.get(i).getSyllabus() + "'," +
                    " ACTIVE=" + listcourse.get(i).getActive() +
                    " WHERE COURSEID='" + listcourse.get(i).getCourseid()+ "'");
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
