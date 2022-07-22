/* ==================
 脚本类型: 在线奖励	    
 脚本作者：枫叶   
 联系方式：1848350048
 =====================
 */
var status = 0;
var Eventid = "站街奖励";
var OnlineLevel = [10, 30, 60, 120, 240, 360, 480];//, 600, 720
var giftContent = [
        //10分钟
        [4001126, 5, 0], //枫叶

		
		
		//30分钟
        [5390002, 5, 1], //喇叭
		[4000463, 1, 1],//国庆纪念币
		
		//1小时
        [4001126, 10, 2], //枫叶
		[5390005, 3, 2],//喇叭
		
		//2小时
		[5122000, 5, 3], //熊宝宝
		[4032391, 5, 3],//碎片
		
		
		
        //4小时
		[4032392, 5, 4], //碎片
		[4000313, 2, 4],//进阶币
        
		//6小时
		[4000038, 1, 5], //金杯
		[2000005, 2, 5],
		[4001126, 10, 5],
		
        
		//8小时
		[2300001, 20, 6], 
		[4032392, 10, 6],
		[4032391, 10, 6],
        
		//10小时
		[4020005, 1, 7],
        [4020006, 1, 7],
		[4020007, 1, 7],
        
		//12小时
		[4020008, 1, 8], //老虎咆哮情景喇叭
		[4004000, 1, 8],
		[4004001, 1, 8],
		[5150040, 1, 8],
       
        ];
var giftId = -1;
var giftToken = [];
var gifts = null;
var time,TimeShow; 
var eff = "#fEffect/CharacterEff.img/1112924/0/0#";

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
	    if (cm.getGamePoints() < 60) {
	     time = "今日在线时间：#e#r"+ cm.getGamePoints() +"#k#n 分钟";
		} else {
		 time = "今日在线时间：#e#r"+ Math.floor(cm.getGamePoints() / 60) +"#k#n 小时 #e#r"+ (cm.getGamePoints() % 60) +"#k#n 分钟";
		}
        text = "\t\t"+彩虹+"  #e#d 在 线 奖 励 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n"+ time +"\r\n";
        for (var key in OnlineLevel) {
            var tips = "";
            giftToken[key] = false;
            if (cm.getGamePoints() >= OnlineLevel[key]) {
                if (cm.getEventCount(Eventid + OnlineLevel[key]) == 0) {
                    tips = "#g< 可领取 >";
                    giftToken[key] = true;
                } else {
                    tips = "#k< 今日已领取 >#b";
                }
            } else {
                tips = "#r< 在线时间不足 >#b";
            }
			if (OnlineLevel[key] < 60) {
	            TimeShow = "#r#e"+ OnlineLevel[key] +"分钟#n#b";
		    } else if (OnlineLevel[key] % 60 == 0){
				TimeShow = "#r#e"+ Math.floor(OnlineLevel[key] / 60) +"小时#n#b";
			} else {
		        TimeShow = "#r#e"+ Math.floor(OnlineLevel[key] / 60) +"小时#r#e"+ (OnlineLevel[key] % 60) +"分钟#n#b";
		    }
            text += "#b#L" + (parseInt(key)) + "#领取 "+ TimeShow +" 在线奖励 " + tips + "#l#k\r\n";
        }
        cm.sendSimple(text);
    } else if (status == 1) {
        giftId = parseInt(selection);
        var text = "\t\t  #b"+ eff +" 在线#r#e " + OnlineLevel[giftId] + " #n#b分钟奖励内容 "+ eff +"#k#n\r\n\r\n";
        gifts = getGift(giftId);
        for (var key in gifts) {
            var itemId = gifts[key][0];
            var itemQuantity = gifts[key][1];
            text += "\t\t\t#v" + itemId + "# #b#z" + itemId + "# #k[" + itemQuantity + "个]\r\n";
        }
        text += "\r\n#d（提示：领取礼包时请注意背包栏位#r是否有足够多的空位#d！）#k";
        cm.sendYesNo(text);
    } else if (status == 2) {
        if (giftId != -1 && gifts != null) {
            if (cm.getInventory(1).isFull(5) || cm.getInventory(2).isFull(5) || cm.getInventory(3).isFull(5) || cm.getInventory(4).isFull(5) || cm.getInventory(5).isFull(5)) {
                cm.sendOk("您的背包空间不足，请保证每个栏位至少8格的空间，以避免领取失败。");
                cm.dispose();
                return;
            }
            if (giftToken[giftId] && cm.getEventCount(Eventid + OnlineLevel[giftId], 1) == 0) {
                cm.setEventCount(Eventid + OnlineLevel[giftId]);
				cm.playerMessage(- 1,"获得 "+ ((giftId + 1) * 3) +" 点活力");
                for (var key in gifts) {
                    var itemId = gifts[key][0];
                    var itemQuantity = gifts[key][1];
                    cm.gainItem(itemId, itemQuantity);
                }
                cm.sendOk("恭喜您，领取成功！快打开包裹看看吧！");
			if (OnlineLevel[giftId] < 60) {
	            TimeShow = OnlineLevel[giftId] +"分钟";
		    } else if (OnlineLevel[giftId] % 60 == 0){
				TimeShow = + Math.floor(OnlineLevel[giftId] / 60) +"小时";
			} else {
		        TimeShow = + Math.floor(OnlineLevel[giftId] / 60) +"小时"+ (OnlineLevel[giftId] % 60) +"分钟";
		    }
			cm.worldMessage(6,"玩家：["+cm.getName()+ " 领取了 " + OnlineLevel[giftId] + " 分钟在线奖励，恭喜他（她）吧！~");
                cm.dispose();
            } else {
                status = -1;
                cm.sendOk("您已经领过了该礼包或者在线时长未达到要求，无法领取。");
            }
        } else {
            cm.sendOk("领取错误！请联系管理员！");
            cm.dispose();
        }
    }
}
function getGift(id) {
    var lastGiftContent = Array();
    for (var key in giftContent) {
        if (giftContent[key][2] == id)
            lastGiftContent.push(giftContent[key]);
    }
    return lastGiftContent;
}
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";