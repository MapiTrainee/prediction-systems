package eu.mapidev.predsys.service;

import eu.mapidev.predsys.domain.AbstractDraw;
import eu.mapidev.predsys.repository.AbstractDrawRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbstractDrawService implements DrawService {

    private AbstractDrawRepository drawRepository;

    public AbstractDrawService(AbstractDrawRepository drawRepository) {
	this.drawRepository = drawRepository;
    }

    @Override
    public Iterable<? extends AbstractDraw> getAllDraws() {
	return drawRepository.findAllByOrderByDateAsc();
    }

    @Override
    public AbstractDraw getDraw(LocalDateTime date) {
	return drawRepository.findByDate(date);
    }

    @Override
    public AbstractDraw createResult(AbstractDraw abstractDraw) {
	String draw = abstractDraw.getDraw();
	LocalDateTime localDateTime = abstractDraw.getDate();
	AbstractDraw foundDraw = getDraw(localDateTime);
	if (foundDraw != null && foundDraw.getDraw() == null) {
	    foundDraw.setDraw(draw);
	    List<Integer> resultNumbers = calculateResult(foundDraw);
	    foundDraw.setResult(resultNumbers);
	    return drawRepository.save(foundDraw);
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
    public AbstractDraw getLastDraw() {
	List<AbstractDraw> draws = drawRepository.findFirstByOrderByDateAsc();
	if (!draws.isEmpty()) {
	    return draws.get(0);
	}
	throw new IllegalStateException("Last draw dosen't exist");
    }

    @Override
    public AbstractDraw createTicketDraw(AbstractDraw abstractDraw) {
	LocalDateTime localDateTime = abstractDraw.getDate();
	AbstractDraw lastDraw = getLastDraw();
	if (lastDraw == null || isNotNullAndHaveDraw(abstractDraw)) {
	    return drawRepository.save(lastDraw);
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
