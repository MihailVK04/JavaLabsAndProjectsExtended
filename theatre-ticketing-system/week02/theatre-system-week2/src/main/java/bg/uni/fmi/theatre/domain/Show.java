package bg.uni.fmi.theatre.domain;

import bg.uni.fmi.theatre.vo.AgeRating;
import bg.uni.fmi.theatre.vo.Genre;

import java.util.Objects;

public class Show {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int ZERO = 0;
    private final Long id;
    private String title;
    private String description;
    private Genre genre;
    private int durationMinutes;
    private AgeRating ageRating;

    public Show(Long id, String title, String description, Genre genre, int durationMinutes, AgeRating ageRating) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required!");
        } else if (title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("Title must be no more that 100 characters long!");
        } else if (durationMinutes <= ZERO) {
            throw new IllegalArgumentException("Show duration in minutes couldn't be less than zero!");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.ageRating = ageRating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return Objects.equals(id, show.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Show{" + "id=" + id + ", title='" + title + '\'' + ", genre=" + genre +
            ", duration=" + durationMinutes + '}';
    }
}
