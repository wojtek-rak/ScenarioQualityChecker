package pl.put.poznan.transformer.test;

import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.*;
import pl.put.poznan.transformer.logic.models.scenarios.Scenario;
import pl.put.poznan.transformer.logic.models.scenarios.ScenarioRoot;
import pl.put.poznan.transformer.logic.models.scenarios.StringBuilderWrapper;
import pl.put.poznan.transformer.logic.models.scenarios.items.StepBasicOperation;
import pl.put.poznan.transformer.logic.models.scenarios.items.StepForEach;
import pl.put.poznan.transformer.logic.models.scenarios.items.StepIf;
import pl.put.poznan.transformer.logic.operations.CountConditionalScenarioItems;
import pl.put.poznan.transformer.logic.operations.CountScenarioItems;
import pl.put.poznan.transformer.logic.operations.ScenarioWithNumbering;
import pl.put.poznan.transformer.rest.models.ScenarioWithNumberingResponse;

import java.util.ArrayList;

public class ScenarioOperationsTest {

    @Test
    public void execute_ValidScenarioObject_CountScenarioItems() {
        int amount = new CountScenarioItems().setScenario(getTestScenario().scenario).execute().amount;
        Assert.assertEquals(13, amount);
    }

    @Test
    public void execute_EmptyScenarioObject_CountScenarioItems() {
        int amount = new CountScenarioItems().setScenario(getEmptyScenario().scenario).execute().amount;
        Assert.assertEquals(0, amount);
    }

    @Test
    public void execute_ValidScenarioObject_CountConditionalScenarioItems() {
        int amount = new CountConditionalScenarioItems().setScenario(getTestScenario().scenario).execute().amount;
        Assert.assertEquals(2, amount);
    }

    @Test
    public void execute_ValidScenarioObject_ReturnProperString() {
        ScenarioWithNumbering scenarioWithNumbering = new ScenarioWithNumbering(new StringBuilderWrapper());
        scenarioWithNumbering.setScenario(getTestScenario());

        ScenarioWithNumberingResponse scenarioWithNumberingResponse = scenarioWithNumbering.execute();

        Assert.assertEquals(expectedScenarioString, scenarioWithNumberingResponse.scenario);
    }

    @Test
    public void execute_MockStringBuilder_ValidNumberOfAppends() {
        StringBuilderWrapper stringBuilderWrapperMock = mock(StringBuilderWrapper.class);
        when(stringBuilderWrapperMock.append(any(String.class))).thenReturn(stringBuilderWrapperMock);
        when(stringBuilderWrapperMock.append(Matchers.<ArrayList<String>>any())).thenReturn(stringBuilderWrapperMock);
        when(stringBuilderWrapperMock.append(any(int.class))).thenReturn(stringBuilderWrapperMock);
        ScenarioWithNumbering scenarioWithNumbering = new ScenarioWithNumbering(stringBuilderWrapperMock);
        scenarioWithNumbering.setScenario(getTestScenario());

        ScenarioWithNumberingResponse scenarioWithNumberingResponse = scenarioWithNumbering.execute();

        verify(stringBuilderWrapperMock, times(60)).append(anyString());
        verify(stringBuilderWrapperMock, times(13)).append(anyInt());
        verify(stringBuilderWrapperMock, times(1)).append(Matchers.<ArrayList<String>>any());
    }

    private String expectedScenarioString = "Tytuł: Title\n" +
            "Aktorzy: [actor]\n" +
            "System: System\n" +
            "1. Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
            "2. Wyświetla się formularz.\n" +
            "3. Bibliotekarz podaje dane książki.\n" +
            "4. IF: Bibliotekarz pragnie dodać egzemplarze książki;\n" +
            "4.1. Bibliotekarz wybiera opcję definiowania egzemplarzy\n" +
            "4.2. System prezentuje zdefiniowane egzemplarze\n" +
            "4.3. FOR EACH: egzemplarz;\n" +
            "4.3.1. Bibliotekarz wybiera opcję dodania egzemplarza\n" +
            "4.3.2. System prosi o podanie danych egzemplarza\n" +
            "4.3.3. Bibliotekarz podaje dane egzemplarza i zatwierdza.\n" +
            "4.3.4. System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.\n" +
            "5. Bibliotekarz zatwierdza dodanie książki.\n" +
            "6. System informuje o poprawnym dodaniu książki.\n";

    private static ScenarioRoot getEmptyScenario(){
        ScenarioRoot scenarioRoot = new ScenarioRoot();
        scenarioRoot.scenario = new Scenario();
        return scenarioRoot;
    }

    private static ScenarioRoot getTestScenario(){
        ScenarioRoot scenarioRoot = new ScenarioRoot();
        Scenario scenario = new Scenario();
        scenario.add(new StepBasicOperation("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"));
        scenario.add(new StepBasicOperation("Wyświetla się formularz."));
        scenario.add(new StepBasicOperation("Bibliotekarz podaje dane książki."));
        Scenario ifScenario = new Scenario();
        scenario.add(new StepIf("Bibliotekarz pragnie dodać egzemplarze książki", ifScenario));
        ifScenario.add(new StepBasicOperation("Bibliotekarz wybiera opcję definiowania egzemplarzy"));
        ifScenario.add(new StepBasicOperation("System prezentuje zdefiniowane egzemplarze"));
        Scenario forEachScenario = new Scenario();
        ifScenario.add(new StepForEach("egzemplarz", forEachScenario));
        forEachScenario.add(new StepBasicOperation("Bibliotekarz wybiera opcję dodania egzemplarza"));
        forEachScenario.add(new StepBasicOperation("System prosi o podanie danych egzemplarza"));
        forEachScenario.add(new StepBasicOperation("Bibliotekarz podaje dane egzemplarza i zatwierdza."));
        forEachScenario.add(new StepBasicOperation("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."));
        scenario.add(new StepBasicOperation("Bibliotekarz zatwierdza dodanie książki."));
        scenario.add(new StepBasicOperation("System informuje o poprawnym dodaniu książki."));

        scenarioRoot.scenario = scenario;
        scenarioRoot.title = "Title";
        ArrayList<String> actors = new ArrayList<String>();
        actors.add("actor");
        scenarioRoot.actors = actors;
        scenarioRoot.systemActor = "System";
        return scenarioRoot;
    }
}