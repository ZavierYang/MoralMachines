package ethicalengine;

/**
  Abstract Class from which all character types inherit.
  @author ChingYuan
**/

public abstract class Character {
    public enum Gender {FEMALE, MALE, UNKNOWN}
    public enum BodyType {AVERAGE, ATHLETIC, OVERWEIGHT, UNSPECIFIED}
    
    private int age;
    private Gender gender;
    private BodyType bodyType;
    
    /**
      Set default value.
    **/
    public Character(){
        this.age = 0;
        this.gender = Gender.UNKNOWN;
        this.bodyType = BodyType.UNSPECIFIED;
    }
    
    /**
      @param 3 arguments to set the character's information
    **/
    public Character(int age, Gender gender, BodyType bodyType){
        this.age = age;
        this.gender = gender;
        this.bodyType = bodyType;
    }
    
    /**
      Copy constructor
      @param character object to be copy
    **/
    public Character(Character character){
        this.age = character.getAge();
        this.gender = character.getGender();
        this.bodyType = character.getBodyType();
    }
    
    /**
      @return character's age
     **/
    public int getAge() {
        return this.age;
    }
    
    /**
      @return character's gender.
     **/
    public Gender getGender() {
        return this.gender;
    }

    /**
      @return character's bodyType.
     **/
    public BodyType getBodyType() {
        return this.bodyType;
    }
    
    /**
      @param age set character's age
     **/
    public void setAge(int age) {
        this.age = age;
    }

    /**
      @param gender set character's gender
     **/
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
      @param bodyType set character's bodyType
     **/
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }  
}
