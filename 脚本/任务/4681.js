/*
	任務: 通往未來之門
	描述: 與阿夕亞的相遇
*/

var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
        return;
    } else if (mode == 0) {
        if (status == 41) {
            qm.sendNext("瞭解了嗎，這是攸關逆奧之城命運的問題。我身為不受#b阿卡夏的咒語#k束縛的人民，會用生命試著阻止國家的滅亡。");
            qm.dispose();
            return;
        }
        status--;
    } else {
        status++;
    }
    switch (status) {
    case 0:
        if (qm.getQuestCustomData() != null) { // if (qm.getQuestCustomData().equals("readHistory")) {
            qm.sendSimple("楓之谷世界的居民，接受考驗的結果如何？#b \n\r #L0#獲得了時間旅行者沙漏！#l \n\r #L1#找到了逆奧之流。（商城道具）#l");
            status = 99;
        } else {
            qm.sendNext("我是#p9120025#，來自#m802000101#為逆奧之城感到惋惜的人。");
        }
        break;
    case 1:
        qm.sendNextPrev("（#p9120025#瞇起眼睛）");
        break;
    case 2:
        qm.sendNextPrev("你真的長大了。\r\n<格裡特你說的是正確的…>");
        break;
    case 3:
        qm.sendNextPrev("我來到楓之谷的時候，當時你還小，現在已成長為一位堂堂正正的戰士了。對一直以來守護著你的我而言…沒有比這個更令人高興的事了…\r\n（#p9120025#的眼眶泛紅）");
        break;
    case 4:
        qm.sendNextPrev("…\r\n你不斷的磨練必殺技術，以堅強意志造就出不屈不撓的精神，又是力量的求道者，也曾經和楓之谷世界黑暗軍隊交戰過，這樣的你一定可以…");
        break;
    case 5:
        qm.sendNextPrev("─ 對不起，我剛才說話的口氣太沒禮貌了。");
        break;
    case 6:
        qm.sendNextPrev("我的使命是鑒別有能力的人，或推測出可能擁有能力的人。");
        break;
    case 7:
        qm.sendNextPrev("不僅如此，導引逆奧之城的救世主，使得逆奧之城能夠免於崩壞的危機，更是我的天命。");
        break;
    case 8:
        qm.sendNextPrev("這個地方叫做#m802000101#，是逆奧之城最遠且最邊界的地方。");
        break;
    case 9:
        qm.sendNextPrev("我要告訴你逆奧之城的真相。");
        break;
    case 10:
        qm.sendNextPrev("…");
        break;
    case 11:
        qm.sendNextPrev("一百年後，逆奧之城會消失。");
        break;
    case 12:
        qm.sendNextPrev("會完完全全地從這個世界上消失。我已預見，逆奧之城將被突然從時空裂縫出現的#b時空扭曲#k吞沒。");
        break;
    case 13:
        qm.sendNextPrev("古代的逆奧之城曾經十分盛行高度的魔法文明。我就是生在那個時代，自小學習魔法技能的逆奧之城魔法師。");
        break;
    case 14:
        qm.sendNextPrev("當時的逆奧之城，與瑪加提亞的交流持續加深。雖然在現今（你所身處的時代），科學的力量不斷抬頭，但魔法研究在過去才是主流。");
        break;
    case 15:
        qm.sendNextPrev("而瑪加提亞可謂魔法研究的先驅。\n我與當地的魔法師相遇，在研究進入尾聲之時，我得到了…#b長生不老的力量#k。");
        break;
    case 16:
        qm.sendNextPrev("這是還沒有人練成過，最困難的魔法。為了逆奧之城，當時的我盡力拼了。\n但過沒多久，永生不死就成了禁忌、異端，且不被人所接受。過去的同伴、自己的國家和整個世界開始與我為敵，當時無處可逃的我，決定要找到世界的終點，因此在逆奧之城的內陸隱居下來了。");
        break;
    case 17:
        qm.sendNextPrev("但末日的到來比我想像中還要快。");
        break;
    case 18:
        qm.sendNextPrev("當逆奧之城被巨大的#b時空扭曲#k吞沒的瞬間，我使出時空跳躍的魔法，漂流在消失後的世界裡，然後發現了一件事。");
        break;
    case 19:
        qm.sendNextPrev("我發現了逆奧之城的真相。");
        break;
    case 20:
        qm.sendNextPrev("逆奧之城的歷史全都紀錄在一本書裡。");
        break;
    case 21:
        qm.sendNextPrev("而將此書所記載的內容具現化，就是逆奧之城的歷史。");
        break;
    case 22:
        qm.sendNextPrev("原來將逆奧之城的人們所有的行動都用超次元技術記錄的#b阿卡夏 - 編年史#k也實際存在…。");
        break;
    case 23:
        qm.sendNextPrev("我的人生就是被這本書弄得亂七八糟的嗎。\n我察覺到我的憤怒了。");
        break;
    case 24:
        qm.sendNextPrev("在動盪的世界裡，我將#b阿卡夏 - 編年史#k拿在手裡讀著。");
        break;
    case 25:
        qm.sendNextPrev("我注意到一件事。");
        break;
    case 26:
        qm.sendNextPrev("#b阿卡夏 - 編年史#k裡沒有記載關於我的內容。");
        break;
    case 27:
        qm.sendNextPrev("我是不存在於過去的。");
        break;
    case 28:
        qm.sendNextPrev("不知道是不是因為與逆奧之城以外的人密切接觸，或是因此變得長生不老後，才從#b阿卡夏 - 編年史#k的詛咒#k解脫的。");
        break;
    case 29:
        qm.sendNextPrev("同時我也得知#b阿卡夏- 編年史#k的內容屬於流動式，且可加以改編。");
        break;
    case 30:
        qm.sendNextPrev("如果是這樣，我想要回到過去，重新改寫歷史，將原本會出現在歷史未來的#b時空扭曲#k，封印在時空的邊界，讓逆奧之城免於滅亡的命運。");
        break;
    case 31:
        qm.sendNextPrev("不過雖說是為了逆奧之城。但改寫歷史實在是狂妄的行為。\n若歷史能改寫，那麼人在未來的存在也將遭到剝奪。");
        break;
    case 32:
        qm.sendNextPrev("所以我的結論是，只改寫毀滅前的逆奧之城歷史。");
        break;
    case 33:
        qm.sendNextPrev("改寫歷史並非一次就可以完成的工作。歷史本身具有自我修正的能力，即使我做了修改，也將回到本來的面貌。");
        break;
    case 34:
        qm.sendNextPrev("我以#m802000101#為根據地，至今仍持續監控接近毀滅前的逆奧之城。");
        break;
    case 35:
        qm.sendNextPrev("接著之後…");
        break;
    case 36:
        qm.sendNextPrev("將會出現一波我前所未見的歷史修正浪潮。");
        break;
    case 37:
        qm.sendNextPrev("我知道這是攸關逆奧之城的大事，原本應該讓逆奧之城人民自己解決的。");
        break;
    case 38:
        qm.sendNextPrev("但逆奧之城人民因為#b阿卡夏的咒語#k而無法改變歷史。");
        break;
    case 39:
        qm.sendNextPrev("如果說這一切都是為了逆奧之城的未來，是太狂妄了些。");
        break;
    case 40:
        qm.sendNextPrev("（#p9120025#恭敬地請求）");
        break;
    case 41:
        qm.sendYesNo("你沒有受到#b阿卡夏的咒語#k束縛，我希望能借助你的力量。");
        break;
    case 42:
        //qm.forceStartQuest();
        qm.setQuestCustomData("readHistory");
        qm.sendNextPrev("謝謝你，來自冒險世界的勇者（#p9120025#安息了）");
        break;
    case 43:
        qm.sendNextPrev("相信擁有強大力量的你，即使在未來的時代也能夠克服戰鬥。");
        break;
    case 44:
        qm.sendNextPrev("只是…讓我見識你真正的力量吧。");
        break;
    case 45:
        qm.sendNextPrev("我從幾千年前開始就一直在尋找勇者。\n因為，在逆奧之城就要毀滅的世界裡，不僅有高度發展的科學文明，連敵人都擁有難以想像的強大力量。");
        break;
    case 46:
        qm.sendNextPrev("為了尋找真正的強者，我一直在進行一項計劃。");
        break;
    case 47:
        qm.sendNextPrev("逆奧之城因為目前的危機而發生了時空扭曲，從超空間開了個裂縫，與這個世界發生了關聯。");
        break;
    case 48:
        qm.sendNextPrev("這個神秘的出入口，恰好連接到了日本，並且與其歷史產生了聯繫。導致了日本的過去和現在，也出現過許多奇怪的現象。");
        break;
    case 49:
        qm.sendNextPrev("而正是因為如此，你可以通過出現在日本的水晶，來到這神秘的卡姆那。");
        break;
    case 50:
        qm.sendNextPrev("在卡姆那，可以獲得前往逆奧之城的過去與未來的不可思議的力量。");
        break;
    case 51:
        qm.sendNextPrev("逆奧之城的歷史中，楓葉古城是一個特殊時期。");
        break;
    case 52:
        qm.sendNextPrev("它像極了日本的戰國，可能是因為兩者在平行世界的共鳴。忍者、武士、妖怪，似乎不時地穿行在這2個空間。");
        break;
    case 53:
        qm.sendNextPrev("這段久遠的故事，也包含了太多值得體驗和挑戰的內容。但是目前你不能回，當務之急是要對逆奧之城未來的歷史進行拯救！");
        break;
    case 54:
        qm.sendNextPrev("噢，也許你不是很明白。所謂未來的歷史，對現在的你來說，是未來，對我，它可能已經成為歷史……一個悲劇的歷史……");
        break;
    case 55:
        qm.sendNextPrev("因為它在未來面臨消失……我希望你能幫我改變這一切，我無法離開，所以希望你幫我拯救逆奧之城的未來！");
        break;
    case 56:
        qm.sendNextPrev("你的能力很強，但請讓我在最後見證你真正的力量。");
        break;
    case 57:
        qm.sendNextPrev("要穿梭時空，需要時間的力量。你必須打倒時間神殿的怪物，並將其證明#b#t04000340# 300個#b、#b#t04000342# 1個、#b#t04000343# 1個#b帶來。");
        qm.dispose();
        break;
    case 100:
        if (selection == 0) {
            if (qm.haveItem(4000343, 1) && qm.haveItem(4000340, 300) && qm.haveItem(4000342, 1)) {
                status = 119;
                qm.sendNextPrev("輝煌。。");
            } else {
                qm.sendNext("這些時間旅行者的沙漏數量，還不足以證明你的能力，無法穿越時空。你必須要能獲得更多的證明。");
                qm.dispose();
            }
        } else {
            if (qm.haveItem(5252002, 1)) {
                status = 129;
                qm.sendNextPrev("輝煌。。");
            } else {
                status = 109;
                qm.sendNext("要是沒有可以穿梭時空的能量和證明，是無法前往未來的。因為時空跳轉需要特殊的能量，而且未來力量超強的敵人，沒有實力實在很危險。");
            }
        }
        break;
    case 110:
        qm.sendNextPrev("打倒時間神殿的怪物後，再拿證明過來。");
        break;
    case 111:
        qm.sendPrev("聽說在商城可以買得到特殊的證明…給你更加自由的選擇和機會。竄改歷史的波濤已演變到那個地步了。不能猶豫。照你的意思。");
        qm.dispose();
        break;
    case 120:
        qm.sendNextPrev("現在我要接收你的道具了。");
        break;
    case 124:
        qm.gainItem(4000343, -1);
        qm.gainItem(4000340, -300);
        qm.gainItem(4000342, -1);
        qm.forceStartQuest();
        qm.dispose();
        break;
    case 130:
        qm.sendNextPrev("現在我要接收你的道具了。.");
        break;
    case 131:
        qm.gainItem(5252002, -1);
        qm.forceStartQuest();
        qm.dispose();
        break;
    default:
        qm.dispose();
        break;
    }
}

function end(mode, type, selection) {
}