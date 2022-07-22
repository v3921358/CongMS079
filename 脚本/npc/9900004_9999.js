var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 金币 = "#fItem/Special/0900.img/09000001/iconRaw/1#";
var 点券 = "#fUI/CashShop/CashItem/0#";
var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
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
                text += ""+奖励+"\r\n"
                text += "#k#v4000463#×12 #v4032391#×3 #v4000038#×6\r\n";
                text += "#v4001126#×200 #v4000313#×20 #v4032392#×3\r\n";
				text += ""+金币+"冒险币 30万 \r\n";
				text += "领取时请将会员VIP③#v1142623#放入背包中，并确保背包其他栏有空格#k#n\r\n";
                text += "\t\t\t\t#L1#"+蓝色箭头+"领取VIP③每日礼包\r\n\r\n";//3
            cm.sendSimple(text);
           }
        } else if (selection == 1) {
if(cm.getBossLog('福利3') >0){
cm.sendOk("你今天已经领取过一次");
cm.dispose();
}
/*else if (cm.getzb()<0)
{
	cm.sendOk("你的充值积分不足1000,无法会员VIP③每日礼包");
cm.dispose();
}*/
          else  if (cm.haveItem(1142623, 1)) {

if(cm.canHold(4002003, 400) && cm.canHold(1122017, 1))
{
//cm.gainNX(6000);//点卷
//cm.getPlayer().modifyCSPoints(2, 15000, true);//抵用
cm.gainMeso(300000);
cm.gainItem(4000463,12);//国庆币
cm.gainItem(4000313,20);//进阶币
cm.gainItem(4032392,3);//破碎的碎片2
cm.gainItem(4032391,3);//破碎的碎片1
cm.gainItem(4001126,200);//枫叶
cm.gainItem(4000038,6)//金杯
cm.setBossLog('福利3');

cm.喇叭(1,"[会员公告]:会员③玩家【"+cm.getPlayer().getName()+"】领取了今天的会员VIP③每日礼包！");
cm.dispose();
}
else
{
  cm.sendOk("请清理背包,确保有足够空间!\r\n");
   cm.dispose();
}
			}else{
            cm.sendOk("请确认已经将会员VIP③勋章放入背包");
            cm.dispose();
			}
        } 
		
    }
}
