package pl.put.poznan.transformer.rest.controllers;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.managers.DataManager;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.operations.*;
import pl.put.poznan.transformer.rest.models.CountScenarioItemsResponse;
import pl.put.poznan.transformer.rest.models.CreateScenarioResponse;
import pl.put.poznan.transformer.rest.models.RawScenario;
import pl.put.poznan.transformer.rest.models.ScenarioWithNumberingResponse;

@RestController
public class ScenarioController {

    @RequestMapping(value = "/count/{id}", method = RequestMethod.GET, produces = "application/json")
    public CountScenarioItemsResponse getCount(@PathVariable("id") int id) {
        return new CountScenarioItems()
                .setScenario(getScenario(id))
                .execute();
    }
    public CountScenarioItemsResponse get_custom(Scenario scenario) {
        return new CountScenarioItems()
                .setScenario(scenario)
                .execute();
    }

    @RequestMapping(value = "/count/conditional/{id}", method = RequestMethod.GET, produces = "application/json")
    public CountScenarioItemsResponse getConditionalCount(@PathVariable("id") int id) {
        return new CountConditionalScenarioItems()
                .setScenario(getScenario(id))
                .execute();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public CreateScenarioResponse post(@RequestBody String json) {
        return new CreateScenario()
                .setRawScenario(new RawScenario(json))
                .execute();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Scenario getScenario(@PathVariable("id") int id) {
        return DataManager.getInstance().scenarioCollection.get(id);
    }

    //ScenarioWithNumberingResponse
    @RequestMapping(value = "/ScenarioWithNumbering/{id}", method = RequestMethod.GET, produces = "text/plain")
    public String getScenarioWithNumbering(@PathVariable("id") int id) {
        return new ScenarioWithNumbering()
                .setScenario(GetScenario(id))
                .execute().scenario;
    }

    private Scenario GetScenario(int id){
        return DataManager.getInstance().scenarioCollection.get(id);
    }
}
