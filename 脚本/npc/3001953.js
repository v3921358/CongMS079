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
var ��ɫ��� = "#d#fUI/Basic/BtHide3/mouseOver/0#";
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
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
			text += "          #k"+�ʺ�+" #r#e#w �� �� �� �� #n#k "+�ʺ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n\r\n";
            text += "  #d#e����ӵ��\r\n  #r"+���ͼ��+": " + cm.getMeso() + "#k\r\n";
			text += "  #L66#"+��ɫ���+"#b����10�ڽ��,���#i4310012:##l\r\n"
			text += "  #L666#"+��ɫ���+"#b�ûƽ�ѩ����ȡ���(ÿ����ȡ0.02������)\r\n"
			
            cm.sendSimple(text);
		} 
            
			else if (selection == 666) {
			
			if (cm.getMeso() <= 1000000000 ) {
			
			if (cm.haveItem(4310012, 1) ) { 

			    cm.gainMeso(980000000);
				cm.gainItem(4310012,-1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��������С�" + " : " + "[" + cm.getChar().getName() + "]�ûƽ�ѩ����ȡ��10�ڽ�ң�")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��,������û�лƽ�ѩ��,��������ȡ���Ŷ��");
				cm.dispose();
			}
			}else{
				cm.sendOk("\t��,������ӵ�еĽ�Ҳ����Զ���10�ڲſ�����ȡŶ��");
				cm.dispose();
			}
				
				}
			
		    else if (selection == 66) {
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		    }else{
			if (cm.getMeso() >= 1000000000 ) { 
			    
			    cm.gainMeso(-1000000000);
				cm.gainItem(4310012,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��������С�" + " : " + "[" + cm.getChar().getName() + "]������10�ڽ�ң������һ���ƽ�ѩ����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��,������ӵ�еĽ�Ҵﲻ������Ҫ��Ŷ��");
				cm.dispose();
			}
			}	
				}
		
		
		

		  else if (status == 2) {
            if (beauty == 1) {
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
            }if (beauty == 4) {
				var zliang = cm.getPlayer().getItemQuantity(4001128, false);
                if (zliang > 0){
					cm.removeAll(4001128);  
					cm.getPlayer().modifyCSPoints(2,zliang*2, true);		
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[�һ�����]����� "+cm.getName()+" Ŭ����ש,�����������ըҩͰ�һ��ˣ�"+(zliang*2)+" ���þ�");//����
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }
            }
			if (beauty == 70) {
				var zliang = cm.getPlayer().getItemQuantity(4032226, false);
                if (zliang > 0){
					cm.removeAll(4032226);  
					cm.getPlayer().modifyCSPoints(1,zliang*1000, true);	//�����*������1Ϊ���2Ϊ���þ�	
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[�һ�����]����� "+cm.getName()+" Ŭ����ש,����������ûƽ�����һ��ˣ�"+(zliang*1000)+" ���");//����
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }
            }
        }
    }
}