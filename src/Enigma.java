import java.util.ArrayList;

class Enigma {


    private CodeWheel[] activeWheels = new CodeWheel[3];
    private CodeWheel[] storedWheels = new CodeWheel[10];
    ArrayList<char[]> cables = new ArrayList<>();
    private CodeWheel reflector;
    private CodeWheel etw;
    private String output;
    private String input;
    private int index = 0;

    public CodeWheel getWheelI() {
        return storedWheels[0];
    }

    public CodeWheel getWheelII() {
        return storedWheels[1];
    }

    public CodeWheel getWheelIII() {
        return storedWheels[2];
    }

    public CodeWheel getWheelIV() {
        return storedWheels[3];
    }

    public CodeWheel getWheelV() {
        return storedWheels[4];
    }

    public CodeWheel getReflectorA() {
        return storedWheels[5];
    }

    public CodeWheel getReflectorB() {
        return storedWheels[6];
    }

    public CodeWheel getReflectorC() {
        return storedWheels[7];
    }

    Enigma() {
        CodeWheel wheel1 = new CodeWheel("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 16);
        CodeWheel wheel2 = new CodeWheel("AJDKSIRUXBLHWTMCQGZNPYFVOE", 4);
        CodeWheel wheel3 = new CodeWheel("BDFHJLCPRTXVZNYEIWGAKMUSQO", 21);
        CodeWheel wheel4 = new CodeWheel("ESOVPZJAYQUIRHXLNFTGKDCMWB", 10);
        CodeWheel wheel5 = new CodeWheel("VZBRGITYUPSDNHLXAWMJQOFECK", 0);
        CodeWheel reflector1 = new CodeWheel("EJMZALYXVBWFCRQUONTSPIKHGD", 0);
        CodeWheel reflector2 = new CodeWheel("YRUHQSLDPXNGOKMIEBFZCWVJAT", 0);
        CodeWheel reflector3 = new CodeWheel("FVPJIAOYEDRZXWGCTKUQSBNMHL", 0);
        // lager ETW( denne var A->A på Tyske Enigma I
        CodeWheel etw1 = new CodeWheel();


        addWheelToStorage(wheel1);
        addWheelToStorage(wheel2);
        addWheelToStorage(wheel3);
        addWheelToStorage(wheel4);
        addWheelToStorage(wheel5);
        addWheelToStorage(reflector1);
        addWheelToStorage(reflector2);
        addWheelToStorage(reflector3);
        setActivETW(etw1);

        setActivReflector(reflector1);

        insertWheelAtPositionOne(wheel1);
        insertWheelAtPositionTwo(wheel2);
        insertWheelAtPositionthree(wheel3);

    }

    public void setRingOffsett(int a, int b, int c) {
        activeWheels[0].setWheelRingOffset(a-1 < 0 ?0:a-1);
        activeWheels[1].setWheelRingOffset(b-1 < 0 ? 0 : b-1);
        activeWheels[2].setWheelRingOffset(c-1 < 0 ? 0: c-1);
    }

    // kun små bokstaver
    public void addCable(char a, char b) {
        cables.add(new char[]{a, b});
    }

    public void planeTextToIncode(String input) {
        this.input = input.toLowerCase().replace(" ","");
    }

    public void runEnigmaMachine() {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char letter : chars) {
            char tmpLetter = letter;
            updateWheelsStepPosition();
            rotateWheels();
            tmpLetter = handelFirstCodeWheel(tmpLetter);
            tmpLetter = handleSecondCodeWheel(tmpLetter);
            tmpLetter = handleThirdCodeWheel(tmpLetter);



            tmpLetter = handelReverse(tmpLetter);
            sb.append(tmpLetter);
        }
        output = sb.toString();
    }

    private void rotateWheels() {

        if (activeWheels[0].getPosition() == activeWheels[0].getStepPosition()) {
            activeWheels[1].moveWheel();
        }else if (activeWheels[0].getPosition() == activeWheels[0].getStepPosition() + 1 &&
                activeWheels[1].getPosition() == activeWheels[1].getStepPosition()) {
            activeWheels[1].moveWheel();

        }
        if (activeWheels[1].getPosition() == activeWheels[1].getStepPosition()) {
            activeWheels[2].moveWheel();
            activeWheels[1].moveWheel();
        }
        activeWheels[0].moveWheel();
    }

    private void updateWheelsStepPosition() {
        for (CodeWheel w : activeWheels){
            int step =w.getStepPosition();
            int off =w.getWheelRingOffset();
            int start = w.getWheelStartPosition();
        }

    }

    private char handelFirstCodeWheel(char tmpLetter) {
        tmpLetter = swapIfCable(tmpLetter);
        tmpLetter = swapInETW(tmpLetter);

        tmpLetter = activeWheels[0].swapChar(tmpLetter);

        return tmpLetter;
    }

    private char handleSecondCodeWheel(char tmpLetter) {

        tmpLetter = activeWheels[1].swapChar(tmpLetter);

        return tmpLetter;
    }
    private char handleThirdCodeWheel(char tmpLetter) {

        tmpLetter = activeWheels[2].swapChar(tmpLetter);
        return tmpLetter;
    }
    private char handelReverse(char tmpLetter) {
        tmpLetter = runActiveReflector(tmpLetter);
        tmpLetter = activeWheels[2].swapCharReverse(tmpLetter);
        tmpLetter = activeWheels[1].swapCharReverse(tmpLetter);
        tmpLetter = activeWheels[0].swapCharReverse(tmpLetter);
        tmpLetter = swapInETW(tmpLetter);
        tmpLetter = swapIfCableReverse(tmpLetter);
        return tmpLetter;
    }



    private char swapInETW(char tmpLetter) {
        return this.etw.swapChar(tmpLetter);
    }

    private char runActiveReflector(char tmpLetter) {
        return this.reflector.swapChar(tmpLetter);
    }

    private char swapIfCable(char tmpLetter) {

        for (char[] pair : cables) {
            if (pair[0] == tmpLetter) {
                tmpLetter = pair[1];
                break;
            } else if (pair[1] == tmpLetter) {
                tmpLetter = pair[0];
                break;
            }

        }
        return tmpLetter;
    }

    private char swapIfCableReverse(char tmpLetter) {

        for (char[] pair : cables) {
            if (pair[1] == tmpLetter) {
                tmpLetter = pair[0];
                break;
            } else if (pair[0] == tmpLetter) {
                tmpLetter = pair[1];
                break;
            }

        }
        return tmpLetter;
    }

    public void setEnigmaActiveWheelsStartingPosition(int w1, int w2, int w3) {
        activeWheels[0].setCodeWheelStartPosition(w1-1 < 0 ?0:w1-1);
        activeWheels[1].setCodeWheelStartPosition(w2-1 < 0 ?0: w2-1);
        activeWheels[2].setCodeWheelStartPosition(w3-1 < 0 ?0:w3-1);
    }

    public void setActivReflector(CodeWheel reflector) {
        this.reflector = reflector;
    }

    public void setActivETW(CodeWheel etw) {
        this.etw = etw;
    }

    public String getOutput() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i % 4 == 0) {
                result.append(" ");
            }
            result.append(output.charAt(i));

        }
        return output;
    }

    public void setActiveCodeWheelAtIndex(CodeWheel wheel, int index) {
        if (index < 3)
            activeWheels[index] = wheel;
    }

    public void insertWheelAtPositionOne(CodeWheel wheel) {
        activeWheels[0] = wheel;
    }

    public void insertWheelAtPositionTwo(CodeWheel wheel) {
        activeWheels[1] = wheel;
    }

    public void insertWheelAtPositionthree(CodeWheel wheel) {
        activeWheels[2] = wheel;
    }

    public void addWheelToStorage(CodeWheel wheel) {
        if (index < storedWheels.length) {
            storedWheels[index] = wheel;
            index++;
        } else
            index = 0;
    }

    public CodeWheel getWheelAtIndex(int index) {
        if (index < storedWheels.length) {
            return storedWheels[index];

        } else
            return storedWheels[0];
    }

}
