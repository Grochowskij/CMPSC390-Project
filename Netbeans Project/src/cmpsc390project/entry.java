package cmpsc390project;

public class entry {
	private String name;
	private int set;
	private int rep;
	private int temp;
	private int amount;
	
	public entry(String a, int b, int c, int d){
		name = a;
		set = b;
		rep = c;
		temp = d;
		amount = temp * rep;
	}
        
        public int getWeight(){
		return set;
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
