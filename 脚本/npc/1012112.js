var FY0 = "��������������������������";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var FY6 = "�ǩ�����������������������";
var FY7 = "�� ΨһQQ:1848350048    ��";
var FY8 = "��������������������������";
 importPackage(java.lang);
importPackage(Packages.tools);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools.packet);
	
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ca = java.util.Calendar.getInstance();
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var cishuxianzhi = 10;//���ƴ���
var ��С�ȼ� = 10;
var ��ߵȼ� = 250;
var �������� = 1;
var ������� = 6;

var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.�Ի�����();
			
            return;
        }
        status--;
    }
    if (cm.getMapId() == 910010100) {
        cm.sendOk("��ϲ��������������񣬵����ߵĴ���׿�����ȡ�����������");
        cm.�Ի�����();
	 } else if (status == 0) {
	 cm.sendYesNo("\r\n\t\t     "+�ʺ�+"#e#d�� �� �� �� #k#n  #r"+�ʺ�+" #k\r\n\r\n��������Ҫ�����£�\r\n����������:#r " + �������� + " #b- #r" + ������� + "#k��Ա\t�ڵȼ����ƣ�#r " + ��С�ȼ� + " #b- #r" + ��ߵȼ� + "�� #k\r\nÿ��ֻ����ս:#k#b"+ cishuxianzhi +"#k�� ������ѽ���:#b"+ cm.getPlayer().getBossLog("YueMiaoCiShu") +"#k��#k\r\n��������:\r\n "+cm.��ʾ��Ʒ(4001101)+" "+cm.��ʾ��Ʒ(1002798)+" \r\n "+cm.��ʾ��Ʒ(4001126)+"  "+cm.��ʾ��Ʒ(4000313)+" "+cm.��ʾ��Ʒ(4170000)+"");
    } else if (status == 1) {
        if (cm.getParty() == null) {
            cm.sendSimple("����Ӻ������Ұѡ�");
			cm.dispose();
			return;
        } else if (!cm.isLeader()) {
            cm.sendSimple("������볢�ԣ������ #b��Ӷӳ�#k ����˵��.#b#l");
			cm.dispose();
			return;
        }else if(cm.getPartyBosslog("YueMiaoCiShu",(cishuxianzhi)) == false) {//�ж�����Ƿ�2��
	            cm.sendOk("�����ж�����ս�����Ѿ�����"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	    }else if( cm.getPlayer().getBossLog("YueMiaoCiShu") >= cishuxianzhi) {
	            cm.sendOk("����,�޶�ÿ��ֻ����ս"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	    } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;
            var it = party.iterator();

            while (it.hasNext()) {
                var cPlayer = it.next();
                if ((cPlayer.getLevel() >= ��С�ȼ�) && (cPlayer.getLevel() <= ��ߵȼ�)) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 6 : 1);
					
                }
				
            }
            if (party.size() > ������� || inMap < ��������) {
                next = false;
				
            }
            if (next) {
                var em = cm.getEventManager("HenesysPQ");
                if (em == null) {
                    cm.sendSimple("PQ������һ����������ϵGM�����ͼ.#b#l");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        for (var i = 4001095; i < 4001099; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
                        for (var i = 4001100; i < 4001101; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
						
                        em.startInstance(cm.getParty(), cm.getMap());
						cm.setPartyBosslog("YueMiaoCiShu");//���ŶӴ���
                        cm.�Ի�����();
						
                        return;
                    } else {
                        cm.sendSimple("��һ���ѽ��� #r��������#k ������볢����һ��Ƶ�������ߵȴ���ǰ���������.#b#");
                    }
                }
            } else {
                cm.sendOk("����: #b����#k\r\n����: #b" + �������� + " - " + ������� + "#k\r\n�ȼ�: #b" + ��С�ȼ� + " - " + ��ߵȼ� + "#k");
				
                cm.�Ի�����();
            }
        }
    }
}
