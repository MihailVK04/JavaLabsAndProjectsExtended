package bg.uni.fmi.theatre.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "hall")
public class Hall {

    private static final int MAX_NAME_LENGTH = 100;
    private static final int ZERO = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Column(nullable = false)
    private int capacity;

    protected Hall() {

    }

    public Hall(String name, int capacity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required!");
        }
        if (capacity <= ZERO) {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return Objects.equals(id, hall.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Hall{id=" + id + ", name='" + name + "',   capacity=" + capacity + "}";
    }
}
