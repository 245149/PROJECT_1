public interface Equations {

    /**
     * @param e - Eccentricity
     * @param M - Mean Anomaly
     * @ E - Eccentric Anomaly
     * @ a - distance from the ellipse focus
     *       (radius of the circle with the focus (sun) int the center)
     * @return
     */

    public abstract double solveE(double e, double[] M);
    public abstract double solveX(double e, double E, double a);
    public abstract double solveY(double e, double E, double a);

}
