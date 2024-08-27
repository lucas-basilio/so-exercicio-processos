package view;
import controller.ProcessosController;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        ProcessosController controller = new ProcessosController();
        int opt = 0;

        while (opt != 9)
        {
            try
            {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma Opção: (1: Mostrar IPv4; 2: Tempo de ping; 9: Encerrar;)"));
            }
            catch (NumberFormatException ex)
            {
                opt = 9;
            }

            switch (opt)
            {
                case 1:
                    controller.ip();
                    break;

                case 2:
                    controller.ping();
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
