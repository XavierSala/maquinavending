package net.xaviersala.vending;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DipositBegudesTest {
    
    Diposit<Beguda> dipositBuit;
    Diposit<Beguda> dipositPleAigua;
    ArrayList<Beguda> b;
    Beguda begudeta;

    @Before
    public void setUp() throws Exception {
        begudeta = new Beguda("Aigua");
        
        dipositBuit = new Diposit<Beguda>();
        b = new ArrayList<Beguda>();
        b.add(new Beguda("Aigua"));
        b.add(begudeta);
        
        dipositPleAigua = new Diposit<Beguda>("Aigua", 0.9f, b.size(), b);
    }

    @Test
    public final void testSetGetNomBeguda() {
        
        assertEquals("Desconeguda", dipositBuit.getNom());
        
        dipositBuit.setNom("Cola");
        assertEquals("Cola", dipositBuit.getNom());
        
        dipositBuit.setNom(null);
        assertEquals("Cola", dipositBuit.getNom());
    }

    /** 
     * Comprovar el preu de la beguda
     */
    @Test
    public final void testGetSetValor() {
        
        assertTrue(dipositBuit.getValor() == 0);

        dipositBuit.setValor(2.5f);
        assertTrue(dipositBuit.getValor() == 2.5d);

        dipositBuit.setValor(-3);
        assertTrue(dipositBuit.getValor() == 2.5d);

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
        int numBegudes = dipositBuit.quantQueda();
        assertEquals(resultatMaquina.OK, dipositBuit.Afegir(begudeta));
        assertEquals(numBegudes+1, dipositBuit.quantQueda());
        
        assertEquals(resultatMaquina.DIPOSIT_PLE, 
                dipositPleAigua.Afegir(new Beguda("Aigua")));
        assertEquals(b.size(), dipositPleAigua.quantQueda());
        
    }

    @Test
    public final void testTreuBeguda() {
        assertNull(dipositBuit.Treu());
        
        // Treure dues aigues, la segona ha de ser "begudeta"
        Beguda resultat = dipositPleAigua.Treu();             
        assertTrue(resultat != null);
        assertEquals("Aigua", resultat.getDescripcio());
        assertSame(begudeta, dipositPleAigua.Treu());
    }


}
