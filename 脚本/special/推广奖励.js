
var status = -1;
var �Լ��ƹ���� =Array();
var type1 =0;
var xz1;
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (status >= 0 && mode == 0) {
		//cm.sendOk("��лʹ�ã�");
		cm.dispose();
		return;
	}
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
		if (status == 0) {
			var text ="";
			text +="\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n";
			text +="                 #e#d�ҵ��ƹ���:#r"+cm.getPlayer().getId()+"#k            \r\n";//#d�ҵ��ƹ����:#r"+�ƹ����()+"#k
			
			text +="              #d�ƹ��ý������:#r"+�ƹ㽱�����()+"#k\r\n";
			text +="          #d����д���ƹ���:#k#r"+(�ƹ���()==0?"��δ��д�ƹ���":""+��ȡ����(�ƹ���())+"")+"#k\r\n";
			
			text +="#L1##b"+������ͷ+"�鿴���Լ��ƹ����\r\n";
			text +="#L2#"+������ͷ+"��ȡ�ƹ㽱�����#l             #L3#�ƹ�#l\r\n";
			text +="                                #L3#˵��#l \r\n";//#L5#"+������ͷ+"���ֶһ��ƹ��ָ#l
			if(�ƹ���()==0){
			text +="#L4#"+������ͷ+"��д�ƹ���\r\n";
			}else{
			text +="#L6#"+������ͷ+"�����ƹ���\r\n";	
			}
			cm.sendSimple(text);
		} else if (status == 1) {
			if(selection ==4){
			type1 =1;
			cm.sendGetNumber("���������ƹ��˵�ID,ע������֮���޷��޸�",1,1,210000000);
			}else if(selection ==6){
			type1 =6;
			cm.sendGetNumber("���������ƹ��˵�ID,ע��ֻ��һ�λ��ᰡ,���θ�����Ҫ����500w���",1,1,210000000);
			}else if(selection ==1){
			if(��ȡ�Լ��ƹ����(cm.getPlayer().getId())==0){
			cm.sendOk("Ŷ���,ò��û��һ����д��������ƹ�");
			cm.dispose();
			return;
			}
			var text ="";
			text +="��ǰ��д����ƹ��������:\r\n";
			for(var a=0;a<�Լ��ƹ����.length;a++){
			text +="#k��ɫ����:#r"+��ȡ����(�Լ��ƹ����[a])+"\r\n";		
			}
			cm.sendOk(text);
			cm.dispose();
			return;	
			}else if(selection ==2){
			type1 =2;	
			var msg = "";
				msg += "��ǰ����ȡ�ƹ㽱�����"+�ƹ㽱�����()+"\r\n"
				msg += "�������ֵ�������һ����\r\n"
				cm.sendGetNumber(msg, 1,1, �ƹ㽱�����());	
			}else if(selection ==5){
				cm.dispose();
				cm.openNpc(3003332,  "�ƹ��ָ");
			}
			else if(selection ==3){
				cm.sendOk("\t\t"+�ʺ�+"  #e#d �� �� ˵ �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\nÿ�ƹ�һ����,�ɻ��1�ƹ����!���ƹ�����,ÿ����1Ԫ,�����Ի��50���,�Դ����ƣ��ۼ����ӣ�������Ч.");
			cm.dispose();
			return;
			}
		}else if(status ==2){
			xz1=selection;
			if(type1==1){
			if(cm.getPlayer().getId()==xz1){
			cm.sendOk("�Լ�������д�Լ�Ŷ");
			cm.dispose();
			return;
			}
			if(cm.getPlayer().getAccountID()==�ж��˺�(xz1)){
			cm.sendOk("������ͬһ���˺��Լ����Լ�Ŷ");
			cm.dispose();
			return;
			}
			if(cm.getOneTimeLog("�ƹ�ϵͳ1")<1){
			if(�ж�����(xz1)==1){	
			cm.sendYesNo("����д���ƹ�����:"+��ȡ����(xz1)+",ȷ������ô��");
			}else {
			cm.sendOk("ò��û���ҵ�������������Ŷ");
			cm.dispose();
			return;	
			}
			}else {
			cm.sendOk("���Ѿ���д���ƹ�����");
			cm.dispose();
			return;	
			}	
			}else if(type1==6){	
			if(cm.getPlayer().getId()==xz1){
			cm.sendOk("�Լ�������д�Լ�Ŷ");
			cm.dispose();
			return;
			}
			if(cm.getPlayer().getAccountID()==�ж��˺�(xz1)){
			cm.sendOk("������ͬһ���˺��Լ����Լ�Ŷ");
			cm.dispose();
			return;
			}
			/*if (cm.getPlayer().getLevel() > 120) {
				cm.sendOk("�㳬��120��,�����Ը����ƹ���");
			    cm.dispose();
			    return;
			} */
			if(cm.getMeso()<8000000){
			cm.sendOk("��ò��û����ô��Ǯ");
			cm.dispose();
			return;
			}
			if(cm.getOneTimeLog("�ƹ�ϵͳ2")<1){
			if(�ж�����(xz1)==1){	
			cm.sendYesNo("����д���ƹ�����:"+��ȡ����(xz1)+",ȷ������ô��");
			}else {
			cm.sendOk("ò��û���ҵ�������������Ŷ");
			cm.dispose();
			return;	
			}
			}else {
			cm.sendOk("���Ѿ��������ƹ�����");
			cm.dispose();
			return;	
			}





			
			}else if(type1==2){
			if (�ƹ㽱�����() >= xz1) {
					�۳��ƹ㽱�����(xz1);
					cm.gainNX(xz1);
					cm.sendOk("�һ��ɹ�");
                    cm.dispose();
				} else {
					cm.sendOk("�ƹ㽱��������޷��һ�");
                    cm.dispose();
				}	
				
				
			}
		}else if(status ==3){
			
		if(type1 ==1){
		var ��ɫid = cm.getPlayer().getId();	
		�����ƹ�(��ɫid,xz1);
		//��ȡ����(xz1,1);
		cm.setOneTimeLog("�ƹ�ϵͳ1");
		cm.sendOk("��д�ɹ�");
		cm.dispose();
		}else if(type1 ==6){
		var ��ɫid = cm.getPlayer().getId();
		cm.gainMeso(-8000000);
		�����ƹ�(��ɫid,xz1);
		//��ȡ����(xz1,1);
		cm.setOneTimeLog("�ƹ�ϵͳ2");
		cm.sendOk("��д�ɹ�");
		cm.dispose();	
			
		}	
			
			
		}
}
}

function �۳��ƹ㽱�����(uit) {
	var uid = cm.getPlayer().getId();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET dj = dj-"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
function �۳��ƹ����(uit) {
	var uid = cm.getPlayer().getId();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET jf = jf-"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function �ƹ���(){
	var ��ɫid = cm.getPlayer().getId();
	var ��ֵ =0;
	var ��ֵ1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,objecid FROM invsystem WHERE ownid = "+��ɫid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	��ֵ = result.getInt("objecid");
	��ֵ1=0;
	}
	result.close();
	pstmt.close();
	if(��ֵ1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO invsystem(id,ownid,objecid) value(null,"+��ɫid+",0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return ��ֵ;	
	
}
function �ж��˺�(uid){
	var ��ֵ1 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT id,accountid FROM characters WHERE id = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	��ֵ1=result.getInt("accountid");
	}
	result.close();
	pstmt.close();	
	return ��ֵ1;
}
function �ж�����(uid){
	var ��ֵ1 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT id FROM characters WHERE id = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	��ֵ1=1;
	}
	result.close();
	pstmt.close();	
	return ��ֵ1;
}

function ��ȡ����(uid){
	var ��ֵ1 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT id,name FROM characters WHERE id = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {

	��ֵ1=result.getString("name");
	
	}
	result.close();
	pstmt.close();	
	return ��ֵ1;
}

function �ƹ����(){
	var ��ɫid = cm.getPlayer().getId();
	var ��ֵ =0;
	var ��ֵ1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,jf FROM tuiguangjf WHERE ownid = "+��ɫid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	��ֵ = result.getInt("jf");
	��ֵ1=0;
	}
	result.close();
	pstmt.close();
	if(��ֵ1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO tuiguangjf(id,ownid,jf,dj) value(null,"+��ɫid+",0,0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return ��ֵ;	
	
}
function �ƹ㽱�����(){
	var ��ɫid = cm.getPlayer().getId();
	var ��ֵ =0;
	var ��ֵ1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,dj FROM tuiguangjf WHERE ownid = "+��ɫid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	��ֵ = result.getInt("dj");
	��ֵ1=0;
	}
	result.close();
	pstmt.close();
	if(��ֵ1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO tuiguangjf(id,ownid,jf,dj) value(null,"+��ɫid+",0,0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return ��ֵ;	
	
}
function ��ȡ����(uid,uit) {
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET jf = jf+"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function �����ƹ�(uid,uit) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE invsystem SET objecid = "+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function ��ȡ�Լ��ƹ����(uid) {
	�Լ��ƹ����=Array();
	var ��ȡ������;
	var ��ֵ =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,objecid FROM invsystem WHERE objecid = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	while (result.next()) {
	��ȡ������=result.getInt("ownid");
	var conn1 = Packages.database.DatabaseConnection.getConnection();
	var sql1 = "SELECT id FROM characters WHERE id = "+��ȡ������+"";
	var pstmt1 = conn1.prepareStatement(sql1);
	var result1 = pstmt1.executeQuery();
	while (result1.next()) {
	�Լ��ƹ����.push(result1.getInt("id"));
	��ֵ++;	
	}
	result1.close();
	pstmt1.close();	
	}
	result.close();
	pstmt.close();	
	return ��ֵ;



}








function getmoneyb() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyb");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function gainmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";