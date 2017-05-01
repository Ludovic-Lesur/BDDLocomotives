/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 26/08/2016
 */

package graphic;

import typedef.*;
import data.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

public class FenetreVue extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private JFrame fenetre;
	private JPanel panel;
	private GridBagConstraints gbc;
	private Interface i;
	private Engin engin;
	private Vue oldVue;
	private Vue newVue;
	private Lieu lieuCourant;
	private Gare departCourant;
	private Gare arriveeCourante;
	private Train trainCourant;
	private boolean modif;

	// Elements graphiques et attributs.
	private JLabel date;
	private JComboBox<String> choixJour;
	private int numJour = 1;
	private JComboBox<String> choixMois;
	private int numMois = 1;
	private JComboBox<String> choixAnnee;
	private int numAnnee = 1;
	private LocalDate dateCourante;

	private JLabel contexte;
	private JRadioButton lieu;
	private JComboBox<String> choixLieu;
	private JRadioButton voyage;
	private JLabel depart;
	private JComboBox<String> choixDepart;
	private JLabel arrivee;
	private JComboBox<String> choixArrivee;
	private ButtonGroup choixContexte;

	private JLabel train;
	private JComboBox<String> choixTrain;

	private JLabel divers;
	private JCheckBox photo;
	private JCheckBox video;
	private JCheckBox cabine;

	private JLabel espace;

	private JButton ok;
	private JButton annuler;

	/**
	 * CONSTRUCTEUR DE LA CLASSE FENETREVUE.
	 * 
	 * @param pI
	 *            Interface graphique mère, de type 'Interface'.
	 * @param pModif
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @param pVue
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param pEngin
	 *            Engin moteur dont on doit modifier la vue, de type 'Engin'.
	 * @return Aucun.
	 */
	public FenetreVue(Interface pI, boolean pModif, Vue pVue, Engin pEngin) {

		i = pI;
		engin = pEngin;
		modif = pModif;
		oldVue = new Vue();
		newVue = new Vue();
		lieuCourant = Lieu.Vide;
		departCourant = Gare.Vide;
		arriveeCourante = Gare.Vide;
		trainCourant = Train.Vide;

		if (modif == true) {
			oldVue = new Vue(pVue);
			newVue = new Vue(pVue);
		}

		// Création de l'interface
		fenetre = new JFrame();
		if (modif == false) {
			fenetre.setTitle("Ajouter vue");
		} else {
			fenetre.setTitle("Modifier vue");
		}
		fenetre.setSize(500, 400);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);

		// Panel
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridheight = 1;

		gbc.insets.top = 0;
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 9;
		ok = new JButton();
		if (modif == false) {
			ok.setText("Ajouter");
		} else {
			ok.setText("Enregistrer");
		}
		ok.setFont(Interface.police);
		ok.setForeground(new Color(100, 200, 0));
		ok.addActionListener(this);
		panel.add(ok, gbc);

		gbc.gridx = 5;
		gbc.gridy = 9;
		annuler = new JButton("Annuler");
		annuler.setFont(Interface.police);
		annuler.setForeground(Color.red);
		annuler.addActionListener(this);
		panel.add(annuler, gbc);

		gbc.gridwidth = 1;
		gbc.insets.top = 10;
		gbc.insets.bottom = 10;
		gbc.gridx = 0;
		gbc.gridy = 0;
		date = new JLabel("Date");
		date.setFont(Interface.police);
		date.setForeground(Color.yellow);
		panel.add(date, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 0;
		choixJour = new JComboBox<String>(Jour.getNumJours());
		choixJour.setFont(Interface.police);
		choixJour.addItemListener(new ItemState());
		choixJour.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixJour, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		choixMois = new JComboBox<String>(Mois.getFrenchNames());
		choixMois.setFont(Interface.police);
		choixMois.addItemListener(new ItemState());
		choixMois.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixMois, gbc);

		gbc.gridx = 5;
		gbc.gridy = 0;
		choixAnnee = new JComboBox<String>(Annee.getAnneesVue());
		choixAnnee.setFont(Interface.police);
		choixAnnee.addItemListener(new ItemState());
		choixAnnee.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixAnnee, gbc);
		selectDate(modif, oldVue);

		gbc.gridx = 0;
		gbc.gridy = 1;
		contexte = new JLabel("Contexte");
		contexte.setFont(Interface.police);
		contexte.setForeground(Color.yellow);
		panel.add(contexte, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		lieu = new JRadioButton("Lieu");
		lieu.setFont(Interface.police);
		lieu.setOpaque(false);
		lieu.setForeground(Color.white);
		lieu.setSelected(true);
		lieu.addActionListener(this);
		panel.add(lieu, gbc);

		gbc.gridwidth = 5;
		gbc.gridx = 2;
		gbc.gridy = 1;
		choixLieu = new JComboBox<String>(Lieu.getNames());
		choixLieu.setFont(Interface.police);
		choixLieu.addItemListener(new ItemState());
		choixLieu.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixLieu, gbc);

		gbc.insets.bottom = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		voyage = new JRadioButton("Voyage");
		voyage.setFont(Interface.police);
		voyage.setOpaque(false);
		voyage.setForeground(Color.white);
		voyage.setSelected(false);
		voyage.addActionListener(this);
		panel.add(voyage, gbc);

		// gbc.fill = GridBagConstraints.CENTER ;
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 2;
		depart = new JLabel("Départ");
		depart.setFont(Interface.police);
		depart.setForeground(Color.white);
		panel.add(depart, gbc);

		gbc.gridx = 4;
		gbc.gridy = 2;
		arrivee = new JLabel("Arrivée");
		arrivee.setFont(Interface.police);
		arrivee.setForeground(Color.white);
		panel.add(arrivee, gbc);

		gbc.insets.top = 0;
		gbc.insets.bottom = 10;
		gbc.gridx = 2;
		gbc.gridy = 3;
		choixDepart = new JComboBox<String>(Gare.getNames());
		choixDepart.setFont(Interface.police);
		choixDepart.addItemListener(new ItemState());
		choixDepart.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixDepart, gbc);

		gbc.gridx = 4;
		gbc.gridy = 3;
		choixArrivee = new JComboBox<String>(Gare.getNames());
		choixArrivee.setFont(Interface.police);
		choixArrivee.addItemListener(new ItemState());
		choixArrivee.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixArrivee, gbc);

		choixContexte = new ButtonGroup();
		choixContexte.add(lieu);
		choixContexte.add(voyage);
		enableLieu();
		disableVoyage();
		selectLieuVoyage(modif, oldVue);

		gbc.insets.top = 10;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		train = new JLabel("Train");
		train.setFont(Interface.police);
		train.setForeground(Color.yellow);
		panel.add(train, gbc);

		gbc.gridwidth = 5;
		gbc.gridx = 2;
		gbc.gridy = 4;
		choixTrain = new JComboBox<String>(Train.getNames());
		choixTrain.setFont(Interface.police);
		choixTrain.addItemListener(new ItemState());
		choixTrain.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		panel.add(choixTrain, gbc);
		selectTrain(modif, oldVue);

		gbc.insets.bottom = 0;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 5;
		divers = new JLabel("Divers");
		divers.setFont(Interface.police);
		divers.setForeground(Color.yellow);
		panel.add(divers, gbc);

		gbc.gridx = 2;
		gbc.gridy = 5;
		photo = new JCheckBox("Photo");
		photo.setBackground(Color.gray);
		photo.setFont(Interface.police);
		photo.setForeground(Color.white);
		panel.add(photo, gbc);

		gbc.insets.top = 0;
		gbc.gridx = 2;
		gbc.gridy = 6;
		video = new JCheckBox("Vidéo");
		video.setBackground(Color.gray);
		video.setFont(Interface.police);
		video.setForeground(Color.white);
		panel.add(video, gbc);

		gbc.insets.bottom = 10;
		gbc.gridx = 2;
		gbc.gridy = 7;
		cabine = new JCheckBox("Cabine");
		cabine.setBackground(Color.gray);
		cabine.setFont(Interface.police);
		cabine.setForeground(Color.white);
		panel.add(cabine, gbc);
		selectDivers(modif, oldVue);

		gbc.gridx = 0;
		gbc.gridy = 8;
		espace = new JLabel("espace");
		espace.setFont(Interface.police);
		espace.setForeground(Color.gray);
		panel.add(espace, gbc);

		// Affichage de la fenêtre
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}

	/**
	 * SELECTIONNE L'ITEM CORRECT EN FONCTION DE LA DATE ACTUELLE DE LA VUE.
	 * 
	 * @param v
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param mode
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @return Aucun.
	 */
	private void selectDate(boolean modif, Vue v) {
		if (modif == false) {
			// Ajout -> affichage de la date d'aujourd'hui.
			dateCourante = LocalDate.now();
		} else {
			// Modification -> affichage de la date actuelle de la vue.
			dateCourante = v.getDate();
		}
		numJour = dateCourante.getDayOfMonth();
		numMois = dateCourante.getMonthValue();
		numAnnee = dateCourante.getYear();
		choixJour.setSelectedIndex(dateCourante.getDayOfMonth() - 1);
		choixMois.setSelectedIndex(dateCourante.getMonth().getValue() - 1);
		choixAnnee.setSelectedIndex(dateCourante.getYear() - Annee.oldestView);
	}

	/**
	 * SELECTIONNE LES ITEMS CORRECTS EN FONCTION DU CONTEXTE ACTUEL DE LA VUE.
	 * 
	 * @param v
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param mode
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @return Aucun.
	 */
	private void selectLieuVoyage(boolean modif, Vue v) {
		if (modif == true) {
			// Voyage
			if (v.getNomLieu().compareTo(Lieu.Vide.getName()) == 0) {
				voyage.setSelected(true);
				lieu.setSelected(false);
				enableVoyage();
				disableLieu();
				selectDepartArrivee(v);
			}
			// Lieu
			else {
				lieu.setSelected(true);
				voyage.setSelected(false);
				enableLieu();
				disableVoyage();
				selectLieu(v);
			}
		} else {
			lieuCourant = Lieu.values()[choixLieu.getSelectedIndex() + 1];
			departCourant = Gare.Vide;
			arriveeCourante = Gare.Vide;
		}
	}

	/**
	 * SELECTIONNE L'ITEM CORRECT EN FONCTION DU LIEU ACTUEL DE LA VUE.
	 * 
	 * @param v
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param mode
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @return Aucun.
	 */
	private void selectLieu(Vue v) {
		int i = 0;
		for (i = 0; i < choixLieu.getItemCount(); i++) {
			if (choixLieu.getItemAt(i).compareTo(v.getNomLieu()) == 0) {
				choixLieu.setSelectedIndex(i);
				lieuCourant = v.getLieu();
				break;
			}
		}
	}

	/**
	 * SELECTIONNE LES ITEMS CORRECTS EN FONCTION DU VOYAGE ACTUEL DE LA VUE.
	 * 
	 * @param v
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param mode
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @return Aucun.
	 */
	private void selectDepartArrivee(Vue v) {
		int i = 0;
		// Départ
		for (i = 0; i < choixDepart.getItemCount(); i++) {
			if (choixDepart.getItemAt(i).compareTo(v.getNomVoyageDepart()) == 0) {
				choixDepart.setSelectedIndex(i);
				departCourant = v.getVoyageDepart();
				break;
			}
		}
		// Arrivée
		for (i = 0; i < choixArrivee.getItemCount(); i++) {
			if (choixArrivee.getItemAt(i).compareTo(v.getNomVoyageArrivee()) == 0) {
				choixArrivee.setSelectedIndex(i);
				arriveeCourante = v.getVoyageArrivee();
				break;
			}
		}
	}

	/**
	 * SELECTIONNE L' ITEM CORRECT EN FONCTION DU TRAIN ACTUEL DE LA VUE.
	 * 
	 * @param v
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param mode
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @return Aucun.
	 */
	private void selectTrain(boolean mode, Vue v) {
		if (mode == true) {
			int i = 0;
			for (i = 0; i < choixTrain.getItemCount(); i++) {
				if (choixTrain.getItemAt(i).compareTo(v.getNomTrain()) == 0) {
					choixTrain.setSelectedIndex(i);
					trainCourant = v.getTrain();
					break;
				}
			}
		} else {
			trainCourant = Train.values()[choixTrain.getSelectedIndex()];
		}
	}

	/**
	 * SELECTIONNE LES ITEMS CORRECTS EN FONCTION DES INDICATIONS DIVERSES
	 * ACTUELLES DE LA VUE.
	 * 
	 * @param v
	 *            Objet 'Vue' à modifier si modif = 'true'. 'null' si modif =
	 *            'false'.
	 * @param mode
	 *            Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou
	 *            en mode modification. 'true' = modification d'une vue
	 *            existante. 'false' = ajout d'une nouvelle vue.
	 * @return Aucun.
	 */
	private void selectDivers(boolean mode, Vue v) {
		if (mode == true) {
			if (v.getPhoto() == true) {
				photo.setSelected(true);
			} else {
				photo.setSelected(false);
			}
			if (v.getVideo() == true) {
				video.setSelected(true);
			} else {
				video.setSelected(false);
			}
			if (v.getCabine() == true) {
				cabine.setSelected(true);
			} else {
				cabine.setSelected(false);
			}
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
			if (e.getSource() == choixJour) {
				if (choixJour.getSelectedIndex() != -1) {
					numJour = choixJour.getSelectedIndex() + 1;
					checkDate();
				}
			}
			if (e.getSource() == choixMois) {
				if (choixMois.getSelectedIndex() != -1) {
					numMois = choixMois.getSelectedIndex() + 1;
					checkDate();
				}
			}
			if (e.getSource() == choixAnnee) {
				if (choixAnnee.getSelectedIndex() != -1) {
					numAnnee = Serie.stringToInteger(choixAnnee.getSelectedItem().toString());
					checkDate();
				}
			}
			if (e.getSource() == choixLieu) {
				lieuCourant = Lieu.values()[choixLieu.getSelectedIndex() + 1];
			}
			if (e.getSource() == choixDepart) {
				departCourant = Gare.values()[choixDepart.getSelectedIndex() + 1];
			}
			if (e.getSource() == choixArrivee) {
				arriveeCourante = Gare.values()[choixArrivee.getSelectedIndex() + 1];
			}
			if (e.getSource() == choixTrain) {
				trainCourant = Train.values()[choixTrain.getSelectedIndex()];
			}
		}
	}

	/**
	 * VERIFIE SI LA DATE SELECTIONNEE EXISTE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void checkDate() {
		try {
			dateCourante = LocalDate.of(numAnnee, numMois, numJour);
			ok.setEnabled(true);
		} catch (DateTimeException timeEx) {
			ok.setEnabled(false);
		}
	}

	/**
	 * AUTORISE LA MODIFICATION DU CHAMP LIEU.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void enableLieu() {
		choixLieu.setEditable(true);
		choixLieu.setEnabled(true);
	}

	/**
	 * DESACTIVE LA MODIFICATION DU CHAMP LIEU.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void disableLieu() {
		choixLieu.setEditable(false);
		choixLieu.setEnabled(false);
	}

	/**
	 * AUTORISE LA MODIFICATION DU CHAMP VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void enableVoyage() {
		choixDepart.setEditable(true);
		choixDepart.setEnabled(true);
		choixArrivee.setEditable(true);
		choixArrivee.setEnabled(true);
	}

	/**
	 * DESACTIVE LA MODIFICATION DU CHAMP VOYAGE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void disableVoyage() {
		choixDepart.setEditable(false);
		choixDepart.setEnabled(false);
		choixArrivee.setEditable(false);
		choixArrivee.setEnabled(false);
	}

	/**
	 * DEFINIT LES ACTIONS DES BOUTONS.
	 * 
	 * @param e
	 *            Evènement déclenché par l'appui sur un bouton.
	 * @return Aucun.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == lieu) {
			enableLieu();
			disableVoyage();
			lieuCourant = Lieu.values()[choixLieu.getSelectedIndex() + 1]; // +1
																			// car
																			// "Vide"
																			// n'est
																			// pas
																			// dans
																			// la
																			// liste
			departCourant = Gare.Vide;
			arriveeCourante = Gare.Vide;
		}
		if (e.getSource() == voyage) {
			enableVoyage();
			disableLieu();
			lieuCourant = Lieu.Vide;
			departCourant = Gare.values()[choixDepart.getSelectedIndex() + 1];
			arriveeCourante = Gare.values()[choixArrivee.getSelectedIndex() + 1];
		}
		if (e.getSource() == ok) {
			newVue.setDate(dateCourante);
			newVue.setLieu(lieuCourant);
			newVue.setVoyage(departCourant, arriveeCourante);
			newVue.setTrain(trainCourant);
			newVue.setPhoto(photo.isSelected());
			newVue.setVideo(video.isSelected());
			newVue.setCabine(cabine.isSelected());
			if (modif == false) {
				engin.getSerie().addXML(engin.getNumero(), newVue);
			} else {
				engin.getSerie().modifyXML(engin.getNumero(), oldVue, newVue);
			}

			Engin enginUpdated = engin.getSerie().rechercherEngin(engin.getNumero(), Parc.dossierParc);
			i.afficherResultat(enginUpdated, false);

			fenetre.setVisible(false);
			fenetre.dispose();
		}
		if (e.getSource() == annuler) {
			fenetre.setVisible(false);
			fenetre.dispose();
		}
	}
}
