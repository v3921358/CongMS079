function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			
            text += "#e#d�����ʿ������������˵�������ʵ��.[ǧ��������]��������˯���ˣ����Ǹ�С����ἤŭ����~�����Ը��ʹ��Ŷ����������ɣ�\r\n\r\n"//3
            
            text += "#L2##e#r�뿪�˵�ͼ.#l\r\n"//3
           
            cm.sendSimple(text);
        } else if (selection == 1) {
		cm.openNpc(9270045, 1);
        } else if (selection == 2) {
			cm.warp(910000000); 
			cm.dispose(); 
        } 
    }
}


