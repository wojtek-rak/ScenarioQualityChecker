package pl.put.poznan.transformer.logic.models.scenarios.items;

import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;

import java.util.ArrayList;
import java.util.List;

public class StepIfElse extends ScenarioStep {
    public String condition;
    public Scenario ifBlock;
    public Scenario elseBlock;

    public StepIfElse(String condition, Scenario ifBlock){
        this.condition = condition;
        this.ifBlock = ifBlock;
    }

    public StepIfElse(String condition, Scenario ifBlock, Scenario elseBlock){
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public List<Scenario> getSubScenarios() {
        ArrayList<Scenario> subScenarios = new ArrayList<>(2);
        subScenarios.add(ifBlock);
        if(elseBlock != null){
            subScenarios.add(elseBlock);
        }
        return subScenarios;
    }
}
