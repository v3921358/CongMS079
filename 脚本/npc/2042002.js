/* 
 * Spiegelmann - Monster Carnival
 */

var status = -1;
var rank = "D";
var exp = 0;

function start() {
    if (cm.getCarnivalParty() != null) {
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
        cm.sendSimple("请来参加怪物擂台！\r\n#b#L0#我要前往怪物嘉年华#l");
    } else if (status == 1) {
        switch (selection) {
            case 0: {
                var level = cm.getPlayerStat("LVL");
				if (cm.getPlayer().getMapId() == 980000010) {
                cm.warp( 980000000, "st00" );
				cm.dispose();
                } else if ( level < 30) {
                    cm.sendOk("对不起，只有30级以上的用户可能会参加怪物狂欢节.");
					cm.dispose();
                } else {
					cm.saveLocation("MONSTER_CARNIVAL");
                    cm.warp( 980000000, "st00" );
					cm.dispose();
                }
                cm.dispose();
            }
            default: {
                cm.dispose();
                break;
            }
            break;
        }
    } else if (status == 100) {
        var carnivalparty = cm.getCarnivalParty();
        if (carnivalparty.getTotalCP() >= 501) {
            rank = "A";
            exp = 40000;
        } else if (carnivalparty.getTotalCP() >= 251) {
            rank = "B";
            exp = 40000;
        } else if (carnivalparty.getTotalCP() >= 101) {
            rank = "C";
            exp = 3000;
        } else if (carnivalparty.getTotalCP() >= 0) {
            rank = "D";
            exp = 1000;
        }
	cm.getPlayer().endPartyQuest(1301);
        if (carnivalparty.isWinner()) {
            cm.sendOk("你赢得了这场战斗，尽管你表现出色。 胜利是你的. \r\n#b怪物嘉年华排名 : " + rank);
        } else {
            cm.sendOk("不幸的是，尽管你表现出色，你也可能已经绑定或者失败了。 胜利应该是你的下一次. \r\n#b怪物嘉年华排名 : " + rank);
        }
    } else if (status == 101) {
        var carnivalparty = cm.getCarnivalParty();
	var los = parseInt(cm.getPlayer().getOneInfo(1301, "lose"));
	var vic = parseInt(cm.getPlayer().getOneInfo(1301, "vic"));
        if (carnivalparty.isWinner()) {
	    vic++;
	    cm.getPlayer().updateOneInfo(1301, "vic", "" + vic);
            carnivalparty.removeMember(cm.getChar());
            cm.gainExpR(exp);
        } else {
	    los++;
	    cm.getPlayer().updateOneInfo(1301, "lose", "" + los);
            carnivalparty.removeMember(cm.getChar());
            cm.gainExpR(exp / 2);

        }
	cm.getPlayer().updateOneInfo(1301, "VR", "" + (java.lang.Math.ceil((vic * 100) / los)));
            cm.warp(980000000);
            cm.dispose();
    }

}
