
/* 
 * �ű�����: cm
 * �ű���;: ����н�
 * �ű�����: ����ؼ
 * ����ʱ��: 2014/12/18
 */

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

				
            var gsjb = "";
            gsjb = " ��ӭ����#r " + cm.getChannelServer().getServerName() + " #k�����ǵ���н�һ�ϵͳ.\r\n#g================================================\r\n#r1����� = 1��ֵ��#k\t\t��ǰ��ֵ��:#r"+cm.getrmb()+" Ԫ\r\n";
			gsjb += "#L5##b[#r��ֵ��#k]�һ�[#r���#k]  #b���� - (#r1 = 3000��#b)#l\r\n\r\n";
			gsjb += "#L13##b[#r���#k]�һ�#v4000463#[#r����#k]  #b���� - (#r1000 = 1#b)#k#l\r\n\r\n";
			
            gsjb += "#L11##b#v4000463#[#r����#k]�һ�[#r���#k]  #b���� - (#r1 = 2200W#b)#l\r\n";
            gsjb += "#L12##b#v4000463#[#r����#k]�һ�[#r���#k]  #b���� - (#r1 = 950#b)#k#l\r\n";
			gsjb += "#L1##b#v4000463##r���� x96�� #k�һ� #b#v3010788##z3010788# #r 1�� #l\r\n"//3
			gsjb += "#L2##b#v4000463##r���� x96�� #k�һ� #b#v3015304##z3015304# #r 1�� #l\r\n"//3
			gsjb += "#L3##b#v4310049##r���� x20�� #k�һ� #b#v4310108##z4310108# #r 1�� #l\r\n"//3
            gsjb += "#L14##b#v4001126#[#r����#k]�һ�[#r���þ�#k]  #b���� - (#r100 = 1���þ�#b)#l\r\n";
            gsjb += "#L15##b#v4000313#[#r����#k]�һ�[#r���#k]  #b���� - (#r10 = 1���#b)#k#l\r\n";

						
            cm.sendSimple(gsjb);
        } else if (status == 1) {
            if (cm.getPlayer() >= 1 && cm.getPlayer() <= 5) {
                cm.sendOk("GM���ܲ���һ���");
                cm.dispose();
            }
			
            if (selection == 0) {
                if (cm.getPlayer().getCSPoints(0) / 500 == 0) {
                    cm.sendNext("�����ʻ�������޷��һ��������ҡ�");
                    status = -1;
                } else {
                    beauty = 1;
                    cm.sendGetNumber("������#r���#k�һ�#b#z4000463##k������:\r\n#b���� - (#r500 = 1#b)\r\n����˻���Ϣ - \r\n    �������: #r" +
                            cm.getPlayer().getCSPoints(0) + " \r\n", 1, 1, cm.getPlayer().getCSPoints(0) / 500);

                }

            
            } else if (selection == 1) {
               if (cm.haveItem(4000463) == 64) {
                    cm.sendNext("��Ļ��Ҳ��㡣");
                    status = -1;
                } else {
                    beauty = 1;
                    cm.sendGetNumber("������Ҫ�һ�#v3010788##z3010788# ��������\r\n��ǰ����: #r#c4000463##k \r\n\r\n\r\n", 1, 1, 10000);

                }
            } else if (selection == 2) {
               if (cm.haveItem(4000463) == 96) {
                    cm.sendNext("��Ļ��Ҳ��㡣");
                    status = -1;
                } else {
                    beauty = 2;
                    cm.sendGetNumber("������Ҫ�һ�#v3015304##z3015304# ��������\r\n��ǰ����: #r#c4000463##k \r\n\r\n\r\n", 1, 1, 10000);

                }
				
			 } else if (selection == 3) {
               if (cm.haveItem(4310049) == 20) {
                    cm.sendNext("��Ļ��Ҳ��㡣");
                    status = -1;
                } else {
                    beauty = 3;
                    cm.sendGetNumber("������Ҫ�һ�#v4310108##z4310108# ��������\r\n��ǰ����: #r#c4310049##k \r\n\r\n\r\n", 1, 1, 10000);

                }

			}else if (selection == 5) {//���һ����
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if(cm.getmoneyb() < 1){
                    cm.sendNext("���ĳ�ֵ�Ҳ���һ�.�������������һ����");
                    status = -1;
                } else {
                    beauty = 5
                    cm.sendGetNumber("������[#r��ֵ��#k]������:\r\n#b����Ϊ:(#r 1 ��ֵ�� = 3000 ���#b)\r\n��ǰ��ֵ��:#r"+cm.getmoneyb()+" Ԫ \r\n", 1, 1, 100000 );

                }
				
				
				
				

            } else if (selection == 8) {
               if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendNext("��ĵ�ȯ���㡣");
                    status = -1;
                } else {
                    beauty = 8;
                    cm.sendGetNumber("������Ҫ�һ�#v5030001# ��������\r\n��ǰ��ȯ: #r"+cm.getPlayer().getCSPoints(1)+"#k \r\n\r\n\r\n", 1, 1, 1);

                }
				
			} else if (selection == 9) {
               if (cm.haveItem(4001028) == 0) {
                    cm.sendNext("��Ļ��Ҳ��㡣");
                    status = -1;
                } else {
                    beauty = 9;
                    cm.sendGetNumber("������Ҫ�һ�#v4250602# ��������\r\n��ǰ����: #r#v4001028##c4001028##k \r\n\r\n\r\n", 1, 1, 100);

                }
				
				

				
				





   
            }else if (selection == 11) {
                if (cm.haveItem(4000463) == 0) {
                    cm.sendNext("���Ļ��Ҳ���һ�.");
                    status = -1;
                } else {
                    beauty = 11

   cm.sendGetNumber("������һ��Ļ�������:\r\n#b���� - (#r1 #z4000463# = 2200 ����#b)\r\n  ��ǰ����: #r #c4000463# �� \r\n", 1, 1, 100000 );
                }



            }else if (selection == 12) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.haveItem(4000463) == 0) {
                    cm.sendNext("���Ļ��Ҳ���һ�.");
                    status = -1;
                } else {
                    beauty = 12
                    cm.sendGetNumber("������[#r����#k]������:\r\n#b����Ϊ:(#r 1 #z4000463# = 950 ���#b)\r\n��ǰ����: #r #c4000463# �� \r\n", 1, 1, 100000 );

                }
				
            }else if (selection == 13) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.getPlayer().getCSPoints(1)  == 0) {
                    cm.sendNext("���ĵ�ȯ����һ�.");
                    status = -1;
                } else {
                    beauty = 13
                   //   cm.sendGetNumber("������һ�#r#z4000463##k������:\r\n#b���� - (#r1500 = 1#b)\r\n��ĵ������: #r" +
                       //     cm.getPlayer().getCSPoints(0) + " \r\n", 1, 1, cm.getPlayer().getCSPoints(0) );
 cm.sendGetNumber("������һ�#r#z4000463##k������:\r\n#b���� - (#r1000 = 1#b)\r\n��ĵ������: #r" +cm.getPlayer().getCSPoints(1) + " \r\n", 1, 1, 100000 );

					   
					   
                }


            }else if (selection == 14) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.haveItem(4001126) == 0) {
                    cm.sendNext("���ķ�Ҷ����һ�.");
                    status = -1;
                } else {
                    beauty = 14
                    cm.sendGetNumber("������[#r��Ҷ#k]������:\r\n#b����Ϊ:(#r 100�� #z4001126# = 1 ���þ�#b)\r\n��ǰ����: #r #c4001126# �� \r\n", 1, 1, 100000 );

                }

				            }else if (selection == 15) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.haveItem(4000313) == 0) {
                    cm.sendNext("���Ļƽ��Ҷ����һ�.");
                    status = -1;
                } else {
                    beauty = 15
                    cm.sendGetNumber("������[#r�ƽ��Ҷ#k]������:\r\n#b����Ϊ:(#r 10�� #z4000313# = 1 ���#b)\r\n��ǰ����: #r #c4000313# �� \r\n", 1, 1, 100000 );

                }




            }else if (selection == 22) {
                var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.getPlayer().getCSPoints(0)  == 0) {
                    cm.sendNext("���ĵ�ȯ����һ�.");
                    status = -1;
                } else {
                    beauty = 22
                      cm.sendGetNumber("������һ�#r#z2040710##k������:\r\n#b���� - (#r10000 = 1#b)\r\n��ĵ������: #r" +
                            cm.getPlayer().getCSPoints(0) + " \r\n", 1, 1, cm.getPlayer().getCSPoints(0) );

                }

            }
               


//=================================================================================			
			
			
			
			
        } else if (status == 2) {
           if (beauty == 1) {
               if (cm.haveItem(4000463, selection*64)){
					//cm.setmoneyb(-selection*5);  //�Ӽ���ֵ��
					 cm.gainItem(4000463, -selection*64);
					 cm.gainItem(3010788, selection);
                     cm.sendNext("�ɹ��һ���:[#r"+selection+"#k]��#v3010788##z3010788#");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }
            } else if (beauty == 2) {
                if (cm.haveItem(4000463, selection*96)){
					 cm.gainItem(4000463, -selection*96);
					 cm.gainItem(3015304, selection);
                     cm.sendNext("�ɹ��һ���:[#r"+selection+"#k]��#v3015304##z3015304#");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }


            } else if (beauty == 3) {
                if (cm.haveItem(4310049, selection*20)){
					 cm.gainItem(4310049, -selection*20);
					 cm.gainItem(4310108, selection);
                     cm.sendNext("�ɹ��һ���:[#r"+selection+"#k]��#v4310108##z4310108#");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }

            } else if (beauty == 4) {
                if (cm.haveItem(4000463, selection*20)){
					 cm.gainItem(4000463, -selection*20);
					 cm.gainItem(5570000, selection);
                     cm.sendNext("�ɹ��һ���:[#r"+selection+"#k]��#v5570000##z5570000#");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }

            }else if (beauty == 5) {
                 if (cm.getmoneyb() >= 1){
					 cm.setmoneyb(-selection);
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose();
                }
				var czb=cm.getmoneyb();
				 if (czb < 0){
					 cm.setmoneyb(+selection);
                    cm.sendNext("���ĳ�ֵ�Ҳ��㣬�޷��һ����������������һ�");
                    cm.dispose();
                } else {
                    cm.gainNX(+3000 * selection);
					cm.gaincz(selection);
                    //cm.gainItem(4000463, +selection);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] ��ֵ�Ҷһ���:[#r"+(selection*3000)+"#k]���");
                    cm.dispose();
                }

            }  else if (beauty == 6) {
                if (cm.haveItem(4000463, selection*10)){
					 cm.gainItem(4000463, -selection*10);
					 cm.gainItem(5211047, selection,3);
                     cm.sendNext("�ɹ��һ���:[#r"+selection+"#k]��#v5211047#��*#r���߻��߻�����Ч��#k");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }
				


            } else if (beauty == 7) {
                if (cm.haveItem(4000463, selection*30)){
					 cm.gainItem(4000463, -selection*30);
					 cm.gainItem(5030001, selection);
                     cm.sendNext("�ɹ��һ���:[#r"+selection+"#k]��#v5030001#");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }

            } else if (beauty == 8) {
                if (cm.getPlayer().getCSPoints(1) > 199){
					cm.gainNX(-200);	//�Ӽ���ȯ
					 cm.gainItem(5030001, 1,1);
                     cm.sendNext("����ɹ���");
					 cm.dispose();
                } else {
                    cm.sendNext("���������޷��һ���");
                    cm.dispose()
                }

			



				
            }else if (beauty == 10) {
                if (cm.haveItem(4000463, selection)){
					 cm.gainItem(4000463, -selection);
					 cm.setmoneyb(+selection);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] ���Ҷһ���:[#r"+(selection)+"#k] ��ֵ��");
                    //cm.gainNX(+1000 * selection);
					 cm.dispose();
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose()
                }
				
				
				
            }else if (beauty == 111) {
                 if (cm.getmoneyb() >= 1){
					 cm.setmoneyb(-selection);
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose();
                }
				var czb=cm.getmoneyb();
				 if (czb < 0){
					 cm.setmoneyb(+selection);
                    cm.sendNext("���Ļ��Ҳ��㣬�޷��һ���");
                    cm.dispose();
                } else {
                    //cm.gainNX(+1000 * selection);
                    cm.gainItem(4000463, +selection);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] ��ֵ�Ҷһ���:[#r"+(selection*1)+"#k]�� ����");
                    cm.dispose();
                }				
				
				
				
            }else if (beauty == 11) {//����Ҷһ����
                 if (cm.haveItem(4000463, selection)){
					 cm.gainItem(4000463, -selection);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] ���Ҷһ���:[#r"+(selection*22000000)+"#k]���");
                     cm.gainMeso(+22000000* selection);
					 cm.dispose();
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose()
                }

            }else if (beauty == 12) {//����Ҷһ����
                 if (cm.haveItem(4000463, selection)){
					 cm.gainItem(4000463, -selection);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] ���Ҷһ���:[#r"+(selection*950)+"#k]�� ���");
                    cm.gainNX(+950 * selection);
					 cm.dispose();
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose()
                }

				

            }else if (beauty == 13) {//���һ������
                 if (cm.getPlayer().getCSPoints(1) >= selection * 1000){
					
                    cm.gainNX(-selection * 1000);
                    cm.gainItem(4000463, selection);
                    cm.sendOk("���ɹ�����#v4000463# x #r" + selection + " #k  ��")
					 cm.dispose();
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose()
                }
				
            }else if (beauty == 14) {//��Ҷ�һ����þ�
                 if (cm.haveItem(4001126, selection* 100)){
					 cm.gainItem(4001126, -selection* 100);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] ����Ҷ�һ���:[#r"+(selection*1)+"#k] ���þ�");
                    cm.gainDY(+1* selection);
					 cm.dispose();
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose()
                }


            }else if (beauty == 15) {//�ƽ��Ҷ�һ����
                 if (cm.haveItem(4000313, selection* 10)){
					 cm.gainItem(4000313, -selection* 10);
                    cm.sendNext("�һ��ɹ��� [#r"+selection+"#k] �ƽ��Ҷ�һ���:[#r"+(selection*1)+"#k] ���");
                    cm.gainNX(+1 * selection);
					 cm.dispose();
                } else {
                    cm.sendNext("������������������޷��һ���");
                    cm.dispose()
                }


            }
            status = -1;
        } else {
            cm.dispose();
        }
    }
}
