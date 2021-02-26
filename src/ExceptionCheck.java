import ethicalengine.Character;
import ethicalengine.Person;
import ethicalengine.Animal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
  Check invalid input
  @author ChingYuan
 **/

public class ExceptionCheck {
    /**
      Whether the number of values in one row is less than or exceeds 10 values.
      @param number indicates the number of input datas
      @param line indicates where the data is in csv.
      @throws InvalidDataFormatException 
     **/
    public void checkDataFormat(int number, int line) throws InvalidDataFormatException{
        if(number != 1 && number != 10){
            throw new InvalidDataFormatException("WARNING: invalid data format in config file "
                                                 + "in line "+ line);
        }
    }
    
    /**
      Whether the value can not cast into integer.
      @param age indicates character's age
      @param line indicates where the data is in csv.
      @throws NumberFormatException 
     **/
    public void checkNumberFormat(String age, int line) throws NumberFormatException{
        Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(age);           
            if(!isNum.matches()){               
                throw new NumberFormatException("WARNING: invalid number format in config file "
                                                 + "in line ¡"+ line +"¿");
            }
    }
    
    /**
      Whether the input value exist in my accommodate values.
      @param type indicates what the category is.
      @param chara indicates the input data.
      @param line indicates where the data is in csv.
      @throws InvalidCharacteristicException 
     **/
    public void checkCharFormat(String type, String chara, int line) 
                                                            throws InvalidCharacteristicException{
        boolean check = false;
        
        if (type.equals("gender")) {
            for(Character.Gender gender : Character.Gender.values()){
                if(chara.equalsIgnoreCase(gender.toString())){
                    check = true;
                    break;
                }
            }
        }else if(type.equals("bodyType")){
            for(Character.BodyType bodyType : Character.BodyType.values()){
                if(chara.equalsIgnoreCase(bodyType.toString())){
                    
                    check = true;
                    break;
                }
            }
        }else if(type.equals("profession")){
            for(Person.Profession profession : Person.Profession.values()){
                if(chara.equalsIgnoreCase(profession.toString())){
                    check = true;
                    break;
                }
            }
        }else if(type.equals("species")){
            Animal animal = new Animal();
            
            for(String species: animal.getAnimals()){
                if(chara.equals(species.toString())){
                    check = true;
                    break;
                }
            }
        }else{
            check = true;
        }
        
        if(!check){
            throw new InvalidCharacteristicException("WARNING: invalid characteristic format "
                                                    + "in config file in line <"+ line +">");
        }
    }
    
    /**
      Check whether the user input the right value.
      @param input indicates the input what the user type.
      @throws InvalidInputException 
     **/
    public void checkValidInput(String input) throws InvalidInputException{
        if(!input.equals("yes") && !input.equals("no")){
            throw new InvalidInputException("Invalid response.");
        }
    }
}
