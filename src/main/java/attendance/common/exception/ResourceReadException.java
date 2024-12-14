package attendance.common.exception;

import static attendance.common.constant.ErrorMessages.RESOURCE_FAILED_READ;

public class ResourceReadException extends RuntimeException {
    public ResourceReadException(String path) {
        super(RESOURCE_FAILED_READ + path);
    }
}
