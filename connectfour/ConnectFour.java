package connectfour;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConnectFour extends JFrame {
    boolean winner = false;
    Turn turn = new Turn();
    ConnectFourGrid gridArray = new ConnectFourGrid(6, 7);
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);
        setTitle("Connect Four");
        JPanel gameGrid = new JPanel();
        add(gameGrid);
        gameGrid.setLayout(new GridLayout(6, 7, 5, 5));
        for (int y = 0; y < this.gridArray.gridArray.length; y++) {
            for (int x = 0; x < this.gridArray.gridArray[0].length; x++) {
                String tagName = (char) ('A' + x) + "" +  ((int) this.gridArray.gridArray.length - y);
                ConnectFourField localField = new ConnectFourField(tagName, x, y);
                gameGrid.add(localField);
                this.gridArray.gridArray[y][x] = localField;
            }
        }
        for (int i = 0; i < this.gridArray.gridArray[0].length; i++) {
            ConnectFourField[] column = this.gridArray.getColumn(i);
            for (int j = 0; j < this.gridArray.gridArray.length; j++) {
                this.gridArray.gridArray[j][i].addListener(this, column);
            }
        }
        ResetButton resetButton = new ResetButton(this);
        JPanel resetPanel = new JPanel();
        resetPanel.setBounds(0, 0, 0, 0);
        resetPanel.add(resetButton);
        add(resetPanel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gameGrid, BorderLayout.CENTER);
        getContentPane().add(resetPanel, BorderLayout.SOUTH);
    }

    void resetFields() {
        this.winner = false;
        this.turn = new Turn();
        for (ConnectFourField[] column : this.gridArray.gridArray) {
            for (ConnectFourField field : column) {
                field.setText(" ");
                field.setBackground(Color.white);
            }
        }
    }
    protected void checkForWinner(ConnectFourField field) {
        ArrayList<ConnectFourField> listV = this.counterHelper(field, 1, 0);
        this.winnerHelper(listV, field);
        ArrayList<ConnectFourField> listH = this.counterHelper(field, 0, 1);
        ArrayList<ConnectFourField> listHl = this.counterHelper(field, 0, -1);
        listH.addAll(listHl);
        this.winnerHelper(listH, field);
        ArrayList<ConnectFourField> listD0 = this.counterHelper(field, -1, 1);
        ArrayList<ConnectFourField> listDlo = this.counterHelper(field, 1, -1);
        listD0.addAll(listDlo);
        this.winnerHelper(listD0, field);
        ArrayList<ConnectFourField> listD1 = this.counterHelper(field, -1, -1);
        ArrayList<ConnectFourField> listDro = this.counterHelper(field, 1, 1);
        listD1.addAll(listDro);
        this.winnerHelper(listD1, field);
    }
    private void winnerHelper(ArrayList<ConnectFourField> list, ConnectFourField currentField) {
        if (list.size() >= 3) {
            this.winner = true;
            currentField.setBackground(this.turn.value.getColor());
            for (ConnectFourField field : list) {
                field.setBackground(this.turn.value.getColor());
            }
        }

    }

    private ArrayList<ConnectFourField> counterHelper(ConnectFourField startField, int yValue, int xValue) {
        /* Onderstaande array bevat de indices van een connectfour spel van 7*6 velden.
        Spelers zetten kruisjes/rondjes 'zo laag mogelijk' op het bord (zie fieldMarked method)
        Maar feitelijk betekent dit dat de y-index zo groot mogelijk is:
        [x0, y0][x1, y0][x2, y0][x3, y0][x4, y0][x5, y0][x6, y0]
        [x0, y1][x1, y1][x2, y1][x3, y1][x4, y1][x5, y1][x6, y1]
        [x0, y2][x1, y2][x2, y2][x3, y2][x4, y2][x5, y2][x6, y2]
        [x0, y3][x1, y3][x2, y3][x3, y3][x4, y3][x5, y3][x6, y3]
        [x0, y4][x1, y4][x2, y4][x3, y4][x4, y4][x5, y4][x6, y4]
        [x0, y5][x1, y5][x2, y5][x3, y5][x4, y5][x5, y5][x6, y5]
        */
        int checkX = startField.x + xValue; // values toevoegen zodat begin veld niet bekeken wordt.
        int checkY = startField.y + yValue;

        ArrayList<ConnectFourField> fieldList = new ArrayList<>();
        while (checkX < this.gridArray.gridArray[0].length && checkY < this.gridArray.gridArray.length && checkY >= 0 && checkX >= 0) {
            String checkFieldValue = this.gridArray.gridArray[checkY][checkX].getText();
            if (!checkFieldValue.equals(startField.getText())) {
                break;
            } else {
                fieldList.add(this.gridArray.gridArray[checkY][checkX]);
            }
            checkY += yValue;
            checkX += xValue;
        }
        return fieldList;
    }
}