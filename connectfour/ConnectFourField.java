package connectfour;

import javax.swing.*;
import java.awt.*;

class ConnectFourField extends JButton {
    int x;
    int y;
    ConnectFourField(String nameTag, int x, int y) {
        setText(" ");
        setVisible(true);
        setName("Button" + nameTag);
        setBackground(Color.white);
        this.x = x;
        this.y = y;

    }
    public void addListener(ConnectFour game, ConnectFourField[] column) {
        addActionListener(e ->  this.fieldMarked(game, column));
    }
    public void fieldMarked(ConnectFour game, ConnectFourField[] column) {
        if (!game.winner) {
            for (int i = column.length - 1; i >= 0; i--) {
                if (" ".equals(column[i].getText())) {
                    column[i].setText(game.turn.getValue() + "");
                    game.checkForWinner(column[i]);
                    game.turn.switchTurn();
                    return;
                }
            }
        }
    }
}
