/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 30/08/2016
 */

package graphic;

import data.*;
import typedef.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FenetreSerie extends JFrame implements ActionListener, DocumentListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private Interface i;
	private Parc parc;
	private JFrame fenetre;
	private JPanel panel;
	private GridBagConstraints gbc;

	// Elements graphiques et attributs.
	private JLabel identifiant;
	private JComboBox<String> choixIdentifiant;
	private Identifiant identifiantCourant;
	private JLabel serie;
	private JTextField champSerie;
	private int serieCourante;
	private JLabel effectif;
	private int effectifCourant;
	private JTextField champEffectif;
	private JCheckBox appariement;
	private JButton ok;
	private JButton annuler;

	/**
	 * CONSTRUCTEUR DE LA CLASSE FENETRERESERIE.
	 * 
	 * @param pParc
	 *            Parc d'engin moteurs auquel la serie doit etre ajoutee, de
	 *            type 'Parc'.
	 * @return Aucun.
	 */
	public FenetreSerie(Interface pI, Parc pParc) {

		i = pI ;
		parc = pParc;

		// Creation de l'interface
		fenetre = new JFrame();
		fenetre.setTitle("Creer serie");
		fenetre.setSize(350, 220);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);

		// Panel
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		identifiant = new JLabel("Identifiant");
		identifiant.setFont(Interface.police);
		identifiant.setForeground(Color.yellow);
		panel.add(identifiant, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		serie = new JLabel("Serie");
		serie.setFont(Interface.police);
		serie.setForeground(Color.yellow);
		panel.add(serie, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		effectif = new JLabel("Effectif");
		effectif.setFont(Interface.police);
		effectif.setForeground(Color.yellow);
		panel.add(effectif, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		choixIdentifiant = new JComboBox<String>(Identifiant.getNames());
		choixIdentifiant.setFont(Interface.police);
		choixIdentifiant.addItemListener(new ItemState());
		panel.add(choixIdentifiant, gbc);
		identifiantCourant = Identifiant.values()[choixIdentifiant.getSelectedIndex() + 1]; // +1 car "INC" n'est pas dans la liste.

		gbc.gridx = 1;
		gbc.gridy = 1;
		champSerie = new JTextField(5);
		champSerie.setFont(Interface.police);
		champSerie.setEditable(true);
		champSerie.getDocument().addDocumentListener(this);
		panel.add(champSerie, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		champEffectif = new JTextField(5);
		champEffectif.setFont(Interface.police);
		champEffectif.setEditable(true);
		champEffectif.getDocument().addDocumentListener(this);
		panel.add(champEffectif, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 2;
		appariement = new JCheckBox("Numerotation appariee");
		appariement.setFont(Interface.police);
		appariement.addActionListener(this);
		appariement.setBackground(Color.gray);
		appariement.setForeground(Color.white);
		appariement.setSelected(false);
		panel.add(appariement, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		ok = new JButton();
		ok.setText("Creer fichier XML");
		ok.setFont(Interface.police);
		ok.setForeground(new Color(100, 200, 0));
		ok.addActionListener(this);
		ok.setEnabled(false);
		panel.add(ok, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		annuler = new JButton("Annuler");
		annuler.setFont(Interface.police);
		annuler.setForeground(Color.red);
		annuler.addActionListener(this);
		panel.add(annuler, gbc);

		// Affichage de la fenetre
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}

	/**
	 * VERIFIE SI LE CHAMP SERIE CONTIENT UN NOMBRE MULTIPLE DE 100.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private boolean checkSerie() {
		boolean result = false;
		if ((Serie.isNumeric(champSerie.getText())) && (champSerie.getText().compareTo("") != 0)) {
			serieCourante = Serie.stringToInteger(champSerie.getText());
			if (serieCourante % 100 == 0) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * VERIFIE SI L'EFFECTIF EST UN NOMBRE ENTIER.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private boolean checkEffectif() {
		boolean result = false;
		if ((Serie.isNumeric(champEffectif.getText())) && (champEffectif.getText().compareTo("") != 0)) {
			effectifCourant = Serie.stringToInteger(champEffectif.getText());
			result = true;
		}
		return result;
	}

	/**
	 * VERIFIE SI LES DEUX CHAMPS DE SAISIE SONT CORRECTS.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void checkTextFields() {
		if (checkSerie() && checkEffectif()) {
			ok.setEnabled(true);
		} else {
			ok.setEnabled(false);
		}
	}

	/**
	 * DEFINIT LES ACTIONS DE LA LISTE DEROULANTE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	class ItemState implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == choixIdentifiant) {
				identifiantCourant = Identifiant.values()[choixIdentifiant.getSelectedIndex() + 1]; // +1 car "INC" n'est pas dans la liste.
			}
		}
	}

	/**
	 * DEFINIT LES ACTIONS DES BOUTONS.
	 * 
	 * @param e
	 *            Evenement declenche par l'appui sur un bouton.
	 * @return Aucun.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			Serie s = parc.creerSerie(identifiantCourant, serieCourante, effectifCourant, appariement.isSelected());
			i.addSerie(s) ;
			fenetre.setVisible(false);
			fenetre.dispose();
		}
		if (e.getSource() == annuler) {
			fenetre.setVisible(false);
			fenetre.dispose();
		}
	}

	/**
	 * FONCTIONS DE VERIFICATION DE SAISIE CLAVIER.
	 * 
	 * @param e
	 *            Evenement declenche par une saisie clavier.
	 * @return Aucun.
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		checkTextFields();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkTextFields();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkTextFields();
	}
}
