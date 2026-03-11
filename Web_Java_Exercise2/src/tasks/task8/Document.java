package tasks.task8;

interface Printable {
    void print();
    default void preview() {
        System.out.println("[Preview] " + getClass().getSimpleName());
    }
}

interface Saveable {
    void save(String path);
    boolean isSaved();
}

class Document implements Printable, Saveable {
    private String title;
    private String content;
    private boolean saved = false;

    Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public void print() {
        System.out.println("=== " + title + " ===");
        System.out.println(content);
    }

    @Override
    public void save(String path) {
        System.out.println("Saving '" + title + "' to " + path);
        saved = true;
    }

    @Override
    public boolean isSaved() { return saved; }
}

class Main {
    public static void main(String[] args) {
        Document doc = new Document("Report", "Q4 results look great.");
        doc.preview();             // default method
        doc.print();
        System.out.println("Saved? " + doc.isSaved());
        doc.save("/docs/report.pdf");
        System.out.println("Saved? " + doc.isSaved());
    }
}
