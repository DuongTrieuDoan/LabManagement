package Service;

import Repository.RepositoryClientActivity;
import domain.ClientActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ServiceClientActivity {
    private RepositoryClientActivity repositoryClientActivity;

    public ServiceClientActivity() {
        repositoryClientActivity=new RepositoryClientActivity();
    }

    private String checkvalidation(ClientActivity clientActivity) throws Exception{
        String validationstring="";
        try{
            if(clientActivity.getClientid().isEmpty()){validationstring = validationstring + "<br/>" + "Client must not be empty";}
            if(!Optional.ofNullable(clientActivity.getCourseid()).isPresent()){validationstring = validationstring + "<br/>" + "Course must not be empty";}
            if(clientActivity.getInstructorlist().isEmpty()){validationstring = validationstring + "<br/>" + "Instructor must not be empty";}
            if(clientActivity.getNotes().isEmpty()){validationstring = validationstring + "<br/>" + "Notes must not be empty";}
            if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){throw e;}
        return validationstring;
    }

    public ClientActivity findbyID(String activityid) throws Exception{
        try {
            LinkedList<ClientActivity> clientActivityLinkedList= repositoryClientActivity.query("SELECT * FROM CLIENTACTIVITY " +
                                            " WHERE ACTIVITYID = '" + activityid + "'",
                    true);
            if(clientActivityLinkedList.isEmpty()){
                throw new Exception("Client activity" + activityid + "was not found.");
            }else{
                return clientActivityLinkedList.getFirst();
            }
        }catch (Exception e){throw e;}
    }

    public List<ClientActivity> findAllByClientIDSession(String clientid, String sessionid) throws Exception{
        try {
            return repositoryClientActivity.query("SELECT * FROM CLIENTACTIVITY " +
                                            " WHERE CLIENTID = '" + clientid + "' AND SESSIONID=" +sessionid,
                    true);
        }catch (Exception e){throw e;}
    }

    public List<ClientActivity> findbyParameter(Map<String, Object> parameterlist){
        List<ClientActivity> mylist = repositoryClientActivity.findbyParameter(parameterlist);
        if(Optional.ofNullable(mylist).isPresent()){return mylist;}else {return null;}
    }

    public void add(ClientActivity clientActivity) throws Exception{
        String validationstring = checkvalidation(clientActivity);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryClientActivity.insert(clientActivity, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The session is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void update(ClientActivity clientActivity) throws Exception{
        String validationstring = checkvalidation(clientActivity);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryClientActivity.update(clientActivity, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The session is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void delete(ClientActivity clientActivity) throws Exception{
        try {
            if(repositoryClientActivity.delete(clientActivity, true)==0){
                throw new Exception("The activity doesn't exist");
            }
        }catch(Exception e){throw e;}
    }
}
