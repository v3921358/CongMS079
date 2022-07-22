/*
	Robert Holly - Ludibrium: Ludibrium (220000000)
*/

var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.sendNext("是嗎……看來我的預感是錯的，你好像沒什麼朋友啊？哈哈哈~玩笑，玩笑~如果你改變了主意，可以再來找我。等朋友多一點的時候……呵呵……");
        cm.dispose();
        return;
    } else if (status >= 1 && mode == 0) {
        cm.sendNext("是嗎……看來我的預感是錯的，你好像沒什麼朋友啊？或者身上沒有25萬楓幣？如果你改變了主意，可以再來找我。等你有了錢的時候……呵呵……");
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        cm.sendYesNo("希望今天客人能多一點……啊！等一下！你想增加好友目錄嗎？我一看你，就覺得你有很多朋友。怎麼樣……只要花一點錢，我就可以為你增加好友目錄。但是不會應用於相同帳號的其他角色，所以一定要慎重。你想增加嗎？");
    } else if (status == 1) {
        cm.sendYesNo("好的！明智的決定。價格不貴。因為#r特殊折扣#k活動開始了，#b好友目錄添加5名一共是25萬楓幣#k。當然，絕不零售。只要購買一次，目錄就可以永久增加。對好友目錄不足的人來說，這個買賣應該不壞。怎麼樣？你願意支付25萬楓幣嗎？");
    } else if (status == 2) {
        var capacity = cm.getBuddyCapacity();
        if (capacity >= 50 || cm.getMeso() < 250000) {
            cm.sendNext("你……確定自己有#b25萬楓幣#k嗎？如果有的話，請你確認一下好友目錄是否已經增加到最大了。即使錢再多，好友目錄的人數也無法增加到#b50個以上#k。");
            cm.dispose();
        } else {
            var newcapacity = capacity + 5;
            cm.gainMeso( - 250000);
            cm.updateBuddyCapacity(newcapacity);
            cm.sendOk("好的！你的好友目錄已經增加了5個。你可以確認一下。如果好友目錄還是不夠的話，可以隨時來找我。我可以隨時幫你增加，不管多少次都行。當然不是免費的……那麼再見~");
            cm.dispose();
        }
    }
}