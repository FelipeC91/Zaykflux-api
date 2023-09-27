package br.com.felipec91.infrastructure.web.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AppointmentInputDTO{

    private final OffsetDateTime appointmentDate;
    private final OffsetTime beginningAt;
    private final OffsetTime endingAt;
    private final String description;
    private final UUID accountableId;

    @Setter
    private UUID ticketId;
    }
