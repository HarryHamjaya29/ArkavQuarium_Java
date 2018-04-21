import java.util.Random;

public class DriverList {
    public static void main(String[] args){
        Random rand = new Random();
        List<Ikan> ikan = new List<Ikan>();

        Ikan newguppy = new Guppy(rand.nextInt(Akuarium.SCREEN_WIDTH), rand.nextInt(Akuarium.SCREEN_HEIGHT), 0, 2000);
        ikan.add(newguppy);

        System.out.println(ikan.getIdx(0).getX() + " " + ikan.getIdx(0).getY());
        System.out.println(ikan.getSize());
    }
}
