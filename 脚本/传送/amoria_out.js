
function enter(pi) {
    var returnMap = pi.getSavedLocation("AMORIA");
    pi.clearSavedLocation("AMORIA");
    if (returnMap < 0) {
        returnMap = 102000000;
    }
    var target = pi.getMap(returnMap);
    var portal = target.getPortal(0);
    if (portal == null) {
        portal = target.getPortal(0);
    }
    if (pi.getMapId() != target) {
        pi.playPortalSE();
        pi.getPlayer().changeMap(target, portal);
    }
}