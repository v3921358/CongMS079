var weapon = new Array(
//�ϳ���ƷID,ǰһ��װ��ID
	//���� ffn    �޽�
	Array(1302275,1302285), //���ֽ�
	Array(1312153,1312162),//���ָ�
	Array(1332225,1332235),//�̵�����
	Array(1342082,1342084),//����С��
	Array(1372177,1372186),//����
	Array(1382208,1382220), //����
	Array(1402196,1402204),//˫�ֽ�
	Array(1412135,1412144),//˫�ָ�
	Array(1432167,1432176),//ǹ
	Array(1442223,1442232),//ì
	
	Array(1452205,1452214), //��
	Array(1462193,1462202),//��
	Array(1472214,1472223),//ȭ��
	Array(1482168,1482177),//ָ��
	Array(1492179,1492188)//��ǹ
	
	
	
	
	
	
);
//������������
var req = [
    [4310059, 1],//������
    [4310012, 10],//ʮ�ֱ�
	[4310029, 10],//ʮ�ֱ�
    [4310034, 10],//�����
    [4251202, 10],//�ߵ����ˮ��
	[4001126, 8888],//��Ҷ
	[4000313, 8888],//�ƽ��Ҷ
	[4310143, 50],//boss��
	[4000463, 88]//��������
];
var rem = 8888;
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
		msg += "\t\t";
		msg += rem+"���";
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
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ��˷�����װ������")); 
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