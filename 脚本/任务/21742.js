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
	//qm.sendNextS("����֮ǰ��#p2090004#��һ���Գ��Ǻ�ɫ֮���Ա������#r#o9300351##k����������õ���#t4220151#��������Ϊ���ܵ�ʧ��#t4220151#������ȫ�������ˡ����븴ԭ#t4220151#����������д����ʲô���ݡ�ȥ��#b��ʦ#p2091008##k��ѯ��ԭ#t4220151#�ķ�����,������#r����#k��", 3);
	//qm.removeAll(4220151);��ʱû�취�޸�
	//qm.gainItem(4220151, 1);
	qm.forceStartQuest(21742);//��ʼ����
	qm.forceCompleteQuest(21742);//�������
	qm.forceStartQuest(21743);//��ʼ����
	qm.forceCompleteQuest(21743);//�������
	qm.forceStartQuest(21744);//��ʼ����
	qm.forceCompleteQuest(21744);//�������
	qm.forceStartQuest(21745);//��ʼ����
	qm.forceCompleteQuest(21745);//�������
	qm.forceStartQuest(21746);//��ʼ����
	qm.forceCompleteQuest(21746);//�������
	qm.forceStartQuest(21747);//��ʼ����
	qm.forceCompleteQuest(21747);//�������
	qm.forceStartQuest(21748);//��ʼ����
	qm.forceCompleteQuest(21748);//�������
	qm.sendNextS("��ϲ��ѧ����ս��ͻ������,��������ֱ��������!��", 3);
	//qm.teachSkill(21100002,0,30,-1);//ս��ͻ��
	qm.teachSkill(21100002, qm.getPlayer().getSkillLevel(21100002), 30);   // Combo Ability 
	qm.dispose();
}


