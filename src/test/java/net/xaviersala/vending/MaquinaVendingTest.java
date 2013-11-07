package net.xaviersala.vending;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaquinaVendingTest {

    MaquinaVending maquina; 
    
    @Before
    public void setUp() throws Exception {
        maquina = new MaquinaVending();
    }

    @Test
    public final void testPosarTreureBeguda() {
        assertTrue(maquina.posarBeguda("Aigua") 
                == resultatMaquina.DIPOSIT_INEXISTENT);
                
    }

    /**
     * Comprovo el funcionament del sistema de gestió de dipòsits
     */
    @Test
    public final void testGestioDiposits() {
        
        assertTrue(maquina.afegirDiposit("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Suc de Pinya") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Suc de Poma") == resultatMaquina.OK);
        
        assertTrue(maquina.afegirDiposit("Aigua") == resultatMaquina.DIPOSIT_REPETIT);
        
        assertTrue(maquina.afegirDiposit("Cervesa") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Cervesa") == resultatMaquina.DIPOSIT_REPETIT);
        
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
