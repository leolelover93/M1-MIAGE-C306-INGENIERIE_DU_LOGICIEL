package main.java;

/**
 * Une classe permettant d'effectuer certaines
 * operations de base sur les entiers.
 * @author Sebastien Choplin
 **/
public class Calcul {

  /**
   * La classe Calcul ne compte que des methodes
   * STATIC par consequent il s'agit d'une
   * classe utilitaire et ne peut etre
   * instancier.
   * @author LEUMASSI FANSI Jean-Leopold
   **/
  public Calcul() {
    throw new UnsupportedOperationException("Impossible "
      + "d'instancier une classe static.");
  }

  /**
   * Calcul la somme de deux nombres.
   * @author Sebastien Choplin
   * @param a est un entier.
   * @param b est un entier.
   * @return retourne la somme de a + b.
   **/
  public static int somme(final int a, final int b) {
    return a + b;
  }

  /**
   * Fonction personnalisee.
   * @author Sebastien Choplin
   * @param a est un entier.
   * @param b est un entier.
   * @return retourne a/b si b est superieur
   *         ou egale a 10. et b dans le cas
   *         contraire.
   **/
  public static int maFonction(final int a, final int b) {
    final int valeurDeTest = 10;
    if (b >= valeurDeTest) {
      return a / b;
    }
    return b;
  }

  /**
   * Fonction de division de deux entiers.
   * @author Sebastien Choplin
   * @param a est un entier.
   * @param b est un entier.
   * @return a / b si b != 0.
   * @throws IllegalArgumentException si b == 0
   **/
  public static int division(final int a, final int b) {
    if (b == 0) {
      throw new IllegalArgumentException("b ne doit pas etre 0");
    }
    return a / b;
  }
}
