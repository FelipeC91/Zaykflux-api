package br.com.felipec91.infrastructure.db.repositoryImpl;

import br.com.felipec91.domain.model.user.entity.UserModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserModelRepositoryImpl implements PanacheRepositoryBase<UserModel, UUID> {
}
