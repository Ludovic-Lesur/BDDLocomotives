/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package data;

import java.util.*;
import java.io.*;
import java.time.*;
import typedef.*;

public class Engin {

	// Infos
	private Serie serie;
	private Identifiant identifiant;
	private String numero;
	private String renumerotation;
	private Etat etat;
	private LocalDate dateMES;
	private LocalDate dateRAD;
	private Gare lieuPR;
	private Association associationPR;
	private Period age;
	private Livree livree;
	private Depot depot;
	private int indice; // Rang de l'engin moteur dans la série.
	// Photo
	private boolean photo;
	private String cheminPhoto;
	public static final String cheminPhotoVide = Parc.dossierParc + "/photos/vide.jpg";
	// Vues
	private int nbVues;
	Vector<Vue> listeVues;
	// Remarques
	private int nbRemarques;
	Vector<Remarque> listeRemarques;

	/**
	 * CONSTRUCTEUR DE LA CLASSE ENGIN.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public Engin() {
		// Infos
		identifiant = Identifiant.Vide;
		numero = "";
		renumerotation = "";
		dateMES = LocalDate.of(0, 1, 1);
		etat = Etat.Vide;
		lieuPR = Gare.Vide;
		associationPR = Association.Vide;
		dateRAD = LocalDate.of(0, 1, 1);
		age = Period.between(dateMES, dateRAD);
		livree = Livree.Vide;
		depot = Depot.Vide;
		indice = 0;
		// Photo
		photo = false;
		cheminPhoto = cheminPhotoVide;
		// Vues
		nbVues = 0;
		listeVues = new Vector<Vue>();
		// Remarques
		nbRemarques = 0;
		listeRemarques = new Vector<Remarque>();
	}

	/**
	 * RENVOIE LA SERIE DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return serie Série de l'engin de type 'Serie'.
	 */
	public Serie getSerie() {
		return serie;
	}

	/**
	 * MODIFIE LA SERIE DE L'ENGIN.
	 * 
	 * @param newSerie
	 *            Nouvelle série de type 'Serie'.
	 * @return Aucun.
	 */
	public void setSerie(Serie newSerie) {
		serie = newSerie;
	}

	/**
	 * MODIFIE L'IDENTIFIANT DE L'ENGIN.
	 * 
	 * @param newIdentifiant
	 *            Nouvel identifiant de l'engin de type 'Identifiant' (identique
	 *            à celui de sa série).
	 * @return Aucun.
	 */
	public void setIdentifiant(Identifiant newIdentifiant) {
		identifiant = newIdentifiant;
	}

	/**
	 * RENVOIE LE NOM COMPLET DE LA SERIE DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return nomComplet Nom complet de la série de l'engin de type 'String'
	 *         (identifiant et numéro) si la série a été définie. Chaine de
	 *         caractère vide sinon.
	 */
	public String getNomSerie() {
		String nomComplet = "";
		if (serie != null) {
			nomComplet = serie.getNomComplet();
		}
		return nomComplet;
	}

	/**
	 * RETOURNE LE NUMERO DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return numero Numero de l'engin de type 'int'.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * MODIFIE LE NUMERO DE L'ENGIN.
	 * 
	 * @param newNumero
	 *            Nouveau numéro de l'engin de type 'int'.
	 * @return Aucun.
	 */
	public void setNumero(String newNumero) {
		numero = newNumero;
	}

	/**
	 * RENVOIE LE NOM COMPLET DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return Nom complet de l'engin de type 'String' constitué de
	 *         l'identifiant et du numéro.
	 */
	public String getNomComplet() {
		return identifiant.getName() + " " + numero;
	}

	/**
	 * RETOURNE LA RENUMEROTATION DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return renumerotation Nouveau numéro de l'engin de type 'int' s'il a été
	 *         renuméroté ou transformé. 0 sinon.
	 */
	public String getRenumerotation() {
		return renumerotation;
	}

	/**
	 * MODIFIE LA RENUMEROTATION A L'ENGIN.
	 * 
	 * @param newRenumerotation
	 *            Nouveau numéro de l'engin de type 'int'.
	 * @return Aucun.
	 */
	public void setRenumerotation(String newRenumerotation) {
		renumerotation = newRenumerotation;
	}

	/**
	 * RENVOIE L'ETAT ACTUEL DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return etat Etat actuel de l'engin de type 'Etat'.
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * MODIFIE L'ETAT DE L'ENGIN.
	 * 
	 * @param newEtat
	 *            Nouvel état de l'engin de type 'String'. L'association au type
	 *            'Etat' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setEtat(String newEtat) {
		etat = Etat.affecter(newEtat);
	}

	/**
	 * RENVOIE LA DATE DE MISE EN SERVICE DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return dateMES Date de mise en service de l'engin de type 'LocalDate' si
	 *         l'engin a été mis en service. LocalDate.now() sinon.
	 */
	public LocalDate getDateMES() {
		return dateMES;
	}

	/**
	 * MODIFIE LA DATE DE MISE EN SERVICE DE L'ENGIN.
	 * 
	 * @param newDateMES
	 *            Nouvelle date de mise en service de l'engin de type
	 *            'LocalDate'.
	 * @return Aucun.
	 */
	public void setDateMES(LocalDate newDateMES) {
		dateMES = newDateMES;
	}

	/**
	 * RENVOIE LA DATE DE RADIATION DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return dateMES Date de radiation de l'engin de type 'LocalDate' si
	 *         l'engin a été radié. LocalDate.now() sinon.
	 */
	public LocalDate getDateRAD() {
		return dateRAD;
	}

	/**
	 * MODIFIE LA DATE DE RADIATION DE L'ENGIN.
	 * 
	 * @param newDateMES
	 *            Nouvelle date de radiation de l'engin de type 'LocalDate'.
	 * @return Aucun.
	 */
	public void setDateRAD(LocalDate newDateRAD) {
		dateRAD = newDateRAD;
	}

	/**
	 * RETOURNE L'ASSOCIATION DE PRESERVATION DE L'ENGIN.
	 * 
	 * @param
	 * @return associationPR Association de préservation de l'engin de type
	 *         'Association' si l'engin a été préservé. Association.vide sinon.
	 */
	public Association getAssociation() {
		return associationPR;
	}

	/**
	 * RETOURNE LES INFORMATIONS DE PRESERVATION DE L'ENGIN.
	 * 
	 * @param formatLong
	 *            Booléen indiquant le format de la chaine de caractère
	 *            retournée. true = nom de l'association et lieu (utilisé dans
	 *            la classe graphique 'Infos'). false = uniquement le nom de
	 *            l'association.
	 * @return infos Informations de préservation de l'engin de type 'String'.
	 */
	public String getPreservation(boolean formatLong) {
		String infos;
		if (formatLong == true) {
			infos = " par " + associationPR.getArticle() + associationPR.getName() + " (" + lieuPR.getName() + ")";
		} else {
			infos = associationPR.getName();
		}
		return infos;
	}

	/**
	 * MODIFIE LES INFORMATIONS DE PRESERVATION DE L'ENGIN.
	 * 
	 * @param newAssociation
	 *            Nouvelle association de préservation de l'engin de type
	 *            'String'. Le lien avec le type 'Association' et l'affectation
	 *            du lieu sont réalisés automatiquement.
	 * @return Aucun.
	 */
	public void setPreservation(String newAssociationPR) {
		associationPR = Association.affecter(newAssociationPR);
		lieuPR = associationPR.getGare();
	}

	/**
	 * CALCULE L'AGE DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void calculerAge() {
		if (etat == Etat.R) {
			// Engin radié -> age = période entre sa mise en service et sa
			// radiation.
			age = Period.between(dateMES, dateRAD);
		} else {
			if (etat == Etat.AMES) {
				// Engin en attente de mise en service -> age = 0.
				age = Period.ZERO;
			} else {
				// Engin en service ou préservé -> age = période entre sa mise
				// en service et aujourd'hui.
				age = Period.between(dateMES, LocalDate.now());
			}
		}
	}

	/**
	 * RETOURNE L'AGE DE L'ENGIN.
	 * 
	 * @param formatLong
	 *            Booléen indiquant le format d'affichage de l'âge. true = le
	 *            nombre d'années, de mois et de jours sont indiqués. false =
	 *            l'âge est converti en un nombre décimal.
	 * @return Aucun.
	 */
	public String getAge(boolean formatLong) {
		String resultat;
		if (formatLong == true) {
			// Format long
			if (age.getYears() == 0) {
				if (age.getMonths() == 0) {
					if (age.getDays() == 0) {
						resultat = "Mise en service aujourd'hui !";
					} else {
						resultat = age.getDays() + " jours";
					}
				} else {
					if (age.getDays() == 0) {
						resultat = age.getMonths() + " mois";
					} else {
						resultat = age.getMonths() + " mois et " + age.getDays() + " jours";
					}

				}
			} else {
				if (age.getMonths() == 0) {
					if (age.getDays() == 0) {
						resultat = age.getYears() + " ans";
					} else {
						resultat = age.getYears() + " ans et " + age.getDays() + " jours";
					}
				} else {
					if (age.getDays() == 0) {
						resultat = age.getYears() + " ans et " + age.getMonths() + " mois";
					} else {
						resultat = age.getYears() + " ans, " + age.getMonths() + " mois et " + age.getDays() + " jours";
					}
				}
			}
		} else {
			// Format court en nombre décimal
			double ageFloat = age.getYears() + age.getMonths() / 12.0 + age.getDays() / 365.0;
			ageFloat = Math.floor(ageFloat * 100) / 100.0;
			resultat = Double.toString(ageFloat);
		}
		return resultat;
	}

	/**
	 * RETOURNE LE RANG DE L'ENGIN MOTEUR DANS SA SERIE.
	 * 
	 * @param Aucun.
	 * @return Indice de l'engin moteur de type 'int'.
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * MODIFIE LE RANG DE L'ENGIN MOTEUR DANS SA SERIE.
	 * 
	 * @param newIndice
	 *            Nouvel indice de l'engin moteur de type 'int'.
	 * @return Aucun.
	 */
	public void setIndice(int newIndice) {
		indice = newIndice;
	}

	/**
	 * INDIQUE SI L'ENGIN FETE UN ANNIVERSAIRE DE MISE EN SERVICE.
	 * 
	 * @param date
	 *            Date de type 'LocalDate' à comparer avec la date de mise en
	 *            service de l'engin.
	 * @return true si jour et mois de 'date' et 'dateMES' sont identiques.
	 *         false sinon.
	 */
	public boolean anniversaire(LocalDate date) {
		boolean joursIdentiques = (dateMES.getDayOfMonth() == date.getDayOfMonth());
		boolean moisIdentiques = (dateMES.getMonth().getValue() == date.getMonth().getValue());
		return ((joursIdentiques && moisIdentiques) && (age != Period.ZERO));
	}

	/**
	 * RETOURNE LA LIVREE DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return livree Livree actuelle de l'engin de type 'Livree'.
	 */
	public Livree getLivree() {
		return livree;
	}

	/**
	 * RETOURNE LA LIVREE DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return Livree actuelle de l'engin de type 'String'.
	 */
	public String getNomLivree() {
		return livree.getName();
	}

	/**
	 * MODIFIE LA LIVREE DE L'ENGIN.
	 * 
	 * @param newLivree
	 *            Nouvelle livrée de l'engin de type 'String'. L'association au
	 *            type 'Livree' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setLivree(String newLivree) {
		livree = Livree.affecter(newLivree);
	}

	/**
	 * RETOURNE LE DEPOT DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return depot Dépôt actuel de l'engin de type 'Depot'.
	 */
	public Depot getDepot() {
		return depot;
	}

	/**
	 * RETOURNE LE DEPOT DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return Dépôt actuel de l'engin de type 'String'.
	 */
	public String getNomDepot() {
		return depot.getName();
	}

	/**
	 * MODIFIE LE DEPOT DE L'ENGIN.
	 * 
	 * @param newLivree
	 *            Nouveau dépôt de l'engin de type 'String'. L'association au
	 *            type 'Depot' est faite automatiquement.
	 * @return Aucun.
	 */
	public void setDepot(String newDepot) {
		depot = Depot.affecter(newDepot);
	}

	/**
	 * RECHERCHE UNE PHOTO POUR L'ENGIN.
	 * 
	 * @param dossierPhoto
	 *            Chemin absolu de type 'String' du dossier où chercher la
	 *            photo.
	 * @return Aucun.
	 */
	public void chercherPhoto(String dossierPhoto) {
		cheminPhoto = cheminPhotoVide;
		File dossier = new File(dossierPhoto);
		File[] fichiers = dossier.listFiles();
		int i = 0;
		// Pour chaque fichier trouvé dans le dossier...
		for (i = 0; i < (fichiers.length); i++) {
			File f = fichiers[i];
			String nomF = f.getName().substring(0, f.getName().length() - 4);
			// Si le nom du fichier est égal au numéro de l'engin -> photo
			// trouvée.
			if ((f.isFile()) && (nomF.compareTo(numero) == 0)) {
				photo = true;
				cheminPhoto = f.getAbsolutePath();
				break;
			}
		}
	}

	/**
	 * INDIQUE SI L'ENGIN A ETE PHOTOGRAPHIE.
	 * 
	 * @param Aucun.
	 * @return photo Booléen indiquant si l'engin possède une photo enregistrée.
	 *         true = l'engin possède une photo. false = l'engin ne possède pas
	 *         de photo.
	 */
	public boolean getPhoto() {
		return photo;
	}

	/**
	 * RETOURNE LE CHEMIN D'ACCES A LA PHOTO DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return cheminPhoto Chemin absolu de type 'String' pointant sur la photo
	 *         de l'engin si elle existe. Chemin absolu de la photo 'Vide.jpg'
	 *         sinon.
	 */
	public String getCheminPhoto() {
		return cheminPhoto;
	}

	/**
	 * RETOURNE LE NOMBRE DE VUES DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return nbVues Nombre de fois où l'engin a été vu, de type 'int'.
	 */
	public int getNbVues() {
		return nbVues;
	}

	/**
	 * RETOURNE LA LISTE DES VUES DE L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return listeVues Liste des vues de l'engin de type Vector<Vue>.
	 */
	public Vector<Vue> getVues() {
		// Trie la liste des vues par ordre chronologique.
		Collections.sort(listeVues);
		return listeVues;
	}

	/**
	 * AJOUTE UNE VUE A L'ENGIN.
	 * 
	 * @param newVue
	 *            Nouvelle vue à ajouter de type 'Vue'.
	 * @return Aucun.
	 */
	public void ajouterVue(Vue newVue) {
		listeVues.addElement(newVue);
		nbVues++;
	}

	/**
	 * RETOURNE LA DATE DE LA VUE LA PLUS RECENTE DE L'ENGIN. (UTILISE POUR
	 * DETERMINER LA DATE DE MISE A JOUR DE LA BASE DE DONNEES).
	 * 
	 * @param Aucun.
	 * @return oldest Date de la dernière vue enregistrée, de type 'LocalDate'.
	 */
	public LocalDate getDerniereVue() {
		LocalDate oldest = LocalDate.of(1994, 10, 16);
		Iterator<Vue> i = listeVues.iterator();
		while (i.hasNext()) {
			Vue vueCourante = i.next();
			if (vueCourante.getDate().isAfter(oldest)) {
				oldest = vueCourante.getDate();
			}
		}
		return oldest;
	}

	/**
	 * RETOURNE LE NOMBRE DE REMARQUES SUR L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return nbVues Nombre de remarques sur l'engin, de type 'int'.
	 */
	public int getNbRemarques() {
		return nbRemarques;
	}

	/**
	 * RETOURNE LA LISTE DES REMARQUES SUR L'ENGIN.
	 * 
	 * @param Aucun.
	 * @return listeVues Liste des remarques sur l'engin de type Vector
	 *         <Remarque>.
	 */
	public Vector<Remarque> getRemarques() {
		// Trie la liste des remarques par ordre chronologique
		Collections.sort(listeRemarques);
		return listeRemarques;
	}

	/**
	 * AJOUTE UNE REMARQUE SUR L'ENGIN.
	 * 
	 * @param newRemarque
	 *            Nouvelle remarque à ajouter de type 'Remarque'.
	 * @return Aucun.
	 */
	public void ajouterRem(Remarque newRemarque) {
		listeRemarques.add(newRemarque);
		nbRemarques++;
	}
}