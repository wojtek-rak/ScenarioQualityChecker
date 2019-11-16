package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.ScenarioItemVisitor;
import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.rest.models.CountScenarioItemsResponse;
import pl.put.poznan.transformer.rest.models.ScenarioWithNumberingResponse;

public class ScenarioWithNumbering extends ScenarioItemVisitor<ScenarioWithNumberingResponse> {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void visit(Scenario scenario) {
        for (ScenarioStep step : scenario) {
            stringBuilder.append(step.getScenarioHeader()).append("\n"); ;
            //amount++;
            for (Scenario subScenario : step.getSubScenarios()) {
                visit(subScenario);
            }
        }
    }

    @Override
    protected ScenarioWithNumberingResponse getResult() {
        ScenarioWithNumberingResponse result = new ScenarioWithNumberingResponse();
        result.scenario = stringBuilder.toString();
        return result;
    }
}
