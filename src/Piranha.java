import java.util.Random;

public class Piranha extends Ikan{
    private static String[] daftargambar;

    public Piranha(double x, double y, double arah, double kecepatan) {
        super(x, y, arah, kecepatan, "Piranha");
        this.setImage("piranhakiri.png");
        this.setLapar(false);
    }

    public void gerak() {
        Random rand = new Random();

        if (0 /*time_since_start*/ - this.getWaktuRandom() >= 3) {
            Posisi tujuan = new Siput(rand.nextInt(0/*SCREEN_WIDTH*/), rand.nextInt(0/*SCREEN_HEIGHT*/)+100, 0, 0);
            this.setPointTujuan(tujuan);
            this.setWaktuMakan(0 /*time_since_start()*/);
        }
        this.setArah(Math.atan2(this.getPointTujuan().getY() - this.getY(), this.getPointTujuan().getX() - this.getX()));
        if (this.getArah()*180/1/*PI*/ > -90 && this.getArah()*180/1/*PI*/ < 90) {
            this.setImage((daftargambar[1]));
        } else {
            this.setImage(daftargambar[0]);
        }
        this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
        this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);

        if (Math.abs(this.getX()-this.getPointTujuan().getX()) < 1 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 1) {
            Posisi tujuan = new Siput(rand.nextInt(0/*SCREEN_WIDTH*/), rand.nextInt(0/*SCREEN_HEIGHT*/)+100, 0, 0);
            this.setPointTujuan(tujuan);
        }
        if (int(0/*time_since_start*/ - this.getWaktuMakan()) == this.getTahanKenyang()) {
            this.setLapar(true);
        }
    }

    //Fungsi yang mencari ikan yang bisa dimakan dari List<Ikan>
//dan mengembalikkan posisi makanan yang bisa dimakan terdekat
//jika tidak ada makanan di radius akan mengembalikkan (-1, -1)
    int Piranha::cariIkanTerdekat(List<Ikan> listikan){

        double min = 99999999999;
        int terdekat = -1;

        Posisi now(this.getX(), this.getY());

        for(int i = 0; i < listikan.getSize(); i++) {
            if (listikan.get(i)->getType()=="Guppy") {
                Posisi a(listikan.get(i)->getX(), listikan.get(i)->getY());
                if (min > hitungjarak(a, now)) {
                    terdekat = i;
                    min = hitungjarak(a, now);
                }
            }
        }

        if (terdekat != -1) {
            this.setArah(Math.atan2(listikan.get(terdekat).getY() - this.getY(), listikan.get(terdekat).getX() - this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[3]);
            } else {
                this.setImage(daftargambar[2]);
            }
            this.setX(this->getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this->getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this->getX() - listikan.get(terdekat).getX()) < 1 && Math.abs(this.getY() - listikan.get(terdekat).getY()) < 1) {
                this.setLapar(false);
                this.setWaktuMakan(0/*time_since_start()*/);
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
            if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 1 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 1) {
                Posisi tujuan(rand.nextInt(0/*SCREEN_WIDTH*/), rand.nextInt(0/*SCREEN_HEIGHT*/+100);
                this.setPointTujuan(tujuan);
            }
        }

        return -1;
    }
}