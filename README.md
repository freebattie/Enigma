# Enigma I Emulator

This program emulate a WW2 Enigma chyper machine,
## Description

An in-depth paragraph about your project and overview of use.

## INSTRUCTIONS FOR RUNNING PROGRAM
     
      1. Make at least one machine using the default Constructor
         nigma enigma1 = new Enigma();
      2. insert 3 wheels to there active positions,can select between 5 wheels I,II,III,IV and V
          enigma1.insertWheelAtPositionOne(enigma1.getWheelIII()); and so on for PositionTwo and Three
          I use PositionOne as Most right Position and 2 as Middel and 3 as most Left Position, some use oppiste
      3.  Set active reflector, select between 3(A,B,C)
          enigma1.setActivReflector(enigma1.getReflectorB());
      4.  Select ring wheel offset 1 == default select a value between 1-26(a-z)
          enigma1.setRingOffsett(20,17,4);
      5.  Set the start value for all 3 wheels(mellom 1 og 26)
          enigma1.setEnigmaActiveWheelsStartingPosition(17,13,4);
      6.  Optional: add cross over cable, There are no protection against adding sevral cables to the same Characters, so you have to make sure to only swap a character one time
          enigma1.addCable('h','k');
      7.  Repeat this for all bokses you made and all settings need to be the same for them to code and decode the same message.
      8.  Add the text you want to decode
          enigma1.planeTextToIncode("abc")
      9.  run the box
          enigma1.runEnigmaMachine()
      10. get the coded message
          String chypher = enigma1.getOutput();
      11. run the output(chyper) again to see that it turns back to the original message, or run the chyper tru the secund box with the same settings.
           enigma1.planeTextToIncode(chypher)
           enigma1.runEnigmaMachine()
           String melding = enigma1.getOutput(); "abc"
           you can use System.out.Println(chyper) 
     


## Acknowledgments

Learning and testing help
* [Info on the Enigma I inner workings](https://www.cryptomuseum.com/crypto/enigma/working.htm)
* [Wiki](https://en.wikipedia.org/wiki/Enigma_machine)
* [tested agains](http://people.physik.hu-berlin.de/~palloks/js/enigma/enigma-u_v26_en.html)
