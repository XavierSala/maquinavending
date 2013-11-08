package net.xaviersala.vending;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MaquinaVendingTest {

    MaquinaVending maquina; 
    ArrayList<DipositBegudes> diposits;
    
    @Before
    public void setUp() throws Exception {
        maquina = new MaquinaVending();
        
    }

    @Test
    public final void testPosarTreureBeguda() {
    	diposits = new ArrayList<DipositBegudes>();
    	String [] tipusDiposits = { "Aigua", "Suc de taronja", "Cola" };
        for(String textDiposit: tipusDiposits) {
        	diposits.add(new DipositBegudes(textDiposit));
        }
    	
        assertTrue(maquina.posarBeguda("Aigua") 
                == resultatMaquina.DIPOSIT_INEXISTENT);
        // Creo els dipòsits per defecte. Aigua, Cola, Suc de Taronja
        maquina = new MaquinaVending(diposits);
        
        assertTrue(maquina.posarBeguda("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.posarBeguda("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.posarBeguda("Cola") == resultatMaquina.OK);
        assertTrue(maquina.posarBeguda("Cervesa") == resultatMaquina.DIPOSIT_INEXISTENT);
        assertTrue(maquina.posarBeguda(null) == resultatMaquina.ERROR);
        
        // Ara permetrem als clients comprar...
        
        assertNull(maquina.treureBeguda("Aigua"));
        
        // Ai no! que no l'hem engegat ;-)        
        maquina.setEnMarxa(true);
        
        // Buidar un dipòsit
        assertNotNull(maquina.treureBeguda("Aigua"));
        assertNotNull(maquina.treureBeguda("Aigua"));
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
        
    	assertTrue(maquina.afegirDiposit(null) == resultatMaquina.ERROR);
        assertTrue(maquina.afegirDiposit("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Suc de Pinya") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Suc de Poma") == resultatMaquina.OK);
        
        assertTrue(maquina.afegirDiposit("Aigua") == resultatMaquina.DIPOSIT_REPETIT);
        
        assertTrue(maquina.afegirDiposit("Cervesa") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Cervesa") == resultatMaquina.DIPOSIT_REPETIT);
        
        assertTrue(maquina.treureDiposit(null) == resultatMaquina.ERROR);
        assertTrue(maquina.treureDiposit("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.treureDiposit("Aigua") == resultatMaquina.DIPOSIT_INEXISTENT);
        
        assertTrue(maquina.treureDiposit("Wisky") == resultatMaquina.DIPOSIT_INEXISTENT);
        
        maquina.setEnMarxa(true);
        
        assertTrue(maquina.afegirDiposit("Vi") == resultatMaquina.MAQUINA_EN_MARXA);
        assertTrue(maquina.treureDiposit("Cerversa") == resultatMaquina.MAQUINA_EN_MARXA);
        
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
