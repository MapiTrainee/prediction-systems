package eu.mapidev.predsys.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "multimulti")
public class MultiMultiDraw extends AbstractDraw implements Serializable {

    public MultiMultiDraw() {
	super();
    }

    public MultiMultiDraw(LocalDateTime localDateTime, String ticket) {
	super(localDateTime, ticket);
    }

    @Override
    public void setDraw(List<Integer> drawNumbers) {
	final int numbersSize = 20;
	final int minNumberValue = 1;
	final int maxNumberValue = 80;
	setDraw(drawNumbers, numbersSize, minNumberValue, maxNumberValue);
    }

    @Override
    public void setTicket(List<Integer> ticketNumbers) {
	final int numbersSize = 3;
	final int minNumberValue = 1;
	final int maxNumberValue = 80;
	setTicket(ticketNumbers, numbersSize, minNumberValue, maxNumberValue);
    }

}
