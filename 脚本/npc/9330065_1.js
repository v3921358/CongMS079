/*
 
 �ű����ݣ���������
 �����ˣ�����.
 ����ʱ�䣺2020-10-18 14:24:10
 
 */
var dj = 0 

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		var selStr = "\t\t\t\t�㵱ǰ�Ѿ�ͻ��#r"+ cm.getOneTimeLog("���ɴ���") +"#k�Ρ�\r\n\r\n";
		selStr += "#L1##r��ȡͻ��5�ν���. - #v3700065#(��ͨ��֤I)#l\r\n\r\n";
		
		if(cm.getOneTimeLog("���ɴ���") > 5){
		selStr += "#L2##r��ȡͻ��10�ν���. - #v3700066#(��ͨ��֤II)#l\r\n\r\n";
		}
		
		if(cm.getOneTimeLog("���ɴ���") > 10){
		selStr += "#L3##r��ȡͻ��15�ν���. - #v3700068#(��ͨ��֤III)#l\r\n\r\n";
		}
		
		if(cm.getOneTimeLog("���ɴ���") > 15){
		selStr += "#L4##r��ȡͻ��20�ν���. - #v3700069#(��ͨ��֤IIII)#l\r\n\r\n";
		}
		
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
		    
		case 1:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("���ɴ���") < 5){
			cm.sendOk("#r���ͻ�ƴ���С��5��,�޷���ȡ����!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b��ĵȼ�����250��.�ﵽ250��֮��ſ�����ȡŶ.");
			cm.dispose();
			}else if(cm.getOneTimeLog("���ɴ���5����") >= 1){
			cm.sendOk("ÿ�׶ν׶ν���ֻ����ȡһ��!");	
			} else {
			cm.setOneTimeLog("����5");
			cm.gainItem(3700065,1);
			cm.sendOk("#r��ȡ�����ɹ�.\r\n\r\n���#v3700065#");
			cm.dispose();			
			}
		case 2:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("���ɴ���") < 10){
			cm.sendOk("#r���ͻ�ƴ���С��10��,�޷���ȡ����!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b��ĵȼ�����250��.�ﵽ250��֮��ſ�����ȡŶ.");
			cm.dispose();
			}else if(cm.getOneTimeLog("���ɴ���10����") >= 1){
			cm.sendOk("ÿ�׶ν׶ν���ֻ����ȡһ��!");	
			} else {
			cm.setOneTimeLog("���ɴ���10����");
			cm.gainItem(3700066,1);
			cm.sendOk("#r��ȡ�����ɹ�.\r\n\r\n���#v3700066#");
			cm.dispose();			
			}
		case 3:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("���ɴ���") < 15){
			cm.sendOk("#r���ͻ�ƴ���С��15��,�޷���ȡ����!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b��ĵȼ�����250��.�ﵽ250��֮��ſ�����ȡŶ.");
			cm.dispose();
			}else if(cm.getOneTimeLog("���ɴ���15����") >= 1){
			cm.sendOk("ÿ�׶ν׶ν���ֻ����ȡһ��!");	
			} else {
			cm.setOneTimeLog("���ɴ���15����");
			cm.gainItem(3700068,1);
			cm.sendOk("#r��ȡ�����ɹ�.\r\n\r\n���#v3700068#");
			cm.dispose();			
			}
		case 4:
			dj = cm.getPlayer().getLevel();
			if(cm.getOneTimeLog("���ɴ���") < 20){
			cm.sendOk("#r���ͻ�ƴ���С��20��,�޷���ȡ����!");
			cm.dispose();
			}else if( dj < 250 ){
			cm.sendOk("#b��ĵȼ�����250��.�ﵽ250��֮��ſ�����ȡŶ.");
			cm.dispose();
			}else if(cm.getOneTimeLog("���ɴ���20����") >= 1){
			cm.sendOk("ÿ�׶ν׶ν���ֻ����ȡһ��!");	
			} else {
			cm.setOneTimeLog("���ɴ���20����");
			cm.gainItem(3700069,1);
			cm.sendOk("#r��ȡ�����ɹ�.\r\n\r\n���#v3700069#");
			cm.dispose();			
			}
			
			
			
        }
    }
}