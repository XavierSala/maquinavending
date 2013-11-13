package net.xaviersala.vending;

import java.util.Comparator;

/**
 * Classe per comparar dos objectes moneda que està pensada per poder 
 * mantenir un ArrayList<Moneda> ordenat.
 * @author xavier
 *
 */
public class ComparadorDiposits implements Comparator<Diposit<Moneda>> {

	@Override
	public int compare(Diposit<Moneda> o1, Diposit<Moneda> o2) {
		return o2.getValor() -  o1.getValor();			
	}

}
