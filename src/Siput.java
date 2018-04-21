public class Siput extends BendaAkuarium{
    private Posisi pointtujuan;

    public Posisi getPointtujuan() {
        return pointtujuan;
    }

    public void setPointtujuan(Posisi pointtujuan) {
        this.pointtujuan = pointtujuan;
    }

    public Siput(double x, double y, double a, double kecepatan) {
        super(x, y, a, kecepatan);
        pointtujuan.setX(Math.random()%853);
        pointtujuan.setY(Math.random()%640);
        this.setImage("siputkanan.png");
    }

    @Override
    public void gerak() {
        boolean kanansiput = true;

        if ((pointtujuan.getX() - this.getX()) > 0) {
            kanansiput = true;
            this.setImage("siputkanan.png");
        } else {
            kanansiput = false;
            this.setImage("siputkiri.png");
        }

        if ((kanansiput) && (this.getX() != 0 /* SCREEN_WIDTH */) && (Math.abs(this.getX() - pointtujuan.getX()) > 0.1)) {
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
            if (Math.abs(listkoin.getIdx(i).getY() - 0 /* SCREEN_HEIGHT */ - 20) < 1) {
                ketemudasar = true;
            }
        }

        if (ketemudasar) {
            tujuan.setX(99999);
            tujuan.setY(0/* SCREEN_HEIGHT */);
            for(int i = 0; i < listkoin.getSize(); i++) {
                if (Math.abs(listkoin.getIdx(i).getY() - 0/* SCREEN_HEIGHT */ - 20) < 1) {
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
                if (listkoin.getIdx(i).getY() - tujuan.getY() < 0.1) {
                    terdekat = i;
                    tujuan.setX(listkoin.getIdx(i).getX());
                    tujuan.setY(listkoin.getIdx(i).getY());
                }
            }
        }

        if (terdekat != -1) {
            this.setArah(Math.atan2(listkoin.getIdx(terdekat).getY() - this.getY(), listkoin.getIdx(terdekat).getX() - this.getX()));
            if (this.getArah()*180/1/*PI*/ > -90 && this.getArah()*180/1/*PI*/ < 90) {
                this.setImage("siputkanan.png");
                kanansiput = true;
            } else {
                this.setImage("siputkiri.png");
                kanansiput = false;
            }

            if ((kanansiput) && (this.getX() != 0 /* SCREEN WIDTH */) && (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX()) > 0.1)) {
                this.setX(this.getX() + this.getKecepatan()*0.0001);
            } else if ((this.getX() != 0) && (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX()) > 0.1)) {
                this.setX(this.getX() - this.getKecepatan()*0.0001);
            }
            if (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX()) < 0.1 && Math.abs(this.getY() - listkoin.getIdx(terdekat).getY()) < 0.1){
                return terdekat;
            }
        }

        return -1;
    }




}
