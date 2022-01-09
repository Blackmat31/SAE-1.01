public class mainScrabble {

    public static void main(String[] args){
        Ut.afficher("Veuillez saisir le nombre de joueurs(min:2;max:4)"+'\n');
        int nbJoueurs = Ut.saisirEntier();//pour saisir le nombre de joueurs
        String[] listeJoueurs = new String[nbJoueurs];//crée un tableau pour y mettre les noms

        while(nbJoueurs<2 || nbJoueurs>4){
            Ut.afficher("Veuillez saisir le nombre de joueurs(min:2;max:4)");
            nbJoueurs=Ut.saisirEntier();
        }
        //pour saisir le nom du joueur 
        for(int i = 0; i < nbJoueurs; i++){
            Ut.afficher("Nom du joueur n°" + (i + 1) + ": ");
            listeJoueurs[i] = Ut.saisirChaine();
        }
        scrabble scrabble = new scrabble(listeJoueurs);//pour crée le scrabble
        scrabble.partie();//pour lancer le scrabble
    }
}