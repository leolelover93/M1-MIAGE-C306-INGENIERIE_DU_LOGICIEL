package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import main.java.Calcul;
import org.junit.jupiter.api.Test;


/**  Tests unitaire pour la classe Calcul. **/
public class CalculTest {

  @Test
  public void testConstructeur() {
    try {
      new Calcul();
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (UnsupportedOperationException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testSomme() {
    assertEquals(5,Calcul.somme(2,3));
  }

  @Test
  public void testDivision() {
    assertEquals(4,Calcul.division(8,2));
  }
  
  @Test
  public void testDivisionFail() {
    try {
      Calcul.division(8, 0);
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testMaFonction() {
    assertEquals(6, Calcul.maFonction(24, 6));
  }

  @Test
  public void testMaFonctionBis() {
    assertEquals(3, Calcul.maFonction(36, 12));
  }
}
