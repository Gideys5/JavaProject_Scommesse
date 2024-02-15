package PROGETTO.project;
import java.util.Objects;
import java.util.Random;
/**
 * Questa classe rappresenta un gioco di scommesse basato su partite di calcio virtuali.
 * Estende la classe astratta ScommesseCalcistiche per la creazione della partita e implementa l'interfaccia Interface_Calcio per i metodi.
 * Fornisce metodi per gestire scommesse legate al calcio.
 * {@link Interface_Calcio}
 * @author Alessandro Aismondo
 * @version 1.0
 */
public class ScommesseCalcio extends ScommesseCalcistiche implements Interface_Calcio {
    // VARIABILI D'ISTANZA CHE OGNI OGGETTO AVRA' A SUA DISPOSIZIONE
    /**
     * La quota iniziale della squadra.
     */
    private float quotaIniziale;
    /**
     * Il nome della squadra.
     */
    private String nome;

    // VARIABILE STATICA --> condivisa da tutti gli oggetti
    /**
     * La quota per le scommesse sul gol.
     */
    private static float gol;
    /**
     * La quota per le scommesse sul no gol.
     */
    private static float noGol;
    /**
     * Costruttore di default per inizializzare le statistiche delle squadre.
     */
    public ScommesseCalcio() {
        this(0, "");
    }
    /**
     * Costruttore con parametri per inizializzare la squadra con una quota iniziale e un nome.
     *
     * @param quotaIniziale La quota iniziale della squadra.
     * @param nome          Il nome della squadra.
     */
    public ScommesseCalcio(float quotaIniziale, String nome) {
        this.quotaIniziale = quotaIniziale;
        this.nome = nome;
    }
    /**
     * Costruttore con un parametro per inizializzare la squadra con un nome.
     * @param nome Il nome della squadra.
     */
    public ScommesseCalcio(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce il nome della squadra.
     * @return Il nome della squadra.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Imposta il nome della squadra.
     * @param nome Il nuovo nome della squadra.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce la quota iniziale della squadra.
     * @return La quota iniziale della squadra.
     */
    public float getQuotaIniziale() {
        return quotaIniziale;
    }
    /**
     * Imposta la quota iniziale della squadra.
     * @param quotaIniziale La nuova quota iniziale della squadra.
     */
    public void setQuotaIniziale(float quotaIniziale) {
        this.quotaIniziale = quotaIniziale;
    }
    /**
     * Restituisce la quota per le scommesse sul gol.
     * @return La quota per le scommesse sul gol.
     */
    public static float getGol() {
        return gol;
    }
    /**
     * Imposta la quota per le scommesse sul gol.
     * @param gol La nuova quota per le scommesse sul gol.
     */
    public static void setGol(float gol) {
        ScommesseCalcio.gol = gol;
    }
    /**
     * Restituisce la quota per le scommesse sul no gol.
     * @return La quota per le scommesse sul no gol.
     */
    public static float getNoGol() {
        return noGol;
    }
    /**
     * Imposta la quota per le scommesse sul no gol.
     * @param noGol La nuova quota per le scommesse sul no gol.
     */
    public static void setNoGol(float noGol) {
        ScommesseCalcio.noGol = noGol;
    }

    @Override
    public String toString() {
        return " " + getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScommesseCalcio that = (ScommesseCalcio) o;
        return Double.compare(quotaIniziale, that.quotaIniziale) == 0 && Objects.equals(nome, that.nome);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Metodo che genera un risultato casuale per una partita calcistica tra due squadre.
     * @param squadraCasa      La squadra di casa.
     * @param squadraTrasferta La squadra in trasferta.
     * @return Un array di interi rappresentante il risultato della partita, ad esempio [golCasa, golTrasferta].
     */

    @Override
    public int[] generaRisultatoPartita(ScommesseCalcio squadraCasa, ScommesseCalcio squadraTrasferta) {
        // Generazione randomica dei gol di casa e trasferta (da 0 a 5)
        Random random = new Random();
        int golCasa = random.nextInt(6);
        int golTrasferta = random.nextInt(6);
        if (squadraCasa.quotaIniziale < 1.30) {
            golCasa += 1;
        }

        //STAMPA DEL RISULTATO DELLA PARTITA
        System.out.println("fiiii, fiiii, fiiiiii!");
        System.out.println("RISULTATO: " + squadraCasa + " " + golCasa + "  -  " + squadraTrasferta + " " + golTrasferta);

        return new int[]{golCasa, golTrasferta};
    }
    /**
     * Metodo per gestire una scommessa calcistica tra due squadre e per assegnare l-eventuale vincità
     * @param squadra_casa      La squadra di casa.
     * @param squadra_trasferta La squadra in trasferta.
     * @throws Exception Se si verifica un'eccezione durante la gestione della scommessa.
     */
    public void scommessa(ScommesseCalcio squadra_casa, ScommesseCalcio squadra_trasferta) throws Exception {
        int[] risultato = generaRisultatoPartita(squadra_casa, squadra_trasferta);
        int golCasa = risultato[0];
        int golTrasferta = risultato[1];

        //1)CONDIZIONE DI VITTORIA, VINCE SQUADRA CASA
        if (golCasa > golTrasferta && ScommesseUtente.getPronostico() == 1) {

            System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * squadra_casa.quotaIniziale));
            ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() + (ScommesseUtente.getPuntata() * squadra_casa.quotaIniziale));

            //INSERISCO NELLO STORICO LA VINCITA'
            ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * squadra_casa.quotaIniziale));

            //2)CONDIZIONE DI VITTORIA, PAREGGIO
        } else if (golCasa == golTrasferta && ScommesseUtente.getPronostico() == 2) {
            System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * ((squadra_casa.quotaIniziale + squadra_trasferta.quotaIniziale) / 2)));
            ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() +  (ScommesseUtente.getPuntata() * ((squadra_casa.quotaIniziale + squadra_trasferta.quotaIniziale) / 2)));

            //INSERISCO NELLO STORICO LA VINCITA'
            ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * ((squadra_casa.quotaIniziale + squadra_trasferta.quotaIniziale) / 2)));

            //3)CONDIZIONE DI VITTORIA, VINCE SQUADRA TRASFERTA
        } else if (golTrasferta > golCasa && ScommesseUtente.getPronostico() == 3) {
            System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * squadra_trasferta.quotaIniziale));
            ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() + (ScommesseUtente.getPuntata() * squadra_trasferta.quotaIniziale));

            //INSERISCO NELLO STORICO LA VINCITA'
            ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * squadra_trasferta.quotaIniziale));

            //4)CONDIZIONE DI VITTORIA, ENTRAMBI SEGNANO ALMENO UN GOL
        } else if (golCasa > 0 && golTrasferta > 0 && ScommesseUtente.getPronostico() == 4) {
            System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * ScommesseCalcio.gol));
            ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() +  (ScommesseUtente.getPuntata() * ScommesseCalcio.gol));

            //INSERISCO NELLO STORICO LA VINCITA'
            ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * ScommesseCalcio.gol));


            ////5)CONDIZIONE DI VITTORIA, ENTRAMBI NON SEGNANO ALMENO UN GOL OPPURE UNO DEI DUE NON SEGNA NEANCHE UN GOL
        } else if (ScommesseUtente.getPronostico() == 5) {
            if (golTrasferta == 0 || golCasa == 0) {
                System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", (ScommesseUtente.getPuntata() * ScommesseCalcio.noGol));
                ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() +  (ScommesseUtente.getPuntata() * ScommesseCalcio.noGol));

                //INSERISCO NELLO STORICO LA VINCITA'
                ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * ScommesseCalcio.noGol));

            } else {
                System.out.printf("HAI PERSO, LA TUA PERDITA E' STATA DI  %.2f %n", ScommesseUtente.getPuntata());
                //INSERISCO NELLO STORICO LA PERDITA'
                ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));
            }

        } else System.out.printf("HAI PERSO, LA TUA PERDITA E' STATA DI  %.2f %n", ScommesseUtente.getPuntata());
        //INSERISCO NELLO STORICO LA PERDITA'
        ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));

        if(golCasa > golTrasferta){
            if (squadra_casa.getQuotaIniziale() > 1.20) {
                squadra_casa.quotaIniziale -= 0.05F;
                squadra_trasferta.quotaIniziale += 0.05F;
            }
        }
        if(golTrasferta > golCasa){
            if(squadra_trasferta.getQuotaIniziale() > 1.20){
                squadra_casa.setQuotaIniziale((float) (squadra_casa.getQuotaIniziale() + 0.05));
                squadra_trasferta.setQuotaIniziale((float) (squadra_trasferta.getQuotaIniziale() - 0.05));
            }

        }
    }
}