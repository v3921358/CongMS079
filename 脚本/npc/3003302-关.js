var status = -1;
var job = 0;
var type = -1;
var skill = [[8]];

function start(){
	action(1, 0, 0);
}

function action(mode, type ,selection) {
	if(mode == 0 && status == 0) {
		status --;
	} else if(mode == 1) {
		status ++;
	} else {
		cm.dispose();
		return;
	}
	
	if (status == 0) {
		cm.sendYesNo("ѧϰ#s8#ͬʱ����ֻ�����~ֻ��1000��ȯ~�Ƿ�ѧϰ?");
	} else if (status == 1){
                if (cm.getPlayer().getCSPoints(1) >= 1000) {	//�жϵ�ȯ	
					if(cm.getJob() >=0 && cm.getJob() <= 900){
						cm.teachSkill(0000008, 1, 1);
						cm.gainNX(-1000);
						cm.sendOk("��ϲ��,�Ѿ�ѧ��!!");
						cm.dispose();
					} else if(cm.getJob() >=2000 && cm.getJob() <= 2112){
						cm.teachSkill(20000024, 1, 1);
						cm.gainNX(-1000);
						cm.sendOk("��ϲ��,�Ѿ�ѧ��!!");
						cm.dispose();	
					} else if(cm.getJob() >=1000 && cm.getJob() <= 1999){
						cm.teachSkill(10000018, 1, 1);
						cm.gainNX(-1000);
						cm.sendOk("��ϲ��,�Ѿ�ѧ��!!");
						cm.dispose();		
					}
                } else {
					cm.sendOk("��û���㹻�ĵ�ȯ���޷�ѧϰ��");
					cm.dispose();
                }
	}
}
