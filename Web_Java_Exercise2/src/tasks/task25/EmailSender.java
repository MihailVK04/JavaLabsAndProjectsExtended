package tasks.task25;

// Abstraction
interface MessageSender {
    void send(String message);
}

// Implementations
class EmailSender implements MessageSender {
    public void send(String message) {
        System.out.println("📧 Email: " + message);
    }
}

class SmsSender implements MessageSender {
    public void send(String message) {
        System.out.println("💬 SMS: " + message);
    }
}

class SlackSender implements MessageSender {
    public void send(String message) {
        System.out.println("🔔 Slack: " + message);
    }
}

// High-level module depends on ABSTRACTION, not concrete class
class NotificationService {
    private final MessageSender sender; // injected dependency

    NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    void notifyUser(String message) {
        sender.send(message);
    }
}

class Main {
    public static void main(String[] args) {
        // Easy to swap implementations!
        NotificationService emailNotifier = new NotificationService(new EmailSender());
        NotificationService smsNotifier   = new NotificationService(new SmsSender());
        NotificationService slackNotifier = new NotificationService(new SlackSender());

        emailNotifier.notifyUser("Your order shipped!");
        smsNotifier.notifyUser("Your code is 4829");
        slackNotifier.notifyUser("Deploy completed");
    }
}
