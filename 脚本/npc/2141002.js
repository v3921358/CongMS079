/*
 NPC Name: 		The Forgotten Temple Manager
 Map(s): 		Deep in the Shrine - Twilight of the gods
 Description: 		Pink Bean
 */

function start() {
    cm.sendYesNo("你现在想出去吗？");
}

function action(mode, type, selection) {
    if (mode == 1) {
        var em = cm.getEventManager("PinkBeanBattle");
        if (em != null) {
            var eim = em.getInstance("PinkBeanBattle");
            if (eim != null) {
                var propsa = eim.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                if ((propsa != null) && propsa.equals("1")) {
                  //  eim.setProperty("isSquadPlayerID_" + cm.getPlayer().getId(), "0");
                }
            }
        }
        cm.warp(270040000, 0);
    }
    cm.dispose();
}