/* 
 * �ű�����: cm
 * �ű���;: ����ű�
 * �ű�����: ��һ��QQ��1500663066
 * ����ʱ��: 2015/12/18
 */
 
 
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var dds;
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            if (status == 0) {
                cm.sendNext("�����Ҫ����н�����������Ұɡ�");
                cm.dispose();
            }
            status--;
        }
        if (status == 0) {
				 
               if (cm.haveItem(4200007, 1)){
				cm.sendOk("���Ǹ����ɵ��ˣ�������򿪶һ���");
				cm.dispose();    
			   }
				 
            var gsjb = "";
            gsjb = "\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\n";
            gsjb += "#L16##r"+��ɫ��ͷ+"����С�㻻����(10��������)#v4031636##v4031640##v4031644##v4031648##k#l\r\n\r\n";
			gsjb += "#L10011##r"+��ɫ��ͷ+"С�㻻����\r\n\r\n";
			
			gsjb += "#L10012##r"+��ɫ��ͷ+"���㻻����\r\n\r\n";
			
           
            cm.sendSimple(gsjb);
        }
		else if (status == 1) {
            if (cm.getPlayer() >= 1 && cm.getPlayer() <= 5) {
                cm.sendOk("GM���ܲ���һ���");
                cm.dispose();
            }
            if (selection == 0) {
                if (cm.haveItem(4031627) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 0
           cm.sendGetNumber("����������:\r\n#b���� - (#r10 = 1#b)\r\n  ��ǰӵ������: #c4031627# ��#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 16) {
                    beauty = 16
					cm.sendYesNo("������������������#r��ǰӵ�е���#k ����������������������\r\n     #v4031627#  #r#c4031627##k�� #v4031633#  #r#c4031633##k�� #v4031634#  #r#c4031634##k�� #v4031635#  #r#c4031635##k��\r\n     #v4031630#  #r#c4031630##k�� #v4031637#  #r#c4031637##k�� #v4031638#  #r#c4031638##k�� #v4031639#  #r#c4031639##k��\r\n     #v4031628#  #r#c4031628##k�� #v4031641#  #r#c4031641##k�� #v4031642#  #r#c4031642##k�� #v4031643#  #r#c4031643##k��\r\n     #v4031631# #r#c4031631##k�� #v4031645# #r#c4031645##k�� #v4031646# #r#c4031646##k�� #v4031647# #r#c4031647##k��\r\n\r\n������������������#r��ǰӵ�е���#k ����������������������\r\n�Ƿ��ȫ���һ�Ϊ�����㣿(����Ա������#r10W#k������Ŷ!)");
            } else if (selection == 1) {
                if (cm.haveItem(4031633) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 1
           cm.sendGetNumber("����������:\r\n#b���� - (#r6 = 1#b)\r\n  ��ǰӵ������: #c4031633# ��#r \r\n", 1, 1, 100000 ); }

            }  
			 else if (selection == 2) {
                if (cm.haveItem(4031634) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 2
           cm.sendGetNumber("����������:\r\n#b���� - (#r4 = 1#b)\r\n  ��ǰӵ������: #c4031634# ��#r \r\n", 1, 1, 100000 ); }

            }			
			else if (selection == 3) {
                if (cm.haveItem(4031635) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 3
           cm.sendGetNumber("����������:\r\n#b���� - (#r2 = 1#b)\r\n  ��ǰӵ������: #c4031635# ��#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 4) {
                if (cm.haveItem(4031630) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 4
           cm.sendGetNumber("����������:\r\n#b���� - (#r10 = 1#b)\r\n  ��ǰӵ������: #c4031630# ��#r \r\n", 1, 1, 100000 ); }


            } else if (selection == 5) {
                if (cm.haveItem(4031637) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 5
           cm.sendGetNumber("����������:\r\n#b���� - (#r6 = 1#b)\r\n  ��ǰӵ������: #c4031637# ��#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 6) {
                if (cm.haveItem(4031638) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 6
           cm.sendGetNumber("����������:\r\n#b���� - (#r4 = 1#b)\r\n  ��ǰӵ������: #c4031638# ��#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 7) {
                if (cm.haveItem(4031639) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 7
           cm.sendGetNumber("����������:\r\n#b���� - (#r2 = 1#b)\r\n  ��ǰӵ������: #c4031639# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 8) {
                if (cm.haveItem(4031628) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 8
           cm.sendGetNumber("����������:\r\n#b���� - (#r10 = 1#b)\r\n  ��ǰӵ������: #c4031628# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 9) {
                if (cm.haveItem(4031641) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 9
           cm.sendGetNumber("����������:\r\n#b���� - (#r6 = 1#b)\r\n  ��ǰӵ������: #c4031641# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 10) {
                if (cm.haveItem(4031642) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 10
           cm.sendGetNumber("����������:\r\n#b���� - (#r4 = 1#b)\r\n  ��ǰӵ������: #c4031642# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 11) {
                if (cm.haveItem(4031643) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 11
           cm.sendGetNumber("����������:\r\n#b���� - (#r2 = 1#b)\r\n  ��ǰӵ������: #c4031643# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 12) {
                if (cm.haveItem(4031631) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 12
           cm.sendGetNumber("����������:\r\n#b���� - (#r10 = 1#b)\r\n  ��ǰӵ������: #c4031631# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 13) {
                if (cm.haveItem(4031645) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 13
           cm.sendGetNumber("����������:\r\n#b���� - (#r6 = 1#b)\r\n  ��ǰӵ������: #c4031645# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 14) {
                if (cm.haveItem(4031646) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 14
           cm.sendGetNumber("����������:\r\n#b���� - (#r4 = 1#b)\r\n  ��ǰӵ������: #c4031646# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 15) {
                if (cm.haveItem(4031647) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 15
           cm.sendGetNumber("����������:\r\n#b���� - (#r2 = 1#b)\r\n  ��ǰӵ������: #c4031647# ��#r \r\n", 1, 1, 100000 ); }

            }
			
			else if (selection == 10011) {
				cm.dispose();
				cm.openNpc(9330045, 10011 );

        }
			
			else if (selection == 10012) {
				cm.dispose();
				cm.openNpc(9330045, 10012 );

        }
			
			
			
			
			else if (selection == 100) {
                if (cm.haveItem(4031636) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 100
           cm.sendGetNumber("����������:\r\n#b���� - (#r1 = 50W#b)\r\n  ��ǰӵ������: #c4031636# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 101) {
                if (cm.haveItem(4031640) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 101
           cm.sendGetNumber("����������:\r\n#b���� - (#r5 = 1#b)\r\n  ��ǰӵ������: #c4031640# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 102) {
                if (cm.haveItem(4031644) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 102
           cm.sendGetNumber("����������:\r\n#b���� - (#r10 = 1#b)\r\n  ��ǰӵ������: #c4031644# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 103) {
                if (cm.haveItem(4031648) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 103
           cm.sendGetNumber("����������:\r\n#b���� - (#r5 = 1#b)\r\n  ��ǰӵ������: #c4031648# ��#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 104) {
                if (cm.haveItem(4031629) == 0 && cm.haveItem(4031632) == 0 && cm.haveItem(4031640) == 0 && cm.haveItem(4031636) == 0 && cm.haveItem(4031644) == 0 && cm.haveItem(4031648) == 0) {
                    cm.sendNext("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 104
					cm.sendGetNumber("#v4031629##v4031632##v4031636##v4031640##v4031644##v4031648# \r\n���������նһ�������:\r\n#b���� - (#r1�� = 400W#b)\r\n", 1, 1, 100000 ); }
            }

        } else if (status == 2) {
            
                 if (cm.getPlayer().getMeso() < 100000){
						 cm.sendOk("��Ҳ��㡣");
						 cm.dispose();
				 }else{
						if (beauty == 16) {
								for (i = 0; i < 100; i++) {
							if (cm.haveItem(4031627, 10)){
							 cm.gainItem(4031627, -10);
							 cm.gainItem(4031636, 1);
							}
							if (cm.haveItem(4031633, 6)){
							 cm.gainItem(4031633, -6);
							 cm.gainItem(4031636, 1);
							}
							if (cm.haveItem(4031634, 4)){
							 cm.gainItem(4031634, -4);
							 cm.gainItem(4031636, 1);
							}
							if (cm.haveItem(4031635, 2)){
							 cm.gainItem(4031635, -2);
							 cm.gainItem(4031636, 1);
							}
							
							if (cm.haveItem(4031630, 10)){
							 cm.gainItem(4031630, -10);
							 cm.gainItem(4031640, 1);
							}
							if (cm.haveItem(4031637, 6)){
							 cm.gainItem(4031637, -6);
							 cm.gainItem(4031640, 1);
							}
							if (cm.haveItem(4031638, 4)){
							 cm.gainItem(4031638, -4);
							 cm.gainItem(4031640, 1);
							}
							if (cm.haveItem(4031639, 2)){
							 cm.gainItem(4031639, -2);
							 cm.gainItem(4031640, 1);
							}
							
							
							if (cm.haveItem(4031628, 10)){
							 cm.gainItem(4031628, -10);
							 cm.gainItem(4031644, 1);
							}
							if (cm.haveItem(4031641, 6)){
							 cm.gainItem(4031641, -6);
							 cm.gainItem(4031644, 1);
							}
							if (cm.haveItem(4031642, 4)){
							 cm.gainItem(4031642, -4);
							 cm.gainItem(4031644, 1);
							}
							if (cm.haveItem(4031643, 2)){
							 cm.gainItem(4031643, -2);
							 cm.gainItem(4031644, 1);
							}
							
							
							if (cm.haveItem(4031631, 10)){
							 cm.gainItem(4031631, -10);
							 cm.gainItem(4031648, 1);
							}
							if (cm.haveItem(4031645, 6)){
							 cm.gainItem(4031645, -6);
							 cm.gainItem(4031648, 1);
							}
							if (cm.haveItem(4031646, 4)){
							 cm.gainItem(4031646, -4);
							 cm.gainItem(4031648, 1);
							}
							if (cm.haveItem(4031647, 2)){
							 cm.gainItem(4031647, -2);
							 cm.gainItem(4031648, 1);
							}
								}	
						 cm.gainMeso(-100000); //�Ӽ����
						 cm.sendOk("�һ��ɹ���");
						 cm.dispose();	
				 }
            }
          /*  if (beauty == 16) {
				
					 xy1 = cm.getPlayer().getItemQuantity(4031627, false);
					 var xyb = xy1/10;
                if (xyb>0){
					if (cm.haveItem(4031627, 10)){
					 cm.gainItem(4031627, -xyb*10);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                }
					 xy2 = cm.getPlayer().getItemQuantity(4031633, false);
					 var xyb = xy2/6;
                if (xyb>0){
					if (cm.haveItem(4031633, 6)){
					 cm.gainItem(4031633, -xyb*6);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                }
					 xy3 = cm.getPlayer().getItemQuantity(4031634, false);
					 var xyb = xy3/4;
                if (xyb>0){
					if (cm.haveItem(4031634, 4)){
					 cm.gainItem(4031634, -xyb*4);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                }
					 xy4 = cm.getPlayer().getItemQuantity(4031635, false);
					 var xyb = xy4/2;
                if (xyb>0){
					if (cm.haveItem(4031635, 2)){
					 cm.gainItem(4031635, -xyb*2);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                }
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
            }*/
			
						

            if (beauty == 0) {
                if (cm.haveItem(4031627, selection*10)){
					 cm.gainItem(4031627, -selection*10);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            } if (beauty == 1) {
                if (cm.haveItem(4031633, selection*6)){
					 cm.gainItem(4031633, -selection*6);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 2) {
                if (cm.haveItem(4031634, selection*4)){
					 cm.gainItem(4031634, -selection*4);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 3) {
                if (cm.haveItem(4031635, selection*2)){
					 cm.gainItem(4031635, -selection*2);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 4) {
                if (cm.haveItem(4031630, selection*10)){
					 cm.gainItem(4031630, -selection*10);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 5) {
                if (cm.haveItem(4031637, selection*6)){
					 cm.gainItem(4031637, -selection*6);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 6) {
                if (cm.haveItem(4031638, selection*4)){
					 cm.gainItem(4031638, -selection*4);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 7) {
                if (cm.haveItem(4031639, selection*2)){
					 cm.gainItem(4031639, -selection*2);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 8) {
                if (cm.haveItem(4031628, selection*10)){
					 cm.gainItem(4031628, -selection*10);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 9) {
                if (cm.haveItem(4031641, selection*6)){
					 cm.gainItem(4031641, -selection*6);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 10) {
                if (cm.haveItem(4031642, selection*4)){
					 cm.gainItem(4031642, -selection*4);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 11) {
                if (cm.haveItem(4031643, selection*2)){
					 cm.gainItem(4031643, -selection*2);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 12) {
                if (cm.haveItem(4031631, selection*10)){
					 cm.gainItem(4031631, -selection*10);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 13) {
                if (cm.haveItem(4031645, selection*6)){
					 cm.gainItem(4031645, -selection*6);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 14) {
                if (cm.haveItem(4031646, selection*4)){
					 cm.gainItem(4031646, -selection*4);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 15) {
                if (cm.haveItem(4031647, selection*2)){
					 cm.gainItem(4031647, -selection*2);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 100) {
                if (cm.haveItem(4031636, selection)){
					 cm.gainItem(4031636, -selection);
					cm.gainMeso(500000* selection); //�Ӽ����
					// cm.gainExp(30000* selection);
					// cm.gainNX(selection*500);	//�Ӽ���ȯ
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					//cm.worldMessage(6,"��ң�["+cm.getName()+"]�����˴��㣡�һ���"+selection*500+"��ȯ��");
				    cm.����(1,"��ң�["+cm.getName()+"]�����˴��㣡�һ��� "+selection*500000+" ��ң�");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }
			if (beauty == 101) {
                if (cm.haveItem(4031640, selection*5)){
					 cm.gainItem(4031640, -selection*5);
					 cm.gainItem(4000463, selection);
					 cm.gainExp(40000* selection);
					 
                     cm.sendOk("�һ��ɹ���");
				    cm.����(1,"��ң�["+cm.getName()+"]�����˴��㣡�һ��� "+selection+" ���������ң�");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }
			if (beauty == 102) {
                if (cm.haveItem(4031644, selection*10)){
					 cm.gainItem(4031644, -selection*10);
					 cm.gainItem(4000038, selection);
					 cm.gainExp(40000* selection);
                     cm.sendOk("�һ��ɹ���");
				    cm.����(1,"��ң�["+cm.getName()+"]�����˴��㣡�һ��� "+selection+" ���𱭣�");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 103) {
                if (cm.haveItem(4031648, selection*5)){
					 cm.gainItem(4031648, -selection*5);
					 cm.gainItem(4000038, selection);
					 cm.gainExp(50000* selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					//cm.worldMessage(6,"��ң�["+cm.getName()+"]�����˴��㣡�һ���"+selection*500+"��ȯ��");
				    cm.����(1,"��ң�["+cm.getName()+"]�����˴��㣡�һ��� "+selection+" ���𱭣�");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }if (beauty == 104) { 
                if (cm.haveItem(4031629, selection) && cm.haveItem(4031632, selection) && cm.haveItem(4031636, selection) && cm.haveItem(4031640, selection) && cm.haveItem(4031644, selection) && cm.haveItem(4031648, selection)){
					 cm.gainItem(4031629, -selection);
					 cm.gainItem(4031632, -selection);
					 cm.gainItem(4031636, -selection);
					 cm.gainItem(4031640, -selection);
					 cm.gainItem(4031644, -selection);
					 cm.gainItem(4031648, -selection);
					 cm.gainMeso(4000000* selection); //�Ӽ����
					 cm.gainExp(500000* selection);
                     //cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �һ���:[#r"+(selection*100000)+"#k] ���");
                     cm.sendOk("�һ��ɹ���");
					//cm.worldMessage(6,"��ң�["+cm.getName()+"]�����˴��㣡�һ���"+selection*500+"��ȯ��");
				    cm.����(1,"��ң�["+cm.getName()+"]�������գ������ "+selection*4000000+" ��ң�");
					 cm.dispose();
                } else {
                    cm.sendNext("�������㣬�޷��һ���");
                    cm.dispose()
                }
            }
            status = -1;
        } else {
            cm.dispose();
        }
    }
}
var acc = "#fEffect/CharacterEff/1112903/0/0#";//������
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//��ɫ�Ҽ�ͷ
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//ѡ�����
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon2/7#";
var ����ne = "#fUI/UIWindow/Quest/icon6/7#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ݮ = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // ��ɫ��ݮ
var ��ݮ1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // ����ɫ��ݮ
var ��ݮ2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // ��ɫ��ݮ
var ��ݮ3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // ��ɫ��ݮ
var ��ݮ4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // ��ɫ��ݮ
var ��ݮ5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // ��ɫ��ݮ
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";