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
       
        if(max>=0){
            this.tabFreq= new int[26]; 
            this.nbTotEx= max;
           

            for(int i=0;i<this.tabFreq.length-1;i++){ //on remplit le tableau de zéro
                this.tabFreq[i]=0;

            }

            }
        }
        /**
         * pré-requis : les éléments de tab sont positifs ou nuls
         * action : crée un multi-ensemble dont le tableau de fréquences est.une copie de tab
         * @param tab
         * 
         */
        public MEE(int[] tab){
            this.tabFreq= new int[26]; 
            for(int i=0;i<this.tabFreq.length-1;i++){ 
                this.tabFreq[i]=tab[i];
                this.nbTotEx=this.nbTotEx+tab[i];
        }

        
    }
    /**
     * 
     * @param e
     */
    public MEE(MEE e){
        this.tabFreq= new int[26];
        this.nbTotEx=e.nbTotEx;
        for(int i=0;i<this.tabFreq.length-1;i++){ 
            this.tabFreq[i]=e.tabFreq[i];
            
    }
    }
    public boolean estVide(){
        return nbTotEx==0;
    }
    public void ajoute(int i){
        this.tabFreq[i]++;


    }
    public boolean retire(int i){
        boolean retire=false;
        if(this.tabFreq[i]>0){
            retire=true;
            this.tabFreq[i]--;

        }
        return retire;


    }
    public int retireAleat(){
        return this.nbTotEx

    }
    public boolean transfere(MEE e,int i){

    }
    public int transfereAleat(MEE e,int k){

    }
    public int sommeValeurs(int[] v){

    }
}


