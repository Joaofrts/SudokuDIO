package br.com.dio.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CheckGameStatusButtom extends JButton {

    public CheckGameStatusButtom(final ActionListener actionListener) {
        this.setText("Verificar Status");
        this.addActionListener(actionListener);
    }
}
