package ethicalengine;

/**
  Class for store person's information.
  @author ChingYuan
**/

public class Person extends Character{
    public enum Profession {DOCTOR, CEO, TEACHER, ENGINEER, CRIMINAL, STUDENT, PROFESSOR,
                          HOMELESS, UNEMPLOYED, UNKNOWN, NONE}
    public enum AgeCategory {BABY, CHILD, ADULT, SENIOR}
    
    private Profession profession;
    private boolean isPregnant, isYou;
    
    /**
    Set default value.
    **/
    public Person() {
        super();
        this.profession = Profession.UNKNOWN;
        this.isPregnant = false;
    }
    
    /**
      @param 4 arguments to set the person's information
    **/
    public Person(int age, Profession profession, Gender gender, BodyType bodytype, boolean isPregnant){
        super(age, gender, bodytype);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }
    
    /**
      @param 3 arguments to set the person's information 
     **/
    public Person(int age, Gender gender, BodyType bodytype){
        super(age, gender, bodytype);
    }
    
    /**
      Copy constructor
      @param person object to be copy
    **/
    public Person(Person person){
        super(person);
        this.profession = person.getProfession();
        this.isPregnant = person.isPregnant();
        
    }
    
    /**
      Return an enumeration value of the type Profession / Only ADULT has profession , age categories should return NONE.
      @return a value in Profession
    **/
    public Profession getProfession(){
        if (getAgeCategory().equals(AgeCategory.ADULT)) {
            return this.profession;
        }else{
            //If the person is not ADULT, set to NONE to ensure the value is right.
            setProfession(Profession.NONE);
            return Profession.NONE;
        }
    }
    
    /**
      Person whose gender is not FEMALE this should return false.
      @return a boolean value whcih indicates whether the person is pregnant. 
    **/
    public boolean isPregnant() {
        if(getGender().equals(Gender.FEMALE)){
            return this.isPregnant;
        }else{
            //If the person is not FEMALE, set to false to ensure the value is right.
            setPregnant(false);
            return false;
        }
    }
    
    /**
      Return an enumeration value depending on the person's age.
      @return a value in AgeCategory
    **/
    public AgeCategory getAgeCategory(){
        int age = getAge();
        
        if(age >= 0 && age <= 4){
            return AgeCategory.BABY;
        }else if(age >= 5 && age <= 16){
            return AgeCategory.CHILD;
        }else if(age >= 17 && age <= 68){
            return AgeCategory.ADULT;
        }else{
            return AgeCategory.SENIOR;
        }
    }
    
    /**
      @return boolean value whcih indicates whether the person is the user.
    **/
    public boolean isYou(){
        return this.isYou;
    }
    
    /**
      @param profession set a person's profession
     **/
    public void setProfession(Profession profession) {
        if (getAgeCategory().equals(AgeCategory.ADULT)) {
            this.profession = profession;
        }else{
            //If the person is not FEMALE, set to false to ensure the value is right.
            this.profession = Profession.NONE;
        }
    }
    
    /**
      Set the value of isPregnant. If person whose gender is not FEMALE this should set false.
      @param pregnant set whether the person is pregnant.
     **/
    public void setPregnant(boolean pregnant){
        if(getGender().equals(Gender.FEMALE)){
            this.isPregnant = pregnant;
        }else{
            this.isPregnant = false;
        }
    }
    
    /**
      @param isYou set whether the person is the user.
     **/
    public void setAsYou(boolean isYou){
        this.isYou = isYou;
    }
    
    /**
      @return a string to print out the person's information
     **/
    public String toString(){
        String data = "";
        
        data = ((isYou()) ? "you " : "" ) + getBodyType().toString().toLowerCase() + " " + 
               getAgeCategory().toString().toLowerCase() + " " +
               ((getAgeCategory().equals(AgeCategory.ADULT)) ? 
                                              getProfession().toString().toLowerCase() + " " : "") + 
                getGender().toString().toLowerCase();
        
        if (getGender().equals(Gender.FEMALE)) {
            data += (isPregnant()) ? " pregnant" : "";
        }
        
        return data;
    }
}
