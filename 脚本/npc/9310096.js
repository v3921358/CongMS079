/*
适配：悠悠冒险岛
作用：继承锻造
*/
//var 警报灯 = "#fUI/StatusBar/BtClaimrmal/0#";
var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
			//cm.sendOk("感谢使用~!");
            cm.dispose();
        }
        status--;
    }
	
    if (status == 0) {
		if (cm.getInventory(1).getItem(1) == null ) {
            cm.sendOk("请把装备放在第一格");
            cm.dispose();
            return;
        }		
		力量 = (cm.getInventory(1).getItem(1).getStr());
		敏捷 = (cm.getInventory(1).getItem(1).getDex());		
		智力 = (cm.getInventory(1).getItem(1).getInt());
		运气 = (cm.getInventory(1).getItem(1).getLuk());
		HP = (cm.getInventory(1).getItem(1).getHp());
		MP = (cm.getInventory(1).getItem(1).getMp());
		物攻 = (cm.getInventory(1).getItem(1).getWatk());
		魔攻 = (cm.getInventory(1).getItem(1).getMatk());
		物防 = (cm.getInventory(1).getItem(1).getWdef());
		魔防 = (cm.getInventory(1).getItem(1).getMdef());
        命中 = (cm.getInventory(1).getItem(1).getAcc());
 		回避 = (cm.getInventory(1).getItem(1).getAvoid());
		速度 = (cm.getInventory(1).getItem(1).getSpeed());
		跳跃 = (cm.getInventory(1).getItem(1).getJump());
		可升 = (cm.getInventory(1).getItem(1).getUpgradeSlots());
		等级 = (cm.getInventory(1).getItem(1).getLevel());
		制作人 = (cm.getInventory(1).getItem(1).getOwner());
		//潜能 = (cm.getInventory(1).getItem(1).getPotential());
		//定义
        需要装备 = 1072874  //升级必须要的
        合成装备 = 1072874   //合成后的装备
		//以下是所需材料
               材料1 = 4310051  
  		       材料2 = 4031213  
		       材料3 = 2614000  
		       材料4 = 2460005
		       材料5 = 4000463
		   材料1数量 = 20
		   材料2数量 = 1
		   材料3数量 = 10
		   材料4数量 = 10
		   材料5数量 = 88		      
		      
		      //金币图 = "#fItem/Special/0900.img/09000001/iconRaw/1#";
        当前装备 = cm.getInventory(1).getItem(1).getItemId();		
		/*if (cm.getBossLog("爆炸冒险岛") == 100) {
            cm.sendOk("嗯...一天只能升级一次哦~!");
            cm.dispose();
            return;
        }
		if (cm.getInventory(1).getItem(1).getLevel() < 124) {
            cm.sendOk("第一格的装备必须砸卷超过124次噢.已强化次数:#r"+cm.getInventory(1).getItem(1).getLevel()+"");
            cm.dispose();
            return;
        }*/
		if (当前装备 != 需要装备) {
            cm.sendOk("请把#v"+需要装备+"##z"+需要装备+"#放在第一栏位！否则无法合成");
            cm.dispose();
            return;
        }
		if (制作人 == "小小冒险岛") {
            cm.sendOk("无法重复升级！");
            cm.dispose();
            return;
        }
        var selStr = "";
			selStr +="#e#r#v"+需要装备+"##t"+需要装备+"# 升级为#v"+合成装备+"##t"+合成装备+"##k#n\r\n";
			selStr +="#d原有武器属性上叠加 四维属性+200 物攻魔攻+100  升级过程会产生奇异黑暗能量，导致属性会有1~5的微动\r\n\r\n";
			selStr +="#e#r升级所需的物质：#b当前#k/#r所需-----------------------\r\n\r\n#k#n";
			//selStr +="#v"+材料1+"##z"+材料1+"#  #e#b#c"+材料1+"##k/#r"+材料1数量+" #n#k\r\n";
			selStr +="#v"+材料2+"##z"+材料2+"#  #e#b#c"+材料2+"##k/#r"+材料2数量+" #n#k\r\n";
			//selStr +="#v"+材料3+"##z"+材料3+"#  #e#b#c"+材料3+"##k/#r"+材料3数量+" #n#k\r\n";
			//selStr +="#v"+材料4+"##z"+材料4+"#  #e#b#c"+材料4+"##k/#r"+材料4数量+" #n#k\r\n";
			//selStr +="#v"+材料5+"##z"+材料5+"#       #e#b#c"+材料5+"##k/#r"+材料5数量+" #n#k\r\n";
			selStr +="  冒险币  #e#b"+cm.getMeso()+"#k/#r"+ 1000000 +" #n#k\r\n";
			selStr +="#e#r-------------------------------------是否升级？";
        cm.sendYesNo(selStr);
    } else if (status == 1) {    
			//玩家 = cm.getPlayer().getName() == 'GM0'	//不需要材料的玩家
			if ( cm.getMeso () >= 1000000 ) {  
			
			cm.gainMeso(-1000000);
            cm.removeSlot(1, 1, 1);//删除装备栏的第一格的装备
			var equip = cm.getEquip(合成装备);
				equip.setStr(力量+100);//力量
                equip.setDex(敏捷+100);//敏捷
                equip.setInt(智力+100);//智力
                equip.setLuk(运气+100);//运气
				equip.setWatk(HP+100);//攻击
                equip.setMatk(MP+100);//魔力
                equip.setHp(物攻+100);//hp
                equip.setMp(魔攻+100);//mp
                equip.setWdef(物防+100);//物防
                equip.setMdef(魔防+100);//魔防
				equip.setAcc(  命中+100);              
                equip.setAvoid(回避+100);             
				equip.setSpeed(速度+100);             
				equip.setJump( 跳跃+100);              
				//equip.setOwner(潜能);
                cm.addbyItem(equip);
		cm.getInventory(1).getItem(1).setOwner("小小冒险岛");
		//cm.lockitem(1,1);  //锁定装备第一为格子 0为开 1为所		
		//cm.刷新状态();
        //cm.warpParty(960010102);
		cm.sendOk("恭喜你，成功升级!");
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"装备继承锻造" + " : " + cm.getPlayer().getName() +" 成功继承锻造出神秘鞋子",true));
		//Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(12, cm.getClient().getChannel(), "『系统公告』" + " : " + "[" + cm.getChar().getName() + "]成功继承锻造出神秘鞋子!"));
        cm.dispose();
		} else { 		
        cm.sendOk("#e#r物质不足，无法升级！#n#k\r\n\r\n");
        cm.dispose();
    }
    }
}
