package eu.mapidev.predsys.service;

import eu.mapidev.predsys.domain.AbstractDraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eu.mapidev.predsys.domain.MultiMultiDraw;
import eu.mapidev.predsys.repository.MultiMultiDrawRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
@Qualifier("MultiMulti")
public class MultiMultiDrawService implements DrawService {
    
    @Autowired
    private MultiMultiDrawRepository drawRepository;
    
    @Override
    public Iterable<? extends AbstractDraw> getAllDraws() {
	return drawRepository.findAll();
    }
    
    @Override
    public AbstractDraw getDraw(LocalDateTime date) {
	return drawRepository.findByDate(date);
    }
    
    @Override
    public AbstractDraw createResult(AbstractDraw abstractDraw) {
	String draw = abstractDraw.getDraw();
	LocalDateTime localDateTime = abstractDraw.getDate();
	MultiMultiDraw multiMultiDraw = (MultiMultiDraw) getDraw(localDateTime);
	if (multiMultiDraw != null && multiMultiDraw.getDraw() == null) {
	    multiMultiDraw.setDraw(draw);
	    List<Integer> resultNumbers = calculateResult(multiMultiDraw);
	    multiMultiDraw.setResult(resultNumbers);
	    return drawRepository.save(multiMultiDraw);
	}
	throw new IllegalStateException(
		String.format("Cannot create result for draw %s with date %s ",
			draw, localDateTime.toString()));
    }
    
    private List<Integer> calculateResult(AbstractDraw abstractDraw) {
	List<Integer> resultNumbers = new ArrayList<>();
	List<Integer> ticketNumbers = abstractDraw.getTicketAsNumbers();
	List<Integer> drawNumbers = abstractDraw.getDrawAsNumbers();
	
	for (Integer ticketNumber : ticketNumbers) {
	    resultNumbers.add(drawNumbers.contains(ticketNumber) ? 1 : 0);
	}
	return resultNumbers;
    }
    
    @Override
    public MultiMultiDraw getLastDraw() {
	List<MultiMultiDraw> draws = drawRepository.findFirstByOrderByDateAsc();
	if (!draws.isEmpty()) {
	    return draws.get(0);
	}
	throw new IllegalStateException("Last draw dosen't exist");
    }
    
    @Override
    public AbstractDraw createTicketDraw(AbstractDraw abstractDraw) {
	LocalDateTime localDateTime = abstractDraw.getDate();
	MultiMultiDraw lastDraw = getLastDraw();
	if (lastDraw == null || isNotNullAndHaveDraw(abstractDraw)) {
	    return drawRepository.save(new MultiMultiDraw(localDateTime, abstractDraw.getTicket()));
	}
	throw new IllegalStateException("Another ticket exists! Cannot add new one, first update draw for it!");
    }
    
    private boolean isNotNullAndHaveDraw(AbstractDraw abstractDraw) {
	return abstractDraw.getDraw() != null && abstractDraw.getDraw() != null;
    }
    
    @Override
    public void deleteDraw(AbstractDraw abstractDraw) {
	deleteDraw(abstractDraw);
    }
    
}
