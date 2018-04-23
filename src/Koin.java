import java.lang.Math;

public class Koin extends BendaAkuarium {
    private int nilai;
    private final int level;
    private static String[] daftargambar = {
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/koin1.jpg",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/koin2.jpg",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/koin3.jpg",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/koin4.jog"
    };

    public Koin(double x, double y, double kecepatan, int nilai, int i) {
        super(x, y, 0, kecepatan);
        this.level = i;
        this.nilai = nilai;
        this.setImage(daftargambar[i-1]);
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public int getLevel() {
        return level;
    }

    public static String[] getDaftargambar() {
        return daftargambar;
    }

    public static void setDaftargambar(String[] daftargambar) {
        Koin.daftargambar = daftargambar;
    }

    @Override
    public void gerak() {
        if (Math.abs(this.getY() - Akuarium.SCREEN_HEIGHT - 20) > 1) {
            this.setY(this.getY() + this.getKecepatan() * 0.01);
        }
    }
}
