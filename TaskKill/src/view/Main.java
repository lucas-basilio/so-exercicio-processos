package view;

import controller.KillController;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        KillController controller = new KillController();

        int opt = 0;

        while (opt != 9)
        {
            try
            {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma Opção: (1: Lista processos; 2: Mata processo (PID); 3: Mata processo (Nome); 9: Encerrar;)"));
            }
            catch (NumberFormatException ex)
            {
                opt = 9;
            }

            switch (opt)
            {
                case 1:
                    controller.listaProcessos();
                    break;

                case 2:
                    controller.mataPid(JOptionPane.showInputDialog(null, "Insira o PID: "));
                    break;

                case 3:
                    controller.mataNome(JOptionPane.showInputDialog(null, "Insira o nome: "));
                    break;

                case 9:
                    System.out.close();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Insira um valor válido");
                    break;
            }
        }
    }
}
