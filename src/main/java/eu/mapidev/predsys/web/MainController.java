package eu.mapidev.predsys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String listIndex() {
	return "index";
    }
    
    @GetMapping("/multi")
    public String listMulti(){
	return "multi";
    }
    
    @GetMapping("/mini")
    public String listMini(){
	return "mini";
    }
}
