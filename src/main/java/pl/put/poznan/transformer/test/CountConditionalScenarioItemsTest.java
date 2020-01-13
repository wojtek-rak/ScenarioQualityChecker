package pl.put.poznan.transformer.test;

import org.junit.Assert;
import org.junit.Test;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.operations.CountConditionalScenarioItems;
import pl.put.poznan.transformer.logic.operations.CountScenarioItems;

public class CountConditionalScenarioItemsTest {

    @Test
    public void execute_ValidScenarioObject_CountScenarioItems() {
        int amount = new CountConditionalScenarioItems().setScenario(Scenario.getTestScenario()).execute().amount;
        Assert.assertEquals(2, amount);
    }

    @Test
    public void execute_FlatScenarioObject_CountScenarioItems() {
        int amount = new CountConditionalScenarioItems().setScenario(Scenario.getFlatTestScenario()).execute().amount;
        Assert.assertEquals(0, amount);
    }

    @Test
    public void execute_EmptyScenarioObject_CountScenarioItems() {
        int amount = new CountConditionalScenarioItems().setScenario(new Scenario()).execute().amount;
        Assert.assertEquals(0, amount);
    }

    @Test
    public void execute_DeepScenarioObject_CountScenarioItems() {
        int amount = new CountConditionalScenarioItems().setScenario(Scenario.getDeepTestScenario()).execute().amount;
        Assert.assertEquals(6, amount);
    }

    @Test
    public void execute_NoScenario_CountScenarioItems() {
        try{
            int amount = new CountConditionalScenarioItems().execute().amount;
            Assert.fail();
        }
        catch (NullPointerException e){
            Assert.assertTrue(true);
        }
    }
}
