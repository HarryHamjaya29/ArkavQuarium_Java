import java.lang.Math;

public abstract class BendaAkuarium implements Posisi, Movement{
    private double x;
    private double y;
    private double arah;
    private double kecepatan;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getArah() {
        return arah;
    }

    public double getKecepatan() {
        return kecepatan;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setArah(double arah) {
        this.arah = arah;
    }

    public void setKecepatan(double kecepatan) {
        this.kecepatan = kecepatan;
    }

    public abstract void gerak();

    @Override
    public double hitungjarak(Posisi other) {
        return Math.sqrt( Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }
}
