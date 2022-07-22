/*
     乐 章
*/

var Price = 1000;               //这里是设置价格的
var Price2 = 4310014; 	//这是设置兑换道具
var svrName = "情怀冒险岛";       //这里用来设置服务器名字

//****以上为参数部分,您可以根据你的需要设置*******//

var fee;
var status = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
	cm.sendOk("需要的话随时来找我!!");
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.sendOk("需要的话随时来找我!!");
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
                   if (status == 0) {          	           	 
						cm.sendGetNumber("#e\t\t请问需要兑换 #z"+Price2+"# #v"+Price2+"# 吗\r\n\r\n\t #r" + Price + " 点卷可兑换 #r #z"+Price2+"# #v"+Price2+"# 1 个\r\n\r\n您当前的点卷余额为 #r"+ cm.getPlayer().getCSPoints(1) +"#k\r\n\r\n【#d输入#r1#d则兑换1个#b#z"+Price2+"##k】\r\n#r请输入兑换数字\r\n",1,1,10000);		 				 
        } else if (status == 1) {  
        			fee = selection;
                var jbyue = fee*Price;
        			if(cm.getPlayer().getCSPoints(1) >= fee*Price){
            		cm.sendYesNo("您确认要兑换#r"+fee+"#k个#v"+Price2+"#吗？您将花费#r"+jbyue+"#k点卷" );  
            	}else{
            		cm.sendOk("你的点卷不足无法兑换!");
            		cm.dispose();
            	}
	    	
        } else if (status == 2) {
        			if(cm.getPlayer().getCSPoints(1) >= fee*Price){
								cm.gainItem(Price2,fee);
						cm.getPlayer().modifyCSPoints(1, -fee*Price);
								cm.sendOk("兑换成功!，您当前的点卷余额为 #r"+ cm.getPlayer().getCSPoints(1) +"#k !" );
        			}else{
        				cm.sendOk("你的点卷不足无法兑换!");
							}
							cm.dispose();
						
	   		}
          
  	 }
}

