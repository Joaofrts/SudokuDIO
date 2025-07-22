package br.com.dio.ui.custom.screen;

import br.com.dio.service.BoardService;
import br.com.dio.ui.custom.button.CheckGameStatusButtom;
import br.com.dio.ui.custom.button.FinishGameButtom;
import br.com.dio.ui.custom.button.ResetButtom;
import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class MainScreen {

    private final static Dimension dimension = new Dimension(600,600);

    private final BoardService boardService;


    private JButton checkGameStatusButtom;
    private JButton finishGameButtom;
    private JButton resetButtom;

    public MainScreen(final Map<String,String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension,mainPanel);
        addResetButtom(mainPanel);
        addCheckGameStatusButtom(mainPanel);
        addFinishGameButtom(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addResetButtom(final JPanel mainPanel) {
        resetButtom = new ResetButtom(e->{
            var dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            if (dialogResult==0){
                boardService.reset();
            }
        });

        mainPanel.add(resetButtom);
    }

    private void addCheckGameStatusButtom(final JPanel mainPanel) {
        checkGameStatusButtom = new CheckGameStatusButtom(e->{
        var hasErrors = boardService.hasErrors();
        var gameStatus = boardService.getStatus();

        var message = switch (gameStatus){
            case NON_STARTED -> "O jogo não foi iniciado.";
            case INCOMPLETE -> "O jogo está incompleto.";
            case COMPLETE -> "O jogo está completo.";
        };
        message+= hasErrors ? " e contém erros." : " e não contém erros.";
        JOptionPane.showMessageDialog(null,message);
    });
        mainPanel.add(checkGameStatusButtom);
    }

    private void addFinishGameButtom(final JPanel mainPanel) {
        finishGameButtom = new FinishGameButtom(e->{
            if(boardService.gameIsFinished()){
                JOptionPane.showMessageDialog(null,"Parabéns! O jogo foi finalizado com sucesso.");
                resetButtom.setEnabled(false);
                finishGameButtom.setEnabled(false);
                checkGameStatusButtom.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null,"Seu jogo tem alguma inconsistência, ajuste e tente novamente.");
            }
        });


        mainPanel.add(finishGameButtom);
    }
}
