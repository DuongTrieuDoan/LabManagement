package Service;


import Repository.*;
import domain.*;

import java.util.*;

public class Testing {
    public static void main(String[] args) {
//
        try {

            RepositoryClientActivity repositoryClientActivity = new RepositoryClientActivity();
            Instructor instructor = new Instructor();
            instructor.setInstructorid("6000002");
            SchoolSession schoolSession = new SchoolSession();
            schoolSession.setSessionid(5);

            LinkedList<ClientActivity> listclientActivity= repositoryClientActivity.existingInstructorInClientActivity(null, instructor);
            System.out.println("a'");
//              ServiceClient serviceClient=new ServiceClient();
//              Client client=new Client();
//              serviceClient.findbyID("abc");
//            }catch (Exception e){e.printStackTrace();}
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
