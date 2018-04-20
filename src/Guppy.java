import java.util.Random;

public class Guppy extends Ikan{
    private final int waktukeluarkankoin = 5;
    private double waktukoin;
    private static int makannaiklevel;
    private static String daftargambar[3][4];
    private int level;
    private int jumlahmakanyangdimakan;

    public Guppy(double x, double y, double arah, double kecepatan){
        super(x,y,arah,kecepatan,"Guppy");
        level = 1;
        jumlahmakanyangdimakan = 0;
        waktukoin= 0 /*time_since_start()*/;
        this.setImage("guppy1kiri.png");
    }

    public int getWaktuKeluarkanKoin() {
        return waktukeluarkankoin;
    }

    public double getWaktuKoin() {
        return waktukoin;
    }

    public static int getMakanNaikLevel() {
        return makannaiklevel;
    }

    public static String[] getDaftarGambar() {
        return daftargambar;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public int getJumlahMakanYangDimakan() {
        return jumlahmakanyangdimakan;
    }

    public void setWaktuKoin(double waktukoin) {
        this.waktukoin = waktukoin;
    }

    public static void setMakanNaikLevel(int makannaiklevel) {
        Guppy.makannaiklevel = makannaiklevel;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public void setJumlahMakanYangDimakan(int jumlahmakanyangdimakan) {
        this.jumlahmakanyangdimakan = jumlahmakanyangdimakan;
    }

    public void cekLevel(){
        level = Math.min(jumlahmakanyangdimakan/(makannaiklevel) +1,3);
    }

    public boolean keluarkanKoinGuppy(){
        double wk = this.getWaktuKoin();
        int wkk = this.getWaktuKeluarkanKoin();
        if (0/*time_since_start()*/ - wk >= wkk) {
            waktukoin = 0 /*time_since_start()*/;
            return true;
        }
        return false;
    }

    public void gerak(){
        Random rand = new Random();

        if (0/*time_since_start()*/ - this.getWaktuRandom() >= 3) {
            Posisi tujuan(rand.nextInt(0/*SCREEN_WIDTH*/), rand.nextInt(0/*SCREEN_HEIGHT*/)+100);
            this.setPointTujuan(tujuan);
            this.setWaktuRandom(0/*time_since_start()*/);
        }
        this.setArah(Math.atan2(this.getPointTujuan().getY()- this.getY(), this.getPointTujuan().getX() - this.getX()));
        if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
            this.setImage(daftargambar[this.getLevel()-1][1]);
        } else {
            this.setImage(daftargambar[this.getLevel()-1][0]);
        }
        this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
        this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
        if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 0.1 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 0.1) {
            Posisi tujuan(rand.nextInt(0/*SCREEN_WIDTH*/), rand.nextInt(0/*SCREEN_HEIGHT*/ +100);
            this.setPointTujuan(tujuan);
        }
        if (int(0/*time_since_start()*/) - int(this.getWaktuMakan()) == this.getTahanKenyang()) {
            this.setLapar(true);
        }
    }

    public int cariMakanGuppy(List<MakananIkan> listmakananikan) {

        Random rand = new Random();
        Posisi now(this.getX(), this.getY());
        int terdekat = listmakananikan.cariIndeksTerdekat(now);

        if (terdekat != -1) {
            this.setArah(Math.atan2(listmakananikan.getRef(terdekat).getY() - this.getY(), listmakananikan.getRef(terdekat).getX() - this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[this.getLevel()-1][3]);
            } else {
                this.setImage(daftargambar[this.getLevel()-1][2]);
            }
            this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this.getX() - listmakananikan.getRef(terdekat).getX()) < 0.1 && Math.abs(this.getY() - listmakananikan.getRef(terdekat).getY()) < 0.1) {
                this.setLapar(false);
                this.setWaktuMakan(0/*time_since_start()*/);
                jumlahmakanyangdimakan++;
                this.cekLevel();
                return terdekat;
            }
        } else {
            this.setArah(Math.atan2(this.getPointTujuan().getY()-this.getY(), this.getPointTujuan().getX()-this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[this.getLevel()-1][3]);
            } else {
                this.setImage(daftargambar[this.getLevel()-1][2]);
            }
            this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 0.1 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 0.1) {
                Posisi tujuan(rand.nextInt(0/*SCREEN_WIDTH*/, rand.nextInt(0/*SCREEN_HEIGHT*/+100);
                this.setPointTujuan(tujuan);
            }
        }

        return -1;
    }




}
