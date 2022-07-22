function start() {
	if (cm.haveMonster(5090001)) {
		cm.getMap().killMonster(5090001);
		cm.mapMessage("看起來你非常的用心來關看你的成績呢！");
	} else {
		cm.sendNext("你也會來關心你的成績啊？");
	}
	cm.dispose();
}