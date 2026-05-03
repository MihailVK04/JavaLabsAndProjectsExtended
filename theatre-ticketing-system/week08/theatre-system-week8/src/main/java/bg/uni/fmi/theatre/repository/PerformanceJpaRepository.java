package bg.uni.fmi.theatre.repository;

import bg.uni.fmi.theatre.entity.PerformanceEntity;
import bg.uni.fmi.theatre.vo.PerformanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceJpaRepository extends JpaRepository<PerformanceEntity, Long> {

    List<PerformanceEntity> findByShowId(Long showId);

    List<PerformanceEntity> findByHallId(Long hallId);

    List<PerformanceEntity> findByStatus(PerformanceStatus status);
}