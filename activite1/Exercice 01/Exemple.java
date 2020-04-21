/**
* Ma class d'exemple.
*/

package activite.exercice01;

public class Exemple {
  /**
   * Variable t qui est un attribut privé
   * de type chaîne de caractères.
   */
  private final String t;

  /**
   * Variable v qui est un attribut private de type nombre entier.
   */
  private final int v;

  /**
   * Contrusteur de la classe Exemple.
   * @param paramT chaîne de caractères.
   */
  public Exemple(final String paramT) {
    t = paramT;
  }

  /**
   * Accesseur de l'attribut "v".
   * @return la valeur de v
   */
  public int getV() {
    return this.v;
  }

  /**
   * Mutateur de l'attribut "v".
   * @param paramV de type int
   */
  public final void setV(final int paramV) {
    v = paramV;
  }

  /**
   * Accesseur de l'attribut "t".
   * @return t si v est positif
   */
  public final String getT() {
    if (v > 0) {
      return t;
    }
  }
}
