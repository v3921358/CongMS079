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
	//qm.sendNextS("#p1002104#˵�յ��˺ͺ�ɫ֮���йص��鱨������ȥ����#b#m200000000##k��#b����#p2012012##k����#p2012012#���Ӧ�ÿ����˽⵽��ϸ�������", 3);
	qm.dispose();
}

function end(mode, type, selection) {
	//qm.teachSkill(21100005, qm.getPlayer().getSkillLevel(21100005), 10);   // Combo Ability 
	qm.forceCompleteQuest();//�������
	qm.dispose();
}