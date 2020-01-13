package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.IOperation;
import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.managers.DataManager;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.models.scenarios.ScenarioRoot;
import pl.put.poznan.transformer.rest.models.CreateScenarioResponse;
import pl.put.poznan.transformer.rest.models.RawScenario;

import java.util.UUID;

/**
 * Główna klasa kontrolująca wrzucanie do bazy nowych scenariuszy
 *
 * @author Marek Subocz
 * @version 1.3
 */
public class RestrictScenarioDeep implements IOperation<CreateScenarioResponse> {

    /**
     * Surowy scenariusz w postaci JSON-a przekazanego w requeście POST
     */
    private RawScenario rawScenario;
    private int max_level;

    public RestrictScenarioDeep setRawScenario(RawScenario rawScenario){
        this.rawScenario = rawScenario;
        return this;
    }

    public RestrictScenarioDeep setMaxLevel(int max_level){
        this.max_level = max_level;
        return this;
    }


    protected void restrict(Scenario scenario, int level) {
        if(level >= max_level){
            scenario.clear();
            return;
        }
        for (ScenarioStep step : scenario) {
            for (Scenario subScenario : step.getSubScenarios()) {
                restrict(subScenario, level + 1);
            }
        }
    }

    /**
     * Metoda kontrolująca przekonwertowanie scenariusza z JSON-a na obiekt klasy Scenario
     * i ustalająca nowe, unikalne id
     *
     * Id jest liczbą całkowitą zwiększaną o 1 po każdym dodaniu nowego scenariusza do bazy
     *
     * @return Id nowego scenariusza
     */
    @Override
    public CreateScenarioResponse execute() {
        ScenarioRoot scenario = ModelConverters.RawToScenario(rawScenario);
        restrict(scenario.scenario, 0);
        String uniqueID = UUID.randomUUID().toString();
        DataManager.getInstance().scenarioCollection.put(uniqueID, scenario);
        CreateScenarioResponse result = new CreateScenarioResponse();
        result.id = uniqueID;
        return result;
    }
}
