package cmpsc390project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class cell {
	private String name = null;
    private int amount = 0;

    public cell() {
    }

    public cell(String name) throws NumberFormatException, IOException {
        this.name = name;
        
        int x = 0;
        File data = new File("Workoutinput.txt");
        String FieldDelimiter = ",";
        BufferedReader reader = new BufferedReader(new FileReader(data));
        String line;
        while ((line = reader.readLine()) != null){
            String[] fields = line.split(FieldDelimiter,-2);
            if (fields[0].equals(name))
            {x = x + (Integer.parseInt(fields[2])*Integer.parseInt(fields[3]));}}
        
        amount = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
