var status; 

function start() { 
    status = -1; 
    action(1, 0, 0); 
} 

function action(mode, type, selection) { 
    if (mode == 1) { 
        status++; 
    }else{ 
        status--; 
    } 
    if (status == 0) { 
    if (cm.getPlayer().getJob() == 0) { 
        cm.sendNext("��ӭ���٣���ʼ���������ð�յ��������\r\nѡ��һ��#rְҵ#k ����ﵽ #rLv 10#k (Lv 8 ����ħ��ʦ).\r\n\r\n���仰˵�����ѡ���Լ���δ��֮·!\r\n����õ�һ�� ְҵ,����ʹ�ø��ּ��ܺ�ħ�����⽫������ð�յ���enjoyable.So���飬Ŭ�������Լ�������"); 
    } else { 
        cm.sendOk("���������Ѿ�����ְҵ����!\r\n����ֻ���ɳ�ѧ��ʹ��"); 
        cm.dispose(); 
    } 
    } else if (status == 1) { 
        cm.sendNextPrev("�ҵĽ�ɫ�ǰ������Ϊһ��#rսʿ.#k\r\n\r\nսʿ����ǿ��Ķ�Զ�̹����͸�ǿ�ȣ��Ӷ�ʹ���Ǿ���������ս������ǰ�ء� ����һ��ְҵ �࣬��ÿһ����Ч�Ļ����������ܿ�ʼ���������˸����������һ�θ߼����ܵ�·�����չ�."); 
    } else if (status == 2) { 
        cm.sendNextPrev("ʹ�õ����������� #b��˫�ֽ�#k, #b˫�ֽ�#k, #b������#k �� #b����#k.\r\n\r\n��Ҫ�ȼ�: #r����Lv 10#k\r\nλ��: #rսʿ�ı�����#k��#bPerion#k\r\nְҵ��ʦ: #r����ħ����#k"); 
    } else if (status == 3) { 
        cm.sendSimple("�����Ϊһ��#rսʿ?#k\r\n#b#L0#Yes#l\r\n#L1#No#l#k"); 
    } else if (status == 4) { 
      if (selection == 0) { 
        cm.sendSimple("Ϊ��ʹ ְҵ ��������������#r����ħ����#k ��#rսʿ�ı�����#k��#bPerion#k.�������ھ��������ˣ�������-The�������һ������ ְҵ ����-\r\n\r\n#b#L0#Yes#l\r\n#L1#No#l#k"); 
    } else if (selection == 1) { 
        cm.sendNext("��������κ����ʣ����ٺ���˵����"); 
        cm.dispose(); 
    } 
    } else if (status == 5) { 
      if (selection == 0) { 
        cm.sendNext("Alright.I���ڴ���ȥ#rսʿ�ı�����#k��#bPerion.#k"); 
    } else if (selection == 1) { 
        cm.sendNext("��������κ����ʣ����ٴθ���˵��."); 
        cm.dispose(); 
    } 
  } else if (status == 6) { 
        cm.warp(102000003, 10); 
	cm.dispose();
  } 
}  