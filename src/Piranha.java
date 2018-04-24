import java.util.Random;

public class Piranha extends Ikan{

    private static String[] daftargambar = {
            "image/piranhakiri.png",
            "image/piranhakanan.png",
            "image/piranhalaparkiri.png",
            "image/piranhalaparkanan.png"
    };

    public Piranha(double x, double y, double arah, double kecepatan) {
        super(x, y, arah, kecepatan, "Piranha");
        this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/piranhakiri.png");
        this.setLapar(false);
    }

    public void gerak() {

        if (Akuarium.time_since_start() - this.getWaktuRandom() >= 3) {
            Posisi tujuan = new Siput(Main.rand.nextInt(Akuarium.SCREEN_WIDTH), Main.rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0, 0);
            this.setPointTujuan(tujuan);
            this.setWaktuRandom(Akuarium.time_since_start());
        }
        this.setArah(Math.atan2(this.getPointTujuan().getY() - this.getY(), this.getPointTujuan().getX() - this.getX()));
        if (this.getArah()*180/Akuarium.PI > -90 && this.getArah()*180/Akuarium.PI < 90) {
            this.setImage(daftargambar[1]);
        } else {
            this.setImage(daftargambar[0]);
        }
        this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
        this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);

        if (Math.abs(this.getX()-this.getPointTujuan().getX()) < 5 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 5) {
            Posisi tujuan = new Siput(Main.rand.nextInt(Akuarium.SCREEN_WIDTH), Main.rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0, 0);
            this.setPointTujuan(tujuan);
        }
        if ((int)(Akuarium.time_since_start() - this.getWaktuMakan()) == this.getTahanKenyang()) {
            this.setLapar(true);
        }
    }

    public int cariIkanTerdekat(List<Ikan> listikan){
        double min = 999999999;
        int terdekat = -1;

        if (Akuarium.time_since_start() - this.getWaktuRandom() >= 3) {
            Posisi tujuan = new Siput(Main.rand.nextInt(Akuarium.SCREEN_WIDTH), Main.rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0, 0);
            this.setPointTujuan(tujuan);
            this.setWaktuRandom(Akuarium.time_since_start());
        }

        Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

        for(int i = 0; i < listikan.getSize(); i++) {
            if (listikan.getIdx(i).getType().equals("Guppy")) {
                Posisi a = new Siput(listikan.getIdx(i).getX(), listikan.getIdx(i).getY(), 0, 0);
                if (min > a.hitungjarak(now)) {
                    terdekat = i;
                    min = a.hitungjarak(now);
                }
            }
        }

        if (terdekat != -1) {
            this.setArah(Math.atan2(listikan.getIdx(terdekat).getY() - this.getY(), listikan.getIdx(terdekat).getX() - this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[3]);
            } else {
                this.setImage(daftargambar[2]);
            }
            this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this.getX() - listikan.getIdx(terdekat).getX()) < 5 && Math.abs(this.getY() - listikan.getIdx(terdekat).getY()) < 5) {
                this.setLapar(false);
                this.setWaktuMakan(Akuarium.time_since_start());
                return terdekat;
            }
        } else {
            this.setArah(Math.atan2(this.getPointTujuan().getY()-this.getY(), this.getPointTujuan().getX()-this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[3]);
            } else {
                this.setImage(daftargambar[2]);
            }
            this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 5 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 5) {
                Posisi tujuan = new Siput(Main.rand.nextInt(Akuarium.SCREEN_WIDTH), Main.rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0,0);
                this.setPointTujuan(tujuan);
            }
        }

        return -1;
    }
}