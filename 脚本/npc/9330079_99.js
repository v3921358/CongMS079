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
            text += "#e#d领取每日签到礼包！点击下方红色选项领取签到礼包。\r\n\r\n"
            text += "#L1##r领取每日签到礼包#l\r\n\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) {
			/*if(!cm.beibao(1,3)){
            cm.sendOk("装备栏空余不足3个空格！");
            cm.dispose();
			}else if(!cm.beibao(2,2)){
            cm.sendOk("消耗栏空余不足2个空格！");
            cm.dispose();
			}else if(!cm.beibao(3,1)){
            cm.sendOk("设置栏空余不足1个空格！");
            cm.dispose();
			}else if(!cm.beibao(4,1)){
            cm.sendOk("其他栏空余不足1个空格！");
            cm.dispose();
			}else if(!cm.beibao(5,1)){
            cm.sendOk("现金栏空余不足1个空格！");
            cm.dispose();
			}else */		
			if(cm.getPlayer().getBossLog("每日签到") >= 1){//判断次数
			cm.sendOk("你已经领取过每日礼包！");
            cm.dispose();
			}else{
			//cm.gainItem(5151001, 1);//染色
			//cm.gainItem(5152001, 1);//整形
			//cm.gainItem(5153000, 1);//护肤
			//cm.gainMeso(999999);
			//cm.gainMeso(50000);   //金币
			cm.gainItem(5040000,2);//降落伞工作人员套装
			cm.gainItem(5390002,2);//小恐龙云豆帽子
			//cm.gainItem(5131000,1);//棒棒糖
			cm.gainItem(4032398,1);//小恐龙云豆套服
			cm.gainItem(5220000,5);//金鸡官帽
			cm.gainItem(5150040,1);//皇家理发券
			//cm.gainItem(5000053,1);//宠物：大猩猩
			//cm.gainItem(2022468,1);//稀有玩具抽奖箱子
			//cm.gainItem(2000002,200);//白色药水
			//cm.gainItem(2000003,200);//蓝色药水
			//cm.gainDY(8888);
            cm.sendOk("领取成功！");
			cm.getPlayer().setBossLog("每日签到");
			//cm.setBossLog("mrlb");//给予次数
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了每日签到礼包！");
            cm.dispose();
			}
		}
    }
}


