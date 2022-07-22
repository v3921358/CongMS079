﻿/* Author: aaroncsn (MapleSea Like)
	NPC Name: 		Mazra
	Map(s): 		The Burning Road: Ariant(2600000000)
	Description: 	Hair Salon Owner
*/

var status = 0;
var beauty = 0;
var mhair = Array(30030, 30020, 30000, 30130, 30350, 30190, 30110, 30180, 30050, 30040, 30160);
var fhair = Array(31050, 31040, 31000, 31060, 31090, 31020, 31130, 31120, 31140, 31330, 31010);
var hairnew = Array();



function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status >= 0) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.sendSimple("嗨，我是#p2100006# 如果你有 #b#t5150027##k 或者 #b#t5151022##k, 可以找我談談。 \r\n#L0#使用:#b#t5150027##k#l \r\n#L1#使用:#b#t5151022##k#l");
		} else if (status == 1) {
			if (selection == 0) {
				beauty = 1;
				hairnew = Array();
				if (cm.getChar().getGender() == 0) {
					for(var i = 0; i < mhair.length; i++) {
						hairnew.push(mhair[i] + parseInt(cm.getChar().getHair() % 10));
					}
				} 
				if (cm.getChar().getGender() == 1) {
					for(var i = 0; i < fhair.length; i++) {
						hairnew.push(fhair[i] + parseInt(cm.getChar().getHair() % 10));
					}
				}
				cm.sendStyle("選擇一個你想要的。", hairnew);
			} else if (selection == 1) {
				beauty = 2;
				haircolor = Array();
				var current = parseInt(cm.getChar().getHair()/10)*10;
				for(var i = 0; i < 8; i++) {
					haircolor.push(current + i);
				}
				cm.sendStyle("選擇一個你想要的。", haircolor);
			}
		}
		else if (status == 2){
			cm.dispose();
			if (beauty == 1){
				if (cm.haveItem(5150027) == true){
					cm.gainItem(5150027, -1);
					cm.setHair(hairnew[selection]);
					cm.sendOk("享受！");
				} else {
					cm.sendNext("痾.... 貌似沒有#t5150027#。");
				}
			}
			if (beauty == 2){
				if (cm.haveItem(5151022) == true){
					cm.gainItem(5151022, -1);
					cm.setHair(haircolor[selection]);
					cm.sendOk("享受！");
				} else {
					cm.sendNext("痾.... 貌似沒有#t5151022#。");
				}
			}
		}
	}
}
