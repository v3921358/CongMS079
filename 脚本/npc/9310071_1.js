var FY0 = "��������������������������";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�ǩ�����������������������";
var FY6 = "�� ΨһQQ:1848350048    ��";
var FY7 = "��������������������������";
var select = -1;
var xx;
importPackage(java.util);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools);
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";
var ����è ="#fUI/ChatBalloon/37/n#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
importPackage(Packages.tools.packet);
function start() {
    var Editing = false //false ��ʼ
    if (Editing) {
        cm.sendOk("ά����");
        cm.dispose();
        return;
    }
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendSimple("#r#e             "+С����+"   #e#d�� ��  �� ��#k#n   "+С����+"  #k#n\r\n  "+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n" +
                "#L2##r"+��ɫ��ͷ+"���Ⱦ���+��#l#k #L12##b"+zzz+"����#l#k\r\n" +
                "#L3##r"+��ɫ��ͷ+"���Ⱦ���+ħ#l#k #L13##b"+zzz+"����#l#k\r\n" +
                "#L4##r"+��ɫ��ͷ+"�еȾ���+��#l#k #L14##b"+zzz+"����#l#k\r\n" +
                "#L5##r"+��ɫ��ͷ+"�еȾ���+ħ#l#k #L15##b"+zzz+"����#l#k\r\n" +
                "#L6##r"+��ɫ��ͷ+"�ߵȾ���+��#l#k #L16##b"+zzz+"����#l#k\r\n" +
                "#L7##r"+��ɫ��ͷ+"�ߵȾ���+ħ#l#k #L17##b"+zzz+"����#l#k\r\n" +

                "\r\n");

    } else if (status == 1) {
		
		if(cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("��һ��û��װ��.");
            cm.dispose();
            return;
        }
        var xx = cm.getInventory(1).getItem(1).getOwner();
        var statup = new java.util.ArrayList();
        var itemId1 = cm.getInventory(1).getItem(1).getItemId();
        var item = cm.getInventory(1).getItem(1).copy();
        var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
        var sx0 = item.getStr();
        var sx1 = item.getDex();
        var sx2 = item.getInt();
        var sx3 = item.getLuk();
        var sx4 = item.getHp();
        var sx5 = item.getMp();
        var sx6 = item.getWatk();
        var sx7 = item.getMatk();
        var sx8 = item.getWdef();
        var sx9 = item.getMdef();
        var sx10= item.getAcc();
        var sx11= item.getAvoid();
        var sx12= item.getHands();
        var sx13= item.getSpeed();
        var sx14= item.getJump();
        if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
            cm.sendOk("��ʱװ����������.");
            cm.dispose();
            return;
        }
        if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
            cm.sendOk("�ֽ�װ���޷����ǡ�");
            cm.dispose();
            return;
        }
        if (Math.floor(item.getItemId()/10000) == 114 || Math.floor(item.getItemId()/10000) == 190 || Math.floor(item.getItemId()/10000) == 111 || Math.floor(item.getItemId()/10000) ==191) {
            cm.sendOk("��һ��װ����#v" + item.getItemId() + "#,�޷����ѵ�װ������!"); 		
            cm.dispose();    
            return;
        }
               if (selection == 2) {
                if(!cm.haveItem(4310029,8)){
                    cm.sendOk("#v4310029#��Ʒ��������8����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,5)){
                    cm.sendOk("#v2340000#��Ʒ��������5����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,500)){
                    cm.sendOk("#v4001126#��Ʒ��������500����");
                    cm.dispose();
                    return;
                }
                if (xx != "ǿ��"){
		    		cm.sendOk("��װ���޷����ѡ�");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-8);
                cm.gainItem(2340000,-5);
                cm.gainItem(4001126,-500);
                var max = 5;
                var min = 2
                var ��ά���� = Math.floor(Math.random()*(max-min+1)+min);
                var gmmax = 2;
                var gmmin = 1;
                var ��ħ = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 30){
                    item.setWatk(sx6+��ħ);//��
                    item.setMatk(sx7);//ħ
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("���Ⱦ���");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
                cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
                cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ����Ⱦ��ѣ����ף����(��)�ɣ���")
		    	cm.sendOk("���ѳɹ���");
		    	
                cm.dispose();
                return;
            } else if (selection == 3) {
                if(!cm.haveItem(4310029,8)){
                    cm.sendOk("#v4310029#��Ʒ��������8����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,5)){
                    cm.sendOk("#v2340000#��Ʒ��������5����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,500)){
                    cm.sendOk("#v4001126#��Ʒ��������500����");
                    cm.dispose();
                    return;
                }
                if (xx != "ǿ��"){
		    		cm.sendOk("��װ���޷����ѡ�");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-8);
                cm.gainItem(2340000,-5);
                cm.gainItem(4001126,-500);
                var max = 5;
                var min = 2
                var ��ά���� = Math.floor(Math.random()*(max-min+1)+min);
                var gmmax = 2;
                var gmmin = 1;
                var ��ħ = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 30){
                    item.setWatk(sx6);
                    item.setMatk(sx7+��ħ);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("���Ⱦ���");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("���ѳɹ���");
		    	cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ����Ⱦ��ѣ����ף����(��)�ɣ���")
                cm.dispose();
                return;
            } else if (selection == 4) {
                if(!cm.haveItem(4310029,10)){
                    cm.sendOk("#v4310029#��Ʒ��������10����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,10)){
                    cm.sendOk("#v2340000#��Ʒ��������10����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,1000)){
                    cm.sendOk("#v4001126#��Ʒ��������1000����");
                    cm.dispose();
                    return;
                }
                if (xx != "���Ⱦ���"){
		    		cm.sendOk("��װ��δ���Ⱦ��ѣ��޷��еȾ��ѡ�");
                    cm.dispose();
                    return;
                }

                cm.gainItem(4310029,-10);
                cm.gainItem(2340000,-10);
                cm.gainItem(4001126,-1000);
                var swmax = 6;
                var swmin = 3;
                var ��ά���� = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 2;
                var ��ħ = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 50){
                    item.setWatk(sx6+��ħ);
                    item.setMatk(sx7);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("�еȾ���");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("���ѳɹ���");
		    	cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ��еȾ��ѣ����ף����(��)�ɣ���")
                cm.dispose();
                return;
            } else if (selection == 5) {
                if(!cm.haveItem(4310029,10)){
                    cm.sendOk("#v4310029#��Ʒ��������10����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,10)){
                    cm.sendOk("#v2340000#��Ʒ��������10����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,1000)){
                    cm.sendOk("#v4001126#��Ʒ��������1000����");
                    cm.dispose();
                    return;
                }
                if (xx != "���Ⱦ���"){
		    		cm.sendOk("��װ��δ���Ⱦ��ѣ��޷��еȾ��ѡ�");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-10);
                cm.gainItem(2340000,-10);
                cm.gainItem(4001126,-1000);
                var swmax = 6;
                var swmin = 3;
                var ��ά���� = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 2;
                var ��ħ = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 50){
                    item.setWatk(sx6);
                    item.setMatk(sx7+��ħ);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("�еȾ���");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("���ѳɹ���");
		    	cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ��еȾ��ѣ����ף����(��)�ɣ���")
                cm.dispose();
                return;
            } else if (selection == 6) {
                if(!cm.haveItem(4310029,12)){
                    cm.sendOk("#v4310029#��Ʒ��������12����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,12)){
                    cm.sendOk("#v2340000#��Ʒ��������12����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,2000)){
                    cm.sendOk("#v4001126#��Ʒ��������2000����");
                    cm.dispose();
                    return;
                }
                if (xx != "�еȾ���"){
		    		cm.sendOk("��װ��δ�еȾ��ѣ��޷��ߵȾ��ѡ�");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-12);
                cm.gainItem(2340000,-12);
                cm.gainItem(4001126,-2000);
                var swmax = 7;
                var swmin = 4;
                var ��ά���� = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 3;
                var ��ħ = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 10){
                    item.setWatk(sx6+��ħ);
                    item.setMatk(sx7);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("�ߵȾ���");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("���ѳɹ���");
		    	cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ��ߵȾ��ѣ����ף����(��)�ɣ���")
                cm.dispose();
                return;
            } else if (selection == 7) {
                if(!cm.haveItem(4310029,12)){
                    cm.sendOk("#v4310029#��Ʒ��������12����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,12)){
                    cm.sendOk("#v2340000#��Ʒ��������12����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,2000)){
                    cm.sendOk("#v4001126#��Ʒ��������2000����");
                    cm.dispose();
                    return;
                }
                if (xx != "�еȾ���"){
		    		cm.sendOk("��װ��δ�еȾ��ѣ��޷��ߵȾ��ѡ�");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-12);
                cm.gainItem(2340000,-12);
                cm.gainItem(4001126,-2000);
                var swmax = 7;
                var swmin = 4;
                var ��ά���� = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 3;
                var ��ħ = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 10){
                    item.setWatk(sx6);
                    item.setMatk(sx7+��ħ);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("�ߵȾ���");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("���ѳɹ���");
		    	cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ��ߵȾ��ѣ����ף����(��)�ɣ���")
                cm.dispose();
                return;
            }  
			else if (selection == 12) {//���Ⱦ���+��
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� һ���������0-2\r\n#r\n��Ҫ��Ʒ��\r\n#k[#v4310029##r#c4310029##k/8][#v2340000##r#c2340000##k/5][#v4001126##r#c4001126##k/500]\r\n\r\n");
                cm.dispose();
                return;
            }
			
			else if (selection == 13) {//���Ⱦ���+ħ
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\nħ һ���������0-2\r\n#r\n��Ҫ��Ʒ��\r\n#k[#v4310029##r#c4310029##k/8][#v2340000##r#c2340000##k/5][#v4001126##r#c4001126##k/500]\r\n\r\n");
                cm.dispose();
                return;
            }
			
			else if (selection == 14) {//�еȾ���+��
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�3-6\r\n#r\r\n�� һ���������0-5\r\n#r\n��Ҫ��Ʒ����\r\n#k[#v4310029##r#c4310029##k/10][#v2340000##r#c2340000##k/10][#v4001126##r#c4001126##k/1000]\r\n\r\n");
                cm.dispose();
                return;
            } 
			
			else if (selection == 15) {//�еȾ���+ħ
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�3-6\r\n#r\r\nħ һ���������0-5\r\n#r\n��Ҫ��Ʒ����\r\n#k[#v4310029##r#c4310029##k/10][#v2340000##r#c2340000##k/10][#v4001126##r#c4001126##k/1000]\r\n\r\n");
                cm.dispose();
                return;
            } 
			
			else if (selection == 16) {//�ߵȾ���+��
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�4-7\r\n#r\r\n�� �߸������0-5\r\n#r\n��Ҫ��Ʒ����\r\n#k[#v4310029##r#c4310029##k/12][#v2340000##r#c2340000##k/12][#v4001126##r#c4001126##k/2000]\r\n\r\n");
                cm.dispose();
                return;
            }

			else if (selection == 17) {//�ߵȾ���+ħ
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�4-7\r\n#r\r\nħ �߸������0-5\r\n#r\n��Ҫ��Ʒ����\r\n#k[#v4310029##r#c4310029##k/12][#v2340000##r#c2340000##k/12][#v4001126##r#c4001126##k/2000]\r\n\r\n");
                cm.dispose();
                return;
            }

			else if (selection == 100) {
                cm.dispose();
                cm.openNpc(NPCID_1);
                return;
            } else if (selection == 101) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
        } else {
            cm.dispose();
            return;
        }

    }
}

function openNpc(npcid) {
    openNpc(npcid, null);
}

function openNpc(npcid, script) {
    var mapid = cm.getMapId();
    cm.dispose();
    if (cm.getPlayerStat("LVL") < 10) {
        cm.sendOk("��ĵȼ�����С��10��.");
    } else if (
            cm.hasSquadByMap() ||
            cm.hasEventInstance() ||
            cm.hasEMByMap() ||
            mapid >= 990000000 ||
            (mapid >= 680000210 && mapid <= 680000502) ||
            (mapid / 1000 === 980000 && mapid !== 980000000) ||
            mapid / 100 === 1030008 ||
            mapid / 100 === 922010 ||
            mapid / 10 === 13003000
            ) {
        cm.sendOk("�㲻��������ʹ���������.");
    } else {
        if (script == null) {
            cm.openNpc(npcid);
        } else {
            cm.openNpc(npcid, script);
        }
    }
}