package br.com.felipec91.domain.model.user.entity;

import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.user.value_object.RoleModel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Entity
@DiscriminatorValue("ATTENDANT")
public class Attendant extends UserModel {

    public Attendant() {
    }

    private Attendant(UUID id, String nome, String email, RoleModel role, Set<GroupModel> groups) {
      super(id,nome,email,role, groups);
    }

    public static Attendant create(UUID id, String nome, String email, Set<GroupModel> groups) {
        return  new Attendant(
                    id,
                    nome,
                    email,
                    RoleModel.ATTENDANT,
                    groups
            );

    }

    public Set<GroupModel> getGroupModelSet() {
        return Collections.unmodifiableSet(super.getGroupModelSet());
    }
}
