var status = -1;

function start() {
	if (cm.isQuestActive(21731) || cm.isQuestActive(20730))
		cm.sendGetText("聽見奇怪的聲音。若想進入，#e#b就要說出暗號#k#n");
	else
		cm.dispose();
}

function action(mode, type, selection) {
    if(mode != 1) {
        cm.dispose();
    } else {
        status++;
		if (status == 0) {
			if (cm.getText() == "浦藍西斯是天才傀儡師！") {
				cm.warp(910510001);
			} else {
				cm.sendNext("有奇怪的聲音嘲笑著\r\n\r\n#e#b是笨蛋嗎？這不是暗號嗎？從連寫法到感嘆號都一模一樣#k#n");
			}
			cm.dispose();
		}
    }
}
