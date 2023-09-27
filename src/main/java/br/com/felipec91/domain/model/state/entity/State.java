package br.com.felipec91.domain.model.state.entity;

import br.com.felipec91.domain.model.concept.DomainEntity;
import br.com.felipec91.domain.model.concept.DomainEntityUUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.validator.constraints.Length;

@Entity
public class State extends DomainEntity<String> {

    private String name;

    public State(){}

    public State(@Length(max = 2, min = 2) String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
