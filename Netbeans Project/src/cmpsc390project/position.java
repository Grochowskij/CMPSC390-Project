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
    	
    	try {
    		File data = new File("Workoutinput.txt");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			Scanner scan = new Scanner(reader);
			
			while (scan.hasNext())
			{entry temp = new entry(scan.next(),scan.nextInt(),scan.nextInt(),scan.nextInt());
			
			if (temp.getName().contentEquals(first.getName()))
			{first.setAmount(first.getAmount() + temp.getAmount());}
			else if (temp.getName().contentEquals(second.getName()))
			{second.setAmount(second.getAmount() + temp.getAmount());
			if (second.getAmount() > first.getAmount())
			{temp = first;
			first = second;
			second = temp;}}
			else if (temp.getName().contentEquals(third.getName()))
			{third.setAmount(third.getAmount() + temp.getAmount());
			if (third.getAmount() > first.getAmount())
			{temp = first;
			first = third;
			third = second;
			second = temp;}
			else if (third.getAmount() > second.getAmount())
			{temp = second;
			second = third;
			third = temp;}}
			else if (temp.getAmount() > first.getAmount())
			{third = second;
			second = first;
			first = temp;}
			else if (temp.getAmount() > second.getAmount())
			{third = second;
			second = temp;}
			else if (temp.getAmount() > third.getAmount())
			{third = temp;}
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Error, data missing...");
		}
		
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
