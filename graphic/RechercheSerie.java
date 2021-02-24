/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 19/08/2016
 */

package graphic;

import data.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RechercheSerie extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private Interface i;
	private Parc p;
	private JPanel panel;
	private GridBagConstraints gbc;

	// Elements graphiques.
	private JLabel titre;
	private JComboBox<String> listeSeries;
	private String serieCourante;
	private JButton afficher;
	private JButton creer;

	/**
	 * CONSTRUCTEUR DE LA CLASSE RECHERCHERSERIE.
	 * 
	 * @param pI
	 *            Interface graphique mere, de type 'Interface'.
	 * @param mainPanel
	 *            Panel de l'interface graphique mere, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mere, de type
	 *            'GridBagConstraints'.
	 * @param parcEngins
	 *            Parc d'engins moteurs dans lequel doit s'effectuer les
	 *            recherches, de type 'Parc'.
	 * @return Aucun.
	 */
	public RechercheSerie(Interface pI, JPanel mainPanel, GridBagConstraints mainGbc, Parc parcEngin) {

		i = pI;
		p = parcEngin;
		serieCourante = Parc.SYM_ALL;

		mainGbc.gridx = 1;
		mainGbc.gridy = 1;

		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;

		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		titre = new JLabel("SERIES");
		titre.setFont(new Font(Interface.police.getFontName(), 1, 13));
		titre.setForeground(Color.white);
		panel.add(titre, gbc);

		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.bottom = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		listeSeries = new JComboBox<String>(p.getNomSeries());
		listeSeries.setFont(Interface.police);
		listeSeries.addItemListener(new ItemState());
		panel.add(listeSeries, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		afficher = new JButton("Afficher");
		afficher.setFont(Interface.police);
		afficher.addActionListener(this);
		panel.add(afficher, gbc);

		gbc.insets.top = 2;

		gbc.gridx = 1;
		gbc.gridy = 2;
		creer = new JButton("Creer");
		creer.setFont(Interface.police);
		creer.addActionListener(this);
		panel.add(creer, gbc);

		mainPanel.add(panel, mainGbc);
	}
	
	/**
	 * MODIFIE LA LISTE DES SERIES DE LA LISTE DEROULANTE.
	 * 
	 * @param 	Aucun.
	 * @return 	Aucun.
	 */
	public void addSerie(Serie newSerie) {
		listeSeries.addItem(newSerie.getNomComplet());
	}

	/**
	 * RENVOIE LA SERIE CHERCHE.
	 * 
	 * @param Aucun.
	 * @return Serie d'engins moteurs trouvee si la recherche a donne un
	 *         resultat. 'null' sinon.
	 */
	private Serie chercher() {
		return p.rechercherSerie(serieCourante);
	}

	/**
	 * DEFINIT LA SERIE D'ENGINS MOTEURS COURANTE.
	 * 
	 * @param newSerie
	 *            Serie d'engins moteurs courante de type 'Serie'.
	 * @return Aucun.
	 */
	public void setSerieCourante(Serie newSerie) {
		serieCourante = newSerie.getNomComplet();
	}

	/**
	 * DEFINIT LES ACTIONS DE LA LISTE DEROULANTE.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	class ItemState implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == listeSeries) {
				serieCourante = (String) e.getItem();
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

		if (e.getSource() == afficher) {
			if (serieCourante.compareTo(Parc.SYM_ALL) == 0) {
				i.afficherParc();
			} else {
				i.afficherResultat(chercher());
			}
			i.setEnableFalse();
		}
		if (e.getSource() == creer) {
			new FenetreSerie(i, p);
		}
	}
}
