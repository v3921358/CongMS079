
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ����è ="#fUI/ChatBalloon/37/n#";
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";
var xx;//
var xxdj;//
var sj1;//�ɹ���
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var xuqiushuliang;
var status = 0;

importPackage(java.util);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
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
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.sendNext("#r#e             "+С����+"   #e#dѫ ��  ǿ ��#k#n   "+С����+"  #k#n\r\n  "+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n   #b��ӭ����#e" + cm.getChannelServer().getServerName() + "ѫ��ǿ������\r\n#k#n1.�����������ʹ����Ʒ����ѫ��ǿ��!\r\n2.��Ҫ���ǵ�ѫ�������װ������һ����!#k\r\n");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			if(cm.getInventory(1).getItem(1)!= null ){
			 cm.sendYesNo("��Ҫ���ǵ�װ��Ϊ:\r\n\r\n#v"+cc.getItemId()+"#\r\n\r\n#r#eȷ��Ҫ��ʼ������?");
			} else{
			cm.sendOk("#b��һ�����޶�����#k");	
			cm.dispose();
			} 
			
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			if(cm.getInventory(1).getItem(1)== null ){
		            cm.sendOk("���Ҫ���ǵ�װ�����ڵ�һ����ܽ���.");
				    cm.dispose();
			}
             else if (Math.floor(item.getItemId()/10000) != 114 ) {
             cm.sendOk("��һ��װ����#v" + item.getItemId() + "#,����ѫ��!");                                   cm.dispose();    
            }
			else if(item.getItemId()==1142803)
			{
				cm.sendOk("��װ���޷�ǿ��!");
				cm.dispose();
				return;
			}
			else if(MapleItemInformationProvider.getInstance().getReqLevel(cm.getInventory(1).getItem(1).getItemId()) < 0) {
				cm.sendOk("���װ���ȼ�û�г���0��");
				cm.dispose();
				return;
			} else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("�ֽ�װ���޷����ǡ�");
                    cm.dispose();
			}else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("�ֽ�װ���޷����ǡ�");
                    cm.dispose();
			}
			else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ����������.");
				cm.dispose();
				return;
			}else if (xx == "������"){
				cm.sendOk("���ֻ��ǿ��5��");
				cm.dispose();
				return;
			}else if(xx == ""){
				sj = Math.floor(Math.random()*100);//���100%
				shibai = 0;
				xxnew = "��";
				��֮��½Ǯ�� = 4310148;
				��֮��½Ǯ������ = 1;
				����� = 4310034;
				��������� = 1;
				ʮ�ֱ� = 4310029;
				ʮ�ֱ����� = 1;
				��Ҷ = 4001126;
				��Ҷ���� = 200;
				������� = 100000;
				��ά���� = 1;
				��ħ = 1;
				����ֵ = 50;
				ħ��ֵ = 50;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����100%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \tHP/MP#r+"+����ֵ+"\r\n#k����Ҫ������Ʒ\r\n#r"+��֮��½Ǯ������+"��#v"+��֮��½Ǯ��+"#\r\n#r"+���������+"��#v"+�����+"#\r\n#r"+ʮ�ֱ�����+"��#v"+ʮ�ֱ�+"#\r\n#r"+��Ҷ����+"��#v"+��Ҷ+"#\r\n���:"+�������+"\r\n#k������һ��ʹ����֮����������\r\n\r\n");
			}else if (xx == "��"){
				sj = Math.floor(Math.random()*80);//���80%
				shibai = 0;
				xxnew = "���";
				xxdj = 1;
				��֮��½Ǯ�� = 4310148;
				��֮��½Ǯ������ = 2;
				����� = 4310034;
				��������� = 1;
				ʮ�ֱ� = 4310029;
				ʮ�ֱ����� = 1;
				��Ҷ = 4001126;
				��Ҷ���� = 400;
				������� = 500000;
				��ά���� = 2;
				��ħ = 1;
				����ֵ = 100;
				ħ��ֵ = 100;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����80%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \tHP/MP#r+"+����ֵ+"\r\n#k����Ҫ������Ʒ\r\n#r"+��֮��½Ǯ������+"��#v"+��֮��½Ǯ��+"#\r\n#r"+���������+"��#v"+�����+"#\r\n#r"+ʮ�ֱ�����+"��#v"+ʮ�ֱ�+"#\r\n#r"+��Ҷ����+"��#v"+��Ҷ+"#\r\n���:"+�������+"\r\n#k������һ��ʹ����֮����������\r\n\r\n");
			}else if (xx == "���"){
				sj = Math.floor(Math.random()*60);//���60%
				shibai = 0;
				xxnew = "����";
				xxdj = 2;
				��֮��½Ǯ�� = 4310148;
				��֮��½Ǯ������ = 3;
				����� = 4310034;
				��������� = 1;
				ʮ�ֱ� = 4310029;
				ʮ�ֱ����� = 1;
				��Ҷ = 4001126;
				��Ҷ���� = 600;
				������� = 1000000;
				��ά���� = 3;
				��ħ = 2;
				����ֵ = 200;
				ħ��ֵ = 200;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����60%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \tHP/MP#r+"+����ֵ+"\r\n#k����Ҫ������Ʒ\r\n#r"+��֮��½Ǯ������+"��#v"+��֮��½Ǯ��+"#\r\n#r"+���������+"��#v"+�����+"#\r\n#r"+ʮ�ֱ�����+"��#v"+ʮ�ֱ�+"#\r\n#r"+��Ҷ����+"��#v"+��Ҷ+"#\r\n���:"+�������+"\r\n#k������һ��ʹ����֮����������\r\n\r\n");
			}else if (xx == "����"){
				sj = Math.floor(Math.random()*40);//���40%
				shibai = 0;
				xxnew = "�����";
				xxdj = 3;
				��֮��½Ǯ�� = 4310148;
				��֮��½Ǯ������ = 4;
				����� = 4310034;
				��������� = 1;
				ʮ�ֱ� = 4310029;
				ʮ�ֱ����� = 1;
				��Ҷ = 4001126;
				��Ҷ���� = 800;
				������� = 1000000;
				��ά���� = 4;
				��ħ = 3;
				����ֵ = 300;
				ħ��ֵ = 300;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����40%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \tHP/MP#r+"+����ֵ+"\r\n#k����Ҫ������Ʒ\r\n#r"+��֮��½Ǯ������+"��#v"+��֮��½Ǯ��+"#\r\n#r"+���������+"��#v"+�����+"#\r\n#r"+ʮ�ֱ�����+"��#v"+ʮ�ֱ�+"#\r\n#r"+��Ҷ����+"��#v"+��Ҷ+"#\r\n���:"+�������+"\r\n#k������һ��ʹ����֮����������\r\n\r\n");
			}else if (xx == "�����"){
				sj = Math.floor(Math.random()*20);//���20%
				shibai = 0;
				xxnew = "������";
				xxdj = 4;
				��֮��½Ǯ�� = 4310148;
				��֮��½Ǯ������ = 5;
				����� = 4310034;
				��������� = 1;
				ʮ�ֱ� = 4310029;
				ʮ�ֱ����� = 1;
				��Ҷ = 4001126;
				��Ҷ���� = 800;
				������� = 1000000;
				��ά���� = 5;
				��ħ = 4;
				����ֵ = 400;
				ħ��ֵ = 400;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����20%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \tHP/MP#r+"+����ֵ+"\r\n#k����Ҫ������Ʒ\r\n#r"+��֮��½Ǯ������+"��#v"+��֮��½Ǯ��+"#\r\n#r"+���������+"��#v"+�����+"#\r\n#r"+ʮ�ֱ�����+"��#v"+ʮ�ֱ�+"#\r\n#r"+��Ҷ����+"��#v"+��Ҷ+"#\r\n���:"+�������+"\r\n#k������һ��ʹ����֮����������\r\n\r\n");
			}/*else if (xx == "������"){
				sj = Math.floor(100);//100%
				shibai = 0;
				xxnew = "�������";
				xxdj = 5;
				��֮��½Ǯ�� = 4310148;
				��֮��½Ǯ������ = 6;
				����� = 4310148;
				��������� = 6;
				ʮ�ֱ� = 4000463;
				ʮ�ֱ����� = 50;
				������� = 10000000;
				��ά���� = 10;
				��ħ = 5;
				����ֵ = 500;
				ħ��ֵ = 500;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����80%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \tHP/MP#r+"+����ֵ+"\r\n#k����Ҫ������Ʒ\r\n#r"+��֮��½Ǯ������+"��#v"+��֮��½Ǯ��+"#\r\n#r"+���������+"��#v"+�����+"#\r\n#r"+ʮ�ֱ�����+"��#v"+ʮ�ֱ�+"#\r\n#r"+��Ҷ����+"��#v"+��Ҷ+"#\r\n���:"+�������+"\r\n#k������һ��ʹ����֮����������\r\n\r\n");
				}*/
		} else if (status == 3) {
		       if(!cm.haveItem(��֮��½Ǯ��,��֮��½Ǯ������)){
				cm.sendOk("#v"+��֮��½Ǯ��+"#��Ʒ��������"+��֮��½Ǯ������+"����");
				cm.dispose();
				return;
			} else if(!cm.haveItem(�����,���������)){
				cm.sendOk("#v"+�����+"#��Ʒ��������"+���������+"����");
				cm.dispose();
				return;
				} else if(!cm.haveItem(ʮ�ֱ�,ʮ�ֱ�����)){
				cm.sendOk("#v"+ʮ�ֱ�+"#��Ʒ��������"+ʮ�ֱ�����+"����");
				cm.dispose();
				return;
			} 
			else if(!cm.haveItem(��Ҷ,��Ҷ����)){
				cm.sendOk("#v"+��Ҷ+"#��Ʒ��������"+��Ҷ����+"����");
				cm.dispose();
				return;
			}
			else if (cm.getMeso() < �������){//�ж϶��ٽ��
				cm.sendOk("��Ҳ���"+�������+"��");
				cm.dispose();
				return;
				} else if(sj <= 10){//����ɹ�
			cm.gainMeso(-�������);//�۳����ٽ��
			cm.gainItem(��֮��½Ǯ��,-��֮��½Ǯ������);
			cm.gainItem(�����,-���������);
			cm.gainItem(ʮ�ֱ�,-ʮ�ֱ�����);
			cm.gainItem(��Ҷ,-��Ҷ����);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Failure/0"); //ʧ��
			cm.sendOk("����ʧ�ܣ�");
			cm.dispose();
			} else {//���ʧ��
			cm.gainMeso(-�������);//�۳����ٽ��
			cm.gainItem(��֮��½Ǯ��,-��֮��½Ǯ������);
			cm.gainItem(�����,-���������);
			cm.gainItem(ʮ�ֱ�,-ʮ�ֱ�����);
			cm.gainItem(��Ҷ,-��Ҷ����);
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = Packages.server.MapleItemInformationProvider.getInstance();
			var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
			var sx0 = item.getStr();//��ȡװ����ǰ����0
			var sx1 = item.getDex();//��ȡװ����ǰ����1
			var sx2 = item.getInt();//��ȡװ����ǰ����2
			var sx3 = item.getLuk();//��ȡװ����ǰ����3
			var sx4 = item.getHp();//��ȡװ����ǰHP4
			var sx5 = item.getMp();//��ȡװ����ǰMP5
			var sx6 = item.getWatk();//��ȡװ����ǰ�﹥6
			var sx7 = item.getMatk();//��ȡװ����ǰħ��7
			var sx8 = item.getWdef();//��ȡװ����ǰ���8
			var sx9 = item.getMdef();//��ȡװ����ǰħ��9
			var sx10= item.getAcc();//��ȡװ����ǰ����10
			var sx11= item.getAvoid();//��ȡװ����ǰ�ر�11
			var sx12= item.getHands();//��ȡװ����ǰ�ּ�12
			var sx13= item.getSpeed();//��ȡװ����ǰ�ƶ��ٶ�13
			var sx14= item.getJump();//��ȡװ����ǰ��Ծ��14
			item.setFlag(1);//����
			item.setStr(sx0+��ά����);
			item.setDex(sx1+��ά����);
			item.setInt(sx2+��ά����);
			item.setLuk(sx3+��ά����);
			item.setHp(sx4+����ֵ);
			item.setMp(sx5+ħ��ֵ);
			item.setWatk(sx6+��ħ);
			item.setMatk(sx7+��ħ);
			item.setWdef(sx8);
			item.setMdef(sx9);
			item.setAcc(sx10);
			item.setAvoid(sx11);
			item.setHands(sx12);
			item.setSpeed(sx13);
			item.setJump(sx14);
			item.setOwner(xxnew);
			cm.dispose();
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
			cm.sendOk("���ǳɹ���");
			cm.ȫ����ɫ����(  " �����ǹ��桿" + " : " + "["+cm.getPlayer().getName()+"]ĳ��װ���ɹ�������֮����"+xxnew+"�����ף����(��)�ɣ���")
			cm.dispose();
				}
			}
	}}
