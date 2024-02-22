package PROGETTO.main;
import PROGETTO.project.*;
import java.util.Scanner;
import java.util.Random;

public class MainScommesse {
    public static void main(String[] args) {

        //CREAZIONE DEGLI OGGETTI SQUADRA DANDO NOMI DI 10 SQUADRE REALI
        ScommesseCalcio Milan = new ScommesseCalcio("Milan");
        ScommesseCalcio Juventus = new ScommesseCalcio("Juventus");
        ScommesseCalcio Inter = new ScommesseCalcio("Inter");
        ScommesseCalcio Napoli = new ScommesseCalcio("Napoli");
        ScommesseCalcio Roma = new ScommesseCalcio("Roma");
        ScommesseCalcio Lazio = new ScommesseCalcio("Lazio");
        ScommesseCalcio Catanzaro = new ScommesseCalcio("Catanzaro");
        ScommesseCalcio Fanfulla = new ScommesseCalcio("Fanfulla");
        ScommesseCalcio Udinese = new ScommesseCalcio("Udinese");
        ScommesseCalcio Fiorentina = new ScommesseCalcio("Fiorentina");

        //ARRAY CHE CONTIENE I NOMI DELLE SQUADRE DA UTILIZZARE PER LE SCOMMESSE
        ScommesseCalcio[] squadre = {Milan, Juventus, Inter, Napoli, Roma, Lazio, Catanzaro, Fanfulla, Udinese, Fiorentina};

        //OGGETTI CLASSE SCOMMESSE CALCIO
        ScommesseCalcio Partita = new ScommesseCalcio();

        //ASSEGNAMENTO DI UNA QUOTA INIZIALE IN BASE ALLA QUALITA' DELLA SQUADRA (PIU' FORTE E' LA SQUADRA MENO ALTA SARA' LA QUOTA e VICEVERSA)
        try {
            Milan.setQuotaIniziale(1.40F);
            Juventus.setQuotaIniziale(1.30F);
            Inter.setQuotaIniziale(1.50F);
            Napoli.setQuotaIniziale(1.65F);
            Roma.setQuotaIniziale(1.80F);
            Lazio.setQuotaIniziale(1.75F);
            Catanzaro.setQuotaIniziale(2.00F);
            Fanfulla.setQuotaIniziale(2.20F);
            Udinese.setQuotaIniziale(1.84F);
            Fiorentina.setQuotaIniziale(1.90F);
        } catch (Exception e) {throw new RuntimeException(e);}
        //_______________________________________________________________________________________________________________

        //CREAZIONE DEGLI OGGETTI PER SCOMMESSECAVALLI
        ScommesseCavalli[] cavalli = new ScommesseCavalli[5];
        ScommesseCavalli gara = new ScommesseCavalli();

        //MESCOLA L'ARRAY DEI NOMI PER AVERE UN ORDINE DIVERSO A OGNI ESECUZIONE DEL PROGRAMMA
        String[] nomi_Cavalli = {"Pegaso","Baldo","Fulmine","Elprimo","Messi"};
        Random random = new Random();
        for (int i = nomi_Cavalli.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Scambia l'elemento corrente con uno casuale
            String temp = nomi_Cavalli[i];
            nomi_Cavalli[i] = nomi_Cavalli[index];
            nomi_Cavalli[index] = temp;
        }
        //CREAZIONE EFFETTIVA DEGLI OGGETTI CAVALLI
        for (int i = 0; i < 5; i++) {
            String nomeCavallo = "Cavallo " + (nomi_Cavalli[i]);
            float quotaCavallo = (float) (1.3 + (Math.random() * (3.8 - 1.3)));  // genera un numero casuale da 1.3 a 3.8
            float top3Cavallo = (float) (quotaCavallo - 0.20);

            ScommesseCavalli cavallo = new ScommesseCavalli(nomeCavallo, quotaCavallo, top3Cavallo);
            //INSERIMENTO NELL'ARRAY
            cavalli[i] = cavallo;
        }

        //_______________________________________________________________________________________________________________
        //CREAZIONE DEGLI OGGETTI PER SCOMMESSECARTE PER GESTIRE LA FUNZIONE
        ScommesseCarte mazziere = new ScommesseCarte();
        //_______________________________________________________________________________________________________________
        //CREAZIONE DEGLI OGGETTI PER SCOMMESSESLOT PER GESTIRE LA FUNZIONE
        ScommesseSlot gira = new ScommesseSlot();
        //_______________________________________________________________________________________________________________

        Scanner tastiera = new Scanner(System.in);//OGGETTO TASTIERA PER RICEVERE INPUT DALL'UTENTE
        int scelta = 0;//VARIABILE PER SCEGLIERE IL GIOCO
        int eta;//CONTROLLO INIZIALE

        boolean itsbet = true;//...
        //CONTROLLO DELL'ETA' PER VERIFICARE CHE IL GIOCATORE SIA MAGGIORENNE
        System.out.print("Ricordiamo che il gioco d'azzardo è vietato ai minori di 18 anni, quanti anni hai?");
        while (!tastiera.hasNextInt()) {
            System.out.println("Errore: La tua età non è valida, si prega di reinserirla.");
            tastiera.next(); // Pulisci il buffer dell'input
        }
        eta = tastiera.nextInt();
        tastiera.nextLine();
        if (eta < 18  || eta > 200) {
            if(eta<18) {
                System.out.println("Sei troppo piccolo, mi dispiace");
            } else
                System.out.println("Il tuo documento non è valido, mi dispiace");
        } else {
            do {
                //INSERIMENTO DEL PROPRIO SALDO DI GIOCO APPENA SI ENTRA
                System.out.print("Visto che non hai un conto di gioco, deposita ora per poter giocare:");
                if (!tastiera.hasNextFloat()) {
                    System.out.println("INSERISCI UN VALORE CORRETTO SUL CONTO!");
                    tastiera.next(); // Pulisce il buffer dell'input
                } else {
                    ScommesseUtente.setSaldo(tastiera.nextFloat());
                    if (ScommesseUtente.getSaldo() <= 1) {
                        System.out.println("NON PUOI AVERE UN SALDO IN NEGATIVO, NON ANCORA ALMENO");
                    }
                    if (ScommesseUtente.getSaldo() > 100000) {
                        System.out.println("NON PUOI AVERE UN SALDO COSI' ALTO (E NON LO AVRAI MAI)");
                    }
                }
            } while (ScommesseUtente.getSaldo() <= 1 || ScommesseUtente.getSaldo() >100000);

            do {
                try {
                    //MENU DI SCELTA DEL CENTRO SCOMMESSE
                    System.out.print("_________________________________________________________________________________________________\n");
                    System.out.print("Nel nostro centro scommesse hai la possibilità di giocare sui seguenti sport:                   |\n");
                    System.out.print("1. Calcio scommesse                                                                             |\n");
                    System.out.print("2. Corse di cavalli                                                                             |\n");
                    System.out.print("3. Black Jack                                                                                   |\n");
                    System.out.print("4. Slot Machine                                                                                 |\n");
                    System.out.print("5. Esci dal centro scommesse                                                                    |\n");
                    System.out.print("Scegli a cosa vuoi giocare ➢ ");
                    while (!tastiera.hasNextInt()) {
                        System.out.println("Errore: Inserisci un numero intero valido.");
                        tastiera.next(); // Pulisci il buffer dell'input
                    }
                    scelta = tastiera.nextInt();

                    switch (scelta) {
                        case 1://SCOMESSE CALCISTICHE

                            //CREAZIONE DELLE QUOTE GOL E NOGOL
                            ScommesseCalcio.setGol( (float) (1.80 + random.nextFloat() * (2.10 - 1.80)));
                            ScommesseCalcio.setNoGol( (float) (1.40 + random.nextFloat() * (1.80 - 1.40)));

                            //OUTPUT CHE MOSTRA LA SIMULAZIONE DEL PRE-PARTITA
                            System.out.println("La prossima partita è tra le due seguenti squadre:\n");
                            mescolaArray(squadre);//RICHIAMO DELLA FUNZIONE PER SCEGLIERE SQUADRE DIVERSE

                            //ESTRAZIONE SQAUDRE CASUALI PER LA SCOMMESSA
                            ScommesseCalcio casa = squadre[0];
                            ScommesseCalcio trasferta = squadre[1];

                            //CREAZIONE QUOTA PAREGGIO
                            float quotapareggio = (squadre[0].getQuotaIniziale() +  squadre[1].getQuotaIniziale()) / 2;

                            //MENU DI SCELTA DEI PRONOSTICI
                            System.out.println(casa + " - " + trasferta);
                            System.out.println("\nSCEGLI LA TUA SCOMMESSA:");
                            System.out.printf("1) Vince "+ squadre[0].getNome()+ " (%.2f)%n",  squadre[0].getQuotaIniziale()    );
                            System.out.printf("2) Pareggio (%.2f)%n", quotapareggio                                             );
                            System.out.printf("3) Vince " + squadre[1].getNome() + " (%.2f)%n", squadre[1].getQuotaIniziale()   );
                            System.out.printf("4) GG (%.2f)%n", ScommesseCalcio.getGol()                                        );
                            System.out.printf("5) NG (%.2f)%n", ScommesseCalcio.getNoGol()                                      );
                            System.out.print("6) Salta la partita                                                            \n");
                            System.out.print("Scegli la tua giocata ➢ ");

                            //FUNZIONE PER SCEGLIERE I PRONOSTICI CON I CONTROLLI
                            SelezionePronostico(tastiera);

                            if(ScommesseUtente.getPronostico() != 6) {
                                //FUNZIONE PER SCEGLIERE LA PUNTATA CON I CONTROLLI
                                SelezionePuntata(tastiera);

                                //RICHIAMO DELLA FUNZIONE PER LA CREAZIONE DELLA PARTITA VIRTUALE
                                Partita.scommessa(casa, trasferta);
                                System.out.printf("ECCO IL TUO SALDO ATTUALE: %.2f %n", ScommesseUtente.getSaldo());

                            }else System.out.println("Per ora i tuoi soldi sono al sicuro");
                            break;

                        case 2://SCOMESSE CAVALLI
                            System.out.println("I cavalli sono ai blocchi di partenza:");
                            System.out.println("\nSCEGLI IL TUO CAVALLO:");
                            //MENU DI SCELTA DEI PRONOSTICI (CAVALLI)
                            System.out.println("1) " +  cavalli[0]);
                            System.out.println("2) " +  cavalli[1]);
                            System.out.println("3) " +  cavalli[2]);
                            System.out.println("4) " +  cavalli[3]);
                            System.out.println("5) " +  cavalli[4]);
                            System.out.print("6) Salta la gara \n");
                            System.out.print("Scegli su chi puntare ➢ ");

                            //FUNZIONE PER SCEGLIERE I PRONOSTICI CON I CONTROLLI
                            SelezionePronostico(tastiera);

                            //SCELTA DEL PRONOSTICO DELLA POSIZIONE
                            if(ScommesseUtente.getPronostico() != 6) {
                                System.out.println("PRONOSTICO:");
                                System.out.println(cavalli[ScommesseUtente.getPronostico()-1]);
                                System.out.println("1)Il cavallo vince la gara"              );
                                System.out.println("2)Il cavallo arriva nella Top 3"         );
                                System.out.print("Scegli il tuo pronostico ➢ ");

                                //FUNZIONE PER SCEGLIERE I PRONOSTICI CON I CONTROLLI
                                Pronostico_posizione(tastiera);

                                //SCELTA DELLA PUNTATA
                                SelezionePuntata(tastiera);

                                //RICHIAMO DELLA FUNZIONE PER LA CREAZIONE DELLA GARA VIRTUALE
                                gara.cavalli(cavalli);
                                System.out.printf("ECCO IL TUO SALDO ATTUALE: %.2f %n", ScommesseUtente.getSaldo());

                            }else System.out.println("Per ora i tuoi soldi sono al sicuro");
                            break;

                        case 3://BLACK JACK

                            //ENTRO AL BANCO DA GIOCO CON LA PUNTATA
                            System.out.println("\nCOMINCIAMO, prima di entrare al tavolo devi fare la puntata:");
                            SelezionePuntata(tastiera);
                            //INSERISCO NELLO STORICO LA PUNTATA
                            ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));


                            //RICHIAMO DELLA FUNZIONE MANO
                            mazziere.mano();
                            System.out.printf("ECCO IL TUO SALDO ATTUALE: %.2f %n", ScommesseUtente.getSaldo());
                            break;

                        case 4://SLOT
                            System.out.println("LE REGOLE DELLE SLOT SONO SEMPLICI, VINCI X100 SE ESCONO 4 FIGURE UGUALI!!!");
                            System.out.println("[OGNI VOLTA CHE GIRERAI LA SLOT VERRA' DECREMENTATA DAL TUO CONTO LA PUNTATA CHE HAI FATTO INIZIALMENTE,ATTENTO!!!]");
                            System.out.println("Vuoi iniziare a giocare?[si][no]");
                            tastiera.nextLine();//PULISCE IL BUFFER
                            while (true) {
                                String inizioSlot = tastiera.next().toLowerCase(); // Converte l'input in minuscolo per gestire sia "si" che "Si" ecc.
                                try {
                                    if (inizioSlot.equals("si")) {
                                        SelezionePuntata(tastiera);
                                        //INSERISCO NELLO STORICO LA PUNTATA
                                        ScommesseUtente.setStorico(ScommesseUtente.getPuntata()* (-1));
                                        gira.girata();
                                        System.out.printf("ECCO IL TUO SALDO ATTUALE: %.2f %n", ScommesseUtente.getSaldo());
                                        break;
                                    } else if (inizioSlot.equals("no")) {
                                        // Codice per chiudere il caso o fare altro in caso di "no"
                                        break;
                                    } else {
                                        // Se l'utente ha inserito una risposta diversa da "si" o "no"
                                        System.out.println("Inserisci solo 'si' o 'no'. Riprova:");
                                        inizioSlot = tastiera.nextLine().toLowerCase(); // Richiedi l'input dell'utente di nuovo
                                    }
                                } catch (Exception e) {
                                    // Gestione generale delle eccezioni (se necessario)
                                    System.out.println("Si è verificato un errore. Riprova:");
                                    inizioSlot = tastiera.nextLine().toLowerCase(); // Richiedi l'input dell'utente di nuovo
                                }
                            }
                            break;

                        case 5:
                            if(ScommesseUtente.getStorico().isEmpty()){
                                System.out.println("Non hai effettuato alcuna scommessa, peccato");

                            }else {
                                System.out.println("ECCO IL TUO STORICO DI VINCITE E PERDITE:");
                                ScommesseUtente.getStorico().forEach(System.out::println);
                            }
                            System.out.println("\nArrivederci stirato");
                            break;
                        case 365:
                            if (itsbet) {
                                System.out.println("Ciauuu, eccoti un regalino dal capo dell'ITSBET 365");
                                ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() * 365);
                                itsbet = false;
                            }
                            // Il codice successivo al blocco if verrà eseguito indipendentemente dall'esecuzione o meno del blocco if
                            break;
                        default:
                            System.out.println("scelta non valida, si prega di reinserire un valore corretto!");
                    }
                    if (ScommesseUtente.getSaldo() <=1){
                        System.out.println("Ti abbiamo sbattuto fuori non hai più un soldo, torna a trovarci quando avrai la fresca");
                        System.out.println("ECCO LO STORICO DELLE TUE VINCITE E PERDITE:");
                        ScommesseUtente.getStorico().forEach(System.out::println);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            } while (scelta != 5  && ScommesseUtente.getSaldo() > 1);
        }
    }

    //FUNZIONE PER SCEGLIERE IL PRONOSTICO SULL'ARRIVO DI UN CAVALLO (PRIMO O NELLA TOP 3)
    private static void Pronostico_posizione(Scanner tastiera) {
        int intervallo;
        do {
            // Verifica se l'input è un intero
            while (!tastiera.hasNextInt()) {
                System.out.println("Errore: Inserisci un numero intero valido.");
                tastiera.next(); // Pulisci il buffer dell'input
            }

            // Leggi il numero intero inserito dall'utente
            intervallo = tastiera.nextInt();

            if ((intervallo != 2) && (intervallo != 1)) {
                System.out.println("Errore: Pronostico non valido.");

                // Pulisci il buffer dell'input prima di chiedere nuovamente all'utente di inserire un numero
                tastiera.nextLine();
            }
        } while ((intervallo != 2) && (intervallo != 1));
        ScommesseUtente.setPronostico_posizione(intervallo);
    }

    //FUNZIONE PER ESEGUIRE I CONTROLLI SULLA PUNTATA CHE SI VUOLE EFFETTUARE
    private static void SelezionePuntata(Scanner tastiera) {
        System.out.printf("SALDO DISPONIBILE: %.2f %n", ScommesseUtente.getSaldo());
        System.out.println("Scegli la tua puntata:");
        do {
            ScommesseUtente.setPuntata(0);
            if (!tastiera.hasNextFloat()) {
                System.out.println("Errore: Inserisci una puntata valida!");
                tastiera.next(); // Pulisci il buffer dell'input
            } else {
                ScommesseUtente.setPuntata(tastiera.nextFloat());
                if (ScommesseUtente.getPuntata() > ScommesseUtente.getSaldo()|| ScommesseUtente.getPuntata() < 1) {
                    System.out.println("Errore: Inserisci una puntata valida!");
                }
            }
        } while (ScommesseUtente.getPuntata() > ScommesseUtente.getSaldo() || ScommesseUtente.getPuntata() < 1);

        //DIMINUZIONE DEL SALDO IN BASE ALLA PUNTATA
        ScommesseUtente.setSaldo(ScommesseUtente.getSaldo() - ScommesseUtente.getPuntata());
    }

    //FUNZIONE PER LA SCELTA DEL PRONOSTICO NELLE FUNZIONI SCOMMESSE
    private static void SelezionePronostico(Scanner tastiera) {
        int intervallo;

        // Utilizza un ciclo do-while per assicurarti che l'utente fornisca un input valido
        do {
            // Verifica se l'input è un intero
            while (!tastiera.hasNextInt()) {
                System.out.println("Errore: Inserisci un numero intero valido.");
                tastiera.next(); // Pulisci il buffer dell'input
            }
            // Leggi il numero intero inserito dall'utente
            intervallo = tastiera.nextInt();

            // Controlla se il numero è compreso tra 1 e 6
            if (intervallo < 1 || intervallo > 6) {
                System.out.println("Errore: Pronostico non valido.");

                // Pulisci il buffer dell'input prima di chiedere nuovamente all'utente di inserire un numero
                tastiera.nextLine();
            }
        } while (intervallo < 1 || intervallo > 6);

        ScommesseUtente.setPronostico(intervallo);
    }

    //FUNZIONE CHE MESCOLA GLI ELEMENTI DI UN ARRAY IN POSIZIONI DIVERSE
    static void mescolaArray(ScommesseCalcio[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Scambia l'elemento corrente con uno casuale
            ScommesseCalcio temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }
}
