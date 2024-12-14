package attendance.repository;

import static attendance.common.constant.ErrorMessages.INVALID_IS_NULL;
import static attendance.common.constant.NumberConstant.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import attendance.common.exception.ResourceNotFoundException;

public abstract class FileRepository<T> {
    private static final String RESOURCE_NOT_FOUND = "Resource not found: %s";

    protected final String resourcePath;

    protected FileRepository(String resourcePath) {
        this.resourcePath = validatePath(resourcePath);
    }

    public abstract List<T> findAll();

    protected List<String> readLines() {
        try (BufferedReader reader = createBufferedReader()) {
            List<String> lines = reader.lines()
                .map(String::trim)
                .collect(Collectors.toList());
            validateLines(lines);
            return Collections.unmodifiableList(lines);
        } catch (IOException e) {
            throw new ResourceNotFoundException(String.format(RESOURCE_NOT_FOUND, resourcePath));
        }
    }

    private BufferedReader createBufferedReader() {
        InputStream resourceStream = getClass().getResourceAsStream(resourcePath);
        validateResource(resourceStream);
        return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
    }

    private void validateResource(InputStream resourceStream) {
        if (resourceStream == null) {
            throw new ResourceNotFoundException(String.format(RESOURCE_NOT_FOUND, resourcePath));
        }
    }

    private String validatePath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_IS_NULL.getMessage());
        }
        return path;
    }

    private void validateLines(List<String> lines) {
        if (isInvalidLines(lines)) {
            throw new IllegalArgumentException(INVALID_IS_NULL.getMessage());
        }
    }

    private boolean isInvalidLines(List<String> lines) {
        return Objects.isNull(lines)
            || lines.isEmpty()
            || lines.size() <= BUSINESS_RULE_INDEX.getValue();
    }
}