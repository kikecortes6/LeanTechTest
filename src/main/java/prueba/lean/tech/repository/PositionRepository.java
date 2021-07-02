package prueba.lean.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.lean.tech.entitys.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
