var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";




var xiaomi = {        
	//如果需要物品条件的寻宝，请填物品ID，不需要请填0  每日寻宝可以限制每天的次数，0为不限制
	需要物品: 4031018,需要数量: 1, 每日寻宝: 0,
	//刷新重置任务需要的物品和数量
	重置需要: 4031018,重置数量: 1 
};



//单个地图参数设置,精确度是设置地图误差最大值
//座标获取提示，GM号用：(!地图代码)命令找到你想的任务座标
var xmxsz = new Array(
{ 地图id: 100000000, 座标x: 134, 座标y:274, 精确度:50, 提示:"你可以去射手村出租车附近找找呗"  },
{ 地图id: 261000002, 座标x: 121, 座标y:125, 精确度:50, 提示:"蓝色的盾牌那里好像藏着点什么"  },
{ 地图id: 800000000, 座标x: 3726, 座标y:95, 精确度:50, 提示:"大碗宽面下有点不一样"  },
{ 地图id: 500010100, 座标x: 465, 座标y:180, 精确度:50, 提示:"有两朵最鲜艳的荷花在散发迷人的香味"  },
{ 地图id: 103000103, 座标x: 1111, 座标y:198, 精确度:50, 提示:"警告牌这里好像藏着点什么"  },
{ 地图id: 220020200, 座标x: 184, 座标y:35, 精确度:50, 提示:"你可以去找找看四颗星的骰子"  },
{ 地图id: 100000001, 座标x: 78, 座标y:38,   精确度:30, 提示:"射手村名宅里的宝箱上好像有点货"  }

);

//奖励库,可以随机奖各种，类型 道具  类型0是金币 类型1是点卷 类型2是抵用  随机数将会往上随机，如果无需随机请填0

var xmfjbl = new Array(


{ 类型: 4430000, 概率: 1, 数量: 1, 随机数: 0 },//物品元宝
{ 类型: 4001126, 概率: 50, 数量: 50, 随机数: 0 },//枫叶
{ 类型: 0, 概率: 50, 数量: 50000, 随机数: 0 },//金币
{ 类型: 1, 概率: 50, 数量: 500, 随机数: 0 },//点卷
{ 类型: 2, 概率: 50, 数量: 1000, 随机数: 0 }//抵用



);






var 表情高兴 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";
var status = -1;
var selection;
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 积分 = new Array(1,2);
var 随机积分 = 积分[Math.floor(Math.random() * 积分.length)];
var xmml1 = 0;
var xmml2 = 0;
var 任务ID = 0;
var 奖励ID = 0;
var DHDCLX;
var zjllog;
var 粉爱心 = "#fItem/Etc/0427/04270005/Icon8/1#";
var M9 = "#fEffect/CharacterEff/1112905/0/1#";//小红心
var 奖励 = "#fUI/UIWindow.img/Quest/reward#";
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var 任务简述 = "#fUI/UIWindow.img/Quest/summary#";
var 首页标题 = "#b┣━━━━━━━ " + 粉爱心 + " #e寻找◆#r宝藏#n " + 粉爱心 + " ━━━━━━━┫#k\r\n\r\n";



function 刷新任务() {   
	setxmwnjlc("MI藏宝图_刷新判断",0);
	
}




//每日记录，里面的函数是枫叶端的，如果是其他端自己改每日函数即可  log是记录识别 cs是参数
function getxmdailylogc(log) {   
	return cm.getPlayer().getBossLog(log);
	
}
function setxmdailylogc(log,cs) {   
	cm.getPlayer().setBossLog(log,-cm.getPlayer().getBossLog(log)+cs);
}
function gainxmdailylogc(log,cs) {   
	cm.getPlayer().setBossLog(log,cs);
}



function 功能喇叭(类型,数量) {
	lbtw = "【藏宝图】";
	// cm.worldMessage(2,lbtw+ " : " + "取得类型："+类型);
	if (类型 == 0) {
		
		cm.worldMessage(2,lbtw+ " : " + "恭喜 ["+cm.getName()+"] 跋山涉川找到宝藏获得 "+数量+" 金币!");
	} else if (类型 == 1) {
		
		cm.worldMessage(2,lbtw+ " : " + "恭喜 ["+cm.getName()+"] 跋山涉川找到宝藏获得 "+数量+" 点卷!");
	} else if (类型 == 2) {
		
		cm.worldMessage(2,lbtw+ " : " + "恭喜 ["+cm.getName()+"] 跋山涉川找到宝藏获得 "+数量+" 抵用!");
	} else {
		
		// cm.xiaomilabaxn(类型,lbtw,"恭喜 ["+cm.getName()+"] 找到了宝藏获得非常不错的道具",14);
		// cm.itemlaba("【时装强化】","恭喜玩家 " + cm.getPlayer().getName() + " 成功进阶一次！属性得到了提升！", item, 15);
		//cm.gainGachaponItem(4000016, 100, "【藏宝图】", 1);
		cm.worldMessage(2,lbtw+ " : " + "恭喜 ["+cm.getName()+"] 跋山涉川找到宝藏获得非常不错的道具!");
		
	}
	
	
	
}

function start() {   
	
	status = -1;
    action(1, 0, 0);
}


function action(mode, type, selection) { 
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
		cm.dispose();
        return;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
    var text = "";
	    // setxmwnjlc("MI藏宝图_刷新判断",0);
		// cm.getPlayer().dropMessage(5, "取得"+getxmwnjlc("MI藏宝图_刷新判断")+"  ");
		if (getxmwnjlc("MI藏宝图_刷新判断") == 0 ) {
			
			setxmwnjlc("MI藏宝图_刷新判断",1);
			
			任务ID = Math.floor(Math.random() * xmxsz.length);
			setxmwnjlc("MI藏宝图_任务ID",任务ID);
			奖励ID = Math.floor(Math.random() * xmfjbl.length);
			setxmwnjlc("MI藏宝图_奖励ID",奖励ID);
			
			// cm.getPlayer().dropMessage(5, "任务重新刷新..."+任务ID+"  "+奖励ID+" " );
			
		}
		
		任务ID = getxmwnjlc("MI藏宝图_任务ID");
		奖励ID = getxmwnjlc("MI藏宝图_奖励ID");
		// 任务ID = 1;
		text += 任务简述+"\r\n"
		text += "寻找宝藏需要拥有惊人的智慧和冒险的勇气,你准备好了吗？\r\n"
		text += "根据任务提示去寻找宝藏吧.考验你的耐心和细心的时候到了\r\n"
		if (xiaomi.需要物品 != null && xiaomi.需要物品 != 0) {
			text += "每次寻宝需要#i"+xiaomi.需要物品+":##b#z"+xiaomi.需要物品+"# x "+xiaomi.需要数量+"#k就可以开始寻宝咯！\r\n"
		}
		
		
		text += "#r小提示：#b[#m"+xmxsz[任务ID].地图id+"#]"+xmxsz[任务ID].提示+"#k\r\n"
		text += "到达指定地点，打开宝藏将会获得丰厚的财富哟，赶快试试吧\r\n"
		// text += "任务ID："+任务ID+"\r\n"
		// text += "奖励ID："+奖励ID+"\r\n"
		
		text += "#b#L100001#我已经到达宝藏地点#l #L100002#太难了我要换任务#l\r\n"
		
		cm.sendSimple(text);
        
    } else if (status == 1) {
		
		xmml1 = selection;
        if (xmml1 == 100001) {
			
			
			
			
			
			
			if (xiaomi.需要物品 != null && xiaomi.需要物品 != 0) {
				if (!cm.haveItem(xiaomi.需要物品,xiaomi.需要数量)) {
					cm.sendOk("你没有：#i"+xiaomi.需要物品+":##b#z"+xiaomi.需要物品+"# X "+xiaomi.需要数量+" 吧？");
					cm.dispose();
					return;
					
				}
				
			}
			
			if (xiaomi.每日寻宝 != null && xiaomi.每日寻宝 != 0 ) {
			if (getxmdailylogc("MI藏宝图_每日寻宝") >= xiaomi.每日寻宝 ) {
				
				cm.sendOk("每日的寻宝次数已经超过最大限制！请明天再来！\r\n");
				cm.dispose();
				return;
			}
			}
			
			if (xmxsz[任务ID].地图id != cm.getMapId() ) {
				// cm.getPlayer().dropMessage(5, "地图信息："+cm.getMapId()+"  "+xmxsz[任务ID].地图id);
				cm.sendOk("什么？你还没找到正确的地图吧？\r\n#r小提示：#b[#m"+xmxsz[任务ID].地图id+"#]"+xmxsz[任务ID].提示+"#k\r\n");
				cm.dispose();
				return;
			}
			
			座标x = cm.getPlayer().getPosition().x;
			座标y = cm.getPlayer().getPosition().y;
			
			
			mapyz = 座标x < (xmxsz[任务ID].座标x + xmxsz[任务ID].精确度) && 座标x > (xmxsz[任务ID].座标x - xmxsz[任务ID].精确度) && 座标y < (xmxsz[任务ID].座标y + xmxsz[任务ID].精确度) && 座标y > (xmxsz[任务ID].座标y - xmxsz[任务ID].精确度);
			if (!mapyz) {
				cm.sendOk("首先恭喜你，已经找对了正确地图！你站的地方貌似不对哟！");
				cm.dispose();
				return;
			}
			
			
			
			
			
			//可控概率随机----------------------------------
			var finalitem = Array();
			for (var i = 0; i < xmfjbl.length; i++) {
            if (xmfjbl[i].概率 >= Math.floor(Math.random() * 1000)) {
                // finalitem.push( {类型:xmfjbl[i].类型, 类型:xmfjbl[i].数量, 类型:xmfjbl[i].随机数  }  );
				finalitem.push(i);
				// cm.getPlayer().dropMessage(5, "加入值："+i+"  ");
				
				}
			}
			if (finalitem != 0) {
				
			var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
			var zzid = finalitem[finalchance];
			// cm.getPlayer().dropMessage(5, "随机信息："+zzid+"  ");
            奖励ID = zzid;	
				
			} else {
			奖励ID = Math.floor(Math.random() * xmfjbl.length);	
			}
			
			
			
			
			
			
			var leix = "";
			
			MI数量 = xmfjbl[奖励ID].数量 + Math.floor(Math.random() * xmfjbl[奖励ID].随机数 ) ;
			
			i = 奖励ID;
			if (xmfjbl[奖励ID].类型 == 0) { //金币
					
					cm.gainMeso(+MI数量);
					leix += ""+金币+"#r 金币 X " + MI数量 + "\r\n";
					
				} else if (xmfjbl[i].类型 == 1) { //点卷
					cm.getPlayer().modifyCSPoints(1, +MI数量, true); //点券
					leix += ""+点券图标+"#r 点卷 X " + MI数量 + "\r\n";
					
				} else if (xmfjbl[i].类型 == 2) { //抵用券
					cm.getPlayer().modifyCSPoints(2, +MI数量, true); //抵用券
					leix += ""+点券图标+"#r 抵用卷 X " + MI数量 + "\r\n";
						
				} else { //物品
					cm.gainItem(xmfjbl[i].类型, MI数量);
					leix += "#r#i" + xmfjbl[i].类型 + "##z" + xmfjbl[i].类型 + "##k X " + MI数量 + "\r\n";
			}
			cm.gainItem(xiaomi.需要物品, - xiaomi.需要数量);
			功能喇叭(xmfjbl[奖励ID].类型,MI数量);
			
			
			if (xiaomi.每日寻宝 != null && xiaomi.每日寻宝 != 0 ) {
			gainxmdailylogc("MI藏宝图_每日寻宝",1);
			}
			
			刷新任务();
			cm.sendOk("恭喜你，成功了："+leix);
			cm.dispose();
	    } else if (xmml1 == 100002) {
			if (xiaomi.重置需要 != null && xiaomi.重置需要 != 0) {
				if (!cm.haveItem(xiaomi.重置需要,xiaomi.重置数量)) {
					cm.sendOk("刷新任务需要：#i"+xiaomi.重置需要+":##b#z"+xiaomi.重置需要+"# X "+xiaomi.重置数量+" ");
					cm.dispose();
					return;
					
				}
				
			}
			cm.gainItem(xiaomi.重置需要, - xiaomi.重置数量);
			刷新任务();
			cm.sendOk("成功刷新任务");
			cm.dispose();
		} else {
			
		}			 
		
    } else if (status == 2) {
		if (xmml1 == 100001) {
			
		} else if (xmml1 == 100002) {
			
		} else {
			
			
		}

	}
}




function getxmwnjlc(bossid) {
	var xmsjfh = 0;
	characterid = cm.getPlayer().getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM xmwnjl WHERE characterid = "+characterid+" AND bossid = '"+bossid+"' ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();		
	if (result.next()) {
	xmsjfh = result.getInt("count");
	
	} 
	result.close();
	pstmt.close();
	return xmsjfh;
}


function setxmwnjlc(wnjllog,cs) {
	var accid = cm.getPlayer().getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM xmwnjl WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();	
	
	if (result.next()) {
		result.close();
	    var conn = Packages.database.DatabaseConnection.getConnection();
	    var sql = "UPDATE xmwnjl SET count = "+cs+"  WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	    var pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
		pstmt.close();		
	} else {
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "insert into xmwnjl (time,bossid,count,characterid) values (CURRENT_TIMESTAMP(),?,?,?);";          
    var psu = conn.prepareStatement(sql);
	psu.setString(1,wnjllog);
	psu.setInt(2,cs);
	psu.setInt(3,accid);
    psu.executeUpdate();	
	psu.close();	
	}	
}

function gainxmwnjlc(wnjllog,cs) {
	var accid = cm.getPlayer().getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM xmwnjl WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();	
	
	if (result.next()) {
		result.close();
	    var conn = Packages.database.DatabaseConnection.getConnection();
	    var sql = "UPDATE xmwnjl SET count = count+"+cs+"  WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	    var pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
		pstmt.close();		
	} else {
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "insert into xmwnjl (time,bossid,count,characterid) values (CURRENT_TIMESTAMP(),?,?,?);";          
    var psu = conn.prepareStatement(sql);
	psu.setString(1,wnjllog);
	psu.setInt(2,cs);
	psu.setInt(3,accid);
    psu.executeUpdate();	
	psu.close();	
	}	
}