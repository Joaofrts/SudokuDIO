package br.com.dio.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FinishGameButtom extends JButton {

    public FinishGameButtom(final ActionListener actionListener) {
        this.setText("Concluir");
        this.addActionListener(actionListener);
    }
}
