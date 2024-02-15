package PROGETTO.project;
/**
 * L'interfaccia Interface_Calcio definisce i metodi associati alle scommesse e ai risultati
 * delle partite di calcio.
 * @author Alessandro Aismondo
 * @version 1.0
 */
public interface Interface_Calcio {

    /**
     * Gestisce una scommessa virtuale tra due squadre di calcio.
     * @param squadra_casa      Oggetto rappresentante la squadra di casa
     * @param squadra_trasferta Oggetto rappresentante la squadra in trasferta
     * @throws Exception Se si verifica un errore durante la gestione della scommessa
     */
    void scommessa(ScommesseCalcio squadra_casa, ScommesseCalcio squadra_trasferta) throws Exception;
    /**
     * Genera un risultato di partita tra due squadre di calcio.
     * @param squadraCasa      Oggetto rappresentante la squadra di casa
     * @param squadraTrasferta Oggetto rappresentante la squadra in trasferta
     * @return Un array contenente il risultato della partita (es. [2, 1])
     * @throws Exception Se si verifica un errore durante la generazione del risultato
     */
    int[] generaRisultatoPartita(ScommesseCalcio squadraCasa, ScommesseCalcio squadraTrasferta) throws Exception;

}