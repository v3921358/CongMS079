var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";

var text1 = "";
var score = 0 ;
var selectionNK = 0;
var zhuangScore = 0;
var xianScore = 0;
var paiArray = Array("A",2,3,4,5,6,7,8,9,10,"J","Q","K");
var paiArray2 = Array(1,2,3,4,5,6,7,8,9,10,10,10,10);
var ran = -1;
var hx = "#fEffect/CharacterEff/1042176/0/0#";

function start() {
	status = -1;
	
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		
		cm.dispose();
	}
	else {
		if (status == 0 && mode == 0) {
		
			cm.sendOk("欢迎下次光临。");
			cm.dispose();
			return;                    
		}
		if (mode == 1) {
			status++;
		}
		else {
			 if (status == 0) {
                cm.sendNext("欢迎下次光临。");
                cm.dispose();
            }
			status--;
		} 
		
		if (status == 0) {
			var text = "";
			score = cm.getPlayer();
			text += "\t\t\t" + hx + hx + hx + "#e#r点卷开心中心#n#k" + hx + hx + hx + "\r\n ";
			text += "#fUI/UIWindow/Quest/icon3/6# ";
			text += "我这里可以玩21点,";
			text += "您当前剩余点卷为 #r" + cm.getPlayer().getCSPoints(1) + "点#k";
			text += " #fUI/UIWindow/Quest/icon3/6#\r\n";
			text += "#L0# 开始游戏 #l\r\n";
			text += "#L1# 游戏说明 #l\r\n";
			cm.sendSimple(text);
		} 
		else if (status == 1) { 
			
			if(selection == 0){		
	
				cm.sendGetNumber("你要投入多少？\r\n", 1, 1000,200000);
				
			}else if(selection == 1){
				text1 += "#fUI/UIWindow/Quest/icon3/6# ";
				text1 += "玩法说明：";
				text1 += " #fUI/UIWindow/Quest/icon3/6#\r\n";
				text1 += "    庄家的点数会在15点-22点之间（庄家也有可能爆掉），你有4次要牌的机会，如果您的点数大于庄家的点数即赢得你投入的金额，如果大于21点或者小于等于庄家的点数，即输掉你所下注的金额，10、J、Q、K都算十点，开心娱乐，请慎重不要沉迷。";
				cm.sendOk(text1);
				cm.dispose();
			}
		}
		//-------------第一次要牌-----------------//
		else if (status == 2) {
			selectionNK = selection;
			if(selectionNK > cm.getPlayer().getCSPoints(1) ){
				cm.sendOk("你个穷鬼，没钱还想来？快回家拿钱来");
				cm.dispose();
			}else{
				zhuangScore = parseInt(Math.random()*8+15); 
				ran = parseInt(Math.random()*paiArray.length);
				xianScore = paiArray2[ran];
				var text2 = "";
				text2 += "拿到了 #r#e" + paiArray[ran] + "#n#k,您目前的点数为： #r#e" + xianScore + "#n#k ，";
				text2 += "您继续要牌吗？ \r\n";
				text2 += "#L2# 要  \r\n";
				text2 += "#L3# 不要  \r\n";
				cm.gainNX(-selectionNK);
				cm.sendSimple(text2);
			}
			
		}
		
		//------------第二次要牌------------------//
		
		else if (status == 3) { 
			if(selection == 2){
				ran = parseInt(Math.random()*paiArray.length);
				xianScore += paiArray2[ran];
				if(xianScore>21){
					cm.sendOk("对不起，您的数字大于21，您输了。");
					//cm.gainNX(-selectionNK);
					cm.dispose();	
				}else{
					text2 = "";
					text2 += "拿到了 #r#e" + paiArray[ran] + "#n#k,您目前的点数为： #r#e" + xianScore + "#n#k ，";
					text2 += "您继续要牌吗？ \r\n";
					text2 += "#L4# 要  \r\n";
					text2 += "#L5# 不要  \r\n";
					cm.sendSimple(text2);
				}
			}
			else if(selection == 3){
				if((xianScore<22 && xianScore<=zhuangScore && zhuangScore<22) || (xianScore>=22 && zhuangScore>=22)){
					cm.sendOk("庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您输了。");
					//cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在赌场惨输" + selectionNK + "点卷，运气真是差呀!!"); //
					//cm.gainNX(-selectionNK);
					cm.dispose();
				}else{
					cm.sendOk("#fUI/UIWindow/Quest/reward#\r\n  庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您赢了。");
					cm.gainNX(selectionNK*2);
					cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在点卷开心中心赢了" + selectionNK + "点卷,人品大爆发了!一起恭喜他吧!!"); //
					cm.dispose();	
				}
			}
			
			
		}
		//-----------第三次要牌---------------
		else if (status == 4) { 
			if(selection == 4){
				ran = parseInt(Math.random()*paiArray.length);
				xianScore += paiArray2[ran];
				if(xianScore>21){
					cm.sendOk("对不起，您的数字大于21，您输了。");
					//cm.gainNX(-selectionNK);
					//cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在赌场惨输" + selectionNK + "点卷，运气真是差呀!!");
					cm.dispose();	
				}else{
					text2 = "";
					text2 += "拿到了 #r#e" + paiArray[ran] + "#n#k,您目前的点数为： #r#e" + xianScore + "#n#k ，";
					text2 += "您继续要牌吗？ \r\n";
					text2 += "#L6# 要  \r\n";
					text2 += "#L7# 不要  \r\n";
					cm.sendSimple(text2);
				}
			}
			else if(selection == 5){
				if((xianScore<22 && xianScore<=zhuangScore && zhuangScore<22) || (xianScore>=22 && zhuangScore>=22)){
					cm.sendOk("庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您输了。");
					//cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在赌场惨输" + selectionNK + "点卷，运气真是差呀!!"); 
					//cm.gainNX(-selectionNK);
					cm.dispose();
				}else{
					cm.sendOk("#fUI/UIWindow/Quest/reward#\r\n  庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您赢了。");
					cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在点卷开心中心赢了" + selectionNK + "点卷,人品大爆发了!一起恭喜他吧!!");
					cm.gainNX(selectionNK*2);
					cm.dispose();	
				}
			}
			
			
		}
		
		//--------第四次要牌----------
		else if (status == 5) { 
			if(selection == 6){
				ran = parseInt(Math.random()*paiArray.length);
				xianScore += paiArray2[ran];
				if(xianScore>21){
					cm.sendOk("对不起，您的数字大于21，您输了。");
					//cm.gainNX(-selectionNK);
					//cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在赌场惨输" + selectionNK + "点卷，运气真是差呀!!");
					cm.dispose();	
				}else{
					if((xianScore<22 && xianScore<=zhuangScore && zhuangScore<22) || (xianScore>=22 && zhuangScore>=22)){
						cm.sendOk("庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您输了。");
						//cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在赌场惨输" + selectionNK + "点卷，运气真是差呀!!"); 
						//cm.gainNX(-selectionNK);
						cm.dispose();
					}else{
						cm.sendOk("#fUI/UIWindow/Quest/reward#\r\n  庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您赢了。");
						cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在点卷开心中心赢了" + selectionNK + "点卷,人品大爆发了!一起恭喜他吧!!");
						cm.gainNX(selectionNK*2);
						cm.dispose();	
					}
				}
				
			}
			else if(selection == 7){
				if((xianScore<22 && xianScore<=zhuangScore && zhuangScore<22) || (xianScore>=22 && zhuangScore>=22)){
					cm.sendOk("庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您输了SS。");
					//cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在赌场惨输" + selectionNK + "点卷，运气真是差呀!!"); 
					//cm.gainNX(-selectionNK);
					cm.dispose();
				}else{
					cm.sendOk("#fUI/UIWindow/Quest/reward#\r\n  庄家的点数为 #r#e"+zhuangScore+"#n#k,您的点数为 #r#e"+xianScore+"#n#k,您赢了。");
					cm.worldMessage(6,"【豆豆屋-蓝蘑菇机】["+cm.getName()+"] 在点卷开心中心赢了" + selectionNK + "点卷,人品大爆发了!一起恭喜他吧!!");
					cm.gainNX(selectionNK*2);
					cm.dispose();	
				}
			}
			
			
		}
		//------------结束-------------
		
	}
}