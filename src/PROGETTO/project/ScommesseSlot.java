package PROGETTO.project;
import java.util.Scanner;
/**
 * Questa classe rappresenta un gioco di scommesse basato sul gioco delle slot machine.
 * Gli utenti possono effettuare puntate e girare la slot machine, con la possibilità di vincere
 * moltiplicando la puntata per 100 in caso di 4 valori uguali.
 * @author Alessandro Aismondo
 * @version 1.0
 */

public class ScommesseSlot{
    // Variabili statiche
    /**
     * Array statico contenente i simboli della frutta utilizzati nella slot machine.
     */
    private static String[] frutta = {"🍌", "🍎", "🍓", "🍇"};
    /**
     * Scanner utilizzato per l'input da tastiera.
     */
    private final Scanner tastiera = new Scanner(System.in);
    /**
     * Costruttore di default per la classe {@code ScommesseSlot}.
     */
    public ScommesseSlot() {
    }
    /**
     * Restituisce l'array di simboli della frutta.
     *
     * @return Array di simboli della frutta.
     */
    public static String[] getFrutta() {
        return frutta;
    }

    /**
     * Imposta l'array di simboli della frutta.
     *
     * @param frutta Nuovo array di simboli della frutta.
     */
    public static void setFrutta(String[] frutta) {
        ScommesseSlot.frutta = frutta;
    }
    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto {@code ScommesseSlot}.
     * @return Stringa rappresentante l'oggetto.
     */
    @Override
    public String toString() {
        return "ScommesseSlot{}";
    }
    /**
     * Verifica se l'oggetto {@code ScommesseSlot} è uguale a un altro oggetto specificato.
     * @param obj Oggetto con cui confrontare.
     * @return {@code true} se gli oggetti sono uguali, {@code false} altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    /**
     * Crea e restituisce una copia dell'oggetto {@code ScommesseSlot}.
     * @return Copia dell'oggetto.
     * @throws CloneNotSupportedException Se la clonazione non è supportata.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Simula una girata della slot machine, permettendo all'utente di rigiocare fino a quando
     * sceglie di terminare il gioco o ha un saldo insufficiente alla puntata scelta all'inizio.
     */
    // Metodo girata della slot machine
    public void girata() {
        // Loop per consentire all'utente di rigiocare
        boolean continuaGioco = true;
        while (continuaGioco) {
            String[] risultato = new String[4];
            for (int i = 0; i < frutta.length; i++) {
                int index = (int) (Math.random() * frutta.length);
                risultato[i] = frutta[index];
            }
            // Stampa il risultato della girata
            System.out.println("Risultato della girata: " + String.join(" ", risultato));

            // Controlla se ci sono 4 valori uguali --> VITTORIA
            if (risultato[0].equals(risultato[1]) && risultato[1].equals(risultato[2]) && risultato[2].equals(risultato[3])) {
                System.out.println("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
                System.out.printf("HAI VINTO, ECCO LA TUA VINCITA':  %.2f %n", ScommesseUtente.getPuntata() * 100);
                System.out.println("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");

                ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() + (ScommesseUtente.getPuntata() * 100));
                //INSERISCO NELLO STORICO LA VINCITA'
                ScommesseUtente.setStorico((ScommesseUtente.getPuntata() * 100));
            }
            //CONTROLLO PER VERIFICARE CHE IL SALDO DELL'UTENTE SIA SUFFICIENTE RISPETTO ALLA PUNTATA SCELTA ALL'INIZIO
            if(ScommesseUtente.getSaldo()<ScommesseUtente.getPuntata()){
                System.out.println("Il vostro saldo è insufficiente per la puntata,\n " + "la preghiamo di cambiare gioco oppure inserire una puntata più consona alla sue finanze");
                continuaGioco=false;
            }
            //SCELTA DELL'UTENTE CHE SCEGLI DI TERMINARE IL GIOCO O CONTINUARE
            if(continuaGioco){
                System.out.println("Vuoi terminare il gioco? [si][no]");
                String risposta = tastiera.nextLine().toLowerCase();

                if (risposta.equals("si")) {
                    continuaGioco = false;
                }else {
                    //DECREMENTO DEL SALDO DELL'UTENTE RISPETTO ALLA PUNTATA INIZIALE
                    ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() - ScommesseUtente.getPuntata());
                    //INSERISCO NELLO STORICO LA PERDITA'
                    ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));
                    System.out.println("Abbiamo decrementato " + ScommesseUtente.getPuntata() + " dal tuo saldo");
                }
            }
        }
    }
}
