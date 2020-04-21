package cmpsc390project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class position {

    private String exercise = null;
    private int amount = 0;
    
    public position() {
    }
    
    public position(int place) throws IOException {
    	
        ArrayList<entry> entries = new ArrayList();
    	entry first = new entry("none1",0,0,0);
        first.setAmount(0);
    	entry second = new entry("none2",0,0,0);
        second.setAmount(0);
    	entry third = new entry("none3",0,0,0);
        third.setAmount(0);
        Boolean found = false;
    	
    	try {
            File data = new File("Workoutinput.txt");
            String FieldDelimiter = ",";
            BufferedReader reader = new BufferedReader(new FileReader(data));
            String line;
	
            //read file and increment each time the workout repeats
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(FieldDelimiter,-2);
                entry input = new entry(fields[0],Integer.parseInt(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]));
                for(int i = 0; i < entries.size(); ++i){
                    if(input.getName().compareTo(entries.get(i).getName())==0){
                        entries.get(i).setAmount(entries.get(i).getAmount()+1);
                        found = true;
                        break;
                    }
                }
                
                //adds input to list if not found in arraylist
                if(!found){
                    entries.add(input);
                } else {
                    found = false;
                }
                
                Collections.sort(entries, Collections.reverseOrder());
                switch(entries.size()){
                    case 0:
                        break;
                    case 1:
                        first = entries.get(0);
                        break;
                    case 2:
                        first = entries.get(0);
                        second = entries.get(1);
                        break;
                    default:
                        first = entries.get(0);
                        second = entries.get(1);
                        third = entries.get(2);
                        break;
                }
                
            }
            
            
	} catch (FileNotFoundException e) {
            System.out.println("Error, data missing...");
	}
		
        switch(place){
            case 1: 
                exercise = first.getName();
                amount = first.getAmount();
                break;
            case 2:
                exercise = second.getName();
                amount = second.getAmount();
                break;
            case 3:
                exercise = third.getName();
                amount = third.getAmount();
                break;
            default:
                break;
        }
    }
	
	public String getExcercise() {
        return exercise;
    }
	
	public int getAmount() {
        return amount;
    }
	
}
