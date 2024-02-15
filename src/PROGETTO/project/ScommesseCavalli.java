package PROGETTO.project;
import java.util.Objects;
import java.util.Random;
/**
 * Questa classe rappresenta un gioco di scommesse basato su una gara di cavalli.
 * Gli utenti possono effettuare scommesse sui cavalli, pronosticando la loro posizione finale.
 * La vittoria o la sconfitta viene determinata in base ai risultati della gara.
 * @author Alessandro Aismondo
 * @version 1.0
 */
public class ScommesseCavalli{
    //VARIABILI D'ISTANZA CHE OGNI OGGETTO AVRA' A SUA DISPOSIZIONE
    /**
     * Nome del cavallo su cui è possibile effettuare scommesse.
     */
    private String nome;
    /**
     * Quota per la vittoria del cavallo.
     */
    private float quota;
    /**
     * Quota per il piazzamento tra i primi tre cavalli.
     */
    private float top3;
    /**
     * Posizione effettiva del cavallo al termine della gara.
     */
    private int arrivo;
    //VARIABILI STATICHE

    //COSTRUTTORI INIZIALI PER LE STATISTICHE DELLE SQUADRE
    /**
     * Costruttore vuoto della classe {@code ScommesseCavalli}.
     * Inizializza un nuovo oggetto con valori di nome, quota e top3 pari a stringa vuota e zero.
     */
    public ScommesseCavalli() {
        this("", 0, 0);
    }
    /**
     * Costruttore della classe {@code ScommesseCavalli} con parametri.
     *
     * @param nome  Il nome del cavallo.
     * @param quota La quota per la vittoria del cavallo.
     * @param top3  La quota per il piazzamento tra i primi tre cavalli.
     */
    public ScommesseCavalli(String nome, float quota, float top3) {
        this.nome = nome;
        this.quota = quota;
        this.top3 = top3;
    }
    /**
     * Restituisce il nome del cavallo.
     * @return Il nome del cavallo.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Imposta il nome del cavallo.
     * @param nome Il nuovo nome del cavallo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce la quota per la vittoria del cavallo.
     * @return La quota per la vittoria del cavallo.
     */
    public float getQuota() {
        return quota;
    }
    /**
     * Imposta la quota per la vittoria del cavallo.
     * @param quota La nuova quota per la vittoria del cavallo.
     */
    public void setQuota(float quota) {
        this.quota = quota;
    }
    /**
     * Restituisce la quota per il piazzamento tra i primi tre cavalli.
     * @return La quota per il piazzamento tra i primi tre cavalli.
     */
    public float getTop3() {
        return top3;
    }
    /**
     * Imposta la quota per il piazzamento tra i primi tre cavalli.
     * @param top3 La nuova quota per il piazzamento tra i primi tre cavalli.
     */
    public void setTop3(float top3) {
        this.top3 = top3;
    }
    /**
     * Restituisce la posizione effettiva del cavallo al termine della gara.
     * @return La posizione effettiva del cavallo al termine della gara.
     */
    public int getArrivo() {
        return arrivo;
    }
    /**
     * Imposta la posizione effettiva del cavallo al termine della gara.
     * @param arrivo La nuova posizione effettiva del cavallo al termine della gara.
     */
    public void setArrivo(int arrivo) {
        this.arrivo = arrivo;
    }
    /**
     * Restituisce una rappresentazione stringa dell'oggetto {@code ScommesseCavalli}.
     * @return Una stringa che contiene il nome del cavallo e le quote per la vittoria e il piazzamento.
     */
    public String toString(){
        return String.format(nome + ": ") + String.format("VINCENTE: %.2f", quota) + String.format(" | TOP 3: %.2f", top3);
    }
    /**
     * Verifica se due oggetti {@code ScommesseCavalli} sono uguali.
     * @param o L'oggetto da confrontare con l'istanza corrente.
     * @return {@code true} se gli oggetti sono uguali, {@code false} altrimenti.
     */    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScommesseCavalli that = (ScommesseCavalli) o;
        return Float.compare(quota, that.quota) == 0 && Float.compare(top3, that.top3) == 0 && arrivo == that.arrivo && Objects.equals(nome, that.nome);
    }
    /**
     * Crea e restituisce una copia dell'oggetto {@code ScommesseCavalli}.
     * @return Una copia dell'oggetto corrente.
     * @throws CloneNotSupportedException Se la clonazione non è supportata.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Genera posizioni casuali per i cavalli in una gara.
     * @param cavalli Un array di oggetti {@code ScommesseCavalli} rappresentanti i cavalli in gara.
     */
    public static void generaPosizioniCasuali(ScommesseCavalli[] cavalli) {
        int i;
        int[] position = {1, 2, 3, 4, 5};
        Random random = new Random();
        for (i = position.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            //Scambia l'elemento corrente con uno casuale
            int temp = position[i];
            position[i] = position[index];
            position[index] = temp;
        }
        System.out.println("Clopette, Clopette, Clopette!");
        // STAMPA DELL'ARRIVO DEI CAVALLI NELLE LORO RISPETTIVE POSIZIONI
        for (i = 0; i < position.length; i++) {
            cavalli[i].arrivo = position[i];
            System.out.println("Il " + cavalli[i].nome + " è arrivato " + cavalli[i].arrivo + "°");
        }
    }

    /**
     * Simula una gara di cavalli e determina i risultati delle scommesse degli utenti.
     * @param cavalli Un array di oggetti {@code ScommesseCavalli} rappresentanti i cavalli in gara.
     */
    public void cavalli(ScommesseCavalli[] cavalli){
        boolean cassa=false;
        generaPosizioniCasuali(cavalli);

        //CONTATORE CHE AGGIORNA IL PRONOSTICO, CONTROLLANDO SE IL CAVALLO SU CUI SI HA PUNTATO HA VINTO O E' ARRIVATO TERZO
        for(int Pronostico = 1; Pronostico<= 5; Pronostico++){
                //CONTROLLO CASO DI PUNTATA SUL CAVALLO E SULLA SUA VITTORIA
                if(ScommesseUtente.getPronostico()==Pronostico && ScommesseUtente.getPronostico_posizione()==1 && cavalli[Pronostico-1].arrivo == 1){
                    System.out.printf("\nHAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * cavalli[Pronostico-1].quota));
                    ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() + (ScommesseUtente.getPuntata() * cavalli[Pronostico-1].quota));
                    //INSERISCO NELLO STORICO LA VINCITA'
                    ScommesseUtente.setStorico(ScommesseUtente.getPuntata() * cavalli[Pronostico-1].quota);
                    cassa=true;
                    break;
                //CONTROLLO CASO DI PUNTATA SUL CAVALLO E SE ARRIVA NELLA TOP3
                } else if ((ScommesseUtente.getPronostico()==Pronostico && ScommesseUtente.getPronostico_posizione()==2 && cavalli[Pronostico-1].arrivo <= 3)){
                    System.out.printf("\nHAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * cavalli[Pronostico-1].top3));
                    ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() + (ScommesseUtente.getPuntata() * cavalli[Pronostico-1].top3));
                    //INSERISCO NELLO STORICO LA VINCITA'
                    ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * cavalli[Pronostico-1].top3));
                    cassa=true;
                    break;
                }
            }
        //SE NON SI VINCE SI COMUNICA LA PERDITA
        if(!cassa){
            System.out.printf("\nHAI PERSO, LA TUA PERDITA E' STATA DI  %.2f %n", ScommesseUtente.getPuntata());
            //INSERISCO NELLO STORICO LA PERDITA'
            ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));

        }
    }

}