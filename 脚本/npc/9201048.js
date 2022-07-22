var status = -1;
var nextt = true;
var check = [670010200, 670010300, 670010400, 670010500, 670010600, 670010700, 670010800];
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }
    cm.removeAll(4031595);
    cm.removeAll(4031594);
    cm.removeAll(4031597);
    if (status == 0) {
        cm.sendSimple("#b#L0#離開這裡 (沒有退費)#l\r\n#L1#我的隊伍已經準備好，我們要挑戰了！#l#k");
    } else if (status == 1) {
        if (selection == 0) {
            cm.warp(670010000, 0);
        } else {
            if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
                cm.sendOk("必須要有隊長在這裡。");
            } else {
                for (var i = 0; i < check.length; i++) {
                    if (cm.getPlayer().getClient().getChannelServer().getMapFactory().getMap(check[i]).playerCount() > 0) {
                        nextt = false;
                    }
                }
                if (!nextt) {
                    cm.sendNext("目前地圖有人");
                    cm.dispose();
                    return;
                }
                var party = cm.getPlayer().getParty().getMembers();
                var mapId = cm.getPlayer().getMapId();
                var next = true;
                var size = 0;
                var it = party.iterator();
                while (it.hasNext()) {
                    var cPlayer = it.next();
                    var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                    if (ccPlayer == null || ccPlayer.getLevel() < 40) {
                        next = false;
                        break;
                    }
                    size += (ccPlayer.isGM() ? 2 : 1);
                }
                if (next && size >= 2) {
                    var em = cm.getEventManager("Amoria");
                    if (em == null) {
                        cm.sendOk("請稍後再嘗試。");
                    } else {
                        em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
                    }
                } else {
                    cm.sendOk("隊伍成員必須有6個以上都在這裡。");
                }
            }
        }
        cm.dispose();
    }
}