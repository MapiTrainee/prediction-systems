package eu.mapidev.utils;

import java.util.Arrays;
import java.util.Collections;
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
    public void shouldConvertNumbersTextWithSpecialDelimiterToNumbers() {
	//given
	String inputNumbersText = "3;6;9";
	String specialDelimiter = ";";

	//when
	List<Integer> actualNumbers = NumbersUtils.convertTextToNumbers(inputNumbersText, specialDelimiter);

	//then
	List<Integer> expectedNumbers = Arrays.asList(3, 6, 9);
	assertThat(actualNumbers).containsExactlyElementsOf(expectedNumbers);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenCannotParseNumberToInteger() {
	//given 
	String inputNumbersText = "1,2,not a number";

	//when
	NumbersUtils.convertTextToNumbers(inputNumbersText);
    }

    @Test
    public void shouldConvertNullTextToEmptyNumberList() {
	//when
	List<Integer> actualNumbers = NumbersUtils.convertTextToNumbers(null);

	//then
	assertThat(actualNumbers).isEmpty();
    }

    @Test
    public void shouldConvertEmptyNumberListToEmptyString() {
	//given
	List<Integer> emptyNumbers = Collections.EMPTY_LIST;

	//when
	String actualText = NumbersUtils.convertNumbersToText(emptyNumbers);

	//then
	assertThat(actualText).isEmpty();
    }

    @Test
    public void shouldConvertNullNumberListToNull() {
	//given
	List<Integer> nullNumbers = null;

	//when
	String actualText = NumbersUtils.convertNumbersToText(nullNumbers);

	//then
	assertThat(actualText).isNull();
    }

    @Test
    public void shouldConvertNumbersToText() {
	//given
	List<Integer> numbers = Arrays.asList(1, 10, 100);

	//when
	String actualText = NumbersUtils.convertNumbersToText(numbers);

	//then
	assertThat(actualText).isEqualTo("1,10,100");
    }

    @Test
    public void shouldConvertNumbersWithSepecialDelimiterToText() {
	//given
	List<Integer> numbers = Arrays.asList(3, 6, 9);
	String specialDelimiter = "_";

	//when
	String actualText = NumbersUtils.convertNumbersToText(numbers, specialDelimiter);

	//then
	assertThat(actualText).isEqualTo(
		String.format("%d%s%d%s%d", 3, specialDelimiter, 6, specialDelimiter, 9));
    }

}
