var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
var 花1 = "#fEffect/CharacterEff/1050334/0/3#";  //紫色大花

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
    if (status == 0) {
		var selStr = "		#r#e "+花1+"<< 挑战挑战男老板BOSS >>"+花1+" #k#n\r\n\r\n#l";
		
	    selStr += "   #b这里是男老板BOSS挑战地点,召唤男老板BOSS,需要女老板的梳子.!!!\r\n\r\n#l";
		
		
		if(cm.getBossLog("男老板BOSS") > 0){//如果没有打完最终的BOSS,则此选项不会出现.
		selStr += "              #L999##e#r<<<领取奖励>>>\r\n\r\n#l";//打完最终BOSS后,此选项出现.	
		selStr += "      击败希拉超级BOSS就可以来领取奖励哦\r\n#l";
		}
		if(cm.getBossLog("男老板BOSS") == 0){//开始挑战第一个BOSS.
		selStr += "       #L1##e#r<<<开始召唤 - 男老板BOSS>>>\r\n\r\n#l";
		}
		selStr += "               #L886##b离开这里\r\n\r\n#l";
		cm.sendSimple(selStr);
		}else if (status == 1) {
        switch (selection) {
			case 1:
			if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
			cm.dispose();
            
			} else if(cm.partyMembersInMap() > 3){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();
						
			} else if (cm.getPlayer().getMap().getMonsterById(9400113)){//判断是否召唤过BOSS,如果召唤了则无法继续召唤.
			cm.sendOk("无法重复召唤BOSS!!!");
			cm.dispose();
			
			} else if(!cm.haveItem(4000138, 1)){
			cm.sendOk("你没有女老板的梳子!!!");	
			cm.dispose();
			} else {
			var mob1 = 9400113; 
			cm.gainItem(4000138, -1);
			cm.spawnMobOnMap(mob1,1,897,158,801040006);//括号内分别对应,怪物ID、怪物数量、X坐标、Y坐标、召唤地图。
			cm.setBossLog("男老板BOSS");
			cm.sendOk(" #b挑战开始,请击杀 #r#o9400113# #k");
			
			}
			cm.dispose();
			break;
		    
		case 886:
			
			cm.warp(801040005);
			cm.dispose();
			break;
		case 999:
			if(cm.getBossLog("挑战希拉奖励") >= 1){//判断该奖励每天只能领取1次.
			cm.sendOk("该奖励每天只能领取1次!!!");
			
			}/*else if(cm.getBossLog("挑战希拉") < 1){//打完最终关卡时,存入一个"挑战完成"的bosslog,判断是否通关,通关则给奖励.
			cm.sendOk("你还没有挑战完成哦!!!");
			
			}*/ else if (cm.getPlayer().getMap().getMonsterById(9900000)){//判断上BOSS是否死亡,如果没有死亡则无法领取奖励.
			cm.sendOk("你还未击杀希拉超级BOSS.\r\n\r\n请先击杀后再来领取奖励.");
			}else if (!cm.checkNumSpace(2, 1)) {
			cm.sendOk("背包消耗栏空间不足1格");
			cm.dispose();
			return;
		    }
			
			
			else{
			cm.gainItem(2049401,1);//给奖励的位子,自行添加奖励.
			cm.sendOk("领取最终奖励成功.\r\n\r\n奖励:#v2049401#!");
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『终极BOSS』" + " : " + "[" + cm.getChar().getName() + "]势如破竹，成功战胜希拉领取了奖励！")); 
			cm.setBossLog("挑战希拉奖励");
			
			}
			cm.dispose();
			break;
			
        }
    }
}