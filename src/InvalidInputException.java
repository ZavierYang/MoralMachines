/**
  Customize InvalidInputException
  @author ChingYuan
 **/

public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super();
    }
    
    public InvalidInputException(String message){
        super(message);
    }
}
