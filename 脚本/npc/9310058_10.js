/*

 ���ʱ�䣺2014��12��28�� 22:22:28
 �ű����ܣ���Ʒ�����ύ����
 */
importPackage(java.sql);
importPackage(java.lang);
importPackage(Packages.database);

var time = new Date();
var ��ʼʱ�� = "2019-12-10 18:00:00";//��ʼʱ��
var ����ʱ�� = "2019-12-20 23:00:00";//����ʱ��
var minLevel = 50;//Ҫ����С�ȼ�
var minOnlineTime = 20;//��Ҫ����ʱ��
var hour = time.getHours(); //���Сʱ
var minute = time.getMinutes();//��÷���
var second = time.getSeconds(); //�����
var itemid = 4001248;//4001248
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("#r���ڻʱ�䣺"+��ʼʱ��+"��"+����ʱ��+"����ȫ���������#r#z"+itemid+"##k#n\r\n��ܰ��ʾ:ǰ���������Ǳ�ְҵ����Ŷ���޷������ְҵʹ��\r\n�����������12��21�շ��š�");
            cm.dispose();
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) { 
                cm.sendSimple("#e#d�ռ�#z"+itemid+"#���#r191214#d����#n ����Ϊһ����ڣ�1�ڣ�\r\n#b���л�ڼ�ֻҪ�ռ� #r#z"+itemid+"# #b�����Ͻ����������ƶȽ���\r\n#r���ڻʱ�䣺"+��ʼʱ��+"��"+����ʱ��+"#k\r\n#b\t��һ������ #r��ְҵ�������� x 1��#b\r\n\t�ڶ������� #r��ְҵ140���� x 1��#b\r\n\t���������� #r��ְҵ140���� x 1��#b\r\n\r\n#d���в��뱾��߾��ɻ�� #r#e1#z"+itemid+"#x5��� #n#b������#k#b \r\n��ǰ��������#z"+itemid+"#�� #r" + cm.getItemQuantity("4001248") + " #b����Ŀǰ���Ѿ��Ͻ��ˣ� #r"+getQty()+" #b����\r\n#L1# ����鿴������\r\n#L0# �����ύ��Ʒ��")//\r\n#L0# �����ύ��Ʒ��
        } else if (status == 1) {
            if (selection == 0) {
		cm.sendGetText("�����ύ���ٸ�#b#z"+itemid+"##k ?");
            } else if (selection == 1) {//����
                Ranking();//����
                cm.dispose();
	    }
        } else if (status == 2) {
		var quantity = parseInt(cm.getText());
                if (quantity >  0&& quantity<= 9999){
		if (cm.haveItem(itemid,quantity)){
		var i =0;
		var DataBase = cm.getConnection().prepareStatement("SELECT * FROM ItemQuantityRanking where charid = " + cm.getPlayer().getId() + " and itemid = "+itemid+"").executeQuery();
		while(DataBase.next()){
			i++;
		}
		if(i == 0){//��һ��
			var insert = cm.getConnection().prepareStatement("INSERT INTO ItemQuantityRanking (id,charid,charName,itemid,quantity) VALUES(?,?,?,?,?)"); // ��������
			insert.setString(1, null); //�����¼ID
                	insert.setString(2, cm.getPlayer().getId());
                	insert.setString(3, cm.getPlayer().getName());
                	insert.setString(4, itemid);
                	insert.setString(5, quantity);
                	insert.executeUpdate(); //����
		}else{ //��n��
			var UpDateData = cm.getConnection().prepareStatement("update ItemQuantityRanking set quantity=? where charid = " + cm.getPlayer().getId() + " and itemid = "+itemid+"")
    			UpDateData.setString(1, parseInt(getQty()) + quantity);
   			UpDateData.executeUpdate();//����;
		}
		cm.sendOk("������ӳɹ���\r\nĿǰ���ľ�������Ϊ�� "+getQty()+"\r\n\r\n");
		cm.gainItem(itemid,-quantity);
		//cm.gainNX(1, quantity*5)
		cm.gainNX(quantity*5)
		//cm.worldSpouseMessage(0x23, "���ռ��꿨��� : " + cm.getChar().getName() + " �Ͻ� "+quantity+" �ź��꿨,����� "+quantity * 5+" ���,Ŀǰ�ܹ��Ͻ��� "+getQty()+" �š�");
		cm.ȫ����ɫ����("���ռ��꿨��� : " + cm.getChar().getName() + " �Ͻ� "+quantity+" �ź��꿨,����� "+quantity * 5+" ���,Ŀǰ�ܹ��Ͻ��� "+getQty()+" �š�")

		}else{
			cm.sendOk("�Բ�����û���㹻��#t"+itemid+"#");
		}
}else{
       cm.sendOk("1����9999�������ֿ������롣");

}
		cm.dispose();
        }
    }
}



function getQty() { //�õ�Ŀǰ����
    var Times = 0;
    var EventDataBase = cm.getConnection().prepareStatement("SELECT quantity FROM ItemQuantityRanking where charid = " + cm.getPlayer().getId() + " and itemid = "+itemid+"").executeQuery();
  
 while (EventDataBase.next()) {
        Times = EventDataBase.getString("quantity");
    }
    return Times;
}



function Ranking() {
    var Text = "#e#d������ʾ��ֻ��ǰ�������Ի�ý���\r\n\r\n�����������£�(1~10����) \r\n\r\n#d"
    var RankDataBase = cm.getConnection().prepareStatement("SELECT * FROM ItemQuantityRanking where itemid = "+itemid+" ORDER BY quantity DESC LIMIT 10").executeQuery();
    var i = 1;
    while (RankDataBase.next()) {
        Text += " #e#b����:#r�� " + i + " ��#k #b��ɫ��:#r" + RankDataBase.getString("charName") + " #b�ύ��Ʒ��:#r " + RankDataBase.getString("quantity") + "#k\r\n"
        //Text += "~~~~~~~~~~~~~~~~~~~\r\n"
        i++;
    }
    cm.sendOk(Text);
}