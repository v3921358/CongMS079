var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //����
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE);//��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE);//��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var С�ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //������
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //������
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //������
var а��С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var ���� = "#fEffect/CharacterEff/1112960/3/1#";  //а��С�� ����
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ����Ҷ ="#fMap/MapHelper/weather/maple/3#";
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";

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
                
   cm.sendOk("��лʹ��.");
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
	for(i = 0; i < 10; i++){
		text += "";
	}				
	text += "#e#d #r"+ ����� +"����תְ"+ ����� +"#b ��Ҫ������Ʒ��\r\n#v4000017##k#z4000017# * #r50#k��\t\t#v4002000##k#z4002000# * #r5#k��\r\n#v4000010##k#z4000010# * #r300#k��\t#v4000463##k#z4000463# * #r10#k��\r\n#v4001126##k#z4001126# * #r500#k��\r\n\r\n"
	text += "#e#b\t���ռ���������Ʒ,ͬ��ʹ�ÿ���תְ���ܣ�\r\n��ܰ��ʾ���򿪿���תְNPC���۳�������ߣ�\r\n��û��ȷ�����Ƿ�תְ���������½ǡ������һ�����\r\n";//����
	text += "#L1##r#v4000017##e�򿪿���תְ���� ���޷�������Ŷ��#v4000017##l";//����
	text += "     \r\n"
        cm.sendSimple(text);
		
        } else if (selection == 1) {
                      if(!cm.canHold(1012412,1)){
			cm.sendOk("��������ı��������ٿճ�2��λ�ã�");
            cm.dispose();
			
        } else if(cm.haveItem(4000017,50) && cm.haveItem(4002000,5) && cm.haveItem(4000010,300) && cm.haveItem(4000463,10) && cm.haveItem(4001126,500) ){
				cm.gainItem(4000017, -50);//��ͷ
				cm.gainItem(4002000, -5);//����ţ��Ʊ
				//cm.gainItem(4310022, -20);//ӣ�������
				cm.gainItem(4000010, -300);//��ˮ����
				cm.gainItem(4000463, -10);//�����
				cm.gainItem(4001126, -500);//��Ҷ
				cm.dispose();
                cm.openNpc(9900004, 553);
				
		}else{
            cm.sendOk("#e#r���ź������޷���������תְ����\r\n\r\n#b��ʾ1������ #z4000017# #v4000017# ����50��\r\n��ʾ2������ #z4002000# #v4002000# ����5��\r\n��ʾ3������ #z4000010# #v4000010# ����300��\r\n��ʾ4������ #z4000463# #v4000463# ����10��\r\n��ʾ5������ #z4001126# #v4001126# ����500��");
            cm.dispose();
			}
		}
    }
}




