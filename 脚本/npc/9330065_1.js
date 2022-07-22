/*
 
 脚本内容：降级奖励
 制作人：阿涛.
 制作时间：2020-10-18 14:24:10
 
 */
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
		var selStr = "\t\t\t\t你当前已经突破#r"+ cm.getOneTimeLog("修仙次数") +"#k次。\r\n\r\n";
		selStr += "#L1##r领取突破5次奖励. - #v3700065#(神级通行证I)#l\r\n\r\n";
		
		if(cm.getOneTimeLog("修仙次数") > 5){
		selStr += "#L2##r领取突破10次奖励. - #v3700066#(神级通行证II)#l\r\n\r\n";
		}
		
		if(cm.getOneTimeLog("修仙次数") > 10){
		selStr += "#L3##r领取突破15次奖励. - #v3700068#(神级通行证III)#l\r\n\r\n";
		}
		
		if(cm.getOneTimeLog("修仙次数") > 15){
		selStr += "#L4##r领取突破20次奖励. - #v3700069#(神级通行证IIII)#l\r\n\r\n";
		}
		
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
		    
		case 1:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("修仙次数") < 5){
			cm.sendOk("#r你的突破次数小于5次,无法领取奖励!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b你的等级不足250级.达到250级之后才可以领取哦.");
			cm.dispose();
			}else if(cm.getOneTimeLog("修仙次数5奖励") >= 1){
			cm.sendOk("每阶段阶段奖励只能领取一次!");	
			} else {
			cm.setOneTimeLog("修仙5");
			cm.gainItem(3700065,1);
			cm.sendOk("#r领取奖励成功.\r\n\r\n获得#v3700065#");
			cm.dispose();			
			}
		case 2:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("修仙次数") < 10){
			cm.sendOk("#r你的突破次数小于10次,无法领取奖励!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b你的等级不足250级.达到250级之后才可以领取哦.");
			cm.dispose();
			}else if(cm.getOneTimeLog("修仙次数10奖励") >= 1){
			cm.sendOk("每阶段阶段奖励只能领取一次!");	
			} else {
			cm.setOneTimeLog("修仙次数10奖励");
			cm.gainItem(3700066,1);
			cm.sendOk("#r领取奖励成功.\r\n\r\n获得#v3700066#");
			cm.dispose();			
			}
		case 3:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("修仙次数") < 15){
			cm.sendOk("#r你的突破次数小于15次,无法领取奖励!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b你的等级不足250级.达到250级之后才可以领取哦.");
			cm.dispose();
			}else if(cm.getOneTimeLog("修仙次数15奖励") >= 1){
			cm.sendOk("每阶段阶段奖励只能领取一次!");	
			} else {
			cm.setOneTimeLog("修仙次数15奖励");
			cm.gainItem(3700068,1);
			cm.sendOk("#r领取奖励成功.\r\n\r\n获得#v3700068#");
			cm.dispose();			
			}
		case 4:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("修仙次数") < 20){
			cm.sendOk("#r你的突破次数小于20次,无法领取奖励!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b你的等级不足250级.达到250级之后才可以领取哦.");
			cm.dispose();
			}else if(cm.getOneTimeLog("修仙次数20奖励") >= 1){
			cm.sendOk("每阶段阶段奖励只能领取一次!");	
			} else {
			cm.setOneTimeLog("修仙次数20奖励");
			cm.gainItem(3700069,1);
			cm.sendOk("#r领取奖励成功.\r\n\r\n获得#v3700069#");
			cm.dispose();			
			}
			
			
			
        }
    }
}