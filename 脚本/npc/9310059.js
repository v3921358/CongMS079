var FY0 = "��������������������������";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var FY6 = "�ǩ�����������������������";
var FY7 = "�� ΨһQQ:1848350048    ��";
var FY8 = "��������������������������";

var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";
var ���� ="#fMap/MapHelper/weather/witch/3#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
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
			text += ""
			text += "\t\t\t     "+ ���� +"#e#d �� �� �� �� #k#n"+ ���� + "#l\r\n\r\n"; 
            text += "#e��ඣ����������������װ��Ŷ���ﵽ120���Ժ󻹿���ȥ�������ͼ��#n\r\n\r\n"
            text += "#e  #rȫ���,#dֻҪ�ϻ�ʱ��,���������ţ�Ƶ�����/����#n\r\n"
			
			text += "#L11##e#b- - - <<<������Ʒװ��>>> - - -#l\r\n"
			text += "#L111##e#b- - - <<<������Ʒװ��>>> - - -#l\r\n"
			text += "#L4##e#b- - - <<<���������ͼ>>> - - -#l\r\n"

            cm.sendSimple(text);
		}
              else  if (selection == 1) {
				cm.dispose();
                cm.openNpc(3003382, "4����װ��");
			}
			  else if (selection == 11) {
				cm.dispose();
                cm.openNpc(3003382, "79�Ͻ�װ��");
			}
			  else if (selection == 111) {
				cm.dispose();
                cm.openNpc(3003382, "100����װ��");
			}
              else if (selection == 2) {
				cm.warpParty(970000003);
				cm.dispose();
			} else if (selection == 22) {
				cm.warpParty(970000004);
				cm.dispose();
			} else if (selection == 4) {	
                                      
				cm.warp(901111111);
				cm.dispose();
			}
		 
		
		  else if (status == 2) {
            if (beauty == 100) {
				var zliang = cm.getPlayer().getItemQuantity(4001126, false);
                if (zliang > 0){
					cm.removeAll(4001126);
					cm.gainMeso(8000*zliang);					
							
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[���һ�]����� "+cm.getName()+" Ŭ����ש,���������һ��ˣ�"+(zliang*8000)+" ��ҡ�");
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }            		
            }
			
        }
    }
}