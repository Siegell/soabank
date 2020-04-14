package by.siegell.soabank.service;

import by.siegell.soabank.domain.exception.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public abstract class EntityCRUDService<Entity, Id, Repository extends JpaRepository<Entity, Id>> {

    abstract Repository getRepository();

    public Entity getById(Id id) throws EntityNotFoundException {
        Optional<Entity> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Entity save(Entity entity) {
        return getRepository().save(entity);
    }

    public void deleteById(Id id) {
        getRepository().deleteById(id);
    }

    public List<Entity> getAll() {
        return getRepository().findAll();
    }

    public void deleteAll(List<Entity> entities) {
        getRepository().deleteAll(entities);
    }

    public boolean isExists(Id id) {
        return getRepository().existsById(id);
    }
}
