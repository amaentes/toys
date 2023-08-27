import java.io.IOException;
import java.util.Scanner;

public class ToyStoreProgram {
    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        try {
            store.loadFromFile("toys.txt");
        } catch (IOException e) {
            System.out.println("Error loading toys from file.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add new toy");
            System.out.println("2. Update toy frequency");
            System.out.println("3. Start toy giveaway");
            System.out.println("4. Save toys to file");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter toy id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    System.out.print("Enter toy name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter toy quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter toy frequency: ");

double frequency = scanner.nextDouble();
                    Toy toy = new Toy(id, name, quantity, frequency);
                    store.addToy(toy);
                    break;
                case 2:
                    System.out.print("Enter toy id: ");
                    int toyId = scanner.nextInt();
                    System.out.print("Enter toy frequency: ");
                    double toyFrequency = scanner.nextDouble();
                    store.updateFrequency(toyId, toyFrequency);
                    break;
                case 3:
                    Toy winner = store.getToy();
                    if (winner != null) {
                        System.out.println("Congratulations! You won a " + winner.getName() + ".");
                    }
                    break;
                case 4:
                    try {
                        store.saveToFile("toys.txt");
                    } catch (IOException e) {
                        System.out.println("Error saving toys to file.");
                    }
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}