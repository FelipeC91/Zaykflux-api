package br.com.felipec91.domain.model.user.entity;

import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.concept.AggregateRoot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_model")
public class UserModel extends AggregateRoot {

    private String name;
    private String email;
    private Boolean active;

    @JsonIgnore
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @JsonIgnore
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_model_group_model",
            joinColumns = {@JoinColumn(name = "user_model_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_model_id")}
    )
    private Set<GroupModel> groupModelSet = new HashSet<>();



    public UserModel() {
    }

    public UserModel(UUID id, String name, String email, Boolean active, LocalDate registrationDate, String password, Set<GroupModel> groupModelSet) {
        super(id);
        this.name = name;
        this.email = email;
        this.active = active;
        this.registrationDate = registrationDate;
        this.password = password;
        this.groupModelSet = groupModelSet;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }
}