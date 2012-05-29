package org.elsys.salvation.client;

import java.util.ArrayList; 

public class DiplomaData {  
  
    private DiplomaRecord[] diplomaRecords;
    private FunctionalityManager fm;
    private String specialtie;
    private int size;
    private int count;
    
    public DiplomaData(FunctionalityManager fm, String specialtie){
    	this.fm = fm;
    	this.specialtie = specialtie;
    }
  
    public DiplomaRecord[] getDiplomaRecords() {  
        if (diplomaRecords == null) {  
            diplomaRecords = getNewRecords();  
        }  
        return diplomaRecords;  
    }  
  
    public DiplomaRecord[] getNewRecords() {
    	size=0;
    	count=0;
    	
    	count(fm.getHardDefences());
    	count(fm.getNetDefences());
    	count(fm.getSoftDefences());
    	
    	diplomaRecords = new DiplomaRecord[size];
                
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
           	
    	return diplomaRecords;  
    }
    
    public void count(ArrayList<Defence> defences){
    	for(int i = 0; i<defences.size();i++){
    		for(int k = 0; k<defences.get(i).getDiplomaWorks().size(); k++){
    			size++;
    		}
    	}
    }
    
    public void getHardDefences(){
    	 for(int i=0; i<fm.getHardDefences().size(); i++){
         	for(int k = 0; k<fm.getHardDefences().get(i).getDiplomaWorks().size(); k++){
         		DiplomaRecord record = new DiplomaRecord(fm.getHardDefences().get(i).getDiplomaWorks().get(k).getName(),
         				fm.getHardDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
         				fm.getHardDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
         				fm.getHardDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
         				"Hardware",
         				fm.getHardDefences().get(i).getDay().toString());
         		diplomaRecords[count] = record;
         		count++;
         	}
         }
    }
    
    public void getSoftDefences(){
    	for(int i=0; i<fm.getSoftDefences().size(); i++){
        	for(int k = 0; k<fm.getSoftDefences().get(i).getDiplomaWorks().size(); k++){
        		DiplomaRecord record = new DiplomaRecord(fm.getSoftDefences().get(i).getDiplomaWorks().get(k).getName(),
        				fm.getSoftDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
        				fm.getSoftDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
        				fm.getSoftDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
        				"Software: " + fm.getSoftDefences().get(i).getDiplomaWorks().get(k).getType(),
        				fm.getSoftDefences().get(i).getDay().toString());
        		diplomaRecords[count] = record;
        		count++;
        	}
        }
    }
    
    public void getNetDefences(){
    	 for(int i=0; i<fm.getNetDefences().size(); i++){
         	for(int k = 0; k<fm.getNetDefences().get(i).getDiplomaWorks().size(); k++){
         		DiplomaRecord record = new DiplomaRecord(fm.getNetDefences().get(i).getDiplomaWorks().get(k).getName(),
         				fm.getNetDefences().get(i).getDiplomaWorks().get(k).getDiplomants(),
         				fm.getNetDefences().get(i).getDiplomaWorks().get(k).getReviewer().getName(),
         				fm.getNetDefences().get(i).getDiplomaWorks().get(k).getLeader().getName(),
         				"Communications",
         				fm.getNetDefences().get(i).getDay().toString());
         		diplomaRecords[count] = record;
         		count++;
         	}
         }
    }
    
}
