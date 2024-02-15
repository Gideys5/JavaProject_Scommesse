package PROGETTO.project;
import java.util.ArrayList;
/**
 * Questa classe rappresenta le informazioni relative alle scommesse effettuate da un utente.
 * Ogni utente ha un saldo, effettua un pronostico sulla posizione di un cavallo, imposta la posizione prevista
 * e scommette una certa quantità di denaro. Vengono anche registrati gli storici delle scommesse.
 * @author [Nome dell'autore]
 * @version 1.0
 */
public class ScommesseUtente {
    /**
     * Saldo dell'utente, rappresenta la quantità di denaro disponibile per le scommesse.
     */
    private static float saldo;
    /**
     * Pronostico dell'utente sulla posizione di un cavallo nella gara.
     */
    private static int pronostico;
    /**
     * Posizione prevista del cavallo associato al pronostico dell'utente.
     */
    private static int pronostico_posizione;
    /**
     * Importo scommesso dall'utente sulla gara.
     */
    private static float puntata;
    /**
     * Lista che tiene traccia degli storici delle scommesse effettuate dall'utente.
     */
    private static final ArrayList<Float> storico = new ArrayList<>(100);

    //COSTRUTTORI INIZIALI PER LE VARIABILI UTENTE
    /**
     * Costruttore vuoto della classe {@code ScommesseUtente}.
     */
    public ScommesseUtente() {
        this(0,0,0,0);
    }
    /**
     * Costruttore della classe {@code ScommesseUtente}.
     *
     * @param saldo               Saldo dell'utente.
     * @param pronostico          Pronostico dell'utente sulla posizione di un cavallo nella gara.
     * @param pronostico_posizione Posizione prevista del cavallo associato al pronostico dell'utente.
     * @param puntata             Importo scommesso dall'utente sulla gara.
     */
    public  ScommesseUtente(float saldo, int pronostico, int pronostico_posizione, float puntata){

        ScommesseUtente.saldo =saldo;
        ScommesseUtente.pronostico =pronostico;
        ScommesseUtente.pronostico_posizione =pronostico_posizione;
        ScommesseUtente.puntata =puntata;

    }

    /**
     * Restituisce il saldo dell'utente.
     * @return Il saldo dell'utente.
     */
    public static float getSaldo() {
        return saldo;
    }

    /**
     * Imposta il saldo dell'utente.
     * @param saldo Il nuovo saldo dell'utente.
     */
    public static void setSaldo(float saldo) {
        ScommesseUtente.saldo = saldo;
    }

    /**
     * Restituisce il pronostico dell'utente sulla posizione di un cavallo nella gara.
     * @return Il pronostico dell'utente.
     */
    public static int getPronostico() {
        return pronostico;
    }

    /**
     * Imposta il pronostico dell'utente sulla posizione di un cavallo nella gara.
     * @param pronostico Il nuovo pronostico dell'utente.
     */
    public static void setPronostico(int pronostico) {
        ScommesseUtente.pronostico = pronostico;
    }

    /**
     * Restituisce la posizione prevista del cavallo associato al pronostico dell'utente.
     * @return La posizione prevista del cavallo.
     */
    public static int getPronostico_posizione() {
        return pronostico_posizione;
    }

    /**
     * Imposta la posizione prevista del cavallo associato al pronostico dell'utente.
     * @param pronostico_posizione La nuova posizione prevista del cavallo.
     */
    public static void setPronostico_posizione(int pronostico_posizione) {
        ScommesseUtente.pronostico_posizione = pronostico_posizione;
    }

    /**
     * Restituisce l'importo scommesso dall'utente sulla gara.
     * @return L'importo scommesso dall'utente.
     */
    public static float getPuntata() {
        return puntata;
    }

    /**
     * Imposta l'importo scommesso dall'utente sulla gara.
     * @param puntata Il nuovo importo scommesso dall'utente.
     */
    public static void setPuntata(float puntata) {
        ScommesseUtente.puntata = puntata;
    }

    /**
     * Restituisce la lista degli storici delle scommesse effettuate dall'utente.
     * @return La lista degli storici delle scommesse dell'utente.
     */
    public static ArrayList<Float> getStorico() {return storico;}
    /**
     * Aggiunge una nuova scommessa agli storici dell'utente.
     * @param puntata L'importo scommesso da aggiungere agli storici.
     */
    public static void setStorico(float puntata) {
        ScommesseUtente.storico.add(puntata);
    }

    //METODI toString, Clone, Equals
    @Override
    public String toString() {
        return "ScommesseUtente{}";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
