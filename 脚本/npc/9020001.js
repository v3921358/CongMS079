
/* ==================
 �ű�����: ��������	    
 �ű����ߣ�����   
 ��ϵ��ʽ��1500663066
 =====================
 */




var status;
var curMap;
var playerStatus;
var chatState;
var questions = Array("���ʷ�ʦһתҪ���ٵȼ�",
    "���ʷ���һתҪ���ٵȼ�",
    "���ʷ�ʦתְ��Ҫ��������",
    "���ʹ�����תְ��Ҫ��������");
    //"���ʶ��ټ����ܽ��ж�ת");
    //"����սʿһתҪ��������");
var qanswers = Array(8, 10, 20, 25);
var party;
var preamble;
var stage2rects = Array(new java.awt.Rectangle(-770,-132,28,178),new java.awt.Rectangle(-733,-337,26,105),new java.awt.Rectangle(-601,-328,29,105),new java.awt.Rectangle(-495,-125,24,165));
var stage2combos = Array(Array(0,1,1,1),Array(1,0,1,1),Array(1,1,0,1),Array(1,1,1,0));
var stage3rects = Array(new java.awt.Rectangle(608,-180,140,50),new java.awt.Rectangle(791,-117,140,45),new java.awt.Rectangle(958,-180,140,50),new java.awt.Rectangle(876,-238,140,45),new java.awt.Rectangle(702,-238,140,45));
var stage3combos = Array(Array(0,0,1,1,1),Array(0,1,0,1,1),Array(0,1,1,0,1),Array(0,1,1,1,0),Array(1,0,0,1,1),Array(1,0,1,0,1),Array(1,0,1,1,0),Array(1,1,0,0,1),Array(1,1,0,1,0),Array(1,1,1,0,0));
var stage4rects = Array(new java.awt.Rectangle(910,-236,35,5),new java.awt.Rectangle(877,-184,35,5),new java.awt.Rectangle(946,-184,35,5),new java.awt.Rectangle(845,-132,35,5),new java.awt.Rectangle(910,-132,35,5),new java.awt.Rectangle(981,-132,35,5));
var stage4combos = Array(Array(0,0,0,1,1,1),Array(0,0,1,0,1,1),Array(0,0,1,1,0,1),Array(0,0,1,1,1,0),Array(0,1,0,0,1,1),Array(0,1,0,1,0,1),Array(0,1,0,1,1,0),Array(0,1,1,0,0,1),Array(0,1,1,0,1,0),Array(0,1,1,1,0,0),Array(1,0,0,0,1,1),Array(1,0,0,1,0,1),Array(1,0,0,1,1,0),Array(1,0,1,0,0,1),Array(1,0,1,0,1,0),Array(1,0,1,1,0,0),Array(1,1,0,0,0,1),Array(1,1,0,0,1,0),Array(1,1,0,1,0,0),Array(1,1,1,0,0,0));
var eye = 9300002;
var necki = 9300000;
var slime = 9300003;
var monsterIds = Array(eye, eye, eye, necki, necki, necki, necki, necki, necki, slime);
var prizeIdScroll = Array(2040502, 2040505,			// ����
    2040802,										// Gloves for DEX
    2040002, 2040402, 2040602);						// Helmet, Topwear and Bottomwear for DEF
var prizeIdUse = Array(2000001, 2000002, 2000003, 2000006,	// ��ɫ����ɫ����ɫҩ����ħ��ҩ��
    2000004, 2022000, 2022003);						// Elixir, Pure Water and Unagi
var prizeQtyUse = Array(80, 80, 80, 50,
    5, 15, 15);
var prizeIdEquip = Array(1032004, 1032005, 1032009,			// Level 20-25 ���װ���ĵط�
    1032006, 1032007, 1032010,						// Level 30 ����
    1032002,										// Level 35 ����
    1002026, 1002089, 1002090);						// ñ��
var prizeIdEtc = Array(4010000, 4010001, 4010002, 4010003,	// ��ʯ��
    4010004, 4010005, 4010006,						// Mineral Ores
    4020000, 4020001, 4020002, 4020003,				// Jewel Ores
    4020004, 4020005, 4020006,						// Jewel Ores
    4020007, 4020008, 4003000);						// Diamond and Black Crystal Ores and Screws
var prizeQtyEtc = Array(15, 15, 15, 15,
    8, 8, 8,
    8, 8, 8, 8,
    8, 8, 8,
    3, 3, 30);

var xmitem = new Array(4002000,4002001,4002003,4001266,4021004); //"+cm.serverName()+"-�¼����ȡ��Ʒ
var xmitemsl = new Array(1); //"+cm.serverName()+"-�¼����ȡ��Ʒ����

function start() {
    status = -1;
    mapId = cm.getMapId();
    if (mapId == 103000800)
	curMap = 1;
    else if (mapId == 103000801)
	curMap = 2;
    else if (mapId == 103000802)
	curMap = 3;
    else if (mapId == 103000803)
	curMap = 4;
    else if (mapId == 103000804)
	curMap = 5;
    playerStatus = cm.isLeader();
    preamble = null;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    
    if (curMap == 1) { // First Stage.
		if (playerStatus) { // Check if player is leader
			if (status == 0) {
			var eim = cm.getEventInstance();
			party = eim.getPlayers();
			preamble = eim.getProperty("leader1stpreamble");

			if (preamble == null) {
				cm.sendNext("�ˣ�����#p9020001# ��ӭ�����������PQ ����Ҫ��Ա��ͨ��֤����ж�Ա�ռ���ɺ�ѿ�Ƭ�ռ�����Ȼ����ҡ�");
				eim.setProperty("leader1stpreamble", "done");
				cm.dispose();
			} else { // Check how many they have compared to number of party members
				// Check for stage completed
				var complete = eim.getProperty(curMap.toString() + "stageclear");
				if (complete != null) {
					cm.sendNext("��ϲ������ ͨ����һ�׶ε����ѿ���!");
					cm.dispose();
				} else {
					var numpasses = (party.size() - 1) < 1 ? 1 : (party.size() - 1);
					var strpasses = "#b" + numpasses.toString() + " ��ͨ��֤#k";
					if (!cm.haveItem(4001008, numpasses)) {
						cm.sendNext("�Һܱ�Ǹ�Ҳ���������أ�����Ҫ: " + strpasses + " ������֮���Ҿͻ�������ء�");
						cm.dispose();
					} else {
						cm.sendNext("���ռ� " + strpasses + "! ��ϲ���ء�");
						clear(1,eim,cm);
						cm.givePartyExp(5000, party);//��һ��
						cm.gainItem(4001008, -numpasses);
						cm.dispose();
					// TODO: Make the shiny thing flash
					}
				}
			}
			}
		} else { // ���Ƕӳ�
			var eim = cm.getChar().getEventInstance();
			pstring = "member1stpreamble" + cm.getChar().getId().toString();
			preamble = eim.getProperty(pstring);
			if (status == 0 && preamble == null) {
			var qstring = "member1st" + cm.getChar().getId().toString();
			var question = eim.getProperty(qstring);
			if (question == null) {
				// Select a random question to ask the player.
				var questionNum = Math.floor(Math.random() * questions.length);
				eim.setProperty(qstring, questionNum.toString());
			}
			cm.sendNext("���������Ҫ�ռ� #b�Ż�ȯ#k �������������Ŀ��ͬ�Ĵ�Ϊ������������⡣");
			} else if (status == 0) { // Otherwise, check for stage completed
				var complete = eim.getProperty(curMap.toString() + "stageclear");
				if (complete != null) {
		//		    cm.sendNext("Please hurry on to the next stage, the portal opened!");
					cm.dispose();
			} else {
				// Reply to player correct/incorrect response to the question they have been asked
				var qstring = "member1st" + cm.getChar().getId().toString();
				var numcoupons = qanswers[parseInt(eim.getProperty(qstring))];
				var qcorr = cm.haveItem(4001007,(numcoupons+1));
				var enough = false;
				if (!qcorr) { // Not too many
					qcorr = cm.haveItem(4001007, numcoupons);
					if (qcorr) { // Just right
						cm.sendNext("���������Ҵ�Ӧ��� #b#t4001008##k. �����ȥ����Ķӳ��ɡ�");
						cm.gainItem(4001007, -numcoupons);
						cm.gainItem(4001008, 1);
						enough = true;
					}
				}
				if (!enough) {
					cm.sendNext("�Һܱ�Ǹ���������ǲ���ȷ�Ĵ𰸣�������������ȷ��������");
				}
				cm.dispose();
			}
			} else if (status == 1) {
				if (preamble == null) {
					var qstring = "member1st" + cm.getChar().getId().toString();
					var question = parseInt(eim.getProperty(qstring));
					cm.sendNextPrev(questions[question]);
				} else { // Shouldn't happen, if it does then just dispose
					cm.dispose();
				}
			} else if (status == 2) { // Preamble completed
				eim.setProperty(pstring,"done");
				cm.dispose();
			} else { // Shouldn't happen, but still...
				eim.setProperty(pstring,"done"); // Just to be sure
				cm.dispose();
			}
		} // End first map scripts
    } else if (2 <= curMap && 4 >= curMap) {
		rectanglestages(cm);
    } else if (curMap == 5) { // Final stage
	var eim = cm.getChar().getEventInstance();
	var stage5done = eim.getProperty("5stageclear");
	if (stage5done == null) {
	    if (playerStatus) { // Leader
		var passes = cm.haveItem(4001008,10);
		if (passes) {
		    // Clear stage
		    cm.sendNext("��ϲ���أ�");
		    party = eim.getPlayers();
		    cm.gainItem(4001008, -10);
		    clear(5,eim,cm);
		    cm.givePartyExp(10000, party);//���һ�ؾ���
		    cm.dispose();
		} else { // Not done yet
		    cm.sendNext("��ӭ�������ս׶���ֻҪ��ͨ��֤�ռ����������Ҿ����ˣ�");
		}
		cm.dispose();
	    } else { // Members
		cm.sendNext("��ӭ�������ս׶�~������ֻҪ�����е�ͨ��֤�����ӳ������ˣ�");
		cm.dispose();
	    }
	} else { // Give rewards and warp to bonus
	    if (status == 0) {
		cm.sendNext("��ĺܲ���˼�飡");
	    }
	    if (status == 1) {
		getPrize(eim,cm);
		cm.dispose();
	    }
	}
    } else { // No map found
	cm.sendNext("��Ч�ĵ�ͼ��������GM��");
	cm.dispose();
    }
}

function clear(stage, eim, cm) {
    eim.setProperty(stage.toString() + "stageclear","true");

    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");

    var mf = eim.getMapFactory();
    map = mf.getMap(103000800 + stage);
    var nextStage = eim.getMapFactory().getMap(103000800 + stage);
    var portal = nextStage.getPortal("next00");
    if (portal != null) {
	portal.setScriptName("kpq" + (stage+1).toString());
    }
}

function failstage(eim, cm) {
    cm.showEffect(true, "quest/party/wrong_kor");
    cm.playSound(true, "Party1/Failed");
}

function rectanglestages (cm) {
    // Debug makes these stages clear without being correct
    var debug = false;
    var eim = cm.getChar().getEventInstance();
    if (curMap == 2) {
	var nthtext = "2";
	var nthobj = "����";
	var nthverb = "��";
	var nthpos = "��������̫��";
	var curcombo = stage2combos;
	var currect = stage2rects;
	var objset = [0,0,0,0];
    } else if (curMap == 3) {
	var nthtext = "3";
	var nthobj = "ƽ̨";
	var nthverb = "վ";
	var nthpos = "վ��̫������Ե";
	var curcombo = stage3combos;
	var currect = stage3rects;
	var objset = [0,0,0,0,0];
    } else if (curMap == 4) {
	var nthtext = "4";
	var nthobj = "��Ͱ";
	var nthverb = "վ";
	var nthpos = "վ��̫������Ե";
	var curcombo = stage4combos;
	var currect = stage4rects;
	var objset = [0,0,0,0,0,0];
    }
    if (playerStatus) { // Check if player is leader
	if (status == 0) {
	    // Check for preamble
	    party = eim.getPlayers();
	    preamble = eim.getProperty("leader" + nthtext + "preamble");
	    if (preamble == null) {
		cm.sendNext("�ˣ���ӭ������ " + nthtext + " �׶�. �����Աߣ���ῴ��һЩ " + nthobj + ", #b����Ҫ������Ա����������ҵĴ𰸣�����¶Ծ�������أ����Ͱɣ� \r\n�~���˲���#r" + nthpos + "��Ȼ�᲻�ܹ���Ŷ��");
		eim.setProperty("leader" + nthtext + "preamble","done");
		var sequenceNum = Math.floor(Math.random() * curcombo.length);
		eim.setProperty("stage" + nthtext + "combo",sequenceNum.toString());
		cm.dispose();
	    } else {
		// Otherwise, check for stage completed
		var complete = eim.getProperty(curMap.toString() + "stageclear");
		if (complete != null) {
		    var mapClear = curMap.toString() + "stageclear";
		    eim.setProperty(mapClear,"true"); // Just to be sure
//		    cm.sendNext("Please hurry on to the next stage, the portal opened!");
		    cm.dispose();
		} else { // Check for people on ropes and their positions
		    var totplayers = 0;
		    for (i = 0; i < objset.length; i++) {
			for (j = 0; j < party.size(); j++) {
			    var present = currect[i].contains(party.get(j).getPosition());
			    if (present) {
				objset[i] = objset[i] + 1;
				totplayers = totplayers + 1;
			    }
			}
		    }
		    // Compare to correct positions
		    // First, are there 3 players on the correct positions?
		    if (totplayers == 3 || debug) {
			var combo = curcombo[parseInt(eim.getProperty("stage" + nthtext + "combo"))];
			// Debug
			// Combo = curtestcombo;
			var testcombo = true;
			for (i = 0; i < objset.length; i++) {
			    if (combo[i] != objset[i])
				testcombo = false;
			}
			if (testcombo || debug) {
			    // Do clear
			    clear(curMap,eim,cm);
			    //var exp = (Math.pow(2,curMap) * 50);
                            var exp = 0;
							if(curMap == 2){
							exp = 5000;//�ڶ���
							}else if(curMap == 3){
							exp = 10000;//������
							}else if(curMap == 4){
							exp = 20000;//���Ĺ�
							}
                            cm.givePartyExp(exp, party);//������
			    cm.dispose();
			} else { // Wrong
			    // Do wrong
			    failstage(eim,cm);
			    cm.dispose();
			}
		    } else {
			if (debug) {
			    var outstring = "Objects contain:"
			    for (i = 0; i < objset.length; i++) {
				outstring += "\r\n" + (i+1).toString() + ". " + objset[i].toString();
			    }
			    cm.sendNext(outstring);
			} else {
			    cm.sendNext("��վ��λ�ã�����վ���м�ͺã�");
			}
			cm.dispose();
		    }
		}
	    }
	} else {
	    var complete = eim.getProperty(curMap.toString() + "stageclear");
	    if (complete != null) {
		var target = eim.getMapInstance(103000800 + curMap);
		var targetPortal = target.getPortal("st00");
		cm.getChar().changeMap(target, targetPortal);
	    }
	    cm.dispose();
	}
    } else { // Not leader
	if (status == 0) {
	    var complete = eim.getProperty(curMap.toString() + "stageclear");
	    if (complete != null) {
		cm.dispose();
//		cm.sendNext("Please hurry on to the next stage, the portal opened!");
	    } else {
//		cm.sendNext("Please have the party leader talk to me.");
		cm.dispose();
	    }
	} else {
	    var complete = eim.getProperty(curMap.toString() + "stageclear");
	    if (complete != null) {
		var target = eim.getMapInstance(103000800 + curMap);
		var targetPortal = target.getPortal("st00");
		cm.getChar().changeMap(target, targetPortal);
	    }
	    cm.dispose();
	}
    }
}

function getPrize(eim,cm) {
    var itemSetSel = Math.random();
    var itemSet;
    var itemSetQty;
    var hasQty = false;
	var ItemName;
	var item;
    if (itemSetSel < 0.3)
	itemSet = prizeIdScroll;
    else if (itemSetSel < 0.6)
	itemSet = prizeIdEquip;
    else if (itemSetSel < 0.9) {
	itemSet = prizeIdUse;
	itemSetQty = prizeQtyUse;
	hasQty = true;
    } else {
	itemSet = prizeIdEtc;
	itemSetQty = prizeQtyEtc;
	hasQty = true;
    }
    var sel = Math.floor(Math.random()*itemSet.length);
    var qty = 1;
    if (hasQty)
	qty = itemSetQty[sel];
	ItemName = cm.getItemName(itemSet[sel]);
	item = new Packages.client.inventory.Item(itemSet[sel], 0, qty, 0);
    cm.gainItem(itemSet[sel], qty);//������������콱����ȡ��Ʒ�ĵط�
	
	/*if (cm.isLeader()) {  //�ж϶ӳ�
		cm.setAccYjLog("�ۺϻ���");
		}*/
	gain����("��������",+1);
	gain����("��������",+1);
	cm.getPlayer().setBossLog("��������");//��һ��������
    cm.gainItem(4000463,1);
	cm.gainItem(4170002,1);	
	cm.gainItem(4001126,3);	
	cm.gainItem(4010000,1);	
	cm.gainItem(4010001,1);	
	cm.gainItem(4010002,1);	
	cm.gainMeso(+50000);//���
	//cm.gainItem(xmitem[Math.floor(Math.random() * xmitem.length)],1); //"+cm.serverName()+"�¼��������ȡ��Ʒ	
    cm.removeAll(4001007);
    cm.removeAll(4001008);
    cm.getPlayer().endPartyQuest(1201);
    //cm.setBossLog("fbkr1");
	var msg = "x"+qty+" ��ϲ���-"+ cm.getPlayer().getName() + "-��ɷ�����Ӹ���,��ҹ�ϲ�ɣ�����";
	if(itemSet[sel] / 1000 == 1032){
		World.Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("��������ӡ� ", " : " + msg, item,  0, cm.getPlayer().getClient().getChannel()).getBytes());
	}    
	

    cm.warp(103000805, "sp");
}

function get����(a) {
	var jf = 0;
		switch (a) {
		case "��������":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			break;
		case "��߻���":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			break;
		case "��ջ���":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			break;
		case "���л���":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			break;
		case "���»���":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			break;
		}

		return jf;
}

function gain����(a,b) {
	var jf = 0;
		switch (a) {
		case "��������":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			cm.getQuestRecord(844440).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��߻���":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			cm.getQuestRecord(844441).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��ջ���":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			cm.getQuestRecord(844442).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			cm.getQuestRecord(844443).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			cm.getQuestRecord(844444).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			cm.getQuestRecord(844445).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "���л���":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			cm.getQuestRecord(844446).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			cm.getQuestRecord(844447).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "���»���":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			cm.getQuestRecord(844450).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		}

}