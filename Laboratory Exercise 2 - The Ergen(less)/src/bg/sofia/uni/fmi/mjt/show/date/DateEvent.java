package bg.sofia.uni.fmi.mjt.show.date;

public record DateEvent(String location, int tensionLevel, int duration) {
    private static final int TENSION_LEVEL_MIN = 0;
    private static final int TENSION_LEVEL_MAX = 10;

    public DateEvent(String location, int tensionLevel, int duration) {
        this.location = location;
        this.duration = duration;
        this.tensionLevel = Math.clamp(tensionLevel, TENSION_LEVEL_MIN, TENSION_LEVEL_MAX);
    }
}