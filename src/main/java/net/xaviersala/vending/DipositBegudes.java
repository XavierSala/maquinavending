/**
 * 
 */
package net.xaviersala.vending;

import java.util.ArrayList;
import java.util.List;


/**
 * Implemeta el funcionament d'un dipòsit de begudes d'un determinat tipus.
 *
 * @author Xavier
 *
 */
public class DipositBegudes {
    
    /**
     * Resultat de les operacions amb el dipòsit codificades amb valors
     * 
     * @author Xavier
     *
     */
    enum resultatDiposit { 
        
        DIPOSITPLE(0), BEGUDAAFEGIDA(1), DIPOSITSENSEDESCRIPCIO(-1);
        
        private int resultat;
        
        resultatDiposit(int resultat) {
            this.resultat = resultat;
        }
        
        int getResultat() {
            return resultat;
        }
    };

    
    
    /**
     * Definim la capacitat per defecte
     */
    public static final int CAPACITATDIPOSITDEFECTE = 10;
    
    String nomBeguda;
    float preuBeguda;
    int capacitatDiposit;
    List<Beguda> begudes;
    
    /**
     * Crea un dipòsit sense dades del que hi haurà a dins.
     *
     * No deixarem que s'hi posin begudes per evitar problemes
     */
    public DipositBegudes() {
        nomBeguda = "Desconeguda";
        preuBeguda = 0;
        capacitatDiposit = CAPACITATDIPOSITDEFECTE;
        begudes = new ArrayList<Beguda>();
    }
    
    /**
     * Crea un dipòsit de begudes i li assigna el tipus de beguda.
     * Deixa el preu a zero
     * @param beguda El nom de la beguda a posar
     */
    public DipositBegudes(String beguda) {
        nomBeguda = beguda;
        preuBeguda = 0;
        capacitatDiposit = CAPACITATDIPOSITDEFECTE;
        begudes = new ArrayList<Beguda>();
    }
    
    /**
     * Crea un dipòsit de begudes i li assigna un tipus de beguda i un preu.
     * @param beguda la beguda que es posarà en el dipòsit
     * @param preu el preu de la beguda
     */
    public DipositBegudes(String beguda, float preu) {
        nomBeguda = beguda;
        preuBeguda = preu;
        capacitatDiposit = CAPACITATDIPOSITDEFECTE;
        begudes = new ArrayList<Beguda>();        
    }

    /**
     * @return el nom de la beguda
     */
    public String getNomBeguda() {
        return nomBeguda;
    }

    /**
     * @param nomBeguda el nom de la beguda a posar
     */
    public void setNomBeguda(String nomBeguda) {
        this.nomBeguda = nomBeguda;
    }

    /**
     * @return el preu de la beguda
     */
    public float getPreuBeguda() {
        return preuBeguda;
    }

    /**
     * @param preuBeguda el preu de la beguda a posar
     */
    public void setPreuBeguda(float preuBeguda) {
        this.preuBeguda = preuBeguda;
    }
    
    
    /**
     * Comprova si el dipòsit està ple o no.
     * @return El dipòsit està ple?
     */
    public boolean isDipositPle() {
        return (begudes.size() == capacitatDiposit);
    }
    
    /**
     * Comprova si el dipòsit està buit.
     * @return El dipòsit està buit?
     */
    public boolean isDipositBuit() {
        return (begudes.size() == 0);
    }
    
    /**
     * Afegeix una beguda al dipòsit del tipus del dipòsit. 
     * Només s'afegeix si té assignat algun tipus de beguda i sempre que no 
     * s'hagi superat la capacitat del dipòsit
     * @return Ha funcionat (1) o no ( 0: ple, -1: no iniciat )
     */
    public int AfegirBeguda() {
        
        if (nomBeguda.equals("Desconegut")) {
            return resultatDiposit.DIPOSITSENSEDESCRIPCIO.getResultat();
        }
        
        if (isDipositPle()) {
            return resultatDiposit.DIPOSITPLE.getResultat();
        }
        begudes.add(new Beguda(nomBeguda));      
        return resultatDiposit.BEGUDAAFEGIDA.getResultat();
    }

    /**
     * Afegeix una beguda al dipòsit del tipus del dipòsit i de la capacitat
     * especificada. 
     * Només s'afegeix si té assignat algun tipus de beguda i sempre que no 
     * s'hagi superat la capacitat del dipòsit
     * @param capacitat
     * @return Ha funcionat (1) o no ( 0: ple, -1: no iniciat )
     */
    public int AfegirBeguda(int capacitat) {
        
        if (nomBeguda.equals("Desconegut")) {
            return resultatDiposit.DIPOSITSENSEDESCRIPCIO.getResultat();
        }
        
        if (isDipositPle()) {
            return resultatDiposit.DIPOSITPLE.getResultat();
        }
        begudes.add(new Beguda(nomBeguda, capacitat));      
        return resultatDiposit.BEGUDAAFEGIDA.getResultat();
    }
    
    /** 
     * Treu una beguda del dipòsit. 
     * Si no s'especifica res més retorna la primera de la llista.
     * @return La beguda que surt o null si no n'hi ha cap
     */
    public Beguda TreuBeguda() {
        Beguda retorna = null;
        
        if (!isDipositBuit()) {
            retorna = begudes.get(0);
            begudes.remove(0);
        }
        
        return retorna;
        
    }
    
    /**
     * Treu una beguda de la capacitat especificada del dipòsit.
     * @return la beguda que surt
     */
    public Beguda TreuBeguda(int capacitat) {
        Beguda retorna = null;
        int index = -1;
        
        if (!isDipositBuit()) {
            index = localitzaBegudaDeCapacitat(capacitat);
            if (index != -1) {
                retorna = begudes.get(index);
            }
        }
        
        return retorna;
                
    }

    /** 
     * Localitza en el dipòsit la primera beguda de la capacitat que s'ha
     * especificat.
     * @param capacitat capacitat que es busca
     * @return la posició de la beguda en el dipòsit
     */
    private int localitzaBegudaDeCapacitat(int capacitat) {
        int index = 0;
        for(Beguda beguda: begudes) {            
            if (beguda.getCapacitatCC() == capacitat) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
