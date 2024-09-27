class Logger {
    private static Logger loggerInstance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (loggerInstance == null) {
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    public void logMessage(String message) {
        System.out.println("Log: " + message);
    }
}

public class Singletonpattern {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.logMessage("This is a log message.");
    }
}
