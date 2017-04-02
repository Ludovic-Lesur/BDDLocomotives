/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 29/05/2016
 */


package typedef ;


public enum Identifiant {
	
	Vide (""),
	BB ("BB"),
	CC ("CC"),
	X ("X"),
	Z ("Z"),
	B ("B"),
	Y ("Y") ;
	
	// Attributs de chaque élément de l'énumération.
	private final String symbol ;
	private final String name ;
	
	
	/**
		CONSTRUCTEUR DE L'ENUMERATION IDENTIFIANT.
	    @param pName		Nom de l'identifiant de type 'String'.
	    @return				Aucun.
	*/
	private Identifiant(String pName) {
		symbol = this.toString() ;
		name = pName ;
	}
	
	
	/**
		RENVOIE LE SYMBOLE DE L'IDENTIFIANT.
	    @param 				Aucun.
	    @return	symbol		Symbole de l'identifiant utilisé dans les fichiers XML, de type 'String'.
	*/
	public String getSymbol() {
		return symbol ;
	}
	
	
	/**
		RENVOIE LE NOM DE L'IDENTIFIANT.
	    @param 			Aucun.
	    @return	name	Nom de l'identifiant de type 'String'.
	*/
	public String getName() {
		return name ;
	}
	
	
	/**
		RENVOIE L'IDENTIFIANT CORRESPONDANT A UN SYMBOLE DONNE.
	    @param pSymbol			Symbole recherché de type 'String'.
	    @return	affectation		Identifiant associé au symbole si la recherche a donné un résultat.
	    						'Vide' sinon.
	*/
	public static Identifiant affecter(String pSymbol) {
		Identifiant affectation = Vide ;
		Identifiant[] listeIdentifiants = Identifiant.values() ;
		int i = 0 ;
		for (i=0 ; i<listeIdentifiants.length ; i++) {
			if (listeIdentifiants[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeIdentifiants[i] ;
				break ;
			}
		}
		return affectation ;
	}
	
	
	/**
		RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	    @param 				Aucun.
	    @return	resultat	Liste des identifiants, de type 'String[]'.
	*/
	public static String[] getNames() {
		Identifiant[] listeIdentifiants = Identifiant.values() ;
		String[] resultat = new String[listeIdentifiants.length-1] ;
		int i = 0 ;
		for (i=0 ; i<listeIdentifiants.length-1 ; i++) {
			resultat[i] = listeIdentifiants[i+1].getName() ;
		}
		return resultat ;
	}
	
	
	/**
		PERMET DE SAVOIR SI DEUX IDENTIFIANTS SONT IDENTIQUES.
	    @param i	Objet 'Identifiant' à comparer avec 'this'.
	    @return		'true' si 'this' est égal à 'i'.
	    			'false' sinon.
	*/
	public boolean equals(Identifiant i) {
		return (symbol.compareTo(i.getSymbol()) == 0) ;
	}
}
