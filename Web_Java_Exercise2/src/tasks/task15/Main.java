package tasks.task15;

class Main {
    sealed interface Notification permits Email, SMS, Push {}
    record Email(String from, String subject) implements Notification {}
    record SMS(String phone, String message) implements Notification {}
    record Push(String app, String title) implements Notification {}

    static void handle(Notification n) {
        // Pattern matching — no explicit cast needed
        if (n instanceof Email e) {
            System.out.println("Email from " + e.from() + ": " + e.subject());
        } else if (n instanceof SMS s) {
            System.out.println("SMS from " + s.phone() + ": " + s.message());
        } else if (n instanceof Push p) {
            System.out.println("Push [" + p.app() + "]: " + p.title());
        }
    }

    // Even cleaner with switch (Java 21+)
    static String summarize(Notification n) {
        return switch (n) {
            case Email e -> "📧 " + e.subject();
            case SMS s   -> "💬 " + s.message();
            case Push p  -> "🔔 " + p.title();
        };
    }

    public static void main(String[] args) {
        Notification[] inbox = {
            new Email("alice@mail.com", "Meeting at 3pm"),
            new SMS("+359888123456", "On my way!"),
            new Push("Slack", "New message in #general")
        };

        for (Notification n : inbox) handle(n);
        System.out.println();
        for (Notification n : inbox) System.out.println(summarize(n));
    }
}
