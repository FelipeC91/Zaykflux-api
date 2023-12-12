package br.com.felipec91.domain.repository.filter;

import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

public interface TicketAdvancedSearchQueryFilter {

    enum DateContext {
        ABERTURA,
        FECHAMENTO
    }

    DateContext getDateContext();
    OffsetDateTime getSince();
    OffsetDateTime getUntil();
    Long getNumber();
    String getTitle();
    TicketStatus getStatus();
    Set<String> getCustomersTradingNameSet();
    String getRequesterName();
    Set<String> getServiceDeskNames();
    String getServiceCatalog();
    String getCatalogArea();
    String getCatalogItem();


    default OffsetDateTime getOrGenerateUntil() {
        return isUntilDateValid() ? getUntil() : OffsetDateTime.now() ;
    }

    default Boolean isUntilDateValid () {
        return Objects.nonNull( getUntil() ) && getUntil().isEqual( getSince() ) || getUntil().isAfter(getSince() );
    }

    default boolean hasAValidDateParameter() {
        return Objects.nonNull( this.getDateContext() ) && Objects.nonNull( getSince() );
    }

    default boolean hasANumber() {
        return Objects.nonNull( getNumber() );
    }

    default boolean hasATitle() {
        return Objects.nonNull( getTitle() );
    }

    default boolean hasAStatus() {
        return Objects.nonNull( getStatus() );
    }

    default boolean hasAnyCustomer() {
        return getCustomersTradingNameSet().size() > 1;
    }

    default boolean hasRequester() {
        return Objects.nonNull( getRequesterName() );
    }

    default boolean hasAnyServiceDesk() {
        return getServiceDeskNames().size() > 1;
    }
}
