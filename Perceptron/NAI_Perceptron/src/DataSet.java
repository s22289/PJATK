import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSet {
    public static int vectorLength;
    public static Map<String,Integer> determinationValues = new HashMap<>();
    public static final Scanner scanner = new Scanner(System.in);

    public static List<Perceptron.InputVector> readFile(String path) {
        List<Perceptron.InputVector> vectors = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] arr = line.split(",");
                vectorLength = arr.length-1;
                double[] values = new double[vectorLength];
                for (int i = 0; i < vectorLength; i++){
                    values[i] = Double.parseDouble(arr[i].trim());
                }
                determinationValues.put(arr[vectorLength],null);
                vectors.add(new Perceptron.InputVector(arr[vectorLength],values));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int identifier = 0;
        for (Map.Entry<String,Integer> entry : determinationValues.entrySet()){
            entry.setValue(identifier++);
        }

        return vectors;
    }

    public static void setDataFromConsole(boolean isRunning, Perceptron perceptron) {
        while (isRunning) {
            System.out.println("Enter attributes 'a1,a2,a3,a4,...' or 'exit': ");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                isRunning = false;
                break;
            }

            String[] attrsString = line.split(",");
            double[] attributes = new double[attrsString.length];

            try {
                for (int i = 0; i < attributes.length; i++)
                    attributes[i] = Double.parseDouble(attrsString[i].trim());
            } catch (Exception exception) {
                System.out.println("Entered data is not valid!");
                System.out.println("=====================================");
                setDataFromConsole(isRunning, perceptron);
                break;
            }

            ArrayList<Perceptron.InputVector> vectors = new ArrayList<>();
            vectors.add(new Perceptron.InputVector(null, attributes));
            perceptron.setTestList(vectors);
            perceptron.run(false);

            System.out.println("=====================================");
        }
    }
}
