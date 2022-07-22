


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
        var selStr = "\t\t   #e#v2550014#装备强化次数升级中心#v2550014##k#n\r\n";// 
		selStr += ""+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+"\r\n"
		
		selStr +="#L1#[#v2049401#]#e#r强化+1#k#l #L2#[#v2049401#]#e#r强化+2#k#l #L3#[#v2049401#]#e#r强化+3#k#l\r\n\r\n";


		selStr += ""+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+爱心+"\r\n"
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        
		case 1:
            cm.dispose();
            cm.openNpc(3004903,"砸卷次数");
            break;
		case 2:
            cm.dispose();
            cm.openNpc(3004903,"砸卷次数2");
            break;
		case 3:
            cm.dispose();
            cm.openNpc(3004903,"砸卷次数3");
            break;
        
		}
    }
}

