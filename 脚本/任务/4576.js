/* ==================
 �ű�����:  ����	    
 �ű���Ȩ����Ϸ���Ŷ�
 ��ϵ�ۿۣ�297870163    609654666
 =====================
 */
 var status = -1;

function start(mode, type, selection) {
	if (mode == -1) {
		qm.dispose();
	} else {
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			if(qm.getPlayer().getBossLog("mhuan") == 1){
			qm.sendOk("���Ѿ�����������,������������");
			qm.dispose();
			}else{
			qm.sendNext("���Ҹ��̼��ֺ���ĵط���?�λ����⹫԰���������һ��ѡ.��,����λ����⹫԰���е�Σ��.��֪����ô����?");
			}
		} else if (status == 1) {
            qm.sendNext("�ҵĺ���,#b������³#k���������λ����⹫԰����һȺ����Χ��,������Ψһһ�����ڴ������,����Ⱥ����һ���һ��ǿ��,���ǳ���Ҫ���˵İ���.");
			
		} else if (status == 2) {
            qm.sendAcceptDecline("��������һ��֮�������λ����⹫԰�İ�ȫ��?");
		} else if (status == 3) {
			if(qm.getPlayer().getLevel() < 120){
					qm.sendOk("#b120��������ȡŶ.");
		            qm.dispose();	
			}else{
			qm.gainItem(4032246, 1);
			qm.getPlayer().setBossLog('mhuan');
			qm.completeQuest();
            qm.sendOk("̫����!�����ڲ����뿪�ʰ��,#b������³#k֪���й����λ����⹫԰��ֻ���������,��ȥ��#b������³#k��,���������һ������ô��.");
			qm.dispose();}
		} 
	}
}