
var status;

function start() {
    status = -1;
    action(1,0,0);
}

function action(mode, type, selection){
    if (mode == 1)
        status++;
    else {
        cm.dispose();
        return;
    }
    var mapId = cm.getPlayer().getMapId();
    if (mapId == 103000890) {
        if (status == 0) {
            cm.warp(103000000);//任务集合中心
            cm.removeAll(4001007);
            cm.removeAll(4001008);
            cm.dispose();
        }
    } else {
        if (status == 0) {
            var outText = "现在离开的话任务就会重新来过,你确定要这么做吗?";
            if (mapId == 103000805) {
                outText = "现在离开的话任务就会重新来过,你确定要这么做吗?";
            }
            cm.sendYesNo(outText);
        } else if (mode == 1) {
            var eim = cm.getPlayer().getEventInstance(); 
            if (eim == null)
                cm.warp(103000890, "st00");
            else if (cm.isLeader()) {
                cm.getEventManager("KerningPQ").setProperty("KPQOpen" , "true");
                eim.disbandParty();
            }
            else
                eim.leftParty(cm.getPlayer());
            cm.dispose();
        }
    }
}