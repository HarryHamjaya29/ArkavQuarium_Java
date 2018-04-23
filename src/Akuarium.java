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
    private final String TOOLBAR_IMAGE = "/media/mhabibih/08966A79966A66E2/ITB/Semester 4/Orientasi Objek Pemrograman/ArkavQuarium_Java/image/tabatas.png";

    private BufferedImage defaultImage;
    private Map<String, BufferedImage> images;
    private int status;
    private long fps;
    private boolean kurangkoin = false;
    private int playCategory = -1;
    private JFrame jFrame;
    private static long now;
    private static long start;

    private Player player = new Player();
    private List<Ikan> ikan;
    private List<MakananIkan> makananikan;
    private List<Koin> koin;
    Siput siput;

    public static double time_since_start() {
        return (now - start)/(1e9);
    }

    public Akuarium(String defaultObjectImagePath) throws IOException {
        Random rand = new Random();

        this.defaultImage = ImageIO.read(new File(defaultObjectImagePath));
        this.images = new HashMap<>();

        this.fps = 1000000000L / 128L;

        ikan = new List<Ikan>();
        makananikan = new List<MakananIkan>();
        koin = new List<Koin>();
        siput = new Siput();

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
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void setContent() {
        jFrame.setContentPane(this);
        jFrame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double mouseX = e.getLocationOnScreen().getX();
                double mouseY = e.getLocationOnScreen().getY();

                mouseX -= 291;
                mouseY -= 105;

                if (status == PLAY) {

                    int ketemu = -1;
                    for(int i = 0; i < koin.getSize() && ketemu == -1; i++) {
                        if (Math.abs(koin.getIdx(i).getX() - mouseX) < 30 && Math.abs(koin.getIdx(i).getY() - mouseY-100) < 30) {
                            ketemu = i;
                        }
                    }
                    if (ketemu != -1) {
                        player.tambahKoin(koin.getIdx(ketemu).getNilai());
                        koin.removeIdx(ketemu);
                    } else {
                        if ((mouseX <= 107 && mouseX >= 48) && (mouseY <= 66 && mouseY >= 23)) {
                            if (player.getJumlahkoin() < 100) {
                                kurangkoin = true;
                            } else {
                                Ikan newguppy = new Guppy(Main.rand.nextInt(SCREEN_WIDTH), Main.rand.nextInt(SCREEN_HEIGHT), 0, 40000);
                                ikan.add(newguppy);
                                player.kurangkanKoin(100);
                                kurangkoin = false;
                            }
                        } else if ((mouseX <= 221 && mouseX >= 160) && (mouseY <= 69 && mouseY >= 18)) {
                            if (player.getJumlahkoin() < 200) {
                                kurangkoin = true;
                            } else {
                                Ikan newpiranha = new Piranha(Main.rand.nextInt(SCREEN_WIDTH), Main.rand.nextInt(SCREEN_HEIGHT), 0, 60000);
                                ikan.add(newpiranha);
                                player.kurangkanKoin(200);
                                kurangkoin = false;
                            }
                        } else if ((mouseX <= 676 && mouseX >= 614) && (mouseY <= 67 && mouseY >= 18)) {
                            if (player.getJumlahkoin() < 500) {
                                kurangkoin = true;
                            } else {
                                player.kurangkanKoin(500);
                                player.tambahTelur();
                                kurangkoin = false;
                            }
                        } else if ((mouseX > 753 || mouseX < 683) || (mouseY > 135 || mouseY < 65)) {
                            if (player.getJumlahkoin() < 5) {
                                kurangkoin = true;
                            } else {
                                MakananIkan newmakananikan = new MakananIkan(mouseX, mouseY + 100);
                                makananikan.add(newmakananikan);
                                player.kurangkanKoin(5);
                                kurangkoin = false;
                            }
                        }
                    }
                }
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

        int dapatkoin = siput.cariKoin(koin);

        if (dapatkoin != -1) {
            koin.removeIdx(dapatkoin);
        }

        int loop = 0;
        while(loop < ikan.getSize()) {
            if (ikan.getIdx(loop).getType().equals("Guppy")) {
                if (ikan.getIdx(loop).mati()) {
                    ikan.removeIdx(loop);
                    continue;
                }
                if (ikan.getIdx(loop).getLapar()) {
                    int makanandimakan = ikan.getIdx(loop).cariMakanGuppy(makananikan);
                    if (makanandimakan != -1) {
                        makananikan.removeIdx(makanandimakan);
                    }
                } else {
                    ikan.getIdx(loop).gerak();
                }
                if (ikan.getIdx(loop).keluarkanKoinGuppy()) {
                    if (ikan.getIdx(loop).getLevel() == 1) {
                        Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 10, 1);
                        koin.add(newkoin);
                    } else if (ikan.getIdx(loop).getLevel() == 2) {
                        Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 20, 2);
                        koin.add(newkoin);
                    } else if (ikan.getIdx(loop).getLevel() == 3) {
                        Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 50, 3);
                        koin.add(newkoin);
                    }
                }
            } else {
                if (ikan.getIdx(loop).mati()) {
                    ikan.removeIdx(loop);
                    continue;
                }
                if (ikan.getIdx(loop).getLapar()) {
                    int ikandimakan = ikan.getIdx(loop).cariIkanTerdekat(ikan);
                    if (ikandimakan != -1) {
                        Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 100*(ikan.getIdx(ikandimakan).getLevel() + 1), 4);
                        koin.add(newkoin);
                        ikan.removeIdx(ikandimakan);
                    }
                } else {
                    ikan.getIdx(loop).gerak();
                }
            }
            loop++;
        }

        for(int i = 0; i < makananikan.getSize(); i++) {
            makananikan.getIdx(i).gerak();
            if (Math.abs(makananikan.getIdx(i).getY() - SCREEN_HEIGHT - 70) < 5) {
                makananikan.removeIdx(i);
            }
        }

        for(int i = 0; i < koin.getSize(); i++) {
            koin.getIdx(i).gerak();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.setColor(Color.WHITE);
        g.drawImage(readImage(BACKGROUND_IMAGE), 0, 0, null);
        g.drawImage(readImage(TOOLBAR_IMAGE), 0, 0, null);

        for(int i = 0; i < ikan.getSize(); i++) {
            g.drawImage(readImage(ikan.getIdx(i).getImage()), (int)Math.floor(ikan.getIdx(i).getX()) - 80/2, (int)Math.floor(ikan.getIdx(i).getY()-100)-80/2, null);
        }

        for(int i = 0; i < makananikan.getSize(); i++) {
            g.drawImage(readImage(makananikan.getIdx(i).getImage()), (int)Math.floor(makananikan.getIdx(i).getX())-40/2, (int)Math.floor(makananikan.getIdx(i).getY())-100-40/2, null);
        }

        for(int i = 0; i < koin.getSize(); i++) {
            g.drawImage(readImage(koin.getIdx(i).getImage()), (int)Math.floor(koin.getIdx(i).getX())-65/2, (int)Math.floor(koin.getIdx(i).getY())-100-30/2, null);
        }

        g.drawImage(readImage(siput.getImage()), (int)Math.floor(siput.getX())-100/2, (int)Math.floor(siput.getY())-100-141/2, null);

        g.drawString("100", 60, 93);
        g.drawString("200", 174, 93);
        g.drawString("500", 631, 93);

        if (kurangkoin) {
            g.setColor(Color.RED);
            g.drawString(player.getJumlahkoin() + "", 752, 93);
        } else {
            g.drawString(player.getJumlahkoin() + "", 752, 93);
        }
    }
}
