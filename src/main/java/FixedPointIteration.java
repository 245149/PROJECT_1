import java.util.ArrayList;

public class FixedPointIteration {
    private Function function;

    public FixedPointIteration(Function function) {
        this.function = function;
    }

    public FixedPointIteration() {
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public ArrayList<double[]> solver(double xl, double xu, double ea, double e, double M, double xn) {

        ArrayList<double[]> solver = new ArrayList<>();

        double[] solution = new double[4];
        solution[0] = xl;
        solution[1] = xu;
        solution[2] = calculateXr(xn, e, M);
        solution[3] = 100;
        solver.add(solution);
        while (solver.get(solver.size() - 1)[3] > ea) {
            solution = calculateBoundaries(solution, e, M);
            solution[2] = calculateXr(solution[2], e, M);
            solution[3] = calculateEa(solution[2], solver.get(solver.size() - 1)[2]);
            solver.add(solution);

        }
        return solver;

    }

    private double calculateXr(double xn, double e, double M) {
        double xR = 0;
        xR = function.function(e, xn, M) + xn;
        return xR;
    }

    private double[] calculateBoundaries(double[] solve, double e, double M) {
        if (function.function(e, solve[0], M) * function.function(e, solve[2], M) < 0) {

            double boundaries[] = new double[solve.length];
            boundaries[0] = solve[0];
            boundaries[1] = solve[2];
            return boundaries;
        } else {
            double boundaries[] = new double[solve.length];
            boundaries[0] = solve[2];
            boundaries[1] = solve[1];
            return boundaries;
        }
    }

    private double calculateEa(double prsntApprx, double prvsApprx) {
        return Math.abs(((prsntApprx - prvsApprx) / prsntApprx) * 100);
    }

}