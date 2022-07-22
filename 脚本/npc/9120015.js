/*
	Konpei - Showa Town(801000000)
*/

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
    } else {
	if (mode == 1)
	    status++;
	if (status == 0) {
		 var cy ="有什么我可以为你服务的吗？？\r\n";
		 if (cm.getPlayer().getMapId()==801000000) {
		 cy +=   "#L0##b告诉我藏身之处的一些消息。#l\r\n";
		 cy +=   "#L1#带我去的藏身之处。#l\r\n";
		 cy +=   "#L2#没有。#l#k";
		 }
		 if (cm.getPlayer().getMapId()==801040005) {
		 cy +=   "#L3##b挑战男老板BOSS#l\r\n";
		 
		 }
		 if (cm.getPlayer().getMapId()==801040006) {
		 cy +=   "#L4##b召唤男老板BOSS#l\r\n";
		 
		 }
	    cm.sendSimple (cy);
	} else if (status == 1) {
	    if (selection == 0) {
		cm.sendNext("我懒得说明。");
		cm.dispose();
	    } if (selection == 1) {
		cm.sendNext("准备走了！");
	    } if (selection == 2) {
		cm.sendOk("我是一个忙碌的人！离我远一点！");
		cm.dispose();
	    }  if (selection == 3) {
		cm.dispose();
		cm.openNpc(9120015,"男老板BOSS");
		
	    }  if (selection == 4) {
		cm.dispose();
		cm.openNpc(9120015,"男老板BOSS召唤");
		
	    } 
	} else if (status == 2) {
	    cm.warp(801040000, 0);
	    cm.dispose();
	}
    }
}