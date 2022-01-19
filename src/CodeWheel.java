class CodeWheel {

    char[] innerWinding = new char[26];
    private int wheelStartPosition = 0;
    private int wheelCurrentPosition = 0;
    char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private int wheelStepPosition;
    private int wheelRingOffset = 0;

    CodeWheel() {
        String tmp = "";
        for (char a : alphabet) {
            tmp += a;
        }
        setWinding(tmp);
        this.wheelStepPosition = 0;
    }

    CodeWheel(String winding, int wheelStepPosition) {
        setWinding(winding);
        this.wheelStepPosition = wheelStepPosition;

    }

    public void setWheelRingOffset(int offsett) {
        this.wheelRingOffset = offsett;



    }

    public void setWheelRingOffset2(int offsett) {
        this.wheelRingOffset = offsett;

            if (wheelStepPosition - offsett >= 0) {
                this.wheelStepPosition -= offsett;
            } else {
                int tmp = 26 + (wheelStepPosition - offsett);
                wheelStepPosition = tmp;
            }
    }

    public void setWinding(String winding) {
        char[] tmp = winding.toLowerCase().toCharArray();
        innerWinding = tmp;
    }

    public int getStepPosition() {
        return wheelStepPosition;
    }

    public void setWheelStepPosition(int wheelStepPosition) {
        this.wheelStepPosition = wheelStepPosition;
    }

    public int getWheelRingOffset() {
        return wheelRingOffset;
    }

    public int getWheelStartPosition() {
        return wheelStartPosition;
    }

    // verdi mellon 0 og 26 0 == ingen offset;
    // A -> D og B -> M
    // ved offset 1
    // A -> M og B -> T osv
    // https://en.wikipedia.org/wiki/Enigma_rotor_details#Rotor_wiring_tables
    public void setCodeWheelStartPosition(int wheelStartPosition) {
        this.wheelStartPosition = wheelStartPosition;
        this.wheelCurrentPosition = this.wheelStartPosition;


    }

    private void updateWheelStepPosition(int wheelStartPosition) {
        if (wheelStepPosition - wheelStartPosition > 0) {
            this.wheelStepPosition -= wheelStartPosition;
        } else {
            int tmp = 26 - wheelStepPosition + wheelStartPosition;
            wheelStepPosition = tmp;
        }
    }

    public int getPosition() {
        return wheelCurrentPosition;
    }

    public void moveWheel() {

        if (wheelCurrentPosition + 1 < 26)
            wheelCurrentPosition++;
        else
            wheelCurrentPosition = 0;
    }

    // WHEEL IC
    // A -> D og B -> M
    // turn og A -> M og B -> T osv
    // https://en.wikipedia.org/wiki/Enigma_rotor_details#Rotor_wiring_tables
    public char swapChar2(char letter) {
        char tmpChar;
        int tmpPosition = 0;
        if (letter + (wheelCurrentPosition - wheelRingOffset) <= (char) 'z') {
            tmpChar = (char) (letter + (wheelCurrentPosition - wheelRingOffset));
        } else if (letter + (wheelCurrentPosition - wheelRingOffset) > 'z') {
            int ofsetTemp = (char) (letter + (wheelCurrentPosition - wheelRingOffset)) - (char) 'z';
            tmpChar = (char) ((char) 'a' + (ofsetTemp - 1));
            if (tmpChar <= (char) 'z') {

            } else if (tmpChar > 'z') {
                int tmpofval = tmpChar - 'z';
                tmpChar = (char) ('a' + (tmpofval - 1));
            }

        }
        //hvis du går inn i denne elsen så er det feil en plass
        else {
            tmpChar = 'å';
        }
        tmpPosition = tmpChar - (char) 'a';

        for (int i = 0; i < innerWinding.length; i++) {
            if (i == tmpPosition) {
                tmpChar = innerWinding[i];
                break;
            }
        }
        return tmpChar;
    }

    public char swapChar10(char letter) {

        int tmpPosition = 0;
        char z = 'z';
        char a = 'a';
        if (letter + (wheelCurrentPosition - wheelRingOffset) <= (char) 'z' && letter + (wheelCurrentPosition - wheelRingOffset) >= a) {
            letter = (char) (letter + (wheelCurrentPosition - wheelRingOffset));
        } else if (letter + (wheelCurrentPosition - wheelRingOffset) > 'z') {
            int ofsetTemp = (char) (letter + (wheelCurrentPosition - wheelRingOffset)) - (char) 'z';
            letter = (char) ((char) 'a' + (ofsetTemp - 1));
            if (letter <= (char) 'z') {

            } else if (letter > 'z') {
                int tmpofval = letter - 'z';
                letter = (char) ('a' + (tmpofval - 1));
            }

        }
        //hvis du går inn i denne elsen så er det feil en plass
        else {
            int tmpoff = a - (letter + (wheelCurrentPosition - wheelRingOffset));
            letter = z;
            letter -= tmpoff - 1;
            if (letter >= a) {

            } else if (letter < a) {
                int tmpoff2 = a - letter;
                letter = z;
                letter -= tmpoff2 - 1;
            }
        }
        tmpPosition = letter - (char) 'a';

        for (int i = 0; i < innerWinding.length; i++) {
            if (i == tmpPosition) {
                letter = innerWinding[i];
                break;
            }
        }
        return letter;
    }
    public char swapChar(char letter) {

        int tmpPosition = 0;
        tmpPosition = letter - (char) 'a';


        if (tmpPosition + (wheelCurrentPosition - wheelRingOffset) <= 25 && tmpPosition + (wheelCurrentPosition - wheelRingOffset) >= 0) {
            tmpPosition =  (tmpPosition + (wheelCurrentPosition - wheelRingOffset));
        } else if (tmpPosition + (wheelCurrentPosition - wheelRingOffset) > 25) {
            tmpPosition =  (tmpPosition + (wheelCurrentPosition - wheelRingOffset)) - 26;
        }
        //hvis du går inn i denne elsen så er det feil en plass
        else {
            tmpPosition =  26 + (tmpPosition + (wheelCurrentPosition - wheelRingOffset));

        }

        letter = alphabet[tmpPosition];
        tmpPosition = letter - (char) 'a';
        letter = innerWinding[tmpPosition];
        tmpPosition = letter - (char) 'a';


        if (tmpPosition - (wheelCurrentPosition - wheelRingOffset) <= 25 && tmpPosition - (wheelCurrentPosition - wheelRingOffset) >= 0) {
            tmpPosition =  (tmpPosition - (wheelCurrentPosition - wheelRingOffset));
        } else if (tmpPosition - (wheelCurrentPosition - wheelRingOffset) > 25) {
            tmpPosition =  (tmpPosition - (wheelCurrentPosition - wheelRingOffset)) - 26;
        }
        //hvis du går inn i denne elsen så er det feil en plass
        else {
            tmpPosition =  26 + (tmpPosition - (wheelCurrentPosition - wheelRingOffset));

        }
        letter = alphabet[tmpPosition];
        return letter;
    }
    public char swapCharReverse(char letter) {

        int tmpPosition = 0;
        tmpPosition = letter - (char) 'a';
        if (tmpPosition + (wheelCurrentPosition - wheelRingOffset) <= 25 && tmpPosition + (wheelCurrentPosition - wheelRingOffset) >= 0) {
            tmpPosition =  (tmpPosition + (wheelCurrentPosition - wheelRingOffset));
        } else if (tmpPosition + (wheelCurrentPosition - wheelRingOffset) > 25) {
            tmpPosition =  (tmpPosition + (wheelCurrentPosition - wheelRingOffset)) - 26;
        }
        //hvis du går inn i denne elsen så er det feil en plass
        else {
            tmpPosition =  26 + (tmpPosition + (wheelCurrentPosition - wheelRingOffset));

        }

        letter = alphabet[tmpPosition];

        for (int i = 0; i <innerWinding.length; i++){
            if (innerWinding[i] == letter){
                tmpPosition = i;
                break;
            }
        }
        letter = alphabet[tmpPosition];
        tmpPosition = letter - (char) 'a';


        if (tmpPosition - (wheelCurrentPosition - wheelRingOffset) <= 25 && tmpPosition - (wheelCurrentPosition - wheelRingOffset) >= 0) {
            tmpPosition =  (tmpPosition - (wheelCurrentPosition - wheelRingOffset));
        } else if (tmpPosition - (wheelCurrentPosition - wheelRingOffset) > 25) {
            tmpPosition =  (tmpPosition - (wheelCurrentPosition - wheelRingOffset)) - 26;
        }
        //hvis du går inn i denne elsen så er det feil en plass
        else {
            tmpPosition =  26 + (tmpPosition - (wheelCurrentPosition - wheelRingOffset));

        }
        letter = alphabet[tmpPosition];
        return letter;

    }

    public char swapCharReverse10(char letter) {
        char z = 'z';
        char a = 'a';


        for (int i = 0; i < innerWinding.length; i++) {

            if (innerWinding[i] == letter) {
                letter = alphabet[i];
                if (letter + (wheelCurrentPosition - wheelRingOffset) * -1 >= a && letter + (wheelCurrentPosition - wheelRingOffset) * -1 <= z) {
                    letter = (char) (letter + ((wheelCurrentPosition - wheelRingOffset) * -1));
                } else if (letter + (wheelCurrentPosition - wheelRingOffset) * -1 < a) {
                    int tmpoff = a - (letter + ((wheelCurrentPosition - wheelRingOffset) * -1));
                    letter = z;
                    letter -= tmpoff - 1;
                    if (letter >= a) {

                    } else if (letter < a) {
                        int tmpoff2 = a - letter;
                        letter = z;
                        letter -= tmpoff2 - 1;
                    }
                } else {
                    int tmpoff = (letter + ((wheelCurrentPosition - wheelRingOffset) * -1)) - z;
                    letter = a;
                    letter += tmpoff - 1;
                    if (letter <= z) {

                    } else if (letter > z) {
                        int tmpoff2 = letter - z;
                        letter = a;
                        letter += tmpoff2 - 1;
                    }
                }

                return letter;

            }
        }
        return letter;
    }

    public char swapCharReverse2(char letter) {
        char z = 'z';
        char a = 'a';
        char tmp = 0;
        for (int i = 0; i < innerWinding.length; i++) {

            if (innerWinding[i] == letter) {
                tmp = alphabet[i];
                if (tmp - (wheelCurrentPosition - wheelRingOffset) >= a) {
                    tmp = (char) (tmp + (wheelCurrentPosition - wheelRingOffset) * -1);
                } else if (tmp - (wheelCurrentPosition - wheelRingOffset) < a) {
                    int tmpoff = a - (tmp + (wheelCurrentPosition - wheelRingOffset) * -1);
                    tmp = z;
                    tmp -= tmpoff - 1;
                    if (tmp >= a) {

                    } else if (tmp < a) {
                        int tmpoff2 = a - tmp;
                        tmp = z;
                        tmp -= tmpoff2 - 1;
                    }
                }

                return tmp;

            }
        }
        return tmp;
    }


}
