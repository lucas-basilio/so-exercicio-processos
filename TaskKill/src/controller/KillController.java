package controller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
    public KillController() {
        super();
    }

    private String os () {
        return System.getProperty("os.name");
    }

    public void listaProcessos ()
    {
        boolean isWindows = os().toLowerCase().contains("windows");
        String command = isWindows ? "TASKLIST /FO TABLE" : "ps -ef";

        try
        {
            InputStream stream = Runtime.getRuntime().exec(command).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = reader.readLine();

            while (line != null)
            {
                System.out.println(line);
                line = reader.readLine();
            }

            reader.close();
            stream.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public void mataPid (String pid)
    {
        boolean isWindows = os().toLowerCase().contains("windows");
        StringBuffer buffer = new StringBuffer();

        buffer.append(isWindows ? "TASKKILL /PID" : "kill -9");
        buffer.append(pid);

        callProcess(buffer.toString());
    }

    public void mataNome (String nome) {
        boolean isWindows = os().toLowerCase().contains("windows");
        StringBuffer buffer = new StringBuffer();

        buffer.append(isWindows ? "TASKKILL /PID" : "kill -f");
        buffer.append(nome);

        callProcess(buffer.toString());
    }

    private void callProcess(String command) {
        try
        {
            Runtime.getRuntime().exec(command);
        }
        catch (Exception ex)
        {
           System.err.println(ex.getMessage());
        }
    }
}
