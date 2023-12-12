package br.com.felipec91.domain.model.concept;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public class DomainEntity<ID> {
    @Id
    private ID id;

    public ID getId() {
        return id;
    }

    public DomainEntity(ID id) {
        this.id = id;
    }

    public DomainEntity() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity<?> that = (DomainEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
