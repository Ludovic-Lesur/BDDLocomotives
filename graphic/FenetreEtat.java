/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 26/08/2016
 */

package graphic;

import data.*;
import typedef.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

public class FenetreEtat extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques
	private JPanel panel;
	private GridBagConstraints gbc;
	private Interface i;

	// Elements graphiques et attributs
	private JFrame fenetre;
	private Etat etatCourant;
	private LocalDate dateMESCourante;
	private LocalDate dateRADCourante;
	private Association preservationCourante;
	private Engin engin;

	private JLabel etat;
	private JRadioButton enAttente;
	private JRadioButton enService;
	private JRadioButton radie;
	private JRadioButton preserve;
	private ButtonGroup choixEtat;

	private JLabel dateMES;
	private JComboBox<String> choixJourMES;
	private int numJourMES = 1;
	private JComboBox<String> choixMoisMES;
	private int numMoisMES = 1;
	private JComboBox<String> choixAnneeMES;
	private int numAnneeMES = 1;

	private JLabel dateRAD;
	private JComboBox<String> choixJourRAD;
	private int numJourRAD = 1;
	private JComboBox<String> choixMoisRAD;
	private int numMoisRAD = 1;
	private JComboBox<String> choixAnneeRAD;
	private int numAnneeRAD = 1;

	private JLabel preservation;
	private JComboBox<String> choixPreservation;

	private JLabel renumerotation;
	private JTextField champRenumerotation;

	private JLabel espace;

	private JButton ok;
	private JButton annuler;
	private boolean remarque;

	/**
	 * CONSTRUCTEUR DE LA CLASSE FENETREETAT.
	 * 
	 * @param pI
	 *            Interface graphique mère, de type 'Interface'.
	 * @param pEngin
	 *            Engin moteur dont on doit modifier l'état, de type 'Engin'.
	 * @param pRemarque
	 *            Booléen indiquant le type de vue actuellement sélectionné.
	 *            'true' = affichage des remarques. 'false' = affichage des
	 *            vues.
	 * @return Aucun.
	 */
	public FenetreEtat(Interface pI, Engin pEngin, boolean pRemarque) {

		i = pI;
		engin = pEngin;
		remarque = pRemarque;

		// Création de l'interface
		fenetre = new JFrame();
		fenetre.setTitle("Modifier état");
		fenetre.setSize(400, 420);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);

		// Panel
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.gridx = 2;
		gbc.gridy = 9;
		ok = new JButton();
		ok.setText("Enregistrer");
		ok.setFont(Interface.police);
		ok.setForeground(new Color(100, 200, 0));
		ok.addActionListener(this);
		panel.add(ok, gbc);

		gbc.insets.bottom = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		etat = new JLabel("Etat");
		etat.setFont(Interface.police);
		etat.setForeground(Color.yellow);
		panel.add(etat, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 0;
		enAttente = new JRadioButton("En attente de mise en service");
		enAttente.setFont(Interface.police);
		enAttente.setOpaque(false);
		enAttente.setForeground(Color.white);
		enAttente.addActionListener(this);
		panel.add(enAttente, gbc);

		gbc.insets.top = 0;
		gbc.gridx = 1;
		gbc.gridy = 1;
		enService = new JRadioButton("En service");
		enService.setFont(Interface.police);
		enService.setOpaque(false);
		enService.setForeground(Color.white);
		enService.addActionListener(this);
		panel.add(enService, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		radie = new JRadioButton("Radié");
		radie.setFont(Interface.police);
		radie.setOpaque(false);
		radie.setForeground(Color.white);
		radie.addActionListener(this);
		panel.add(radie, gbc);

		gbc.insets.bottom = 10;
		gbc.gridx = 1;
		gbc.gridy = 3;
		preserve = new JRadioButton("Préservé");
		preserve.setFont(Interface.police);
		preserve.setOpaque(false);
		preserve.setForeground(Color.white);
		preserve.addActionListener(this);
		panel.add(preserve, gbc);

		choixEtat = new ButtonGroup();
		choixEtat.add(enAttente);
		choixEtat.add(enService);
		choixEtat.add(radie);
		choixEtat.add(preserve);

		gbc.insets.top = 10;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		dateMES = new JLabel("Mise en service");
		dateMES.setFont(Interface.police);
		dateMES.setForeground(Color.yellow);
		panel.add(dateMES, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		choixJourMES = new JComboBox<String>(Jour.getNumJours());
		choixJourMES.setFont(Interface.police);
		choixJourMES.addItemListener(new ItemState());
		choixJourMES.setSelectedIndex(LocalDate.now().getDayOfMonth() - 1);
		choixJourMES.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixJourMES, gbc);
		numJourMES = LocalDate.now().getDayOfMonth();

		gbc.gridx = 2;
		gbc.gridy = 4;
		choixMoisMES = new JComboBox<String>(Mois.getFrenchNames());
		choixMoisMES.setFont(Interface.police);
		choixMoisMES.addItemListener(new ItemState());
		choixMoisMES.setSelectedIndex(LocalDate.now().getMonth().getValue() - 1);
		choixMoisMES.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixMoisMES, gbc);
		numMoisMES = LocalDate.now().getMonthValue();

		gbc.gridx = 3;
		gbc.gridy = 4;
		choixAnneeMES = new JComboBox<String>(Annee.getAnneesRemarque());
		choixAnneeMES.setFont(Interface.police);
		choixAnneeMES.addItemListener(new ItemState());
		choixAnneeMES.setSelectedIndex(choixAnneeMES.getItemCount() - 1);
		choixAnneeMES.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixAnneeMES, gbc);
		numAnneeMES = LocalDate.now().getYear();
		selectDateMES(engin);

		gbc.gridx = 0;
		gbc.gridy = 5;
		dateRAD = new JLabel("Radiation");
		dateRAD.setFont(Interface.police);
		dateRAD.setForeground(Color.yellow);
		panel.add(dateRAD, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		choixJourRAD = new JComboBox<String>(Jour.getNumJours());
		choixJourRAD.setFont(Interface.police);
		choixJourRAD.addItemListener(new ItemState());
		choixJourRAD.setSelectedIndex(LocalDate.now().getDayOfMonth() - 1);
		choixJourRAD.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixJourRAD, gbc);
		numJourRAD = LocalDate.now().getDayOfMonth();

		gbc.gridx = 2;
		gbc.gridy = 5;
		choixMoisRAD = new JComboBox<String>(Mois.getFrenchNames());
		choixMoisRAD.setFont(Interface.police);
		choixMoisRAD.addItemListener(new ItemState());
		choixMoisRAD.setSelectedIndex(LocalDate.now().getMonth().getValue() - 1);
		choixMoisRAD.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixMoisRAD, gbc);
		numMoisRAD = LocalDate.now().getMonthValue();

		gbc.gridx = 3;
		gbc.gridy = 5;
		choixAnneeRAD = new JComboBox<String>(Annee.getAnneesRemarque());
		choixAnneeRAD.setFont(Interface.police);
		choixAnneeRAD.addItemListener(new ItemState());
		choixAnneeRAD.setSelectedIndex(choixAnneeRAD.getItemCount() - 1);
		choixAnneeRAD.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixAnneeRAD, gbc);
		numAnneeRAD = LocalDate.now().getYear();
		selectDateRAD(engin);

		gbc.gridx = 0;
		gbc.gridy = 6;
		preservation = new JLabel("Préservation");
		preservation.setFont(Interface.police);
		preservation.setForeground(Color.yellow);
		panel.add(preservation, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 6;
		choixPreservation = new JComboBox<String>(Association.getNames());
		choixPreservation.setFont(Interface.police);
		choixPreservation.addItemListener(new ItemState());
		panel.add(choixPreservation, gbc);
		selectPreservation(engin);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;
		renumerotation = new JLabel("Renumérotation");
		renumerotation.setFont(Interface.police);
		renumerotation.setForeground(Color.yellow);
		panel.add(renumerotation, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 7;
		champRenumerotation = new JTextField(10);
		champRenumerotation.setFont(Interface.police);
		panel.add(champRenumerotation, gbc);
		selectRenumerotation(engin);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 8;
		espace = new JLabel("espace");
		espace.setFont(Interface.police);
		espace.setForeground(Color.gray);
		panel.add(espace, gbc);

		gbc.gridx = 3;
		gbc.gridy = 9;
		annuler = new JButton("Annuler");
		annuler.setFont(Interface.police);
		annuler.setForeground(Color.red);
		annuler.addActionListener(this);
		panel.add(annuler, gbc);

		selectEtat(engin);

		// Affichage de la fenêtre
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}

	/**
	 * SELECTIONNE L'ITEM CORRECT EN FONCTION DE L'ETAT ACTUEL.
	 * 
	 * @param e
	 *            Engin moteur dont on doit modifier l'état, de type 'Engin'.
	 * @return Aucun.
	 */
	private void selectEtat(Engin e) {
		switch (e.getEtat()) {
		case AMES:
			enAttente.setSelected(true);
			etatCourant = Etat.AMES;
			break;
		case ES:
			enService.setSelected(true);
			etatCourant = Etat.ES;
			checkDateMES();
			break;
		case R:
			radie.setSelected(true);
			etatCourant = Etat.R;
			checkDateMES();
			checkDateRAD();
			break;
		case PR:
			preserve.setSelected(true);
			etatCourant = Etat.PR;
			checkDateMES();
			checkDateRAD();
			break;
		case Vide:
			enAttente.setSelected(true);
			etatCourant = Etat.AMES;
			disableMES();
			disableRAD();
			disablePR();
			break;
		}
	}

	/**
	 * SELECTIONNE LA DATE DE MISE EN SERVICE ACTUELLE DE L'ENGIN.
	 * 
	 * @param e
	 *            Engin moteur dont on doit modifier l'état, de type 'Engin'.
	 * @return Aucun.
	 */
	private void selectDateMES(Engin e) {
		if (e.getEtat() != Etat.AMES) {
			enableMES();
			choixJourMES.setSelectedIndex(e.getDateMES().getDayOfMonth() - 1);
			choixMoisMES.setSelectedIndex(e.getDateMES().getMonth().getValue() - 1);
			choixAnneeMES.setSelectedIndex(e.getDateMES().getYear() - Annee.oldestRemark);
			dateMESCourante = e.getDateMES();
		} else {
			disableMES();
			choixJourMES.setSelectedIndex(LocalDate.now().getDayOfMonth() - 1);
			choixMoisMES.setSelectedIndex(LocalDate.now().getMonth().getValue() - 1);
			choixAnneeMES.setSelectedIndex(LocalDate.now().getYear() - Annee.oldestRemark);
			dateMESCourante = null;
		}
	}

	/**
	 * SELECTIONNE LA DATE DE RADIATION ACTUELLE DE L'ENGIN.
	 * 
	 * @param e
	 *            Engin moteur dont on doit modifier l'état, de type 'Engin'.
	 * @return Aucun.
	 */
	private void selectDateRAD(Engin e) {
		if ((e.getEtat() == Etat.R) || (e.getEtat() == Etat.PR)) {
			enableRAD();
			choixJourRAD.setSelectedIndex(e.getDateRAD().getDayOfMonth() - 1);
			choixMoisRAD.setSelectedIndex(e.getDateRAD().getMonth().getValue() - 1);
			choixAnneeRAD.setSelectedIndex(e.getDateRAD().getYear() - Annee.oldestRemark);
			dateRADCourante = e.getDateRAD();
		} else {
			disableRAD();
			choixJourRAD.setSelectedIndex(LocalDate.now().getDayOfMonth() - 1);
			choixMoisRAD.setSelectedIndex(LocalDate.now().getMonth().getValue() - 1);
			choixAnneeRAD.setSelectedIndex(LocalDate.now().getYear() - Annee.oldestRemark);
			dateRADCourante = null;
		}
	}

	/**
	 * SELECTIONNE LA PRESERVATION DE L'ENGIN.
	 * 
	 * @param e
	 *            Engin moteur dont on doit modifier l'état, de type 'Engin'.
	 * @return Aucun.
	 */
	private void selectPreservation(Engin e) {
		if (e.getEtat() == Etat.PR) {
			enablePR();
			int i = 0;
			for (i = 0; i < choixPreservation.getItemCount(); i++) {
				if (choixPreservation.getItemAt(i).compareTo(e.getPreservation(false)) == 0) {
					choixPreservation.setSelectedIndex(i);
					preservationCourante = e.getAssociation();
					break;
				}
			}
		} else {
			disablePR();
		}
	}

	/**
	 * SELECTIONNE LA RENUMEROTATION ACTUELLE DE L'ENGIN.
	 * 
	 * @param e
	 *            Engin moteur dont on doit modifier l'état, de type 'Engin'.
	 * @return Aucun.
	 */
	private void selectRenumerotation(Engin e) {
		String renum = e.getRenumerotation();
		if (renum.compareTo("") != 0) {
			champRenumerotation.setText(renum);
		}
	}

	/**
	 * DEFINIT LES ACTIONS DES LISTES DEROULANTES.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	class ItemState implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			// Mise en service
			if (e.getSource() == choixJourMES) {
				if (choixJourMES.getSelectedIndex() != -1) {
					numJourMES = choixJourMES.getSelectedIndex() + 1;
					checkDateMES();
				}
			}
			if (e.getSource() == choixMoisMES) {
				if (choixMoisMES.getSelectedIndex() != -1) {
					numMoisMES = choixMoisMES.getSelectedIndex() + 1;
					checkDateMES();
				}
			}
			if (e.getSource() == choixAnneeMES) {
				if (choixAnneeMES.getSelectedIndex() != -1) {
					numAnneeMES = Serie.stringToInteger(choixAnneeMES.getSelectedItem().toString());
					checkDateMES();
				}
			}
			// Radiation
			if (e.getSource() == choixJourRAD) {
				if (choixJourRAD.getSelectedIndex() != -1) {
					numJourRAD = choixJourRAD.getSelectedIndex() + 1;
					checkDateRAD();
				}
			}
			if (e.getSource() == choixMoisRAD) {
				if (choixMoisRAD.getSelectedIndex() != -1) {
					numMoisRAD = choixMoisRAD.getSelectedIndex() + 1;
					checkDateRAD();
				}
			}
			if (e.getSource() == choixAnneeRAD) {
				if (choixAnneeRAD.getSelectedIndex() != -1) {
					numAnneeRAD = Serie.stringToInteger(choixAnneeRAD.getSelectedItem().toString());
					checkDateRAD();
				}
			}
			// Préservation
			if (e.getSource() == choixPreservation) {
				preservationCourante = Association.values()[choixPreservation.getSelectedIndex() + 1]; // +1
																										// car
																										// "INC"
																										// n'est
																										// pas
																										// dans
																										// la
																										// liste
			}
		}
	}

	/**
	 * VERIFIE SI LA DATE DE MISE EN SERVICE SELECTIONNEE EXISTE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void checkDateMES() {
		try {
			dateMESCourante = LocalDate.of(numAnneeMES, numMoisMES, numJourMES);
			ok.setEnabled(true);
		} catch (DateTimeException timeEx) {
			ok.setEnabled(false);
		}
	}

	/**
	 * VERIFIE SI LA DATE DE RADIATION SELECTIONNEE EXISTE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void checkDateRAD() {
		try {
			dateRADCourante = LocalDate.of(numAnneeRAD, numMoisRAD, numJourRAD);
			ok.setEnabled(true);
		} catch (DateTimeException timeEx) {
			ok.setEnabled(false);
		}
	}

	/**
	 * ACTIVE LA SAISIE DE LA DATE DE MISE EN SERVICE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void enableMES() {
		choixJourMES.setEnabled(true);
		choixMoisMES.setEnabled(true);
		choixAnneeMES.setEnabled(true);
	}

	/**
	 * DESACTIVE LA SAISIE DE LA DATE DE MISE EN SERVICE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void disableMES() {
		choixJourMES.setEnabled(false);
		choixMoisMES.setEnabled(false);
		choixAnneeMES.setEnabled(false);
		dateMESCourante = null;
	}

	/**
	 * ACTIVE LA SAISIE DE LA DATE DE RADIATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void enableRAD() {
		choixJourRAD.setEnabled(true);
		choixMoisRAD.setEnabled(true);
		choixAnneeRAD.setEnabled(true);
	}

	/**
	 * DESACTIVE LA SAISIE DE LA DATE DE RADIATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void disableRAD() {
		choixJourRAD.setEnabled(false);
		choixMoisRAD.setEnabled(false);
		choixAnneeRAD.setEnabled(false);
		dateRADCourante = null;
	}

	/**
	 * ACTIVE LA SAISIE DE PRESERVATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void enablePR() {
		choixPreservation.setEditable(true);
		choixPreservation.setEnabled(true);
		preservationCourante = Association.values()[choixPreservation.getSelectedIndex() + 1]; // +1
																								// car
																								// "INC"
																								// n'est
																								// pas
																								// dans
																								// la
																								// liste
	}

	/**
	 * DESACTIVE LA SAISIE DE PRESERVATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void disablePR() {
		choixPreservation.setEditable(false);
		choixPreservation.setEnabled(false);
		preservation = null;
	}

	/**
	 * DEFINIT LES ACTIONS DES BOUTONS.
	 * 
	 * @param e
	 *            Evènement déclenché par l'appui sur un bouton.
	 * @return Aucun.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enAttente) {
			etatCourant = Etat.AMES;
			preservation = null;
			dateMESCourante = null;
			dateRADCourante = null;
			ok.setEnabled(true);
			disableMES();
			disableRAD();
			disablePR();
		}
		if (e.getSource() == enService) {
			etatCourant = Etat.ES;
			preservation = null;
			checkDateMES();
			dateRADCourante = null;
			enableMES();
			disableRAD();
			disablePR();
		}
		if (e.getSource() == radie) {
			etatCourant = Etat.R;
			preservation = null;
			checkDateMES();
			checkDateRAD();
			enableMES();
			enableRAD();
			disablePR();
		}
		if (e.getSource() == preserve) {
			etatCourant = Etat.PR;
			checkDateMES();
			checkDateRAD();
			enableMES();
			enableRAD();
			enablePR();
		}
		if (e.getSource() == ok) {
			engin.getSerie().modifyXML(engin.getNumero(), etatCourant);
			engin.getSerie().modifyXML(engin.getNumero(), champRenumerotation.getText());
			engin.getSerie().modifyXML(engin.getNumero(), dateMESCourante, true);
			engin.getSerie().modifyXML(engin.getNumero(), dateRADCourante, false);
			engin.getSerie().modifyXML(engin.getNumero(), preservationCourante);

			Engin enginUpdated = engin.getSerie().rechercherEngin(engin.getNumero(), Parc.dossierParc);
			i.afficherResultat(enginUpdated, remarque);

			fenetre.setVisible(false);
			fenetre.dispose();
		}
		if (e.getSource() == annuler) {
			fenetre.setVisible(false);
			fenetre.dispose();
		}
	}
}