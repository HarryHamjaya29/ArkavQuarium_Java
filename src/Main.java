import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;


public class Main{
    public static Random rand = new Random();

    public static void main(String[] args){
        try {
            Akuarium akuarium = new Akuarium("../../../image/guppy1kiri.png");
            akuarium.buildFrame();

            akuarium.setContent();
            akuarium.startAkuarium();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
