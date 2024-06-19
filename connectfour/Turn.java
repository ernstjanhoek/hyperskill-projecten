package connectfour;

class Turn {
    TurnEnum value = TurnEnum.X;

    char getValue() {
        return this.value.getCharacter();
    }

    public void switchTurn() {
        this.value = TurnEnum.switchTurn(this.value);
    }

    enum TurnEnum {
        X('X', java.awt.Color.pink), O('O', java.awt.Color.green);

        TurnEnum(char c, java.awt.Color color) {
            this.c = c;
            this.color = color;
        }

        private final char c;
        private final java.awt.Color color;

        private static TurnEnum switchTurn(TurnEnum turn) {
            return turn == X ? O : X;
        }

        char getCharacter() {
            return this.c;
        }

        java.awt.Color getColor() {
            return color;
        }

    }
}
