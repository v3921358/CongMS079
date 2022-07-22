
/*
	Crysta; - Kamuma (Neo Tokyo Teleporter)
*/

function start() {
    switch (cm.getMapId()) {
        case 800040000:
            cm.warp(802000100, 0);
            cm.dispose();
            break;
        case 802000211:
            if (cm.getQuestStatus(4686) == 2) {
                //cm.gainItem(4032181, 100);
                cm.warp(802000212, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 100);
                cm.forceCompleteQuest(4686);
            }
            cm.dispose();
            break;
        case 802000313:
            if (cm.getQuestStatus(4689) == 2) {
                //cm.gainItem(4032181, 50);
                cm.warp(802000312, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 50);
                cm.forceCompleteQuest(4689);
            }
            cm.dispose();
            break;
        case 802000411:
            if (cm.getQuestStatus(4693) == 2) {
                //cm.gainItem(4032181, 100);
                cm.warp(802000412, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 100);
                cm.forceCompleteQuest(4693);
            }
            cm.dispose();
            break;
        case 802000611:
            if (cm.getQuestStatus(4696) == 2) {
                //cm.gainItem(4032181, 100);
                cm.warp(802000612, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 100);
                cm.forceCompleteQuest(4696);
            }
            cm.dispose();
            break;
        case 802000111:
            if (cm.getQuestStatus(4698) == 2) {
                //cm.gainItem(4032181, 100);
                cm.warp(802000112, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 100);
                cm.forceCompleteQuest(4698);
            }
            cm.dispose();
            break;
        case 802000711:
            if (cm.getQuestStatus(50003) == 2) {
                //cm.gainItem(4032181, 100);
                cm.warp(802000712, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 100);
                cm.forceCompleteQuest(50003);
            }
            cm.dispose();
            break;
        case 802000821:
            if (cm.getQuestStatus(50016) == 2) {
                //cm.gainItem(4032181, 100);
                cm.gainItem(4032361, 1);
                cm.warp(802000820, 0);
            } else {
                cm.showEffect(false, "quest/party/clear");
                cm.playSound(false, "Party1/Clear");
                //cm.gainItem(4032181, 100);

                cm.forceCompleteQuest(50016);
            }
            cm.dispose();
            break;
        default:
			var txt = "";
			txt += "嗨~ 我是水晶，需要我幫忙？";
			txt += " \r #b#L8#前往#m802000100##l"
			if (cm.getQuestStatus(4682) == 2){
				txt += " \r #b#L0#前往#m802000200##l";
			}
			if (cm.getQuestStatus(4687) == 2){
				txt += " \r #L1#前往#m802000300##l";
			}
			if (cm.getQuestStatus(4690) == 2){
				txt += " \r #L2#前往#m802000500##l";
			}
			if (cm.getQuestStatus(4694) == 2){
				txt += " \r #L3#前往#m802000600##l";
			}
			if (cm.getQuestStatus(50001) == 2){
				txt += " \r #L5#前往#m802000700##l";
			}
			if (cm.getQuestStatus(50002) == 2){
				txt += " \r #L9#前往#m802000801##l";
			}
			if (cm.getQuestStatus(50002) == 2){
				txt += " \r #L7#前往#m802000810##l";
			}
			txt += "\r #L6#讓我好好考慮一下。#l";
            cm.sendSimple(txt);
            break;
    }
}

function action(mode, type, selection) {
    if (selection != 6) {

        var questid = true,
            mapid = 0,
            portal = 0;

        switch (selection) {
            case 0:
                questid = cm.getQuestStatus(4682) == 2;
                mapid = 802000200;
                portal = 2;
                break;
            case 1:
                questid = cm.getQuestStatus(4687) == 2;
                mapid = 802000300;
                portal = 0;
                break;
            case 2:
                questid = cm.getQuestStatus(4690) == 2;
                mapid = 802000500;
                portal = 0;
                break;
            case 3:
                questid = cm.getQuestStatus(4694) == 2;
                mapid = 802000600;
                portal = 0;
                break;
            case 5:
                questid = cm.getQuestStatus(50001) == 2;
                mapid = 802000700;
                portal = 0;
                break;
            case 7:
                questid = cm.getQuestStatus(50002) == 2;
                mapid = 802000810;
                portal = 0;
                break;
			case 8:
                mapid = 802000100;
                portal = 0;
                break;
			case 9:
                questid = cm.getQuestStatus(50002) == 2;
                mapid = 802000801;
                portal = 0;
                break;
        }
        if (questid && mapid > 0) {
            cm.warp(mapid, portal);
        } else {
            cm.sendOk("我不認為您可以穿越過這個強大的力量。");
        }
    }

    cm.dispose();
}