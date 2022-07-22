

var status = -1;
var sel;
var mod;
function start() {
    //cm.sendOk("暫時沒有補償。");
    //cm.dispose();
    //return;
    cm.sendSimple("我是轉生管理員 \r\n\r\n" +
            "需要轉生道具：\r\n" +
            "#i4001126#x1\r\n" +
            "需要25萬楓幣\r\n" +
            "#b#L0#我要終極轉生#l#k\r\n\r\n" +
            "");
}

function action(mode, type, selection) {
    if (mode == 0) {
        cm.dispose();
        return;
    } else {
        status++;
    }
    if (status == 0) {
        sel = selection;
        if (sel == 0) {
            if (cm.getPlayer().getLevel() < 200) {
                cm.sendOk("您的等级不足200级。");
                cm.dispose();
                return;
            }
            if (!cm.haveItem(4001126)) {
                cm.sendOk("所需道具不足。");
                cm.dispose();
                return;
            }


            if (cm.getMeso() < 250000) {
                cm.sendOk("所需冒险币不足。");
                cm.dispose();
                return;
            }
            if (!cm.canHoldByType(2, 1)) {
                cm.sendOk("请确认背包是否已经满了。");
                cm.dispose();
                return;
            }

            cm.gainItem(4001126, -1);//减需要道具数量

            cm.gainMeso(-250000);//需要冒险币数量

            cm.changeJob(0);
            cm.StatsZs();
            cm.getPlayer().setExp(0);

            cm.sendOk("你已成功转生。");
            cm.dispose();
            return;
        }
        cm.dispose();
    }
}
