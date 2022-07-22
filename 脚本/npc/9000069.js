load('nashorn:mozilla_compat.js');
importPackage(Packages.client.inventory);

var status = -1;
var 維修 = true;
var 大獎 = Array(3010047, 3010046, 3010002, 3010003, 3010092, 3010051, 3010052, 3010139);
var 黑之包 = Array(2100001,2100002,2100003,2100004,2100005,2100006,2100007);
var id = 0;
var get;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    var GM = cm.getPlayer().isGM();
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (維修 && !GM) {
        cm.sendNext("端午節活動尚未開始。\r\n#b活動開始時間2016/06/09~2016/06/13");
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendSimple("#r#e警告!此活動物品都均為永久\r\n請把各欄位都空四格以上以免發生意外\r\n#k#b#L1#查看裡面的大獎勵#l\r\n#L2#100個粽子可以抽獎一次。#l#k");
    } else if (status == 1) {
        if (selection == 1) {
            var s = "";
            for (var i = 0; i < 大獎.length; i++) {
                s += "#b#i" + 大獎 [i] + ":##t" + 大獎 [i] + "#\r\n";
            }
            cm.sendNext("#b請查看端午節大獎 :\r\n" + s);
            status = -1;
        } else if (selection == 2) {
            if (!cm.haveItem(2022034, 100)) {
                cm.sendNext("很抱歉由於您沒有#b#p2022034##t2022034##k。");
                cm.dispose();
            } else {
                cm.sendGetNumber("您願意捐#i2022034##t2020034#給屈原了嗎???", 100, 100, 100);
            }
        }
    } else if (status == 2) {
        if (!cm.haveItem(2022034, 100)) {
            cm.sendNext("很抱歉由於您沒有#b#p2022034##t2022034##k。");
            cm.dispose();
        } else {
            cm.gainItem(2022034, -100);
			id = Math.floor((Math.random() * 100) + 1);
			if (id <= 5) {
				get = 大獎[Math.floor(Math.random() * 大獎.length)];
				cm.worldMessage(6 ,"[端午節活動測試] 恭喜:" + cm.getChar().getName() + " 抽中了 "+MapleItemInformationProvider.getInstance().getName(get)+"!!");
			} else if (id <= 20) {
				get = 黑之包[Math.floor(Math.random() * 黑之包.length)];
			} else if (id <= 40) {
				get = 2022034;
			} else if (id <= 100) {
				get = 2210015;
			}
			cm.gainItem(get, 1);
            cm.sendNext("恭喜您抽到#v" + get + "##r#t" + get + "#!"+id);
			cm.dispose();
        }
    }
}