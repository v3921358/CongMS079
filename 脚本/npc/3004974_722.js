/* ==================
 脚本类型: 跑商
 版权：金华枫叶团队     
 联系扣扣：1848350048
 =====================
 */
var status = 0;
var 黑水晶 = 4021008;
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 忠告 = "#k温馨提示：任何非法程序和外挂封号处理.封杀侥幸心理.";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
			
            var txt1 = "             #L1##v4001230#[#r#c4001230##k#d/10]兑换#i4310150##k\r\n\r\n";
			var txt2 = "#L2##v4001228#[#r#c4001228##k#d/8]兑换#i4310150##k    #L3##v4001227#[#r#c4001227##k#d/6]兑换#i4310150##k\r\n\r\n";
            var txt3 = "#L4##v4001226#[#r#c4001226##k#d/5]兑换#i4310150##k    #L5##v4001229#[#r#c4001229##k#d/3]兑换#i4310150##k\r\n\r\n";
			var txt4 = "#L6#" + 红色箭头 + "殴打红蜗牛王                #L7#" + 蓝色箭头 + "殴打大树妖王#k\r\n\r\n";
			var txt5 = "#L8#" + 红色箭头 + "殴打巨居蟹王                #L9#" + 蓝色箭头 + "殴打肯德熊王#k\r\n\r\n";
			var txt6 = "#L10#" + 红色箭头 + "殴打艾利杰王                #L11#" + 蓝色箭头 + "殴打妖怪禅师#k\r\n\r\n";
			var txt7 = "#L12#" + 红色箭头 + "殴打九尾狐王                #L13#" + 蓝色箭头 + "殴打小鳄鱼王#k\r\n\r\n";
			var txt8 = "#L14#" + 红色箭头 + "殴打浮士德王                #L15#" + 蓝色箭头 + "殴打小提莫王#k\r\n\r\n";
			var txt9 = "#L16#" + 红色箭头 + "殴打大海兽王                #L17#" + 蓝色箭头 + "殴打大宇怪王#k\r\n\r\n";
			var txt10 = "#L18#" + 红色箭头 + "殴打吉米拉王                #L19#" + 蓝色箭头 + "殴打歇尔夫王#k\r\n\r\n";
			var txt11 = "#L20#" + 红色箭头 + "殴打火焰龙王                #L21#" + 蓝色箭头 + "殴打天鹰怪王#k\r\n\r\n";
			var txt12 = "#L22#" + 红色箭头 + "殴打巨居蟹王                #L23#" + 蓝色箭头 + "殴打肯德熊王#k\r\n\r\n";
            cm.sendSimple("\t\t"+彩虹+"  #e#d 每 日 打 野 #k#n  #r  "+彩虹+"#b#k#n\r\r\n" + txt1 + "" + txt2 + "" + txt3 + "" + txt4 + "" + txt5 + "" + txt6 + "" + txt7 + "" + txt8 + "" + txt9 + "" + txt10 + "" + txt11 + "");

        } else if (status == 1) {
            if (selection == 1) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001230)>=10){
			cm.gainItem(4001230,-10);
			cm.gainItem(4310150,1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有10个#v4001230#无法兑换！");
			cm.dispose();
			}
            }
			
			else if (selection == 21) { 
			if(cm.getMeso() >= 30000){
			cm.gainMeso(-30000);
			cm.warp(240020402);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足3万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 20) { 
			if(cm.getMeso() >= 30000){
			cm.gainMeso(-30000);
			cm.warp(240020402);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足3万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 19) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(230020100);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足2万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 18) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(261030000);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足2万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 17) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(260010201);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足2万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 16) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(240040401);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足2万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			
			else if (selection == 15) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(220050100);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足2万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 14) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(100040106);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 13) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(107000300);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 12) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(222010310);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 11) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(250010503);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 10) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(200010300);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 9) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(250010304);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 8) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(110040000);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 7) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(101030404);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			else if (selection == 6) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(104000400);
            cm.dispose();
			}else{
			cm.sendOk("抱歉，您金币不足1万，请徒步过去吧！");
			cm.dispose();
			}
            }
			
			
			else if (selection == 5) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001229)>=3){
			cm.gainItem(4001229,-3);
			cm.gainItem(4310150,1);
            cm.sendOk("兑换成功！");
            cm.dispose();
			}else{
			cm.sendOk("你没有3个#v4001229#无法兑换！");
			cm.dispose();
			}
            }
			
			else if (selection == 4) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001226)>=5){
			cm.gainItem(4001226,-5);
			cm.gainItem(4310150,1);
            cm.sendOk("兑换成功！");
            cm.dispose();
			}else{
			cm.sendOk("你没有5个#v4001226#无法兑换！");
			cm.dispose();
			}
            }
			
			else if (selection == 3) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001227)>=6){
			cm.gainItem(4001227,-6);
			cm.gainItem(4310150,1);
            cm.sendOk("兑换成功！");
            cm.dispose();
			}else{
			cm.sendOk("你没有6个#v4001227#无法兑换！");
			cm.dispose();
			}
            }

			else if (selection == 2) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001228)>=8){
			cm.gainItem(4001228,-8);
			cm.gainItem(4310150,1);
            cm.sendOk("兑换成功！");
            cm.dispose();
			}else{
			cm.sendOk("你没有8个#v4001228#无法兑换！");
			cm.dispose();
			}
            }


        }
    }
}
