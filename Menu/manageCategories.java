package Menu;

import DSA.hashset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class manageCategories {
    static final hashset categorySet = new hashset();

    static {
        try {
            loadCategoriesFromFile(); // Load categories on class initialization
        } catch (IOException e) {
            System.out.println("⚠️ Error loading categories from file: " + e.getMessage());
        }
    }

    public static void categories() {
        Scanner s = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n📂 Category Manager:");
                System.out.println("1. List Categories");
                System.out.println("2. Add Category");
                System.out.println("3. Delete Category");
                System.out.println("0. Back");

                System.out.print("Choose an option: ");
                String choice = s.nextLine().trim();

                switch (choice) {
                    case "1":
                        listcategories();
                        break;
                    case "2":
                        addcategory(s);
                        break;
                    case "3":
                        deletecategory(s);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("❌ Invalid option. Please enter 1, 2, 3, or 0.");
                }
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static void listcategories() {
        try {
            System.out.println("\n📋 Current Categories:");
            categorySet.printAll();
        } catch (Exception e) {
            System.out.println("❌ Failed to list categories: " + e.getMessage());
        }
    }

    public static void addcategory(Scanner s) {
        try {
            System.out.print("Enter new category: ");
            String input = s.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("⚠️ Category cannot be empty.");
                return;
            }

            if (categorySet.contains(input)) {
                System.out.println("⚠️ Category already exists.");
                return;
            }

            categorySet.add(input);
            saveCategoriesToFile(); // <-- Save to file
            System.out.println("✅ Category added!");

        } catch (Exception e) {
            System.out.println("❌ Failed to add category: " + e.getMessage());
        }
    }

    public static void deletecategory(Scanner s) {
        try {
            System.out.print("Enter category to delete: ");
            String input = s.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("⚠️ Category cannot be empty.");
                return;
            }

            if (!categorySet.contains(input)) {
                System.out.println("⚠️ Category does not exist.");
                return;
            }

            categorySet.remove(input);
            saveCategoriesToFile(); // <-- Save to file
            System.out.println("✅ Category deleted!");

        } catch (Exception e) {
            System.out.println("❌ Failed to delete category: " + e.getMessage());
        }
    }

    private static void loadCategoriesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Menu/category.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    categorySet.add(line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Cannot read from category.txt. Make sure the file exists and is accessible.", e);
        }
    }

    private static void saveCategoriesToFile() {
        try (FileWriter writer = new FileWriter("Menu/category.txt")) {
            for (int i = 0; i < hashset.SIZE; i++) {
                DSA.hashset.Node node = categorySet.buckets[i];
                while (node != null) {
                    writer.write(node.value + "\n");
                    node = node.next;
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save categories to file: " + e.getMessage());
        }
    }
    public static void addCategoryIfNew(String category) {
        category = category.trim();
        if (category.isEmpty()) return;

        if (!categorySet.contains(category)) {
            categorySet.add(category); // Add to memory
            saveCategoriesToFile();    // Save to category.txt
            System.out.println("📁 New category added to category.txt: " + category);
        }
    }

}
