package cmpsc390project;

public class entry implements Comparable<entry>{
	private String name;
	private int set;
	private int rep;
	private int weight;
	private int amount;
	
	public entry() {
		name = "";
		set = 0;
		rep = 0;
		weight = 0;
		amount = 0;
	}
	
	public entry(String a, int b, int c, int d){
		name = a;
		weight = b;
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
        
        @Override
        public int compareTo(entry o) {
           if(this.getAmount() > o.getAmount()){
               return 1;
           } else {
               if (this.getAmount() == o.getAmount()){
                   return 0;
               } else {
                   return -1;
               }
           }
        }
}
