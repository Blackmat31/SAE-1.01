public class MEE{

    private int [] tabFreq; //tabFreq[i] est le nombre d’exemplaires
                            // (fréquence) de l’élément i

    private int nbTotEx;// nombre total d'exmplaires

    /**
     * Pré-requis : max >= 0*
     * Action : crée un multi-ensemble vide dont les éléments seront
     * inférieurs à max
     * @param max
     */

    public MEE (int max){
            this.tabFreq= new int[max]; //On initialise le tableau 'tabFreq' et met 'nbTotEx'à 0
            this.nbTotEx= 0;
        }
        /**
         * pré-requis : les éléments de tab sont positifs ou nuls
         * action : crée un multi-ensemble dont le tableau de fréquences est.une copie de tab
         * @param tab
         * 
         */
        public MEE(int[] tab){
            this(tab.length);
            for(int i=0; i<tab.length; i++){
                this.tabFreq[i] = tab[i];
            }
    
            this.nbTotEx = 0;
            for (int i = 0; i<this.tabFreq.length; i++){
                this.nbTotEx = this.nbTotEx + this.tabFreq[i];
            }
        }
    /**
     * 
     * @param e
     * constructeur par copie
     * On copie les éléments de e pour les mettre dans les this
     */
    public MEE(MEE e){
        this.tabFreq = e.tabFreq;
        this.nbTotEx = e.nbTotEx;
            
    }
    /**
     * 
     * @return
     * Le résultat est vrai si est seulement si l'ensemble est vide
     * 
     */
    public boolean estVide(){
        return nbTotEx==0;
    }
    /**
     *  Ajoute un exemplaire de i à this
     * @param i
     */
    public void ajoute(int i){
        this.tabFreq[i]++;// On ajoute un nouveau élément de i
        this.nbTotEx++;// ON incrémente de 1

    }
    /**
     *  retire un exemplaire de i dans this s'il en existe et retourne vrai si est seulement si cette action a pu être effectuée
     * @param i
     * @return
     */
    public boolean retire(int i){
        boolean retire=false;//On met retire a faux ,pour savoir si on a retirer retirer.
        if(this.tabFreq[i]>0){
            retire=true;
            this.tabFreq[i]--;
            this.nbTotEx--;

        }
        return retire;


    }
    /**
     *  Retire de this un exemplaire choisi aléatoirement et le retourne.
     * @return
     */
    public int retireAleat(){
        int nbAlea=-1;
        if (this.estVide()==false) {
            nbAlea = Ut.randomMinMax(0, this.tabFreq.length-1);
            while(this.tabFreq[nbAlea]==0) {
                nbAlea = Ut.randomMinMax(0, this.tabFreq.length-1);
        }
        this.retire(nbAlea);   
    }
        return nbAlea;

    }
    /**
     * :Tranfère un exemplaire de i de this vers e s'il en existe, et retourne vrai ssi cette action a pu être effectué.
     * @param e
     * @param i
     * @return
     */
    public boolean transfere(MEE e,int i){
        boolean transfereOk= false;
        if(this.tabFreq[i]>0){
            this.ajoute(i);
            this.retire(i);
            transfereOk=true;
        }
        return transfereOk;
    }
    /**
     *  transfère k exemplaires choisis aléatoirement de this vers e dans la limite du contenu de this.
     * @param e
     * @param k
     * @return
     */
    public int transfereAleat(MEE e,int k){
        int comptVal=1;
        int valSelect;
        int res=0;
        while(comptVal<=k){
            valSelect=Ut.randomMinMax(0,(this.tabFreq.length-1));
            this.transfere(e, valSelect);
            if(this.transfere(e, valSelect)==true){
                res++;
            }
            comptVal++;
        }
        return res;
        }
        /**
         *  Retourne la somme des valeurs des exemplaires des éléments de this, la valeur d'un exemplaire d'un élément i de this étant égale a v[i]
         * @param v
         * @return
         */
    public int sommeValeurs(int[] v){
        int sommeVal=0;//On met la somme à O
        
            for (int i = 0; i<this.tabFreq.length; i++){//on fait parcourir le tableau
                    sommeVal +=this.tabFreq[i]*v[i];// on ajoute a la somme le nombre d'exemplaire * la valeur des points.
                }
                return sommeVal;
            }
        

    /**
     * Getter pour tabFreq
     * @return
     */
    public int[] getTabFreq(){
        return this.tabFreq;
    }
    /**
     * getter pour le cardinal 
     * @return
     */
    public int getCardinal(){
        return this.nbTotEx;
    }
    /**
     * le charactère c est une majuscule 
     * Regarde si le caractère est contenue dans le MEE et renvoie si il est ou non.
     * @param c
     * @return
     */

    public boolean contient(char c){
        int i= (int)c-65;
        boolean res=false;
        if(this.tabFreq[i]>0){
            res=true;
        }
        return res;
    }
}