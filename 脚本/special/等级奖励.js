var 红心= "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#"; 
var 爱心 = "#fEffect/CharacterEff/1050334/0/1#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
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
			if(cm.getJob() >= 0 && cm.getJob()<= 522 && cm.hasSkill(1017) == false){
			cm.teachSkill(1017,1,1);
			}else if(cm.getJob() >=1000 || cm.getJob() <= 2112 && cm.hasSkill(20001019) == false){
			cm.teachSkill(20001019,1,1);
			}
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }

           text += "您好，尊敬的#b#h ##k#b 我是等级成长礼包领取NPC#k\r\n#r[本服为2倍基础经验，萌新务必注意：请绿色游戏 切勿作弊 抓到一视同仁！\r\n"//3
           //text += " \t   #d赞助积分：#r"+cm.getzb()+"#k#n#d\t\t点卷余额：#b" + cm.getPlayer().getCSPoints(1) + "#k#n\t#d\r\n"
           text += " #b务必注意：领取礼包前请先整理背包空间，至少每个栏位保留8个空位，否则后果自负#e\r\n"
	
           text += "     #L1#" + 红心 + " #r10级 成长礼包#l\r\n"//3
           text += "     #L2#" + 红心 + " #r30级 成长礼包#l\r\n"//3
           text += "     #L3#" + 红心 + " #r70级 成长礼包#l\r\n"//3
           text += "     #L4#" + 红心 + " #r100级 成长礼包#l\r\n"//3
           text += "     #L5#" + 红心 + " #r110级 成长礼包#l\r\n"//3
           text += "     #L6#" + 红心 + " #r120级 成长礼包#l\r\n"//3
           text += "     #L7#" + 红心 + " #r130级 成长礼包#l\r\n"//3
           text += "     #L8#" + 红心 + " #r140级 成长礼包#l\r\n"//3
           text += "     #L9#" + 红心 + " #r150级 成长礼包#l\r\n"//3
           text += "     #L10#" + 红心 + " #r160级 成长礼包#l\r\n"//3
           text += "     #L11#" + 红心 + " #r170级 成长礼包#l\r\n"//3
           text += "     #L12#" + 红心 + " #r180级 成长礼包#l\r\n"//3
           text += "     #L13#" + 红心 + " #r190级 成长礼包#l\r\n"//3
           text += "     #L14#" + 红心 + " #r200级 成长礼包#l\r\n"//3
           text += "     #L15#" + 红心 + " #r210级 成长礼包#l\r\n"//3
           text += "     #L16#" + 红心 + " #r220级 成长礼包#l\r\n"//3
           text += "     #L17#" + 红心 + " #r230级 成长礼包#l\r\n"//3
           text += "     #L18#" + 红心 + " #r240级 成长礼包#l\r\n"//3
           text += "     #L19#" + 红心 + " #r250级 成长礼包#l\r\n"//3


		    cm.sendSimple(text);
        } else if (selection == 0) {//自由市场
            cm.warp(910000000);
            cm.dispose();

        } else if (selection == 100) {//
            cm.dispose();
          cm.openNpc(9310071, 0);
        } else if (selection == 1004) {//
            cm.gainNX(999999);;
            cm.gainMeso(210000000);
              cm.sendOk("\r\n\r\n\t\t\t#e#r恭喜你获得了99999点卷!\r\n\r\n\t\t\t#e#r恭喜你获得了2E金币!");
            cm.dispose();


        } else if (selection == 1) {//10级成长礼包
	if (cm.getPlayerStat("LVL") >= 10  && cm.getPlayer().getOneTimeLog("成长礼包1") <1){
		cm.getPlayer().setOneTimeLog("成长礼包1");//给永久记录
		cm.gainItem(4001126, 100);//500个枫叶
		cm.gainMeso(300000 );//给与金币
		cm.gainItem(2000005, 30);//超级药水
		cm.gainDY(1000)//抵用1000点
		//cm.gainItem(1142803,5,5,5,5,50,50,5,5,0,0,0,0,0,0);
		cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到10级，领取了10级萌新成长礼包！");
		cm.sendOk("领取成功！");
		cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够10级或者请留出背包空间");
            cm.dispose();
		}
        } else if (selection == 2) {//30级成长礼包
	if(cm.getPlayerStat("LVL") >= 30 && cm.getPlayer().getOneTimeLog("成长礼包2") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包2");//给永久记录
			cm.gainItem(4001126, 200);//500个枫叶
			cm.gainMeso(500000 );//给与金币
            cm.gainItem(2000005, 50);//超级药水
			cm.gainDY(3000)//抵用3000点
	//cm.gainItem(1012011,3,3,3,3,0,0,3,3,0,0,0,0,0,0);//圣诞鼻子
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到30级，领取了30级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够30级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 3) {//70级成长礼包
	if(cm.getPlayerStat("LVL")>= 70 && cm.getPlayer().getOneTimeLog("成长礼包3") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包3");//给永久记录
			cm.gainMeso(1000000 );//给与金币
            cm.gainItem(2000005, 100);//超级药水
			cm.gainDY(5000)//抵用3000点
			cm.gainItem(4001126, 200);//500个枫叶
            //cm.gainItem(1003529, 1);//紫金枫叶帽子
            //cm.gainItem(1052457, 1);//紫金枫叶套服
            //cm.gainItem(1102394, 1);//紫金枫叶披风
            //cm.gainItem(1082430, 1);//紫金枫叶手套
            //cm.gainItem(1072660, 1);//紫金枫叶鞋子
            //cm.gainItem(2022618, 1);//紫金枫叶武器自选
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到70级，领取了70级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够70级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 4) {//100级成长礼包
	if(cm.getPlayerStat("LVL") >= 100 && cm.getPlayer().getOneTimeLog("成长礼包4") < 1){
	cm.getPlayer().setOneTimeLog("成长礼包4");//给永久记录
            cm.gainItem(1003946, 1);//革命帽子
            cm.gainItem(1052647, 1);//革命套服
            cm.gainItem(1102612, 1);//革命披风
            cm.gainItem(1082540, 1);//革命手套
            cm.gainItem(1072853, 1);//革命鞋子
            //cm.gainItem(2022613, 1);//革命武器自选
			cm.gainItem(4001126, 200);//500个枫叶
			cm.gainMeso(3000000 );//给与金币
            cm.gainItem(2000005, 100);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(5000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到100级，领取了100级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够100级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 5) {//110级成长礼包
	if(cm.getPlayerStat("LVL") >= 110 && cm.getPlayer().getOneTimeLog("成长礼包5") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包5");//给永久记录
			cm.gainItem(4001126, 200);//500个枫叶
			cm.gainMeso(5000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(5000);
  	//cm.gainItem(1113091,6,6,6,6,200,200,6,6,0,0,0,0,0,0);//飞奔截止
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到110级，领取了110级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够110级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 6) {//120级成长礼包
	if(cm.getPlayerStat("LVL") >= 120 && cm.getPlayer().getOneTimeLog("成长礼包6") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包6");//给永久记录
			cm.gainItem(4001126, 200);//500个枫叶
			cm.gainMeso(5000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(5000);
	//cm.gainItem(1012011,5,5,5,5,0,0,5,5,0,0,0,0,0,0);//圣诞鼻子
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到120级，领取了120级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够120级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 7) {//130级成长礼包
	if(cm.getPlayerStat("LVL") >= 130 && cm.getPlayer().getOneTimeLog("成长礼包7") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包7");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(5000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(5000);

			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到130级，领取了130级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够130级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 8) {//140级成长礼包
	if(cm.getPlayerStat("LVL") >= 140 && cm.getPlayer().getOneTimeLog("成长礼包8") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包8");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(5000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(5000);

			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到140级，领取了140级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够140级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 9) {//150级成长礼包
	if(cm.getPlayerStat("LVL") >= 150 && cm.getPlayer().getOneTimeLog("成长礼包9") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包9");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(10000);
  		//cm.gainItem(1142650,15,15,15,15,200,200,10,10,0,0,0,0,0,0);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到150级，领取了150级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够150级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 10) {//160级成长礼包
	if(cm.getPlayerStat("LVL") >= 160 && cm.getPlayer().getOneTimeLog("成长礼包10") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包10");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(10000)//抵用3000点
            cm.gainNX(10000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到160级，领取了160级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够160级或者请留出背包空间");
            cm.dispose();
}


        } else if (selection == 11) {//170级成长礼包
	if(cm.getPlayerStat("LVL") >= 170 && cm.getPlayer().getOneTimeLog("成长礼包11") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包11");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(20000)//抵用3000点
            cm.gainNX(20000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到170级，领取了170级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够170级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 12) {//180级成长礼包
	if(cm.getPlayerStat("LVL") >= 180 && cm.getPlayer().getOneTimeLog("成长礼包12") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包12");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(20000)//抵用3000点
            cm.gainNX(20000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到180级，领取了180级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够180级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 13) {//190级成长礼包
	if(cm.getPlayerStat("LVL") >= 190 && cm.getPlayer().getOneTimeLog("成长礼包13") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包13");//给永久记录
			cm.gainItem(4001126, 300);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(20000)//抵用3000点
            cm.gainNX(20000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到190级，领取了190级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够190级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 14) {//200级成长礼包
	if(cm.getPlayerStat("LVL") >= 200 && cm.getPlayer().getOneTimeLog("成长礼包14") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包14");//给永久记录
			cm.gainItem(4001126, 500);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(30000)//抵用3000点
            cm.gainNX(30000);
  	//cm.gainItem(1142472,20,20,20,20,400,400,15,15,0,0,0,0,0,0);
             //cm.gainItem(1142111,10,10,10,10,50,50,10,10,10,10,10,10,10,10);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到200级，领取了200级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够200级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 15) {//210级成长礼包
	if(cm.getPlayerStat("LVL") >= 210 && cm.getPlayer().getOneTimeLog("成长礼包15") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包15");//给永久记录
			cm.gainItem(4001126, 500);//500个枫叶
			cm.gainMeso(20000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(30000)//抵用3000点
            cm.gainNX(30000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到210级，领取了210级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够210级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 16) {//220级成长礼包
	if(cm.getPlayerStat("LVL") >= 200 && cm.getPlayer().getOneTimeLog("成长礼包16") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包16");//给永久记录
			cm.gainItem(4001126, 500);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(30000)//抵用3000点
            cm.gainNX(30000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到220级，领取了220级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够220级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 17) {//230级成长礼包
	if(cm.getPlayerStat("LVL") >= 230 && cm.getPlayer().getOneTimeLog("成长礼包17") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包17");//给永久记录
			cm.gainItem(4001126, 1000);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(30000)//抵用3000点
            cm.gainNX(30000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到230级，领取了230级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够230级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 18) {//240级成长礼包
	if(cm.getPlayerStat("LVL") >= 240 && cm.getPlayer().getOneTimeLog("成长礼包18") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包18");//给永久记录
			cm.gainItem(4001126, 1000);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(30000)//抵用3000点
            cm.gainNX(30000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到240级，领取了240级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够240级或者请留出背包空间");
            cm.dispose();
}
        } else if (selection == 19) {//250级成长礼包
	if(cm.getPlayerStat("LVL") >= 250 && cm.getPlayer().getOneTimeLog("成长礼包19") < 1){
			cm.getPlayer().setOneTimeLog("成长礼包19");//给永久记录
			cm.gainItem(4001126, 1000);//500个枫叶
			cm.gainMeso(10000000 );//给与金币
            cm.gainItem(2000005, 200);//超级药水
			cm.gainDY(100000)//抵用3000点
            cm.gainNX(100000);
			cm.worldMessage(6,"【萌新成长礼包】恭喜玩家：["+cm.getName()+"] 达到250级，领取了250级萌新成长礼包！");
            cm.sendOk("领取成功！");
            cm.dispose();
		}else{
            cm.sendOk("你已经领取过了！\r\n您的等级不够250级或者请留出背包空间");
            cm.dispose();
}

//============================================
		}


		}
    }
//}


