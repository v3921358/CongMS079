/* ==================
 脚本类型:  玩具副本	    
 脚本作者： 颜林 
 联系方式： 1500663066
 =====================
 */
importPackage(Packages.client);
importPackage(Packages.client.inventory);
importPackage(Packages.server);
importPackage(Packages.tools);
var status;
var exp = 10000;

function start() {
    status = -1;
    playerStatus = cm.isLeader();
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        var eim = cm.getPlayer().getEventInstance();
        var stage7status = eim.getProperty("stage7status");
        if (stage7status == null) {
            if (playerStatus) {
                var map = eim.getMapInstance(cm.getPlayer().getMapId());
                var passes = cm.haveItem(4001022, 2);
                var stage7leader = eim.getProperty("stage7leader");
                if (stage7leader == "done") {
                    if (passes) {
                        party = eim.getPlayers();
                        map = cm.getMapId();
                        cm.removeAll(4001022);
						cm.gainItem(4001022,-2);
                        clear(7, eim, cm);
                        cm.givePartyExp(exp, party);
                        cm.sendOk("恭喜你们通过了第7阶段。现在通过传送门到达下一个阶段吧……");
                        cm.dispose();
                    } else {
                        cm.sendNext("你确定给我带来了2张#i4001022#？请检查一下自己的背包是否足够。");
                    }
                    cm.dispose();
                } else {
                    cm.sendOk("欢迎来到 玩具之城 - (#r组队任务#k)第#b7#k阶段\r\n\r\n到处走走看看。请你和你的队员一起带来#r2张通行证#k给我……");
                    eim.setProperty("stage7leader", "done");
                    cm.dispose();
                }
            } else {
                cm.sendNext("欢迎来到 玩具之城 - (#r组队任务#k)第#b7#k阶段\r\n\r\n到处走走看看。带来#r2张通行证#k给我,如果你成功拿到了通行证请交给你们的组长。然后再请他转交给我……");
                cm.dispose();
            }
        } else {
            cm.sendNext("恭喜你们通过了第7阶段。现在通过传送门到达下一个阶段吧……");
            cm.dispose();
        }
    }
}
function clear(stage, eim, cm) {
    eim.setProperty("stage" + stage.toString() + "status", "clear");
    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");
    var map = eim.getMapInstance(cm.getPlayer().getMapId());
    var mf = eim.getMapFactory();
    map = mf.getMap(922010700);
    var nextStage = eim.getMapInstance(922010900);
    var portal = nextStage.getPortal("next00");
    if (portal != null) {
        portal.setScriptName("lpq7");
    }
}