/* ==================
 �ű�����: ���߽���	    
 �ű����ߣ���Ҷ   
 ��ϵ��ʽ��1848350048
 =====================
 */
var status = 0;
var Eventid = "վ�ֽ���";
var OnlineLevel = [10, 30, 60, 120, 240, 360, 480];//, 600, 720
var giftContent = [
        //10����
        [4001126, 5, 0], //��Ҷ

		
		
		//30����
        [5390002, 5, 1], //����
		[4000463, 1, 1],//��������
		
		//1Сʱ
        [4001126, 10, 2], //��Ҷ
		[5390005, 3, 2],//����
		
		//2Сʱ
		[5122000, 5, 3], //�ܱ���
		[4032391, 5, 3],//��Ƭ
		
		
		
        //4Сʱ
		[4032392, 5, 4], //��Ƭ
		[4000313, 2, 4],//���ױ�
        
		//6Сʱ
		[4000038, 1, 5], //��
		[2000005, 2, 5],
		[4001126, 10, 5],
		
        
		//8Сʱ
		[2300001, 20, 6], 
		[4032392, 10, 6],
		[4032391, 10, 6],
        
		//10Сʱ
		[4020005, 1, 7],
        [4020006, 1, 7],
		[4020007, 1, 7],
        
		//12Сʱ
		[4020008, 1, 8], //�ϻ������龰����
		[4004000, 1, 8],
		[4004001, 1, 8],
		[5150040, 1, 8],
       
        ];
var giftId = -1;
var giftToken = [];
var gifts = null;
var time,TimeShow; 
var eff = "#fEffect/CharacterEff.img/1112924/0/0#";

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
	    if (cm.getGamePoints() < 60) {
	     time = "��������ʱ�䣺#e#r"+ cm.getGamePoints() +"#k#n ����";
		} else {
		 time = "��������ʱ�䣺#e#r"+ Math.floor(cm.getGamePoints() / 60) +"#k#n Сʱ #e#r"+ (cm.getGamePoints() % 60) +"#k#n ����";
		}
        text = "\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n"+ time +"\r\n";
        for (var key in OnlineLevel) {
            var tips = "";
            giftToken[key] = false;
            if (cm.getGamePoints() >= OnlineLevel[key]) {
                if (cm.getEventCount(Eventid + OnlineLevel[key]) == 0) {
                    tips = "#g< ����ȡ >";
                    giftToken[key] = true;
                } else {
                    tips = "#k< ��������ȡ >#b";
                }
            } else {
                tips = "#r< ����ʱ�䲻�� >#b";
            }
			if (OnlineLevel[key] < 60) {
	            TimeShow = "#r#e"+ OnlineLevel[key] +"����#n#b";
		    } else if (OnlineLevel[key] % 60 == 0){
				TimeShow = "#r#e"+ Math.floor(OnlineLevel[key] / 60) +"Сʱ#n#b";
			} else {
		        TimeShow = "#r#e"+ Math.floor(OnlineLevel[key] / 60) +"Сʱ#r#e"+ (OnlineLevel[key] % 60) +"����#n#b";
		    }
            text += "#b#L" + (parseInt(key)) + "#��ȡ "+ TimeShow +" ���߽��� " + tips + "#l#k\r\n";
        }
        cm.sendSimple(text);
    } else if (status == 1) {
        giftId = parseInt(selection);
        var text = "\t\t  #b"+ eff +" ����#r#e " + OnlineLevel[giftId] + " #n#b���ӽ������� "+ eff +"#k#n\r\n\r\n";
        gifts = getGift(giftId);
        for (var key in gifts) {
            var itemId = gifts[key][0];
            var itemQuantity = gifts[key][1];
            text += "\t\t\t#v" + itemId + "# #b#z" + itemId + "# #k[" + itemQuantity + "��]\r\n";
        }
        text += "\r\n#d����ʾ����ȡ���ʱ��ע�ⱳ����λ#r�Ƿ����㹻��Ŀ�λ#d����#k";
        cm.sendYesNo(text);
    } else if (status == 2) {
        if (giftId != -1 && gifts != null) {
            if (cm.getInventory(1).isFull(5) || cm.getInventory(2).isFull(5) || cm.getInventory(3).isFull(5) || cm.getInventory(4).isFull(5) || cm.getInventory(5).isFull(5)) {
                cm.sendOk("���ı����ռ䲻�㣬�뱣֤ÿ����λ����8��Ŀռ䣬�Ա�����ȡʧ�ܡ�");
                cm.dispose();
                return;
            }
            if (giftToken[giftId] && cm.getEventCount(Eventid + OnlineLevel[giftId], 1) == 0) {
                cm.setEventCount(Eventid + OnlineLevel[giftId]);
				cm.playerMessage(- 1,"��� "+ ((giftId + 1) * 3) +" �����");
                for (var key in gifts) {
                    var itemId = gifts[key][0];
                    var itemQuantity = gifts[key][1];
                    cm.gainItem(itemId, itemQuantity);
                }
                cm.sendOk("��ϲ������ȡ�ɹ�����򿪰��������ɣ�");
			if (OnlineLevel[giftId] < 60) {
	            TimeShow = OnlineLevel[giftId] +"����";
		    } else if (OnlineLevel[giftId] % 60 == 0){
				TimeShow = + Math.floor(OnlineLevel[giftId] / 60) +"Сʱ";
			} else {
		        TimeShow = + Math.floor(OnlineLevel[giftId] / 60) +"Сʱ"+ (OnlineLevel[giftId] % 60) +"����";
		    }
			cm.worldMessage(6,"��ң�["+cm.getName()+ " ��ȡ�� " + OnlineLevel[giftId] + " �������߽�������ϲ���������ɣ�~");
                cm.dispose();
            } else {
                status = -1;
                cm.sendOk("���Ѿ�����˸������������ʱ��δ�ﵽҪ���޷���ȡ��");
            }
        } else {
            cm.sendOk("��ȡ��������ϵ����Ա��");
            cm.dispose();
        }
    }
}
function getGift(id) {
    var lastGiftContent = Array();
    for (var key in giftContent) {
        if (giftContent[key][2] == id)
            lastGiftContent.push(giftContent[key]);
    }
    return lastGiftContent;
}
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