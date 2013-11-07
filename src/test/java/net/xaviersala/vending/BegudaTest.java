package net.xaviersala.vending;

import static org.junit.Assert.*;

import org.junit.Test;

public class BegudaTest {
   

    /**
     * Comprovar que la funció set/get de capacitat està ben implementada.
     */
    @Test
    public final void testSetGetCapacitatCC() {
        Beguda beure1 = new Beguda(); 
        assertEquals(0, beure1.getCapacitatCC());
        
        beure1.setCapacitatCC(50);                       
        assertEquals(50, beure1.getCapacitatCC());
        
        beure1.setCapacitatCC(33);                       
        assertEquals(33, beure1.getCapacitatCC());
        
        // Comprovar que no s'admeten valors errònis
        beure1.setCapacitatCC(-1);
        assertEquals(33, beure1.getCapacitatCC());
        
        beure1.setCapacitatCC(0);
        assertEquals(33, beure1.getCapacitatCC());                
    }

    /**
     * Comprovar que la funció set/get de la composició està ben implementada.
     */
    @Test
    public final void testSetGetComposicio() {
        Beguda beure1 = new Beguda(); 
        
        beure1.setComposicio("H2O2");                       
        assertEquals("H2O2", beure1.getComposicio());
        
        beure1.setComposicio("H2O");                   
        assertEquals("H2O", beure1.getComposicio());
        
        // Comprovar que no s'admeten valors errònis
        beure1.setComposicio(null);
        assertEquals("H2O", beure1.getComposicio());        
    }

    /**
     * Comprovar que la funció set/get de la descripció de la beguda
     * està ben implementada.
     */
    @Test
    public final void testGetSetDescripcio() {
        Beguda beure1 = new Beguda(); 
        assertEquals("", beure1.getDescripcio());
        
        beure1.setDescripcio("Aigua");                       
        assertEquals("H2O2", beure1.getDescripcio());
        
        beure1.setDescripcio("Suc de taronja");                   
        assertEquals("Suc de taronja", beure1.getDescripcio());
        
        // Comprovar que no s'admeten valors errònis
        beure1.setDescripcio(null);
        assertEquals("Suc de taronja", beure1.getDescripcio());             
    }


}
