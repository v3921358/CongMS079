/*С���ݽű�
���ߣ���Ҷ    QQ��1848350048    
 */
var ��ȯͼ�� = "#fUI/CashShop/CashItem/0#";
//var ��ֵ��վ = "http://pay.5566pay.com/Pay/5b8c39815c0f60d7d4020ea6f32e387c";
var ������� = 5000000;
var ������� = 200000;
var Ԫ������ = 50;
//���͵ص㣬���Ľ��
var maps = Array(
		[910000000, 1],
		[104000000, 1],
		[101000000, 1],
        [120000000, 1],
        [102000000, 1],
        [100000000, 1]

        );
var status = 0;
var show;
var sCost;
var selectedMap = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status >= 2) {
            cm.˵������(" ������ȥ�����ˣ��ǵ�����Ŷ��");
            cm.�Ի�����();
            return;
        }
        status--;
    }
    if (status == 0) {
    } else if (status == 1) {		
		
		var selStr="";
		if(cm.getPlayer().getNowdaylog()<1)
		{
			cm.getPlayer().setNowdaylog();
		}
		�������=�������*cm.getPlayer().getNowdaylog();
		�������=�������*cm.getPlayer().getNowdaylog();
		Ԫ������=Ԫ������*cm.getPlayer().getNowdaylog();
            selStr += "  #d#e��ӭ����С���ݼ�������������Ϸ���򣬽�ֹ���ң���������#k#n#b\r\n";
		    selStr +="  #r#eÿ�γ�ȥ�����Ѳ�ͬ��ȵ�Ԫ��,��ÿ�����ѵĶ�Ȼ����Ŵ�������������!#k#n#b\r\n"
			selStr += "  #r#e��ѡ����Ҫȥ�ĵط���#k#n#b\r\n";
            //selStr +="  (Ŀǰ����Ϊ:[#r"+�������+"#k#b]��� �� [#k#r"+�������+"#k#b]���!#n)\r\n"
			
			selStr +="  (Ŀǰ��Ҫ����:[#k#r"+Ԫ������+"#k#b]Ԫ��!#n)\r\n"

			
			selStr +=" #L1000#"+��ȯͼ��+"#dԪ����"+getmoneyb()+"  [#b�����ֵԪ��#d]#l\r\n"//ƽ̨��վ��ֵ
			//selStr +=" "+��ȯͼ��+"#d�����"+cm.getPlayer().getCSPoints(1)+"  #l\r\n"
		for (var i = 0; i < maps.length; i++) {
			 selStr += "\r\n#L" + i + "# #m" + maps[i] + "##l";
        }
        cm.sendSimple(selStr);
    } else if (status == 2) {
				if (selection == 1000) {
			//cm.openWeb(��ֵ��վ); //����ҳ
			//cm.playerMessage(5, "���ڴ򿪳�ֵ��վ�����û�е�����ֵ��վ����ϵGM1"); //���˿����ĶԻ� 5��ɫ�� 6��ɫ�� 1Ϊ����
			cm.dispose();
			cm.openNpc(3003385,71);
			return;
			
		}
		        
        show = maps[selection][1];
		var text ="\t����Ŀ��: #b#m" + maps[selection] + "##k\r\n";
		//text +=" #L1#����[#r"+�������+"#k]��Ҵ�����:#b#m" + maps[selection] + "##k#l\r\n"
		//text +=" #L2#����[#r"+�������+"#k]�������:#b#m" + maps[selection] + "##k#l\r\n"
		text +=" #L3#����[#r"+Ԫ������+"#k]Ԫ��������:#b#m" + maps[selection] + "##k#l\r\n"
        cm.sendYesNo(text);
        selectedMap = selection;
    } else if (status == 3) {
		if(selection==1)
		{
			if(cm.getPlayer().getMeso()<�������)
			{
			  cm.sendOk("����ӵ�еĽ�Ҳ���,����ϵ�����Ա���в���!");
			  cm.dispose();
			  return;
			}
			else   
			{
			cm.getPlayer().setNowdaylog();
			cm.gainMeso(-�������);
            cm.warp(maps[selectedMap][0]);
            cm.dispose();
			return;
			}
		}
		else if(selection==2){
		
			if(cm.getPlayer().getCSPoints(1) < �������)
			{
			  cm.sendOk("����ӵ�еĵ����,����ϵ�����Ա���в���!");
			  cm.dispose();
			  return;
			}
			else{
				  cm.getPlayer().setNowdaylog();
                  cm.getPlayer().modifyCSPoints(1, -�������, true);				  
                  cm.warp(maps[selectedMap][0]);
                  cm.dispose();
			return;
			}
		}else if(selection==3){
		
			if(getmoneyb() < Ԫ������)
			{
			  cm.sendOk("����ӵ�е�Ԫ������,����ϵ�����Ա���в���!");
			  cm.dispose();
			  return;
			}
			else{
				  cm.getPlayer().setNowdaylog();
                  setmoneyb(-Ԫ������);				  
                  cm.warp(maps[selectedMap][0]);
                  cm.dispose();
			return;
			}
		}
		else 
		{
			cm.sendOk("�Ƿ�����,����ϵ�����Ա���в���!");
			  cm.dispose();
			  return;
		}

            
        
    }
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
function setmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}