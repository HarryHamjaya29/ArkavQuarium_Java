import java.util.Random;

public class Ikan extends BendaAkuarium{
    private static final int banyakikan= 0;

    private boolean lapar;
    private final int tahankenyang = 9;
    private int waktumakan;
    private final int hunger = 15;
    private String type;

    private Posisi pointtujuan;
    double wakturandom;

    public Ikan(double x, double y, double arah, double kecepatan, String type){
        super(x,y,arah,kecepatan);

        lapar= false;
        waktumakan = 0;
        this.setImage("ikankiri.gif");
        this.type = type;
        waktumakan = 0 /*time_since_start()[]*/;
    }

    public static int getBanyakikan() {
        return banyakikan;
    }

    public boolean isLapar() {
        return lapar;
    }

    public void setLapar(boolean lapar) {
        this.lapar = lapar;
    }

    public int getTahanKenyang() {
        return tahankenyang;
    }

    public int getWaktuMakan() {
        return waktumakan;
    }

    public void setWaktuMakan(int waktumakan) {
        this.waktumakan = waktumakan;
    }

    public int getHunger() {
        return hunger;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Posisi getPointTujuan() {
        return pointtujuan;
    }

    public void setPointTujuan(Posisi pointtujuan) {
        this.pointtujuan = pointtujuan;
    }

    public double getWaktuRandom() {
        return wakturandom;
    }

    public void setWaktuRandom(double wakturandom) {
        this.wakturandom = wakturandom;
    }

    public void gerak(){
        Random rand = new Random();

        this.setArah(Math.atan2(pointtujuan.getY()- this.getY(), pointtujuan.getX()-this.getX()));
        if (this.getArah()*180/Math.PI>(-90) && this.getArah()*180/Math.PI < 90){
            this.setImage("ikankanan.png");
        } else {
            this.setImage("ikankiri.gif");
        }
        this.setX(this.getX() + this.getKecepatan()*Math.cos(this.getArah())*0.0001);
        this.setY(this.getY() + this.getKecepatan()*Math.sin(this.getArah())*0.0001);
        if (Math.abs(this.getY()- pointtujuan.getX()) < 0.1 && Math.abs(this.getY() - pointtujuan.getY())<0.1 ) {
            pointtujuan.setX(rand.nextInt(0/*SCREEN_WIDTH*/));
            pointtujuan.setY(rand.nextInt(0/*SCREEN_HEIGHT*/));
        }
        if ((int)0/*time_since_start()*/ - (int)waktumakan ==tahankenyang){
            lapar = true;
        }
    }

    public boolean mati(){
        if (0/*time_since_start()*/ - this.getWaktuMakan() - this.getTahanKenyang()>= this.getHunger()){
            return true;
        }
        return false;
    }

    public int cariIkanTerdekat(List<Ikan> listikan){
        return 0;
    }

    public int cariMakanGuppy(List<MakananIkan> listmakananikan){
        return 0;
    }

    public boolean keluarkanKoinGuppy(){
        return false;
    }

    public double getWaktuKoin(){
        return 0.0f;
    }

    public double getLevel(){
        return 0;
    }

    public int getJumlahMakanYangDimakan(){
        return 0;
    }

    public void setLevel(int x){

    }

    public void setJumlahMakanYangDimakan(int x){

    }

    public void setWaktuKoin(double x){

    }


}

