package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.Date;  

public class DiplomaData {  
  
    private DiplomaRecord[] records; 
    private FunctionalityManager FM;
    private int size;
    
    public DiplomaData(FunctionalityManager fm){
    	this.FM = fm;
    }
  
    public DiplomaRecord[] getRecords() {  
        if (records == null) {  
            records = getNewRecords();  
        }  
        return records;  
    }  
  
    public DiplomaRecord[] getNewRecords() {
    	size=0;
    	
    	count(FM.getHardDefences());
    	count(FM.getNetDefences());
    	count(FM.getSoftDefences());
    	
    	records = new DiplomaRecord[size];
        int count = 0;
        
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
        
        for(int i=0; i<FM.getNetDefences().size(); i++){
        	for(int k = 0; k<FM.getNetDefences().get(i).getDiplomaWorks().size(); k++){
        		DiplomaRecord record = new DiplomaRecord(FM.getNetDefences().get(i).getDiplomaWorks().get(k).getName(),
        				FM.getNetDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
        				FM.getNetDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
        				FM.getNetDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
        				"Networks",
        				FM.getNetDefences().get(i).getDay().toString());
        		records[count] = record;
        		count++;
        	}
        }
        
        for(int i=0; i<FM.getSoftDefences().size(); i++){
        	for(int k = 0; k<FM.getSoftDefences().get(i).getDiplomaWorks().size(); k++){
        		DiplomaRecord record = new DiplomaRecord(FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getName(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
        				FM.getSoftDefences().get(i).getDiplomaWorks().get(k).getType(),
        				FM.getSoftDefences().get(i).getDay().toString());
        		records[count] = record;
        		count++;
        	}
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
    
}
