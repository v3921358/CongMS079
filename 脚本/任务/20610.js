/*
 * Cygnus Skill -
 */

var status = -1;

function start(mode, type, selection) {
    status++;

    if (status == 0) {
	qm.askAcceptDecline("你有沒有熟練你的技能了呢？我相信你已經掌握了所有技能使用的方法，接下來我將再傳授一招#b最終技能#k給你。");
    } else if (status == 1) {
	if (mode == 0) {
	    qm.sendOk("好吧，你在做什麼，現在不會使你看起來像有人說的謙虛。你只要看看由自滿這樣做，這是從來沒有一個好東西。");
	} else {
	    qm.forceStartQuest();
	}
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}