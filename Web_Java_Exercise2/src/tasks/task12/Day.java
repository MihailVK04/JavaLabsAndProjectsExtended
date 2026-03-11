package tasks.task12;

enum Day {
    MONDAY("Mon", false),
    TUESDAY("Tue", false),
    WEDNESDAY("Wed", false),
    THURSDAY("Thu", false),
    FRIDAY("Fri", false),
    SATURDAY("Sat", true),
    SUNDAY("Sun", true);

    private final String abbr;
    private final boolean weekend;

    Day(String abbr, boolean weekend) {
        this.abbr = abbr;
        this.weekend = weekend;
    }

    String abbr() { return abbr; }
    boolean isWeekend() { return weekend; }
}

class Main {
    public static void main(String[] args) {
        for (Day d : Day.values()) {
            String type = d.isWeekend() ? "weekend" : "workday";
            System.out.printf("%s (%s) — %s%n", d, d.abbr(), type);
        }

        Day today = Day.WEDNESDAY;
        String mood = switch (today) {
            case MONDAY    -> "Rough start";
            case FRIDAY    -> "Almost there!";
            case SATURDAY, SUNDAY -> "Relaxing";
            default        -> "Keep going";
        };
        System.out.println("\n" + today + ": " + mood);
    }
}
