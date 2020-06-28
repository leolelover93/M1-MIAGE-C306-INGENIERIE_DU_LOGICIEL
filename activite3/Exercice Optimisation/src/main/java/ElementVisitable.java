/**
 *  Classe permettant de stocker les elements
 *  initialise de la grille (ligne, colonne,
 *  valeur).
 */

package main.java;

import java.util.Comparator;

/**
 *  Classe permettant de stocker les elements
 *  initialise de la grille (ligne, colonne,
 *  valeur).
 * @author LEUMASSI FANSI Jean-Leopold
 */
public class ElementVisitable {
  private int ligne; //Ligne de la case initialisee.
  private int colonne; //Colonne de la case initialisee.
  private int nombreValeurPossibles; //Nombre de valeurs possibles.

  /**
   * Constructeur par defaut de la classe.
   *
   * @param ligne la ligne de l'element
   * @param colonne la colonne de l'element
   * @param nombreValeurPossibles le nombre de valeurs possibles
   */
  public ElementVisitable(final int ligne, final int colonne,
      final int nombreValeurPossibles) {
    this.ligne = ligne;
    this.colonne = colonne;
    this.nombreValeurPossibles = nombreValeurPossibles;
  }

  /**
   * Accesseur permettant de retourner la ligne.
   * @return la ligne de sur laquelle se trouve
   *         l'element.
   */
  public int getLigne() {
    return ligne;
  }

  /**
   * Mutateur permettant de modifier la ligne de l'element.
   * @param ligne la nouvelle ligne
   */
  public void setLigne(int ligne) {
    this.ligne = ligne;
  }

  /**
   * Accesseur permettant de retourner la colonne.
   * @return la colonne de sur laquelle se trouve
   *         l'element.
   */
  public int getColonne() {
    return colonne;
  }

  /**
   * Mutateur permettant de modifier la colonne de l'element.
   * @param colonne la nouvelle valeur
   */
  public void setColonne(int colonne) {
    this.colonne = colonne;
  }

  /**
   * Accesseur permettant de retourner le nombre de valeurs possibles.
   * @return la valeur de l'element.
   */
  public int getNombreValeurPossibles() {
    return nombreValeurPossibles;
  }

  /**
   * Mutateur permettant de modifier la valeur de l'element.
   * @param nombreValeurPossibles la nouvelle valeur
   */
  public void setNombreValeurPossibles(int nombreValeurPossibles) {
    this.nombreValeurPossibles = nombreValeurPossibles;
  }


  public static Comparator<ElementVisitable> NombreValeurPossiblesComparator =
      new Comparator<ElementVisitable>() {

    public int compare(ElementVisitable e1, ElementVisitable e2) {
      int nombreValeurPossiblesComparator1 = e1.getNombreValeurPossibles();
      int nombreValeurPossiblesComparator2 = e2.getNombreValeurPossibles();

      //ascending order
      return nombreValeurPossiblesComparator1 - nombreValeurPossiblesComparator2;
      //descending order
      //return StudentName2.compareTo(StudentName1);
    }
  };

}
