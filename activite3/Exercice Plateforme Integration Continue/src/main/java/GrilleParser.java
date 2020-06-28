/**
 *  Implementation de la classe "GrilleParser"
 *  necessaire a la au chargement des grilles
 *  d'evaluation.
 */

package main.java;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public final class GrilleParser {

  private GrilleParser() {
  }

  /**
   *  Methode permettant de charger une grille a l'aide
   *  des donnees contenues dans un flux.
   *  @param in le le flux de donnees a exploiter.
   *  @param grille la grille sur laquelle effectuer l'opreration
   */
  public static void parse(final InputStream in, final Grille grille)
      throws IOException {
    Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    int dimension = grille.getDimension();
    char[] buffer = new char[dimension];

    for (int line = 0; line < dimension; line++) {
      int lus = reader.read(buffer);
      if (lus != dimension) {
        throw new EOFException("format incorrect");
      }

      for (int i = 0; i < dimension; i++) {
        grille.setValue(line, i, buffer[i]);
      }

      lus = reader.read(new char[1]);
      if (lus != 1) {
        throw new EOFException("pas de fin de ligne ? ligne=" + line);
      }
    }

    reader.close();

  }

  /**
   *  Methode permettant de charger une grille a l'aide
   *  des donnees contenues dans un fichier.
   *  @param f le fichier a lire.
   *  @param grille la grille sur laquelle effectuer l'opreration
   */
  public static void parse(final File f, final Grille grille)
      throws IOException {
    parse(new FileInputStream(f), grille);
  }

}
