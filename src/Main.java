import java.util.ArrayList;



/*
aa
    ABCDEFGHIJKLMNOPQRSTUVWXYZ
    BDFHJLCPRTXVZNYEIWGAKMUSQO C
    AJDKSIRUXBLHWTMCQGZNPYFVOE C c->
    EKMFLGDQVZNTOWYHXUSPAIBRCJ F D
    YRUHQSLDPXNGOKMIEBFZCWVJAT S F


     ABCDEFGHIJKLMNOPQRSTUVWXYZ
I   EKMFLGDQVZNTOWYHXUSPAIBRCJ k->j
II   AJDKSIRUXBLHWTMCQGZNPYFVOE b c->b
III  BDFHJLCPRTXVZNYEIWGAKMUSQO d d
     YRUHQSLDPXNGOKMIEBFZCWVJAT h

 */










public class Main {
    /*
     * INSTRUKSJON FOR KJØRING AV PROGRAM
     *
     * 1. Lag minimum 1 maskin med default constructor
     *    nigma enigma1 = new Enigma();
     * 2.  Sett inn 3 hjul på det 3 aktive plassene,kan velge mellom 5 hjul I,II,III,IV og V
     *     enigma1.insertWheelAtPositionOne(enigma1.getWheelIII()); osv for PositionTwo og Three
     * 3.  Velg hvilken reflector maskinen skal bruke, kan velge mellom 3 stk(A,B,C)
     *     enigma1.setActivReflector(enigma1.getReflectorB());
     * 4.  Velg ring ofsett for alle 3 hjulene 0 == default velg et tall mellom 0-25(a-z)
     *     enigma1.setRingOffsett(20,17,4);
     * 5.  Velg start verdi for det 3 hjulene.(mellom 0 og 25)
     *     enigma1.setEnigmaActiveWheelsStartingPosition(17,13,4);
     * 6.  VALGFRITT: legg til krossover kabler, her er det bokstaver i par, du må selv passe på å ikke legge inn en bokstav meir en 1 gang
     *     enigma1.addCable('h','k');
     * 7.  Repiter dette for alle boksene du lager, alle instillinger må være like for bokser som skal kunne dekode beskjeder fra andre bokser.
     * 8.  Legg in tekst som du vill inkode, ingen mellomrom,  for å skille ord brukte man til dømes 2 stk XX for å indikere mellomrom
     *     enigma1.planeTextToIncode("abc")
     * 9.  kjør boksen
     *     enigma1.runEnigmaMachine()
     * 10. hent ut kodet melding
     *     String chypher = enigma1.getOutput();
     * 11. kjør chypher tekst gjennom boksen på ny for å få ut orginal teksten, eller kjør gjennom en annen boks som er helt likt innstilt.
     *      enigma1.planeTextToIncode(chypher)
     *      enigma1.runEnigmaMachine()
     *      String melding = enigma1.getOutput(); "abc"
     *      du kan bruke System.out.Println(chyper) osv for å se hva teksten blir.
     * */

    public static void main(String[] args) {



        //lager 3 stk enigma maskiner
        Enigma enigma1 = new Enigma();
        Enigma enigma2 = new Enigma();
        Enigma enigma3 = new Enigma();
        String message;
        String scrambledText;

        //setter opp første boksen

        enigma1.insertWheelAtPositionOne(enigma1.getWheelIII());
        enigma1.insertWheelAtPositionTwo(enigma1.getWheelII());
        enigma1.insertWheelAtPositionthree(enigma1.getWheelI());
        enigma1.setActivReflector(enigma1.getReflectorB());
        enigma1.setRingOffsett(1,3,1);
        enigma1.setEnigmaActiveWheelsStartingPosition(1,4,1);
        //enigma1.addCable('h','k');
        //enigma1.addCable('b','c');
        //enigma1.addCable('l','f');
        //enigma1.addCable('z','a');
        // inkrypter teksten
        System.out.println("kodet melding: ");
        // her skriver du inn teksten du vill kryptere
        message = "you can now put spaces in the text you can now put spaces in the text you can now put spaces in the text you can now put spaces in the text you can now put spaces in the text you can now put spaces in the text you can now put spaces in the text";
        enigma1.planeTextToIncode(message);

        enigma1.runEnigmaMachine();
        scrambledText = enigma1.getOutput();
        System.out.println(scrambledText);
        // kopi av kryptert beskjed til bruk i boks 3
        String stolenText = scrambledText;

        enigma2.insertWheelAtPositionOne(enigma2.getWheelIII());
        enigma2.insertWheelAtPositionTwo(enigma2.getWheelII());
        enigma2.insertWheelAtPositionthree(enigma2.getWheelI());
        enigma2.setActivReflector(enigma2.getReflectorB());
        enigma2.setRingOffsett(1,3,1);
        enigma2.setEnigmaActiveWheelsStartingPosition(1,4,1);
        /*
        enigma2.addCable('h','k');
        enigma2.addCable('b','c');
        enigma2.addCable('l','f');
        enigma2.addCable('z','a');
        */
        System.out.println("Enigma maskin med rett instillinger: ");
        // sender inn krypter beskjed med like innstillinger
        enigma2.planeTextToIncode(scrambledText);
        enigma2.runEnigmaMachine();
        scrambledText = enigma2.getOutput();
        System.out.println(scrambledText);


        // setter opp tredje boksen med kun 1 feil innstillinger
        enigma3.insertWheelAtPositionOne(enigma3.getWheelIII());
        enigma3.insertWheelAtPositionTwo(enigma3.getWheelV());
        enigma3.insertWheelAtPositionthree(enigma3.getWheelI());
        enigma3.setActivReflector(enigma3.getReflectorB());
        // en verdi er satt feil med +1
        enigma3.setRingOffsett(5 ,0,0);
        enigma3.setEnigmaActiveWheelsStartingPosition(4,0,5);
        enigma3.addCable('h','k');
        enigma3.addCable('b','c');
        enigma3.addCable('l','f');
        enigma3.addCable('z','a');

        System.out.println("Enigma maskin med kun en verdi feil instilt: ");
        // sender inn kodet beskjed med feil instillinger
        enigma3.planeTextToIncode(stolenText);
        enigma3.runEnigmaMachine();
        stolenText = enigma3.getOutput();
        System.out.println(stolenText);
        Enigma enigma4 = new Enigma();
        ArrayList<String> hits = new ArrayList<>();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // OBS OBS OBS
        // denne funksjonen tester ca 11 millioner forskjellige kombinasjoner av hjulene og ring offsett, kan ta lang tid
        // på min maskin tok det ca 11min
        //testEnigmaCombos();
        //loopAllSetttings(enigma4,"aa","bd",hits,false);
        //loopAllSetttings(enigma4,"bb","aj",hits,true);
        //for (String s : hits){
        //    System.out.println(s);
        // }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    private static void testEnigmaCombos() {
        Enigma enigma1 = new Enigma();

        String message = "youcannotputspacesinthetextyoucannotputspacesinthetextyoucannotputspacesinthetext" +
                "youcannotputspacesinthetextyoucannotputspacesinthetextyoucannotputspacesinthetext" +
                "youcannotputspacesinthetext";

        enigma1.insertWheelAtPositionOne(enigma1.getWheelII());
        enigma1.insertWheelAtPositionTwo(enigma1.getWheelI());
        enigma1.insertWheelAtPositionthree(enigma1.getWheelIII());
        enigma1.setActivReflector(enigma1.getReflectorB());

        // kan nå også justere ring offsetten.
        //enigma1.setRingOffsett(20,17,4);

        double x = 0d;

        System.out.println("ingen feil funnet etter  :" );
        System.out.println("ingen feil funnet etter  :" +x +" forsøk");
    }

    private static ArrayList<String> loopAllSetttings(Enigma enigma1, String message, String expected, ArrayList<String> hits ,boolean isrun) {



                ArrayList<String> remove = new ArrayList<>();
                for (int a= 0 ; a<26 ; a++) {
                    for (int i= 0 ; i<26 ; i++) {
                        for (int j = 0; j < 26; j++) {
                            for (int k = 0; k < 26; k++) {
                                enigma1.setRingOffsett(a, j, i);
                                enigma1.setEnigmaActiveWheelsStartingPosition(k, j, i);
                                enigma1.planeTextToIncode(message);
                                enigma1.runEnigmaMachine();
                                String text = enigma1.getOutput();

                                if (expected.equalsIgnoreCase(text)) {
                                    //System.out.println("is working : " + text);
                                    if (isrun){
                                        for (String x: hits){
                                            if (!x.contains(a+" "+k+" "+j+" "+i)){
                                                remove.add(x);
                                            }

                                        }
                                    }
                                    else
                                        hits.add(a+" "+k+" "+j+" "+i);

                                    enigma1.setEnigmaActiveWheelsStartingPosition(i, j, k);
                                    enigma1.planeTextToIncode(message);
                                    enigma1.runEnigmaMachine();
                                    text = enigma1.getOutput();

                                    if (expected.equalsIgnoreCase(text)) {
                                        //System.out.println("is working : " + text);
                                        if (isrun){
                                            for (String x: hits){
                                                if (!x.contains(a+" "+i+" "+j+" "+k)){
                                                    remove.add(x);
                                                }

                                            }
                                        }
                                        else
                                            hits.add(a+" "+i+" "+j+" "+k);
                                    }
                                    enigma1.setEnigmaActiveWheelsStartingPosition(j, i, k);
                                    enigma1.planeTextToIncode(message);
                                    enigma1.runEnigmaMachine();
                                    text = enigma1.getOutput();

                                    if (expected.equalsIgnoreCase(text)) {
                                        //System.out.println("is working : " + text);
                                        if (isrun){
                                            for (String x: hits){
                                                if (!x.contains(a+" "+j+" "+i+" "+k)){
                                                    remove.add(x);
                                                }

                                            }
                                        }
                                        else
                                            hits.add(a+" "+j+" "+i+" "+k);
                                    }
                                }

                            }


                        }
                    }
                }
                hits.removeAll(remove);

        return hits;
    }
    public static void Test2(){
        Enigma enigma1 = new Enigma();



        enigma1.insertWheelAtPositionOne(enigma1.getWheelI());
        enigma1.insertWheelAtPositionTwo(enigma1.getWheelII());
        enigma1.insertWheelAtPositionthree(enigma1.getWheelIII());
        enigma1.setActivReflector(enigma1.getReflectorB());
        for (int k= 0 ;k<26 ; k++){
            String message = "a";
            enigma1.setRingOffsett(k,0,0);
            enigma1.setEnigmaActiveWheelsStartingPosition(k,0,0);
            enigma1.planeTextToIncode(message);
            enigma1.runEnigmaMachine();

            String scrambledText = enigma1.getOutput();
            if (scrambledText.equals("b")){
                System.out.println(scrambledText);
                System.out.println(" setting");
            }




        }
    }
}