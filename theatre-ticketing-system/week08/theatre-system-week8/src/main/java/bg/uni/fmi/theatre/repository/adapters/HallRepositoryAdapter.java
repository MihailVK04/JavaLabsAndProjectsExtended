package bg.uni.fmi.theatre.repository.adapters;

import bg.uni.fmi.theatre.domain.Hall;
import bg.uni.fmi.theatre.entity.HallEntity;
import bg.uni.fmi.theatre.repository.HallJpaRepository;
import bg.uni.fmi.theatre.repository.HallRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HallRepositoryAdapter implements HallRepository {

    private final HallJpaRepository hallJpaRepository;

    public HallRepositoryAdapter(HallJpaRepository hallJpaRepository) {
        this.hallJpaRepository = hallJpaRepository;
    }

    @Override
    public Hall save(Hall hall) {
        HallEntity saved = hallJpaRepository.save(HallEntity.from(hall));
        return saved.toHall();
    }

    @Override
    public Optional<Hall> findById(Long id) {
        return hallJpaRepository.findById(id).map(HallEntity::toHall);
    }

    @Override
    public List<Hall> findAll() {
        return hallJpaRepository.findAll().stream()
            .map(HallEntity::toHall)
            .toList();
    }

    @Override
    public void deleteById(Long id) {
        hallJpaRepository.deleteById(id);
    }
}
