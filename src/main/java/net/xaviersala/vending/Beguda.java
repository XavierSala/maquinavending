package net.xaviersala.vending;

public class Beguda {
    /**
     * Capacitat en Centimetres cúbics.
     */
    private int capacitatCC;
    /**
     * Composició de la beguda.
     */
    private String composicio;
    
    /**
     * Descripció de la beguda.
     */
    private String descripcio;
    /**
     * Crea una beguda desconeguda de capacitat desconeguda.
     */
    public Beguda() {
        capacitatCC = 0;
        composicio = "";
        descripcio = "Desconeguda";
    }
    
    /**
     * Crea una beguda d'un tipus
     */
    public Beguda(String descripcioBeguda) {
        descripcio = descripcioBeguda;
        capacitatCC = 0;
        composicio = "";
    }
    
    /** 
     * Crea una beguda d'un tipus i d'una capacitat.
     * @param descripcioBeguda Text que descriu la beguda
     * @param capacitat capacitat en cc de la beguda
     */
    public Beguda(String descripcioBeguda, int capacitat) {
        descripcio = descripcioBeguda;        
        capacitatCC = capacitat;
        composicio = "";
    }

    /**
     * Obtenir la capacitat en cc.
     * @return La capacitatCC
     */
    public int getCapacitatCC() {
        return capacitatCC;
    }

    /**
     * Permet posar la capacitat d'una beguda determinada. 
     *
     * No permet valors inferiors a 0.
     *
     * @param capacitatCC La capacitat en CC. 
     */
    public void setCapacitatCC(int capacitatCC) {
        if (capacitatCC > 0) {
            this.capacitatCC = capacitatCC;
        }
    }

    /**
     * @return la composicio de la beguda
     */
    public String getComposicio() {
        return composicio;
    }

    /**
     * Permet entrar la composició d'una beguda.
     *
     * No es permeten cadenes buides
     * @param composició de la beguda a posar
     */
    public void setComposicio(String composicio) {
        if (composicio!=null) {
            this.composicio = composicio;
        }
    }

    /**
     * @return la descripcio
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Permet entrar la descripció d'una beguda.
     *
     * No es permeten cadenes buides
     * @param descripcio de la beguda
     */
    public void setDescripcio(String descripcio) {
        if (descripcio != null) {
            this.descripcio = descripcio;
        }
    }

}
