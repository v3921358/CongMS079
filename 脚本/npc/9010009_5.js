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
            txt = "我是每日跑商任务NPC！第二轮.\r\n\r\n";

            if (cm.getBossLog("跑商任务") == 1){// cm.getPS()  的意思是 读取跑商值如果等于0 就得出他没有开始跑商 就运行他进行第一环跑商!
                txt += "#L1##b收集30个绿水灵珠#v4000010#交给我！[奖励经验"+cm.getLevel()*1000+" ] [奖励金币"+cm.getLevel()*2000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("跑商任务") > 1){ 
                txt += "你已经完成过了然后了第二轮，继续进行下一环吧.!\r\n请第二天再来完成本环节！";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000010,30)){
				cm.setBossLog("跑商任务");//跑环CD
                cm.gainItem(4000010, -30);
                cm.gainMeso(cm.getLevel()*2000);//读取变量
                cm.gainExp(cm.getLevel()*1000);
				cm.gainItem(4000313,10);
				cm.gainDY(1500);//给予抵用卷66666点 
                cm.sendOk("跑商第二轮完成![奖励经验"+cm.getLevel()*1000+" ] [奖励金币"+cm.getLevel()*2000+" ]黄金枫叶10张、抵用券1500点\r\n\r\n你已经完成过了然后了第二轮，继续进行下一环吧.");
                cm.dispose();
            }else{
                cm.sendOk("收集30个绿水灵珠#v4000010#交给我！");
                cm.dispose();
            }
        }
    }
}
