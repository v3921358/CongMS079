
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
var װ������=1902002;
var ǿ��װ��=Array(
1902002,
1912000,
1912406,
1902406,
1912404,
1902404,
1912403,
1902403,
1912412,
1902412,
1912413,
1902413,
1912416,
1902416,
1912417,
1902417,
1912420,
1902420

);
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
			cm.sendNext("\t"+�ʺ�+"  #e#d Ԫ �� �� �� �� ��#k#n  #r  "+�ʺ�+"#b#k#n\r\r\n  "+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n   #b��ӭ����#e" + cm.getChannelServer().getServerName() + "������������\r\n#k#n1.�����������ʹ�õ���������!\r\n2.��Ҫ���ǵ�Ԫ�����������װ������һ����!#k\r\n");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			for(var inumber=0;inumber<ǿ��װ��.length;inumber++)
			{   
		        if(cm.getInventory(1).getItem(1) == null) {
                cm.sendOk("��һ��û��װ��.");
                cm.dispose();
                return;
                }
				if(cc.getItemId()==ǿ��װ��[inumber])
				{
					imtenuber=cc.getItemId();
					break;
				}
			}
			if((cm.getInventory(1).getItem(1)!= null )||imtenuber>0){
			 cm.sendYesNo("��Ҫ���ǵ�װ��Ϊ:\r\n\r\n#v"+cc.getItemId()+"#\r\n\r\n#r#eȷ��Ҫ��ʼ������?");
			} else{
			cm.sendOk("#b��һ�����޶������ߵ�һ����Ʒ���ǿ�����Ԫ������#k");	
			cm.dispose();
			} 	
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			if(cm.getInventory(1).getItem(1)== null ){
		            cm.sendOk("���Ҫ���ǵ�Ԫ��������ڵ�һ����ܽ���.");
				    cm.dispose();
			}
             else if (imtenuber<0 ) {
             cm.sendOk("��һ��װ��#v" + item.getItemId() + "#,���ǿ����ǵ�Ԫ������!");
			 cm.dispose();    
			 return;
            }
			else if(MapleItemInformationProvider.getInstance().getReqLevel(cm.getInventory(1).getItem(1).getItemId()) < 0) {
				cm.sendOk("���װ���ȼ�û�г���0��");
				cm.dispose();
				return;
				} /*else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("�ֽ�װ���޷����ǡ�");
                    cm.dispose();
			}*/ else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ����������.");
				cm.dispose();
				return;
			}else if (xx == "������+10"){
				cm.sendOk("�ף���ϲ���Ѿ���������Ǽ�������+10�����Ѿ�û��������Ϊ��������Ŷ��");
				cm.dispose();
			}else if(xx == ""){
				sj = Math.floor(Math.random()*99);//���100%
				shibai = 0;
				xxnew = "��";
				������� = 1000;
				��ά���� = 1;
				��ħ = 1;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����99%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "��"){
				sj = Math.floor(Math.random()*80);
				shibai = 0;
				xxnew = "���";
				xxdj = 1;
				������� = 2000;
				��ά���� = 1;
				��ħ = 1;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����80%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "���"){
				sj = Math.floor(Math.random()*70);
				shibai = 0;
				xxnew = "����";
				xxdj = 2;
				������� = 3000;
				��ά���� = 1;
				��ħ = 1;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����70%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "����"){
				sj = Math.floor(Math.random()*60);
				shibai = 0;
				xxnew = "�����";
				xxdj = 3;
				������� = 4000;
				��ά���� = 1;
				��ħ = 1;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����60%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "�����"){
				sj = Math.floor(Math.random()*50);
				shibai = 0;
				xxnew = "������";
				xxdj = 4;
				������� = 5000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����50%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "������"){
				sj = Math.floor(Math.random()*40);
				shibai = 0;
				xxnew = "������";
				xxdj = 5;
				������� = 6000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����40%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������"){
				sj = Math.floor(Math.random()*30);
				shibai = 0;
				xxnew = "������";
				xxdj = 6;
				������� = 7000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����30%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������"){
				sj = Math.floor(Math.random()*20);
				shibai = 0;
				xxnew = "������";
				xxdj = 7;
				������� = 8000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����20%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������"){
				sj = Math.floor(Math.random()*16);
				shibai = 0;
				xxnew = "������";
				xxdj = 8;
				������� = 10000;
				��ά���� = 5;
				��ħ = 5;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����16%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������"){
				sj = Math.floor(Math.random()*13);
				shibai = 0;
				xxnew = "������";
				xxdj = 9;
				������� = 10000;
				��ά���� = 5;
				��ħ = 5;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����13%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if(xx == "������"){
				sj = Math.floor(Math.random()*99);//���100%
				shibai = 0;
				xxnew = "������+1";
				������� = 2000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����99%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "������+1"){
				sj = Math.floor(Math.random()*80);//���80%
				shibai = 0;
				xxnew = "������+2";
				xxdj = 1;
				������� = 3000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����80%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "������+2"){
				sj = Math.floor(Math.random()*70);//���60%
				shibai = 0;
				xxnew = "������+3";
				xxdj = 2;
				������� = 4000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����70%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "������+3"){
				sj = Math.floor(Math.random()*60);//���40%
				shibai = 0;
				xxnew = "������+4";
				xxdj = 3;
				������� = 5000;
				��ά���� = 2;
				��ħ = 2;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����60%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "������+4"){
				sj = Math.floor(Math.random()*50);//���20%
				shibai = 0;
				xxnew = "������+5";
				xxdj = 4;
				������� = 7000;
				��ά���� = 3;
				��ħ = 3;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����50%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
			}else if (xx == "������+5"){
				sj = Math.floor(Math.random()*40);//100%
				shibai = 0;
				xxnew = "������+6";
				xxdj = 5;
				������� = 8000;
				��ά���� = 3;
				��ħ = 3;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����40%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������+6"){
				sj = Math.floor(Math.random()*30);
				shibai = 0;
				xxnew = "������+7";
				xxdj = 6;
				������� = 9000;
				��ά���� = 3;
				��ħ = 3;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����30%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������+7"){
				sj = Math.floor(Math.random()*20);
				shibai = 0;
				xxnew = "������+8";
				xxdj = 7;
				������� = 10000;
				��ά���� = 3;
				��ħ = 3;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����20%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������+8"){
				sj = Math.floor(Math.random()*16);
				shibai = 0;
				xxnew = "������+9";
				xxdj = 8;
				������� = 20000;
				��ά���� = 6;
				��ħ = 6;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����16%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}else if (xx == "������+9"){
				sj = Math.floor(Math.random()*13);
				shibai = 0;
				xxnew = "������+10";
				xxdj = 9;
				������� = 20000;
				��ά���� = 6;
				��ħ = 6;
				cm.sendNext("��ѡ���װ���ǣ�#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n��ǰ��֮���ȼ�Ϊ��#r"+xx+"#k\r\n���ǳɹ�����13%\r\n#r��ά����:#r+"+��ά����+"\tħ/��#r+"+��ħ+" \r\n#k����Ҫ������Ʒ��\r\n#r���:"+�������+"\r\n#k������һ����������\r\n\r\n");
				}
				else
				{
					cm.sendOk("1");
					cm.dispose();
					return;
				}
		} else if (status == 3) {
		         
			
			if (cm.getPlayer().getCSPoints(1) < �������){//�ж϶��ٵ��
				cm.sendOk("�����"+�������+"��");
				cm.dispose();
				return;
				}  else if(sj <= 10){//����ɹ�
			cm.gainNX(-�������);//�۳����ٵ��
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Failure/0"); //ʧ��
			cm.sendOk("����ʧ�ܣ�");
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
			cm.sendOk("���ǳɹ���");
			cm.ȫ����ɫ����(  " ��Ԫ���������ǡ�" + " : " + "["+cm.getPlayer().getName()+"]Ԫ������ɹ�������֮����"+xxnew+"�����ף����(��)�ɣ���")
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
