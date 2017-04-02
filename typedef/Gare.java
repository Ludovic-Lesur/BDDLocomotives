/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 29/05/2016
 */


package typedef ;


public enum Gare {
	
	Vide (""),
	AG ("Agen"),
	AER ("Assier"),
	BLG ("Brive-la-Gaillarde"),
	BLV ("Blainville"),
	BRI ("Briançon"),
	CAH ("Cahors"),
	CDC ("Capdenac"),
	CMX ("Carmaux"),
	CMS ("Clelles-Mens"),
	CLE ("Clermont-Ferrand"),
	DIP ("Dieupentale"),
	FIG ("Figeac"),
	GO ("Gourdon"),
	GE ("Grenoble"),
	ISS ("Issoire"),
	LNG ("Langeac"),
	LGG ("Langogne"),
	LS ("Limoges"),
	LCX ("Lus-La-Croix-Haute"),
	LYD ("Lyon Part-Dieu"),
	MSC ("Marseille-Saint-Charles"),
	MAU ("Millau"),
	MBN ("Montauban"),
	MN ("Mohon"),
	MSE ("Mulhouse"),
	NA ("Narbonne"),
	NEU ("Neussargues"),
	TE ("Toulouse"),
	TS ("Tarbes"),
	TDC ("Thédirac-Peyrilles"),
	RDZ ("Rodez"),
	SYH ("Saint-Chely-d'Apcher"),
	SJY ("Saint-Jory") ;
	
	// Attributs de chaque élément de l'énumération.
	private final String symbol ;
	private final String name ;
	
	
	/**
		CONSTRUCTEUR DE L'ENUMERATION GARE.
	    @param pName		Nom de la gare de type 'String'.
	    @return				Aucun.
	*/
	private Gare(String pName) {
		symbol = this.toString() ;
		name = pName ;
	}
	
	
	/**
		RENVOIE LE SYMBOLE DE LA GARE.
	    @param 				Aucun.
	    @return	symbol		Symbole de la gare utilisé dans les fichiers XML, de type 'String'.
	*/
	public String getSymbol() {
		return symbol ;
	}
	
	
	/**
		RENVOIE LE NOM DE LA GARE.
	    @param 			Aucun.
	    @return	name	Nom de la gare de type 'String'.
	*/
	public String getName() {
		return name ;
	}
	
	
	/**
		RENVOIE LA GARE CORRESPONDANT A UN SYMBOLE DONNE.
	    @param pSymbol			Symbole recherché de type 'String'.
	    @return	affectation		Gare associée au symbole si la recherche a donné un résultat.
	    						'Vide' sinon.
	*/
	public static Gare affecter(String pSymbol) {
		Gare affectation = Vide ;
		Gare[] listeGares = Gare.values() ;
		int i = 0 ;
		for (i=0 ; i<listeGares.length ; i++) {
			if (listeGares[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeGares[i] ;
				break ;
			}
		}
		return affectation ;
	}
		
	
	/**
		RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	    @param 				Aucun.
	    @return	resultat	Liste des gares, de type 'String[]'.
	*/
	public static String[] getNames() {
		Gare[] listeLieu = Gare.values() ;
		String[] resultat = new String[listeLieu.length-1] ;
		int i = 0 ;
		for (i=0 ; i<listeLieu.length-1 ; i++) {
			resultat[i] = listeLieu[i+1].getName() ;
		}
		return resultat ;
	}
	
	
	/**
		PERMET DE SAVOIR SI DEUX GARES SONT IDENTIQUES.
	    @param g	Objet 'Gare' à comparer avec 'this'.
	    @return		'true' si 'this' est égal à 'g'.
	    			'false' sinon.
	*/
	public boolean equals(Gare g) {
		return (symbol.compareTo(g.getSymbol()) == 0) ;
	}
}
