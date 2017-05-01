/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 19/08/2016
 */

package graphic;

import typedef.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class AnniversairesMES extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private Interface i;
	private JPanel panel;
	private GridBagConstraints gbc;

	// Elements graphiques et attributs.
	private JLabel titre;
	private JButton afficher;
	private JRadioButton aujourdhui;
	private JRadioButton autreDate;
	private ButtonGroup choixDate;
	private JComboBox<String> choixJour;
	private int numJour = 1;
	private JComboBox<String> choixMois;
	private int numMois = 1;
	private boolean mode; // 'false' = aujourd'hui.
							// 'true' = autre date.

	/**
	 * CONSTRUCTEUR DE LA CLASSE ANNIVERSAIREMES.
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
	public AnniversairesMES(Interface pI, JPanel mainPanel, GridBagConstraints mainGbc) {

		mainGbc.gridx = 3;
		mainGbc.gridy = 1;

		i = pI;
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		titre = new JLabel("ANNIVERSAIRES  M.E.S.");
		titre.setFont(new Font(Interface.police.getFontName(), 1, 13));
		titre.setForeground(Color.white);
		panel.add(titre, gbc);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.bottom = 2;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		aujourdhui = new JRadioButton("Aujourd'hui");
		aujourdhui.setFont(Interface.police);
		aujourdhui.setOpaque(false);
		aujourdhui.setForeground(Color.white);
		aujourdhui.setSelected(true);
		aujourdhui.addActionListener(this);
		panel.add(aujourdhui, gbc);

		gbc.insets.top = 2;

		gbc.gridx = 0;
		gbc.gridy = 2;
		autreDate = new JRadioButton("Autre date");
		autreDate.setFont(Interface.police);
		autreDate.setOpaque(false);
		autreDate.setForeground(Color.white);
		autreDate.setSelected(false);
		autreDate.addActionListener(this);
		panel.add(autreDate, gbc);

		choixDate = new ButtonGroup();
		choixDate.add(aujourdhui);
		choixDate.add(autreDate);
		mode = false;

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		afficher = new JButton("Afficher");
		afficher.setFont(Interface.police);
		afficher.addActionListener(this);
		panel.add(afficher, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		choixJour = new JComboBox<String>(Jour.getNumJours());
		choixJour.setFont(Interface.police);
		choixJour.addItemListener(new ItemState());
		choixJour.setSelectedIndex(LocalDate.now().getDayOfMonth() - 1);
		choixJour.setEditable(false);
		choixJour.setEnabled(false);
		choixJour.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		numJour = LocalDate.now().getDayOfMonth();
		panel.add(choixJour, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		choixMois = new JComboBox<String>(Mois.getFrenchNames());
		choixMois.setFont(Interface.police);
		choixMois.addItemListener(new ItemState());
		choixMois.setSelectedIndex(LocalDate.now().getMonth().getValue() - 1);
		choixMois.setEditable(false);
		choixMois.setEnabled(false);
		choixMois.setMaximumRowCount(Interface.COMBOBOX_HEIGHT);
		numMois = LocalDate.now().getMonthValue();
		panel.add(choixMois, gbc);

		mainPanel.add(panel, mainGbc);
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
				numJour = choixJour.getSelectedIndex() + 1;
				checkDate();
			}
			if (e.getSource() == choixMois) {
				numMois = choixMois.getSelectedIndex() + 1;
				checkDate();
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
			LocalDate.of(2000, numMois, numJour);
			afficher.setEnabled(true);
		} catch (DateTimeException timeEx) {
			afficher.setEnabled(false);
		}
	}

	/**
	 * DEFINIT LES ACTIONS DES BOUTONS.
	 * 
	 * @param e
	 *            Evènement déclenché par l'appui sur un bouton.
	 * @return Aucun.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == aujourdhui) {
			choixJour.setEditable(false);
			choixJour.setEnabled(false);
			choixMois.setEditable(false);
			choixMois.setEnabled(false);
			afficher.setEnabled(true);
			mode = false;
		}
		if (e.getSource() == autreDate) {
			choixJour.setEditable(true);
			choixJour.setEnabled(true);
			choixMois.setEditable(true);
			choixMois.setEnabled(true);
			mode = true;
		}
		if (e.getSource() == afficher) {
			if (mode == false) {
				i.afficherAnniversaires(LocalDate.now());
			} else {

				i.afficherAnniversaires(LocalDate.of(2000, numMois, numJour));
			}
			i.setEnableFalse();
		}
	}
}