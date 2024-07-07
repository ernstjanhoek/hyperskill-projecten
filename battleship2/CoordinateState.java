package battleship2;

public enum CoordinateState {
        SHIP('O'), SEA('~'), MISS('M'), HIT('X');
        private final char value;

        CoordinateState(char value) {
            this.value = value;
        }

        public char getCharValue() {
            return this.value;
        }
    }
