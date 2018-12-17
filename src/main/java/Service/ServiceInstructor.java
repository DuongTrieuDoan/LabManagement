package Service;

import Repository.RepositoryClientActivity;
import Repository.RepositoryInstructor;
import domain.Instructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ServiceInstructor {
    private RepositoryInstructor repositoryInstructor;

    public ServiceInstructor() {
        repositoryInstructor=new RepositoryInstructor();
    }

    public Instructor findbyID(String id) throws Exception {
        Instructor instructor=null;
        try {
            LinkedList<Instructor> instructorLinkedList =
                    new LinkedList<Instructor>(repositoryInstructor.query("SELECT * FROM INSTRUCTOR WHERE INSTRUCTORID='" + id + "'", true));
            if(!instructorLinkedList.isEmpty()){instructor=instructorLinkedList.getFirst();}
            else{throw new Exception("The instructor doesn't exist");}
        } catch (Exception e) { e.printStackTrace();
        }
        return instructor;
    }

    public List<Instructor> findAll() throws Exception{
        try {
            return repositoryInstructor.query("SELECT * FROM INSTRUCTOR", true);
        }catch (Exception e){throw e;}
    }

    public Boolean validateEmail(String emailaddress){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        return pattern.matcher(emailaddress).matches();
    }

    public Boolean validatePhone(String phonenumber){
        Pattern pattern = Pattern.compile("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$");
        return pattern.matcher(phonenumber).matches();
    }

    private String checkvalidation(Instructor instructor) throws Exception{
        String validationstring="";
        try{
            if(instructor.getInstructorid().isEmpty()){validationstring = validationstring + "<br/>" + "Instructor ID must not be empty";}
            if(instructor.getFirstname().isEmpty()){validationstring = validationstring + "<br/>" + "Firstname must not be empty";}
            if(instructor.getLastname().isEmpty()){validationstring = validationstring + "<br/>" + "Lastname must not be empty";}
            if(!Optional.ofNullable(instructor.getStartdate()).isPresent()){validationstring = validationstring + "<br/>" + "Start date must not be empty";}
            if(!(instructor.getEmailaddress().isEmpty()||instructor.getEmailaddress()==null)){
                if(!validateEmail(instructor.getEmailaddress())){
                    validationstring = validationstring + "<br/>" + "Email address is invalid";}}
            if(!(instructor.getPhone().isEmpty()||instructor.getPhone()==null)){
                if(!validatePhone(instructor.getPhone())){
                    validationstring = validationstring + "<br/>" + "Phone number is invalid";}}
            if(instructor.getActive().toString()==""){validationstring = validationstring + "<br/>" + "Active must not be empty";}
            if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){throw e;}
        return validationstring;
    }

    public void update(Instructor instructor) throws Exception{
        String validationstring = checkvalidation(instructor);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryInstructor.update(instructor, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The instructor is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void add(Instructor instructor) throws Exception{
        String validationstring = checkvalidation(instructor);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryInstructor.insert(instructor, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry '" +instructor.getInstructorid()+ "'")>=0){
                throw new Exception("The Instructor with id " +instructor.getInstructorid()+ " is already existing. Please choose update function if you still want to update this person!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void delete(Instructor instructor) throws Exception{
        try {
            RepositoryClientActivity repositoryClientActivity=new RepositoryClientActivity();
            Optional.ofNullable(repositoryClientActivity.existingInstructorInClientActivity(null, instructor) )
                    .ifPresent(x->{
                        throw new RuntimeException("Cannot remove instrutors existing in client activities.");
                    });


            if(repositoryInstructor.delete(instructor, true)==0){
                throw new Exception("The instructor with id " + instructor.getInstructorid() +" doesn't exist");
            }
        }catch(Exception e){throw e;}
    }
}
