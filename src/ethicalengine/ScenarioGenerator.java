package ethicalengine;

import java.util.ArrayList;
import java.util.Random;

/**
  Create a variety of scenarios.
  @author ChingYuan
**/

public class ScenarioGenerator {
    private final int UNDEFINED = 0;
    private Random rand = new Random();
    private int passengerCountMinimum = UNDEFINED, 
                passengerCountMaximum = UNDEFINED, 
                pedestrianCountMinimum = UNDEFINED, 
                pedestrianCountMaximum = UNDEFINED;
    
    /**
      Set default value.
     **/
    public ScenarioGenerator(){
        Random giveRand = new Random();
        rand.setSeed(giveRand.nextLong());
    }
    
    /**
      @param seed set the random seed.
     **/
    public ScenarioGenerator(long seed){
        rand.setSeed(seed);
    }
    
    /**
      @param 4 arguments to set the minimum and maximum for passenger and pedestrian number.
     **/
    public ScenarioGenerator(long seed, int passengerCountMinimum, int passengerCountMaximum, 
                                     int pedestrianCountMinimum, int pedestrianCountMaximum){
        rand.setSeed(seed);
        this.passengerCountMinimum = passengerCountMinimum;
        this.passengerCountMaximum = passengerCountMaximum;
        this.pedestrianCountMinimum = pedestrianCountMinimum;
        this.pedestrianCountMaximum = pedestrianCountMaximum;
    }

    /**
      @param min set the min number of passenger
     **/
    public void setPassengerCountMin(int min) {
        this.passengerCountMinimum = min;
    }

    /**
      @param max set the max number of passenger
     **/
    public void setPassengerCountMax(int max) {
        this.passengerCountMaximum = max;
    }

    /**
      @param min set the min number of pedestrian
     **/
    public void setPedestrianCountMin(int min) {
        this.pedestrianCountMinimum = min;
    }

    /**
      @param max set the max number of pedestrian
     **/
    public void setPedestrianCountMax(int max) {
        this.pedestrianCountMaximum = max;
    }

    /**
      @return the min number of passenger
     **/
    public int getPassengerCountMinimum() {
        return passengerCountMinimum;
    }
    
    /**
      @return the max number of passenger
     **/
    public int getPassengerCountMaximum() {
        return passengerCountMaximum;
    }

    /**
      @return the min number of pedestrian
     **/
    public int getPedestrianCountMinimum() {
        return pedestrianCountMinimum;
    }

    /**
      @return the max number of pedestrian
     **/
    public int getPedestrianCountMaximum() {
        return pedestrianCountMaximum;
    }
    
    /**
      @return a random generated person
     **/
    public Person getRandomPerson(){
        //Set the age between 0~99.
        int age = rand.nextInt(100);
        
        Character.Gender gender = Character.Gender.values()
                                 [rand.nextInt(Character.Gender.values().length)];
        Character.BodyType bodyType= Character.BodyType.values()
                                 [rand.nextInt(Character.BodyType.values().length)];
        
        Person.Profession profession;
        if(age >= 17 && age <= 68){
            profession = Person.Profession.values()[rand.nextInt(Person.Profession.values().length)];
        }else{
            profession = Person.Profession.NONE;
        }
        
        //We only make adult female can Pregnant because it make sence and others are not.
        boolean isPregnant;
        if(age >= 17 && age <= 68 && gender == Character.Gender.FEMALE){
            //50% this female is pregnant.
            isPregnant = rand.nextDouble() <= 0.5;
        }else{
            isPregnant = false;
        }
        
        return new Person(age, profession, gender, bodyType, isPregnant);
    }
    
    /**
      @return a random generated animal
     **/
    public Animal getRandomAnimal(){
        //The average age of the animals I gave is 20 years old.
        int age = rand.nextInt(21);
        
        Character.Gender gender = Character.Gender.values()
                                 [rand.nextInt(Character.Gender.values().length)];
        Character.BodyType bodyType= Character.BodyType.values()
                                 [rand.nextInt(Character.BodyType.values().length)];
       
        //Get the animal species from Animal class.
        Animal animal = new Animal();
        String species = animal.getAnimals()[rand.nextInt(animal.getAnimals().length)];
        
        //reuse the variable in order to reduce the number of variables.
        animal = new Animal(age, gender, bodyType, species);
        
        //50% the animal is pet.
        animal.setPet(rand.nextDouble() <= 0.5);
        return animal;
    }
    
    /**
      @return a random generated scenario
     **/
    public Scenario generate(){
        ArrayList<Character> passengers = new ArrayList<Character>();
        ArrayList<Character> pedestrians = new ArrayList<Character>();
        int passengerNum, pedestrianNum;
        boolean isLegalCrossing;
        boolean carHasYou = false, laneHasYou = false;
                
        if(this.passengerCountMaximum == UNDEFINED){
            setPassengerCountMax(5);
        }
        
        if(this.passengerCountMinimum == UNDEFINED){
            setPassengerCountMin(1);
        }
        
        if(this.pedestrianCountMaximum == UNDEFINED){
            setPedestrianCountMax(5);
        }
        
        if(this.pedestrianCountMinimum == UNDEFINED){
            setPedestrianCountMin(1);
        }
        
        passengerNum = rand.nextInt(getPassengerCountMaximum() - getPassengerCountMinimum() + 1) +
                       getPassengerCountMinimum();
        
        pedestrianNum = rand.nextInt(getPedestrianCountMaximum() - getPedestrianCountMinimum()+ 1) +
                        getPassengerCountMinimum();
        
        
        //add person or animal randomly in passengers.
        for (int i = 0; i < passengerNum; i++) {
            //The percentage of adding animal or person are 50% respectively.
            if(rand.nextDouble() <= 0.5){
                passengers.add(getRandomAnimal());
            }else{
                Person person = getRandomPerson();
                
                //Set randomly whether the user is in car.
                if(!carHasYou){
                    //30% the user is in car
                    person.setAsYou(carHasYou = rand.nextDouble() <= 0.3);
                }else{
                    person.setAsYou(false);
                }
                passengers.add(person);
            }
        }
        
        //add person or animal randomly in pedestrains.
        for (int i = 0; i < pedestrianNum; i++) {
            //The percentage of adding animal or person are 50% respectively.
            if(rand.nextDouble() <= 0.5){
                pedestrians.add(getRandomAnimal());
            }else{
                Person person = getRandomPerson();
                
                //If the user is not in car, set randomly whether the user is on lane.
                if(!carHasYou){
                    if(!laneHasYou){
                        //30% the user is on lane.
                        person.setAsYou(rand.nextDouble() <= 0.3);
                    }else{
                        person.setAsYou(false);
                    }
                    
                }
                pedestrians.add(person);
            }
        }
        
        //50% pedestrains pass street legally.
        isLegalCrossing = rand.nextDouble() <= 0.5;
        
        
        return new Scenario(passengers.toArray(new Character[passengers.size()]), 
                            pedestrians.toArray(new Character[pedestrians.size()]), 
                            isLegalCrossing);
    }
}