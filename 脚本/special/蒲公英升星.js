
var xx;//
var xxdj;//
var sj1;//成功率
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var xuqiushuliang;
var status = 0;
var 装备代码=1112426;
var 强化装备=Array(
1112426,
1112426
);
var imtenuber=-1;
importPackage(java.util);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
function start() {
	status = -1;
	action(1, 0, 0);
}
function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if ((mode == 0 && status == 2) || (mode == 0 && status == 13)) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.sendNext("\t"+彩虹+"  #e#d 蒲 公 英 升 星 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n   #b欢迎来到#e" + cm.getChannelServer().getServerName() + "升星中心\r\n#k#n1.在我这里可以使用物品加金币进行升星!\r\n2.需要升星的戒指请放在装备栏第一个格!#k\r\n");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			for(var inumber=0;inumber<强化装备.length;inumber++)
			{   
		        if(cm.getInventory(1).getItem(1) == null) {
                cm.sendOk("第一格没有装备.");
                cm.dispose();
                return;
                }
				if(cc.getItemId()==强化装备[inumber])
				{
					imtenuber=cc.getItemId();
					break;
				}
			}
			if((cm.getInventory(1).getItem(1)!= null )||imtenuber>0){
			 cm.sendYesNo("你要升星的装备为:\r\n\r\n#v"+cc.getItemId()+"#\r\n\r\n#r#e确定要开始升星吗?");
			} else{
			cm.sendOk("#b第一格子无东西或者第一格物品不是可强化道具#k");	
			cm.dispose();
			} 	
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			if(cm.getInventory(1).getItem(1)== null ){
		            cm.sendOk("请把要升星的装备放在第一格才能进行.");
				    cm.dispose();
			}
             else if (imtenuber<0 ) {
             cm.sendOk("第一格装备是#v" + item.getItemId() + "#,不是蒲公英戒指!");
			 cm.dispose();    
			 return;
            }
			else if(MapleItemInformationProvider.getInstance().getReqLevel(cm.getInventory(1).getItem(1).getItemId()) < 0) {
				cm.sendOk("你的装备等级没有超过0级");
				cm.dispose();
				return;
				} else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("现金装备无法升星。");
                    cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能升星.");
				cm.dispose();
				return;
			}else if (xx == "★★★★★"){
				cm.sendOk("亲，恭喜您已经升到最高星级★★★★★，我已经没有能力再为它提升了哦！");
				cm.dispose();
			}else if(xx == ""){
				sj = Math.floor(Math.random()*80);
				shibai = 0;
				xxnew = "☆";
				枫叶 = 4001126;
				枫叶数量 = 500;
				
				绿蜗牛壳数量 = 200;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 200;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 0;
				猪头 = 4000017;
				
				蛇皮数量 = 0;
				蛇皮 = 4000034;
				
				混种石块数量 = 0;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 0;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 0;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 0;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 0;
				
				金币数量 = 500000;
				点卷数量 = 0;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率98%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\r\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆"){
				sj = Math.floor(Math.random()*80);
				shibai = 0;
				xxnew = "☆☆";
				xxdj = 1;
				枫叶 = 4001126;
				枫叶数量 = 1000;
				
				绿蜗牛壳数量 = 50;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 50;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 300;
				猪头 = 4000017;
				
				蛇皮数量 = 0;
				蛇皮 = 4000034;
				
				混种石块数量 = 0;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 0;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 0;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 0;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 0;
				
				金币数量 = 1000000;
				点卷数量 = 0;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率95%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\r\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\r\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆"){
				sj = Math.floor(Math.random()*70);
				shibai = 0;
				xxnew = "☆☆☆";
				xxdj = 2;
				枫叶 = 4001126;
				枫叶数量 = 1500;
				
				绿蜗牛壳数量 = 50;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 50;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 50;
				猪头 = 4000017;
				
				蛇皮数量 = 500;
				蛇皮 = 4000034;
				
				混种石块数量 = 0;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 0;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 0;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 0;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 0;
				
				金币数量 = 2000000;
				点卷数量 = 0;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率90%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\r\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\r\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆☆"){
				sj = Math.floor(Math.random()*55);
				shibai = 0;
				xxnew = "☆☆☆☆";
				xxdj = 3;
				枫叶 = 4001126;
				枫叶数量 = 2000;
				
				绿蜗牛壳数量 = 50;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 50;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 50;
				猪头 = 4000017;
				
				蛇皮数量 = 50;
				蛇皮 = 4000034;
				
				混种石块数量 = 1000;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 0;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 0;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 0;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 0;
				
				金币数量 = 4000000;
				点卷数量 = 0;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率80%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆☆☆"){
				sj = Math.floor(Math.random()*60);
				shibai = 0;
				xxnew = "☆☆☆☆☆";
				xxdj = 4;
				枫叶 = 4001126;
				枫叶数量 = 4000;
				
				绿蜗牛壳数量 = 100;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 100;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 100;
				猪头 = 4000017;
				
				蛇皮数量 = 100;
				蛇皮 = 4000034;
				
				混种石块数量 = 100;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 10;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 0;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 0;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 1;
				
				金币数量 = 6000000;
				点卷数量 = 0;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率70%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+独角狮硬角数量+"个#v"+独角狮硬角+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\t\n#r"+商品纪念币数量+"个#v"+商品纪念币+"#\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆☆☆☆"){
				sj = Math.floor(Math.random()*55);
				shibai = 0;
				xxnew = "★☆☆☆☆";
				xxdj = 5;
				枫叶 = 4001126;
				枫叶数量 = 5000;
				
				绿蜗牛壳数量 = 100;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 100;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 100;
				猪头 = 4000017;
				
				蛇皮数量 = 100;
				蛇皮 = 4000034;
				
				混种石块数量 = 100;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 10;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 2000;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 0;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 2;
				
				金币数量 = 10000000;
				点卷数量 = 0;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率70%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+独角狮硬角数量+"个#v"+独角狮硬角+"#\t\n#r"+自由灵魂数量+"个#v"+自由灵魂+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\t\n#r"+商品纪念币数量+"个#v"+商品纪念币+"#\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★☆☆☆☆"){
				sj = Math.floor(Math.random()*55);
				shibai = 0;
				xxnew = "★★☆☆☆";
				xxdj = 6;
				枫叶 = 4001126;
				枫叶数量 = 8000;
				
				绿蜗牛壳数量 = 100;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 100;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 100;
				猪头 = 4000017;
				
				蛇皮数量 = 100;
				蛇皮 = 4000034;
				
				混种石块数量 = 100;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 10;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 100;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 2000;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 0;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 3;
				
				金币数量 = 20000000;
				点卷数量 = 0;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率70%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+独角狮硬角数量+"个#v"+独角狮硬角+"#\t\n#r"+自由灵魂数量+"个#v"+自由灵魂+"#\r\n#r"+绵羊尾巴数量+"个#v"+绵羊尾巴+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\t\n#r"+商品纪念币数量+"个#v"+商品纪念币+"#\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★☆☆☆"){
				sj = Math.floor(Math.random()*54);
				shibai = 0;
				xxnew = "★★★☆☆";
				xxdj = 7;
				枫叶 = 4001126;
				枫叶数量 = 10000;
				
				绿蜗牛壳数量 = 100;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 100;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 100;
				猪头 = 4000017;
				
				蛇皮数量 = 100;
				蛇皮 = 4000034;
				
				混种石块数量 = 100;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 10;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 100;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 100;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 2000;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 0;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 4;
				
				金币数量 = 30000000;
				点卷数量 = 0;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率60%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+独角狮硬角数量+"个#v"+独角狮硬角+"#\t\n#r"+自由灵魂数量+"个#v"+自由灵魂+"#\r\n#r"+绵羊尾巴数量+"个#v"+绵羊尾巴+"#\t\n#r"+石灰粉瓶数量+"个#v"+石灰粉瓶+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\t\n#r"+商品纪念币数量+"个#v"+商品纪念币+"#\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★☆☆"){
				sj = Math.floor(Math.random()*45);
				shibai = 0;
				xxnew = "★★★★☆";
				xxdj = 8;
				枫叶 = 4001126;
				枫叶数量 = 20000;
				
				绿蜗牛壳数量 = 100;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 100;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 100;
				猪头 = 4000017;
				
				蛇皮数量 = 100;
				蛇皮 = 4000034;
				
				混种石块数量 = 100;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 10;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 100;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 100;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 100;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 2000;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 0;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 5;
				
				金币数量 = 50000000;
				点卷数量 = 0;
				四维属性 = 5;
				攻魔 = 5;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率55%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+独角狮硬角数量+"个#v"+独角狮硬角+"#\t\n#r"+自由灵魂数量+"个#v"+自由灵魂+"#\r\n#r"+绵羊尾巴数量+"个#v"+绵羊尾巴+"#\t\n#r"+石灰粉瓶数量+"个#v"+石灰粉瓶+"#\t\n#r"+骷髅护肩数量+"个#v"+骷髅护肩+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\t\n#r"+商品纪念币数量+"个#v"+商品纪念币+"#\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★★☆"){
				sj = Math.floor(Math.random()*30);
				shibai = 0;
				xxnew = "★★★★★";
				xxdj = 9;
				枫叶 = 4001126;
				枫叶数量 = 30000;
				
				绿蜗牛壳数量 = 100;
				绿蜗牛壳 = 4000019;
				
				红蜗牛壳数量 = 100;
				红蜗牛壳 = 4000016;
				
				猪头数量 = 100;
				猪头 = 4000017;
				
				蛇皮数量 = 100;
				蛇皮 = 4000034;
				
				混种石块数量 = 100;
				混种石块 = 4000177;
				
				独角狮硬角数量 = 10;
				独角狮硬角 = 4000073;
				
				自由灵魂数量 = 100;
				自由灵魂 = 4000144;
				
				绵羊尾巴数量 = 100;
				绵羊尾巴 = 4000242;
				
				石灰粉瓶数量 = 100;
				石灰粉瓶 = 4000182;
				
				骷髅护肩数量 = 100;
				骷髅护肩 = 4000267;
				
				无表情面具数量 = 2000;
				无表情面具 = 4000451;
				
				黄金枫叶 = 4000313;
				黄金枫叶数量 = 10;
				
				商品纪念币 = 4310150;
				商品纪念币数量 = 5;
				
				金币数量 = 100000000;
				点卷数量 = 0;
				四维属性 = 5;
				攻魔 = 5;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率50%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r金币:"+金币数量+"\r\n#r"+枫叶数量+"个#v"+枫叶+"#\t\n#r"+绿蜗牛壳数量+"个#v"+绿蜗牛壳+"#\t\n#r"+红蜗牛壳数量+"个#v"+红蜗牛壳+"#\t\n#r"+猪头数量+"个#v"+猪头+"#\r\n#r"+蛇皮数量+"个#v"+蛇皮+"#\t\n#r"+混种石块数量+"个#v"+混种石块+"#\t\n#r"+独角狮硬角数量+"个#v"+独角狮硬角+"#\t\n#r"+自由灵魂数量+"个#v"+自由灵魂+"#\r\n#r"+绵羊尾巴数量+"个#v"+绵羊尾巴+"#\t\n#r"+石灰粉瓶数量+"个#v"+石灰粉瓶+"#\t\n#r"+骷髅护肩数量+"个#v"+骷髅护肩+"#\t\n#r"+无表情面具数量+"个#v"+无表情面具+"#\r\n#r"+黄金枫叶数量+"个#v"+黄金枫叶+"#\t\n#r"+商品纪念币数量+"个#v"+商品纪念币+"#\r\n#k请点击下一步进行升星\r\n\r\n");
				}
				else
				{
					cm.sendOk("亲，低于10星的蒲公英才可以在这里升星哦！");
					cm.dispose();
					return;
				}
		} else if (status == 3) {
                
				if(!cm.haveItem(绿蜗牛壳,绿蜗牛壳数量)){
				cm.sendOk("#v"+绿蜗牛壳+"#物品数量不足"+绿蜗牛壳数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(红蜗牛壳,红蜗牛壳数量)){
				cm.sendOk("#v"+红蜗牛壳+"#物品数量不足"+红蜗牛壳数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(猪头,猪头数量)){
				cm.sendOk("#v"+猪头+"#物品数量不足"+猪头数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(蛇皮,蛇皮数量)){
				cm.sendOk("#v"+蛇皮+"#物品数量不足"+蛇皮数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(混种石块,混种石块数量)){
				cm.sendOk("#v"+混种石块+"#物品数量不足"+混种石块数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(独角狮硬角,独角狮硬角数量)){
				cm.sendOk("#v"+独角狮硬角+"#物品数量不足"+独角狮硬角数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(自由灵魂,自由灵魂数量)){
				cm.sendOk("#v"+自由灵魂+"#物品数量不足"+自由灵魂数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(绵羊尾巴,绵羊尾巴数量)){
				cm.sendOk("#v"+绵羊尾巴+"#物品数量不足"+绵羊尾巴数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(石灰粉瓶,石灰粉瓶数量)){
				cm.sendOk("#v"+石灰粉瓶+"#物品数量不足"+石灰粉瓶数量+"个！");
				cm.dispose();
				return;
				}
				
				else if(!cm.haveItem(骷髅护肩,骷髅护肩数量)){
				cm.sendOk("#v"+骷髅护肩+"#物品数量不足"+骷髅护肩数量+"个！");
				cm.dispose();
				return;
				}
				
                else if(!cm.haveItem(无表情面具,无表情面具数量)){
				cm.sendOk("#v"+无表情面具+"#物品数量不足"+无表情面具数量+"个！");
				cm.dispose();
				return;
				}
		        else if(!cm.haveItem(枫叶,枫叶数量)){
				cm.sendOk("#v"+枫叶+"#物品数量不足"+枫叶数量+"个！");
				cm.dispose();
				return;
				} else if(!cm.haveItem(黄金枫叶,黄金枫叶数量)){
				cm.sendOk("#v"+黄金枫叶+"#物品数量不足"+黄金枫叶数量+"个！");
				cm.dispose();
				return;
				}
				else if(!cm.haveItem(商品纪念币,商品纪念币数量)){
				cm.sendOk("#v"+商品纪念币+"#物品数量不足"+商品纪念币数量+"个！");
				cm.dispose();
				return;
				}
				else if (cm.getPlayer().getCSPoints(1) < 点卷数量){
				cm.sendOk("点卷不足"+点卷数量+"！");
				cm.dispose();
				return;
				}
			
			else if (cm.getMeso() < 金币数量){
				cm.sendOk("金币不足"+金币数量+"！");
				cm.dispose();
				return;
				} else if(sj <= 10){//随机成功
			cm.gainItem(绿蜗牛壳,-绿蜗牛壳数量);
			cm.gainItem(红蜗牛壳,-红蜗牛壳数量);
			cm.gainItem(猪头,-猪头数量);
			cm.gainItem(蛇皮,-蛇皮数量);
			cm.gainItem(混种石块,-混种石块数量);
			cm.gainItem(独角狮硬角,-独角狮硬角数量);
			cm.gainItem(自由灵魂,-自由灵魂数量);
			cm.gainItem(绵羊尾巴,-绵羊尾巴数量);
			cm.gainItem(石灰粉瓶,-石灰粉瓶数量);
			cm.gainItem(骷髅护肩,-骷髅护肩数量);
			cm.gainItem(无表情面具,-无表情面具数量);
			
			cm.gainNX(-点卷数量);		
			cm.gainItem(商品纪念币,-商品纪念币数量);
			cm.gainItem(黄金枫叶,-黄金枫叶数量);
			cm.gainItem(枫叶,-枫叶数量);
			cm.gainMeso(-金币数量);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Failure/0"); //失败
			cm.sendOk("升星失败！");
			cm.dispose();
			} else {
			cm.gainItem(绿蜗牛壳,-绿蜗牛壳数量);
			cm.gainItem(红蜗牛壳,-红蜗牛壳数量);
			cm.gainItem(猪头,-猪头数量);
			cm.gainItem(蛇皮,-蛇皮数量);
			cm.gainItem(混种石块,-混种石块数量);
			cm.gainItem(独角狮硬角,-独角狮硬角数量);
			cm.gainItem(自由灵魂,-自由灵魂数量);
			cm.gainItem(绵羊尾巴,-绵羊尾巴数量);
			cm.gainItem(石灰粉瓶,-石灰粉瓶数量);
			cm.gainItem(骷髅护肩,-骷髅护肩数量);
			cm.gainItem(无表情面具,-无表情面具数量);			
			cm.gainNX(-点卷数量);
			cm.gainMeso(-金币数量);
			cm.gainItem(商品纪念币,-商品纪念币数量);
			cm.gainItem(黄金枫叶,-黄金枫叶数量);
			cm.gainItem(枫叶,-枫叶数量);
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = Packages.server.MapleItemInformationProvider.getInstance();
			var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
			var sx0 = item.getStr();//获取装备当前力量0
			var sx1 = item.getDex();//获取装备当前敏捷1
			var sx2 = item.getInt();//获取装备当前智力2
			var sx3 = item.getLuk();//获取装备当前运气3
			var sx4 = item.getHp();//获取装备当前HP4
			var sx5 = item.getMp();//获取装备当前MP5
			var sx6 = item.getWatk();//获取装备当前物攻6
			var sx7 = item.getMatk();//获取装备当前魔攻7
			var sx8 = item.getWdef();//获取装备当前物防8
			var sx9 = item.getMdef();//获取装备当前魔防9
			var sx10= item.getAcc();//获取装备当前命中10
			var sx11= item.getAvoid();//获取装备当前回避11
			var sx12= item.getHands();//获取装备当前手技12
			var sx13= item.getSpeed();//获取装备当前移动速度13
			var sx14= item.getJump();//获取装备当前跳跃力14
			item.setFlag(1);//上锁
			item.setStr(sx0+四维属性);
			item.setDex(sx1+四维属性);
			item.setInt(sx2+四维属性);
			item.setLuk(sx3+四维属性);
			item.setHp(sx4);
			item.setMp(sx5);
			item.setWatk(sx6+攻魔);
			item.setMatk(sx7+攻魔);
			item.setWdef(sx8);
			item.setMdef(sx9);
			item.setAcc(sx10);
			item.setAvoid(sx11);
			item.setHands(sx12);
			item.setSpeed(sx13);
			item.setJump(sx14);
			item.setOwner(xxnew);
			cm.dispose();
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
			cm.sendOk("升星成功！");
			cm.全服黄色喇叭(  " 【戒指升星】" + " : " + "["+cm.getPlayer().getName()+"]蒲公英戒指成功提升星之力至"+xxnew+"！大家祝贺他(她)吧！！")
			cm.dispose();
				}
			}
	}}
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";