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
		if (cm.getJob() == 211 || cm.getJob() == 221 || cm.getJob() == 231 || cm.getJob() == 212 || cm.getJob() == 222 || cm.getJob() == 232) {	
	    cm.sendOk("您属于法师职业,但是您已经成功三转了,已经超越了教官的强大了!");
	    cm.dispose();
	    return;
		}
            if (!(cm.getJob()==210 ||cm.getJob()==220||cm.getJob()==230)) {
		cm.sendOk("请找您的转职教官,您不属于法师职业!");
                cm.dispose();
                return;
			} else if (cm.getPlayer().getLevel() < 70) {
				cm.sendOk("你的等級尚未滿70等");
				cm.dispose();
				return;		
            }	
			if (cm.haveItem(4031057, 1)){
                cm.sendNext("恭喜你到达这里,最后我将给你一个考验!");			
            } else if (!(cm.haveItem(4031057,1))) {
				cm.warp(101000003);
                cm.sendOk("去找 #r汉斯#k 他会帮助你的!");
                cm.dispose();
            } else if (cm.getPlayer().getRemainingSp() <= (cm.getLevel() - 70) * 3) {
                cm.sendNext("你的技能点数还沒点完..");
		} else {
                cm.sendOk("你还不能转职...");
                cm.dispose();
            }
        } else if (status == 1) {
            if (cm.haveItem(4031058, 1)) {
                if (cm.getJob()==210) {
                    cm.changeJob(211);
                    //cm.getPlayer().gainAp(5);
					cm.gainItem(4031057, -1);
					cm.gainItem(4031058, -1);
					cm.sendOk("恭喜你現在已經成為最帥的魔導士(火.毒)了!");
					cm.worldMessage("『轉職快報』：恭喜玩家."+ cm.getChar().getName() +"  成功三轉-魔導士(火.毒)讓我們熱烈的祝福他/她吧！");
                    cm.dispose();
                } else if (cm.getJob()==220) {
                    cm.changeJob(221);
                    //cm.getPlayer().gainAp(5);
					cm.gainItem(4031057, -1);
					cm.gainItem(4031058, -1);
                    cm.sendOk("恭喜你現在已經成為最帥的魔導士(冰.雷)了!");
					cm.worldMessage("『轉職快報』：恭喜玩家."+ cm.getChar().getName() +"  成功三轉-魔導士(冰.雷)讓我們熱烈的祝福他/她吧！");
					
                    cm.dispose();
                } else if (cm.getJob()==230) {
                    cm.changeJob(231);
                    //cm.getPlayer().gainAp(5);
					cm.gainItem(4031057, -1);
					cm.gainItem(4031058, -1);
                    cm.sendOk("恭喜你現在已經成為最帥的祭司了!");
					cm.worldMessage("『轉職快報』：恭喜玩家."+ cm.getChar().getName() +"  成功三轉-祭司讓我們熱烈的祝福他/她吧！");
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
