var �ʹڰ� ="#fUI/GuildMark/Mark/Plant/00003006/15#";
load("nashorn:mozilla_compat.js");
importPackage(Packages.client);
importPackage(Packages.client.inventory);
importPackage(Packages.server);
importPackage(Packages.tools);
var �ٷ� = "#fEffect/CharacterEff/1003252/0/0#";
var ���� = "#fEffect/CharacterEff/1032063/0/0#";
var �� = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var s = "#fUI/StatusBar/BtClaim/normal/0#";
var h = "#fUI/CashShop/CSEffect/effect/1#";
var ʱ��֮ʯ = 4021010;
var status = 0;
var zones = 0;
var ItemId = Array(

        //Array(1332247, 500, "����ذ��"),
		//Array(1302297, 500, "���н�"),
		//Array(1322223, 500, "���д�"),
		//Array(1342090, 500, "���е�"),
		//Array(1382231, 500, "���ж���"),
		//Array(1312173, 500, "���и�"),
		//Array(1452226, 500, "���й�"),
		//Array(1442242, 500, "�����"),
		//Array(1152160, 500, "���н�"),
		//Array(1402220, 500, "����˫�ֽ�"),
		//Array(1482189, 500, "���г�ȭ"),
		//Array(1432187, 500, "����ì"),
		//Array(1462213, 500, "������"),
		//Array(1472235, 500, "����ȭ��"),
		//Array(1492199, 500, "�������"),
		//Array(1122269, 200, "���е�׹"),
		//Array(1032224, 200, "���ж���"),
		//Array(1052669, 200, "���лʼ�����"),
		//Array(1102623, 200, "��������"),
		//Array(1082556, 200, "��������"),
		//Array(1012438, 200, "��������"),
		//Array(1072870, 200, "����Ь"),
		//Array(1022211, 200, "�����۾�"),
		//Array(1132247, 200, "��������"),
		//Array(1003976, 200, "����ñ��")
        );
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (cm.getLevel < 10) {
                cm.sendOK("��ĵȼ�����10�������򿪸��", 2);
                cm.dispose();
            } else {
                selStr = "\t\t#e#k"+�ʹڰ�+" ��Ҫ�������һ�ʲô��Ʒ��? "+�ʹڰ�+"\r\n==============================================\r\n\t\t\t#dĿǰӵ����������:#r"+cm.getBeans()+"#n\r\n";
                for (var i = 0; i < ItemId.length; i++) {
                    selStr += "\r\n#L" + i + "##b#v" + ItemId[i][0] + "##k (��Ҫ#r " + ItemId[i][1] + " #k������ֵ��)";
                }
                cm.sendSimple(selStr);
                zones == 0;
            }
        } else if (status == 1) {
            if (zones == 1) {
                cm.sendNext("�����Ұ�����ʲô�أ�", 2);
                zones = 2;
            } else if (zones == 0) {
				if (cm.getBeans() >= ItemId[selection][1]) { 
				var finalitem = Array();
				finalitem.push(ItemId[selection][1]);
				if (finalitem.length != 0) {
			var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var notice = finalitem[finalchance][3];
              item = cm.gainGachaponItem(ItemId[selection][0], 1, "�����һ�");
			if (item != -1) {
				cm.gainBeans(-(ItemId[selection][1]));
                     //   cm.gainGachaponItem(ItemId[selection][0], 1, "�����һ�", notice);
                        
						Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ��̳ǡ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��������һ�������װ������")); 
						cm.sendOk("��һ���1�� #b#t" + item + "");
                        cm.dispose();
					}
					else
					{
					cm.sendOk("����ȷ���ڱ�����װ�������ģ����ã����������ⴰ�����Ƿ���һ�����ϵĿռ䡣");
					cm.safeDispose();
					}
					cm.safeDispose();
					}
                    } else {
                        cm.sendOk("�������㣡");
                        cm.dispose();
                    }
                }
            } else if (status == 2) {
                if (zones == 2) {
                    cm.sendNext("������������ħ����ʱ��֮ʯ��һȺĢ����͵����,���ܰ�ȥ��������");
                    zones = 3;
                }
            } else if (status == 3) {
                if (zones == 3) {
                    cm.sendNext("��,���û���⣿���������Щ͵����ʱ��֮ʯ��Ģ����������ʲô�ط���?", 2);
                    zones = 4;
                }
            } else if (status == 4) {
                if (zones == 4) {
                    cm.sendNext("�����Ʒ��#bȫ�������#k�ġ�������������ϵ�#b�κ�һ������#k�ϻ�ȡ��");
                    zones = 5;
                }
            } else if (status == 5) {
                if (zones == 5) {
                    cm.sendYesNo("������ܰ��������æ�Ļ�,�һ����һЩ���Ľ����ģ����Ƿ�Ը����ң�");
                }
            } else if (status == 6) {
                cm.setBossLog('MogoQuest');
                cm.gainItem(5220001, 1);
                cm.sendOk("�ǳ���л����ú����˵�������ܻ�����Ʒ�ˣ�");
                cm.dispose();
            }
    }
}	
