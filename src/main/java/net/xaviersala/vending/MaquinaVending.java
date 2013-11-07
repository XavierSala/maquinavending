/**
 * 
 */
package net.xaviersala.vending;

import java.util.ArrayList;
import java.util.List;

/**
 * Màquina de venta de begudes.
 * 
 * Es podrà configurar perquè tingui un número determinat
 * de dipòsits de begudes
 * 
 * - Per poder treure una beguda abans cal haver posat la 
 * quantitat de diners corresponent, que la màquina disposi
 * de suficient canvi i que hi hagi stock de la beguda demanada
 * - També es proporcionen les funcions de manteniment que 
 * faran que es puguin afegir monedes i begudes de diferents tipus a més de
 * nous dipòsits de begudes.
 * 
 * @author Xavier
 *
 */
public class MaquinaVending {
   
   /**
   * Llista dels dipòsits de begudes disponibles.
   */
   List<DipositBegudes> diposits;
   
   /** 
    * Monedes que han entrat.
    */
   int saldo; 
   
   /**
    * Determina si la màquina està en marxa o no.
    * 
    * Per poder estar en marxa cal tenir almenys algun dipòsit afegit
    */
   boolean enMarxa;
   


   /**
    * Creació d'una màquina de vending
    */
   public MaquinaVending() {
       diposits = new ArrayList<DipositBegudes>();
       saldo = 0;
   }
   
   /** 
    * Treu una beguda del tipus especificat de la màquina
    * @param quinaBeguda
    * @return
    */
   public Beguda treureBeguda(String quinaBeguda) {       
       int index = -1;
       
       if (enMarxa == true) {
           // Comprovar saldo
           
           // Localitzar en quin dipòsit s'ha de fer
           index = localitzarDiposit(quinaBeguda);
           if (index != -1) {  
               // Treure la beguda si és possible             
               return diposits.get(index).TreuBeguda();               
           }
       }
              
       return null;
   }

   /** 
    * Posa una beguda del tipus especificat de la màquina.
    * @param quinaBeguda
    * @return resultat de l'intent de posar la beguda
    */
   public resultatMaquina posarBeguda(String quinaBeguda) {       
       int index = -1;
       resultatMaquina retorn = resultatMaquina.OK;
       
       if (enMarxa == false) {           
           
           // Localitzar en quin dipòsit s'ha de fer
           index = localitzarDiposit(quinaBeguda);
           if (index != -1) {  
               // Treure la beguda si és possible             
               retorn =  diposits.get(index).AfegirBeguda();          
           }
           else {
               retorn = resultatMaquina.ERROR_DIPOSIT_REPETIT;
           }
       } else {
           retorn = resultatMaquina.ERROR_MAQUINA_EN_MARXA;
       }
              
       return retorn;
   }   
   
   
   /** 
    * Posa una beguda del tipus i la capacitat especificats a la màquina.
    * @param quinaBeguda tipus de la beguda que es posa
    * @param capacitat capacitat de la beguda posada
    * @return resultat de l'intent de posar la beguda
    */
   public resultatMaquina posarBeguda(String quinaBeguda, int capacitat) {       
       int index = -1;
       resultatMaquina retorn = resultatMaquina.OK;
       
       if (enMarxa == true) {           
           
           // Localitzar en quin dipòsit s'ha de fer
           index = localitzarDiposit(quinaBeguda);
           if (index != -1) {  
               // Treure la beguda si és possible             
               return diposits.get(index).AfegirBeguda(capacitat);          
           } else {
               retorn = resultatMaquina.ERROR_DIPOSIT_REPETIT;
           }
       } else {
           retorn = resultatMaquina.ERROR_MAQUINA_EN_MARXA;
       }
              
       return retorn;
   }     
   
   /**
    * Afegim un dipòsit del tipus que es rep a la màquina (només s'afegeix si
    * el tipus no hi era i la màquina no està en funcionament)
    * @param tipus tipus de dipòsit a afegir
    * @return retorna com ha acabat la operació
    */
   public resultatMaquina afegirDiposit(String tipus) {
       if (!isEnMarxa()) {
           if (localitzarDiposit(tipus) != -1) {
               diposits.add(new DipositBegudes(tipus));
               return resultatMaquina.OK;
           } else {
               return resultatMaquina.ERROR_JA_HI_ES;
           }
       }
       else {
           return resultatMaquina.ERROR_MAQUINA_EN_MARXA;
       }
   }
   
   /**
    * Treure de la màquina el dipòsit especificat.
    * 
    * Només es poden treure els dipòsits si la màquina està aturada
    * 
    * @param tipus descripció de la màquina que es vol treure
    * @return
    */
   public resultatMaquina treureDiposit(String tipus) {
       int index = -1;
       
       if (!isEnMarxa()) {
           index = localitzarDiposit(tipus);
           if (index != -1) {
               diposits.remove(index);
               return resultatMaquina.OK;
           } else {
               return resultatMaquina.ERROR_NO_HI_ES;
           }
       }
       else {
           return resultatMaquina.ERROR_MAQUINA_EN_MARXA;
       }       
   }

   /**
    * Definir el preu d'una beguda.
    * @param tipus tipus de dipòsit a afegir
    * @param preu preu de la beguda del dipòsit
    * @return retorna com ha acabat la operació
    */
   public resultatMaquina posarPreu(String tipus, float preu) {
       int index=-1;
       if (!isEnMarxa()) {
           index = localitzarDiposit(tipus);
           if (index != -1) {
               diposits.get(index).setPreuBeguda(preu);
               return resultatMaquina.OK;
           } else {
               return resultatMaquina.ERROR_NO_HI_ES;
           }
       }
       else {
           return resultatMaquina.ERROR_MAQUINA_EN_MARXA;
       }
   }
   
   /**
    * @return està enMarxa
    */
   public boolean isEnMarxa() {
       return enMarxa;
   }

   /**
    * Posar en marxa o aturar.
    * @param enMarxa Si es vol posar en marxa o no
    */
   public void setEnMarxa(boolean enMarxa) {
       this.enMarxa = enMarxa;
   }   
   
   /**
    * Localitza el dipòsit en el que hi ha la beguda especificada.
    * @param quinaBeguda beguda que es busca
    * @return retorna en quina posició està
    */
    private int localitzarDiposit(String quinaBeguda) {
        int index = 0;
        
        for(DipositBegudes diposit: diposits) {
            if (diposit.getNomBeguda().equals(quinaBeguda)) {
                return index;
            }
            index++;
        }
        return -1;
    }

}
