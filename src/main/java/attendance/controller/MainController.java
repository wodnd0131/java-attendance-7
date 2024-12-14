package attendance.controller;

import static attendance.common.util.DateTimesWrapper.*;
import static attendance.common.validation.InputValidator.*;

import java.util.function.Supplier;

import attendance.common.constant.OutputMessage;
import attendance.domain.stock.Stock;
import attendance.repository.FileRepository;
import attendance.view.interfaces.InputView;
import attendance.view.interfaces.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final FileRepository<Stock> fileRepository;

    public MainController(
        InputView inputView,
        OutputView outputView,
        FileRepository<Stock> fileRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.fileRepository = fileRepository;
    }

    public void run() {
        String input = introduce();
        System.out.println(input);

    }

    private String introduce() {
        outputView.println(OutputMessage.OPENING_MESSAGE, nowToString());
        String input = inputView.readLine();
        if (input.equals("Q")) {
            return input;
        }
        validateInputRange(input);
        validateInput(input);
        return input;
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
