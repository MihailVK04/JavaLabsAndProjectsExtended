package tasks.task30;

import java.util.*;

// Product interface
interface Document {
    void open();
    void save();
    String type();
}

class PdfDocument implements Document {
    public void open() { System.out.println("  Opening PDF viewer..."); }
    public void save() { System.out.println("  Saving as .pdf"); }
    public String type() { return "PDF"; }
}

class WordDocument implements Document {
    public void open() { System.out.println("  Opening word processor..."); }
    public void save() { System.out.println("  Saving as .docx"); }
    public String type() { return "Word"; }
}

class SpreadsheetDocument implements Document {
    public void open() { System.out.println("  Opening spreadsheet app..."); }
    public void save() { System.out.println("  Saving as .xlsx"); }
    public String type() { return "Spreadsheet"; }
}

// Factory with static method
class DocumentFactory {
    static Document create(String extension) {
        return switch (extension.toLowerCase()) {
            case "pdf"  -> new PdfDocument();
            case "docx" -> new WordDocument();
            case "xlsx" -> new SpreadsheetDocument();
            default     -> throw new IllegalArgumentException("Unknown: " + extension);
        };
    }
}

class Main {
    public static void main(String[] args) {
        List<String> files = List.of("pdf", "docx", "xlsx", "pdf");

        for (String ext : files) {
            Document doc = DocumentFactory.create(ext);
            System.out.println(doc.type() + " document:");
            doc.open();
            doc.save();
            System.out.println();
        }
    }
}
