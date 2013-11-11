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
public class Diposit<T> {
   
    /**
     * Definim la capacitat per defecte
     */
    public static final int CAPACITATDIPOSITDEFECTE = 10;
    
    String nom;
    float valor;
    int capacitatDiposit;

    /**
     * Llista que conté les begudes del dipòsit
     */
    List<T> contingut;
    
    /**
     * Crea un dipòsit sense dades del que hi haurà a dins.
     *
     * No deixarem que s'hi posin begudes per evitar problemes
     */
    public Diposit() {
        nom = "Desconeguda";
        valor = 0;
        capacitatDiposit = CAPACITATDIPOSITDEFECTE;
        contingut = new ArrayList<T>();
    }
    
    /**
     * Crea un dipòsit de begudes i li assigna el tipus de beguda.
     * Deixa el preu a zero
     * @param beguda El nom de la beguda a posar
     */
    public Diposit(String beguda) {
        nom = beguda;
        valor = 0;
        capacitatDiposit = CAPACITATDIPOSITDEFECTE;
        contingut = new ArrayList<T>();
    }
    
    /**
     * Crea un dipòsit de begudes i li assigna un tipus de beguda i un preu.
     * @param beguda la beguda que es posarà en el dipòsit
     * @param preu el preu de la beguda
     */
    public Diposit(String beguda, float preu) {
        nom = beguda;
        valor = preu;
        capacitatDiposit = CAPACITATDIPOSITDEFECTE;
        contingut = new ArrayList<T>();        
    }
    
    /**
     * Aquesta funció seria l'equivalent de posar un dipòsit que ja té algunes
     * begudes posades
     * @param beguda Tipus de begudes del dipòsit
     * @param preu Preu de cada beguda
     * @param capacitat capacitat del dipòsit
     * @param b llista de begudes que ja estan a lloc
     */
    public Diposit(String beguda, float preu, int capacitat, 
            ArrayList<T> b) {
        contingut = b;
        nom = beguda;
        valor = preu;
        capacitatDiposit = capacitat;
    }

    /**
     * @return el nom de la beguda
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom el nom de la beguda a posar
     */
    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        }
    }

    /**
     * @return el preu de la beguda
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor el preu de la beguda a posar
     */
    public void setValor(float valor) {
        if (valor>0) {
            this.valor = valor;
        }
    }
    
    /**
     * @return la quantitat de begudes del diposit
     */
    public int getCapacitatDiposit() {
        return capacitatDiposit;
    }

    /**
     * @param capacitatDiposit Defineix la quantitat de begudes
     */
    public void setCapacitatDiposit(int capacitatDiposit) {
        if (capacitatDiposit < 0) {
            this.capacitatDiposit = capacitatDiposit;
        }
    }
    
    /**
     * Comprova si el dipòsit està ple o no.
     * @return El dipòsit està ple?
     */
    public boolean isDipositPle() {
        return (contingut.size() == capacitatDiposit);
    }
    
    /**
     * Comprova si el dipòsit està buit.
     * @return El dipòsit està buit?
     */
    public boolean isDipositBuit() {
        return (contingut.size() == 0);
    }
    
    /**
     * Afegeix una beguda al dipòsit del tipus del dipòsit. 
     * Només s'afegeix si té assignat algun tipus de beguda i sempre que no 
     * s'hagi superat la capacitat del dipòsit
     * @return Ha funcionat (1) o no ( 0: ple, -1: no iniciat )
     */
    public resultatMaquina Afegir(T element) {
        
        if (nom.equals("Desconegut")) {
            return resultatMaquina.DIPOSIT_SENSE_DESCRIPCIO;
        }
        
        if (isDipositPle()) {
            return resultatMaquina.DIPOSIT_PLE;
        }
        contingut.add(element);      
        return resultatMaquina.OK;
    }

    
    /**
     * Determina quantes begudes queden
     * @return begudes en el dipòsit
     */
    public int quantQueda() {
        return contingut.size(); 
    }
    
 
    
    /** 
     * Treu una beguda del dipòsit. 
     * Si no s'especifica res més retorna la primera de la llista.
     * @return La beguda que surt o null si no n'hi ha cap
     */
    public T Treu() {
        T retorna = null;
        
        if (!isDipositBuit()) {
            retorna = contingut.get(0);
            contingut.remove(0);
        }
        
        return retorna;
        
    }

}
