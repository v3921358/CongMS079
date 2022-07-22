package handling.channel.handler;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import database.DBConPool;
import java.awt.geom.Point2D;
import tools.packet.UIPacket;
import constants.MapConstants;
import server.MaplePortal;
import java.util.List;
import server.maps.MapleMap;
import server.maps.AnimatedMapleMapObject;
import java.util.Collection;
import server.movement.LifeMovementFragment;
import server.maps.MapleMapObject;
import gui.CongMS;
import server.MapleInventoryManipulator;
import java.lang.ref.WeakReference;
import server.Timer.CloneTimer;
import server.events.MapleSnowball.MapleSnowballs;
import client.anticheat.CheatingOffense;
import java.awt.Point;
import server.MapleStatEffect;
import server.MapleItemInformationProvider;
import client.ISkill;
import server.life.MobSkill;
import server.life.MobAttackInfo;
import client.PlayerStats;
import server.life.MapleMonster;
import client.SkillFactory;
import tools.packet.MobPacket;
import server.Randomizer;
import server.AutobanManager;
import server.life.MobSkillFactory;
import server.life.MobAttackInfoFactory;
import java.util.ArrayList;
import io.netty.channel.Channel;
import tools.MapleAESOFB;
import tools.MockIOSession;
import abc.离线人偶;
import handling.channel.ChannelServer;
import tools.packet.MTSCSPacket;
import server.maps.FieldLimitType;
import client.MapleBuffStat;
import java.util.Iterator;
import client.inventory.IItem;
import client.inventory.MapleInventoryType;
import handling.world.World.Broadcast;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import server.quest.MapleQuest;
import client.SkillMacro;
import tools.data.LittleEndianAccessor;
import constants.GameConstants;
import client.MapleCharacter;
import client.MapleClient;

public class PlayerHandler
{
    private static int channel;
    
    private static boolean isFinisher(final int skillid) {
        switch (skillid) {
            case 1111003:
            case 1111004:
            case 1111005:
            case 1111006:
            case 11111002:
            case 11111003: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static void ChangeMonsterBookCover(final int bookid, final MapleClient c, final MapleCharacter chr) {
        if (bookid == 0 || GameConstants.isMonsterCard(bookid)) {
            chr.setMonsterBookCover(bookid);
            chr.getMonsterBook().updateCard(c, bookid);
        }
    }
    
    public static void ChangeSkillMacro(final LittleEndianAccessor slea, final MapleCharacter chr) {
        for (int num = slea.readByte(), i = 0; i < num; ++i) {
            final String name = slea.readMapleAsciiString();
            final int shout = slea.readByte();
            final int skill1 = slea.readInt();
            final int skill2 = slea.readInt();
            final int skill3 = slea.readInt();
            final SkillMacro macro = new SkillMacro(skill1, skill2, skill3, name, shout, i);
            chr.updateMacros(i, macro);
        }
    }
    
    public static final void ChangeKeymap(final LittleEndianAccessor slea, final MapleCharacter chr) {
        if (slea.available() > 8L && chr != null) {
            chr.updateTick(slea.readInt());
            for (int numChanges = slea.readInt(), i = 0; i < numChanges; ++i) {
                chr.changeKeybinding(slea.readInt(), slea.readByte(), slea.readInt());
            }
        }
        else if (chr != null) {
            final int type = slea.readInt();
            final int data = slea.readInt();
            switch (type) {
                case 1: {
                    if (data <= 0) {
                        chr.getQuestRemove(MapleQuest.getInstance(122221));
                        break;
                    }
                    chr.getQuestNAdd(MapleQuest.getInstance(122221)).setCustomData(String.valueOf(data));
                    break;
                }
                case 2: {
                    if (data <= 0) {
                        chr.getQuestRemove(MapleQuest.getInstance(122223));
                        break;
                    }
                    chr.getQuestNAdd(MapleQuest.getInstance(122223)).setCustomData(String.valueOf(data));
                    break;
                }
            }
        }
    }
    
    public static final void UseChair(final int itemId, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        if (itemId == 3012024) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        final IItem toUse = chr.getInventory(type).findById(itemId);
        if (toUse == null && itemId >= 3010000 && itemId < 9999999) {
            FileoutputUtil.logToFile("logs/Hack/Ban/修改封包.txt", "\r\n " + FileoutputUtil.NowTime() + " 玩家：" + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ") 修改椅子(" + itemId + ")封包，坐上椅子時封鎖。 身上並沒有該物品");
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封鎖系統] " + c.getPlayer().getName() + " 因為修改封包而被管理員永久停權。"));
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密語]  " + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ") 修改椅子(" + itemId + ")封包，坐上椅子時封鎖。 身上並沒有該物品"));
            c.getPlayer().ban("修改封包", true, true, false);
            c.getSession().close();
            return;
        }
        if (itemId / 10000 == 301 && GameConstants.isFishingMap(chr.getMapId())) {
            if (!chr.haveItem(5340000) && !chr.haveItem(5340001)) {
                chr.dropMessage(1, "你背包里没有普通鱼竿或者高级鱼竿。");
            }
            if (!chr.haveItem(2300000) && !chr.haveItem(2300001)) {
                chr.dropMessage(1, "你背包里没有鱼饵。");
            }
            boolean haz = false;
            for (final IItem item : c.getPlayer().getInventory(MapleInventoryType.CASH).list()) {
                if (item.getItemId() == 5340000) {
                    haz = true;
                }
                else {
                    if (item.getItemId() != 5340001) {
                        continue;
                    }
                    haz = false;
                    if (chr.Getcharactera("" + chr.getId() + "", 1) <= 1) {
                        chr.startFishingTask(true);
                        break;
                    }
                    break;
                }
            }
            if (haz && chr.Getcharactera("" + chr.getId() + "", 1) <= 1) {
                chr.startFishingTask(false);
            }
        }
        chr.setChair(itemId);
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.showChair(chr.getId(), itemId), false);
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void CancelChair(final short id, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || c == null) {
            return;
        }
        if (id == -1) {
            if (chr.getChair() / 10000 == 301 && GameConstants.isFishingMap(chr.getMapId())) {
                chr.cancelFishingTask();
            }
            chr.setChair(0);
            c.sendPacket(MaplePacketCreator.cancelChair(-1));
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.showChair(chr.getId(), 0), false);
        }
        else {
            chr.setChair((int)id);
            c.sendPacket(MaplePacketCreator.cancelChair((int)id));
        }
        chr.cancelBuffStats(MapleBuffStat.MONSTER_RIDING);
    }
    
    public static final void TrockAddMap(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte addrem = slea.readByte();
        final byte vip = slea.readByte();
        if (vip == 1) {
            if (addrem == 0) {
                chr.deleteFromRocks(slea.readInt());
            }
            else if (addrem == 1 && !FieldLimitType.VipRock.check(chr.getMap().getFieldLimit())) {
                if (c.getPlayer().getMapId() != 180000000) {
                    chr.addRockMap();
                }
                else {
                    chr.dropMessage(1, "你不能儲存這張地图");
                }
            }
        }
        else if (addrem == 0) {
            chr.deleteFromRegRocks(slea.readInt());
        }
        else if (addrem == 1 && !FieldLimitType.VipRock.check(chr.getMap().getFieldLimit())) {
            if (c.getPlayer().getMapId() <= 197010000 && c.getPlayer().getMapId() != 180000000) {
                chr.addRegRockMap();
            }
            else {
                chr.dropMessage(1, "你不能儲存這張地图");
            }
        }
        c.sendPacket(MTSCSPacket.getTrockRefresh(chr, vip, addrem == 0));
    }
    
    public static final void CharInfoRequest(final int objectid, final MapleClient c, final MapleCharacter chr) {
        if (c.getPlayer() == null || c.getPlayer().getMap() == null) {
            return;
        }
        final MapleCharacter player = c.getPlayer().getMap().getCharacterById(objectid);
        c.sendPacket(MaplePacketCreator.enableActions());
        if (player == null) {
            c.getChannelServer();
            if (!ChannelServer.clones.isEmpty()) {
                c.getChannelServer();
                final ArrayList<离线人偶> clone = ChannelServer.clones;
                for (final 离线人偶 jr : clone) {
                    if (jr.charId == objectid) {
                        final MapleCharacter victim = MapleCharacter.loadCharFromDB(jr.charId, new MapleClient(null, null, (Channel)new MockIOSession()), true);
                        if (victim == null) {
                            continue;
                        }
                        c.sendPacket(MaplePacketCreator.charInfo(victim, c.getPlayer().getId() == objectid));
                        break;
                    }
                }
            }
        }
        if (player != null && !player.isClone() && (!player.isGM() || c.getPlayer().isGM())) {
            c.sendPacket(MaplePacketCreator.charInfo(player, c.getPlayer().getId() == objectid));
        }
    }
    
    public static final void TakeDamage(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (slea.available() < 5L) {
            return;
        }
        slea.skip(4);
        final byte type = slea.readByte();
        slea.skip(1);
        int damage = slea.readInt();
        int oid = 0;
        int monsteridfrom = 0;
        final int reflect = 0;
        byte direction = 0;
        final int pos_x = 0;
        final int pos_y = 0;
        int fake = 0;
        int mpattack = 0;
        boolean is_pg = false;
        boolean isDeadlyAttack = false;
        MapleMonster attacker = null;
        if (chr == null || chr.isHidden() || chr.getMap() == null) {
            return;
        }
        if (chr.isGM() && chr.isInvincible()) {
            return;
        }
        final PlayerStats stats = chr.getStat();
        if (type != -2 && type != -3 && type != -4) {
            monsteridfrom = slea.readInt();
            oid = slea.readInt();
            attacker = chr.getMap().getMonsterByOid(oid);
            direction = slea.readByte();
            if (attacker == null) {
                return;
            }
            if (type != -1) {
                final MobAttackInfo attackInfo = MobAttackInfoFactory.getInstance().getMobAttackInfo(attacker, (int)type);
                if (attackInfo != null) {
                    if (attackInfo.isDeadlyAttack()) {
                        isDeadlyAttack = true;
                        mpattack = stats.getMp() - 1;
                    }
                    else {
                        mpattack += attackInfo.getMpBurn();
                    }
                    final MobSkill skill = MobSkillFactory.getMobSkill(attackInfo.getDiseaseSkill(), attackInfo.getDiseaseLevel());
                    if (skill != null && (damage == -1 || damage > 0)) {
                        skill.applyEffect(chr, attacker, false);
                    }
                    attacker.setMp(attacker.getMp() - attackInfo.getMpCon());
                }
            }
        }
        if (damage == -1) {
            fake = 4020002 + (chr.getJob() / 10 - 40) * 100000;
        }
        else if (damage < -1 || damage > 60000) {
            AutobanManager.getInstance().addPoints(c, 1000, 60000L, "Taking abnormal amounts of damge from " + monsteridfrom + " : " + damage);
            return;
        }
        if (!chr.inBossMap()) {
            chr.getCheatTracker().checkTakeDamage(damage);
        }
        if (damage > 0) {
            chr.getCheatTracker().setAttacksWithoutHit(false);
            if (chr.getBuffedValue(MapleBuffStat.MORPH) != null) {
                chr.cancelMorphs();
            }
            if (type != -2 && type != -3 && type != -4) {
                final int bouncedam_ = ((Randomizer.nextInt(100) < chr.getStat().DAMreflect_rate) ? chr.getStat().DAMreflect : 0) + ((type == -1 && chr.getBuffedValue(MapleBuffStat.POWERGUARD) != null) ? ((int)chr.getBuffedValue(MapleBuffStat.POWERGUARD)) : 0);
                if (bouncedam_ > 0 && attacker != null) {
                    long bouncedamage = (long)(damage * bouncedam_ / 100);
                    chr.getMap().broadcastMessage(chr, MobPacket.damageMonster(oid, bouncedamage), chr.getPosition());
                    bouncedamage = Math.min(bouncedamage, attacker.getMobMaxHp() / 10L);
                    attacker.damage(chr, bouncedamage, true);
                    damage = (int)((long)damage - bouncedamage);
                    is_pg = true;
                }
            }
            if (type != -2 && type != -3 && type != -4) {
                switch (chr.getJob()) {
                    case 112: {
                        final ISkill skill2 = SkillFactory.getSkill(1120004);
                        if (chr.getSkillLevel(skill2) > 0) {
                            damage = (int)((double)skill2.getEffect((int)chr.getSkillLevel(skill2)).getX() / 1000.0 * (double)damage);
                            break;
                        }
                        break;
                    }
                    case 122: {
                        final ISkill skill2 = SkillFactory.getSkill(1220005);
                        if (chr.getSkillLevel(skill2) > 0) {
                            damage = (int)((double)skill2.getEffect((int)chr.getSkillLevel(skill2)).getX() / 1000.0 * (double)damage);
                            break;
                        }
                        break;
                    }
                    case 132: {
                        final ISkill skill2 = SkillFactory.getSkill(1320005);
                        if (chr.getSkillLevel(skill2) > 0) {
                            damage = (int)((double)skill2.getEffect((int)chr.getSkillLevel(skill2)).getX() / 1000.0 * (double)damage);
                            break;
                        }
                        break;
                    }
                    case 2112: {
                        final ISkill skill2 = SkillFactory.getSkill(21120004);
                        if (chr.getSkillLevel(skill2) > 0) {
                            damage = (int)((double)skill2.getEffect((int)chr.getSkillLevel(skill2)).getX() / 1000.0 * (double)damage);
                            break;
                        }
                        break;
                    }
                }
                if (chr.getBuffedValue(MapleBuffStat.COMBO_BARRIER) != null) {
                    damage = (int)((double)(int)chr.getBuffedSkill_X(MapleBuffStat.COMBO_BARRIER) / 1000.0 * (double)damage);
                }
            }
            if (chr.getBuffedValue(MapleBuffStat.MAGIC_GUARD) != null) {
                int hploss = 0;
                int mploss = 0;
                if (isDeadlyAttack) {
                    if (stats.getHp() > 1) {
                        hploss = stats.getHp() - 1;
                    }
                    if (stats.getMp() > 1) {
                        mploss = stats.getMp() - 1;
                    }
                    if (chr.getBuffedValue(MapleBuffStat.INFINITY) != null) {
                        mploss = 0;
                    }
                    chr.addMPHP(-hploss, -mploss);
                }
                else {
                    mploss = (int)((double)damage * ((double)chr.getBuffedValue(MapleBuffStat.MAGIC_GUARD) / 100.0)) + mpattack;
                    hploss = damage - mploss;
                    if (chr.getBuffedValue(MapleBuffStat.INFINITY) != null) {
                        mploss = 0;
                    }
                    else if (mploss > stats.getMp()) {
                        mploss = stats.getMp();
                        hploss = damage - mploss + mpattack;
                    }
                    chr.addMPHP(-hploss, -mploss);
                }
            }
            else if (chr.getBuffedValue(MapleBuffStat.MESOGUARD) != null) {
                damage = ((damage % 2 == 0) ? (damage / 2) : (damage / 2 + 1));
                final int mesoloss = (int)((double)damage * ((double)chr.getBuffedValue(MapleBuffStat.MESOGUARD) / 100.0));
                if (chr.getMeso() < mesoloss) {
                    chr.gainMeso(-chr.getMeso(), false);
                    chr.cancelBuffStats(MapleBuffStat.MESOGUARD);
                }
                else {
                    chr.gainMeso(-mesoloss, false);
                }
                if (isDeadlyAttack && stats.getMp() > 1) {
                    mpattack = stats.getMp() - 1;
                }
                chr.addMPHP(-damage, -mpattack);
            }
            else if (isDeadlyAttack) {
                chr.addMPHP((stats.getHp() > 1) ? (-(stats.getHp() - 1)) : 0, (stats.getMp() > 1) ? (-(stats.getMp() - 1)) : 0);
            }
            else {
                chr.addMPHP(-damage, -mpattack);
            }
            chr.handleBattleshipHP(-damage);
        }
        if (!chr.isHidden()) {
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.damagePlayer((int)type, monsteridfrom, chr.getId(), damage, fake, direction, reflect, is_pg, oid, pos_x, pos_y), false);
        }
    }
    
    public static final void UseItemEffect(final int itemId, final MapleClient c, final MapleCharacter chr) {
        final IItem toUse = chr.getInventory(MapleInventoryType.CASH).findById(itemId);
        if (toUse == null || toUse.getItemId() != itemId || toUse.getQuantity() < 1) {
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.itemEffect(chr.getId(), itemId), false);
            chr.setItemEffect(itemId);
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (itemId != 5510000) {
            chr.setItemEffect(itemId);
        }
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.itemEffect(chr.getId(), itemId), false);
    }
    
    public static final void CancelItemEffect(final int id, final MapleCharacter chr) {
        if (chr != null) {
            chr.cancelEffect(MapleItemInformationProvider.getInstance().getItemEffect(-id), false, -1L);
        }
    }
    
    public static final void CancelBuffHandler(final int sourceid, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        final ISkill skill = SkillFactory.getSkill(sourceid);
        if (skill != null) {
            if (skill.isChargeSkill()) {
                chr.setKeyDownSkill_Time(0L);
                chr.getMap().broadcastMessage(chr, MaplePacketCreator.skillCancel(chr, sourceid), false);
            }
            else {
                chr.cancelEffect(skill.getEffect(1), false, -1L);
            }
        }
    }
    
    public static final void SkillEffect(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int skillId = slea.readInt();
        final byte level = slea.readByte();
        final byte flags = slea.readByte();
        final byte speed = slea.readByte();
        final byte unk = slea.readByte();
        final ISkill skill = SkillFactory.getSkill(skillId);
        if (chr == null) {
            return;
        }
        final int skilllevel_serv = chr.getSkillLevel(skill);
        if (skilllevel_serv > 0 && skilllevel_serv == level && skill.isChargeSkill()) {
            chr.setKeyDownSkill_Time(System.currentTimeMillis());
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.skillEffect(chr, skillId, level, flags, speed, unk), false);
        }
    }
    
    public static final void SpecialMove(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || !chr.isAlive() || chr.getMap() == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        slea.skip(4);
        final int skillid = slea.readInt();
        final int skillLevel = slea.readByte();
        final ISkill skill = SkillFactory.getSkill(skillid);
        if (chr.getSkillLevel(skill) <= 0 || (chr.getSkillLevel(skill) != skillLevel && skillid != 12101000)) {
            if (!GameConstants.isMulungSkill(skillid) && !GameConstants.isPyramidSkill(skillid)) {
                return;
            }
            if (GameConstants.isMulungSkill(skillid)) {
                if (chr.getMapId() / 10000 != 92502) {
                    return;
                }
                chr.mulungEnergyModify(false);
            }
            else if (GameConstants.isPyramidSkill(skillid) && chr.getMapId() / 10000 != 92602) {
                return;
            }
        }
        final MapleStatEffect effect = skill.getEffect(chr.getSkillLevel(GameConstants.getLinkedSkill(skillid)));
        if (effect.getCooldown() > 0 && !chr.isGM()) {
            if (chr.skillisCooling(skillid)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (skillid != 5221006) {
                c.sendPacket(MaplePacketCreator.skillCooldown(skillid, effect.getCooldown()));
                chr.addCooldown(skillid, System.currentTimeMillis(), (long)(effect.getCooldown() * 1000));
            }
        }
        switch (skillid) {
            case 1121001:
            case 1221001:
            case 1321001:
            case 9001020: {
                final byte number_of_mobs = slea.readByte();
                slea.skip(3);
                for (int i = 0; i < number_of_mobs; ++i) {
                    final int mobId = slea.readInt();
                    final MapleMonster mob = chr.getMap().getMonsterByOid(mobId);
                    if (mob != null) {
                        mob.switchController(chr, mob.isControllerHasAggro());
                    }
                }
                chr.getMap().broadcastMessage(chr, MaplePacketCreator.showBuffeffect(chr.getId(), skillid, 1, slea.readByte()), chr.getPosition());
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            default: {
                Point pos = null;
                if (slea.available() == 5L || slea.available() == 7L) {
                    pos = slea.readPos();
                    final boolean faceLeft = slea.readByte() == 0;
                    int stance = chr.getStance();
                    if (faceLeft) {
                        stance &= 0xFE;
                    }
                    else {
                        stance |= 0x1;
                    }
                    chr.setStance(stance);
                }
                if (effect.isMagicDoor()) {
                    if (FieldLimitType.MysticDoor.check(chr.getMap().getFieldLimit())) {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        break;
                    }
                    if (chr.skillisCooling(2311002)) {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    c.sendPacket(MaplePacketCreator.skillCooldown(2311002, 2));
                    chr.addCooldown(2311002, System.currentTimeMillis(), 2000L);
                    effect.applyTo(c.getPlayer(), pos);
                    break;
                }
                else {
                    final int mountid = MapleStatEffect.parseMountInfo(c.getPlayer(), skill.getId());
                    if (mountid != 0 && mountid != GameConstants.getMountItem(skill.getId()) && !c.getPlayer().isGM() && c.getPlayer().getBuffedValue(MapleBuffStat.MONSTER_RIDING) == null && c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-118)) == null && !GameConstants.isMountItemAvailable(mountid, (int)c.getPlayer().getJob())) {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    effect.applyTo(c.getPlayer(), pos);
                    break;
                }
            }
        }
    }
    
    public static final void closeRangeAttack(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr, final boolean energy) {
        if (chr == null || (energy && chr.getBuffedValue(MapleBuffStat.ENERGY_CHARGE) == null && chr.getBuffedValue(MapleBuffStat.BODY_PRESSURE) == null && !GameConstants.isKOC((int)chr.getJob()))) {
            return;
        }
        final boolean mirror = chr.getBuffedValue(MapleBuffStat.MIRROR_IMAGE) != null;
        if (!chr.isAlive() || chr.getMap() == null) {
            chr.getCheatTracker().registerOffense(CheatingOffense.ATTACKING_WHILE_DEAD);
            return;
        }
        final AttackInfo attack = DamageParse.Modify_AttackCrit(DamageParse.parseDmgM(slea), chr, 1);
        double maxdamage = (double)chr.getStat().getCurrentMaxBaseDamage();
        int attackCount = 1;
        int skillLevel = 0;
        MapleStatEffect effect = null;
        ISkill skill = null;
        if (attack.skill != 0) {
            if (attack.skill == 5001003 || attack.skill == 5201001 || attack.skill == 5201006 || attack.skill == 5210000 || attack.skill == 5211005 || attack.skill == 5211004 || attack.skill == 5211006 || attack.skill == 5221004 || attack.skill == 5221009 || attack.skill == 4001344 || attack.skill == 4101005 || attack.skill == 4111005 || attack.skill == 4111004 || attack.skill == 4121003 || attack.skill == 4221003 || attack.skill == 4121007 || attack.skill == 14001004 || attack.skill == 14111002 || attack.skill == 14111005 || attack.skill == 13001003 || attack.skill == 13111000 || attack.skill == 13111001 || attack.skill == 13111007 || attack.skill == 13111006 || attack.skill == 13111002 || attack.skill == 3001005 || attack.skill == 3001004 || attack.skill == 3101005 || attack.skill == 3111006 || attack.skill == 3111003 || attack.skill == 3111004 || attack.skill == 3121003 || attack.skill == 3121004 || attack.skill == 3201005 || attack.skill == 3211006 || attack.skill == 3211004 || attack.skill == 3211003 || attack.skill == 3221003 || attack.skill == 3221007 || attack.skill == 3221001) {
                c.getSession().close();
                return;
            }
            if (chr.getMapId() == 109010000 || chr.getMapId() == 109030001 || chr.getMapId() == 109060000 || chr.getMapId() == 109040000) {
                chr.dropMessage(5, "該地图無法使用技能");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (attack.skill == 1221011 && chr.getMapId() >= 211060000 && chr.getMapId() <= 211070200) {
                chr.dropMessage(5, "該地图無法該使用技能");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (attack.skill == 21120006 && (chr.getMapId() == 211060100 || chr.getMapId() == 211060300 || chr.getMapId() == 211060500 || chr.getMapId() == 211060700 || chr.getMapId() == 211060900)) {
                chr.dropMessage(5, "該地图無法該使用技能");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            skill = SkillFactory.getSkill(GameConstants.getLinkedSkill(attack.skill));
            skillLevel = chr.getSkillLevel(skill);
            effect = attack.getAttackEffect(chr, skillLevel, skill);
            if (effect == null) {
                return;
            }
            maxdamage *= (double)effect.getDamage() / 100.0;
            attackCount = ((effect.getAttackCount() > effect.getBulletCount()) ? effect.getAttackCount() : effect.getBulletCount());
            if (effect.getCooldown() > 0 && !chr.isGM()) {
                if (chr.skillisCooling(attack.skill)) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                c.sendPacket(MaplePacketCreator.skillCooldown(attack.skill, effect.getCooldown()));
                chr.addCooldown(attack.skill, System.currentTimeMillis(), (long)(effect.getCooldown() * 1000));
            }
        }
        attackCount *= (mirror ? 2 : 1);
        if (!energy) {
            if ((chr.getMapId() == 109060000 || chr.getMapId() == 109060002 || chr.getMapId() == 109060004) && attack.skill == 0) {
                MapleSnowballs.hitSnowball(chr);
            }
            int numFinisherOrbs = 0;
            final Integer comboBuff = chr.getBuffedValue(MapleBuffStat.COMBO);
            if (isFinisher(attack.skill)) {
                if (comboBuff != null) {
                    numFinisherOrbs = (int)comboBuff - 1;
                }
                chr.handleOrbconsume();
            }
            else if (attack.targets > 0 && comboBuff != null) {
                switch (chr.getJob()) {
                    case 111:
                    case 112:
                    case 1110:
                    case 1111:
                    case 1112: {
                        if (attack.skill != 1111008) {
                            chr.handleOrbgain();
                            break;
                        }
                        break;
                    }
                }
            }
            switch (chr.getJob()) {
                case 511:
                case 512: {
                    chr.handleEnergyCharge(5110001, attack.targets * attack.hits);
                    break;
                }
                case 1510:
                case 1511:
                case 1512: {
                    chr.handleEnergyCharge(15100004, attack.targets * attack.hits);
                    break;
                }
            }
            if (attack.targets > 0 && attack.skill == 1211002) {
                final int advcharge_level = chr.getSkillLevel(SkillFactory.getSkill(1220010));
                if (advcharge_level > 0) {
                    if (!SkillFactory.getSkill(1220010).getEffect(advcharge_level).makeChanceResult()) {
                        chr.cancelEffectFromBuffStat(MapleBuffStat.WK_CHARGE);
                        chr.cancelEffectFromBuffStat(MapleBuffStat.LIGHTNING_CHARGE);
                    }
                }
                else {
                    chr.cancelEffectFromBuffStat(MapleBuffStat.WK_CHARGE);
                    chr.cancelEffectFromBuffStat(MapleBuffStat.LIGHTNING_CHARGE);
                }
            }
            if (attack.skill == 1009) {
                chr.getMap().broadcastMessage(chr, MaplePacketCreator.showBuffeffect(chr.getId(), attack.skill, 1), false);
            }
            if (numFinisherOrbs > 0) {
                maxdamage *= (double)numFinisherOrbs;
            }
            else if (comboBuff != null) {
                ISkill combo;
                if (c.getPlayer().getJob() == 1110 || c.getPlayer().getJob() == 1111 || c.getPlayer().getJob() == 1112) {
                    combo = SkillFactory.getSkill(11111001);
                }
                else {
                    combo = SkillFactory.getSkill(1111002);
                }
                if (c.getPlayer().getSkillLevel(combo) > 0) {
                    maxdamage *= 1.0 + ((double)combo.getEffect((int)c.getPlayer().getSkillLevel(combo)).getDamage() / 100.0 - 1.0) * (double)((int)comboBuff - 1);
                }
            }
            if (isFinisher(attack.skill)) {
                if (numFinisherOrbs == 0) {
                    return;
                }
                maxdamage = 500000.0;
            }
        }
        chr.checkFollow();
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.closeRangeAttack(chr.getId(), (int)attack.tbyte, attack.skill, skillLevel, attack.display, attack.animation, attack.speed, attack.allDamage, energy, (int)chr.getLevel(), chr.getStat().passive_mastery(), attack.unk, attack.charge), chr.getPosition());
        DamageParse.applyAttack(attack, skill, c.getPlayer(), attackCount, maxdamage, effect, mirror ? AttackType.NON_RANGED_WITH_MIRROR : AttackType.NON_RANGED);
        final WeakReference<MapleCharacter>[] clones = chr.getClones();
        for (int i = 0; i < clones.length; ++i) {
            if (clones[i].get() != null) {
                final MapleCharacter clone = (MapleCharacter)clones[i].get();
                final ISkill skil2 = skill;
                final int skillLevel2 = skillLevel;
                final int attackCount2 = attackCount;
                final double maxdamage2 = maxdamage;
                final MapleStatEffect eff2 = effect;
                final AttackInfo attack2 = DamageParse.DivideAttack(attack, chr.isGM() ? 1 : 4);
                CloneTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        clone.getMap().broadcastMessage(MaplePacketCreator.closeRangeAttack(clone.getId(), (int)attack2.tbyte, attack2.skill, skillLevel2, attack2.display, attack2.animation, attack2.speed, attack2.allDamage, energy, (int)clone.getLevel(), clone.getStat().passive_mastery(), attack2.unk, attack2.charge));
                        DamageParse.applyAttack(attack2, skil2, chr, attackCount2, maxdamage2, eff2, mirror ? AttackType.NON_RANGED_WITH_MIRROR : AttackType.NON_RANGED);
                    }
                }, (long)(500 * i + 500));
            }
        }
    }
    
    public static final void rangedAttack(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        if (!chr.isAlive() || chr.getMap() == null) {
            chr.getCheatTracker().registerOffense(CheatingOffense.ATTACKING_WHILE_DEAD);
            return;
        }
        final AttackInfo attack = DamageParse.Modify_AttackCrit(DamageParse.parseDmgR(slea), chr, 2);
        int bulletCount = 1;
        int skillLevel = 0;
        MapleStatEffect effect = null;
        ISkill skill = null;
        if (attack.skill != 0) {
            if (chr.getMapId() == 109010000 || chr.getMapId() == 109030001 || chr.getMapId() == 109060000 || chr.getMapId() == 109040000) {
                chr.dropMessage(5, "該地图無法使用技能");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            skill = SkillFactory.getSkill(GameConstants.getLinkedSkill(attack.skill));
            skillLevel = chr.getSkillLevel(skill);
            effect = attack.getAttackEffect(chr, skillLevel, skill);
            if (effect == null) {
                return;
            }
            if (attack.skill == 1221011 && chr.getMapId() >= 211060000 && chr.getMapId() <= 211070200) {
                chr.dropMessage(5, "該地图無法該使用技能");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (attack.skill == 21120006 && (chr.getMapId() == 211060100 || chr.getMapId() == 211060300 || chr.getMapId() == 211060500 || chr.getMapId() == 211060700 || chr.getMapId() == 211060900)) {
                chr.dropMessage(5, "該地图無法該使用技能");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            switch (attack.skill) {
                case 3101005:
                case 3201005:
                case 3211004:
                case 5201001:
                case 13111007:
                case 14101006:
                case 21110004:
                case 21120006: {
                    bulletCount = effect.getAttackCount();
                    break;
                }
                default: {
                    bulletCount = effect.getBulletCount();
                    break;
                }
            }
            if (effect.getCooldown() > 0 && !chr.isGM()) {
                if (chr.skillisCooling(attack.skill)) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                c.sendPacket(MaplePacketCreator.skillCooldown(attack.skill, effect.getCooldown()));
                chr.addCooldown(attack.skill, System.currentTimeMillis(), (long)(effect.getCooldown() * 1000));
            }
        }
        final Integer ShadowPartner = chr.getBuffedValue(MapleBuffStat.SHADOWPARTNER);
        if (ShadowPartner != null) {
            bulletCount *= 2;
        }
        int projectile = 0;
        int visProjectile = 0;
        if (attack.AOE != 0 && chr.getBuffedValue(MapleBuffStat.SOULARROW) == null && attack.skill != 4111004) {
            if (chr.getInventory(MapleInventoryType.USE).getItem((short)attack.slot) == null) {
                return;
            }
            projectile = chr.getInventory(MapleInventoryType.USE).getItem((short)attack.slot).getItemId();
            if (attack.csstar > 0) {
                if (chr.getInventory(MapleInventoryType.CASH).getItem((short)attack.csstar) == null) {
                    return;
                }
                visProjectile = chr.getInventory(MapleInventoryType.CASH).getItem((short)attack.csstar).getItemId();
            }
            else {
                visProjectile = projectile;
            }
            if (chr.getBuffedValue(MapleBuffStat.SPIRIT_CLAW) == null) {
                int bulletConsume = bulletCount;
                if (effect != null && effect.getBulletConsume() != 0) {
                    bulletConsume = effect.getBulletConsume() * ((ShadowPartner != null) ? 2 : 1);
                }
                if (!MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, projectile, bulletConsume, false, true)) {
                    return;
                }
            }
        }
        if (attack.skill == 5211005) {
            if (!MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, 2332000, 1, false, true)) {
                c.getPlayer().dropMessage(5, "您身上的冰蓝魔方不足");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
        }
        else if (attack.skill == 5211004 && !MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, 2331000, 1, false, true)) {
            c.getPlayer().dropMessage(5, "您身上的火红魔方不足");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final Integer comboBuff = chr.getBuffedValue(MapleBuffStat.COMBO);
        if (attack.targets > 0 && comboBuff != null) {
            switch (chr.getJob()) {
                case 111:
                case 112:
                case 1110:
                case 1111:
                case 1112: {
                    if (attack.skill != 1111008) {
                        chr.handleOrbgain();
                        break;
                    }
                    break;
                }
            }
        }
        switch (chr.getJob()) {
            case 511:
            case 512: {
                chr.handleEnergyCharge(5110001, attack.targets * attack.hits);
                break;
            }
            case 1510:
            case 1511:
            case 1512: {
                chr.handleEnergyCharge(15100004, attack.targets * attack.hits);
                break;
            }
        }
        int projectileWatk = 0;
        if (projectile != 0) {
            projectileWatk = MapleItemInformationProvider.getInstance().getWatkForProjectile(projectile);
        }
        final PlayerStats statst = chr.getStat();
        double basedamage = 0.0;
        Label_1112: {
            switch (attack.skill) {
                case 4001344:
                case 4121007:
                case 14001004:
                case 14111005: {
                    basedamage = (double)((float)statst.getTotalLuk() * 5.0f * (float)(statst.getTotalWatk() + projectileWatk) / 100.0f);
                    break;
                }
                case 4111004: {
                    basedamage = 13000.0;
                    break;
                }
                default: {
                    if (projectileWatk != 0) {
                        basedamage = (double)statst.calculateMaxBaseDamage(statst.getTotalMagic(), statst.getTotalWatk() + projectileWatk);
                    }
                    else {
                        basedamage = (double)statst.getCurrentMaxBaseDamage();
                    }
                    switch (attack.skill) {
                        case 13101005: {
                            if (effect != null) {
                                basedamage *= (double)effect.getX() / 100.0;
                                break Label_1112;
                            }
                            break Label_1112;
                        }
                    }
                    break;
                }
            }
        }
        if (effect != null) {
            basedamage *= (double)effect.getDamage() / 100.0;
            int money = effect.getMoneyCon();
            if (money != 0) {
                if (money > chr.getMeso()) {
                    money = chr.getMeso();
                }
                chr.gainMeso(-money, false);
            }
        }
        chr.checkFollow();
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.rangedAttack(chr.getId(), attack.tbyte, attack.skill, skillLevel, attack.display, attack.animation, attack.speed, visProjectile, attack.allDamage, attack.position, (int)chr.getLevel(), chr.getStat().passive_mastery(), attack.unk), chr.getPosition());
        DamageParse.applyAttack(attack, skill, chr, bulletCount, basedamage, effect, (ShadowPartner != null) ? AttackType.RANGED_WITH_SHADOWPARTNER : AttackType.RANGED);
        final WeakReference<MapleCharacter>[] clones = chr.getClones();
        for (int i = 0; i < clones.length; ++i) {
            if (clones[i].get() != null) {
                final MapleCharacter clone = (MapleCharacter)clones[i].get();
                final ISkill skil2 = skill;
                final MapleStatEffect eff2 = effect;
                final double basedamage2 = basedamage;
                final int bulletCount2 = bulletCount;
                final int visProjectile2 = visProjectile;
                final int skillLevel2 = skillLevel;
                final AttackInfo attack2 = DamageParse.DivideAttack(attack, chr.isGM() ? 1 : 4);
                CloneTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        clone.getMap().broadcastMessage(MaplePacketCreator.rangedAttack(clone.getId(), attack2.tbyte, attack2.skill, skillLevel2, attack2.display, attack2.animation, attack2.speed, visProjectile2, attack2.allDamage, attack2.position, (int)clone.getLevel(), clone.getStat().passive_mastery(), attack2.unk));
                        DamageParse.applyAttack(attack2, skil2, chr, bulletCount2, basedamage2, eff2, AttackType.RANGED);
                    }
                }, (long)(500 * i + 500));
            }
        }
    }
    
    public static final void MagicDamage(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        if (!chr.isAlive() || chr.getMap() == null) {
            chr.getCheatTracker().registerOffense(CheatingOffense.ATTACKING_WHILE_DEAD);
            return;
        }
        final AttackInfo attack = DamageParse.Modify_AttackCrit(DamageParse.parseDmgMa(slea), chr, 3);
        final ISkill skill = SkillFactory.getSkill(GameConstants.getLinkedSkill(attack.skill));
        final int skillLevel = chr.getSkillLevel(skill);
        final MapleStatEffect effect = attack.getAttackEffect(chr, skillLevel, skill);
        if (effect == null) {
            return;
        }
        if (chr.getMapId() == 109010000 || chr.getMapId() == 109030001 || chr.getMapId() == 109060000 || chr.getMapId() == 109040000) {
            chr.dropMessage(5, "該地图無法使用技能");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (attack.skill == 1221011 && chr.getMapId() >= 211060000 && chr.getMapId() <= 211070200) {
            chr.dropMessage(5, "該地图無法該使用技能");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (attack.skill == 21120006 && (chr.getMapId() == 211060100 || chr.getMapId() == 211060300 || chr.getMapId() == 211060500 || chr.getMapId() == 211060700 || chr.getMapId() == 211060900)) {
            chr.dropMessage(5, "該地图無法該使用技能");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (effect.getCooldown() > 0 && !chr.isGM()) {
            if (chr.skillisCooling(attack.skill)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            c.sendPacket(MaplePacketCreator.skillCooldown(attack.skill, effect.getCooldown()));
            chr.addCooldown(attack.skill, System.currentTimeMillis(), (long)(effect.getCooldown() * 1000));
        }
        chr.checkFollow();
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.magicAttack(chr.getId(), (int)attack.tbyte, attack.skill, skillLevel, attack.display, attack.animation, attack.speed, attack.allDamage, attack.charge, (int)chr.getLevel(), attack.unk), chr.getPosition());
        DamageParse.applyAttackMagic(attack, skill, c.getPlayer(), effect);
        final WeakReference<MapleCharacter>[] clones = chr.getClones();
        for (int i = 0; i < clones.length; ++i) {
            if (clones[i].get() != null) {
                final MapleCharacter clone = (MapleCharacter)clones[i].get();
                final ISkill skil2 = skill;
                final MapleStatEffect eff2 = effect;
                final int skillLevel2 = skillLevel;
                final AttackInfo attack2 = DamageParse.DivideAttack(attack, chr.isGM() ? 1 : 4);
                CloneTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        clone.getMap().broadcastMessage(MaplePacketCreator.magicAttack(clone.getId(), (int)attack2.tbyte, attack2.skill, skillLevel2, attack2.display, attack2.animation, attack2.speed, attack2.allDamage, attack2.charge, (int)clone.getLevel(), attack2.unk));
                        DamageParse.applyAttackMagic(attack2, skil2, chr, eff2);
                    }
                }, (long)(500 * i + 500));
            }
        }
    }
    
    public static final void DropMeso(final int meso, final MapleCharacter chr) {
        final int 丢出金币开关 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"丢出金币开关"));
        if (丢出金币开关 > 0) {
            chr.dropMessage(1, "管理员已经从后台禁止丢出金币");
            chr.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!chr.isAlive() || meso < 10 || meso > 50000 || meso > chr.getMeso()) {
            chr.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        chr.gainMeso(-meso, false, true);
        chr.getMap().spawnMesoDrop(meso, chr.getPosition(), (MapleMapObject)chr, chr, true, (byte)0);
        chr.getCheatTracker().checkDrop(true);
    }
    
    public static final void ChangeEmotion(final int emote, final MapleCharacter chr) {
        if (emote > 7) {
            final int emoteid = 5159992 + emote;
            final MapleInventoryType type = GameConstants.getInventoryType(emoteid);
            if (chr.getInventory(type).findById(emoteid) == null) {
                return;
            }
        }
        if (emote > 0 && chr != null && chr.getMap() != null) {
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.facialExpression(chr, emote), false);
            final WeakReference<MapleCharacter>[] clones = chr.getClones();
            for (int i = 0; i < clones.length; ++i) {
                if (clones[i].get() != null) {
                    final MapleCharacter clone = (MapleCharacter)clones[i].get();
                    CloneTimer.getInstance().schedule((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            clone.getMap().broadcastMessage(MaplePacketCreator.facialExpression(clone, emote));
                        }
                    }, (long)(500 * i + 500));
                }
            }
        }
    }
    
    public static final void Heal(final LittleEndianAccessor slea, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        slea.readInt();
        int healHP = slea.readShort();
        int healMP = slea.readShort();
        slea.readByte();
        slea.readInt();
        final PlayerStats stats = chr.getStat();
        if (stats.getHp() <= 0) {
            return;
        }
        if (healHP != 0 && chr.canHP()) {
            int check = (int)stats.getHealHP();
            if (chr.getChair() != 0) {
                check += 150;
            }
            if (healHP > check * 5) {
                chr.getCheatTracker().registerOffense(CheatingOffense.REGEN_HIGH_HP, String.valueOf(healHP) + "服務端:" + stats.getHealHP());
                healHP = (int)stats.getHealHP();
            }
            chr.addHP(healHP);
        }
        if (healMP != 0 && chr.canMP()) {
            int check = (int)stats.getHealMP();
            if (chr.getChair() != 0) {
                check += 150;
            }
            if (healMP > check * 5) {
                healMP = (int)stats.getHealMP();
            }
            chr.addMP(healMP);
        }
    }
    
    public static final void MovePlayer(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        final Point Original_Pos = chr.getPosition();
        slea.skip(33);
        List<LifeMovementFragment> res;
        try {
            res = MovementParse.parseMovement(slea, 1);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("AIOBE Type1:\n" + slea.toString(true));
            return;
        }
        if (res != null && c.getPlayer().getMap() != null) {
            if (slea.available() != 8L) {
                System.err.println("slea.available != 8 (movement parsing error)\n" + slea.toString(true));
                return;
            }
            final List<LifeMovementFragment> res2 = new ArrayList<LifeMovementFragment>((Collection<? extends LifeMovementFragment>)res);
            final MapleMap map = c.getPlayer().getMap();
            if (chr.isHidden()) {
                chr.setLastRes(res2);
                c.getPlayer().getMap().broadcastGMMessage(chr, MaplePacketCreator.movePlayer(chr.getId(), res, Original_Pos), false);
            }
            else {
                c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.movePlayer(chr.getId(), res, Original_Pos), false);
            }
            MovementParse.updatePosition(res, (AnimatedMapleMapObject)chr, 0);
            final Point pos = chr.getPosition();
            map.movePlayer(chr, pos);
            if (chr.getFollowId() > 0 && chr.isFollowOn() && chr.isFollowInitiator()) {
                final MapleCharacter fol = map.getCharacterById(chr.getFollowId());
                if (fol != null) {
                    final Point original_pos = fol.getPosition();
                    fol.getClient().sendPacket(MaplePacketCreator.moveFollow(Original_Pos, original_pos, pos, res));
                    MovementParse.updatePosition(res, (AnimatedMapleMapObject)fol, 0);
                    map.broadcastMessage(fol, MaplePacketCreator.movePlayer(fol.getId(), res, original_pos), false);
                }
                else {
                    chr.checkFollow();
                }
            }
            final WeakReference<MapleCharacter>[] clones = chr.getClones();
            for (int i = 0; i < clones.length; ++i) {
                if (clones[i].get() != null) {
                    final MapleCharacter clone = (MapleCharacter)clones[i].get();
                    final List<LifeMovementFragment> res3 = new ArrayList<LifeMovementFragment>((Collection<? extends LifeMovementFragment>)res2);
                    CloneTimer.getInstance().schedule((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (clone.getMap() == map) {
                                    if (clone.isHidden()) {
                                        clone.setLastRes((List<LifeMovementFragment>)res3);
                                    }
                                    else {
                                        map.broadcastMessage(clone, MaplePacketCreator.movePlayer(clone.getId(), (List<LifeMovementFragment>)res3, Original_Pos), false);
                                    }
                                    MovementParse.updatePosition((List<LifeMovementFragment>)res3, (AnimatedMapleMapObject)clone, 0);
                                    map.movePlayer(clone, pos);
                                }
                            }
                            catch (Exception ex) {}
                        }
                    }, (long)(500 * i + 500));
                }
            }
            int count = c.getPlayer().getFallCounter();
            if (map.getFootholds().findBelow(c.getPlayer().getPosition()) == null && c.getPlayer().getPosition().y > c.getPlayer().getOldPosition().y && c.getPlayer().getPosition().x == c.getPlayer().getOldPosition().x) {
                if (count > 10) {
                    if (map.getId() == 926010010 || map.getId() == 926010030 || map.getId() == 926010050 || map.getId() == 926010070) {
                        c.getPlayer().changeMap(926010000);
                    }
                    else {
                        c.getPlayer().changeMap(map, map.getPortal(0));
                        c.getPlayer().setFallCounter(0);
                    }
                }
                else {
                    c.getPlayer().setFallCounter(++count);
                }
            }
            else if (count > 0) {
                c.getPlayer().setFallCounter(0);
            }
            c.getPlayer().setOldPosition(new Point(c.getPlayer().getPosition()));
        }
    }
    
    public static final void ChangeMapSpecial(final String portal_name, final MapleClient c, final MapleCharacter chr) {
        final MaplePortal portal = chr.getMap().getPortal(portal_name);
        if (portal != null) {
            portal.enterPortal(c);
        }
    }
    
    public static final void ChangeMap(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        if (slea.available() != 0L) {
            slea.readByte();
            final int targetid = slea.readInt();
            MaplePortal portal = null;
            try {
                portal = chr.getMap().getPortal(slea.readMapleAsciiString());
            }
            catch (Exception ex) {}
            if (slea.available() >= 6L) {
                slea.readInt();
            }
            slea.skip(1);
            final boolean wheel = slea.readByte() > 0 && !MapConstants.isEventMap(chr.getMapId()) && chr.haveItem(5510000, 1, false, true);
            if (chr.getMapId() == 109020001 && portal != null && portal.getName().equals((Object)"join00")) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (targetid != -1 && !chr.isAlive()) {
                chr.setStance(0);
                if (chr.getEventInstance() != null && chr.getEventInstance().revivePlayer(chr) && chr.isAlive()) {
                    return;
                }
                if (chr.getPyramidSubway() != null) {
                    chr.getStat().setHp(50);
                    chr.getPyramidSubway().fail(chr);
                    return;
                }
                if (!wheel) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    chr.isSquadPlayerID();
                    chr.getStat().setHp(50);
                    final MapleMap to = chr.getMap().getReturnMap();
                    chr.changeMap(to, to.getPortal(0));
                    c.sendPacket(MaplePacketCreator.enableActions());
                }
                else {
                    chr.getStat().setHp(chr.getStat().getMaxHp() / 100 * 40);
                    MapleInventoryManipulator.removeById(c, MapleInventoryType.CASH, 5510000, 1, true, false);
                    chr.isSquadPlayerID();
                    final MapleMap to = chr.getMap();
                    chr.changeMap(to, to.getPortal(0));
                }
            }
            else if (targetid != -1 && chr.isGM()) {
                final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                chr.changeMap(to, to.getPortal(0));
            }
            else if (targetid != -1 && !chr.isGM()) {
                final int divi = chr.getMapId() / 100;
                if (divi == 9130401) {
                    if (targetid == 130000000 || targetid / 100 == 9130401) {
                        final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                        chr.changeMap(to2, to2.getPortal(0));
                    }
                }
                else if (divi == 9140900) {
                    if (targetid == 914090011 || targetid == 914090012 || targetid == 914090013 || targetid == 140090000) {
                        final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                        chr.changeMap(to2, to2.getPortal(0));
                    }
                }
                else if (divi == 9140901 && targetid == 140000000) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (divi == 9140902 && (targetid == 140030000 || targetid == 140000000)) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (divi == 9000900 && targetid / 100 == 9000900 && targetid > chr.getMapId()) {
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (divi / 1000 == 9000 && targetid / 100000 == 9000) {
                    if (targetid < 900090000 || targetid > 900090004) {
                        c.sendPacket(UIPacket.IntroDisableUI(false));
                        c.sendPacket(UIPacket.IntroLock(false));
                        c.sendPacket(MaplePacketCreator.enableActions());
                    }
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (divi / 10 == 1020 && targetid == 1020000) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (chr.getMapId() == 900090101 && targetid == 100030100) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (chr.getMapId() == 2010000 && targetid == 104000000) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
                else if (chr.getMapId() == 106020001 || chr.getMapId() == 106020502) {
                    if (targetid == chr.getMapId() - 1) {
                        c.sendPacket(UIPacket.IntroDisableUI(false));
                        c.sendPacket(UIPacket.IntroLock(false));
                        c.sendPacket(MaplePacketCreator.enableActions());
                        final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                        chr.changeMap(to2, to2.getPortal(0));
                    }
                }
                else if (chr.getMapId() == 0 && targetid == 10000) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to2 = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to2, to2.getPortal(0));
                }
            }
            else if (portal != null) {
                if (chr.getMapId() == 211060000 && portal.getTargetMapId() == 211040600) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                portal.enterPortal(c);
            }
            else {
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
    }
    
    public static final void InnerPortal(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        final MaplePortal portal = chr.getMap().getPortal(slea.readMapleAsciiString());
        final Point Original_Pos = chr.getPosition();
        final int toX = slea.readShort();
        final int toY = slea.readShort();
        if (portal == null) {
            return;
        }
        if (portal.getPosition().distanceSq((Point2D)chr.getPosition()) > 22500.0) {
            chr.getCheatTracker().registerOffense(CheatingOffense.USING_FARAWAY_PORTAL);
        }
        chr.getMap().movePlayer(chr, new Point(toX, toY));
        chr.checkFollow();
    }
    
    public static final void snowBall(final LittleEndianAccessor slea, final MapleClient c) {
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void leftKnockBack(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getMapId() / 10000 == 10906) {
            c.sendPacket(MaplePacketCreator.leftKnockBack());
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public static void ShowExpChair(final LittleEndianAccessor slea, final MapleClient client) {
        final int chairid = slea.readInt();
        client.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void AranCombo(final MapleClient c, final MapleCharacter chr, int toAdd) {
        if (chr != null && chr.getJob() >= 2000 && chr.getJob() <= 2112) {
            if (chr.hasGmLevel(5)) {
                toAdd += 9;
            }
            int combo = chr.getCombo();
            final long curr = System.currentTimeMillis();
            if (combo > 0 && curr - chr.getLastCombo() > 10000L) {
                combo = 0;
            }
            combo = (short)Math.min(30000, combo + toAdd);
            chr.setLastCombo(curr);
            chr.setCombo(combo);
            switch (combo) {
                case 10:
                case 20:
                case 30:
                case 40:
                case 50:
                case 60:
                case 70:
                case 80:
                case 90:
                case 100: {
                    if (chr.getSkillLevel(21000000) < combo / 10) {
                        break;
                    }
                    SkillFactory.getSkill(21000000).getEffect(combo / 10).applyComboBuff(chr, combo);
                    break;
                }
            }
        }
    }
    
    public static final void handleLogout(final LittleEndianAccessor slea, final MapleClient c) {
        String ACname = null;
        try {
            ACname = slea.readMapleAsciiString();
        }
        catch (NegativeArraySizeException ex2) {}
        if (ACname != null) {
            int LoginState = 0;
            int id = 0;
            String ip = "";
            final StringBuilder sb = new StringBuilder();
            sb.append("select id, loggedin from accounts where name = ?");
            if (c != null) {
                ip = c.getSessionIPAddress();
                sb.append(" and SessionIP = ?");
            }
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                 final PreparedStatement ps = con.prepareStatement(sb.toString())) {
                ps.setString(1, ACname);
                if (c != null) {
                    ps.setString(2, ip);
                }
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        LoginState = rs.getInt("loggedin");
                        id = rs.getInt("id");
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("[handleLogout] 處理登出出錯" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (c != null) {
                c.setAccID(id);
                if (LoginState > 0) {
                    c.updateLoginState(0, c.getSessionIPAddress());
                }
            }
        }
    }
    
    public static void SpecialAttack(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || chr.getMap() == null) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        final int pos_x = slea.readInt();
        final int pos_y = slea.readInt();
        final int display = slea.readInt();
        final int skillId = slea.readInt();
        final ISkill skill = SkillFactory.getSkill(skillId);
        final int skilllevel = chr.getSkillLevel(skillId);
        if (skill == null || skilllevel <= 0) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.showBuffeffect(chr.getId(), skillId, 1, (int)chr.getLevel(), skilllevel), false);
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.showSpecialAttack(chr.getId(), pos_x, pos_y, display, skillId), chr.getTruePosition());
    }
}
