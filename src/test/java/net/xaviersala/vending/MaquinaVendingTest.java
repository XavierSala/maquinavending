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
        fail("Not yet implemented");
    }

    @Test
    public final void testGestioDiposits() {
        
        assertTrue(maquina.afegirDiposit("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Aigua") == resultatMaquina.ERROR_JA_HI_ES);
        
        assertTrue(maquina.afegirDiposit("Cervesa") == resultatMaquina.OK);
        assertTrue(maquina.afegirDiposit("Cervesa") == resultatMaquina.ERROR_JA_HI_ES);
        
        assertTrue(maquina.treureDiposit("Aigua") == resultatMaquina.OK);
        assertTrue(maquina.treureDiposit("Aigua") == resultatMaquina.ERROR_NO_HI_ES);
        
        assertTrue(maquina.treureDiposit("Wisky") == resultatMaquina.ERROR_NO_HI_ES);
        
        maquina.setEnMarxa(true);
        
        assertTrue(maquina.afegirDiposit("Vi") == resultatMaquina.ERROR_MAQUINA_EN_MARXA);
        assertTrue(maquina.treureDiposit("Cerversa") == resultatMaquina.ERROR_MAQUINA_EN_MARXA);
        
    }



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
