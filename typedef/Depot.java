/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package typedef;

import java.awt.*;

public enum Depot {

	Vide("", Color.white),
	STF_MP("STF Midi-Pyrénées", Color.blue),
	STF_AQ("STF Aquitaine", Color.blue),
	STF_LR("STF Languedoc", Color.blue), 
	STF_AU("STF Auvergne", Color.blue),
	STF_LIM("STF Limousin", Color.blue),
	STF_BFC("STF Bourgogne-F.C.", Color.blue),
	STF_RHA("STF Rhône-Alpes", Color.blue),
	STF_CT("STF Centre-Tours", Color.blue),
	STF_BR("STF Bretagne",Color.blue),
	STF_PL("STF Pays de la Loire", Color.blue),
	STF_NU("STF Lignes N/U", Color.blue),
	STF_PACA("STF P.A.C.A.", Color.blue),
	STF_NPDC("STF N.P.D.C.", Color.blue),
	STN_NOR("STF Normandie", Color.blue),
	STF_AL("STF Alsace", Color.blue),
	STF_CHA("STF Champagne-Ardennes", Color.blue),
	STF_LOR("STF Lorraine", Color.blue),
	STF_PIC("STF Picardie", Color.blue),
	STF_IC("STF Voyages-IC", new Color(148, 0, 211)),
	STF_Fret("STF Locs Fret", new Color(100, 200, 0)),
	STF_Infra("STF Locs Infra", Color.orange),
	TE("Toulouse", Color.black),
	BX("Bordeaux", Color.black),
	LS("Limoges", Color.black),
	AV("Avignon", Color.black),
	BS("Beziers", Color.black),
	MBC("Marseille-Blancarde", Color.black),
	DPY("Dijon", Color.black),
	PSO("Paris-Sud-Ouest", Color.black),
	LNS("Lens", Color.black),
	CR("Chambery", Color.black),
	VSG("Villeneuve-St-G.", Color.black),
	VSX("Vénissieux", Color.black),
	MO("Montrouge", Color.black),
	NV("Nevers", Color.black),
	CY("Chalindrey", Color.black),
	LUA("Longueau", Color.black),
	NI("Nîmes", Color.black),
	RES("Rennes", Color.black),
	SG("Strasbourg", Color.black);

	// Attributs de chaque élément de l'énumération.
	private final String symbol;
	private final String name;
	private final Color couleur;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION DEPOT.
	 * 
	 * @param pName
	 *            Nom du dépôt de type 'String'.
	 * @param pCouleur
	 *            Code couleur du dépôt pour l'affichage de type 'Color'.
	 * @return Aucun.
	 */
	private Depot(String pName, Color pCouleur) {
		symbol = this.toString();
		name = pName;
		couleur = pCouleur;
	}

	/**
	 * RENVOIE LE SYMBOLE DU DEPOT.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole du dépôt utilisé dans les fichiers XML, de
	 *         type 'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DU DEPOT.
	 * 
	 * @param Aucun.
	 * @return name Nom du dépôt de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LA COULEUR D'AFFICHAGE DU DEPOT.
	 * 
	 * @param Aucun.
	 * @return couleur Couleur du dépôt de type 'Color'.
	 */
	public Color getColor() {
		return couleur;
	}

	/**
	 * RENVOIE LE DEPOT CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherché de type 'String'.
	 * @return affectation Dépôt associé au symbole si la recherche a donné un
	 *         résultat. 'Vide' sinon.
	 */
	public static Depot affecter(String pSymbol) {
		Depot affectation = Vide;
		Depot[] listeDepots = Depot.values();
		int i = 0;
		for (i = 0; i < listeDepots.length; i++) {
			if (listeDepots[i].getSymbol().compareTo(pSymbol) == 0) {
				affectation = listeDepots[i];
				break;
			}
		}
		return affectation;
	}

	/**
	 * RENVOIE LA LISTE DES ITEMS SAUF VIDE.
	 * 
	 * @param Aucun.
	 * @return resultat Liste des noms de dépôt, de type 'String[]'.
	 */
	public static String[] getNames() {
		Depot[] listeDepot = Depot.values();
		String[] resultat = new String[listeDepot.length - 1];
		int i = 0;
		for (i = 0; i < listeDepot.length - 1; i++) {
			resultat[i] = listeDepot[i + 1].getName();
		}
		return resultat;
	}
}
