package pl.put.poznan.transformer.logic.models.scenarios;

import pl.put.poznan.transformer.logic.abstraction.ScenarioStep;
import pl.put.poznan.transformer.logic.models.scenarios.items.ScenarioBasicOperation;
import pl.put.poznan.transformer.logic.models.scenarios.items.ScenarioForEach;
import pl.put.poznan.transformer.logic.models.scenarios.items.ScenarioIfElse;
import pl.put.poznan.transformer.rest.models.RawScenario;

import java.util.ArrayList;
import java.util.List;

public class Scenario extends ArrayList<ScenarioStep> {
    public Scenario(){ }

    public Scenario(RawScenario rawScenario){
        // TODO implementation
    }

    public static Scenario getTestScenario(){
        Scenario scenario = new Scenario();
        scenario.add(new ScenarioBasicOperation("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"));
        scenario.add(new ScenarioBasicOperation("Wyświetla się formularz."));
        scenario.add(new ScenarioBasicOperation("Bibliotekarz podaje dane książki."));
        Scenario ifScenario = new Scenario();
        scenario.add(new ScenarioIfElse("Bibliotekarz pragnie dodać egzemplarze książki", ifScenario));
        ifScenario.add(new ScenarioBasicOperation("Bibliotekarz wybiera opcję definiowania egzemplarzy"));
        ifScenario.add(new ScenarioBasicOperation("System prezentuje zdefiniowane egzemplarze"));
        Scenario forEachScenario = new Scenario();
        ifScenario.add(new ScenarioForEach("egzemplarz", forEachScenario));
        forEachScenario.add(new ScenarioBasicOperation("Bibliotekarz wybiera opcję dodania egzemplarza"));
        forEachScenario.add(new ScenarioBasicOperation("System prosi o podanie danych egzemplarza"));
        forEachScenario.add(new ScenarioBasicOperation("Bibliotekarz podaje dane egzemplarza i zatwierdza."));
        forEachScenario.add(new ScenarioBasicOperation("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."));
        scenario.add(new ScenarioBasicOperation("Bibliotekarz zatwierdza dodanie książki."));
        scenario.add(new ScenarioBasicOperation("System informuje o poprawnym dodaniu książki."));
        return scenario;
    }
}
