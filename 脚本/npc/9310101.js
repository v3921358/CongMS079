/*

 �ű����������˵�
 */


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	//��ʼ
    if (status == 0) {
		
        var selStr = "\r\n";
		
			selStr += " 1.��������ͨ�����̳ǹ���#b����Ʊ#k��Ȼ���ڶ������һ�������ÿλ���һ��ֻ�ܶһ�#b10#k�ζ�����ÿ�ζһ��Ķ����������̶���\r\n";
			selStr += " 2.����������#b����������#k���������\r\n";
			selStr += " 3.ÿ�δ򶹶�������#b5�Ŷ���#k��\r\n";
			selStr += " 4.������鿴����������ʵʱ���£���ҪС��һ�²Ż���ʾ��ȷ������ʵ�������ڹ��򶹶�����򶹶����ǻ�仯�ġ�\r\n";

        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9900004, 1);
                break;
            
			default:
                cm.dispose();
                break;
        }
    }
}