/**
 * Kelas ini Untuk mengatur attribut user yang memainkan game.
 */
public class Player {
    /**
     * Attribut untuk menyimpan nilai koin dari player.
     */
    private int jumlahkoin;

    /**
     * Attribut untuk menyimpan banyak telur yang dibeli dari player.
     */
    private int banyaktelur;

    /**
     * Koin awal yang diterima oleh player.
     */
    private static final int KOIN_AWAL = 1000;

    /**
     * Constructor.
     */
    public Player() {
        this.jumlahkoin = KOIN_AWAL;
        this.banyaktelur = Main.NOL;
    }

    /**
     * @param x .
     * @param y .
     */
    public Player(final int x, final int y) {
        this.jumlahkoin = x;
        this.banyaktelur = y;
    }

    /**
     * @return int.
     */
    public int getJumlahkoin() {
        return jumlahkoin;
    }

    /**
     * @param x .
     */
    public void setJumlahkoin(final int x) {
        this.jumlahkoin = x;
    }

    /**
     * @return int.
     */
    public int getBanyaktelur() {
        return banyaktelur;
    }

    /**
     * @param x .
     */
    public void setBanyaktelur(final int x) {
        this.banyaktelur = x;
    }

    /**
     * @param koin .
     */
    public void tambahKoin(final int koin) {
        jumlahkoin += koin;
    }

    /**
     * @param koin .
     */
    public void kurangkanKoin(final int koin) {
        jumlahkoin -= koin;
    }

    /**
     * Untuk menambahkan telur dari player.
     */
    public void tambahTelur() {
        banyaktelur++;
    }
}
