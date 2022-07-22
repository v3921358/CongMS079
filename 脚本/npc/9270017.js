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
var status = 0;

function start() {
    cm.sendYesNo("這架飛機將在起飛不久，請問你現在離開？您將有再次購買飛機票到這裡來.");
}

function action(mode, type, selection) {
    if (mode != 1) {
        if (mode == 0)
         cm.sendOk("請再堅持一秒鐘，與平面將起飛。謝謝你的耐心。");
        cm.dispose();
        return;
    }
    status++;
    if (status == 1) {
		cm.warp(103000000, 0); //回墮落城市
        cm.dispose();
    }
	cm.dispose();
}