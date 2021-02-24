# MoralMachines
The idea of Moral Machines is based on the Trolley Dilemma, a fictional scenario presenting a decision maker with a moral dilemma: choosing ”the lesser of two evils”. The scenario entails an autonomous car whose brakes fail at a pedestrian crossing. As it is too late to relinquish control to the car’s passengers, the car needs to make a decision based on the facts available about the situation. Figure 1 shows an example scenario.

In this project, you will create an Ethical Engine, a program designed to explore different scenarios, build an algorithm to decide between the life of the car’s passengers vs. the life of the pedestrians, audit your decision-making algorithm through simulations, and allow users of your program to judge the outcomes themselves.

## Core Classes

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
