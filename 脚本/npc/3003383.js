var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 创意 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
var dj = 0 

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
		var selStr = "#d   "+美化1+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+"<<羽化重生>>"+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化3+"\r\n\r\n  ";
		
		if (cm.getPlayer().getLevel() < 249 && !cm.getPlayer().isGM()) {
		selStr += "哈喽，记住哦！等您达到满级250级可以来找我哦\r\n\r\n";
		}
		
		
		if (cm.getPlayer().getLevel() > 249 && !cm.getPlayer().isGM()) {
		selStr += "           #k这里可以帮助满级玩家进行重生！\r\n#l";
		selStr += "          #k你当前已经突破#r"+ cm.getOneTimeLog("修仙次数") +"#k次 每次突破换线生效！\r\n\r\n";
		selStr += "                    #L2##b领取奖励#l\r\n";
		selStr += "                    #L3##b突破说明\r\n#l";
		selStr += "              #L1##b我要重生（降级至200级）#l\r\n";
		}
		
		if (cm.getPlayer().isGM()) {
		
	    selStr += "           #k这里可以帮助满级玩家进行重生！\r\n#l";
		selStr += "          #k你当前已经突破#r"+ cm.getOneTimeLog("修仙次数") +"#k次 每次突破换线生效！\r\n\r\n";
		selStr += "                    #L2##b领取奖励#l\r\n";
		selStr += "                    #L3##b突破说明\r\n#l";
		selStr += "              #L1##b我要重生（降级至200级）#l\r\n";
		}
		
		selStr += "\r\n   "+美化4+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化5+"";
	
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
		    
		case 1:
			dj = cm.getChar().getLevel();
			if(cm.getOneTimeLog("修仙次数") >= 20){
			cm.sendOk("#r你已经累计降级20次,无法继续使用此功能了!");
			}else if( dj <= 249 ){
			cm.sendOk("#b你的等级不足250级.努力升级吧!加油!!!");
			} else {
			cm.getChar().setLevel(200);
			cm.getChar().setExp(0);
			cm.setOneTimeLog("修仙次数");
			cm.gainItem(4310059,1);//绝世币代码.
			cm.sendOk("#r降级成功!\r\n\r\n降级奖励: #v4001126##z4001126# x 1#k");//此处也需要更换为混沌币代码.			
			}
			cm.dispose();
			break;
		case 2:
			cm.dispose();
			cm.openNpc(9330065,1);
			break;
		case 3:
			
			cm.sendOk("突破对象:#r等级达到250级的玩家.可以来进行突破自我，限制突破10次.\r\n#k突破之后:#r玩家等级降低至200级(只降低等级,其余不变.),每次突破奖励1个绝世币.\r\n#k降级好处:#r保留属性,玩家再次升级可以继续获得能力点.\r\n#k突破奖励:#r每突破5次,即可领取一次大奖!\r\n#l");
			cm.dispose();
			break;
					
			
        }
    }
}
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