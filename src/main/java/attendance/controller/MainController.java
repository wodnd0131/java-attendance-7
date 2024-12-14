package attendance.controller;

import static attendance.common.constant.ErrorMessages.*;
import static attendance.common.constant.OutputMessage.*;
import static attendance.common.util.DateTimesWrapper.*;
import static attendance.common.validation.DateTimeValidator.*;
import static attendance.common.validation.InputValidator.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import attendance.common.constant.OutputMessage;
import attendance.domain.attendance.Attendance;
import attendance.repository.FileRepository;
import attendance.view.interfaces.InputView;
import attendance.view.interfaces.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    private final List<Attendance> attendances;
    private final String today = nowToString();

    public MainController(
        InputView inputView,
        OutputView outputView,
        FileRepository<Attendance> fileRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        attendances = fileRepository.findAll();
    }

    public void run() {
        while (true) {
            int input = getIntroduceInput();
            if (input == 5) {
                break;
            }
            introduceHandler(input);
        }

    }

    private int getIntroduceInput() {
        outputView.println(OutputMessage.OPENING_MESSAGE, today);
        String input = inputView.readLine();
        if (input.equals("Q")) {
            return 5;
        }
        validateInputRange(input);
        validateInput(input);
        return Integer.parseInt(input);
    }

    private void introduceHandler(int input) {
        if (input == 1) {
            attend();
        }
        if (input == 2) {
            modify();
        }
        if (input == 3) {
            confirmRecord();
        }
        if (input == 4) {
            System.out.println(input);
        }
    }

    private void attend() {
        isHoliday(today);
        isName(OutputMessage.WRITE_NAME);
        LocalTime time = isTime();
        outputView.println(OutputMessage.SHOW_ATTENDANCE_RESULT, today, time.toString(), checkLateness(time));
    }

    private void modify() {
        String name = isName(OutputMessage.MODIFY_NAME);
        String day = isDay();
        LocalDate target = toLocalDate("2024-12-" + day);
        isHoliday(dateToString(target));
        LocalTime time = isTime();

        List<Attendance> targetAttendanceList = attendances.stream()
            .filter(a -> a.name().equals(name))
            .toList();
        Optional<Attendance> targetAttendance = targetAttendanceList.stream()
            .filter(a -> a.date().isEqual(target))
            .findFirst();

        if (targetAttendance.isPresent()) {
            String result = String.format(OutputMessage.MODIFY_SUCCESS.getMessage(), dateToString(target)
                , targetAttendance.get().time().toString()
                , checkLateness(targetAttendance.get().time())
                , time
                , checkLateness(time));
            outputView.println(result);
            return;
        }
        String result = String.format(OutputMessage.MODIFY_SUCCESS.getMessage(),
            dateToString(target)
            , "--:--"
            , "(결석)"
            , time
            , checkLateness(time));
        outputView.println(result);
    }

    private void confirmRecord() {
        String name = isName(OutputMessage.WRITE_NAME);

        List<Attendance> target = attendances.stream()
            .filter(attendance -> attendance.name().equals(name))
            .sorted(Comparator.comparing(Attendance::date))
            .collect(Collectors.toList());

        target.forEach(t -> System.out.println(t.getRecord()));
        Map<String, List<Attendance>> record = target.stream()
            .collect(Collectors.groupingBy(element -> checkLateness(element.time())));
        List<Attendance> def = new ArrayList<>();
        int absence = record.getOrDefault("(결석)", def).size();
        outputView.println("출석: " + record.getOrDefault("(출석)", def).size() + "회\n"
            + "지각: " + record.getOrDefault("(지각)", def).size() + "회\n"
            + "결석: " + absence + "회");
        if (absence > 5) {
            outputView.println("제적 대상자입니다.\n");
            return;
        }
        if (absence > 2) {
            outputView.println("면담 대상자입니다.\n");
            return;
        }
        if (absence > 1) {
            outputView.println("경고 대상자입니다.\n");
        }

    }

    private String isDay() {
        outputView.println(MODIFY_DAY);
        String input = inputView.readLine();
        int day = validateInput(input);
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT.toString());
        }
        return input;
    }

    private void isHoliday(String today) {
        if (today.contains("토") || today.contains("일요") || today.contains("25")) {
            throw new IllegalArgumentException(
                ERROR_PREFIX.getMessage() + TODAY_NOT_OPERATING_HOUR.getMessage(today));
        }
    }

    private String isName(OutputMessage message) {
        outputView.println(message);
        String input = inputView.readLine();
        validateNull(input);
        long i = attendances.stream().filter(x -> x.name().equals(input)).count();
        if (i < 1) {
            throw new IllegalArgumentException(WRONG_NICKNAME.toString());
        }
        return input;
    }

    private LocalTime isTime() {
        outputView.println(WRITE_TIME);
        String inputTime = inputView.readLine();
        validateNull(inputTime);
        try {
            return toLocalTime(inputTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT.toString());
        }
    }
}
