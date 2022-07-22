var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
 

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
		var selStr = "#d   "+美化1+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+"<<界外BOSS>>"+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化3+"\r\n\r\n#b#k  ";
		if(cm.getPlayer().getLevel() <= 199 ){
		selStr += "你的实力还不够，请去提升你的等级";
		}
		if(cm.getPlayer().getLevel() >= 200 ){
		
		selStr += "#L7##e挑战恶犬#l\t#L8##e挑战桃乐丝#l\r\n\r\n";
		selStr += "----------------------------------------------#l\r\n#k";
		
		selStr += "#L9##e挑战觉醒希拉#l\t#L10##e挑战敦凯尔#l\r\n\r\n";
		selStr += "----------------------------------------------#l\r\n#k";

		
		selStr += "       #L6##e挑战露西德 #l\r\n";
		}
		
		selStr += "\r\n   "+美化4+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化5+"";

		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
			
		    
		case 1:
		    if (cm.getParty() == null) { 
            cm.sendOk("你没有队伍无法进入!");
            cm.dispose();
			return;
			}
			if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
            cm.dispose();
			return;
			}
		    var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= 200 )
                    levelValid++;
            }
            if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在200级 .");
                cm.dispose();
				return;
			}  else if(cm.partyMembersInMap() > 3){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();			
			} else if (cm.getPlayer().getMeso() < 20000000){//判断金币是否大于2000W.
			cm.sendOk("你的金币不足,入场费需要2000W.");
			cm.dispose();
			} else if (cm.getPlayer().getLevel() < 200){//判断等级是否小于200.
			cm.sendOk("等级不足200,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("稀拉挑战") > 0){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过1次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(970000005) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			
			cm.gainMeso(-20000000);//金币2000w
			cm.刷新地图(970000005);
			cm.warpParty(970000005);
			cm.setBossLog("稀拉挑战");
			
			}
			cm.dispose();
			break;
			
		case 2:
		    if (cm.getParty() == null) { 
            cm.sendOk("你没有队伍无法进入!");
            cm.dispose();
			return;
			}
			if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
            cm.dispose();
			return;
			}
		    var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= 200 )
                    levelValid++;
            }
            if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在200级 .");
                cm.dispose();
				return;
			} else if(cm.partyMembersInMap() > 2){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();			
			} else if (cm.getPlayer().getMeso() < 20000000){//判断金币是否大于2000W.
			cm.sendOk("你的金币不足,入场费需要2000W.");
			cm.dispose();
			} else if (cm.getPlayer().getLevel() < 200){//判断等级是否小于200.
			cm.sendOk("等级不足200,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("希纳斯挑战") > 0){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过1次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(901111112) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			
			cm.gainMeso(-20000000);//金币2000w
			cm.刷新地图(901111112);
			cm.warpParty(901111112);
			cm.setBossLog("希纳斯挑战");
			
			}
			cm.dispose();
			break;
			
		case 3:
		    if (cm.getParty() == null) { 
            cm.sendOk("你没有队伍无法进入!");
            cm.dispose();
			return;
			}
			if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
            cm.dispose();
			return;
			}
			var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= 200 )
                    levelValid++;
            }
            if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在200级 .");
                cm.dispose();
				return;
			}
			else if(cm.partyMembersInMap() > 2){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();			
			} else if (cm.getPlayer().getMeso() < 20000000){//判断金币是否大于2000W.
			cm.sendOk("你的金币不足,入场费需要2000W.");
			cm.dispose();
			} else if (cm.getPlayer().getLevel() < 200){//判断等级是否小于200.
			cm.sendOk("等级不足200,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("大蛇丸挑战") > 0){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过1次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(901111113) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			
			cm.gainMeso(-20000000);//金币2000w
			cm.刷新地图(901111113);
			cm.warpParty(901111113);
			cm.setBossLog("大蛇丸挑战");
			
			}
			cm.dispose();
			break;
			
		case 4:
		    if (cm.getParty() == null) { 
            cm.sendOk("你没有队伍无法进入!");
            cm.dispose();
			return;
			}
			if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
            cm.dispose();
			return;
			}
			var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= 200 )
                    levelValid++;
            }
            if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在200级 .");
                cm.dispose();
				return;
			}
			 else if(cm.partyMembersInMap() > 2){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();			
			} else if (cm.getPlayer().getMeso() < 20000000){//判断金币是否大于2000W.
			cm.sendOk("你的金币不足,入场费需要2000W.");
			cm.dispose();
			} else if (cm.getPlayer().getLevel() < 200){//判断等级是否小于200.
			cm.sendOk("等级不足200,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("伴伴挑战") > 0){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过1次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(901111114) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			
			cm.gainMeso(-20000000);//金币2000w
			cm.刷新地图(901111114);
			cm.warpParty(901111114);
			cm.setBossLog("伴伴挑战");
			
			}
			cm.dispose();
			break;
			
		case 5:
		    if (cm.getParty() == null) { 
            cm.sendOk("你没有队伍无法进入!");
            cm.dispose();
			return;
			}
			if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
            cm.dispose();
			return;
			}
			var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= 200 )
                    levelValid++;
            }
            if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在200级 .");
                cm.dispose();
				return;
			}
			else if(cm.partyMembersInMap() > 2){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();			
			} else if (cm.getPlayer().getMeso() < 20000000){//判断金币是否大于2000W.
			cm.sendOk("你的金币不足,入场费需要2000W.");
			cm.dispose();
			} else if (cm.getPlayer().getLevel() < 200){//判断等级是否小于200.
			cm.sendOk("等级不足200,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("女皇挑战") > 0){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过1次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(901111115) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			
			cm.gainMeso(-20000000);//金币2000w
			cm.刷新地图(901111115);
			cm.warpParty(901111115);
			cm.setBossLog("女皇挑战");
			
			}
			cm.dispose();
			break;
			
		case 6:
			cm.warpParty(882100001);
			cm.dispose();
			break;
			
			case 7:
			cm.warpParty(510100200);
			cm.dispose();
			break;
			
			case 8:
			cm.warpParty(992000000);
			cm.dispose();
			break;
			
			case 9:
			cm.warpParty(450011990);
			cm.dispose();
			break;
			
			
			case 10:			
			cm.warpParty(450012200);
			cm.dispose();
			break;
        }
    }
}
var aaa = "#fUI/UIWindow.img/MobGage/Mob/9420513#";
var 希纳斯 = "#fUI/UIWindow.img/MobGage/Mob/9400641#";
var 大蛇 = "#fUI/UIWindow.img/MobGage/Mob/6090000#";
var 伴伴 = "#fUI/UIWindow.img/MobGage/Mob/8180001#";
var 女皇 = "#fUI/UIWindow.img/MobGage/Mob/4220000#";
var 露西德 = "#fUI/UIWindow.img/MobGage/Mob/9400590#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 美化1 = "#fUI/ChatBalloon.img/pet/120/nw#";//选择道具
var 美化3 = "#fUI/ChatBalloon.img/pet/120/ne#";//选择道具
var 美化2 = "#fUI/ChatBalloon.img/pet/120/n#";//选择道具
var 美化4 = "#fUI/ChatBalloon.img/pet/120/sw#";//选择道具
var 美化5 = "#fUI/ChatBalloon.img/pet/120/se#";//选择道具
var 美化6 = "#fUI/ChatBalloon.img/pet/120/s#";//选择道具
var 美化7 = "#fUI/ChatBalloon.img/156/arrow#";//选择道具