# MoralMachines
The idea of Moral Machines is based on the Trolley Dilemma, a fictional scenario presenting a decision maker with a moral dilemma: choosing ”the lesser of two evils”. The scenario entails an autonomous car whose brakes fail at a pedestrian crossing. As it is too late to relinquish control to the car’s passengers, the car needs to make a decision based on the facts available about the situation. Figure 1 shows an example scenario.

In this project, you will create an Ethical Engine, a program designed to explore different scenarios, build an algorithm to decide between the life of the car’s passengers vs. the life of the pedestrians, audit your decision-making algorithm through simulations, and allow users of your program to judge the outcomes themselves.

# Importance
This is a project from a programming course (subject). If you come to watch because of your course (subject) assignment or project, do not just copy and paste this code or just modify the variables name otherwise your score is possible to be penalised.

# Contents
1. [Core Classes](#1core-classes)

    1.1. [The Abstract Class Character](#11-the-abstract-class-character)
   
    1.2. [Class Person.java and Anima.java](#12-class-personjava-and-animaljava)
   
    1.3. [Class Scenario.java](#13-class-scenariojava)
    
    1.4. [Class EthicalEngine.java](#14-class-ethicalenginejava)
2. [Class ScenarioGenerator.java](#2-class-scenariogeneratorjava)
3. [Class Audit.java](#3-class-auditjava)

    3.1 [Store Statistic](#31-store-statistic)

    3.2 [Save Audit Results](#32-save-audit-results)
4. [Import a Configuration File](#4-import-a-configuration-file)

    4.1. [Specify the Configuration File as Command-Line Argument](#41-specify-the-configuration-file-as-command-line-argument)
    
    4.2. [Parsing the Configuration File](#42-parsing-the-configuration-file)

    4.3. [Handle Invalid Data Row](#43-handle-invalid-data-row)

    4.4. [Audit your Algorithm Using the Scenarios from the Config File](#44-audit-your-algorithm-using-the-scenarios-from-the-config-file)
5. [Interactive Scenarios](#5-interactive-scenarios)


## 1. Core Classes

Your program should consist of seven core classes:

    ethicalengine/
    |-- Character.java
    |-- Person.java
    |-- Animal.java
    |-- Scenario.java
    |__ ScenarioGenerator.java
    Audit.java
    EthicalEngine.java
    welcome.ascii
**The classes Character, Person, Animal, Scenario, and ScenarioGenerator must be part of the package ethicalengine. The classes Audit and EthicalEngine should not be within a package.**

### 1.1 The Abstract Class Character
The class further comprises two enumeration types:
1. Gender must include the types FEMALE and MALE as well as a default option UNKNOWN, but can also include more diverse options if you so choose.
2. BodyType includes the types AVERAGE, ATHLETIC, and OVERWEIGHT as well as a default option UNSPECIFIED.

Age should be treated as a class invariant for which the following statement always yields true: age >= 0

[Go To Top](#moralmachines)
### 1.2 Class Person.java and Animal.java
This two classes inherite from Character.java.
1. Person.java: scenarios are inhabited by people who exhibit a number of characteristics. In the scenarios, each person is either considered to be a passenger or a pedestrian. A person can be you. This class represents a human in the scenarios. On top of its parent methods, the class Person must at least include the following public methods:
    * the constructor Person(int age, Profession profession, Gender gender, BodyType bodytype, boolean isPregnant).
    * the copy constructor Person(Person otherPerson).
    * getAgeCategory(): returns an enumeration value of the type AgeCategory depending on the person’s age with one of the following values:

            – BABY : a person with an age between 0 and 4.
            – CHILD: a person with an age between 5 and 16.
            – ADULT: a person with an age between 17 and 68.
            – SENIOR: a person with an age above 68
    * the public method getProfession(): returns an enumeration value of the type Profession, which must include the following values: DOCTOR, CEO, CRIMINAL, HOMELESS,                 UNEMPLOYED, UNKNOWN (as default). **Only ADULTs have professions**, other age categories should return the default value NONE. Additionally, you are tasked with coming up       with at least two more categories you deem feasible.
    * the public method isPregnant(): returns a boolean indicating whether the person is pregnant. For all instances of Person whose gender is not FEMALE this should return           false.
    * the public method setPregnant(boolean pregnant): sets the value returned by isPregnant() while preventing invalid states, such as a pregnant male.
    * isYou(): returns a boolean indicating whether the person is representative of the user, e.g., you are one of the passengers in the car.
    * the public method setAsYou(boolean isYou): sets the value of whether the person is representative of the user.
    * the public method toString() must output a person’s characteristics according to the format shown below. Format:

            [isYou] <bodyType> <age category> [profession] <gender> [pregnant]
 2. Animal.java: animals are part of the environment we live in. People walk their pets so make sure your program accounts for these, at least for: cats and dogs.
    * the constructor Animal(String species).
    * the copy constructor Animal(Animal otherAnimal).
    * the public method getSpecies(): returns a String indicating what type of species the animal represents.
    * the public method setSpecies(String species): sets the value returned by getSpecies().
    * the public method isPet(): returns a boolean value depending whether the animal is a pet or wild animal.
    * the public method setPet(Boolean isPet): sets the value returned by isPet().
    * the public method toString() must output a pet’s characteristics. Format:

            <species> [is pet]
[Go To Top](#moralmachines)
### 1.3 Class Scenario.java
This class contains all relevant information about a presented scenario, including the car’s passengers and the pedestrians on the street as well as whether the pedestrians are crossing legally. **Each scenario can have only one instance of Person for which isYou() returns true.** The following public methods must be implemented:
* the constructor Scenario(Character[] passengers, Character[] pedestrians, boolean isLegalCrossing): you can use **Arrays or ArrayLists** in your class, but you need to make     sure this constructor takes a person array as an argument.
* the public method hasYouInCar(): returns a boolean indicating whether you (the user) is in the car.
* the public method hasYouInLane(): returns a boolean indicating whether you (the user) are in the lane, i.e., crossing the street.
* the public method getPassengers(): returns the cars’ passengers as a Character[] array.
* the public method getPedestrians(): returns the pedestrians as a Character[] array.
* the public method isLegalCrossing(): returns whether the pedestrians are legally crossing at the traffic light.
* the public method setLegalCrossing(boolean isLegalCrossing): sets whether the pedestrians are legally crossing the street.
* the public method getPassengerCount(): returns the number of passengers in the car (in int).
* the public method getPedestrianCount(): returns the number of pedestrians on the street (in int).
* the public method toString() must output the scenario. Format:

        ======================================
        # Scenario
        ======================================
        Legal Crossing: <yes/no>
        Passengers (<getPassengerCount>)
        - <character.toString>
        Pedestrians (<getPedestrianCount)
        - <character.toString>
[Go To Top](#moralmachines)
### 1.4 Class EthicalEngine.java
This class holds the main method and manages your program execution. It takes care of program parameters (see [Section 4](#4-import-a-configuration-file)) as well as user input (see [Section 5](#5-interactive-scenarios)). This class also houses the decide(scenario) method, which implements the decision-making algorithm outputting either PEDESTRIANS or PASSENGERS depending on whom to save. The code must choose whom to save for any scenario.

**Decision Algorithm:** Your task is to implement the public static method decide(Scenario scenario) that either returns a value of the Enumeration type Decision, which is either PEDESTRIANS or PASSENGERS. **Your code must choose whom to save for any scenario.** You can consider the characteristics of the characters involved as well as the situation. For instance, you can take any of the characters’ characteristics. 

[Go To Top](#moralmachines)
## 2. Class ScenarioGenerator.java
This class is used to create a variety of scenarios. To guarantee a balanced set of scenarios, it is crucial to randomize as many elements as possible, including the number and characteristics of persons and animals involved in each scenario as well as the scenario itself.

The following methods:
* the empty constructor ScenarioGenerator(): this constructor should set the seed to a truly random number
* the constructor ScenarioGenerator(long seed): this constructor sets the seed with a predefined value
* the constructor ScenarioGenerator(long seed, int passengerCountMinimum, int passengerCountMaximum, int pedestrianCountMinimum, int pedestrianCountMaximum): this constructor  sets
the seed as well as the minimum and maximum number for both passengers and pedestrians with predefined values
* the public method setPassengerCountMin(int min): sets the minimum number of car passengers for each scenario
* the public method setPassengerCountMax(int max): sets the maximum number of car passengers for each scenario
* the public method setPedestrianCountMin(int min): sets the minimum number of pedestrians for each scenario
* the public method setPedestrianCountMax(int max): sets the maximum number of pedestrians for each scenario
* the public method getRandomPerson() which returns a newly created instance of Person with random age, gender, bodyType, profession, and state of pregnancy
* the public method getRandomAnimal() which returns a newly created instance of Animal with random age, gender, bodyType, species, and whether it is a pet or not
* the public method generate() which returns a newly created instance of Scenario containing a random number of passengers and pedestrians with random characteristics as well as   a randomly red or green light condition with you (the user) being either in the car, on the street, or absent.

[Go To Top](#moralmachines)
## 3. Class Audit.java
In this class, you will simulate a variety of scenarios and have your EthicalEngine decide on their outcomes.

The class Audit.java should:
* create a specific number of random scenarios
* allow your EthicalEngine to decide on each outcome,
* summarize the results for each characteristic in a so-called statistic of projected survival.

The class Audit.java must have following methods:
* the empty constructor Audit()
* the public method run(int runs): runs the simulation by creating N = runs scenarios and running each scenario through the EthicalEngine using its decide(Scenario scenario)       method. For each scenario you need to save the outcome and add the result to your statistic
* the public method setAuditType(String name): sets the name of the audit type. For example: *Algorithm* for an audit of your algorithm.
* the public method getAuditType(): returns the name of the audit. Default should be Unspecified.
* the public method toString(): returns a summary of the simulation in the format depicted below. If no simulation has been run, this method returns ”no audit available”.
* the public method printStatistic(): prints the summary returned by the toString() method to the command-line.
### 3.1 Store Statistic
Statistic should list a number of factors, including:
* age category
* gender
* body type
* profession
* pregnant
* class type (person or animal)
* species
* pets
* legality (red or green light)

output format(pseudocode): If the program is in interactive mode ([Section 5](#5-interactive-scenarios)), the auditType is user otherwise the type is Unspecified.

    ======================================
    # <auditType> Audit
    ======================================
    - % SAVED AFTER <int run> RUNS
    <for each characterstic:>
        <characterstic>: <survival ratio>
    --
    average age: <average>
The list of characteristics must be sorted in **descending order of the survival ratio.** If tie, it is not nessary to order the Alphabet. The average age is calculated across all survivors of class Person (**animals are excluded**). Statistic must not list animals by gender, age, or body type.

**Update Statistic within an Audit:**
If you run multiple scenarios within a particular audit, make sure to update your statistic rather than overwrite it. For example, you may run an audit subsequently over 10 (audit.run(10)), 50 (audit.run(50)), and 100 (audit.run(100)) scenarios and print an updated statistic after each run to the command-line. The result on the command-line should be three statistic outputs: the first with 10, the second with 60, and the last with 160 runs.

### 3.2 Save Audit Results
To save the results of your audit to a file, add the public method printToFile(String filepath) to your Audit class. The method prints the results of the toString() method to a target file named results.log. The *filepath* variable is set by the command-line flat **−r or −−results** and includes both the target directory (logs/, in this case) and the filename (results.log). If results.log already exists in the target directory, you should append the new data rather than overwrite the existing file.

[Go To Top](#moralmachines)
## 4. Import a Configuration File
In this task, you need to extend your **EthicalEngine class** to allow it to create scenarios based on data it reads from a configuration file.
### 4.1 Specify the Configuration File as Command-Line Argument
In this task, you need to create a command-line option. Command-line options or so-called *flags* specify options that modify the operation of your program. Options follow the program execution command on the command-line, separated by spaces. Options can be specified **in any order**. The following program calls are equivalent and should be supported by your program:

    $ java EthicalEngine --config path/to/config.csv
    and
    $ java EthicalEngine -c path/to/config.csv
The command line argument following the flag –config of -c respectively specifies the filepath where the configuration file (config.csv) is located. Your program should check whether the file is located at the specified location and handle a FileNotFoundException in case the file does not exist. In this case, your program should terminate with the following error message:

    ERROR: could not find config file.
### 4.2 Parsing the Configuration File
Your EthicalEngine class needs to be able to read in a config file as depicted in Table 1 and create a Scenario instance for each scenario the file contains. Note that a config file can contain any number of scenarios with any number of passengers and pedestrians. You can assume that all config files follow the same format with the columns ordered as the provided CSV file.

### 4.3 Handle Invalid Data Row
While reading in the config file line by line your program may encounter three types of exceptions, which your program should be able to handle:
1. **InvalidDataFormatException**: Appear when there is a invalid number of data fields per row. If the number of values in one row is less than or exceeds 10 values, this exception should be thrown. Program should handle such exceptions by issuing the warning statement **”WARNING: invalid data format in config file in line < linecount >”** to the command-line and skip the respective row then continue reading in the next line.

2. **NumberFormatException**: Appear when there is invalid data type. If the value can not cast into an existing data type (e.g., a character where, an int should be for age), this exception should be thrown. Program should handle such exceptions by issuing the warning statement **”WARNING: invalid number format in config file in line < linecount >”** to the command-line, assign a default value instead, and continue with the next value in that line.

3. **InvalidCharacteristicException**: Appear when there is invalid field values. If program does not accommodate a specific value (e.g., skinny as a bodyType), this exception should be thrown. Program should handle such exceptions by issuing a warning statement **”WARNING: invalid characteristic in config file in line < linecount >”** to the command-line, assign a default value instead, and continue with the next value in that line.

**< linecount > depicts the line number in the config file where the error was found.**

### 4.4 Audit your Algorithm Using the Scenarios from the Config File
Once your program has imported all scenarios from config.csv it should create a new Audit. Therefore, you need to extend your Audit class by adding two more methods:
1. the constructor Audit(Scenario[] scenarios): this constructor creates a new instance with a fixed set of scenarios
2. the public method run(): runs the simulation with the scenarios specified and runs each scenario through the EthicalEngine using its decide(Scenario scenario) method.

[Go To Top](#moralmachines)
## 5. Interactive Scenarios
We can let the user take over and be the judge. Therefore, you need to build an interactive console program, which presents the user with a number of ethical scenarios. For each scenario the user is asked to make a decision about who should survive. The results are logged to a user file (user.log) but only if the user consents to it.

### 5.1 Program Setup
As described in [Section 4.1](#41-specify-the-configuration-file-as-command-line-argument), we will use command-line options or so-called flags to initialize the execution of EthicalEngines. Therefore, you should add a few more options as possible command-line arguments. The following program call should invoke the help:

    $ java EthicalEngine --help
    and
    $ java EthicalEngine -h
The command-line output following the invocation of the help should look like this:
    
    EthicalEngine - Final Project
    Usage: java EthicalEngine [arguments]
    Arguments:
    -c or --config Optional: path to config file
    -h or --help Print Help (this message) and exit
    -r or --results Optional: path to results log file
    -i or --interactive Optional: launches interactive mode
The following command will launch the program with a config file in the interactive mode:

    $ java EthicalEngine -i -c config.csv
Here is an example of launching the program in the interactive mode with random scenarios:

    $ java EthicalEngine -i
### 5.2 Program Execution
You need to extend the EthicalEngine class to manage the user interaction and support the following program flow:
1. Show Welcome Screen At the start of the program, a welcome message must be shown: your program should read in and display the contents of welcome.ascii to the user
2. After the welcome message, program should prompt to the user with the following method on the command-line in order to collect user consent for data collection.
    
        Do you consent to have your decisions saved to a file? (yes/no)
    Only if the user confirms (yes), your program should save the user statistic to user.log. If the user selects no your program should function normally but not write any of       the users’ decisions to the file (it should still display the statistic on the command-line though). If the user types in anything other than yes or no, an                       **InvalidInputException** should be thrown and the user should be prompted again:
    
        Invalid response. Do you consent to have your decisions saved to a file? (yes/no)
3.  Once the user consented (or not), the scenario judging begins. Therefore, scenarios are either imported from the config file or (if the config file is not specified)             randomly generated. Make sure to set the audit type to User using the method *setAuditType(String name)*. Scenarios are presented one by one using the *toString()* method       of the *Scenario* instance and printing its outputs to the command-line. Each scenario should be followed by a prompt saying:

        Who should be saved? (passenger(s) [1] or pedestrian(s) [2])
        Note that input passenger, passengers, or 1 represent passenger. And input pedestrian, pedestrians, or 2 represent pedestrian.
    After the user made a decision, the next scenario is shown followed by the prompt to judge the scenario. This procedure should repeat until 3 scenarios have been shown and       judged. After the third scenario decision, the result statistic is presented.
4.  The statistic must be printed to the command-line using the same method and format as described in [Section 3.1](#31-store-statistic). If the user previously consented to the data collection, the statistic is saved (i.e., appended) to the file user.log using the function printToFile(String filepath) of the Audit class. Additionally, the user should be prompted to either continue or quit the program as follows:
    
        Would you like to continue? (yes/no)
    Should the user choose no the program terminates. If the user decides to continue (yes), the next three scenarios should be shown. If the config file does not contain any       more scenarios, the final statistic should be shown followed by the following prompt.
    
        That’s all. Press Enter to quit.
[Go To Top](#moralmachines)

## Source
University of Melbourne COMP90041 Subject final project
