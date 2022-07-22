var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status <= 1) {
	    cm.sendNext("需要去再來找我吧!");
	    cm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
    cm.sendSimple("你有沒有聽過海灘與所謂的 #b黃金海灘#k, 這個地方在維多利亞呢?? 我可以現在幫助你到那個地方只需要 #b1500 楓幣#k, 或者如果你有一張 #bVIP 到黃金海灘的票#k 那麼就可以免費去..\r\n\r\n#L0##b 我願意付 1500 楓幣.#l\r\n#L1# 我有一張 VIP 到黃金海灘的票.#l\r\n#L2# 什麼是VIP到黃金海灘的票#k?#l");
    } else if (status == 1) {
	if (selection == 0) {
	    cm.sendYesNo("所以你想付 #b1500 楓幣#k 然後去黃金海灘??");
	} else if (selection == 1) {
	    status = 2;
	    cm.sendYesNo("所以你有一張 #bVIP 到黃金海灘的票#k?");
	} else if (selection == 2) {
	    status = 4;
	    cm.sendNext("你一定好奇什麼是 #bVIP 到黃金海灘的票#k. 哈哈，這是非常可以理解的。貴賓門票到弗洛里納海灘是一個項目在哪裡，只要你在身上，你可以用自己的方式來弗洛里納海灘免費。那就是，即使我們不得不買這些，但不幸的是我在我的寶貴的暑假失去了一個雷幾週前這樣一個難得的道具.");
	}
    } else if (status == 2) {
	if (cm.getMeso() < 1500) {
	    cm.sendNext("你沒有足夠的楓幣也沒有VIP票 滾吧!");
	    cm.dispose();
	} else {
	    cm.gainMeso(-1500);
	    cm.saveLocation("FLORINA");
	    cm.warp(110000000, 0);
	    cm.dispose();
	}
    } else if (status == 3) {
	if (cm.haveItem(4031134)) {
	    cm.saveLocation("FLORINA");
	    cm.warp(110000000, 0);
	    cm.dispose();
	} else {
	    cm.sendNext("你確定你有#bVIP 到黃金海灘的票#k. 嗎? 確認一下好嗎....");
	    cm.dispose();
	}
    } else if (status == 4) {
	cm.sendNext("你一定好奇神麼是 #bVIP 到黃金海灘的票#k. 哈哈，這是非常可以理解的。貴賓門票到弗洛里納海灘是一個項目在哪裡，只要你在身上，你可以用自己的方式來弗洛里納海灘免費。那就是，即使我們不得不買這些，但不幸的是我在我的寶貴的暑假失去了一個雷幾週前這樣一個難得的項目.");
    } else if (status == 5) {
	cm.sendNextPrev("我回來沒有它，我就覺得可怕沒有它。希望有人把它撿起來，並把它安全的地方。無論如何，這是我的故事，誰知道，你可以把它撿起來，並把它用好。如果您有任何問題，請隨時問。");
    } else if (status == 6) {
	cm.dispose();
    }
}