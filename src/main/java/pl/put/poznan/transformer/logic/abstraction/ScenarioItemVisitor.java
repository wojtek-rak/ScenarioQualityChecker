package pl.put.poznan.transformer.logic.abstraction;

import pl.put.poznan.transformer.logic.models.scenarios.Scenario;

public abstract class ScenarioItemVisitor<ResultType> implements IOperation<ResultType> {
    private Scenario scenario;

    public ScenarioItemVisitor<ResultType> setScenario(Scenario scenario){
        this.scenario = scenario;
        return this;
    }

    public ResultType evaluate(Scenario scenario){
        visit(scenario.getRoot());
        return getResult();
    }

    public ResultType execute(){
        return evaluate(scenario);
    }

    public abstract void visit(ScenarioItem scenarioItem);

    protected abstract ResultType getResult();
}
