

var status = -1;
var sel;
var mod;
var moda;
function start() {

    var sg = cm.getPlayer().getStLog() < 1 ? "你还沒有拜师。" : cm.getCharacterNameById(cm.getPlayer().getStLogid(cm.getPlayer().getId())) != null ? "你的师傅是：" + cm.getCharacterNameById(cm.getPlayer().getStLogid(cm.getPlayer().getId())) : "你的师傅已经刪除角色。";
    var st = cm.getPlayer().getStChrNameLog(cm.getPlayer().getId()) != "" ? "你的徒弟有：" + cm.getPlayer().getStChrNameLog(cm.getPlayer().getId()) : "你没有徒弟。";
    cm.sendSimple("我是师徒系统服务员，有什么可以为你服务？\r\n\r\n" + "#b"+sg + "\r\n\r\n" + st + "#k\r\n\r\n#b#L0#我要拜师#l#k#k\r\n\r\n#b#L1#师傅领取奖励#l#k#k");
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
            if (cm.getPlayer().getLevel() < 10) {
                cm.sendOk("你的等级不足10级，无法拜师。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getLevel() >= 70) {
                cm.sendOk("你的等级超过70级，无法拜师。");
                cm.dispose();
                return;
            }

            if (cm.getPlayer().getStLog() >= 1) {
                cm.sendOk("你已经有师傅无法重复拜师");
                cm.dispose();
                return;
            }
            cm.sendGetText("请输入要拜师的角色名字。");
            moda = 100;
        } else if (sel == 1) {
            cm.sendSimple("我是师徒系统服务员，一共可以领取10次奖励，你可以领取奖励如下\r\n\r\n" +
                    "第一次 800点券\r\n\r\n" +
                    "第二次 #i4001126#30个\r\n\r\n" +
                    "第三次 #i4001126#10个\r\n\r\n" +
                    "第四次 #i4001126#2个\r\n\r\n" +
                    "第五次 #i4001126#5个\r\n\r\n" +
                    "第六次 3000点券\r\n\r\n" +
                    "第七次 #i4001126#5个\r\n\r\n" +
                    "第八次 #i4001126#30个\r\n\r\n" +
                    "第九次 #i4001126#2个#i4001126#1个#i4001126#1个\r\n\r\n" +
                    "第十次 #i4001126#永久\r\n\r\n" +
                    "\r\n\r\n" +
                    "#b#L200#师傅领取奖励#l#k#k");
        }
    } else if (status == 1) {
        if (moda == 100) {
            var text = cm.getText();
            if (text === null || text === "") {
                cm.sendOk("并未输入任何內容.");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getLevel() < 10) {
                cm.sendOk("你的等级不足10级，无法拜师。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getLevel() >= 70) {
                cm.sendOk("你的等级超过70级，无法拜师。");
                cm.dispose();
                return;
            }

            if (cm.getPlayer().getStLog() >= 1) {
                cm.sendOk("你已经有师傅无法重复拜师");
                cm.dispose();
                return;
            }
            var id = cm.getCharacterIdByName(text);
            if (id == -1) {
                cm.sendOk("你输入的名字不存在。");
                cm.dispose();
                return;
            }
            if (cm.getCharacterByNameLevel(text) < 120) {
                cm.sendOk("对方等级不足120级，无法拜师。");
                cm.dispose();
                return;
            }

            cm.getPlayer().setStLog(id);
            cm.sendOk("拜师成功。");
            cm.dispose();
            return;
        } else if (selection == 200) {
            if (!cm.canHoldByType(1, 1)) {
                cm.sendOk("请确认背包是否已经满了。");
                cm.dispose();
                return;
            }
            if (!cm.canHoldByType(2, 1)) {
                cm.sendOk("请确认背包是否已经满了。");
                cm.dispose();
                return;
            }
            if (!cm.canHoldByType(3, 1)) {
                cm.sendOk("请确认背包是否已经满了。");
                cm.dispose();
                return;
            }
            if (!cm.canHoldByType(4, 1)) {
                cm.sendOk("请确认背包是否已经满了。");
                cm.dispose();
                return;
            }
            if (!cm.canHoldByType(5, 1)) {
                cm.sendOk("请确认背包是否已经满了。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getStjf(cm.getPlayer().getId()) < 1) {
                cm.sendOk("目前你还沒有徒弟出师。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") >= 10) {
                cm.sendOk("你已經領取過10次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 0) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.getPlayer().modifyCSPoints(2, 800, true);
                cm.sendOk("成功领取第1次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 1) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(5220000, 30);
                cm.sendOk("成功领取第2次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 2) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(4031408, 10);
                cm.sendOk("成功领取第3次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 3) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(2450000, 2);
                cm.sendOk("成功领取第4次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 4) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(2049100, 5);
                cm.sendOk("成功领取第5次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 5) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.getPlayer().modifyCSPoints(2, 3000, true);
                cm.sendOk("成功领取第6次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 6) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(5062001, 30);
                cm.sendOk("成功领取第7次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 7) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(2340000, 5);
                cm.sendOk("成功领取第8次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 8) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(2049302, 2);
		cm.gainItem(2049412, 1);
                cm.gainItem(5064000, 1);
                cm.sendOk("成功领取第9次师傅奖励。");
                cm.dispose();
                return;
            }
            if (cm.getPlayer().getBossLogS("师傅奖励") == 9) {
                cm.getPlayer().setBossLog("师傅奖励");
                cm.getPlayer().updateStjfLog(cm.getPlayer().getId(), cm.getPlayer().getStjf(cm.getPlayer().getId()) - 1);
                cm.gainItem(1143033, 1);
                cm.sendOk("成功领取第10次师傅奖励。");
                cm.dispose();
                return;
            }

        }
    }
}
