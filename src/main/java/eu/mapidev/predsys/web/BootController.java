
package eu.mapidev.predsys.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BootController {

    @Value("${spring.profiles.active:default}")
    private String information;

    @RequestMapping("/")
    public ModelAndView multi() {
        return new ModelAndView("multi");
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping("/info")
    public ResponseEntity<?> info() {
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
}
