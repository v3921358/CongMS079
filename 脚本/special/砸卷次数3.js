var status = -1;
var gailv = 50;//输入百分之几不要输入百分号只要数字在里面就行了!!
var xx;
importPackage(java.util);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
			cm.sendOk("感谢使用~!");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("装备栏第一格必须有物品才行哦~!");
            cm.dispose();
            return;
        }
		
		
				
		
		//if (cm.getInventory(1).getItem(1).getLevel() >= 5) {
         //   cm.sendOk("强化的装备等级不能大于#r120#k级哦~!\r\n当前#v"+Id+"##t"+Id+"# #b 已强化次数:#r"+cm.getInventory(1).getItem(1).getLevel()+"");
         //   cm.dispose();
        //    return;
        //}
		
		/*if (cm.getInventory(1).getItem(1).getUpgradeSlots() >= 5) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("可砸卷次数不能超过#r 5 #k次哦~!\r\n当前#v"+Id+"##t"+Id+"# #b 可砸卷次数为:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"");
            cm.dispose();
            return;
        }*/
		
		
		
		/*if ((cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel()) >= 12) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("可升级次数不能超过#r 12 #k次哦~!\r\n当前#v"+Id+"##t"+Id+"# #b 可强化次数为:#r"+(cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel())+"");

            cm.dispose();
            return;
        }*/
		
		
		
		if (!cm.haveItem(2049401,10) && cm.getMeso() < 10000000) {
            cm.sendOk("每次升级需要消耗#b10#k个#i2049401:#和1000万金币");
            cm.dispose();
            return;
        }
		var Id = cm.getInventory(1).getItem(1).getItemId();
        var selStr = "您好欢迎来到#r强化中心#k (成功概率为50%)\r\n\r\n";
            selStr += "您要升级的道具是 #v"+Id+"##t"+Id+"# \r\n#b目前可砸卷总次数为:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"#b\r\n每次升级增加#r1#b次可砸卷次数,消耗#b10#k个#i2049401:#和1000万金币.\r\n\r\n#r升级失败概率==50%，你确定要升级吗？\r\n";//当前已强化次数:#r"+cm.getInventory(1).getItem(1).getLevel()+"
        cm.sendYesNo(selStr);
    } else if (status == 1) {
		var xx = cm.getInventory(1).getItem(1).getOwner();
		var statup = new java.util.ArrayList();
        var itemId1 = cm.getInventory(1).getItem(1).getItemId();
        var item = cm.getInventory(1).getItem(1).copy();
        var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
		//if (Math.floor(item.getItemId()/10000) >= 121 && Math.floor(item.getItemId()/10000) <= 171) {
            
        
		if (cm.getBossLog("强化次数6") >= 5){
				cm.sendOk("你今天已经成功强化过5次了,请明天在来吧!");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1) == null)  {
				cm.sendOk("请把要强化的装备放在第一格才能进行.");
				cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能进行强化.");
				cm.dispose();
			} else if (xx != "高等觉醒+2"){
		    	cm.sendOk("这个装备不是高等觉醒+2，请先强化到+2再来找我哟！");
                cm.dispose();
                return;
            } else if(cm.getInventory(1).getItem(1).copy().getFlag()==1){//判断装备上锁
                cm.sendOk("上锁的装备，需要解锁才可以强化哦!"); 		
                cm.dispose();    
                return;
            }
			else if (cm.getInventory(1).getItem(1).getUniqueId() !=-1) {
                cm.sendOk("时装在我这里无法提升强化。");
                cm.dispose();
            
            }else if(cm.getMeso() < 10000000) {
                cm.sendOk("抱歉您的金币不足1000万，请凑足了再来！");
                cm.dispose();
            }else if(!cm.haveItem(2049401,10)) {
                cm.sendOk("每次强化需要消耗#b10#k个#i2049401，请凑足了再来！");
                cm.dispose();
            }else {
		
		
		s1 = Math.floor(Math.random() * (100 - 1) + 1);//随机数
		if(s1 <= gailv){
			cm.gainMeso(-10000000);
			cm.gainItem(2049401,-10);
			item.setOwner("高等觉醒+3");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			
			

		    cm.getInventory(1).getItem(1).setUpgradeSlots(cm.getInventory(1).getItem(1).getUpgradeSlots()+1);
			cm.setBossLog('强化次数6');
			cm.刷新状态();
			cm.喇叭(2,"恭喜[" + cm.getPlayer().getName() + "]在高级地图强化中升级了一次可强化次数！"); 
			cm.dispose();
		} else {
			//cm.setBossLog('强化次数4');
			cm.gainMeso(-10000000);
			cm.gainItem(2049401,-10);
			cm.sendOk("强化失败~!");
            cm.dispose();
		}
    }
	/*}else {
		cm.sendOk("这里是武器装备次数强化中心，只可以武器才可以哟！！");
        cm.dispose();
	}*/
	
}
}