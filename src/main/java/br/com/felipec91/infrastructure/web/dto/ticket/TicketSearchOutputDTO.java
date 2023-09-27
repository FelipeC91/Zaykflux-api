package br.com.felipec91.infrastructure.web.dto.ticket;

import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketSearchOutputDTO {

    private UUID id;
    private Long number;
    private String title;
    private String customerTradingName;
    private Priority priority;
    private TicketStatus status;
    private String serviceDesk;
    private String requesterName;
    private String requesterEmail;
    private OffsetDateTime openedAt;
    private OffsetDateTime updatedAt;

    public TicketSearchOutputDTO() {
    }

    public TicketSearchOutputDTO(UUID id, Long number, String title, String customerTradingName, Priority priority, TicketStatus status, String serviceDesk, String requesterName, String requesterEmail, OffsetDateTime openedAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.customerTradingName = customerTradingName;
        this.priority = priority;
        this.status = status;
        this.serviceDesk = serviceDesk;
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
        this.openedAt = openedAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public String getServiceDesk() {
        return serviceDesk;
    }

    public void setServiceDesk(String serviceDesk) {
        this.serviceDesk = serviceDesk;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomerTradingName(String customerTradingName) {
        this.customerTradingName = customerTradingName;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public void setOpenedAt(OffsetDateTime openedAt) {
        this.openedAt = openedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

