import java.lang.Math;

public abstract class BendaAkuarium implements Posisi, Movement{
    private double x;
    private double y;
    private double arah;
    private double kecepatan;
    private String image;

    public BendaAkuarium(double x, double y, double arah, double kecepatan) {
        this.x = x;
        this.y = y;
        this.arah = arah;
        this.kecepatan = kecepatan;
    }

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

    public String getImage() {
        return image;
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

    public void setImage(String image) {
        this.image = image;
    }

    public abstract void gerak();

    @Override
    public double hitungjarak(Posisi other) {
        return Math.sqrt( Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }
}
