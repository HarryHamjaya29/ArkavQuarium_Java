import java.util.Random;
import java.lang.String;

public class Guppy extends Ikan{
    private final int waktukeluarkankoin = 5;
    private double waktukoin;
    private static int makannaiklevel = 5;
    private static String[][] daftargambar = {
        {   "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppy1kiri.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppy1kanan.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppylapar1kiri.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppylapar1kanan.png"},
        {   "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppy2kiri.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppy2kanan.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppylapar2kiri.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppylapar2kanan.png"},
        {   "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppy3kiri.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppy3kanan.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppylapar3kiri.png",
            "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/guppylapar3kanan.png"}
    };
    private int level;
    private int jumlahmakanyangdimakan;

    public Guppy(int x, int y, double arah, double kecepatan){
        super(x,y,arah,kecepatan,"Guppy");
        level = 1;
        jumlahmakanyangdimakan = 0;
        waktukoin= Akuarium.time_since_start();
        this.setImage(daftargambar[0][0]);
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
        if (Akuarium.time_since_start() - wk >= wkk) {
            waktukoin = Akuarium.time_since_start();
            return true;
        }
        return false;
    }

    public void gerak(){
        Random rand = new Random();
        if (Akuarium.time_since_start() - this.getWaktuRandom() >= 3) {
            Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH), rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0,0);
            this.setPointTujuan(tujuan);
            this.setWaktuRandom(Akuarium.time_since_start());
        }
        this.setArah(Math.atan2(this.getPointTujuan().getY()- this.getY(), this.getPointTujuan().getX() - this.getX()));

        if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
            this.setImage(daftargambar[this.getLevel()-1][1]);
        } else {
            this.setImage(daftargambar[this.getLevel()-1][0]);
        }
        this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
        this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
        if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 1 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 1) {
            Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH), rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0,0);
            this.setPointTujuan(tujuan);
        }
        if ((int)Akuarium.time_since_start() - (int)this.getWaktuMakan() == this.getTahanKenyang()) {
            this.setLapar(true);
        }
    }

    public int cariMakananIkanTerdekat(List<MakananIkan> listmakananikan) {
        double min = 99999999;
        int indeksmin = -1;

        Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

        for(int i = 0; i < listmakananikan.getSize(); i++) {
            Posisi a = new Siput(listmakananikan.getIdx(i).getX(), listmakananikan.getIdx(i).getY(), 0, 0);
            if (min > now.hitungjarak(a)) {
                indeksmin = i;
                min = now.hitungjarak(a);
            }
        }

        return indeksmin;
    }

    public int cariMakanGuppy(List<MakananIkan> listmakananikan) {

        Random rand = new Random();

        Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

        int terdekat = this.cariMakananIkanTerdekat(listmakananikan);

        if (terdekat != -1) {
            this.setArah(Math.atan2(listmakananikan.getIdx(terdekat).getY() - this.getY(), listmakananikan.getIdx(terdekat).getX() - this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[this.getLevel()-1][3]);
            } else {
                this.setImage(daftargambar[this.getLevel()-1][2]);
            }
            this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this.getX() - listmakananikan.getIdx(terdekat).getX()) < 1 && Math.abs(this.getY() - listmakananikan.getIdx(terdekat).getY()) < 1) {
                this.setLapar(false);
                this.setWaktuMakan(Akuarium.time_since_start());
                jumlahmakanyangdimakan++;
                this.cekLevel();
                return terdekat;
            }
        } else {
            if (Akuarium.time_since_start() - this.getWaktuRandom() >= 3) {
                Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH), rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0,0);
                this.setPointTujuan(tujuan);
                this.setWaktuRandom(Akuarium.time_since_start());
            }
            this.setArah(Math.atan2(this.getPointTujuan().getY()-this.getY(), this.getPointTujuan().getX()-this.getX()));
            if (this.getArah()*180/Math.PI > -90 && this.getArah()*180/Math.PI < 90) {
                this.setImage(daftargambar[this.getLevel()-1][3]);
            } else {
                this.setImage(daftargambar[this.getLevel()-1][2]);
            }
            this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
            this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
            if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 1 && Math.abs(this.getY() - this.getPointTujuan().getY()) < 1) {
                Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH), rand.nextInt(Akuarium.SCREEN_HEIGHT)+100, 0,0);
                this.setPointTujuan(tujuan);
            }
        }

        return -1;
    }




}
