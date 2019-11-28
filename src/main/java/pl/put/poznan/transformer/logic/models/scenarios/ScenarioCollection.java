package pl.put.poznan.transformer.logic.models.scenarios;

import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioCollection extends HashMap<Integer, Scenario> {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    @Override
    public Scenario put(Integer key, Scenario value) {
        counter++;
        return super.put(key, value);
    }
}
