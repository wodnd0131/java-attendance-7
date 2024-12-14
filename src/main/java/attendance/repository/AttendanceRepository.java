package attendance.repository;

import static attendance.common.util.DateTimesWrapper.*;

import java.util.List;
import java.util.stream.Collectors;

import attendance.domain.attendance.Attendance;

public class AttendanceRepository extends FileRepository<Attendance> {
    private static final String RESOURCE_STOCK_PATH = "/attendances.csv";

    public AttendanceRepository() {
        super(RESOURCE_STOCK_PATH);
    }

    @Override
    public List<Attendance> findAll() {
        List<String> lines = readLines();
        return lines.stream()
            .map(this::parseAttendance)
            .collect(Collectors.toList());
    }

    private Attendance parseAttendance(String line) {
        String[] parts = line.split(",");
        String[] dateTime = parts[1].split(" ");
        return new Attendance(parts[0], toLocalDate(dateTime[0]), toLocalTime(dateTime[1]));
    }
}

