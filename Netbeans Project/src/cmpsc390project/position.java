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
