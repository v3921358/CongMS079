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
var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var MaplePacketCreator = Java.type('tools.MaplePacketCreator');

var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var �����Ʒ = "#v1302000#";
var add = "#fEffect/CharacterEff/1112903/0/0#";//������
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//��ɫ�Ҽ�ͷ
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//ѡ�����
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //����
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var �۰��� = "#fItem/Etc/0427/04270005/Icon8/1#";  //
var �ջ� = "#fUI/PredictHarmony/card/19#";//��ƬЧ���ջ�
var Ц = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/0#";//Ц��
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ��Ů ="#fMap/MapHelper/weather/witch/0#";//��Ů
var ���� ="#fMap/MapHelper/weather/balloon/4#";//����
var ��� ="#fMap/MapHelper/weather/LoveEffect2/4/0#";//���
var õ�� ="#fMap/MapHelper/weather/rose/0#";//õ�廨
var �̻� ="#fMap/MapHelper/weather/squib/squib1/3#";//�̻�

var ��ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var С�ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //������
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //������
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //������
var а��С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var а��С��2 = "#fEffect/CharacterEff/1112960/3/1#";  //а��С�� ����
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ����Ҷ ="#fMap/MapHelper/weather/maple/3#";
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";
var ���� ="#fMap/MapHelper/weather/witch/3#";
var ���1 = "#v5680069#";
var ���� = "#fUI/UIWindow.img/Quest/reward#";
var ��� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var ��ȯͼ�� = "#fUI/CashShop/CashItem/0#";
var ���� ="#fMap/MapHelper/weather/balloon/4#";//����



//Ŀ¼����
var fyszML = new Array(
{ ���: 0,Ŀ¼: 30 },
{ ���: 1,Ŀ¼: 100 },
{ ���: 2,Ŀ¼: 500 },
{ ���: 3,Ŀ¼: 688 },
{ ���: 4,Ŀ¼: 888 },
{ ���: 5,Ŀ¼: 1888 },
{ ���: 6,Ŀ¼: 3888 },
{ ���: 7,Ŀ¼: 5888 },
{ ���: 8,Ŀ¼: 8888 },
{ ���: 9,Ŀ¼: 12888 },
{ ���: 10,Ŀ¼: 16888 },
{ ���: 11,Ŀ¼: 20888 },
{ ���: 12,Ŀ¼: 24888 }

);

//��ϸ��������  ����0=���,����1=���,����2=���ã�������ͨ�� ֱ����ƷID�Զ�ʶ��װ����������Ʒ������1��������0�ǿɽ���
var xmxsz = new Array(
/*{ ����: 1112422,����: 8, ����: 8, ����: 8, ����: 8, HP: 80, MP: 80, �﹥: 5, ħ��: 5, ���: 5, ħ��: 5, ����: 5, �ر�: 5, �ƶ�: 5, ��Ծ: 5, ����: 1, ����: 1, Ŀ¼: 30 }*/



{ ����: 4000423, ����: 1, Ŀ¼: 30 },
{ ����: 3700357, ����: 1, Ŀ¼: 30 },
{ ����: 4001126, ����: 500, Ŀ¼: 30 },


{ ����: 4000425, ����: 1, Ŀ¼: 100 },
{ ����: 4001126, ����: 1000, Ŀ¼: 100 },

{ ����: 4000424, ����: 1, Ŀ¼: 500 },
{ ����: 4001126, ����: 1500, Ŀ¼: 500 },

{ ����: 4170005, ����: 2, Ŀ¼: 688 },
{ ����: 4170006, ����: 2, Ŀ¼: 688 },
{ ����: 4001126, ����: 100, Ŀ¼: 688 },
{ ����: 4000313, ����: 10, Ŀ¼: 688 },
{ ����: 4000463, ����: 10, Ŀ¼: 688 },

{ ����: 4170005, ����: 3, Ŀ¼: 888 },
{ ����: 4170006, ����: 3, Ŀ¼: 888 },
{ ����: 2340000, ����: 1, Ŀ¼: 888 },
{ ����: 2049118, ����: 1, Ŀ¼: 888 },
{ ����: 4001126, ����: 200, Ŀ¼: 888 },
{ ����: 4000313, ����: 20, Ŀ¼: 888 },
{ ����: 4000463, ����: 20, Ŀ¼: 888 },

{ ����: 2340000, ����: 2, Ŀ¼: 1888 },
{ ����: 2049118, ����: 2, Ŀ¼: 1888 },
{ ����: 4001126, ����: 300, Ŀ¼: 1888 },
{ ����: 4000313, ����: 30, Ŀ¼: 1888 },
{ ����: 4000463, ����: 30, Ŀ¼: 1888 },
{ ����: 4251200, ����: 1, Ŀ¼: 1888 },

{ ����: 4310034, ����: 3, Ŀ¼: 3888 },
{ ����: 4310029, ����: 3, Ŀ¼: 3888 },
{ ����: 2340000, ����: 3, Ŀ¼: 3888 },
{ ����: 2049118, ����: 3, Ŀ¼: 3888 },
{ ����: 4310148, ����: 1, Ŀ¼: 3888 },
{ ����: 4001126, ����: 400, Ŀ¼: 3888 },
{ ����: 4000313, ����: 50, Ŀ¼: 3888 },
{ ����: 4000463, ����: 40, Ŀ¼: 3888 },
{ ����: 4310143, ����: 1, Ŀ¼: 3888 },
{ ����: 4251200, ����: 2, Ŀ¼: 3888 },

{ ����: 2340000, ����: 5, Ŀ¼: 5888 },
{ ����: 2049118, ����: 5, Ŀ¼: 5888 },
{ ����: 4310148, ����: 2, Ŀ¼: 5888 },
{ ����: 4310034, ����: 5, Ŀ¼: 5888 },
{ ����: 4310029, ����: 5, Ŀ¼: 5888 },
{ ����: 4001126, ����: 500, Ŀ¼: 5888 },
{ ����: 4000313, ����: 100, Ŀ¼: 5888 },
{ ����: 4000463, ����: 50, Ŀ¼: 5888 },
{ ����: 4310143, ����: 2, Ŀ¼: 5888 },
{ ����: 4251200, ����: 3, Ŀ¼: 5888 },

{ ����: 4000463, ����: 70, Ŀ¼: 8888 },
{ ����: 4000313, ����: 150, Ŀ¼: 8888 },
{ ����: 4000038, ����: 100, Ŀ¼: 8888 },
{ ����: 4310143, ����: 3, Ŀ¼: 8888 },
{ ����: 4310148, ����: 3, Ŀ¼: 8888 },
{ ����: 4251201, ����: 1, Ŀ¼: 8888 },
{ ����: 1, ����: 2000000, Ŀ¼: 8888 },

{ ����: 4170005, ����: 5, Ŀ¼: 12888 },
{ ����: 4170006, ����: 5, Ŀ¼: 12888 },
{ ����: 4310012, ����: 1, Ŀ¼: 12888 },
{ ����: 4000463, ����: 90, Ŀ¼: 12888 },
{ ����: 4000313, ����: 200, Ŀ¼: 12888 },
{ ����: 4000038, ����: 150, Ŀ¼: 12888 },
{ ����: 4310143, ����: 5, Ŀ¼: 12888 },
{ ����: 4310148, ����: 4, Ŀ¼: 12888 },
{ ����: 4251201, ����: 3, Ŀ¼: 12888 },
{ ����: 1, ����: 4000000, Ŀ¼: 12888 },

{ ����: 4310012, ����: 2, Ŀ¼: 16888 },
{ ����: 4000463, ����: 110, Ŀ¼: 16888 },
{ ����: 4000313, ����: 260, Ŀ¼: 16888 },
{ ����: 4000038, ����: 200, Ŀ¼: 16888 },
{ ����: 4310143, ����: 6, Ŀ¼: 16888 },
{ ����: 4310148, ����: 5, Ŀ¼: 16888 },
{ ����: 4251202, ����: 1, Ŀ¼: 16888 },
{ ����: 1, ����: 6000000, Ŀ¼: 16888 },

{ ����: 4170005, ����: 6, Ŀ¼: 20888 },
{ ����: 4170006, ����: 6, Ŀ¼: 20888 },
{ ����: 4310012, ����: 3, Ŀ¼: 20888 },
{ ����: 4000463, ����: 150, Ŀ¼: 20888 },
{ ����: 4000313, ����: 300, Ŀ¼: 20888 },
{ ����: 4000038, ����: 260, Ŀ¼: 20888 },
{ ����: 4310143, ����: 7, Ŀ¼: 20888 },
{ ����: 4310148, ����: 6, Ŀ¼: 20888 },
{ ����: 4251202, ����: 2, Ŀ¼: 20888 },
{ ����: 1, ����: 8000000, Ŀ¼: 20888 },

{ ����: 4310012, ����: 4, Ŀ¼: 24888 },
{ ����: 4000463, ����: 200, Ŀ¼: 24888 },
{ ����: 4000313, ����: 300, Ŀ¼: 24888 },
{ ����: 4000038, ����: 300, Ŀ¼: 24888 },
{ ����: 4310143, ����: 8, Ŀ¼: 24888 },
{ ����: 4310148, ����: 7, Ŀ¼: 24888 },
{ ����: 4251202, ����: 3, Ŀ¼: 24888 },
{ ����: 4310059, ����: 1, Ŀ¼: 24888 },
{ ����: 1, ����: 10000000, Ŀ¼: 24888 },

{ ����: 2, ����: 12644, Ŀ¼: 9999999 }
);

var ��ǰ��ֵ = 0;
var ��ȡ���� = 0;
var ѡ�� = -1;
var DHDCLX = -1;
function start() {
	status = -1;

	action(1, 0, 0);
}

function action(mode, type, selection) {

	if (mode == 1) {
		status++;
	} else if (mode == 0 && status != 0) {
		status--;
	} else {
		cm.dispose();
		return;
	}

	if (status == 0) {
		//�ܼ� = cm.getHyPay(2)+cm.getHyPay(1);
		��ǰ��ֵ = cm.getBossRankCount8("�������ֻ���");
		��ȡ���� = cm.getBossRankCount8("�������");
		if (��ȡ���� < 0) {
				��ȡ���� = 0
			}
		if (��ǰ��ֵ < 0) {
				��ǰ��ֵ = 0
			}
		var �ڶ� = ��ȡ����;
		var �ڶ��� = ++�ڶ�;
		MapleItemInformationProvider = Packages.server.MapleItemInformationProvider;
		var text = "";
		text += "\t\t\t\t#e#d#v5120030#���������#v5120030#\r\n\r\n";
		s = 0;
		for (var c = 0; c < fyszML.length; c++) { //

			//if (fyszML[c].��� >= ��ȡ����) {
			if ((fyszML[c].��� == ��ȡ����)||(fyszML[c].��� == �ڶ���)) {
				text += "#L" + fyszML[c].��� + "#"+����+"#e#d�ۼ�������  [#r" + ��ǰ��ֵ + "#d/" + fyszML[c].Ŀ¼ + "]\r\n\r\n"
              s++;
			}

		}
		if(s==0){
		cm.sendOk("ȫ��������");
			cm.dispose();
			return;	
		}
		cm.sendSimple(text);
	} else if (status == 1) {
		DHDCLX = fyszML[selection].Ŀ¼;

		var selStr = ""+����+"\r\n";
		for (var i = 0; i < xmxsz.length; i++) { //��Ҫ�̶�����
			if (xmxsz[i].Ŀ¼ == DHDCLX) {
				if (xmxsz[i].���� == 0) { //���
					selStr += ""+���+"#r ��� X " + xmxsz[i].���� + "\r\n";
				} else if (xmxsz[i].���� == 1) { //���
					selStr += ""+��ȯͼ��+"#r ��� X " + xmxsz[i].���� + "\r\n";
				} else if (xmxsz[i].���� == 2) { //����ȯ
					selStr += ""+��ȯͼ��+"#r ���þ� X " + xmxsz[i].���� + "\r\n";
				} else { //��Ʒ

					var sx = "";
					if (xmxsz[i].���� != 0) {
						sx += " ����+" + xmxsz[i].����;
					}
					if (xmxsz[i].���� != 0) {
						sx += " ����+" + xmxsz[i].����;
					}
					if (xmxsz[i].���� != 0) {
						sx += " ����+" + xmxsz[i].����;
					}
					if (xmxsz[i].���� != 0) {
						sx += " ����+" + xmxsz[i].����;
					}
					if (xmxsz[i].HP != 0) {
						sx += " HP+" + xmxsz[i].HP;
					}
					if (xmxsz[i].MP != 0) {
						sx += " MP+" + xmxsz[i].MP;
					}
					if (xmxsz[i].�﹥ != 0) {
						sx += " �﹥+" + xmxsz[i].�﹥;
					}
					if (xmxsz[i].ħ�� != 0) {
						sx += " ħ��+" + xmxsz[i].ħ��;
					}

					if (xmxsz[i].��� != 0) {
						sx += " ���+" + xmxsz[i].���;
					}

					if (xmxsz[i].ħ�� != 0) {
						sx += " ħ��+" + xmxsz[i].ħ��;
					}
					if (xmxsz[i].���� != 0) {
						sx += " ����+" + xmxsz[i].����;
					}
					if (xmxsz[i].�ر� != 0) {
						sx += " �ر�+" + xmxsz[i].�ر�;
					}
					if (xmxsz[i].�ƶ� != 0) {
						sx += " �ƶ�+" + xmxsz[i].�ƶ�;
					}
					if (xmxsz[i].��Ծ != 0) {
						sx += " ��Ծ+" + xmxsz[i].��Ծ;
					}
					//var ii = net.sf.cherry.server.MapleItemInformationProvider.getInstance();		
					var ii = MapleItemInformationProvider.getInstance();
					var type = ii.getInventoryType(xmxsz[i].����); 

					if(type == 1){
						selStr += "#r#i" + xmxsz[i].���� + "##z" + xmxsz[i].���� + "##k X " + xmxsz[i].���� + "\r\n" + sx + "\r\n";
					} else {
						selStr += "#r#i" + xmxsz[i].���� + "##z" + xmxsz[i].���� + "##k X " + xmxsz[i].���� + "\r\n";
					}
					

				}

			}
		}

		

		ѡ�� = selection;
		cm.sendYesNo(selStr);
	} else if (status == 2) {
		
		if (cm.getInventory(1).isFull(3) || cm.getInventory(2).isFull(3) || cm.getInventory(3).isFull(3) || cm.getInventory(4).isFull(3)) {
			cm.sendOk("#b�뱣֤ȫ�屳��4������,�����޷��һ�.");
			cm.dispose();
			return;
		} else if (��ȡ���� != ѡ��) { //
			cm.sendOk("#b�밴˳��һ��һ����ȡ" );
			cm.dispose();
			return;
		} else if (DHDCLX > ��ǰ��ֵ) { //
			cm.sendOk("#��û������[#r" + ��ǰ��ֵ + "#d/" + DHDCLX + "]");
			cm.dispose();
			return;
		} else {
			//----------------------------------------------------------------------
			for (var i = 0; i < xmxsz.length; i++) {			
				if (xmxsz[i].Ŀ¼ == DHDCLX) {
					
					//------------------------------------------------------
					if (xmxsz[i].���� == 0) { //���
						cm.gainMeso(+xmxsz[i].����); //�۳����ٽ��
					} else if (xmxsz[i].���� == 1) { //���
						cm.getPlayer().modifyCSPoints(1, +xmxsz[i].����, true); //��ȯ
					} else if (xmxsz[i].���� == 2) { //����ȯ
						cm.getPlayer().modifyCSPoints(2, +xmxsz[i].����, true); //����ȯ
					} else { //��Ʒ
						if (MapleItemInformationProvider.getInstance().getInventoryType(xmxsz[i].����).getType() == 1) {
						var ii = MapleItemInformationProvider.getInstance();	
						var toDrop = ii.randomizeStats(ii.getEquipById(xmxsz[i].����)).copy();
						var type = ii.getInventoryType(xmxsz[i].����); //���װ��������
							
							
							//var type = ii.getInventoryType(xmxsz[i].����); //���װ��������
							//var toDrop = ii.randomizeStats(ii.getEquipById(xmxsz[i].����)).copy(); // ����һ��Equip��
							//Array(��Ʒid������ ������ ������ �������������� ��ħ������ ��ħ�������� ��������� ��� ),
							toDrop.setFlag(xmxsz[i].����); //����������ʱ��һ�𣬷����BUG����ʧ//����
							if (xmxsz[i].���� != 0) {
								toDrop.setStr(xmxsz[i].����); //������
							}

							if (xmxsz[i].���� != 0) {
								toDrop.setDex(xmxsz[i].����); //������
							}

							if (xmxsz[i].���� != 0) {
								toDrop.setInt(xmxsz[i].����); //������
							}

							if (xmxsz[i].���� != 0) {
								toDrop.setLuk(xmxsz[i].����); //������
							}
							
							if (xmxsz[i].HP != 0) {
								toDrop.setHp(xmxsz[i].HP); //HP
							}
							if (xmxsz[i].MP != 0) {
								toDrop.setMp(xmxsz[i].MP); //MP
							}

							if (xmxsz[i].�﹥ != 0) {
								toDrop.setWatk(xmxsz[i].�﹥); //������
							}

							if (xmxsz[i].ħ�� != 0) {
								toDrop.setMatk(xmxsz[i].ħ��); //ħ����
							}

							if (xmxsz[i].��� != 0) {
								toDrop.setWdef(xmxsz[i].���); //�������
							}

							if (xmxsz[i].ħ�� != 0) {
								toDrop.setMdef(xmxsz[i].ħ��); //ħ������
							}
							
							if (xmxsz[i].���� != 0) {
								toDrop.setAcc(xmxsz[i].����); //����
							}
							
							if (xmxsz[i].�ر� != 0) {
								toDrop.setAvoid(xmxsz[i].�ر�); //�ر���
							}

							

							

							if (xmxsz[i].��Ծ != 0) {
								toDrop.setSpeed(xmxsz[i].��Ծ); //��Ծ
							}

							if (xmxsz[i].�ƶ� != 0) {
								toDrop.setJump(xmxsz[i].�ƶ�); //�ƶ�
							}
							cm.addFromDrop(toDrop);
							//cm.itemlaba("�����������","��ϲ��� " + cm.getPlayer().getName() + " ��ȡ���ۻ� "+DHDCLX+" �������������", toDrop, 15);
							
							
							//item.getItemId();

						} else {
							cm.gainItem(xmxsz[i].����, xmxsz[i].����);
							//cm.itemlabaB("�����������", "��ϲ��� " + cm.getPlayer().getName() + " ��ȡ���ۻ� "+DHDCLX+" �������������", xmxsz[i].����, 15);
						}

					}

				}
			}
			cm.setBossRankCount8("�������", +1);
			cm.sendOk("#b�ɹ���.");
			cm.dispose();

		}

	}

}
function getlibao() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("libao");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function gainlibao(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET libao = libao+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function getmoney() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("money");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function getmoneyc() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyc");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}