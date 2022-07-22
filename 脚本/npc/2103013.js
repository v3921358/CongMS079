var status;
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/15#";
var 彩虹1 ="#fUI/ChatBalloon/122/n#";
var 彩虹上1 =  "#fUI/ChatBalloon/122/ne#";
var 彩虹上2 =  "#fUI/ChatBalloon/122/nw#";
var 彩1 =    "#fUI/ChatBalloon/122/e#";
var 彩2 =    "#fUI/ChatBalloon/122/w#";
var 获得 = "#fUI/UIWindow.img/QuestIcon/4/0#";  
var 彩虹下 ="#fUI/ChatBalloon/122/s#";
var 彩虹下1 ="#fUI/ChatBalloon/122/se#";
var 彩虹下2 ="#fUI/ChatBalloon/122/sw#";
var 彩虹中 ="#fUI/ChatBalloon/122/head#";
var 星星 = "#fEffect/CharacterEff/1051294/1/0#"; //旋转星星1
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var typed = -1;
var fbmc = "金字魔塔副本";//副本名称
var minLevel = 140;//最低等级
var maxLevel = 250;//最高等级
var minPartySize = 3;//最低人数
var maxPartySize = 6;//最高人数
var cishuxianzhi = 1;//限制次数
var maxjinbi = 5000000;//判断征集令金币
importPackage(java.lang);
importPackage(Packages.tools);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools.packet);
importPackage(java.lang)
importPackage(java.util);
importPackage(Packages.tools);
importPackage(Packages.server.quest);
importPackage(Packages.client);
importPackage(Packages.scripting);
importPackage(Packages.handling.channel);
importPackage(Packages.handling);
importPackage(Packages.handling.word);
var 初级奖励 = [
[4001128, "*随机1-19999桶 概率:"+100],//卡帕莱特珠子 朱丽叶的珠子
	[4000463, 20],//国庆币
	[4032393, 15],//碎片
	[4032394, 15],//碎片
	[4032395, 15],//碎片
	[4032396, 15],//碎片
	[4032397, 15],////碎片
	[2340000, 10],//祝福卷轴
	[2049147, 5],//混沌卷轴
	[4001017, 10],//火焰的眼
	[4310054, 10],//鉴定币
	[4310020, 10],//鉴定币
	[4310021, 10],//鉴定币
	[5061002, 5],//A锁
	[5061003, 5],//S锁
	[4310034, 5],//正义币
	[4310029, 5],//十字币
	[1112952, 1]//希拉的愤怒
];

var 中级奖励 = [
[4001128, "*随机1-19999桶 概率:"+100],//卡帕莱特珠子 朱丽叶的珠子
	[4000463, 30],//国庆币
	[4032393, 30],//碎片
	[4032394, 30],//碎片
	[4032395, 30],//碎片
	[4032396, 30],//碎片
	[4032397, 30],////碎片
	[2340000, 20],//祝福卷轴
	[2049147, 20],//混沌卷轴
	[4001017, 20],//火焰的眼
	[4310054, 20],//鉴定币
	[4310020, 20],//鉴定币
	[4310021, 20],//鉴定币
	[5061002, 10],//A锁
	[5061003, 10],//S锁
	[4310034, 10],//正义币
	[4310029, 10],//十字币
	[1112952, 2]//希拉的愤怒
];
var 终极奖励 = [
	[4001128, "*随机1-19999桶 概率:"+100],//卡帕莱特珠子 朱丽叶的珠子
	[4000463, 40],//国庆币
	[4032393, 60],//碎片
	[4032394, 60],//碎片
	[4032395, 60],//碎片
	[4032396, 60],//碎片
	[4032397, 60],////碎片
	[2340000, 40],//祝福卷轴
	[2049147, 40],//混沌卷轴
	[4001017, 40],//火焰的眼
	[4310054, 40],//鉴定币
	[4310020, 40],//鉴定币
	[4310021, 40],//鉴定币
	[5061002, 20],//A锁
	[5061003, 20],//S锁
	[4310034, 20],//正义币
	[4310029, 20],//十字币
	[1112952, 5]//希拉的愤怒
];


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
        status++;
    } else 
        if (status == 1) {
		    cm.sendOk("	看起来你现在还没考虑好。\r\n	如果你考虑清楚了，那么请再来找我!");
            cm.dispose();
        status--;
    }
    if (status == 0) {
        if (cm.getPlayer().getMapId() == 926010000) {//判断是否在副本地图入口，如果是在地图入口的位置就显示以下          
		  var tex2 = "";
            var text = "";
			var 文本信息 = "";
			var 文本信息2 = "";
			var 文本信息3 = "";
            for (i = 0; i < 18; i++) {
				//for (w = 0; w < 10; w++) {
                text += "";
				文本信息 += "   " + cm.显示物品(初级奖励[i][0]) + "  " + 初级奖励[i][1] + " % #k\r\n";
				文本信息2 += "   " + cm.显示物品(中级奖励[i][0]) + "  " + 中级奖励[i][1] + " % #k\r\n";
				文本信息3 += "   " + cm.显示物品(终极奖励[i][0]) + "  " + 终极奖励[i][1] + " % #k\r\n";
            }
			
			
			text +=""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+"\r\n\r\n";
			text += "#k\t\t\t欢迎来到#r" + fbmc + "#k\r\n"
			text += "#k①人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k 队员\t②等级限制：#r " + minLevel + " #b- #r" + maxLevel + "级 #k\r\n"
			text += "#k今日挑战次数：#b"+ cm.getPlayer().getBossLog("金字魔塔副本") +" / "+ cishuxianzhi +"#k次\r\n"
			text += "#k\t\t\t#r点券奖励：1200*关卡层数 #k\r\n"
			text += "#k\t\t\t#r抵用奖励：500*关卡层数 #k\r\n"
			text += "#k\t\t\t#r经验奖励：5000*关卡层数 #k\r\n"
			text += "#k\t\t\t#r金字魔塔副本奖池预览: #k\r\n"
			text += "#k#r挑战10层成功可概率获得:#k\r\n"+文本信息+"#k\r\n"
			text += "#k#r挑战20层成功可概率获得:#k\r\n"+文本信息2+"#k\r\n"
			text += "#k#r挑战30层成功可概率获得:#k\r\n"+文本信息3+"#k\r\n"
			//text += "#k#r点券10000抵用5000#k\r\n"
			text += "  #L1000##r"+星星+"挑战金字魔塔副本"+星星+"#k\r\n" //#L2##r副本征集令：#k" + maxjinbi + "金币/次#l
			text +=""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+"\r\n\r\n";
			cm.sendSimple(text); 
			} else if (cm.getPlayer().getMapId() == 926010001) {
				cm.dispose();
				cm.openNpc(2103013,"金字魔塔副本"); 
				
				} else if (cm.getPlayer().getMapId() == 926020001){
			var text = "";	
            text += "#k\t\t\t重返金字魔塔副本消耗金币:#r3000万#k\r\n"			
            text += "#L2000##r"+星星+"重返金字魔塔副本"+星星+"#k\r\n" //#L2##r副本征集令：#k" + maxjinbi + "金币/次#l
			text +=""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+""+邪恶小兔+"\r\n\r\n";

				cm.sendSimple(text);
				
				}
   
		} else if (selection == 2000) {//重返按钮
                 
						  
			if (cm.getPlayer().getClient().getChannel() != 1) {
							   
				 cm.sendOk("\t抱歉哦！【#r注意：只有在1线才可以重返金字魔塔副本噢】。#k\r\n");
				        cm.dispose();
				  }else
					  if (cm.getPlayer().getCSPoints(2) > 10000&&cm.getBossLog("金字魔塔重返") <= 5) {
					cm.getPlayer().modifyCSPoints(2, -10000, true);     
				 cm.warp(926010001); 
				 cm.setBossLog('金字魔塔重返');//给次数	
				 cm.dispose(); 
				 }else
	             cm.sendOk("\t你的抵用不足或重返次数已达到上限~。#k\r\n");
				 cm.dispose();
                 
				       

					
             
						
						
		} else if (selection == 1000) {//开始挑战按钮
		typed = 1;
		cm.sendYesNo("	确认带领队伍挑战#r" + fbmc + "#k吗？");
		} else if (typed == 1) {
		var party = cm.getPlayer().getParty();
	var levelValid = 926010000;
	 var inMap = cm.partyMembersInMap();
				if (cm.getPlayerCount(926010001) > 0 ||cm.getPlayerCount(926020001) > 0){
					cm.sendOk("已经有人挑战#b" + fbmc + "#k,无法进入！");
                    cm.dispose();
				}else  if (party == null || party.getLeader().getId() != cm.getPlayer().getId()) {
                    cm.sendOk("你不是队长。请你们队长来说话吧！");
                    cm.dispose();
					}else  if (cm.getPlayer().getClient().getChannel() != 1) {
                    cm.sendOk("只有在频道1才能够挑战！");
                    cm.dispose();
				}else  if (cm.getPlayer().getLevel() < minLevel) {
                    cm.sendOk("你的等级必须达到"+minLevel+"级以上.");
                    cm.dispose();

				
                } else {
            var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= minLevel && party.get(i).getLevel() <= maxLevel)
                    levelValid++;
            }
			if (inMap < minPartySize || inMap > maxPartySize) {
                cm.sendOk("当前地图你的队伍人数不足"+minPartySize+"人.请把你的队伍人员召集到同一个地图内在进入副本.");
                cm.dispose();
				return;
			}else if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在 "+minLevel+" 和 "+maxLevel+"之间.");
                cm.dispose();
				return;	
			}else if(cm.getPartyBosslog("金字魔塔副本",(cishuxianzhi)) == false) {//判断组队次数
	            cm.sendOk("队伍中队友挑战次数已经用完1次！");
                cm.dispose();
				return;
			}else if(cm.getPlayer().getBossLog("金字魔塔副本") >= cishuxianzhi) {
	            cm.sendOk("您好,限定每天只能挑战"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
		}else{
if(cm.getPlayerCount(926010001) <= 0){
		
		cm.getMap(926010001).resetFully();//刷新地图
	
}			
            cm.setPartyBosslog("金字魔塔副本");//给团队次数
			cm.getMap(926010001).resetFully();//刷新地图			
			cm.warpParty(926010001);
			cm.showInstruction("#r[点击NPC开始召唤怪物~~~~]#k\r\n\r\n", 180, 60);
			
			var mapId = cm.getPlayer().getMapId();
			var party = cm.getPlayer().getParty().getMembers();
			var it = party.iterator();
			while (it.hasNext()) {
				var cPlayer = it.next();
				victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (victim.getId() != cm.getPlayer().getId()) {
					next = true;
					break;
				}
			}
			if (cm.getPlayer().getParty().getMembers().size() >= 2) {
				cm.全服黄色喇叭(  " 【金字魔塔副本】" + " : " + "["+cm.getPlayer().getName()+"]带领 [ " + (cm.getPlayer().getParty().getMembers().size()-1) + " ]位队员开始挑战（" + fbmc + "）")
			//cm.worldMessage(6,"【" + fbmc + "】-玩家：[ "+cm.getName()+" ] 带领 [ " + (cm.getPlayer().getParty().getMembers().size()-1) + " ]位队员开始挑战（" + fbmc + "）");
			}else{
			cm.worldMessage(6,"【" + fbmc + "】-玩家：[ "+cm.getName()+" ]独自一人单挑（" + fbmc + "）");
			}
			cm.dispose();
		}
	}
	
}
}