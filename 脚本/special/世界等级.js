
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var xx = -1;
var jiage = -1;
var ÿ��ͻ�� = 200000;
var ���ͻ�Ƶȼ� = 250;
	function start() {
		status = -1;
		action(1, 0, 0);
		}


	function action(mode, type, selection) {
		if (mode == -1) {
		cm.sendOk("��л���㣡");
		cm.dispose();
		} else {

	if (mode == 0) {
		cm.sendOk("��л���㣡");
        	cm.dispose();
        	return;
    		}

	if (mode == 1) {
		status++;
		} else {
		status--;
		}
        if (status == 0) {
			var ���� = Math.round(100/cm.ȡ��ҩͰ������()*Math.pow(10,6))/Math.pow(10,6);
            var gsjb = "";
				gsjb = "#e����ð�յ���½�Ѿ�����ħ��ʦ����,��Ҫð�ռ��ռ���ҩͰը�����ֵ��������ͻ�����ƣ�#eÿ��ͻ��Ϊ10��#n\r\n";
		
				gsjb += "#v4001128##z4001128#���������й���\r\n";
				gsjb += "#r#eð�յ������Ѿ�������ס�˵ȼ�Ϊ:#r#e"+ cm.ȡ���Ƶȼ�() +"��#k#n\r\n";
				gsjb += "���統ǰ�ռ���#B"+ (cm.ȡ��ҩͰ����()*����)+"#["+ cm.ȡ��ҩͰ����()+"/"+cm.ȡ��ҩͰ������()+"]\r\n\r\n";
				gsjb += "#L3##e#b�ύͻ�Ƶȼ�#r[Hot]#l\r\n";
				gsjb += "#L33##e#b���ύ�һ����þ�#r[Hot]#l\r\n";
            cm.sendSimple(gsjb);
        } else if (status == 1) {
			var ���� = Math.round(100/cm.ȡ��ҩͰ������()*Math.pow(10,6))/Math.pow(10,6);
            if (selection == 3) {
            if (cm.haveItem(4001128) == 0) {
                cm.sendNext("�����ʻ�#z4001128#�������㡣");
                status = -1;
			} else {
                beauty = 3;//������3
				cm.sendGetNumber("�������Ӧ#b#v4001128##z4001128##k\r\nĿǰ�ռ������Ϣ - \r\nĿǰ�Ѿ��ռ�: #B"+ (cm.ȡ��ҩͰ����()*����)+"#["+ cm.ȡ��ҩͰ����()+"/"+cm.ȡ��ҩͰ������()+"]\r\n", 1, 1, 9999);   
			}
		}
		if (selection == 33) {
            if (cm.haveItem(4001128) == 0) {
                cm.sendNext("�����ʻ�#z4001128#�������㡣");
                status = -1;
			} else {
                beauty = 33;//������33
				cm.sendGetNumber("�������Ӧ#b#v4001128##z4001128##k\r\n���ﲻ�ύ��ըҩͰͻ�Ƶȼ� - ֱ�Ӷһ����þ�\r\n", 1, 1, 9999);   
			}
		}
        } else if (status == 2) {
			var ���� = Math.round(100/cm.ȡ��ҩͰ������()*Math.pow(10,6))/Math.pow(10,6);
            if (beauty == 1) {
            if (selection <= 0) {
                    cm.sendOk("����Ķһ����ִ���");
                    cm.dispose();
		}
        }else if (beauty == 3) {
            if (cm.haveItem(4001128, selection) && cm.ȡ���Ƶȼ�() < ���ͻ�Ƶȼ�) {//ֻ�����Ƶȼ�С�����ͻ�Ƶȼ��ſ��Լ��������ߣ��趫��������������
                    cm.����Ʒ(4001128, -selection);
					cm.д���ҩͰ����(selection);
					//cm.gainNX(selection);//�����
					cm.getPlayer().modifyCSPoints(2,selection, true);//����Ӷ��
					cm.refreshMaplePoints();
				if(cm.ȡ��ҩͰ����()>=cm.ȡ��ҩͰ������()){//ֻ�дﵽ�������Ż����������ͻ��
					if(cm.ȡ���Ƶȼ�() < 150){//�ȼ��ֶ�ͻ��
						//ֻ�����Ƶȼ�С��200��������
						cm.д�����Ƶȼ�(10);
					}else{//������Ǵ��ڵ���200ͻ��5��
						cm.д�����Ƶȼ�(5);	
					}
					
					cm.ȫ��ͻ������ȼ�����();
					cm.�۳���ҩͰ����(cm.ȡ��ҩͰ������());
					cm.д���ҩͰ������(ÿ��ͻ��);	

				}
					cm.sendOk("�ɹ�Ϊð�մ�½������ΰ��Ĺ��ף�лл\r\n���統ǰ�ռ���#B"+ (cm.ȡ��ҩͰ����()*����)+"#["+ cm.ȡ��ҩͰ����()+"/"+cm.ȡ��ҩͰ������()+"]\r\n");
					cm.getChar().saveToDB(false,false);
					cm.dispose();
                } else {//����Ͳ���ͻ���ˣ��͵�������ʾ
                    cm.sendNext("�����������������ӵ�е�ըҩͰ�������㣬�޷�ͻ�����ޡ������Ѿ��ﵽ���ֵ��");
                    cm.dispose();
                }
			} 
			else if (beauty == 33) {
            if (cm.haveItem(4001128, selection)) {
                    cm.����Ʒ(4001128, -selection);
					
					cm.getPlayer().modifyCSPoints(2,selection, true);//����Ӷ��

					cm.sendOk("�ɹ���ըҩͰ�һ��˵��þ�");
					
					cm.dispose();
                } else {
                    cm.sendNext("�����������������ӵ�е�ըҩͰ��������");
                    cm.dispose();
                }
			} 
        }
    }
}
