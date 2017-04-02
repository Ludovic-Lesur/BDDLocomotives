/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 26/08/2016
 */


package graphic ;

import data.* ;
import typedef.* ;
import java.awt.* ;
import java.awt.event.* ;
import java.time.* ;
import javax.swing.* ;


public class FenetreRemarque extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L ;
	
	// Lien avec les autres classes graphiques.
	private JFrame fenetre ;
	private JPanel panel ;
	private GridBagConstraints gbc ;
	private Interface i ;
	private Engin engin ;
	private Remarque oldRemarque ;
	private Remarque newRemarque ;
	private boolean modif ; 
	
	// Elements graphiques et attributs.
	private JLabel date ;
	private JComboBox<String> choixJour ;
	private int numJour = 1 ;
	private JComboBox<String> choixMois ;
	private int numMois = 1 ;
	private JComboBox<String> choixAnnee ;
	private int numAnnee = 1 ;
	private LocalDate dateCourante ;
	
	private JLabel observation ;
	private JTextArea champObservation ;
	
	private JLabel espace ;
	
	private JButton ok ;
	private JButton annuler ;
	
	
	/**
		CONSTRUCTEUR DE LA CLASSE FENETREREMARQUE.
	    @param pI			Interface graphique mère, de type 'Interface'.
	    @param pModif		Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou en mode modification.
	    						'true' = modification d'une remarque existante.
	    						'false' = ajout d'une nouvelle remarque.
	    @param pRemarque	Objet 'Remarque' à modifier si modif = 'true'.
	    						'null' si modif = 'false'.
	    @param pEngin		Engin moteur dont on doit modifier la remarque, de type 'Engin'.
	    @return				Aucun.
	*/
	public FenetreRemarque(Interface pI, boolean pModif, Remarque pRemarque, Engin pEngin) {
		
		i = pI ;
		engin = pEngin ;
		modif = pModif ;
		oldRemarque = new Remarque() ;
		newRemarque = new Remarque() ;
		
		if (modif == true) {
			oldRemarque = new Remarque(pRemarque) ;
			newRemarque = new Remarque(pRemarque) ;
		}
		
		// Création de l'interface
		fenetre = new JFrame() ;
		if (modif == false) {
			fenetre.setTitle("Ajouter remarque" ) ;
		}
		else {
			fenetre.setTitle("Modifier remarque" ) ;
		}
		fenetre.setSize(380, 300) ;
		fenetre.setResizable(false) ;
		fenetre.setLocationRelativeTo(null) ;
				
		// Panel
		panel = new JPanel() ;
		panel.setBackground(Color.gray) ;
		panel.setLayout(new GridBagLayout()) ;
		gbc = new GridBagConstraints() ;
		gbc.anchor = GridBagConstraints.WEST ;
		gbc.fill = GridBagConstraints.HORIZONTAL ;
		gbc.insets = new Insets(10, 10, 10, 10) ;
		gbc.gridwidth = 1 ;
		gbc.gridheight = 1 ;
		
		gbc.gridx = 2 ;
		gbc.gridy = 3 ;
		ok = new JButton() ;
		if (modif == false) {
			ok.setText("Ajouter") ;
		}
		else {
			ok.setText("Enregistrer") ;
		}
		ok.setFont(Interface.police) ;
		ok.setForeground(new Color(100, 200, 0)) ;
		ok.addActionListener(this) ;
		panel.add(ok, gbc) ;
				
		gbc.gridx = 0 ;
		gbc.gridy = 0 ;
		date = new JLabel("Date") ;
		date.setFont(Interface.police) ;
		date.setForeground(Color.yellow) ;
		panel.add(date, gbc) ;
				
		gbc.gridx = 1 ;
		gbc.gridy = 0 ;
		choixJour = new JComboBox<String>(Jour.getNumJours()) ; 
		choixJour.setFont(Interface.police) ;
		choixJour.addItemListener(new ItemState()) ;
		choixJour.setMaximumRowCount(Interface.COMBOBOX_HEIGHT) ;
		panel.add(choixJour, gbc) ;
		numJour = LocalDate.now().getDayOfMonth() ;
				
		gbc.gridx = 2 ;
		gbc.gridy = 0 ;
		choixMois = new JComboBox<String>(Mois.getFrenchNames()) ; 
		choixMois.setFont(Interface.police) ;
		choixMois.addItemListener(new ItemState()) ;
		choixMois.setMaximumRowCount(Interface.COMBOBOX_HEIGHT) ;
		panel.add(choixMois, gbc) ;
		numMois = LocalDate.now().getMonthValue() ;
				
		gbc.gridx = 3 ;
		gbc.gridy = 0 ;
		choixAnnee = new JComboBox<String>(Annee.getAnneesRemarque()) ;
		choixAnnee.setFont(Interface.police) ;
		choixAnnee.addItemListener(new ItemState()) ;
		choixAnnee.setMaximumRowCount(Interface.COMBOBOX_HEIGHT) ;
		panel.add(choixAnnee, gbc) ;
		numAnnee = LocalDate.now().getYear() ;
		selectDate(modif, oldRemarque) ;
				
		gbc.gridx = 0 ;
		gbc.gridy = 1 ;
		observation = new JLabel("Observation") ;
		observation.setFont(Interface.police) ;
		observation.setForeground(Color.yellow) ;
		panel.add(observation, gbc) ;
		
		gbc.gridwidth = 3 ;
		gbc.gridx = 1 ;
		gbc.gridy = 1 ;
		champObservation = new JTextArea() ;
		champObservation.setFont(Interface.police) ;
		champObservation.setLineWrap(true) ;
		fillField(modif, oldRemarque) ;
		JScrollPane scroll = new JScrollPane(champObservation, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
		
		scroll.setPreferredSize(new Dimension(200, 100)) ;
		panel.add(scroll, gbc) ;
		
		gbc.gridwidth = 1 ;
		gbc.gridx = 0 ;
		gbc.gridy = 2 ;
		espace = new JLabel("espace") ;
		espace.setFont(Interface.police) ;
		espace.setForeground(Color.gray) ;
		panel.add(espace, gbc) ;
		
		gbc.gridx = 3 ;
		gbc.gridy = 3 ;
		annuler = new JButton("Annuler") ;
		annuler.setFont(Interface.police) ;
		annuler.setForeground(Color.red) ;
		annuler.addActionListener(this) ;
		panel.add(annuler, gbc) ;
		
		// Affichage de la fenêtre
		fenetre.setContentPane(panel) ;
		fenetre.setVisible(true) ;
	}
	
	
	/**
		SELECTIONNE L'ITEM CORRECT EN FONCTION DE LA DATE ACTUELLE.
	    @param r		Objet 'Remarque' à modifier si modif = 'true'.
		    				'null' si modif = 'false'.
	    @param mode		Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou en mode modification.
		    				'true' = modification d'une remarque existante.
	    					'false' = ajout d'une nouvelle remarque.
	    @return			Aucun.
	*/
	private void selectDate(boolean mode, Remarque r) {
		if (mode == false) {
			// Ajout -> affichage de la date d'aujourd'hui
			dateCourante = LocalDate.now() ;
		}
		else {
			// Modification -> affichage de la date actuelle de la remarque
			dateCourante = r.getDate() ;
		}
		numJour = dateCourante.getDayOfMonth() ;
		numMois = dateCourante.getMonthValue() ;
		numAnnee = dateCourante.getYear() ;
		choixJour.setSelectedIndex(dateCourante.getDayOfMonth()-1) ;
		choixMois.setSelectedIndex(dateCourante.getMonth().getValue()-1) ;
		choixAnnee.setSelectedIndex(dateCourante.getYear()-Annee.oldestRemark) ;
	}
	
	
	/**
		REMPLIT LE CHAMP DE TEXTE DE LA REMARQUE SI ON ES EN MODE MODIFICATION.
	    @param r		Objet 'Remarque' à modifier si modif = 'true'.
		    				'null' si modif = 'false'.
	    @param mode		Booléen indiquant si la fenêtre doit s'ouvrir en mode ajout ou en mode modification.
		    				'true' = modification d'une remarque existante.
	    					'false' = ajout d'une nouvelle remarque.
	    @return			Aucun.
	*/
	private void fillField(boolean modif, Remarque r) {
		if (modif == true) {
			champObservation.setText(r.getTexte()) ;
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DES LISTES DEROULANTES.
	    @param	Aucun.
	    @return	Aucun.
	*/
	class ItemState implements ItemListener {
					
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == choixJour) {
				numJour = choixJour.getSelectedIndex()+1 ;
				checkDate() ;
				
			}
			if (e.getSource() == choixMois) {
				numMois = choixMois.getSelectedIndex()+1 ;
				checkDate() ;
			}
			if (e.getSource() == choixAnnee) {
				numAnnee = Serie.stringToInteger(choixAnnee.getSelectedItem().toString()) ;
				checkDate() ;
			}
		}
	}
	
	
	/**
		VERIFIE SI LA DATE SELECTIONNEE EXISTE.
	    @param	Aucun.
	    @return	Aucun.
	*/
	private void checkDate() {
		try {
			dateCourante = LocalDate.of(numAnnee, numMois, numJour) ;
			ok.setEnabled(true) ;
		}
		catch(DateTimeException timeEx) {
			ok.setEnabled(false) ;
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DES BOUTONS.
	    @param e	Evènement déclenché par l'appui sur un bouton.
	    @return		Aucun.
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			newRemarque.setDate(dateCourante) ;
			newRemarque.setTexte(champObservation.getText()) ;
			if (modif == false) {
				engin.getSerie().addXML(Integer.toString(engin.getNumero()), newRemarque) ;
			}
			else {
				engin.getSerie().modifyXML(Integer.toString(engin.getNumero()), oldRemarque, newRemarque) ;
			}
			
			Engin enginUpdated = engin.getSerie().rechercherEngin(engin.getNumero(), Parc.dossierParc) ;
			i.afficherResultat(enginUpdated, true) ;
			
			fenetre.setVisible(false) ;
			fenetre.dispose() ;	
		}
		if (e.getSource() == annuler) {
			fenetre.setVisible(false) ;
			fenetre.dispose() ;
		}
	}
}
