package edu.wit.comp1050;

import java.io.IOException;

/**
 * Simple program to test the JavaLogger. We need a class that "starts" the
 * program. In other words, that implements
 * the program entry point: public static void main(String[] args) {} method.
 */
public class MyApp {
    public static void main(String[] args) throws IOException {

        JavaLogger logger = new JavaLogger("mylog", true);
        logger.all("VALUE: Integer.MAX_VALUE | DESCRIPTION: ALL LEVELS INCLUDING CUSTOM LEVELS");
        logger.debug("VALUE: 500 | DESCRIPTION: USED FOR DEBUGGING PURPOSES");
        logger.info("VALUE: 400 | DESCRIPTION: A NORMAL, EXPECTED, RELEVANT EVENT HAPPENED");
        logger.warn(
                "VALUE: 300 | DESCRIPTION: SOMETHING UNEXPECTED - THOUGH NO NECESSARILY AN ERROR - HAPPENED AND NEEDS TO BE WATCHED");
        logger.error("VALUE: 200 | DESCRIPTION: AN ERROR OCCURED IN THE APPLICATION");
        logger.fatal("VALUE: 100 | DESCRIPTION: THE APPLICATION IS UNUSABLE. ACTION NEEDS TO BE TAKEN IMMEDIATELY");
        logger.off("VALUE: 0 | DESCRIPTION: NO LOGGING");
        logger.trace("VALUE: 600 | DESCRIPTION: USED FOR DEBUGGING PURPOSES - INCLUDES THE MOST DETAILED INFORMATION");
        logger.close();
    }
}
