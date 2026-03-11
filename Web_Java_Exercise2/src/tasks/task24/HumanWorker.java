package tasks.task24;

// Segregated interfaces — each focused on one capability

interface Workable {
    void work();
}

interface Feedable {
    void eat();
}

interface Restable {
    void sleep();
}

interface MeetingAttendee {
    void attendMeeting();
}

// Human implements all that apply
class HumanWorker implements Workable, Feedable, Restable, MeetingAttendee {
    private String name;
    HumanWorker(String name) { this.name = name; }

    public void work()           { System.out.println(name + " is working."); }
    public void eat()            { System.out.println(name + " is eating lunch."); }
    public void sleep()          { System.out.println(name + " is sleeping."); }
    public void attendMeeting()  { System.out.println(name + " joined the meeting."); }
}

// Robot only implements what makes sense
class RobotWorker implements Workable {
    private String model;
    RobotWorker(String model) { this.model = model; }

    public void work() { System.out.println(model + " is assembling parts."); }
    // No eat(), sleep(), or attendMeeting() — not needed!
}

class Main {
    static void assignTask(Workable w) { w.work(); }

    public static void main(String[] args) {
        HumanWorker alice = new HumanWorker("Alice");
        RobotWorker bot = new RobotWorker("ARM-7");

        assignTask(alice);
        assignTask(bot);

        alice.eat();
        alice.attendMeeting();
        // bot.eat(); — COMPILE ERROR, good!
    }
}
