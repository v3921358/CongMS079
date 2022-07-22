/*
     乐 章
*/

var Price = 4000463;               //这里是设置价格的
var Price2 = 4000313; 
var 数字 = 100; 
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
						cm.sendGetNumber("#e请问需要兑换#z"+Price+"# #v"+Price+"#吗\r\n\r\n#r#v"+Price2+"##z"+Price2+"# "+数字+" 个，可兑换#r#v"+Price+"##z"+Price+"# 1 个#n\r\n您当前的#v"+Price2+"##z"+Price2+"#余额为 #r"+ cm.itemQuantity(Price2) +"#k\r\n【#d输入 #r1#d 则兑换1个#b#v"+Price+"##z"+Price+"#】\r\n\r\n#r请输入需要兑换的数字",1,1,10000);		 				 
        } else if (status == 1) {  
        			fee = selection;
                var jbyue = fee*数字;
        			if(cm.haveItem(Price2,fee*数字)){
            		cm.sendYesNo("您确认要兑换#r"+fee+"#k个#v"+Price+"# 吗？您将花费#r"+jbyue+"#k#v"+Price2+"# " );  
            	}else{
            		cm.sendOk("你的#v"+Price2+"##z"+Price2+"#不足无法兑换!");
            		cm.dispose();
            	}
	    	
        } else if (status == 2) {
        			if(cm.haveItem(Price2,fee*数字)){
								
								cm.gainItem(Price2,-fee*数字);
                                                                cm.gainItem(Price,fee);
								cm.sendOk("兑换成功!，您当前的 #v"+Price2+"##z"+Price2+"# 余额为 #r"+ cm.itemQuantity(Price2) +"#k !" );
        			}else{
        				cm.sendOk("你的 #v"+Price2+"##z"+Price2+"# 不足无法兑换!");
							}
							cm.dispose();
						
	   		}
          
  	 }
}

