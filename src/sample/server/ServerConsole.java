package sample.server;

public class ServerConsole {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void print(String message, MessageStatus status) {
        switch (status) {
            case WAITING:
                System.out.println(ANSI_BLUE + message);
                break;
            case ENTERING:
                System.out.println(ANSI_GREEN + message);
                break;
            case MESSAGING:
                System.out.println(ANSI_YELLOW + message);
                break;
            case DISCONNECT:
                System.out.println(ANSI_RED + message);
                break;
        }
    }

}
