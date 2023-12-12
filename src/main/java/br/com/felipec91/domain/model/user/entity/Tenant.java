package br.com.felipec91.domain.model.user.entity;

import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.user.value_object.RoleModel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Set;
import java.util.UUID;

@Entity
@DiscriminatorValue("TENANT")
public class Tenant extends UserModel {

    private Tenant(UUID id, String nome, String email, RoleModel role, Set<GroupModel> groups) {
        super(id,nome,email,role, groups);
    }

    public Tenant(){}

    public static Tenant create(UUID id, String nome, String email) {
        return  new Tenant(
                id,
                nome,
                email,
                RoleModel.TENANT,
                null
        );
    }
}
