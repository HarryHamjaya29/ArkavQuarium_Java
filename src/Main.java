import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;


public class Main{
    public static Random rand = new Random();

    public static final int NOL = 0;
    public static final int MAX_NUMBER = 999999;
    public static final int SETENGAH_LINGKARAN = 180;
    public static final int SEPEREMPAT_LINGKARAN = 90;

    public static void main(String[] args){
        try {
            Akuarium akuarium = new Akuarium("image/guppy1kiri.png");
            akuarium.buildFrame();

            akuarium.setContent();
            akuarium.startAkuarium();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
