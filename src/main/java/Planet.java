import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * @author Arkodiusz Kowal
 * @version FINAL
 * @since 27.11.2019
 */

public class Planet {

    /**
     * Planet class - responsible for paving the way for subsequent creation of a user input temporary Object,
     * on which crucial operations and calculations are performed in order to get the final form of an orbit;
     *
     */

    private double e;
    private double a;
    private double T;
    private String name;

    /**
     * @param e - input eccentricity;
     * @param a - input orb distance from the orbital focus (a huge mass in comparison - e.g. Sun);
     * @param t - input self orbit time;
     * @param name - input name of user orb;
     */

    public Planet(double e, double a, double t, String name) {
        this.e = e;
        this.a = a;
        this.T = t;
        this.name = name;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        this.T = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "e=" + e +
                ", a=" + a +
                ", T=" + T +
                ", name=" + name +
                '}';
    }

    /**
     * T - orbital period of a planet
     * @return dataM - an interpretation of orbital trajectory (in angles) as resolution (number of points of the function);
     *                 [orbital period in days rounded up to an integer value];
     */

    public double[] samplingM() {

        double T = getT();
        T = (int) ceil(T);
        double pi = 3.14159265359;
        double M; //sampling
        double[] dataM = new double[(int) T];
        for (int i = 1; i < T; i++) {
            M = i * ((2 * pi) / T);
            dataM[i] = M;
        }
        return dataM;
    }

    /**
     * @return - FixedPointIteration method of the "E" root calculation;
     */

    public double FPIsolveE() {

        double En = 0;
        //double[] M = samplingM();
        double[] dataE = new double[1000];

        ArrayList<double[]> solve;
        double M = 1;
        Function function = new Function() {
            @Override
            public double function(double e, double E, double M) {
                return M + e * Math.sin(E) - E;
            }
        };
        FixedPointIteration fixedPointIteration = new FixedPointIteration(function);
        solve = fixedPointIteration.solver(-5, 10, 0.0001, getE(), M, 3);
        En = solve.get(solve.size() - 1)[2];
        return En;
    }

    /**
     * @return - NewtonRaphson method of the "E" root calculation;
     */

    public double NRsolveE() {

        //double[] M = samplingM();
        double[] dataE = new double[1000];

        double En = 0;
        //double[] M = samplingM();
        //double[] dataE = new double[1000];

        ArrayList<double[]> solve;
        double M = 1;
        Function function = new Function() {
            @Override
            public double function(double e, double E, double M) {
                return M + e * Math.sin(E) - E;
            }
        };
        NewtonRaphson newtonRaphson = new NewtonRaphson(function);
        solve = newtonRaphson.solver(-5, 10, 0.0001, getE(), M, 3);
        En = solve.get(solve.size() - 1)[2];
        return En;
    }

    /**
     * @return - SecantLine method of the "E" root calculation;
     */

    public double SLsolveE() {

        //double[] M = samplingM();
        //double[] dataE = new double[1000];

        double En = 0;
        //double[] M = samplingM();
        //double[] dataE = new double[1000];

        ArrayList<double[]> solve;
        double M = 1;
        Function function = new Function() {
            @Override
            public double function(double e, double E, double M) {
                return M + e * Math.sin(E) - E;
            }
        };
        SecantLine secantLine = new SecantLine(function);
        solve = secantLine.solver(-5, 10, 0.0001, getE(), M, 5,6);
        En = solve.get(solve.size() - 1)[2];
        return En;
    }

    /**
     * @return - Bisection method of the "E" root calculation;
     */

    public double BsolveE() {

        //double[] M = samplingM();
        //double[] dataE = new double[1000];

        double En = 0;
        //double[] M = samplingM();
        //double[] dataE = new double[1000];

        ArrayList<double[]> solve;
        double M = 1;
        Function function = new Function() {
            @Override
            public double function(double e, double E, double M) {
                return M + e * Math.sin(E) - E;
            }
        };
        SecantLine secantLine = new SecantLine(function);
        solve = secantLine.solver(-5, 10, 0.0001, getE(), M, 5,6);
        En = solve.get(solve.size() - 1)[2];
        return En;
    }

}