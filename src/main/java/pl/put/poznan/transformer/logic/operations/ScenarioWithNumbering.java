package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.ScenarioItemVisitor;
import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.models.scenarios.ScenarioRoot;
import pl.put.poznan.transformer.logic.models.scenarios.StringBuilderWrapper;
import pl.put.poznan.transformer.rest.models.CountScenarioItemsResponse;
import pl.put.poznan.transformer.rest.models.ScenarioWithNumberingResponse;

public class ScenarioWithNumbering extends ScenarioItemVisitor<ScenarioWithNumberingResponse> {

    public ScenarioWithNumbering(StringBuilderWrapper sb)
    {
        stringBuilderWrapper = sb;
    }

    private StringBuilderWrapper stringBuilderWrapper;

    protected void visit(String prefix, int counter, Scenario scenario) {
        for (ScenarioStep step : scenario) {
            stringBuilderWrapper.append(prefix)
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
        result.scenario = stringBuilderWrapper.toString();
        return result;
    }

    @Override
    public ScenarioWithNumberingResponse execute(){
        ScenarioRoot scenarioRoot = (ScenarioRoot)getScenario();
        stringBuilderWrapper.append("Tytuł: ").append(scenarioRoot.title).append("\n");
        stringBuilderWrapper.append("Aktorzy: ").append(scenarioRoot.actors).append("\n");
        stringBuilderWrapper.append("System: ").append(scenarioRoot.systemActor).append("\n");
        visit("", 1, scenarioRoot.scenario);
        return getResult();
    }

    @Override
    protected void visit(Scenario scenario) {

    }
}
