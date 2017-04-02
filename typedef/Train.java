/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 26/05/2016
 */


package typedef ;


public enum Train {
	
	Vide (""),
	TER ("TER"),
	RRR ("RRR"),
	IC ("Intercité"),
	LUNEA ("Lunéa"),
	LUNEA_ECO ("Lunéa Eco"),
	FRET ("Fret"),
	INFRA ("Infra"),
	TM ("TM"),
	HLP ("HLP") ;
	
	// Attributs de chaque élément de l'énumération.
	private final String symbol ;
	private final String name ;
	
	
	/**
		CONSTRUCTEUR DE L'ENUMERATION TRAIN.
	    @param pName		Nom du train de type 'String'.
	    @return				Aucun.
	*/
	private Train(String pName) {
		symbol = this.toString() ;
		name = pName ;
	}
	
	
	/**
		RENVOIE LE SYMBOLE DU TRAIN.
	    @param 				Aucun.
	    @return	symbol		Symbole du train utilisé dans les fichiers XML, de type 'String'.
	*/
	public String getSymbol() {
		return symbol ;
	}
	
	
	/**
		RENVOIE LE NOM DU TRAIN.
	    @param 			Aucun.
	    @return	name	Nom du train de type 'String'.
	*/
	public String getName() {
		return name ;
	}
	
	
	/**
		RENVOIE LE TRAIN CORRESPONDANT A UN SYMBOLE DONNE.
	    @param pSymbol			Symbole recherché de type 'String'.
	    @return	affectation		Train associé au symbole si la recherche a donné un résultat.
	    						'Vide' sinon.
	*/
	public static Train affecter(String pSymbol) {
		Train affectation = Vide ;
		Train[] listeTrains = Train.values() ;
		int i = 0 ;
		for (i=0 ; i<listeTrains.length ; i++) {
			if (listeTrains[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeTrains[i] ;
				break ;
			}
		}
		return affectation ;
	}
		
	
	/**
		RENVOIE LA LISTE DES ITEMS.
	    @param 				Aucun.
	    @return	resultat	Liste des trains, de type 'String[]'.
	*/
	public static String[] getNames() {
		Train[] listeLieu = Train.values() ;
		String[] resultat = new String[listeLieu.length] ;
		int i = 1 ;
		for (i=0 ; i<listeLieu.length ; i++) {
			resultat[i] = listeLieu[i].getName() ;
		}
		return resultat ;
	}
	
	
	/**
		PERMET DE SAVOIR SI DEUX TRAINS SONT IDENTIQUES.
	    @param t	Objet 'Train' à comparer avec 'this'.
	    @return		'true' si 'this' est égal à 't'.
	    			'false' sinon.
	*/
	public boolean equals(Train t) {
		return (symbol.compareTo(t.getSymbol()) == 0) ;
	}
}
