importPackage(net.sf.odinms.client);
var status = 0;







	function start() {
		status = -1;
		action(1, 0, 0);
		}
	function action(mode, type, selection) {
		if (mode == -1) {
		cm.dispose();
		} else {
		if (status >= 0 && mode == 0) {
		cm.dispose();
		return;
		}
		if (mode == 1)
		status++;
		else
		status--;


	if (status == 0) {

	
	   var textz = "\r\n您好，尊敬的 #b#h ##k,欢迎来到追梦冒险岛\r\n";


		textz += "#r#e赞助100:1万点卷  充值联系群主qq#k#n:#e#r记得联系群主#k#n\r\n\r\n";
		textz += "#r#e当前点卷："+cm.getPlayer().getCSPoints(1)+"#k#n\r\n\r\n";
		

		textz += "#L5##b2000点卷兑换1个#v4033498##k#n#l\r\n"
		//textz += "#L6##b2000点卷兑换1张#v2460005##k#n#l\r\n"
		//textz += "#L7##b2000点卷兑换1张#v4032323##k#n#l\r\n"

                cm.sendSimple(textz);  
	}else if (status == 1) {

	if (selection == 1) {
		if(cm.getNX(1)>=50000){
			cm.gainNX(-50000);
			cm.gainMeso(50000000);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【兑换系统】["+cm.getName()+"]兑换了5000万金币！");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}
	}else if (selection == 2) {
		if(cm.getNX(1)>=100000){
			cm.gainNX(-100000);
			cm.gainMeso(100000000);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【兑换系统】["+cm.getName()+"]兑换了1亿金币！");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}
	}else if (selection == 3) {
		if(cm.getNX(1)>=1000){
			cm.gainNX(-1000);
			cm.gainItem(5121015,1);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【兑换系统】["+cm.getName()+"]兑换了雪孩子");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}
	}else if (selection == 4) {
		if(cm.getNX(1)>=1000){
			cm.gainNX(-1000);
			cm.gainItem(5121020,1);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【兑换系统】["+cm.getName()+"]兑换了喜洋洋");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}
	}else if (selection == 5) {
		var prompt = "你想要一些#t" + 2460005 + "#? 那样的话，你想要多少个？";
			
		cm.sendGetNumber(prompt,1,1,100);
		beaty = 1;
	}else if (selection == 6) {
		var prompt = "你想要一些#t" + 5220000 + "#? 那样的话，你想要多少个？";
			
		cm.sendGetNumber(prompt,1,1,100);
		beaty = 2;
	}else if (selection == 7) {
		var prompt = "你想要一些#t" + 4032226 + "#? 那样的话，你想要多少个？";
			
		cm.sendGetNumber(prompt,1,1,100);
		beaty = 3;
	}else if (selection == 8) {
		var prompt = "你想要一些#t" + 4110010 + "#? 那样的话，你想要多少个？";
			
		cm.sendGetNumber(prompt,1,1,100);
		beaty = 4;	
	}
}else if ( status == 2){
	if(beaty == 1){
		if(cm.getNX(1)>= 2000 * selection){
			cm.gainNX(-2000* selection);
			cm.gainItem(4033498,1* selection);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(4,"【点卷兑换】["+cm.getName()+"]兑换了发亮的水晶");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}
	}else if(beaty == 2){
		if(cm.getNX(1)>=2000 * selection){
			cm.gainNX(-2000 * selection);
			cm.gainItem(2460005,1 * selection);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【点卷兑换】["+cm.getName()+"]兑换了鉴定放大镜");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}
	}else if(beaty == 3){
		if(cm.getNX(1)>=2000 * selection){
			cm.gainNX(-2000 * selection);
			cm.gainItem(4032323,1 * selection);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【点卷兑换】["+cm.getName()+"]兑换了闪耀水晶");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();
		}	
	}else if(beaty == 4){
		if(cm.getNX(1)>=5000 * selection){
			cm.gainNX(-5000 * selection);
			cm.gainItem(4110010,1 * selection);
			cm.sendOk("恭喜你，兑换成功! .");
			cm.喇叭(3,"【点卷兑换】["+cm.getName()+"]兑换了豆豆票");
			cm.dispose();
		}else{
			cm.sendOk("抱歉，您的点卷不足~.");
			cm.dispose();	
		}
	}
}
}
}
