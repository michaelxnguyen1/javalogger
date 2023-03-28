
package edu.wit.comp1050;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JavaLogger is a simple, lightweight logger to help track what our programs
 * are doing when they run.
 */
public class JavaLogger {
    /**
     * Constructor uses the parameters to calculate the name of the log file. It
     * also creates the log file
     * in the current working directory.
     *
     * @param baseName          Base name of the logger, e.g., "myapplog"
     * @param addDateToBaseName True if the YYYY-MM-DD should be added to the base
     *                          name to
     *                          derive the full name of the log file
     * @throws IOException Thrown if the log file cannot be created
     */
    public JavaLogger(String baseName, boolean addDateToBaseName) throws IOException {
        logFileName = baseName;
        if (addDateToBaseName) {
            // LocalDateTime is a handy class for getting the current local date and time
            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String dateTime = formatDT.format(today);

            // We need the string representation so we can add it to the base name
            logFileName = logFileName + "-" + dateTime;
        }

        // Append .log to the final name
        logFileName += ".log";

        // Create the log file so we can write log messages to it
        logFile = new BufferedWriter(new FileWriter(logFileName));

        // Get the level we need to respect from the properties file
        try (BufferedReader configReader = new BufferedReader(
                new FileReader("edu/wit/comp1050/logger.properties"))) {
            String levelString = configReader.readLine();
            logLevel = LogLevel.valueOf(levelString); // Access LogLevel enum and assign to logLevel
        }
    }

    /**
     * Writes a ALL message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void all(String msg) {
        writeMessage(LogLevel.ALL, msg);
    }

    /**
     * Writes an DEBUG message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void debug(String msg) {
        writeMessage(LogLevel.DEBUG, msg);
    }

    /**
     * Writes an INFO message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void info(String msg) {
        writeMessage(LogLevel.INFO, msg);
    }

    /**
     * Writes an WARN message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void warn(String msg) {
        writeMessage(LogLevel.INFO, msg);
    }

    /**
     * Writes an ALERT message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void alert(String msg) {
        writeMessage(LogLevel.WARN, msg);
    }

    /**
     * Writes an ERROR message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void error(String msg) {
        writeMessage(LogLevel.ERROR, msg);
    }

    /**
     * Writes an FATAL message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void fatal(String msg) {
        writeMessage(LogLevel.FATAL, msg);
    }

    /**
     * Writes an OFF message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void off(String msg) {
        writeMessage(LogLevel.OFF, msg);
    }

    /**
     * Writes an TRACE message to the log
     * 
     * @param msg Message to emit into the log file.
     */
    public void trace(String msg) {
        writeMessage(LogLevel.TRACE, msg);
    }

    /**
     * Closes the log file and frees up system resources.
     * 
     * @throws IOException Thrown if log file cannot be closed
     */
    public void close() throws IOException {
        logFile.close();
    }

    /**
     * Private helper method that actually does the writing.
     * 
     * @param level Level of the log message (DEBUG..ALERT)
     * @param msg   Message to write to the log file
     */
    private void writeMessage(LogLevel level, String msg) {
        if (level.compareTo(this.logLevel) < 0) {
            return;
        }
        try {
            String now = LocalDateTime.now().toString();
            String logFileLine = now + "\t" + level.toString() + "\t" + msg;
            logFile.write(logFileLine + "\n");
        } catch (IOException ioex) {
            System.out.printf("EXCEPTION! %s%n", ioex.getMessage());
        }
    }

    private String logFileName; // log file name
    private LogLevel logLevel; // log level to respect when writing log messages
    private final BufferedWriter logFile; // file and associated writer.
}
