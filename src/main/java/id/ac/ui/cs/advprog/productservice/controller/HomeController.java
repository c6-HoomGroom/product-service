package id.ac.ui.cs.advprog.productservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("")
    public String goToHomePage(Model model){
        return "homePage";
    }

    @GetMapping("/test")
    public String goToTestPage(Model model){
        return "testPage";
    }
}
