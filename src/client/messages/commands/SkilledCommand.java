package client.messages.commands;

import java.util.Iterator;
import client.MapleCharacter;
import tools.FileoutputUtil;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import constants.PiPiConfig;
import handling.channel.ChannelServer;
import handling.world.World.Find;
import client.MapleStat;
import client.MapleClient;
import constants.ServerConstants.PlayerGMRank;

public class SkilledCommand
{
    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.老實習生;
    }
    
    public static class Level extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int level = c.getPlayer().getLevel();
            try {
                level = Short.parseShort(splitted[1]);
            }
            catch (Exception ex) {}
            c.getPlayer().setLevel((short)level);
            c.getPlayer().updateSingleStat(MapleStat.LEVEL, level);
            c.getPlayer().setExp(0);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Level <等級> - 改變等級").toString();
        }
    }
    
    public static class 黑單 extends FakeReport
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("!黑單 <玩家名稱> - 將玩家設定為無法回報的黑名單").toString();
        }
    }
    
    public static class FakeReport extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String input = splitted[1];
            final int ch = Find.findChannel(input);
            if (ch <= 0) {
                c.getPlayer().dropMessage("玩家[" + input + "]不在線上");
                return true;
            }
            final MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(input);
            if (target.isGM()) {
                c.getPlayer().dropMessage(1, "無法黑單GM唷");
                return true;
            }
            final int accID = target.getAccountID();
            PiPiConfig.setBlackList(accID, input);
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 在回報系統黑單了 " + input;
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            FileoutputUtil.logToFile("Logs/Data/玩家回報黑單.txt", "\r\n  " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " 在回報系統黑單了 " + input);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!FakeReport <玩家名稱> - 將玩家設定為無法回報的黑名單").toString();
        }
    }
    
    public static class Heal extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().getStat().setHp((int)c.getPlayer().getStat().getCurrentMaxHp());
            c.getPlayer().getStat().setMp((int)c.getPlayer().getStat().getCurrentMaxMp());
            c.getPlayer().updateSingleStat(MapleStat.HP, (int)c.getPlayer().getStat().getCurrentMaxHp());
            c.getPlayer().updateSingleStat(MapleStat.MP, (int)c.getPlayer().getStat().getCurrentMaxMp());
            c.getPlayer().dispelDebuffs();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!heal - 補滿血魔").toString();
        }
    }
    
    public static class HealMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter player = c.getPlayer();
            for (final MapleCharacter mch : player.getMap().getCharacters()) {
                if (mch != null) {
                    mch.getStat().setHp((int)mch.getStat().getMaxHp());
                    mch.updateSingleStat(MapleStat.HP, (int)mch.getStat().getMaxHp());
                    mch.getStat().setMp((int)mch.getStat().getMaxMp());
                    mch.updateSingleStat(MapleStat.MP, (int)mch.getStat().getMaxMp());
                    mch.dispelDebuffs();
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!healmap  - 治癒地图上所有的人").toString();
        }
    }
}
