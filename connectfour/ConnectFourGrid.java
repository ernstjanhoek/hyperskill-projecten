package connectfour;

class ConnectFourGrid {
    ConnectFourField[][] gridArray;

    ConnectFourGrid(int yLength, int xLength) {
        this.gridArray = new ConnectFourField[yLength][xLength];
    }

    ConnectFourField[] getColumn(int xIndex) {
        ConnectFourField[] outArray = new ConnectFourField[this.gridArray.length];
        for (int i = this.gridArray.length - 1; i >= 0; i--) {
            outArray[i] = this.gridArray[i][xIndex];
        }
        return outArray;
    }
}
