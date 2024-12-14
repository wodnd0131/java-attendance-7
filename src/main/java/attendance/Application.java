package attendance;

import attendance.common.config.DependencyConfig;
import attendance.controller.MainController;

public class Application {
    public static void main(String[] args) {
        DependencyConfig config = new DependencyConfig();
        MainController controller = config.mainController();
        controller.run();
    }
}

