package prueba.lean.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.lean.tech.entitys.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
