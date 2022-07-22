/*  This is mada by Kent    
 *  This source is made by Funms Team
 *  功能：等级送礼
 *  @Author Kent 
 */

var status = 0;
var bossid = "等级礼包";
var giftLevel = Array(10,30,50,70,100,120,150,180,200,210,220,230,240,250);
var giftContent = Array(
	//道具代码--数量--等级礼包序号从0开始 

//10级

    Array(2000005, 10, 0),//超级药水
	Array(5062000, 1, 0), //神奇魔方
	Array(5150040, 1, 0), //皇家理发卷
	Array(5152053, 1, 0), //皇家整容卷
	
//30级

    Array(2000005, 50, 1),//超级药水
	Array(5062000, 2, 1), //神奇魔方
	Array(5150040, 2, 1), //皇家理发卷
	Array(5152053, 1, 1), //皇家整容卷	


//50级

    Array(2000005, 80, 2),//超级药水
	Array(5062000, 3, 2), //神奇魔方
	Array(5150040, 3, 2), //皇家理发卷
	Array(5152053, 3, 2), //皇家整容卷
        
//70级

    Array(2000005, 100, 3),//超级药水
	Array(5062000, 4, 3), //神奇魔方
	Array(5150040, 4, 3), //皇家理发卷
	Array(5152053, 4, 3), //皇家整容卷
	
//100级

    Array(2000005, 200, 4),//超级药水
	Array(5062000, 5, 4), //神奇魔方
	Array(5150040, 5, 4), //皇家理发卷
	Array(5152053, 5, 4), //皇家整容卷

//120级

    Array(2000005, 200, 5),//超级药水
	Array(5062000, 5, 5), //神奇魔方
	Array(5150040, 5, 5), //皇家理发卷
	Array(5152053, 5, 5), //皇家整容卷

//150级

    Array(2000005, 200, 6),//超级药水
	Array(5062000, 5, 6), //神奇魔方
	Array(5150040, 5, 6), //皇家理发卷
	Array(5152053, 5, 6), //皇家整容卷

//180级

    Array(200005, 300, 7),//超级药水
	Array(5150040, 5, 7), //皇家理发卷
	Array(5152053, 5, 7), //皇家整容卷
	Array(5062000, 10, 7), //神奇魔方

    //Array(1132243, 1, 7),//低级贝勒德腰带
    //Array(1122264, 1, 7),//低级贝勒德吊坠
	
	
//200级

    Array(200005, 300, 8),//超级药水
	Array(5150040, 5, 8), //皇家理发卷
	Array(5152053, 5, 8), //皇家整容卷
	Array(5062000, 20, 8), //神奇魔方
	
//210级

    Array(2000005, 300, 9),//超级药水
	Array(5150040, 10, 9), //皇家理发卷
	Array(5152053, 10, 9), //皇家整容卷
	Array(5062000, 30, 9), //神奇魔方
	Array(2340000, 30, 9), //祝福卷轴
	Array(4000463, 5, 9), //国庆经念币
	//Array(1113072, 1, 4), //低级贝勒德戒指
	//Array(1032220, 1, 4), //低级贝勒德耳环
	
//220级

    Array(2000005, 300, 10),//超级药水
	Array(5150040, 10, 10), //皇家理发卷
	Array(5152053, 10, 10), //皇家整容卷
	Array(5062000, 30, 10), //神奇魔方
	Array(2340000, 30, 10), //祝福卷轴
	Array(4000463, 5, 10), //国庆经念币
	
//230级

    Array(2000005, 300, 11),//超级药水
	Array(5150040, 10, 11), //皇家理发卷
	Array(5152053, 10, 11), //皇家整容卷
	Array(5062000, 30, 11), //神奇魔方
	Array(2340000, 30, 11), //祝福卷轴
	Array(4000463, 5, 11), //国庆经念币
	
//240级

    Array(2000005, 300, 12),//超级药水
	Array(5150040, 10, 12), //皇家理发卷
	Array(5152053, 10, 12), //皇家整容卷
	Array(5062000, 30, 12), //神奇魔方
	Array(2340000, 30, 12), //祝福卷轴
	Array(4000463, 5, 12), //国庆经念币
	
//250级
    Array(2000005, 300, 13),//超级药水
	Array(5150040, 10, 13), //皇家理发卷
	Array(5152053, 10, 13), //皇家整容卷
	Array(5062000, 30, 13), //神奇魔方
	Array(2340000, 30, 13), //祝福卷轴
	Array(4000463, 5, 13)//国庆经念币

        /*
         2046996 - 惊人的单手武器攻击力卷轴100%
         2046997 - 惊人的单手武器魔力卷轴100%
         2047818 - 惊人的双手武器攻击力卷轴100%
         2612059 - 惊人的双手武器魔力卷轴100% 
		 */
         
        );
var giftId = -1;
var giftToken = Array();
var gifts = null;
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
        var text = "";
        text += "嘿，我为你准备了许多宝贝，等你达到相应等级的时候就可以领取了，另外点击可以查看礼包内容呢，快抢先看看吧！\r\n";
        for (var key in giftLevel) {
            var tips = "";
            giftToken[key] = false;
            if (cm.getChar().getLevel() >= giftLevel[key]) {
                if (cm.getBossLog(bossid + key, 1) == 0) {
                    tips = "(可领取)";
                    giftToken[key] = true;
                } else {
                    tips = "#g(已领取)#b";
                }
            } else {
                tips = "#r(等级不足)#b";
            }
            text += "#b#L" + (parseInt(key)) + "#领取#r#e" + giftLevel[key] + "#n#b级等级礼包 " + tips + "#l#k\r\n";
        }
        cm.sendSimple(text);
    } else if (status == 1) {
        giftId = parseInt(selection);
        var text = "#r#e" + giftLevel[giftId] + "#n#b级礼包内容：\r\n";
        gifts = getGift(giftId);
        for (var key in gifts) {
            var itemId = gifts[key][0];
            var itemQuantity = gifts[key][1];
            text += "#v" + itemId + "##b#t" + itemId + "##k #rx " + itemQuantity + "#k\r\n";
        }
        text += "\r\n#d是否现在就领取该礼包？#k";
        cm.sendYesNo(text);
    } else if (status == 2) {
        if (giftId != -1 && gifts != null) {
            if (cm.getSpace(1) < 8 || cm.getSpace(2) < 8 || cm.getSpace(3) < 8 || cm.getSpace(4) < 8 || cm.getSpace(5) < 8) {
                cm.sendOk("您的背包空间不足，请保证每个栏位至少8格的空间，以避免领取失败。");
                cm.dispose();
                return;
            }
            var job = cm.getChar().getJob();
            if ((job == 10000 || job == 10100 || job == 10110 || job == 10111 || job == 10112) && cm.getChar().getLevel() < 140) {
                cm.sendOk("神之子需要到140才能领取！");
                cm.dispose();
                return;
            }
            if (giftToken[giftId] && cm.getBossLog(bossid + key, 1) == 0) {
                cm.setBossLog(bossid + (giftId), 1, 1);
                for (var key in gifts) {
                    var itemId = gifts[key][0];
                    var itemQuantity = gifts[key][1];
                    cm.gainItem(itemId, itemQuantity);
                }
                cm.sendOk("恭喜您，领取成功！快打开包裹看看吧！");
                cm.dispose();
            } else {
                status = -1;
                cm.sendSimple("您已经领过了该礼包或者等级未达到要求，无法领取。");
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