/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 19/08/2016
 */


package graphic ;

import typedef.* ;
import data.* ;
import javax.swing.* ;
import javax.swing.event.* ;
import java.awt.* ;
import java.awt.event.* ;


public class RechercheEngin extends JFrame implements ActionListener, DocumentListener {
	
	private static final long serialVersionUID = 1L ;
	
	// Lien avec les autres classes graphiques.
	private Interface i ;
	private Parc p ;
	private Engin enginCourant ;
	private JPanel panel ;
	private GridBagConstraints gbc ;
	
	// Eléments graphiques.
	private JLabel titre ;
	private JComboBox<String> choixIdentifiant ;
	private Identifiant identifiantCourant ;
	private JTextField champRecherche ;
	
	private JButton rechercher ;
	private JButton precedent ;
	private JButton suivant ;
	private JLabel resultat ;
	private JLabel espace ;
	private JLabel affichage ;
	
	private JRadioButton affVues ;
	private JRadioButton affRemarques ;
	private ButtonGroup choixAff ;
	
	private boolean remarque ;
	private boolean enable ;
	
	
	/**
		CONSTRUCTEUR DE LA CLASSE RECHERCHERENGIN.
	    @param pI			Interface graphique mère, de type 'Interface'.
	    @param mainPanel	Panel de l'interface graphique mère, de type 'JPanel'.
	    @param mainGbc		Contraintes de l'interface graphique mère, de type 'GridBagConstraints'.
	    @param parcEngins	Parc d'engins moteurs dans lequel doit s'effectuer les recherches, de type 'Parc'.
	    @return				Aucun.
	*/
	public RechercheEngin(Interface pI, JPanel mainPanel, GridBagConstraints mainGbc, Parc parcEngins) {
		
		i = pI ;
		p = parcEngins ;
		enginCourant = new Engin() ;
		
		mainGbc.gridx = 2 ;
		mainGbc.gridy = 1 ;
		
		panel = new JPanel(new GridBagLayout()) ;
		panel.setBackground(Color.gray) ;
		gbc = new GridBagConstraints() ;
		gbc.insets = new Insets(15, 15, 15, 15) ;
		gbc.anchor = GridBagConstraints.CENTER ;
		gbc.fill = GridBagConstraints.CENTER ;
		
		gbc.gridwidth = 4 ;
		gbc.gridx = 0 ;
		gbc.gridy = 0 ;
		titre = new JLabel("ENGINS  MOTEURS") ;
		titre.setFont(new Font(Interface.police.getFontName(), 1, 13)) ;
		titre.setForeground(Color.white) ;
		panel.add(titre, gbc) ;
		
		gbc.anchor = GridBagConstraints.WEST ;
		gbc.fill = GridBagConstraints.HORIZONTAL ;
		gbc.gridwidth = 1 ;
		gbc.gridx = 4 ;
		gbc.gridy = 0 ;
		espace = new JLabel("espace") ;
		espace.setFont(Interface.police) ;
		espace.setForeground(Color.gray) ;
		panel.add(espace, gbc) ;
		
		gbc.gridwidth = 1 ;
		gbc.gridx = 5 ;
		gbc.gridy = 0 ;
		affichage = new JLabel("AFFICHAGE") ;
		affichage.setFont(new Font(Interface.police.getFontName(), 1, 13)) ;
		affichage.setForeground(Color.white) ;
		panel.add(affichage, gbc) ;
		
		gbc.insets.bottom = 2 ;
		
		gbc.gridx = 0 ;
		gbc.gridy = 1 ;
		choixIdentifiant = new JComboBox<String>(Identifiant.getNames()) ;
		choixIdentifiant.setFont(Interface.police) ;
		choixIdentifiant.addItemListener(new ItemState()) ;
		panel.add(choixIdentifiant, gbc) ;
		identifiantCourant = Identifiant.values()[choixIdentifiant.getSelectedIndex()+1] ; // +1 car "Vide" n'est pas dans la liste.
		
		gbc.gridx = 1 ;
		gbc.gridy = 1 ;
		champRecherche = new JTextField(10) ;
		champRecherche.setFont(Interface.police) ;
		champRecherche.setEditable(true) ;
		champRecherche.getDocument().addDocumentListener(this) ;
		panel.add(champRecherche, gbc) ;
		
		gbc.gridwidth = 2 ;
		gbc.gridx = 2 ;
		gbc.gridy = 1 ;
		rechercher = new JButton("Rechercher") ;
		rechercher.setFont(Interface.police) ;
		rechercher.addActionListener(this) ;
		rechercher.setEnabled(false) ;
		panel.add(rechercher, gbc) ;
		
		gbc.gridwidth = 1 ;
		gbc.gridx = 5 ;
		gbc.gridy = 1 ;
		affVues = new JRadioButton("Vues") ;
		affVues.setFont(Interface.police) ;
		affVues.setOpaque(false) ;
		affVues.setForeground(Color.white) ;
		affVues.setSelected(true) ;
		affVues.addActionListener(this) ;
		panel.add(affVues, gbc) ;
	
		gbc.insets.top = 2 ;	
		gbc.gridwidth = 1 ;
		gbc.gridx = 1 ;
		gbc.gridy = 2 ;
		resultat = new JLabel("Aucun résultat.") ;
		resultat.setFont(Interface.police) ;
		resultat.setForeground(Color.orange) ;
		panel.add(resultat, gbc) ;
		
		gbc.gridx = 2 ;
		gbc.gridy = 2 ;
		precedent = new JButton("<") ;
		precedent.setFont(Interface.police) ;
		precedent.addActionListener(this) ;
		precedent.setEnabled(false) ;
		panel.add(precedent, gbc) ;
		
		gbc.gridx = 3 ;
		gbc.gridy = 2 ;
		suivant = new JButton(">") ;
		suivant.setFont(Interface.police) ;
		suivant.addActionListener(this) ;
		suivant.setEnabled(false) ;
		panel.add(suivant, gbc) ;
		
		gbc.gridx = 5 ;
		gbc.gridy = 2 ;
		affRemarques = new JRadioButton("Remarques") ;
		affRemarques.setFont(Interface.police) ;
		affRemarques.setOpaque(false) ;
		affRemarques.setForeground(Color.white) ;
		affRemarques.setSelected(true) ;
		affRemarques.addActionListener(this) ;
		panel.add(affRemarques, gbc) ;
		
		choixAff = new ButtonGroup() ;
		choixAff.add(affVues) ;
		choixAff.add(affRemarques) ;
		remarque = false ;
		enable = true ;
		
		mainPanel.add(panel, mainGbc) ;
	}
	
	
	/**
		RENVOIE L'ENGIN MOTEUR CHERCHE.
	    @param					Aucun.
	    @return	enginTrouve		Engin moteur trouvé si la recherche a donné un résultat.
	    						'null' sinon.
	*/
	private Engin chercher() {
		Engin enginTrouve = null ;
		int numCherche = Serie.stringToInteger(champRecherche.getText()) ;
		enginTrouve = p.rechercherEngin(identifiantCourant, numCherche) ;
		if (enginTrouve == null) {
			resultat.setText("Aucun résultat.") ;
			resultat.setForeground(Color.orange) ;
		}
		else {
			resultat.setText("1 résultat.") ;
			resultat.setForeground(Color.green) ;
		}
		return enginTrouve ;
	}
	
	
	/**
		DEFINIT L'ENGIN MOTEUR COURANT.
	    @param newEngin		Engin moteur courant de type 'Engin'.
	    @return				Aucun.
	*/
	public void setEnginCourant(Engin newEngin) {
		if (newEngin != null) {
			enginCourant = newEngin ;
			// Sélection de l'identifiant.
			String identifiantCourant = enginCourant.getSerie().getIdentifiant().getName() ;
			String[] listeIdentifiants = Identifiant.getNames() ;
			int k = 0 ;
			for (k=0 ; k<listeIdentifiants.length ; k++) {
				if (listeIdentifiants[k].compareTo(identifiantCourant) == 0) {
					break ;
				}
			}
			choixIdentifiant.setSelectedIndex(k) ;
			// Sélection du numéro.
			champRecherche.setText(Integer.toString(enginCourant.getNumero())) ;
			chercher() ;
		}
	}
	
	
	/**
		ACTIVE LE CHOIX ENTRE VUES ET REMARQUES.
	    @param 	Aucun.
	    @return	Aucun.
	*/
	public void setEnableTrue() {
		enable = true ;
	}
	
	
	/**
		DESACTIVE LE CHOIX ENTRE VUES ET REMARQUES.
	    @param 	Aucun.
	    @return	Aucun.
	*/
	public void setEnableFalse() {
		enable = false ;
	}
	
	
	/**
		VERIFIE LE TEXTE ENTRE DANS LE CHAMP DE RECHERCHE.
	    @param 	Aucun.
	    @return	Aucun.
	*/
	private void checkNumero() {
		int test = Serie.stringToInteger(champRecherche.getText()) ;
		if (test == 0) {
			rechercher.setEnabled(false) ;
			suivant.setEnabled(false) ;
			precedent.setEnabled(false) ;
		}
		else {
			rechercher.setEnabled(true) ;
			Engin eC = p.rechercherEngin(identifiantCourant, test) ;
			if (eC != null) {
				if (eC.getIndice() < eC.getSerie().getEffectif()) {
					suivant.setEnabled(true) ;
				}
				if (eC.getIndice() > 1) {
					precedent.setEnabled(true) ;
				}
			}
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DE LA LISTE DEROULANTE.
	    @param	Aucun.
	    @return	Aucun.
	*/
	class ItemState implements ItemListener {
						
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == choixIdentifiant) {		
				identifiantCourant = Identifiant.values()[choixIdentifiant.getSelectedIndex()+1] ; // +1 car "Vide" n'est pas dans la liste.
			}
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DES BOUTONS.
	    @param e	Evènement déclenché par l'appui sur un bouton.
	    @return		Aucun.
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rechercher) {
			i.afficherResultat(chercher(), remarque) ;
			enable = true ;
		}
		if (e.getSource() == suivant) {
			Engin eC = p.rechercherEngin(identifiantCourant, Serie.stringToInteger(champRecherche.getText())) ;
			Serie sC = eC.getSerie() ;
			int newNum = sC.rechercherNumero(eC.getIndice()+1) ;
			champRecherche.setText(Integer.toString(newNum)) ;
			i.afficherResultat(chercher(), remarque) ;
			enable = true ;
		}
		if (e.getSource() == precedent) {
			Engin eC = p.rechercherEngin(identifiantCourant, Serie.stringToInteger(champRecherche.getText())) ;
			Serie sC = eC.getSerie() ;
			int newNum = sC.rechercherNumero(eC.getIndice()-1) ;
			champRecherche.setText(Integer.toString(newNum)) ;
			i.afficherResultat(chercher(), remarque) ;
			enable = true ;
		}
		if (e.getSource() == affVues) {
			remarque = false ;
			if (enable == true) {
				i.afficherResultat(enginCourant, remarque) ;
			}
		}
		if (e.getSource() == affRemarques) {
			remarque = true ;
			if (enable == true) {
				i.afficherResultat(enginCourant, remarque) ;
			}
		}
	}

	
	/**
		FONCTIONS DE VERIFICATION DE SAISIE CLAVIER.
	    @param e	Evènement déclenché par une saisie clavier.
	    @return		Aucun.
	*/
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		checkNumero() ;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkNumero() ;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkNumero() ;
	}
}