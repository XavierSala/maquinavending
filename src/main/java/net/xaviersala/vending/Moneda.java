package net.xaviersala.vending;

public class Moneda {
    /**
     * valor de la moneda.
     */
    private int valor;
    /**
     * Tipus de moneda
     */
    private String tipusMoneda;
    
    
    public Moneda(int valor, String tipusMoneda) {
    	this.valor = valor;
    	this.tipusMoneda = tipusMoneda;
    }
    
	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	/**
	 * @return the tipusMoneda
	 */
	public String getTipusMoneda() {
		return tipusMoneda;
	}
	/**
	 * @param tipusMoneda the tipusMoneda to set
	 */
	public void setTipusMoneda(String tipusMoneda) {
		this.tipusMoneda = tipusMoneda;
	}
    
    
    
}
