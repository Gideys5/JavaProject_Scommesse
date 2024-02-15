package PROGETTO.project;
import java.util.Random;
import java.util.Scanner;
/**
 * Questa classe rappresenta un gioco di scommesse basato sul gioco delle carte Blackjack.
 * Il gioco coinvolge un giocatore che compete contro un mazziere.
 * @author Alessandro Aismondo
 * @version 1.0
 */
public class ScommesseCarte {
    /**
     * Oggetto Random utilizzato per la generazione di numeri casuali.
     */
    Random random = new Random();
    /**
     * Oggetto Scanner utilizzato per l'input da tastiera.
     */
    Scanner tastiera = new Scanner(System.in);

    //VARIABILI D'ISTANZA CHE OGNI OGGETTO AVRA' A SUA DISPOSIZIONE
    /**
     * Valore del mazziere durante il gioco.
     */
    private int valore_mazziere;
    /**
     * Valore complessivo della mano del giocatore durante il gioco.
     */
    private int valoreMano;
    //VARIABILI STATICHE
    /**
     * Valore massimo consentito nel gioco del Blackjack.
     */
    private final int Max = 21;
    /**
    * Costruttore vuoto della classe {@code ScommesseCarte}.
    * Inizializza un nuovo oggetto con valori di mazziere e mano iniziali pari a zero.
    */
    public ScommesseCarte() {
        this(0,0);


    }
    /**
     * Costruttore della classe {@code ScommesseCarte} con parametri.
     *
     * @param valore_mazziere Il valore iniziale del mazziere.
     * @param valoreMano      Il valore iniziale della mano del giocatore.
     */
    public ScommesseCarte(int valore_mazziere, int valoreMano) {
       this.valore_mazziere=valore_mazziere;
       this.valoreMano=valoreMano;
    }
    //METODI SET E GET
    /**
     * Restituisce l'oggetto {@code Random} utilizzato per la generazione di numeri casuali.
     * @return L'oggetto {@code Random}.
     */
    public Random getRandom() {
        return random;
    }
    /**
     * Imposta l'oggetto {@code Random} utilizzato per la generazione di numeri casuali.
     * @param random Il nuovo oggetto {@code Random}.
     */
    public void setRandom(Random random) {
        this.random = random;
    }
    /**
     * Restituisce il valore del mazziere durante il gioco.
     * @return Il valore del mazziere.
     */
    public int getValore_mazziere() {
        return valore_mazziere;
    }
    /**
     * Imposta il valore del mazziere durante il gioco.
     * @param valore_mazziere Il nuovo valore del mazziere.
     */
    public void setValore_mazziere(int valore_mazziere) {
        this.valore_mazziere = valore_mazziere;
    }
    /**
     * Restituisce il valore complessivo della mano del giocatore durante il gioco.
     * @return Il valore della mano del giocatore.
     */
    public int getValoreMano() {
        return valoreMano;
    }
    /**
     * Imposta il valore complessivo della mano del giocatore durante il gioco.
     * @param valoreMano Il nuovo valore della mano del giocatore.
     */
    public void setValoreMano(int valoreMano) {
        this.valoreMano = valoreMano;
    }
    /**
     * Restituisce il valore massimo consentito nel gioco del Blackjack.
     * @return Il valore massimo consentito.
     */
    public int getMax() {
        return Max;
    }
    /**
     * Metodo principale che gestisce il flusso del gioco del Blackjack.
     * I giocatori pescano le carte e decidono se chiedere altre carte o fermarsi.
     * Il mazziere pesca automaticamente fino a raggiungere un punteggio minimo.
     * La vittoria o la sconfitta viene determinata alla fine del gioco.
     */
    public void mano() {
        //INIZIALIZZA LE VARIABILI PER OGNI NUOVA PARTITA
        int manoGiocatore = 0;
        int manoMazziere = 0;
        int asso = 1;
        //ARRAY CON VALORE DELLE CARTE E IL LORO RISPETTIVO SEME
        int[] valori = {asso, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] simboli = {"♥", "♦", "♣", "♠"};
        boolean inGioco = true;
        int cartaGiocatore;
        int semeGiocatore;

        int cartaMazziere;
        int semeMazziere;
        String valoreCarta_M="";
        String simboloSeme_M="";

        do{
            boolean pescataMazziere=false;
            //ESTRAZIONE VALORE E SEME PER IL GIOCATORE
            cartaGiocatore = random.nextInt(valori.length);
            semeGiocatore = random.nextInt(simboli.length);

            //ESTRAI IL VALORE E IL SIMBOLO DELLA CARTA PER IL GIOCATORE PER LA STAMPA
            String valoreCarta = String.valueOf(valori[cartaGiocatore]);
            String semeCarta = simboli[semeGiocatore];

            //INCRMENTO IL VALORE DELLE CARTE IN MANO AL GIOCATORE (IL +1 E' PERCHE' LA POSIZIONE DELLA CARTA E' A -1)
            manoGiocatore+= cartaGiocatore+1;

            //STAMPA LE CARTE PESCATE IN QUESTA MANO
            System.out.println("Carta estratta: " + valoreCarta + " (" + semeCarta + ")");
            System.out.println("Valore delle tue carte:" + manoGiocatore);

            if(manoGiocatore > Max){
                System.out.println("Hai sforato con le carte");
                break;
            }
            if(manoMazziere<17) {
                //ESTRAZIONE VALORE E SEME PER IL MAZZIERE
                cartaMazziere = random.nextInt(valori.length);
                semeMazziere = random.nextInt(simboli.length);

                //ESTRAI IL VALORE E IL SIMBOLO DELLA CARTA PER IL MAZZIERE PER LA STAMPA
                valoreCarta_M = String.valueOf(valori[cartaMazziere]);
                simboloSeme_M = simboli[semeMazziere];

                //INCRMENTO IL VALORE DELLE CARTE IN MANO AL GIOCATORE (IL +1 E' PERCHE' LA POSIZIONE DELLA CARTA E' A -1)
                manoMazziere += cartaMazziere + 1;
                pescataMazziere=true;
            }
            if(pescataMazziere) {
                //STAMPA LE CARTE PESCATE IN QUESTA MANO DAL MAZZIERE
                System.out.println("Carta estratta dal mazziere:" + valoreCarta_M + " (" + simboloSeme_M + ")");
                System.out.println("Valore delle carte del mazziere:" + manoMazziere);
            }
            if(manoMazziere > Max){
                break;
            }
            //MENU DI SCELTA PER IL GIOCATORE
            System.out.println("Vuoi continuare:");
            System.out.println("1)Chiedi carta"             );
            System.out.println("2)Stai (smetti di pescare)" );
            System.out.print("➢ ");
            int scelta;
            do {
                //VERIFICA SE L'INPUT è UN INTERO
                while (!tastiera.hasNextInt()) {
                    System.out.println("Errore: Inserisci un numero intero valido.");
                    tastiera.next(); // Pulisci il buffer dell'input
                }
                //LEGGI IL NUMERO INTERO INSERITO DALL'UTENTE
                scelta = tastiera.nextInt();
                if ((scelta != 2) && (scelta != 1)) {
                    System.out.println("Errore: Non esiste nel Black Jack.");
                    // Pulisci il buffer dell'input prima di chiedere nuovamente all'utente di inserire un numero
                    tastiera.nextLine();
                }
            } while ((scelta != 2) && (scelta != 1));
            if(scelta == 2) {
                inGioco = false;
            }
        }while (inGioco);

        //SE IL GIOCATORE LASCIA E IL MAZZIERE DEVE CONTINUARE A PESCARE
        if(manoGiocatore <= Max && manoMazziere < 17){
            System.out.println("***Il mazziere continua a pescare***");
            do {
                //ESTRAZIONE VALORE E SEME PER IL MAZZIERE
                cartaMazziere = random.nextInt(valori.length);
                semeMazziere = random.nextInt(simboli.length);

                //ESTRAI IL VALORE E IL SIMBOLO DELLA CARTA PER IL MAZZIERE PER LA STAMPA
                valoreCarta_M = String.valueOf(valori[cartaMazziere]);
                simboloSeme_M = simboli[semeMazziere];

                manoMazziere += cartaMazziere + 1;
                //STAMPA LE CARTE PESCATE IN QUESTA MANO DAL MAZZIERE
                System.out.println("Carta estratta dal mazziere:" + valoreCarta_M + " (" + simboloSeme_M + ")");
                System.out.println("Valore delle carte del mazziere:" + manoMazziere);

            }while (manoMazziere < 17);
        }
        //CONDIZIONI DI VITTORIA O DI SCONFITTA
        if(manoGiocatore >Max ||  manoGiocatore<= manoMazziere && manoMazziere <=Max){

              System.out.printf("HAI PERSO, LA TUA PERDITA E' STATA DI  %.2f %n", ScommesseUtente.getPuntata());
              //INSERISCO NELLO STORICO LA PERDITA'
              ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));

        }else if(manoGiocatore<=Max && manoMazziere > Max || manoGiocatore > manoMazziere ){

              System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", ScommesseUtente.getPuntata() * 2);
              ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() +  (ScommesseUtente.getPuntata() * 2));
              //INSERISCO NELLO STORICO LA VINCITA'
              ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * 2));
        }

    }
}