package client;

import tools.Pair;
import server.maps.MapleMap;
import handling.world.World.Broadcast;
import server.Timer.EtcTimer;
import tools.MaplePacketCreator;
import tools.HexTool;
import scripting.LieDetectorScript;
import java.util.concurrent.ScheduledFuture;

public class MapleLieDetector
{
    public byte type;
    public int attempt;
    public int cid;
    public String tester;
    public String answer;
    public boolean inProgress;
    public boolean passed;
    public long lasttime;
    public ScheduledFuture<?> schedule;
    
    public MapleLieDetector(final int chid) {
        this.cid = chid;
        this.reset();
    }
    
    public final boolean startLieDetector(final String tester, final boolean isItem, final boolean anotherAttempt) {
        if (!anotherAttempt && ((this.isPassed() && isItem) || this.inProgress() || this.attempt == 3)) {
            return false;
        }
        final Pair captcha = LieDetectorScript.getImageBytes();
        if (captcha == null) {
            return false;
        }
        final byte[] image = HexTool.getByteArrayFromHexString((String)captcha.getLeft());
        this.answer = (String)captcha.getRight();
        this.tester = tester;
        this.inProgress = true;
        this.type = (byte)(byte)(isItem ? 0 : 1);
        ++this.attempt;
        final MapleCharacter chrid = MapleCharacter.getOnlineCharacterById(this.cid);
        if (this.attempt < 3 && chrid != null) {
            chrid.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.sendLieDetector(image, this.attempt));
        }
        this.schedule = EtcTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                final MapleCharacter searchchr = MapleCharacter.getOnlineCharacterById(cid);
                if (!MapleLieDetector.this.isPassed() && searchchr != null) {
                    if (attempt >= 3) {
                        final MapleCharacter search_chr = searchchr.getMap().getCharacterByName(tester);
                        if (search_chr != null && search_chr.getId() != searchchr.getId()) {
                            search_chr.dropMessage(5, searchchr.getName() + " 沒有通過測謊儀。");
                        }
                        MapleLieDetector.this.end();
                        searchchr.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.LieDetectorResponse((byte)7, (byte)0));
                        final MapleMap map = searchchr.getMap().getReturnMap();
                        searchchr.changeMap(map, map.getPortal(0));
                        Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 玩家: " + searchchr.getName() + " (等級 " + (int)searchchr.getLevel() + ") 未通過測謊儀檢測，疑似使用腳本外掛！"));
                    }
                    else {
                        MapleLieDetector.this.startLieDetector(tester, isItem, true);
                    }
                }
            }
        }, 60000L);
        return true;
    }
    
    public final int getAttempt() {
        return this.attempt;
    }
    
    public final byte getLastType() {
        return this.type;
    }
    
    public final String getTester() {
        return this.tester;
    }
    
    public final String getAnswer() {
        return this.answer;
    }
    
    public final boolean inProgress() {
        return this.inProgress;
    }
    
    public final boolean isPassed() {
        return this.passed;
    }
    
    public void setPassed(final boolean passedi) {
        this.passed = passedi;
    }
    
    public final boolean canDetector(final long time) {
        return this.lasttime + 300000L > time;
    }
    
    public final void end() {
        this.inProgress = false;
        this.passed = true;
        this.attempt = 0;
        this.lasttime = System.currentTimeMillis();
        if (this.schedule != null) {
            this.schedule.cancel(false);
            this.schedule = null;
        }
    }
    
    public final void reset() {
        this.tester = "";
        this.answer = "";
        this.attempt = 0;
        this.inProgress = false;
        this.passed = false;
    }
}
