var xx;//
var xxdj;//
var xxnew;//�����ǵȼ� owner
var shibai;//ʧ�ܱ�ʶ
var sj1;//�ɹ���
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var xuqiushuliang;
var status = 0;
var stats = Array("����", "����", "����", "����", "HP", "MP", "������", "ħ������", "�������", "ħ������", "������", "�ر���", "������", "�ƶ��ٶ�", "��Ծ��", "������", "�ƽ�����ʹ����", "ʹ�þ�����", "������", "ǳ�� 1", "ǳ�� 2", "ǳ�� 3", "װ������");
var num;
function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if ((mode == 0 && status == 2) || (mode == 0 && status == 13)) {
			cm.dispose();
			return;
		}else 	
		if (status >= 0 && mode == 0) {
		cm.sendOk("#b�õ�,�´��ټ�.");
		cm.dispose();
		return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0){
		itemList = cm.getInventory(1).list().iterator();
		text = "\t#e========��ӭ����	#r����������ð�յ�#k========\r\n\#e����������Զ�#r�Ѿ���������#k��װ�������������ԣ�\r\nÿ��ʹ����֮��������#r1-10#k����ά��˫��#k\r\n��֮���ȼ�Խ�ߣ�ʧ�ܼ���Խ�󣬵�ǰ����10��\r\nʧ�ܽ����½���֮���ȼ���#r6��֮ǰ�����½�#k��\r\n#b";
        text += "#e- ������ѡ��Ҫǿ����֮���ĵ��� -#n\r\n#b";
        var indexof = 1;
        while (itemList.hasNext()) {
            var item = itemList.next();
            text += "#L" + item.getPosition() + "##v" + item.getItemId() + "#";
            if (indexof > 1 && indexof % 5 == 0) {
                text += "\r\n";
            }
            indexof++;
        }
        cm.sendSimple(text);
		} else if (status == 1){
			num = selection;
			var xx = cm.getInventory(1).getItem(num).getOwner();
			if(xx == ""){
				sj1 = 0;
				shibai = 0;
				xxnew = "��";
				xxdj = 0;
			}else if (xx == "��"){
				sj1 = Math.floor(Math.random()*1);
				shibai = 0;
				xxnew = "���";
				xxdj = 1;
			}else if (xx == "���"){
				sj1 = Math.floor(Math.random()*2);
				shibai = 0;
				xxnew = "����";
				xxdj = 2;
			}else if (xx == "����"){
				sj1 = Math.floor(Math.random()*3);
				shibai = 0;
				xxnew = "�����";
				xxdj = 3;
			}else if (xx == "�����"){
				sj1 = Math.floor(Math.random()*4);
				shibai = 0;
				xxnew = "������";
				xxdj = 4;
			}else if (xx == "������"){
				sj1 = Math.floor(Math.random()*5);
				shibai = 0;
				xxnew = "�������";
				xxdj = 5;
			}else if (xx == "�������"){
				sj1 = Math.floor(Math.random()*6);
				shibai = 0;
				xxnew = "7��";
				xxdj = 6;
			}else if (xx == "7��"){
				sj1 = Math.floor(Math.random()*7);
				shibai = 2;
				xxnew = "8��";
				xxdj = 7;
			}else if (xx == "8��"){
				sj1 = Math.floor(Math.random()*8);
				shibai = 3;
				xxnew = "9��";
				xxdj = 8;
			}else if (xx == "9��"){
				sj1 = Math.floor(Math.random()*9);
				shibai = 4;
				xxnew = "10��";
				xxdj = 9;
			}else { 
				cm.dispose();
			}
			var xuqiushuliang = (xxdj+1)*(xxdj+1)*10+9;
			text ="��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(num).getItemId()+"##t"+cm.getInventory(1).getItem(num).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n����Ҫ#v3992025##r"+xuqiushuliang+"#k��������ǿ�����ɹ��ʣ�"+Math.floor(100/(xxdj+1))+"%\r\n������һ��ʹ����֮������ǿ��\r\n\r\n";
			if(xx == ""||xx == "��"||xx == "���"||xx == "����"||xx == "�����"||xx == "������"||xx == "�������"||xx == "7��"||xx == "8��"||xx == "9��"||xx == "10��"){
				cm.sendNext(text);
			}else {
				cm.sendOk("��װ������ʹ����֮��ǿ�������ͼ����ϵ����Ա��\r\n"+xx+"");
				cm.dispose();
			}
		} else if (status == 2){
			var xuqiushuliang = (xxdj+1)*(xxdj+1)*10+9;
			var item = cm.getInventory(1).getItem(num).copy();
			var xx = cm.getInventory(1).getItem(num).getOwner();
			if (item.getUpgradeSlots() != 0){
				cm.sendOk("��ѡ���װ������ʣ��ǿ������������ʹ����֮����");
				cm.dispose();
				return;
			}
			if(cm.getInventory(1).getItem(num).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ǿ����֮��.");
				cm.dispose();
				return;
			}
			if(!cm.haveItem(3992025,xuqiushuliang)){
				cm.sendOk("#v3992025#��Ʒ�������㣡");
				cm.dispose();
				return;
			}
			if (cm.isCash(cm.getInventory(1).getItem(num).getItemId())) {
				cm.sendOk("�ֽ���߲��ܽ���");
				cm.dispose();
				return;
			}
			if(sj1==0){
				cm.gainItem(3992025,-xuqiushuliang);
				cm.sendYesNo("#e#rǿ���ɹ�����ȷ�ϣ�#k\r\n\t��ѡ���װ��#v"+cm.getInventory(1).getItem(num).getItemId()+"#�� "+xx+" ������ #r"+xxnew+" #k\r\n\t#e#r��\r\n");
			}else {
				cm.gainItem(3992025,-xuqiushuliang);
				if(shibai == 0){
					cm.sendOk("ǿ��ʧ����,������֮����û���½���");
					cm.dispose();
				}else {
					var statup = new java.util.ArrayList();
					var itemId1 = cm.getInventory(1).getItem(num).getItemId();
					var item = cm.getInventory(1).getItem(num).copy();
					var ii = MapleItemInformationProvider.getInstance();
					var type =  ii.getInventoryType(itemId1);
					var sx0 = item.getStr();//����0
					var sx1 = item.getDex();//����1
					var sx2 = item.getInt();//����2
					var sx3 = item.getLuk();//����3
					var sx4 = item.getHp();//HP4
					var sx5 = item.getMp();//MP5
					var sx6 = item.getWatk();//�﹥6
					var sx7 = item.getMatk();//ħ��7
					var sx8 = item.getWdef();//���8
					var sx9 = item.getMdef();//ħ��9
					var sx10= item.getAcc();//����10
					var sx11= item.getAvoid();//�ر�11
					var sx12= item.getHands();//�ּ�12
					var sx13= item.getSpeed();//�ƶ��ٶ�13
					var sx14= item.getJump();//��Ծ��14
					item.setStr(sx0-(xxdj-5)*2);
					item.setDex(sx1-(xxdj-5)*2);
					item.setInt(sx2-(xxdj-5)*2);
					item.setLuk(sx3-(xxdj-5)*2);
					item.setHp(sx4-(xxdj-5)*2);
					item.setMp(sx5-(xxdj-5)*2);
					item.setWatk(sx6-(xxdj-5)*2);
					item.setMatk(sx7-(xxdj-5)*2);
					//item.setWdef(sx8*0.99);
					//item.setMdef(sx9*0.99);
					//item.setAcc(sx10*0.99);
					//item.setAvoid(sx11*0.99);
					//item.setHands(sx12*0.99);
					//item.setSpeed(sx13*0.99);
					//item.setJump(sx14*0.99);
					item.setOwner("�������");
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					cm.sendOk("ǿ��ʧ����,��֮�����½�����6��");
					cm.dispose();
				}
			}
		} else if (status == 3){//+sjsx
			var nsx0 = getMinAndMax(1,xxdj+1);
			var nsx1 = getMinAndMax(1,xxdj+1);
			var nsx1 = getMinAndMax(1,xxdj+1);
			var nsx2 = getMinAndMax(1,xxdj+1);
			var nsx3 = getMinAndMax(1,xxdj+1);
			var nsx4 = getMinAndMax(1,xxdj+1);
			var nsx5 = getMinAndMax(1,xxdj+1);
			var nsx6 = getMinAndMax(1,xxdj+1);
			var nsx7 = getMinAndMax(1,xxdj+1);
			var nsx8 = getMinAndMax(1,xxdj+1);
			var nsx9 = getMinAndMax(1,xxdj+1);
			var nsx10 = getMinAndMax(1,xxdj+1);
			var nsx11 = getMinAndMax(1,xxdj+1);
			var nsx12 = getMinAndMax(1,xxdj+1);
			var nsx13 = getMinAndMax(1,xxdj+1);
			var nsx14 = getMinAndMax(1,xxdj+1);
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(num).getItemId();
			var item = cm.getInventory(1).getItem(num).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//����0
			var sx1 = item.getDex();//����1
			var sx2 = item.getInt();//����2
			var sx3 = item.getLuk();//����3
			var sx4 = item.getHp();//HP4
			var sx5 = item.getMp();//MP5
			var sx6 = item.getWatk();//�﹥6
			var sx7 = item.getMatk();//ħ��7
			var sx8 = item.getWdef();//���8
			var sx9 = item.getMdef();//ħ��9
			var sx10= item.getAcc();//����10
			var sx11= item.getAvoid();//�ر�11
			var sx12= item.getHands();//�ּ�12
			var sx13= item.getSpeed();//�ƶ��ٶ�13
			var sx14= item.getJump();//��Ծ��14
			item.setStr(sx0+nsx0);
			item.setDex(sx1+nsx1);
			item.setInt(sx2+nsx2);
			item.setLuk(sx3+nsx3);
			item.setHp(sx4+nsx4);
			item.setMp(sx5+nsx5);
			item.setWatk(sx6+nsx6);
			item.setMatk(sx7+nsx7);
			//item.setWdef(sx8+nsx8);
			//item.setMdef(sx9+nsx9);
			//item.setAcc(sx10+nsx10);
			//item.setAvoid(sx11+nsx11);
			//item.setHands(sx12+nsx12);
			//item.setSpeed(sx13+nsx13);
			//item.setJump(sx14+nsx14);
			item.setOwner(xxnew);
			cm.dispose();
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.sendOk("��֮��ǿ���ɹ���");
			cm.laba(cm.getPlayer().getName() + " [��֮��]" + " : " + "�ɹ�ǿ����֮����"+xxnew+"�����ף����(��)�ɣ���",11);
			cm.dispose();
		}
	}
}

function getMinAndMax(minVal, maxVal) {
	return Math.floor(Math.random()*(maxVal-minVal+1))+minVal;
}
