var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ttt ="#fUI/UIWindow.img/Quest/icon9/0#";
var xxx ="#fUI/UIWindow.img/Quest/icon8/0#";
var sss ="#fUI/UIWindow.img/QuestIcon/3/0#";
var ��ɫ = "#fEffect/CharacterEff/1114000/2/0#";
var status = 0;
var fstype = 0;


	function start() {
		status = -1;
		action(1, 0, 0);
		}
	function action(mode, type, selection) {
		if (mode == -1) {
		cm.dispose();
		} else {
		if (status >= 0 && mode == 0) {
		cm.dispose();
		return;
		}
		if (mode == 1)
		status++;
		else
		status--;


	if (status == 0) {

	    var textz = "#r��������ǿ��װ��! #k\r\n#bע��:װ����ŵ�һ��#k#k#l#rǿ���ɹ���ȫ����ϲŶ!#k\r\n";

		textz += "------------------------------------------------------\r\n";
               
	//    textz += "#b#L9#" + ��ɫ + "ʹ��1��#z4170000##v4170000#����װ��30HP��30MP��1�����1ħ����100%�ɹ���\r\n\r\n";
		
		//textz += "#b#L4#" + ��ɫ + "ʹ��30����ֵ������װ����ά��20�㣨ʧ�ܷ�10�ң�\r\n\r\n";
		
		
		//textz += "#b#L55#" + ��ɫ + "ʹ��2500�������װ��1������������7�Σ���#rʧ�ܷ���#k��\r\n\r\n";
		textz += "#b#L66#" + ��ɫ + "ʹ��100�������װ��50��#rHP(Ѫ)#b��ʧ�ܲ�������#l\r\n\r\n";
		textz += "#b#L7#" + ��ɫ + "ʹ��200����ȯ����װ��50��#rMP(��)#b��ʧ�ܲ�������#l\r\n\r\n";
		//textz += "\t\t#e#r#L777#" + ��ɫ + "#v4000038#ǿ��װ����������\r\n\r\n";
		
		
		//textz += "#b#L6#" + ��ɫ + "ʹ��2000W�������װ����ά��5�㣨ʧ�ܲ�������\r\n\r\n";
		//textz += "#b#L66#" + ��ɫ + "ʹ��2000�������װ��100��HP  ��ʧ�ܷ�700�㣩\r\n\r\n";
		
		//textz += "#b#L44#" + ��ɫ + "ʹ��3����ֵ������װ����ά��2��(ʧ�ܷ�1��)��10��\r\n\r\n";
		
		//textz += "#b#L10#" + ��ɫ + "ʹ��1��#z4170009##v4170009#�һ�һ��#z2370000##v2370000#\r\n\r\n";
		//textz += "#r#L8#" + ��ɫ�ǵ� + "��1��ʱ��֮������װ������10��100%�ɹ����۳��غϣ�\r\n";



		cm.sendSimple (textz);  
  
//----------------------------------------------------------------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------------------------------------------------------------		
	}else if (status == 1) {

            if (selection == 0) { //����ĸ��
                fstype = 0;
                cm.sendNext("��Ŀǰѡ�����������ĸ������װ��10�������м���ʧ�ܣ�ʧ���˻�һ�룬�ɹ��˻غϼ�1��");

            }else if (selection == 1) { //�ǻ�ĸ��
                fstype = 1;
                cm.sendNext("��Ŀǰѡ��������ǻ�ĸ������װ��10�������м���ʧ�ܣ�ʧ���˻�һ�룬�ɹ��˻غϼ�1��");

            }else if (selection == 2) { //����ĸ��
                fstype = 2;
                cm.sendNext("��Ŀǰѡ�����������ĸ������װ��10���ݣ��м���ʧ�ܣ�ʧ���˻�һ�룬�ɹ��˻غϼ�1��");

            }else if (selection == 3) { //����ĸ��
                fstype = 3;
                cm.sendNext("��Ŀǰѡ�����������ĸ������װ��10�������м���ʧ�ܣ�ʧ���˻�һ�룬�ɹ��˻غϼ�1��");
        
			}else if (selection == 4) { //����
                fstype = 4;
                cm.sendNext("��Ŀǰѡ�������30��ֵ������װ����ά��20.\r\n��ʧ�ܷ���10��ֵ�ң�");
			
			}else if (selection == 44) { //����
                fstype = 44;
                cm.sendNext("��Ŀǰѡ�������3��ֵ������װ����ά��2.\r\n��ʧ�ܷ���1��ֵ�ң�");

            }else if (selection == 5) { //����
                fstype = 5;
                cm.sendNext("��Ŀǰѡ�������2���ȯ����װ����ά��10����ʧ�ܲ�������");

            }else if (selection == 55) { //����
                fstype = 55;
                 cm.openNpc(9900004, 1246);

            }else if (selection == 6) { //����
                fstype = 6;
                cm.sendNext("��Ŀǰѡ�������2000W�������װ����ά��5�㣨ʧ�ܲ�������");

            }else if (selection == 7) { //������
                fstype = 7;
                cm.sendNext("��Ŀǰѡ�������200���������װ��50��MP��ʧ�ܲ�������");
				}else if (selection == 777) { //������
                fstype = 777;
                cm.openNpc(9900004,122222);	

				}else if (selection == 66) { //������
                fstype = 66;
                cm.sendNext("��Ŀǰѡ�������100�������װ��50��HP��ʧ�ܲ�������");
				
            }else if (selection == 8) { //ʱ��֮ʯ
                fstype = 8;
                cm.sendNext("��Ŀǰѡ�������ʱ��֮ʯ����װ��������ħ����10.�ӹ���2000���100%�ɹ����غϼ�1��");
            }else if (selection == 9) { //������ĵ�
                fstype = 9;
                cm.sendNext("��Ŀǰѡ������÷�����ĵ�����װ��30HP��30MP��1�����1ħ����100%�ɹ��������غϣ���");
            }else if (selection == 10) { //����������
                fstype = 10;
                cm.sendNext("��Ŀǰѡ������ú����������һ����ӱ�����");
            }

//----------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------

      } else if (status == 2) {

            if (fstype == 0) { //����ĸ��
                fstype = 0;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
  
                }
            }


            if (fstype == 1) { //�ǻ�ĸ��
                fstype = 1;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }

            if (fstype == 2) { //����ĸ��
                fstype = 2;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }

            if (fstype == 3) { //����ĸ��
                fstype = 3;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }
             

            if (fstype == 4) { //����ĸ��
                fstype = 4;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }


			if (fstype == 44) { //����ĸ��
                fstype = 44;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }
			
			
            if (fstype == 5) { //����ĸ��
                fstype = 5;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }


            if (fstype == 6) { //����ĸ��
                fstype = 6;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }


            if (fstype == 66) { //����ĸ��
                fstype = 66;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }

            if (fstype == 7) { //����ĸ��
                fstype = 7;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("�ݲ�֧�ֵ�ȯװ�����ǣ���ʹ����ͨװ����");
                    cm.dispose();
                }
            }
			
			if (fstype == 9) { //����ĸ��
                fstype = 9;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                }
            }

//----------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------
	if (fstype == 0) {
		if (!cm.haveItem(4005000,10))  {
                    cm.sendOk("�����#r 10 #k��#z4005000##v4005000#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setStr(item.getStr()+10);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005000,-10);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        �ɹ�Ϊװ������10����"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005000,-2);
		 cm.sendOk("ǿ��ʧ�ܣ��˻���8������ˮ��");	
		}
		 cm.dispose();
		}



	} else if (fstype == 1) {
		if (!cm.haveItem(4005001,10))  {
                    cm.sendOk("�����#r 10 #k��#z4005001##v4005001#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setInt(item.getInt()+10);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005001,-10);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        �ɹ�Ϊװ������10����"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005001,-2);
		 cm.sendOk("ǿ��ʧ�ܣ��˻���8���ǻ�ˮ��");	
		}
		 cm.dispose();
		}

	} else if (fstype == 2) {
		if (!cm.haveItem(4005002,10))  {
                    cm.sendOk("�����#r 10 #k��#z4005002##v4005002#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setDex(item.getDex()+20);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005002,-10);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        �ɹ�Ϊװ������10����"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005002,-2);
		 cm.sendOk("ǿ��ʧ�ܣ��˻���8������ˮ��");	
		}
		 cm.dispose();
		}

	} else if (fstype == 3) {
		if (!cm.haveItem(4005003,10))  {
                    cm.sendOk("�����#r 10 #k��#z4005003##v4005003#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setLuk(item.getLuk()+20);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005003,-10);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        �ɹ�Ϊװ������10����"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005003,-2);
		 cm.sendOk("ǿ��ʧ�ܣ��˻���8������ˮ��");	
		}
		 cm.dispose();
		}

	}  else if (fstype == 4) {
              if (cm.getmoneyb() <= 30){
                    cm.sendOk("�����30��ֵ�Ҽӹ���");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr() + 20); //����װ������
		 item.setDex(item.getDex() + 20);//����װ������
		 item.setInt(item.getInt() + 20);//����װ������
		 item.setLuk(item.getLuk() + 20);//����װ������
		 //cm.gainItem(4001084,-1);
		 cm.setmoneyb(-30);
		 cm.gainjf(+30);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
              //cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        װ��������ά��20��");
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.setmoneyb(-20);
		 cm.gainjf(+20);
		 cm.sendOk("ǿ��ʧ�ܣ��˻���10����ֵ��");	
		}
		 cm.dispose();
		}

		
			}  else if (fstype == 44) {
              if (cm.getmoneyb() < 3){
                    cm.sendOk("�����3��ֵ�Ҽӹ���");
                    cm.dispose();
					
		}	else    if (cm.getBossLog('PlayQuest40') >= 10) {
			cm.sendOk("�����ǿ����������10��!");
			cm.dispose();	
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr()+2); //����װ������
		 item.setDex(item.getDex() + 2);//����װ������
		 item.setInt(item.getInt() + 2);//����װ������
		 item.setLuk(item.getLuk() + 2);//����װ������
		 //item.setWatk(item.getWatk() + 10);//�ӹ���
		 //item.setMatk(item.getMatk() + 10);//��ħ��
		 //cm.gainItem(4001084,-1);
		 cm.setmoneyb(-3);
		 cm.gainjf(+3);
		 cm.setBossLog('PlayQuest40');
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
             // cm.serverNotice("������ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        ʹ��3����ֵ��Ϊװ��������ά��2��");
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.setmoneyb(-1);
		 cm.gainjf(+1);
		 cm.setBossLog('PlayQuest40');
		 cm.sendOk("ǿ��ʧ�ܣ��˻���1����ֵ��");	
		}
		 cm.dispose();
		}

		
		
	} else if (fstype == 5) {
		if (cm.getNX(1) <= 20000){
                    cm.sendOk("�����2���ȯ�ӹ���");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 3);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr()+5); //����װ������
		 item.setDex(item.getDex() + 5);//����װ������
		 item.setInt(item.getInt() + 5);//����װ������
		 item.setLuk(item.getLuk() + 5);//����װ������
		// cm.gainItem(4001085,-1);
		 cm.gainNX(-20000);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
            cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +" ʹ��2000WΪװ��������ά��5��"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		  cm.gainNX(-20000);
		 cm.sendOk("ǿ��ʧ�ܣ�����~~~~~");	
		}
		 cm.dispose();
		}

	} else if (fstype == 66) {//���Ѫϴ
		 if (cm.getPlayer().getNX() <=100){
                    cm.sendOk("�����100���ӹ���");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
              //   item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 //item.setStr(item.getStr()+5); //����װ������
		 //item.setDex(item.getDex() + 5);//����װ������
		 //item.setInt(item.getInt() + 5);//����װ������
		 //item.setLuk(item.getLuk() + 5);//����װ������
		 item.setHp(item.getHp()+50);//����װ��100Ѫ
		// cm.setBossLog("��������");//
		// cm.setBossLog("��߸���");//
		 //cm.setBossLog("���︱��");//
		// cm.gainItem(4001083,-1);
		 cm.gainNX(-100);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        ʹ��100���Ϊװ������50��HP"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		cm.gainNX(-100);
		 cm.sendOk("ǿ��ʧ�ܣ�����~~~~~");	
		}
		 cm.dispose();
		}

		} else if (fstype == 6) {
		 if (cm.getMeso() <= 20000000){
                    cm.sendOk("�����2000���Ҽӹ���");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 5);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
              //   item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr()+5); //����װ������
		 item.setDex(item.getDex() + 5);//����װ������
		 item.setInt(item.getInt() + 5);//����װ������
		 item.setLuk(item.getLuk() + 5);//����װ������
		// cm.gainItem(4001083,-1);
		 cm.gainMeso(-20000000);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        ʹ��2000W���Ϊװ��������ά��5��"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainMeso(-20000000);
		 cm.sendOk("ǿ��ʧ�ܣ�����~~~~~");	
		}
		 cm.dispose();
		}

		
	} else if (fstype == 7) {
                if (cm.getPlayer().getDY() <=200){
                    cm.sendOk("�����200����");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 //item.setStr(item.getStr()+7); //����װ������
		 //item.setDex(item.getDex() + 7);//����װ������
		 //item.setInt(item.getInt() + 7);//����װ������
		 //item.setLuk(item.getLuk() + 7);//����װ������
				item.setMp(item.getMp()+50);
		// cm.gainItem(4001430,-1);
		 cm.gainDY(-200);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
               cm.serverNotice("��ǿ��ϵͳ������ϲ"+ cm.getChar().getName() +"        ʹ��200�����ȯΪװ������50��MP"); 
			   //cm.����(5,"[����ϵͳ]�����" + cm.getPlayer().getName() + "���������!��ý��10�򡢾���8�򡢵��þ�200��");
			   cm.worldMessage(5,"��"+cm.getName()+"����boss�����ٻ��ٻ���Ů�ϰ壡");
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
	   cm.gainDY(-200);
	   cm.sendOk("ǿ��ʧ�ܣ�����~~~~");	
		}
		 cm.dispose();
		}

	} else if (fstype == 8) {
		if (!cm.haveItem(4021010,1))  {
                    cm.sendOk("�����#r 1 #k��#z4021010##v4021010#");
                    cm.dispose();
		}else if (cm.getPlayer().getNX() <=2000){
                    cm.sendOk("�����2000���");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 //var chance = Math.floor(Math.random() * 3);
                // if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setWatk(item.getWatk() + 10);
		 item.setMatk(item.getMatk() + 10);
		 cm.gainItem(4021010,-1);
		 cm.gainNX(-2000);
		 cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
                 cm.serverNotice("������֮��ǿ��װ��������ϲ"+ cm.getChar().getName() +"        ʹ��1��ʱ��֮ʯΪװ�����ӹ���10"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		//} else {
		 //cm.gainItem(4005003,-5);
		 //cm.sendOk("ǿ��ʧ�ܣ��˻���5������ĸ��");	
		//}
		 cm.dispose();
		}


	}	else if (fstype == 9) {
		if (!cm.haveItem(4170000,1)){
                    cm.sendOk("�����#r 1 #k��#z4170000##v4170000#");
                    cm.dispose();
		}else if (cm.getMeso() <= 0){
                    cm.sendOk("�������ٴ�1���");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("��������û�ˣ��޷�ǿ��!");
                    //cm.dispose();
			
		} else {

                 //var chance = Math.floor(Math.random() * 3);
                // if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
				item.setHp(item.getHp()+30);
				item.setMp(item.getMp()+30);
				item.setWdef(item.getWdef()+1);
				item.setMdef(item.getMdef()+1);
		//cm.gainItem(4005003,-1);
                 //item.setUpgradeSlots((item.getUpgradeSlots() - 1));
				cm.gainItem(4170000,-1);

				cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k");
		 
                // cm.serverNotice("��ǿ��װ��������ϲ"+ cm.getChar().getName() +"�ɹ�Ϊװ������30HP��30MP��1�����1ħ��"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		//} else {
		 //cm.gainItem(4005003,-1);
		 //cm.sendOk("ǿ��ʧ�ܣ��˻���9������ĸ��");	
		//}
		 cm.dispose();
		}

	} else if (fstype == 10) {
		if (!cm.haveItem(4170009,1)){
            cm.sendOk("�����#r 1 #k��#z4170009##v4170009#");
            cm.dispose();
		} else {
            cm.gainItem(4170009,-1);
			cm.gainItem(2370000,1);
			cm.sendOk("#r#e�һ��ɹ�,ף����Ϸ���!#k");
            cm.dispose();
		} 

	}
									
}
}
}
