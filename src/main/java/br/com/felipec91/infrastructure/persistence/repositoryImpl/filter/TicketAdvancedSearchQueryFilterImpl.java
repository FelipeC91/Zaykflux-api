package br.com.felipec91.infrastructure.persistence.repositoryImpl.filter;

import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import br.com.felipec91.domain.repository.filter.TicketAdvancedSearchQueryFilter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

    public class TicketAdvancedSearchQueryFilterImpl implements TicketAdvancedSearchQueryFilter {

    private DateContext dateContext;

    private OffsetDateTime since;

    private OffsetDateTime until;

    private Long number;

    private String title;

    private TicketStatus status;

    private Set<String> customersTradingNameSet = new HashSet<>();

    private String requesterName;

    private Set<String> serviceDeskNames = new HashSet<>();

    private String serviceCatalog;

    private String catalogArea;

    private String catalogItem;


    public TicketAdvancedSearchQueryFilterImpl() {}
    public TicketAdvancedSearchQueryFilterImpl(DateContext dateParam, OffsetDateTime since, OffsetDateTime until, Long number, String title, TicketStatus status, Set<String> customersTradingNameSet, String requesterName, Set<String> serviceDeskNames, String serviceCatalog, String catalogArea, String catalogItem) {
        this.dateContext = dateParam;
        this.since = since;
        this.until = until;
        this.number = number;
        this.title = title;
        this.status = status;
        this.customersTradingNameSet = customersTradingNameSet;
        this.requesterName = requesterName;
        this.serviceDeskNames = serviceDeskNames;
        this.serviceCatalog = serviceCatalog;
        this.catalogArea = catalogArea;
        this.catalogItem = catalogItem;
    }

    public DateContext getDateContext() {
        return this.dateContext;
    }

    public OffsetDateTime getSince() {
        return since;
    }

    public Long getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Set<String> getCustomersTradingNameSet() {
        return customersTradingNameSet;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public Set<String> getServiceDeskNames() {
        return serviceDeskNames;
    }

    public OffsetDateTime getUntil() {
        return until;
    }

    public String getServiceCatalog() {
        return serviceCatalog;
    }

    public String getCatalogArea() {
        return catalogArea;
    }

    public String getCatalogItem() { return catalogItem; }

}
