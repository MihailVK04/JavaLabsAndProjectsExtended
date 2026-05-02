package bg.uni.fmi.theatre.entity;

import bg.uni.fmi.theatre.domain.Hall;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hall")
public class HallEntity {

    private static final int NAME_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NAME_LENGTH)
    private String name;

    protected HallEntity() {}

    public static HallEntity from(Hall hall) {
        HallEntity e = new HallEntity();
        e.id = hall.getId();
        e.name = hall.getName();
        return e;
    }

    public Hall toHall() {
        return new Hall(id, name);
    }
}
