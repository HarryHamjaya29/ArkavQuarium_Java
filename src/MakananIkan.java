public class MakananIkan extends BendaAkuarium {
    public MakananIkan(double x, double y) {
        super(x, y, 0, 500);
        this.setImage("makananikan");
    }

    public void gerak() {
        if (Math.abs(this.getY() - 640) > 0.1) {
            this.setY(this.getY() + 0.1);
        }
    }

}
