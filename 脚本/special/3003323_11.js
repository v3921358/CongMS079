var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
	
	if(!cm.getPlayer().isGM()){
			//cm.dispose();
			//return ;
	}
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢您的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "\t枫叶中心。 \r\n\t\r\n"
			//text += "#L1##v4001126##t4001126# X 300 兑换 #v5211047#三小时双倍经验卡\r\n"
			//text += "#L2#400点卷 兑换 #v5360014#三小时双倍爆率卡\r\n"
			text += "#L3##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1302030##t1302030# X 1 #v4005000##t4005000# X5兑换 #v1402039##z1402039#\r\n"
			text += "#L4##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1442024##t1442024# X 1 #v4005000##t4005000# X5兑换 #v1442046##z1442046#\r\n"
			text += "#L5##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1432012##t1432012# X 1 #v4005000##t4005000# X5兑换 #v1432040##z1432040#\r\n"
			text += "#L6##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1452022##t1452022# X 1 #v4005002##t4005002# X5兑换 #v1452045##z1452045#\r\n"
			text += "#L9##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1462019##t1462019# X 1 #v4005002##t4005002# X5兑换 #v1462040##z1462040#\r\n"
			text += "#L10##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1382012##t1382012# X 1 #v4005001##t4005001# X5兑换 #v1382039##z1382039#\r\n"
			text += "#L20##v4001126##t4001126# X 1500   #v4005002##t4005002# X 7   #v1492021##t1492021# X 1 #v4005004##t4005004 # X7兑换 #v1492073##z1492073#\r\n"
			text += "#L21##v4001126##t4001126# X 1500   #v4005000##t4005000# X 7   #v1482021##t1482021# X 1 #v4005004##t4005004 # X7兑换 #v1482073##z1482073#\r\n"
	
			text += "#L7##v4001126##t4001126# X 2000   #v4005004##t4005004# X 35   #v1092030##t1092030# X 1 #v4005001##t4005001# X35兑换 #v1092045##z1092045#（8魔法力）\r\n"
			text += "#L8##v4001126##t4001126# X 1000   #v4005003##t4005003# X 10   #v1092030##t1092030# X 1 #v4005000##t4005000# X10兑换 #v1092047##z1092047#（5攻击力）\r\n"
			text += "#L11##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1332025##t1332025# X 1 #v4005003##t4005003# X5兑换 #v1332056##z1332056#\r\n"
			text += "#L12##v4001126##t4001126# X 1000   #v4005004##t4005004# X 5   #v1472032##t1472032# X 1 #v4005003##t4005003# X5兑换 #v1472055##z1472055#\r\n"
			text += "#L13##v4001126##t4001126# X 2000   #v4005004##t4005004# X 35   #v1092030##t1092030# X 1 #v4005000##t4005000# X35兑换 #v1092046##z1092046#（8攻击力）\r\n"
			text += "#L14##v4001126##t4001126# X 1500   #v4005003##t4005003# X 30   #v1092047##t1092047# X 1 #v4005004##t4005004# X30兑换 #v1092050##z1092050#（10攻击力）\r\n"
			text += "#L15##v4001126##t4001126# X 2500   #v4005004##t4005004# X 60   #v1092050##t1092050# X 1 #v4005003##t4005003# X60兑换 #v1092049##z1092049#（20攻击力）\r\n"
            cm.sendSimple(text);
        } else if (selection == 1) {
			if(cm.haveItem(4001126,300)){
				cm.gainItem(4001126,-300);
				cm.gainItem(5211047,1,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 2) {
			if(cm.getPlayer().getCSPoints(1) >= 400){
				cm.gainNX(-400);
				cm.gainItem(5360014,1,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 3) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1302030,1) && cm.haveItem(4005000,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1302030,-1);
				cm.gainItem(4005000,-5);
				cm.gainItem(1402039,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 4) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1442024,1) && cm.haveItem(4005000,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1442024,-1);
				cm.gainItem(4005000,-5);
				cm.gainItem(1442046,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 5) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1432012,1) && cm.haveItem(4005000,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1432012,-1);
				cm.gainItem(4005000,-5);
				cm.gainItem(1432040,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 6) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1452022,1) && cm.haveItem(4005002,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1452022,-1);
				cm.gainItem(4005002,-5);
				cm.gainItem(1452045,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 7) {
			if((cm.haveItem(4001126,2000) && cm.haveItem(4005004,35) && cm.haveItem(1092030,1) && cm.haveItem(4005001,35))){
				cm.gainItem(4001126,-2000);
				cm.gainItem(4005004,-35);
				cm.gainItem(1092030,-1);
				cm.gainItem(4005001,-35);
				cm.gainItem(1092045,0,0,0,2,0,8);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 8) {
			if((cm.haveItem(4001126,1000) && cm.haveItem(4005003,10) && cm.haveItem(1092030,1) && cm.haveItem(4005000,10))){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005003,-10);
				cm.gainItem(1092030,-1);
				cm.gainItem(4005000,-10);
				cm.gainItem(1092047,0,0,0,0,5,0);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 9) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1462019,1) && cm.haveItem(4005002,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1462019,-1);
				cm.gainItem(4005002,-5);
				cm.gainItem(1462040,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 10) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005001,5) && cm.haveItem(1382012,1) && cm.haveItem(4005000,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005001,-5);
				cm.gainItem(1382012,-1);
				cm.gainItem(4005000,-5);
				cm.gainItem(1382039,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 11) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1332025,1) && cm.haveItem(4005003,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1332025,-1);
				cm.gainItem(4005003,-5);
				cm.gainItem(1332056,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 12) {
			if(cm.haveItem(4001126,1000) && cm.haveItem(4005004,5) && cm.haveItem(1472032,1) && cm.haveItem(4005003,5)){
				cm.gainItem(4001126,-1000);
				cm.gainItem(4005004,-5);
				cm.gainItem(1472032,-1);
				cm.gainItem(4005003,-5);
				cm.gainItem(1472055,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 13) {
			if((cm.haveItem(4001126,2000) && cm.haveItem(4005004,35) && cm.haveItem(1092030,1) && cm.haveItem(4005000,35))){
				cm.gainItem(4001126,-2000);
				cm.gainItem(4005004,-35);
				cm.gainItem(1092030,-1);
				cm.gainItem(4005000,-35);
				cm.gainItem(1092046,0,0,0,0,8,0);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 14) {
			if(cm.haveItem(4001126,1500) && cm.haveItem(4005003,30) && cm.haveItem(1092047,1) && cm.haveItem(4005004,30)){
				cm.gainItem(4001126,-1500);
				cm.gainItem(4005003,-30);
				cm.gainItem(1092047,-1);
				cm.gainItem(4005004,-30);
				cm.gainItem(1092050,0,0,0,0,10,0);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 15) {
			if((cm.haveItem(4001126,2500) && cm.haveItem(4005004,60) && cm.haveItem(1092050,1) && cm.haveItem(4005003,60))){
				cm.gainItem(4001126,-2500);
				cm.gainItem(4005004,-60);
				cm.gainItem(1092050,-1);
				cm.gainItem(4005003,-60);
				cm.gainItem(1092049,0,0,0,0,20,0);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
        } else if (selection == 20) {
			if(cm.haveItem(4001126,1500) && cm.haveItem(4005002 ,7) && cm.haveItem(1492021,1) && cm.haveItem(4005004 ,7)){
				cm.gainItem(4001126,-1500);
				cm.gainItem(4005002,-7);
				cm.gainItem(1492021,-1);
				cm.gainItem(4005004,-7);
				cm.gainItem(1492073,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
		}else if (selection == 21) {
			if(cm.haveItem(4001126,1500) && cm.haveItem(4005000  ,7) && cm.haveItem(1482021,1) && cm.haveItem(4005004 ,7)){
				cm.gainItem(4001126,-1500);
				cm.gainItem(4005000,-7);
				cm.gainItem(1482021,-1);
				cm.gainItem(4005004,-7);
				cm.gainItem(1482073,1);
				cm.sendOk("兑换成功");
            cm.dispose();
			}else{
				cm.sendOk("条件不足");
            cm.dispose();
			}
		}
    }
}


