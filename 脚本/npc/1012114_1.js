/*
 ZEVMSð�յ�(079)��Ϸ�����
 ��Ӧ�ĵ���4170001
 */
var ��� = cm.�����(50);
var status = 0;
//��ƷID���齱���ʣ���Ʒ������(0/���㲥��1�㲥)
var itemList =
        Array(
                Array(4004000, 100, ���, 1),
                Array(4004001, 100, ���, 1),
                Array(4004002, 100, ���, 1),
                Array(4004003, 100, ���, 1),
				Array(4004004, 100, ���, 1),
                Array(2000005, 100, ���, 1)
				
                );

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        var chance = Math.floor(Math.random() * +100);
        var finalitem = Array();
        for (var i = 0; i < itemList.length; i++) {
            if (itemList[i][1] >= chance) {
                finalitem.push(itemList[i]);
            }
        }
        if (finalitem.length != 0) {
            if (finalitem.length == 0) {
                return 1;
            }
            var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var itemId = finalitem[finalchance][0];
            var quantity = finalitem[finalchance][2];
            var notice = finalitem[finalchance][3];
            item = cm.gainGachaponItem(itemId, quantity, "", notice);
            if (item != -1) {
                cm.gainItem(��, -1);
               
            } else {
                cm.sendOk("����ȷ���ڱ�����װ�������ģ������������Ƿ���һ�����ϵĿռ䡣");
            }
            cm.safeDispose();
        } else {
            cm.sendOk("�������������ʲô��û���õ���");
            cm.safeDispose();
        }
        cm.safeDispose();
    }
}