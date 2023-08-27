import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ToyStore {
    private ArrayList<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateFrequency(int id, double frequency) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setFrequency(frequency);
                break;
            }
        }
    }

    public Toy getToy() {
        double totalFrequency = 0;
        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
        }
        double random = Math.random() * totalFrequency;
        double countFrequency = 0;
        for (Toy toy : toys) {
            countFrequency += toy.getFrequency();
            if (countFrequency >= random) {
                if (toy.getQuantity() > 0) {
                    toy.quantity--;
                    return toy;
                } else {
                    System.out.println("Sorry, this toy is out of stock.");
                    break;
                }
            }
        }
        return null;
    }

    public void saveToFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (Toy toy : toys) {
            writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getFrequency() + "\n");
        }
        writer.close();
    }

    public void loadFromFile(String fileName) throws IOException {
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            double frequency = Double.parseDouble(parts[3]);
            Toy toy = new Toy(id, name, quantity, frequency);
            toys.add(toy);
        }
        bufferedReader.close();
        reader.close();
    }
}
