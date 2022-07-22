package client.messages;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import client.messages.commands.CommandExecute;
import java.lang.reflect.Modifier;
import client.messages.commands.GodCommand;
import client.messages.commands.AdminCommand;
import client.messages.commands.GMCommand;
import client.messages.commands.InternCommand;
import client.messages.commands.SkilledCommand;
import client.messages.commands.PracticerCommand;
import client.messages.commands.PlayerCommand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.FilePrinter;
import database.DBConPool;
import client.MapleCharacter;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import constants.PiPiConfig;
import java.util.LinkedList;
import gui.CongMS;
import tools.FileoutputUtil;
import constants.ServerConstants.PlayerGMRank;
import constants.ServerConstants.CommandType;
import java.util.Iterator;
import client.MapleClient;
import java.util.List;
import java.util.ArrayList;
import client.messages.commands.CommandObject;
import java.util.HashMap;

public class CommandProcessor
{
    private static final HashMap<String, CommandObject> commands;
    private static final HashMap<Integer, ArrayList<String>> NormalCommandList;
    private static final HashMap<Integer, ArrayList<String>> VipCommandList;
    private static final List<String> showcommands;
    
    public static void dropHelp(final MapleClient c, final int type) {
        final StringBuilder sb = new StringBuilder("指令列表:\r\n ");
        HashMap<Integer, ArrayList<String>> commandList = new HashMap<Integer, ArrayList<String>>();
        int check = 0;
        if (type == 0) {
            commandList = CommandProcessor.NormalCommandList;
            check = c.getPlayer().getGMLevel();
        }
        for (int i = 0; i <= check; ++i) {
            if (commandList.containsKey((Object)Integer.valueOf(i))) {
                sb.append((type == 1) ? "VIP" : "").append("權限等級： ").append(i).append("\r\n");
                for (final String s : (ArrayList<String>)commandList.get((Object)Integer.valueOf(i))) {
                    final CommandObject co = (CommandObject)CommandProcessor.commands.get((Object)s);
                    sb.append(co.getMessage());
                    sb.append(" \r\n");
                }
            }
        }
        c.getPlayer().dropNPC(sb.toString());
    }
    
    private static void sendDisplayMessage(final MapleClient c, final String msg, final CommandType type) {
        if (c.getPlayer() == null) {
            return;
        }
        switch (type) {
            case NORMAL: {
                c.getPlayer().dropMessage(6, msg);
                break;
            }
            case TRADE: {
                c.getPlayer().dropMessage(-2, "錯誤 : " + msg);
                break;
            }
        }
    }
    
    public static boolean processCommand(final MapleClient c, final String line, final CommandType type) {
        if (c != null) {
            final char commandPrefix = line.charAt(0);
            for (final PlayerGMRank prefix : PlayerGMRank.values()) {
                if (line.startsWith(String.valueOf(prefix.getCommandPrefix() + prefix.getCommandPrefix()))) {
                    return false;
                }
            }
            if (commandPrefix == PlayerGMRank.普通玩家.getCommandPrefix()) {
                final String[] splitted = line.split(" ");
                splitted[0] = splitted[0].toLowerCase();
                final CommandObject co = (CommandObject)CommandProcessor.commands.get((Object)splitted[0]);
                if (co == null || co.getType() != type) {
                    sendDisplayMessage(c, "沒有這個指令,可以使用 @幫助/@help 來查看指令.", type);
                    return true;
                }
                try {
                    final boolean ret = co.execute(c, splitted);
                    if (!ret) {
                        c.getPlayer().dropMessage("指令錯誤，用法： " + co.getMessage());
                    }
                }
                catch (Exception e) {
                    sendDisplayMessage(c, "有錯誤.", type);
                    if (c.getPlayer().isGM()) {
                        sendDisplayMessage(c, "錯誤: " + (Object)e, type);
                    }
                    FileoutputUtil.outputFileError("logs/Except/Log_Command_Except.txt", (Throwable)e);
                    FileoutputUtil.logToFile("logs/Except/Log_Command_Except.txt", FileoutputUtil.NowTime() + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ")使用了指令 " + line + " ---在地图「" + c.getPlayer().getMapId() + "」頻道：" + c.getChannel() + " \r\n");
                }
                return true;
            }
            else {
                final int 游戏指令开关 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏指令开关"));
                if (游戏指令开关 > 0) {
                    if (c.getPlayer().getGMLevel() > PlayerGMRank.普通玩家.getLevel()) {
                        final String[] splitted2 = line.split(" ");
                        splitted2[0] = splitted2[0].toLowerCase();
                        final List<String> show = new LinkedList<String>();
                        for (final String com : CommandProcessor.showcommands) {
                            if (com.contains((CharSequence)splitted2[0])) {
                                show.add(com);
                            }
                        }
                        if (show.isEmpty()) {
                            final StringBuilder sb = new StringBuilder();
                            final int iplength = splitted2[0].length();
                            for (final String com2 : CommandProcessor.showcommands) {
                                final int sclength = com2.length();
                                final String[] next = new String[sclength];
                                for (int i = 0; i < next.length; ++i) {
                                    next[i] = "false";
                                }
                                if (iplength == sclength) {
                                    for (int i = 0; i < sclength; ++i) {
                                        final String st = com2.substring(i, i + 1);
                                        for (int r = 0; r < iplength; ++r) {
                                            final String it = splitted2[0].substring(r, r + 1);
                                            if (st.equals((Object)it)) {
                                                next[i] = "true";
                                            }
                                        }
                                    }
                                    boolean last = true;
                                    for (int j = 0; j < next.length; ++j) {
                                        if ("false".equals((Object)next[j])) {
                                            last = false;
                                        }
                                    }
                                    if (!last || !show.isEmpty()) {
                                        continue;
                                    }
                                    show.add(com2);
                                }
                            }
                        }
                        if (show.size() == 1 && !splitted2[0].equals((Object)show.get(0))) {
                            sendDisplayMessage(c, "自动识別关联指令[" + (String)show.get(0) + "].", type);
                            splitted2[0] = (String)show.get(0);
                        }
                        if (line.charAt(0) == '!') {
                            final CommandObject co2 = (CommandObject)CommandProcessor.commands.get((Object)splitted2[0]);
                            if (co2 == null || co2.getType() != type) {
                                if (splitted2[0].equals((Object)(line.charAt(0) + "help"))) {
                                    dropHelp(c, 0);
                                    return true;
                                }
                                sendDisplayMessage(c, "沒有這個指令.", type);
                                return true;
                            }
                            else {
                                boolean CanUseCommand = false;
                                if (c.getPlayer().getGMLevel() >= co2.getReqGMLevel()) {
                                    CanUseCommand = true;
                                }
                                if (!CanUseCommand) {
                                    sendDisplayMessage(c, "你沒有權限可以使用指令.", type);
                                    return true;
                                }
                                if (PiPiConfig.getCommandLock() && !c.getPlayer().isGod()) {
                                    sendDisplayMessage(c, "目前無法使用指令.", type);
                                    return true;
                                }
                                if (c.getPlayer() != null) {
                                    boolean ret2 = false;
                                    try {
                                        ret2 = co2.execute(c, splitted2);
                                        if (ret2) {
                                            logGMCommandToDB(c.getPlayer(), line);
                                            FileoutputUtil.logToFile("logs/Data/管理員命令.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用管理員命令:" + line);
                                            ShowMsg(c, line, type);
                                        }
                                        else {
                                            c.getPlayer().dropMessage("指令錯誤，用法： " + co2.getMessage());
                                        }
                                    }
                                    catch (Exception e2) {
                                        FileoutputUtil.outputFileError("logs/Except/Log_Command_Except.txt", (Throwable)e2);
                                        String output = FileoutputUtil.NowTime();
                                        if (c != null && c.getPlayer() != null) {
                                            output = output + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ")使用了指令 " + line + " ---在地图「" + c.getPlayer().getMapId() + "」頻道：" + c.getChannel();
                                        }
                                        FileoutputUtil.logToFile("logs/Except/Log_Command_Except.txt", output + " \r\n");
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static void ShowMsg(final MapleClient c, final String line, final CommandType type) {
        if (c.getPlayer() != null) {
            if (!c.getPlayer().isGod() && !line.toLowerCase().startsWith("!cngm")) {
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] " + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ")使用了指令 " + line + " ---在地图「" + c.getPlayer().getMapId() + "」頻道：" + c.getChannel()));
            }
            if (c.getPlayer().getGMLevel() == 5) {
                System.out.println("＜超級管理員＞ " + c.getPlayer().getName() + " 使用了指令: " + line);
            }
            else if (c.getPlayer().getGMLevel() == 4) {
                System.out.println("＜領導者＞ " + c.getPlayer().getName() + " 使用了指令: " + line);
            }
            else if (c.getPlayer().getGMLevel() == 3) {
                System.out.println("＜巡邏者＞ " + c.getPlayer().getName() + " 使用了指令: " + line);
            }
            else if (c.getPlayer().getGMLevel() == 2) {
                System.out.println("＜老實習生＞ " + c.getPlayer().getName() + " 使用了指令: " + line);
            }
            else if (c.getPlayer().getGMLevel() == 1) {
                System.out.println("＜新實習生＞ " + c.getPlayer().getName() + " 使用了指令: " + line);
            }
            else if (c.getPlayer().getGMLevel() != 100) {
                sendDisplayMessage(c, "你沒有權限可以使用指令.", type);
            }
        }
    }
    
    private static void logGMCommandToDB(final MapleCharacter player, final String command) {
        if (player == null) {
            return;
        }
        PreparedStatement ps = null;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            ps = con.prepareStatement("INSERT INTO gmlog (cid, command, mapid) VALUES (?, ?, ?)");
            ps.setInt(1, player.getId());
            ps.setString(2, command);
            ps.setInt(3, player.getMap().getId());
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            FilePrinter.printError("CommandProccessor.txt", (Throwable)ex, "logGMCommandToDB");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException e) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            }
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException e2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e2);
            }
        }
    }
    
    private static void DoNormalCommand() {
        final Class[] array;
        final Class<?>[] CommandFiles = (Class<?>[])(array = new Class[] { PlayerCommand.class, PracticerCommand.class, SkilledCommand.class, InternCommand.class, GMCommand.class, AdminCommand.class, GodCommand.class });
        for (final Class<?> clasz : array) {
            try {
                final PlayerGMRank rankNeeded = (PlayerGMRank)clasz.getMethod("getPlayerLevelRequired", (Class<?>[])new Class[0]).invoke(null, (Object[])null);
                final Class<?>[] commandClasses = clasz.getDeclaredClasses();
                final ArrayList<String> cL = new ArrayList<String>();
                for (final Class<?> c : commandClasses) {
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
                            if (o instanceof CommandExecute && enabled) {
                                cL.add(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase());
                                CommandProcessor.commands.put(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase(), new CommandObject(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase(), (CommandExecute)o, rankNeeded.getLevel()));
                                CommandProcessor.showcommands.add(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase());
                            }
                        }
                    }
                    catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException ex6) {

                        FilePrinter.printError("CommandProccessor.txt", (Throwable)ex6);
                    }
                }
                Collections.sort((List<String>)cL);
                CommandProcessor.NormalCommandList.put(Integer.valueOf(rankNeeded.getLevel()), cL);
            }
            catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex7) {
                FilePrinter.printError("CommandProccessor.txt", (Throwable)ex7);
            }
        }
    }
    
    static {
        commands = new HashMap<String, CommandObject>();
        NormalCommandList = new HashMap<Integer, ArrayList<String>>();
        VipCommandList = new HashMap<Integer, ArrayList<String>>();
        showcommands = new LinkedList<String>();
        DoNormalCommand();
    }
}
