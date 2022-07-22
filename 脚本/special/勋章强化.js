
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";
var xx;//
var xxdj;//
var sj1;//成功率
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var xuqiushuliang;
var status = 0;

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
			cm.sendNext("#r#e             "+小黄星+"   #e#d勋 章  强 化#k#n   "+小黄星+"  #k#n\r\n  "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n   #b欢迎来到#e" + cm.getChannelServer().getServerName() + "勋章强化中心\r\n#k#n1.在我这里可以使用物品进行勋章强化!\r\n2.需要升星的勋章请放在装备栏第一个格!#k\r\n");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			if(cm.getInventory(1).getItem(1)!= null ){
			 cm.sendYesNo("你要升星的装备为:\r\n\r\n#v"+cc.getItemId()+"#\r\n\r\n#r#e确定要开始升星吗?");
			} else{
			cm.sendOk("#b第一格子无东西！#k");	
			cm.dispose();
			} 
			
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			if(cm.getInventory(1).getItem(1)== null ){
		            cm.sendOk("请把要升星的装备放在第一格才能进行.");
				    cm.dispose();
			}
             else if (Math.floor(item.getItemId()/10000) != 114 ) {
             cm.sendOk("第一格装备是#v" + item.getItemId() + "#,不是勋章!");                                   cm.dispose();    
            }
			else if(item.getItemId()==1142803)
			{
				cm.sendOk("该装备无法强化!");
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
			}else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("现金装备无法升星。");
                    cm.dispose();
			}
			else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能升星.");
				cm.dispose();
				return;
			}else if (xx == "★★★★★"){
				cm.sendOk("最高只能强化5星");
				cm.dispose();
				return;
			}else if(xx == ""){
				sj = Math.floor(Math.random()*100);//随机100%
				shibai = 0;
				xxnew = "★";
				星之大陆钱币 = 4310148;
				星之大陆钱币数量 = 1;
				正义币 = 4310034;
				正义币数量 = 1;
				十字币 = 4310029;
				十字币数量 = 1;
				枫叶 = 4001126;
				枫叶数量 = 200;
				金币数量 = 100000;
				四维属性 = 1;
				攻魔 = 1;
				生命值 = 50;
				魔法值 = 50;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率100%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \tHP/MP#r+"+生命值+"\r\n#k您需要以下物品\r\n#r"+星之大陆钱币数量+"个#v"+星之大陆钱币+"#\r\n#r"+正义币数量+"个#v"+正义币+"#\r\n#r"+十字币数量+"个#v"+十字币+"#\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n金币:"+金币数量+"\r\n#k请点击下一步使用星之力进行升星\r\n\r\n");
			}else if (xx == "★"){
				sj = Math.floor(Math.random()*80);//随机80%
				shibai = 0;
				xxnew = "★★";
				xxdj = 1;
				星之大陆钱币 = 4310148;
				星之大陆钱币数量 = 2;
				正义币 = 4310034;
				正义币数量 = 1;
				十字币 = 4310029;
				十字币数量 = 1;
				枫叶 = 4001126;
				枫叶数量 = 400;
				金币数量 = 500000;
				四维属性 = 2;
				攻魔 = 1;
				生命值 = 100;
				魔法值 = 100;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率80%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \tHP/MP#r+"+生命值+"\r\n#k您需要以下物品\r\n#r"+星之大陆钱币数量+"个#v"+星之大陆钱币+"#\r\n#r"+正义币数量+"个#v"+正义币+"#\r\n#r"+十字币数量+"个#v"+十字币+"#\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n金币:"+金币数量+"\r\n#k请点击下一步使用星之力进行升星\r\n\r\n");
			}else if (xx == "★★"){
				sj = Math.floor(Math.random()*60);//随机60%
				shibai = 0;
				xxnew = "★★★";
				xxdj = 2;
				星之大陆钱币 = 4310148;
				星之大陆钱币数量 = 3;
				正义币 = 4310034;
				正义币数量 = 1;
				十字币 = 4310029;
				十字币数量 = 1;
				枫叶 = 4001126;
				枫叶数量 = 600;
				金币数量 = 1000000;
				四维属性 = 3;
				攻魔 = 2;
				生命值 = 200;
				魔法值 = 200;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率60%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \tHP/MP#r+"+生命值+"\r\n#k您需要以下物品\r\n#r"+星之大陆钱币数量+"个#v"+星之大陆钱币+"#\r\n#r"+正义币数量+"个#v"+正义币+"#\r\n#r"+十字币数量+"个#v"+十字币+"#\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n金币:"+金币数量+"\r\n#k请点击下一步使用星之力进行升星\r\n\r\n");
			}else if (xx == "★★★"){
				sj = Math.floor(Math.random()*40);//随机40%
				shibai = 0;
				xxnew = "★★★★";
				xxdj = 3;
				星之大陆钱币 = 4310148;
				星之大陆钱币数量 = 4;
				正义币 = 4310034;
				正义币数量 = 1;
				十字币 = 4310029;
				十字币数量 = 1;
				枫叶 = 4001126;
				枫叶数量 = 800;
				金币数量 = 1000000;
				四维属性 = 4;
				攻魔 = 3;
				生命值 = 300;
				魔法值 = 300;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率40%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \tHP/MP#r+"+生命值+"\r\n#k您需要以下物品\r\n#r"+星之大陆钱币数量+"个#v"+星之大陆钱币+"#\r\n#r"+正义币数量+"个#v"+正义币+"#\r\n#r"+十字币数量+"个#v"+十字币+"#\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n金币:"+金币数量+"\r\n#k请点击下一步使用星之力进行升星\r\n\r\n");
			}else if (xx == "★★★★"){
				sj = Math.floor(Math.random()*20);//随机20%
				shibai = 0;
				xxnew = "★★★★★";
				xxdj = 4;
				星之大陆钱币 = 4310148;
				星之大陆钱币数量 = 5;
				正义币 = 4310034;
				正义币数量 = 1;
				十字币 = 4310029;
				十字币数量 = 1;
				枫叶 = 4001126;
				枫叶数量 = 800;
				金币数量 = 1000000;
				四维属性 = 5;
				攻魔 = 4;
				生命值 = 400;
				魔法值 = 400;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率20%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \tHP/MP#r+"+生命值+"\r\n#k您需要以下物品\r\n#r"+星之大陆钱币数量+"个#v"+星之大陆钱币+"#\r\n#r"+正义币数量+"个#v"+正义币+"#\r\n#r"+十字币数量+"个#v"+十字币+"#\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n金币:"+金币数量+"\r\n#k请点击下一步使用星之力进行升星\r\n\r\n");
			}/*else if (xx == "★★★★★"){
				sj = Math.floor(100);//100%
				shibai = 0;
				xxnew = "★★★★★★";
				xxdj = 5;
				星之大陆钱币 = 4310148;
				星之大陆钱币数量 = 6;
				正义币 = 4310148;
				正义币数量 = 6;
				十字币 = 4000463;
				十字币数量 = 50;
				金币数量 = 10000000;
				四维属性 = 10;
				攻魔 = 5;
				生命值 = 500;
				魔法值 = 500;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率80%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \tHP/MP#r+"+生命值+"\r\n#k您需要以下物品\r\n#r"+星之大陆钱币数量+"个#v"+星之大陆钱币+"#\r\n#r"+正义币数量+"个#v"+正义币+"#\r\n#r"+十字币数量+"个#v"+十字币+"#\r\n#r"+枫叶数量+"个#v"+枫叶+"#\r\n金币:"+金币数量+"\r\n#k请点击下一步使用星之力进行升星\r\n\r\n");
				}*/
		} else if (status == 3) {
		       if(!cm.haveItem(星之大陆钱币,星之大陆钱币数量)){
				cm.sendOk("#v"+星之大陆钱币+"#物品数量不足"+星之大陆钱币数量+"个！");
				cm.dispose();
				return;
			} else if(!cm.haveItem(正义币,正义币数量)){
				cm.sendOk("#v"+正义币+"#物品数量不足"+正义币数量+"个！");
				cm.dispose();
				return;
				} else if(!cm.haveItem(十字币,十字币数量)){
				cm.sendOk("#v"+十字币+"#物品数量不足"+十字币数量+"个！");
				cm.dispose();
				return;
			} 
			else if(!cm.haveItem(枫叶,枫叶数量)){
				cm.sendOk("#v"+枫叶+"#物品数量不足"+枫叶数量+"个！");
				cm.dispose();
				return;
			}
			else if (cm.getMeso() < 金币数量){//判断多少金币
				cm.sendOk("金币不足"+金币数量+"！");
				cm.dispose();
				return;
				} else if(sj <= 10){//随机成功
			cm.gainMeso(-金币数量);//扣除多少金币
			cm.gainItem(星之大陆钱币,-星之大陆钱币数量);
			cm.gainItem(正义币,-正义币数量);
			cm.gainItem(十字币,-十字币数量);
			cm.gainItem(枫叶,-枫叶数量);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Failure/0"); //失败
			cm.sendOk("升星失败！");
			cm.dispose();
			} else {//随机失败
			cm.gainMeso(-金币数量);//扣除多少金币
			cm.gainItem(星之大陆钱币,-星之大陆钱币数量);
			cm.gainItem(正义币,-正义币数量);
			cm.gainItem(十字币,-十字币数量);
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
			item.setHp(sx4+生命值);
			item.setMp(sx5+魔法值);
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
			cm.全服黄色喇叭(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功提升星之力至"+xxnew+"！大家祝贺他(她)吧！！")
			cm.dispose();
				}
			}
	}}
