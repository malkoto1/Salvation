package org.elsys.salvation.client;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PersonRecord extends ListGridRecord{
	public PersonRecord() {  
    }  
  
    public PersonRecord(String name, String date) {  
        setName(name);
        setDate(date);
    }  
    
    public void setName(String name){
    	setAttribute("name", name);
    }
    
    public String voidgetName(){
    	return getAttributeAsString("name");
    }
    
    public void setDate(String date){
    	setAttribute("date", date);
    }
    
    public String voidgetDate(){
    	return getAttributeAsString("date");
    }
}
