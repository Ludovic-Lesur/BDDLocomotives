/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 29/05/2016
 */

package data;

import typedef.*;

public class Voyage {

	// Attributs
	private Gare depart;
	private Gare arrivee;

	/**
	 * CONSTRUCTEUR DE LA CLASSE VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public Voyage() {
		depart = Gare.Vide;
		arrivee = Gare.Vide;
	}

	/**
	 * CONSTRUCTEUR DE RECOPIE DE LA CLASSE VOYAGE. UTILISEE POUR SAUVEGARDER
	 * UNE VUE AVANT MODIFICATION DANS LA CLASSE 'FenetreVue'.
	 * 
	 * @param v
	 *            Voyage à recopier, de type 'Voyage'.
	 * @return Aucun.
	 */
	public Voyage(Voyage v) {
		depart = v.getDepart();
		arrivee = v.getArrivee();
	}

	/**
	 * MODIFIE LA GARE DE DEPART DU VOYAGE.
	 * 
	 * @param newDepart
	 *            Nouvelle gare de départ de type 'String'. L'association au
	 *            type 'Gare' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setDepart(String newDepart) {
		depart = Gare.affecter(newDepart);
	}

	/**
	 * MODIFIE LA GARE DE DEPART DU VOYAGE.
	 * 
	 * @param newDepart
	 *            Nouvelle gare de départ de type 'Gare'.
	 * @return Aucun.
	 */
	public void setDepart(Gare newDepart) {
		depart = newDepart;
	}

	/**
	 * RETOURNE LE NOM DE LA GARE DE DEPART.
	 * 
	 * @param Aucun.
	 * @return Nom de la gare de départ de type 'String'.
	 */
	public String getNomDepart() {
		return depart.getName();
	}

	/**
	 * RETOURNE LA GARE DE DEPART.
	 * 
	 * @param Aucun.
	 * @return depart Gare de départ de type 'Gare'.
	 */
	public Gare getDepart() {
		return depart;
	}

	/**
	 * MODIFIE LA GARE D'ARRIVEE DU VOYAGE.
	 * 
	 * @param newDepart
	 *            Nouvelle gare d'arrivée de type 'String'. L'association au
	 *            type 'Gare' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setArrivee(String newArrivee) {
		arrivee = Gare.affecter(newArrivee);
	}

	/**
	 * MODIFIE LA GARE D'ARRIVEE DU VOYAGE.
	 * 
	 * @param newDepart
	 *            Nouvelle gare d'arrivée de type 'Gare'.
	 * @return Aucun.
	 */
	public void setArrivee(Gare newArrivee) {
		arrivee = newArrivee;
	}

	/**
	 * RETOURNE LE NOM DE LA GARE D'ARRIVEE.
	 * 
	 * @param Aucun.
	 * @return Nom de la gare d'arrivée de type 'String'.
	 */
	public String getNomArrivee() {
		return arrivee.getName();
	}

	/**
	 * RETOURNE LA GARE D'ARRIVEE.
	 * 
	 * @param Aucun.
	 * @return depart Gare d'arrivée de type 'Gare'.
	 */
	public Gare getArrivee() {
		return arrivee;
	}

	/**
	 * PERMET DE SAVOIR SI DEUX OBJETS 'VOYAGE' SONT IDENTIQUES.
	 * 
	 * @param v
	 *            Voyage à comparer avec 'this', de type 'Voyage'.
	 * @return identique 'true' si les deux objets sont identiques. 'false'
	 *         sinon.
	 */
	public boolean equals(Voyage v) {
		boolean identique = (depart.equals(v.getDepart())) && (arrivee.equals(v.getArrivee()));
		return identique;
	}
}