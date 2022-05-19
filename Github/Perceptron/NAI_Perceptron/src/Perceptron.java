//s22289


import javax.xml.crypto.Data;
import java.util.*;

public class Perceptron {
    static class InputVector {
        private String type;
        private double[] values;

        public InputVector(String type, double[] values) {
            this.type = type;
            this.values = values;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double[] getValues() {
            return values;
        }

        public void setValues(double[] values) {
            this.values = values;
        }
    }

    private List<InputVector> testList, trainList;
    private double[] weights;
    private double bias, alpha;

    public Perceptron(double alpha){
        testList = DataSet.readFile("C:\\Users\Furkan\\IdeaProjects\\NAI_Perceptron\\perceptron.test.data.csv");
        trainList = DataSet.readFile("C:\\Users\Furkan\\IdeaProjects\\NAI_Perceptron\\perceptron.data.csv");
        configurePerceptron();
        this.alpha = alpha;
    }

    private void configurePerceptron(){
        bias = (Math.random()*2)-1;
        weights = new double[DataSet.vectorLength];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random()*2)-1;
        }
    }

    private int output(InputVector vector){
        // output = w1*x1 + w2*x2 + ... + wn*xn - bias
        double net = 0;
        for (int i = 0; i < vector.getValues().length; i++) {
            net = net + vector.getValues()[i]*weights[i];
        }
        return net-bias >= 0 ? 1 : 0;
    }

    public void learn(){
        for (int i = 0; i < trainList.size(); i++) {
            int errorsTotal = 0;
            for(InputVector vector : trainList){
                int output = output(vector);
                int actualDeterminant = 0;

                for (Map.Entry<String,Integer> entry : DataSet.determinationValues.entrySet()){
                    if(entry.getKey().equals(vector.getType())){
                        actualDeterminant = entry.getValue();
                    }
                }
                int error = actualDeterminant - output;
                errorsTotal+=error;

                //update weights and bias according to delta rule
                for (int j = 0; j < DataSet.vectorLength; j++) {
                    //w' = w + alpha(d-y)x
                    weights[j] += alpha*vector.getValues()[j]*error;
                }
                bias += error*alpha;
            }
            if (errorsTotal == 0) break;
        }
    }

    public void run(boolean bool){
        int output;
        double total = 0;
        double found = 0;
        for(InputVector vector : testList){
            output = output(vector);

            String actualType = null;
            for (Map.Entry<String, Integer> entry : DataSet.determinationValues.entrySet()){
                if (output == entry.getValue()){
                    actualType = entry.getKey();
                }
            }
            if (bool && vector.getType().equals(actualType)){
                found++;
            }
            total++;

            System.out.println(Arrays.toString(vector.getValues()) + "->" + actualType);
        }
        if (bool){
            System.out.println("Accuracy: " + (found*100)/total + "%");
        }
    }

    public void setTestList(ArrayList<InputVector> testList){
        this.testList = testList;
    }

    public static void main(String[] args) {
        System.out.println("Iris");
        System.out.print("Enter the learning rate: ");
        Scanner scanner = new Scanner(System.in);
        double alpha = scanner.nextDouble();
        Perceptron perceptron = new Perceptron(alpha);

        perceptron.learn();
        perceptron.learn();
        perceptron.learn();
        perceptron.run(true);
        DataSet.setDataFromConsole(true,perceptron);
    }
}
