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


function start() {
    if (cm.haveItem(4031013,30)) {
        cm.sendNext("wow 果然是個大俠恭喜通過這次個考驗 你已經是個強大的盜賊了所以我將頒贈給你神秘的小禮物.");
    } else {
        cm.sendOk("你還沒有 #b30 #t4031013##k. 請收集完畢再來找我,祝你好運.");
        cm.dispose();
    }
}

function action(mode, type, selection) {
    if (mode == 1) {
        cm.warp(103000003, 0);
		cm.removeAll(4031013);
		cm.gainItem(4031011, -1);
		cm.gainItem(4031012, 1);
	}
	cm.dispose();
}