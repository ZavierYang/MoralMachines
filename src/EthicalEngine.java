/*********************************************************************
 *  Author:       ChingYuan Yang                                    *
 *  StudentID:    1070917                                            *
 *  Username:     chingyuany                                         *
 *                                                                   *
 *  Description:  This algorithm is created for Trolley Dilemma      *
 *                                                                   *
 *  Written:      01/06/2019                                         *
 *  Update:       25/06/2019                                         *
 *                                                                   *
 ********************************************************************/

import ethicalengine.Animal;
import ethicalengine.Character;
import ethicalengine.Person;
import ethicalengine.Scenario;
import ethicalengine.ScenarioGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class EthicalEngine {
    enum Decision {PEDESTRIANS, PASSENGERS}
    
    /*The variable indicates whether the user wants to store the result. Put this variable in 
      private static is because the run(Scanner) method needs this variable.
    */
    private static boolean fileSave = false;
    public static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args){
        /*
        variables:
            ethicalEngine: The instance of EthicalEngine in order to avoid to much static methods.
            exceptionCheck: The instance of ExceptionCheck in order to check valid value.
            scenarioGenerator: The instance of ScenarioGenerator in order to generate scenarios 
                               for audit.
            audit: The instance of Audit in order to run scenarios.
            resultPath: The path where result to store.
            scenarios: The arraylist to store all scenarios for Audit.
            interaction: Whether the mode is interaction.
        */
        
        EthicalEngine ethicalEngine = new EthicalEngine();
        ExceptionCheck exceptionCheck = new ExceptionCheck();
        ScenarioGenerator scenarioGenerator = new ScenarioGenerator();
        Audit audit = null;
        String resultPath = "";
        ArrayList<Scenario> scenarios = null;
        boolean interaction = false;
        String help = "EthicalEngine - COMP90041 - Final Project\n\n"+
                      "Usage: java EthicalEngine [arguments]\n\n" +
                      "Arguments:\n" + 
                      "   -c or --config      Optional: path to config file\n" + 
                      "   -h or --help        Optional: path to config file\n" + 
                      "   -r or --results     Optional: path to config file\n" + 
                      "   -i or --interactive Optional: path to config file\n";
        
        //Check flag type.
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-h") || args[i].equals("--help")){
                System.out.print(help);
                System.exit(0);
            }else if((args[i].equals("-c") || args[i].equals("--config")) && scenarios == null){
                if(i + 1 == args.length){
                    System.out.print(help);
                    System.exit(0);
                }else{
                    scenarios = ethicalEngine.runByCSV(args[i+1]);
                }
            }else if(args[i].equals("-r") || args[i].equals("--results")){
                resultPath = args[i + 1];
            }else if(args[i].equals("-i") || args[i].equals("--interactive")){
                //Set the interaction mode to true.
                interaction = true;
                
                //if -i comes firts we need to load data to check valid data first.
                for(int j = i + 1; j < args.length; j++){
                    if((args[j].equals("-c") || args[j].equals("--config")) && scenarios == null){
                        if(j + 1 == args.length){
                            System.out.print(help);
                            System.exit(0);
                        }else{
                            scenarios = ethicalEngine.runByCSV(args[j+1]);
                        }
                    }
                }
                
                try {
                    //If the mode is interactive, print welcome message.
                    File welcome = new File("welcome.ascii");
                    InputStreamReader input = new InputStreamReader (new FileInputStream(welcome));
                    BufferedReader reader = new BufferedReader(input);
                    String line;

                    while((line=reader.readLine())!=null){
                         System.out.println(line);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
        //Whether the interaction mode is activated.
        if(interaction){
            //validInput: Check the input value is valid or not. 
            boolean validInput = false;
            String input;
                
            while(!validInput){
                try {
                    System.out.println("Do you consent to have your decisions " + 
                                      "saved to a file? (yes/no)"); 
                    input = keyboard.next();
                    
                    //Check whether the user input the right value. i.e. yes or no.
                    exceptionCheck.checkValidInput(input);
                    
                    /*If the exceptionCheckis pass, then set validInput to true in order 
                      to go through while loop. 
                    */
                    validInput = true;
                    
                    if(input.equals("yes")){
                        fileSave = true;
                    }else{
                        fileSave = false;
                    }
                }catch(Exception e) {
                    System.out.print(e.getMessage());
                }
            }
            ethicalEngine.scenarioMode(audit, scenarios, interaction, scenarioGenerator,resultPath);
            
        }else{
            ethicalEngine.scenarioMode(audit, scenarios, interaction, scenarioGenerator,resultPath);
        }
    }
    
    /**
      Decide to save passengers or pedestrians
      @param scenario the input to be judged
      @return Decision indicates who are be saved
     **/
    public static Decision decide(Scenario scenario){
        /*Create two save point and add the value by one in each situation.
          If the user is at one side, the side will increase more value.
          Which value is bigger, they will be saved.
          If two value are same, then choose randomly.
        */
        
        int savePassengerPoint = 0, savePedestriansPoint = 0;
        ArrayList<Character> passengers = new ArrayList<Character>
                                              (Arrays.asList(scenario.getPassengers()));
        
        ArrayList<Character> pedestrians = new ArrayList<Character> 
                                               (Arrays.asList(scenario.getPedestrians()));
        
        //If pedestrians pass street legally, then the pedestrians save point is increased.
        if(scenario.isLegalCrossing()){
            savePedestriansPoint += 1;
        }
        
        //Increase the save point which count is bigger. If equal, increase both.
        if(scenario.getPassengerCount() == scenario.getPedestrianCount()){
            savePassengerPoint += 1;
            savePedestriansPoint += 1;
        }else if(scenario.getPassengerCount() >= scenario.getPedestrianCount()){
            savePassengerPoint += 1;
        }else{
            savePedestriansPoint += 1;
        }
        
        //Add savepoint by characters.
        savePassengerPoint = addSavePoint(passengers, savePassengerPoint);
        savePedestriansPoint = addSavePoint(pedestrians, savePedestriansPoint);
        
        //Increase the save point considerablly where the user is.
        if(scenario.hasYouInCar()){
            savePassengerPoint += 5;
        }else if(scenario.hasYouInLane()){
            savePedestriansPoint += 5;
        }
        
        /*Which save point is bigger, then they are saved. 
          If two value are same, then choose randomly.
        */
        if(savePassengerPoint > savePedestriansPoint){
            return Decision.PASSENGERS;
        }else if(savePassengerPoint < savePedestriansPoint){
            return Decision.PEDESTRIANS;
        }else{
            return Decision.values()[(int)(Math.random() * Decision.values().length)];
        }
    }
    
    /**
      Add characters' save point
      @param characters indicates who's save point be add
      @param savePoint indicates the total save point
      @return the save point which is be add.
     **/
    public static int addSavePoint(ArrayList<Character> characters, int savePoint){
        for(Character passenger : characters){
            //Increase the passenger save point per person.
            if(passenger instanceof Person){
                savePoint += 1;
                
                //If the person is pregnant, increase the passenger save point.
                if(((Person)passenger).isPregnant()){
                    savePoint += 1;
                }
                
                //If the person is child or adult, increase the passenger save point.
                if(((Person)passenger).getAgeCategory() == Person.AgeCategory.CHILD ||
                   ((Person)passenger).getAgeCategory() == Person.AgeCategory.ADULT){
                    savePoint += 1;
                }
            }
        }
        
        return savePoint;
    }
    
    /**
      Load csv to get the prepared scenario
      @param file the path of the file
      @return Scenario ArrayList from csv
     **/
    public ArrayList<Scenario> runByCSV(String file){
        ExceptionCheck exceptionCheck = new ExceptionCheck();
        String line;
        int csvLine = 1, scenarioNum = 0;
        String[] categories;
        
        //scenario's requirement variables.
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        ArrayList<Character> passengers = null;
        ArrayList<Character> pedestrians = null;
        Person person;
        Animal animal;
        boolean islegal = false;
        
        try{
            InputStreamReader input = new InputStreamReader (new FileInputStream(file));
            BufferedReader reader = new BufferedReader(input);
            
            //Read the first row first because the first row is not data.
            line = reader.readLine();
            categories = line.split(",");
            
            while((line=reader.readLine())!=null){
                csvLine++;
                String data[] = line.split(",");
                
                try{
                    exceptionCheck.checkDataFormat(data.length, csvLine);
                    
                    //data's length = 1 means this line's data is new scenario.
                    if(data.length == 1){
                        scenarioNum++;
                        
                        //Don't add scenario at first loop because there is no data yet.
                        if(scenarioNum != 1){
                            scenarios.add
                            (new Scenario(
                                    passengers.toArray(new Character[passengers.size()]), 
                                    pedestrians.toArray(new Character[pedestrians.size()]), 
                                    islegal));
                        }

                        passengers = new ArrayList<Character>();
                        pedestrians = new ArrayList<Character>();

                        //split data to get whether the scenario is legal.
                        String[] traffic = data[0].split(":");
                        if(traffic[1].equals("green")){
                            islegal = true;
                        }else{
                            islegal = false;
                        }
                    }else{
                        if(data[0].equals("person")){
                            person = new Person();

                            for (int i = 1; i < data.length - 1; i++) {
                                if(!data[i].equals("")){
                                    addCharacter(exceptionCheck, person, categories[i], 
                                                                       data[i], csvLine);
                                }
                            }
                            
                            if (data[9].equals("passenger")) {
                                passengers.add(person);
                            }else{
                                pedestrians.add(person);
                            }
                        }else{
                            animal = new Animal();

                            for (int i = 1; i < data.length - 1; i++) {
                                if(!data[i].equals("")){
                                    addCharacter(exceptionCheck, animal, categories[i], 
                                                                        data[i], csvLine);
                                }
                            }

                            if (data[9].equals("passenger")) {
                                passengers.add(animal);
                            }else{
                                pedestrians.add(animal);
                            }
                        }
                    }
                }catch(InvalidDataFormatException exception) {
                    System.out.println(exception.getMessage());
                    continue;
                }
            }
            //Add last scenario.
            scenarios.add(new Scenario(passengers.toArray(new Character[passengers.size()]), 
                                       pedestrians.toArray(new Character[pedestrians.size()]), 
                                       islegal));
            
        }catch(FileNotFoundException exception) {
            System.out.println("ERROR: could not find config file.");
            System.exit(0);
        }catch(Exception exception){
        }
        
        return scenarios;
    }
    
    /**
      Set character's information
      @param exceptionCheck to check whether the data is valid
      @param character the character to be generate
      @param categories the characteristics of the character
      @param data the input information of the characteristics
      @param csvLine where is the data in csv
     **/
    public void addCharacter(ExceptionCheck exceptionCheck, Character character,
                           String categories, String data, int csvLine){
        try {
            if(categories.equals("age")){
                //Check whether the data is valid datatype.
                exceptionCheck.checkNumberFormat(data, csvLine);
                character.setAge(Integer.parseInt(data));
            }else{
                //Check whether the data is valid data.
                exceptionCheck.checkCharFormat(categories, data, csvLine);
                
                if(categories.equals("gender")){
                    character.setGender(Character.Gender.valueOf(data.toUpperCase()));
                    
                }else if(categories.equals("bodyType")){
                    character.setBodyType(Character.BodyType.valueOf(data.toUpperCase()));
                    
                }else if(categories.equals("profession")){
                    ((Person)character).setProfession(Person.Profession.valueOf(data.toUpperCase()));
                    
                }else if(categories.equals("pregnant")){
                    if(Boolean.parseBoolean(data)){
                        ((Person)character).setPregnant(true);
                    }
                }else if(categories.equals("isYou")){
                    if(Boolean.parseBoolean(data)){
                        ((Person)character).setAsYou(true);
                    }
                }else if(categories.equals("species")){
                    ((Animal)character).setSpecies(data);
                    
                }else if(categories.equals("isPet")){
                    if(Boolean.parseBoolean(data)){
                        ((Animal)character).setPet(true);
                    }
                }
            }
        }catch(NumberFormatException exception) {
            System.out.println(exception.getMessage());
            
        }catch(InvalidCharacteristicException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    /**
      Execute different method of run metohd according to whether the csv has provided.
      @param audit to run the scenarios.
      @param scenarios an arraylist contains several scenarios
      @param interaction indicates whether the mode is interactived.
      @param scenarioGenerator to generate scenarios.
      @param file where the csv is
      @param resultPath where to store result
     */
    public void scenarioMode(Audit audit, ArrayList<Scenario> scenarios, boolean interaction, 
                              ScenarioGenerator scenarioGenerator, String resultPath){
        if(scenarios != null){
            audit = new Audit(scenarios);
            
            if(!resultPath.equals("")){
                audit.setFilePath(resultPath);
            }
            
            if(interaction){
                audit.setAuditType("User"); 
            }      
            
            audit.run();
        }else{
            //If no csv, generate random number of scenarios.
            Random rand = new Random();
            int randomScenarios = rand.nextInt(10) + 1;

            audit = new Audit();
            
            if(!resultPath.equals("")){
                audit.setFilePath(resultPath);
            }
            
            if(interaction){
                audit.setAuditType("User"); 
            }
            
            audit.run(randomScenarios);
            
        }
    }
    
    /**
      @return whether to save the result.
     **/
    public static boolean getFileSave(){
        return fileSave;
    }
}