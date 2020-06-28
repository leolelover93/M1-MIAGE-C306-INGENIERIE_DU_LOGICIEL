/**
 *  Implementation de la classe "GrilleImpl"
 *  necessaire a la creation d'une simple
 *  grille de sudoku.
 */

package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Implementation permettant de creer une
 * grille de sudokou 9x9 ou 16x16.
 * @author LEUMASSI FANSI Jean-Leopold
 *
 */
public class GrilleImpl implements Grille {

  /**
   * Liste des valeurs possibles avec leurs positions.
   */
  HashMap<Integer, Character> dicoValeursPossibles = new HashMap<Integer, Character>();    

  /**
   * segmenteur de blocs.
   */
  private ArrayList<ElementVisitable> listeDesVisitables;
  
  /**
   * segmenteur de blocs.
   */
  private ListIterator<ElementVisitable> listIterator;

  /**
   * segmenteur de blocs.
   */
  private final int racine;

  /**
   * Tableau permettant de savoir si une valeur est deja
   * contenue sur une ligne.
   */
  private boolean [][] existeSurLigne;    

  /**
   * Tableau permettant de savoir si une valeur est deja
   * contenue sur une colonne.
   */
  private boolean [][] existeSurColonne;    

  /**
   * Tableau permettant de savoir si une valeur est deja
   * contenue dans un bloc.
   */
  private boolean [][] existeSurBloc;    

  /**
   * Constante representant la dimenssion de la grille.
   */
  private final int dimension;

  /**
   * Grille de sudoku.
   */
  private char[][] grille;

  /**
   * Tableau des valeurs possibles en fonction du type de grille.
   */
  private char[] valeurPossibles;

  /**
   * Constructeur permettant d'initialiser
   * la grille de sudokou 9x9 ou 16x16.
   *
   * @param laDimension de la grille
   *        de type int.
   */
  public GrilleImpl(final int laDimension) {
    this.dimension = laDimension;
    racine = (int) Math.sqrt(this.getDimension());
    this.existeSurBloc = new boolean[this.getDimension()][this.getDimension()];
    this.existeSurColonne = new boolean[this.getDimension()][this.getDimension()];
    this.existeSurLigne = new boolean[this.getDimension()][this.getDimension()];

    this.grille = new char[this.getDimension()][this.getDimension()];
    this.valeurPossibles = new char[this.getDimension()];
    //Puis on recupere les valeurs possibles
    System.arraycopy(POSSIBLE, 0, valeurPossibles, 0, this.getDimension());
    
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.dimension; j++) {
        this.grille[i][j] = EMPTY;
        this.existeSurBloc[i][j] = false;
        this.existeSurColonne[i][j] = false;
        this.existeSurLigne[i][j] = false;
      }
      this.dicoValeursPossibles.put(i, POSSIBLE[i]);
    }
    this.listeDesVisitables = new ArrayList<ElementVisitable>();

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
    if (presentSurBloc(x, y, value)) {
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
   * @return true si la valeur est presente,
   *         false dans le cas contraire.
   */
  private boolean presentSurBloc(final int x, final int y, final char value) {
    int posX = x - (x % this.racine);
    int posY = y - (y % this.racine);
    // ou encore : posX = 3*(i/3), posY = 3*(j/3);

    for (int i = posX; i < posX + this.racine; i++) {
      for (int j = posY; j < posY + this.racine; j++) {
        if (this.getGrille()[i][j] == value && value != EMPTY) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Methode pour calculer le nombre de valeurs possibles
   * pour une case vide.
   * @param i numero de la ligne.
   * @param j numero de la colle.
   * @return le nombre de valeurs qu'il est possible d'inserer
   *         dans une case vide.
   */
  private int nombrePossiblite(final int i, final int j) {
    int ret = 0;
    int n = this.racine * (i / this.racine) + (j / this.racine);
    for (char caract : this.valeurPossibles) {
      int k = getKey(this.dicoValeursPossibles, caract);
      // Verifie dans les tableaux si la valeur est deja presente
      if (!existeSurLigne[i][k] && !existeSurColonne[j][k]
          && !existeSurBloc[n][k]) {
        ret = ret + 1;
      }
    }
    return ret;
  }

  /**
   * Methode permettant de recuperer la cle d'une Map via
   * sa valeur associee.
   * @param map l'objet map
   * @param value la valeur associee de la cle qu'on cherche.
   * @return K valeur de type K.
   */
  private static <K, V> K getKey(Map<K, V> map, V value) {
    for (K key : map.keySet()) {
      if (value.equals(map.get(key))) {
        return key;
      }
    }
    return null;
  }

  /**
   * Methode permettant de resoudre une grille avec
   * la methode par backtracking optimisee.
   * @return vrai lorsque la grille est resolue
   */
  public boolean resoudreGrille() {
    // Enregistre dans les tableaux toutes les valeurs deja presentes 
    int k;
    for (int i = 0; i < this.getDimension(); i++) {
      for (int j = 0; j < this.getDimension(); j++) {
        if (this.grille[i][j] != EMPTY) {
          k = getKey(this.dicoValeursPossibles, this.grille[i][j]);
          int n = this.racine * (i / this.racine) + (j / this.racine);
          this.existeSurLigne[i][k] = true;
          this.existeSurColonne[j][k] = true;
          this.existeSurBloc[n][k] = true;
        }
      }
    }

    // On cree et remplit la liste des cases vides a visiter.
    for (int ligne = 0; ligne < this.getDimension(); ligne++) {
      for (int colonne = 0; colonne < this.getDimension(); colonne++) {
        if (this.grille[ligne][colonne] == EMPTY) {
          ElementVisitable elementVisitables = new ElementVisitable(ligne, colonne,
              this.nombrePossiblite(ligne, colonne));
          this.listeDesVisitables.add(elementVisitables);
        }
      }
    }

    //On tri la liste par ordre croissant sur le nombre de possibilite.
    Collections.sort(this.listeDesVisitables, ElementVisitable.NombreValeurPossiblesComparator);
    this.listIterator = listeDesVisitables.listIterator();
    boolean retour = estValide(listIterator);
    // vide la liste
    this.listeDesVisitables.clear();
    // retourne le resultat
    return retour;
  }

  /**
   * Fonction de backtracking recursive estValide().
   * @param listIterator liste chainee a parcourir
   * @return true lorsque l'operation se passe bien
   */
  private boolean estValide(ListIterator<ElementVisitable> listIterator) {
    //Si la liste est vide, on renvoi true
    if (!listIterator.hasNext()) {
      return true;
    }

    ElementVisitable elementCourant = listIterator.next();
    int i = elementCourant.getLigne();
    int j = elementCourant.getColonne();
    while (elementCourant.getNombreValeurPossibles() > 0) {
      for (char caract : this.valeurPossibles) {
        int k = getKey(this.dicoValeursPossibles, caract);
        int n = this.racine * (i / this.racine) + (j / this.racine);
        // Verifie dans les tableaux si la valeur est deja presente
        if (!existeSurLigne[i][k] && !existeSurColonne[j][k]
            && !existeSurBloc[n][k]) {
          // Ajoute k aux valeurs enregistrees
          existeSurLigne[i][k] = true;
          existeSurColonne[j][k] = true;
          existeSurBloc[n][k] = true;
          // Ecrit le choix valide dans la grille
          try {
            this.setValue(i, j, this.dicoValeursPossibles.get(k));
            if (estValide(listIterator)) {
              return true; 
            } else {
              this.getGrille()[i][j] = EMPTY;
            }
          } catch (Exception ex) {
            //throw new IllegalArgumentException(ex.toString());
            continue;
          }
          // Supprime k des valeurs enregistrees
          existeSurLigne[i][k] = false;
          existeSurColonne[j][k] = false;
          existeSurBloc[n][k] = false;
        }
      }
      elementCourant.setNombreValeurPossibles(elementCourant.getNombreValeurPossibles() - 1);
    }
    return false;
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
