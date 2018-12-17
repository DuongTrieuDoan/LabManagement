package Repository;

import domain.Client;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RepositoryClient extends DBIntegration {
    public LinkedList<Client> QueryClient(String SQLString, Boolean closeconnection) {
        LinkedList<Client> tmpList = new LinkedList<Client>();
        ResultSet rs;
        try {
            super.SetConnection();
            rs = super.sqlQuery(SQLString);
            while (rs.next()) {
                try {
                    Client tmpClient = new Client();
                    tmpClient.setClientid(rs.getObject("clientid").toString());
                    tmpClient.setFirstname(rs.getObject("firstname").toString());
                    tmpClient.setLastname(rs.getObject("lastname").toString());
                    tmpClient.setAddress(rs.getObject("address").toString());
                    tmpClient.setEmailaddress(rs.getObject("emailaddress").toString());
                    tmpClient.setRegisterdate(((Date) rs.getObject("registerdate")));
                    tmpClient.setPhone(rs.getObject("phone").toString());
                    tmpClient.setActive((Boolean) rs.getObject("Active"));
                    tmpList.add(tmpClient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(closeconnection){CloseConnection();};
        }
        return tmpList;
    }

    public int update(Client client, Boolean closeconnection) throws Exception {
        Integer rslt=0;
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="UPDATE CLIENT SET " +
                " ADDRESS='" + client.getAddress() +"'," +
                " EMAILADDRESS='" +client.getEmailaddress() +"'," +
                " FIRSTNAME='" + client.getFirstname() +"'," +
                " LASTNAME='" + client.getLastname() + "'," +
                " REGISTERDATE='" + client.getRegisterdateUTCString() + "'," +
                " PHONE ='" + client.getPhone() + "'," +
                " ACTIVE=" + client.getActive() +
                " WHERE CLIENTID='" + client.getClientid()+ "'";
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

    public int insert(Client client, Boolean closeconnection) throws Exception {
        int rslt=0;
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQLString="INSERT CLIENT ( CLIENTID, FIRSTNAME, LASTNAME, ADDRESS, PHONE, REGISTERDATE, EMAILADDRESS, ACTIVE) " +
                " VALUES('" + client.getClientid() +"'," +
                "'" +client.getFirstname() +"'," +
                "'"+client.getLastname() +"'," +
                "'" + client.getAddress() + "'," +
                " '"+ client.getPhone() + "'," +
                " '"+ client.getRegisterdateUTCString() + "',"  +
                "'"+ client.getEmailaddress() + "',"
                + client.getActive() +")";
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

    public int delete(Client client, Boolean closeconnection) throws Exception{
        //This method should be changed to ensure relating data won't be affected by a deleting action of course
        int rslt=0;
        String SQLString="DELETE FROM CLIENT WHERE CLIENTID='" + client.getClientid() +"'";
        try {
            super.SetConnection();
            rslt = super.sqlUpdate(SQLString);
            super.CloseConnection();
        } catch (Exception e) {
//            e.printStackTrace();
            throw e;
        }finally {
            if(closeconnection){CloseConnection();}
        }
        return rslt;
    }

    public int[] updateBath(List<Client> listclient, Boolean closeconnection) throws Exception{
        int rslt[]= null;
        List<String> SQLstring =new LinkedList<String>();
        for(int i=0; i<listclient.size();i++){
            SQLstring.add(" UPDATE CLIENT SET " +
                    " ADDRESS='" + listclient.get(i).getAddress() +"'," +
                    " EMAILADDRESS='" +listclient.get(i).getEmailaddress() +"'," +
                    " FIRSTNAME='" + listclient.get(i).getFirstname() +"'," +
                    " LASTNAME='" + listclient.get(i).getLastname() + "'," +
                    " REGISTERDATE='" + listclient.get(i).getRegisterdateUTCString() + "'," +
                    " PHONE ='" + listclient.get(i).getPhone() + "'," +
                    " ACTIVE=" + listclient.get(i).getActive() +
                    " WHERE CLIENTID='" + listclient.get(i).getClientid()+ "'");
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
}
