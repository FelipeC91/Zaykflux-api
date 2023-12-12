package br.com.felipec91.application.exception_handler;

import jakarta.ws.rs.core.Response;

import java.time.OffsetDateTime;

public record ApiErrorRecord(String path, int responseStatusCode, Response.Status responseStatus, OffsetDateTime date, String error, Object errorDetails) {
}
