/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 19/08/2016
 */

package graphic;

import data.*;
import typedef.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class Tableau extends JPanel {

	private static final long serialVersionUID = 1L;

	// Lien avec les autres classes graphiques.
	private Interface i;

	// Eléments graphiques.
	private JScrollPane scroll;
	private DefaultTableModel modeleVue;
	private DefaultTableModel modeleRemarques;
	private DefaultTableModel modeleSerie;
	private DefaultTableModel modeleParc;
	private JTable tableau;
	int mode; // 0 = liste de vues.
				// 1 = liste de remarques.
				// 2 = liste d'engins.
				// 3 = liste de séries.

	private Vector<Serie> listeSeries; // Affichage du parc.
	private Vector<Engin> listeEngins; // Affichage d'une série et des
										// anniversaires.
	private Vector<Vue> listeVues; // Affichage d'un engin - vues.
	private Vector<Remarque> listeRemarques; // Affichage d'un engin -
												// remarques.

	/**
	 * CONSTRUCTEUR DE LA CLASSE TABLEAU.
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
	public Tableau(Interface pI, JPanel mainPanel, GridBagConstraints mainGbc) {

		i = pI;

		mainGbc.gridx = 2;
		mainGbc.gridy = 2;

		modeleVue = new DefaultTableModel();
		modeleVue.addColumn("DATE");
		modeleVue.addColumn("LIEU");
		modeleVue.addColumn("VOYAGE");
		modeleVue.addColumn("TRAIN");
		modeleVue.addColumn("PHO");
		modeleVue.addColumn("VID");
		modeleVue.addColumn("CAB");
		listeVues = new Vector<Vue>();

		modeleRemarques = new DefaultTableModel();
		modeleRemarques.addColumn("DATE");
		modeleRemarques.addColumn("OBSERVATION");
		listeRemarques = new Vector<Remarque>();

		modeleSerie = new DefaultTableModel();
		modeleSerie.addColumn("ENGIN");
		modeleSerie.addColumn("MISE EN SERVICE");
		modeleSerie.addColumn("ETAT");
		modeleSerie.addColumn("AGE");
		modeleSerie.addColumn("LIVREE");
		modeleSerie.addColumn("DEPOT");
		listeEngins = new Vector<Engin>();

		modeleParc = new DefaultTableModel();
		modeleParc.addColumn("SERIE");
		modeleParc.addColumn("TYPE");
		modeleParc.addColumn("EFFECTIF");
		modeleParc.addColumn("AMES");
		modeleParc.addColumn("ES");
		modeleParc.addColumn("R");
		modeleParc.addColumn("PR");
		modeleParc.addColumn("VUS");
		listeSeries = new Vector<Serie>();

		tableau = new JTable(modeleVue) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tableau.setFont(new Font(Interface.police.getFontName(), 0, Interface.police.getSize()));
		tableau.getTableHeader().setFont(Interface.police);
		tableau.getTableHeader().setPreferredSize(new Dimension(600, 30));

		// Hauteur des lignes.
		tableau.setRowHeight(30);
		// Largeur des colonnes.
		dimensionnerTableau(null, false);
		mode = 0;

		// Détection du clic souris.
		tableau.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evnt) {
				if (evnt.getClickCount() == 1) {
					int ind = tableau.getSelectedRow();
					switch (mode) {
					// Liste de vues.
					case 0:
						i.enableModifAll();
						i.setVue(listeVues.elementAt(ind));
						break;
					// Liste de remarques.
					case 1:
						i.enableModifAll();
						i.setRemarque(listeRemarques.elementAt(ind));
						break;
					// Liste d'engins.
					case 2:
						i.setEngin(listeEngins.elementAt(ind));
						break;
					// Liste de séries.
					case 3:
						i.setSerie(listeSeries.elementAt(ind));
						break;
					}
				}
			}
		});

		scroll = new JScrollPane(tableau);
		scroll.setPreferredSize(new Dimension(600, 450));
		mainPanel.add(scroll, mainGbc);
	}

	/**
	 * TESTE SI UNE LIGNE DU TABLEAU A ETE SELECTIONNEE.
	 * 
	 * @param Aucun.
	 * @return 'true' si une ligne du tableau a été sélectionnée. 'false' sinon.
	 */
	public boolean rowIsSelected() {
		return (tableau.getSelectedRow() != -1);
	}

	/**
	 * DIMENSIONNE LE TABLEAU POUR L'AFFICHAGE D'UN ENGIN MOTEUR.
	 * 
	 * @param e
	 *            Engin moteur dont on doit afficher les caractéristiques, de
	 *            type 'Engin'.
	 * @param remarque
	 *            Booléen indiquant les caractéristiques à afficher. 'true' =
	 *            affichage des remarques. 'false' = affichage des vues.
	 * @return Aucun.
	 */
	private void dimensionnerTableau(Engin e, boolean remarque) {
		TableColumn colonne;
		if (remarque == false) {
			// 1
			colonne = tableau.getColumnModel().getColumn(0);
			colonne.setPreferredWidth(54);
			colonne.setResizable(false);
			// 2
			colonne = tableau.getColumnModel().getColumn(1);
			colonne.setPreferredWidth(130);
			colonne.setResizable(false);
			// 3
			colonne = tableau.getColumnModel().getColumn(2);
			colonne.setPreferredWidth(130);
			colonne.setResizable(false);
			// 4
			colonne = tableau.getColumnModel().getColumn(3);
			colonne.setPreferredWidth(30);
			colonne.setResizable(false);
			// 5
			colonne = tableau.getColumnModel().getColumn(4);
			colonne.setPreferredWidth(5);
			colonne.setResizable(false);
			// 6
			colonne = tableau.getColumnModel().getColumn(5);
			colonne.setPreferredWidth(5);
			colonne.setResizable(false);
			// 7
			colonne = tableau.getColumnModel().getColumn(6);
			colonne.setPreferredWidth(5);
			colonne.setResizable(false);
		} else {
			// 1
			colonne = tableau.getColumnModel().getColumn(0);
			colonne.setPreferredWidth(30);
			colonne.setResizable(false);
			// 2
			colonne = tableau.getColumnModel().getColumn(1);
			colonne.setPreferredWidth(463);
			colonne.setResizable(false);
		}
	}

	/**
	 * DIMENSIONNE LE TABLEAU POUR L'AFFICHAGE D'UNE SERIE D'ENGINS MOTEURS.
	 * 
	 * @param s
	 *            Série d'engins moteurs à afficher, de type 'Serie'.
	 * @return Aucun.
	 */
	private void dimensionnerTableau(Serie s) {
		TableColumn colonne;
		// 1
		colonne = tableau.getColumnModel().getColumn(0);
		colonne.setPreferredWidth(1);
		colonne.setResizable(false);
		// 2
		colonne = tableau.getColumnModel().getColumn(1);
		colonne.setPreferredWidth(40);
		colonne.setResizable(false);
		// 3
		colonne = tableau.getColumnModel().getColumn(2);
		colonne.setPreferredWidth(90);
		colonne.setResizable(false);
		// 4
		colonne = tableau.getColumnModel().getColumn(3);
		colonne.setPreferredWidth(1);
		colonne.setResizable(false);
		// 5
		colonne = tableau.getColumnModel().getColumn(4);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
		// 6
		colonne = tableau.getColumnModel().getColumn(5);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
	}

	/**
	 * DIMENSIONNE LE TABLEAU POUR L'AFFICHAGE D'UN PARC D'ENGINS MOTEURS.
	 * 
	 * @param p
	 *            Parc d'engins moteurs à afficher, de type 'Parc'.
	 * @return Aucun.
	 */
	private void dimensionnerTableau(Parc p) {
		TableColumn colonne;
		// 1
		colonne = tableau.getColumnModel().getColumn(0);
		colonne.setPreferredWidth(70);
		colonne.setResizable(false);
		// 2
		colonne = tableau.getColumnModel().getColumn(1);
		colonne.setPreferredWidth(130);
		colonne.setResizable(false);
		// 3
		colonne = tableau.getColumnModel().getColumn(2);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
		// 4
		colonne = tableau.getColumnModel().getColumn(3);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
		// 5
		colonne = tableau.getColumnModel().getColumn(4);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
		// 6
		colonne = tableau.getColumnModel().getColumn(5);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
		// 7
		colonne = tableau.getColumnModel().getColumn(6);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
		// 8
		colonne = tableau.getColumnModel().getColumn(7);
		colonne.setPreferredWidth(50);
		colonne.setResizable(false);
	}

	/**
	 * EFFACE LE CONTENU DU TABLEAU.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public void supprimerTableau() {
		int rowCount;
		int i = 0;
		rowCount = modeleVue.getRowCount();
		for (i = rowCount - 1; i >= 0; i--) {
			modeleVue.removeRow(i);
		}
		rowCount = modeleRemarques.getRowCount();
		for (i = rowCount - 1; i >= 0; i--) {
			modeleRemarques.removeRow(i);
		}
		rowCount = modeleSerie.getRowCount();
		for (i = rowCount - 1; i >= 0; i--) {
			modeleSerie.removeRow(i);
		}
		rowCount = modeleParc.getRowCount();
		for (i = rowCount - 1; i >= 0; i--) {
			modeleParc.removeRow(i);
		}
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UN ENGIN MOTEUR.
	 * 
	 * @param e
	 *            Engin moteur dont on doit afficher les caractéristiques, de
	 *            type 'Engin'.
	 * @param remarque
	 *            Booléen indiquant les caractéristiques à afficher. 'true' =
	 *            affichage des remarques. 'false' = affichage des vues.
	 * @return Aucun.
	 */
	public void update(Engin e, boolean remarque) {
		supprimerTableau();
		if (remarque == false) {
			// Affichage des vues
			if (e != null) {
				listeVues = e.getVues();
				mode = 0;
				Iterator<Vue> i = listeVues.iterator();
				while (i.hasNext()) {
					Vue vueCourante = i.next();
					String date = Interface.dateToString(vueCourante.getDate(), false);
					String lieu = vueCourante.getNomLieu();
					String voyage = "";
					if (lieu.compareTo(Lieu.Vide.getName()) == 0) {
						voyage = vueCourante.getNomVoyageDepart() + "  -->  " + vueCourante.getNomVoyageArrivee();
					}
					String train = vueCourante.getNomTrain();
					String photo = Boolean.toString(vueCourante.getPhoto());
					String video = Boolean.toString(vueCourante.getVideo());
					String cabine = Boolean.toString(vueCourante.getCabine());
					String[] ligne = { date, lieu, voyage, train, photo, video, cabine };
					modeleVue.addRow(ligne);
				}
			}
			tableau.setModel(modeleVue);
			tableau.setDefaultRenderer(Object.class, new VueRenderer(listeVues));
		} else {
			// Affichage des remarques
			if (e != null) {
				listeRemarques = e.getRemarques();
				mode = 1;
				Iterator<Remarque> j = listeRemarques.iterator();
				while (j.hasNext()) {
					Remarque remCourante = j.next();
					String date = Interface.dateToString(remCourante.getDate(), false);
					String texte = remCourante.getTexte();
					String[] ligne = { date, texte };
					modeleRemarques.addRow(ligne);
				}
			}
			tableau.setModel(modeleRemarques);
			tableau.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
		}
		dimensionnerTableau(e, remarque);
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UNE SERIE D'ENGINS MOTEURS.
	 * 
	 * @param s
	 *            Série d'engins moteurs à afficher, de type 'Serie'.
	 * @return Aucun.
	 */
	public void update(Serie s) {
		supprimerTableau();
		if (s != null) {
			listeEngins = s.getEngins();
			mode = 2;
			Iterator<Engin> j = listeEngins.iterator();
			while (j.hasNext()) {
				Engin enginCourant = j.next();
				String numero = enginCourant.getNumero();
				String miseEnService = Interface.dateToString(enginCourant.getDateMES(), false);
				String age = enginCourant.getAge(false);
				String etat;
				if (enginCourant.getRenumerotation().compareTo("") != 0) {
					etat = enginCourant.getRenumerotation() + " " + enginCourant.getEtat().getName();
				} else {
					etat = enginCourant.getEtat().getName();
				}
				String livree = enginCourant.getNomLivree();
				String depot = enginCourant.getNomDepot();
				String[] ligne = { numero, miseEnService, etat, age, livree, depot };
				modeleSerie.addRow(ligne);
			}
		}
		tableau.setModel(modeleSerie);
		tableau.setDefaultRenderer(Object.class, new SerieRenderer(s));
		dimensionnerTableau(s);
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR UN PARC D'ENGINS MOTEURS.
	 * 
	 * @param p
	 *            Parc d'engins moteurs à afficher, de type 'Parc'.
	 * @return Aucun.
	 */
	public void update(Parc p) {
		supprimerTableau();
		if (p != null) {
			listeSeries = p.getSeries();
			mode = 3;
			Iterator<Serie> j = listeSeries.iterator();
			while (j.hasNext()) {
				Serie serieCourante = j.next();
				String nom = serieCourante.getNomComplet();
				String type = serieCourante.getTypeString();
				String effectif = Integer.toString(serieCourante.getEffectifActuel());
				String enAttente = Integer.toString(serieCourante.getEnAttente());
				String enService = Integer.toString(serieCourante.getEnService());
				String radies = Integer.toString(serieCourante.getRadies());
				String preserves = Integer.toString(serieCourante.getPreserves());
				String vues = Integer.toString(serieCourante.getNbEnginsVus());
				String[] ligne = { nom, type, effectif, enAttente, enService, radies, preserves, vues };
				modeleParc.addRow(ligne);
			}
		}
		tableau.setModel(modeleParc);
		tableau.setDefaultRenderer(Object.class, new ParcRenderer(listeSeries));
		dimensionnerTableau(p);
	}

	/**
	 * DEFINIT L'AFFICHAGE POUR LES ANNIVERSAIRES DE MISE EN SERVICE
	 * 
	 * @param listeAnniv
	 *            Liste d'engins moteurs dont c'est l'anniversaire de mise en
	 *            service, de type Vector<Engin>.
	 * @return Aucun.
	 */
	public void update(Vector<Engin> listeAnniv) {
		supprimerTableau();
		if (listeAnniv != null) {
			listeEngins = listeAnniv;
			mode = 2;
			Iterator<Engin> j = listeEngins.iterator();
			while (j.hasNext()) {
				Engin enginCourant = j.next();
				String numero = enginCourant.getNomComplet();
				String miseEnService = Interface.dateToString(enginCourant.getDateMES(), false);
				// Age absolu quelque-soit l'état
				Period ageAbsolu = Period.between(enginCourant.getDateMES(), LocalDate.now());
				double ageFloat = ageAbsolu.getYears() + ageAbsolu.getMonths() / 12.0 + ageAbsolu.getDays() / 365.0;
				ageFloat = Math.floor(ageFloat * 100) / 100.0;
				String age = Double.toString(ageFloat);
				if (enginCourant.getEtat() == Etat.R) {
					age = "(" + age + ")";
				}
				String etat = enginCourant.getEtat().getName();
				String livree = enginCourant.getNomLivree();
				String depot = enginCourant.getNomDepot();
				String[] ligne = { numero, miseEnService, etat, age, livree, depot };
				modeleSerie.addRow(ligne);
			}
		}
		tableau.setModel(modeleSerie);
		tableau.setDefaultRenderer(Object.class, new SerieRenderer(listeAnniv));
		dimensionnerTableau(new Serie());
	}

	/**
	 * DEFINIT LES COULEURS POUR UNE SERIE D'ENGINS MOTEURS.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public class SerieRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		private Serie serie;
		private Vector<Engin> listeAnniv;

		public SerieRenderer(Serie pSerie) {
			serie = pSerie;
			listeAnniv = null;
		}

		public SerieRenderer(Vector<Engin> pListeAnniv) {
			listeAnniv = pListeAnniv;
			serie = null;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			Engin enginCourant = new Engin();
			if (serie != null) {
				String numero = table.getValueAt(row, 0).toString();
				enginCourant = serie.rechercherEngin(numero, Parc.dossierParc);
			} else {
				enginCourant = listeAnniv.elementAt(row);
			}
			// Ligne sur fond jaune si l'engin a été vu
			if (enginCourant.getNbVues() > 0) {
				setBackground(new Color(255, 246, 143));
			} else {
				setBackground(Color.white);
			}
			// Ecriture de couleur en fonction de l'état
			if ((column == 0) || (column == 2) || (column == 3)) {
				if (enginCourant.getEtat() == Etat.R) {
					setForeground(Color.red);
				} else {
					setForeground(new Color(100, 200, 0));
				}
			} else {
				setForeground(Color.black);
			}
			// Coloration des dépôts
			if (column == 5) {
				setForeground(enginCourant.getDepot().getColor());
			}
			return this;
		}
	}

	/**
	 * DEFINIT LES COULEURS POUR UN PARC D'ENGINS MOTEURS.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public class ParcRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		private Vector<Serie> listeSeries;

		public ParcRenderer(Vector<Serie> pListeSeries) {
			listeSeries = pListeSeries;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			Serie serieCourante = listeSeries.elementAt(row);
			setForeground(Color.black);
			// Ligne sur fond jaune si la série n'est pas encore entièrement
			// enregistrée dans la base.
			if (serieCourante.getEffectifActuel() < serieCourante.getEffectif()) {
				setBackground(new Color(255, 246, 143));
			} else {
				setBackground(Color.white);
			}
			switch (column) {
			case 0:
				// Couleur de la série en fonction du type
				setForeground(serieCourante.getType().getColor());
				break;
			case 3:
				setForeground(new Color(100, 200, 0));
				break;
			case 4:
				setForeground(new Color(100, 200, 0));
				break;
			case 5:
				setForeground(Color.red);
				break;
			case 6:
				setForeground(Color.orange);
				break;
			case 7:
				setBackground(new Color(255, 246, 143));
				break;
			default:
				setForeground(Color.black);
				break;
			}
			return this;
		}
	}

	/**
	 * DEFINIT LES COULEURS POUR LES VUES D'UN ENGIN MOTEUR.
	 * 
	 * @param Aucun.
	 * @return Aucun.
	 */
	public class VueRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		private Vector<Vue> listeVues;

		public VueRenderer(Vector<Vue> pListeVues) {
			listeVues = pListeVues;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			// Ligne sur fond jaune si une photo ou une vidéo est associée à la
			// vue
			Vue vueCourante = listeVues.elementAt(row);
			setForeground(Color.black);
			if ((vueCourante.getPhoto() == true) || (vueCourante.getVideo() == true)) {
				setBackground(new Color(255, 246, 143));
			} else {
				setBackground(Color.white);
			}
			// Photo
			if (column == 4) {
				if (vueCourante.getPhoto() == true) {
					setBackground(new Color(127, 255, 0));
					setForeground(new Color(127, 255, 0));
				} else {
					setBackground(new Color(255, 114, 86));
					setForeground(new Color(255, 114, 86));
				}
			}
			// Vidéo
			if (column == 5) {
				if (vueCourante.getVideo() == true) {
					setBackground(new Color(127, 255, 0));
					setForeground(new Color(127, 255, 0));
				} else {
					setBackground(new Color(255, 114, 86));
					setForeground(new Color(255, 114, 86));
				}
			}
			// Cabine
			if (column == 6) {
				if (vueCourante.getCabine() == true) {
					setBackground(new Color(127, 255, 0));
					setForeground(new Color(127, 255, 0));
				} else {
					setBackground(new Color(255, 114, 86));
					setForeground(new Color(255, 114, 86));
				}
			}
			return this;
		}
	}
}
