import java.util.ArrayList;

public class Bisection {
    private Function function;

    public Bisection(Function function) {
        this.function = function;
    }

    public ArrayList<double[]> solver(double xl, double xu, double ea, double e, double M) {

        ArrayList<double[]> solver = new ArrayList<>();

        double[] solution = new double[4];
        solution[0] = xl;
        solution[1] = xu;
        solution[2] = calculateXr(xl, xu, e, M);
        solution[3] = 100;
        solver.add(solution);
        while (solver.get(solver.size() - 1)[3] > ea) {
            solution = calculateBoundaries(solution, e, M);
            solution[2] = calculateXr(solution[0], solution[1], e, M);
            solution[3] = calculateEa(solution[2], solver.get(solver.size() - 1)[2]);
            solver.add(solution);
        }
        return solver;

    }

    private double calculateXr(double xl, double xu, double e, double M) {
        double xr = 0;
        if (function.function(e, xl, M) * function.function(e, xu, M) > 0) {} else {
            xr = (xl + xu) / 2;
        }

        return xr;
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