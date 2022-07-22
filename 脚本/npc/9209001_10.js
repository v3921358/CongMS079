


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
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //兔子粉
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";

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
        var selStr = "\t\t\t#e#v2550014#次数升级中心#v2550014##k#n\r\n";// 
		//selStr += "  #r当前时间:  "+tz+"" + cm.getHour() + "时:" + cm.getMin() + "分:" + cm.getSec() + "秒"+tz+"#l \r\n";//"+ cm.getChar().getName() +" 名字" + cm.getSec() + "秒.
		//selStr += "  #r您现有余额:#l  #b"+正方箭头+"点卷："+cm.getPlayer().getCSPoints(1)+" "+tz+"\r\t               "+正方箭头+"抵用券："+cm.getPlayer().getCSPoints(2)+""+tz+"\r\t               "+正方箭头+"累计充值："+cm.getPresent()+""+tz+"\r\t               "+正方箭头+"充值积分:"+cm.getmoneyb()+""+tz+"#k\r\n";
		selStr += ""+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+"\r\n"
        //selStr += "  #L1##b材料 兑换 #v2550014# #l\r\n\r\n";//出席图章兑换
		//selStr += "  #L2##b钻石强化 增加随机值#l\r\n\r\n";//red币兑换
		//selStr += "  #L3##r材料强化 增加砸卷次数#l\r\n\r\n";//枫叶兑换
		selStr += "#L4##r50%概率强化 增加砸卷次数 [每天5次]#k#n#l\r\n\r\n";
		selStr += "   需要材料:消耗5个#i4000463#"+金币+"金币：500万\r\n";


		selStr += ""+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+"\r\n"
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
            cm.warp(910000000);
            cm.dispose();
            break;
		case 1:
            cm.dispose();
            cm.openNpc(9310074,829);
            break;
        case 2:
            cm.dispose();
            cm.openNpc(9310074,821);
            break;
        case 3:
            cm.dispose();
            cm.openNpc(9310074,823);
	    //cm.openShop(103);
            break;
        case 4:
            cm.dispose();
            cm.openNpc(9310074,820);
            break;
        case 5:
            cm.dispose();
            cm.openNpc(1092015,1);
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

