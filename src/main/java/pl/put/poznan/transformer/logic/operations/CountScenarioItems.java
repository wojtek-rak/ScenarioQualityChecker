package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.ScenarioItem;
import pl.put.poznan.transformer.logic.abstraction.ScenarioItemVisitor;
import pl.put.poznan.transformer.rest.models.CountScenarioItemsResponse;

public class CountScenarioItems extends ScenarioItemVisitor<CountScenarioItemsResponse> {
    private Integer amount;

    @Override
    public void visit(ScenarioItem scenarioItem) {
        amount++;
        for (ScenarioItem scenarioItemChild : scenarioItem.getChildren()) {
            visit(scenarioItemChild);
        }
    }

    @Override
    protected CountScenarioItemsResponse getResult() {
        CountScenarioItemsResponse result = new CountScenarioItemsResponse();
        result.amount = amount;
        return result;
    }
}
