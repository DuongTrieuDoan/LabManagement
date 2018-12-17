package Service;

import Repository.RepositoryClient;
import domain.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ServiceClient {
    private RepositoryClient repositoryClient;

    public ServiceClient() {
        repositoryClient=new RepositoryClient();
    }

    public Client findbyID(String id) throws Exception {
        Client client=null;
        try {
            LinkedList<Client> clientLinkedList =
                        new LinkedList<Client>(repositoryClient.QueryClient("SELECT * FROM CLIENT " +
                                "WHERE CLIENTID='" + id +"'", true));;
            if(!clientLinkedList.isEmpty()){client=clientLinkedList.get(0);}
            else{throw new Exception("The client doesn't exist");}
        } catch (Exception e) { e.printStackTrace(); throw e;
        }
        return client;
    }

    public List<Client> findAll() throws Exception{
        try {
            return repositoryClient.QueryClient("SELECT * FROM CLIENT", true);
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

    private String checkvalidation(Client client) throws Exception{
        String validationstring="";
        try{
            if(client.getClientid().isEmpty()){validationstring = validationstring + "<br/>" + "Client ID must not be empty";}
            if(client.getFirstname().isEmpty()){validationstring = validationstring + "<br/>" + "Firstname must not be empty";}
            if(client.getLastname().isEmpty()){validationstring = validationstring + "<br/>" + "Lastname must not be empty";}
            if(!Optional.ofNullable(client.getRegisterdate()).isPresent()){validationstring = validationstring + "<br/>" + "Start date must not be empty";}
            if(client.getActive().toString()==""){validationstring = validationstring + "<br/>" + "Active must not be empty";}
            if(!(client.getEmailaddress().isEmpty()||client.getEmailaddress()==null)){
                if(!validateEmail(client.getEmailaddress())){
                validationstring = validationstring + "<br/>" + "Email address is invalid";}}
            if(!(client.getPhone().isEmpty()||client.getPhone()==null)){
                if(!validatePhone(client.getPhone())){
                validationstring = validationstring + "<br/>" + "Phone number is invalid";}}
            if(validationstring!=""){validationstring = "<br/>Please check the following information:" + validationstring;}
        }catch(Exception e){e.printStackTrace(); throw e;}
        return validationstring;
    }

    public void add(Client client) throws Exception{
        String validationstring = checkvalidation(client);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryClient.insert(client, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry '" +client.getClientid()+ "'")>=0){
                throw new Exception("The Client with id " +client.getClientid()+ " is already existing. Please choose update function if you still want to update this person!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void update(Client client) throws Exception{
        String validationstring = checkvalidation(client);
        if (validationstring!=""){
            throw new Exception(validationstring);
        }
        try {
            repositoryClient.update(client, true);
        }catch(Exception e){
            if(e.toString().indexOf("Duplicate entry")>=0){
                throw new Exception("The client is already existing. Please choose update function if you still want to update it!");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");
        }
    }

    public void delete(Client client) throws Exception{
        try {
            if(repositoryClient.delete(client, true)==0){
                throw new Exception("The client with id " + client.getClientid() +" doesn't exist");
            }
        }catch(Exception e){
            if(e.toString().indexOf("foreign key constraint fails")>=0){
                throw new Exception("Cannot delete the Client with id " +client.getClientid()+ ". Please delete all client activities relating to the session first.");
            }
            System.out.println(e.toString());
            throw new Exception("Technical issue. Please try agian!");}
    }
}
