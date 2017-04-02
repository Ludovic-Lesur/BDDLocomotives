/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */


package typedef ;


public enum Type {
	
	Vide (""),
	LocoElec1500 ("Electrique 1500V"),
	LocoElec25000 ("Electrique 25000V"),
	LocoElecBi ("Electrique bicourante"),
	LocoTher ("Thermique"),
	Automotrice ("Automotrice"),
	Autorail ("Autorail"),
	Bimode ("Bimode"),
	LOCMA ("Manoeuvre") ;
	
	// Attributs de chaque élément de l'énumération.
	private final String symbol ;
	private final String name ;
	
	
	/**
		CONSTRUCTEUR DE L'ENUMERATION TYPE.
	    @param pName		Nom du type d'engin moteur de type 'String'.
	    @return				Aucun.
	*/
	private Type(String pName) {
		symbol = this.toString() ;
		name = pName ;
	}
	
	
	/**
		RENVOIE LE SYMBOLE DU TYPE.
	    @param 				Aucun.
	    @return	symbol		Symbole du type utilisé dans les fichiers XML, de type 'String'.
	*/
	public String getSymbol() {
		return symbol ;
	}
	
	
	/**
		RENVOIE LE NOM DU TYPE.
	    @param 			Aucun.
	    @return	name	Nom du type d'engin moteur de type 'String'.
	*/
	public String getName() {
		return name ;
	}
	
	
	/**
		RENVOIE LE TYPE CORRESPONDANT A UN SYMBOLE DONNE.
	    @param pSymbol			Symbole recherché de type 'String'.
	    @return	affectation		Type associé au symbole si la recherche a donné un résultat.
	    						'Vide' sinon.
	*/
	public static Type affecter(Identifiant id, int im) {
		Type affectation = Vide ;
		switch (id) {
		case BB :
			if (im < 10000) {
				affectation = LocoElec1500 ;
			}
			else {
				if (im < 20000) {
					affectation = LocoElec25000 ;
				}
				else {
					if (im < 30000) {
						affectation = LocoElecBi ;
					}
					else {
						if (im >= 60000) {
							affectation = LocoTher ;
						}
					}
				}
			}
			break ;
		case CC :
			if (im < 10000) {
				affectation = LocoElec1500 ;
			}
			else {
				if (im < 20000) {
					affectation = LocoElec25000 ;
				}
				else {
					if (im < 30000) {
						affectation = LocoElecBi ;
					}
					else {
						if (im >= 60000) {
							affectation = LocoTher ;
						}
					}
				}
			}
			break ;
		case X :
			affectation = Autorail ;
			break ;
		case Z :
			affectation = Automotrice ;
			break ;
		case B :
			affectation = Bimode ;
			break ;
		case Y :
			affectation = LOCMA ;
			break ;
		case Vide :
			break ;
		}
		return affectation ;
	}
}
