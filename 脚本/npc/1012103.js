
var status = 0;
var beauty = 0;
var hairprice = 1000000;
var haircolorprice = 1000000;
var mhair = Array(30030, 30020, 30000, 30310, 30330, 30060, 30150, 30410, 30210, 30140, 30120, 30200);
var fhair = Array(31050, 31040, 31000, 31150, 31310, 31300, 31160, 31100, 31410, 31030, 31080, 31070);
var hairnew = Array();
var ��ֹ���� = Array(40000,40010,40020,40030,40040,40050,40060,40090,40100,40110,40250,40260,40270,40280,40340,40350,40360,40370,40390,40430,40470,40480,40490,41060,41070,41080,41090,41100,41110,41120,41130,41140,41150,41160,41190,41200,41210,41340,41350,41360,41400,41410,41420,41430,41440,41460,41550,41570,41580,42060,42070);
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode < 1) {
        cm.dispose();
    } else {
        status++;
        if (status == 0) 
            cm.sendSimple("���ã����������������ϰ�. ������� #b#t5150001##k ������ #b#t5151001##k �������Ұ����ͷ��������ѡ��һ������Ҫ��.\r\n#L1#ʹ�� #i5150001##t5150001##l\r\n#L2#ʹ�� #i5151001##t5151001##l");
        else if (status == 1) {
            if (selection == 0) {
                beauty = 0;
                cm.sendSimple("Which coupon would you like to buy?\r\n#L0#Haircut for " + hairprice + " mesos: #i5150001##t5150001##l\r\n#L1#Dye your hair for " + haircolorprice + " mesos: #i5151001##t5151001##l");
            } else if (selection == 1) {
                beauty = 1;
                hairnew = Array();
				
                if (cm.getPlayer().getGender() == 0)
                    for(var i = 0; i < mhair.length; i++)
                        hairnew.push(mhair[i] + parseInt(cm.getPlayer().getHair()% 10));
                if (cm.getPlayer().getGender() == 1)
                    for(var i = 0; i < fhair.length; i++)
                        hairnew.push(fhair[i] + parseInt(cm.getPlayer().getHair() % 10));
				cm.sendStyle("���ҿ�����ѡ�����ĸ�."+current+".", hairnew);
				
            } else if (selection == 2) {
				var current = parseInt(cm.getPlayer().getHair()/10)*10;
				var imtenuber = 0;
			    for(var i=0;i<��ֹ����.length;i++){
			    if(cm.getPlayer().getHair()==��ֹ����[i]){//�ж�ӵ��������͵�������Ĵ����ִ��
				imtenuber=��ֹ����[i];//imtenuber����Ϊ���ʹ���
				break;
				}
			    }
				if(imtenuber == 0){
                beauty = 2;
                haircolor = Array();
                var current = parseInt(cm.getPlayer().getHair()/10)*10;
                for(var i = 0; i < 8; i++)
                haircolor.push(current + i);
                
				cm.sendStyle("���ҿ�����ѡ�����ĸ�..", haircolor);
				}else{
			     cm.sendOk("�ʼҷ���ֻ�ܻʼ����Ⱦɫ������ط�������ȾɫӴ!");
				 cm.dispose();
                 return;
				}
            }
        } else if (status == 2){
            cm.dispose();
            if (beauty == 1){
                if (cm.haveItem(5150001)){
                    cm.gainItem(5150001, -1);
                    cm.setHair(hairnew[selection]);
                    cm.sendOk("����!");
                } else
                    cm.sendOk("��ò��û��#b#t5150001##k..");
            }
            if (beauty == 2){
                if (cm.haveItem(5151001)){
                    cm.gainItem(5151001, -1);
                    cm.setHair(haircolor[selection]);
                    cm.sendOk("����!");
                } else
                    cm.sendOk("��ò��û��#b#t5151001##k..");
            }
            if (beauty == 0){
                if (selection == 0 && cm.getMeso() >= hairprice) {
                    cm.gainMeso(-hairprice);
                    cm.gainItem(5150001, 1);
                    cm.sendOk("����!");
                } else if (selection == 1 && cm.getMeso() >= haircolorprice) {
                    cm.gainMeso(-haircolorprice);
                    cm.gainItem(5151001, 1);
                    cm.sendOk("����!");
                } else
                    cm.sendOk("��û���㹻�Ľ�ҹ���!");
            }
        }
    }
}
