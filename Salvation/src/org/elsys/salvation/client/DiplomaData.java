package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;  

public class DiplomaData {  
  
    private DiplomaRecord[] records; 
    private FunctionalityManager FM;
    private String specialtie;
    private int size;
    private int count;
    
    public DiplomaData(FunctionalityManager fm, String specialtie){
    	this.FM = fm;
    	this.specialtie = specialtie;
    }
  
    public DiplomaRecord[] getRecords() {  
        if (records == null) {  
            records = getNewRecords();  
        }  
        return records;  
    }  
  
    public DiplomaRecord[] getNewRecords() {
    	size=0;
    	count=0;
    	
    	count(FM.getHardDefences());
    	count(FM.getNetDefences());
    	count(FM.getSoftDefences());
    	
    	records = new DiplomaRecord[size];
        
        
        
        if(specialtie.equals("Hardware")){
        	getHardDefences();
        }else if(specialtie.equals("Software")){
        	getSoftDefences();
        }else if(specialtie.equals("Communications")){
        	getNetDefences();
        }else{
        	getHardDefences();
        	getSoftDefences();
        	getNetDefences();
        }
           	
    	return records;  
    }
    
    public void count(ArrayList<Defence> defences){
    	for(int i = 0; i<defences.size();i++){
    		for(int k = 0; k<defences.get(i).getDiplomaWorks().size(); k++){
    			size++;
    		}
    	}
    }
    
    public void getHardDefences(){
    	 for(int i=0; i<FM.getHardDefences().size(); i++){
         	for(int k = 0; k<FM.getHardDefences().get(i).getDiplomaWorks().size(); k++){
         		DiplomaRecord record = new DiplomaRecord(FM.getHardDefences().get(i).getDiplomaWorks().get(k).getName(),
         				FM.getHardDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
         				FM.getHardDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
         				FM.getHardDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
         				"Hardware",
         				FM.getHardDefences().get(i).getDay().toString());
         		records[count] = record;
         		count++;
         	}
         }
    }
    
    public void getSoftDefences(){
    	for(int i=0; i<FM.getSoftDefences().size(); i++){
        	for(int k = 0; k<FM.getSoftDefences().get(i).getDiplomaWorks().size(); k++){
        		DiplomaRecord record = new DiplomaRecord(FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getName(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
        				"Software: " + FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getType(),
        				FM.getSoftDefences().get(i).getDay().toString());
        		records[count] = record;
        		count++;
        	}
        }
    }
    
    public void getNetDefences(){
    	 for(int i=0; i<FM.getNetDefences().size(); i++){
         	for(int k = 0; k<FM.getNetDefences().get(i).getDiplomaWorks().size(); k++){
         		DiplomaRecord record = new DiplomaRecord(FM.getNetDefences().get(i).getDiplomaWorks().get(k).getName(),
         				FM.getNetDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
         				FM.getNetDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
         				FM.getNetDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
         				"Communications",
         				FM.getNetDefences().get(i).getDay().toString());
         		records[count] = record;
         		count++;
         	}
         }
    }
    
}
