/*
*   交换商店，增强版  NPC
*   By: ZreHy_MS               如果需要改动，请自行修改，本人开服用的~
*/

var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendSimple ("您好，尊敬的 #b#h ##k, 我是" + cm.getChannelServer().getServerName() + "的#r破功系统功能#k负责人\r\n\r\n每增加一级VIP等级，破功上限增加10W\r\n\r\n您目前VIP等级为： #e#r" + cm.getvip() + "#k#n \r\n#L2##e2500点券=====#r提升1级VIP#k#n#l#k\r\n\r\n");
        } else if (status == 1) {
            switch(selection) {
        case 2:
            if(cm.getPlayer().getNX() > 2500){
                cm.sendOk("恭喜你，你提升了1级VIP等级! .");
				cm.gainvip(+1);
                cm.gainNX(-2500);
				cm.worldMessage(6,"【破攻系统】["+cm.getName()+"]提升了1级VIP等级!");
                cm.dispose();
            }else{
                cm.sendOk("你没有 足够的点券，我不能给你换购~.");
                cm.dispose();
            }
            break;
        case 1: 
            if(cm.getMeso() >= 1000000){
                cm.sendOk("恭喜你，你获得了 10000 点券! .");
                cm.gainMeso(-1000000);
                cm.gainNX(10000);
                cm.dispose();
            }else{
                cm.sendOk("你没有 1000000 金币，我不能给你换购~.");
                cm.dispose();
            }
            break;
         case 0: 
            if(cm.haveItem(4001126,100)){
                //cm.gainDY(100);
                cm.gainvip(+1);
				cm.gainItem(4001126,-100);
				cm.sendOk("恭喜你，你提升了1级VIP等级! .");
			        cm.worldMessage(6,"【破攻系统】["+cm.getName()+"]提升了1级VIP等级!");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 枫叶，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 3: 
            if(cm.haveItem(4001126,100)){
                //cm.gainDY(100);
                cm.gainMeso(2000000);
				cm.gainItem(4001126,-100);
				cm.sendOk("恭喜你，你获得了 2000000 金币! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]100张枫叶兑换2000000金币成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 枫叶，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 4: 
            if(cm.haveItem(4000313,10)){
                //cm.gainDY(100);
                cm.gainMeso(2000000);
				cm.gainItem(4000313,-10);
				cm.sendOk("恭喜你，你获得了 2000000 金币! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]10张黄金枫叶兑换2000000金币成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 黄金枫叶，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 5: 
            if(cm.haveItem(4032226,100)){
                //cm.gainDY(100);
                cm.gainvip(+10);
				cm.gainItem(4032226,-100);
				cm.sendOk("恭喜你，你提升了10级VIP等级! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]100只黄金猪猪提升10级VIP等级!成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 黄金猪猪，我不能给你换购~.");
                cm.dispose();
            }
            break;
           case 6: 
            if(cm.haveItem(4001126,100)){
                cm.gainItem(4000313,10);
				cm.gainItem(4001126,-100);
				cm.sendOk("恭喜你，你获得了 10张黄金枫叶! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]100张枫叶兑换10张黄金枫叶成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 枫叶，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 7: 
            if(cm.getMeso() >= 10000000){
                cm.sendOk("恭喜你，你获得了 2000000 经验值! .");
                cm.gainMeso(-10000000);
                cm.gainExp(2000000);
                cm.dispose();
            }else{
                cm.sendOk("你没有 10000000 金币，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 8: 
            if(cm.getMeso() >= 100000000){
                cm.sendOk("恭喜你，你获得了 50000000 经验值! .");
                cm.gainMeso(-100000000);
                cm.gainExp(50000000);
                cm.dispose();
            }else{
                cm.sendOk("你没有 1亿 金币，我不能给你换购~.");
                cm.dispose();
            }
            break;
            }
        }
    }
}