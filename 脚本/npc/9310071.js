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
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";
var ����è ="#fUI/ChatBalloon/37/n#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
importPackage(Packages.server);
importPackage(Packages.tools);
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
                "#L0##r"+��ɫ��ͷ+"װ��ͻ��#l#k #L10##b"+zzz+"���ܣ�װ�����׸�Ҫ��װ���ſ��Ե㣩#l#k\r\n" +
                "#L1##r"+��ɫ��ͷ+"װ��ǿ��#l#k #L11##b"+zzz+"���ܣ�װ�����׸�Ҫ��װ���ſ��Ե㣩#l#k\r\n" +
                "#L100##r"+��ɫ��ͷ+"װ������#l#k #L101##b"+zzz+"���ܣ�װ�����׸�Ҫ��װ���ſ��Ե㣩#l#k\r\n\r\n"+
				"#L109##d                 "+С����+"װ������"+С����+"#l#k \r\n" 
			
				);
            
    } else if (status == 1) {
		if (selection == 109) {
                cm.dispose();
                cm.openNpc(9310071, 109);
                return;
            }
	    if (selection == 100) {
                cm.dispose();
                cm.openNpc(9310071, 1);
                return;
            } 
		
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
        if (cm.getInventory(1).getItem(1).getUniqueId() !=-1) {
            cm.sendOk("�ֽ�װ���޷����ǡ�");
            cm.dispose();
            return;
        }
        if (Math.floor(item.getItemId()/10000) == 114 || Math.floor(item.getItemId()/10000) == 190 || Math.floor(item.getItemId()/10000) == 111 || Math.floor(item.getItemId()/10000) ==191) {
            cm.sendOk("��һ��װ����#v" + item.getItemId() + "#,�޷����ѵ�װ������!"); 		
            cm.dispose();    
            return;
        }if (Math.floor(item.getItemId()/10000) == 113 ) {
            cm.sendOk("��һ��װ��#v" + item.getItemId() + "#,���޷����ѵ�װ�����ͣ����ǿ���������ҳ����Ʒ��������!"); 		
            cm.dispose();    
            return;
        }if (Math.floor(item.getItemId()/10000) == 101 ) {
            cm.sendOk("��һ��װ��#v" + item.getItemId() + "#,���޷����ѵ�װ�����ͣ����ǿ���������ҳ����Ʒ��������!"); 		
            cm.dispose();    
            return;
        }
            if (selection == 0) {
                if(!cm.haveItem(4310034,1)){
                    cm.sendOk("#v4310034#��Ʒ��������1����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,200)){
                    cm.sendOk("#v4001126#��Ʒ��������200����");
                    cm.dispose();
                    return;
                }
                if (xx != ""){
		    		cm.sendOk("��װ���޷�ͻ��");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310034,-1);
                cm.gainItem(4001126,-200);
                var ��ά���� = 1;
                var ��ħ = 1;
		    	item.setFlag(1);//0Ϊû������1Ϊ����
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                    if(RandomChance <= 60){
                        item.setWatk(sx6+��ħ);
		    	        item.setMatk(sx7+��ħ);
                    }
		    	item.setWatk(sx6+��ħ);
		    	item.setMatk(sx7+��ħ);
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("ͻ��");				
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("ͻ�Ƴɹ���");
				cm.worldMessage(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ�ͻ�ƣ����ף����(��)�ɣ���")
                cm.dispose();
                return;
            } else if (selection == 1) {
                if(!cm.haveItem(4310034,5)){
                    cm.sendOk("#v4310034#��Ʒ��������5����");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,400)){
                    cm.sendOk("#v4001126#��Ʒ��������400����");
                    cm.dispose();
                    return;
                }
                if (xx != "ͻ��"){
		    		cm.sendOk("����ͻ�ơ�");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310034,-5);
                cm.gainItem(4001126,-400);
                var ��ά���� = Math.floor(Math.random()*6);//����ȡ��   ����0-5֮�������   Math.random()Ϊ0-1�������
                var ��ħ = Math.round(Math.random()*3);//��������ȡ��   ȡ0-3���������
		    	item.setFlag(1);
		    	item.setStr(sx0+��ά����);
		    	item.setDex(sx1+��ά����);
		    	item.setInt(sx2+��ά����);
		    	item.setLuk(sx3+��ά����);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 30){
                    item.setWatk(sx6+��ħ);
                    item.setMatk(sx7+��ħ);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("ǿ��");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
		    	cm.sendOk("ǿ���ɹ���");
				cm.worldMessage(  " ���������ġ�" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ�ǿ������")
                cm.dispose();
                return;
            }  else if (selection == 10) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�1\r\n#r\r\n��ħ �ٷְٸ������0-2\r\n#r\n��Ҫ��Ʒ��\r\n#v4310034#*1#v4001126#*200\r\n");
                cm.dispose();
                return;
            }//ͻ�ƽ���

			else if (selection == 11) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά�������0-6\r\n#r\r\n��ħ �ٷְٸ������0-3\r\n#r\n��Ҫ��Ʒ��\r\n#v4310034#*5#v4001126#*400\r\n");
                cm.dispose();
                return;
            }//ǿ������

			else if (selection == 12) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 13) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 14) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 15) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 16) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 17) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\nȫ����ά������ؼ�2-5\r\n#r\r\n�� �ٷ�10�������1~2���ٷ�90���ʲ���ӡ�\r\n#r\n��Ҫ��Ʒ��ʮ�ֱ�X1�µ����ˮ��X1ף������X1�������X1��ҶX200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 18) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\n\r\n#r\r\nװ�����������������ɱ�Ϊ�ܽ���\r\n���ɽ��ף��������̳ǹ�����������ʹ�ú�ɱ�Ϊ�ܽ���\r\n���ɽ�����Ϊ�������ò��ܽ����ͽ���\r\n\r\n");
                cm.dispose();
                return;
            }
			
			else if (selection == 101) {
                cm.sendOk("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ�ȼ�Ϊ��#r"+xx+"#k\r\n\r\n\r\n#rͻ�ƺ�ǿ�����װ�������Խ��о���Ŷ\r\n���ѷ�Ϊ�����У��ߵȾ���\r\n���ѳ��ȵ�װ���ſ��Բ����еȾ��ѣ��Դ�����\r\n\r\n");
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