package pl.sda.javawwa9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Grzesiek on 2018-10-13
 */

@Controller
public class HomeController {

    @GetMapping({"/", "domek"})
    public String home() {
        return "index";
    }
}
