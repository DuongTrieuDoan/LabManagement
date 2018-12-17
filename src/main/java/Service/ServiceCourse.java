package Service;

import Repository.RepositoryCourse;
import domain.Course;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ServiceCourse {
    private RepositoryCourse repositoryCourse;

    public ServiceCourse() {
        repositoryCourse=new RepositoryCourse();
    }

    private String checkvalidation(Course course) throws Exception{
        String validationstring="";
        try{
            if(course.getCourseid().isEmpty()){validationstring = validationstring + "<br/>" + "Course ID must not be empty";}
            if(course.getCoursename().isEmpty()){validationstring = validationstring + "<br/>" + "Course name must not be empty";}
            if(course.getListinstructorid().isEmpty()){validationstring = validationstring + "<br/>" + "List of instrutors must not be empty";}
            if(course.getSyllabus().isEmpty()){validationstring = validationstring + "<br/>" + "Syllabus must not be empty";}
            if(!Optional.ofNullable(course.getStartdate()).isPresent()){validationstring = validationstring + "<br/>" + "Start date must not be empty";}
            if(!Optional.ofNullable(course.getEnddate()).isPresent()){validationstring = validationstring + "<br/>" + "End date must not be empty";}
            if(course.getActive().toString()==""){validationstring = validationstring + "<br/>" + "Active must not be empty";}
            if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){throw e;}
        return validationstring;
    }

    public List<Course> findAll() throws Exception{
        try {
            return repositoryCourse.query("SELECT * FROM COURSE", true);
        }catch (Exception e){throw e;}
    }

    public List<Course> findAllByProgramID(String programid) throws Exception{
        try {
            return repositoryCourse.query("SELECT * FROM COURSE WHERE PROGRAMID='" + programid + "'", true);
        }catch (Exception e){throw e;}
    }

    public Course findbyID(String id) throws Exception {
        Course course=null;
        try {
            LinkedList<Course> courseLinkedList =
                    new LinkedList<Course>(repositoryCourse.query("SELECT * FROM COURSE WHERE COURSEID='" + id + "'", true));
            if(!courseLinkedList.isEmpty()){course=courseLinkedList.getFirst();}
            else{throw new Exception("The course doesn't exist");}
        } catch (Exception e) { e.printStackTrace();
        }
        return course;
    }

    public void add(Course course) throws Exception{
        String validationstring = checkvalidation(course);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryCourse.insert(course, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry '" +course.getCourseid()+ "'")>=0){
                throw new Exception("The Course with id " +course.getCourseid()+ " is already existing. Please choose update function if you still want to update this person!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void update(Course course) throws Exception{
        String validationstring = checkvalidation(course);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryCourse.update(course, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The course is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void delete(Course course) throws Exception{
        try {
            if(repositoryCourse.delete(course, true)==0){
                throw new Exception("The course with id " + course.getCourseid() +" doesn't exist");
            }
        }catch(Exception e){          if(e.toString().indexOf("a foreign key constraint fails")>=0){
            throw new Exception("There are some client activities in the cousre with id " +course.getCourseid());
        }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }
}
