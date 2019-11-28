package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.IOperation;
import pl.put.poznan.transformer.logic.managers.DataManager;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.rest.models.CreateScenarioResponse;
import pl.put.poznan.transformer.rest.models.RawScenario;

/**
 * Główna klasa kontrolująca wrzucanie do bazy nowych scenariuszy
 *
 * @author Marek Subocz
 * @version 1.3
 */
public class CreateScenario implements IOperation<CreateScenarioResponse> {

    /**
     * Surowy scenariusz w postaci JSON-a przekazanego w requeście POST
     */
    private RawScenario rawScenario;

    public CreateScenario setRawScenario(RawScenario rawScenario){
        this.rawScenario = rawScenario;
        return this;
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
        Scenario scenario = ModelConverters.RawToScenario(rawScenario);
        int id = DataManager.getInstance().scenarioCollection.getCounter() + 1;
        DataManager.getInstance().scenarioCollection.put(id, scenario);
        CreateScenarioResponse result = new CreateScenarioResponse();
        result.id = id;
        return result;
    }
}
