package bg.uni.fmi.theatre.repository;

import bg.uni.fmi.theatre.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallJpaRepository extends JpaRepository<HallEntity, Long> {
}
