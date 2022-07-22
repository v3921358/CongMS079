var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE);//获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
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
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
//var tz = "#fEffect/CharacterEff/1082565/4/0#";  //兔子粉
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
        var selStr = ""+ 大水滴 +"亲爱的欢迎来到童心冒险岛服务中心"+ 大水滴 +""+ 花草 +""+ 花草1 +"\r\n";
        selStr += " 点券：#r"+cm.getPlayer().getCSPoints(1)+"#d\t抵用券：#r"+cm.getPlayer().getCSPoints(2)+"#d\t充值积分:#r"+cm.getmoneyb()+"#k\r\n#e#d             【当前时间"+hour+":"+minute+":"+second+"】#k\r\n";
        selStr += "   #r#L0#"+ 桃花 +"点券商城#l#k #r#L1#"+ 桃花 +"游戏宝库#l#r#L4#"+ 桃花 +"双倍购买#l\r\n\r\n";
        selStr += "   #L3#"+ 桃花 +"皇家坐骑#l#k #r#L2#"+ 桃花 +"获取UFO/群宠/骑宠/锻造#l \r\n\r\n";
        //selStr += "   #b#L10# 装备制作#l#k #g#L8# 限时商城#l#k #L9# 点券兑换#l\r\n\r\n";
       // selStr += "   #L15# 材料兑换#l#k #L11# 箱子兑换#l #r#L12# 领取点券#l\r\n\r\n";
	//selStr += "   #b#L14# 充值网站#l#k #r#L13# 官方网站#l "; //#r#L2# 经验项链#l
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
            cm.dispose();
            cm.openNpc(9900004,6001);
            break;
		case 1:
            cm.dispose();
            cm.openNpc(1052013,0);

            //cm.openNpc(9900004,7);
            break;
        case 2:
            cm.dispose();
            cm.openNpc(9900004,6002);
            break;
        case 3:
            cm.dispose();
            cm.openNpc(9900004,6003);
	    //cm.openShop(103);
            break;
        case 4:
            cm.dispose();
            cm.openNpc(1052013,0);
            break;
        case 5:
            cm.dispose();
            cm.openNpc(9900004,6);
            break;
        case 6:
            cm.dispose();
            cm.openNpc(9900004,9);
            break;
        case 7:
            cm.dispose();
            cm.openNpc(9900004,8);
            break;
        case 8:
            cm.dispose();
           cm.openShop(6);
            break;
        case 9:
            cm.dispose();
            cm.openNpc(9310071,0);
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
            cm.openWeb("http://wpa.qq.com/msgrd?v=3&uin=450411012&site=qq&menu=yes");
			break;
	    case 12:
            cm.dispose();
            cm.openNpc(9900004,20);
			 break;
		case 13:
            cm.dispose();
            cm.openWeb("http://www.shikongmxd.top/");
			 break;
		case 15:
            cm.dispose();
            cm.openNpc(2020000,0);
            break;
		case 16:
            cm.dispose();
            cm.openNpc(9310054,0);
            break;
        case 17:
            cm.dispose();
            cm.openNpc(9000036,0);
            break;
		}
    }
}
