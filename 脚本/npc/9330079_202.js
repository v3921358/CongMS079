/*
     �� ��
*/

var Price = 4000463;               //���������ü۸��
var Price2 = 4000313; 
var ���� = 100; 
var svrName = "�黳ð�յ�";       //�����������÷���������

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
						cm.sendGetNumber("#e������Ҫ�һ�#z"+Price+"# #v"+Price+"#��\r\n\r\n#r#v"+Price2+"##z"+Price2+"# "+����+" �����ɶһ�#r#v"+Price+"##z"+Price+"# 1 ��#n\r\n����ǰ��#v"+Price2+"##z"+Price2+"#���Ϊ #r"+ cm.itemQuantity(Price2) +"#k\r\n��#d���� #r1#d ��һ�1��#b#v"+Price+"##z"+Price+"#��\r\n\r\n#r��������Ҫ�һ�������",1,1,10000);		 				 
        } else if (status == 1) {  
        			fee = selection;
                var jbyue = fee*����;
        			if(cm.haveItem(Price2,fee*����)){
            		cm.sendYesNo("��ȷ��Ҫ�һ�#r"+fee+"#k��#v"+Price+"# ����������#r"+jbyue+"#k#v"+Price2+"# " );  
            	}else{
            		cm.sendOk("���#v"+Price2+"##z"+Price2+"#�����޷��һ�!");
            		cm.dispose();
            	}
	    	
        } else if (status == 2) {
        			if(cm.haveItem(Price2,fee*����)){
								
								cm.gainItem(Price2,-fee*����);
                                                                cm.gainItem(Price,fee);
								cm.sendOk("�һ��ɹ�!������ǰ�� #v"+Price2+"##z"+Price2+"# ���Ϊ #r"+ cm.itemQuantity(Price2) +"#k !" );
        			}else{
        				cm.sendOk("��� #v"+Price2+"##z"+Price2+"# �����޷��һ�!");
							}
							cm.dispose();
						
	   		}
          
  	 }
}

