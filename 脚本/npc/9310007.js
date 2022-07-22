function start() {
	cm.sendYesNo("#你现在要出去吗?");
}

function action(mode, type, selection) {
    if (mode == 1) {
		if(cm.getMapId == 701010323){
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『蜈蚣副本』" + " : " + "[" + cm.getChar().getName() + "]成功挑战大王蜈蚣,获得赤珠！！")); 
			cm.warp(701010320, 0);
			cm.dispose();
		}else{
			cm.warp(701010320, 0);
			cm.dispose();
			
		}
	}
}