package us.harvey.proof.log4j2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Head {
    static final Logger logger = LogManager.getLogger(Head.class.getName());
    private String name;

    public boolean doIt() {
        logger.entry();
        logger.error("Did it again!");
        return logger.exit(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
