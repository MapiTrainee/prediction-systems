package eu.mapidev.predsys.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class MultiMultiDrawTest {

    private MultiMultiDraw draw;

    @Before
    public void setUp() {
	draw = new MultiMultiDraw();
    }

    @Test
    public void shouldBeAbleToSetTicketWithCorrectTicketNumbers() {
	//given
	List<Integer> ticketNumbers = Arrays.asList(10, 20, 30);

	//when
	draw.setTicket(ticketNumbers);

	//then
	assertThat(draw.getTicketAsNumbers()).containsExactlyElementsOf(ticketNumbers);
    }

    @Test
    public void shouldBeAbleToSetDrawWithCorrectDrawNumbers() {
	//given
	List<Integer> drawNumbers = Arrays.asList(
		1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
		71, 72, 73, 74, 75, 76, 77, 78, 79, 80);

	//when
	draw.setDraw(drawNumbers);

	//then
	assertThat(draw.getDrawAsNumbers()).containsExactlyElementsOf(drawNumbers);
    }

    @Test
    public void shouldAllowToSetNull() {
	//given
	List<Integer> drawNumbers = null;
	List<Integer> ticketNumbers = null;

	//when
	draw.setDraw(drawNumbers);
	draw.setTicket(ticketNumbers);

	//then
	assertThat(draw.getDraw()).isNull();
	assertThat(draw.getTicket()).isNull();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenTryToSetDrawToEmptyNumbers() {
	//given
	List<Integer> drawNumbers = Collections.EMPTY_LIST;

	//when
	draw.setDraw(drawNumbers);
    }
    
    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenTryToSetTicketToEmptyNumbers() {
	//given
	List<Integer> ticketNumbers = Collections.EMPTY_LIST;

	//when
	draw.setTicket(ticketNumbers);
    }

}