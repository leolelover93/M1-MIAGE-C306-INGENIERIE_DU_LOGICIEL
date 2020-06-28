/**
 *  Implementation de la classe "GrilleImpl"
 *  necessaire a la creation d'une simple
 *  grille de sudoku.
 */

package main.java;

/**
 * Implementation permettant de creer une
 * grille de sudokou 9x9 ou 16x16.
 * @author LEUMASSI FANSI Jean-Leopold
 *
 */
public class GrilleImpl implements Grille {

  /**
   * Constante representant la dimenssion de la grille.
   */
  private final int dimension;

  /**
   * Grille de sudoku.
   */
  private char[][] grille;

  /**
   * Constructeur permettant d'initialiser
   * la grille de sudokou 9x9 ou 16x16.
   *
   * @param laDimension de la grille
   *        de type int.
   */
  public GrilleImpl(final int laDimension) {
    this.dimension = laDimension;
    this.grille = new char[this.getDimension()][this.getDimension()];
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.dimension; j++) {
        this.grille[i][j] = EMPTY;
      }
    }
  }

  /**
   * Accesseur permettant de retourner la grille.
   * @return char [][]
   */
  public char[][] getGrille() {
    return grille;
  }

  /**
   * mutateur permettant d'affecter une grille entiere.
   * @param laGrille la grille a affecter.
   */
  public void setGrille(final char[][] laGrille) {
    System.arraycopy(laGrille, 0, this.grille, 0, this.getDimension());
  }

  @Override
  public int getDimension() {
    return this.dimension;
  }

  @Override
  public void setValue(final int x, final int y, final char value)
      throws IllegalArgumentException {
    try {
      this.possible(x, y, value);
      //Si la value n'est pas autorisee dans
      //la grille aux vues des autres valeurs
      //contenues dans le tableau, on leve une
      //exception.
      if (!this.estAutorisee(x, y, value)) {
        throw new IllegalArgumentException("la valeur est interdite au "
          + "vues des valeurs deja contenues dans la grille.");
      }
      this.getGrille()[x][y] = value;
    } catch (Exception ex) {
      throw new IllegalArgumentException(ex.toString());
    }
  }

  @Override
  public char getValue(final int x, final int y)
      throws IllegalArgumentException {
    if (estHorsBornes(x, y, this.getDimension())) {
      throw new IllegalArgumentException("x et/ou y est(sont) hors bornes.");
    }
    return this.getGrille()[x][y];
  }

  @Override
  public boolean complete() {
    for (char[] sousGrille : this.getGrille()) {
      for (char carac : sousGrille) {
        if (carac == EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean possible(final int x, final int y, final char value)
      throws IllegalArgumentException {

    // On verifie que x et y sont dans les bornes autorisees;
    // pour cela, x et y ne doivent pas etre inferieur a 0
    // et ne doivent pas depasser la taille max de la grille.
    if (estHorsBornes(x, y, this.getDimension())) {
      throw new IllegalArgumentException("x et/ou y est(sont) hors bornes.");
    }

    //On cree un tableau qui recevra les valeurs
    //possibles en fonction du type de la grille.
    char[] temp = new char[this.getDimension()];

    //Puis on recupere les valeurs possibles
    System.arraycopy(POSSIBLE, 0, temp, 0, this.getDimension());

    //on verifie si value est un caractere autorise.
    boolean estPresent = false;
    for (char s : temp) {
      if (s == value || value == EMPTY) {
        estPresent = true;
        break;
      }
    }
    if (!estPresent) {
      throw new IllegalArgumentException("Cette valeur n'est pas autorisee "
        + "(parmis les valeurs possibles.)");
    }
    return estPresent;
  }

  /**
   * Methode pour verifier si x et y sont hors bornes.
   * @param x position x dans la grille.
   * @param y position y dans la grille.
   * @param laDimension taille de la grille.
   * @return true si x et/ou y sont hors bornes,
   *         false si x et y sont dans les bornes.
   */
  private boolean estHorsBornes(final int x, final int y,
      final int laDimension) {
    boolean test1 = false;
    if (x < 0 || y < 0) {
      test1 = true;
    } else {
      test1 = false;
    }

    boolean test2 = false;
    if (x >= laDimension || y >= laDimension) {
      test2 = true;
    } else {
      test2 = false;
    }

    if (test1 || test2) {
      return true;
    }
    return false;
  }

  /**
   * Methode pour verifier si une valeur est autorisee
   * aux vues des autres valeurs de la grille.
   * @param x position x dans la grille.
   * @param y position y dans la grille.
   * @param value valeur a tester.
   * @return true si la valeur est autorisee,
   *         false dans le cas contraire.
   */
  private boolean estAutorisee(final int x, final int y, final char value) {
    // On verifie que l'element n'exite pas
    // sur la meme ligne.
    if (this.presentSurLigne(x, value)) {
      return false;
    }

    // On verifie que l'element n'exite pas
    // sur la meme colonne.
    if (this.presentSurColonne(y, value)) {
      return false;
    }

    /*On doit vzrifier que la sous grille
      (3x3) ou (4x4) ne contient pas deja
      la valeur.*/
    int racine = (int) Math.sqrt(this.getDimension());
    if (presentSurBloc(x, y, value, racine)) {
      return false;
    }
    return true;
  }

  /**
   * Methode pour verifier si une valeur est presente
   * sur une ligne.
   * @param x numero de la ligne.
   * @param value valeur a tester.
   * @return true si la valeur est presente,
   *         false dans le cas contraire.
   */
  private boolean presentSurLigne(final int x, final char value) {
    for (int i = 0; i < this.getDimension(); i++) {
      if (this.getGrille()[x][i] == value && value != EMPTY) {
        return true;
      }
    }
    return false;
  }

  /**
   * Methode pour verifier si une valeur est presente
   * sur une colonne.
   * @param y numero de la colonne.
   * @param value valeur a tester.
   * @return true si la valeur est presente,
   *         false dans le cas contraire.
   */
  private boolean presentSurColonne(final int y, final char value) {
    for (int i = 0; i < this.getDimension(); i++) {
      if (this.getGrille()[i][y] == value && value != EMPTY) {
        return true;
      }
    }
    return false;
  }

  /**
   * Methode pour verifier si une valeur est presente
   * dans une sous grille ou un bloc de la grille.
   * @param x numero de la ligne.
   * @param y numero de la colle.
   * @param value valeur a chercher.
   * @param racine racine carree de la dimension.
   * @return true si la valeur est presente,
   *         false dans le cas contraire.
   */
  private boolean presentSurBloc(final int x, final int y,
      final char value, final int racine) {
    int posX = x - (x % racine);
    int posY = y - (y % racine);
    // ou encore : posX = 3*(i/3), posY = 3*(j/3);

    for (int i = posX; i < posX + racine; i++) {
      for (int j = posY; j < posY + racine; j++) {
        if (this.getGrille()[i][j] == value && value != EMPTY) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Methode permettant de resoudre une grille de sudoku.
   * @return true si tout se passe bien.
   */
  public boolean resoudreGrille() {
    //On cree un tableau qui recevra les valeurs
    //possibles en fonction du type de la grille.
    char[] valeurPossibles = new char[this.getDimension()];

    //Puis on recupere les valeurs possibles
    System.arraycopy(POSSIBLE, 0, valeurPossibles, 0, this.getDimension());
    for (int ligne = 0; ligne < this.getDimension(); ligne++) {
      for (int colonne = 0; colonne < this.getDimension(); colonne++) {
        if (this.getGrille()[ligne][colonne] == EMPTY) {
          for (char carac : valeurPossibles) {
            try {
              this.setValue(ligne, colonne, carac);
              if (this.resoudreGrille()) {
                return true;
              } else {
                this.getGrille()[ligne][colonne] = EMPTY;
              }
            } catch (Exception ex) {
              //throw new IllegalArgumentException(ex.toString());
              continue;
            }
          }
          return false;
        }
      }
    }
    return this.complete();
  }

  /**
   * Methode permettant d'afficher la grille de sudoku.
   */
  public void afficherGrille() {
    int racine = (int) Math.sqrt(this.getDimension());
    String dashed = "------------------------------------";
    if (this.getDimension() == POSSIBLE.length) {
      dashed = "------------------------------------------------------------";
    }
    System.out.println(dashed);
    for (int i = 0; i < this.getDimension(); i++) {
      if (i % racine == 0 && i != 0) {
        System.out.println(dashed);
      }

      for (int j = 0; j < this.getDimension(); j++) {
        if (j % racine == 0 && j != 0) {
          System.out.print(" | ");
        }

        if (j == 0) {
          System.out.print("| " + this.getGrille()[i][j] + " ");
        } else {
          System.out.print(" " + this.getGrille()[i][j] + " ");
        }

        if (j == this.getDimension() - 1) {
          System.out.print(" |");
        }
      }

      System.out.println();
    }

    System.out.println(dashed + "\n\n");
  }

}
