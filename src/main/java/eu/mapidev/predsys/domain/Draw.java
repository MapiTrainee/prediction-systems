package eu.mapidev.predsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import eu.mapidev.utils.DrawUtils;

@Entity
@Table(name = "draw")
@NamedQueries({
    @NamedQuery(name = "Draw.findAll", query = "SELECT d FROM Draw d")})
public class Draw implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "multi")
    private String multi;
    @Column(name = "ticket")
    private String ticket;
    @Column(name = "result")
    private String result;

    public Draw() {
    }

    public Draw(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Integer> getMulti() {
        return DrawUtils.convertStringToSet(multi);
    }

    public void setMulti(TreeSet<Integer> set) {
        if (set != null) {
            if (set.size() == 20) {
                for (int s : set) {
                    if (s < 1 || s > 80) {
                        throw new IllegalStateException("Each element of 'multi' array must be between 1-80!");
                    }
                }
                this.multi = DrawUtils.convertSetToString(set);
            } else {
                throw new IllegalStateException("Length of 'multi' [unique] array must be equal 20!");
            }
        } else {
            this.multi = null;
        }
    }

    public Set<Integer> getTicket() {
        return DrawUtils.convertStringToSet(ticket);
    }

    public void setTicket(TreeSet<Integer> set) {
        if (set != null) {
            if (set.size() == 3) {
                for (int s : set) {
                    if (s < 1 || s > 80) {
                        throw new IllegalStateException("Each element of 'ticket' array must be between 1-80!");
                    }
                }
                this.ticket = DrawUtils.convertSetToString(set);
            } else {
                throw new IllegalStateException("Length of 'ticket' [unique] array must be equal 3!");
            }
        } else {
            this.ticket = null;
        }
    }

    public int[] getResult() {
        return DrawUtils.convertStringToArray(result);
    }

    public boolean isMulti() {
        return multi != null;
    }

    public boolean isTicket() {
        return ticket != null;
    }

    @JsonIgnore
    public void updateResult(int[] array) {
        this.result = DrawUtils.convertArrayToString(array);
    }

    public void setResult(Integer[] array) {
        throw new IllegalStateException("'result' can not be set!");

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Draw)) {
            return false;
        }
        Draw other = (Draw) object;
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xyz.pietryga.test.Draw[ date=" + date + " ]";
    }

}
