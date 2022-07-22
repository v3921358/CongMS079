var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 忠告 = "#k温馨提示：任何非法程序和外挂封号处理.封杀侥幸心理.";
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
            
          
            var txt1 = "#L2#" + 蓝色箭头 + "#d#v1012292#兑换全属性2，需要#d#v4170018#*66\r\n\r\n";
            var txt2 = "#L3#" + 蓝色箭头 + "#d#v1012293#升级全属性5，需要#d#v4170007#*66\r\n\r\n";
            var txt3 = "#L4#" + 蓝色箭头 + "#d#v1012294#升级全属性8，需要#d#v4170006#*66\r\n\r\n";
            var txt4 = "#L5#" + 蓝色箭头 + "#d#v1012295#升级全属性12，需要#d#v4170005#*66\r\n\r\n";
            var txt5 = "#L6#" + 蓝色箭头 + "#d#v1012291#升级全属性18，需要#d#v4170004#*66\r\n\r\n";
       

            cm.sendSimple("#r血面制作中心,每次升级都需要上一级物品#k\r\n"+ txt1 + ""+ txt2 + ""+ txt3 + ""+ txt4 + ""+ txt5 + "");
            

        } else if (status == 1) {
             if (selection == 2) { //更多功能
             if((cm.haveItem(4170018,66))) {
                        cm.gainItem(4170018,-66);
                       cm.gainItem(1012292,2,2,2,2,200,200,2,2,0,0,0,0,0,0);//勋章
            cm.sendOk("面具兑换成功！");
			
                   cm.dispose();
			}else{
cm.sendOk("你没有以下物品,不可制作!\r\n#d#v4170018#X66\r\n");
	     	   cm.dispose();
}
            } else if (selection == 3) { //更多功能
   if((cm.haveItem(4170007,66))&& (cm.haveItem(1012292,1))) {
                        cm.gainItem(4170007,-66);
                        cm.gainItem(1012292,-1);
                       cm.gainItem(1012293,5,5,5,5,500,500,5,5,0,0,0,0,0,0);//勋章
            cm.sendOk("面具兑换成功！");
			
                   cm.dispose();
			}else{
cm.sendOk("你没有以下物品,不可制作!\r\n#d#v1012292#X1#v4170007#X66\r\n");
	     	   cm.dispose();
}
            } else if (selection == 4) { //更多功能
   if((cm.haveItem(4170006,66))&& (cm.haveItem(1012293,1))) {
                        cm.gainItem(4170006,-66);
                        cm.gainItem(1012293,-1);
                       cm.gainItem(1012294,8,8,8,8,800,800,8,8,0,0,0,0,0,0);//勋章
            cm.sendOk("面具兑换成功！");
			
                   cm.dispose();
			}else{
cm.sendOk("你没有以下物品,不可制作!\r\n#d#v4170006#X66#v1012293#X1\r\n");
	     	   cm.dispose();
}
            } else if (selection == 5) { //更多功能
  if((cm.haveItem(4170005,66))&& (cm.haveItem(1012294,1))) {
                        cm.gainItem(4170005,-66);
                        cm.gainItem(1012294,-1);
                       cm.gainItem(1012295,12,12,12,12,1000,1000,12,12,0,0,0,0,0,0);//勋章
            cm.sendOk("面具兑换成功！");
			
                   cm.dispose();
			}else{
cm.sendOk("你没有以下物品,不可制作!\r\n#d#v4170004#X66#v1012294#X1\r\n");
	     	   cm.dispose();
}
            } else if (selection == 6) { //更多功能
   if((cm.haveItem(4170004,66))&& (cm.haveItem(1012295,1))) {
                        cm.gainItem(4170004,-66);
                        cm.gainItem(1012295,-1);
                       cm.gainItem(1012291,18,18,18,18,3000,3000,18,18,0,0,0,0,0,0);//勋章
            cm.sendOk("面具兑换成功！");
			
                   cm.dispose();
			}else{
cm.sendOk("你没有以下物品,不可制作!\r\n#d#v4170004#X66#v1012295#X1\r\n");
	     	   cm.dispose();
}
            }
        }
    }
}
