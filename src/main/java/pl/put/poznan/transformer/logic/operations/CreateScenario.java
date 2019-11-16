package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.IOperation;
import pl.put.poznan.transformer.logic.managers.DataManager;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.rest.models.CreateScenarioResponse;
import pl.put.poznan.transformer.rest.models.RawScenario;

public class CreateScenario implements IOperation<CreateScenarioResponse> {
    private RawScenario rawScenario;

    public CreateScenario setRawScenario(RawScenario rawScenario){
        this.rawScenario = rawScenario;
        return this;
    }

    @Override
    public CreateScenarioResponse execute() {
        Scenario scenario = ModelConverters.RawToScenario(rawScenario);
        int id = scenario.hashCode();
        DataManager.getInstance().scenarioCollection.put(id, scenario);
        CreateScenarioResponse result = new CreateScenarioResponse();
        result.id = id;
        return result;
    }
}
