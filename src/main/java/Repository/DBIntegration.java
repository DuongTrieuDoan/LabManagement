package Repository;

import java.sql.*;
import java.util.List;

public abstract class DBIntegration {
    protected String CONNECT_STRING = "";
    protected String DRIVER = "com.mysql.jdbc.Driver";
    protected String HOST_DABATASE = "jdbc:mysql://localhost:3306/labmanagement";
    protected String OPTION_STRING = "?serverTimezone=UTC&useSSL=false&&useJDBCCompliantTimezoneShift=true";
    protected String UID = "lab";
    protected String PWD = "lab";
    protected Connection connection;


    public DBIntegration() {
    }

    public Connection getConnection() {
        return connection;
    }

    ////////DB connecting actions
    public void SetConnection() throws Exception {
        try {
            if ((this.connection == null)||(this.connection.isClosed())) {
                this.connection = DriverManager.getConnection(
                        HOST_DABATASE + OPTION_STRING, UID, PWD);
            }else if(true){

            }else {}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CloseConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ////////// end DB connecting actions


    public ResultSet sqlQuery(String statementstring) throws SQLException {
        ResultSet rs = null;
        try {
            System.out.println(statementstring);
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(statementstring);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Boolean sqlExecution(String statementstring) throws Exception {
        Boolean rslt = false;
        try {
            System.out.println(statementstring);
            Statement stmt = connection.createStatement();
            stmt.execute(statementstring);
            rslt = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslt;
    }

    public int sqlUpdate(String statementstring) throws Exception {
        int rslt = 0;
        try {
            System.out.println(statementstring);
            Statement stmt = connection.createStatement();
            rslt=stmt.executeUpdate(statementstring);
        }
        catch (Exception e) {
            throw e;
          //  e.printStackTrace();
        }
        return rslt;
    }

    public int[] sqlUpdateBatch(List<String> statementstring) throws Exception {
        int rslt[]=null;
        try {
            System.out.println(statementstring);
            Statement stmt = connection.createStatement();
            for(int i=0;i<statementstring.size();i++){
                stmt.addBatch(statementstring.get(i));
            }
            rslt =stmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslt;
    }

    public String exceptionHandle(Exception e){
        return e.toString();
    }
    private void draft() {
/*
///selecting actions
    ///selection
    public ResultSet Selection(Object obj, String table, String condition){
        ResultSet rs=null;
        String fieldsstring="";
        String statementstring="";
        try{
            this.SetConnection();
            Statement stmt=this.getConnection().createStatement();
            Class c = Class.forName(obj.getClass().getName());
            Field f[] = c.getDeclaredFields();
            for (int i = 0; i < f.length; i++){
                fieldsstring=fieldsstring+ (f[i].toString().substring(f[i].toString().lastIndexOf(".")+1)) + ",";
                System.out.println(fieldsstring);
            }
            statementstring="SELECT " + fieldsstring.substring(0,fieldsstring.length()-1) + " FROM " + table +  condition;
            System.out.println(statementstring);
            rs=stmt.executeQuery(statementstring);
        }catch (Exception e){e.printStackTrace();}
        return rs;
    }
    //Linkedlist
    public LinkedList<Object> parsertoObject(Object obj, ResultSet rs){
        LinkedList<Object> listObject = new LinkedList<Object>();
        try {
            Class<?> clazz = Class.forName(obj.getClass().getName());
            Method method;
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
                while (rs.next()) {
                    Object tmpobj = clazz.getDeclaredConstructor().newInstance(new Object[]{});
                    for (int i = 1; i <= colCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    try {
                        System.out.println("set" + columnName.toUpperCase().substring(0, 1) + columnName.toLowerCase().substring(1));
                        method = clazz.getMethod("set" + columnName.toUpperCase().substring(0, 1) + columnName.toLowerCase().substring(1), String.class);
                        method.invoke(tmpobj, rs.getObject(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                listObject.add(tmpobj);
            }
        }catch (Exception e){e.printStackTrace();}
        return listObject;
    }
    public LinkedList<Object> selectbyidLinkedList(Object obj, String table, String id, String idvalue) {
        LinkedList<Object> listObject = new LinkedList<Object>();
        try{
            this.SetConnection();
            listObject = this.parsertoObject(obj,this.Selection(obj,table," WHERE " + id +"='"+idvalue+"'"));
        }catch (Exception e){e.printStackTrace();}finally {this.CloseConnection();
        }
        return listObject;
    }
    public LinkedList<Object> selectbykeysLinkedList(Object obj, String table, String conditions) {
        LinkedList<Object> listObject = new LinkedList<Object>();
        try{
            this.SetConnection();
            listObject = this.parsertoObject(obj,this.Selection(obj,table," WHERE " + conditions +"'"));
        }catch (Exception e){e.printStackTrace();}finally {this.CloseConnection();
        }
        return listObject;
    }
    public LinkedList<Object> selectallLinkedList(Object obj, String table) {
        LinkedList<Object> listObject = new LinkedList<Object>();
        try{
            this.SetConnection();
            listObject = this.parsertoObject(obj,this.Selection(obj,table,""));
        }catch (Exception e){e.printStackTrace();}finally {this.CloseConnection();
        }
        return listObject;
    }
    ///end Linkedlist
    ///XML////
    public Document parsertoXML(Object obj, ResultSet rs){
        Document doc=null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            Element results = doc.createElement(obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1));
            doc.appendChild(results);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (rs.next()) {
                Element row = doc.createElement("Row");
                results.appendChild(row);
                for (int i = 1; i <= colCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object value = rs.getObject(i);
                    Element node = doc.createElement(columnName);
                    node.appendChild(doc.createTextNode(value.toString()));
                    row.appendChild(node);
                }
            }
            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);

            System.out.println(sw.toString());
        }catch (Exception e){e.printStackTrace();}
        return doc;
    }
    public Document selectbyidXML(Object obj, String table, String condition) {
        Document doc = null;
        try{
            this.SetConnection();
            doc = this.parsertoXML(obj,this.Selection(obj,table,condition));

        }catch (Exception e){e.printStackTrace();}finally {this.CloseConnection();
        }
        return doc;
    }
    ///end XML
/// end selecting actions

///// other action
    public Object Update(Object e) {
        return null;
    }

    public Object Delete(Object e) {
        return null;
    }

    public Object Add(Object e) {
        return null;
    }
}

*/
    }
}