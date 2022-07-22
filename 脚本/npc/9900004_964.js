/*
 
 脚本内容：降级系统
 制作人：.
 制作时间：2020-10-18 13:42:03
 
 */
var dj = 0 

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		var selStr = "你当前已经修仙#r"+ cm.getOneTimeLog("修仙次数") +"#k次。\r\n\r\n";
	    selStr += "这里可以帮助满级玩家进行降级.\r\n\r\n#l";
		selStr += "降级对象:#r等级达到250级的玩家.限制降级20次.\r\n#l";
		selStr += "#k降级之后:#r玩家等级降低至200级(只降低等级,其余不变.),每次降级奖励50个混沌币.\r\n#l";
		selStr += "#k降级好处:#r保留属性,玩家再次升级可以继续获得能力点.\r\n#l";
		selStr += "#k降级奖励:#r每降级5次,即可领取一次大奖!\r\n#l";
		selStr += "#k每次降级:#r换线生效!\r\n#l";
		selStr += "#L1##r我要降级至200级.#l\t\t#L2##d领取降级奖励.#l\r\n";
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
		    
		case 1:
			dj = cm.getChar().getLevel();
			if(cm.getOneTimeLog("修仙次数") >= 20){
			cm.sendOk("#r你已经累计降级20次,无法继续使用此功能了!");
			}else if( dj <= 249 ){
			cm.sendOk("#b你的等级不足250级.努力升级吧!加油!!!");
			} else {
			cm.getChar().setLevel(200);
			cm.getChar().setExp(0);
			cm.setOneTimeLog("修仙次数");
			cm.gainItem(4001126,1);//此处我用枫叶代替,请自行更换为混沌币代码.
			cm.sendOk("#r降级成功!\r\n\r\n降级奖励: #v4001126##z4001126# x 1#k");//此处也需要更换为混沌币代码.			
			}
			cm.dispose();
			break;
		case 2:
			cm.dispose();
			cm.openNpc(9000008,1);
			break;
					
			
        }
    }
}