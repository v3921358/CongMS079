


var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE);//获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 爱心 = "#fEffect/CharacterEff/1112905/0/1#";
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //兔子粉
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 爱心 ="#fEffect/CharacterEff/1112905/0/1#";


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
    if (cm.getMapId() == 180000001) {
            cm.sendOk("很遗憾，您因为违反用户守则被禁止游戏活动，如有异议请联系管理员.");
            cm.dispose();
        }  
    else if (status == 0) {
        var selStr = "  #e#r欢迎来到#r " + cm.getChannelServer().getServerName() + " #k 活动中心!#n\r\n\r\n";// 
		//selStr += "  #r游戏  时间:  #b"+tz+"" + cm.getHour() + "时:" + cm.getMin() + "分:" + cm.getSec() + "秒."+tz+"#l \r\n";//"+ cm.getChar().getName() +" 名字" + cm.getSec() + "秒.
		//selStr += "  #r您现有余额:#l  #b"+正方箭头+"点卷："+cm.getPlayer().getCSPoints(1)+" "+tz+"\r\t               "+正方箭头+"抵用券："+cm.getPlayer().getCSPoints(2)+""+tz+"\r\t               "+正方箭头+"累计充值："+cm.getPresent()+""+tz+"\r\t               "+正方箭头+"充值积分:"+cm.getmoneyb()+""+tz+"#k\r\n";
		//selStr += ""+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+"\r\n"
		selStr+= ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";

		selStr += "\t#L1##e#r"+大水滴+"疯狂牛奶活动#d（金币 经验 抵用卷 点卷）#l\r\n";
		//selStr += "\t#L2##e#r"+大水滴+"疯狂盖楼活动#d（#z3992025# 抵用卷 点卷）#l\r\n";
		selStr += "\t#L3##e#r"+大水滴+"疯狂答题活动#d（卷轴 勋章 暗影币 国庆币）#l\r\n";
		//selStr += "#L4##e#r"+大水滴+"收集年卡活动#d（12月14日-12月21日）#l#k\r\n";
		//selStr += "#L5##e#r"+大水滴+"七天欢乐活动#d（12月14日-12月21日）#l#k\r\n";

		selStr+= "\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";

		//selStr += ""+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+"\r\n"
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
            cm.warp(910000000);
            cm.dispose();
            break;
		case 1:
            cm.dispose();
            cm.openNpc(9330079, 101);//挤奶活动
            break;
        case 2:
            cm.dispose();
            cm.openNpc(9330079, 102);//盖楼活动
            break;
        case 3:
            cm.dispose();
            cm.openNpc(9330079, 103);//答题活动
	    //cm.openShop(103);
            break;
        case 4:
            cm.dispose();
            cm.openNpc(9310058, 10);//收集年卡活动
            break;
        case 5:
            cm.dispose();
            cm.openNpc(9310058, 3);//开服七天乐
            break;
        case 6:
            cm.dispose();
            cm.openNpc(9120017,5);
            break;
        case 7:
            cm.dispose();
            cm.openNpc(9900004,200);
            break;
        case 8:
            cm.dispose();
           cm.openShop(6);
            break;
        case 9:
            cm.dispose();
            cm.openNpc(9900004,26);
            break;
        case 10:
            cm.dispose();
            cm.openNpc(9310060,0);
            break;
        case 11:
            cm.dispose();
            cm.openNpc(9000017,2);
            break;
		case 14:
            cm.dispose();
            cm.openWeb("http://wpa.qq.com/msgrd?V=1&Uin=241360103&Site");
			break;
	    case 12:
            cm.dispose();
            cm.openNpc(9900004,20);
			 break;
		case 13:
            cm.dispose();
            cm.openWeb("http://dwz.cn/4Mm3CI");
			 break;
		case 15:
            cm.dispose();
            cm.openNpc(2020000,0);
            break;
		case 16:
            cm.dispose();
            cm.openNpc(9900004,5);
            break;
        case 17:
            cm.dispose();
            cm.openNpc(9050003,0);
            break;
	    case 77:
            cm.dispose();
            cm.openNpc(9040004,0);
            break;
	    case 78:
            cm.dispose();
            cm.openNpc(9900004,9999);
            break;
		case 18:
            cm.dispose();
            cm.openNpc(9050000,0);
            break;
	    case 99:
            cm.dispose();
            cm.openNpc(9250010,0);
            break;
	    case 20:
            cm.dispose();
            cm.openNpc(9900004,11);
            break;
	    case 19:
            cm.dispose();
            cm.openNpc(9310097,0);
            break;
	    case 199:
            cm.dispose();
            cm.openNpc(9900004,1314);
            break;
	    case 21:
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
		}
    }
}

