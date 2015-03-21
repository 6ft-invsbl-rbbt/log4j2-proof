package us.harvey.proof.log4j2;

//import org.apache.logging.log4j.BasicConfigurator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4jHelloWorld {
    static final Logger logger = LogManager.getLogger(Log4jHelloWorld.class);

    public static void main(String[] args) {
        //Configure logger
        //BasicConfigurator.configure();
        //logger.debug("Hello World!");

        // Set up a simple configuration that logs on the console.
        logger.trace("Entering application.");
        Head bar = new Head();
        if (!bar.doIt()) {
            logger.error("Didn't do it.");
        }
        logger.trace("Exiting application.");
    }
}
