package com.demo.api;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    // Logger log = LoggerFactory.getLogger(getClass());


    @RequestMapping("/home")
    public String home(@RequestBody JsonObject jsonObject){
        System.out.println("home controller");
        System.out.println(jsonObject);
        return "home";
    }
}
