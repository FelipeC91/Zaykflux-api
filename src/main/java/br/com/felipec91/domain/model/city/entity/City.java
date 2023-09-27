package br.com.felipec91.domain.model.city.entity;

import br.com.felipec91.domain.model.concept.DomainEntityUUID;
import br.com.felipec91.domain.model.state.entity.State;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class City extends DomainEntityUUID {


    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;


    public City() {}

    public City(UUID id, String name, State state) {
        super(id);
        this.name = name;
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}