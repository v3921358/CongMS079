/*
	NPC Name: 		Cobra - Retired dragon trainer
	Map(s): 		Leafre : Cabin
*/
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status >= 0 && mode == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;

    if (status == 0) {
	cm.sendSimple("如果你有翅膀，我敢肯定，你可以去那裡。但是，這本身並沒有足夠的。如果你想要飛，雖然風這比刀片鋒利，你需要堅韌的尺度為好。我是唯一半身左邊那個知道回來的路上......如果你想去那裡，我可以改變你。不管你是什麼，這一刻，你會成為一個 #b龍#k...\r\n #L0##b我想變成一隻龍.#k#l");
    } else if (status == 1) {
	cm.useItem(2210016);
	cm.warp(200090500, 0);
	cm.dispose();
    }
}