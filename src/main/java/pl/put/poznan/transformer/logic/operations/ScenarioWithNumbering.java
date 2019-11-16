package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.ScenarioItemVisitor;
import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.rest.models.CountScenarioItemsResponse;
import pl.put.poznan.transformer.rest.models.ScenarioWithNumberingResponse;

public class ScenarioWithNumbering extends ScenarioItemVisitor<ScenarioWithNumberingResponse> {
    private StringBuilder stringBuilder = new StringBuilder();

    protected void visit(String prefix, int counter, Scenario scenario) {
        for (ScenarioStep step : scenario) {
            stringBuilder.append(prefix)
                    .append(counter).append(". ")
                    .append(step.getScenarioHeader()).append("\n"); ;

            for (Scenario subScenario : step.getSubScenarios()) {
                visit(String.format("%s%d.", prefix, counter) , 1, subScenario);
            }

            counter++;
        }
    }

    @Override
    protected ScenarioWithNumberingResponse getResult() {
        ScenarioWithNumberingResponse result = new ScenarioWithNumberingResponse();
        result.scenario = stringBuilder.toString();
        return result;
    }

    @Override
    public ScenarioWithNumberingResponse execute(){
        visit("", 1, getScenario());
        return getResult();
    }

    @Override
    protected void visit(Scenario scenario) {

    }
}
