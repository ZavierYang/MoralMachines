# MoralMachines
The idea of Moral Machines is based on the Trolley Dilemma, a fictional scenario presenting a decision maker with a moral dilemma: choosing ”the lesser of two evils”. The scenario entails an autonomous car whose brakes fail at a pedestrian crossing. As it is too late to relinquish control to the car’s passengers, the car needs to make a decision based on the facts available about the situation. Figure 1 shows an example scenario.

In this project, you will create an Ethical Engine, a program designed to explore different scenarios, build an algorithm to decide between the life of the car’s passengers vs. the life of the pedestrians, audit your decision-making algorithm through simulations, and allow users of your program to judge the outcomes themselves.

# Contents
1. [Core Classes](#class-person.java-and-animal.java)

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

### 1.1. The Abstract Class Character
The class further comprises two enumeration types:
1. Gender must include the types FEMALE and MALE as well as a default option UNKNOWN, but can also include more diverse options if you so choose.
2. BodyType includes the types AVERAGE, ATHLETIC, and OVERWEIGHT as well as a default option UNSPECIFIED.

Age should be treated as a class invariant for which the following statement always yields true: age >= 0

### 1.2. Class Person.java and Animal.java
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
### 1.3. Class Scenario.java
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
### 1.4. Class EthicalEngine.java
This class holds the main method and manages your program execution. It takes care of program parameters (see Section 4) as well as user input (see Section 5). This class also houses the decide(scenario) method, which implements the decision-making algorithm outputting either PEDESTRIANS or PASSENGERS depending on whom to save. The code must choose whom to save for any scenario.

**Decision Algorithm:** Your task is to implement the public static method decide(Scenario scenario) that either returns a value of the Enumeration type Decision, which is either PEDESTRIANS or PASSENGERS. **Your code must choose whom to save for any scenario.** You can consider the characteristics of the characters involved as well as the situation. For instance, you can take any of the characters’ characteristics. 

## 2. ScenarioGenerator.java
## 3. Audit.java
## 4. Import a Configuration File
## 5. Interactive Scenarios
