var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��    Ψһ΢��:ZerekY   ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";
 var status = -1;

function start(mode, type, selection) {
	qm.forceStartQuest();//��ʼ����
	qm.dispose();
}

function end(mode, type, selection) {
	qm.teachSkill(21100000, qm.getPlayer().getSkillLevel(21100000), 20);
	qm.sendNextS("��л�������!�����ھͰѾ�׼ì���ڸ��㣡", 3);
	qm.gainExp(3900);
	qm.forceCompleteQuest();//�������
	qm.dispose();
}