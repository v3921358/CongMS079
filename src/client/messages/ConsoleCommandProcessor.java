package client.messages;

import java.util.List;
import java.util.Collections;
import tools.FilePrinter;
import client.messages.commands.ConsoleCommandExecute;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import client.messages.commands.ConsoleCommand;
import client.messages.commands.ConsoleCommandObject;
import java.util.HashMap;

public class ConsoleCommandProcessor
{
    private static final HashMap<String, ConsoleCommandObject> commands;
    
    private static void sendDisplayMessage(final String msg) {
        System.err.println("[sendDisplayMessage]錯誤:" + msg);
    }
    
    public static boolean processCommand(final String line) {
        final String[] splitted = line.split(" ");
        splitted[0] = splitted[0].toLowerCase();
        final ConsoleCommandObject co = (ConsoleCommandObject)ConsoleCommandProcessor.commands.get((Object)splitted[0]);
        if (co == null) {
            sendDisplayMessage("沒有這個指令.");
            return true;
        }
        final int ret = co.execute(splitted);
        return true;
    }
    
    static {
        commands = new HashMap<String, ConsoleCommandObject>();
        final Class[] array;
        final Class<?>[] CommandFiles = (Class<?>[])(array = new Class[] { ConsoleCommand.class });
        for (final Class<?> clasz : array) {
            try {
                final Class<?>[] a = clasz.getDeclaredClasses();
                final ArrayList<String> cL = new ArrayList<String>();
                for (final Class<?> c : a) {
                    try {
                        if (!Modifier.isAbstract(c.getModifiers()) && !c.isSynthetic()) {
                            final Object o = c.newInstance();
                            boolean enabled;
                            try {
                                enabled = c.getDeclaredField("enabled").getBoolean((Object)c.getDeclaredField("enabled"));
                            }
                            catch (NoSuchFieldException ex3) {
                                enabled = true;
                            }
                            if (o instanceof ConsoleCommandExecute && enabled) {
                                cL.add(c.getSimpleName().toLowerCase());
                                ConsoleCommandProcessor.commands.put(c.getSimpleName().toLowerCase(), new ConsoleCommandObject(c.getSimpleName().toLowerCase(), (ConsoleCommandExecute)o));
                            }
                        }
                    }
                    catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException ex5) {
                        FilePrinter.printError("ConsoleCommandProcessor.txt", (Throwable)ex5);
                    }
                }
                Collections.sort((List<String>)cL);
            }
            catch (Exception ex2) {
                FilePrinter.printError("ConsoleCommandProcessor.txt", (Throwable)ex2);
            }
        }
    }
}
