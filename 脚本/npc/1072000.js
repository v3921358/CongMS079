/*
	This file is part of the OdinMS Maple Story Server
    Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc> 
                       Matthias Butz <matze@odinms.de>
                       Jan Christian Meyer <vimes@odinms.de>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation. You may not use, modify
    or distribute this program under any other version of the
    GNU Affero General Public License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/* Magician Job Instructor
*/

var status;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1)
        cm.dispose();
    else {
        if (mode == 1)
            status++;
        else
            status--;

			if (cm.haveItem(4031008)) {
				if (status == 0)
					cm.sendNext("噢, 你是 #b武術教練#k 介紹來的嗎")
				else if (status == 1)
					cm.sendNextPrev("所以你要證明你的實力嗎 ? 很好...");
				else if (status == 2)
					cm.sendNextPrev("我可以給你一次機會,請你把握.");
				else if (status == 3)
					cm.sendYesNo("請給我 #b30 #t4031013##k. 祝你好運.");
				else if (status == 4) {
					cm.warp(108000300, 0);
					cm.dispose();
				}
			} else {
				cm.sendOk("很抱歉,我需要 #b武術教練的信件#k 請去找武術教練拿取謝謝");
				cm.dispose();
			}
    }
}	