package attendance.view.impl;

import attendance.common.constant.OutputMessage;
import attendance.view.interfaces.OutputView;

public class ConsoleOutputView implements OutputView {
    private static final String lineFeed = "\n";

    @Override
    public void println(String string) {
        System.out.println(lineFeed + string);
    }

    @Override
    public void println(OutputMessage message, Object... args) {
        System.out.printf(message.getMessage(), args);
    }
}