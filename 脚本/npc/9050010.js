status = -1;
var meso = 3000000;
var itemList = Array(
        Array(1102271, 930, 1, 0), //愛你唷!巧克力氣球
        Array(1702089, 1, 1, 0), //巧克力蘋果
        Array(1702228, 930, 1, 0), //濃郁香蕉巧克力
        Array(1702299, 930, 1, 0), //甜蜜巧克力棒
        Array(1003836, 930, 1, 0), //彩色螺殼假髮
        Array(1003837, 930, 1, 0), //彩色飄飄海帶假髮
        Array(1003838, 930, 1, 0), //彩色天弓假髮
        Array(1003839, 930, 1, 0), //彩色核子爆發假髮
        Array(1004327, 930, 1, 0), //星光閃閃髮飾
        Array(1082548, 930, 1, 0), //星光手觸
        Array(1102755, 930, 1, 0), //星光氣球
        Array(1702564, 930, 1, 0), //趣味溜溜球
        Array(1702200, 930, 1, 0), //透明雨傘
        Array(1102503, 930, 1, 0), //搖晃的小貓尾巴
        Array(1102510, 930, 1, 0), //貓咪尾巴飛柔柔
        Array(1012208, 930, 1, 0), //蘋果臉
        Array(1052587, 930, 1, 0), //哈維海豹裝
        Array(1102651, 930, 1, 0), //雷射熊尾巴
        Array(1052655, 930, 1, 0), //靈魂熊服裝
        Array(1050359, 930, 1, 0), //涼爽的雪花
        Array(1003083, 930, 1, 0), //芽芽露水
        Array(1003901, 930, 1, 0), //膽大的兔子帽
        Array(1004384, 930, 1, 0), //綠怪物帽子
        Array(1004480, 930, 1, 0), //頑皮男孩的帽子
        Array(1062229, 930, 1, 0), //頑皮男孩的下褲
        Array(1003123, 930, 1, 0), //黑色娃娃頭巾
        Array(1003149, 930, 1, 0), //洛皮爾小兔頭巾
        Array(1702533, 930, 1, 0), //上鏡頭
        Array(1042320, 930, 1, 0), //環島旅行咕咕T
        Array(1012384, 930, 1, 0), //淘氣鬼髮箍
        Array(1102359, 930, 1, 0), //圓通通雪人氣球
        Array(1003130, 930, 1, 0), //光明羽毛之冠
        Array(1702185, 930, 1, 0), //光明天使
        Array(1702279, 930, 1, 0), //光明羽毛劍
        Array(1702280, 930, 1, 0), //光明羽毛雙手劍
        Array(1702281, 930, 1, 0), //光明羽毛權杖
        Array(1702282, 930, 1, 0), //光明羽毛弓
        Array(1702283, 930, 1, 0), //光明羽毛指虎
        Array(1702436, 930, 1, 0), //光明聖杖
        Array(1042275, 930, 1, 0), //青蛙雨滴
        Array(1102380, 930, 1, 0), //大大小小的青蛙
        Array(1702382, 930, 1, 0), //中秋節柿子樹樹枝
        Array(3010070, 10, 1, 1), //皮卡啾椅子
        Array(3010139, 30, 1, 1), //我的王座
        Array(3010123, 30, 1, 1), //花漾彩蝶椅
        Array(3010069, 30, 1, 1), //機器人椅
        Array(3012001, 70, 1, 1) //營火
        );

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063#  #i3994071##i3994077#\r\n" +
                    "        #r此為泡泡谷轉蛋機，您不想轉蛋嗎？");
            cm.dispose();
        }
        status--;
    }

    if (status == 0) {
        if (cm.getPlayer().getLevel() < 10) {
            cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063#  #i3994071##i3994077#\r\n" +
                    "        #r此為泡泡谷楓幣轉蛋機，需要10等才能抽獎。");
            cm.dispose();
        } else if (cm.getMeso() > meso) {
            cm.sendYesNo("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063#  #i3994071##i3994077#\r\n" +
                    "\t  #b該轉蛋機轉到的點裝均無法放回商城以及有期限30天\r\n" +
					"           #r此為泡泡谷轉蛋機，轉一次需要300萬哦#k\r\n");
        } else {
            cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063#  #i3994071##i3994077#\r\n" +
					"\t  #b該轉蛋機轉到的點裝均無法放回商城以及有期限30天\r\n" +
                    "       #r此為泡泡谷轉蛋機，需要300萬楓幣。#k\r\n");
            cm.safeDispose();
        }
    } else if (status == 1) {
        var chance = Math.floor(Math.random() * 1000);
        if (chance > 930) {
            chance = 930;
        }
        if (chance < 1) {
            chance = 930;
        }
        var finalitem = Array();
        for (var i = 0; i < itemList.length; i++) {
            if (itemList[i][1] >= chance) {
                finalitem.push(itemList[i]);
            }
        }
        if (finalitem.length != 0) {
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var itemId = finalitem[finalchance][0];
            var quantity = finalitem[finalchance][2];
            var notice = finalitem[finalchance][3];
            if ((cm.getMeso() > meso) && cm.canHold()) {
                cm.gainMeso(-meso);
                if (notice == 1) {
                    cm.gainGachaponItemTime(itemId, quantity, "轉蛋機", 30);
                } else {
                    cm.gainItemTime(itemId, quantity, 30);
                }
                cm.getItemLog("金幣轉蛋機", " 抽到 " + itemId + "(" + cm.getItemName(itemId) + ") " + quantity + "個。");
                cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063#  #i3994071##i3994077#\r\n" +
                    " #r此為泡泡谷轉蛋機，恭喜你得到了#k #b#t" + itemId + "##k #r" + quantity + "個。#k");
            } else {
                cm.sendOk("請確認背包是否已經滿了以及是否有300萬唷。");
            }
            cm.safeDispose();
        } else {
            cm.sendOk("出現未知問題，請稍後再試。");
            cm.safeDispose();
        }
    } else {
        cm.dispose();
    }
}