var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY7 = "┃   唯一QQ:12384161    ┃";
var CY8 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";

var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE);//获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var add = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具

var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //爱心

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
var 音乐 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 反方箭头 = "#fUI/Basic/BtHide2/mouseOver/0#";
var 大箭头 = "#fUI/Basic/icon/arrow#"; // → 大箭头
var 警钟 = "#fUI/Basic/BtClaim/normal/0#";
var 金币 = "#fUI/UIWindow/QuestIcon/7/0#";
var 点券 = "#fUI/CashShop/CashItem/0#";
var 加号 = "#fUI/Basic/BtMax/normal/0#";
var 热点推荐 = "#fUI/CashShop/CSChar/BtCoordination/mouseOver/0#";
var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
var 商品已售空 = "#fUI/CashShop/GuideWords/0#";
var 现在不是购买时间 = "#fUI/CashShop/GuideWords/1#";
var 完成 = "#fUI/Basic/CheckBox/0#";   //有框框 无√
var 完成1 = "#fUI/Basic/CheckBox/1#";   //有框框 有√
var 蝴蝶 = "#fEffect/CharacterEff/1051366/1/0#"; // 蓝色蝴蝶
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 四方印 = "#fUI/GuildMark/Mark/Pattern/00004014/11#"; // 紫色四方印
var 四方印1 = "#fUI/GuildMark/Mark/Pattern/00004014/16#"; // 褐色四方印
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 邪恶小兔1 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 经验值 = "#fUI/UIWindow/QuestIcon/8/0#";
var 人气度 = "#fUI/UIWindow/QuestIcon/6/0#";
var 几率获得 = "#fUI/UIWindow/Quest/prob#";
var 选择获得 = "#fUI/UIWindow/Quest/select#";
var 奖励 = "#fUI/UIWindow/Quest/reward#";
var 双箭头 = "#fUI/UIWindow/Quest/icon8/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";



function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	
	if (cm.getPlayer().getLevel() < 10) {
        cm.sendOk("您的等级小于10级暂时无法使用拍卖功能。");
        cm.dispose();
    }
	
    if (cm.getMapId() == 180000001) {
            cm.sendOk("很遗憾，您因为违反用户守则被禁止游戏活动，如有异议请联系管理员.");
            cm.dispose();
        }  
    else if (status == 0) {
        var selStr ="\t\t"+彩虹+"  #e#d 个 人 信 息 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";
		selStr += "#e#d亲爱的玩家 #r[#h ##k#r]#d,欢迎来到 #r[" + cm.getChannelServer().getServerName() + "] #k\r\n\r\n"; 
		selStr += "  #r当前服务器时间:  #b"+桃花+"  " + cm.getHour() + " 时:" + cm.getMin() + " 分:" + cm.getSec() + " 秒\r\n";
		selStr += "  #r当前在线  时间:  #b"+桃花+"  " + cm.getGamePoints() + "  分钟 \r\n";
		selStr += "  #r当前可用  点卷:  #b"+桃花+"  " + cm.getPlayer().getCSPoints(1) + "  点\r\n";
		selStr += "  #r当前可用  抵用:  #b"+桃花+"  " + cm.getPlayer().getCSPoints(2) + "  点\r\n";
		selStr += "  #r当前可用  豆豆:  #b"+桃花+"  " + cm.getBeans() + "  点\r\n";
		selStr += "  #r当前可用  金币:  #b"+桃花+"  " + cm.getMeso() + "  点\r\n";

		
	

		
		
		
		
		selStr += "\r\n"
		selStr += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";

		
		
	cm.sendSimple(selStr);
	cm.dispose();
    } else if (status == 1) {
        switch (selection) {
        case 1://自由市场
            cm.warp(910000000);
            cm.dispose();
            break;
			
		case 2://个人信息
            cm.dispose();
            cm.sendOk("#d"+草莓+"亲爱的玩家#r [#e#h ##n#k#r] #d\t 欢迎来到 童心冒险岛 "+草莓+"\r\n"+大黄星+"#b童心冒险岛  充值比例:\t#r1元==1余额==1000点券.\r\n"+大黄星+"#b当前在线        时间:\t#r"+cm.getGamePoints()+" 分钟#k#n\r\n"+大黄星+"#b当前可用        点券:\t#r"+cm.getPlayer().getCSPoints(1)+"  \r\n"+大黄星+"#b当前可用      抵用卷:\t#r"+cm.getPlayer().getCSPoints(2)+"\r\n"+大黄星+"#b当前可用      游戏币:\t#r" + cm.getMeso() + "#k\r\n"+大黄星+"#b当前可用        余额:\t#r"+cm.getrmb()+"    #k\r\n"+大黄星+"#b当前累计    消费积分:\t#r" + cm.getczjf() + " #k\r\n\r\n");//"+大黄星+"#b当前可用    杀怪点数:\t#r" + cm.getPlayer().getSG() + " #k\r\n您当前未兑现金额为"+ cm.getwzcz() +"元 ，兑换失败！
            break;
			
        case 3://万能传送
            cm.dispose();
            cm.openNpc(9900004,21);
            break;
			
        case 4://福利中心
            cm.dispose();
            cm.openNpc(9900004, "福利中心");
            break;
			
        case 5://每日任务
            cm.dispose();
            cm.openNpc(9900004, "每日任务");
            break;
			
        case 6://主线任务
            cm.dispose();
            cm.openNpc(9900004,506);
            break;
			
        case 7://强化中心
            cm.dispose();
            cm.openNpc(9310072,0);
            break;
			
        case 8://兑换中心
            cm.dispose();
            cm.openNpc(9310073,0);
            break;
			
        case 9://装备制作
            cm.dispose();
            cm.openNpc(9900004, 505);
            break;
				
        case 10://爆率查询
            cm.dispose();
            cm.openNpc(9900004, "清理背包");
            break;
			
        case 11://清理背包
            cm.dispose();
            cm.openNpc(9900004, "怪物爆率");
            break;
			
		case 12://戒指升级
            cm.dispose();
            cm.openNpc(9000017,0);
			break;

		case 13://排名
            cm.dispose();
            //cm.openNpc(9900004, 7009);
			cm.openNpc(9040004);
			 break;
			 
		case 14://美容美发
            cm.dispose();
            cm.openNpc(9900004, 7);
            break;
			
		case 15://杂货商店
            cm.dispose();
            cm.openNpc(9900004, 7018);
            break;
			
        case 16://师徒系统
            cm.dispose();
            cm.openNpc(9900004, 1248);
            break;
			
	   
		case 17://加入QQ群
            cm.dispose();
            cm.openWeb("http://wpa.qq.com/msgrd?V=1&Uin=1342041396&Site");	
            break;

	    case 18:
            cm.dispose();
            cm.openNpc(9900004,11);
            break;
			
	    case 19:
            cm.dispose();
            cm.openNpc(9310097,0);
            break;
			
	    case 20:
            cm.dispose();
            cm.openNpc(9310072);
            break;
			
	    case 22://充值礼包
            cm.dispose();
            cm.openNpc(9900004,49);
            break;
			
	    case 23://完美抽奖
            cm.dispose();
            cm.openNpc(9900004,49);
            break;
			
        case 24:
            cm.dispose();
           cm.openShop(93);
            break;
			
	    case 25://道具删除
            cm.dispose();
            cm.openNpc(9310073,0);
            break;
			
	    case 26://装备回收
            cm.dispose();
            cm.openNpc(9050003,0);
            break;
			
	    case 27://BOSS战利品
            cm.dispose();
            cm.openNpc(9900004,300);
            break;
			
	    case 28://随时仓库
            cm.dispose();
            cm.openNpc(9030100,0);
            break;
			
////////////////以下为GM功能，普通玩家看不见////////////////////////////////////////////	
		
			case 101://怪物攻城
            cm.dispose();
            cm.openNpc(9900004,998);
            break;
			
			case 102://刷新地图
            cm.dispose();
            cm.刷新地图();
            break;
		
			case 103://刷新状态
            cm.dispose();
            cm.刷新状态();
            break;
			
			case 104://重载爆率
            cm.dispose();
            cm.重载爆率();
            break;
			
			case 105://重载传送点
            cm.dispose();
            cm.重载传送点();
            break;
			
			case 106://重载商店
            cm.dispose();
            cm.重载商店();
            break;
			
			case 107://满技能
            cm.dispose();
            cm.openNpc(9100200, 50);
            break;
			
			case 108://测试福利
            cm.dispose();
            cm.gainNX(999999);;
            cm.gainMeso(210000000);
            cm.sendOk("\r\n\r\n\t\t\t#e#r恭喜你获得了99999点卷!\r\n\r\n\t\t\t#e#r恭喜你获得了2E金币!");
            break;
		
			
			
		}
    }
}

