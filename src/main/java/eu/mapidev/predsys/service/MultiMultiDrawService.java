package eu.mapidev.predsys.service;

import eu.mapidev.predsys.domain.AbstractDraw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eu.mapidev.predsys.domain.MultiMultiDraw;
import eu.mapidev.predsys.repository.MultiMultiDrawRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
@Qualifier("MultiMulti")
public class MultiMultiDrawService implements DrawService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiMultiDraw.class);

    @Autowired
    private MultiMultiDrawRepository drawRepository;

    @Override
    public Iterable<? extends AbstractDraw> getAllDraws() {
	return drawRepository.findAll();
    }

    @Override
    public AbstractDraw getDraw(LocalDateTime date) {
	return drawRepository.findOne(date);
    }

    @Override
    public AbstractDraw updateDraw(AbstractDraw draw) {
	throw new UnsupportedOperationException("updateDraw not implemented yet");
	/*
	if (multiMultiDraw.isMulti() == multiMultiDraw.isTicket()) {
	    throw new IllegalStateException("'multi' or 'ticket' must be different from null and can not be set in one request!");
	}

	if (drawRepository.exists(multiMultiDraw.getDate())) {
	    MultiMultiDraw existingDraw = drawRepository.findOne(multiMultiDraw.getDate());
	    if (existingDraw.isMulti() && existingDraw.isTicket()) {
		throw new IllegalStateException("calculated draw can not be change!");
	    } else if (existingDraw.isMulti() && multiMultiDraw.isTicket()) {
		List<Integer> multiSet = existingDraw.getDrawAsNumbers();
		List<Integer> ticketSet = multiMultiDraw.getTicketAsNumbers();

		int[] resultArray = new int[3];
		int i = 0;
		for (Integer ticketValue : ticketSet) {
		    if (multiSet.contains(ticketValue)) {
			resultArray[i] = 1;
		    }
		    i++;
		}

		existingDraw.updateResult(resultArray);
		existingDraw.setTicket((TreeSet<Integer>) ticketSet);
		return drawRepository.save(existingDraw);

	    } else if (existingDraw.isTicket() && multiMultiDraw.isMulti()) {
		List<Integer> multiSet = multiMultiDraw.getDrawAsNumbers();
		List<Integer> ticketSet = existingDraw.getTicketAsNumbers();

		int[] resultArray = new int[3];
		int i = 0;
		for (Integer ticketValue : ticketSet) {
		    if (multiSet.contains(ticketValue)) {
			resultArray[i] = 1;
		    }
		    i++;
		}

		existingDraw.updateResult(resultArray);
		existingDraw.setDraw((TreeSet<Integer>) multiSet);

		return drawRepository.save(existingDraw);
	    }

	}
	return drawRepository.save(multiMultiDraw);
	 */
    }

    @Override
    public MultiMultiDraw getLastDraw() {
	List<MultiMultiDraw> draws = drawRepository.findFirstByOrderByDateDesc();
	if (!draws.isEmpty()) {
	    return draws.get(0);
	}
	throw new IllegalStateException("Last draw dosen't exist");
    }
}
