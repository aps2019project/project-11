package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    private static ReadPropertyFile readPropertyFile;

    static {
        try {
            readPropertyFile = new ReadPropertyFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String ipAddress;
    private static int port;


    private ReadPropertyFile() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream= new FileInputStream("H:\\AP_Project\\src\\config.properties");
        properties.load(fileInputStream);
        setIpAddress(properties.getProperty("ip"));
        setPort(Integer.parseInt(properties.getProperty("port")));
    }

    public static ReadPropertyFile getReadPropertyFile() {
        return readPropertyFile;
    }

    public static void setReadPropertyFile(ReadPropertyFile readPropertyFile) {
        ReadPropertyFile.readPropertyFile = readPropertyFile;
    }

    public static String getIpAddress() {
        return ipAddress;
    }

    private void setIpAddress(String ipAddress) {
        ReadPropertyFile.ipAddress = ipAddress;
    }

    public static int getPort() {
        return port;
    }

    private void setPort(int port) {
        this.port = port;
    }
}
