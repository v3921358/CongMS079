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
            txt = "我是每日跑商任务NPC！第十轮.\r\n\r\n";

            if (cm.getBossLog("跑商任务") == 9){// cm.getPS()  的意思是 读取跑商值如果等于0 就得出他没有开始跑商 就运行他进行第一环跑商!
                txt += "#L1##b收集1个扎昆的象征#v4001083#交给我！\r\n\r\n#r任务奖励: [奖励经验"+cm.getLevel()*10000+" ] [奖励金币"+cm.getLevel()*10000+" ]#l#k";
                cm.sendSimple(txt);

					
            }else{
				if (cm.getBossLog("跑商任务") > 9){ 
                txt += "你已经完成过了然后了第十轮，继续进行下一环吧.!\r\n请第二天再来完成本环节！";
				}
                cm.sendOk(txt);
				
                cm.dispose();
            }
		
		
        } else if (selection == 1) {
            if (cm.haveItem(4001083,1)){
                cm.setBossLog("跑商任务");//跑环CD  的意思是 你完成跑商第一环的时候给予你 跑商值+1这样你就无法在重复做第一环了。只有凌晨12点刷新才行！
		
                cm.gainItem(4001083, -1);
                cm.gainMeso(cm.getLevel()*10000);//读取变量
                cm.gainExp(cm.getLevel()*10000);
				//cm.gainItem(5211047,1,3); //双倍经验
				//cm.gainItem(5360014,1,3); //双倍爆率
				cm.gainItem(4000313,50);//黄金枫叶
				//cm.gainvip(+3);
				cm.gainNX(3000);
                cm.sendOk("跑商第十轮完成![奖励经验"+cm.getLevel()*10000+" ] [奖励金币"+cm.getLevel()*10000+" ]、黄金枫叶*50、点券3000点、双倍经验三小时一张、双倍爆率卡三小时一张\r\n\r\n你已经完成过了然后了第十轮，继续进行下一环吧.");
                cm.dispose();
            }else{
                cm.sendOk("收集1个扎昆的象征#v4001083#交给我！");
                cm.dispose();
            }
        }
    }


}