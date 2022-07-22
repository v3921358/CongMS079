﻿﻿/* 
 * Spiegelmann - Monster Carnival
 */

        var status = -1;
var rank = "C";
var exp = 0;

function start() {

    if (cm.getCarnivalParty() != null && cm.getCarnivalParty().getTotalCP() > 0) {
        status = 99;
    }
    action(1, 0, 0);

}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (mode == -1) {
        cm.dispose();
        return;
    }
    if (status == 0) {
        switch (cm.getMapId()) {
            case 980030010:
            case 980031300:
            case 980031400:
            case 980032300:
            case 980032400:
            case 980033300:
            case 980033400:
                cm.warp(980030000, 4);
                cm.dispose();
                break;
            default:
                cm.sendSimple("聽說修菲凱蔓喜歡吃鮑魚\r\n#b#L0#我要前往怪物擂台#l");
        }
    } else if (status == 1) {
        switch (selection) {
            case 0:
            {
                var level = cm.getPlayerStat("LVL");
                if (level < 50) {
                    cm.sendOk("50等以上才能玩怪物擂台賽喔");
                } else {
                    cm.warp(980030000, "st00");
                }
                cm.dispose();
            }
            default:
                {
                    cm.dispose();
                    break;
                }
                break;
        }
    } else if (status == 100) {
        var carnivalparty = cm.getCarnivalParty();
        if (carnivalparty.getTotalCP() >= 501) {
            rank = "A";
            exp = 89000;
        } else if (carnivalparty.getTotalCP() >= 251) {
            rank = "B";
            exp = 70000;
        } else if (carnivalparty.getTotalCP() >= 101) {
            rank = "C";
            exp = 50000;
        } else if (carnivalparty.getTotalCP() >= 0) {
            rank = "D";
            exp = 35000;
        }
        cm.getPlayer().endPartyQuest(1302);
        if (carnivalparty.isWinner()) {
            cm.sendOk("恭喜你贏了 太神啦\r\n#b怪物擂台賽排行 : " + rank);
        } else {
            cm.sendOk("雖然輸了也不要氣餒Q_Q\r\n#b怪物擂台賽排行 : " + rank);
        }
    } else if (status == 101) {
        var carnivalparty = cm.getCarnivalParty();
        var los = parseInt(cm.getPlayer().getOneInfo(1302, "lose"));
        var vic = parseInt(cm.getPlayer().getOneInfo(1302, "vic"));
        if (carnivalparty.isWinner()) {
            vic++;
            cm.getPlayer().updateOneInfo(1302, "vic", "" + vic);
            carnivalparty.removeMember(cm.getChar());
            cm.gainExpR(exp);
        } else {
            los++;
            cm.getPlayer().updateOneInfo(1302, "lose", "" + los);
            carnivalparty.removeMember(cm.getChar());
            cm.gainExpR(exp / 2);

        }
        cm.getPlayer().updateOneInfo(1302, "VR", "" + (java.lang.Math.ceil((vic * 100) / los)));
        cm.warp(980030000);
        cm.dispose();
    }

}