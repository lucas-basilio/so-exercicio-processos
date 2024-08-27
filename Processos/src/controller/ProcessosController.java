package controller;
import java.io.*;
import java.util.Properties;
import java.util.regex.Pattern;

public class ProcessosController {
    public ProcessosController () {
        super();
    }

    private String os() {
        return System.getProperty("os.name");
    }

    public void ip() throws IOException {

        BufferedReader reader = null;
        StringBuffer ipconfigObj = new StringBuffer();
        String currentAdapter = "";

        if (os().toLowerCase().contains("windows")) {
            try
            {
                InputStream stream = Runtime.getRuntime().exec("ipconfig /all").getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                String line = reader.readLine();

                while (line != null)
                {
                    if (!line.isBlank())
                    {
                        if (line.charAt(0) != ' ')
                        {
                            currentAdapter = line;
                        }
                        if (line.contains("IPv4") && !line.contains("255"))
                        {
                            ipconfigObj.append(currentAdapter);
                            ipconfigObj.append(" ");
                            //Regex pra selecionar o IP "\\d(\\d.).+(\\d)"
                            ipconfigObj.append(line.split(":")[1].replaceAll("[^\\d.]", ""));
                            ipconfigObj.append("; ");
                        }
                    }
                    line = reader.readLine();
                }
            }
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        } else {
            try
            {
                InputStream stream = Runtime.getRuntime().exec("ip addr").getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception ex)
            {
                InputStream stream = Runtime.getRuntime().exec("ifconfig").getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
            }
            String line = reader.readLine();

            while (line != null)
            {
                if (!line.isBlank())
                {
                    if (line.charAt(0) != ' ' && Pattern.compile("(\\d:)").matcher(line).find())
                    {
                        currentAdapter = line.split(": ")[1];
                    }

                    if (line.contains("inet") && !line.contains(":"))
                    {
                        ipconfigObj.append(currentAdapter);
                        ipconfigObj.append(" ");
                        ipconfigObj.append(line.split("inet")[1].split("/")[0]);
                        ipconfigObj.append("; ");
                    }
                }
                line = reader.readLine();
            }
        }

        reader.close();

        for (String item : ipconfigObj.toString().split("; "))
        {
            System.out.println(item);
        }
    }

    public void ping () {
        boolean isWindows = os().toLowerCase().contains("windows");
        String command = isWindows ? "ping -4 -n 10 www.google.com.br" : "ping -4 -c 10 www.google.com.br";
        String line = "";
        StringBuffer media = new StringBuffer();

        try
        {
            InputStream stream = Runtime.getRuntime().exec(command).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            line = reader.readLine();

            while (line != null)
            {
                String currentLine = reader.readLine();

                if (currentLine != null)
                {
                    line = currentLine;
                }
                else
                {
                    if (isWindows)
                    {
                        media.append(line.split(", ")[2]);
                    }
                    else
                    {
                        media.append(line.split("/")[1]);
                        media.append(": ");
                        media.append(line.split("/")[4]);
                    }
                    line = null;
                }
            }

            stream.close();
            reader.close();
            System.out.println(media);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
