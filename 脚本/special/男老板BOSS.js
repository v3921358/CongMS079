var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
 

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
		var selStr = "--------------------------------------------------#l\r\n#k";
		 if (cm.getPlayer().getMapId()==801040005) {
		selStr += " #L1##e进入挑战 ["+ cm.getBossLog("男老板挑战") +"/1]次#l        \r\n\r\n";
		 }

		selStr += "-------------------------------------------#l\r\n#k";


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
			}  else if(cm.partyMembersInMap() < 1 ){
			cm.sendOk("你的队伍人数不能小于3人!!!");
			cm.dispose();			
			}  else if (cm.getPlayer().getLevel() < 30){//判断等级是否小于10.
			cm.sendOk("等级不足30,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("男老板挑战") > 0){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过1次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(801040006) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			
			
			cm.刷新地图(801040006);
			cm.warpParty(801040006);
			cm.setBossLog("男老板挑战");
			
			}
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