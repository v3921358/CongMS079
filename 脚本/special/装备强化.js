var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �Ҹ� = "#k��ܰ��ʾ���κηǷ��������ҷ�Ŵ���.��ɱ��������.";
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
            
          
            var txt1 = "#L2#" + ��ɫ��ͷ + "#d#v1012292#�һ�ȫ����2����Ҫ#d#v4170018#*66\r\n\r\n";
            var txt2 = "#L3#" + ��ɫ��ͷ + "#d#v1012293#����ȫ����5����Ҫ#d#v4170007#*66\r\n\r\n";
            var txt3 = "#L4#" + ��ɫ��ͷ + "#d#v1012294#����ȫ����8����Ҫ#d#v4170006#*66\r\n\r\n";
            var txt4 = "#L5#" + ��ɫ��ͷ + "#d#v1012295#����ȫ����12����Ҫ#d#v4170005#*66\r\n\r\n";
            var txt5 = "#L6#" + ��ɫ��ͷ + "#d#v1012291#����ȫ����18����Ҫ#d#v4170004#*66\r\n\r\n";
       

            cm.sendSimple("#rѪ����������,ÿ����������Ҫ��һ����Ʒ#k\r\n"+ txt1 + ""+ txt2 + ""+ txt3 + ""+ txt4 + ""+ txt5 + "");
            

        } else if (status == 1) {
             if (selection == 2) { //���๦��
             if((cm.haveItem(4170018,66))) {
                        cm.gainItem(4170018,-66);
                       cm.gainItem(1012292,2,2,2,2,200,200,2,2,0,0,0,0,0,0);//ѫ��
            cm.sendOk("��߶һ��ɹ���");
			
                   cm.dispose();
			}else{
cm.sendOk("��û��������Ʒ,��������!\r\n#d#v4170018#X66\r\n");
	     	   cm.dispose();
}
            } else if (selection == 3) { //���๦��
   if((cm.haveItem(4170007,66))&& (cm.haveItem(1012292,1))) {
                        cm.gainItem(4170007,-66);
                        cm.gainItem(1012292,-1);
                       cm.gainItem(1012293,5,5,5,5,500,500,5,5,0,0,0,0,0,0);//ѫ��
            cm.sendOk("��߶һ��ɹ���");
			
                   cm.dispose();
			}else{
cm.sendOk("��û��������Ʒ,��������!\r\n#d#v1012292#X1#v4170007#X66\r\n");
	     	   cm.dispose();
}
            } else if (selection == 4) { //���๦��
   if((cm.haveItem(4170006,66))&& (cm.haveItem(1012293,1))) {
                        cm.gainItem(4170006,-66);
                        cm.gainItem(1012293,-1);
                       cm.gainItem(1012294,8,8,8,8,800,800,8,8,0,0,0,0,0,0);//ѫ��
            cm.sendOk("��߶һ��ɹ���");
			
                   cm.dispose();
			}else{
cm.sendOk("��û��������Ʒ,��������!\r\n#d#v4170006#X66#v1012293#X1\r\n");
	     	   cm.dispose();
}
            } else if (selection == 5) { //���๦��
  if((cm.haveItem(4170005,66))&& (cm.haveItem(1012294,1))) {
                        cm.gainItem(4170005,-66);
                        cm.gainItem(1012294,-1);
                       cm.gainItem(1012295,12,12,12,12,1000,1000,12,12,0,0,0,0,0,0);//ѫ��
            cm.sendOk("��߶һ��ɹ���");
			
                   cm.dispose();
			}else{
cm.sendOk("��û��������Ʒ,��������!\r\n#d#v4170004#X66#v1012294#X1\r\n");
	     	   cm.dispose();
}
            } else if (selection == 6) { //���๦��
   if((cm.haveItem(4170004,66))&& (cm.haveItem(1012295,1))) {
                        cm.gainItem(4170004,-66);
                        cm.gainItem(1012295,-1);
                       cm.gainItem(1012291,18,18,18,18,3000,3000,18,18,0,0,0,0,0,0);//ѫ��
            cm.sendOk("��߶һ��ɹ���");
			
                   cm.dispose();
			}else{
cm.sendOk("��û��������Ʒ,��������!\r\n#d#v4170004#X66#v1012295#X1\r\n");
	     	   cm.dispose();
}
            }
        }
    }
}
