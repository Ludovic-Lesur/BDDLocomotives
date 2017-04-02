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


public class FenetreLivree extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L ;

	// Lien avec les autres classes graphiques
	private JPanel panel ;
	private GridBagConstraints gbc ;
	private Interface i ;
	
	// Elements graphiques et attributs
	private JFrame fenetre ;
	private Livree livreeCourante ;
	private Engin engin ;
	private JLabel livree ;
	private JComboBox<String> choixLivree ;
	private JLabel espace ;
	private JButton ok ;
	private JButton annuler ;
	private boolean remarque ;
	
	
	/**
		CONSTRUCTEUR DE LA CLASSE FENETREETAT.
	    @param pI			Interface graphique mère, de type 'Interface'.
	    @param pEngin		Engin moteur dont on doit modifier la livrée, de type 'Engin'.
	    @param pRemarque	Booléen indiquant le type de vue actuellement sélectionné.
	    						'true' = affichage des remarques.
	    						'false' = affichage des vues.
	    @return				Aucun.
	*/
	public FenetreLivree(Interface pI, Engin pEngin, boolean pRemarque) {
		
		i = pI ;
		engin = pEngin ;
		remarque = pRemarque ;
		
		// Création de l'interface
		fenetre = new JFrame() ;
		fenetre.setTitle("Modifier livrée" ) ;
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
		livree = new JLabel("Livrée") ;
		livree.setFont(Interface.police) ;
		livree.setForeground(Color.yellow) ;
		panel.add(livree, gbc) ;
		
		gbc.gridwidth = 3 ;
		gbc.gridx = 1 ;
		gbc.gridy = 0 ;
		choixLivree = new JComboBox<String>(Livree.getNames()) ; 
		choixLivree.setFont(Interface.police) ;
		choixLivree.addItemListener(new ItemState()) ;
		choixLivree.setMaximumRowCount(Interface.COMBOBOX_HEIGHT) ;
		selectLivree(engin) ;
		panel.add(choixLivree, gbc) ;
		
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
		
		// Affichage de la fenêtre
		fenetre.setContentPane(panel) ;
		fenetre.setVisible(true) ;
	}
	
	
	/**
		SELECTIONNE L'ITEM CORRECT EN FONCTION DE LA LIVREE ACTUELLE.
	    @param e	Engin moteur dont on doit modifier la livrée, de type 'Engin'.
	    @return		Aucun.
	*/
	private void selectLivree(Engin engin) {
		int i = 0 ;
		livreeCourante = Livree.values()[1] ;
		for (i=0 ; i<choixLivree.getItemCount() ; i++) {
			if (choixLivree.getItemAt(i).compareTo(engin.getNomLivree()) == 0) {
				choixLivree.setSelectedIndex(i) ;
				livreeCourante = engin.getLivree() ;
				break ;
			}
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DES LISTES DEROULANTES.
	    @param	Aucun.
	    @return	Aucun.
	*/
	class ItemState implements ItemListener {
					
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == choixLivree) {
				livreeCourante = Livree.values()[choixLivree.getSelectedIndex()+1] ; // +1 car "INC" n'est pas dans la liste
			}
		}
	}
	
	
	/**
		DEFINIT LES ACTIONS DES BOUTONS.
	    @param e	Evènement déclenché par l'appui sur un bouton.
	    @return		Aucun.
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			engin.getSerie().modifyXML(Integer.toString(engin.getNumero()), livreeCourante) ;
			
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