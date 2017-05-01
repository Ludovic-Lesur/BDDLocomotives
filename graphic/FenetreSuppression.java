/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 27/08/2016
 */

package graphic;

import data.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreSuppression extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private JFrame fenetre;
	private JPanel panel;
	private GridBagConstraints gbc;
	private Interface i;
	private Engin enginCourant;
	private Vue vueCourante;
	private Remarque remarqueCourante;
	private boolean remarque;

	// Elements graphiques et attributs.
	private JLabel question;
	private JLabel date;
	private JLabel engin;
	private JButton oui;
	private JButton non;

	/**
	 * CONSTRUCTEUR DE LA CLASSE FENETRESUPPRESSION.
	 * 
	 * @param pI
	 *            Interface graphique mère, de type 'Interface'.
	 * @param pEngin
	 *            Engin dont on doit supprimer un attribut (vue ou remarque), de
	 *            type 'Engin'.
	 * @param pVue
	 *            Objet 'Vue' à supprimer si pBoolRemarque = 'false'. 'null'
	 *            sinon.
	 * @param pRemarque
	 *            Objet 'Remarque' à supprimer si pBoolRemarque = 'true'. 'null'
	 *            sinon.
	 * @param pBoolRemarque
	 *            Booléen indiquant l'attribut à supprimer. 'true' = suppression
	 *            d'une remarque. 'false' = suppression d'une vue.
	 * @return Aucun.
	 */
	public FenetreSuppression(Interface pI, Engin pEngin, Vue pVue, Remarque pRemarque, boolean pBoolRemarque) {

		i = pI;
		enginCourant = pEngin;
		vueCourante = pVue;
		remarqueCourante = pRemarque;
		remarque = pBoolRemarque;

		// Création de l'interface
		fenetre = new JFrame();
		if (remarque == false) {
			fenetre.setTitle("Supprimer vue");
		} else {
			fenetre.setTitle("Supprimer remarque");
		}
		fenetre.setSize(250, 200);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);

		// Panel
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		question = new JLabel();
		if (remarque == false) {
			question.setText("Supprimer la vue ?");
		} else {
			question.setText("Supprimer la remarque ?");
		}
		question.setFont(Interface.police);
		question.setForeground(Color.yellow);
		panel.add(question, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		engin = new JLabel();
		engin.setText(enginCourant.getNomComplet());
		engin.setFont(Interface.police);
		engin.setForeground(Color.white);
		panel.add(engin, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		date = new JLabel();
		if (remarque == false) {
			date.setText(Interface.dateToString(vueCourante.getDate(), false));
		} else {
			date.setText(Interface.dateToString(remarqueCourante.getDate(), false));
		}
		date.setFont(Interface.police);
		date.setForeground(Color.white);
		panel.add(date, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		oui = new JButton();
		oui.setText("Oui");
		oui.setFont(Interface.police);
		oui.setForeground(new Color(100, 200, 0));
		oui.addActionListener(this);
		panel.add(oui, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		non = new JButton();
		non.setText("Non");
		non.setFont(Interface.police);
		non.setForeground(Color.red);
		non.addActionListener(this);
		panel.add(non, gbc);

		// Affichage de la fenêtre
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}

	/**
	 * DEFINIT LES ACTIONS DES BOUTONS.
	 * 
	 * @param e
	 *            Evènement déclenché par l'appui sur un bouton.
	 * @return Aucun.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == oui) {
			if (remarque == false) {
				enginCourant.getSerie().deleteXML(enginCourant.getNumero(), vueCourante);
			} else {
				enginCourant.getSerie().deleteXML(enginCourant.getNumero(), remarqueCourante);
			}

			Engin enginUpdated = enginCourant.getSerie().rechercherEngin(enginCourant.getNumero(), Parc.dossierParc);
			i.afficherResultat(enginUpdated, remarque);

			fenetre.setVisible(false);
			fenetre.dispose();
		}
		if (e.getSource() == non) {
			fenetre.setVisible(false);
			fenetre.dispose();
		}
	}
}
