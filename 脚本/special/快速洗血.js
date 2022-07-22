function start() {
	status = -1;

	action(1, 0, 0);
}
function action(mode, type, selection) {
	if (mode == 1)
		status++;
	else {
		cm.dispose();
		return;
	}
	if (status == 0) {
		var tex2 = "";
		var text = "";
		for (i = 0; i < 10; i++) {
			text += "";
		}
		// 显示物品ID图片用的代码是 #v这里写入ID#
		text += "#k\t\t\t\t#b#v4030001#快捷洗血#v4030001##k\r\n"// 3
		text += "#L1##b1000点券兑换50HP#l#L2##b20000点券兑换1500HP\r\n#l"// 3
		text += "#L3##b1000点券兑换50MP#l#L4##b20000点券兑换1500MP\r\n#l"// 3
		//text += "#L3##b#v1112160##t1112160##l#L4##b#v1112272##t1112272#\r\n#l"// 3
		cm.sendSimple(text);
	} else if (status == 1) {
		hp=0;
		mp=0;
		if (selection == 1) {
            hp = 50;
            cost = 1000;
            cm.sendYesNo("您确定要使用"+cost+"点券购买 #b" + hp + "HP#k?" );
		} else if (selection == 2) {
			hp = 1500;
            cost = 20000;
            cm.sendYesNo("您确定要使用"+cost+"点券购买 #b" + hp + "HP#k?" );
		}else if (selection == 3) {
			mp = 50;
            cost = 1000;
            cm.sendYesNo("您确定要使用"+cost+"点券购买 #b" + mp + "MP#k?" );
		}else if (selection == 4) {
			mp = 1500;
            cost = 20000;
            cm.sendYesNo("您确定要使用"+cost+"点券购买 #b" + mp + "MP#k?" );
		}
		
	}else if (status == 2) {
		
		if(hp==50){
			if(cm.getPlayer().getJob()==0){
				cm.sendOk("新手职业不能使用该功能!");
				cm.dispose();
				return;
			}
			if(cm.getPlayer().getJob()==200||cm.getPlayer().getJob()==210||cm.getPlayer().getJob()==211||cm.getPlayer().getJob()==212||cm.getPlayer().getJob()==220||cm.getPlayer().getJob()==221||cm.getPlayer().getJob()==222||cm.getPlayer().getJob()==230||cm.getPlayer().getJob()==231||cm.getPlayer().getJob()==232){
				cm.sendOk("法师职业请快捷买蓝!");
				cm.dispose();
				return;
			}
			/*if (cm.getPlayer().getCSPoints(2) >= cost) {
				cm.getPlayer().getStat().setMaxHp(cm.getPlayer().getStat().getMaxHp() + hp);
				cm.getPlayer().modifyCSPoints(2, -cost, true);
				cm.dispose();
			} else*/
			if (cm.getPlayer().getCSPoints(1) >= cost) {
				cm.getPlayer().getStat().setMaxHp(cm.getPlayer().getStat().getMaxHp() + hp);
				cm.getPlayer().modifyCSPoints(1, -cost, true);
				cm.dispose();
			} else {
				cm.sendOk("你的点券不足!");
				cm.dispose();
			}
		}else if(hp==1500){
			if(cm.getPlayer().getJob()==0){
				cm.sendOk("新手职业不能使用该功能!");
				cm.dispose();
				return;
			}
			if(cm.getPlayer().getJob()==200||cm.getPlayer().getJob()==210||cm.getPlayer().getJob()==211||cm.getPlayer().getJob()==212||cm.getPlayer().getJob()==220||cm.getPlayer().getJob()==221||cm.getPlayer().getJob()==222||cm.getPlayer().getJob()==230||cm.getPlayer().getJob()==231||cm.getPlayer().getJob()==232){
				cm.sendOk("法师职业请快捷买蓝!");
				cm.dispose();
				return;
			}
			if (cm.getPlayer().getCSPoints(1) >= cost) {
				cm.getPlayer().getStat().setMaxHp(cm.getPlayer().getStat().getMaxHp() + hp);
				cm.getPlayer().modifyCSPoints(1, -cost, true);
				cm.dispose();
			} else {
				cm.sendOk("你的点券不足!");
				cm.dispose();
			}
		}else if(mp==50){
			
			/*if (cm.getPlayer().getCSPoints(2) >= cost) {
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+mp);
				cm.getPlayer().modifyCSPoints(2, -cost, true);
				cm.dispose();
			} else */
			if (cm.getPlayer().getCSPoints(1) >= cost) {
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+mp);
				cm.getPlayer().modifyCSPoints(1, -cost, true);
				cm.dispose();
			} else {
				cm.sendOk("你的点券不足!");
				cm.dispose();
			}
		}else if(mp==1500){
			if (cm.getPlayer().getCSPoints(1) >= cost) {
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+mp);
				cm.getPlayer().modifyCSPoints(1, -cost, true);
				cm.dispose();
			} else {
				cm.sendOk("你的点券不足!");
				cm.dispose();
			}
		}
	}
}
