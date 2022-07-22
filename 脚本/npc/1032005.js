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
/**
-- Odin JavaScript --------------------------------------------------------------------------------
	VIP Cab - Victoria Road : Lith Harbor (104000000)
-- By ---------------------------------------------------------------------------------------------
	Xterminator
-- Version Info -----------------------------------------------------------------------------------
	1.0 - First Version by Xterminator
---------------------------------------------------------------------------------------------------
**/
var status = 0;
var cost;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status >= 1 && mode == 0){
	cm.sendNext("這個鎮也有很多提供。找到我們如果當你覺得有必要去到螞蟻隧道");
	cm.dispose();
	return;	
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendNext("你好！這是出租車只有VIP客戶。而不是只帶你到不同的城鎮，如正規出租車，我們提供一個更好的服務值得貴賓級的。這是一個有點貴，但是對於......只有10,000 楓幣，我們會帶你安全地送到\n #b螞蟻礦坑#k.");
    } else if (status == 1) {
	var job = cm.getJob();
	if (job == 0 || job == 2000 || job == 1000) {
	    cm.sendYesNo("我們有對新手90%的打折 所以你只需要花 #b1,000 楓幣#k 是否要去了呢??");
	    cost = 1000;
	} else {
	    cm.sendYesNo("到了那邊有個24小時的排檔可以購買補品 #b10,000 楓幣#k?");
	    cost = 10000;
	}
    } else if (status == 2) {
	if (cm.getMeso() < cost) {
	    cm.sendNext("請確認你是否有足夠的楓幣!")
	} else {
	    cm.gainMeso(-cost);
	    cm.warp(105070001, 0);
	}
	cm.dispose();
    }
}