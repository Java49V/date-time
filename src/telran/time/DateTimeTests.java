package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.UnsupportedTemporalTypeException;
class DateTimeTests {

	@Test
	void test() {
		
		LocalDate birthAS = LocalDate.of(1799, 6, 6);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY EEEE");
		
		System.out.println(birthAS.format(dtf));
		LocalDate barMizva =  birthAS.plusYears(13);
		assertEquals(barMizva, birthAS.with(new BarMizvaAdjuster()));
		assertThrowsExactly(UnsupportedTemporalTypeException.class,
				() -> LocalTime.now().with(new BarMizvaAdjuster()));
		
	}
	@Test
	void nextFriday13Test() {
		TemporalAdjuster fr13 = new NextFriday13();
		ZonedDateTime zdt = ZonedDateTime.now();
		ZonedDateTime fr13Expected = ZonedDateTime.of(2023, 10, 13, 0, 0, 0, 0, ZoneId.systemDefault());
		assertEquals(fr13Expected.toLocalDate(), zdt.with(fr13).toLocalDate());
//		assertEquals(fr13Expected, zdt.with(fr13));
		LocalDate fr13Expected2 = LocalDate.of(2024, 9, 13);
		LocalDate ld = LocalDate.of(2023, 10, 13);
		assertEquals(fr13Expected2, ld.with(fr13));
		assertThrowsExactly(UnsupportedTemporalTypeException.class,
				() -> LocalTime.now().with(new NextFriday13()));
	}
	@Test
	void canadaCurrentTime() {
		//displayCurrentTime("Europe/London");
		//TODO display current date & time in all time zones related to Canada
		//Date / Time (HH:mm) / Time Zone name
		displayCurrentTime("canada");
		displayCurrentTime1("canada");
	}
	private void displayCurrentTime(String zoneName) {
		ZonedDateTime ztd = ZonedDateTime.now();
		for(String zone: ZoneId.getAvailableZoneIds()) {			
			if (zone.toLowerCase().contains(zoneName)) {
				displayCurrentTime(ztd.withZoneSameInstant(ZoneId.of(zone)));
			}
		}
	}
	private void displayCurrentTime1(String zoneName) {
		ZonedDateTime zdt = ZonedDateTime.now();
		ZoneId.getAvailableZoneIds().stream()
				.filter(zone -> zone.toLowerCase().contains(zoneName))
				.map(zone -> zdt.withZoneSameInstant(ZoneId.of(zone)))
				.forEach(dt -> displayCurrentTime(dt));				
	}
	private void displayCurrentTime(ZonedDateTime zdt) {
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm / z");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm / VV");
		System.out.println(zdt.format(dtf1));
		System.out.println(zdt.format(dtf2));
}

}
//package telran.time;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.TemporalAdjuster;
//import java.time.temporal.UnsupportedTemporalTypeException;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//class DateTimeTests {
//
//	@Test
//	void test() {
//		
//		LocalDate birthAS = LocalDate.of(1799, 6, 6);
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY EEEE");
//		
//		System.out.println(birthAS.format(dtf));
//		LocalDate barMizva =  birthAS.plusYears(13);
//		assertEquals(barMizva, birthAS.with(new BarMizvaAdjuster()));
//		assertThrowsExactly(UnsupportedTemporalTypeException.class,
//				() -> LocalTime.now().with(new BarMizvaAdjuster()));
//		
//	}
//	@Test
//	void nextFriday13Test() {
//		TemporalAdjuster fr13 = new NextFriday13();
//		ZonedDateTime zdt = ZonedDateTime.now();
//		ZonedDateTime fr13Expected = ZonedDateTime.of(2023, 10, 13, 0, 0, 0, 0, ZoneId.systemDefault());
//		assertEquals(fr13Expected, zdt.with(fr13));
//		LocalDate fr13Expected2 = LocalDate.of(2024, 9, 13);
//		LocalDate ld = LocalDate.of(2023, 10, 13);
//		assertEquals(fr13Expected2, ld.with(fr13));
//		
//	}
//	@Test
//    void canadaCurrentTime() {
//        displayCurrentTimeInAllTimeZones("Canada");
//    }
//
//    void displayCurrentTimeInAllTimeZones(String region) {
//        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");
//        
//        for (String zoneId : zoneIds) {
//            ZoneId zone = ZoneId.of(zoneId);
//            if (zone.getRules().getTransitions().isEmpty() && zone.getId().startsWith(region)) {
//                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), zone);
//                String formattedDateTime = zonedDateTime.format(formatter);
//                System.out.println(formattedDateTime + " / " + zoneId);
//            }
//        }
//    }
////	@Test
////	void canadaCurrentTime() {
////		//displayCurrentTime("Europe/London");
////		//TODO display current date & time in all time zones related to Canada
////		//Date / Time (HH:mm) / Time Zone name
////	}
////	void displayCurrentTime(String zoneName) {
//////		ZoneId.getAvailableZoneIds()
//////		.forEach(System.out::println);
////		System.out.println(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(zoneName)));
////	}
//
//}


