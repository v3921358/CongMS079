var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
var ���� = "#fEffect/CharacterEff/1051295/0/0#";
var �ٷ� = "#fEffect/CharacterEff/1003252/0/0#";
var Сѩ�� = "#fEffect/CharacterEff/1003393/0/0#";
var ���� = "#fEffect/CharacterEff/1032063/0/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
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

            cm.sendOk("��л��Ĺ��٣�");
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
			text += "            "+����+" #e���������б�#n "+����+"\r\n"
			
			if(cm.getPlayer().getOneTimeLog("��������") == 0){
					text += "      #L1#"+����+"#e��������1(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 0){
					text += "              "+����+"#r#e��������1#n"+����+"#l"+���+"#k\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 1 && cm.getLevel() > 14){
					text += "      #L2#"+����+"#e��������2(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 1 && cm.getLevel() > 14){
					text += "              "+����+"#r#e��������2#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "		  "+�ٷ�+"#e   (#rlv.15#k)��������2   "+�ٷ�+"#l\r\n\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 2 && cm.getLevel() > 20){
					text += "      #L3#"+����+"#e��������3(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 2 && cm.getLevel() > 20){
					text += "              "+����+"#r#e��������3#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.21#k)��������3   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 3 && cm.getLevel() > 29){
					text += "      #L4#"+����+"#e��������4(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 3 && cm.getLevel() > 29){
					text += "              "+����+"#r#e��������4#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.30#k)��������4   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 4 && cm.getLevel() > 34){
					text += "      #L5#"+����+"#e��������5(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 4 && cm.getLevel() > 34){
					text += "              "+����+"#r#e��������5#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.35#k)��������5   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 5 && cm.getLevel() > 39){
					text += "      #L6#"+����+"#e��������6(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 5 && cm.getLevel() > 39){
					text += "              "+����+"#r#e��������6#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.40#k)��������6   "+�ٷ�+"#l\r\n"//3
			}
			
			
			if(cm.getPlayer().getOneTimeLog("��������") == 6 && cm.getLevel() > 44){
					text += "      #L7#"+����+"#e��������7(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 6 && cm.getLevel() > 44){
					text += "              "+����+"#r#e��������7#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.45#k)��������7   "+�ٷ�+"#l\r\n"//3
			}
			if(cm.getPlayer().getOneTimeLog("��������") == 7 && cm.getLevel() > 49){
					text += "      #L8#"+����+"#e��������8(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 7 && cm.getLevel() > 49){
					text += "              "+����+"#r#e��������8#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.50#k)��������8   "+�ٷ�+"#l\r\n"//3
			}
			
			
			if(cm.getPlayer().getOneTimeLog("��������") == 8 && cm.getLevel() > 59){
					text += "      #L9#"+����+"#e��������9(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 8 && cm.getLevel() > 59){
					text += "              "+����+"#r#e��������9#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.60#k)��������9   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 9 && cm.getLevel() > 64){
					text += "      #L10#"+����+"#e��������10(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 9 && cm.getLevel() > 64){
					text += "              "+����+"#r#e��������10#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.65#k)��������10   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 10 && cm.getLevel() > 69){
					text += "      #L11#"+����+"#e��������11(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 10 && cm.getLevel() > 69){
					text += "              "+����+"#r#e��������11#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.70#k)��������11   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 11 && cm.getLevel() > 74){
					text += "      #L12#"+����+"#e��������12(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 11 && cm.getLevel() > 74){
					text += "              "+����+"#r#e��������12#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.75#k)��������12   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 12 && cm.getLevel() > 79){
					text += "      #L13#"+����+"#e��������13(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 12 && cm.getLevel() > 79){
					text += "              "+����+"#r#e��������13#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.80#k)��������13   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 13 && cm.getLevel() > 89){
					text += "      #L14#"+����+"#e��������14(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 13 && cm.getLevel() > 89){
					text += "              "+����+"#r#e��������14#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.90#k)��������14   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 14 && cm.getLevel() > 99){
					text += "      #L15#"+����+"#e��������15(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 14 && cm.getLevel() > 99){
					text += "              "+����+"#r#e��������15#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.100#k)��������15   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 15 && cm.getLevel() > 109){
					text += "      #L16#"+����+"#e��������16(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 15 && cm.getLevel() > 109){
					text += "              "+����+"#r#e��������16#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.110#k)��������16   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 16 && cm.getLevel() > 119){
					text += "      #L17#"+����+"#e��������17(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 16 && cm.getLevel() > 119){
					text += "              "+����+"#r#e��������17#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.120#k)��������17   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 17 && cm.getLevel() > 129){
					text += "      #L18#"+����+"#e��������18(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 17 && cm.getLevel() > 129){
					text += "              "+����+"#r#e��������18#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.130#k)��������18   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 18 && cm.getLevel() > 139){
					text += "      #L19#"+����+"#e��������19(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 18 && cm.getLevel() > 139){
					text += "              "+����+"#r#e��������19#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.140#k)��������19   "+�ٷ�+"#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 19 && cm.getLevel() > 149){
					text += "      #L20#"+����+"#e��������20(#r�ɿ�ʼ#k)#n"+����+"#l\r\n\r\n"//3
				} else if(cm.getPlayer().getOneTimeLog("��������") > 19 && cm.getLevel() > 149){
					text += "              "+����+"#r#e��������20#n"+����+"#l"+���+"#k\r\n"//3
				} else {
					text += "         "+�ٷ�+"#e   (#rlv.150#k)��������20   "+�ٷ�+"#l\r\n"//3
			}
            cm.sendSimple(text);
        } else if (selection == 1) {
			if (cm.haveItem(4000002,50) && cm.haveItem(4000017,10)){
				cm.gainItem(4000002, -50);//�����Ʒ
				cm.gainItem(4000017, -10);//�����Ʒ
				cm.gainExp(10000);//���˸�����
				cm.gainItem(1442012,20,20,20,20,20,20,20,20,0,0,0,0,0,0);
				cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������1����þ���ֵ����ȫ����40���ѩ�壡");
				cm.getPlayer().setOneTimeLog("��������");
				cm.sendOk("������������񣬻��ȫ����40���ѩ�壡");
				cm.dispose();
		}else{

			cm.sendOk(��̾��+"#r��ӭ�������߾���1��#k\r\n���ǵó�������ð�յ���ʱ�������ǴӲʺ絺һ��һ�εĹ��¡�\r\n����������Ժ����ǲ���ǰ�У�������һ����#r����ĺ�����#k�ĵط���\r\n���㵽����Ѱ�һ��䣬�����ռ�50��#v4000002#10��#v4000017#��\r\n������#v1442012# ȫ����40��");
			cm.dispose();
	    }
        } else if (selection == 2) {
		if (cm.haveItem(4000015,50) && cm.haveItem(4000008,50)){
			cm.gainItem(4000015, -50);//�����Ʒ
			cm.gainItem(4000008, -50);//�����Ʒ
			cm.gainMeso(+200000); //�Ӽ����
			 cm.gainExp(50000);//���˸�����
				cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������2����þ���ֵ����20W��ң�");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{

			cm.sendOk(��̾��+"#r��ӭ�������߾���2��#k\r\n����ĳ�����������ͣ���������Σ����Ե��ô��ż��̡�\r\n�뵽һ����#r�����϶���#k�ĵط���\r\n�ռ�50��#v4000015#50��#v4000008#��\r\n������#v4031138# 20W��");
			cm.dispose();
	    }
        } else if (selection == 3) {
		if (cm.haveItem(4250602,5)){
			cm.gainItem(4250602,-5);
			cm.gainItem(1142107,5,5,5,5,5,5,5,5,0,0,0,0,0,0);
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������3����þ���ֵ����:����ѫ�¸�����5 ��/ħ5��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();	
		}else{

		 cm.sendOk(��̾��+"#r��ӭ�������߾���3��#k\r\n�뵽#r�����������������#k���ռ�5��#v4250602#���ҡ�\r\n������#v1142107# ������5 ��/ħ5��");
		 cm.dispose();
		
	    }
        } else if (selection == 4) {
			
		if (cm.haveItem(4000106,50) && cm.haveItem(4000107,20)){
			cm.gainItem(4000106, -50);//�����Ʒ
			cm.gainItem(4000107, -20);//�����Ʒ
			cm.gainItem(1032243,5,5,5,5,0,0,5,5,10,10,5,5,0,0);
			cm.gainItem(2000006, 200);//�����Ʒ
			cm.gainExp(92000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������4����þ���ֵ�����۾���");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���4��#k\r\n�뵽#r��¶̨������#k�ռ�50��#v4000106#20��#v4000107#���ҡ�\r\n������#v2022003#��200#v2000006#��200��");
			cm.dispose();
	    }
        } else if (selection == 5) {
			
		if (cm.haveItem(4000170,50) && cm.haveItem(4000169,50)){
			cm.gainItem(1113165,5,5,5,5,0,0,2,2,0,0,0,0,0,0);
			cm.gainExp(122000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������5����þ���ֵ�������׽�ָ����5 ��/ħ2��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���5��#k\r\n�뵽ͯ����-��ɽ����ռ�#v4000170##v4000169#��50�������ҡ�\r\n������#v1082245#������5 ��/ħ2");
			cm.dispose();
	    }
        } else if (selection == 6) {
			if (cm.haveItem(4000276 ,100) && cm.haveItem(4000277 ,50)){
			//cm.gainItem(1142107,5,5,5,5,5,5,1,1,0,0,0,0,0,0);
			cm.gainItem(4000276, -100);//�����Ʒ
			cm.gainItem(4000277, -50);//�����Ʒ
			cm.gainItem(1012101,5,5,5,5,0,0,2,2,0,0,0,0,0,0);
			cm.gainExp(252000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������6����þ���ֵ�������θ�����2 ��/ħ1��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���6��#k\r\n�뵽#r��ͨ������ڣ�#k�ռ�100��#v4000276#50��#v4000277#�����ҡ�\r\n������#v1012101#������5 ��/ħ2");
			cm.dispose();
	    }
        } else if (selection == 7) {
			if (cm.haveItem(4000115 ,50)){
			//cm.gainItem(1142107,5,5,5,5,5,5,1,1,0,0,0,0,0,0);
			cm.gainItem(4000115, -50);//�����Ʒ
			cm.gainItem(1032243,5,5,5,5,0,0,5,5,10,10,5,5,0,0);
			cm.gainExp(30000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������7����þ���ֵ�������׶���������5 ��/ħ5��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���7��#k\r\n�뵽#r��ʱ��֮·4��#k�ռ�50��#v4000115#�����ҡ�\r\n������#v1032098#������2 ��/ħ2");
			cm.dispose();
	    }
        } else if (selection == 8) {
			if (cm.haveItem(4000088 ,50) && cm.haveItem(4000086 ,50)){
			cm.gainDY(+10000);
			cm.gainItem(4000086, -50);//�����Ʒ
			cm.gainItem(4000088, -1);//�����Ʒ
			
			cm.gainExp(400000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������8����þ���ֵ����10000����ȯ");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���8��#k\r\n�뵽#r��ѩ�򸽽���#k�ռ�50��#v4000088#��#v4000086#�����ҡ�\r\n������10000����ȯ");
			cm.dispose();
	    }
		} else if (selection == 9) {
			if (cm.haveItem(4000177 ,50) && cm.haveItem(4000025 ,50)){
			//cm.gainItem(1142107,5,5,5,5,5,5,1,1,0,0,0,0,0,0);
			cm.gainItem(4000177, -50);//�����Ʒ
			cm.gainItem(4000025, -50);//�����Ʒ
			cm.gainItem(1102082,5,5,5,5,0,0,2,2,10,10,5,5,0,0);
			cm.gainExp(812000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������9����þ���ֵ�������������5 ��/ħ2��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���9��#k\r\n�뵽#r������֮�֣�#k�ռ�50��#v4000177#50��#v4000025#�����ҡ�\r\n������#v1102082#������5 ��/ħ2");
			cm.dispose();
	    }
		} else if (selection == 10) {
			if (cm.haveItem(4000289 ,100)){
			//cm.gainItem(1142107,5,5,5,5,5,5,1,1,0,0,0,0,0,0);
			cm.gainItem(4000289, -100);//�����Ʒ
			cm.gainItem(1113164,2,2,2,2,0,0,5,5,10,10,5,5,0,0);
			cm.gainExp(1000000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������10����þ���ֵ������ָ������2 ��/ħ5��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���10��#k\r\n�뵽#r������ɭ�֣�#k�ռ�100��#v4000289#�����ҡ�\r\n������#v1113165#������2 ��/ħ5");
			cm.dispose();
	    }	
		} else if (selection == 11) {
			if (cm.haveItem(4170005 ,5)){
			cm.gainItem(4170005, -5);//�����Ʒ
			cm.gainItem(1132088,5,5,5,5,0,0,3,3,10,10,5,5,0,0);
			cm.gainExp(1200000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������11����þ���ֵ��������������5 ��/ħ3��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���11��#k\r\n�����#r�������Ӹ�����#k��5��#v4170005#�����ҡ�\r\n������#v1132088#������5 ��/ħ3");
			cm.dispose();
	    }
		} else if (selection == 12) {
			if (cm.haveItem(4000226 ,60) && cm.haveItem(4000229 ,60)){
			//cm.gainItem(1142107,15,15,15,15,5,5,1,1,0,0,0,0,0,0);
			cm.gainItem(4000226, -60);//�����Ʒ
			cm.gainItem(4000229, -60);//�����Ʒ
			cm.gainItem(1072718,15,15,15,15,0,0,1,1,10,10,5,5,0,0);
			cm.gainExp(1200000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������12����þ���ֵ����Ь�Ӹ�����15 ��/ħ1��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���12��#k\r\n�뵽#r����ľ������ɭ�֣�#k�ռ�60��#v4000226#60��#v4000229#�����ҡ�\r\n������#v1072718#������15 ��/ħ1");
			cm.dispose();
	    }
		} else if (selection == 13) {
			if (cm.haveItem(4000238 ,150)){
			//cm.gainItem(1142107,5,5,5,5,5,5,1,1,0,0,0,0,0,0);
			cm.gainItem(4000238, -150);//�����Ʒ
			//cm.gainItem(4000229, -100);//�����Ʒ
			//cm.gainItem(1072718,5,5,5,5,0,0,1,1,10,10,5,5,0,0);
			cm.gainExp(1500000);//���˸�����
			cm.gainMeso(+10000000); //�Ӽ����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������13����þ���ֵ�������1000W��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���13��#k\r\n�뵽#r�����֮��·�ڣ�#k�ռ�150��#v4000238#�����ҡ�\r\n����#v4031138#��1000W");
			cm.dispose();
	    }
		} else if (selection == 14) {
			if (cm.haveItem(4000182 ,100)){
			cm.gainItem(4000182, -100);//�����Ʒ
			cm.gainItem(1022129,5,5,5,5,0,0,3,3,10,10,5,5,0,0);
			cm.gainExp(2000000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������14����þ���ֵ�����۾�������5 ��/ħ3��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���14��#k\r\n�뵽#r���Ͽ��1��#k�ռ�100��#v4000182#�����ҡ�\r\n������#v1022129#������5 ��/ħ3");
			cm.dispose();
	    }	
		} else if (selection == 15) {
			if (cm.haveItem(4250602 ,10) && cm.haveItem(4170005 ,5)){
			cm.gainItem(4250602, -10);//�����Ʒ
			cm.gainItem(4170005, -5);//�����Ʒ
		cm.gainNX(+5000);//�����Ʒ
			cm.gainExp(2212000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������15����þ���ֵ����5000��ȯ��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���15��#k\r\n����ɷ�������߸�����#v4250602#10����5��#v4170005#�����ҡ�\r\n������5000��ȯ");
			cm.dispose();
	    }
		} else if (selection == 16) {
			if (cm.haveItem(4000180 ,100) && cm.haveItem(4000181 ,100)){
			cm.gainItem(4000180, -100);//�����Ʒ
			cm.gainItem(4000181, -100);//�����Ʒ
			cm.gainItem(1112676,20,20,20,20,0,0,2,2,10,10,5,5,0,0);
			cm.gainExp(4012000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������16����þ���ֵ������ָ����20 ��/ħ2��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			cm.sendOk(��̾��+"#r��ӭ�������߾���16��#k\r\n�뵽#r�����Ѵ���Ĺ�أ�#k�ռ�100��#v4000180#100��#v4000181#�����ҡ�\r\n������#v1112676#������20 ��/ħ2");
			cm.dispose();
	    }	
		} else if (selection == 17) {
			if (cm.haveItem(4001266 ,1)){
			//cm.gainItem(4000180, -200);//�����Ʒ
			cm.gainItem(4001266, -1);//�����Ʒ
			if (cm.getJob()==2112){
                cm.teachSkill(20001003 ,1,1); //Magic Armorս��
                cm.teachSkill(20001004 ,1,1); //Magic Armorս��
				cm.gainItem(1912011, 1);//�����Ʒ
				cm.gainItem(1902015, 1);//�����Ʒ
				cm.gainItem(1902016, 1);//�����Ʒ
				cm.gainItem(1902017, 1);//�����Ʒ
				cm.gainItem(1902018, 1);//�����Ʒ
				cm.gainExp(17212000);//���˸�����
				cm.getPlayer().setOneTimeLog("��������");
				cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������17����þ���ֵ�������＼��������һֻ��");
				cm.sendOk("������������񣬻�ý�����");
				cm.safeDispose(); //�����ű�
				return;
			}
			cm.gainItem(1912000, 1);//�����Ʒ
			cm.gainItem(1902001, 1);//�����Ʒ
            cm.teachSkill(1003,1,1); //Magic Armor
            cm.teachSkill(1004,1,1); //Magic Armor
			//cm.gainItem(1112676,2,2,2,2,0,0,2,2,10,10,5,5,0,0);
			cm.gainExp(17212000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������17����þ���ֵ�������＼��������һֻ��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
				if (!cm.canHold(4001266,1)) {
				cm.sendOk("������ı����Ƿ�������");
				cm.dispose();
				return;
				}
			cm.gainItem(4001266, 1);//�����Ʒ
			cm.sendOk(��̾��+"#r��ӭ�������߾���17��ð�յ�ף����Ŀ��ģ�\r\n������ɣ��ٴε��ҽ������������＼��������һֻ��");
			cm.dispose();
	    }
		} else if (selection == 18) {
			if (cm.haveItem(4000235 ,1) && cm.haveItem(4000243 ,1) && cm.haveItem(4000460 ,1) && cm.haveItem(4000461 ,1) && cm.haveItem(4000462 ,1)){
			//cm.gainItem(4000180, -200);//�����Ʒ
			//cm.gainItem(4000181, -200);//�����Ʒ
			cm.gainItem(4000235, -1);//�����Ʒ
			cm.gainItem(4000243, -1);//�����Ʒ
			cm.gainItem(4000460, -1);//�����Ʒ
			cm.gainItem(4000461, -1);//�����Ʒ
			cm.gainItem(4000462, -1);//�����Ʒ
			cm.gainItem(1052457  ,20,20,20,20,0,0,2,2,100,100,50,50,0,0);
			cm.gainExp(37212000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������18����þ���ֵ�����׷�������20 ��/ħ2��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			//cm.sendOk(��̾��+"#r��ӭ�������߾���17��ð�յ�ף����Ŀ��ģ�\r\n������ɣ��ٴε��ҽ������������＼��������һֻ��");
			cm.sendOk(��̾��+"#r��ӭ�������߾���18��#k\r\n����#r�����������ӥ����࣬���������ޣ��׿���#k\r\n�ռ�#v4000235##v4000243##v4000460##v4000461##v4000462#��1�������ҡ�\r\n������#v1052457#������10 ��/ħ2");
			cm.dispose();
	    }	
		} else if (selection == 19) {
			if (cm.haveItem(4001083 ,1) && cm.haveItem(4001085 ,1)){
			//cm.gainItem(4000180, -200);//�����Ʒ
			//cm.gainItem(4000181, -200);//�����Ʒ
			cm.gainItem(4001083, -1);//�����Ʒ
			cm.gainItem(4001085, -1);//�����Ʒ
			cm.gainItem(1112661  ,15,15,15,15,0,0,5,5,0,0,30,30,0,0);
			cm.gainExp(57212000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������19����þ���ֵ������ָ������15 ��/ħ5��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			//cm.sendOk(��̾��+"#r��ӭ�������߾���17��ð�յ�ף����Ŀ��ģ�\r\n������ɣ��ٴε��ҽ������������＼��������һֻ��");
			cm.sendOk(��̾��+"#r��ӭ�������߾���19��#k\r\n����#r��������������#k�ռ�#v4001083##v4001085#��1��֤�����ʵ����\r\n������#v1112661#������5 ��/ħ5");
			cm.dispose();
	    }	
		} else if (selection == 20) {
		if (cm.haveItem(4020009 ,20) && cm.haveItem(4000245 ,20)){
			//cm.gainItem(4000180, -200);//�����Ʒ
			//cm.gainItem(4000181, -200);//�����Ʒ
			cm.gainItem(4020009, -10);//�����Ʒ
			cm.gainItem(4000245, -10);//�����Ʒ
			cm.gainItem(1702224  ,18,18,18,18,200,200,20,20,50,50,30,30,10,10);
			cm.gainNX(+30000);//�����Ʒ
			cm.gainExp(47212000);//���˸�����
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�������������20����þ���ֵ����;��ȯ3���Լ�͸������������18 ��/ħ20��");
			cm.sendOk("������������񣬻�ý�����");
			cm.dispose();
		}else{
			//cm.sendOk(��̾��+"#r��ӭ�������߾���17��ð�յ�ף����Ŀ��ģ�\r\n������ɣ��ٴε��ҽ������������＼��������һֻ��");
			cm.sendOk(��̾��+"#r��ӭ�������߾���20��#k\r\n�뵽#r��������Ѩ��ʱ����#k�ռ�#v4020009##v4000245#��20�������ҡ�\r\n������#v1702224#������18 ��/ħ20�Լ���ȯ3��");
			cm.dispose();
	    }20
		}
    }
}
