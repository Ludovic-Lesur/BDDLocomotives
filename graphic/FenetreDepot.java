/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 27/08/2016
 */


package graphic ;

import typedef.* ;
import data.* ;
import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;


public class FenetreDepot extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L ;

	// Lien avec les autres classes graphiques
	private Interface i ;
	private JPanel panel ;
	private GridBagConstraints gbc ;
	
	// Elements graphiques et attributs
	private JFrame fenetre ;
	private boolean remarque ;
	private Depot depotCourant ;
	private Engin engin ;
	private JLabel depot ;
	private JComboBox<String> choixDepot ;
	private JLabel espace ;
	private JButton ok ;
	private JButton annuler ;
	
	
	/**
		CONSTRUCTEUR DE LA CLASSE FENETREDEPOT.
	    @param pI			Interface graphique m�re, de type 'Interface'.
	    @param pEngin		Engin moteur dont on doit modifier le d�p�t, de type 'Engin'.
	    @param pRemarque	Bool�en indiquant le type de vue actuellement s�lectionn�.
	    						'true' = affichage des remarques.
	    						'false' = affichage des vues.
	    @return				Aucun.
	*/
	public FenetreDepot(Interface pI, Engin pEngin, boolean pRemarque) {
		
		i = pI ;
		remarque = pRemarque ;
		engin = pEngin ;
		
		// Cr�ation de l'interface
		fenetre = new JFrame() ;
		fenetre.setTitle("Modifier d�p�t") ;
		fenetre.setSize(250, 200) ;
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
		
		gbc.gridx = 0 ;
		gbc.gridy = 0 ;
		depot = new JLabel("D�p�t") ;
		depot.setFont(Interface.police) ;
		depot.setForeground(Color.yellow) ;
		panel.add(depot, gbc) ;
		
		gbc.gridwidth = 3 ;
		gbc.gridx = 1 ;
		gbc.gridy = 0 ;
		choixDepot = new JComboBox<String>(Depot.getNames()) ; 
		choixDepot.setFont(Interface.police) ;
		choixDepot.addItemListener(new ItemState()) ;
		choixDepot.setMaximumRowCount(Interface.COMBOBOX_HEIGHT) ;
		selectDepot(engin) ;
		panel.add(choixDepot, gbc) ;
		
		gbc.gridwidth = 1 ;
		gbc.gridx = 0 ;
		gbc.gridy = 1 ;
		espace = new JLabel("espace") ;
		espace.setFont(Interface.police) ;
		espace.setForeground(Color.gray) ;
		panel.add(espace, gbc) ;
		
		gbc.gridwidth = 2 ;
		gbc.gridx = 0 ;
		gbc.gridy = 2 ;
		ok = new JButton() ;
		ok.setText("Enregistrer") ;
		ok.setFont(Interface.police) ;
		ok.setForeground(new Color(100, 200, 0)) ;
		ok.addActionListener(this) ;
		panel.add(ok, gbc) ;
		
		gbc.gridx = 2 ;
		gbc.gridy = 2 ;
		annuler = new JButton("Annuler") ;
		annuler.setFont(Interface.police) ;
		annuler.setForeground(Color.red) ;
		annuler.addActionListener(this) ;
		panel.add(annuler, gbc) ;
		
		// Affichage de la fen�tre
		fenetre.setContentPane(panel) ;
		fenetre.setVisible(true) ;
	}
	
	
	/**
		SELECTIONNE L'ITEM CORRECT EN FONCTION DU DEPOT ACTUEL.
	    @param e	Engin moteur dont on doit modifier le d�p�t, de type 'Engin'.
	    @return		Aucun.
	*/
	private void selectDepot(Engin e) {
		int i = 0 ;
		depotCourant = Depot.values()[1] ;
		for (i=0 ; i<choixDepot.getItemCount() ; i++) {
			if (choixDepot.getItemAt(i).compareTo(e.getNomDepot()) == 0) {
				choixDepot.setSelectedIndex(i) ;
				depotCourant = e.getDepot() ;
				break ;
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
			if (e.getSource() == choixDepot) {
				depotCourant = Depot.values()[choixDepot.getSelectedIndex()+1] ; // +1 car "INC" n'est pas dans la liste
			}
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DES BOUTONS.
	    @param e	Ev�nement d�clench� par l'appui sur un bouton.
	    @return		Aucun.
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			engin.getSerie().modifyXML(Integer.toString(engin.getNumero()), depotCourant) ;
			Engin enginUpdated = engin.getSerie().rechercherEngin(engin.getNumero(), Parc.dossierParc) ;
			i.afficherResultat(enginUpdated, remarque) ;
			fenetre.setVisible(false) ;
			fenetre.dispose() ;
		}
		if (e.getSource() == annuler) {
			fenetre.setVisible(false) ;
			fenetre.dispose() ;
		}
	}
}