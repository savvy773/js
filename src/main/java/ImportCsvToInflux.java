import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

public class ImportCsvToInflux {
    public static void main(String[] args) throws Exception {
        // Set up connection to InfluxDB using environment variables when
        // available so the same binary can run in different environments
        String url = System.getenv().getOrDefault("INFLUX_URL", "http://localhost:8086");
        String username = System.getenv().getOrDefault("INFLUX_USER", "user");
        String password = System.getenv().getOrDefault("INFLUX_PASS", "password");
        String database = System.getenv().getOrDefault("INFLUX_DB", "exampledb");
        
        InfluxDB influxDB = InfluxDBFactory.connect(url, username, password);
        influxDB.setDatabase(database);

        String csvDir = System.getenv().getOrDefault("CSV_DIR", "D:/temp");
        File folder = new File(csvDir);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
        if (files == null) {
            System.err.println("No CSV files found in " + csvDir);
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
