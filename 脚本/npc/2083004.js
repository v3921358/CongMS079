/* global cm */

function start() {
   // cm.sendSimple("\t\t您想要挑战暗黑龙王吗？想好了，请告诉我。\r\n\t\t每人每天可以进入5次哦~！#k\r\n#L0#\t\t#r挑战暗黑龙王(进阶)#k\r\n\r\n#L2#\t\t挑战暗黑龙王(普通)#k\r\n")
   cm.sendSimple("\t\t您想要挑战暗黑龙王吗？想好了，请告诉我。\r\n\t\t每人每天可以进入5次哦~！#k\r\n#L2#\t\t挑战暗黑龙王#k\r\n")
}
//#L2#挑战暗黑龙王(困难)\r\n

function action(mode, type, selection) {
    cm.dispose();

    switch (selection) {
        case 0:
            if (cm.getLevel() < 120) {
                cm.sendOk("挑战普通暗黑龙王的最低等级为120级，再去练练吧！");
            } if (cm.getParty() == null) {
            cm.sendYesNo("你并没有组队，请创建组建一个队伍在来吧。");
        } else if (!cm.isLeader()) { 
            cm.sendOk("请让你们的组队长和我对话。");
        }
			else if (cm.getBossLog('ptheilong') > 5) {
                cm.sendOk("抱歉你只能参加5次");
                cm.dispose();
            } 
            if (cm.getMap(240060000).getCharactersSize() != 0 || cm.getMap(240060100).getCharactersSize() != 0|| cm.getMap(240060200).getCharactersSize() != 0) {
                cm.sendOk("其它队伍，正在挑战中。");
                cm.safeDispose();
            }if (cm.getPlayer().getClient().getChannel() != 4) {
            cm.sendOk("混沌黑龙只有在频道 4 才可以挑战，频道1 2 3 5 远征黑龙王。");
            cm.dispose();
            return;
        }
			else {
                cm.setBossLog('ptheilong');
				cm.getMap(105100100).resetFully();
				cm.spawnMobOnMap(8810126,1,-131,160,105100100);//240060200
				
                cm.warp(105100100, 0);
		      
                cm.全服黄色喇叭("『挑战黑龙』：【" + cm.getChar().getName() + "】悍不畏死的去挑战黑龙王BOSS(进阶)去了");
                cm.dispose();
            }
            break;
        case 1:
            cm.sendOk("等你考虑好再来找我吧");
            cm.dispose();
        case 2:
		    cm.dispose();
            cm.openNpc(2083004,10); 
            break;
    }
}
