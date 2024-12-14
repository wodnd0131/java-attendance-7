package attendance.view.interfaces;

import attendance.common.constant.OutputMessage;

public interface OutputView {
    void println(String string);

    void println(OutputMessage message, Object... args);
}
