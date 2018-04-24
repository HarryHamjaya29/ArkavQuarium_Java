public class Player {
    private int jumlahkoin;
    private int banyaktelur;

    public Player() {
        this.jumlahkoin = 2000;
        this.banyaktelur = 0;
    }

    public Player(int x, int y) {
        this.jumlahkoin = x;
        this.banyaktelur = y;
    }

    public int getJumlahkoin() {
        return jumlahkoin;
    }

    public void setJumlahkoin(int jumlahkoin) {
        this.jumlahkoin = jumlahkoin;
    }

    public int getBanyaktelur() {
        return banyaktelur;
    }

    public void setBanyaktelur(int banyaktelur) {
        this.banyaktelur = banyaktelur;
    }

    public void tambahKoin(int koin) {
        jumlahkoin += koin;
    }

    public void kurangkanKoin(int koin) {
        jumlahkoin -= koin;
    }

    public void tambahTelur() {
        banyaktelur++;
    }
}
