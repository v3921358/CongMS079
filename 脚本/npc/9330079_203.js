/*
     �� ��
*/

var Price = 1000;               //���������ü۸��
var Price2 = 4310014; 	//�������öһ�����
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
						cm.sendGetNumber("#e\t\t������Ҫ�һ� #z"+Price2+"# #v"+Price2+"# ��\r\n\r\n\t #r" + Price + " ���ɶһ� #r #z"+Price2+"# #v"+Price2+"# 1 ��\r\n\r\n����ǰ�ĵ�����Ϊ #r"+ cm.getPlayer().getCSPoints(1) +"#k\r\n\r\n��#d����#r1#d��һ�1��#b#z"+Price2+"##k��\r\n#r������һ�����\r\n",1,1,10000);		 				 
        } else if (status == 1) {  
        			fee = selection;
                var jbyue = fee*Price;
        			if(cm.getPlayer().getCSPoints(1) >= fee*Price){
            		cm.sendYesNo("��ȷ��Ҫ�һ�#r"+fee+"#k��#v"+Price2+"#����������#r"+jbyue+"#k���" );  
            	}else{
            		cm.sendOk("��ĵ�����޷��һ�!");
            		cm.dispose();
            	}
	    	
        } else if (status == 2) {
        			if(cm.getPlayer().getCSPoints(1) >= fee*Price){
								cm.gainItem(Price2,fee);
						cm.getPlayer().modifyCSPoints(1, -fee*Price);
								cm.sendOk("�һ��ɹ�!������ǰ�ĵ�����Ϊ #r"+ cm.getPlayer().getCSPoints(1) +"#k !" );
        			}else{
        				cm.sendOk("��ĵ�����޷��һ�!");
							}
							cm.dispose();
						
	   		}
          
  	 }
}

