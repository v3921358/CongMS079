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
	//cm.sendSimple("����Ҫ��ʲô��\n\r #b#L0#������㳡#l \n\r #L1#�����#l \n\r #L2#�������#l \n\r #L3#����ζ�Ķ�����#l \n\r #L4#����ָ��#l \n\r #L5##i1142146:#ó��500�𵰣�����ѫ��[�ڣ�30��]��#l");
	
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
	
	
	
            text += "\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n#e�����빺�������#v3011000#�����#v5340001#�������#v2300000#\r\n#k\r\n"//3
            text += "#L100#    ����������  #r��������#k  ����������#l#k\r\n\r\n"
            text += "#L14#    ����������  #r������㳡#k  ����������  #l\r\n\r\n"
			text += "#L5# #v4031636##v4031640#  ��  #r����һ�����#k  ��  #v4031644##v4031648##l\r\n\r\n"//3
		   
  /*          text += "#v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007#\r\n"//3
            text += "#v5021007#��ӭ��λ�μ�ѩ��ð�յ���һ��#r�������#k��#v5021007#\r\n\r\n"//3
            text += "#L10##v3011000##v5340001##v2300001#��ȡ��#l\r\n"//3
            text += "#L11##v4031627##v4031628##v4031630##v4031631#��ѯ��ǰ�������#l\r\n"//3
			
	sl = cm.getPlayer().getItemQuantity(4031627, false);
            sl = cm.getPlayer().getItemQuantity(4031627, false);
            slA = cm.getPlayer().getItemQuantity(4031628, false);
            slB = cm.getPlayer().getItemQuantity(4031630, false);
            slC = cm.getPlayer().getItemQuantity(4031631, false);
            //slD = cm.getPlayer().getItemQuantity(4031632, false);
            slE = cm.getPlayer().getItemQuantity(4031633, false);
            slF = cm.getPlayer().getItemQuantity(4031634, false);
            slG = cm.getPlayer().getItemQuantity(4031635, false);
            slH = cm.getPlayer().getItemQuantity(4031636, false);
            slI = cm.getPlayer().getItemQuantity(4031637, false);
            slJ = cm.getPlayer().getItemQuantity(4031638, false);
            slK = cm.getPlayer().getItemQuantity(4031639, false);
            slL = cm.getPlayer().getItemQuantity(4031640, false);
            slM = cm.getPlayer().getItemQuantity(4031641, false);
            slN = cm.getPlayer().getItemQuantity(4031642, false);
            slO = cm.getPlayer().getItemQuantity(4031643, false);
            slP = cm.getPlayer().getItemQuantity(4031644, false);
            slQ = cm.getPlayer().getItemQuantity(4031645, false);
            slR = cm.getPlayer().getItemQuantity(4031646, false);
            slS = cm.getPlayer().getItemQuantity(4031647, false);
            slT = cm.getPlayer().getItemQuantity(4031648, false);
            //slU = cm.getPlayer().getItemQuantity(4031629, false);
			slD=0;
			slU=0;
			var jifen = sl + slA + slB + slC + slD + slE + slF + slG + slH + slI + slJ + slK + slL + slM + slN + slO + slP + slQ + slR + slS + slT + slU;
			cm.����(1,"��ң�["+cm.getName()+"] "+jifen+" ��");
			
			
		   */
		   
		   
		   
		   
		   
		if (cm.getPlayer().getClient().getChannel() != 1) {
			cm.sendOk("ֻ����1�ߵ���Ŷ��");
			cm.dispose();
			return;
		}
	
		   if (cm.getPlayer().getMapId()==741000200){
            cm.sendSimple(text);
		   }else{
			//cm.sendOk("��GM֪ͨ��");
			//cm.dispose();
				//cm.����(3,"��ң�"+cm.getName()+" a��");
				  
		if (cm.getLevel() > 29){
			//cm.givePartyExp(651111100);//�����������˾���
            cm.sendSimple(text);
		}else{
			cm.sendOk("30�����ϲſ��Բμ�Ŷ��");
			cm.dispose();
		}
           // cm.sendSimple(text);
		   }
    //cm.sendSimple("��ã������泡����Ա��\n\r�������Ҫ���е��㣬�빺�����ͷ�#v5340001#/#v5340000#���֣��߼�10���һ���㣬��ͨ����20��һ�Σ����˽�����ô�࣬����Ѽ��һ�������ɣ�\n\r #b#L0# #v4000411#  ������㳡.#l \n\r #L2##v3011000#  1000���ҹ��������.#l \n\r #L3##v5350000# ����߼����100��1000W#l \n\r #L4##v4161001#   ����ָ��.#l \n\r #L6##v4031630#   �һ��������.#l \n\r #L5##v4001200#   С��齱��.#l");
       } else if (status == 1) {
	if (sel == 0) {
		
	} else if (selection == 1) {
			//cm.warp(741000200, 0);
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
		    cm.gainItem(3011000, 1, 12);
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
		if (cm.getPlayer().getCSPoints(1) >= 500) {//�ж��������Ƿ��㹻500
		    cm.gainNX(-500);	//�Ӽ���ȯ
		    cm.gainItem(5340001, 1,12);
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
		if (cm.getPlayer().getCSPoints(1) >= 1000) {
		    cm.gainNX(-1000);	//�Ӽ���ȯ
		    cm.gainItem(2300001, 100);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������ĵ����㹻�ı����ռ䡣");
		    cm.dispose();
		}
		
		}else if (selection == 12) {
		if (cm.canHold(2300001) && cm.getMeso() >= 10000000) {
		    cm.gainMeso(-10000000);
		    cm.gainItem(2300000, 500);
		    cm.sendOk("ף����ֵ��㣡");
		    cm.dispose();
		} else {
		    cm.sendOk("�����Ƿ�������Ľ�һ��㹻�ı����ռ䡣");
		    cm.dispose();
		}
	}else if (selection == 13) {
		if (cm.getPlayer().getCSPoints(1) >= 4000) {
		    cm.gainNX(-4000);	//�Ӽ���ȯ
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