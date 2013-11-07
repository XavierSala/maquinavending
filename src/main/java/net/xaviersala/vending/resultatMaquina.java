/**
 * 
 */
package net.xaviersala.vending;

/**
 * @author Xavier
 *
 */
public enum resultatMaquina {
    
    OK(0), 
    DIPOSIT_REPETIT(-1), 
    MAQUINA_EN_MARXA(-2), 
    MAQUINA_ATURADA(-3),
    DIPOSIT_SENSE_DESCRIPCIO(-4),
    DIPOSIT_INEXISTENT(-5),
    DIPOSIT_PLE(-6);
    
    private int resultat;
    
    resultatMaquina(int resultat) {
        this.resultat = resultat;
    }
    
    int getResultat() {
        return resultat;
    }
};