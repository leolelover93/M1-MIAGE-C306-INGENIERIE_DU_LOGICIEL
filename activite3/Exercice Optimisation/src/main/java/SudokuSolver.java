/**
 *  Implementation de la classe "SudokuSolver"
 *  servant de point d'entree du programme.
 */

package main.java;

import java.io.File;
import java.io.IOException;

/**
 *  Implementation de la classe "SudokuSolver"
 *  servant de point d'entree du programme.
 */
public class SudokuSolver {
  static final int NEUF = 9;
  static final int SEIZE = 9;
  static File fichierSudoku;
  static String fichierSudoku1Url = "ressources/sudoku1.txt";

  /**
   *  Methode principale du programme.
   *  @param args [] tableau de chaines de caractere.
   */
  public static void main(String[] args) {
    fichierSudoku = new File(fichierSudoku1Url);
    GrilleImpl s = new GrilleImpl(NEUF);
    try {
      GrilleParser.parse(fichierSudoku, s);
      s.afficherGrille();

      if (s.resoudreGrille()) {
        s.afficherGrille();
      } else {
        System.out.println("Unsuccessful");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
