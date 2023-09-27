package br.com.felipec91.domain.model.group.entity;

import br.com.felipec91.domain.model.concept.DomainEntityUUID;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@Table(name = "permission_model")
public class PermissionModel extends DomainEntityUUID {

    private String name;

    private String description;


    public PermissionModel() {
    }

    public PermissionModel(UUID id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
