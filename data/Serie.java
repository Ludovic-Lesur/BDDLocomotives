/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */


package data ;

import graphic.* ;
import typedef.* ;
import java.io.* ;
import java.util.* ;
import java.time.* ;
import org.jdom2.* ;
import org.jdom2.input.* ;
import org.jdom2.output.* ;


public class Serie implements Comparable<Serie> {

	// Parseur XML
	private File fichierXML ;
	private Document document ;
	private Element racine ;
	// Attributs
	private Identifiant identifiant ;
	private int serie ;
	private Type type ;
	private int effectif ;
	private int enService ;
	private int enAttente ;
	private int preserves ;
	private int radies ;
	
	
	/**
		CONSTRUCTEUR SANS ARGUMENT DE LA CLASSE SERIE.
	    @param	Aucun.
	    @return	Aucun.
	*/
	public Serie() {}
	
	
	/**
		CONSTRUCTEUR DE LA CLASSE SERIE.
	    @param sourceXML	Chemin absolu de type 'String' du fichier XML contenant les données de la série.
	    @return				Aucun.
	*/
	public Serie(File sourceXML) {
		
		fichierXML = sourceXML ;
		
		identifiant = Identifiant.Vide ;
		type = Type.Vide ;
		effectif = 0 ;
		enService = 0 ;
		enAttente = 0 ;
		preserves = 0 ;
		radies = 0 ;
		
		// On crée un nouveau document JDOM avec en argument le fichier XML
		SAXBuilder sxb = new SAXBuilder() ;
		try {
			document = sxb.build(sourceXML) ;
			racine = document.getRootElement() ;
			calculerAttributs() ;
		}
		catch (IOException e) {
			System.out.println("Fichier non trouvé.") ;
		}
		catch (JDOMException e) {
			System.out.println("Erreur de parsing.") ;
		}
	}
	
	
	/**
		MET A JOUR LA BASE DE DONNEES DE LA SERIE.
	    @param	Aucun.
	    @return	Aucun.
	*/
	public void update() {
		effectif = 0 ;
		enService = 0 ;
		enAttente = 0 ;
		preserves = 0 ;
		radies = 0 ;
		
		// On crée un nouveau document JDOM avec en argument le fichier XML
		SAXBuilder sxb = new SAXBuilder() ;
		try {
			document = sxb.build(fichierXML) ;
			racine = document.getRootElement() ;
			calculerAttributs() ;
		}
		catch (IOException e) {
			System.out.println("Fichier non trouvé.") ;
		}
		catch (JDOMException e) {
			System.out.println("Erreur de parsing.") ;
		}
	}
	
	
	/**
		MET A JOUR LES CARACTERISTIQUES DE LA SERIE.
	    @param	Aucun.
	    @return	Aucun.
	*/
	private void calculerAttributs() {
		identifiant = Identifiant.affecter(racine.getChild(BaliseXML.XML_IDENTIFIANT).getText()) ;
		serie = stringToInteger(racine.getChild(BaliseXML.XML_SERIE).getText()) ;
		type = Type.affecter(identifiant, serie) ;
		effectif = 0 ;
		enService = 0 ;
		enAttente = 0 ;
		preserves = 0 ;
		radies = 0 ;
		// Parcours des engins...
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			effectif++ ;
			String symbol = enginCourant.getChild(BaliseXML.XML_ETAT).getText() ;
			if (symbol.compareTo(Etat.AMES.getSymbol()) == 0) {
				enAttente++ ;
			}
			if (symbol.compareTo(Etat.ES.getSymbol()) == 0) {
				enService++ ;
			}
			if (symbol.compareTo(Etat.R.getSymbol()) == 0) {
				radies++ ;
			}
			if (symbol.compareTo(Etat.PR.getSymbol()) == 0) {
				preserves++ ;
			}
		}
	}
	
	
	/**
		RETOURNE L'IDENTIFIANT DE LA SERIE.
	    @param					Aucun.
	    @return	identifiant		Identifiant de la série de type 'Identifiant'.
	*/
	public Identifiant getIdentifiant() {
		return identifiant ;
	}
	
	
	/**
		RETOURNE LE TYPE DE LA SERIE.
	    @param			Aucun.
	    @return	type	Type de la série de type 'Type'.
	*/
	public Type getType() {
		return type ;
	}
	
	
	/**
		RETOURNE LE TYPE DE LA SERIE.
	    @param			Aucun.
	    @return	type	Type de la série de type 'String'.
	*/
	public String getTypeString() {
		return type.getName() ;
	}
	
	
	/**
		RETOURNE LE NOM COMPLET DE LA SERIE.
	    @param		Aucun.
	    @return		Nom complet de la série (identifiant et numéro de série).
	*/
	public String getNomComplet() {
		return (identifiant.getName() + " " + serie) ;
	}
	
	
	/**
		RETOURNE L'EFFECTIF DE LA SERIE.
	    @param				Aucun.
	    @return	effectif	Nombre d'engins moteurs de la série de type 'int'.
	*/
	public int getEffectif() {
		return effectif ;
	}
	
	
	/**
		RETOURNE L'EFFECTIF DE LA SERIE AYANT ETE ENREGISTRE DANS LA BASE DE DONNEE.
	    @param		Aucun.
	    @return		Nombre d'engins moteurs enregistrés de type 'int'.
	*/
	public int getEffectifActuel() {
		return (enAttente + enService + radies + preserves) ;
	}
	
	
	/**
		RETOURNE L'EFFECTIF EN SERVICE DE LA SERIE.
	    @param				Aucun.
	    @return	enService	Nombre d'engins moteurs de la série en service.
	*/
	public int getEnService() {
		return enService ;
	}
	
	
	/**
		RETOURNE L'EFFECTIF EN ATTENTE DE MISE EN SERVICE DE LA SERIE.
	    @param				Aucun.
	    @return	eAttente	Nombre d'engins moteurs de la série en attente de mise en service.
	*/
	public int getEnAttente() {
		return enAttente ;
	}
	
	
	/**
		RETOURNE L'EFFECTIF RADIE DE LA SERIE.
	    @param			Aucun.
	    @return	radies	Nombre d'engins moteurs de la série radiés.
	*/
	public int getRadies() {
		return radies ;
	}
	
	
	/**
		RETOURNE L'EFFECTIF PRESERVE DE LA SERIE.
	    @param				Aucun.
	    @return	preserves	Nombre d'engins moteurs de la série préservés.
	*/
	public int getPreserves() {
		return preserves ;
	}
	
	
	/**
		RETOURNE LA DATE DE MISE EN SERVICE LA PLUS ANCIENNE DE LA SERIE.
		UTILISE POUR CLASSER LES SERIES DANS L'ORDRE CHRONOLOGIQUE POUR LA CLASSE 'TABLEAU'.
	    @param				Aucun.
	    @return	dateMESMin	Date de mise en service la plus ancienne de la série de type 'LocalDate'.
	*/
	public LocalDate getJourMESMin() {
		LocalDate dateMESMin = LocalDate.now() ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				LocalDate dateMES = e.getDateMES() ;
				// On ne prend pas en compte les engins en attente...
				if (dateMES != null) {
					if (dateMES.isBefore(dateMESMin)) {
						dateMESMin = dateMES ;
					}
				}
			}
		}
		return dateMESMin ;
	}
	
	
	/**
		RETOURNE L'ANNEE DE MISE EN SERVICE LA PLUS ANCIENNE DE LA SERIE.
	    @param				Aucun.
	    @return	dateMESMin	Annee de mise en service la plus ancienne de la série de type 'String'.
	*/
	public String getAnneeMESMin() {
		int resultat = Integer.MAX_VALUE ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				LocalDate dateMES = e.getDateMES() ;
				// On ne prend pas en compte les engins en attente...
				if (dateMES != null) {
					if (dateMES.getYear() < resultat) {
						resultat = dateMES.getYear() ;
					}
				}
			}
		}
		return Integer.toString(resultat) ;
	}
	
	
	/**
		RETOURNE L'ANNEE DE MISE EN SERVICE LA PLUS RECENTE DE LA SERIE.
	    @param				Aucun.
	    @return	dateMESMin	Annee de mise en service la plus récente de la série de type 'String'.
	*/
	public String getAnneeMESMax() {
		int resultat = Integer.MIN_VALUE ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				LocalDate dateMES = e.getDateMES() ;
				// On ne prend pas en compte les engins en attente...
				if (dateMES != null) {
					if (dateMES.getYear() > resultat) {
						resultat = dateMES.getYear() ;
					}
				}
			}
		}
		return Integer.toString(resultat) ;
	}
	
	
	/**
		RETOURNE LE NOMBRE D'ENGINS VUS DE LA SERIE.
	    @param					Aucun.
	    @return	nbEnginsVus		Nombre d'engins moteurs de la série ayant été vus au moins une fois.
	*/
	public int getNbEnginsVus() {
		int nbEnginsVus = 0 ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				if (e.getNbVues() > 0) {
					nbEnginsVus++ ;
				}
			}
		}
		return nbEnginsVus ;
	}
	
	
	/**
		RECHERCHE UN ENGIN DANS LA SERIE A PARTIR DE SON NUMERO.
	    @param numCherche		Numéro de l'engin cherché de type 'int'.
	    @param dossierParc		Chemin absolu dossier du parc d'engins moteurs, où se trouve le fichier XML de la série.
	    @return	enginTrouve		Engin trouvé de type 'Engin' si la recherche aboutit.
	    						'null' sinon.
	*/
	public Engin rechercherEngin(int numCherche, String dossierParc) {
		Engin enginTrouve = null ;
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		int numCourant ;
		int renumCourante ;
		int k = 0 ; // Compteur pour l'indice.
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			k++ ;
			numCourant = stringToInteger(enginCourant.getChild(BaliseXML.XML_NUMERO).getText()) ;
			renumCourante = stringToInteger(enginCourant.getChild(BaliseXML.XML_RENUMEROTATION).getText()) ;
			if ((numCourant == numCherche) || (renumCourante == numCherche)) {
				// Engin trouvé -> création de l'objet à partir des données XML.
				enginTrouve = new Engin() ;
				enginTrouve.setSerie(this) ;
				enginTrouve.setIdentifiant(identifiant) ;
				enginTrouve.setNumero(numCourant) ;
				enginTrouve.setRenumerotation(enginCourant.getChild(BaliseXML.XML_RENUMEROTATION).getText()) ;
				enginTrouve.setDateMES(stringToDate(enginCourant.getChild(BaliseXML.XML_DATEMES).getText())) ;
				enginTrouve.setEtat(enginCourant.getChild(BaliseXML.XML_ETAT).getText()) ;
				enginTrouve.setPreservation(enginCourant.getChild(BaliseXML.XML_PRESERVATION).getText()) ;
				enginTrouve.setDateRAD(stringToDate(enginCourant.getChild(BaliseXML.XML_DATERAD).getText())) ;
				enginTrouve.calculerAge() ;
				enginTrouve.setLivree(enginCourant.getChild(BaliseXML.XML_LIVREE).getText()) ;
				enginTrouve.setDepot(enginCourant.getChild(BaliseXML.XML_DEPOT).getText()) ;
				enginTrouve.setIndice(k) ;
				// Photo.
				String dossierPhoto = dossierParc + "/photos/" + identifiant.getName().toLowerCase() + serie ;
				enginTrouve.chercherPhoto(dossierPhoto) ;
				// Boucle des vues.
				List<Element> listeVues = enginCourant.getChildren(BaliseXML.XML_VUE) ;
				Iterator<Element> itVues = listeVues.iterator() ;
				while(itVues.hasNext()) {
				    Element vueCourante = (Element)itVues.next() ;
				    // Vue trouvée -> création de l'objet à partir des données XML.
				    Vue resVue = new Vue() ;
				    resVue.setDate(stringToDate(vueCourante.getChild(BaliseXML.XML_DATEVUE).getText())) ;
				    resVue.setLieu(vueCourante.getChild(BaliseXML.XML_LIEU).getText()) ;
				    Element voyage = vueCourante.getChild(BaliseXML.XML_VOYAGE) ;
				    resVue.setVoyage(voyage.getChild(BaliseXML.XML_DEPART).getText(), voyage.getChild(BaliseXML.XML_ARRIVEE).getText()) ;
				    resVue.setTrain(vueCourante.getChild(BaliseXML.XML_TRAIN).getText()) ;
				    resVue.setCabine(stringToBoolean(vueCourante.getChild(BaliseXML.XML_CABINE).getText())) ;
				    resVue.setPhoto(stringToBoolean(vueCourante.getChild(BaliseXML.XML_PHOTO).getText())) ;
				    resVue.setVideo(stringToBoolean(vueCourante.getChild(BaliseXML.XML_VIDEO).getText())) ;
				    enginTrouve.ajouterVue(resVue) ;
				}
				// Boucle des remarques.
				List<Element> listeRemarques = enginCourant.getChildren(BaliseXML.XML_REM) ;
				Iterator<Element> itRemarques = listeRemarques.iterator() ;
				while(itRemarques.hasNext()) {
				    Element remarqueCourante = (Element)itRemarques.next() ;
				    // Remarque trouvée -> création de l'objet à partir des données XML.
				    Remarque resRemarque = new Remarque() ;
				    resRemarque.setDate(stringToDate(remarqueCourante.getChild(BaliseXML.XML_DATEREM).getText())) ;
				    resRemarque.setTexte(remarqueCourante.getChild(BaliseXML.XML_TEXTE).getText()) ;
				    enginTrouve.ajouterRem(resRemarque) ;
				}
				// On sort de la boucle while de recherche.
				break ;
			}
		}
		return enginTrouve ;
	}
	
	
	/**
		RENVOIE LE NUMERO DE L'ENGIN CORREPONDANT A UN INDICE DONNE.
	    @param indiceCherche	Indice recherché de type 'int'.
	    @return	resultat		Numéro de l'engin moteur correspondant à l'indice cherché.
	*/
	public int rechercherNumero(int indiceCherche) {
		int resultat = 0 ;
		if (indiceCherche >= 1 && indiceCherche <= effectif) {
			List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
			Iterator<Element> itEngins = listEngins.iterator() ;
			int k = 0 ; // Compteur pour l'indice.
			while(itEngins.hasNext()) {
				Element enginCourant = (Element)itEngins.next() ;
				k++ ;
				if (k == indiceCherche) {
					resultat = stringToInteger(enginCourant.getChild(BaliseXML.XML_NUMERO).getText()) ;
					break ;
				}
			}
		}
		return resultat ;
	}

	
	/**
		ENREGISTRE LE FICHIER XML DE LA SERIE.
	    @param 	Aucun.
	    @return	Aucun.
	*/
	private void saveXML() {
		try {
			String nomRacine = identifiant.getSymbol().toLowerCase() + Integer.toString(serie) ;
			DocType xmlFormat = new DocType(nomRacine, "format.dtd") ;
			document.setDocType(xmlFormat) ;
	    	Format f = Format.getPrettyFormat() ;
	    	f.setExpandEmptyElements(true) ;
	        XMLOutputter sortie = new XMLOutputter(f) ;
	        sortie.output(document, new FileOutputStream(fichierXML)) ;
	    }
	    catch (java.io.IOException e){}
	}

	
	/**
		MODIFIE L'ETAT D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param e		Nouvel état de l'engin de type 'Etat'.
	    @return			Aucun.
	*/
	public void modifyXML(String numero, Etat e) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				enginCourant.getChild(BaliseXML.XML_ETAT).setText(e.getSymbol()) ;
				System.out.println("Modification de l'état de l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LA RENUMEROTATION D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero				Numéro de l'engin à modifier de type 'String'.
	    @param strRenumerotation	Nouvelle renumérotation de l'engin de type 'String'.
	    @return						Aucun.
	*/
	public void modifyXML(String numero, String strRenumerotation) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				enginCourant.getChild(BaliseXML.XML_RENUMEROTATION).setText(strRenumerotation) ;
				System.out.println("Modification de la renumérotation de l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LA DATE DE MISE EN SERVICE OU DE RADIATION D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param d		Nouvelle date de type 'LocalDate'.
	    @param mes		Mode de la fonction de type 'boolean'.
	    					true = date de mise en service modifiée.
	    					false = date de radiation modfiée.
	    @return			Aucun.
	*/
	public void modifyXML(String numero, LocalDate d, boolean mes) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				if (mes == true) {
					if (d != null) {
						enginCourant.getChild(BaliseXML.XML_DATEMES).setText(Interface.dateToString(d, false)) ;
					}
					else {
						enginCourant.getChild(BaliseXML.XML_DATEMES).setText(Jour.Vide.getSymbol()) ;
					}
					System.out.println("Modification de la date MES de l'engin " + identifiant + " " + strNum) ;
				}
				else {
					if (d != null) {
						enginCourant.getChild(BaliseXML.XML_DATERAD).setText(Interface.dateToString(d, false)) ;
					}
					else {
						enginCourant.getChild(BaliseXML.XML_DATERAD).setText(Jour.Vide.getSymbol()) ;
					}
					System.out.println("Modification de la date RAD de l'engin " + identifiant + " " + strNum) ;
				}
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LA PRESERVATION D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param a		Nouvelle association de préservation de l'engin de type 'Association'.
	    @return			Aucun.
	*/
	public void modifyXML(String numero, Association a) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				if (a != null) {
					enginCourant.getChild(BaliseXML.XML_PRESERVATION).setText(a.getSymbol()) ;
				}
				else {
					enginCourant.getChild(BaliseXML.XML_PRESERVATION).setText(Association.Vide.getSymbol()) ;
				}
				System.out.println("Modification de la préservation de l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LA LIVREE D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param l		Nouvelle livrée de l'engin de type 'Livree'.
	    @return			Aucun.
	*/
	public void modifyXML(String numero, Livree l) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				enginCourant.getChild(BaliseXML.XML_LIVREE).setText(l.getSymbol()) ;
				System.out.println("Modification de la livrée de l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LE DEPOT D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param d		Nouveau dépôt de l'engin de type 'Depot'.
	    @return			Aucun.
	*/
	public void modifyXML(String numero, Depot d) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				enginCourant.getChild(BaliseXML.XML_DEPOT).setText(d.getSymbol()) ;
				System.out.println("Modification du dépôt de l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		AJOUTE UNE VUE A UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param v		Nouvelle vue de l'engin de type 'Vue'.
	    @return			Aucun.
	*/
	public void addXML(String numero, Vue v) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				// Ajout de la vue
				Element vue = new Element(BaliseXML.XML_VUE) ;
				// Attributs de la vue
				Element date = new Element(BaliseXML.XML_DATEVUE) ;
				date.setText(Interface.dateToString(v.getDate(), false)) ;
				Element lieu = new Element(BaliseXML.XML_LIEU) ;
				lieu.setText(v.getLieu().getSymbol()) ;
 				Element voyage = new Element(BaliseXML.XML_VOYAGE) ;
				Element depart = new Element(BaliseXML.XML_DEPART) ;
				depart.setText(v.getVoyageDepart().getSymbol()) ;
				Element arrivee = new Element(BaliseXML.XML_ARRIVEE) ;
				arrivee.setText(v.getVoyageArrivee().getSymbol()) ;
				voyage.addContent(depart) ;
				voyage.addContent(arrivee) ;
				Element train = new Element(BaliseXML.XML_TRAIN) ;
				train.setText(v.getTrain().getSymbol()) ;
				Element cabine = new Element(BaliseXML.XML_CABINE) ;
				cabine.setText(v.getNomCabine()) ;
				Element photo = new Element(BaliseXML.XML_PHOTO) ;
				photo.setText(v.getNomPhoto()) ;
				Element video = new Element(BaliseXML.XML_VIDEO) ;
				video.setText(v.getNomVideo()) ;	
				vue.addContent(date) ;
				vue.addContent(lieu) ;
				vue.addContent(voyage) ;
				vue.addContent(train) ;
				vue.addContent(cabine) ;
				vue.addContent(photo) ;
				vue.addContent(video) ;
				enginCourant.addContent(vue) ;
				System.out.println("Ajout d'une vue sur l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LA VUE D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param oldVue	Ancienne vue de l'engin à modifier, de type 'Vue'.
	    @param newVue	Nouvelle vue de type 'Vue' qui remplacera 'oldVue'.
	    @return			Aucun.
	*/
	public void modifyXML(String numero, Vue oldVue, Vue newVue) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element) itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				// On cherche la vue à supprimer en fonction de la date, du lieu, du départ et de l'arrivée
				List<Element> listVues = enginCourant.getChildren(BaliseXML.XML_VUE) ;
				Iterator<Element> itVues = listVues.iterator() ;
				while(itVues.hasNext()) {
					Element vueCourante = (Element) itVues.next() ;
					// On reconstruit un objet Vue à partir des données XML
			    	Vue vue = new Vue() ;
			    	vue.setDate(stringToDate(vueCourante.getChild(BaliseXML.XML_DATEVUE).getText())) ;
			    	vue.setLieu(vueCourante.getChild(BaliseXML.XML_LIEU).getText()) ;
			    	Element voyage = vueCourante.getChild(BaliseXML.XML_VOYAGE) ;
			    	vue.setVoyage(voyage.getChild(BaliseXML.XML_DEPART).getText(), voyage.getChild(BaliseXML.XML_ARRIVEE).getText()) ;
			    	vue.setTrain(vueCourante.getChild(BaliseXML.XML_TRAIN).getText()) ;
			    	vue.setCabine(stringToBoolean(vueCourante.getChild(BaliseXML.XML_CABINE).getText())) ;
			    	vue.setPhoto(stringToBoolean(vueCourante.getChild(BaliseXML.XML_PHOTO).getText())) ;
			    	vue.setVideo(stringToBoolean(vueCourante.getChild(BaliseXML.XML_VIDEO).getText())) ;
			    	// Et on la compare à la vue à modifier
			    	if (oldVue.equals(vue)) {
			    		// Modification avec newVue
			    		vueCourante.getChild(BaliseXML.XML_DATEVUE).setText(Interface.dateToString(newVue.getDate(), false)) ;
			    		vueCourante.getChild(BaliseXML.XML_LIEU).setText(newVue.getLieu().getSymbol()) ;
			    		Element voyageCourant = vueCourante.getChild(BaliseXML.XML_VOYAGE) ;
			    		voyageCourant.getChild(BaliseXML.XML_DEPART).setText(newVue.getVoyageDepart().getSymbol()) ;
			    		voyageCourant.getChild(BaliseXML.XML_ARRIVEE).setText(newVue.getVoyageArrivee().getSymbol()) ;
			    		vueCourante.getChild(BaliseXML.XML_TRAIN).setText(newVue.getTrain().getSymbol()) ;
			    		vueCourante.getChild(BaliseXML.XML_CABINE).setText(newVue.getNomCabine()) ;
			    		vueCourante.getChild(BaliseXML.XML_PHOTO).setText(newVue.getNomPhoto()) ;
			    		vueCourante.getChild(BaliseXML.XML_VIDEO).setText(newVue.getNomVideo()) ;
			    		System.out.println("Modification d'une vue sur l'engin " + identifiant + " " + strNum) ;
			    		break ;
			    	}
				}
			}
		}
		saveXML() ;
	}
	
	
	/**
		SUPPRIME UNE VUE D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param v		Vue de l'engin à supprimer, de type 'Vue'.
	    @return			Aucun.
	*/
	public void deleteXML(String numero, Vue v) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element) itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				// On cherche la vue à supprimer en fonction de la date, du lieu, du départ et de l'arrivée
				List<Element> listVues = enginCourant.getChildren(BaliseXML.XML_VUE) ;
				Iterator<Element> itVues = listVues.iterator() ;
				while(itVues.hasNext()) {
					Element vueCourante = (Element) itVues.next() ;
					// On reconstruit un objet Vue à partir des données XML
			    	Vue vue = new Vue() ;
			    	vue.setDate(stringToDate(vueCourante.getChild(BaliseXML.XML_DATEVUE).getText())) ;
			    	vue.setLieu(vueCourante.getChild(BaliseXML.XML_LIEU).getText()) ;
			    	Element voyage = vueCourante.getChild(BaliseXML.XML_VOYAGE) ;
			    	vue.setVoyage(voyage.getChild(BaliseXML.XML_DEPART).getText(), voyage.getChild(BaliseXML.XML_ARRIVEE).getText()) ;
			    	vue.setTrain(vueCourante.getChild(BaliseXML.XML_TRAIN).getText()) ;
			    	vue.setCabine(stringToBoolean(vueCourante.getChild(BaliseXML.XML_CABINE).getText())) ;
			    	vue.setPhoto(stringToBoolean(vueCourante.getChild(BaliseXML.XML_PHOTO).getText())) ;
			    	vue.setVideo(stringToBoolean(vueCourante.getChild(BaliseXML.XML_VIDEO).getText())) ;
			    	System.out.println("Test d'égalité") ;
			    	// Et on la compare à la vue à supprimer
			    	if (v.equals(vue)) {
			    		System.out.println("Suppression d'une vue sur l'engin " + identifiant + " " + strNum) ;
			    		enginCourant.removeContent(vueCourante) ;
			    		break ;
			    	}
				}
			}
		}
		saveXML() ;
	}
	
	
	/**
		AJOUTE UNE REMARQUE A UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param r		Nouvelle remarque de l'engin de type 'Remarque'.
	    @return			Aucun.
	*/
	public void addXML(String numero, Remarque r) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element)itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				// Ajout de la remarque
				Element remarque = new Element(BaliseXML.XML_REM) ;
				// Attributs de la remarque
				Element date = new Element(BaliseXML.XML_DATEREM) ;
				date.setText(Interface.dateToString(r.getDate(), false)) ;
				Element texte = new Element(BaliseXML.XML_TEXTE) ;
				texte.setText(r.getTexte()) ;
				remarque.addContent(date) ;
				remarque.addContent(texte) ;
				enginCourant.addContent(remarque) ;
				System.out.println("Ajout d'une remarque sur l'engin " + identifiant + " " + strNum) ;
				break ;
			}
		}
		saveXML() ;
	}
	
	
	/**
		MODIFIE LA REMARQUE D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero		Numéro de l'engin à modifier de type 'String'.
	    @param oldRemarque	Ancienne remarque de l'engin à modifier, de type 'Remarque'.
	    @param newRemarque	Nouvelle remarque de type 'Remarque' qui remplacera 'oldRemarque'.
	    @return				Aucun.
	*/
	public void modifyXML(String numero, Remarque oldRemarque, Remarque newRemarque) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element) itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				// On cherche la remarque à supprimer en fonction de la date et du texte
				List<Element> listRemarques = enginCourant.getChildren(BaliseXML.XML_REM) ;
				Iterator<Element> itVues = listRemarques.iterator() ;
				while(itVues.hasNext()) {
					Element remarqueCourante = (Element) itVues.next() ;
					// On reconstruit un objet Remarque à partir des données XML
					Remarque remarque = new Remarque() ;
			    	remarque.setDate(stringToDate(remarqueCourante.getChild(BaliseXML.XML_DATEREM).getText())) ;
			    	remarque.setTexte(remarqueCourante.getChild(BaliseXML.XML_TEXTE).getText()) ;
			    	// Et on la compare à la vue à modifier
			    	if (oldRemarque.equals(remarque)) {
			    		// Modification avec newRemarque
			    		remarqueCourante.getChild(BaliseXML.XML_DATEREM).setText(Interface.dateToString(newRemarque.getDate(), false)) ;
			    		remarqueCourante.getChild(BaliseXML.XML_TEXTE).setText(newRemarque.getTexte()) ;
			    		System.out.println("Modification d'une remarque sur l'engin " + identifiant + " " + strNum) ;
			    		break ;
			    	}
				}
			}
		}
		saveXML() ;
	}
	
	
	/**
		SUPPRIME UNE REMARQUE D'UN ENGIN MOTEUR DE LA SERIE DANS LE FICHIER XML.
	    @param numero	Numéro de l'engin à modifier de type 'String'.
	    @param r		Remarque de l'engin à supprimer, de type 'Remarque'.
	    @return			Aucun.
	*/
	public void deleteXML(String numero, Remarque r) {
		List<Element> listEngins = racine.getChildren(BaliseXML.XML_ENGIN) ;
		Iterator<Element> itEngins = listEngins.iterator() ;
		// On cherche l'engin à modifier
		while(itEngins.hasNext()) {
			Element enginCourant = (Element) itEngins.next() ;
			String strNum = enginCourant.getChild(BaliseXML.XML_NUMERO).getText() ;
			if (strNum.compareTo(numero) == 0) {
				// On cherche la remarque à supprimer en fonction de la date et du texte
				List<Element> listRemarques = enginCourant.getChildren(BaliseXML.XML_REM) ;
				Iterator<Element> itVues = listRemarques.iterator() ;
				while(itVues.hasNext()) {
					Element remarqueCourante = (Element) itVues.next() ;
					// On reconstruit un objet Remarque à partir des données XML
					Remarque remarque = new Remarque() ;
			    	remarque.setDate(stringToDate(remarqueCourante.getChild(BaliseXML.XML_DATEREM).getText())) ;
			    	remarque.setTexte(remarqueCourante.getChild(BaliseXML.XML_TEXTE).getText()) ;
			    	// Et on la compare à la vue à supprimer
			    	if (r.equals(remarque)) {
			    		System.out.println("Suppression d'une remarque sur l'engin " + identifiant + " " + strNum) ;
			    		enginCourant.removeContent(remarqueCourante) ;
			    		break ;
			    	}
				}
			}
		}
		saveXML() ;
	}
	
	
	/**
		RETOURNE LA LISTE DES ENGINS DE LA SERIE.
	    @param 					Aucun.
	    @return	serieEngins		Liste des engins de la série de type Vector<Engin>.
	*/
	public Vector<Engin> getEngins() {
		Vector<Engin> serieEngins = new Vector<Engin>() ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				serieEngins.add(e) ;
			}
		}
		return serieEngins ;
	}
	
	
	/**
		RETOURNE LA LISTE DES ENGINS DE LA SERIE AYANT UNE DATE DE MISE EN SERVICE DONNEE.
	    @param date				Date de mise en service cherchée de type 'LocalDate'.
	    @return	serieEngins		Liste des engins de la série mis en service les mêmes jour et mois que 'date', de type Vector<Engin>.
	*/
	public Vector<Engin> getAnniversaires(LocalDate date) {
		Vector<Engin> resultat = new Vector<Engin>() ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				if (e.anniversaire(date)) {
					resultat.add(e) ;
				}
			}
		}
		return resultat ;
	}
	
	
	/**
		RETOURNE LA DATE DE LA VUE LA PLUS RECENTE SUR TOUS LES ENGINS DE LA SERIE.
		(UTILISE POUR DETERMINER LA DATE DE MISE A JOUR DE LA BASE DE DONNEES).
	    @param			Aucun.
	    @return oldest	Date de la dernière vue enregistrée, de type 'LocalDate'.
	*/
	public LocalDate getDerniereVue() {
		LocalDate oldest = LocalDate.of(1994, 10, 16) ;
		int k = 0 ;
		for (k=1 ; k<=effectif ; k++) {
			int numCherche = rechercherNumero(k) ;
			Engin e = rechercherEngin(numCherche, Parc.dossierParc) ;
			if (e != null) {
				LocalDate currentDate = e.getDerniereVue() ;
				if (currentDate.isAfter(oldest)) {
					oldest = currentDate ;
				}
			}
		}
		return oldest ;
	}
	
	
	/**
		COMPARE LA DATE DE MISE EN SERVICE DE DEUX SERIES.
	    @param s			Serie dont la date de mise en service est à comparer avec celle de 'this', de type 'Serie'.
	    @return	resultat	-1 si 'this' est plus ancienne que 's'.
							1 si 'this' est plus récente ou du même âge que 's'.
	*/
	public int compareTo(Serie s) {
		int resultat ;
		if (this.getJourMESMin().isBefore(s.getJourMESMin())) {
			resultat = -1 ;
		}
		else {
			resultat = 1 ;
		}
	    return resultat ;
	}
	
	
	/**
		CONVERTIT UNE DATE DU FORMAT 'STRING' VERS LE FORMAT 'LOCALDATE'.
	    @param strDate		Date à convertir de type 'String'.
	    @return	resultat	Date équivalente de type 'LocalDate'.
	*/
	public static LocalDate stringToDate(String strDate) {
		LocalDate d = LocalDate.now() ;
		if (strDate.length() == 10) {
			int annee = stringToInteger(strDate.substring(6)) ;
			int mois = stringToInteger(strDate.substring(3, 5)) ;
			int jour = stringToInteger(strDate.substring(0, 2)) ;
			d = LocalDate.of(annee, mois, jour) ;
		}
		return d ;
	}
	
	
	/**
		TESTE SI UNE CHAINE DE CARACTERE CONTIENT UNIQUEMENT DES CHIFFRES.
	    @param chaine	Chaine de caractère à tester de type 'String'.
	    @return			'true' si la chaine peut être convertir en nombre.
	    				'false' sinon.
	*/
	public static boolean isNumeric (String chaine) {
		try {
			Integer.parseInt(chaine) ;
		}
		catch(NumberFormatException ex) {
			return false ;
		}
		return true ;
	}
	
	
	/**
		CONVERTIT UNE CHAINE DE CARACTERES EN ENTIER SI POSSIBLE.
	    @param str			Chaine de caractère à convertir de type 'String'.
	    @return	resultat	Nombre entier équivalent à 'str' si la convertion est possible.
	    					'0' sinon.
	*/
	public static int stringToInteger(String str) {
		int resultat = 0 ;
		if (isNumeric(str)) {
			resultat = Integer.parseInt(str) ;
		}
		return resultat ;
	}
	
	
	/**
		CONVERTIT UNE CHAINE DE CARACTERES EN BOOLEEN SI POSSIBLE.
	    @param str			Chaine de caractère à convertir de type 'String'.
	    @return	resultat	Booléen équivalent à 'str' si la convertion est possible.
	    					'false' sinon.
	*/
	public static boolean stringToBoolean(String str) {
		boolean resultat = false ;
		if (stringToInteger(str) != 0) {
			resultat = true ;
		}
		return resultat ;
	}
}