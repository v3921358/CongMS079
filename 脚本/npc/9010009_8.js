importPackage(Packages.client);
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
        if (status == 0) {
            var txt = "";
            txt = "我是每日跑商任务NPC！第五轮.\r\n\r\n";

				if (cm.getBossLog("跑商任务") == 4){
                txt += "#L1##b收集30个道符#v4000008#交给我！ [奖励经验"+cm.getLevel()*2500+" ] [奖励金币"+cm.getLevel()*5000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("跑商任务") > 4){ 
                txt += "你已经完成过了然后了第五轮，继续进行下一环吧.!\r\n请第二天再来完成本环节！";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000008,30)){
                cm.setBossLog("跑商任务");//跑环CD  的意思是 你完成跑商第一环的时候给予你 跑商值+1这样你就无法在重复做第一环了。只有凌晨12点刷新才行！
		
                cm.gainItem(4000008, -30);
                cm.gainMeso(cm.getLevel()*5000);//读取变量
                cm.gainExp(cm.getLevel()*2500);
				cm.gainItem(4000313,25);
				//cm.gainItem(5211047,1,3); //绑定的双倍经验3天
				//cm.gainItem(5360014,1,3); //绑定的双倍爆率3天
				cm.gainDY(3000);//给予抵用卷66666点 
                cm.sendOk("跑商第五轮完成![奖励经验"+cm.getLevel()*2500+" ] [奖励金币"+cm.getLevel()*5000+" ] 黄金枫叶25张、抵用券3000点、双倍经验三小时一张、双倍爆率卡三小时一张\r\n\r\n你已经完成过了然后了第五轮，继续进行下一环吧.");
                cm.dispose();
            }else{
                cm.sendOk("收集30个道符#v4000008#交给我!");
                cm.dispose();
            }
        }
    }
}
