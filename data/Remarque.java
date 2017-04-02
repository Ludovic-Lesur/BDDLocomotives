/**
 * Javadoc
 * 
 * @author Ludovic Lesur
 * @since 25/05/2016
 */


package data ;

import java.time.* ;


public class Remarque implements Comparable<Remarque> {
	
	// Attributs
	private LocalDate date ;
	private String texte ;
	
	
	/**
		CONSTRUCTEUR DE LA CLASSE REMARQUE.
	    @param	Aucun.
	    @return	Aucun.
	*/
	public Remarque() {
		date = LocalDate.of(0, 1, 1) ;
		texte = null ;
	}
	
	
	/**
		CONSTRUCTEUR DE RECOPIE DE LA CLASSE PARC.
		UTILISEE POUR SAUVEGARDER UNE REMARQUE AVANT MODIFICATION DANS LA CLASSE 'FenetreRemarque'.
	    @param r	Remarque à recopier, de type 'Remarque'.
	    @return		Aucun.
	*/
	public Remarque(Remarque r) {
		date = r.getDate() ;
		texte = r.getTexte() ;
	}
	
	
	/**
		RETOURNE LA DATE DE LA REMARQUE.
	    @param			Aucun.
	    @return	date	Date de la remarque de type 'LocalDate'.
	*/
	public LocalDate getDate() {
		return date ;
	}
	
	
	/**
		MODIFIE LA DATE DE LA REMARQUE.
	    @param newDate	Nouvelle date de la remarque de type 'LocalDate'.
	    @return			Aucun.
	*/
	public void setDate(LocalDate newDate) {
		date = newDate ;
	}
	
	
	/**
		RETOURNE LE TEXTE DE LA REMARQUE.
	    @param			Aucun.
	    @return	texte	Texte de la remarque de type 'String'.
	*/
	public String getTexte() {
		return texte ;
	}
	
	
	/**
		MODIFIE LE TEXTE DE LA REMARQUE.
	    @param newTexte		Nouveau texte de la remarque de type 'String'.
	    @return				Aucun.
	*/
	public void setTexte(String newTexte) {
		texte = newTexte ;
	}
	
	
	/**
		PERMET DE SAVOIR SI DEUX OBJETS 'REMARQUE' SONT IDENTIQUES.
	    @param r			Remarque à comparer avec 'this', de type 'Remarque'.
	    @return	identique	'true' si les deux objets sont identiques.
	    					'false' sinon.
	*/
	public boolean equals(Remarque r) {
		boolean identique = true ;
		if (date.equals(r.getDate()) == false) {
			identique = false ;
		}
		else {
			if (texte.compareTo(r.getTexte()) != 0) {
				identique = false ;
			}
		}
		return identique ;
	}
	
	
	/**
		COMPARE LA DATE DE DEUX REMARQUES.
	    @param r			Remarque dont la date est à comparer avec celle de 'this', de type 'Remarque'.
	    @return	resultat	-1 si 'this' est avant 'r'.
							0 si 'this' est à la même date que 'r'.
							1 si 'this' est après 'r'.
	*/
	public int compareTo(Remarque r) {
		int resultat ;
	    if (date.isAfter(r.getDate())) {
	       	resultat = 1 ;
	    }
	    else {
	        if (date.isBefore(r.getDate())) {
	        	resultat = -1 ;
	        }
	        else {
	        	resultat = 0 ;
	        }
	    }
	    return resultat ;
	}
}