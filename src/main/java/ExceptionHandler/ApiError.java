package ExceptionHandler;

import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

public record ApiError(String path, int responseStatusCode, Response.Status responseStatus, OffsetDateTime date, String title, Object developerMessage) {
}
