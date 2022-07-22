var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:1848350048  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";

var 材料合成 = new Array(
{ 需求材料: 3600001,需求数量: 20,需求金币: 100000,合成材料: 4310034,需求出处介绍: "跑环可以获得跑环币"},
{ 需求材料: 3600001,需求数量: 30,需求金币: 100000,合成材料: 4310029,需求出处介绍: "跑环可以获得跑环币"},
{ 需求材料: 4170005,需求数量: 10,需求金币: 1000000,合成材料: 4310034,需求出处介绍: "玩具副本可以获得玩具蛋"},
{ 需求材料: 4170006,需求数量: 10,需求金币: 1000000,合成材料: 4310029,需求出处介绍: "天空副本可以获得天空蛋"},
{ 需求材料: 4310150,需求数量: 15,需求金币: 1000000,合成材料: 4310148,需求出处介绍: "每日打野任务可以获得纪念币"},
{ 需求材料: 4032391,需求数量: 100,需求金币: 1000000,合成材料: 2340000,需求出处介绍: "海盗,毒物副本可以获得"},
{ 需求材料: 4032392,需求数量: 100,需求金币: 1000000,合成材料: 2049118,需求出处介绍: "海盗,毒物副本可以获得"},
{ 需求材料: 4170002,需求数量: 30,需求金币: 1000000,合成材料: 1902001,需求出处介绍: "废弃副本可以获得！\r\n#r注：银色野猪一角色仅限一只，第二只不会给予#k"}
);
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
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

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
            
            var text = "";
			text += "                  #k"+皇冠白+" #r#e#w 材 料 兑 换 #n#k "+皇冠白+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
            
			for (var i = 0; i < 材料合成.length; i++) {
			text += "#L" + i + "#[#v"+材料合成[i].需求材料+"##r#c"+材料合成[i].需求材料+"##k/"+材料合成[i].需求数量+" + "+材料合成[i].需求金币+"金币]兑换#d#fUI/Basic/BtHide3/mouseOver/0##v"+材料合成[i].合成材料+"##l\r\n\r\n"+材料合成[i].需求出处介绍+"\r\n--------------------------------------------------\r\n";
			}
			cm.sendSimple(text);
}
	else if (selection == 0) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("背包其他栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[0].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[0].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[0].需求材料,材料合成[0].需求数量) ){
				cm.gainMeso(-材料合成[0].需求金币);
				cm.gainItem(材料合成[0].需求材料,-材料合成[0].需求数量);
				cm.gainItem(材料合成[0].合成材料,1);//星之币
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了正义币#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[0].需求材料+"#数量不足"+材料合成[0].需求数量+"。");
				cm.dispose();
			}
			}
		}
else if (selection == 1) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("背包其他栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[1].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[1].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[1].需求材料,材料合成[1].需求数量) ){
				cm.gainMeso(-材料合成[1].需求金币);
				cm.gainItem(材料合成[1].需求材料,-材料合成[1].需求数量);
				cm.gainItem(材料合成[1].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了十字币#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[1].需求材料+"#数量不足"+材料合成[1].需求数量+"。");
				cm.dispose();
			}
			}
		}		
else if (selection == 2) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("背包其他栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[2].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[2].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[2].需求材料,材料合成[2].需求数量) ){
				cm.gainMeso(-材料合成[2].需求金币);
				cm.gainItem(材料合成[2].需求材料,-材料合成[2].需求数量);
				cm.gainItem(材料合成[2].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了正义币#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[2].需求材料+"#数量不足"+材料合成[2].需求数量+"。");
				cm.dispose();
			}
			}
		}			
else if (selection == 3) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("背包其他栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[3].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[3].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[3].需求材料,材料合成[3].需求数量) ){
				cm.gainMeso(-材料合成[3].需求金币);
				cm.gainItem(材料合成[3].需求材料,-材料合成[3].需求数量);
				cm.gainItem(材料合成[3].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了十字币#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[3].需求材料+"#数量不足"+材料合成[3].需求数量+"。");
				cm.dispose();
			}
			}
		}
else if (selection == 4) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("背包其他栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[4].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[4].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[4].需求材料,材料合成[4].需求数量) ){
				cm.gainMeso(-材料合成[4].需求金币);
				cm.gainItem(材料合成[4].需求材料,-材料合成[4].需求数量);
				cm.gainItem(材料合成[4].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了星之大陆钱币#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[4].需求材料+"#数量不足"+材料合成[4].需求数量+"。");
				cm.dispose();
			}
			}
		}
else if (selection == 5) {
			if (!cm.checkNumSpace(2, 2)) {
			cm.sendOk("背包消耗栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[5].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[5].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[5].需求材料,材料合成[5].需求数量) ){
				cm.gainMeso(-材料合成[5].需求金币);
				cm.gainItem(材料合成[5].需求材料,-材料合成[5].需求数量);
				cm.gainItem(材料合成[5].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了祝福卷轴#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[5].需求材料+"#数量不足"+材料合成[5].需求数量+"。");
				cm.dispose();
			}
			}
		}
else if (selection == 6) {
			if (!cm.checkNumSpace(2, 2)) {
			cm.sendOk("背包消耗栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[6].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[6].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(材料合成[6].需求材料,材料合成[6].需求数量) ){
				cm.gainMeso(-材料合成[6].需求金币);
				cm.gainItem(材料合成[6].需求材料,-材料合成[6].需求数量);
				cm.gainItem(材料合成[6].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了混沌卷轴#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[6].需求材料+"#数量不足"+材料合成[6].需求数量+"。");
				cm.dispose();
			}
			}
		}
else if (selection == 7) {
			if (!cm.checkNumSpace(1, 2)) {
			cm.sendOk("背包装备栏空间不足2格");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < 材料合成[7].需求金币) {
            cm.sendOk("抱歉您的金币不足"+材料合成[7].需求金币+"，请凑足了再来！");
            cm.dispose();
			return;
			}
			if(cm.haveItem(材料合成[7].合成材料,1) ){
			cm.sendOk("抱歉您已经有了银色野猪！");
            cm.dispose();
			return;
			}
			else{
			
			if(cm.haveItem(材料合成[7].需求材料,材料合成[7].需求数量) ){
				cm.gainMeso(-材料合成[7].需求金币);
				cm.gainItem(材料合成[7].需求材料,-材料合成[7].需求数量);
				cm.gainItem(材料合成[7].合成材料,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『材料兑换』" + " : " + "[" + cm.getChar().getName() + "]通过兑换材料，获得了银色野猪#！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+材料合成[7].需求材料+"#数量不足"+材料合成[7].需求数量+"。");
				cm.dispose();
			}
			}
		}



    }
}