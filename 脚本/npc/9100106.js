var bl = 200; // 多少点卷兑换物品
var wpid = 3994048; // 点卷兑换的物品的id
var wpsx = 720; // 小时
var mrjlid = 2050004; // 每日奖励的id
var mrjlbl = 100; // 每日奖励的倍率
var ca = java.util.Calendar.getInstance();
var bosslog = '倍数兑换'; // bosslog的状态值
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);//判断星期

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
            var text = "";
            text += "【跳跳奖励领取中心】\r\n\r\n";
            text += "【周四#r20:30-20:40】\r\n\r\n";
            text += "【#d当前服务器时间: #r" + hour + " #b点 #r" + minute + " #b分】\r\n\r\n";
            text += "#L1#领取今日跳跳奖励:#v4001129#*1 #v4001126#*100 #v4000313#*30 \r\n\r\n";
            cm.sendSimple(text);
        }
        else if (status == 1) {
            if (selection == 1) {
              if(cm.getBossLog('周四活动')<1 && weekday == 5 &&hour == 20 && minute >= 30 && minute <= 40){
			 cm.setBossLog('周四活动');
			 cm.gainItem(4001129,1);
			 cm.gainItem(4001126,100);//进阶币
			 cm.gainItem(4000313,30);//国庆币

            cm.sendOk("领取奖励");
			cm.dispose();
		
			}else{
            cm.sendOk("#r当前服务器时间: #r" + hour + " #b点 #r" + minute + " #b分活动未开启\r\n或者已经领取过一次!");
			cm.dispose();
        
			}
            } else if (selection == 2) {
                if (cm.getPlayer().getBossLog("bosslog")>0) {
                    cm.sendOk("今天已经领取过，请明天在来!");
                    cm.dispose();
                    return ;
                }
           
                //cm.gainItem(2460005, +10);
				  //cm.gainItem(261400, +10);
				   //cm.gainItem(2531000, +10);
				 
                cm.getPlayer().setBossLog("bosslog"); //给一天次数记录
				cm.warp(910000000);
                cm.sendOk("今日奖励领取成功");
				cm.喇叭(4,"玩家["+cm.getPlayer().getName()+"]完成今日跳跳任务");
                cm.dispose();
            }
            else {
                cm.dispose();
            }
        }
        else {
            cm.dispose();
        }
    }
}