package pl.put.poznan.transformer.logic.models.scenarios;

import java.util.ArrayList;

public class StringBuilderWrapper {
    private StringBuilder stringBuilder = new StringBuilder();

    public StringBuilderWrapper append(String toAppend)
    {
        stringBuilder.append(toAppend);
        return this;
    }

    public StringBuilderWrapper append(ArrayList<String> toAppend)
    {
        stringBuilder.append(toAppend);
        return this;
    }

    public StringBuilderWrapper append(int toAppend)
    {
        stringBuilder.append(toAppend);
        return this;
    }


    public String toString()
    {
        return stringBuilder.toString();
    }
}
