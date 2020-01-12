package pl.put.poznan.transformer.test;

import org.junit.Assert;
import org.junit.Test;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.operations.CountScenarioItems;

import static org.mockito.Mockito.mock;

public class CountScenarioItemsTest {

    @Test
    public void execute_ValidScenarioObject_CountScenarioItems() {
        int amount = new CountScenarioItems().setScenario(Scenario.getTestScenario()).execute().amount;
        Assert.assertEquals(13, amount);
    }

    @Test
    public void execute_FlatScenarioObject_CountScenarioItems() {
        int amount = new CountScenarioItems().setScenario(Scenario.getFlatTestScenario()).execute().amount;
        Assert.assertEquals(3, amount);
    }

    @Test
    public void execute_EmptyScenarioObject_CountScenarioItems() {
        int amount = new CountScenarioItems().setScenario(new Scenario()).execute().amount;
        Assert.assertEquals(0, amount);
    }

    @Test
    public void execute_DeepScenarioObject_CountScenarioItems() {
        int amount = new CountScenarioItems().setScenario(Scenario.getDeepTestScenario()).execute().amount;
        Assert.assertEquals(7, amount);
    }

    @Test
    public void execute_NoScenario_CountScenarioItems() {
        try{
            int amount = new CountScenarioItems().execute().amount;
            Assert.fail();
        }
        catch (NullPointerException e){
            Assert.assertTrue(true);
        }
    }
}
