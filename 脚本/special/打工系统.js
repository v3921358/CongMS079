
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 礼包物品 = "#v1302000#";
var add = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //爱心
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";

var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 粉爱心 = "#fItem/Etc/0427/04270005/Icon8/1#";  //
var 菊花 = "#fUI/PredictHarmony/card/19#";//卡片效果菊花
var 笑 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/0#";//笑脸
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 巫女 ="#fMap/MapHelper/weather/witch/0#";//巫女
var 气球 ="#fMap/MapHelper/weather/balloon/4#";//气球
var 射箭 ="#fMap/MapHelper/weather/LoveEffect2/4/0#";//射箭
var 玫瑰 ="#fMap/MapHelper/weather/rose/0#";//玫瑰花
var 烟花 ="#fMap/MapHelper/weather/squib/squib1/3#";//烟花

var 大粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var 小粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //粉兔子
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //橙兔子
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //蓝兔子
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 邪恶小兔2 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
var 礼包1 = "#v5680069#";
var 奖励 = "#fUI/UIWindow.img/Quest/reward#";
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var 气球 ="#fMap/MapHelper/weather/balloon/4#";//气球
var 任务简述 = "#fUI/UIWindow.img/Quest/summary#";
var 经验值 = "#fUI/UIWindow.img/QuestIcon/8/0#";
/* 脚本定制： 20219442*/

var reqitemid = new Array(
//战士武器
1302010,1302011,1302012,1302018,1302023,1302056,1302059,1312008,1312009,1312010,1312011,1312015,1312030,01312031,1322017,1322018,1322019,1322020,1322028,1322028,1322029,1322045,1322052,1402003,1402004,1402005,1402011,1402012,1402015,1402016,1402035,1402036,1412003,1412007,1412008,1412009,1412010,1412021,1412026,1422005,1422009,1422010,1422012,1422013,1422027,1422028,1432004,1432006,1432007,1432010,1432011,1432030,1432038,1442005,1442008,1442010,1442019,1442020,1442044,1442045,

//法师武器
1372007,1372009,1372010,1372014,1372015,1372016,1372032,1382006,1382007,1382008,1382010,1382035,1382036,


//弓箭手武器
1452004,1452008,1452009,1452010,1452011,1452012,1452013,1452014,1452015,1452017,1452019,1452020,1452021,1452044,1462007,1462008,1462009,1462010,1462011,1462012,1462013,1462015,1462016,1462017,1462018,1462039,

//飞侠武器
1332003,1332015,1332016,1332017,1332018,1332019,1332022,1332023,1332026,1332027,1332049,1332050,1332051,1332052,1472018,1472019,1472020,1472021,1472022,1472023,1472024,1472025,1472026,1472027,1472028,1472029,1472031,1472033,1472051,1472052,1472053,

//海盗武器
1482007,1482008,1482009,1482010,1482011,1482012,1482092,1492007,1492008,1492009,1492010,1492011,1492012,1492013

);

//脚本参数设定
var qilin = { 
总阶段: 12,   属性值: 1,
点卷_基础奖励: 20,    点卷_阶段加成: 10,     点卷_道具等级加成: 1,  
金币_基础奖励: 100000,  金币_阶段加成: 10000,  金币_道具等级加成: 1000,
经验_基础奖励: 10000,   经验_角色等级加成: 15,  经验_阶段加成: 1000,  经验_道具等级加成: 1000
};






var 阶段 = 0;

function start() {
	status = -1;

	action(1, 0, 0);
}

function action(mode, type, selection) {

	if (mode == 1) {
		status++;
	} else if (mode == 0 && status != 0) {
		status--;
	} else {
		cm.dispose();
		return;
	}

	if (status == 0) {
		qujsdj = cm.getPlayer().getLevel();
		if (qujsdj < 50) {
			cm.sendOk("50级以上才能开始做此任务哦！");
            cm.dispose();
            return;
		} 
		if (cm.getPlayer().getBossLog("麒麟打工系统_总次",1) > 0 ) {
			cm.sendOk("每天只能完成一次12环！");
            cm.dispose();
            return;
		} 
		
		Maper = Packages.server.MapleItemInformationProvider;//可用
		阶段 = cm.getPlayer().getBossLog("麒麟打工系统_阶段",1);
		完成总次数 = cm.getPlayer().getBossLog("麒麟打工系统_总次",1);
		
		
		
		
		if (cm.getPlayer().getBossLog("麒麟打工系统_随机物品",1) == 0) {
		var mycarswp = new Array();
		var a = 0;
		for (var i = 0; i < reqitemid.length; i++) {
			var zbdj = Maper.getInstance().getReqLevel(reqitemid[i]);
			if (zbdj <= 110) {
			if (zbdj <= (qujsdj+10)  ) {
				a++;
				mycarswp[a] = reqitemid[i];
				//cm.getPlayer().dropMessage(5, "循环："+a);
			} 
			}
			
		}	
		var suijizbid = mycarswp[Math.floor(Math.random() * mycarswp.length)];	
			
			
		
		cm.getPlayer().setBossLog("麒麟打工系统_随机物品",1,+1);			
		cm.getPlayer().setBossLog("麒麟每日跑环_当前任务道具",1,-cm.getPlayer().getBossLog("麒麟每日跑环_当前任务道具",1)+suijizbid);
		
		}
		需要道具 = cm.getPlayer().getBossLog("麒麟每日跑环_当前任务道具",1);
		
		if (!Maper.getInstance().itemExists(需要道具)) {
			重置当前任务();
			cm.getPlayer().dropMessage(5, "[每日打工]系统出现未知错误："+需要道具+"  请尝试重新打开，如一直无法打开请联系GM");
			cm.dispose();
		    return;
		}
		
		item = cm.getEquip(需要道具).copy();
		
		if (item.getWatk() >= item.getMatk()){
			xyzhi = item.getWatk();
		} else {
			xyzhi = item.getMatk();
		}
		//Maper = Packages.server.MapleItemInformationProvider;
		装备等级 = Maper.getInstance().getReqLevel(需要道具);
		xyzhi = xyzhi+qilin.属性值;
		//阶段 = 11;
		奖励点卷 = qilin.点卷_基础奖励+(阶段*qilin.点卷_阶段加成)+(装备等级*qilin.点卷_道具等级加成);
		奖励金币 = qilin.金币_基础奖励+(阶段*qilin.金币_阶段加成)+(装备等级*qilin.金币_道具等级加成);
		
		getlevelExp = Packages.constants.GameConstants.getExpNeededForLevel(qujsdj);
		
		jy1 = qilin.经验_基础奖励+(阶段*qilin.经验_阶段加成)+(装备等级*qilin.经验_道具等级加成);
		奖励经验 = parseInt(jy1+(getlevelExp/qilin.经验_角色等级加成));
		超强翻倍 = "";
		if (装备等级 >= 251) {
			奖励点卷 = 奖励点卷*2;
			奖励金币 = 奖励金币*2;
			奖励经验 = 奖励经验*2;
			超强翻倍 = "#r翻倍#k";
		}
		
		进度 = parseInt(100/qilin.总阶段*阶段);
		var text = ""+任务简述+" 第["+(阶段+1)+"/"+qilin.总阶段+"]阶\r\n";
		
		//text += "测试1:"+装备等级+"\r\n";
		
		
		text += "完成总次数："+完成总次数+"\r\n";
		text += "完成进度：#B"+进度+"[%]#"+进度+" %\r\n";
		
		text += "#b#i"+需要道具+":##z"+需要道具+"##k 需要攻击力或魔法力 #r"+xyzhi+"#k 以上的\r\n";
        text += "放进装备栏第一格提交任务！将会获得丰厚奖励！\r\n";
		//text += "#L1000##r物品代码："+需要道具+" [查询爆率]#k#l\r\n\r\n"	
		
		text += "PS:如果你不方便，可以委托他人帮你制作哦！\r\n\r\n";
		
		text += ""+奖励+"\r\n";
		
		
		text += ""+金币+"#k冒险币 #r+"+奖励金币+" "+超强翻倍+"\r\n";
		text += ""+点券图标+"#k点卷 #r+"+奖励点卷+" "+超强翻倍+"\r\n";
		text += ""+经验值+" #r+"+奖励经验+" "+超强翻倍+"\r\n";
		text += "#e#b#L1#"+小黄星+"提交任务#l #L2#"+小黄星+"重置本环任务#l #L3#"+小黄星+"攻略说明#l\r\n";
		
		

		

		
		cm.sendSimpleS(text,2);
	} else if (status == 1) {
		if (selection == 1) {			
			if (qujsdj < 50) {
				cm.sendOk("50级以上才能开始做此任务哦！");
                // cm.dispose();
                // return;
			 
			} else if (cm.getInventory(1).getItem(1) == null) {
				cm.sendOk("必须放在装备栏第一格");
                // cm.dispose();
                // return;
			} else {
			
			
			
			var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
			
			if (item.getItemId() != 需要道具){
				cm.sendOk("我需要的装备是:#v"+需要道具+"##t"+需要道具+"#");
			} else if (item.getWatk() < xyzhi && item.getMatk() < xyzhi ) {
				cm.sendOk("抱歉，你的装备不符合要求..");		
		    } else if (cm.getInventory(2).isFull(3)) {
				cm.sendOk("背包空间不足！");
			} else {
				cm.getPlayer().setBossLog("麒麟打工系统_阶段",1,+1);
				
				重置当前任务();		
				cm.removeSlot(1,1,1);
				
				cm.getPlayer().modifyCSPoints(1, +奖励点卷, true); //点券
				cm.gainMeso(奖励金币); //扣除多少金币
				cm.gainExp(奖励经验); //扣除多少经验
				cm.sendOk("恭喜完成任务获得丰厚奖励..");
				cm.喇叭(1,"【打工中心】" + " : " + "恭喜["+cm.getPlayer().getName()+"]完成第"+(阶段+1)+"阶段，获得点卷："+奖励点卷+" 金币："+奖励金币+" 经验："+奖励经验+"");
				if (cm.getPlayer().getBossLog("麒麟打工系统_阶段",1) >= qilin.总阶段) {
					cm.gainItem(2049100,1);//混沌卷轴60%
					cm.gainItem(2340000,1);//祝福卷
					
					cm.getPlayer().setBossLog("麒麟打工系统_总次",1,+1);
					cm.getPlayer().setBossLog("麒麟打工系统_阶段",1,-cm.getPlayer().getBossLog("麒麟打工系统_阶段",1));
					cm.喇叭(1,"【打工中心】" + " : " + "恭喜["+cm.getPlayer().getName()+"] 完成了全部打工任务，重新开始了新的工作...");
				
			    }
		    }
			cm.dispose();
			}
			status = -1;
			
			
		} else if (selection == 2) {
			if (cm.haveItem(4000017,10)) {
			cm.gainItem(4000017,-10);
			重置当前任务();
			cm.sendOk("成功重置任务..");
			// cm.dispose();	
			} else {
			cm.sendOk("重置任务需要10个#v4000017#猪头..");
			// cm.dispose();		
			}
			status = -1;
		} else if (selection == 3) {
			cm.sendOk("任务道具可在锻造里出来，怪物也有爆率哦，或者你也可以委托其他人来帮忙哦\r\n任务做到最后有小小的惊喜奖励哦！\r\n\r\n"+奖励+"\r\n#v2049100##t2049100# X 1\r\n#v2340000##t2340000# X 1");
			
			status = -1;
		
		} else if (selection == 1000){
			//cm.checkDropper(selection);
			cm.sendOk(cm.checkDropper(需要道具));
			cm.dispose();
		}
		
		

		

	}

}


function 重置当前任务() {
	cm.getPlayer().setBossLog("麒麟打工系统_随机物品",1,-cm.getPlayer().getBossLog("麒麟打工系统_随机物品"));
}
