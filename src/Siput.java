/**
 * Kelas siput ialah turunan dari kelas Benda Akuarium yang merupakan
 * benda yang mengumpulkan koin.
 */
public class Siput extends BendaAkuarium {

  /**
   * Kecepatan dari siput.
   */
  private static final int KECEPATAN_SIPUT = 20000;

  /**
   * Radius ketika siput tidak bergerak lagi.
   */
  private static final double RADIUS_GERAKAN = 0.1;

  /**
   * Ratio Kecepatan dari siput untuk menyesuaikan dengan fps.
   */
  private static final double RATIO_KECEPATAN = 0.0001;

  /**
   * Jarak dari dasar koin pada akuarium.
   */
  private static final int DASAR_KOIN = 20;
  /**
   * Point tujuan dari siput untuk gerakan selanjutya.
   */
  private Posisi pointtujuan;

  /**
   * Mengembalikan PointTujuan dari Siput.
   * @return Posisi
   */
  public Posisi getPointtujuan() {
    return pointtujuan;
  }

  /**
   * Untuk mengganti point tujuan dari Siput.
   * @param x .
   */
  public void setPointtujuan(final Posisi x) {
    this.pointtujuan = x;
  }

  /**
   * Constructor.
   */
  public Siput() {
    super(Main.rand.nextInt(Akuarium.SCREEN_WIDTH),
            Akuarium.SCREEN_HEIGHT, 0, KECEPATAN_SIPUT);
    pointtujuan = new MakananIkan(Main.rand.nextInt(Akuarium.SCREEN_WIDTH),
            Main.rand.nextInt(Akuarium.SCREEN_HEIGHT));
    pointtujuan.setX(Main.rand.nextInt(Akuarium.SCREEN_WIDTH));
    pointtujuan.setY(Main.rand.nextInt(Akuarium.SCREEN_HEIGHT));
    this.setImage("image/siputkanan.png");
  }

  /**
   * Constructor dengan parameter.
   * @param x         .
   * @param y         .
   * @param a         .
   * @param kecepatan .
   */
  public Siput(final double x, final double y,
               final double a, final double kecepatan) {
    super(x, y, a, kecepatan);
    pointtujuan = new MakananIkan(Math.random() % Akuarium.SCREEN_WIDTH,
            Math.random() % Akuarium.SCREEN_HEIGHT);
    this.setImage("image/siputkanan.png");
  }

  /**
   * Untuk melakukan gerakan dari siput.
   */
  @Override
  public void gerak() {
    boolean kanansiput = true;

    if ((pointtujuan.getX() - this.getX()) > 0) {
      kanansiput = true;
      this.setImage("image/siputkanan.png");
    } else {
      kanansiput = false;
      this.setImage("image/siputkiri.png");
    }

    if ((kanansiput) && (this.getX() != Akuarium.SCREEN_WIDTH)
            && (Math.abs(this.getX() - pointtujuan.getX())
            > RADIUS_GERAKAN)) {
      this.setX(this.getX() + this.getKecepatan() * RATIO_KECEPATAN);
    } else if ((this.getX() != Main.NOL) && (Math.abs(this.getX()
            - pointtujuan.getX()) > RADIUS_GERAKAN)) {
      this.setX(this.getX() - this.getKecepatan() * RATIO_KECEPATAN);
    }
  }

  /**
   * Untuk mencari apakah ada koin yang dapat dituju oleh siput.
   * @param listkoin .
   * @return int.
   */
  int cariKoin(final List<Koin> listkoin) {
    boolean kanansiput = true;
    boolean ketemudasar = false;

    int terdekat = -1;

    Posisi tujuan = new Siput(this.getX(), this.getY(), Main.NOL, Main.NOL);
    Posisi now = new Siput(this.getX(), this.getY(), Main.NOL, Main.NOL);

    for (int i = 0; i < listkoin.getSize() && !ketemudasar; i++) {
      if (Math.abs(listkoin.getIdx(i).getY() - Akuarium.SCREEN_HEIGHT
              - DASAR_KOIN) < 1) {
        ketemudasar = true;
      }
    }

    if (ketemudasar) {
      tujuan.setX(Main.MAX_NUMBER);
      tujuan.setY(Akuarium.SCREEN_HEIGHT);
      for (int i = 0; i < listkoin.getSize(); i++) {
        if (Math.abs(listkoin.getIdx(i).getY() - Akuarium.SCREEN_HEIGHT
                - DASAR_KOIN) < Akuarium.RADIUS_PENGAMBILAN) {
          if (Math.abs(now.getX() - listkoin.getIdx(i).getX())
                  < Math.abs(now.getX() - tujuan.getX())) {
            terdekat = i;
            tujuan.setX(listkoin.getIdx(i).getX());
          }
        }
      }
    } else {
      tujuan.setX(this.getX());
      tujuan.setY(0);
      for (int i = 0; i < listkoin.getSize(); i++) {
        if (listkoin.getIdx(i).getY() > tujuan.getY()) {
          terdekat = i;
          tujuan.setX(listkoin.getIdx(i).getX());
          tujuan.setY(listkoin.getIdx(i).getY());
        }
      }
    }

    if (terdekat != -1) {
      this.setArah(Math.atan2(listkoin.getIdx(terdekat).getY()
              - this.getY(), listkoin.getIdx(terdekat).getX()
              - this.getX()));
      if (this.getArah() * Main.SETENGAH_LINGKARAN / Akuarium.PI
              > Main.SETENGAH_LINGKARAN
              && this.getArah() * Main.SETENGAH_LINGKARAN / Akuarium.PI
              < Main.SETENGAH_LINGKARAN) {
        this.setImage("image/siputkanan.png");
        kanansiput = true;
      } else {
        this.setImage("image/siputkiri.png");
        kanansiput = false;
      }

      if ((kanansiput) && (this.getX() != Akuarium.SCREEN_WIDTH)
              && (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX())
              > 1)) {
        this.setX(this.getX() + this.getKecepatan() * RATIO_KECEPATAN);
      } else if ((this.getX() != 0) && (Math.abs(this.getX()
              - listkoin.getIdx(terdekat).getX()) > 1)) {
        this.setX(this.getX() - this.getKecepatan() * RATIO_KECEPATAN);
      }
      if (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX())
              < Akuarium.RADIUS_PENGAMBILAN
              && Math.abs(this.getY() - listkoin.getIdx(terdekat).getY())
              < DASAR_KOIN + 1) {
        return terdekat;
      }
    }

    return -1;
  }
}
