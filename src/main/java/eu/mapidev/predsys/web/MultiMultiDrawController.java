package eu.mapidev.predsys.web;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import eu.mapidev.predsys.domain.MultiMultiDraw;
import eu.mapidev.predsys.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
public class MultiMultiDrawController {

    @Autowired
    @Qualifier("MultiMulti")
    private DrawService service;

    @RequestMapping(value = "/multi", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDraws() {
        return new ResponseEntity<>(service.getAllDraws(), HttpStatus.OK);
    }

    @RequestMapping(value = "/multi/last", method = RequestMethod.GET)
    public ResponseEntity<?> getLastDraw() {
        return new ResponseEntity<>(service.getLastDraw(), HttpStatus.OK);
    }

    @RequestMapping(value = "/multi/{timestamp}", method = RequestMethod.GET)
    public ResponseEntity<?> getDraw(@PathVariable("timestamp") Long timestamp) {
        return new ResponseEntity<>(service.getDraw(new Date(timestamp)), HttpStatus.OK);
    }

    @RequestMapping(value = "/multi", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateDraw(@RequestBody MultiMultiDraw draw) {
        return new ResponseEntity<>(service.updateDraw(draw), HttpStatus.CREATED);
    }

    @ExceptionHandler({IllegalStateException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
