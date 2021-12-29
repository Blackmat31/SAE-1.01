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
            this.tabFreq= new int[max]; 
            this.nbTotEx= 0;
        }
        /**
         * pré-requis : les éléments de tab sont positifs ou nuls
         * action : crée un multi-ensemble dont le tableau de fréquences est.une copie de tab
         * @param tab
         * 
         */
        public MEE(int[] tab){
            for(int i=0;i<this.tabFreq.length-1;i++){ 
                this.tabFreq[i]=tab[i];
                if (tab[i]!=0) {
                    this.nbTotEx=this.nbTotEx+tab[i];   
                }
        }

        
    }
    /**
     * 
     * @param e
     */
    public MEE(MEE e){
        this(e.tabFreq);
            
    }
    public boolean estVide(){
        return nbTotEx==0;
    }
    public void ajoute(int i){
        this.tabFreq[i]++;
        this.nbTotEx++;

    }
    public boolean retire(int i){
        boolean retire=false;
        if(this.tabFreq[i]>0){
            retire=true;
            this.tabFreq[i]--;
            this.nbTotEx--;

        }
        return retire;


    }
    public int retireAleat(){
        int nbAlea=-1;
        if (this.estVide()==false) {
            nbAlea = Ut.randomMinMax(0, this.tabFreq.length-1);
            while(this.tabFreq[nbAlea]==0) {
                nbAlea = Ut.randomMinMax(0, this.tabFreq.length-1);
        }              
        this.nbTotEx--;
        this.tabFreq[nbAlea]--;
        
        
    }
        return nbAlea;

    }
    public boolean transfere(MEE e,int i){
        boolean transfereOk= false;
        if(i>=0 && i<this.tabFreq.length && this.tabFreq[i]>0){
            e.tabFreq[i]++;
            this.tabFreq[i]--;
            transfereOk=true;
        }
        return transfereOk;
    }
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
    public int sommeValeurs(int[] v){
        int sommeVal=0;
        if(this.tabFreq.length<=v.length){
            for (int i = 0; i<this.tabFreq.length; i++){
                    sommeVal +=this.tabFreq[i]*v[i];
                }
            }
        return sommeVal;

    }
    public int[] getTabFreq(){
        return this.tabFreq;
    }
    public int getTabFreq(int i){
        return this.tabFreq[i];
    }
    public int getCardinal(){
        return this.nbTotEx;
    }
}
