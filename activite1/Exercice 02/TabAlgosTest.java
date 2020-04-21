/**
 * Dans cet exercice, nous allons implémenter des
 * tests unitaires pour la classe TabAlgos.
 * Le but étant de fournir un code respectant les
 * règles d'écriture permettant de valider que les
 * méthodes testées fourniront bien le résultat attendu.
 */

package activite.exercice02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;


/**
 * Dans cette classe, nous allons implémenter
 * des tests unitaires.
 * Ils nous permettront de tester les méthodes
 * écrites dans la classe TabAlgos.
 * @author LEUMASSI FANSI Jean-Léopold
 */
public class TabAlgosTest {

  /**
   * Constante ayant pour valeur 99.
   */
  private final int val1 = 99;

  /**
   * Constante ayant pour valeur 45.
   */
  private final int val2 = 45;

  /**
   * Constante ayant pour valeur 68.
   */
  private final int val3 = 68;

  /**
   * Constante ayant pour valeur 18.
   */
  private final int val4 = 18;

  /**
   * Constante ayant pour valeur 34.
   */
  private final int val5 = 34;

  /**
   * Constante ayant pour valeur 50.
   */
  private final int val6 = 50;

  /**
   * Constante ayant pour valeur 50.
   */
  private final int val7 = 37;

  /**
   * Tableau N° 01.
   */
  private final int[] tab1 = {val1, val2, val3, val4, val5, val6};

  /**
   * Tableau N° 02.
   */
  private final int[] tab2 = {val1, val2, val3, val4, val5, val6};

  /**
   * Tableau N° 03.
   */
  private final int[] tab3 = {val3, val2, val1, val4, val6, val5};

  /**
   * Tableau N° 04.
   */
  private final int[] tab4 = {val3, val4, val6, val5};

  /**
   * Tableau N° 05.
   */
  private final int[] tab5 = {val3, val2, val7, val4, val6, val5};

  /**
   * Tableau null.
   */
  private final int[] tabNull = null;

  /**
   * Tableau vide.
   */
  private final int[] tabVide = new int[0];

  /**
   * Constante ayant pour valeur 0,1.
   */
  private final double delta = 0.1;

  /**
   * Constante ayant pour valeur 41,3.
   */
  private final double moyenne = 52.33;

  /**
   * Test pour la méthode plusGrand. Cas nominal
   */
  @Test
  public void plusGrandTest() {
    assertEquals(val1, TabAlgos.plusGrand(tab1));
  }


  /**
   * Test de la méthode plusGrand avec tableau vide.
   */
  @Test
  public void plusGrandTestAvecParamVide() {
    try {
      TabAlgos.plusGrand(tabVide);
      fail("l'exception pour les tableaux vides aurait dû être levée.");
    } catch (IllegalArgumentException e) {
      // rien à faire il s'agit d'un comportement normal
    }
  }

  /**
   * Test de la méthode plusGrand avec tableau vide.
   */
  @Test
  public void plusGrandTestAvecParamNull() {
    try {
      TabAlgos.plusGrand(tabNull);
      fail("l'exception pour les tableaux nulls aurait dû être levée.");
    } catch (IllegalArgumentException e) {
      // rien à faire il s'agit d'un comportement normal
    }
  }

  /**
   * Test de la méthode moyenne cas nominal.
   */
  @Test
  public void moyenneTest() {
    assertEquals(moyenne, TabAlgos.moyenne(tab1), delta);
  }

  /**
   * Test de la méthode moyenne avec tableau vide.
   */
  @Test
  public void moyenneTestAvecParamVide() {
    try {
      TabAlgos.moyenne(tabVide);
      fail("l'exception pour les tableaux vides "
          + "et nulls aurait dû être levée.");
    } catch (IllegalArgumentException e) {
      // rien à faire il s'agit d'un comportement normal
    }
  }

  /**
   * Test de la méthode moyenne avec tableau null ou vide.
   */
  @Test
  public void moyenneTestAvecParamNull() {
    try {
      TabAlgos.moyenne(tabNull);
      fail("l'exception pour les tableaux vides "
          + "et nulls aurait dû être levée.");
    } catch (IllegalArgumentException e) {
      // rien à faire il s'agit d'un comportement normal
    }
  }

  /**
   * Test pour la méthode egaux. Cas nominal
   */
  @Test
  public void egauxTest() {
    assertEquals(true, TabAlgos.egaux(tab1, tab2));
  }

  /**
   * Test pour la méthode egaux: tableaux de même
   * taille avec des différences de valeurs.
   */
  @Test
  public void egauxTestDifferenceValeurs() {
    assertEquals(false, TabAlgos.egaux(tab1, tab3));
  }

  /**
   * Test pour la méthode egaux: tableaux de
   * taille différentes.
   */
  @Test
  public void egauxTestTaillesDifferente() {
    assertEquals(false, TabAlgos.egaux(tab1, tab4));
  }

  /**
   * Test pour la méthode similaires. Cas nominal
   */
  @Test
  public void similairesTest() {
    assertEquals(true, TabAlgos.similaires(tab1, tab3));
  }

  /**
   * Test pour la méthode similaires:
   * tableaux de même taille avec différences de valeurs.
   */
  @Test
  public void similairesTestDifferenceValeurs() {
    assertEquals(false, TabAlgos.similaires(tab1, tab5));
  }

  /**
   * Test pour la méthode similaires: tableaux de
   * taille différentes.
   */
  @Test
  public void similairesTestTaillesDifferente() {
    assertEquals(false, TabAlgos.similaires(tab1, tab4));
  }

}
