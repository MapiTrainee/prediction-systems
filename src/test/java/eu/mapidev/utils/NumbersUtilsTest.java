package eu.mapidev.utils;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;

public class NumbersUtilsTest {

    @Test
    public void shouldConvertDefaultNumbersTextToNumbers() {
	//given
	String inputNumbersText = "1,2,4";

	//when 
	List<Integer> actualNumbers = NumbersUtils.convertTextToNumbers(inputNumbersText);

	//then
	List<Integer> expectedNumbers = Arrays.asList(1, 2, 4);
	assertThat(actualNumbers).containsExactlyElementsOf(expectedNumbers);
    }

    @Test
    public void shouldConvertNumbersTextWithSpecialDelimiter() {
	//given
	String specialDelimiter = ";";
	String inputNumbersText = "3;6;9";

	//when
	List<Integer> actualNumbers = NumbersUtils.convertTextToNumbers(inputNumbersText, specialDelimiter);

	//then
	List<Integer> expectedNumbers = Arrays.asList(3, 6, 9);
	assertThat(actualNumbers).containsExactlyElementsOf(expectedNumbers);
    }
    
    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenCannotParseNumberToInteger(){
	//given 
	String inputNumbersText = "1,2,not a number";
	
	//when
	NumbersUtils.convertTextToNumbers(inputNumbersText);
    }

}
