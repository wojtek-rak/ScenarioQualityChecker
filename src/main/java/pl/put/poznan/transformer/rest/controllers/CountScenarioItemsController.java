package pl.put.poznan.transformer.rest.controllers;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.operations.CountScenarioItems;
import pl.put.poznan.transformer.logic.operations.TextTransformer;
import pl.put.poznan.transformer.rest.models.CountScenarioItemsResponse;

import java.util.Arrays;

@RestController
@RequestMapping("/count")
public class CountScenarioItemsController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public CountScenarioItemsResponse get() {
        return new CountScenarioItems()
                .setScenario(Scenario.getTestScenario())
                .execute();
    }
}
