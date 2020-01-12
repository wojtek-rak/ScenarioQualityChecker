package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.ScenarioItemVisitor;
import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.models.scenarios.ScenarioRoot;
import pl.put.poznan.transformer.rest.models.StepsWithNoActorResponse;

public class StepsWithNoActor extends ScenarioItemVisitor<StepsWithNoActorResponse> {

    private String response = "";
    private ScenarioRoot scenarioRoot;

    public StepsWithNoActor setScenarioRoot(ScenarioRoot scenarioRoot) {
        StepsWithNoActor stepsWithNoActor = this;
        this.scenarioRoot = scenarioRoot;
        return stepsWithNoActor;
    }

    @Override
    protected void visit(Scenario scenario) {
        boolean startsWithActor;
        for (ScenarioStep step : scenario) {
            startsWithActor = false;
            for(String actor : scenarioRoot.actors) {
                if(step.extractText().startsWith(actor)){
                    startsWithActor = true;
                    break;
                }
            }
            if(step.extractText().startsWith(scenarioRoot.systemActor)) {
                startsWithActor = true;
            }
            if(!startsWithActor){
                response = response + step.extractText() + '\n';
            }
            for (Scenario subScenario : step.getSubScenarios()) {
                visit(subScenario);
            }
        }
    }

    @Override
    protected StepsWithNoActorResponse getResult() {
        StepsWithNoActorResponse stepsWithNoActorResponse = new StepsWithNoActorResponse();
        stepsWithNoActorResponse.response = response;
        return stepsWithNoActorResponse;
    }
}
