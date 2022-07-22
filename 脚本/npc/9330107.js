/* 	
 * 	不夜城 收集香爐活動-龍山寺師父
 */

var status = -1;
var time = 1;
var mode = false;

function action(mode, type, selection) {
    if(!mode) {
		cm.sendNext("新年活動NPC維修中");
		cm.dispose();
		return;
	}
	if(cm.getClient().getChannel() != 1) {
		cm.sendNext("新年活動只能再頻道一唷");
		cm.dispose();
		return;
	}
	if (mode == 1) {
        status++;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendNext("您好！我是#p9330107#，目前我們正在舉辦新年活動，如果您施捨給我香爐祭拜神明，誠意夠的話神明將會給各位一個大大的驚喜來當作回報的。");
    } else if (status == 1) {
        cm.sendSimple("每當人們施捨越多，會有越大的期望。。。 \n\r #b#L0#我要施捨香爐#l#k \n\r #b#L1#觀察看目前香爐的收集量#l#k \n\r #b#L2#這活動有什麼意義呢？#l#k \n\r #b#L3#我要領取打活動用的武器#l#k");
    } else if (status == 2) {
        if (selection == 1) {
            cm.sendNext("香爐目前的進度 \n\r #B" + cm.getKegs() + "# \n\r 如果您施捨給我香爐祭拜神明，誠意夠的話神明將會給各位一個大大的驚喜來當作回報的...");
            cm.safeDispose();
		} else if (selection == 2) {
			cm.sendNext("這活動很好玩的，新年嘛就是要開開心心的阿~\r\n您說是吧？？");
			cm.safeDispose();
		} else if (selection == 3) {
			if (cm.getBossLog('time') < 1) {
				cm.sendNext("那就給您吧~");
				cm.gainItemPeriod(1472081,1,1);
				cm.gainItem(2070020,1500);
				cm.setBossLog("time");
				cm.safeDispose();
			} else {
				cm.sendNext("小兄弟，別這麼貪心嘛~一天只能領一次的啊！");
				cm.safeDispose();
			}
        } else if (selection == 0) {
            cm.sendGetNumber("小兄弟您已經帶上香爐啦？那麼您要給我幾個 #b#t4000516##k呢？？ \n\r", 0, 0, 5000);
        }
    } else if (status == 3) {
        var num = selection;
        if (!cm.haveItem(4000516) || num == 0) {
            cm.sendOk("嗚嗚小兄弟您居然欺騙我。。。\r\n請不要欺騙我。。。");
        } else if (cm.haveItem(4000516, num)) {
            cm.gainItem(4000516, -num);
            cm.giveKegs(num);
            cm.sendOk("我不會忘記您的大恩大德的！！！");
        }
        cm.safeDispose();
    }
}