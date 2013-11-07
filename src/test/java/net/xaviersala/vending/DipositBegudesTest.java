package net.xaviersala.vending;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DipositBegudesTest {
    
    DipositBegudes dipositBuit;
    DipositBegudes dipositPleAigua;
    ArrayList<Beguda> b;
    Beguda begudeta;

    @Before
    public void setUp() throws Exception {
        begudeta = new Beguda("Aigua");
        
        dipositBuit = new DipositBegudes();
        b = new ArrayList<Beguda>();
        b.add(new Beguda("Aigua"));
        b.add(begudeta);
        
        dipositPleAigua = new DipositBegudes("Aigua", 0.9f, b.size(), b);
    }

    @Test
    public final void testSetGetNomBeguda() {
        
        assertEquals("Desconeguda", dipositBuit.getNomBeguda());
        
        dipositBuit.setNomBeguda("Cola");
        assertEquals("Cola", dipositBuit.getNomBeguda());
        
        dipositBuit.setNomBeguda(null);
        assertEquals("Cola", dipositBuit.getNomBeguda());
    }

    @Test
    public final void testGetSetPreuBeguda() {
        
        assertTrue(dipositBuit.getPreuBeguda() == 0);

        dipositBuit.setPreuBeguda(2.5f);
        assertTrue(dipositBuit.getPreuBeguda() == 2.5d);

        dipositBuit.setPreuBeguda(-3);
        assertTrue(dipositBuit.getPreuBeguda() == 2.5d);

    }


    @Test
    public final void testDipositPleiBuit() {
        assertTrue(dipositBuit.isDipositBuit());
        assertFalse(dipositBuit.isDipositPle());
        
        assertTrue(dipositPleAigua.isDipositPle());
        assertFalse(dipositPleAigua.isDipositBuit());

    }


    @Test
    public final void testAfegirBeguda() {
        int numBegudes = dipositBuit.quantesBegudesQueden();
        assertEquals(resultatMaquina.OK, dipositBuit.AfegirBeguda());
        assertEquals(numBegudes+1, dipositBuit.quantesBegudesQueden());
        
        assertEquals(resultatMaquina.DIPOSIT_PLE, 
                dipositPleAigua.AfegirBeguda());
        assertEquals(b.size(), dipositPleAigua.quantesBegudesQueden());
        
    }

    @Test
    public final void testTreuBeguda() {
        assertEquals(null, dipositBuit.TreuBeguda());
        
        // Treure dues aigues, la segona ha de ser "begudeta"
        Beguda resultat = dipositPleAigua.TreuBeguda();             
        assertTrue(resultat != null);
        assertEquals("Aigua", resultat.getDescripcio());
        assertSame(begudeta, dipositPleAigua.TreuBeguda());
    }


}
