import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KNN {

    static class Iris {
        //widths and lengths
        double[] data;
        String type;
        //constructor
        public Iris(double[] data, String type) {
            this.type = type;
            this.data = data;
        }
    }

    static class Result {
        double distance;
        String type;

        public Result(double distance, String type) {
            this.distance = distance;
            this.type = type;
        }
    }

    static class DistComp implements Comparator<Result> {

        @Override
        public int compare(Result o1, Result o2) {

            return Double.compare(o1.distance, o2.distance);
        }

    }

    //reading iris data as console input
    public static double[] loadInput(){
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] row = line.split(",");
        double[] x = new double[4];
        for (int i = 0; i < row.length - 1; i++) {
            x[i] = Double.parseDouble(row[i]);
        }
        return x;
    }

    //reading iris data from file
    public static List<Iris> loadDataTrainingSet(String fname) throws FileNotFoundException {
        List<Iris> list = new ArrayList<>();
        Scanner input = new Scanner(new File(fname));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] row = line.split(",");
            double[] x = new double[4];
            for (int i = 0; i < row.length - 1; i++) {
                x[i] = Double.parseDouble(row[i]);
            }
            list.add(new Iris(x, row[4]));
        }
        input.close();
        return list;
    }

    public KNN(int K, List<Iris> test, List<Iris> train) {

        double count = 0;

        for (Iris i : test) {
            List<Result> dists = new ArrayList();

            for (Iris iris : train) {
                double dist = 0;
                for (int j = 0; j < iris.data.length; j++) {
                    dist = dist + Math.pow(iris.data[j] - i.data[j], 2);
                }

                dist = Math.sqrt(dist);
                dists.add(new Result(dist, iris.type));
            }

            Collections.sort(dists, new DistComp());

            List<String> types = new ArrayList<>();
            for (int g = 0; g < K; g++) {
                types.add(dists.get(g).type);

            }


            String result = null;
            int max = 0;
            for(String s : types){
                int freq = Collections.frequency(types,s);
                if (freq>max){
                    max = freq;
                    result = s;
                }
            }
            System.out.print(result + "      ");
            if (!i.type.equals(result)){
                count++;
            }

            System.out.println(i.type + " ");
        }
        count = Math.round((30 - count)/30 * 100 * 100.0)/100.0;
        System.out.println("Accuracy: " + count);

    }
    //manuel
    public KNN(int K, List<Iris> train, double[] iris){
        List<Result> dists = new ArrayList();
        for (Iris i : train){

            for (int j = 0; j < iris.length; j++) {
                double dist = 0;
                dist = dist + Math.pow(iris[j] - i.data[j], 2);
                dist = Math.sqrt(dist);
                dists.add(new Result(dist, i.type));
            }
        }
        Collections.sort(dists,new DistComp());

        List<String> types = new ArrayList<>();
        for (int g = 0; g < K; g++) {
            types.add(dists.get(g).type);
        }

        String result = null;
        int max = 0;
        for(String s : types){
            int freq = Collections.frequency(types,s);
            if (freq>max){
                max = freq;
                result = s;
            }
        }
        System.out.println(result);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int k;
        System.out.println("Do you want to use the test data?(yes/no)");
        String s = sc.nextLine();
        if (s.equals("yes")){
            System.out.println(" KNN result" + "         Expected result" + "\n");
            new KNN(9, loadDataTrainingSet("D:\\dersler\\4.aömestır\\NAI(articial_ıntelligence)\\KNN_naı\\iris.test.data.txt"),
                    loadDataTrainingSet("D:\\dersler\\4.aömestır\\NAI(articial_ıntelligence)\\KNN_naı\\iris.data.txt"));
        } else if (s.equals("no")){
            System.out.println("Enter the value of k:");
            k = sc.nextInt();
            System.out.println("Enter the features of Iris: ");
            double[] iris = loadInput();
            System.out.println(" KNN result" + "\n");
            new KNN(k, loadDataTrainingSet("D:\\dersler\\4.aömestır\\NAI(articial_ıntelligence)\\KNN_naı\\iris.data.txt"),
                    iris);
        }



    }

}