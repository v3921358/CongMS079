var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
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

            cm.sendOk("感谢你的光临！");
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
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "你好~!这里是理财中心. #v3700148##v3700149##v3700150#\r\n"
			text += ""+感叹号+"您目前拥有:"+cm.getmoneyb()+"余额\r\n\r\n"
			text += "#r #L7#理财奖励说明#l    \r\n\r\n"
			text += ""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+"\r\n"
			text += "#dNo.1 普通理财                    #v3700150##t3700150#\r\n价格:30余额/周 100余额/月\r\n特权:\r\n1.上线具有特殊身份标识.\r\n2.每日上线就可领取:\r\n3.购买即送丰厚大礼包\r\n#L1#购买月卡(100余额)#l     #L2#购买周卡(30余额)#l\r\n\r\n\r\n"
			text += ""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+"\r\n"
			text += "#bNo.2 经济理财                    #v3700149##t3700149#\r\n价格:60余额/周 200余额/月\r\n特权:\r\n1.上线具有特殊身份标识.\r\n2.每日上线就可领取:\r\n3.购买即送丰厚大礼包\r\n#L3#购买月卡(200余额)#l     #L4#购买周卡(60余额)#l\r\n\r\n\r\n"
			text += ""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+""+蓝色角点+"\r\n"
			text += "#rNo.3 豪华理财                    #v3700148##t3700148#\r\n价格:90余额/周 300余额/月\r\n特权:\r\n1.上线具有特殊身份标识.\r\n2.每日上线就可领取:\r\n3.购买即送丰厚大礼包\r\n#L5#购买月卡(300余额)#l     #L6#购买周卡(90余额)#l\r\n\r\n"
			if(cm.haveItem(3700148,1) || cm.haveItem(3700149,1) || cm.haveItem(3700150,1)){
				cm.sendOk("您已经办理了一个理财会员,如需更换,请删除设置栏的#v3700148##v3700149##v3700150#其中一个再来找我~!");
			} else {
				cm.sendSimple(text);
			}
        } else if (selection == 1) {
			if(cm.getmoneyb() >= 100){
			 cm.getzb(-100);
			 cm.gainItem(3700150,1,30);
			 cm.gainMeso(50000000);
			 cm.gainNX(30000);
cm.全服漂浮喇叭("恭喜玩家["+cm.getName()+"]已经成功购买理财会员一个月,获得冒险币 × 5000万 点卷 × 3万 快去打劫他吧~!", 5121005);
				cm.dispose();
			} else {
				cm.sendOk("您的余额不足~!请充值！");
            cm.dispose();
			}
				}else if  (selection == 2) {
				if(cm.getmoneyb() >= 30){
					cm.getzb(-30);
			 cm.gainItem(3700150,1,7);
			 cm.gainMeso(10000000);
			 cm.gainNX(5000);
cm.全服漂浮喇叭("恭喜玩家["+cm.getName()+"]已经成功购买理财会员一个周,获得冒险币 × 1000万 点卷 × 5000 快去打劫他吧~!", 5121005);
				cm.dispose();
			} else {
				cm.sendOk("您的余额不足~!请充值！");
            cm.dispose();
			}
}else if  (selection == 3) {
				if(cm.getmoneyb() >= 200){
					cm.getzb(-200);
			 cm.gainItem(3700149,1,30);
			 cm.gainMeso(100000000);
			 cm.gainNX(50000);
cm.全服漂浮喇叭("恭喜玩家["+cm.getName()+"]已经成功购买经济理财会员一个月,获得冒险币 × 1亿 点卷 × 5万 快去打劫他吧~!", 5121007);
				cm.dispose();
			} else {
				cm.sendOk("您的余额不足~!请充值！");
            cm.dispose();
			}
}else if  (selection == 4) {
				if(cm.getmoneyb() >= 60){
					cm.getzb(-60);
			 cm.gainItem(3700149,1,7);
			 cm.gainMeso(20000000);
			 cm.gainNX(10000);
cm.全服漂浮喇叭("恭喜玩家["+cm.getName()+"]已经成功购买经济理财会员一个周,获得冒险币 × 2000万 点卷 × 1万 快去打劫他吧~!", 5121007);
				cm.dispose();
			} else {
				cm.sendOk("您的余额不足~!请充值！");
            cm.dispose();
			}
}else if  (selection == 5) {
				if(cm.getmoneyb() >= 300){
					cm.getzb(-300);
			 cm.gainItem(3700148,1,30);
			 cm.gainMeso(150000000);
			 cm.gainNX(100000);
cm.全服漂浮喇叭("恭喜玩家["+cm.getName()+"]已经成功购买豪华理财会员一个月,获得冒险币 × 1.5亿 点卷 × 10万 快去打劫他吧~!", 5121006);
				cm.dispose();
			} else {
				cm.sendOk("您的余额不足~!请充值！");
            cm.dispose();
			}
}else if  (selection == 6) {
				if(cm.getmoneyb() >= 90){
					cm.getzb(-90);
			 cm.gainItem(3700148,1,7);
			 cm.gainMeso(30000000);
			 cm.gainNX(20000);
cm.全服漂浮喇叭("恭喜玩家["+cm.getName()+"]已经成功购买理财会员一个周,获得冒险币 × 3000万 点卷 × 2万 快去打劫他吧~!", 5121006);
				cm.dispose();
			} else {
				cm.sendOk("您的余额不足~!请充值！");
            cm.dispose();
			}
}else if  (selection == 7) {
				cm.sendOk("#r#e普通理财：#k \r\n周卡直接奖励1000W金币和5000点券！\r\n月卡直接奖励5000万金币和3万点券！\r\n每日首次登陆：返还4积分，\r\n\r\n#r经济理财:\r\n#k周卡直接奖励2000万金币和10000点券!\r\n月卡直接奖励1亿金币和5万点券。\r\n每日首次登陆：返还10积分，\r\n\r\n#r豪华理财:\r\n#k周卡直接奖励3000W金币和20000点券!\r\n月卡直接奖励1.5亿金币和10W点券。\r\n每日首次登陆：返还18积分，\r\n");
            cm.dispose();
			
				}				
			}
		}	
