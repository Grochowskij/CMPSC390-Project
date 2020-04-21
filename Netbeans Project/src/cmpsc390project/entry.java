package cmpsc390project;

public class entry implements Comparable<entry>{
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
		amount = 1;
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
