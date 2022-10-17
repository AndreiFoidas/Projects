package ro.ubb.teameugen.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.teameugen.common.Domain.Entities.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface IRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
}
