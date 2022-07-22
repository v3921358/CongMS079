

var Price = 1000;               //这里是设置价格的
var Price2 = 4310014; 
var svrName = "冒险岛";       //这里用来设置服务器名字

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
						cm.sendGetNumber("#e请问需要兑换点卷吗\r\n#r#z"+Price2+"# #v"+Price2+"# 1个 可兑换 #r"+Price+" 点卷\r\n\r\n您当前的 #z"+Price2+"# #v"+Price2+"# 余额为 #r"+ cm.itemQuantity(Price2) +"#k 个\r\n\r\n【#d输入#r1#d则扣除1个#b#z"+Price2+"##k】\r\n\r\n#r请输入需要兑换的数字：",1,1,10000);		 				 
        } else if (status == 1) {  
        			fee = selection;
                                var jbyue = fee*Price;
        			if (cm.haveItem(Price2,fee)){
            		cm.sendYesNo("您确认要兑换#r"+jbyue+"#k点卷吗？您将消耗#r"+fee+"#k个#v"+Price2+"#？" );  
            	}else{
            		cm.sendOk("你的#v"+Price2+"#不足无法兑换!");
            		cm.dispose();
            	}
	    	
        } else if (status == 2) {
        			if (cm.haveItem(Price2,fee)){
								cm.gainItem(Price2,-fee);
						cm.getPlayer().modifyCSPoints(1, fee*Price);
								cm.sendOk("兑换成功!，您当前的点卷余额为 #r"+ cm.getPlayer().getCSPoints(1) +"#k !" );
        			}else{
        				cm.sendOk("你的#v"+Price2+"#不足无法兑换!");
							}
							cm.dispose();
						
	   		}
          
  	 }
}

