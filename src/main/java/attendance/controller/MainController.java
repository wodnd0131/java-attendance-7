package attendance.controller;

import java.util.function.Supplier;

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

        // do {
        // } while (userPromptService.confirmContinueShopping());
    }

    // private T receiveShoppingList() {
    //     return handleReEnter(() -> {
    //         return shoppingList;
    //     });
    // }

    private <T> T handleReEnter(Supplier<T> inputSupplier) {
        try {
            return inputSupplier.get();
        } catch (IllegalArgumentException | NullPointerException e) {
            outputView.println(e.getMessage());
            return handleReEnter(inputSupplier);
        }
    }
}
