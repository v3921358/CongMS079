/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű������к�
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
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
    if (status == 0) {
        var selStr = "	  Hi~#b#h ##k���ǿ��к����ܿ�ܿ���к�����������ʲô������\r\n";
		if(cm.�жϵ�ͼ()!=749020910){
        selStr += " #L1000##b������ս#k#l\r\n";
		
		
		
		
		selStr += " #L1##bȥ���쵰���볡#k#l\r\n";
		
		
		
		}else{
			selStr += " #L1##b��Ҫ�뿪��#k#l\r\n";
			selStr += " #L2##b������ͼ#k#l\r\n";
			selStr += " #L3##b�һ���Ʒ#k#l\r\n";
		}

        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
				cm.openNpc(2007,7);
                break;
			case 2:
                cm.dispose();
				cm.openNpc(9330078,1);
                break;
			case 3:
                cm.dispose();
				cm.openNpc(9330078,2);
                break;
			case 100:
                cm.dispose();
				cm.openNpc(2007,9);
                break;
			case 1000:
                cm.dispose();
				cm.openNpc(9330078,1000);
                break;
        }
    }
}