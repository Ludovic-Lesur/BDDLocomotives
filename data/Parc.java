/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */

package data;

import graphic.*;
import typedef.*;
import java.io.*;
import java.util.*;
import java.time.*;
import org.jdom2.*;
import org.jdom2.output.*;

public class Parc {

	// Attributs
	String nom;
	public static String dossierParc;
	private int nbSeries;
	private int nbEngins;
	private int nbEnginsActuel;
	Vector<Serie> listeSeries;
	public static final String SYM_ALL = "Toutes les séries   ";
	private LocalDate miseAJour;

	/**
	 * CONSTRUCTEUR DE LA CLASSE PARC.
	 * 
	 * @param pParc
	 *            Nom du parc d'engins moteurs à creer, de type 'String'.
	 * @return Aucun.
	 */
	public Parc(String pParc) throws IOException, JDOMException {
		nom = pParc;
		dossierParc = Interface.dossierData + "/" + pParc;
		nbSeries = 0;
		nbEngins = 0;
		nbEnginsActuel = 0;
		listeSeries = new Vector<Serie>();
		// Parcours des fichiers dans le dossier passé en argument...
		File dossier = new File(dossierParc);
		File[] fichiers = dossier.listFiles();
		int i = 0;
		// Pour chaque fichier trouvé dans le dossier...
		for (i = 0; i < (fichiers.length); i++) {
			if (fichiers[i].isFile()) {
				String nomFichier = fichiers[i].toString();
				if (nomFichier.lastIndexOf(".") > 0) {
					// On vérifie l'extention XML.
					String ext = nomFichier.substring(nomFichier.lastIndexOf("."));
					if (ext.compareTo(".xml") == 0) {
						// On crée une structure de données.
						Serie s = new Serie(fichiers[i]);
						listeSeries.add(s);
						nbSeries++;
						nbEngins = nbEngins + s.getEffectif();
						nbEnginsActuel = nbEnginsActuel + s.getEffectifActuel();
						System.out.println("Ajout de la série " + s.getNomComplet());
					}
				}
			}
		}
		Collections.sort(listeSeries);
		updateMiseAJour();
	}

	/**
	 * MET A JOUR LA BASE DE DONNEES DU PARC D'ENGINS MOTEURS.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void update() {
		nbSeries = 0;
		nbEngins = 0;
		nbEnginsActuel = 0;
		listeSeries = new Vector<Serie>();
		// Parcours des fichiers dans le dossier passé en argument...
		File dossier = new File(dossierParc);
		File[] fichiers = dossier.listFiles();
		int i = 0;
		// Pour chaque fichier trouvé dans le dossier...
		for (i = 0; i < (fichiers.length); i++) {
			if (fichiers[i].isFile()) {
				String nomFichier = fichiers[i].toString();
				if (nomFichier.lastIndexOf(".") > 0) {
					// On vérifie l'extention XML.
					String ext = nomFichier.substring(nomFichier.lastIndexOf("."));
					if (ext.compareTo(".xml") == 0) {
						// On crée une structure de données.
						Serie s = new Serie(fichiers[i]);
						listeSeries.add(s);
						nbSeries++;
						nbEngins = nbEngins + s.getEffectif();
						nbEnginsActuel = nbEnginsActuel + s.getEffectifActuel();
					}
				}
			}
		}
		Collections.sort(listeSeries);
	}

	/**
	 * RETOURNE LE NOM DU PARC.
	 * 
	 * @param Aucun.
	 * @return nom Nom du parc d'engins moteurs de type 'String'.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * RETOURNE LE NOMBRE DE SERIES CONSTITUANT LE PARC.
	 * 
	 * @param Aucun.
	 * @return nbSeries Nombre de séries suivies de type 'int'.
	 */
	public int getNbSeries() {
		return nbSeries;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS DU PARC.
	 * 
	 * @param Aucun.
	 * @return nbEngins Nombre d'engins moteurs suivis de type 'int'.
	 */
	public int getEffectif() {
		return nbEngins;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS DU PARC AYANT ETE ENREGISTRES DANS LA
	 * BASE DE DONNEES.
	 * 
	 * @param Aucun.
	 * @return nbEnginsActuel Nombre d'engins moteurs enregistrés de type 'int'.
	 */
	public int getEffectifActuel() {
		return nbEnginsActuel;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS AYANT ETE VUS.
	 * 
	 * @param Aucun.
	 * @return nbEnginsVus Nombre d'engins moteurs ayant au moins une vue
	 *         enregistrée, de type 'int'.
	 */
	public int getNbEnginsVus() {
		int nbEnginsVus = 0;
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			nbEnginsVus = nbEnginsVus + serieCourante.getNbEnginsVus();
		}
		return nbEnginsVus;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS EN ATTENTE DE MISE EN SERVICE.
	 * 
	 * @param Aucun.
	 * @return nbEnginsEnAttente Nombre d'engins moteurs en attente de mise en
	 *         service, de type 'int'.
	 */
	public int getEnAttente() {
		int nbEnginsEnAttente = 0;
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			nbEnginsEnAttente = nbEnginsEnAttente + serieCourante.getEnAttente();
		}
		return nbEnginsEnAttente;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS EN SERVICE.
	 * 
	 * @param Aucun.
	 * @return nbEnginsEnService Nombre d'engins moteurs en service, de type
	 *         'int'.
	 */
	public int getEnService() {
		int nbEnginsEnService = 0;
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			nbEnginsEnService = nbEnginsEnService + serieCourante.getEnService();
		}
		return nbEnginsEnService;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS RADIES.
	 * 
	 * @param Aucun.
	 * @return nbEnginsRadies Nombre d'engins moteurs radiés, de type 'int'.
	 */
	public int getRadies() {
		int nbEnginsRadies = 0;
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			nbEnginsRadies = nbEnginsRadies + serieCourante.getRadies();
		}
		return nbEnginsRadies;
	}

	/**
	 * RETOURNE LE NOMBRE D'ENGINS MOTEURS PRESERVES.
	 * 
	 * @param Aucun.
	 * @return nbEnginsPreserves Nombre d'engins moteurs préservés, de type
	 *         'int'.
	 */
	public int getPreserves() {
		int nbEnginsPreserves = 0;
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			nbEnginsPreserves = nbEnginsPreserves + serieCourante.getPreserves();
		}
		return nbEnginsPreserves;
	}

	/**
	 * RETOURNE LA LISTE DES SERIES DU PARC.
	 * 
	 * @param Aucun.
	 * @return listeSeries Liste des séries du parc de type 'Vector<Serie>'.
	 */
	public Vector<Serie> getSeries() {
		Collections.sort(listeSeries);
		return listeSeries;
	}

	/**
	 * RETOURNE LA LISTE DES SERIES DU PARC SOUS FORME DE TABLEAU DE CHAINES DE
	 * CARACTERES.
	 * 
	 * @param Aucun.
	 * @return boxSeries Liste des séries du parc de type 'String[]'. Utilisée
	 *         pour créer la 'JComboBox' des séries dans la classe
	 *         'RechercheSerie'.
	 */
	public String[] getNomSeries() {
		String[] boxSeries = new String[nbSeries + 1];
		boxSeries[0] = SYM_ALL;
		Iterator<Serie> i = listeSeries.iterator();
		int indice = 1;
		while (i.hasNext()) {
			boxSeries[indice] = i.next().getNomComplet();
			indice++;
		}
		return boxSeries;
	}

	/**
	 * CREE UN FICHIER XML VIERGE POUR UNE NOUVELLE SERIE.
	 * 
	 * @param pIdentifiant
	 *            : Identifiant de la nouvelle série de type 'Identifiant'.
	 * @param pSerie
	 *            : Numéro de la nouvelle série de type 'int'.
	 * @param effectif
	 *            : Nombre d'engins moteurs de la nouvelle série de type 'int'.
	 * @param appariement
	 *            : Booléen indiquant si les engins moteurs sont numérotés par
	 *            paire (automotrices et autorails).
	 * @return Aucun.
	 */
	public Serie creerSerie(Identifiant pIdentifiant, int pSerie, int effectif, boolean appariement) {
		String nomRacine = pIdentifiant.getSymbol().toLowerCase() + Integer.toString(pSerie);
		Element newRacine = new Element(nomRacine);
		DocType xmlFormat = new DocType(nomRacine, "format.dtd");
		Document newDocument = new Document(newRacine, xmlFormat);
		// Infos générales
		Element identifiant = new Element(BaliseXML.XML_IDENTIFIANT);
		identifiant.setText(pIdentifiant.getSymbol());
		newRacine.addContent(identifiant);
		Element serie = new Element(BaliseXML.XML_SERIE);
		serie.setText(Integer.toString(pSerie));
		newRacine.addContent(serie);
		// Ajout des engins moteurs
		int i = 0;
		for (i = 0; i < effectif; i++) {
			Element engin = new Element(BaliseXML.XML_ENGIN);
			newRacine.addContent(engin);
			// Numéro
			Element numero = new Element(BaliseXML.XML_NUMERO);
			if (appariement == true) {
				numero.setText(Integer.toString(pSerie + 2 * i + 1) + "/" + Integer.toString(pSerie + 2 * i + 2));
			} else {
				numero.setText(Integer.toString(pSerie + i + 1));
			}
			engin.addContent(numero);
			// Renumérotation
			Element renumerotation = new Element(BaliseXML.XML_RENUMEROTATION);
			engin.addContent(renumerotation);
			// Date de mise en service
			Element dateMES = new Element(BaliseXML.XML_DATEMES);
			engin.addContent(dateMES);
			// Etat
			Element etat = new Element(BaliseXML.XML_ETAT);
			engin.addContent(etat);
			// Préservation
			Element preservation = new Element(BaliseXML.XML_PRESERVATION);
			engin.addContent(preservation);
			// Date de radiation
			Element dateRAD = new Element(BaliseXML.XML_DATERAD);
			engin.addContent(dateRAD);
			// Livrée
			Element livree = new Element(BaliseXML.XML_LIVREE);
			engin.addContent(livree);
			// Dépôt
			Element depot = new Element(BaliseXML.XML_DEPOT);
			engin.addContent(depot);
		}
		// Création du dossier photo
		File newDossier = new File(dossierParc + "/photos/" + nomRacine);
		newDossier.mkdirs();
		// Création du fichier XML
		try {
			File newFichier = new File(dossierParc + "/" + nomRacine + ".xml");
			Format f = Format.getPrettyFormat();
			f.setExpandEmptyElements(true);
			XMLOutputter sortie = new XMLOutputter(f);
			sortie.output(newDocument, new FileOutputStream(newFichier));
			Serie s = new Serie(newFichier);
			listeSeries.add(s);
			nbSeries++;
			nbEngins = nbEngins + s.getEffectif();
			System.out.println("Ajout de la série " + s.getNomComplet());
			return s ;
		}
		catch (java.io.IOException e) {
			return null;
		}
		
	}

	/**
	 * RECHERCHE UNE SERIE DANS LE PARC.
	 * 
	 * @param serieCherchee
	 *            : Nom complet (identifiant + numéro) de la série cherchée, de
	 *            type 'String'.
	 * @return resultat : Série trouvée de type 'Serie'. Le résultat est unique
	 *         et certain par construction.
	 */
	public Serie rechercherSerie(String serieCherchee) {
		Iterator<Serie> i = listeSeries.iterator();
		Serie serieTrouvee = null;
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			if (serieCourante.getNomComplet().compareTo(serieCherchee) == 0) {
				serieTrouvee = serieCourante;
				break;
			}
		}
		return serieTrouvee;
	}

	/**
	 * RECHERCHE UN ENGIN MOTEUR DANS LE PARC.
	 * 
	 * @param id
	 *            : Identifiant de l'engin moteur cherché, de type
	 *            'Identifiant'.
	 * @param numCherche
	 *            Numéro de l'engin moteur cherche, de type 'int'.
	 * @return enginTrouve : Engin moteur trouvé de type 'Engin' si la recherche
	 *         a abouti. Le résultat est dans ce cas unique. 'null' sinon.
	 */
	public Engin rechercherEngin(Identifiant id, String numCherche) {
		Engin enginTrouve = null;
		// On parcourt toutes les séries
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Serie serieCourante = i.next();
			// Si l'identifiant est le même...
			if (serieCourante.getIdentifiant().equals(id)) {
				// On cherche l'engin moteur avec son numéro
				Engin resultatTemp = serieCourante.rechercherEngin(numCherche, dossierParc);
				if (resultatTemp != null) {
					enginTrouve = resultatTemp;
					break;
				}
			}
		}
		return enginTrouve;
	}

	/**
	 * RETOURNE LA LISTE DES ENGINS MOTEURS AYANT UNE DATE DE MISE EN SERVICE
	 * DONNEE.
	 * 
	 * @param date
	 *            : Date de mise en service cherchée, de type 'LocalDate'.
	 * @return enginsTrouves : Liste des engins moteurs dont la date de mise en
	 *         service correspond au jour et au mois de 'date'.
	 */
	public Vector<Engin> getAnniversaires(LocalDate date) {
		Vector<Engin> enginsTrouves = new Vector<Engin>();
		// On parcourt toutes les séries
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			Vector<Engin> tableau = i.next().getAnniversaires(date);
			enginsTrouves.addAll(tableau);
		}
		return enginsTrouves;
	}
	
	/**
	 * RENVOIE LA DATE DE VUE LA DATE DE MISE A JOUR DE LA BASE DE DONNEES.
	 * 
	 * @param Aucun.
	 * @return derniere Date de la dernière vue enregistrée, de type
	 *         'LocalDate'.
	 */
	public LocalDate getUpdate() {
		return miseAJour;
	}
	
	/**
	 * MODIFIE LA DATE DE VUE LA DATE DE MISE A JOUR DE LA BASE DE DONNEES.
	 * 
	 * @param Aucun.
	 * @return derniere Date de la dernière vue enregistrée, de type
	 *         'LocalDate'.
	 */
	public void setUpdate(LocalDate newDate) {
		miseAJour = newDate;
	}

	/**
	 * RECALCULE LA DATE DE VUE LA DATE DE MISE A JOUR DE LA BASE DE DONNEES.
	 * 
	 * @param Aucun.
	 * @return derniere Date de la dernière vue enregistrée, de type
	 *         'LocalDate'.
	 */
	public LocalDate updateMiseAJour() {
		miseAJour = LocalDate.of(1994, 10, 16);
		Iterator<Serie> i = listeSeries.iterator();
		while (i.hasNext()) {
			LocalDate currentDate = i.next().getDerniereVue();
			if (currentDate.isAfter(miseAJour)) {
				miseAJour = currentDate;
			}
		}
		return miseAJour;
	}
}