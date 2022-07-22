package server.maps;

import java.util.Iterator;
import java.util.Collection;
import server.life.MapleMonster;
import tools.MaplePacketCreator;
import server.life.MapleLifeFactory;
import client.MapleStat;
import client.MapleBuffStat;
import client.MapleCharacter;
import handling.channel.handler.AttackInfo;

public class MaplePvp
{
    private static int pvpDamage;
    private static int maxDis;
    private static int maxHeight;
    private static boolean isAoe;
    public static boolean isLeft;
    public static boolean isRight;
    
    private static boolean isMeleeAttack(final AttackInfo attack) {
        switch (attack.skill) {
            case 1001004:
            case 1001005:
            case 1111003:
            case 1111004:
            case 1121006:
            case 1121008:
            case 1221007:
            case 1221009:
            case 1311001:
            case 1311002:
            case 1311003:
            case 1311004:
            case 1311005:
            case 1321003:
            case 4001334:
            case 4201005:
            case 4221001: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static boolean isRangeAttack(final AttackInfo attack) {
        switch (attack.skill) {
            case 2001004:
            case 2001005:
            case 2101004:
            case 2101005:
            case 2111006:
            case 2121003:
            case 2201004:
            case 2211002:
            case 2211003:
            case 2211006:
            case 2221003:
            case 2221006:
            case 2301005:
            case 2321007:
            case 3001004:
            case 3001005:
            case 3111006:
            case 3121003:
            case 3121004:
            case 3211006:
            case 3221001:
            case 3221003:
            case 3221007:
            case 4001344:
            case 4101005:
            case 4111004:
            case 4111005:
            case 4121003:
            case 4121007:
            case 4211002:
            case 4221003:
            case 4221007: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static boolean isAoeAttack(final AttackInfo attack) {
        switch (attack.skill) {
            case 1111005:
            case 1111006:
            case 1211002:
            case 1221011:
            case 1311006:
            case 2111002:
            case 2111003:
            case 2121001:
            case 2121006:
            case 2121007:
            case 2201005:
            case 2221001:
            case 2221007:
            case 2311004:
            case 2321001:
            case 2321008:
            case 3101005:
            case 3111003:
            case 3111004:
            case 3201005:
            case 3211003:
            case 3211004:
            case 4121004:
            case 4121008:
            case 4211004:
            case 4221004: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static void getDirection(final AttackInfo attack) {
        MaplePvp.isRight = true;
        MaplePvp.isLeft = true;
    }
    
    private static void DamageBalancer(final AttackInfo attack) {
        if (attack.skill == 0) {
            MaplePvp.pvpDamage = 100;
            MaplePvp.maxDis = 130;
            MaplePvp.maxHeight = 35;
        }
        else if (isMeleeAttack(attack)) {
            MaplePvp.maxDis = 130;
            MaplePvp.maxHeight = 45;
            MaplePvp.isAoe = false;
            if (attack.skill == 4201005) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 70.0 + 5.0);
            }
            else if (attack.skill == 1121008) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 140.0 + 180.0);
                MaplePvp.maxHeight = 50;
            }
            else if (attack.skill == 4221001) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 50.0 + 150.0);
            }
            else if (attack.skill == 1121006 || attack.skill == 1221007 || attack.skill == 1321003) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 120.0 + 80.0);
            }
            else {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 350.0 + 250.0);
            }
        }
        else if (isRangeAttack(attack)) {
            MaplePvp.maxDis = 300;
            MaplePvp.maxHeight = 40;
            MaplePvp.isAoe = false;
            if (attack.skill == 4201005) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 70.0 + 5.0);
            }
            else if (attack.skill == 4121007) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 45.0 + 15.0);
            }
            else if (attack.skill == 4001344 || attack.skill == 2001005) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 105.0 + 90.0);
            }
            else if (attack.skill == 4221007) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 170.0 + 180.0);
            }
            else if (attack.skill == 3121004 || attack.skill == 3111006 || attack.skill == 3211006) {
                MaplePvp.maxDis = 450;
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 30.0 + 20.0);
            }
            else if (attack.skill == 2121003 || attack.skill == 2221003) {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 300.0 + 300.0);
            }
            else {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 150.0 + 250.0);
            }
        }
        else if (isAoeAttack(attack)) {
            MaplePvp.maxDis = 350;
            MaplePvp.maxHeight = 350;
            MaplePvp.isAoe = true;
            if (attack.skill == 2121001 || attack.skill == 2221001 || attack.skill == 2321001 || attack.skill == 2121006) {
                MaplePvp.maxDis = 175;
                MaplePvp.maxHeight = 175;
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 170.0 + 180.0);
            }
            else {
                MaplePvp.pvpDamage = (int)Math.floor(Math.random() * 400.0 + 300.0);
            }
        }
    }
    
    private static void monsterBomb(final MapleCharacter player, final MapleCharacter attackedPlayers, final MapleMap map, final AttackInfo attack) {
        if (attackedPlayers.getLevel() > player.getLevel() + 25) {
            MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage * 1.35);
        }
        else if (attackedPlayers.getLevel() < player.getLevel() - 25) {
            MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage / 1.35);
        }
        else if (attackedPlayers.getLevel() > player.getLevel() + 100) {
            MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage * 1.5);
        }
        else if (attackedPlayers.getLevel() < player.getLevel() - 100) {
            MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage / 1.5);
        }
        final Integer mguard = attackedPlayers.getBuffedValue(MapleBuffStat.MAGIC_GUARD);
        final Integer mesoguard = attackedPlayers.getBuffedValue(MapleBuffStat.MESOGUARD);
        final int magicattack = (player.getDex() + player.getInt() + player.getLuk() + player.getStr()) / 300;
        MaplePvp.pvpDamage += magicattack;
        final int magicat = (player.getStat().getTotalMagic() + player.getStat().getTotalWatk()) / 100;
        MaplePvp.pvpDamage += magicat;
        if (MaplePvp.pvpDamage > 99999) {
            MaplePvp.pvpDamage = 99999;
        }
        if (mguard != null) {
            final int mploss = (int)((double)MaplePvp.pvpDamage / 0.5);
            MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage * 0.7);
            if (mploss > attackedPlayers.getStat().getMp()) {
                MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage / 0.7);
                attackedPlayers.cancelEffectFromBuffStat(MapleBuffStat.MAGIC_GUARD);
            }
            else {
                attackedPlayers.setMp(attackedPlayers.getStat().getMp() - mploss);
                attackedPlayers.updateSingleStat(MapleStat.MP, (int)attackedPlayers.getStat().getMp());
            }
        }
        else if (mesoguard != null) {
            final int mesoloss = (int)((double)MaplePvp.pvpDamage * 0.75);
            MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage * 0.75);
            if (mesoloss > attackedPlayers.getMeso()) {
                MaplePvp.pvpDamage = (int)((double)MaplePvp.pvpDamage / 0.75);
                attackedPlayers.cancelEffectFromBuffStat(MapleBuffStat.MESOGUARD);
            }
            else {
                attackedPlayers.gainMeso(-mesoloss, false);
            }
        }
        final MapleMonster pvpMob = MapleLifeFactory.getMonster(9400711);
        map.spawnMonsterOnGroundBelow(pvpMob, attackedPlayers.getPosition());
        for (int attacks = 0; attacks < attack.hits; ++attacks) {
            if (attack.skill == 0) {
                map.broadcastMessage(MaplePacketCreator.damagePlayer(1, pvpMob.getId(), attackedPlayers.getId(), MaplePvp.pvpDamage, 0, (byte)0, 0, false, pvpMob.getObjectId(), (int)pvpMob.getPosition().getX(), (int)pvpMob.getPosition().getY()));
                attackedPlayers.addHP(-MaplePvp.pvpDamage);
            }
            else {
                map.broadcastMessage(MaplePacketCreator.damagePlayer(1, pvpMob.getId(), attackedPlayers.getId(), MaplePvp.pvpDamage * attackedPlayers.getLevel(), 0, (byte)0, 0, false, pvpMob.getObjectId(), (int)pvpMob.getPosition().getX(), (int)pvpMob.getPosition().getY()));
                attackedPlayers.addHP(-MaplePvp.pvpDamage * attackedPlayers.getLevel());
            }
        }
        int attackedDamage = 0;
        if (attack.skill == 0) {
            attackedDamage = MaplePvp.pvpDamage * attack.hits * attackedPlayers.getLevel();
        }
        else {
            attackedDamage = MaplePvp.pvpDamage * attack.hits * attackedPlayers.getLevel();
        }
        attackedPlayers.getClient().sendPacket(MaplePacketCreator.getErrorNotice(player.getName() + " 打了 " + attackedDamage + " 點的伤害!"));
        map.killMonster(pvpMob, player, false, false, (byte)(-1));
        if (attackedPlayers.getStat().getHp() <= 0 && !attackedPlayers.isAlive()) {
            int expReward = attackedPlayers.getLevel() * 100;
            final int gpReward = (int)Math.floor(Math.random() * 150.0 + 50.0);
            if ((double)player.getLevel() * 0.25 >= (double)player.getLevel()) {
                expReward *= 20;
            }
            player.getClient().sendPacket(MaplePacketCreator.getErrorNotice("你殺了 " + attackedPlayers.getName() + "!! !"));
            attackedPlayers.getClient().sendPacket(MaplePacketCreator.getErrorNotice("無情的" + player.getName() + "殺了你"));
            final int random = (int)Math.floor(Math.random() * 3000.0);
            if (attackedPlayers.getMeso() >= random) {
                attackedPlayers.getMap().spawnMesoDrop(random, attackedPlayers.getPosition(), (MapleMapObject)attackedPlayers, attackedPlayers, false, (byte)0);
                attackedPlayers.gainMeso(-random, true);
                attackedPlayers.getClient().sendPacket(MaplePacketCreator.getErrorNotice("無情的" + player.getName() + "殺了你 你損失了" + random + "元!"));
            }
            else {
                attackedPlayers.dropMessage("[系統警告] 您的金币已經不足，請馬上離開。");
                player.dropMessage("[系統警告] 請不要再殘害他，對方金币已耗盡。");
            }
        }
    }
    
    public static void doPvP(final MapleCharacter player, final MapleMap map, final AttackInfo attack) {
        DamageBalancer(attack);
        getDirection(attack);
        for (final MapleCharacter attackedPlayers : player.getMap().getNearestPvpChar(player.getPosition(), (double)MaplePvp.maxDis, (double)MaplePvp.maxHeight, (Collection<MapleCharacter>)player.getMap().getCharacters())) {
            if (attackedPlayers.getLevel() >= 70 && attackedPlayers.isAlive() && (player.getParty() == null || player.getParty() != attackedPlayers.getParty())) {
                monsterBomb(player, attackedPlayers, map, attack);
            }
        }
    }
    
    static {
        MaplePvp.isAoe = false;
        MaplePvp.isLeft = false;
        MaplePvp.isRight = false;
    }
}
