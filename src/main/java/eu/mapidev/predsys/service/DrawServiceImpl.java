package eu.mapidev.predsys.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eu.mapidev.predsys.domain.Draw;
import eu.mapidev.predsys.repository.DrawRepository;

@Service
public class DrawServiceImpl implements DrawService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Draw.class);

    @Autowired
    private DrawRepository drawRepository;

    @Override
    public Iterable<Draw> getDraws() {
        return drawRepository.findAllByOrderByDateAsc();
    }

    @Override
    public Draw getDraw(Date date) {
        return drawRepository.findOne(date);
    }

    @Override
    public Draw updateDraw(Draw draw) {

        if (draw.isMulti() == draw.isTicket()) {
            throw new IllegalStateException("'multi' or 'ticket' must be different from null and can not be set in one request!");
        }

        if (drawRepository.exists(draw.getDate())) {
            Draw existingDraw = drawRepository.findOne(draw.getDate());
            if (existingDraw.isMulti() && existingDraw.isTicket()) {
                throw new IllegalStateException("calculated draw can not be change!");
            } else if (existingDraw.isMulti() && draw.isTicket()) {
                Set<Integer> multiSet = existingDraw.getMulti();
                Set<Integer> ticketSet = draw.getTicket();

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

            } else if (existingDraw.isTicket() && draw.isMulti()) {

                Set<Integer> multiSet = draw.getMulti();
                Set<Integer> ticketSet = existingDraw.getTicket();

                int[] resultArray = new int[3];
                int i = 0;
                for (Integer ticketValue : ticketSet) {
                    if (multiSet.contains(ticketValue)) {
                        resultArray[i] = 1;
                    }
                    i++;
                }

                existingDraw.updateResult(resultArray);
                existingDraw.setMulti((TreeSet<Integer>) multiSet);

                return drawRepository.save(existingDraw);
            }

        }
        return drawRepository.save(draw);
    }

    @Override
    public Draw getLastDraw() {
        Iterator<Draw> iterator = drawRepository.findFirstByOrderByDateDesc().iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IllegalStateException("Last draw dosen't exist");
    }
}
