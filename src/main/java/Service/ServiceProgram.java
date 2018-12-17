package Service;

import Repository.RepositoryProgram;
import domain.Program;

import java.util.LinkedList;
import java.util.List;

public class ServiceProgram {
    private RepositoryProgram repositoryProgram;

    public ServiceProgram() {
        repositoryProgram=new RepositoryProgram();
    }

    public Program findbyID(String id) throws Exception {
        Program program=null;
        try {
            LinkedList<Program> programLinkedList =
                        new LinkedList<Program>(repositoryProgram.query("SELECT * FROM PROGRAM " +
                                "WHERE PROGRAMID='" + id +"'", true));
            if(!programLinkedList.isEmpty()){program=programLinkedList.get(0);}
            else{throw new Exception("The program doesn't exist");}
        } catch (Exception e) { e.printStackTrace();
        }
        return program;
    }

    public List<Program> findAll() throws Exception{
        try {
            return repositoryProgram.query("SELECT * FROM PROGRAM", true);
        }catch (Exception e){throw e;}
    }

    private String checkvalidation(Program program) throws Exception{
        String validationstring="";
        try{
            if(program.getProgramid().isEmpty()){validationstring = validationstring + "<br/>" + "Program ID must not be empty";}
            if(program.getProgramname().isEmpty()){validationstring = validationstring + "<br/>" + "Program name must not be empty";}
            if(program.getDescription().isEmpty()){validationstring = validationstring + "<br/>" + "Descriptioin must not be empty";}
            if(program.getActive().toString()==""){validationstring = validationstring + "<br/>" + "Active must not be empty";}
            if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){throw e;}
        return validationstring;
    }

    public void add(Program program) throws Exception{
        String validationstring = checkvalidation(program);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryProgram.insert(program, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry '" +program.getProgramid()+ "'")>=0){
                throw new Exception("The Program with id " +program.getProgramid()+ " is already existing. Please choose update function if you still want to update this person!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void update(Program program) throws Exception{
        String validationstring = checkvalidation(program);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryProgram.update(program, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The program is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void delete(Program program) throws Exception{
        try {
            if(repositoryProgram.delete(program, true)==0){
                throw new Exception("The program with id " + program.getProgramid() +" doesn't exist");
            }
        }catch(Exception e){
            if(e.toString().indexOf("a foreign key constraint fails")>=0){
                throw new Exception("There are some course under the Program with id " +program.getProgramid());
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }
}
