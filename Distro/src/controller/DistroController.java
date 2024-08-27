package controller;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {
    public DistroController() {
        super();
    }

    private String os ()
    {
        return System.getProperty("os.name");
    }

    public void exibeDistro()
    {
        if (!os().toLowerCase().contains("linux"))
        {
            JOptionPane.showMessageDialog(null, "O sistema não é linux");
            return;
        }

        try
        {
            InputStream stream = Runtime.getRuntime().exec("cat /etc/os-release").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String distroName = reader.readLine();

            while (!distroName.toLowerCase().contains("pretty_name"))
            {
                distroName = reader.readLine();
            }

            System.out.println(distroName.split("'")[1]);

            reader.close();
            stream.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
