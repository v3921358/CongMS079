/* 
 * NPC :      Mihai
 * Map :      Timu's Forest
 */

function start() {
    cm.sendNext("呵呵...難道我剛剛做的被發現了嗎？！");
}

function action(mode, type, selection) {
    if (mode == 1) {
	cm.removeNpc(cm.getMapId(), cm.getNpc());
	cm.spawnMonster(9001010,1); // Transforming
    }
    cm.dispose();
}