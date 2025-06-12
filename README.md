# js

This repository demonstrates a simple Java program that reads all `*.csv` files from `D:\\temp` and writes the data to an InfluxDB instance. The example uses the
[`influxdb-java`](https://github.com/influxdata/influxdb-java) library.

## Usage

1. Ensure you have [Maven](https://maven.apache.org/) installed on the server.
   Run the following command to download dependencies and build a single
   executable JAR:

   ```sh
   mvn package
   ```

   This creates `target/importcsvinflux-1.0-SNAPSHOT-shaded.jar` containing
   `ImportCsvToInflux` and all required libraries.

2. Copy the resulting JAR to your local machine. Execute it with the desired
   configuration:

   ```sh
   INFLUX_URL=http://localhost:8086 \
   INFLUX_USER=user \
   INFLUX_PASS=password \
   INFLUX_DB=exampledb \
   CSV_DIR=D:/temp \
   java -jar importcsvinflux-1.0-SNAPSHOT-shaded.jar
   ```

   Adjust the environment variables as needed. The program skips lines beginning
   with `#` and writes the remaining lines directly as InfluxDB line protocol.
