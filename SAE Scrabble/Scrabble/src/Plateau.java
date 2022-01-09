public class Plateau {
    private Case [][] g =new Case[15][15];

     /**
     * Initialisateur du plateau, à partir de la matrice standard selon les règles
     * du Scrabble
     */

    public Plateau(){
    int[][] plateau = { {5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5},
                        {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
                        {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
                        {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
                        {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                        {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                        {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                        {5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5},
                        {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                        {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                        {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                        {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
                        {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
                        {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
                        {5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5}};

                           for (int i = 0; i < plateau.length; i++) {
                            for (int j = 0; j < plateau[0].length; j++) {
                                this.g[i][j] = new Case(plateau[i][j]);
                            }
                        }
                    }
 /**
     * constructeur de plateau
     * @param plateau
     */
    public Plateau (Case[][] plateau) {
    this.g = plateau;
        }
           /**
         * Initialise une chaîne de caractères qui affichera le plateau avec
         * les indices de colonnes et les indices de lignes, et le contenu de ce
         *  plateau.
         * @return une chaine de caractères 
         */
        public String toString (){
            String affPlateau = "   |00 |01 |02 |03 |04 |05 |06 |07 |08 |09 |10 |11 |12 |13 |14 |" + '\n';
                for (int i = 0; i < 64; i++) {
                    affPlateau = affPlateau +"-";
                }
                affPlateau = affPlateau +'\n';
        char letCol = 'A';
        for (int i = 0; i < g.length; i++) {
            affPlateau += (" " + letCol + " |");
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j].getCouleur() == 1 && !g[i][j].estRecouverte()) {
                    affPlateau += "   |";
                } else {
                    affPlateau += " " + g[i][j].toString() + " |";
                }
            }

            affPlateau += '\n';
            for (int d = 0; d < 64; d++) {
                affPlateau = affPlateau +"-";
            }
            affPlateau = affPlateau +'\n';
            letCol++;
        }
        return affPlateau;
        }
         /**
         * methode qui vérifie si le mot est dans le chevalet
         * @param mot
         * @param e
         * @return
         */
        public boolean verifChevalet(String mot, MEE e){
            boolean DansChevalet = true;
            int posMot = 0;
            int[] chevalet = new int[e.getTabFreq().length];
    
            for(int i=0; i<e.getTabFreq().length; i++){
                chevalet[i] = e.getTabFreq()[i];
            }
    
            while (DansChevalet == true && posMot < mot.length()) {
                if (chevalet[(int)(mot.charAt(posMot)-65)] != 0) {
                    chevalet[(int)(mot.charAt(posMot)-65)]--;
                    posMot++;
                } else {
                    DansChevalet = false;
                }
            }
    
    
            return DansChevalet;
        }

        /**
* pré-requis : mot est un mot accepté par CapeloDico,
* 0 <= numLig <= 14, 0 <= numCol <= 14, sens est un élément
* de {’h’,’v’} et l’entier maximum prévu pour e est au moins 25
* résultat : retourne vrai ssi le placement de mot sur this à partir
* de la case (numLig, numCol) dans le sens donné par sens à l’aide
* des jetons de e est valide.
*/
        public boolean placementValide(String mot, int numLig, int numCol,
char sens, MEE e){
    boolean res = false;
    boolean bonMot= false;
    int compteurLettre =0;
    String stockLettre="";


    //demande de vérification par l'humain "CapleDico"
     Ut.afficherSL("le mot :"+ mot +"est-il un mot valide");
     Ut.afficherSL("1 pour oui | 2 pour non");
     if (Ut.saisirEntier()==1) {
         bonMot=true;
     }
    // verification de la longueur du mot
    if(mot.length()>=2 && bonMot){
        //Cas où le plateau est vide, et donc que la case centrale est vide.
    if(this.g[7][7].estRecouverte()==false){
        
            //Cas où le plateau est vide, et que le sens de placement du mot est horizontal.
            if(sens=='h'){
                if(7+mot.length()<=15){
                    if (verifChevalet(mot, e)) {
                        res=true;
                    }
                    }
                }
            //Cas où le plateau est vide, et que le sens de placement du mot est vertical.
            else if(sens=='v'){
                if(7+mot.length()<=15){
                    if (verifChevalet(mot, e)) {
                        res=true;
                    
                                }
                    }
                   
                }
        }    
    
    //Cas où le plateau n'est pas vide, et donc que la case centrale est recouverte.
    else{
        
        //Cas où le plateau est vide, et que le sens de placement du mot est horizontal.
        if(sens=='h'){
            if(numCol+mot.length()<=15 && this.g[numLig][numCol-1].estRecouverte()==false && this.g[numLig][numCol+mot.length()+1].estRecouverte()==false){
                for (int i = numCol; i < numCol+mot.length();i++) {
                    if (!this.g[numLig][i].estRecouverte()) {
                        stockLettre+=mot.charAt(i-numCol);
                        compteurLettre++;
                    }if (this.g[numLig][i].estRecouverte()) {

                      if (this.g[numLig][i].getLettre()==((int)mot.charAt(i-numCol)-65)) {
                          compteurLettre++;
                      }
                      if (compteurLettre==mot.length()) {
                        if (verifChevalet(stockLettre, e)) {
                            res=true;
                        }
                    }
                    }
                }

            }
        }
        //Cas où le plateau est vide, et que le sens de placement du mot est vertical.
        else if(sens=='v'){
            if(numLig+mot.length()<=15 && this.g[numLig-1][numCol].estRecouverte()==false && this.g[numLig+mot.length()+1][numCol].estRecouverte()==false){
                //Manque si la zone avec case avec jeton et case sans jeton, si la lettre du jeton est la même que celle du mot à placer dans la case, et si le mot est présent dans le chevalet.
                for (int i = numCol; i < numCol+mot.length();i++) {
                    if (!this.g[numLig][i].estRecouverte()) {
                        stockLettre+=mot.charAt(i-numCol);
                        compteurLettre++;
                    }if (this.g[numLig][i].estRecouverte()) {

                      if (this.g[numLig][i].getLettre()==((int)mot.charAt(i-numCol)-65)) {
                          compteurLettre++;
                      }
                      if (compteurLettre==mot.length()) {
                        if (verifChevalet(stockLettre, e)) {
                            res=true;
                        }
                    }
                    }
                }

            }
            }
        }
    
}


    return res;
}

/**
* pré-requis : le placement de mot sur this à partir de la case
* (numLig, numCol) dans le sens donné par sens est valide
* résultat : retourne le nombre de points rapportés par ce placement, le
* nombre de points de chaque jeton étant donné par le tableau nbPointsJet.
*/

public int nbPointsPlacement(String mot, int numLig, int numCol,
char sens, int[] nbPointsJet) {
int nbPointFinal =0;
int multiplicateur =1;
int indexLettre;
if (sens =='h') {
    for (int i = 0; i < mot.length(); i++) {
        indexLettre=(int)mot.charAt(i)-65;
        if (g[numLig][numCol+i].getCouleur()==2) {
            nbPointFinal+=nbPointsJet[indexLettre]*2;
        }
        if (g[numLig][numCol+i].getCouleur()==3) {
            nbPointFinal+=nbPointsJet[indexLettre]*3;
        }
        if (g[numLig][numCol+i].getCouleur()==4) {
            multiplicateur+=2;
        }
        if (g[numLig][numCol+i].getCouleur()==5) {
            multiplicateur+=3;
        }
        
    }
    }
    if (sens =='v') {
        for (int i = 0; i < mot.length(); i++) {
            indexLettre=(int)mot.charAt(i)-65;
            if (g[numLig+i][numCol].getCouleur()==2) {
                nbPointFinal+=nbPointsJet[indexLettre]*2;
            }
            if (g[numLig+i][numCol].getCouleur()==3) {
                nbPointFinal+=nbPointsJet[indexLettre]*3;
            }
            if (g[numLig+i][numCol].getCouleur()==4) {
                multiplicateur+=2;
            }
            if (g[numLig+i][numCol].getCouleur()==5) {
                multiplicateur+=3;
            }
            
        }
        }
        return nbPointFinal*multiplicateur;
}


/**
* pré-requis : le placement de mot sur this à partir de la case
* (numLig, numCol) dans le sens donné par sens à l’aide des
* jetons de e est valide.
* action/résultat : effectue ce placement et retourne le
* nombre de jetons retirés de e.
*/
public int place(String mot, int numLig, int numCol, char sens, MEE e){
    int nbJetonRetiree = 0;
        int index = 0;

        //Je regarde le sens.
        if(sens == 'h'){
            //Puis je crée un boucle qui vas check toutes les cases du mot.
            for(int i=numCol; i<numCol+mot.length(); i++){
                //Si c'est possible de retirer.
                if(e.retire(((int)mot.charAt(index)-65))){
                    //Alors j'ajoute 1 aux res.
                    nbJetonRetiree ++;
                }
                //Puis je place sur le plateau.
                g[numLig][i].setLettre(mot.charAt(index));
                index++;
            }
        }else if(sens == 'v'){
            //Puis je crée un boucle qui vas check toutes les cases du mot.
            for(int i=numLig; i<numLig+mot.length(); i++){
                //Si c'est possible de retirer.
                if(e.retire((int)mot.charAt(index)-65)){
                    //Alors je rajoute 1 au res.
                    nbJetonRetiree ++;
                }
                //Puis je place sur le plateau.
                g[i][numCol].setLettre(mot.charAt(index));
                index++;
            }
            
            
        }

        return nbJetonRetiree;

}
   }