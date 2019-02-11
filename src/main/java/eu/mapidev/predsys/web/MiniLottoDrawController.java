package eu.mapidev.predsys.web;

import eu.mapidev.predsys.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mini")
@RestController
public class MiniLottoDrawController extends AbstractDrawController {

    @Autowired
    public MiniLottoDrawController(@Qualifier("MiniLottoServ") DrawService service) {
	super(service);
    }
}
