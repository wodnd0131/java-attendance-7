package attendance.view.impl;

import camp.nextstep.edu.missionutils.Console;
import attendance.view.interfaces.InputView;

public class ConsoleInputView implements InputView {
    @Override
    public String readLine() {
        return Console.readLine();
    }

    @Override
    public void close() {
        Console.close();
    }
}
