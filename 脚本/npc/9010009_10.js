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
            txt = "我是每日跑商任务NPC！第七轮.\r\n\r\n";

            if (cm.getBossLog("跑商任务") == 6){// cm.getPS()  的意思是 读取跑商值如果等于0 就得出他没有开始跑商 就运行他进行第一环跑商!
                txt += "#L1##b收集50个星光精灵的星块#v4000059#交给我！[奖励经验"+cm.getLevel()*3500+" ] [奖励金币"+cm.getLevel()*7000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("跑商任务") > 6){ 
                txt += "你已经完成过了然后了第七轮，继续进行下一环吧.!\r\n请第二天再来完成本环节！";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000059,50)){
                cm.setBossLog("跑商任务");//跑环CD  的意思是 你完成跑商第一环的时候给予你 跑商值+1这样你就无法在重复做第一环了。只有凌晨12点刷新才行！
		
                cm.gainItem(4000059, -50);
                cm.gainMeso(cm.getLevel()*7000);//读取变量
                cm.gainExp(cm.getLevel()*3500);
				cm.gainItem(4000313,35);
				cm.gainItem(4001129,3);
				cm.gainNX(1500);
                cm.sendOk("跑商第七轮完成![奖励经验"+cm.getLevel()*3500+" ] [奖励金币"+cm.getLevel()*7000+" ]黄金枫叶35张、冒险纪念币3个、点券1500点\r\n\r\n你已经完成过了然后了第七轮，继续进行下一环吧.");
                cm.dispose();
            }else{
                cm.sendOk("收集100个星光精灵的星块#v4000059#交给我!");
                cm.dispose();
            }
        }
    }
}
