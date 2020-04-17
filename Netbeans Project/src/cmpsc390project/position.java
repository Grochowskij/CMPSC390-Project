package cmpsc390project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class position {

    private String excercise = null;
    private int amount = 0;
    
    public position() {
    }
    
    public position(int place) {
    	
    	entry first = new entry("none1",0,0,0);
    	entry second = new entry("none2",0,0,0);
    	entry third = new entry("none3",0,0,0);
    	
    	
		
        if (place == 1)
        {excercise = first.getName();
        amount = first.getAmount();}
        else if (place == 2)
        {excercise = second.getName();
        amount = second.getAmount();}
        else if (place == 3)
        {excercise = third.getName();
        amount = third.getAmount();}
    }
	
	public String getExcercise() {
        return excercise;
    }
	
	public int getAmount() {
        return amount;
    }
	
}
