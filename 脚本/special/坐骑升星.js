
/* ==================
 脚本类型:  双倍卡领取NPC	    
 脚本作者： 枫叶
 联系方式QQ： 1848350048
 =====================
 */
var xx;//
var xxdj;//
var sj1;//成功率
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var xuqiushuliang;
var status = 0;
var 装备代码=1902002;
var 强化装备=Array(
1902002,
1912000,
1912406,
1902406,
1912404,
1902404,
1912403,
1902403,
1912412,
1902412,
1912413,
1902413,
1912416,
1902416,
1912417,
1902417,
1912420,
1902420

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
			cm.sendNext("\t"+彩虹+"  #e#d 元 宝 坐 骑 升 星#k#n  #r  "+彩虹+"#b#k#n\r\r\n  "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n   #b欢迎来到#e" + cm.getChannelServer().getServerName() + "坐骑升星中心\r\n#k#n1.在我这里可以使用点卷进行升星!\r\n2.需要升星的元宝坐骑请放在装备栏第一个格!#k\r\n");
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
			cm.sendOk("#b第一格子无东西或者第一格物品不是可升星元宝坐骑#k");	
			cm.dispose();
			} 	
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			if(cm.getInventory(1).getItem(1)== null ){
		            cm.sendOk("请把要升星的元宝坐骑放在第一格才能进行.");
				    cm.dispose();
			}
             else if (imtenuber<0 ) {
             cm.sendOk("第一格装备#v" + item.getItemId() + "#,不是可升星的元宝坐骑!");
			 cm.dispose();    
			 return;
            }
			else if(MapleItemInformationProvider.getInstance().getReqLevel(cm.getInventory(1).getItem(1).getItemId()) < 0) {
				cm.sendOk("你的装备等级没有超过0级");
				cm.dispose();
				return;
				} /*else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("现金装备无法升星。");
                    cm.dispose();
			}*/ else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能升星.");
				cm.dispose();
				return;
			}else if (xx == "★★★★★+10"){
				cm.sendOk("亲，恭喜您已经升到最高星级★★★★★+10，我已经没有能力再为它提升了哦！");
				cm.dispose();
			}else if(xx == ""){
				sj = Math.floor(Math.random()*99);//随机100%
				shibai = 0;
				xxnew = "☆";
				点卷数量 = 1000;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率99%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆"){
				sj = Math.floor(Math.random()*80);
				shibai = 0;
				xxnew = "☆☆";
				xxdj = 1;
				点卷数量 = 2000;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率80%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆"){
				sj = Math.floor(Math.random()*70);
				shibai = 0;
				xxnew = "☆☆☆";
				xxdj = 2;
				点卷数量 = 3000;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率70%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆☆"){
				sj = Math.floor(Math.random()*60);
				shibai = 0;
				xxnew = "☆☆☆☆";
				xxdj = 3;
				点卷数量 = 4000;
				四维属性 = 1;
				攻魔 = 1;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率60%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆☆☆"){
				sj = Math.floor(Math.random()*50);
				shibai = 0;
				xxnew = "☆☆☆☆☆";
				xxdj = 4;
				点卷数量 = 5000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率50%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "☆☆☆☆☆"){
				sj = Math.floor(Math.random()*40);
				shibai = 0;
				xxnew = "★☆☆☆☆";
				xxdj = 5;
				点卷数量 = 6000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率40%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★☆☆☆☆"){
				sj = Math.floor(Math.random()*30);
				shibai = 0;
				xxnew = "★★☆☆☆";
				xxdj = 6;
				点卷数量 = 7000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率30%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★☆☆☆"){
				sj = Math.floor(Math.random()*20);
				shibai = 0;
				xxnew = "★★★☆☆";
				xxdj = 7;
				点卷数量 = 8000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率20%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★☆☆"){
				sj = Math.floor(Math.random()*16);
				shibai = 0;
				xxnew = "★★★★☆";
				xxdj = 8;
				点卷数量 = 10000;
				四维属性 = 5;
				攻魔 = 5;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率16%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★★☆"){
				sj = Math.floor(Math.random()*13);
				shibai = 0;
				xxnew = "★★★★★";
				xxdj = 9;
				点卷数量 = 10000;
				四维属性 = 5;
				攻魔 = 5;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率13%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if(xx == "★★★★★"){
				sj = Math.floor(Math.random()*99);//随机100%
				shibai = 0;
				xxnew = "★★★★★+1";
				点卷数量 = 2000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率99%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "★★★★★+1"){
				sj = Math.floor(Math.random()*80);//随机80%
				shibai = 0;
				xxnew = "★★★★★+2";
				xxdj = 1;
				点卷数量 = 3000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率80%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "★★★★★+2"){
				sj = Math.floor(Math.random()*70);//随机60%
				shibai = 0;
				xxnew = "★★★★★+3";
				xxdj = 2;
				点卷数量 = 4000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率70%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "★★★★★+3"){
				sj = Math.floor(Math.random()*60);//随机40%
				shibai = 0;
				xxnew = "★★★★★+4";
				xxdj = 3;
				点卷数量 = 5000;
				四维属性 = 2;
				攻魔 = 2;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率60%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "★★★★★+4"){
				sj = Math.floor(Math.random()*50);//随机20%
				shibai = 0;
				xxnew = "★★★★★+5";
				xxdj = 4;
				点卷数量 = 7000;
				四维属性 = 3;
				攻魔 = 3;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率50%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
			}else if (xx == "★★★★★+5"){
				sj = Math.floor(Math.random()*40);//100%
				shibai = 0;
				xxnew = "★★★★★+6";
				xxdj = 5;
				点卷数量 = 8000;
				四维属性 = 3;
				攻魔 = 3;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率40%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★★★+6"){
				sj = Math.floor(Math.random()*30);
				shibai = 0;
				xxnew = "★★★★★+7";
				xxdj = 6;
				点卷数量 = 9000;
				四维属性 = 3;
				攻魔 = 3;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率30%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★★★+7"){
				sj = Math.floor(Math.random()*20);
				shibai = 0;
				xxnew = "★★★★★+8";
				xxdj = 7;
				点卷数量 = 10000;
				四维属性 = 3;
				攻魔 = 3;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率20%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★★★+8"){
				sj = Math.floor(Math.random()*16);
				shibai = 0;
				xxnew = "★★★★★+9";
				xxdj = 8;
				点卷数量 = 20000;
				四维属性 = 6;
				攻魔 = 6;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率16%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}else if (xx == "★★★★★+9"){
				sj = Math.floor(Math.random()*13);
				shibai = 0;
				xxnew = "★★★★★+10";
				xxdj = 9;
				点卷数量 = 20000;
				四维属性 = 6;
				攻魔 = 6;
				cm.sendNext("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前星之力等级为：#r"+xx+"#k\r\n升星成功几率13%\r\n#r四维属性:#r+"+四维属性+"\t魔/攻#r+"+攻魔+" \r\n#k您需要以下物品：\r\n#r点卷:"+点卷数量+"\r\n#k请点击下一步进行升星\r\n\r\n");
				}
				else
				{
					cm.sendOk("1");
					cm.dispose();
					return;
				}
		} else if (status == 3) {
		         
			
			if (cm.getPlayer().getCSPoints(1) < 点卷数量){//判断多少点卷
				cm.sendOk("点卷不足"+点卷数量+"！");
				cm.dispose();
				return;
				}  else if(sj <= 10){//随机成功
			cm.gainNX(-点卷数量);//扣除多少点卷
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Failure/0"); //失败
			cm.sendOk("升星失败！");
			cm.dispose();
			} else {//随机失败
			cm.gainNX(-点卷数量);//扣除多少点卷
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
			cm.全服黄色喇叭(  " 【元宝坐骑升星】" + " : " + "["+cm.getPlayer().getName()+"]元宝坐骑成功提升星之力至"+xxnew+"！大家祝贺他(她)吧！！")
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
