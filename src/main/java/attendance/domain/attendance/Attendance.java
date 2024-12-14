package attendance.domain.attendance;

import static attendance.common.util.DateTimesWrapper.*;
import static attendance.common.validation.DateTimeValidator.*;

import java.time.LocalDate;
import java.time.LocalTime;

import attendance.common.constant.OutputMessage;

public record Attendance(String name, LocalDate date, LocalTime time) {

    public String getRecord() {
        return String.format(OutputMessage.RECORD.getMessage(), dateToString(date), time, checkLateness(time));
    }
}
