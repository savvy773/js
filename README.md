# js

This repository demonstrates a simple Java program that reads all `*.csv` files from `D:\\temp` and writes the data to an InfluxDB instance. The example uses the [`influxdb-java`](https://github.com/influxdata/influxdb-java) library.

## Usage

1. Obtain the `influxdb-java` dependency (version `2.23`). If you use Maven, add:

```xml
<dependency>
  <groupId>org.influxdb</groupId>
  <artifactId>influxdb-java</artifactId>
  <version>2.23</version>
</dependency>
```

2. Compile the `ImportCsvToInflux` class:

```sh
javac -cp influxdb-java-2.23.jar src/main/java/ImportCsvToInflux.java
```

3. Run the program (adjust the classpath to where the JAR is located):

```sh
java -cp influxdb-java-2.23.jar;. ImportCsvToInflux
```

The program skips lines beginning with `#` and writes the remaining lines directly as InfluxDB line protocol to the configured database.
