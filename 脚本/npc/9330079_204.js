

var Price = 1000;               //���������ü۸��
var Price2 = 4310014; 
var svrName = "ð�յ�";       //�����������÷���������

//****����Ϊ��������,�����Ը��������Ҫ����*******//

var fee;
var status = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
	cm.sendOk("��Ҫ�Ļ���ʱ������!!");
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.sendOk("��Ҫ�Ļ���ʱ������!!");
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
                   if (status == 0) {          	           	 
						cm.sendGetNumber("#e������Ҫ�һ������\r\n#r#z"+Price2+"# #v"+Price2+"# 1�� �ɶһ� #r"+Price+" ���\r\n\r\n����ǰ�� #z"+Price2+"# #v"+Price2+"# ���Ϊ #r"+ cm.itemQuantity(Price2) +"#k ��\r\n\r\n��#d����#r1#d��۳�1��#b#z"+Price2+"##k��\r\n\r\n#r��������Ҫ�һ������֣�",1,1,10000);		 				 
        } else if (status == 1) {  
        			fee = selection;
                                var jbyue = fee*Price;
        			if (cm.haveItem(Price2,fee)){
            		cm.sendYesNo("��ȷ��Ҫ�һ�#r"+jbyue+"#k�������������#r"+fee+"#k��#v"+Price2+"#��" );  
            	}else{
            		cm.sendOk("���#v"+Price2+"#�����޷��һ�!");
            		cm.dispose();
            	}
	    	
        } else if (status == 2) {
        			if (cm.haveItem(Price2,fee)){
								cm.gainItem(Price2,-fee);
						cm.getPlayer().modifyCSPoints(1, fee*Price);
								cm.sendOk("�һ��ɹ�!������ǰ�ĵ�����Ϊ #r"+ cm.getPlayer().getCSPoints(1) +"#k !" );
        			}else{
        				cm.sendOk("���#v"+Price2+"#�����޷��һ�!");
							}
							cm.dispose();
						
	   		}
          
  	 }
}

