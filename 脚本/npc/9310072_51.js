var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��   ΨһQQ:1848350048  ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";

var ���Ϻϳ� = new Array(
{ �������: 3600001,��������: 20,������: 100000,�ϳɲ���: 4310034,�����������: "�ܻ����Ի���ܻ���"},
{ �������: 3600001,��������: 30,������: 100000,�ϳɲ���: 4310029,�����������: "�ܻ����Ի���ܻ���"},
{ �������: 4170005,��������: 10,������: 1000000,�ϳɲ���: 4310034,�����������: "��߸������Ի����ߵ�"},
{ �������: 4170006,��������: 10,������: 1000000,�ϳɲ���: 4310029,�����������: "��ո������Ի����յ�"},
{ �������: 4310150,��������: 15,������: 1000000,�ϳɲ���: 4310148,�����������: "ÿ�մ�Ұ������Ի�ü����"},
{ �������: 4032391,��������: 100,������: 1000000,�ϳɲ���: 2340000,�����������: "����,���︱�����Ի��"},
{ �������: 4032392,��������: 100,������: 1000000,�ϳɲ���: 2049118,�����������: "����,���︱�����Ի��"},
{ �������: 4170002,��������: 30,������: 1000000,�ϳɲ���: 1902001,�����������: "�����������Ի�ã�\r\n#rע����ɫҰ��һ��ɫ����һֻ���ڶ�ֻ�������#k"}
);
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
            
            var text = "";
			text += "                  #k"+�ʹڰ�+" #r#e#w �� �� �� �� #n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
            
			for (var i = 0; i < ���Ϻϳ�.length; i++) {
			text += "#L" + i + "#[#v"+���Ϻϳ�[i].�������+"##r#c"+���Ϻϳ�[i].�������+"##k/"+���Ϻϳ�[i].��������+" + "+���Ϻϳ�[i].������+"���]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v"+���Ϻϳ�[i].�ϳɲ���+"##l\r\n\r\n"+���Ϻϳ�[i].�����������+"\r\n--------------------------------------------------\r\n";
			}
			cm.sendSimple(text);
}
	else if (selection == 0) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[0].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[0].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[0].�������,���Ϻϳ�[0].��������) ){
				cm.gainMeso(-���Ϻϳ�[0].������);
				cm.gainItem(���Ϻϳ�[0].�������,-���Ϻϳ�[0].��������);
				cm.gainItem(���Ϻϳ�[0].�ϳɲ���,1);//��֮��
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ�����������#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[0].�������+"#��������"+���Ϻϳ�[0].��������+"��");
				cm.dispose();
			}
			}
		}
else if (selection == 1) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[1].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[1].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[1].�������,���Ϻϳ�[1].��������) ){
				cm.gainMeso(-���Ϻϳ�[1].������);
				cm.gainItem(���Ϻϳ�[1].�������,-���Ϻϳ�[1].��������);
				cm.gainItem(���Ϻϳ�[1].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ������ʮ�ֱ�#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[1].�������+"#��������"+���Ϻϳ�[1].��������+"��");
				cm.dispose();
			}
			}
		}		
else if (selection == 2) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[2].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[2].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[2].�������,���Ϻϳ�[2].��������) ){
				cm.gainMeso(-���Ϻϳ�[2].������);
				cm.gainItem(���Ϻϳ�[2].�������,-���Ϻϳ�[2].��������);
				cm.gainItem(���Ϻϳ�[2].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ�����������#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[2].�������+"#��������"+���Ϻϳ�[2].��������+"��");
				cm.dispose();
			}
			}
		}			
else if (selection == 3) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[3].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[3].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[3].�������,���Ϻϳ�[3].��������) ){
				cm.gainMeso(-���Ϻϳ�[3].������);
				cm.gainItem(���Ϻϳ�[3].�������,-���Ϻϳ�[3].��������);
				cm.gainItem(���Ϻϳ�[3].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ������ʮ�ֱ�#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[3].�������+"#��������"+���Ϻϳ�[3].��������+"��");
				cm.dispose();
			}
			}
		}
else if (selection == 4) {
			if (!cm.checkNumSpace(4, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[4].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[4].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[4].�������,���Ϻϳ�[4].��������) ){
				cm.gainMeso(-���Ϻϳ�[4].������);
				cm.gainItem(���Ϻϳ�[4].�������,-���Ϻϳ�[4].��������);
				cm.gainItem(���Ϻϳ�[4].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ��������֮��½Ǯ��#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[4].�������+"#��������"+���Ϻϳ�[4].��������+"��");
				cm.dispose();
			}
			}
		}
else if (selection == 5) {
			if (!cm.checkNumSpace(2, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[5].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[5].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[5].�������,���Ϻϳ�[5].��������) ){
				cm.gainMeso(-���Ϻϳ�[5].������);
				cm.gainItem(���Ϻϳ�[5].�������,-���Ϻϳ�[5].��������);
				cm.gainItem(���Ϻϳ�[5].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ������ף������#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[5].�������+"#��������"+���Ϻϳ�[5].��������+"��");
				cm.dispose();
			}
			}
		}
else if (selection == 6) {
			if (!cm.checkNumSpace(2, 2)) {
			cm.sendOk("�����������ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[6].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[6].������+"���������������");
            cm.dispose();
			return;}
            else{
			
			if(cm.haveItem(���Ϻϳ�[6].�������,���Ϻϳ�[6].��������) ){
				cm.gainMeso(-���Ϻϳ�[6].������);
				cm.gainItem(���Ϻϳ�[6].�������,-���Ϻϳ�[6].��������);
				cm.gainItem(���Ϻϳ�[6].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ�����˻������#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[6].�������+"#��������"+���Ϻϳ�[6].��������+"��");
				cm.dispose();
			}
			}
		}
else if (selection == 7) {
			if (!cm.checkNumSpace(1, 2)) {
			cm.sendOk("����װ�����ռ䲻��2��");
			cm.dispose();
			return;
		}
			if(cm.getMeso() < ���Ϻϳ�[7].������) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���"+���Ϻϳ�[7].������+"���������������");
            cm.dispose();
			return;
			}
			if(cm.haveItem(���Ϻϳ�[7].�ϳɲ���,1) ){
			cm.sendOk("��Ǹ���Ѿ�������ɫҰ��");
            cm.dispose();
			return;
			}
			else{
			
			if(cm.haveItem(���Ϻϳ�[7].�������,���Ϻϳ�[7].��������) ){
				cm.gainMeso(-���Ϻϳ�[7].������);
				cm.gainItem(���Ϻϳ�[7].�������,-���Ϻϳ�[7].��������);
				cm.gainItem(���Ϻϳ�[7].�ϳɲ���,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����϶һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ����ϣ��������ɫҰ��#��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t#v"+���Ϻϳ�[7].�������+"#��������"+���Ϻϳ�[7].��������+"��");
				cm.dispose();
			}
			}
		}



    }
}