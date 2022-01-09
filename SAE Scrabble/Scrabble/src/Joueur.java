public class Joueur {
private String nom;
private MEE chevalet;
private int score;

public Joueur (String nom){
    this.nom = nom;
    this.score=0;
    this.chevalet=new MEE(26);
}
/**
 * affiche un string 
 * @return
 */
public String toString(){
    return("Joueur: "+this.nom+'\n'+"Score: "+this.score);
}
/**
 * getter pour le score 
 * @return
 */
public int getScore(){
    return this.score;
}
/**
 * setter pour le score
 * @param nb
 */
public void ajouteScore(int nb){
    this.score+=nb;
}
/**
* pré-requis : nbPointsJet indique le nombre de points rapportés par
* chaque jeton/lettre
* résultat : le nombre de points total sur le chevalet de ce joueur
* suggestion : bien relire la classe MEE !
*/
public int nbPointsChevalet (int[] nbPointsJet){
    int pointsChevalet = 0;
    if(this.chevalet.estVide() == true){
        pointsChevalet = 0;
    }else {
    pointsChevalet= this.chevalet.sommeValeurs(nbPointsJet);}
    return pointsChevalet;
}

/**
* pré-requis : les éléments de s sont inférieurs à 26
* action : simule la prise de nbJetons jetons par this dans le sac s,
* dans la limite de son contenu.
*/
public void prendJetons (MEE s, int nbJetons) {
    s.transfereAleat(this.chevalet,nbJetons);
}
/**
 * action: permet de créer un String qui affiche un chevalet.
 * @return chevalet
 */
public String afficheChevalet() {
    String chevalet = "| ";
        int[] tableauChevalet = this.chevalet.getTabFreq();
        for(int i=0; i<tableauChevalet.length; i++){
            if(tableauChevalet[i] !=0){
                for(int k=0; k<tableauChevalet[i]; k++){
                    chevalet += (char)(i+65) + " | ";
                }
            }
        }
    return chevalet;
}
/**
* pré-requis : les éléments de s sont inférieurs à 26
* et nbPointsJet.length >= 26
* action : simule le coup de this : this choisit de passer son tour,
* d’échanger des jetons ou de placer un mot
* résultat : -1 si this a passé son tour, 1 si son chevalet est vide,
* et 0 sinon
@param Plateau,Mee,int[]
@return res
*/
public int joue(Plateau p, MEE s, int[] nbPointsJet) {
    int res=0;
    
    System.out.println(p.toString());
    System.out.println("Votre score : " + this.score);
    System.out.println("N pour passer, E pour échanger, P pour placer : ");
    char lettre= Ut.saisirCaractere();
    if (lettre=='N') {
        res=-1;
    }else{if (lettre=='E') {
        this.echangeJetons(s);
        res=0;
    }else{if (lettre=='P') {
        if(joueMot(p, s, nbPointsJet)){
            if(this.chevalet.estVide()){
                res = 1;
            }else {
                res = 0;
            }
        }
        
    }
}
}
return res;
}

/** pré-requis : les éléments de s sont inférieurs à 26
* et nbPointsJet.length >= 26
* action : simule le placement d’un mot de this :
* a) le mot, sa position sur le plateau et sa direction, sont saisis
* au clavier
* b) vérifie si le mot est valide
* c) si le coup est valide, le mot est placé sur le plateau
* résultat : vrai ssi ce coup est valide, c’est-à-dire accepté par
* CapeloDico et satisfaisant les règles détaillées plus haut
* stratégie : utilise la méthode joueMotAux
*/
public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {
    boolean res = false;
    char sens=' ';
    System.out.println(afficheChevalet());
    System.out.println("Sairsir le mot à jouer");String mot =Ut.saisirChaine();
    System.out.println("Sélectionner le numéro de la ligne");int numLig = Ut.saisirEntier();
    System.out.println("Sélectionner le numéro de la colonne");int numCol = Ut.saisirEntier();
    while (sens!='h'&& sens!='v') {
        System.out.println("Sélectionner le sens:'h' pour horizontal et v pour vertical");
        sens= Ut.saisirCaractere();
    }
    while(!p.placementValide(mot, numLig, numCol, sens, this.chevalet)){
        System.out.println("\n\n - Erreur, placement du mot invalide !");
            System.out.print("Veuilliez saisir le mot à placer: "); mot = Ut.saisirChaine();
            System.out.print("Veuilliez saisir le numéro de Colonne: "); numCol = Ut.saisirEntier();
            System.out.print("Veuilliez saisir le numéro de Ligne: "); numLig = Ut.saisirEntier();
            System.out.print("Veuilliez saisir le sens de direction (v: Vertical et h: Horizontal) : "); sens = Ut.saisirCaractere();
        }
        joueMotAux(p, s, nbPointsJet, mot, numLig, numCol, sens);
        res= true;
    return res;
}



/** pré-requis : cf. joueMot et le placement de mot à partir de la case
* (numLig, numCol) dans le sens donné par sens est valide
* action : simule le placement d’un mot de this
*/
public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot,
int numLig, int numCol, char sens) {
    if (this.chevalet.getCardinal()<7) {
        this.prendJetons(s, 7-this.chevalet.getCardinal());
        
    }
    System.out.println(this.score);
    this.ajouteScore(p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet));
    System.out.println(this.score);
    if(mot.equalsIgnoreCase("SCRABBLE")){
        this.ajouteScore(50);
    }

}

/**
* pré-requis : sac peut contenir des entiers de 0 à 25
* action : simule l’échange de jetons de ce joueur :
* - saisie de la suite de lettres du chevalet à échanger
* en vérifiant que la suite soit correcte
* - échange de jetons entre le chevalet du joueur et le sac
* stratégie : appelle les méthodes estCorrectPourEchange et echangeJetonsAux
*/
public void echangeJetons(MEE sac) {
    String motChange = "";
        System.out.println("Veuilliez saisir les lettres que vous voulez changer en MAJUSCULE: "); motChange = Ut.saisirChaine();

        while(estCorrectPourEchange(motChange) == false){
            System.out.println("ERREUR: Vous n'avez pas mis toutes les lettres en MAJUSCULE ou vous n'avez pas ces lettres dans votre chevalet.");
            System.out.println("Veuilliez saisir les lettres que vous voulez changer en MAJUSCULE: "); motChange = Ut.saisirChaine();
        }

        echangeJetonsAux(sac, motChange);

}
/** résultat : vrai ssi les caractères de mot correspondent tous à des
* lettres majuscules et l’ensemble de ces caractères est un
* sous-ensemble des jetons du chevalet de this
*/
public boolean estCorrectPourEchange (String mot) {
    boolean res = true;
        int i=0;
        while(i<mot.length() && res){
            if(((int)mot.charAt(i)>65)){
                res = false;
            }
            i++;
        }
        i=0;
        while(res && i<mot.length()){
            if(this.chevalet.contient(mot.charAt(i))){
                i++;
            }else {
                res = false;
            }
        }


        return res;
    }
/** pré-requis : sac peut contenir des entiers de 0 à 25 et ensJetons
* est un ensemble de jetons correct pour l’échange
* action : simule l’échange de jetons de ensJetons avec des
* jetons du sac tirés aléatoirement.
*/
public void echangeJetonsAux(MEE sac, String ensJetons) {
    for(int i=0; i<ensJetons.length(); i++){
        int letterChange = ((int)ensJetons.charAt(i)-65);
        //Je transfere aleatoirement une lettre dans le chevalet
        sac.transfereAleat(this.chevalet, 1);
        //Ensuite je transfere la lettre du chevalet vers le sac.
        this.chevalet.transfere(sac, letterChange);
    }
    if(this.chevalet.getCardinal() < 7){
        this.prendJetons(sac, 7 - this.chevalet.getCardinal());
    }
}
public MEE getChevalet() {
    return this.chevalet;
}
}
