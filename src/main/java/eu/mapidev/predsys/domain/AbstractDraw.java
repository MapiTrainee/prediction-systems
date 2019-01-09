package eu.mapidev.predsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.mapidev.utils.NumbersUtils;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "date", nullable = false)
    protected LocalDateTime date;

    @Column(name = "draw")
    protected String draw;

    @Column(name = "ticket", nullable = false)
    protected String ticket;

    @Column(name = "result")
    protected String result;

    public AbstractDraw() {
    }

    public AbstractDraw(Long id, LocalDateTime localDateTime, String ticket) {
	this.id = id;
	this.date = localDateTime;
	this.ticket = ticket;
    }

    @JsonProperty("ticket")
    public List<Integer> getTicketAsNumbers() {
	return NumbersUtils.convertTextToNumbers(ticket);
    }

    @JsonProperty("result")
    public List<Integer> getResultAsNumbers() {
	return NumbersUtils.convertTextToNumbers(result);
    }

    @JsonProperty("draw")
    public List<Integer> getDrawAsNumbers() {
	return NumbersUtils.convertTextToNumbers(draw);
    }

    public abstract void setDraw(List<Integer> drawNumbers);

    protected void setDraw(List<Integer> drawNumbers, final int numbersSize, final int minNumberValue, final int maxNumberValue) {
	if (numbersNotNull(drawNumbers)
		&& numbersHaveSize(drawNumbers, numbersSize)
		&& numbersAreInRange(drawNumbers, minNumberValue, maxNumberValue)
		&& numbersAreUnique(drawNumbers)) {
	    Collections.sort(drawNumbers);
	    draw = NumbersUtils.convertNumbersToText(drawNumbers);
	} else {
	    draw = null;
	}
    }

    public abstract void setTicket(List<Integer> ticketNumbers);

    protected void setTicket(List<Integer> ticketNumbers, final int numbersSize, final int minNumberValue, final int maxNumberValue) {
	if (numbersNotNull(ticketNumbers)
		&& numbersHaveSize(ticketNumbers, numbersSize)
		&& numbersAreInRange(ticketNumbers, minNumberValue, maxNumberValue)
		&& numbersAreUnique(ticketNumbers)) {
	    Collections.sort(ticketNumbers);
	    ticket = NumbersUtils.convertNumbersToText(ticketNumbers);
	} else {
	    ticket = null;
	}
    }

    private boolean numbersNotNull(Collection<Integer> numbers) {
	return numbers != null;
    }

    private boolean numbersHaveSize(Collection<Integer> numbers, final int numbersSize) {
	if (numbers.size() != numbersSize) {
	    throw new IllegalStateException(
		    String.format("Size of numbers must be equal %d, but this time was %d",
			    numbersSize, numbers.size()));
	}
	return true;
    }

    private boolean numbersAreInRange(Collection<Integer> numbers, final int minNumberValue, final int maxNumberValue) {
	for (int number : numbers) {
	    if (number < minNumberValue || number > maxNumberValue) {
		throw new IllegalStateException(
			String.format("Each element of numbers must be in range [%d-%d], but this time was %d!",
				minNumberValue, maxNumberValue, number));
	    }
	}
	return true;
    }

    private boolean numbersAreUnique(Collection<Integer> numbers) {
	Set<Integer> uniqueNumbers = new HashSet<>(numbers);
	if (numbers.size() != uniqueNumbers.size()) {
	    throw new IllegalStateException("Each element must be unique!");
	}
	return true;
    }

    public LocalDateTime getDate() {
	return date;
    }

    public void setDate(LocalDateTime localDateTime) {
	this.date = localDateTime;
    }

    @JsonIgnore
    public String getDraw() {
	return draw;
    }

    public void setDraw(String draw) {
	this.draw = draw;
    }

    @JsonIgnore
    public String getTicket() {
	return ticket;
    }

    public void setTicket(String ticket) {
	this.ticket = ticket;
    }

    @JsonIgnore
    public String getResult() {
	return result;
    }

    public void setResult(String result) {
	this.result = result;
    }

}
