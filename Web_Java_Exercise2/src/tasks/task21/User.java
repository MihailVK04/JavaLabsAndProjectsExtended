package tasks.task21;

// Each class has ONE responsibility

class User {
    private final String name;
    private final String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName()  { return name; }
    String getEmail() { return email; }
}

class UserValidator {
    boolean isValid(User user) {
        boolean nameOk  = user.getName() != null && !user.getName().isBlank();
        boolean emailOk = user.getEmail() != null && user.getEmail().contains("@");
        return nameOk && emailOk;
    }
}

class UserRepository {
    void save(User user) {
        System.out.println("DB: Saved user '" + user.getName() + "'");
    }
}

class EmailService {
    void sendWelcome(User user) {
        System.out.println("Email: Welcome sent to " + user.getEmail());
    }
}

// Orchestrator ties them together
class UserRegistrationService {
    private final UserValidator validator = new UserValidator();
    private final UserRepository repo = new UserRepository();
    private final EmailService emailService = new EmailService();

    void register(User user) {
        if (!validator.isValid(user)) {
            System.out.println("Invalid user: " + user.getName());
            return;
        }
        repo.save(user);
        emailService.sendWelcome(user);
    }
}

class Main {
    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();
        service.register(new User("Alice", "alice@mail.com"));
        service.register(new User("", "bad"));
    }
}
