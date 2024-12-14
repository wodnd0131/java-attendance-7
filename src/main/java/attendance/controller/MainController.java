package attendance.controller;

import static attendance.common.util.DateTimesWrapper.*;
import static attendance.common.validation.InputValidator.*;

import java.util.List;

import attendance.common.constant.OutputMessage;
import attendance.domain.stock.Attendance;
import attendance.repository.FileRepository;
import attendance.view.interfaces.InputView;
import attendance.view.interfaces.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    private final List<Attendance> attendances;

    public MainController(
        InputView inputView,
        OutputView outputView,
        FileRepository<Attendance> fileRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        attendances = fileRepository.findAll();
    }

    public void run() {
        attendances.forEach(System.out::println);
        int input = getIntroduceInput();
        introduceHandler(input);
        // TODO. 1. 출석 확인

    }

    private int getIntroduceInput() {
        outputView.println(OutputMessage.OPENING_MESSAGE, nowToString());
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
            System.out.println(input);
        }
        if (input == 2) {
            System.out.println(input);
        }
        if (input == 3) {
            System.out.println(input);
        }
        if (input == 4) {
            System.out.println(input);
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
