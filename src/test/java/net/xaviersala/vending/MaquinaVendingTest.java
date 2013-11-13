package net.xaviersala.vending;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MaquinaVendingTest {

    MaquinaVending maquina; 
    ArrayList<Diposit<Beguda>> diposits;
    int[] monedes= { 5, 10, 20, 50, 100, 200 };
    
    
    @Before
    public void setUp() throws Exception {
        maquina = new MaquinaVending();
        
    }

    @Test
    public final void testPosarTreureBeguda() {
    	diposits = new ArrayList<Diposit<Beguda>>();
    	
    	// Dipòsits de monedes
    	
    	    	
    	// Dipòsits de begudes
    	String [] tipusDiposits = { "Aigua", "Suc de taronja", "Cola" };
    	int[] preuDiposits = { 100, 150, 125 };
    	
        for(int i=0; i<tipusDiposits.length; i++) {
        	diposits.add(new Diposit<Beguda>(tipusDiposits[i], preuDiposits[i]));
        }
    	
        assertTrue(maquina.posarBeguda("Aigua") 
                == resultatMaquina.DIPOSIT_INEXISTENT);
        // Creo els dipòsits per defecte. Aigua, Cola, Suc de Taronja
        maquina = new MaquinaVending(diposits);
        
        // Crear els dipòsits de monedes
    	for (int moneda : monedes) {
    		maquina.afegirDipositMonedes("€", moneda);
    	}
                        
        assertTrue(maquina.posarBeguda("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.posarBeguda("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.posarBeguda("Cola") == resultatMaquina.OK);
        assertTrue(maquina.posarBeguda("Cervesa") == resultatMaquina.DIPOSIT_INEXISTENT);
        assertTrue(maquina.posarBeguda(null) == resultatMaquina.ERROR);
        
        // Ara permetrem als clients comprar...
        // Ai no! que no l'hem engegat ;-)   
        assertNull(maquina.treureBeguda("Aigua"));
        
        // Engegar màquina
        maquina.setEnMarxa(true);
        
        // No ha posat monedes!, no surt
        assertNull(maquina.treureBeguda("Aigua"));
        
        // Posem 10 monedes de 1 €
        for (int i=0; i< 10; i++) {
        	assertTrue(maquina.posarMoneda(new Moneda(100, "€")) == resultatMaquina.OK);
        }
        // Trec les dues aigues
        assertNotNull(maquina.treureBeguda("Aigua"));
        assertNotNull(maquina.treureBeguda("Aigua"));
        // Ja no en queden...
        assertNull(maquina.treureBeguda("Aigua"));
        
        // Treure una beguda que no hi és
        assertNull(maquina.treureBeguda("Vi"));
        
        // Putejar el sistema ... 
        assertNull(maquina.treureBeguda(null));
                
    }

    /**
     * Comprovo el funcionament del sistema de gestió de dipòsits
     */
    @Test
    public final void testGestioDiposits() {
        
    	assertTrue(maquina.afegirDipositBegudes(null, 1) == resultatMaquina.ERROR);
        assertTrue(maquina.afegirDipositBegudes("Aigua", 100) == resultatMaquina.OK);
        assertTrue(maquina.afegirDipositBegudes("Suc de Pinya", 150) == resultatMaquina.OK);
        assertTrue(maquina.afegirDipositBegudes("Suc de Poma", 125) == resultatMaquina.OK);
        
        assertTrue(maquina.afegirDipositBegudes("Aigua", 100) == resultatMaquina.DIPOSIT_REPETIT);
        
        assertTrue(maquina.afegirDipositBegudes("Cervesa", 175) == resultatMaquina.OK);
        assertTrue(maquina.afegirDipositBegudes("Cervesa", 200) == resultatMaquina.DIPOSIT_REPETIT);
        
        assertTrue(maquina.treureDipositBegudes(null) == resultatMaquina.ERROR);
        assertTrue(maquina.treureDipositBegudes("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.treureDipositBegudes("Aigua") == resultatMaquina.DIPOSIT_INEXISTENT);
        
        assertTrue(maquina.treureDipositBegudes("Wisky") == resultatMaquina.DIPOSIT_INEXISTENT);
        
        maquina.setEnMarxa(true);
        
        assertTrue(maquina.afegirDipositBegudes("Vi", 200) == resultatMaquina.MAQUINA_EN_MARXA);
        assertTrue(maquina.treureDipositBegudes("Cerversa") == resultatMaquina.MAQUINA_EN_MARXA);
        
    }

    /**
     * Tornar canvi...
     */
    @Test
    public final void testTornarCanvi() {
    	for (int moneda : monedes) {
    		assertTrue(maquina.afegirDipositMonedes("€", 
    				moneda) == resultatMaquina.OK);
    	}
    	
    	maquina.setEnMarxa(true);
    	
    	assertTrue(maquina.posarMoneda(
    			new Moneda(100, "€")) == resultatMaquina.OK);
    	assertTrue(maquina.posarMoneda(
    			new Moneda(100, "€")) == resultatMaquina.OK);
    	assertTrue(maquina.posarMoneda(
    			new Moneda(50, "€")) == resultatMaquina.OK);    	
    	
    	List<Moneda> moneder = new ArrayList<Moneda>();
    	
    	// No es pot tornar perquè no tenim monedes
    	moneder = maquina.tornarMonedes(25);
    	assertNull(moneder);
    	
    	moneder = maquina.tornarMonedes(50);
    	assertNotNull(moneder);
    	assertTrue(moneder.size() == 1);
    	assertTrue(moneder.get(0).getValor() == 50);
    	
    	
    	
    	
    	
    	
    }

    /** 
     * Comprovo el funcionament de la opció d'arrancar i aturar la màquina.
     */
    @Test
    public final void testEnMarxa() {
        assertFalse(maquina.isEnMarxa());
        
        maquina.setEnMarxa(false);
        assertFalse(maquina.isEnMarxa());
        
        maquina.setEnMarxa(true);
        assertTrue(maquina.isEnMarxa());
        
        maquina.setEnMarxa(true);
        assertTrue(maquina.isEnMarxa());
        
        maquina.setEnMarxa(false);
        assertFalse(maquina.isEnMarxa());
        
    }

}
