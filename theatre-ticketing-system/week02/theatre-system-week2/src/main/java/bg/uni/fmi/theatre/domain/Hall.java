package bg.uni.fmi.theatre.domain;

import java.util.Objects;

public class Hall {

    private final Long id;
    private String name;

    public Hall(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required!");
        }
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
        return "Hall{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
