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
	qm.sendNextS("����ȥ#b#m250000000##k����˵��#m250000000#��#b#p2090004##k��������˽⵽��ϸ���������", 3);
	qm.forceStartQuest();//��ʼ����
	qm.dispose();
}

function end(mode, type, selection) {
	qm.forceCompleteQuest();//�������
	qm.dispose();
}