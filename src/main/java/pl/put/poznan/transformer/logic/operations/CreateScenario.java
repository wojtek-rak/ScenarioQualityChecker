package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.IOperation;
import pl.put.poznan.transformer.rest.models.RawScenario;
import pl.put.poznan.transformer.rest.models.OperationResultMessage;

public class CreateScenario implements IOperation<OperationResultMessage> {
    private RawScenario rawScenario;

    public CreateScenario setRawScenario(RawScenario rawScenario){
        this.rawScenario = rawScenario;
        return this;
    }

    @Override
    public OperationResultMessage execute() {
        // TODO implementation
        return null;
    }
}
