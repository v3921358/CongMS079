var ��ɫС����1 ="#fEffect/CharacterEff/1112905/0/1#";
var ǳ��С���� ="#fMap/MapHelper/weather/balloon/5#";
var ��ɫС���� ="#fMap/MapHelper/weather/sweetHeart/0#";
var ��ɫС����1 ="#fMap/MapHelper/weather/sweetHeart/1#";
var ��ɫС����2 ="#fMap/MapHelper/weather/sweetHeart/2#";
var ��ɫС����3 ="#fMap/MapHelper/weather/sweetHeart/3#";
var ����С���� ="#fMap/MapHelper/weather/sweetHeart/5#";
var ���� = "#fEffect/CharacterEff/1051366/1/0#"; // ��ɫ����
var ��ݮ = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // ��ɫ��ݮ
var ��ݮ1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // ����ɫ��ݮ
var ��ݮ2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // ��ɫ��ݮ
var ��ݮ3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // ��ɫ��ݮ
var ��ݮ4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // ��ɫ��ݮ
var ��ݮ5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // ��ɫ��ݮ
function start() {
	
	var texts = "\t\t\t\t #v3992025##e�ζ�����#k#n#v3992025#\r\n\r\n";
		//texts += "#L11# "+ǳ��С����+" ���������˵�#l\r\n\r\n";
		
		
		//texts += "#L1#"+����+"װ���Ҿ��������#l\r\n\r\n";
		texts += "#L109#"+����+"װ������#l   \r\n\r\n";
		texts += "#L10999#"+����+"�ƹ�����#l   \r\n\r\n";
		texts += "#L106#"+����+"�ռ�BOSS#l\r\n\r\n";
		//texts += "#L1099#"+����+"��ת����#l\r\n\r\n";
		//\t\t#L522#"+����+"ʱװ����ϵͳ#k#l
		
		
		//texts += "#L10#"+����+"���ǿ������#l\t\t\t#L0#"+����+"HP/MPϴѪϴ��#l\r\n\r\n";
		//texts += "#L1#"+����+"װ���Ҿ��������#l\t\t#L8#"+����+"���߲���ǿ��#l\r\n\r\n";
		//texts += "#L6#"+����+"���˻����ǿ��#l\t\t  #L5##k"+����+"����װ��ǿ��#l\r\n\r\n";
		//texts += "#L522#"+����+"ʱװ����ϵͳ#k#l\r\n\r\n";
		
		
		
		//texts += "#L3#"+����+"������֮��ǿ������#l\t  #L2#"+����+"��֮��ǿ������#l\r\n\r\n";
		//texts += "#L18#"+ǳ��С����+"�๦��ǿ��ϵͳ#l #L20#"+����+"ħ����֮��ϵͳ#l\r\n";
		//texts += "#L4#�������[ǿ����֮��]#kX1000   "+����+"�۸�: #r40000 #k��ȯ#l\r\n";
		//texts += "#L7#�������[ǿ����֮��]#kX1000   "+����+"�۸�: #r40000 #k��ȯ#l\r\n";
		//texts += "#L9#�������[ħ����֮��]#kX1000   "+����+"�۸�: #r40000 #k��ȯ#l#l\r\n";
		
		cm.sendSimple(texts);
}

function action(mode, type, selection) {
	cm.dispose();
	if (selection == 0) {	//ϴѪϴ��
		cm.openNpc(9310059, "����ϴѪ");
		
	}else if (selection == 3368) {	
		cm.openNpc(9310059, "��������");
		
	}else if (selection == 106) {	
		cm.openNpc(9310059, "�ռ�BOSS");
		
	}
	else if (selection == 1) {	
		cm.openNpc(9310059, 10);
		
	}else if (selection == 1893) {	
		cm.openNpc(9310059, 1893);
		
	}
	else if (selection == 1894) {	
		cm.openNpc(9900004, 1894);
		
	}
	else if (selection == 109) {	
		cm.openNpc(9310059, 109);
		
	}
	else if (selection == 1099) {	
		cm.openNpc(9900004, "��ת����");
		
	}
	else if (selection == 10999) {	
		cm.openNpc(9900004, "�ƹ�����");
		
	}else if (selection == 3){
		cm.openNpc(9310059,3);
		
	}else if (selection == 4){
		if(cm.getPlayer().getNX() < 40000) {
			cm.sendOk("��ȯ����");
			cm.dispose();
		}else {
			cm.gainNX(-40000);
			cm.gainItem(3992025,1000);
			cm.sendOk("����ɹ���");
			cm.dispose();
		}
		
	}else if (selection == 5){
		cm.openNpc(9310059,4);
		
	}else if (selection == 522){
		cm.openNpc(9310059,522);
		
	}else if (selection ==6){
		cm.openNpc(9310059,5);
		
	}else if (selection == 7){
		if (cm.getPlayer().getNX() < 40000) {
			cm.sendOk("��ȯ����");
			cm.dispose();
		}else {
			cm.gainNX(-40000);
			cm.gainItem(3992025,1000);
			cm.sendOk("����ɹ���");
			cm.dispose();
		}
		
	}else if (selection ==8){
		cm.openNpc(9310059,6);
		
	}else if (selection ==18){
		cm.openNpc(9310059,2132);
		
	}else if (selection == 9){
		if(cm.getPlayer().getNX() < 40000) {
			cm.sendOk("�����");
			cm.dispose();
		}else {
			cm.gainNX(-40000);
			cm.gainItem(3992010,1000);
			cm.sendOk("����ɹ���");
			cm.dispose();
		}
		
	}else if (selection == 10) {	
		cm.openNpc(9310059, "װ��ǿ��");
		
	}else if (selection == 11) {	
		cm.openNpc(9310059, "��������");
		
}
}