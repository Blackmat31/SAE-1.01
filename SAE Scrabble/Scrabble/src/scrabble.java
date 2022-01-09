public class scrabble {
    private Joueur[] joueurs;
    private int numJoueur; 
    private Plateau plateau;
    private MEE sac;
    
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,8,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
    /**
     * Action: créer un nouveau scrabble à partir d'une liste String.
     * @param listeJoueurs
     */
    public scrabble(String[] listeJoueurs){
            
    int[] sacTab = {9,2,2,3,15,2,2,2,8,1,1,5,3,6,6,2,1,6,6,6,6,2,1,1,1,1};
    this.plateau = new Plateau();
    this.sac = new MEE(sacTab);
    
    
    this.joueurs = new Joueur[listeJoueurs.length];
    for(int i=0; i<listeJoueurs.length; i++){
    
    this.joueurs[i] = new Joueur(listeJoueurs[i]);
    
       }
    }
    
    
    /**
     * Action: execute une partie de scrabble
     */
        public void partie(){
            boolean arretPartie = false;
            int passePartie = 0;

            for(int i=0; i<joueurs.length; i++){
                while(joueurs[i].getChevalet().getCardinal() < 7){
                    joueurs[i].prendJetons(this.sac, 1);
    
                }
            }
    
            this.numJoueur = Ut.randomMinMax(0, joueurs.length-1);
    
            while(arretPartie == false){
                int actionJouer = joueurs[this.numJoueur].joue(this.plateau, this.sac, this.nbPointsJeton);
    
                //Le joueur passe son tour
                if(actionJouer == -1){
                    passePartie++;
    
                    //Si tous les joueurs on passer leur tour.
                    if(passePartie == joueurs.length){
                        //Arrêt de la partie
                        arretPartie = true;
    
                        //Retire les points de leurs chevalet à leurs score.
                        for(int k=0; k<joueurs.length; k++){
                            int lastPoints = joueurs[k].nbPointsChevalet(nbPointsJeton);
                            joueurs[k].ajouteScore(-(lastPoints));
                        }
                    }
                    //Le joueur n'as plus de jetons dans son chevalet.
                }else if(actionJouer == 1){
                    arretPartie = true;
                    int pointsRestantAutreChevalet = 0;
    
                    //On calcule le nombre de points restant sur le chevalet des autres joueurs
                    for(int i=0; i<joueurs.length; i++){
                        //On rajoute pour connaitre le nombre total de points sur le chevalet des autres joueurs.
                        pointsRestantAutreChevalet += joueurs[i].nbPointsChevalet(nbPointsJeton);
                        //On supprime le nombre de point du chevalet du joueur à son score total.
                        joueurs[i].ajouteScore(-(joueurs[i].nbPointsChevalet(nbPointsJeton)));
                    }
                    joueurs[this.numJoueur].ajouteScore(pointsRestantAutreChevalet);
                }
    
                if(this.numJoueur == joueurs.length-1 && arretPartie == false){
                    this.numJoueur = 0;
                    passePartie = 0;
                }else {
                    if(arretPartie == false){
                        this.numJoueur ++;
                    }
                }
            }
    
            System.out.println(afficheVainqueur());
        }
    
        /**
         * Action: Fonction qui retourne en String, les résultats des joueurs du scrabble et le vainqueur.
         * @return
         */
        public String afficheVainqueur(){
            String resultatVainqueurs = "";
            int idJoueursVainqueur = 0;
            int[] vainqueursJoueurs = new int[joueurs.length];
    
           
            for(int i = 1; i<joueurs.length; i++) {
                if (joueurs[idJoueursVainqueur].getScore() == joueurs[i].getScore()) {
                    vainqueursJoueurs[i] = i;
                }else if(joueurs[i].getScore() > joueurs[idJoueursVainqueur].getScore()){
                    idJoueursVainqueur = i;
                }
            }
    
            if(idJoueursVainqueur == vainqueursJoueurs[1] && vainqueursJoueurs[1] != 0) {
                resultatVainqueurs += "Ex-aequo: " + joueurs[idJoueursVainqueur].toString() + " et ";
                for(int i=1; i<vainqueursJoueurs.length; i++) {
                    if(vainqueursJoueurs[i] != 0 && joueurs[vainqueursJoueurs[i]].getScore() == joueurs[idJoueursVainqueur].getScore()) {
                        resultatVainqueurs += joueurs[vainqueursJoueurs[i]].getNom() + ", ";
                    }
                }
            }else {
                resultatVainqueurs += "\n\nLe vainqueur est: " + joueurs[idJoueursVainqueur].getNom();
            }
    
    
    
    
            return resultatVainqueurs;
        }
        
    }
