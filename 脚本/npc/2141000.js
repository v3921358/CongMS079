/*
 * Time Temple - Kirston
 * Twilight of the Gods
 */

function start() {
    cm.askAcceptDecline("�����������֮��,�Ҿ����ٻ���ħ��ʦ!\r\n�ȵ�!�����������!Ϊʲô�ٻ����˺�ħ��ʦ?�Ҹо�������ħ��ʦ��ȫ��ͬ�ġ���������!!!!!!!\r\n\r\n #b(���������ʹ��������ȥ.)");
}

function action(mode, type, selection) {
    if (mode == 1) {
        cm.removeNpc(270050100, 2141000);
        cm.forceStartReactor(270050100, 2709000);
    }
    cm.dispose();

// If accepted, = summon PB + Kriston Disappear + 1 hour timer
// If deny = NoTHING HAPPEN
}