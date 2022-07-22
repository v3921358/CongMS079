/* 流浪煉金術士 */

var status = 0;
var menu = "";
var set;
var makeitem;
var access = true;
var reqitem = new Array();
var cost = 4000;
var makeditem = new Array(4006000,4006001);
var reqset = new Array(
    [[[4000046,20],[4000027,20],[4021001,1]],
    [[4000025,20],[4000049,20],[4021006,1]],
    [[4000129,15],[4000130,15],[4021002,1]],
    [[4000074,15],[4000057,15],[4021005,1]],
    [[4000054,7],[4000053,7],[4021003,1]],
    [[4000238,15],[4000241,15],[4021000,1]]],
						
    [[[4000028,20],[4000027,20],[4011001,1]],
    [[4000014,20],[4000056,20],[4011003,1]],
    [[4000132,15],[4000128,15],[4011005,1]],
    [[4000074,15],[4000069,15],[4011002,1]],
    [[4000080,7],[4000079,7],[4011004,1]],
    [[4000226,15],[4000237,15],[4011001,1]]]);

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if(mode == 0 && (status ==1 || status == 2)) {
	cm.dispose();
	return;
    }
    if(mode == 0) {
	cm.sendNext("還沒得到需要的材料吧? 但以後隨時有材料就給我吧。打獵,購買等等，得到道具的辦法有多種。");
	cm.dispose();
    }
    if(mode == 1) {
	status++;
    }
    if(status == 0) {
	cm.sendNext("把田鼠的舌頭和雞翅混合1:1… 阿!沒有發光之粉~ 喲? 從什麼時候在哪裡了?");
    } else if(status == 1) {
	cm.sendSimple("我是流浪煉金術士. 還是練習工, 但我能做給你一些東西. 怎麼樣? 想不想跟我交易?\r\n\r\n#b#L0#做#t4006000##l\r\n#b#L1#做#t4006001##l");
    } else if(status == 2) {
	set = selection;
	makeitem = makeditem[set];
	for(i=0; i < reqset[set].length; i++) {
	    menu += "\r\n#L"+i+"##b用#t"+reqset[set][i][0][0]+"#和#t"+reqset[set][i][1][0]+"#做#l";
	}
	cm.sendSimple("呼呼… 只有我能做#b#t"+makeitem+"##k這神秘的石頭. 最近很多冒險者說需要這個. 做#t"+makeitem+"#的辦法一共有6個. 你願意用什麼辦法做?"+menu);
    } else if(status == 3) {
        //cm.playerMessage("當前選擇 status: " + status + " selection: " + selection +"  "+reqset.length);
	if (selection < 0 ) { // || selection >= reqset.length
	    //cm.playerMessage("出現錯誤關閉");
	    cm.dispose();
	    return;
	}
	set = reqset[set][selection];
	reqitem[0] = new Array(set[0][0],set[0][1]);
	reqitem[1] = new Array(set[1][0],set[1][1]);
	reqitem[2] = new Array(set[2][0],set[2][1]);
	menu = "";
	for(i=0; i < reqitem.length; i++) {
	    menu += "\r\n#v"+reqitem[i][0]+"# #t"+reqitem[i][0]+"# #b"+reqitem[i][1]+"個";
	}
	menu += "\r\n#i4031138# #b"+cost+" 金幣#k";
	cm.sendYesNo("為做#b5個#t"+makeitem+"##k需要如下的材料，大多是打獵怪物就會得到的。所以你努力下去，不那麼難得到。怎麼樣? 你真想做嗎?\r\n"+menu);
    } else if(status == 4) {
	for(i=0; i < reqitem.length; i++) {
	    if(!cm.haveItem(reqitem[i][0],reqitem[i][1]))
		access = false;
	}
	if(access == false || !cm.canHold(makeitem) || cm.getMeso() < cost) {
	    cm.sendNext("請你再確認都有需要的道具或背包的其他窗有沒有空間.");
	} else {
	    cm.sendOk("Here, take the 5 pieces of #b#t"+makeitem+"##k. Even I have to admit, this is a masterpiece. Alright, if you need my help down the road, by all means come back and talk to me!");
	    cm.gainItem(reqitem[0][0],-reqitem[0][1]);
	    cm.gainItem(reqitem[1][0],-reqitem[1][1]);
	    cm.gainItem(reqitem[2][0],-reqitem[2][1]);
	    cm.gainMeso(-cost);
	    cm.gainItem(makeitem,5);
	}
	cm.dispose();
    }
}
