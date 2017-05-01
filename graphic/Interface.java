/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 24/05/2016
 */

package graphic;

import data.*;
import typedef.*;
import java.io.*;
import java.time.*;
import javax.swing.*;
import org.jdom2.*;
import java.awt.*;

public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;

	// Apparence commune � tous les �l�ments graphiques.
	public final static Font police = new Font("Averia", 1, 10);
	public final static int COMBOBOX_HEIGHT = 20;

	// El�ments graphiques.
	private JFrame fenetre;
	private JPanel panel;
	private GridBagConstraints gbc;
	private Parc parcEngins;
	public static final String dossierData = "C:/Users/Ludovic/Documents/Eclipse/BDD_Locos/data";

	private RechercheEngin rechercheEngin;
	private Modification modification;
	private Infos infos;
	private Tableau tableauVues;

	/**
	 * CONSTRUCTEUR DE LA CLASSE INTERFACE.
	 * 
	 * @param pParcEngins
	 *            Parc d'engins moteurs sur lequel soit �tre d�mar�e l'interface
	 *            graphique, de type 'Parc'.
	 * @return Aucun.
	 */
	public Interface(Parc pParcEngins) {

		parcEngins = pParcEngins;

		// Cr�ation de l'interface
		fenetre = new JFrame();
		fenetre.setTitle("Base de donn�es Engins Moteurs " + parcEngins.getNom());
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Panel
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);

		// Ajout des �l�ments
		gbc.anchor = GridBagConstraints.WEST;
		new RechercheSerie(this, panel, gbc, parcEngins);
		infos = new Infos(panel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		rechercheEngin = new RechercheEngin(this, panel, gbc, parcEngins);
		tableauVues = new Tableau(this, panel, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		new AnniversairesMES(this, panel, gbc);
		modification = new Modification(this, panel, gbc);

		// Affichage de l'interface
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}

	/**
	 * DEFINIT L'AFFICHAGE GLOBAL POUR UN ENGIN MOTEUR.
	 * 
	 * @param enginTrouve
	 *            Engin moteur dont on doit afficher les informations, de type
	 *            'Engin'.
	 * @param remarque
	 *            Bool�en indiquant le mode d'affichage. 'true' = affichage des
	 *            remarques. 'false' = affichage des vues.
	 * @return Aucun.
	 */
	public void afficherResultat(Engin enginTrouve, boolean remarque) {
		disableModifAll();
		setEnableTrue();
		rechercheEngin.setEnginCourant(enginTrouve);
		infos.update(panel, gbc, enginTrouve);
		tableauVues.update(enginTrouve, remarque);
		modification.update(enginTrouve, remarque);
		fenetre.setContentPane(panel);
	}

	/**
	 * DEFINIT L'AFFICHAGE GLOBAL POUR UNE SERIE D'ENGINS MOTEURS.
	 * 
	 * @param serieTrouvee
	 *            S�rie d'engins moteurs dont on doit afficher les informations,
	 *            de type 'Serie'.
	 * @return Aucun.
	 */
	public void afficherResultat(Serie serieTrouvee) {
		serieTrouvee.update();
		disableModifAll();
		setEnableFalse();
		infos.update(panel, gbc, serieTrouvee);
		tableauVues.update(serieTrouvee);
		modification.update(serieTrouvee);
		fenetre.setContentPane(panel);
	}

	/**
	 * DESACTIVE LE CHOIX ENTRE VUES ET REMARQUES.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void setEnableFalse() {
		rechercheEngin.setEnableFalse();
	}

	/**
	 * ACTIVE LE CHOIX ENTRE VUES ET REMARQUES.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void setEnableTrue() {
		rechercheEngin.setEnableTrue();
	}

	/**
	 * DEFINIT L'AFFICHAGE GLOBAL POUR LE PARC D'ENGINS MOTEURS.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void afficherParc() {
		parcEngins.update();
		disableModifAll();
		infos.update(panel, gbc, parcEngins);
		tableauVues.update(parcEngins);
		modification.update(parcEngins);
		fenetre.setContentPane(panel);
	}

	/**
	 * DEFINIT L'AFFICHAGE GLOBAL POUR LES ANNIVERSAIRES DE MISE EN SERVICE.
	 * 
	 * @param date
	 *            Date de mise en service cherch�e, de type 'LocalDate'.
	 * @return Aucun.
	 */
	public void afficherAnniversaires(LocalDate date) {
		disableModifAll();
		infos.update(panel, gbc, parcEngins.getAnniversaires(date));
		tableauVues.update(parcEngins.getAnniversaires(date));
		modification.update(parcEngins.getAnniversaires(date));
		fenetre.setContentPane(panel);
	}

	/**
	 * TRANSMETS LA VUE SELECTIONNEE DU TABLEAU A LA CLASSE MODIFICATION.
	 * 
	 * @param newVue
	 *            Objet 'Vue' s�lectionn�e dans le tableau principal.
	 * @return Aucun.
	 */
	public void setVue(Vue newVue) {
		modification.setVue(newVue);
	}

	/**
	 * TRANSMETS LA REMARQUE SELECTIONNEE DU TABLEAU A LA CLASSE MODIFICATION.
	 * 
	 * @param newRemarque
	 *            Objet 'Remarque' s�lectionn�e dans le tableau principal.
	 * @return Aucun.
	 */
	public void setRemarque(Remarque newRemarque) {
		modification.setRemarque(newRemarque);
	}

	/**
	 * TRANSMETS L'ENGIN MOTEUR SELECTIONNE DU TABLEAU A LA CLASSE MODIFICATION.
	 * 
	 * @param newEngin
	 *            Objet 'Engin' s�lectionn� dans le tableau principal.
	 * @return Aucun.
	 */
	public void setEngin(Engin newEngin) {
		modification.setEngin(newEngin);
	}

	/**
	 * TRANSMETS LA SERIE SELECTIONNEE DU TABLEAU A LA CLASSE MODIFICATION.
	 * 
	 * @param newRemarque
	 *            Objet 'Serie' s�lectionn�e dans le tableau principal.
	 * @return Aucun.
	 */
	public void setSerie(Serie newSerie) {
		modification.setSerie(newSerie);
	}

	/**
	 * ACTIVE LE MENU DE MODIFICATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void enableModifAll() {
		modification.enableModifAttributs();
		modification.enableModifSuppr();
	}

	/**
	 * DESACTIVE LE MENU DE MODIFICATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void disableModifAll() {
		modification.disableNavigation();
		modification.disableModifAttributs();
		modification.disableModifSuppr();
	}

	/**
	 * PERMET DE SAVOIR SI UNE LIGNE DU TABLEAU EST SELECTIONNEE.
	 * 
	 * @param Aucun.
	 * @return 'true' si une ligne du tableau a �t� s�lectionn�e par un clic
	 *         souris. 'false' sinon.
	 */
	public boolean rowIsSelected() {
		return tableauVues.rowIsSelected();
	}

	/**
	 * RETOURNE LA DATE DE MISE A JOUR DE LA BASE DE DONNEES.
	 * 
	 * @param Aucun.
	 * @return Date de vue la plus r�cente de la base de donn�es de type
	 *         'String'.
	 */
	public String getUpdate() {
		return dateToString(parcEngins.getDerniereVue(), false);
	}

	/**
	 * CONVERTIT UNE DATE EN CHAINE DE CARACTERES.
	 * 
	 * @param date
	 *            Date � convertir de type 'LocalDate'.
	 * @param formatLong
	 *            Bool�en indiquant le format de convertion. 'true' : format
	 *            long avec jour de la semaine et �criture compl�te. 'false' :
	 *            format court xx/xx/xxxx.
	 * @return result Chaine de caract�re correspondant � la date.
	 */
	public static String dateToString(LocalDate date, boolean formatLong) {
		String result = "";
		if (date != null) {
			String jour = Jour.affecter(date.getDayOfWeek().toString()).getFrenchName();
			String numero = Integer.toString(date.getDayOfMonth());
			String mois = Mois.affecter(date.getMonth().toString()).getFrenchName();
			int annee = date.getYear();

			if (date.getDayOfMonth() < 10) {
				numero = "0" + date.getDayOfMonth();
			} else {
				numero = Integer.toString(date.getDayOfMonth());
			}
			if (formatLong == false) {
				if (date.getMonth().getValue() < 10) {
					mois = "0" + date.getMonth().getValue();
				} else {
					mois = Integer.toString(date.getMonth().getValue());
				}
				result = numero + "/" + mois + "/" + annee;
			} else {
				result = jour + " " + numero + " " + mois + " " + annee;
			}
		}
		return result;
	}

	/**
	 * MAIN FUNCTION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public static void main(String[] args) throws IOException, JDOMException {
		// Cr�ation du parc d'engins moteur
		Parc p = new Parc("SNCF");
		// Lancement de l'interface
		Interface i = new Interface(p);
		i.setEnableFalse();
		i.afficherAnniversaires(LocalDate.now());
	}
}
