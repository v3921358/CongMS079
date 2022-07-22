/**
-- Odin JavaScript --------------------------------------------------------------------------------
	Wiz the Librarian - Helios Tower <Library>(222020000)
-- By ---------------------------------------------------------------------------------------------
	Information
-- Version Info -----------------------------------------------------------------------------------
	1.0 - First Version by Information
---------------------------------------------------------------------------------------------------
**/

var status = 0;
var questid = new Array(3615,3616,3617,3618,3630,3633,3639);
var questitem = new Array(4031235,4031236,4031237,4031238,4031270,4031280,4031298);
var i;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
	var counter = 0;
	var books = "";

	for (var i = 0; i < questid.length; i++) {
	    if (cm.getQuestStatus(questid[i]) == 2) {
		counter++;
		books += "\r\n#v"+questitem[i]+"# #b#t"+questitem[i]+"##k";
	    }
	}
	if(counter == 0) {
	    cm.sendOk("#b#h ##k 還沒有還一本故事書.");
	    cm.safeDispose();
	} else {
	    cm.sendNext("我看看 #b#h ##k 總共還了 of #b"+counter+"#k 書 下列是還得書本列表:"+books);
	}
    } else if (status == 1) {
	cm.sendNextPrev("該書庫沉澱下來，現在歸功於你 #b#h ##k 鉅大的幫助，如果故事書被混合再一次被混和起來的話，我會希望你在幫忙一次。");
    } else if (status == 2) {
	cm.dispose();
    }
}