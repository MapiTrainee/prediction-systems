package eu.mapidev.predsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.mapidev.utils.NumbersUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;
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

    public AbstractDraw(LocalDateTime localDateTime) {
	this.date = localDateTime;
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

    public abstract void setDraw(TreeSet<Integer> drawNumbers);

    public abstract void setTicket(TreeSet<Integer> ticketNumbers);

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
