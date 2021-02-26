package ethicalengine;

/**
  Contains a list of passengers, a list of pedestrians, as well as additional scenario conditions,
  such as whether pedestrians are legally crossing at the traffic light.
  @author ChingYuan
**/

import java.util.ArrayList;
import java.util.Arrays;

public class Scenario {
    private ArrayList<Character> passengers;
    private ArrayList<Character> pedestrians;
    private boolean isLegalCrossing;
    
    /**
      @param 3 arguments to set the scenario
     **/
    public Scenario(Character[] passengers, Character[] pedestrians, boolean isLegalCrossing){
        this.passengers = new ArrayList<Character>(Arrays.asList(passengers));
        this.pedestrians = new ArrayList<Character>(Arrays.asList(pedestrians));
        this.isLegalCrossing = isLegalCrossing;
    }

    /**
     @param isLegalCrossing scenario is legal or not. 
     */
    public void setLegalCrossing(boolean isLegalCrossing) {
        this.isLegalCrossing = isLegalCrossing;
    }
    
    /**
      Get all passengers
      @return arrayList which contain all passengers
     **/
    public Character[] getPassengers() {
        return this.passengers.toArray(new Character[this.passengers.size()]);
    }

    /**
      Get all pedestrians
      @return arrayList which contain all pedestrians
     **/
    public Character[] getPedestrians() {
        return this.pedestrians.toArray(new Character[this.pedestrians.size()]);
    }

    /**
      @return boolean which indicates whether the pedestrians are legally crossing the street
     **/
    public boolean isLegalCrossing() {
        return isLegalCrossing;
    }
    
    /**
      @return boolean which indicates the user is in the car
     **/
    public boolean hasYouInCar(){
        ArrayList<Character> passengers = new ArrayList<Character>(Arrays.asList(getPassengers()));
        
        for(Character passenger : passengers){
            if(passenger.getClass().equals(Person.class)){
                if(((Person) passenger).isYou()){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
      @return boolean which indicates the user is on the lane
     **/
    public boolean hasYouInLane(){
        ArrayList<Character> pedestrians = new ArrayList<Character>(Arrays.asList(getPedestrians()));
        
        for(Character pedestrian : pedestrians){
            if(pedestrian.getClass().equals(Person.class)){
                if(((Person) pedestrian).isYou()){
                    return true;
                }
            }
        }
        
        return false;       
    }
    
    /**
      @return the number of passengers in the car
     **/
    public int getPassengerCount(){
        return getPassengers().length;
    }
    
    /**
      @return the number of pedestrians on the street 
     **/
    public int getPedestrianCount(){
        return getPedestrians().length;
    }
    
    /**
      @return the string to print the scenario
     **/
    public String toString(){
        ArrayList<Character> passengers = new ArrayList<Character>(Arrays.asList(getPassengers()));
        ArrayList<Character> pedestrians = new ArrayList<Character>(Arrays.asList(getPedestrians()));
        
        String output = "";
        output += "======================================\n"
                + "# Scenario\n"
                + "======================================\n";
        
        output += "Legal Crossing: " + ((isLegalCrossing()) ? "yes\n" : "no\n")
                + "Passengers (" + getPassengerCount() + ")\n";
        
        for(Character passenger : passengers){
            output += "- " + passenger.toString() + "\n";
        }
        
         output += "Pedestrians (" + getPedestrianCount()+ ")";
         
         for(Character pedestrian : pedestrians){
             output += "\n- " + pedestrian.toString();
         }
        return output;
    }
}