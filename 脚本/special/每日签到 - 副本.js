
/* global cm */

        var status = -1;
var select = -1;
var eff1 = "";//"#fUI/LogoMs/1#";
var eff2 = "";//"#fUI/LogoMs/2#";
var eff3 = "";//"#fUI/LogoMs/3#";
var eff4 = "";//"#fUI/LogoMs/4#";
var eff5 = "";//"#fUI/LogoMs/7#";
var eff6 = "";//"#fUI/LogoMs/10#";
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
var ���� = "#fEffect/CharacterEff/1112960/3/1#";  //а��С�� ����
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ����Ҷ ="#fMap/MapHelper/weather/maple/3#";
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";


function start() {
    if (cm.getPlayer().getLevel() < 10) {
        cm.sendOk("�ȼ�С��10���޷�ʹ���������ܡ�");
        cm.dispose();
        return;
    }
	
			
  cm.sendSimple(

	  //"\t\t\t  #e��ӭ����#r " + cm.getChannelServer().getServerName() + " #k!#n\r\n"+
        //    "         " + eff6 + " " + eff6 + " " + eff6 + " " + " #bĿǰ��������#k " + eff4 + " #r" + (cm.getTotalOnline() * 2 + cm.getRandom([0, 1])) + "#k��\r\n" +
	 /*
	    "#L99##b"+��ݮ+"������Ϣ#l"+
		"#L1##b"+��ݮ+"�����г�#l"+
		"#L2##b"+��ݮ+"���ܴ���#l"+
		"#L3##b"+��ݮ+"����תְ#l"+
		"#L4##b"+��ݮ+"ÿ������#l"+


		*/
		
		"���ã��𾴵� #b#h ##k,��ӭ���� #r" + cm.getChannelServer().getServerName() + " #kǩ������#k��\r\n�ǵ�ÿ��ǩ�����������Ŷ��#l\r\n#bע�����¸���ǩ������������ȼ�����ÿ����ȡһ��\r\n�ߵȼ��߿�#r��������#k#bǩ�������� ���н�Ҷ������ҩˮ�ȵ�������#l\r\n"+
		
		//"#L10##e#rǩ��ͼ�¶һ�����#k\r\n"+
		"#L1##e#bСѧǩ�� ��Ҫ�ȼ�#d��LV30����\r\n"+
		"#L2##b��ѧǩ�� ��Ҫ�ȼ�#d��LV70����\r\n"+
		"#L3##b��ѧǩ�� ��Ҫ�ȼ�#d��LV120����\r\n"+
		"#L4##b��ʿǩ�� ��Ҫ�ȼ�#d��LV150����\r\n"+
		"#L5##b��ǩ�� ��Ҫ�ȼ�#d��LV200����\r\n"+
		
		
//=====================================================================================		
""
     );
	 
	 
	
	 
	 
	 
}

function action(mode, type, selection) {
    if (select === -1) {
        select = selection;
    }
    var level = cm.getPlayer().getLevel();
    switch (select) {
        case 0:
        {
            openNpc(9900004, "ÿ��ǩ��");

            break;
        }
        case 1:
        {
		if (cm.getPlayer().getBossLog("ÿ��ǩ��30") >= 1 || cm.getGamePoints() <= 30  || cm.getPlayerStat("LVL") < 30 ) {
            cm.sendOk("ǩ��ʧ��ԭ��\r\n1.�������Ѿ�ǩ������\r\n2.��������ʱ�䲻��30����\r\n3.���ĵȼ�����30��\r\nлл���٣��������ٹ���");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("ÿ��ǩ��30");
			//cm.serverNotice("�����桻����ҡ�"+ cm.getChar().getName() +"���������г� ��ȡ��ÿ�ս�����");
			//cm.gainItem(2049100,5);//�������
			//cm.gainItem(5072000,3);//���ʵ�����

				cm.gainItem(4032398, 1);//��ϯͼ��
				cm.gainItem(2000005, 10);//����ҩˮ
				cm.gainExp( + 100000);//����
				cm.gainMeso(+ 100000);//���
			    //cm.gainItem(2340000,5);//ף����
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��ÿ��ǩ����" + " : " + "[" + cm.getChar().getName() + "]�ɹ������Сѧǩ������")); 
			cm.sendOk("ÿ��Сѧǩ�����,������½���:\r\n#b��ϯͼ��1��+���10��+����21��+����ҩˮ10��!");
		}
			break;
		}
		
		case 2:
        {
		if (cm.getPlayer().getBossLog("ÿ��ǩ��70") >= 1 || cm.getGamePoints() <= 60  || cm.getPlayerStat("LVL") < 70 ) {//|| cm.getGamePoints() < 60
            cm.sendOk("ǩ��ʧ��ԭ��\r\n1.�������Ѿ�ǩ������\r\n2.��������ʱ�䲻��60����\r\n3.���ĵȼ�����70��\r\nлл���٣��������ٹ���");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("ÿ��ǩ��70");
			//cm.serverNotice("�����桻����ҡ�"+ cm.getChar().getName() +"���������г� ��ȡ��ÿ�ս�����");
			//cm.gainItem(2049100,5);//�������
			//cm.gainItem(5072000,3);//���ʵ�����

				//cm.gainItem(4032398, 1);//��ϯͼ��
				cm.gainItem(2000005, 50);//����ҩˮ
				cm.gainExp( + 200000);//����
				cm.gainMeso(+ 300000);//���
			    //cm.gainItem(2340000,5);//ף����
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��ÿ��ǩ����" + " : " + "[" + cm.getChar().getName() + "]�ɹ��������ѧǩ������")); 
			cm.sendOk("ÿ����ѧǩ�����,������½���:\r\n#b���30��+����20��+����ҩˮ50��!");
		}
			break;
		}
		
		
		case 3:
        {
		if (cm.getPlayer().getBossLog("ÿ��ǩ��120") >= 1 || cm.getGamePoints() <= 120  || cm.getPlayerStat("LVL") < 120 ) {
            cm.sendOk("ǩ��ʧ��ԭ��\r\n1.�������Ѿ�ǩ������\r\n2.��������ʱ�䲻��120����\r\n3.���ĵȼ�����120��\r\nлл���٣��������ٹ���");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("ÿ��ǩ��120");
			//cm.serverNotice("�����桻����ҡ�"+ cm.getChar().getName() +"���������г� ��ȡ��ÿ�ս�����");
			//cm.gainItem(2049100,5);//�������
			//cm.gainItem(5072000,3);//���ʵ�����

				//cm.gainItem(4032398, 1);//��ϯͼ��
				cm.gainItem(2000005, 50);//����ҩˮ
				cm.gainExp( + 200000);//����
				cm.gainMeso(+ 300000);//���
			    //cm.gainItem(2340000,5);//ף����
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��ÿ��ǩ����" + " : " + "[" + cm.getChar().getName() + "]�ɹ�����˴�ѧǩ������")); 
			cm.sendOk("ÿ�մ�ѧǩ�����,������½���:\r\n#b���50��+����30��+����ҩˮ100��!");
		}
			break;
		}
		
        
		case 4:
        {
		if (cm.getPlayer().getBossLog("ÿ��ǩ��150") >= 1 || cm.getGamePoints() <= 120  || cm.getPlayerStat("LVL") < 150 ) {
            cm.sendOk("ǩ��ʧ��ԭ��\r\n1.�������Ѿ�ǩ������\r\n2.��������ʱ�䲻��120����\r\n3.���ĵȼ�����150��\r\nлл���٣��������ٹ���");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("ÿ��ǩ��150");
			//cm.serverNotice("�����桻����ҡ�"+ cm.getChar().getName() +"���������г� ��ȡ��ÿ�ս�����");
			//cm.gainItem(2049100,5);//�������
			//cm.gainItem(5072000,3);//���ʵ�����

				//cm.gainItem(4032398, 1);//��ϯͼ��
				cm.gainItem(2000005, 150);//����ҩˮ
                cm.gainExp( + 500000);//����
				cm.gainMeso(+ 800000);//���
				cm.gainItem(2340000,1);//ף����
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��ÿ��ǩ����" + " : " + "[" + cm.getChar().getName() + "]�ɹ�����˲�ʿǩ������")); 
                cm.sendOk("ÿ�ղ�ʿǩ�����,������½���:\r\n#bף������1��+���80��+����50��+����ҩˮ150��!");
		}
			break;
		}
       
        case 5:
        {
		if (cm.getPlayer().getBossLog("ÿ��ǩ��200") >= 1 || cm.getGamePoints() <= 120  || cm.getPlayerStat("LVL") < 200 ) {
            cm.sendOk("ǩ��ʧ��ԭ��\r\n1.�������Ѿ�ǩ������\r\n2.��������ʱ�䲻��120����\r\n3.���ĵȼ�����200��\r\nлл���٣��������ٹ���");
			cm.dispose();	
        }else{
            cm.getPlayer().getBossLog("ÿ��ǩ��200");
			//cm.serverNotice("�����桻����ҡ�"+ cm.getChar().getName() +"���������г� ��ȡ��ÿ�ս�����");
			//cm.gainItem(2049100,5);//�������
			//cm.gainItem(5072000,3);//���ʵ�����

				//cm.gainItem(4032398, 1);//��ϯͼ��
				cm.gainItem(2000005, 200);//����ҩˮ
                cm.gainExp( + 1000000);//����
				cm.gainMeso(+ 1000000);//���
				cm.gainItem(2340000,5);//ף����
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��ÿ��ǩ����" + " : " + "[" + cm.getChar().getName() + "]�ɹ��������ǩ������")); 
                cm.sendOk("ÿ����ǩ�����,������½���:\r\n#bף������5��+���100��+����100��+����ҩˮ200��!");
		}
			break;
		}
		
		
        
		
		
		case 10:
        {
            openNpc(9330079, "ǩ���һ�");
            break;
		
		}
		
		
       




        case 100:
        {
            select3(mode, type, selection);
            break;
        }
        case 101:
        {
            cm.sendOk(
                    "ÿ��0:00�������дΔ��������D˹��\r\n" +
                    "#bƤ���ʣ�N�Δ���#k#r" + (3 - cm.getPlayer().getBossLogD("Ƥ��౴Δ�")) + "#k\r\n" +
                    "#b霺�����ʣ�N�Δ���#k#r" + (3 - cm.getPlayer().getBossLogD("�����Δ�")) + "#k\r\n" +
                    "#b������ħ�Δ�ʣ�N�Δ���#k#r" + (3 - cm.getPlayer().getBossLogD("������ħ�Δ�")) + "#k\r\n" +
                    "#b���D˹ʣ�N�Δ���#k#r" + (2 - cm.getPlayer().getBossLogD("pop")) + "#k\r\n" +
                    "#b�ܪ{��ʣ�N�Δ���#k#r" + (3 - cm.getPlayer().getBossLogD("�ܪ{���Δ�")) + "#k\r\n" +
                    "");
            cm.dispose();
            return;
        }

        case 102:
        {
            if (cm.haveItem(5460000)) {
                cm.gainItem(5460000, -1);
                cm.teachSkill(8, 1, 0);
                cm.teachSkill(10000018, 1, 0); // Maker
                cm.teachSkill(20000024, 1, 0); // Maker
                cm.sendOk("ѧϰ�ɹ���");
                cm.dispose();
                return;
            } else {
                cm.sendOk("��]��#t5460000##i5460000#��");
                cm.dispose();
                return;
            }
            break;
        }

	

	case 103:
        {
            if (cm.getOneTimeLog("���ֳ���") < 1) {
                cm.setOneTimeLog("���ֳ���");
                cm.gainPet(5000061, "Ƥ���", 1, 0, 100, 0, 119);//Ƥ��� 90�� (����)
                cm.sendOk("��ȡ���ֳ���ɹ���");
                cm.dispose();
                return;
            } else {
                cm.sendOk("������ȡ�����ֳ��");
                cm.dispose();
                return;
            }
            cm.dispose();
            break;
        }








	
		case 99://������Ϣ
        {
           cm.sendOk(
                    "#d"+��ݮ+"�װ������#r [#e#h ##n#k#r] #d\t ��ӭ���� ͯ��ð�յ� "+��ݮ+"\r\n" +
                    "#d"+�����+"#bͯ��ð�յ�  ��ֵ����:\t#r1Ԫ==1���==1000��ȯ.\r\n" +
                    "#d"+�����+"#b��ǰ����        ʱ��:\t#r" + java.lang.Long.valueOf((cm.getCurrentTime() - cm.getPlayer().getMrqdTime()) / 60000) + "#k���\r\n" +
                    "#b"+�����+"#b��ǰ����        ��ȯ:\t#r"+cm.getPlayer().getCSPoints(1)+"  \r\n" +
                    "#b"+�����+"#b��ǰ����      ���þ�:\t#r"+cm.getPlayer().getCSPoints(2)+"  \r\n" +
                    //"#b"+�����+"#b��ǰ����        ���:\t#r"+cm.getmoneyb()+"    #k\r\n" +
					"#b"+�����+"#b��ǰ����      ��Ϸ��:\t#r" + cm.getMeso() + "#k\r\n" +

					
					
					
					
                    "");
            cm.dispose();
            return;
        }
		
		
        default :
        {
            cm.sendOk("�˹���δ���");
            cm.dispose();
        }
    }
}

function select3(mode, type, selection) {
    if (mode === 1) {
        status++;
    } else if (mode === 0) {
        status--;
    }

    var i = -1;
    if (status <= i++) {
        cm.dispose();
    } else if (status === i++) {
        var gain = cm.getMP();
        if (gain <= 0) {
            cm.sendOk("Ŀǰ�]���κ��ھ��c��ࡡ�");
            cm.dispose();
            return;
        } else {
            cm.sendYesNo("Ŀǰ���~�c��: " + cm.getMaplePoint() + "\r\n" + "Ŀǰ�ھ��c���ѽ��۷e: " + gain + " �c���Ƿ��Iȡ?");
        }
    } else if (status === i++) {
        var gain = cm.getMP();
        cm.setMP(0);
        cm.gainMaplePoint(gain);
        cm.save();
        cm.sendOk("��ȡ�� " + gain + " �����ߵ���, Ŀǰ���~�c��: " + cm.getMaplePoint());
        cm.dispose();
    } else {
        cm.dispose();
    }
}

function CGM(mode, type, selection) {
    if (mode === 1) {
        status++;
    } else if (mode === 0) {
        status--;
    }

    var i = -1;
    if (status <= i++) {
        cm.dispose();
    } else if (status === i++) {
        cm.sendGetText("��������Ҫ��GM���͵���Ϣ");
    } else if (status === i++) {
        var text = cm.getText();
        if (text === null || text === "") {
            cm.sendOk("��δ�����κ΃���.");
            cm.dispose();
            return;
        }
        cm.dispose();
        cm.processCommand("@CGM " + text);
    } else {
        cm.dispose();
    }
}

function openNpc(npcid) {
    openNpc(npcid, null);
}

function openNpc(npcid, script) {
    var mapid = cm.getMapId();
    cm.dispose();
    if (cm.getPlayerStat("LVL") < 10) {
        cm.sendOk("��ĵȼ�����С��10��.");
    } else if (
            cm.hasSquadByMap() ||
            cm.hasEventInstance() ||
            cm.hasEMByMap() ||
            mapid >= 990000000 ||
            (mapid >= 680000210 && mapid <= 680000502) ||
            (mapid / 1000 === 980000 && mapid !== 980000000) ||
            mapid / 100 === 1030008 ||
            mapid / 100 === 922010 ||
            mapid / 10 === 13003000
            ) {
        cm.sendOk("�㲻��������ʹ���������.");
    } else {
        if (script == null) {
            cm.openNpc(npcid);
        } else {
            cm.openNpc(npcid, script);
        }
    }
}