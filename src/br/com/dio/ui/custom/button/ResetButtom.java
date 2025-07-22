package br.com.dio.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetButtom extends JButton {

    public ResetButtom(final ActionListener actionListener) {
        this.setText("Resetar");
        this.addActionListener(actionListener);
    }
}
