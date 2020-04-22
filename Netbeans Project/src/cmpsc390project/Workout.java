/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpsc390project;

/**
 *
 * @author HP
 */
public class Workout {
    String title;
    String bodyClass;
    String instr;
    
    public Workout()
    {
        title = null;
        bodyClass= null;
        instr = null;
    }
    
    public Workout(String t,String b, String i)
    {
        title=t;
        bodyClass = b;
        instr = i;
    }
    
    public String getTitle()
    {
        return title;
    }
    
  //  public Image getAnim()
   // {
       // return anim;
  //  }
    
    public String getInstr()
    {
        return instr;
    }
    
    public String getBodyClass()
    {
        return bodyClass;
    }
    
    @Override
    public String toString()
    {
        return this.title;
    }
}
