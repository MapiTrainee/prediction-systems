package eu.mapidev.predsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.Table;
import eu.mapidev.utils.NumbersUtils;

@Entity
@Table(name = "multimulti")
public class MultiMultiDraw extends AbstractDraw implements Serializable {

    public MultiMultiDraw() {
	super();
    }

    public MultiMultiDraw(Date date) {
	super(date);
    }

    @Override
    public void setDraw(TreeSet<Integer> drawNumbers) {
	if (drawNumbers != null) {
	    if (drawNumbers.size() == 20) {
		for (int s : drawNumbers) {
		    if (s < 1 || s > 80) {
			throw new IllegalStateException("Each element of 'draw' array must be between 1-80!");
		    }
		}
		this.draw = NumbersUtils.convertSetToString(drawNumbers);
	    } else {
		throw new IllegalStateException("Length of 'draw' [unique] array must be equal 20!");
	    }
	} else {
	    this.draw = null;
	}
    }

    @Override
    public void setTicket(TreeSet<Integer> ticketNumbers) {
	if (ticketNumbers != null) {
	    if (ticketNumbers.size() == 3) {
		for (int s : ticketNumbers) {
		    if (s < 1 || s > 80) {
			throw new IllegalStateException("Each element of 'ticket' array must be between 1-80!");
		    }
		}
		this.ticket = NumbersUtils.convertSetToString(ticketNumbers);
	    } else {
		throw new IllegalStateException("Length of 'ticket' [unique] array must be equal 3!");
	    }
	} else {
	    this.ticket = null;
	}
    }

    @JsonIgnore
    public void updateResult(int[] array) {
	this.result = NumbersUtils.convertArrayToString(array);
    }

    public void setResult(Integer[] array) {
	throw new IllegalStateException("'result' can not be set!");
    }

}
