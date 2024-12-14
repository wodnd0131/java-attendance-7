package attendance.controller;

import static attendance.common.constant.ErrorMessages.*;
import static attendance.common.constant.OutputMessage.*;
import static attendance.common.util.DateTimesWrapper.*;
import static attendance.common.validation.DateTimeValidator.*;
import static attendance.common.validation.InputValidator.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import attendance.common.constant.OutputMessage;
import attendance.domain.stock.Attendance;
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
        int input = getIntroduceInput();
        introduceHandler(input);
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

    // TODO. 1. 출석 확인
    private void introduceHandler(int input) {
        if (input == 1) {
            attend();
        }
        if (input == 2) {
            modify();
        }
        if (input == 3) {
            System.out.println(input);
        }
        if (input == 4) {
            System.out.println(input);
        }
    }

    private void attend() {
        // isHoliday();
        isName(OutputMessage.WRITE_NAME);
        LocalTime time = isTime();
        outputView.println(OutputMessage.SHOW_ATTENDANCE_RESULT, today, time.toString(), "출석");
        //ToDo. 운영 시간 검증
    }

    private void modify() {
        String name = isName(OutputMessage.MODIFY_NAME);
        String day = isDay();
        LocalTime time = isTime();
        LocalDate target = toLocalDate("2024-12-" + day);
        List<Attendance> targetAttendanceList = attendances.stream()
            .filter(a -> a.name().equals(name))
            .toList();
        Optional<Attendance> targetAttendance = targetAttendanceList.stream()
            .filter(a -> a.date().isEqual(target))
            .findFirst();

        if (targetAttendance.isPresent()) {
            checkLateness(targetAttendance.get().time());
            outputView.println(OutputMessage.MODIFY_SUCCESS, dateToString(target)
                , targetAttendance.get().time().toString()
                , checkLateness(targetAttendance.get().time())
                , time.toString()
                , checkLateness(time)
            );
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

    private void isHoliday() {
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
    // private <T> T handleReEnter(Supplier<T> inputSupplier) {
    //     try {
    //         return inputSupplier.get();
    //     } catch (IllegalArgumentException | NullPointerException e) {
    //         outputView.println(e.getMessage());
    //         return handleReEnter(inputSupplier);
    //     }
    // }
}
