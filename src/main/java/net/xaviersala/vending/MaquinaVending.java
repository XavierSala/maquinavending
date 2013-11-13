/**
 * 
 */
package net.xaviersala.vending;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Màquina de venta de begudes.
 * 
 * Es podrà configurar perquè tingui un número determinat de dipòsits de begudes
 * 
 * - Per poder treure una beguda abans cal haver posat la quantitat de diners
 * corresponent, que la màquina disposi de suficient canvi i que hi hagi stock
 * de la beguda demanada - També es proporcionen les funcions de manteniment 
 * que  * faran que es puguin afegir monedes i begudes de diferents tipus a més de 
 * nous dipòsits de begudes.
 * 
 * @author Xavier
 * 
 */
public class MaquinaVending {

	/**
	 * Llista dels dipòsits de begudes disponibles.
	 */
	List<Diposit<Beguda>> dipositsBegudes;
	List<Diposit<Moneda>> dipositsMonedes;

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
		dipositsBegudes = new ArrayList<Diposit<Beguda>>();
		dipositsMonedes = new ArrayList<Diposit<Moneda>>();
		saldo = 0;
	}

	/**
	 * Creació d'una màquina de vending amb dipòsits.
	 * 
	 * @param llista
	 *            amb els dipòsits amb els que s'ha de crear la màquina
	 */
	public MaquinaVending(List<Diposit<Beguda>> diposits) {
		this.dipositsBegudes = diposits;
		dipositsMonedes = new ArrayList<Diposit<Moneda>>();
		saldo = 0;
	}

	/**
	 * Treu una beguda del tipus especificat de la màquina
	 * 
	 * @param quinaBeguda
	 * @return
	 */
	public Beguda treureBeguda(String quinaBeguda) {
		int index = -1;

		if (quinaBeguda == null) {
			return null;
		}

		if (enMarxa == true) {
			// Comprovar saldo

			// Localitzar en quin dipòsit s'ha de fer
			index = localitzarDiposit(quinaBeguda);
			if (index != -1) {
				// Treure la beguda si és possible
				int preuBeguda = dipositsBegudes.get(index).getValor();
				if (saldo >= preuBeguda) {
					saldo = saldo - preuBeguda;
					return dipositsBegudes.get(index).Treu();
				}
			}
		}

		return null;
	}

	/**
	 * Tornar monedes per valor de les que ha posat
	 * 
	 * @return Llista de monedes retornades
	 */
	public List<Moneda> cancelarCompra() {
		return tornarMonedes(saldo);
	}

	/**
	 * Tornar les monedes que permeten fer la quantitat especificada..
	 * 
	 * @param quantitatATornar
	 *            quantitat a tornar
	 * @return Llista de monedes retornades
	 */
	public List<Moneda> tornarMonedes(int quantitatATornar) {
		List<Moneda> monedesATornar = new ArrayList<Moneda>();
		
		int saldoATornar = quantitatATornar;

		// Valor de la moneda més petita
		int monedaMespetita = dipositsMonedes.get(dipositsMonedes.size()-1).getValor();
		
		if (saldoATornar < monedaMespetita) return null;
				
		int posicio = 0;
		do { 
			int valorMoneda = dipositsMonedes.get(posicio).getValor();
			if ( valorMoneda <= saldoATornar) {
				Moneda monedaTreta = dipositsMonedes.get(posicio).Treu();
				if (monedaTreta != null) {
					saldoATornar = saldoATornar - valorMoneda;
					monedesATornar.add(monedaTreta);
				} else {
					// No queden monedes d'aquest tipus...
					posicio = posicio + 1;
				}				
			} else {
				// Moneda massa gran
				posicio = posicio + 1;
			}
						
		} while (saldoATornar!=0 && posicio < dipositsMonedes.size());
		
		if (saldoATornar > 0) {
			return null;
		}
		
		// Podem tornar la quantitat, les trec del saldo
		saldo = saldo - quantitatATornar;
		return monedesATornar;
	}

	/**
	 * Posar monedes
	 * 
	 * @param moneda
	 *            que posa
	 */
	public resultatMaquina posarMoneda(Moneda m) {
		resultatMaquina retorn;

		if (m == null || enMarxa == false) {
			return resultatMaquina.ERROR;
		}

		// int index = localitzarDiposit(m.getValor())
		if (enMarxa == false) {
			return resultatMaquina.MAQUINA_EN_MARXA;
		}
		// Localitzar en quin dipòsit s'ha de fer
		int index = localitzarDiposit(m);
		if (index != -1) {
			// posar la moneda si és possible
			retorn = dipositsMonedes.get(index).Afegir(m);
			if (retorn == resultatMaquina.OK) {
				saldo = saldo + m.getValor();
			}
		} else {
			retorn = resultatMaquina.DIPOSIT_INEXISTENT;
		}

		return retorn;
	}

	private int localitzarDiposit(Moneda m) {
		int index = 0;

		for (Diposit<Moneda> moneda : dipositsMonedes) {
			if (moneda.getNom().equals(m.getTipusMoneda())
					&& moneda.getValor() == m.getValor()) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Posa una beguda del tipus especificat de la màquina.
	 * 
	 * @param quinaBeguda
	 * @return resultat de l'intent de posar la beguda
	 */
	public resultatMaquina posarBeguda(String quinaBeguda) {
		int index = -1;
		if (quinaBeguda == null) {
			return resultatMaquina.ERROR;
		}

		resultatMaquina retorn = resultatMaquina.OK;

		if (enMarxa == false) {

			// Localitzar en quin dipòsit s'ha de fer
			index = localitzarDiposit(quinaBeguda);
			if (index != -1) {
				// Treure la beguda si és possible
				retorn = dipositsBegudes.get(index).Afegir(
						new Beguda(quinaBeguda));
			} else {
				retorn = resultatMaquina.DIPOSIT_INEXISTENT;
			}
		} else {
			retorn = resultatMaquina.MAQUINA_EN_MARXA;
		}

		return retorn;
	}

	/**
	 * Posa una beguda del tipus i la capacitat especificats a la màquina.
	 * 
	 * @param quinaBeguda
	 *            tipus de la beguda que es posa
	 * @param capacitat
	 *            capacitat de la beguda posada
	 * @return resultat de l'intent de posar la beguda
	 */
	public resultatMaquina posarBeguda(String quinaBeguda, int capacitat) {
		int index = -1;
		if (quinaBeguda == null) {
			return resultatMaquina.ERROR;
		}
		resultatMaquina retorn = resultatMaquina.OK;

		if (enMarxa == true) {

			// Localitzar en quin dipòsit s'ha de fer
			index = localitzarDiposit(quinaBeguda);
			if (index != -1) {
				// Treure la beguda si és possible
				Beguda nova = new Beguda(quinaBeguda, capacitat);
				return dipositsBegudes.get(index).Afegir(nova);
			} else {
				retorn = resultatMaquina.DIPOSIT_INEXISTENT;
			}
		} else {
			retorn = resultatMaquina.MAQUINA_EN_MARXA;
		}

		return retorn;
	}

	/**
	 * Afegim un dipòsit del tipus que es rep a la màquina (només s'afegeix si
	 * el tipus no hi era i la màquina no està en funcionament)
	 * 
	 * @param tipus tipus de dipòsit a afegir
	 *  @param preu preu de les begudes del dipòsit
	 * @return retorna com ha acabat la operació
	 */
	public resultatMaquina afegirDipositBegudes(String tipus, int preu) {
		if (tipus == null) {
			return resultatMaquina.ERROR;
		}

		if (!isEnMarxa()) {
			if (localitzarDiposit(tipus) == -1) {
				dipositsBegudes.add(new Diposit<Beguda>(tipus, preu));
				return resultatMaquina.OK;
			} else {
				return resultatMaquina.DIPOSIT_REPETIT;
			}
		} else {
			return resultatMaquina.MAQUINA_EN_MARXA;
		}
	}
	
	/**
	 * Afegim un dipòsit de monedes que es rep a la màquina (només s'afegeix si
	 * el tipus no hi era i la màquina no està en funcionament)
	 * 
	 * @param tipus tipus de moneda
	 * @param valor valor de les monedes
	 * @return retorna com ha acabat la operació
	 */
	public resultatMaquina afegirDipositMonedes(String tipus, int valor) {
		if (tipus == null || valor <=0) {
			return resultatMaquina.ERROR;
		}

		if (!isEnMarxa()) {
			if (localitzarDiposit(tipus) == -1) {
				dipositsMonedes.add(new Diposit<Moneda>(tipus, valor));
				
				// Mantenir els dipòsits ordenats
				Collections.sort(dipositsMonedes, new ComparadorDiposits());
				
				return resultatMaquina.OK;
			} else {
				return resultatMaquina.DIPOSIT_REPETIT;
			}
		} else {
			return resultatMaquina.MAQUINA_EN_MARXA;
		}
	}

	/**
	 * Treure de la màquina el dipòsit especificat.
	 * 
	 * Només es poden treure els dipòsits si la màquina està aturada
	 * 
	 * @param tipus
	 *            descripció de la màquina que es vol treure
	 * @return
	 */
	public resultatMaquina treureDipositBegudes(String tipus) {
		int index = -1;

		if (tipus == null) {
			return resultatMaquina.ERROR;
		}
		if (!isEnMarxa()) {
			index = localitzarDiposit(tipus);
			if (index != -1) {
				dipositsBegudes.remove(index);
				return resultatMaquina.OK;
			} else {
				return resultatMaquina.DIPOSIT_INEXISTENT;
			}
		} else {
			return resultatMaquina.MAQUINA_EN_MARXA;
		}
	}

	/**
	 * Definir el preu d'una beguda.
	 * 
	 * @param tipus
	 *            tipus de dipòsit a afegir
	 * @param preu
	 *            preu de la beguda del dipòsit en cèntims
	 * @return retorna com ha acabat la operació
	 */
	public resultatMaquina posarPreu(String tipus, int preu) {

		int index = -1;
		if (tipus == null) {
			return resultatMaquina.ERROR;
		}

		if (!isEnMarxa()) {
			index = localitzarDiposit(tipus);
			if (index != -1) {
				dipositsBegudes.get(index).setValor(preu);
				return resultatMaquina.OK;
			} else {
				return resultatMaquina.DIPOSIT_INEXISTENT;
			}
		} else {
			return resultatMaquina.MAQUINA_EN_MARXA;
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
	 * 
	 * @param enMarxa
	 *            Si es vol posar en marxa o no
	 */
	public void setEnMarxa(boolean enMarxa) {
		this.enMarxa = enMarxa;
	}

	/**
	 * Localitza el dipòsit en el que hi ha la beguda especificada.
	 * 
	 * @param quinaBeguda
	 *            beguda que es busca
	 * @return retorna en quina posició està
	 */
	private int localitzarDiposit(String quinaBeguda) {
		int index = 0;

		for (Diposit<Beguda> diposit : dipositsBegudes) {
			if (diposit.getNom().equals(quinaBeguda)) {
				return index;
			}
			index++;
		}
		return -1;
	}

}
