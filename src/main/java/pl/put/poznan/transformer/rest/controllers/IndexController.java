package pl.put.poznan.transformer.rest.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestMapping;
        import pl.put.poznan.transformer.logic.managers.DataManager;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("ids_set", DataManager.getInstance().scenarioCollection.keySet());
        return "index";
    }
}
