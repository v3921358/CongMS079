
/* ==================
 �ű�����:  ˫������ȡNPC	    
 �ű����ߣ� ��Ҷ
 ��ϵ��ʽQQ�� 1848350048
 =====================
 */
var xx;//
var xxdj;//
var sj1;//�ɹ���
var sjsx=Math.floor(Math.random()*7)-2;
var sjsx2=Math.floor(Math.random()*10)-2;
var xuqiushuliang;
var status = 0;


var imtenuber=-1;

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
			cm.sendNext("\t"+�ʺ�+"  #e#d �� �� �� �� ǿ ��#k#n  #r  "+�ʺ�+"#b#k#n\r\r\n  "+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n   #b��ӭ����#e" + cm.getChannelServer().getServerName() + "ʱװǿ������\r\n#k#n1.�����������ʹ�õ�����ǿ���κ�ʱװ!\r\n2.��Ҫǿ����ʱװ����������+10�������װ������һ����!#k\r\n");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			
			if((cm.getInventory(1).getItem(1)!= null )||imtenuber>0){
			 cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc.getItemId()+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
			} else{
			cm.sendOk("#b��һ�����޶������ߵ�һ����Ʒ���ǿ�ǿ��ʱװ#k");	
			cm.dispose();
			} 	
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			if(cm.getInventory(1).getItem(1)== null ){
		            cm.sendOk("���Ҫǿ����ʱװ���ڵ�һ����ܽ���.");
				    cm.dispose();
			}
             
			else if(MapleItemInformationProvider.getInstance().getReqLevel(cm.getInventory(1).getItem(1).getItemId()) < 0) {
				cm.sendOk("���װ���ȼ�û�г���0��");
				cm.dispose();
				return;
			}/*else if (Packages.server.MapleItemInformationProvider.getInstance().isCash(cm.getInventory(1).getItem(1).getItemId()) != true) {//����ж����߲���ʱװ
            cm.sendOk("����ʱװ���޷�ǿ����");
            cm.dispose();
            return;
            }*/
			/*else if (Packages.server.MapleItemInformationProvider.getInstance().isCash(cm.getInventory(1).getItem(1).getItemId()) == true) {//����ж���ʱװ
            cm.sendOk("����ʱװ���޷�ǿ����");
            cm.dispose();
            return;
            }*/
			
            
			else if (xx == "�����20"){
				cm.sendOk("�ף���ϲ���Ѿ�ǿ��������Ǽ���������20�����Ѿ�û��������Ϊ��������Ŷ��");
				cm.dispose();
			}else if(xx == "������+10"){
				sj = Math.floor(Math.random()*99);//���100%
				shibai = 0;
				xxnew = "�����11";
				������� = 200000;
				��ά���� = 6;
				��ħ = 6;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����99%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "�����11"){
				sj = Math.floor(Math.random()*80);//���80%
				shibai = 0;
				xxnew = "�����12";
				xxdj = 1;
				������� = 200000;
				��ά���� = 6;
				��ħ = 6;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����80%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "�����12"){
				sj = Math.floor(Math.random()*70);//���60%
				shibai = 0;
				xxnew = "�����13";
				xxdj = 2;
				������� = 200000;
				��ά���� = 6;
				��ħ = 6;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����70%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "�����13"){
				sj = Math.floor(Math.random()*60);//���40%
				shibai = 0;
				xxnew = "�����14";
				xxdj = 3;
				������� = 300000;
				��ά���� = 7;
				��ħ = 7;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����60%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "�����14"){
				sj = Math.floor(Math.random()*50);//���20%
				shibai = 0;
				xxnew = "�����15";
				xxdj = 4;
				������� = 300000;
				��ά���� = 7;
				��ħ = 7;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����50%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "�����15"){
				sj = Math.floor(Math.random()*40);//100%
				shibai = 0;
				xxnew = "�����16";
				xxdj = 5;
				������� = 300000;
				��ά���� = 7;
				��ħ = 7;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����40%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "�����16"){
				sj = Math.floor(Math.random()*30);
				shibai = 0;
				xxnew = "�����17";
				xxdj = 6;
				������� = 400000;
				��ά���� = 8;
				��ħ = 8;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����30%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "�����17"){
				sj = Math.floor(Math.random()*20);
				shibai = 0;
				xxnew = "�����18";
				xxdj = 7;
				������� = 500000;
				��ά���� = 10;
				��ħ = 10;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����20%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "�����18"){
				sj = Math.floor(Math.random()*16);
				shibai = 0;
				xxnew = "�����19";
				xxdj = 8;
				������� = 500000;
				��ά���� = 10;
				��ħ = 10;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����16%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "�����19"){
				sj = Math.floor(Math.random()*13);
				shibai = 0;
				xxnew = "�����20";
				xxdj = 9;
				������� = 800000;
				��ά���� = 20;
				��ħ = 20;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����13%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}
				else
				{
					cm.sendOk("��������+10���޷�����ǿ��Ӵ");
					cm.dispose();
					return;
				}
		
		}else if (status == 3) {
		         
			
			if (cm.getPlayer().getCSPoints(1) < �������){//�ж϶��ٵ��
				cm.sendOk("�����"+�������+"��");
				cm.dispose();
				return;
				}  else if(sj <= 10){//����ɹ�
			cm.gainNX(-�������);//�۳����ٵ��
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Failure/0"); //ʧ��
			cm.sendOk("ǿ��ʧ�ܣ�");
			cm.dispose();
			} else {//���ʧ��
			cm.gainNX(-�������);//�۳����ٵ��
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
			item.setHp(sx4);
			item.setMp(sx5);
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
			cm.sendOk("ǿ���ɹ���");
			cm.ȫ����ɫ����(  " ����ǿ����" + " : " + "["+cm.getPlayer().getName()+"]�ɹ�ǿ����"+xxnew+"�����ף����(��)�ɣ���")
			cm.dispose();
				}
			}
	}}
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ����è ="#fUI/ChatBalloon/37/n#";
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
