var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";
var weapon = new Array(
//�ϳ���ƷID,ǰһ��װ��ID
	//Array(1003797,1052498), //սʿñ��
	//Array(1003798,1052499),
	//Array(1003799,1052500),
	//Array(1003800,1052501),
	//Array(1003801,1052502),
	
	//Array(1042254,1052498), //����
	//Array(1042255,1052499),
	//Array(1042256,1052500),
	//Array(1042257,1052501),
	//Array(1042258,1052498),
	
	//Array(1062165,1052498),	//����	
	//Array(1062166,1052499),	//����
	//Array(1062167,1052500),	//����
	//Array(1062167,1052498),	//����	
	//Array(1062168,1052502), //����
	

	
	//���� �޽�    130����������˹
	Array(1302285,1302336), //���ֽ�
	Array(1312162,1312201),//���ָ�
	Array(1332235,1332277),//�̵�����
	Array(1372186,1372225),//����
	
	Array(1382220,1382263), //����
	Array(1402204,1402253),//˫�ֽ�
	Array(1412144,1412180),//˫�ָ�
	Array(1432176,1432216),//ǹ
	Array(1442232,1442270),//ì
	
	Array(1452214,1452255), //��
	Array(1462202,1462241),//��
	Array(1472223,1472263),//ȭ��
	Array(1482177,1482218),//ָ��
	Array(1492188,1492233)//��ǹ
	
	
	/*
	//150����
	1302275,
	1312153,
	1332225,
	1342082,
	1372177,
	1382208,
	1402196,
	1412135,
	1432167,
	1442223,
	1452205,
	1462193,
	1472214,
	1482168,
	1492179
	
	//150����
	1003797,
	1003798,
	1003799,
	1003800,
	1003801,
	
	1062165,
	1062166,
	1062167,
	1062168,
	1062169,
	
	1042254,
	1042255,
	1042256,
	1042257,
	1042258

	*/
	
	
	
);
//������������
var req = [
    [1122000, 1],//��������
    [4310029, 5],//ʮ�ֱ�
    [4310034, 5],//�����
    [4251202, 1],//�ߵ����ˮ��
	[4001126, 8888],//��Ҷ
	[4000313, 888],//�ƽ��Ҷ
	[4310143, 20],//boss��
	[4000463, 88]//��������
];
var rem = 200000000;
var gailv = 100;//����ٷ�֮����Ҫ����ٷֺ�ֻҪ���������������!!
var sels;
var status = -1;
/* 
���Ը��ʴ��� 
		var aa = "";
		for (var i = 0; i < 1000; i++) {
			s1 = Math.floor(Math.random() * (100 - 1) + 1);
			if(s1 <= gailv){
				aa +="#g"+s1+"#k ";
			} else {
				aa +="#r"+s1+"#k ";
			}
		}
		cm.sendOk(aa);
*/
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        var msg = "";
        msg += "\r\n#d��Ҫ:#b ";
        msg += "\r\n";
        for (var ii = 0; ii < req.length; ii++) {
            msg += "#i" + req[ii][0] + ":##z" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 2 == 0 && ii !=0) {
                msg += "\r\n";
            }
        }
		
		msg += " "+���ͼ��+":#r2��#k";
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i][1] + ":##z" + weapon[i][1] + "# #b����#r #i" + weapon[i][0] + ":##z" + weapon[i][0] + "##l\r\n";
        }
        cm.sendSimple("#d��Ҫ�ϳ�װ����? \t\tĿǰ�ϳɸ���Ϊ: #r#e"+gailv+"%#d#n\r\n��ܰ��ʾ���ϳ�ʧ�ܲ��ϲ���������ѡ��" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels][0])) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b����û��#r#i" + req[i][0] + ":##z" + req[i][0] + "#x" + req[i][1] + "");
                cm.dispose();
                return;
            }
        }
		if (!cm.haveItem(weapon[sels][1],1)) {
            cm.sendNext("#b����û��#r#i" + weapon[sels][1] + ":##z" + weapon[sels][1] + "#");
            cm.dispose();
            return;
        }
		if(cm.getMeso() < rem){
			cm.sendNext("#b����û��#r"+rem+"���");
            cm.dispose();
            return;
		}
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�װ��#r #i" + weapon[sels] + ":#? \r\n");
    } else if (status == 2) {
		s1 = Math.floor(Math.random() * (100 - 1) + 1);
		if(s1 <= gailv){
			for (var i = 0; i < req.length; i++) {
				cm.gainItem(req[i][0], -req[i][1]);
			}
			cm.gainMeso(-rem);
			cm.gainItem(weapon[sels][1],-1);
			cm.gainItem(weapon[sels][0], 1);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ��˿����޽�װ������")); 
			cm.sendNext("#b�Ѿ��һ������� #i" + weapon[sels] + "#");
			cm.dispose();
		} else {
			for (var i = 0; i < req.length; i++) {
				cm.gainItem(req[i][0], -req[i][1]);
			}
			cm.gainMeso(-rem);
			cm.sendNext("#b�ϳ�ʧ��,��Ͷ��Ĳ�����ʧ��~!");
			cm.dispose();
		}
    } else {
        //cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}