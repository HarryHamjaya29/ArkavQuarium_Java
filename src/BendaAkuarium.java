/**
 * kelas abstrak BendaAkuarium.
 * merupakan kelas yang mengimplementasi interface posisi, movement
 */


public abstract class BendaAkuarium implements Posisi, Movement {
    /**
     * merupakan kelas yang memiliki atribut posisi arah, kecepatan, dan gambar.
     */

    private double x;
    /**
     * merupakan absis dari posisi benda aquarium.
     */
    private double y;
    /**
     * merupakan ordinat dari posisi benda aquarium.
     */
    private double arah;
    /**
     * merupakan orientasi dari pergerakan benda aquarium.
     */
    private double kecepatan;
    /**
     * nilai kecepatan pergerakan benda aquarium.
     */
    private String image;

    /**
     * @param x1 .
     * @param y1 .
     * @param arah1 .
     * @param kecepatan1 .
     */

    public BendaAkuarium(final double x1, final double y1,
                         final double arah1, final double kecepatan1) {

        this.x = x1;
        this.y = y1;
        this.arah = arah1;
        this.kecepatan = kecepatan1;
    }

    /**
     * @return .
     */

    public double getX() {
        return x;
    }

    /**
     * @return .
     */

    public double getY() {
        return y;
    }
    /**
     * @return .
     */

    public double getArah() {
        return arah;
    }
    /**
     * @return .
     */

    public double getKecepatan() {
        return kecepatan;
    }
    /**
     * @return .
     */

    public String getImage() {
        return image;
    }

    /**
     * @param x1 .
     */
    public void setX(final double x1) {
        this.x = x1;
    }

    /**
     * @param y1 .
     */
    public void setY(final double y1) {
        this.y = y1;
    }

    /**
     * @param arah1 .
     */
    public void setArah(final double arah1) {
        this.arah = arah1;
    }

    /**
     * @param kecepatan1 .
     */
    public void setKecepatan(final double kecepatan1) {
        this.kecepatan = kecepatan1;
    }

    /**
     * @param image1 .
     */

    public void setImage(final String image1) {
        this.image = image1;
    }

    /**
     * method abstrak gerak akan diimplementasikan ke kelas yang lain .
     */
    public abstract void gerak();

    /**
     * mengoverride method hitungjarak posisi .
     * @param other .
     * @return .
     */
    @Override
    public double hitungjarak(final Posisi other) {
        double a = Math.pow(this.getX() - other.getX(), 2);
        double b = Math.pow(this.getY() - other.getY(), 2);

        return Math.sqrt(a + b);
    }
}
