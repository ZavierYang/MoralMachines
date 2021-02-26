package ethicalengine;

/**
  Class for store animal's information.
  @author ChingYuan
**/

public class Animal extends Character{
    String[] animals = {"cat", "dog", "rabbit", "pig", "bird", "ferret"};
    private String species;
    private boolean isPet;
    
    /**
      Set default value.
     **/
    public Animal(){
        //The default value of species is cat
        super();
        this.species = "cat";
        this.isPet = false;
    }
    
    /**
      @param species set the animal's species
    **/
    public Animal(String species){
        this.species = species;
    }
    
    /**
      @param 4 arguments to set the animal's information
    **/
    public Animal(int age, Gender gender, BodyType bodytype, String species){
        super(age, gender, bodytype);
        this.species = species;
    }
    
    /**
      Copy constructor
      @param otherAnimal object to be copy
    **/
    public Animal(Animal otherAnimal){
        this.species = otherAnimal.getSpecies();
    }
    
    /**
      @return the array fo species animal species I gave.
     **/
    public String[] getAnimals(){
        return this.animals.clone();
    }
    
    /**
      @return animal's species.
     **/ 
    public String getSpecies() {
        return this.species;
    }

    /**
      @return a boolean which indicates whether this animal is pet
     **/
    public boolean isPet() {
        return this.isPet;
    }

    /**
      @param species set animal's species.
     **/
    public void setSpecies(String species) {
        this.species = species;
    }
    
    /**
      @param isPet set whether this animal is pet
     **/
    public void setPet(boolean isPet) {
        this.isPet = isPet;
    }
    
    /**
      @return a string to print out the animal's information
     **/
    public String toString(){
        return getSpecies() + ((isPet()) ? " is pet" : "");
    }
}
