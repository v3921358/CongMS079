/*ע
���
11ף��4==11
12====5==21
��ȯ
21ף��6==12
22====7==22
��������
QQ327321366
*/
var sx = Math.floor(Math.random()*14);
var xh1=100;//70%��������������
var xh2=200;//30%��������������
var sj1=Math.floor(Math.random()*7);//0/1/2/3/4/5/6/7/8/9//�ɹ���
var meso=5000000;//����дǮ
var dianquan = 10000;
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var status = 0;
var slot = Array("����", "����", "����", "����", "HP", "MP", "������", "ħ������", "�������", "ħ������", "������", "�ر���", "�ּ�", "�ƶ��ٶ�", "��Ծ��");
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
		text = "\t\t\t\t#e#v2049100#����ǿ��#v2049100##k#n\r\n\�������������߻�������ǿ������!�ɹ�������#r70%#k\r\n�ɵ�����������#r-2~+8#k\r\n#r#b��������ǿ������#r1��,#b��ѡ����Ҫǿ����װ��!\r\n#k\r\n";
        text += "#r- ������ѡ��Ҫǿ���ĵ��� -#n\r\n#b";
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
		text ="��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(num).getItemId()+"##t"+cm.getInventory(1).getItem(num).getItemId()+"#\r\n\r\n";
        text += "#e ��������ѡ����ʹ�õľ��ᣨ#r�ɹ�������90%#k�� #n\r\n\r\n#b";
		text += "\r\n#L1##v2049100##z2049100#ͬʱ����"+meso+"ð�ձ�(-2~5)#b";//70
		text += "\r\n#L2##v2049100##z2049100#ͬʱ����"+dianquan+"��ȯ#b(-2~8)";//30
        cm.sendSimple(text);
		} else if (status == 2) {
			if (selection == 1){//70
				if(cm.haveItem(2049100)&&cm.getMeso()>meso){
					cm.sendSimple("�Ƿ�ʹ��ף�����᣿\r\n#L11##r��#k#l\t\t#L12#��#l");
				}else {
					cm.sendOk("��ȷ�ϱ������Ƿ���#v2049100##z2049100#���ǽ�Ҳ���");
					cm.dispose();
				}
			}else if (selection == 2){//30
				if(cm.haveItem(2049100)&&cm.getPlayer().getNX()>=dianquan){
					cm.sendSimple("�Ƿ�ʹ��ף�����᣿\r\n#L21##r��#k#l\t\t#L22#��#l");
				}else {
					cm.sendOk("��ȷ�ϱ������Ƿ���#v2049100##z2049100#���ǵ�ȯ����");
					cm.dispose();
				}
			} else {
				cm.dispose();
			}
		} else if (status == 3) {
			if(selection == 11){//70
				var cc = cm.getInventory(1).getItem(num).getItemId();
					status = 3;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
			}else if(selection == 12) {//70
				var cc = cm.getInventory(1).getItem(num).getItemId();
					status = 4;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
			}else if(selection == 21) {//30
				var cc = cm.getInventory(1).getItem(num).getItemId();
					status = 5;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
			}else if (selection == 22){//30
				var cc = cm.getInventory(1).getItem(num).getItemId();
					status = 6;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
			}
		} else if (status == 4){
			status = 10;
			if(!cm.haveItem(2340000)){
				cm.sendOk("#v2340000#��Ʒ�������㣡");
				cm.dispose();
				return;
			}
			var item = cm.getInventory(1).getItem(num).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(num).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			}
			if(sj1 == 1){
				cm.gainItem(2049100,-1);
				cm.gainItem(2340000,-1);
				cm.gainMeso(-meso);
				cm.sendOk("ǿ��ʧ���ˣ�");
				cm.dispose();
			}else{
				cm.gainItem(2049100,-1);
				cm.gainItem(2340000,-1);
				cm.gainMeso(-meso);
				cm.sendYesNo("#e#rǿ���ɹ���#k\r\n\t��ѡ���װ��#v"+cm.getInventory(1).getItem(num).getItemId()+"#�������������£�\r\n\t"+slot[sx]+"#r "+sjsx+"��HP/MP��*10��#k\r\n\t#e#r�Ƿ�������ӳɣ�\r\n����װ��ԭʼ�޸��������ȷ�ϣ����˻ز��ϣ���");
			}
		} else if (status == 5){
			status = 20;
			var item = cm.getInventory(1).getItem(num).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(num).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			}
			if(sj1 == 1){
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(num).getItemId();
			var item = cm.getInventory(1).getItem(num).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
				cm.gainMeso(-meso);
				cm.gainItem(2049100,-1);
				item.setUpgradeSlots(item.getUpgradeSlots()-1);
				MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
				MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
				cm.sendOk("ǿ��ʧ���ˣ�");
				cm.dispose();
			}else{
				cm.gainMeso(-meso);
				cm.gainItem(2049100,-1);
				cm.sendYesNo("#e#rǿ���ɹ���#k\r\n\t��ѡ���װ��#v"+cm.getInventory(1).getItem(num).getItemId()+"#�������������£�\r\n\t"+slot[sx]+"#r "+sjsx+"��HP/MP��*10��#k\r\n\t#e#r�Ƿ�������ӳɣ�\r\n����װ��ԭʼ�޸��������ȷ�ϣ����˻ز��ϣ���");
			}
		} else if (status == 6){
			status = 11;
			if(!cm.haveItem(2340000)){
				cm.sendOk("#v2340000#��Ʒ�������㣡");
				cm.dispose();
				return;
			}
			var item = cm.getInventory(1).getItem(num).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(num).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			}
			if(sj1 == 1){
				cm.gainItem(2049100,-1);
				cm.gainItem(2340000,-1);
				cm.gainNX(-dianquan);
				cm.sendOk("ǿ��ʧ���ˣ�");
				cm.dispose();
			}else{
				cm.gainItem(2049100,-1);
				cm.gainItem(2340000,-1);
				cm.gainNX(-dianquan);
				cm.sendYesNo("#e#rǿ���ɹ���#k\r\n\t��ѡ���װ��#v"+cm.getInventory(1).getItem(num).getItemId()+"#�������������£�\r\n\t"+slot[sx]+"#r "+sjsx2+"��HP/MP��*10��#k\r\n\t#e#r�Ƿ�������ӳɣ�\r\n����װ��ԭʼ�޸��������ȷ�ϣ����˻ز��ϣ���");
			}
		} else if (status == 7){
			status = 21;
			var item = cm.getInventory(1).getItem(num).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(num).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			}
			if(sj1 == 1){
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(num).getItemId();
			var item = cm.getInventory(1).getItem(num).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
				cm.gainItem(2049100,-1);
				cm.gainNX(-dianquan);
				item.setUpgradeSlots(item.getUpgradeSlots()-1);
				MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
				MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
				cm.sendOk("ǿ��ʧ���ˣ�");
				cm.dispose();
			}else{
				cm.gainItem(2049100,-1);
				cm.gainNX(-dianquan);
				cm.sendYesNo("#e#rǿ���ɹ���#k\r\n\t��ѡ���װ��#v"+cm.getInventory(1).getItem(num).getItemId()+"#�������������£�\r\n\t"+slot[sx]+"#r "+sjsx2+"��HP/MP��*10��#k\r\n\t#e#r�Ƿ�������ӳɣ�\r\n����װ��ԭʼ�޸��������ȷ�ϣ����˻ز��ϣ���");
			}
		} else if (status == 8){
			cm.dispose();
		} else if (status == 11){//+sjsx
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
			if(sx==0&&sx0!=0){
			item.setStr(sx0+sjsx);
			}else if(sx==1&&sx1!=0){
			item.setDex(sx1+sjsx);
			}else if(sx==2&&sx2!=0){
			item.setInt(sx2+sjsx);
			}else if(sx==3&&sx3!=0){
			item.setLuk(sx3+sjsx);
			}else if(sx==4&&sx4!=0){
			item.setHp(sx4+sjsx*10);
			}else if(sx==5&&sx5!=0){
			item.setMp(sx5+sjsx*10);
			}else if(sx==6&&sx6!=0){
			item.setWatk(sx6+sjsx);
			}else if(sx==7&&sx7!=0){
			item.setMatk(sx7+sjsx);
			}else if(sx==8&&sx8!=0){
			item.setWdef(sx8+sjsx);
			}else if(sx==9&&sx9!=0){
			item.setMdef(sx9+sjsx);
			}else if(sx==10&&sx10!=0){
			item.setAcc(sx10+sjsx);
			}else if(sx==11&&sx11!=0){
			item.setAvoid(sx11+sjsx);
			}else if(sx==12&&sx12!=0){
			item.setHands(sx12+sjsx);
			}else if(sx==13&&sx13!=0){
			item.setSpeed(sx13+sjsx);
			}else if(sx==14&&sx14!=0){
			item.setJump(sx14+sjsx);
			}else {
			cm.sendOk("��װ��ԭʼ����û��������ǿ������ֹ��#r�˻ز���#k��\r\nTips:�뱣֤����õ������ԣ�ԭʼװ����Ҫ��Ŷ��");
			cm.gainItem(2049100,1);
			cm.gainItem(2340000,1);
			item.setUpgradeSlots(item.getUpgradeSlots()+1);
			item.setLevel(item.getLevel()-1);
			cm.gainMeso(meso);
			cm.dispose();
			}
			item.setUpgradeSlots(item.getUpgradeSlots()-1);
			item.setLevel(item.getLevel()+1);
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			//�滻1
			cm.sendOk("ǿ���ɹ���");
			cm.dispose();
		} else if (status == 12) {//+sjsx2
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
			if(sx==0&&sx0!=0){
			item.setStr(sx0+sjsx2);
			}else if(sx==1&&sx1!=0){
			item.setDex(sx1+sjsx2);
			}else if(sx==2&&sx2!=0){
			item.setInt(sx2+sjsx2);
			}else if(sx==3&&sx3!=0){
			item.setLuk(sx3+sjsx2);
			}else if(sx==4&&sx4!=0){
			item.setHp(sx4+sjsx2*10);
			}else if(sx==5&&sx5!=0){
			item.setMp(sx5+sjsx2*10);
			}else if(sx==6&&sx6!=0){
			item.setWatk(sx6+sjsx2);
			}else if(sx==7&&sx7!=0){
			item.setMatk(sx7+sjsx2);
			}else if(sx==8&&sx8!=0){
			item.setWdef(sx8+sjsx2);
			}else if(sx==9&&sx9!=0){
			item.setMdef(sx9+sjsx2);
			}else if(sx==10&&sx10!=0){
			item.setAcc(sx10+sjsx2);
			}else if(sx==11&&sx11!=0){
			item.setAvoid(sx11+sjsx2);
			}else if(sx==12&&sx12!=0){
			item.setHands(sx12+sjsx2);
			}else if(sx==13&&sx13!=0){
			item.setSpeed(sx13+sjsx2);
			}else if(sx==14&&sx14!=0){
			item.setJump(sx14+sjsx2);
			}else {
			cm.sendOk("��װ��ԭʼ����û��������ǿ������ֹ��#r�˻ز���#k��\r\nTips:�뱣֤����õ������ԣ�ԭʼװ����Ҫ��Ŷ��");
			cm.gainItem(2049100,1);
			cm.gainItem(2340000,1);
			cm.gainNX(dianquan);
			item.setUpgradeSlots(item.getUpgradeSlots()+1);
			item.setLevel(item.getLevel()-1);
			cm.dispose();
			}
			item.setUpgradeSlots(item.getUpgradeSlots()-1);
			item.setLevel(item.getLevel()+1);
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.sendOk("ǿ���ɹ���");
			//�滻1
			cm.dispose();
		} else if (status == 21){//+sjsx
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
			if(sx==0&&sx0!=0){
			item.setStr(sx0+sjsx);
			}else if(sx==1&&sx1!=0){
			item.setDex(sx1+sjsx);
			}else if(sx==2&&sx2!=0){
			item.setInt(sx2+sjsx);
			}else if(sx==3&&sx3!=0){
			item.setLuk(sx3+sjsx);
			}else if(sx==4&&sx4!=0){
			item.setHp(sx4+sjsx*10);
			}else if(sx==5&&sx5!=0){
			item.setMp(sx5+sjsx*10);
			}else if(sx==6&&sx6!=0){
			item.setWatk(sx6+sjsx);
			}else if(sx==7&&sx7!=0){
			item.setMatk(sx7+sjsx);
			}else if(sx==8&&sx8!=0){
			item.setWdef(sx8+sjsx);
			}else if(sx==9&&sx9!=0){
			item.setMdef(sx9+sjsx);
			}else if(sx==10&&sx10!=0){
			item.setAcc(sx10+sjsx);
			}else if(sx==11&&sx11!=0){
			item.setAvoid(sx11+sjsx);
			}else if(sx==12&&sx12!=0){
			item.setHands(sx12+sjsx);
			}else if(sx==13&&sx13!=0){
			item.setSpeed(sx13+sjsx);
			}else if(sx==14&&sx14!=0){
			item.setJump(sx14+sjsx);
			}else {
			cm.sendOk("��װ��ԭʼ����û��������ǿ������ֹ��#r�˻ز���#k��\r\nTips:�뱣֤����õ������ԣ�ԭʼװ����Ҫ��Ŷ��");
			cm.gainItem(2049100,1);
			cm.gainMeso(meso);
			item.setUpgradeSlots(item.getUpgradeSlots()+1);
			item.setLevel(item.getLevel()-1);
			cm.dispose();
			}
			item.setUpgradeSlots(item.getUpgradeSlots()-1);
			item.setLevel(item.getLevel()+1);
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.sendOk("ǿ���ɹ���");
			//�滻1
			cm.dispose();
		} else if (status == 22) {//+sjsx2
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
			if(sx==0&&sx0!=0){
			item.setStr(sx0+sjsx2);
			}else if(sx==1&&sx1!=0){
			item.setDex(sx1+sjsx2);
			}else if(sx==2&&sx2!=0){
			item.setInt(sx2+sjsx2);
			}else if(sx==3&&sx3!=0){
			item.setLuk(sx3+sjsx2);
			}else if(sx==4&&sx4!=0){
			item.setHp(sx4+sjsx2*10);
			}else if(sx==5&&sx5!=0){
			item.setMp(sx5+sjsx2*10);
			}else if(sx==6&&sx6!=0){
			item.setWatk(sx6+sjsx2);
			}else if(sx==7&&sx7!=0){
			item.setMatk(sx7+sjsx2);
			}else if(sx==8&&sx8!=0){
			item.setWdef(sx8+sjsx2);
			}else if(sx==9&&sx9!=0){
			item.setMdef(sx9+sjsx2);
			}else if(sx==10&&sx10!=0){
			item.setAcc(sx10+sjsx2);
			}else if(sx==11&&sx11!=0){
			item.setAvoid(sx11+sjsx2);
			}else if(sx==12&&sx12!=0){
			item.setHands(sx12+sjsx2);
			}else if(sx==13&&sx13!=0){
			item.setSpeed(sx13+sjsx2);
			}else if(sx==14&&sx14!=0){
			item.setJump(sx14+sjsx2);
			}else {
			cm.sendOk("��װ��ԭʼ����û��������ǿ������ֹ��#r�˻ز���#k��\r\nTips:�뱣֤����õ������ԣ�ԭʼװ����Ҫ��Ŷ��");
			cm.gainItem(2049100,1);
			cm.gainNX(dianquan);
			item.setUpgradeSlots(item.getUpgradeSlots()+1);
			item.setLevel(item.getLevel()-1);
			cm.dispose();
			}
			item.setUpgradeSlots(item.getUpgradeSlots()-1);
			item.setLevel(item.getLevel()+1);
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,num,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.sendOk("ǿ���ɹ���");
			//�滻1
			cm.dispose();
		}else if(status == 23){
			cm.dispose();
		}
	}
}
