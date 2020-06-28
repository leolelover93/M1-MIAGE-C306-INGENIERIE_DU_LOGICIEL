package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import main.java.GrilleImpl;
import main.java.GrilleParser;
import org.junit.jupiter.api.Test;

/**
 * Classe GrilleImplTest representant de
 * tester les methodes de la classe GrilleImpl.
 * @author LEUMASSI FANSI Jean-Leopold
 *
 */
public class GrilleImplTest {
  static final int DIMENSION_NEUF = 9;
  static final int DIMENSION_SEIZE = 16;
  static final char EMPTY_CHAR = '@';
  static final char[] POSSIBLE = new char[] { '1', '2', '3', '4', '5', '6',
    '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f' };
  char [][] grilleDeNeuf = new char[][] {{'9', '1', '3', '6', '2', '7', '5', '8', '4'},
      {'6', '4', '2', '5', '8', '9', '7', '3', '1'},
      {'8', '7', '5', '3', '4', '1', '6', '9', '2'},
      {'5', '8', '9', '1', '7', '4', '3', '2', '6'},
      {'2', '6', '1', '8', '9', '3', '4', '5', '7'},
      {'7', '3', '4', '2', '6', '5', '8', '1', '9'},
      {'1', '2', '8', '7', '3', '6', '9', '4', '5'},
      {'3', '9', '6', '4', '5', '2', '1', '7', '8'},
      {'4', '5', '7', '9', '1', '8', '2', '6', '3'}};

  char [][] grilleDeSeize = new char[][] {{'8', 'f', '6', '5', '1', 'a', '7', 'd', 
      '4', '9', 'c', '3', '2', 'b', '0', 'e'},
      {'1', '4', '2', '3', 'b', 'c', 'e', '9', '0', '6', '8', 'd', '5', 'f', '7', 'a'},
      {'c', '7', 'a', '9', '0', '4', '2', '3', 'b', 'e', 'f', '5', '6', 'd', '8', '1'},
      {'b', '0', 'd', 'e', 'f', '5', '8', '6', '7', 'a', '2', '1', '4', '3', 'c', '9'},
      {'2', '6', '4', '7', 'd', '8', '9', 'b', 'c', '0', 'e', 'f', '3', 'a', '1', '5'},
      {'3', '9', '5', '1', '6', '0', 'a', 'f', '2', '8', 'b', '4', 'c', '7', 'e', 'd'},
      {'f', 'd', '8', '0', 'c', '1', '3', 'e', '5', '7', 'a', '9', 'b', '6', '4', '2'},
      {'a', 'b', 'e', 'c', '2', '7', '5', '4', 'd', '3', '1', '6', '9', '0', 'f', '8'},
      {'d', '5', '9', '4', 'e', '3', 'f', '8', '1', 'c', '7', 'a', '0', '2', '6', 'b'},
      {'7', '2', 'b', 'a', '9', '6', 'd', 'c', 'e', '4', '0', '8', 'f', '1', '5', '3'},
      {'0', 'c', '3', 'f', '4', 'b', '1', 'a', '6', '5', 'd', '2', 'e', '8', '9', '7'},
      {'6', 'e', '1', '8', '5', '2', '0', '7', '9', 'f', '3', 'b', 'd', 'c', 'a', '4'},
      {'e', '1', '7', '6', '3', 'f', '4', '2', 'a', 'd', '9', 'c', '8', '5', 'b', '0'},
      {'9', '8', '0', '2', 'a', 'd', '6', '5', 'f', 'b', '4', '7', '1', 'e', '3', 'c'},
      {'5', 'a', 'c', 'd', '8', '9', 'b', '0', '3', '1', '6', 'e', '7', '4', '2', 'f'},
      {'4', '3', 'f', 'b', '7', 'e', 'c', '1', '8', '2', '5', '0', 'a', '9', 'd', '6'}};

  char [][] grilleTestPleine;
  char [][] grilleTestSemiPleineUne;
  char [][] grilleTestSemiPleineDeux;
  File fichierSudoku;
  String fichierSudoku1Url = "ressources/sudoku1.txt";
  String fichierSudoku2Url = "ressources/sudoku2.txt";
  String fichierSudoku3Url = "ressources/sudoku3.txt";
  

  @Test
  public void testConstructeurNeuf() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    assertEquals(DIMENSION_NEUF,test.getDimension());
    assertEquals(EMPTY_CHAR,test.getGrille()[0][0]);
  }

  @Test
  public void testConstructeurSeize() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    assertEquals(DIMENSION_SEIZE,test.getDimension());
    assertEquals(EMPTY_CHAR,test.getGrille()[0][0]);
  }

  @Test
  public void testGrilleNeufSetValue() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    test.setValue(0, 0, '1');
  }

  @Test
  public void testGrilleSeizeSetValue() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    test.setValue(0, 0, '0');
  }

  @Test
  public void testGrilleSetValueXHorsBorne() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(DIMENSION_NEUF, 0, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSetValueYHorsBorne() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(0, DIMENSION_NEUF, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSetValueXHorsBorne2() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(-1, 0, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSetValueYHorsBorne2() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(0, -1, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleNeufSetValueCaractereNonAutorise() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(0, 0, '0');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSeizeSetValueCaractereNonAutorise() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    try {
      test.setValue(0, 0, 'g');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }
  
  @Test
  public void testGrilleSetValueCaractereImpossible() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(0, 0, 'z');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSetValueValeurDejaAjouteePresentSurLigne() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(0, 0, '1');
      test.setValue(0, 1, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSetValueValeurDejaAjouteePresentSurColonne() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    try {
      test.setValue(0, 0, '1');
      test.setValue(1, 0, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }
  
  @Test
  public void testGrilleSetValueValeurDejaAjouteePresentSurBloc() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    try {
      test.setValue(0, 0, '1');
      test.setValue(1, 1, '1');
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleNeufGetValue() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    test.setValue(0, 0, '1');
    assertEquals('1', test.getValue(0, 0));
  }

  @Test
  public void testGrilleSeizeGetValue() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    test.setValue(0, 0, '0');
    assertEquals('0', test.getValue(0, 0));
  }

  @Test
  public void testGrilleNeufGetValueHorsBornes() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    try {
      test.setValue(0, 0, '1');
      assertEquals('0', test.getValue(0, DIMENSION_NEUF));
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleSeizeGetValueHorsBornes() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    try {
      test.setValue(0, 0, '1');
      assertEquals('0', test.getValue(0, DIMENSION_SEIZE));
      fail("Reaction annormale, une exception a ete ignoree.");
    } catch (IllegalArgumentException e) {
      System.out.println("C'est normal de se retrouver ici car : \"" + e.getMessage() + "\"");
    }
  }

  @Test
  public void testGrilleNeufComplete() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    test.setGrille(grilleDeNeuf);
    assertEquals(true, test.complete());
  }

  @Test
  public void testGrilleSeizeComplete() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    test.setGrille(grilleDeSeize);
    assertEquals(true, test.complete());
  }

  @Test
  public void testGrilleNeufIncomplete() {
    GrilleImpl test = new GrilleImpl(DIMENSION_NEUF);
    grilleDeNeuf[0][1] = EMPTY_CHAR;
    test.setGrille(grilleDeNeuf);
    assertEquals(false, test.complete());
  }

  @Test
  public void testGrilleSeizeIncomplete() {
    GrilleImpl test = new GrilleImpl(DIMENSION_SEIZE);
    grilleDeSeize[5][8] = EMPTY_CHAR;
    test.setGrille(grilleDeSeize);
    assertEquals(false, test.complete());
  }

  @Test
  public void testResolutionGrilleNeuf() {
    GrilleImpl grille = new GrilleImpl(DIMENSION_NEUF);
    assertEquals(true, grille.resoudreGrille());
    grille.afficherGrille();
  }

  @Test
  public void testResolutionGrilleSeize() {
    GrilleImpl grille = new GrilleImpl(DIMENSION_SEIZE);
    assertEquals(true, grille.resoudreGrille());
    grille.afficherGrille();
  }

  @Test
  public void testResolutionGrillePleine() {
    fichierSudoku = new File(fichierSudoku3Url);
    GrilleImpl grille = new GrilleImpl(DIMENSION_NEUF);
    GrilleImpl grille2 = new GrilleImpl(DIMENSION_NEUF);
    try {
      GrilleParser.parse(fichierSudoku, grille);
      GrilleParser.parse(fichierSudoku, grille2);
      grille.resoudreGrille();
      for (int ligne = 0; ligne < grille.getDimension(); ligne++) {
        for (int colonne = 0; colonne < grille.getDimension(); colonne++) {
          assertEquals(grille2.getValue(ligne, colonne), grille.getValue(ligne, colonne));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testResolutionGrilleSemiPleine1() {
    fichierSudoku = new File(fichierSudoku1Url);
    GrilleImpl grille = new GrilleImpl(DIMENSION_NEUF);
    try {
      GrilleParser.parse(fichierSudoku, grille);
      assertEquals(true, grille.resoudreGrille());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testResolutionGrilleSemiPleine2() {
    fichierSudoku = new File(fichierSudoku2Url);
    GrilleImpl grille = new GrilleImpl(DIMENSION_NEUF);
    try {
      GrilleParser.parse(fichierSudoku, grille);
      assertEquals(true, grille.resoudreGrille());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
