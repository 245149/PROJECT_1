import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

     System.out.println(Arrays.toString(samplingM()));

    }

    public static double[] samplingM() {

        double pi = 3.14159265359;
        double M; //próbkowanie
        double[] dataM = new double[1000];
        for (int i = 1; i < 1000; i++) {
            M = i * ((2 * pi) / 1000);//próbkowanie
            dataM[i] = M;
        }
        return dataM;
    }

}
