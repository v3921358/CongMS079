function action(mode, type, selection) {
  if (cm.getJob() == 132) {
	  var jobname="�s���Tʿ"
  } else if (cm.getJob() == 112) {
	  var jobname="�Ӣ��"
  } else if (cm.getJob() == 122) {
	  var jobname="�}֮�Tʿ"
  } else if (cm.getJob() == 212) {
	  var jobname="��ħ����"
  } else if (cm.getJob() == 222) {
	  var jobname="����ħ����"
  } else if (cm.getJob() == 232) {
	  var jobname="�������"
  } else if (cm.getJob() == 312) {
	  var jobname="�f��֮��"
  } else if (cm.getJob() == 322) {
	  var jobname="����"
  } else if (cm.getJob() == 412) {
	  var jobname="�[֮��"
  } else if (cm.getJob() == 422) {
	  var jobname="�s����"
  } else if (cm.getJob() == 512) {
	  var jobname="��ɫ�W��"
  } else if (cm.getJob() == 522) {
	  var jobname="���L"
  } else if (cm.getJob() == 2112) {
	  var jobname="���Y֮��"
  } else {
	  var jobname="����"
  }	
  if (cm.haveItem(3700150,1)){
	  if(cm.getBossLog("���ÿ�չ���")==0){
		  cm.setBossLog("���ÿ�չ���");
		  cm.setzb(5);
		  cm.gainItem(4310057,1);
		  cm.ȫ��Ư������("����������["+cm.getName()+"]�����״ε�¼����:���� �� 5 �һ��سɾ�����ϡ�1", 5121015);
		cm.����(1,jobname+"������������["+cm.getName()+"]�����״ε�¼С����ð�յ��ɹ���ȡ�����չ���:�һ��سɾ�����ϡ�1 ���֡�5��");
                cm.dispose();
	  } else {
	  cm.����(1,jobname+"������������["+cm.getName()+"]�����ˡ�");
                cm.dispose();
	  }
  } else if (cm.haveItem(3700149,1)){
	  if(cm.getBossLog("���ÿ�չ���")==0){
		  cm.setBossLog("���ÿ�չ���");
		  cm.setzb(10);
		  cm.gainItem(4310057,1);
		  cm.ȫ��Ư������("���ľ���������["+cm.getName()+"]�����״ε�¼����:���� �� 10 �һ��سɾ�����ϡ�1", 5121005);
		cm.����(3,jobname+"�����ľ���������["+cm.getName()+"]�����״ε�¼С����ð�յ��ɹ���ȡ�����չ���:�һ��سɾ�����ϡ�1 ���֡�10��");
                cm.dispose();
	  } else {
	  cm.����(3,jobname+"�����ľ���������["+cm.getName()+"]�����ˡ�");
                cm.dispose();
	  }
  } else if (cm.haveItem(3700148,1)){  
  if(cm.getBossLog("���ÿ�չ���")==0){
		  cm.setBossLog("���ÿ�չ���");
		  cm.setzb(18);
		  cm.gainItem(4310057,1);
		  cm.ȫ��Ư������("���ް�������ƻ�Ա["+cm.getName()+"]�����״ε�¼����:���� �� 18 �һ��سɾ�����ϡ�1", 5121002);
		cm.����(2,jobname+"�����ް�������ƻ�Ա["+cm.getName()+"]�����״ε�¼С����ð�յ��ɹ���ȡ�����չ���:�һ��سɾ�����ϡ�1 ���֡�18��");
                cm.dispose();
	  } else {
	  cm.����(2,jobname+"�����ް�������ƻ�Ա["+cm.getName()+"]�����ˡ�");
                cm.dispose();
	  }
  } else {
	cm.����(1,jobname+"��["+cm.getName()+"]�����ˡ�");
                cm.dispose();
          }

}
