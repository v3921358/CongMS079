var status = 0;
var ���� = "#fEffect/CharacterEff/1022223/4/0#";

var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var Сѩ�� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ����1 = "#fEffect/CharacterEff/1032063/0/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var �ٷ� = "#fEffect/CharacterEff/1003252/0/0#";
function start() {
	status = -1;
	action(1, 0, 0);
}
//1142371 2012����ѫ��
//1142360 ð�յ�ָ��ѫ��
var ring1=1112907;//�ʺ��ָ
var ring2=1112425;//�����Ĳʺ��ָ
var ring3=1112423;//׿Խ�Ĳʺ��ָ

var souvenir=4310029;//ʮ�ֱ�
var live1=1142208;//�ʺ絺����ֱ������ѫ��
var live2=1142790;//�ʺ絺����ֱ���ƽ�ѫ��
var live3=1142367;//�ʺ絺����ֱ����ʯѫ��
var live4=1142298;//�ʺ絺����ֱ���߲�ѫ��

var snowflake=4310066 ;//����������
var Trad1=1142006;//�ʺ絺��������ѫ�� 
var Trad2=1142441;//�ʺ絺�����ƽ�ѫ�� 
var Trad3=1142404;//�ʺ絺������ʯѫ�� 
var Trad4=1142408;//�ʺ絺�����߲�ѫ�� 

var natcoin=4032398;//��ϯͼ��
var nat1=1142508;//�ʺ絺Ա��ѫ��1142508
var nat2=1142510;//�ʺ絺����ѫ��1142510
var nat3=1142511;//�ʺ絺����ѫ��1142511
var nat4=1142509;//�ʺ絺����ѫ��1142509
var nat5=1142008;//�ʺ絺�߲�ѫ��1142008


function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status == 0) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;

		if (status == 0) {
        	var text=�ٷ�+"#d����,������ѫ�¶һ�ϵͳ��\r\n";
			text+="#k����Ҫ����ѫ���أ�\r\n";
			text+="#b#L0#"+����+"�ʺ絺ֱ��ѫ��#l\r\n";
			text+="#b#L1#"+����+"�ʺ絺����ѫ��#l\r\n";
			text+="#b#L2#"+����+"�ʺ絺Ա��ѫ��#l\r\n";
			//text+="#b#L3#"+����+"�ʺ絺����ѫ��#l\r\n";
			//text+="#b#L4#"+����+"�ʺ絺�ٷ���֤Ů��ѫ��#l\r\n";
			//text+="#b#L5#"+����+"�ʺ絺�Ͷ�ѫ��ѫ��#l\r\n";
			cm.sendSimple(text);
		} 
		else if(status==1){
			switch (selection){
				case 0://�ʺ絺ֱ��ѫ��
					var text1="#k��Ҫ�����ǣ�\r\n";
					var need=souvenir;
					text1+="#b#L0##i" + need +"##t" +need+"#x10 �һ� #v" + live1 +"##l\r\n";
					text1+="#b#L1##i" +live1 + "#x1 + " +"#i"+need +"##t" +need+"#x10 �һ� #v" + live2 +"##l\r\n";
					text1+="#b#L2##i" +live2 + "#x1 + " +"#i" + need +"##t" +need+"#x15 �һ� #v" + live3 +"##l\r\n";
					text1+="#b#L3##i" +live3 + "#x1 + " +"#i"+ need +"##t" +need+"#x30 �һ� #v" + live4 +"##l\r\n";
					
					cm.sendSimple(text1);
					break;
				case 1://�ʺ絺����ѫ��
					var text1="#k��Ҫ�����ǣ�\r\n";
					var need=snowflake;
					text1+="#b#L4##i" + need +"##t" +need+"#x50 �һ� #v" + Trad1 +"##l\r\n";
					text1+="#b#L5##i" +Trad1 + "#x1 + " +"#i"+need +"##t" +need+"#x50 �һ� #v" + Trad2 +"##l\r\n";
					text1+="#b#L6##i" +Trad2 + "#x1 + " +"#i" + need +"##t" +need+"#x80 �һ� #v" + Trad3 +"##l\r\n";
					text1+="#b#L7##i" +Trad3 + "#x1 + " +"#i"+ need +"##t" +need+"#x140 �һ� #v" + Trad4 +"##l\r\n";
					
					cm.sendSimple(text1);
					
					break;
				case 2://�ʺ絺Ա��ѫ��
					var text1="#k��Ҫ�����ǣ�\r\n";
					var need=natcoin;
					text1+="#b#L8##i" + need +"##t" +need+"#x15 �һ� #v" + nat1 +"##l\r\n";
					text1+="#b#L9##i" +nat1 + "#x1 + " +"#i"+need +"##t" +need+"#x20 �һ� #v" + nat2 +"##l\r\n";
					text1+="#b#L10##i" +nat2 + "#x1 + " +"#i" + need +"##t" +need+"#x30 �һ� #v" + nat3 +"##l\r\n";
					text1+="#b#L11##i" +nat3 + "#x1 + " +"#i"+ need +"##t" +need+"#x50 �һ� #v" + nat4 +"##l\r\n";
					text1+="#b#L12##i" +nat4 + "#x1 + " +"#i"+ need +"##t" +need+"#x80 �һ� #v" + nat5 +"##l\r\n";
					
					cm.sendSimple(text1);
					break;
			}
		}
		else if (status == 2){
			switch (selection){
				case 0:
					if(cm.haveItem(souvenir,10)){
						cm.gainItem(souvenir,-10);
						cm.gainItem(live1,8,8,8,8,50,50,5,5,5,5,5,5,5,5);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 1:
					if(cm.haveItem(souvenir,10) && cm.haveItem(live1,1)){
						cm.gainItem(souvenir,-10);
						cm.gainItem(live1,-1);
						cm.gainItem(live2,12,12,12,12,100,100,8,8,15,15,8,8,8,8);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 2:
					if(cm.haveItem(souvenir,15) && cm.haveItem(live2,1)){
						cm.gainItem(souvenir,-15);
						cm.gainItem(live2,-1);
						cm.gainItem(live3,18,18,18,18,150,150,12,12,30,30,9,9,9,9);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 3:
					if(cm.haveItem(souvenir,30) && cm.haveItem(live3,1)){
						cm.gainItem(souvenir,-30);
						cm.gainItem(live3,-1);
						cm.gainItem(live4,23,23,23,23,1500,1500,18,18,30,30,10,10,10,10);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				//	
				case 4:
					if(cm.haveItem(snowflake,50)){
						cm.gainItem(snowflake,-50);
						cm.gainItem(Trad1,5,5,5,5,25,25,2,2,10,10,3,3,3,3);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 5:
					if( cm.haveItem(Trad1,1 ) && cm.haveItem(snowflake,50)){
						cm.gainItem(snowflake,-50);
						cm.gainItem(Trad1,-1);
						cm.gainItem(Trad2,8,8,8,8,60,60,3,3,20,20,5,5,5,5);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 6:
					if( cm.haveItem(Trad2,1 ) && cm.haveItem(snowflake,80)){
						cm.gainItem(snowflake,-80);
						cm.gainItem(Trad2,-1);
						cm.gainItem(Trad3,15,15,15,15,80,80,5,5,30,30,7,7,7,7);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 7:
					if( cm.haveItem(Trad3,1 ) && cm.haveItem(snowflake,140)){
						cm.gainItem(snowflake,-140);
						cm.gainItem(Trad3,-1);
						cm.gainItem(Trad4,20,20,20,20,100,100,8,8,50,50,8,8,10,10);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				//
				case 8:
					if(cm.haveItem(natcoin,15)){
						cm.gainItem(natcoin,-15);
						cm.gainItem(nat1,10,10,10,10,50,50,5,5,30,30,3,3,3,3);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 9:
					if( cm.haveItem(nat1,1 ) && cm.haveItem(natcoin,20)){
						cm.gainItem(natcoin,-20);
						cm.gainItem(nat1,-1);
						cm.gainItem(nat2,15,15,15,15,200,200,5,5,50,50,4,4,4,4);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 10:
					if( cm.haveItem(nat2,1 ) && cm.haveItem(natcoin,30)){
						cm.gainItem(natcoin,-30);
						cm.gainItem(nat2,-1);
						cm.gainItem(nat3,18,18,18,18,400,400,10,10,100,100,6,6,6,6);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 11:
					if( cm.haveItem(nat3,1 ) && cm.haveItem(natcoin,50)){
						cm.gainItem(natcoin,-50);
						cm.gainItem(nat3,-1);
						cm.gainItem(nat4,22,22,22,22,600,600,12,12,150,150,8,8,8,8);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
				case 12:
					if( cm.haveItem(nat4,1 ) && cm.haveItem(natcoin,80)){
						cm.gainItem(natcoin,-80);
						cm.gainItem(nat4,-1);
						cm.gainItem(nat5,25,25,25,25,1500,1500,16,16,200,200,10,10,10,10);
						cm.sendOk("�һ��ɹ�������");
						cm.dispose();
					}
					else{
						cm.sendOk("�ף�����������Ʒ������~~");
						cm.dispose();
					}
					break;
			}
		}
		//cm.sendNext(text);
	}
}