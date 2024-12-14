package attendance.common.exception;

import static attendance.common.constant.ErrorMessages.RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String path) {
        super(RESOURCE_NOT_FOUND + path);
    }
}
