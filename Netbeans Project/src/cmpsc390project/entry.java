package cmpsc390project;

public class entry {
	private String name;
	private int set;
	private int rep;
	private int weight;
	private int amount;
	
	public entry(String a, int b, int c, int d){
		name = a;
		weight = b;
		rep = c;
		set = d;
		amount = 1;
	}
        
        public int getWeight(){
		return weight;
	}
        
        public int getReps(){
		return rep;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int add) {
		amount = add;
	}
}
