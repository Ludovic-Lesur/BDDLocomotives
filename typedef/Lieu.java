/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package typedef;

public enum Lieu {

	Vide(""),
	TE_D("D�p�t de Toulouse"),
	CLE_D("D�p�t de Clermont-Ferrand"),
	GE_D("D�p�t de Grenoble"),
	PPN_D("D�p�t de Perpignan"),
	TE_P("Ateliers de Toulouse-P�riole"),
	AL_G("Gare d'Albi-Ville"),
	ALE_G("Gare d'Ales"),
	BRI_G("Gare de Brian�on"),
	CAH_G("Gare de Cahors"),
	CMX_G("Gare de Carmaux"),
	CDC_G("Gare de Capdenac"),
	CDF_G("Gare de Castelnau-d'Estretefonds"),
	CSD_G("Gare de Caussade"),
	CLE_G("Gare de Clermont-Ferrand"),
	DEG_G("Gare de Degagnac"),
	DIP_G("Gare de Dieupentale"),
	FEN_G("Gare de Fenouillet-Saint-Alban"),
	GO_G("Gare de Gourdon"),
	GRT_G("Gare de Gramat"),
	GRI_G("Gare de Grisolles"),
	ISS_G("Gare d'Issoire"),
	LGE_G("Gare de Lab�ge"),
	LNT_G("Gare de Lacourtensourt"),
	LNQ_G("Gare de Lalbenques-Fontanes"),
	LMF_G("Gare de Lamothe-F�nelon"),
	LTL_G("Gare de Latour-de-Carol"),
	LS_G("Gare de Limoges-B�n�dictins"),
	LSU_G("Gare de Lisle-sur-Tarn"),
	LYD_G("Gare de Lyon Part-Dieu"),
	MSC_G("Gare de Marseille Saint-Charles"),
	MBN_G("Gare de Montauban"),
	MNL_G("Gare de Montlaur"),
	MLO_G("Gare de Montlouis"),
	MPZ_G("Gare de Montpezat-de-Quercy"),
	NA_G("Gare de Narbonne"),
	NEU_G("Gare de Neussargues"),
	PSS_G("Gare de Portet-Saint-Simon"),
	REA_G("Gare de R�alville"),
	RDZ_G("Gare de Rodez"),
	XAE_G("Gare de Saint-Agne"),
	SYH_G("Gare de Saint-Chely-d'Apcher"),
	SFC_G("Gare de Saint-Flour"),
	SJY_G("Gare de Saint-Jory"),
	SOU_G("Gare de Souillac"),
	TDC_G("Gare de Th�dirac-Peyrilles"),
	TE_G("Gare de Toulouse-Matabiau"),
	TEW_G("Gare de Toulouse-Saint-Cyprien-Ar�nes"),
	THS_G("Gare de Thu�s-les-Bains"),
	VR_R("Gare de Villefranche-de-Rouergue"),
	L590000_PK_555_62("Lieu-dit Le Souc"),
	L590000_PK_558_20("Lieu-dit Le Marges"),
	L590000_PK_579_64("Tunnel de Roques"),
	L590000_PK_592_84("Tunnel de Mercu�s"),
	L590000_PK_628_66("Lieu-dit Malminot"),
	L590000_PK_635_63("Lieu-dit Les Truquets"),
	L590000_PN_309("PN 309 (Auniac)"),
	L590000_PN_310("PN 310 (Auniac)"),
	L590000_PN_311("PN 311 (Gourdon)"),
	L590000_PN_321("PN 321 (Uzech-les-Oules)"),
	L650000_PK_4_48("Pont de la Charbonni�re"),
	TE_A("Remise de Toulouse-Acacias"),
	TE_E("Rotonde de Toulouse-Etoile"),
	NI_RSUD("Rotonde-Sud de N�mes"),
	CAH_T("Triage de Cahors"),
	CDC_T("Triage de Capdenac"),
	CTY_T("Triage de Castelnaudary"),
	GO_T("Triage de Gourdon"),
	MBN_T("Triage de Montauban"),
	NA_T("Triage de Narbonne"),
	NEU_T("Triage de Neussargues"),
	SJY_T("Triage de Saint-Jory"),
	TE_R("Triage de Toulouse-Raynal");

	// Attributs de chaque �l�ment de l'�num�ration.
	private final String symbol;
	private final String name;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION LIEU.
	 * 
	 * @param pName
	 *            Nom de lu lieu de type 'String'.
	 * @return Aucun.
	 */
	private Lieu(String pName) {
		symbol = this.toString();
		name = pName;
	}

	/**
	 * RENVOIE LE SYMBOLE DU LIEU.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole du lieu utilis� dans les fichiers XML, de type
	 *         'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DU LIEU.
	 * 
	 * @param Aucun.
	 * @return name Nom du lieu de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LE LIEU CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherch� de type 'String'.
	 * @return affectation Lieu associ� au symbole si la recherche a donn� un
	 *         r�sultat. 'Vide' sinon.
	 */
	public static Lieu affecter(String pSymbol) {
		Lieu affectation = Vide;
		Lieu[] listeLieux = Lieu.values();
		int i = 0;
		for (i = 0; i < listeLieux.length; i++) {
			if (listeLieux[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeLieux[i];
				break;
			}
		}
		return affectation;
	}

	/**
	 * RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des lieux, de type 'String[]'.
	 */
	public static String[] getNames() {
		Lieu[] listeLieu = Lieu.values();
		String[] resultat = new String[listeLieu.length - 1];
		int i = 0;
		for (i = 0; i < listeLieu.length - 1; i++) {
			resultat[i] = listeLieu[i + 1].getName();
		}
		return resultat;
	}

	/**
	 * PERMET DE SAVOIR SI DEUX IDENTIFIANTS SONT IDENTIQUES.
	 * 
	 * @param l
	 *            Objet 'Lieu' � comparer avec 'this'.
	 * @return 'true' si 'this' est �gal � 'l'. 'false' sinon.
	 */
	public boolean equals(Lieu l) {
		return (symbol.compareTo(l.getSymbol()) == 0);
	}
}
