package pl.put.poznan.transformer.logic.operations;

import pl.put.poznan.transformer.logic.abstraction.IOperation;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer implements IOperation<String> {

    private String[] transforms;
    private String input;

    public TextTransformer setTransforms(String[] transforms){
        this.transforms = transforms;
        return  this;
    }

    public TextTransformer setInput(String input){
        this.input = input;
        return  this;
    }

    @Override
    public String execute() {
        return input.toUpperCase();
    }
}
