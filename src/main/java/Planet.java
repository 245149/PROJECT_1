public class Planet {

    private double e;
    private double d;

    public Planet(double e, double d) {
        this.e = e;
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "e=" + e +
                ", d=" + d +
                '}';
    }

}
