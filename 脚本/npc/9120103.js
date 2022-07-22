/* Dr. Feeble
	Henesys Random Eye Change.
*/
var status = 0;
var beauty = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendNext("���,����ҽ��!�������#b�Ѻʹ�����������ͨ��Ա��#k,��ͷ��ĵ�����Ϊ���������������,�һ����������.��ô��Ҫ��ʲô��\r\n\#L2##b������������#k(ʹ��#b�Ѻʹ�����������ͨ��Ա��#k)���#l");
    } else if (status == 1) {
	cm.sendYesNo("�������#b�Ѻʹ�����������ͨ��Ա��#k,��ô�ҽ���������ı�һ������,��ȷ��Ҫ�ı�������");
    } else if (status == 2){
	var face = cm.getPlayerStat("FACE");
	var facetype;

	if (cm.getPlayerStat("GENDER") == 0) {
	    facetype = [20000, 20001, 20002, 20003, 20004, 20005, 20006, 20007, 20008, 20012, 20014];
	} else {
	    facetype = [21000, 21001, 21002, 21003, 21004, 21005, 21006, 21007, 21008, 21012, 21014];
	}
	for (var i = 0; i < facetype.length; i++) {
	    facetype[i] = facetype[i] + face % 1000 - (face % 100);
	}

	if (cm.setRandomAvatar(5152008, facetype) == 1) {
	    cm.sendOk("#e����,���������һ���ϲ�������!");
	} else {
	    cm.sendOk("�������㲢û�����ǵĻ�Ա��,�ҿ��²��ܸ�����,�Һܱ�Ǹ.�����ȹ���ɡ�");
	}
	cm.dispose();
    }
}
