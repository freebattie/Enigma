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
        CodeWheel wheel1 = new CodeWheel("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 18);
        CodeWheel wheel2 = new CodeWheel("AJDKSIRUXBLHWTMCQGZNPYFVOE", 6);
        CodeWheel wheel3 = new CodeWheel("BDFHJLCPRTXVZNYEIWGAKMUSQO", 23);
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
        activeWheels[0].setWheelRingOffset(a);
        activeWheels[1].setWheelRingOffset(b);
        activeWheels[2].setWheelRingOffset(c);
    }

    // kun små bokstaver
    public void addCable(char a, char b) {
        cables.add(new char[]{a, b});
    }

    public void planeTextToIncode(String input) {
        this.input = input.toLowerCase();
    }

    public void runEnigmaMachine() {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char letter : chars) {
            char tmpLetter = letter;
            tmpLetter = handelFirstCodeWheel(tmpLetter);

            if (activeWheels[0].getPosition() == activeWheels[0].getStepPosition()) {
                tmpLetter = handleSecondCodeWheelRotating(tmpLetter);
            } else if (activeWheels[0].getPosition() == activeWheels[0].getStepPosition() + 1 &&
                    activeWheels[1].getPosition() == activeWheels[1].getStepPosition()) {
                tmpLetter = handleSecondCodeWheelRotating(tmpLetter);

            } else
                tmpLetter = activeWheels[1].swapChar(tmpLetter);
            if (activeWheels[0].getPosition() == activeWheels[0].getStepPosition() && activeWheels[1].getPosition() == activeWheels[1].getStepPosition()) {
                tmpLetter = handleThirdCodeWheelRotating(tmpLetter);
            } else
                tmpLetter = activeWheels[2].swapChar(tmpLetter);

            tmpLetter = handelReverse(tmpLetter);
            sb.append(tmpLetter);
        }
        output = sb.toString();
    }

    private char handelFirstCodeWheel(char tmpLetter) {
        tmpLetter = swapIfCable(tmpLetter);
        tmpLetter = swapInETW(tmpLetter);
        activeWheels[0].moveWheel();
        tmpLetter = activeWheels[0].swapChar(tmpLetter);

        return tmpLetter;
    }

    private char handleSecondCodeWheelRotating(char tmpLetter) {
        activeWheels[1].moveWheel();
        tmpLetter = activeWheels[1].swapChar(tmpLetter);

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

    private char handleThirdCodeWheelRotating(char tmpLetter) {
        activeWheels[2].moveWheel();
        tmpLetter = activeWheels[2].swapChar(tmpLetter);
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

    public void setEnigmaActiveWheelsStartingPosition(int wheelOne, int wheelTwo, int wheelThree) {
        activeWheels[0].setCodeWheelStartPosition(wheelOne);
        activeWheels[1].setCodeWheelStartPosition(wheelTwo);
        activeWheels[2].setCodeWheelStartPosition(wheelThree);
    }

    public void setActivReflector(CodeWheel reflector) {
        this.reflector = reflector;
    }

    public void setActivETW(CodeWheel etw) {
        this.etw = etw;
    }

    public String getOutput() {
        return this.output;
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
