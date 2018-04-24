/**
 * Kelas Posisi ialah kelas yang menyimpan posisi suatu benda di akuarium.
 */
public interface Posisi {
    /**
     * @param x .
     */
    void setX(double x);

    /**
     * @param y .
     */
    void setY(double y);

    /**
     * @return Double.
     */
    double getX();

    /**
     * @return Double.
     */
    double getY();

    /**
     * @param x .
     * @return Double.
     */
    double hitungjarak(Posisi x);
}
