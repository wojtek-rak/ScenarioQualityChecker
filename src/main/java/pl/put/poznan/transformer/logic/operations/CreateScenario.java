package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.IOperation;
import pl.put.poznan.transformer.logic.managers.DataManager;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.models.scenarios.ScenarioRoot;
import pl.put.poznan.transformer.rest.models.CreateScenarioResponse;
import pl.put.poznan.transformer.rest.models.RawScenario;

import java.util.UUID;

public class CreateScenario implements IOperation<CreateScenarioResponse> {
    private RawScenario rawScenario;

    public CreateScenario setRawScenario(RawScenario rawScenario){
        this.rawScenario = rawScenario;
        return this;
    }

    @Override
    public CreateScenarioResponse execute() {
        ScenarioRoot scenario = ModelConverters.RawToScenario(rawScenario);
        String uniqueID = UUID.randomUUID().toString();
        DataManager.getInstance().scenarioCollection.put(uniqueID, scenario);
        CreateScenarioResponse result = new CreateScenarioResponse();
        result.id = uniqueID;
        return result;
    }
}
