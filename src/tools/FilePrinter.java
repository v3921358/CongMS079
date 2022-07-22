package tools;

import java.util.Calendar;
import java.util.Date;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FilePrinter
{
    public static final String LoginServer = "LoginServer.txt";
    public static final String MapleServerHandler = "MapleServerHandler.txt";
    public static final String StatesHandling = "StatesHandling.txt";
    public static final String PacketLogs = "PacketLogs.txt";
    public static final String AccountStuck = "AccountStuck.txt";
    public static final String PacketLogsExcpt = "PacketLogsExcpt.txt";
    public static final String CommandProccessor = "CommandProccessor.txt";
    public static final String ConsoleCommandProcessor = "ConsoleCommandProcessor.txt";
    public static final String LoginError = "LoginError.txt";
    public static final String MapleQuest = "MapleQuest.txt";
    public static final String HorntailLog = "HorntailLog.txt";
    public static final String PinkbeanLog = "PinkbeanLog.txt";
    public static final String ZakumLog = "ZakumLog.txt";
    private static final SimpleDateFormat sdf;
    private static final String FILE_PATH;
    private static final String ERROR = "error/";
    private static final DateFormat dateFormat;
    
    public static void printError(final String name, final Throwable t) {
        FileOutputStream out = null;
        final String file = FilePrinter.FILE_PATH + "error/" + name;
        try {
            final File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write(getString(t).getBytes());
            out.write("\n---------------------------------\r\n".getBytes());
        }
        catch (IOException ex) {}
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public static void printError(final String name, final Throwable t, final String info) {
        FileOutputStream out = null;
        final String file = FilePrinter.FILE_PATH + "error/" + name;
        try {
            final File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write((info + "\r\n").getBytes());
            out.write(getString(t).getBytes());
            out.write("\n---------------------------------\r\n".getBytes());
        }
        catch (IOException ex) {}
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public static void printError(final String name, final String s) {
        FileOutputStream out = null;
        final String file = FilePrinter.FILE_PATH + "error/" + name;
        try {
            final File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write(s.getBytes());
            out.write("\n---------------------------------\n".getBytes());
        }
        catch (IOException ex) {}
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public static void print(final String name, final String s) {
        print(name, s, true);
    }
    
    public static void print(final String name, final String s, final boolean line) {
        FileOutputStream out = null;
        final String file = FilePrinter.FILE_PATH + name;
        try {
            final File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write(s.getBytes());
            out.write("\r\n".getBytes());
            if (line) {
                out.write("---------------------------------\r\n".getBytes());
            }
        }
        catch (IOException ex) {}
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    private static String getString(final Throwable e) {
        String retValue = null;
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter((Writer)sw);
            e.printStackTrace(pw);
            retValue = sw.toString();
        }
        finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (sw != null) {
                    sw.close();
                }
            }
            catch (IOException ex) {}
        }
        return retValue;
    }
    
    public static String getLocalDateString() {
        final Date date = new Date();
        return FilePrinter.dateFormat.format(date);
    }
    
    static {
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        FILE_PATH = "logs/" + FilePrinter.sdf.format(Calendar.getInstance().getTime()) + "/";
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }
}
