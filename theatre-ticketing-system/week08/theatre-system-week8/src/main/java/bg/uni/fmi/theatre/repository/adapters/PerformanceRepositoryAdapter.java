package bg.uni.fmi.theatre.repository.adapters;

import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.entity.PerformanceEntity;
import bg.uni.fmi.theatre.repository.PerformanceJpaRepository;
import bg.uni.fmi.theatre.repository.PerformanceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerformanceRepositoryAdapter implements PerformanceRepository {

    private final PerformanceJpaRepository repository;

    public PerformanceRepositoryAdapter(PerformanceJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Performance save(Performance performance) {
        PerformanceEntity saved = repository.save(PerformanceEntity.from(performance));
        return saved.toPerformance();
    }

    @Override
    public Optional<Performance> findById(Long id) {
        return repository.findById(id).map(PerformanceEntity::toPerformance);
    }

    @Override
    public List<Performance> findAll() {
        return repository.findAll().stream()
            .map(PerformanceEntity::toPerformance)
            .toList();
    }

    @Override
    public List<Performance> findByShowId(Long showId) {
        return repository.findByShowId(showId).stream()
            .map(PerformanceEntity::toPerformance)
            .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
