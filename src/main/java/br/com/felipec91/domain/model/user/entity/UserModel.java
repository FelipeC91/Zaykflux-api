package br.com.felipec91.domain.model.user.entity;

import br.com.felipec91.domain.model.concept.AggregateRoot;
import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.user.value_object.RoleModel;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_model")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_model", discriminatorType = DiscriminatorType.STRING)
public abstract class UserModel extends AggregateRoot {

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_model", insertable=false, updatable=false)
    private RoleModel roleModel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_model_group_model",
            joinColumns = {@JoinColumn(name = "user_model_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_model_id")}
    )
    private Set<GroupModel> groupModelSet;




    public UserModel() {
    }

    protected UserModel(UUID id, String name, String email, RoleModel roleModel, Set<GroupModel> groupModelSet) {
        super(id);
        this.name = name;
        this.email = email;
        this.roleModel = roleModel;
        this.groupModelSet = groupModelSet;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    protected Set<GroupModel> getGroupModelSet() {
        return this.groupModelSet;
    }
}