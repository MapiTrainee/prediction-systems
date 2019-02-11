package eu.mapidev.predsys.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "minilotto")
public class MiniLottoDraw extends AbstractDraw {

    @Override
    public void setDraw(List<Integer> drawNumbers) {
	final int numbersSize = 5;
	final int minNumberValue = 1;
	final int maxNumberValue = 42;
	setDraw(drawNumbers, numbersSize, minNumberValue, maxNumberValue);
    }

    @Override
    public void setTicket(List<Integer> ticketNumbers) {
	final int numbersSize = 5;
	final int minNumberValue = 1;
	final int maxNumberValue = 42;
	setTicket(ticketNumbers, numbersSize, minNumberValue, maxNumberValue);
    }

}
