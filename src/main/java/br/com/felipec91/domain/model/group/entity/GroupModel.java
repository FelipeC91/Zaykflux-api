package br.com.felipec91.domain.model.group.entity;

import br.com.felipec91.domain.model.concept.DomainEntityUUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "group_model")
public class GroupModel extends DomainEntityUUID {

    private String name;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_model_permission_model",
            joinColumns = @JoinColumn(name = "goup_model_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_model_id")
    )
    private Set<PermissionModel> permissionModelSet = new HashSet<>();


    public GroupModel() {

    }

    public GroupModel(UUID id, String name, Set<PermissionModel> permissionModelSet) {
        super(id);
        this.name = name;
        this.permissionModelSet = permissionModelSet;
    }

    public String getName() {
        return name;
    }

    public Set<PermissionModel> getPermissionModelSet() {
        return permissionModelSet;
    }
}
