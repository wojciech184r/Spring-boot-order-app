package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Controller //obsługa żąań HTTP, rejestracja jako bean
public class HelloController {

    //@RequestMapping(method = RequestMethod.GET, path = "/hello")
    @GetMapping("/hello") //ten zapis jest równoważny powyższemu
    public String helloWorld() {

        //czyszczenie cache
        //przywrócenie ustawianie wartości domyslnych

        return "welcome"; //nazwa widoku o rozszerzeniu .html
    }

    @GetMapping("/hello-msg")
    public String helloWorldWithMsg(Model model) { //wstrzyknęty zostanie pusty obiekt ModelMap

        model.addAttribute("helloMsg", "Wiadomość przekazana z kontrolera!");

        model.addAttribute("elements", Arrays.asList("one", "two", "three"));

        return "welcome-msg";
    }

}
