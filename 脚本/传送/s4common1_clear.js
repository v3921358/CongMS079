// EL Nath PQ

function enter(pi) {
    if (pi.haveMonster(9300093)) { // Tylus
        var pt = pi.getEventManager("ProtectTylus");
        if (pt == null) {
            pi.warp(211000001, 0);
        } else {
            if (pt.getInstance("ProtectTylus").getTimeLeft() < 180000) { // 3 minutes left
                pi.warp(921100301, 0);
            } else {
                pi.playerMessage("請保護泰勒斯。");
                return false;
            }
        }
    } else {
        pi.warp(211000001, 0);
        pi.playerMessage("喔不！泰勒斯被綁架了！");
    }
    return true;
}