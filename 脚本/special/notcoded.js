/* global cm */

function start() {
	sj = Math.floor(Math.random()*7);
	if(sj == 0){
    var info = "你的长相很提神。\r\n";
	}else if(sj == 1){
	var info = "我觉得世界上就只有两种人能吸引人，一种是特漂亮的一种就是你这样的。\r\n";
	}else if(sj == 2){
	var info = "不是我不笑，一笑粉就掉!\r\n";
	}else if(sj == 3){
	var info = "以你的理解能力，我解释了你也不见得懂，所以，你继续渺茫吧。\r\n";
	}else if(sj == 4){
	var info = "你看，这么多人，这么大的世界，我遇到了你，你也遇到了我，多好。\r\n";
	}else if(sj == 5){
	var info = "用扯淡的态度，面对操蛋的人生。\r\n";
	}else if(sj == 6){
	var info = "你还真是，给你个棒槌，你还当针了!\r\n";
	}
    if (cm.getPlayerStat("GM") > 0) {
		info += " #bGM提示：仅GM可见,此NPC没有脚本#k\r\n ";
        info += "#bNPC的ID编号: #r" + cm.getNpc() + "#k ";
    }
    cm.sendOk(info);
    cm.dispose();
}