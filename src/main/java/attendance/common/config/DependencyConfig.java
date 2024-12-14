package attendance.common.config;

import attendance.controller.MainController;
import attendance.domain.stock.Stock;
import attendance.repository.FileRepository;
import attendance.repository.StockRepository;
import attendance.view.impl.ConsoleInputView;
import attendance.view.impl.ConsoleOutputView;
import attendance.view.interfaces.InputView;
import attendance.view.interfaces.OutputView;

public final class DependencyConfig {

    public MainController mainController() {
        return new MainController(
            inputView(),
            outputView(),
            fileRepository()
        );
    }

    private InputView inputView() {
        return new ConsoleInputView();
    }

    private OutputView outputView() {
        return new ConsoleOutputView();
    }

    private FileRepository<Stock> fileRepository() {
        return new StockRepository();
    }
}