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
	qm.sendNextS("��#p1002104#˵����#m200000000#��������������֮��#p1002104#˵��ð���Щ�������#b#p1201000##k����", 3);
	qm.dispose();
}

function end(mode, type, selection) {
	qm.teachSkill(21100004, qm.getPlayer().getSkillLevel(21100004), 20);   // Combo Ability 
	qm.forceCompleteQuest();//�������
	qm.sendNextS("#r��������#k���ܾͽ�������!", 3);
	qm.dispose();
}