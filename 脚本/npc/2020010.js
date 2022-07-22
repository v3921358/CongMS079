/*
	This file is part of the OdinMS Maple Story Server
    Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc>
		       Matthias Butz <matze@odinms.de>
		       Jan Christian Meyer <vimes@odinms.de>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
//BY MOOGRA
/* Robeira
	Magician 3rd job advancement
	El Nath: Chief's Residence (211000001)
	Custom Quest 100100, 100102
*/

var status = 0;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 1) {
            cm.sendOk("等您下定決心再次找我吧.");
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
		if (cm.getJob() == 311 || cm.getJob() == 321 || cm.getJob() == 312 || cm.getJob() == 322) {	
	    cm.sendOk("您屬於弓箭部,但是您已經成功三轉了,已經超越了教官的強度了!");
	    cm.dispose();
	    return;
		}
            if (!(cm.getJob()==310 ||cm.getJob()==320)) {
				cm.sendOk("請找您的轉職教官,您不屬於弓箭部的滾吧!");
                cm.dispose();
                return;
			} else if (cm.getPlayer().getLevel() < 70) {
				cm.sendOk("你的等級尚未滿70等");
				cm.dispose();
				return;		
            }	
			if (cm.haveItem(4031057, 1)){
                cm.sendNext("恭喜你到達這裡,最後我將給你一個考驗!");			
            } else if (!(cm.haveItem(4031057,1))) {
				cm.warp(100000201);
                cm.sendOk("去找 #r赫麗娜#k 他會幫助你的!");
                cm.dispose();
            } else if (cm.getPlayer().getRemainingSp() <= (cm.getLevel() - 70) * 3) {
                cm.sendNext("你的技能點數還沒點完..");
		} else {
                cm.sendOk("你還不能轉職...");
                cm.dispose();
            }
        } else if (status == 1) {
            if (cm.haveItem(4031058, 1)) {
                if (cm.getJob()==310) {
                    cm.changeJob(311);
                    //cm.getPlayer().gainAp(5);
					cm.gainItem(4031057, -1);
					cm.gainItem(4031058, -1);
					cm.sendOk("恭喜轉職了!");
					cm.worldMessage("『轉職快報』：恭喜玩家."+ cm.getChar().getName() +"  成功三轉-遊俠讓我們熱烈的祝福他/她吧！");
                    cm.dispose();
                } else if (cm.getJob()==320) {
                    cm.changeJob(321);
                    //cm.getPlayer().gainAp(5);
					cm.gainItem(4031057, -1);
					cm.gainItem(4031058, -1);
                    cm.sendOk("恭喜轉職了!");
					cm.worldMessage("『轉職快報』：恭喜玩家."+ cm.getChar().getName() +"  成功三轉-狙擊手讓我們熱烈的祝福他/她吧！");
                    cm.dispose();
                }
            } else if (cm.haveItem(4031057, 1))
                cm.sendAcceptDecline("你準備承擔最終測試??");
            else
                cm.sendAcceptDecline("但是，我可以讓你更加強大。雖然你必須證明不僅是你的實力，但你的知識。你準備好挑戰了嗎？");
        } else if (status == 2) {
            if (cm.haveItem(4031057, 1)) {
                cm.sendOk("去找神聖的石頭測驗吧!!.");
                cm.dispose();
            }
        }
    }
}
