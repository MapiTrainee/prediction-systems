package eu.mapidev.predsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.mapidev.utils.NumbersUtils;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class AbstractDraw {

    @Id
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;

    @Column(name = "draw")
    protected String draw;

    @NotEmpty
    @Column(name = "ticket")
    protected String ticket;

    @Column(name = "result")
    protected String result;

    public AbstractDraw() {
    }

    public AbstractDraw(Date date) {
	this.date = date;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
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
