package attendance.domain.attendance;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Attendances {
    private List<Attendance> attendanceList;

    public Attendances(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList.stream()
            .sorted(Comparator.comparing(Attendance::date))
            .collect(Collectors.toList());
    }

    public List<Attendance> getAttendanceList(String name) {
        return attendanceList = attendanceList.stream()
            .filter(attendance -> attendance.name().equals(name))
            .collect(Collectors.toList());
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    // 출석 변경 내용 저장
}
