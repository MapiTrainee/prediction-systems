package eu.mapidev.predsys.web;

import eu.mapidev.predsys.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/multi")
@RestController
public class MultiMultiDrawController extends AbstractDrawController {

    @Autowired
    public MultiMultiDrawController(@Qualifier("MultiMultiServ") DrawService service) {
	super(service);
    }

}
