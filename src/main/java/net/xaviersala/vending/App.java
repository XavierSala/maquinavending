package net.xaviersala.vending;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	 MaquinaVending maquina = (MaquinaVending) new MaquinaVending();
    	 
    	 String diposits[] = { "aigua", "suc de taronja", "suc de llimona", "cola" };
    	 int preu[]           = {110,       135,                  135,                   125 };
    	 
    	 // ------------------------ Crear dipòsits de begudes
    	 for(int i=0; i<diposits.length; i++) {
    		 nouDiposit(maquina, diposits[i], preu[i]);
    	 }
    	 nouDiposit(maquina, "aigua", 110);

    	// ------------------------- Crear dipòsits de monedes
    	 int monedes[] = { 5, 10, 20, 50, 100, 200 };
    	 
    	 for (int moneda : monedes) {
    		 nouDipostiMonedes(maquina, "€", moneda);
    	 }
    	 
    	 System.out.println(" ----------------------------------------------- \n");
    	 
    	 int valor=0;
    	 // Emplenar dipòsits
    	 for(int i=0; i<50; i++) {  
    		 valor = (int) (Math.random() * diposits.length);
    		 posar(maquina, diposits[valor]);    		 
    	 }    	 
    	 posar(maquina, "cervesa");
    	 
    	 imprimeixResultats(maquina);
    	 
    	 // Engegar la màquina
    	 maquina.setEnMarxa(true);
    	 
    	 // Treure begudes
    	 treure(maquina, "cervesa");
    	 
    	 // Treure beguda existent sense diners
    	 treure(maquina, "aigua");
    	 
    	 // Posar diners
    	 for (int i=0; i<10; i++) {
    		 valor = (int) (Math.random() * monedes.length);
    		 posarMoneda(maquina, monedes[valor], "€");
    	 }
    	 // Treure begudes aleatòriament
    	 for (int i=0; i<35; i++) {
    		 valor = (int) (Math.random() * diposits.length);
    		 treure(maquina, diposits[valor]);
    	 }    
    	 
    	 System.out.println("...... TORNAR MONEDES");
    	 List<Moneda> x = maquina.cancelarCompra();
    	 if (x.size() > 0) {
	    	 for(Moneda m: x) {
	    		 System.out.println(m.getValor() + " " + m.getTipusMoneda());
	    	 }
    	 }
    	 
    	 imprimeixResultats(maquina);
    	
    }

	private static void nouDipostiMonedes(MaquinaVending maquina,
			String temp, int moneda) {
		System.out.print("... Afegir dipòsit de : " + moneda + " cèntims de " + temp);
		
		resultatMaquina resultat = maquina.afegirDipositMonedes(temp, moneda);
		
		switch (resultat) {		
			case OK:
				System.out.println(" OK");
				break;
			case ERROR: 
				System.out.println("ERROR indeterminat");
				break;
			case DIPOSIT_REPETIT: 
				System.out.println(" Dipòsit repetit");
				break;
			case MAQUINA_ATURADA: 
				System.out.println(" Màquina en marxa");
				break;
		default:
			break;
		}
	}

	/**
	 * @param maquina
	 */
	private static void imprimeixResultats(MaquinaVending maquina) {
		System.out.println("\n---------------------------------");
		for(Diposit<Beguda> d: maquina.dipositsBegudes) {
    		 System.out.println("Queden...: "  
    	                           + d.quantQueda() + " " 
    			                   + d.getNom());
    	 }
		System.out.println("---------------------------------\n");
	}

	/**
	 * Afegir nou dipòsit
	 * @param maquina
	 * @param temp
	 */
	private static void nouDiposit(MaquinaVending maquina, 
			String temp, int preu) {
		System.out.print("... Afegir dipòsit de : " + temp);
		
		resultatMaquina resultat = maquina.afegirDipositBegudes(temp, preu);
		
		switch (resultat) {		
			case OK:
				System.out.println(" OK");
				break;
			case ERROR: 
				System.out.println("ERROR indeterminat");
				break;
			case DIPOSIT_REPETIT: 
				System.out.println(" Dipòsit repetit");
				break;
			case MAQUINA_ATURADA: 
				System.out.println(" Màquina en marxa");
				break;
		default:
			break;
		}
	}

	/**
	 * @param maquina
	 */
	private static void posar(MaquinaVending maquina, String tipus) {
		System.out.print("...Posar " + tipus);
		resultatMaquina resultat = maquina.posarBeguda(tipus);
		switch (resultat) {
		case OK: 
			System.out.println(" OK");
			break;
		case DIPOSIT_PLE: 
			System.out.println(" El dipòsit ja està ple");
			break;
		case DIPOSIT_INEXISTENT: 
			System.out.println(" El dipòsit no hi és");
			break;
		case MAQUINA_ATURADA: 
			System.out.println(" Màquina en marxa");
			break;
		default:
			break;
		}

	}

	
	/**
	 * Posar monedes
	 */
	private static void posarMoneda(MaquinaVending maquina, int valor, String tipus) {
		System.out.print("...Posar moneda de " + valor + " " + tipus);
		resultatMaquina resultat = maquina.posarMoneda(new Moneda(valor, tipus));
		switch (resultat) {
		case OK: 
			System.out.println(" OK");
			break;
		case DIPOSIT_PLE: 
			System.out.println(" El dipòsit ja està ple");
			break;
		case DIPOSIT_INEXISTENT: 
			System.out.println(" El dipòsit no hi és");
			break;
		case MAQUINA_ATURADA: 
			System.out.println(" Màquina en marxa");
			break;
		default:
			break;
		}
		
	}
	
	
	/**
	 * Afegir nou dipòsit.
	 * @param maquina
	 * @param tipus
	 */
	private static void treure(MaquinaVending maquina, String tipus) {
		 Beguda b = maquina.treureBeguda(tipus);
		 System.out.print("Treure beguda: ");
		 if (b != null) {
			 System.out.println(b.toString() + " és " + b.getDescripcio());
		 } else {
			 System.out.println("No hi ha " + tipus + " o no hi ha prou diners");
		 }    	 
	}
}
