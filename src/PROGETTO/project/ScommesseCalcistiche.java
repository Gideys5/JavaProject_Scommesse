package PROGETTO.project;
/**
 * Classe astratta che rappresenta un'entità generica per le scommesse calcistiche.
 * Le classi concrete che estendono questa classe devono implementare il metodo
 * {@code generaRisultatoPartita} per generare un risultato casuale di una partita calcistica
 * composta da una squadra di casa e una squadra di trasferta.
 * @author Alessandro Aismondo
 * @version 1.0
 */
public abstract class ScommesseCalcistiche {

    /**
     * Genera un risultato casuale per una partita calcistica tra due squadre.
     * @param squadraCasa      La squadra di casa.
     * @param squadraTrasferta La squadra in trasferta.
     * @return Un array di interi rappresentante il risultato della partita, ad esempio [golCasa, golTrasferta].
     */
    protected abstract int[] generaRisultatoPartita(ScommesseCalcio squadraCasa, ScommesseCalcio squadraTrasferta);

}
