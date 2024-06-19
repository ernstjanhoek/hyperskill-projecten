package connectfour;

import javax.swing.*;

class ResetButton extends JButton {
    ResetButton(ConnectFour game) {
        setName("ButtonReset");
        setText("Reset");
        setVisible(true);
        setSize(80, 40);
        addActionListener(e -> game.resetFields());
    }
}
