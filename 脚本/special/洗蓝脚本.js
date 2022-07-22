/*
 *兑换
 */
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var status = 0; 
var cost = 30000;

function start() { 
    status = -1; 
    action(1, 0, 0); 
} 

function action(mode, type, selection) { 
    if (mode == -1) { 
        cm.dispose(); 
    } else if (mode == 0) { 
        cm.dispose(); 
    } else { 
        if (mode == 1) 
            status++; 
        else 
            status--; 
        if (status == 0) { 
	    abb = 1;
	    cm.sendGetText("您好,欢迎使用金币增加MP.每"+cost+"金币增加1点最大MP值。\r\n目前拥有"+金币图标+"：#r"+cm.getMeso()+" #k\r\n#r注：请输入想增加的#b蓝魔值#r..最大MP不能超过3万,否则出错该不负责");
        } else if (status == 1) { 
	if(cm.getText() < 1){
	    cm.playerMessage(1,"单次输入的数字不能小于1。且不能大于1万。");
	    cm.dispose();
	} else if(cm.getText() > 10000){
	    cm.playerMessage(1,"单次输入的数字不能小于1。且不能大于1万。");
	    cm.dispose();
	} else {
	    cm.sendYesNo("您好,欢迎使用金币增加蓝量.\r\n增加#r" + cm.getText() + "#k蓝量将会使用掉您#r" + cm.getText() * cost + "#k金币\r\n请确认后使用。"); 
	    } 
        } else if (status == 2) { 
		var getmaxmp = cm.getPlayer().getStat().getMaxMp();
	if (cm.getMeso() >= cm.getText()*cost) { 
		   cm.gainMeso(-cm.getText() * cost);
		   cm.getPlayer().getStat().setMaxMp(getmaxmp+cm.getText() * 1);
		   //Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『洗血中心』" + " : " + "恭喜玩家[" + cm.getChar().getName() + "] 用"+ cm.getText() * cost +" 金币增加了 "+ cm.getText() +" HP上限")); 
           
           cm.sendOk("成功增加了"+cm.getText()+"您增加的MP.换线或小退一下即可看到。");
           cm.dispose();
        } else {
           cm.sendOk("您没有足够的金币,请获取后使用.");
           cm.dispose();
	 }
      } 
   }
}   