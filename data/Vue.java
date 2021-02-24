/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 26/05/2016
 */

package data;

import typedef.*;
import java.time.*;

public class Vue implements Comparable<Vue> {

	// Attributs
	private LocalDate date;
	private Lieu lieu;
	private Voyage voyage;
	private Train train;
	private boolean cabine;
	private boolean photo;
	private boolean video;

	/**
	 * CONSTRUCTEUR DE LA CLASSE VUE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public Vue() {
		date = LocalDate.of(0, 1, 1);
		lieu = Lieu.Vide;
		voyage = new Voyage();
		train = Train.Vide;
		cabine = false;
		photo = false;
		video = false;
	}

	/**
	 * CONSTRUCTEUR DE RECOPIE DE LA CLASSE VUE. UTILISEE POUR SAUVEGARDER UNE
	 * VUE AVANT MODIFICATION DANS LA CLASSE 'FenetreVue'.
	 * 
	 * @param v
	 *            Vue a recopier, de type 'Vue'.
	 * @return Aucun.
	 */
	public Vue(Vue v) {
		date = v.getDate();
		lieu = v.getLieu();
		voyage = new Voyage(v.getVoyage());
		train = v.getTrain();
		cabine = v.getCabine();
		photo = v.getPhoto();
		video = v.getVideo();
	}

	/**
	 * MODIFIE LA DATE DE LA VUE.
	 * 
	 * @param newDate
	 *            Nouvelle date de la vue de type 'LocalDate'.
	 * @return Aucun.
	 */
	public void setDate(LocalDate newDate) {
		date = newDate;
	}

	/**
	 * RETOURNE LA DATE DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return date Date de la vue de type 'LocalDate'.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * MODIFIE LE LIEU DE LA VUE.
	 * 
	 * @param newLieu
	 *            Nouveau lieu de la vue de type 'String'. L'association au type
	 *            'Lieu' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setLieu(String newLieu) {
		lieu = Lieu.affecter(newLieu);
	}

	/**
	 * MODIFIE LE LIEU DE LA VUE.
	 * 
	 * @param newLieu
	 *            Nouveau lieu de la vue de type 'Lieu'.
	 * @return Aucun.
	 */
	public void setLieu(Lieu newLieu) {
		lieu = newLieu;
	}

	/**
	 * RETOURNE LE NOM DU LIEU DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return Nom du lieu de a vue de type 'String'.
	 */
	public String getNomLieu() {
		return lieu.getName();
	}

	/**
	 * RETOURNE LE LIEU DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return Lieu de a vue de type 'Lieu'.
	 */
	public Lieu getLieu() {
		return lieu;
	}

	/**
	 * MODIFIE LE VOYAGE DE LA VUE.
	 * 
	 * @param newDepart
	 *            Nouvelle gare de depart de type 'String'. L'association au
	 *            type 'Gare' est faite automatiquement.
	 * @param newArrivee
	 *            Nouvelle gare d'arrivee de type 'String'. L'association au
	 *            type 'Gare' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setVoyage(String newDepart, String newArrivee) {
		voyage.setDepart(newDepart);
		voyage.setArrivee(newArrivee);
	}

	/**
	 * MODIFIE LE VOYAGE DE LA VUE.
	 * 
	 * @param newDepart
	 *            Nouvelle gare de depart de type 'Gare'.
	 * @param newArrivee
	 *            Nouvelle gare d'arrivee de type 'Gare'.
	 * @return Aucun.
	 */
	public void setVoyage(Gare newDepart, Gare newArrivee) {
		voyage.setDepart(newDepart);
		voyage.setArrivee(newArrivee);
	}

	/**
	 * RETOURNE LE VOYAGE DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return voyage Eventuel voyage associe a la vue de type 'Voyage'.
	 */
	public Voyage getVoyage() {
		return voyage;
	}

	/**
	 * RETOURNE LE NOM DE LA GARE DE DEPART DU VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Nom de la gare de depart du voyage de type 'String'.
	 */
	public String getNomVoyageDepart() {
		return voyage.getNomDepart();
	}

	/**
	 * RETOURNE LA GARE DE DEPART DU VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Gare de depart du voyage de type 'Gare'.
	 */
	public Gare getVoyageDepart() {
		return voyage.getDepart();
	}

	/**
	 * RETOURNE LE NOM DE LA GARE D'ARRIVEE DU VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Nom de la gare d'arrivee du voyage de type 'String'.
	 */
	public String getNomVoyageArrivee() {
		return voyage.getNomArrivee();
	}

	/**
	 * RETOURNE LA GARE D'ARRIVEE DU VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Gare d'arrivee du voyage de type 'Gare'.
	 */
	public Gare getVoyageArrivee() {
		return voyage.getArrivee();
	}

	/**
	 * MODIFIE LE TYPE DE TRAIN DE LA VUE.
	 * 
	 * @param newTrain
	 *            Nouveau train de la vue de type 'String'. L'association au
	 *            type 'Train' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setTrain(String newTrain) {
		train = Train.affecter(newTrain);
	}

	/**
	 * MODIFIE LE TYPE DE TRAIN DE LA VUE.
	 * 
	 * @param newTrain
	 *            Nouveau train de la vue de type 'Train'.
	 * @return Aucun.
	 */
	public void setTrain(Train newTrain) {
		train = newTrain;
	}

	/**
	 * RETOURNE LE NOM DU TYPE DE TRAIN DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return Nom du type de train de la vue de type 'String'.
	 */
	public String getNomTrain() {
		return train.getName();
	}

	/**
	 * RETOURNE LE TYPE DE TRAIN DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return Type de train de la vue de type 'Train'.
	 */
	public Train getTrain() {
		return train;
	}

	/**
	 * MODIFIE LE BOOLEEN 'CABINE'.
	 * 
	 * @param newCabine
	 *            Nouvelle indication de cabine de type 'boolean'.
	 * @return Aucun.
	 */
	public void setCabine(boolean newCabine) {
		cabine = newCabine;
	}

	/**
	 * INDIQUE SI LA CABINE DE L'ENGIN A ETE VISITEE LE JOUR DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return cabine 'true' si la cabine de l'engin a ete visitee le jour de la
	 *         vue. 'false' sinon.
	 */
	public boolean getCabine() {
		return cabine;
	}

	/**
	 * RETOURNE LE BOOLEEN 'CABINE' SOUS FORME DE 'STRING' POUR LES FICHIERS
	 * XML.
	 * 
	 * @param Aucun.
	 * @return "1" si la cabine de l'engin a ete visitee le jour de la vue. "0"
	 *         sinon.
	 */
	public String getNomCabine() {
		if (cabine == false) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * MODIFIE LE BOOLEEN 'PHOTO'.
	 * 
	 * @param newCabine
	 *            Nouvelle indication de photo de type 'boolean'.
	 * @return Aucun.
	 */
	public void setPhoto(boolean newPhoto) {
		photo = newPhoto;
	}

	/**
	 * INDIQUE L'ENGIN A ETE PHOTOGRAPHIE LE JOUR DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return cabine 'true' si l'engin a ete photographie le jour de la vue.
	 *         'false' sinon.
	 */
	public boolean getPhoto() {
		return photo;
	}

	/**
	 * RETOURNE LE BOOLEEN 'PHOTO' SOUS FORME DE 'STRING' POUR LES FICHIERS XML.
	 * 
	 * @param Aucun.
	 * @return "1" si l'engin a ete photographie le jour de la vue. "0" sinon.
	 */
	public String getNomPhoto() {
		if (photo == false) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * MODIFIE LE BOOLEEN 'VIDEO'.
	 * 
	 * @param newCabine
	 *            Nouvelle indication de video de type 'boolean'.
	 * @return Aucun.
	 */
	public void setVideo(boolean newVideo) {
		video = newVideo;
	}

	/**
	 * INDIQUE L'ENGIN A ETE FILME LE JOUR DE LA VUE.
	 * 
	 * @param Aucun.
	 * @return cabine 'true' si l'engin a ete filme le jour de la vue. 'false'
	 *         sinon.
	 */
	public boolean getVideo() {
		return video;
	}

	/**
	 * RETOURNE LE BOOLEEN 'VIDEO' SOUS FORME DE 'STRING' POUR LES FICHIERS XML.
	 * 
	 * @param Aucun.
	 * @return "1" si l'engin a ete filme le jour de la vue. "0" sinon.
	 */
	public String getNomVideo() {
		if (video == false) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * PERMET DE SAVOIR SI DEUX OBJETS 'VUE' SONT IDENTIQUES.
	 * 
	 * @param v
	 *            Vue a comparer avec 'this', de type 'Vue'.
	 * @return identique 'true' si les deux objets sont identiques. 'false'
	 *         sinon.
	 */
	public boolean equals(Vue v) {
		boolean identique = true;
		if (date.equals(v.getDate()) == false) {
			identique = false;
		} else {
			if (lieu.equals(v.getLieu()) == false) {
				identique = false;
			} else {
				if (voyage.equals(v.getVoyage()) == false) {
					identique = false;
				} else {
					if (train.equals(v.getTrain()) == false) {
						identique = false;
					} else {
						if (cabine != v.getCabine()) {
							identique = false;
						} else {
							if (photo != v.getPhoto()) {
								identique = false;
							} else {
								if (video != v.getVideo()) {
									identique = false;
								}
							}
						}
					}
				}
			}
		}
		return identique;
	}

	/**
	 * COMPARE LA DATE DE DEUX VUE.
	 * 
	 * @param v
	 *            Vue dont la date est a comparer avec celle de 'this', de type
	 *            'Vue'.
	 * @return resultat -1 si 'this' est avant 'v'. 0 si 'this' est a la meme
	 *         date que 'v'. 1 si 'this' est apres 'v'.
	 */
	public int compareTo(Vue v) {
		int result;
		if (date.isAfter(v.getDate())) {
			result = -1;
		} else {
			if (date.isBefore(v.getDate())) {
				result = 1;
			} else {
				result = 0;
			}
		}
		return result;
	}
}
