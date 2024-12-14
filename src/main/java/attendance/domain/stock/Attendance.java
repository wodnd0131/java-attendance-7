package attendance.domain.stock;

import java.time.LocalDate;
import java.time.LocalTime;

public record Attendance(String name, LocalDate date, LocalTime time) {
}
