var ΢Ц = "#fUI/GuildBBS.img/GuildBBS/Emoticon/Basic/0#";
var ���� = "#fUI/GuildBBS.img/GuildBBS/Emoticon/Basic/1#";
var ���� = "#fUI/GuildBBS.img/GuildBBS/Emoticon/Basic/2#";
var ��ɫ���� = "#fEffect/CharacterEff/1003252/1/0#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            var text = "";
            for (i = 0; i < 100; i++) { 
                text += "";
            }
			 
            var �İ�ʨ������������ = cm.getPlayerCount(551030200);
            var �İ�ʨ������������ս���� = cm.getBossLog("ʨ������������ս����");
            var �������� = cm.getPlayerCount(220080001);
            var ��������ս���� = cm.getBossLog("��������ս����");
            var �������� = cm.getPlayerCount(230040420);
            var ��������ս���� = cm.getBossLog("��������ս����");
            var �������� = cm.getPlayerCount(280030000);
            var ��������ս���� = cm.getBossLog("��������ս����");
            var �������� = cm.getPlayerCount(240060200);
            var ��������ս���� = cm.getBossLog("��������ս����");
            var Ʒ�������� = cm.getPlayerCount(910000022);
            var Ʒ��������ս���� = cm.getBossLog("Ʒ��������ս����");
			var �������� = cm.getPlayerCount(910000021);
            var ��������ս���� = cm.getBossLog("��������ս����");
			var �໢���� = cm.getPlayerCount(910000020);
            var �໢����ս���� = cm.getBossLog("�໢����ս����");
            var ����������� = cm.getPlayerCount(910000019);
            var �����������ս���� = cm.getBossLog("�����������ս����");
            var а�����ֵ����� = cm.getPlayerCount(910000018);
            var а�����ֵ�����ս���� = cm.getBossLog("а�����ֵ�����ս����");
            var ������� = cm.getPlayerCount(910000017);
            var �������ս���� = cm.getBossLog("�������ս����");
            text += "#b�װ���#k [#r" + cm.getPlayer().getName() + "#k] #b��ӭ����#bף����Ϸ���!!#k\r\n";
            text += "#r---------------------ÿ��BOSS��ս---------------------";
            text += "#r\t\t#L13##b���ȵ��Ҳ鿴��ս�����ע������#l#k\r\n\r\n";
            text += "#rBOSS��ս״̬��\r\n";
            text += "#r[�İ�ʨ��|�����ܣ�" + �İ�ʨ������������ + "��]\r\n[���ӣ�" + �������� + "��][������" + �������� + "��]\r\n[������" + �������� + "��][������" + �������� + "��][Ʒ���ͣ�" + Ʒ�������� + "��]\r\n[���ף�" + �������� + "��][�໢��" + �໢���� + "��][������ˣ�" + ����������� + "��]\r\n[а�����ֵܣ�" + а�����ֵ����� + "��][��裺" + ������� + "��]\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b�İ�ʨ��|������(���Ƶȼ�120~250)\r\n[ÿ��2��][����ս��" + �İ�ʨ������������ս���� + "��]\r\n\t\t\t#L1#������ս#l #L2#���ô���#l #L20#������ս��Ʒ#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b����-(���Ƶȼ�120~250)\r\n�����ŵ�3�߿��ٻ���boss\r\n[ÿ��2��][����ս��" + ��������ս���� + "��]\r\n\t\t\t#L3#������ս#l #L4#���ô���#l #L21#������ս��Ʒ#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b����-(���Ƶȼ�120~250)\r\n[ÿ��2��][����ս��" + ��������ս���� + "��]\r\n\t\t\t#L5#������ս#l #L6#���ô���#l #L22#������ս��Ʒ#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b����-(���Ƶȼ�120~250)\r\n[ÿ��2��][����ս��" + ��������ս���� + "��]\r\n\t\t\t#L7#������ս#l #L8#���ô���#l #L17#������ս��Ʒ#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b��������-(���Ƶȼ�140~250)\r\n[ÿ��2��][����ս��" + ��������ս���� + "��]\r\n\t\t\t#L9#������ս#l #L10#���ô���#l #L18#������ս��Ʒ#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#bƷ����-(���Ƶȼ�150~250)5000W�����ս\r\n[ÿ��1��][����ս��" + Ʒ��������ս���� + "��]\r\n\t\t\t#L11#������ս#l #L12#���ô���#l #L19#������ս��Ʒ#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
			//text += "#b����-(���Ƶȼ�200~250)6000W�����ս\r\n[ÿ��3��][����ս��" + ��������ս���� + "��]\r\n\t\t\t#L14#������ս#l #L15#���ô���#l #L24#������ս��Ʒ#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b�໢-(���Ƶȼ�200~250)3000W�����ս\r\n[ÿ��3��][����ս��" + �໢����ս���� + "��]\r\n\t\t\t#L50#������ս#l #L51#���ô���#l #L52#������ս��Ʒ#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b�������-(���Ƶȼ�200~250)3000W�����ս\r\n[ÿ��3��][����ս��" + �����������ս���� + "��]\r\n\t\t\t#L53#������ս#l #L54#���ô���#l #L55#������ս��Ʒ#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#bа�����ֵ�-(���Ƶȼ�200~250)3000W�����ս\r\n[ÿ��3��][����ս��" + а�����ֵ�����ս���� + "��]\r\n\t\t\t#L56#������ս#l #L57#���ô���#l #L58#������ս��Ʒ#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b���-(���Ƶȼ�200~250)3000W�����ս\r\n[ÿ��3��][����ս��" + �������ս���� + "��]\r\n\t\t\t#L59#������ս#l #L60#���ô���#l #L61#������ս��Ʒ#l#k\r\n\r\n";

            cm.sendSimpleS(text, 2);
        } else if (selection == 1) {
            if (cm.getPlayer().getLevel() >= 120) {
                if (cm.getBossLog("ʨ������������ս����") < 2) {
                    if (cm.haveItem(4032246, 1)) {
                        if (cm.getPlayerCount(551030200) <= 0) { // Fant. Map
                            var FantMap = cm.getMap(551030200);
                            FantMap.resetFully();
                            //cm.gainItem(4032246, -1);
                            cm.warpParty(551030200, "sp");
                            cm.getPlayer().setBossLog('ʨ������������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�İ�ʨ���������ܡ��������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(551030200).getSpeedRunStart() == 0 && (cm.getMonsterCount(551030200) <= 0 || cm.getMap(551030200).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainItem(4032246, -1);
                                cm.warpParty(551030200, "sp");
                                cm.getPlayer().setBossLog('ʨ������������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�İ�ʨ���������ܡ��������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��û��#v4032246##z4032246#���޷���ս���뵽�̳��ڹ���");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����120�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 2) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('ʨ������������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����1000��ȯ������ �İ�ʨ���������ܵĽ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����1000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 3) {
            if (cm.getPlayer().getLevel() >= 120) {
                if (cm.getBossLog("��������ս����") < 2) {
                    if (cm.haveItem(4031179, 1)) {
                        if (cm.getPlayerCount(220080001) <= 0) {
                            var FantMap = cm.getMap(220080001);
                            FantMap.resetFully();
                            //cm.gainItem(4031179, -1);
                            cm.warpParty(220080001, "st00");
                            cm.getPlayer().setBossLog('��������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս������ͼ˹���������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(220080001).getSpeedRunStart() == 0 && (cm.getMonsterCount(220080001) <= 0 || cm.getMap(220080001).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainItem(4031179, -1);
                                cm.warpParty(220080001, "st00");
                                cm.getPlayer().setBossLog('��������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս������ͼ˹���������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��û��#v4031179##z4031179#���޷���ս���뵽�̳��ڹ���");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����120�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 4) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('��������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����1000��ȯ������ ������ͼ˹�Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����1000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 5) {
            //230040420
            if (cm.getPlayer().getLevel() >= 120) {
                if (cm.getBossLog("��������ս����") < 2) {
                    if (cm.haveItem(4000175, 1)) {
                        if (cm.getPlayerCount(230040420) <= 0) {
                            var FantMap = cm.getMap(230040420);
                            FantMap.resetFully();
                            cm.gainItem(4000175, -1);
                            cm.warpParty(230040420, "out00");
                            cm.getPlayer().setBossLog('��������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�������������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(230040420).getSpeedRunStart() == 0 && (cm.getMonsterCount(230040420) <= 0 || cm.getMap(230040420).isDisconnected(cm.getPlayer().getId()))) {
                                cm.gainItem(4000175, -1);
                                cm.warpParty(230040420, "out00");
                                cm.getPlayer().setBossLog('��������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�������������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��û��#v4000175##z4000175#���޷���ս���뵽�̳��ڹ���");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����120�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 6) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('��������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����1000��ȯ������ �����Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����1000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 7) {
            //280030000 ��̳
			//211042300 Զ��
            if (cm.getPlayer().getLevel() >= 50) {
                if (cm.getBossLog("��������ս����") < 2) {
                    if (cm.haveItem(4001017, 1)) {
                        if (cm.getPlayerCount(280030000) <= 0) {
                            var FantMap = cm.getMap(280030000);
                            FantMap.resetFully();
                            //cm.gainItem(4001017, -1);
                            cm.warpParty(211042400);
                            cm.getPlayer().setBossLog('��������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�������������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(280030000).getSpeedRunStart() == 0 && (cm.getMonsterCount(280030000) <= 0 || cm.getMap(280030000).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainItem(4001017, -1);
                                cm.warpParty(211042400);
                                cm.getPlayer().setBossLog('��������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�������������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��û��#v4001017##z4001017#���޷���ս���뵽�̳��ڹ���");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����50�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 8) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('��������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����1000��ȯ������ �����Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����1000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 9) {
            //240060200
            //5220006 - �����볡ȯ - ƾ��Ʊ�ɽ��������̽��.ÿ�ν�������1��.
			//240050400Զ��
            if (cm.getPlayer().getLevel() >= 140) {
                if (cm.getBossLog("��������ս����") < 2) {
                    if (cm.haveItem(5220006, 1)) {
                        if (cm.getPlayerCount(240060200) <= 0) {
                            var FantMap = cm.getMap(240060200);
                            FantMap.resetFully();
                            cm.gainItem(5220006, -1);
                            cm.warpParty(240050400);
                            cm.ˢ�µ�ͼ();
                            cm.getPlayer().setBossLog('��������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�����������������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(240060200).getSpeedRunStart() == 0 && (cm.getMonsterCount(240060200) <= 0 || cm.getMap(240060200).isDisconnected(cm.getPlayer().getId()))) {
                                cm.gainItem(5220006, -1);
                                cm.warpParty(240050400);
                                cm.getPlayer().setBossLog('��������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�����������������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��û��#v5220006##z5220006#���޷���ս���빺��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����140�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 10) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('��������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����1000��ȯ������ ���������Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����1000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 11) {
            //270050100
            if (cm.getPlayer().getLevel() >= 150) {
                if (cm.getBossLog("Ʒ��������ս����") < 1) {
                    if (cm.getPlayer().getMeso() >50000000) {
                        if (cm.getPlayerCount(910000022) <= 0) {
                            var FantMap = cm.getMap(910000022);
                            FantMap.resetFully();
                            //cm.gainNX(-1000);
                            cm.gainMeso(-50000000);
                            cm.warpParty(910000022);
							cm.ˢ�µ�ͼ();
							cm.spawnMobStats(8820001,2980000000,300000000);
                            cm.getPlayer().setBossLog('Ʒ��������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��սƷ���͡��������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000022).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000022) <= 0 || cm.getMap(910000022).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainNX(-1000);
                                cm.warpParty(910000022);
                                cm.getPlayer().setBossLog('Ʒ��������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��սƷ���͡��������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��Ҳ���5000W.�޷���ս��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����150�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 12) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('Ʒ��������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����10000��ȯ������ Ʒ���͵Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����10000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 13) {
            cm.sendOk("��ս������\r\n�İ�ʨ��������[ÿ��ÿ��2��]\r\n����[ÿ��ÿ��2��]\r\n����[ÿ��ÿ��2��]\r\n����[ÿ��ÿ��2��]\r\n����[ÿ��ÿ��2��]\r\nƷ����[ÿ��ÿ��1��]\r\n\r\n��ս����\r\n�İ�ʨ��������[��Ҫ��#z4032246#x1��]\r\n����[��Ҫ��#z4031179#x1��]\r\n����[��Ҫ��#z4000175#x1��]\r\n����[��Ҫ��#z4001017#x1��]\r\n��������[��Ҫ��#z5220006#x1��]\r\nƷ����[��Ҫ����ȯx1000]\r\n\r\nע�����\r\n1���뿴��ÿ�տ���ս�Ĵ�������ս;�У�һ�������������ͳ�ȥ��Ҳ��һ�Ρ�\r\n2��ÿ����ս������۵�������ƷŶ~��\r\n3�����������У���򲻹�Թ˭���Լ������Լ���������");
            cm.dispose();
        } else if (selection == 14) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("��������ս����") < 3) {
                    if (cm.getPlayer().getMeso() >60000000) {
                        if (cm.getPlayerCount(910000021) <= 0) {
                            var FantMap = cm.getMap(910000021);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-60000000);
                            cm.warpParty(910000021);
							cm.ˢ�µ�ͼ();
							cm.spawnMobStats(8810000,3280000000,500000000);
                            cm.getPlayer().setBossLog('��������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս���ס��������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000021).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000021) <= 0 || cm.getMap(910000021).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainNX(-1000);
                                cm.warpParty(910000021);
                                cm.getPlayer().setBossLog('��������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս���ס��������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��Ҳ���6000W.�޷���ս��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����180�����޷����롣");
                cm.dispose();
            }
        } else if (selection == 15) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('��������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����10000��ȯ������ ���׵Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����10000�޷�������ս����");
                cm.dispose();
            }
        } else if (selection == 16) {
            if (!cm.canHold(4000175, 1)) {
                cm.sendOk("��������!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 300) {
                cm.gainNX(-300);
                cm.gainItem(4000175, 1);
                cm.sendOk("����ɹ�!");
                cm.����(2,"��ϲ���[" + cm.getPlayer().getName() + "]������300��ȯ����������BOSS����ս��Ʒ��");
                cm.dispose();
            } else {
                cm.sendOk("���ĵ�ȯ����300.�޷�����");
                cm.dispose();
            }
        } else if (selection == 17) {
            if (!cm.canHold(4001017, 1)) {
                cm.sendOk("��������!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 400) {
                cm.gainNX(-400);
                cm.gainItem(4001017, 1);
                cm.sendOk("����ɹ�!");
                cm.����(2,"��ϲ���[" + cm.getPlayer().getName() + "]������400��ȯ����������BOSS����ս��Ʒ��");
                cm.dispose();
            } else {
                cm.sendOk("���ĵ�ȯ����400.�޷�����");
                cm.dispose();
            }
        } else if (selection == 20) {
            if (!cm.canHold(4032246, 1)) {
                cm.sendOk("��������!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 100) {
                cm.gainNX(-100);
                cm.gainItem(4032246, 1);
                cm.sendOk("����ɹ�!");
                cm.����(2,"��ϲ���[" + cm.getPlayer().getName() + "]������100��ȯ�����˱�����/�İ�ʨ��BOSS����ս��Ʒ��");
                cm.dispose();
            } else {
                cm.sendOk("���ĵ�ȯ����100.�޷�����");
                cm.dispose();
            }
        } else if (selection == 21) {
            if (!cm.canHold(4031179, 1)) {
                cm.sendOk("��������!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 200) {
                cm.gainNX(-200);
                cm.gainItem(4031179, 1);
                cm.sendOk("����ɹ�!");
                cm.����(2,"��ϲ���[" + cm.getPlayer().getName() + "]������200��ȯ����������BOSS����ս��Ʒ��");
                cm.dispose();
            } else {
                cm.sendOk("���ĵ�ȯ����200.�޷�����");
                cm.dispose();
            }
        } else if (selection == 22) {
            if (!cm.canHold(4000175, 1)) {
                cm.sendOk("��������!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 300) {
                cm.gainNX(-300);
                cm.gainItem(4000175, 1);
                cm.sendOk("����ɹ�!");
                cm.����(2,"��ϲ���[" + cm.getPlayer().getName() + "]������300��ȯ����������BOSS����ս��Ʒ��");
                cm.dispose();
            } else {
                cm.sendOk("���ĵ�ȯ����300.�޷�����");
                cm.dispose();
            }
        } else if (selection == 18) {
            //5220006 - �����볡ȯ - ƾ��Ʊ�ɽ��������̽��.ÿ�ν�������1��.
            if (!cm.canHold(5220006, 1)) {
                cm.sendOk("��������!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.gainItem(5220006, 1);
                cm.sendOk("����ɹ�!");
                cm.����(2,"��ϲ���[" + cm.getPlayer().getName() + "]������1000��ȯ�����˰��ں���BOSS����ս��Ʒ��");
                cm.dispose();
            } else {
                cm.sendOk("���ĵ�ȯ����1000.�޷�����");
                cm.dispose();
            }
        } else if (selection == 19) {
            cm.sendOk("Ʒ����������ս��Ʒ��ֱ������1000��ȯ��������սһ�Ρ�");
            cm.dispose();

            }
          else if (selection == 24) {
            cm.sendOk("����������ս��Ʒ��ֱ������1000��ȯ��������սһ�Ρ�");
            cm.dispose();
          }else if (selection == 50) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("�໢����ս����") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000020) == 0) {
                            var FantMap = cm.getMap(910000020);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.ˢ�µ�ͼ();
                            cm.warpParty(910000020);
							//cm.ˢ�µ�ͼ();
							cm.spawnMobStats(9601014,2100000000,85019000);
                            cm.getPlayer().setBossLog('�໢����ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�໢���������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000020).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000020) <= 0 || cm.getMap(910000020).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000020) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000020);
                                cm.getPlayer().setBossLog('�໢����ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս�໢���������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��Ҳ���6000W.�޷���ս��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����200�����޷����롣");
                cm.dispose();
            }
          }else if (selection == 51) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('�໢����ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����10000��ȯ������ �໢�Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����10000�޷�������ս����");
                cm.dispose();
            }
          }else if (selection == 52) {
            cm.sendOk("�໢������ս��Ʒ��ֱ������5000W��ң�������սһ�Ρ�");
            cm.dispose();

          }else if (selection == 53) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("�����������ս����") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000019) == 0) {
                            var FantMap = cm.getMap(910000019);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.ˢ�µ�ͼ();
                            cm.warpParty(910000019);
							cm.spawnMobStats(8880000,2100000000,85019000);
                            cm.getPlayer().setBossLog('�����������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս������ˡ��������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000019).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000019) <= 0 || cm.getMap(910000019).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000019) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000019);
                                cm.getPlayer().setBossLog('�����������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս������ˡ��������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��Ҳ���3000W.�޷���ս��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����200�����޷����롣");
                cm.dispose();
            }
          }else if (selection == 54) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('�����������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����10000��ȯ������ ������˵Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����10000�޷�������ս����");
                cm.dispose();
            }
          }else if (selection == 55) {
            cm.sendOk("�������������ս��Ʒ��ֱ������1000W��ң�������սһ�Ρ�");
            cm.dispose();

          }else if (selection == 56) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("а�����ֵ�����ս����") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000018) == 0) {
                            var FantMap = cm.getMap(910000018);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.ˢ�µ�ͼ();
                            cm.warpParty(910000018);
							//cm.ˢ�µ�ͼ();
							cm.spawnMobStats(9601015,2100000000,91019000);
                            cm.getPlayer().setBossLog('а�����ֵ�����ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��սа�����ֵܡ��������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000018).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000018) <= 0 || cm.getMap(910000018).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000018) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000018);
                                cm.getPlayer().setBossLog('а�����ֵ�����ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��սа�����ֵܡ��������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��Ҳ���3000W.�޷���ս��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����200�����޷����롣");
                cm.dispose();
            }
          }else if (selection == 57) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('а�����ֵ�����ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����10000��ȯ������ а�����ֵܵĽ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����10000�޷�������ս����");
                cm.dispose();
            }
          }else if (selection == 58) {
            cm.sendOk("а�����ֵ�������ս��Ʒ��ֱ������3000W��ң�������սһ�Ρ�");
            cm.dispose();

          }else if (selection == 59) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("�������ս����") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000017) == 0) {
                            var FantMap = cm.getMap(910000017);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.ˢ�µ�ͼ();
                            cm.warpParty(910000017);
							//cm.ˢ�µ�ͼ();
							cm.spawnMobStats(9700004,2100000000,85019000);
                            cm.getPlayer().setBossLog('�������ս����');
                            cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս��衣�������ڴ����ı��֡�")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000017).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000017) <= 0 || cm.getMap(910000017).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000017) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000017);
                                cm.getPlayer().setBossLog('�������ս����');
                                cm.����(2, "���[" + cm.getPlayer().getName() + "]��ʼ��ս��衣�������ڴ����ı��֡�")
                                cm.dispose();
                            } else {
                                cm.sendOk("�����ս���Ѿ���ʼ�ˣ���ȴ���");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("��Ҳ���3000W.�޷���ս��");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("����յ���ս�����Ѿ����ꡣ");
                    cm.dispose();
                }
            } else {
                cm.sendOk("�ȼ�����200�����޷����롣");
                cm.dispose();
            }
          }else if (selection == 60) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('�������ս����');
                cm.sendOk("������ս�����ɹ�����");
                cm.����(2, "��ϲ���[" + cm.getPlayer().getName() + "]����10000��ȯ������ ���Ľ�����ս������")
                cm.dispose();
            } else {
                cm.sendOk("��ȯ����10000�޷�������ս����");
                cm.dispose();
            }
          }else if (selection == 61) {
            cm.sendOk("���������ս��Ʒ��ֱ������3000W��ң�������սһ�Ρ�");
            cm.dispose();

            }
			
    }
}

			

