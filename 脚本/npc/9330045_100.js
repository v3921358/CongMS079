/* ==================
 �ű�����: ���߽���	    
 �ű����ߣ���Ҷ   
 ��ϵ��ʽ��1848350048
 =====================
 */
var status = -1;
var sel;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
	
	
	
            text += "\t\t"+�ʺ�+"  #e#d �� �� �� �� Ʒ #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n#b��Ҫ��ʾ���������ǰ���������������֮��û�л������Ų�����#k\r\n"//3
            text += "#L2##r#v3011000# 300����#l          #L3##r#v5340001# 88888���#l\r\n"
            text += "#L6##v2300000# 200����100��#l      #L12##v2300000# 1000����500��#l\r\n"
            text += "#L4##v2300001# 1����100��#l       #L13##v2300001# 4����500��#k#l\r\n\r\n"
			text += "#L14#      ����������  ������㳡#k  ����������  #l\r\n\r\n"

		   
		   
		   
		   
		   
		if (cm.getPlayer().getClient().getChannel() != 1) {
			cm.sendOk("ֻ����1�ߵ���Ŷ��");
			cm.dispose();
			return;
		}
	
		   if (cm.getPlayer().getMapId()==741000200){
            cm.sendSimple(text);
		   }else{
			
		if (cm.getLevel() > 29){
			
            cm.sendSimple(text);
		}else{
			cm.sendOk("30�����ϲſ��Բμ�Ŷ��");
			cm.dispose();
		}
		   }
    
       } else if (status == 1) {
	if (sel == 0) {
		
	} else if (selection == 1) {
			cm.warp(910000000, 0);
		    cm.dispose();
			
		} else if (selection == 14) {
			cm.warp(741000200, 0);
		    cm.dispose();
	
	} else if (selection == 10) {
     if (cm.getBossLog('�������') == 1) {
		    cm.sendOk("ֻ����ȡһ�Σ�");
		    cm.dispose();
		 } else {
		if (cm.haveItem(3011000)) {
		cm.sendOk("���Ѿ���һ�ѵ����Ρ�ÿ����ɫֻ����1�������Ρ�");
	    } else {
		    cm.gainItem(3011000, 1, 1);
		    cm.gainItem(5340001, 1, 1);
		    cm.gainItem(2300001, 100, 1);
			cm.setBossLog('�������');
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		}
		}
	} else if (selection == 11) {
		    cm.sendOk("�Ե�");
		    cm.dispose();
	} else if (selection == 2) {
	    if (cm.haveItem(3011000)) {
		cm.sendOk("���Ѿ���һ�ѵ����Ρ�ÿ����ɫֻ����1�������Ρ�");
	    } else {
		  if (cm.getLevel() > 49){
				if (cm.canHold(3011000) && cm.getMeso() >= 3000000) {
		    cm.gainMeso(-3000000);
		    cm.gainItem(3011000, 1);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
				} else {
		    cm.sendOk("�����Ƿ�������Ľ�һ��㹻�ı����ռ䡣");
		    cm.dispose();
				}
		  }else{
		    cm.sendOk("����Ҫ50���ſ��������㣡");
		    cm.dispose();
		  }	
	    }
	}else if (selection == 3) {
	    if (cm.haveItem(5340001)) {
		cm.sendOk("���Ѿ���һ�Ѹ߼�����ˡ�");
		cm.dispose();
	    } else {
		if (cm.getPlayer().getCSPoints(1) >= 88888) {
		    cm.gainNX(-88888);	
		    cm.gainItem(5340001, 1);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������ĵ����㹻�ı����ռ䡣");
		    cm.dispose();
		}
	    }
	}else if (selection == 6) {
		if (cm.canHold(2300001) && cm.getMeso() >= 2000000) {
		    cm.gainMeso(-2000000);
		    cm.gainItem(2300000, 100);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������Ľ�һ��㹻�ı����ռ䡣");
		    cm.dispose();
		}
	}else if (selection == 4) {
		if (cm.canHold(2300001) && cm.getPlayer().getCSPoints(1) >= 10000) {
		    cm.gainNX(-10000);	
		    cm.gainItem(2300001, 100);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������ĵ����㹻�ı����ռ䡣");
		    cm.dispose();
		}
		
		}else if (selection == 12) {
		if (cm.canHold(2300000) && cm.getMeso() >= 10000000) {
		    cm.gainMeso(-10000000);
		    cm.gainItem(2300000, 500);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������Ľ�һ��㹻�ı����ռ䡣");
		    cm.dispose();
		}
	}else if (selection == 13) {
		if (cm.canHold(2300001) && cm.getPlayer().getCSPoints(1) >= 40000) {
		    cm.gainNX(-40000);	
		    cm.gainItem(2300001, 500);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������ĵ����㹻�ı����ռ䡣");
		    cm.dispose();
		}
		
	}else if (selection == 5) {
		cm.dispose();
		cm.openNpc(9330045, 5);
	}else if (selection == 100) {
		cm.dispose();
		cm.openNpc(9330045, 100);
	}
    } else if (status == 2) {
	if (sel == 1) {
	    if (cm.canHold(2300001,120) && cm.getMeso() >= 300000) {
		if (!cm.haveItem(2300001)) {
		    cm.gainMeso(-300000);
		    cm.gainItem(2300001, 120);
		    cm.sendNext("���ֵ���~");
		} else {
		    cm.sendNext("���Ѿ����˵�����ն���");
		}
	    } else {
		cm.sendOk("�����Ƿ��������300000��һ��㹻�ı����ռ䡣");
	    }
	    cm.safeDispose();
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