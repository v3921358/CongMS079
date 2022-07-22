var baseid = 541020610;
var dungeonid = 541020620;
var dungeons = 19;

function enter(pi) {
    if (pi.getMapId() == baseid) {
        if (pi.getParty() != null) {
            if (pi.isLeader()) {
                var party = pi.getPlayer().getParty().getMembers();
                var mapId = pi.getPlayer().getMapId();
                var next = true;
                var it = party.iterator();
                while (it.hasNext()) {
                    var cPlayer = it.next();
                    var ccPlayer = pi.getPlayer().getMap().getCharacterById(cPlayer.getId());
                    if (ccPlayer == null) {
                        next = false;
                        break;
                    }
                }
                for (var i = 0; i < dungeons; i++) {
                    if (!next) {
                        pi.playerMessage(5, "隊伍成員必須在相同地圖上。");
                        return;
                    }
                    if (pi.getPlayerCount(dungeonid + i) == 0) {
                        pi.warpParty(dungeonid + i);
                        return;
                    }
                }
            } else {
                pi.playerMessage(5, "你不是隊長。");
                return;
            }
        } else {
            for (var i = 0; i < dungeons; i++) {
                if (pi.getPlayerCount(dungeonid + i) == 0) {
                    pi.warp(dungeonid + i);
                    return;
                }
            }
        }
        pi.playerMessage(5, "目前所有地下城都在使用，請稍後在嘗試。");
    } else {
        pi.playPortalSE();
        pi.warp(baseid, "MD00");
    }
}