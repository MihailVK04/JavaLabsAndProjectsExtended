package tasks.task20;

class Book {
    private String title;
    private String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Without overriding toString, you get: Book@1a2b3c4d
    @Override
    public String toString() {
        return "\"" + title + "\" by " + author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book b)) return false;
        return title.equals(b.title) && author.equals(b.author);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, author);
    }
}

class Main {
    public static void main(String[] args) {
        Book b1 = new Book("1984", "Orwell");
        Book b2 = new Book("1984", "Orwell");
        Object obj = b1;

        System.out.println("toString : " + b1);
        System.out.println("equals   : " + b1.equals(b2));
        System.out.println("hashCode : " + b1.hashCode() + " == " + b2.hashCode());
        System.out.println("getClass : " + obj.getClass().getSimpleName());
        System.out.println("is Book? : " + (obj instanceof Book));
    }
}
