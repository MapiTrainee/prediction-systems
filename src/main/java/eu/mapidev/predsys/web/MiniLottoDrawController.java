package eu.mapidev.predsys.web;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import eu.mapidev.predsys.domain.MultiMultiDraw;
import eu.mapidev.predsys.service.DrawService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mini")
@RestController()
public class MiniLottoDrawController {

    @Autowired
    @Qualifier("MultiMulti")
    private DrawService service;

    @GetMapping("/draw")
    public ResponseEntity<?> getAllDraws() {
	return new ResponseEntity<>(service.getAllDraws(), HttpStatus.OK);
    }

    @GetMapping("/draw/{year}/{month}/{day}/{hour}/{minute}")
    public ResponseEntity<?> getDrawByDatetext(@PathVariable int year,
	    @PathVariable int month,
	    @PathVariable int day,
	    @PathVariable int hour,
	    @PathVariable int minute) {
	return new ResponseEntity<>(service.getDraw(LocalDateTime.of(year, month, day, hour, minute)), HttpStatus.OK);
    }

    @PostMapping("/draw")
    public ResponseEntity<?> createTicketDraw(@RequestBody MultiMultiDraw draw) {
	return new ResponseEntity<>(service.createTicketDraw(draw), HttpStatus.CREATED);
    }

    @PutMapping("/draw")
    public ResponseEntity<?> createResult(@RequestBody MultiMultiDraw draw) {
	return new ResponseEntity<>(service.createResult(draw), HttpStatus.CREATED);
    }

    @ExceptionHandler({IllegalStateException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
	response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
