/* ==================
 �ű�����: ����
 ��Ȩ���𻪷�Ҷ�Ŷ�     
 ��ϵ�ۿۣ�1848350048
 =====================
 */
var status = 0;
var ��ˮ�� = 4021008;
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
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
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �Ҹ� = "#k��ܰ��ʾ���κηǷ��������ҷ�Ŵ���.��ɱ��������.";
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
			
            var txt1 = "             #L1##v4001230#[#r#c4001230##k#d/10]�һ�#i4310150##k\r\n\r\n";
			var txt2 = "#L2##v4001228#[#r#c4001228##k#d/8]�һ�#i4310150##k    #L3##v4001227#[#r#c4001227##k#d/6]�һ�#i4310150##k\r\n\r\n";
            var txt3 = "#L4##v4001226#[#r#c4001226##k#d/5]�һ�#i4310150##k    #L5##v4001229#[#r#c4001229##k#d/3]�һ�#i4310150##k\r\n\r\n";
			var txt4 = "#L6#" + ��ɫ��ͷ + "Ź�����ţ��                #L7#" + ��ɫ��ͷ + "Ź���������#k\r\n\r\n";
			var txt5 = "#L8#" + ��ɫ��ͷ + "Ź��޾�з��                #L9#" + ��ɫ��ͷ + "Ź��ϵ�����#k\r\n\r\n";
			var txt6 = "#L10#" + ��ɫ��ͷ + "Ź��������                #L11#" + ��ɫ��ͷ + "Ź��������ʦ#k\r\n\r\n";
			var txt7 = "#L12#" + ��ɫ��ͷ + "Ź���β����                #L13#" + ��ɫ��ͷ + "Ź��С������#k\r\n\r\n";
			var txt8 = "#L14#" + ��ɫ��ͷ + "Ź��ʿ����                #L15#" + ��ɫ��ͷ + "Ź��С��Ī��#k\r\n\r\n";
			var txt9 = "#L16#" + ��ɫ��ͷ + "Ź�������                #L17#" + ��ɫ��ͷ + "Ź��������#k\r\n\r\n";
			var txt10 = "#L18#" + ��ɫ��ͷ + "Ź��������                #L19#" + ��ɫ��ͷ + "Ź��Ъ������#k\r\n\r\n";
			var txt11 = "#L20#" + ��ɫ��ͷ + "Ź���������                #L21#" + ��ɫ��ͷ + "Ź����ӥ����#k\r\n\r\n";
			var txt12 = "#L22#" + ��ɫ��ͷ + "Ź��޾�з��                #L23#" + ��ɫ��ͷ + "Ź��ϵ�����#k\r\n\r\n";
            cm.sendSimple("\t\t"+�ʺ�+"  #e#d ÿ �� �� Ұ #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n" + txt1 + "" + txt2 + "" + txt3 + "" + txt4 + "" + txt5 + "" + txt6 + "" + txt7 + "" + txt8 + "" + txt9 + "" + txt10 + "" + txt11 + "");

        } else if (status == 1) {
            if (selection == 1) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001230)>=10){
			cm.gainItem(4001230,-10);
			cm.gainItem(4310150,1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��10��#v4001230#�޷��һ���");
			cm.dispose();
			}
            }
			
			else if (selection == 21) { 
			if(cm.getMeso() >= 30000){
			cm.gainMeso(-30000);
			cm.warp(240020402);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���3����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 20) { 
			if(cm.getMeso() >= 30000){
			cm.gainMeso(-30000);
			cm.warp(240020402);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���3����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 19) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(230020100);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���2����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 18) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(261030000);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���2����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 17) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(260010201);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���2����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 16) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(240040401);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���2����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			
			else if (selection == 15) { 
			if(cm.getMeso() >= 20000){
			cm.gainMeso(-20000);
			cm.warp(220050100);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���2����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 14) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(100040106);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 13) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(107000300);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 12) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(222010310);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 11) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(250010503);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 10) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(200010300);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 9) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(250010304);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 8) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(110040000);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 7) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(101030404);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			else if (selection == 6) { 
			if(cm.getMeso() >= 10000){
			cm.gainMeso(-10000);
			cm.warp(104000400);
            cm.dispose();
			}else{
			cm.sendOk("��Ǹ������Ҳ���1����ͽ����ȥ�ɣ�");
			cm.dispose();
			}
            }
			
			
			else if (selection == 5) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001229)>=3){
			cm.gainItem(4001229,-3);
			cm.gainItem(4310150,1);
            cm.sendOk("�һ��ɹ���");
            cm.dispose();
			}else{
			cm.sendOk("��û��3��#v4001229#�޷��һ���");
			cm.dispose();
			}
            }
			
			else if (selection == 4) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001226)>=5){
			cm.gainItem(4001226,-5);
			cm.gainItem(4310150,1);
            cm.sendOk("�һ��ɹ���");
            cm.dispose();
			}else{
			cm.sendOk("��û��5��#v4001226#�޷��һ���");
			cm.dispose();
			}
            }
			
			else if (selection == 3) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001227)>=6){
			cm.gainItem(4001227,-6);
			cm.gainItem(4310150,1);
            cm.sendOk("�һ��ɹ���");
            cm.dispose();
			}else{
			cm.sendOk("��û��6��#v4001227#�޷��һ���");
			cm.dispose();
			}
            }

			else if (selection == 2) {
			if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		    }
			if(cm.itemQuantity(4001228)>=8){
			cm.gainItem(4001228,-8);
			cm.gainItem(4310150,1);
            cm.sendOk("�һ��ɹ���");
            cm.dispose();
			}else{
			cm.sendOk("��û��8��#v4001228#�޷��һ���");
			cm.dispose();
			}
            }


        }
    }
}
