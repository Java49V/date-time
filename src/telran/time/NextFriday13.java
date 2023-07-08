package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.DayOfWeek;
import java.time.LocalDate;



public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		 LocalDate date = LocalDate.from(temporal);

	        if (date.getDayOfMonth() >= 13) {
	            date = date.plusMonths(1);
	        }
	        date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
	        date = date.withDayOfMonth(13);
	        return temporal.with(date);
	}

}
