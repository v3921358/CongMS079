
var status = 0;
var banMap = Array(109080000, 109080010, 109040000, 109030001, 109060000, 109010000);

//function start() {
  //  cm.sendNext("#e#d��!����#b����#d���߻����Ա���ҿ��԰����͵����ͼ��");
//}


function action(mode, type, selection) {
	for(var i = 0; i < banMap.length; i++) {
	if (cm.getPlayer().getMapId() == banMap[i]){
		cm.sendOk("��Ҫ͵ȥ�ɣ�");
		cm.dispose();
		}
	}
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 2 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 1) {
            cm.sendNextPrev("#e#d���λ������ʼ��������ҲҪ������Ŷ!");
        } else if (status == 2) {
            cm.sendSimple("#eĳĳð�յ���������..\r\n#L0##e1.#n#d#e ʲô�����߻?#k#l\r\n#L1##e2.#n#d#e ����ݽ���.#k#l\r\n#L2##e3.#n#r#e ������ͼ��#k#l");
        } else if (status == 3) {
            if (selection == 0) {
                cm.sendNext("#e#d���߻Ϊ����ʱ���У�����ʱ�й��棬��ҿɸ��ݹ�����ʾ�����������ݴ��ͣ�ֱ�ӽ��뵽���ͼ���������ĿΪ6������Ŀ��ʼ�����Ҷ��������߻,��÷��Ľ����ɡ�");
                cm.dispose();
            } else if (selection == 1) {
                cm.sendSimple("#d#e���߻��Ϊ6�֣������ǻ����Ŀ. #b\r\n#L0# ������#l\r\n#L1# �ռ�����#l\r\n#L2# ��ѩ��#l\r\n#L3# �����#l\r\n#L6# ��ƿ��#l\r\n#L4# �Ƿ��}����#l\r\n#L5# Ѱ��#l#k");
            } else if (selection == 2) {
				if (!cm.canHold()) {
					cm.sendNext("#d#e��ȷ���Ƿ������п�λ��");
				} else if (cm.getChannelServer().getEvent() > -1) {
					if (cm.haveItem(4031017)) {
						cm.removeAll(4031017);
					}
					cm.saveReturnLocation("EVENT");
					cm.getPlayer().setChalkboard(null);
					cm.warp(cm.getChannelServer().getEvent(), cm.getChannelServer().getEvent() == 109080000 || cm.getChannelServer().getEvent() == 109080010 ? 0 : "join00");
				} else {
					cm.sendNext("#d#e����û�п��ԲμӵĻŶ�����ע�������ʾ���μӻŶ�����߻Ϊ����ʱ�������š�");
				}
				cm.dispose();
			}
        } else if (status == 4) {
            if (selection == 0) {
                cm.sendNext("#b[������]#k �Լ�#e#r�ٶ�#k!");
                cm.dispose();
            } else if (selection == 1) {
                cm.sendNext("#b[�ռ�����] �Լ�#e#r�ٶ�#k!");
                cm.dispose();
            } else if (selection == 2) {
                cm.sendNext("#b[��ѩ��]#k �Լ�#e#r�ٶ�#k!");
                cm.dispose();
            } else if (selection == 3) {
                cm.sendNext("#b[�����]#k �Լ�#e#rGoogle#k!");
                cm.dispose();
			} else if (selection == 6) {
                cm.sendNext("#b[��ƿ��]#k �Լ�#e#r�ٶ�#k!");
                cm.dispose();
            } else if (selection == 4) {
                cm.sendNext("#b[�Ƿ���󿼿���]#k �Լ�#e#r�ٶ�#k!");
                cm.dispose();
            } else if (selection == 5) {
                cm.sendNext("#b[Ѱ��]#k �Լ�#e#r�ٶ�#k!");
                cm.dispose();
            }
        }   
    }
}  