package xyz.pietryga.predsys.web;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.pietryga.predsys.domain.Draw;
import xyz.pietryga.predsys.service.DrawService;

@RestController
public class DrawController {

    @Autowired
    private DrawService drawService;

    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public ResponseEntity<?> getDraws() {
        return new ResponseEntity<>(drawService.getDraws(), HttpStatus.OK);
    }

    @RequestMapping(value = "/draw/last", method = RequestMethod.GET)
    public ResponseEntity<?> getLastDraw() {
        return new ResponseEntity<>(drawService.getLastDraw(), HttpStatus.OK);
    }

    @RequestMapping(value = "/draw/{timestamp}", method = RequestMethod.GET)
    public ResponseEntity<?> getDraw(@PathVariable("timestamp") Long timestamp) {
        return new ResponseEntity<>(drawService.getDraw(new Date(timestamp)), HttpStatus.OK);
    }

    @RequestMapping(value = "/draw", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateDraw(@RequestBody Draw draw) {
        return new ResponseEntity<>(drawService.updateDraw(draw), HttpStatus.CREATED);
    }

    @ExceptionHandler({IllegalStateException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
