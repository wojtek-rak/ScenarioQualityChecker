package pl.put.poznan.transformer.logic.abstraction;

import java.util.List;

public abstract class ScenarioItem {
    public abstract List<ScenarioItem> getChildren();
}
