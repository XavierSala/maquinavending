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
    ERROR_JA_HI_ES(-1), 
    ERROR_MAQUINA_EN_MARXA(-2), 
    ERROR_MAQUINA_ATURADA(-3),
    ERROR_DIPOSIT_SENSE_DESCRIPCIO(-4),
    ERROR_DIPOSIT_REPETIT(-5),
    ERROR_DIPOSIT_PLE(-6)
    ;
    
    private int resultat;
    
    resultatMaquina(int resultat) {
        this.resultat = resultat;
    }
    
    int getResultat() {
        return resultat;
    }
};