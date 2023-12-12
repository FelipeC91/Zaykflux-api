package br.com.felipec91.domain.model.concept;

import java.util.UUID;

public class AggregateRoot extends DomainEntityUUID {

    public AggregateRoot(){}

    public AggregateRoot(UUID id) {
        super(id);
    }
}
