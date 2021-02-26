
import ethicalengine.ScenarioGenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class NewClass {
    public static void main(String[] args) {
        ScenarioGenerator scenarioGenerator1 = new ScenarioGenerator(20);
        //ScenarioGenerator scenarioGenerator2 = new ScenarioGenerator(20);
        
        System.out.println(scenarioGenerator1.generate().toString());
        System.out.println(scenarioGenerator1.generate().toString());
    }
}
