function enter(pi) {
    if (pi.haveItem(4031346)) {
        if (pi.getMapId() == 240010100) {
            pi.playPortalSE();
            pi.warp(101010000, "minar00");
        } else {
            pi.playPortalSE();
            pi.warp(240010100, "elli00");
        }
        pi.gainItem(4031346, -1);
        return true;
    } else {
        pi.playerMessage("為了要傳送到神祕的地方需要魔法種子。");
        return false;
    }
}