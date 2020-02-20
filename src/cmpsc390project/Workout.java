package cmpsc390project;

public class Workout 
{
    private double weight;
    private int reps;
    private String workoutName;
    
    public Workout()
    {
        this.weight=0;
        this.reps=0;
        this.workoutName="";
    }
    public Workout(String wn, double w, int r)
    {
        this.weight=w;
        this.reps=r;
        this.workoutName=wn;
    }
    
    public void setWeight(double w)
    {
        this.weight = w;
    }
    public double getWeight()
    {
        return this.weight;
    }
    
    public void setReps(int r)
    {
        this.reps=r;
    }
    public int getReps()
    {
        return this.reps;
    }
    
    public void setWorkoutName(String wn)
    {
        this.workoutName = wn;
    }
    public String getWorkoutName()
    {
        return this.workoutName;
    }
    
    @Override
    public String toString()
    {
        return "Workout Name: " + this.workoutName + " Weight: " + this.weight + " Reps: " + this.reps;
    }
    
    public String fileFormat()
    {
        return this.workoutName + " " + this.weight + " " + this.reps;
    }
}
