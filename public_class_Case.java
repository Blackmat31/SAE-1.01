public class Case {
    private int couleur;
    private char lettre;
    /**
     *  pré-requis : uneCouleur est un entier entre 1 et 5
     * action : constructeur de Case
     * @param uneCouleur
     */
    public Case (int uneCouleur){
        this.couleur = uneCouleur;
        this.lettre=0;

    }
        
       /**
        * résultat : la couleur de this, un nombre entre 1 et 5
        * @return
        */
        public int getCouleur (){
            return this.couleur;
        }
        /**
         *  pré-requis : cette case est recouverte
         * @return
         */
        public char getLettre (){
            return this.lettre;
        }
        /**
         *  pré-requis : let est une lettre majuscule
         * @param let
         */
        public void setLettre (char let){
            this.lettre=let;
        }
        /**
         * résultat : vrai ssi la case est recouverte par une lettre
         * @return res
         */
        public boolean estRecouverte (){
            boolean res =false;
            if (this.lettre!=0) {
                res=true;
            }
            return res;
        }
        /**
         * 
         * résultat : renvoie un String en fonction de si estRecouverte est vrai ou pas
         * @return res
         */
        public String toString (){
            String res="";
            if(this.estRecouverte() == true){
                res = "La case est recouverte par la lettre " + this.getLettre();
            }else {
                res = "La case est vide";
            }
        return res;
    }      
}