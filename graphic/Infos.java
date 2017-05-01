/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 20/08/2016
 */

package graphic;

import data.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Infos {

	// Eléments graphiques.
	private JPanel panel;
	private GridBagConstraints gbc;

	private JLabel titre;
	private JLabel champ11;
	private JLabel champ12;
	private JLabel labelPhoto;
	private JLabel champ21;
	private JLabel champ22;
	private JLabel champ31;
	private JLabel champ32;
	private JLabel champ41;
	private JLabel champ42;
	private JLabel champ51;
	private JLabel champ52;
	private JLabel champ61;
	private JLabel champ62;
	private JLabel champ71;
	private JLabel champ72;

	/**
	 * CONSTRUCTEUR DE LA CLASSE INFOS.
	 * 
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @return Aucun.
	 */
	public Infos(JPanel mainPanel, GridBagConstraints mainGbc) {

		mainGbc.gridx = 1;
		mainGbc.gridy = 2;

		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(7, 7, 7, 7);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		titre = new JLabel("INFOS");
		titre.setForeground(Color.white);
		titre.setFont(new Font(Interface.police.getFontName(), 1, 13));
		panel.add(titre, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		champ11 = new JLabel(" ");
		champ11.setForeground(Color.white);
		champ11.setFont(new Font(Interface.police.getFontName(), 2, 20));
		panel.add(champ11, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		champ12 = new JLabel(" ");
		champ12.setForeground(Color.white);
		champ12.setFont(new Font(Interface.police.getFontName(), 2, 20));
		panel.add(champ12, gbc);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		labelPhoto = new JLabel();
		panel.add(labelPhoto, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		champ21 = new JLabel(" ");
		champ21.setForeground(Color.yellow);
		champ21.setFont(Interface.police);
		panel.add(champ21, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		champ22 = new JLabel(" ");
		champ22.setForeground(Color.white);
		champ22.setFont(Interface.police);
		panel.add(champ22, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		champ31 = new JLabel(" ");
		champ31.setForeground(Color.yellow);
		champ31.setFont(Interface.police);
		panel.add(champ31, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		champ32 = new JLabel(" ");
		champ32.setForeground(Color.white);
		champ32.setFont(Interface.police);
		panel.add(champ32, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		champ41 = new JLabel(" ");
		champ41.setForeground(Color.yellow);
		champ41.setFont(Interface.police);
		panel.add(champ41, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		champ42 = new JLabel(" ");
		champ42.setForeground(Color.white);
		champ42.setFont(Interface.police);
		panel.add(champ42, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		champ51 = new JLabel(" ");
		champ51.setForeground(Color.yellow);
		champ51.setFont(Interface.police);
		panel.add(champ51, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		champ52 = new JLabel(" ");
		champ52.setForeground(Color.white);
		champ52.setFont(Interface.police);
		panel.add(champ52, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		champ61 = new JLabel(" ");
		champ61.setForeground(Color.yellow);
		champ61.setFont(Interface.police);
		panel.add(champ61, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		champ62 = new JLabel(" ");
		champ62.setForeground(Color.white);
		champ62.setFont(Interface.police);
		panel.add(champ62, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		champ71 = new JLabel(" ");
		champ71.setForeground(Color.yellow);
		champ71.setFont(Interface.police);
		panel.add(champ71, gbc);

		gbc.gridx = 1;
		gbc.gridy = 8;
		champ72 = new JLabel(" ");
		champ72.setForeground(Color.white);
		champ72.setFont(Interface.police);
		panel.add(champ72, gbc);

		cacher(mainPanel, mainGbc);
		mainPanel.add(panel, mainGbc);
	}

	/**
	 * AFFICHE UNE PHOTO DANS LE CHAMP DEDIE.
	 * 
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @param chemin
	 *            Chemin absolu de la photo a afficher de type 'String'.
	 * @return Aucun.
	 */
	private void afficherPhoto(JPanel mainPanel, GridBagConstraints mainGbc, String chemin) {
		gbc.weightx = 2;
		gbc.weighty = 1;
		ImageIcon image = new ImageIcon(chemin);
		Image im = image.getImage();
		im = im.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		image = new ImageIcon(im);
		labelPhoto.setIcon(image);
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UN ENGIN MOTEUR.
	 * 
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @param e
	 *            Engin dont on doit afficher les informations, de type 'Engin'.
	 * @return Aucun.
	 */
	public void update(JPanel mainPanel, GridBagConstraints mainGbc, Engin e) {
		if (e != null) {
			afficher();
			switch (e.getEtat()) {
			case AMES:
				champ21.setText("Etat");
				champ22.setForeground(Color.green);
				champ22.setText(e.getEtat().getName());
				champ31.setText("Livrée");
				champ32.setText(e.getNomLivree());
				champ41.setText("Futur dépôt");
				champ42.setText(e.getNomDepot());
				champ51.setForeground(Color.gray);
				champ52.setForeground(Color.gray);
				champ61.setForeground(Color.gray);
				champ62.setForeground(Color.gray);
				champ71.setForeground(Color.gray);
				champ72.setForeground(Color.gray);
				break;
			case ES:
				champ21.setText("Mise en service");
				champ22.setText(Interface.dateToString(e.getDateMES(), true));
				champ31.setText("Etat");
				champ32.setForeground(Color.green);
				champ32.setText(e.getEtat().getName());
				champ41.setText("Age");
				champ42.setText(e.getAge(true));
				champ51.setText("Livrée");
				champ52.setText(e.getNomLivree());
				champ61.setText("Dépôt");
				champ62.setText(e.getNomDepot());
				champ71.setForeground(Color.gray);
				champ72.setForeground(Color.gray);
				break;
			case R:
				champ21.setText("Mise en service");
				champ22.setText(Interface.dateToString(e.getDateMES(), true));
				champ31.setText("Etat");
				champ32.setForeground(Color.orange);
				champ32.setText(e.getEtat().getName());
				champ41.setText("Radiation");
				champ42.setText(Interface.dateToString(e.getDateRAD(), true));
				champ51.setText("Age à la radiation");
				champ52.setText(e.getAge(true));
				champ61.setText("Livrée");
				champ62.setText(e.getNomLivree());
				champ71.setText("Dépôt");
				champ72.setText(e.getNomDepot());
				break;
			case PR:
				champ21.setText("Mise en service");
				champ22.setText(Interface.dateToString(e.getDateMES(), true));
				champ31.setText("Etat");
				champ32.setForeground(Color.green);
				champ32.setText(e.getEtat().getName() + e.getPreservation(true));
				champ41.setText("Radiation");
				champ42.setText(Interface.dateToString(e.getDateRAD(), true));
				champ51.setText("Age");
				champ52.setText(e.getAge(true));
				champ61.setText("Livrée");
				champ62.setText(e.getNomLivree());
				champ71.setText("Dépôt d'origine");
				champ72.setText(e.getNomDepot());
				break;
			default:
				cacher(mainPanel, mainGbc);
			}
			if (e.getNumero().isEmpty() == false) {
				champ11.setForeground(Color.white);
				champ11.setText(e.getNomComplet());
				if (e.getRenumerotation().compareTo("") != 0) {
					champ12.setForeground(Color.white);
					champ12.setText("(" + e.getRenumerotation() + ")");
				} else {
					champ12.setForeground(Color.gray);
				}
			} else {
				champ11.setForeground(Color.gray);
				champ12.setForeground(Color.gray);
			}
			afficherPhoto(mainPanel, mainGbc, e.getCheminPhoto());
		} else {
			cacher(mainPanel, mainGbc);
		}
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UNE SERIE D'ENGINS MOTEURS.
	 * 
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @param s
	 *            Série dont on doit afficher les informations, de type 'Serie'.
	 * @return Aucun.
	 */
	public void update(JPanel mainPanel, GridBagConstraints mainGbc, Serie s) {
		if (s != null) {
			afficher();
			afficherPhoto(mainPanel, mainGbc, Engin.cheminPhotoVide);
			champ11.setText(s.getNomComplet());
			champ12.setForeground(Color.gray);
			champ21.setText("Livraison");
			champ22.setText(s.getAnneeMESMin() + " à " + s.getAnneeMESMax());
			champ31.setText("Effectif");
			champ32.setText(Integer.toString(s.getEffectifActuel()));
			champ41.setText("En attente de mise en service");
			champ42.setForeground(Color.green);
			champ42.setText(Integer.toString(s.getEnAttente()));
			champ51.setText("En service");
			champ52.setForeground(Color.green);
			champ52.setText(Integer.toString(s.getEnService()));
			champ61.setText("Radiés");
			champ62.setForeground(Color.orange);
			champ62.setText(Integer.toString(s.getRadies()));
			champ71.setText("Préservés");
			champ72.setForeground(Color.green);
			champ72.setText(Integer.toString(s.getPreserves()));
		} else {
			cacher(mainPanel, mainGbc);
		}
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UN PARC D'ENGINS MOTEURS.
	 * 
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @param p
	 *            Parc dont on doit afficher les informations, de type 'Parc'.
	 * @return Aucun.
	 */
	public void update(JPanel mainPanel, GridBagConstraints mainGbc, Parc p) {
		afficher();
		afficherPhoto(mainPanel, mainGbc, Engin.cheminPhotoVide);
		champ11.setText("Parc " + p.getNom());
		champ12.setForeground(Color.gray);
		champ21.setText("Séries suivies");
		champ22.setText(Integer.toString(p.getNbSeries()));
		champ31.setText("Engins en attente de mise en service");
		champ32.setForeground(Color.green);
		champ32.setText(Integer.toString(p.getEnAttente()));
		champ41.setText("Engins en service");
		champ42.setForeground(Color.green);
		champ42.setText(Integer.toString(p.getEnService()));
		champ51.setText("Engins radiés");
		champ52.setForeground(Color.orange);
		champ52.setText(Integer.toString(p.getRadies()));
		champ61.setText("Engins préservés");
		champ62.setForeground(Color.green);
		champ62.setText(Integer.toString(p.getPreserves()));
		champ71.setText("TOTAL");
		champ72.setText(Integer.toString(p.getEffectifActuel()));
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR LA LISTE DES ANNIVERSAIRES.
	 * 
	 * @param mainPanel
	 *            Panel de l'interface graphique mère, de type 'JPanel'.
	 * @param mainGbc
	 *            Contraintes de l'interface graphique mère, de type
	 *            'GridBagConstraints'.
	 * @param listeAnniversaires
	 *            Liste d'engins moteurs dont c'est l'anniversaire de mise en
	 *            service, de type 'Vector<Engin>'.
	 * @return Aucun.
	 */
	public void update(JPanel mainPanel, GridBagConstraints mainGbc, Vector<Engin> listeAnniversaires) {
		cacher(mainPanel, mainGbc);
	}

	/**
	 * REND VISIBLES LES CHAMPS D'INFORMATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void afficher() {
		champ11.setForeground(Color.white);
		champ12.setForeground(Color.white);
		champ21.setForeground(Color.yellow);
		champ22.setForeground(Color.white);
		champ31.setForeground(Color.yellow);
		champ32.setForeground(Color.white);
		champ41.setForeground(Color.yellow);
		champ42.setForeground(Color.white);
		champ51.setForeground(Color.yellow);
		champ52.setForeground(Color.white);
		champ61.setForeground(Color.yellow);
		champ62.setForeground(Color.white);
		champ71.setForeground(Color.yellow);
		champ72.setForeground(Color.white);
	}

	/**
	 * CACHE TOUS LES CHAMPS D'INFORMATION.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	private void cacher(JPanel mainPanel, GridBagConstraints mainGbc) {
		champ11.setForeground(Color.gray);
		champ12.setForeground(Color.gray);
		champ21.setForeground(Color.gray);
		champ22.setForeground(Color.gray);
		champ31.setForeground(Color.gray);
		champ32.setForeground(Color.gray);
		champ41.setForeground(Color.gray);
		champ42.setForeground(Color.gray);
		champ51.setForeground(Color.gray);
		champ52.setForeground(Color.gray);
		champ61.setForeground(Color.gray);
		champ62.setForeground(Color.gray);
		champ71.setForeground(Color.gray);
		champ72.setForeground(Color.gray);
		afficherPhoto(mainPanel, mainGbc, Engin.cheminPhotoVide);
	}
}
