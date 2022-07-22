var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
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
                text += "在我这里可以购买双倍增益哦！\r\n\r\n";
            			
            //text += "" + 蓝色箭头 + "#L1##b#e小黑板 (24小时)#v1142145#需要：点卷=1200点#l \r\n\r\n"//3				
            text += "" + 蓝色箭头 + "#L2##b#e黑板#v5370001#   （24小时）    需要：抵用券=500点#l \r\n\r\n"//3				
           // text += "" + 蓝色箭头 + "#L3##b#e双倍经验卡 （12小时）#v5211047#需要：点卷=500点#l \r\n\r\n"//3				
            //text += "" + 蓝色箭头 + "#L9##b#e双倍爆率卡（6小时）#v5360014#需要：点卷=300点#l \r\n\r\n"//3
			//text += "" + 蓝色箭头 + "#L4##b#e双倍经验卡 （一周）#v5210001#需要：点卷=3000点#l \r\n\r\n"//3	
			//text += "" + 蓝色箭头 + "#L5##b#e双倍爆率卡（一周）#v5360016#需要：点卷=3000点#l \r\n\r\n"//3
			//text += "" + 蓝色箭头 + "#L11##r购买#v5520000#一把需要：充值币=20#l \r\n\r\n"//3
            //text += "" + 蓝色箭头 + "#L14##r#e#v5211060##z5211060# （7天权）需要：充值币10个\r\n\r\n"//3
		    //text += "" + 蓝色箭头 + "#L13##r#e扩充#r角色位一个需要：充值币30个+5500点券#l \r\n\r\n"//3
			cm.sendSimple(text);
            }
			
			
			} else if (selection == 13) {
                if(cm.getmoneyb() < 30){
				cm.sendOk("充值币不足无法换购！");
                cm.dispose();
				} else if (cm.getPlayer().getNX() >= 5500) {
					cm.gainNX(-5500);
				cm.increaseCharacterSlots(0);//
				 cm.setmoneyb(-30);	
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[角色位1个]");
            cm.dispose();
			}else{
            cm.sendOk("点券不足无法换购！");
            cm.dispose();
               }
			   
			   } else if (selection == 11) {
               // if (cm.getPlayer().getNX() >= 5000) {
				   if(cm.getmoneyb() >= 20){
				cm.setmoneyb(-20);	
				cm.gainItem(5520000,1,1);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[宿命剪刀]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
			   
        } else if (selection == 1) {
                if (cm.getPlayer().getNX() >= 1200) {
				cm.gainNX(-1200);
				cm.gainItem(1142145,1,12);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[网吧勋章经验增加30%（12小时）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 2) {
                if (cm.getPlayer().getDY() >= 500) {
				cm.gainDY(-500);
				cm.gainItem(5370001,1,24);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "] 用500抵用卷 成功购买 [黑板（24小时权）] ");
            cm.dispose();
			}else{
            cm.sendOk("抵用券不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 3) {
                if (cm.getPlayer().getNX() >= 500) {
				cm.gainNX(-500);
				cm.gainItem(5211047,1,12);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍经验卡（12小时权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 4) {
               if (cm.getPlayer().getNX() >= 3000) {
				cm.gainNX(-3000);
				cm.gainItem(5210001,1,168);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍经验卡（7天权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("充值币不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 5) {
                if (cm.getPlayer().getNX() >= 3000) {
				cm.gainNX(-3000);
				cm.gainItem(5360016,1,168);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍爆率卡（一周权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("充值币不足无法换购！");
            cm.dispose();
			}
			
			} else if (selection == 14) {
                if(cm.getmoneyb() >= 10){
				 cm.setmoneyb(-10);	
				cm.gainItem(5211060,1,168);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[三倍经验卡（168小时权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 6) {
                if (cm.getPlayer().getNX() >= 300) {
				cm.gainNX(-300);
				cm.gainItem(5360014,1,10);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍爆率卡（10小时权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 7) {
                if (cm.getPlayer().getNX() >= 600) {
				cm.gainNX(-600);
				cm.gainItem(5360014,1,24);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍爆率卡（24小时权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 8) {
                if (cm.getPlayer().getNX() >= 2000) {
				cm.gainNX(-2000);
				cm.gainItem(5360014,1,168);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍爆率卡（7天权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("点卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 9) {
                if (cm.getPlayer().getNX() >= 300) {
				cm.gainNX(-300);
				cm.gainItem(5360014,1,6);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍爆率卡（6小时权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("抵用卷不足无法换购！");
            cm.dispose();
			}
        } else if (selection == 10) {
                if (cm.getPlayer().getDY() >= 600) {
				cm.gainDY(-600);
				cm.gainItem(5360014,1,3);
cm.喇叭(2, "[" + cm.getPlayer().getName() + "]成功购买[双倍爆率卡（3小时权）]，爆肝愉快~");
            cm.dispose();
			}else{
            cm.sendOk("抵用卷不足无法换购！");
            cm.dispose();
			}
		}
    }
}


