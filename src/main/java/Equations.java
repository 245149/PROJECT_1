public interface Equations {

    /**
     * @param e - Eccentricity
     * @ M - Mean Anomaly
     * @ E - Eccentric Anomaly
     * @ a - distance from the ellipse focus
     *       (radius of the circle with the focus (sun) int the center)
     * @return - list of E solutions
     */

//    public abstract double[] FPIsolveE(double e, double E, double[] M); //Fixed-point iteration method
//    public abstract double[] NRsolveE(double e, double E, double [] M); //Newton-Raphson method
//    public abstract double[] SLsolveE(double e, double E, double [] M); //SecantLine method
//    public abstract double[] BsolveE(double e, double E, double [] M); //Bisection method
    public abstract double[] solveX(double e, double [] E, double a);
    public abstract double[] solveY(double e, double [] E, double a);

}
