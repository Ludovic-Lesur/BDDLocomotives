/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package typedef;

import java.awt.*;

public enum Type {

	Vide("", Color.black),
	LocoElec1500("Electrique 1500V", Color.red),
	LocoElec25000("Electrique 25000V", Color.cyan),
	LocoElecBi("Electrique bicourante", new Color(148, 0, 211)),
	LocoTher("Thermique", new Color(100, 200, 0)),
	Automotrice("Automotrice", Color.orange),
	Autorail("Autorail", Color.darkGray),
	Bimode("Bimode", Color.blue),
	LOCMA("Manoeuvre", Color.magenta);

	// Attributs de chaque élément de l'énumération.
	private final String symbol;
	private final String name;
	private final Color color;

	/**
	 * CONSTRUCTEUR DE L'ENUMERATION TYPE.
	 * 
	 * @param pName
	 *            Nom du type d'engin moteur de type 'String'.
	 * @return Aucun.
	 */
	private Type(String pName, Color pColor) {
		symbol = this.toString();
		name = pName;
		color = pColor;
	}

	/**
	 * RENVOIE LE SYMBOLE DU TYPE.
	 * 
	 * @param Aucun.
	 * @return symbol Symbole du type utilisé dans les fichiers XML, de type
	 *         'String'.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * RENVOIE LE NOM DU TYPE.
	 * 
	 * @param Aucun.
	 * @return name Nom du type d'engin moteur de type 'String'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * RENVOIE LA COULEUR ASSOCIEE AU TYPE.
	 * 
	 * @param Aucun.
	 * @return color Couleur de police utilisée pour représenter le type, de
	 *         type 'Color'.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * RENVOIE LE TYPE CORRESPONDANT A UN SYMBOLE DONNE.
	 * 
	 * @param pSymbol
	 *            Symbole recherché de type 'String'.
	 * @return affectation Type associé au symbole si la recherche a donné un
	 *         résultat. 'Vide' sinon.
	 */
	public static Type affecter(Identifiant id, int im) {
		Type affectation = Vide;
		switch (id) {
		case BB:
			if (im < 10000) {
				affectation = LocoElec1500;
			} else {
				if (im < 20000) {
					affectation = LocoElec25000;
				} else {
					if (im < 30000) {
						affectation = LocoElecBi;
					} else {
						if (im >= 60000) {
							affectation = LocoTher;
						}
					}
				}
			}
			break;
		case CC:
			if (im < 10000) {
				affectation = LocoElec1500;
			} else {
				if (im < 20000) {
					affectation = LocoElec25000;
				} else {
					if (im < 30000) {
						affectation = LocoElecBi;
					} else {
						if (im >= 60000) {
							affectation = LocoTher;
						}
					}
				}
			}
			break;
		case X:
			affectation = Autorail;
			break;
		case Z:
			affectation = Automotrice;
			break;
		case B:
			affectation = Bimode;
			break;
		case Y:
			affectation = LOCMA;
			break;
		case Vide:
			break;
		}
		return affectation;
	}
}
