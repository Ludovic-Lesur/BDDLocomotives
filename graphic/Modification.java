/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 20/08/2016
 */

package graphic;

import data.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Modification extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private Interface i;
	private JPanel panel;
	private GridBagConstraints gbc;

	// Eléments graphiques.
	private JLabel titre1;
	private JLabel espace1;
	private JLabel nombre;
	private JLabel nb;
	private JButton ajouter;
	private JButton modifier;
	private JButton supprimer;
	private JButton modifierEtat;
	private JButton modifierLivree;
	private JButton modifierDepot;
	private JLabel espace2;
	private JLabel titre2;
	private JButton navigation;
	private JLabel espace3;
	private JLabel update;

	private Vue selectedVue;
	private Remarque selectedRemarque;
	private Engin selectedEngin;
	private Serie selectedSerie;
	boolean remarque;
	boolean modeNavigation; // 0 = vers un engin.
							// 1 = vers une série.

	/**
	 * CONSTRUCTEUR DE LA CLASSE MODIFICATION.
	 * 
	 * @param pI
	 *            Interface graphique mère, de type 'Interface'.
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @return Aucun.
	 */
	public Modification(Interface pI, JPanel mainPanel, GridBagConstraints mainGbc) {

		i = pI;

		mainGbc.gridx = 3;
		mainGbc.gridy = 2;

		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.EAST;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		nombre = new JLabel("Vues :");
		nombre.setFont(new Font(Interface.police.getFontName(), 1, 15));
		nombre.setForeground(Color.yellow);
		panel.add(nombre, gbc);

		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.WEST;
		gbc.gridx = 2;
		gbc.gridy = 0;
		nb = new JLabel("");
		nb.setFont(new Font(Interface.police.getFontName(), 1, 15));
		nb.setForeground(Color.white);
		panel.add(nb, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		ajouter = new JButton("Ajouter");
		ajouter.setFont(Interface.police);
		ajouter.addActionListener(this);
		panel.add(ajouter, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		modifier = new JButton("Modifier");
		modifier.setFont(Interface.police);
		modifier.addActionListener(this);
		panel.add(modifier, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		supprimer = new JButton("Supprimer");
		supprimer.setFont(Interface.police);
		supprimer.addActionListener(this);
		panel.add(supprimer, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		espace1 = new JLabel("espace");
		espace1.setFont(new Font(Interface.police.getFontName(), 1, 12));
		espace1.setForeground(Color.gray);
		panel.add(espace1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		titre1 = new JLabel("MODIFICATION  ENGIN");
		titre1.setFont(new Font(Interface.police.getFontName(), 1, 13));
		titre1.setForeground(Color.white);
		panel.add(titre1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		modifierEtat = new JButton("Modifier état");
		modifierEtat.setFont(Interface.police);
		modifierEtat.addActionListener(this);
		panel.add(modifierEtat, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		modifierLivree = new JButton("Modifier livrée");
		modifierLivree.setFont(Interface.police);
		modifierLivree.addActionListener(this);
		panel.add(modifierLivree, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		modifierDepot = new JButton("Modifier dépôt");
		modifierDepot.setFont(Interface.police);
		modifierDepot.addActionListener(this);
		panel.add(modifierDepot, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 3;
		espace2 = new JLabel("espace");
		espace2.setFont(new Font(Interface.police.getFontName(), 1, 12));
		espace2.setForeground(Color.gray);
		panel.add(espace2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		titre2 = new JLabel("NAVIGATION");
		titre2.setFont(new Font(Interface.police.getFontName(), 1, 13));
		titre2.setForeground(Color.white);
		panel.add(titre2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		navigation = new JButton("Afficher");
		navigation.setFont(Interface.police);
		navigation.addActionListener(this);
		panel.add(navigation, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 3;
		espace3 = new JLabel("espace");
		espace3.setFont(new Font(Interface.police.getFontName(), 1, 12));
		espace3.setForeground(Color.gray);
		panel.add(espace3, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 11;
		update = new JLabel("Mise à jour : " + Interface.dateToString(i.getUpdate(), false));
		update.setFont(new Font(Interface.police.getFontName(), 1, Interface.police.getSize()));
		update.setForeground(Color.white);
		panel.add(update, gbc);

		ajouter.setEnabled(false);
		disableModifAttributs();
		disableModifSuppr();
		navigation.setEnabled(false);
		mainPanel.add(panel, mainGbc);

		selectedVue = new Vue();
		selectedRemarque = new Remarque();
		selectedEngin = new Engin();
		selectedSerie = new Serie();
		remarque = false;
	}

	/**
	 * DEFINIT LA VUE COURANTE.
	 * 
	 * @param newVue
	 *            Vue sélectionnée dans le tableau de type 'Vue'.
	 * @return Aucun.
	 */
	public void setVue(Vue newVue) {
		selectedVue = newVue;
	}

	/**
	 * DEFINIT LA REMARQUE COURANTE.
	 * 
	 * @param newRemarque
	 *            Remarque sélectionnée dans le tableau de type 'Remarque'.
	 * @return Aucun.
	 */
	public void setRemarque(Remarque newRemarque) {
		selectedRemarque = newRemarque;
	}

	/**
	 * DEFINIT L'ENGIN COURANT.
	 * 
	 * @param newEngin
	 *            Engin sélectionné dans le tableau de type 'Engin'.
	 * @return Aucun.
	 */
	public void setEngin(Engin newEngin) {
		selectedEngin = newEngin;
		navigation.setEnabled(true);
		navigation.setText("Afficher engin " + selectedEngin.getNomComplet());
		modeNavigation = false;
	}

	/**
	 * DEFINIT LA SERIE COURANTE.
	 * 
	 * @param newSerie
	 *            Série d'engins moteurs sélectionnée dans le tableau de type
	 *            'Serie'.
	 * @return Aucun.
	 */
	public void setSerie(Serie newSerie) {
		selectedSerie = newSerie;
		navigation.setEnabled(true);
		navigation.setText("Afficher série " + selectedSerie.getNomComplet());
		modeNavigation = true;
	}
	
	/**
	 * RECALCULE LA DATE DE MISE A JOUR DE LA BASE DE DONNEES.
	 * 
	 * @param 	Aucun.
	 * @return Aucun.
	 */
	public void updateUpdate(String newDate) {
		update.setText("Mise à jour : " + newDate);
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UN ENGIN MOTEUR.
	 * 
	 * @param e
	 *            Engin moteur actuellement affiché de type 'Engin'.
	 * @param pRemarque
	 *            Booléen indiquant le type de vue. 'true' = affichage des
	 *            remarques de l'engin. 'false' = affichage des vues de l'engin.
	 * @return Aucun.
	 */
	public void update(Engin e, boolean pRemarque) {
		remarque = pRemarque;
		if (remarque == false) {
			nombre.setText("Vues :");
		} else {
			nombre.setText("Remarques :");
		}
		if (e != null) {
			selectedEngin = e;
			selectedSerie = e.getSerie();
			enableModifAttributs();
			ajouter.setEnabled(true);
			navigation.setEnabled(true);
			navigation.setText("Afficher série " + e.getNomSerie());
			modeNavigation = true;
			nb.setForeground(Color.white);
			if (remarque == false) {
				nb.setText(Integer.toString(e.getNbVues()));
			} else {
				nb.setText(Integer.toString(e.getNbRemarques()));
			}
		} else {
			ajouter.setEnabled(false);
			disableModifAttributs();
			disableModifSuppr();
			navigation.setEnabled(false);
			nb.setForeground(Color.gray);
		}
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UNE SERIE D'ENGINS MOTEURS.
	 * 
	 * @param s
	 *            Série d'engins moteurs actuellement affichée de type 'Serie'.
	 * @return Aucun.
	 */
	public void update(Serie s) {
		ajouter.setEnabled(false);
		disableModifAttributs();
		disableModifSuppr();
		if (s != null) {
			selectedSerie = s;
			nombre.setText("Engins vus :");
			nb.setForeground(Color.white);
			nb.setText(s.getNbEnginsVus() + " / " + s.getEffectif());
		} else {
			navigation.setEnabled(false);
			nb.setForeground(Color.gray);
		}
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UN PARC D'ENGINS MOTEURS.
	 * 
	 * @param p
	 *            Parc d'engins moteurs actuellement affiché de type 'Parc'.
	 * @return Aucun.
	 */
	public void update(Parc p) {
		ajouter.setEnabled(false);
		disableModifAttributs();
		disableModifSuppr();
		nombre.setText("Engins vus :");
		nb.setForeground(Color.white);
		nb.setText(p.getNbEnginsVus() + " / " + p.getEffectifActuel());
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR LA LISTE DES ANNIVERSAIRES DE MISE EN SERVICE.
	 * 
	 * @param listeAnniversaires
	 *            Liste d'engins moteurs dont c'est l'anniversaire de mise en
	 *            service, de type Vector<Engin>.
	 * @return Aucun.
	 */
	public void update(Vector<Engin> listeAnniversaires) {
		ajouter.setEnabled(false);
		disableModifAttributs();
		disableModifSuppr();
		nombre.setText("Anniv. M.E.S. :");
		nb.setText(Integer.toString(listeAnniversaires.size()));
	}

	/**
	 * ACTIVE LES BOUTONS DE MODIFICATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void enableModifAttributs() {
		modifierEtat.setEnabled(true);
		modifierLivree.setEnabled(true);
		modifierDepot.setEnabled(true);
	}

	/**
	 * DESACTIVE LES BOUTONS DE MODIFICATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void disableModifAttributs() {
		modifierEtat.setEnabled(false);
		modifierLivree.setEnabled(false);
		modifierDepot.setEnabled(false);
	}

	/**
	 * ACTIVE LES BOUTONS DE MODIFICATION ET DE SUPPRESSION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void enableModifSuppr() {
		modifier.setEnabled(true);
		supprimer.setEnabled(true);
	}

	/**
	 * DESACTIVE LES BOUTONS DE MODIFICATION ET DE SUPPRESSION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void disableModifSuppr() {
		modifier.setEnabled(false);
		supprimer.setEnabled(false);
	}

	/**
	 * DESACTIVE LE BOUTON DE NAVIGATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void disableNavigation() {
		navigation.setText("Afficher");
		navigation.setEnabled(false);
	}

	/**
	 * DEFINIT LES ACTIONS DES BOUTONS.
	 * 
	 * @param e
	 *            Evènement déclenché par l'appui sur un bouton.
	 * @return Aucun.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == ajouter) {
			if (remarque == false) {
				new FenetreVue(i, false, null, selectedEngin);
			} else {
				new FenetreRemarque(i, false, null, selectedEngin);
			}
		}
		if ((e.getSource() == modifier) && (i.rowIsSelected())) {
			if (remarque == false) {
				if (selectedVue != null) {
					new FenetreVue(i, true, selectedVue, selectedEngin);
				}
			} else {
				if (selectedRemarque != null) {
					new FenetreRemarque(i, true, selectedRemarque, selectedEngin);
				}
			}
		}
		if ((e.getSource() == supprimer) && (i.rowIsSelected())) {
			if ((remarque == false) && (selectedEngin != null)) {
				if (selectedVue != null) {
					new FenetreSuppression(i, selectedEngin, selectedVue, null, remarque);
				}
			} else {
				if (selectedRemarque != null) {
					new FenetreSuppression(i, selectedEngin, null, selectedRemarque, remarque);
				}
			}
		}
		if (e.getSource() == modifierEtat) {
			if (selectedEngin != null) {
				new FenetreEtat(i, selectedEngin, remarque);
			}
		}
		if (e.getSource() == modifierLivree) {
			if (selectedEngin != null) {
				new FenetreLivree(i, selectedEngin, remarque);
			}
		}
		if (e.getSource() == modifierDepot) {
			if (selectedEngin != null) {
				new FenetreDepot(i, selectedEngin, remarque);
			}
		}
		if (e.getSource() == navigation) {
			if (navigation.isEnabled()) {
				if (modeNavigation == false) {
					i.afficherResultat(selectedEngin, remarque);
				} else {
					i.afficherResultat(selectedSerie);
				}
			}
		}
	}
}
