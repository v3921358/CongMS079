var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //爱心
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE);//获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 大粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var 小粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //粉兔子
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //橙兔子
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //蓝兔子
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 音乐 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";

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
                
   cm.sendOk("感谢使用.");
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
	for(i = 0; i < 10; i++){
		text += "";
	}				
	text += "#e#d #r"+ 大黄星 +"快速转职"+ 大黄星 +"#b 需要以下物品：\r\n#v4000017##k#z4000017# * #r50#k个\t\t#v4002000##k#z4002000# * #r5#k个\r\n#v4000010##k#z4000010# * #r300#k个\t#v4000463##k#z4000463# * #r10#k个\r\n#v4001126##k#z4001126# * #r500#k个\r\n\r\n"
	text += "#e#b\t我收集了以上物品,同意使用快速转职功能，\r\n温馨提示：打开快速转职NPC即扣除任务道具，\r\n如没有确定好是否转职，请点击左下角【结束兑话】。\r\n";//七天
	text += "#L1##r#v4000017##e打开快速转职功能 【无法反悔了哦】#v4000017##l";//七天
	text += "     \r\n"
        cm.sendSimple(text);
		
        } else if (selection == 1) {
                      if(!cm.canHold(1012412,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
			
        } else if(cm.haveItem(4000017,50) && cm.haveItem(4002000,5) && cm.haveItem(4000010,300) && cm.haveItem(4000463,10) && cm.haveItem(4001126,500) ){
				cm.gainItem(4000017, -50);//猪头
				cm.gainItem(4002000, -5);//绿蜗牛邮票
				//cm.gainItem(4310022, -20);//樱花纪念币
				cm.gainItem(4000010, -300);//绿水灵珠
				cm.gainItem(4000463, -10);//国庆币
				cm.gainItem(4001126, -500);//枫叶
				cm.dispose();
                cm.openNpc(9900004, 553);
				
		}else{
            cm.sendOk("#e#r真遗憾，您无法开启快速转职功能\r\n\r\n#b提示1、您的 #z4000017# #v4000017# 不足50个\r\n提示2、您的 #z4002000# #v4002000# 不足5个\r\n提示3、您的 #z4000010# #v4000010# 不足300个\r\n提示4、您的 #z4000463# #v4000463# 不足10个\r\n提示5、您的 #z4001126# #v4001126# 不足500个");
            cm.dispose();
			}
		}
    }
}




