public class MakananIkan extends BendaAkuarium {
    public MakananIkan(double x, double y) {
        super(x, y, 0, 500);
        this.setImage("/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/makananikan");
    }

    public void gerak() {
        this.setY(this.getY() + 2);
    }

}
