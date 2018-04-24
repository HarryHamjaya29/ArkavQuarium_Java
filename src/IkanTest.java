import org.junit.Test;

import static org.junit.Assert.*;

public class IkanTest {

    @Test
    public void getLapar() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertFalse(test.getLapar());
    }

    @Test
    public void setLapar() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertFalse(test.getLapar());
        test.setLapar(true);
        assertTrue(test.getLapar());
    }

    @Test
    public void getTahanKenyang() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertEquals(9, test.getTahanKenyang());
    }

    @Test
    public void getWaktuMakan() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertEquals(0f, test.getWaktuMakan(), 1e-10);
    }

    @Test
    public void setWaktuMakan() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertEquals(0f, test.getWaktuMakan(), 1e-10);
        test.setWaktuMakan(5.6f);
        assertEquals(5.6f, test.getWaktuMakan(), 1e-10);
    }

    @Test
    public void getHunger() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertEquals(15, test.getHunger());
    }

    @Test
    public void getType() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertEquals("piranha", test.getType());
        Ikan test2 = new Guppy(30, 30, 3.14, 100);
        assertEquals("guppy", test.getType());
    }

    @Test
    public void setType() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        assertEquals("piranha", test.getType());
        test.setType("dory");
        assertEquals("dory", test.getType());
    }

    @Test
    public void getPointTujuan() {
        Ikan test = new Piranha(30, 30, 3.14, 100);
        Ikan test2 = new Piranha(30, 30, 3.14, 100);
        assertNotEquals(test.getPointTujuan(), test2.getPointTujuan());
    }

    @Test
    public void setPointTujuan() {
        //Posisi
    }

    @Test
    public void getWaktuRandom() {
    }

    @Test
    public void setWaktuRandom() {
    }

    @Test
    public void gerak() {
    }

    @Test
    public void mati() {
    }

    @Test
    public void cariIkanTerdekat() {
    }

    @Test
    public void cariMakanGuppy() {
    }

    @Test
    public void keluarkanKoinGuppy() {
    }

    @Test
    public void getWaktuKoin() {
    }

    @Test
    public void getLevel() {
    }

    @Test
    public void getJumlahMakanYangDimakan() {
    }

    @Test
    public void setLevel() {
    }

    @Test
    public void setJumlahMakanYangDimakan() {
    }

    @Test
    public void setWaktuKoin() {
    }
}