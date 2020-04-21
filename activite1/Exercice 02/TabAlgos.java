/**
 * Implémenter de méthodes un tableau d'entiers.
 * Le but étant de fournir un code bien fait,
 * respectant les règles d'écriture et accompagné,
 * suivi d'un jeu de tests unitaires permettant de
 * valider qu'il fourni bien le résultat attendu.
 */

package activite.exercice02;

/**
 * Dans cette classe, nous allons implémenter
 * des algorithmes sur un tableau d'entiers.
 * @author LEUMASSI FANSI Jean-Léopold
 */
public final class TabAlgos {
  /**
   * Constructeur protected avec exception
   * pour empecher l'instantiation de la classe.
   * @throws Exception cette classe ne peut pas être instanciée.
   */
  protected TabAlgos() throws Exception {
    throw new Exception("cette classe ne peut pas être instanciée");
  }

  /**
   * Trouver valeur Max d'un tableau.
   * @param tab est un tableau d'entier.
   * @return valeur la plus grande d'un tableau.
   * @throw IllegalArgumentException si tab et null ou vide.
   */
  public static int plusGrand(final int[] tab) {
    //Cas d'un tableau null
    if (tab == null) {
      throw new IllegalArgumentException("le tableau ne doit pas être null.");
    }

    //Cas d'un tableau vide. La taille du tableau est égale à zero.
    if (tab.length == 0) {
      throw new IllegalArgumentException("le tableau ne doit pas être null.");
    }

    //on affecte la plus petite valeur entière possible à notre variable.
    int valeurMax = Integer.MIN_VALUE;

    //En parcourant le tableau, si une valeur supérieur à valeurMax est trouvée,
    //alors valeurMax prend cette valeur.
    for (int i = 0; i < tab.length; i++) {
      if (tab[i] > valeurMax) {
        valeurMax = tab[i];
      }
    }
    return valeurMax;
  }

  /**
   * Retourne la moyenne du tableau.
   * @param tab est un tableau d'entier.
   * @return moyenne des valeurs du tableau.
   * @throw IllegalArgumentException si tab et null ou vide.
   **/
  public static double moyenne(final int[] tab) {
    double somme = 0.0;
    //Cas d'un tableau null
    if (tab == null) {
      throw new IllegalArgumentException("le tableau ne doit pas être null.");
    }

    //Cas d'un tableau vide. La taille du tableau est égale à zero.
    if (tab.length == 0) {
      throw new IllegalArgumentException("le tableau ne doit pas être null.");
    }

    for (int i = 0; i < tab.length; i++) {
      somme += tab[i]; //on procède à la somme des éléments du tableau.
    }
    return (somme / tab.length);
  }

  /**
   * Compare le contenu de 2 tableaux en tenant compte de l'ordre.
   * @param tab1 est un tableau d'entiers.
   * @param tab2 est un tableau d'entiers.
   * @return true si les 2 tableaux contiennent les mêmes éléments
   *         avec les mêmes nombres d'occurences
   *         (avec les elements dans le meme ordre).
   **/
  public static boolean egaux(final int[] tab1, final int[] tab2) {
    //Si les deux tableaux sont de même taille
    if (tab1.length == tab2.length) {
      for (int i = 0; i < tab1.length; i++) {
        if (tab1[i] != tab2[i]) {
          //Si deux éléments de même index sont différents
          //alors les tableaux ne sont pas égaux.
          return false;
        }
      }
      return true;
    } else {
      //si les deux tabeaux ne sont pas de même taille.
      return false;
    }
  }

  /**
   * Compare le contenu de 2 tableaux sans tenir compte de l'ordre.
   * @param tab1 est un tableau d'entiers.
   * @param tab2 est un tableau d'entiers.
   * @return true si les 2 tableaux contiennent les mêmes éléments
   *         avec les mêmes nombres d'occurrence
   *         (pas forcément dans le meme ordre).
   **/
  public static boolean similaires(final int[] tab1, final int[] tab2) {
    //si les tableaux sont de la même taille
    if (tab1.length == tab2.length) {
      int[] temp1 = triABulles(tab1);
      int[] temp2 = triABulles(tab2);
      return egaux(temp1, temp2);
    } else {
      //si les deux tabeaux ne sont pas de même taille.
      return false;
    }
  }

  /**
   * Compare le contenu de 2 tableaux sans tenir compte de l'ordre.
   * @param tab est un tableau d'entier.
   * @return un tableau trié par ordre croissant.
   **/
  private static int[] triABulles(final int[] tab) {
    int temp;
    for (int i = tab.length - 1; i >= 1; i--) {
      for (int j = 0; j < i; j++) {
        if (tab[j] > tab[j + 1]) {
          temp = tab[j + 1];
          tab[j + 1] = tab[j];
          tab[ j ] = temp;
        }
      }
    }
    return tab;
  }
}
