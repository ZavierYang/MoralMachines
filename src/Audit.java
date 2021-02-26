import ethicalengine.ScenarioGenerator;
import ethicalengine.Scenario;
import ethicalengine.Character;
import ethicalengine.Person;
import ethicalengine.Animal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
  This class is to run various scenarios
  @author ChingYuan
 **/

public class Audit {
    private int run, totalRun, totalHuman, totalAge, 
                redTotal, redSurvival, greenTotal,greenSurvival, usercount;
    private String name;
    private ArrayList<Survival> statistics;
    private ArrayList<Survival> appearance;
    private ArrayList<Scenario> scenarios;
    private final String FILENAME = "results.log", USERFILENAME = "user.log"; 
    private String filePath;
    
    /**
      Set default values.
     **/
    public Audit(){
        this.run = 0;
        this.totalRun = 0;
        this.totalHuman = 0;
        this.totalAge = 0;
        this.redTotal = 0;
        this.redSurvival = 0;
        this.greenTotal = 0;
        this.greenSurvival = 0;
        this.usercount = 0;
        this.name = "Unspecified";
        this.statistics = new ArrayList<Survival>();
        this.appearance = new ArrayList<Survival>();
        this.scenarios = new ArrayList<Scenario>();
        this.filePath = ".\\";
    }
    
    /**
      @param scenarios set the scenarios to be run
     **/
    public Audit(ArrayList<Scenario> scenarios){
        this();
        this.scenarios = scenarios;
    }
    
    /**
     @param name the type of audit
     */
    public void setAuditType(String name){
        this.name = name;
    }
    
    /**
      @param run The number of the scenarios need to be run.
     **/
    public void setRun(int run) {
        this.run = run;
    }
    
    /**
     @param totalRun The number of the scenarios has run.
     **/
    public void setTotalRun(int totalRun) {
        this.totalRun = totalRun;
    }

    /**
     @param totalHuman how many human in all scenarios.
     **/
    public void setTotalHuman(int totalHuman) {
        this.totalHuman = totalHuman;
    }
    
    /**
     @param totalAge the number of human's age in all scenarios.
     **/
    public void setTotalAge(int totalAge) {
        this.totalAge = totalAge;
    }
    
    /**
      @param usercount To calculate how many scenarios do the user run 
     **/
    public void setUsercount(int usercount) {
        this.usercount = usercount;
    }

    /**
     @param filePath where to save the result file
     **/
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     @param redTotal How many passengers and pedestrians in red scenario.
     **/
    public void setRedTotal(int redTotal) {
        this.redTotal = redTotal;
    }

    /**
     @param redSurvival How many people and animals survive in in red scenario.
     **/
    public void setRedSurvival(int redSurvival) {
        this.redSurvival = redSurvival;
    }

    /**
     @param greenTotal How many people and animals survive in in green scenario.
     **/
    public void setGreenTotal(int greenTotal) {
        this.greenTotal = greenTotal;
    }

    /**
     @param greenSurvival How many people and animals survive in in green scenario.
     **/
    public void setGreenSurvival(int greenSurvival) {
        this.greenSurvival = greenSurvival;
    }
    
    /**
      @return get the Audit Type
     **/
    public String getAuditType() {
        return this.name;
    }

    /**
      @return get the number that the scenarios need to be run
     **/
    public int getRun() {
        return this.run;
    }

    /**
      @return get the number that the scenarios has run
     **/
    public int getTotalRun() {
        return this.totalRun;
    }

    /**
      @return get the number of total human in all scenarios.
     **/
    public int getTotalHuman() {
        return this.totalHuman;
    }

    
    /**
      @return get how many scenarios do the user run
     **/
    public int getUsercount() {
        return usercount;
    }

    /**
      @return get Survival ArrayList which contain which characteristics survive.
     **/
    public ArrayList<Survival> getStatistics() {
        return this.statistics;
    }

    /**
      @return get Survival ArrayList which contain which characteristics appear.
     **/
    public ArrayList<Survival> getAppearance() {
        return this.appearance;
    }

    /**
      @return get total human's ages
     **/
    public int getTotalAge() {
        return this.totalAge;
    }

    /**
      @return get Scenario ArrayList which need to be run
     **/
    public ArrayList<Scenario> getScenarios() {
        return this.scenarios;
    }
    
    /**
      @return get the path where to store result
     **/
    public String getFilePath() {
        return this.filePath;
    }

    /**
      @return get the total number in red scenarios.
     **/
    public int getRedTotal() {
        return redTotal;
    }

    /**
      @return get the survival number in red scenarios.
     **/
    public int getRedSurvival() {
        return redSurvival;
    }

    /**
      @return get the total number in green scenarios.
     **/
    public int getGreenTotal() {
        return greenTotal;
    }

    /**
      @return get the survival number in green scenarios.
     **/
    public int getGreenSurvival() {
        return greenSurvival;
    }
    
    /**
      Run for the scenarios specified.
     **/
    public void run(){
        for(Scenario scenario : getScenarios()){
            System.out.println(scenario.toString());
            setTotalRun(getTotalRun() + 1);
            setUsercount(getUsercount() + 1);
            
            //Add each characteristic appearance
            storeCharacter(scenario.getPedestrians(), getAppearance());
            storeCharacter(scenario.getPassengers(), getAppearance());
            
            //Different mode have different execution.
            mode(scenario);
        }
        
        //This code only run for the scenarios are run automatically  
        if(!EthicalEngine.getFileSave() && !getAuditType().equals("User")){
            printStatistic();
            printToFile(getFilePath(), FILENAME);  
        }
    }
    
    /**
      Run for the random scenarios.
      @param run the number of scenarios need to be run
     **/
    public void run(int run){
        setRun(run);
        setTotalRun(getTotalRun() + getRun());
        ScenarioGenerator scenarioGenerator = new ScenarioGenerator();
        Scenario scenario;
        
        for (int i = 0; i < getRun(); i++) {
            setUsercount(getUsercount() + 1);
            scenario = scenarioGenerator.generate();
            System.out.println(scenario.toString());
            
            storeCharacter(scenario.getPedestrians(), getAppearance());
            storeCharacter(scenario.getPassengers(), getAppearance());
            
            //Different mode have different execution.
            mode(scenario);
        }
        
        //This code only run for the scenarios are run automatically  
        if(!EthicalEngine.getFileSave() && !getAuditType().equals("User")){
            printStatistic();
            printToFile(getFilePath(), FILENAME);  
        }
    }
    
    /**
     * This method change execution method according to whether the interactive mode opened.
     * @param scenario The scenario which currently be run
     */
    public void mode(Scenario scenario){
        //If Audit Type is User, System need to interact with the user.
        if(getAuditType().equals("User")){
            String input;
            System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
            input = EthicalEngine.keyboard.next();

            if(input.equals("passenger") || input.equals("passenger") || input.equals("1")){
                //Add Passengers statistics.
                storeCharacter(scenario.getPassengers(), getStatistics());
                
                //Add the value of legal statistics.
                storeScenarioStastistic(scenario, "PASSENGER");
                
                //Store total age and the number of person.
                storeAge(scenario.getPassengers());
                
            }else if(input.equals("pedestrian") || input.equals("pedestrians")  
                                                || input.equals("2")){
                //Add Pedestrians statistics.
                storeCharacter(scenario.getPedestrians(), getStatistics());

                //Add the value of legal statistics.
                storeScenarioStastistic(scenario, "PEDESTRIANS");
                
                //Store total age and the number of person.
                storeAge(scenario.getPedestrians());
            }
            
            //Check whether scenarios is finished first.
            if(getUsercount() == scenarios.size() || getUsercount() == getRun()){
                /*If userCount equals to the size of scenarios. It means tht there is on 
                  scenarios. But, there are statistics still need to store. So, print and store
                  statistics. Then, quit program.
                */
                printStatistic();
                
                if (EthicalEngine.getFileSave()) {
                    printToFile(getFilePath(), USERFILENAME);
                }

                System.out.println("That's all. Press Enter to quit.");

                try {
                    System.in.read();
                    System.exit(0);
                } catch (Exception e) {
                }
            }

            //Store statistics once per 3 times in interactive mode.
            if(getUsercount() % 3 == 0){
                printStatistic();

                if (EthicalEngine.getFileSave()) {
                    printToFile(getFilePath(), USERFILENAME);
                }

                System.out.println("Would you like to continue? (yes/no)");
                input = EthicalEngine.keyboard.next();

                if(input.equals("yes")){
                    /*If userCount equals to the size of scenarios. It means tht there is on 
                      scenarios. So, quit program.
                    */
                    if(getUsercount() == scenarios.size() || getUsercount() == getRun()){
                        System.out.println("That's all. Press Enter to quit.");

                        try {
                            System.in.read();
                            System.exit(0);
                        } catch (Exception e) {
                        }
                    }
                }else if(input.equals("no")){
                    System.out.println("That's all. Press Enter to quit.");

                    try {
                        System.in.read();
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
            }
        }else{
            if(EthicalEngine.decide(scenario).toString().equals("PEDESTRIANS")){
                //Add Pedestrians statistics.
                storeCharacter(scenario.getPedestrians(), getStatistics());

                //Add the value of legal statistics.
                storeScenarioStastistic(scenario, EthicalEngine.decide(scenario).toString());
                
                //Store total age and the number of person.
                storeAge(scenario.getPedestrians());
            }else{
                //Add Passengers statistics.
                storeCharacter(scenario.getPassengers(), getStatistics());

                //Add the value of legal statistics.
                storeScenarioStastistic(scenario, EthicalEngine.decide(scenario).toString());
                
                //Store total age and the number of person.
                storeAge(scenario.getPassengers());
            }
        }
    }
    
    public void storeAge(Character[] savedCharacters){
        for(Character savedCharacter : savedCharacters){
            if(savedCharacter instanceof Person){
                setTotalAge(getTotalAge() + ((Person)savedCharacter).getAge());
                setTotalHuman(getTotalHuman() + 1);
            }
        }
    }
    
    /**
      Add the value of legal statistics.
      @param scenario the scenario which is currently be run
     **/
    public void storeScenarioStastistic(Scenario scenario, String survive){
        if (scenario.isLegalCrossing()){
            storeStatistics("green", getStatistics());
            
            //Add total number of characters.
            setGreenTotal(getGreenTotal() + scenario.getPassengerCount()
                                          + scenario.getPedestrianCount());
            //Add total number of characters who survive.
            if(survive.equals("PEDESTRIANS")){
                setGreenSurvival(getGreenSurvival() + scenario.getPedestrianCount());
            }else{
                setGreenSurvival(getGreenSurvival() + scenario.getPassengerCount());
            }
            
        }else{
            storeStatistics("red", getStatistics());
            
            //Add total number of characters.
            setRedTotal(getRedTotal() + scenario.getPassengerCount() 
                                      + scenario.getPedestrianCount());
            //Add total number of characters who survive.
            if(survive.equals("PEDESTRIANS")){
                setRedSurvival(getRedSurvival() + scenario.getPedestrianCount());
            }else{
                setRedSurvival(getRedSurvival() + scenario.getPassengerCount());
            }
        }
    }
    
    /**
      Store the characteristics.
      @param savedCharacters the characters who's information to be stored
      @param statistics the Survival ArrayList to record the survival characteristics
     */
    public void storeCharacter(Character[] savedCharacters, ArrayList<Survival> statistics){
        for(Character character : savedCharacters){
            if(character instanceof Person){
                Person person = (Person)character;
                
                storeStatistics(person.getAgeCategory().toString(), statistics);
                storeStatistics(person.getBodyType().toString(), statistics);
                storeStatistics("person", statistics);
                
                //UNKNOWN does not need to be stored.
                if(!person.getGender().equals(Person.Gender.UNKNOWN)){
                    storeStatistics(person.getGender().toString(), statistics);
                }
                
                //UNKNOWN and NONE do not need to be stored.
                if(!person.getProfession().equals(Person.Profession.UNKNOWN) && 
                   !person.getProfession().equals(Person.Profession.NONE)){
                    
                    storeStatistics(person.getProfession().toString(), statistics);
                }
                
                if(person.isPregnant()){
                    storeStatistics("pregnant", statistics);
                }
                
                if(person.isYou()){
                    storeStatistics("you", statistics);
                }
            }else{
                Animal animal = (Animal)character;
                
                storeStatistics("animal", statistics);
                storeStatistics(animal.getSpecies(), statistics);
                
                if(animal.isPet()){
                    storeStatistics("pet", statistics);
                }
            }
        }
    }
    
    /**
      Store the characteristics or add value to statistics.
      @param value the characteristics to be stored.
      @param statistics the Survival ArrayList to record the survival characteristics
     */
    public void storeStatistics(String value, ArrayList<Survival> statistics){
        
        if(statistics.size() == 0){
            statistics.add(new Survival(value));
        }else{
            //Check whether statistics exist this value. If yes, add 1. If no, add new category.
            boolean find = false;
            int valueIndex = 0;
            for (int j = 0; j < statistics.size(); j++) {
                if(statistics.get(j).getCategory().equals(value)){
                    find = true;
                    valueIndex = j;
                    break;
                }
            }
            
            if(find){
                statistics.get(valueIndex).add();
            }else{
                statistics.add(new Survival(value));
            }
        }
    }
    
    /**
      @return the string to print the statistics.
     **/
    public String toString(){
        String output = "";
        float ratio = 0;
        ArrayList<Survival> statistics = getStatistics();
        ArrayList<Survival> appearances = getAppearance();
        DecimalFormat df = new DecimalFormat("#.0");
       
        if (getTotalRun() == 0) {
            output = "no audit available";
        }else{
            sortRatio(statistics);
            
            output += "======================================\n"
                   + "# " + getAuditType()+ " Audit\n"
                   + "======================================\n"
                   + "- % SAVED AFTER "
                   + ((getAuditType().equals("User")) ? getUsercount() : getTotalRun()) + " RUNS\n";
            
            for(Survival statistic : statistics){
                if(statistic.getCategory().equals("green")){
                    ratio = (float) (Math.floor((float)getGreenSurvival() / 
                                                getGreenTotal() * 10.0) / 10.0);
                    if(ratio == 0.0){
                        //Do not store and print the ratio is 0.
                        continue;
                    }
                 }else if(statistic.getCategory().equals("red")){
                    ratio = (float) (Math.floor((float)getRedSurvival() / 
                                     getRedTotal() * 10.0) / 10.0);
                    
                    if(ratio == 0.0){
                        //Do not store and print the ratio is 0.
                        continue;
                    }
                 }else{
                    for(Survival appearance : appearances){
                        //Find the matched category in appearances in order to calculate the ratio.
                        if(statistic.getCategory().equals(appearance.getCategory())){
                            ratio = (float) (Math.floor(statistic.getTimes() / 
                                                      appearance.getTimes() * 10.0) / 10.0);
                        }
                    }
                }
                output += statistic.getCategory().toLowerCase() + ": " + String.valueOf(ratio) + "\n";
            }
            
            for(Survival appearance : appearances){
                //Find the matched category in appearances in order to calculate the ratio.
                boolean find = false;
                
                for(Survival statistic : statistics){
                    if(appearance.getCategory().equals(statistic.getCategory())){
                        find = true;
                        break;
                    }
                }
                
                if(!find){
                    output += appearance.getCategory().toLowerCase() + ": " + "0.0" + "\n";
                }
            }
            
            output += "--\n" + "average age: " 
                    + (float) (Math.floor((float)getTotalAge() / getTotalHuman() * 10.0) / 10.0);
        }
        
        return output;
    }
    
    /**
      Sort statistics by win Survival Ratio iin descending.  
      @param statistics the Survival ArrayList which need to be sorted.
     */
    public void sortRatio(ArrayList<Survival> statistics){
        ArrayList<Survival> appearances = getAppearance();
        
        //sort by ratio
        Comparator sortWin = new Comparator<Survival>(){
            @Override
            public int compare(Survival statistic1,  Survival statistic2) { 
                float statistic1Ratio = 0, statistic2Ratio = 0;
                
                /*If category is green or red, calculate the ratio seperately because they use 
                  different ways to store the required value.
                */
                if(statistic1.getCategory().equals("green")){
                    statistic1Ratio = (float)getGreenSurvival() / (float)getGreenTotal();
                }else if(statistic1.getCategory().equals("red")){
                    statistic1Ratio = (float)getRedSurvival()/ (float)getRedTotal();
                }
                
                if(statistic2.getCategory().equals("green")){
                    statistic2Ratio = (float)getGreenSurvival() / (float)getGreenTotal();
                }else if(statistic2.getCategory().equals("red")){
                    statistic2Ratio = (float)getRedSurvival()/ (float)getRedTotal();
                }
                
                
                for(Survival appearance : appearances){
                    //Find the matched category in appearances in order to calculate the ratio.
                     if(appearance.getCategory().equals(statistic1.getCategory())){
                         statistic1Ratio = statistic1.getTimes() / appearance.getTimes();

                     }else if(appearance.getCategory().equals(statistic2.getCategory())){
                         statistic2Ratio = statistic2.getTimes() / appearance.getTimes();
                     } 
                 } 
                
                if(statistic1Ratio < statistic2Ratio) 
                    return 1; 
                else if(statistic1Ratio > statistic2Ratio) 
                    return -1; 
                else
                    return 0;
            } 
        };
        
        Collections.sort(statistics, sortWin);
    }
    
    /**
      Print statistic.
     **/
    public void printStatistic(){
        System.out.println(toString());
    }
    
    /**
      Save statistics to file.
      @param filepath where the result to be store
      @param fileName the result file's name
     **/
    public void printToFile(String filepath, String fileName){
        File folder = new File(filepath);
        //Check Whether the directory is exist.
        if ((!folder.exists() && !folder.isDirectory()) && !filepath.equals(".\\")){
            System.out.println("ERROR: could not print results. Target directory does not exist.");
        }else{
            File file = new File(filepath + fileName);
            
            //Check Whether the file is exist.
            try{
                if (!file.exists()) {
                   file.createNewFile();
                }
                
                 PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
                 
                 //Separate the value we don't need to store.
                 String[] output = toString().split("--");
                 printWriter.println(output[0]);
                 printWriter.close();
            }catch(IOException exception){
            }           
        }
    }
}