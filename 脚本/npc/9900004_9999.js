var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��� = "#fItem/Special/0900.img/09000001/iconRaw/1#";
var ��ȯ = "#fUI/CashShop/CashItem/0#";
var ���� = "#fUI/CashShop/CSDiscount/bonus#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += ""+����+"\r\n"
                text += "#k#v4000463#��12 #v4032391#��3 #v4000038#��6\r\n";
                text += "#v4001126#��200 #v4000313#��20 #v4032392#��3\r\n";
				text += ""+���+"ð�ձ� 30�� \r\n";
				text += "��ȡʱ�뽫��ԱVIP��#v1142623#���뱳���У���ȷ�������������пո�#k#n\r\n";
                text += "\t\t\t\t#L1#"+��ɫ��ͷ+"��ȡVIP��ÿ�����\r\n\r\n";//3
            cm.sendSimple(text);
           }
        } else if (selection == 1) {
if(cm.getBossLog('����3') >0){
cm.sendOk("������Ѿ���ȡ��һ��");
cm.dispose();
}
/*else if (cm.getzb()<0)
{
	cm.sendOk("��ĳ�ֵ���ֲ���1000,�޷���ԱVIP��ÿ�����");
cm.dispose();
}*/
          else  if (cm.haveItem(1142623, 1)) {

if(cm.canHold(4002003, 400) && cm.canHold(1122017, 1))
{
//cm.gainNX(6000);//���
//cm.getPlayer().modifyCSPoints(2, 15000, true);//����
cm.gainMeso(300000);
cm.gainItem(4000463,12);//�����
cm.gainItem(4000313,20);//���ױ�
cm.gainItem(4032392,3);//�������Ƭ2
cm.gainItem(4032391,3);//�������Ƭ1
cm.gainItem(4001126,200);//��Ҷ
cm.gainItem(4000038,6)//��
cm.setBossLog('����3');

cm.����(1,"[��Ա����]:��Ա����ҡ�"+cm.getPlayer().getName()+"����ȡ�˽���Ļ�ԱVIP��ÿ�������");
cm.dispose();
}
else
{
  cm.sendOk("��������,ȷ�����㹻�ռ�!\r\n");
   cm.dispose();
}
			}else{
            cm.sendOk("��ȷ���Ѿ�����ԱVIP��ѫ�·��뱳��");
            cm.dispose();
			}
        } 
		
    }
}
