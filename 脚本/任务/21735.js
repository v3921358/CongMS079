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
	qm.removeAll(4032323);
	qm.gainItem(4032323, 1);
	qm.forceStartQuest();//��ʼ����
	qm.sendNextS("#p1002104#˵�Դ��ܵ�#o9300346#�Ĺ���֮�󣬾Ͷ�Աһ�������ڽ��������ҵ��˷�ӡʯ����˵�����Լ�����̫Σ�գ�Ӧ�ð��������ڵ��ϡ�#b#m140000000##kӦ�ñȽϰ�ȫ�����#b��ӡʯ����#p1201000#��#k", 3);
	qm.dispose();
}

function end(mode, type, selection) {
	qm.teachSkill(21100005, qm.getPlayer().getSkillLevel(21100005), 10);   // Combo Ability 
	qm.forceCompleteQuest();//�������
	qm.sendNextS("�úõĽ��#b������Ѫ#k���ܰɣ�", 3);
	qm.dispose();
}