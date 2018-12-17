package domain;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClassRoom {
    private String roomid;
    private String roomnumber;
    private String address;
    private int numberofseats;
    private List<Date> unavailabledatelist; // ("YYMMDD:YYMMDD:YYMMDD:YYMMDD:")in database
    private Boolean active;

    public ClassRoom() {
    }
    public ClassRoom(String roomid, String roomnumber, String address, int numberofseats, List<Date> unavailabledatelist, Boolean active) {
        this.roomid = roomid;
        this.roomnumber = roomnumber;
        this.address = address;
        this.numberofseats = numberofseats;
        this.unavailabledatelist = unavailabledatelist;
        if(!this.unavailabledatelist.isEmpty()){Collections.sort(this.unavailabledatelist);}
        this.active=active;
    }
    public ClassRoom(String roomid, String roomnumber, String address, int numberofseats, String unavailabledatelist, Boolean active) {
        this.roomid = roomid;
        this.roomnumber = roomnumber;
        this.address = address;
        this.numberofseats = numberofseats;
        if(unavailabledatelist!=""&&!unavailabledatelist.isEmpty()){
        setUnavailabledatelist(unavailabledatelist,"yyMMdd");}
        this.active=active;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(int numberofseats) {
        this.numberofseats = numberofseats;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Date> getUnavailabledatelist() {
        return unavailabledatelist;
    }

    public void setUnavailabledatelist(List<Date> unavailabledatelist) {
        this.unavailabledatelist = unavailabledatelist;
        if(!this.unavailabledatelist.isEmpty()){Collections.sort(this.unavailabledatelist);}
    }
    public void setUnavailabledatelist(String unavailabledatelist, String patten) {
        Date unavailabledate;
        List<Date> dateList=new LinkedList<Date>();
        try{
            Integer index=0;
            while (unavailabledatelist.length()>0){
                index = unavailabledatelist.indexOf(":");
                SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
                unavailabledate= dateFormat.parse(unavailabledatelist.substring(0,index));
                dateList.add(unavailabledate);
                unavailabledatelist=unavailabledatelist.substring(index+1);
            }
        }catch (Exception e){e.printStackTrace();}
        this.unavailabledatelist = dateList;
        if(!this.unavailabledatelist.isEmpty()){Collections.sort(this.unavailabledatelist);}
    }

    public String getUnavailabledatelistString(String patten) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
        String myString="";
        try{
           for(int i=0;i<this.unavailabledatelist.size();i++){
               myString= myString + dateFormat.format(this.unavailabledatelist.get(i)) +":";
           }
        }catch (Exception e){e.printStackTrace();}

        return myString;
    }

    public void addUnavailabledatelist(String unavailabledate, String patten){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
            Date mydate=dateFormat.parse(unavailabledate);
            this.unavailabledatelist.add(mydate);
        }catch (Exception e){e.printStackTrace();}
    }
    public void addUnavailabledatelist(Date unavailabledate){
        this.unavailabledatelist.add(unavailabledate);
    }

    public void show(){
        System.out.println(this.roomid);
        System.out.println(this.roomnumber);
        System.out.println(this.address);
        System.out.println(this.numberofseats);
        System.out.println(this.getUnavailabledatelistString("yyMMdd"));
        for(int i=0;i<this.unavailabledatelist.size();i++){
            System.out.println(this.unavailabledatelist.get(i).toString());
        }
    }

}
