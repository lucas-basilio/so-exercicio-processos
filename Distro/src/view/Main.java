package view;

import controller.DistroController;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        DistroController controller = new DistroController();

        controller.exibeDistro();
    }
}
