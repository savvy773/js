import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

public class ImportCsvToInflux {
    public static void main(String[] args) throws Exception {
        // Set up connection to InfluxDB
        String url = "http://localhost:8086";
        String username = "user"; // adjust as necessary
        String password = "password";
        String database = "exampledb";
        
        InfluxDB influxDB = InfluxDBFactory.connect(url, username, password);
        influxDB.setDatabase(database);

        File folder = new File("D:/temp");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
        if (files == null) {
            System.err.println("No CSV files found in " + folder.getAbsolutePath());
            return;
        }

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue; // skip comments and blank lines
                    }
                    influxDB.write(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading " + file.getName() + ": " + e.getMessage());
            }
        }

        influxDB.close();
    }
}
