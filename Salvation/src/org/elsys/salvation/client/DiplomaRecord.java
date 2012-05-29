package org.elsys.salvation.client;
  
import com.smartgwt.client.widgets.grid.ListGridRecord;  
  
public class DiplomaRecord extends ListGridRecord {  
  
    public DiplomaRecord() {  
    }  
  
    public DiplomaRecord(String name, String diplomants, String reviewer, String leader, String type, String date) {  
        setName(name);  
        setDiplomants(diplomants);  
        setReviewer(reviewer);  
        setLeader(leader);  
        setType(type);
        setDate(date);
    }  
    
    public void setName(String name){
    	setAttribute("name", name);
    }
    
    public String voidgetName(){
    	return getAttributeAsString("name");
    }
    
    public void setDiplomants(String diplomants){
    	setAttribute("diplomants", diplomants);
    }
    
    public String voidgetDiplomants(){
    	return getAttributeAsString("diplomants");
    }
    
    public void setReviewer(String reviewer){
    	setAttribute("reviewer", reviewer);
    }
    
    public String voidgetReviewer(){
    	return getAttributeAsString("reviewer");
    }
    
    public void setLeader(String leader){
    	setAttribute("leader", leader);
    }
    
    public String voidgetLeader(){
    	return getAttributeAsString("leader");
    }
    
    public void setType(String type){
    	setAttribute("type", type);
    }
    
    public String voidgetType(){
    	return getAttributeAsString("type");
    }
    
    public void setDate(String date){
    	setAttribute("date", date);
    }
    
    public String voidgetDate(){
    	return getAttributeAsString("date");
    }
 
}  
