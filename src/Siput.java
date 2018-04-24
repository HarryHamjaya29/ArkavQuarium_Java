import java.util.Random;

public class Siput extends BendaAkuarium {

    private Posisi pointtujuan;

    public Posisi getPointtujuan() {
        return pointtujuan;
    }

    public void setPointtujuan(Posisi pointtujuan) {
        this.pointtujuan = pointtujuan;
    }

    public Siput() {
        super(Main.rand.nextInt(Akuarium.SCREEN_WIDTH), Akuarium.SCREEN_HEIGHT, 0, 20000);
        pointtujuan = new MakananIkan(Main.rand.nextInt(Akuarium.SCREEN_WIDTH), Main.rand.nextInt(Akuarium.SCREEN_HEIGHT));
        pointtujuan.setX(Main.rand.nextInt(Akuarium.SCREEN_WIDTH));
        pointtujuan.setY(Main.rand.nextInt(Akuarium.SCREEN_HEIGHT));
        this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/siputkanan.png");
    }

    public Siput(double x, double y, double a, double kecepatan) {
        super(x, y, a, kecepatan);
        pointtujuan = new MakananIkan(Math.random()%853, Math.random()%640);
        this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/siputkanan.png");
    }

    @Override
    public void gerak() {
        boolean kanansiput = true;

        if ((pointtujuan.getX() - this.getX()) > 0) {
            kanansiput = true;
            this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/siputkanan.png");
        } else {
            kanansiput = false;
            this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/siputkiri.png");
        }

        if ((kanansiput) && (this.getX() != Akuarium.SCREEN_WIDTH) && (Math.abs(this.getX() - pointtujuan.getX()) > 0.1)) {
            this.setX(this.getX() + this.getKecepatan()*0.0001);
        } else if ((this.getX() != 0) && (Math.abs(this.getX() - pointtujuan.getX()) > 0.1)) {
            this.setX(this.getX() - this.getKecepatan() * 0.0001);
        }
    }

    int cariKoin(List<Koin> listkoin) {
        boolean kanansiput = true;
        boolean ketemudasar = false;

        int terdekat = -1;

        Posisi tujuan = new Siput(this.getX(), this.getY(), 0,0);
        Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

        for(int i = 0; i < listkoin.getSize() && !ketemudasar; i++) {
            if (Math.abs(listkoin.getIdx(i).getY() - Akuarium.SCREEN_HEIGHT - 20) < 1) {
                ketemudasar = true;
            }
        }

        if (ketemudasar) {
            tujuan.setX(99999);
            tujuan.setY(Akuarium.SCREEN_HEIGHT);
            for(int i = 0; i < listkoin.getSize(); i++) {
                if (Math.abs(listkoin.getIdx(i).getY() - Akuarium.SCREEN_HEIGHT - 20) < 1) {
                    if (Math.abs(now.getX() - listkoin.getIdx(i).getX()) < Math.abs (now.getX() - tujuan.getX())) {
                        terdekat = i;
                        tujuan.setX(listkoin.getIdx(i).getX());
                    }
                }
            }
        } else {
            tujuan.setX(this.getX());
            tujuan.setY(0);
            for(int i = 0; i < listkoin.getSize(); i++) {
                if (listkoin.getIdx(i).getY() > tujuan.getY()) {
                    terdekat = i;
                    tujuan.setX(listkoin.getIdx(i).getX());
                    tujuan.setY(listkoin.getIdx(i).getY());
                }
            }
        }

        if (terdekat != -1) {
            this.setArah(Math.atan2(listkoin.getIdx(terdekat).getY() - this.getY(), listkoin.getIdx(terdekat).getX() - this.getX()));
            if (this.getArah()*180/Akuarium.PI > -90 && this.getArah()*180/Akuarium.PI < 90) {
                this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/siputkanan.png");
                kanansiput = true;
            } else {
                this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/siputkiri.png");
                kanansiput = false;
            }

            if ((kanansiput) && (this.getX() != Akuarium.SCREEN_WIDTH) && (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX()) > 1)) {
                this.setX(this.getX() + this.getKecepatan()*0.0001);
            } else if ((this.getX() != 0) && (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX()) > 1)) {
                this.setX(this.getX() - this.getKecepatan()*0.0001);
            }
            if (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX()) < 1 && Math.abs(this.getY() - listkoin.getIdx(terdekat).getY()) < 21){
                return terdekat;
            }
        }

        return -1;
    }




}
