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
