import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Akuarium extends JPanel {
    public static int SCREEN_WIDTH = 853;
    public static int SCREEN_HEIGHT = 640;
    public static double PI = 3.14159265;
    public static int LOSE = 0;
    public static int WIN = 1;
    public static int LOAD = 0;
    public static int NEW = 1;
    public static int HOME = 0;
    public static int PLAY = 1;
    public static int FINISH = 2;

    private final String BACKGROUND_IMAGE = "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/Aquarium6.jpg";

    private BufferedImage defaultImage;
    private Map<String, BufferedImage> images;
    private int status;
    private long fps;
    private int playCategory = -1;
    private JFrame jFrame;
    private static long now;
    private static long start;

    private List<Ikan> ikan;
    private List<MakananIkan> makananikan;
    private List<Koin> koin;
    Siput siput;

    public static double time_since_start() {
        return (now - start)/(1e9);
    }

    public Akuarium(String defaultObjectImagePath) throws IOException {
        this.defaultImage = ImageIO.read(new File(defaultObjectImagePath));
        this.images = new HashMap<>();

        this.fps = 1000000000L / 128L;

        ikan = new List<Ikan>();

        playCategory = -1;
    }

    private BufferedImage readImage(String path) {
        BufferedImage newImage = this.images.get(path);
        if (newImage == null)  {
            try {
                newImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                newImage = defaultImage;
            }
            this.images.put(path, newImage);
        }
        return newImage;
    }

    public void buildFrame() {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        jFrame.setVisible(true);
    }

    public void setContent() {
        jFrame.setContentPane(this);
        jFrame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

//                System.out.println(e.getLocationOnScreen().getX() + ", " + e.getLocationOnScreen().getY());

                double mouseX = e.getLocationOnScreen().getX();
                double mouseY = e.getLocationOnScreen().getY();
            }
        });
    }

    public void startAkuarium() {
        status = HOME;

        playAkuarium();
    }

    public void playAkuarium() {
        Random rand = new Random();

        status = PLAY;

        long lastFrameStart = System.nanoTime();
        start = System.nanoTime();

        Ikan newguppy = new Guppy(rand.nextInt(SCREEN_WIDTH), rand.nextInt(SCREEN_HEIGHT), 0, 40000);
        ikan.add(newguppy);

        initDefault();

        while(true) {
//            System.out.println(ikan.getSize());

            now = System.nanoTime();

            if ((now - lastFrameStart) >= fps) {
                syncAll();
            }

//            System.out.println(time_since_start());
            jFrame.invalidate();
            jFrame.validate();
            jFrame.repaint();
            lastFrameStart = now;
        }
    }

    public void initDefault() {

    }

    public void syncAll() {
        int loop = 0;
        while(loop < ikan.getSize()) {
            if (ikan.getIdx(loop).getType().equals("Guppy")) {
                ikan.getIdx(loop).gerak();
            }
            loop++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Random rand = new Random();

        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.setColor(Color.WHITE);
        g.drawImage(readImage(BACKGROUND_IMAGE), 0, 0, null);

        for(int i = 0; i < ikan.getSize(); i++) {
            g.drawImage(readImage(ikan.getIdx(i).getImage()), (int)Math.floor(ikan.getIdx(i).getX()), (int)Math.floor(ikan.getIdx(i).getY()-100), null);
        }
    }
}
