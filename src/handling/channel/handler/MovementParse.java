package handling.channel.handler;

import java.util.Iterator;
import server.movement.LifeMovement;
import server.maps.AnimatedMapleMapObject;
import server.movement.StaticLifeMovement;
import java.awt.Point;
import java.util.ArrayList;
import server.movement.LifeMovementFragment;
import java.util.List;
import tools.data.LittleEndianAccessor;

public class MovementParse
{
    public static final List<LifeMovementFragment> parseMovement(final LittleEndianAccessor lea, final int kind) {
        final List<LifeMovementFragment> res = new ArrayList<LifeMovementFragment>();
        final byte numCommands = lea.readByte();
        if (numCommands <= 0) {
            return null;
        }
        for (byte i = 0; i < numCommands; ++i) {
            final byte command = lea.readByte();
            switch (command) {
                case 0:
                case 5:
                case 15:
                case 17: {
                    final short xpos = lea.readShort();
                    final short ypos = lea.readShort();
                    final short xwobble = lea.readShort();
                    final short ywobble = lea.readShort();
                    final short unk = lea.readShort();
                    short fh = 0;
                    if (command == 15) {
                        fh = lea.readShort();
                    }
                    final byte newstate = lea.readByte();
                    final short duration = lea.readShort();
                    final StaticLifeMovement mov = new StaticLifeMovement((int)command, new Point((int)xpos, (int)ypos), (int)duration, (int)newstate, (int)unk);
                    mov.setUnk(unk);
                    mov.setFh(fh);
                    mov.setPixelsPerSecond(new Point((int)xwobble, (int)ywobble));
                    res.add(mov);
                    break;
                }
                case 1:
                case 2:
                case 6:
                case 12:
                case 13:
                case 16:
                case 18:
                case 19:
                case 22: {
                    final short xwobble2 = lea.readShort();
                    final short ywobble2 = lea.readShort();
                    final byte newstate2 = lea.readByte();
                    final short duration2 = lea.readShort();
                    final StaticLifeMovement mov2 = new StaticLifeMovement((int)command, null, (int)duration2, (int)newstate2, 0);
                    mov2.setPixelsPerSecond(new Point((int)xwobble2, (int)ywobble2));
                    res.add(mov2);
                    break;
                }
                case 3:
                case 4:
                case 7:
                case 8:
                case 9:
                case 11: {
                    final short xpos = lea.readShort();
                    final short ypos = lea.readShort();
                    final short unk2 = lea.readShort();
                    final byte newstate3 = lea.readByte();
                    final short duration3 = lea.readShort();
                    final StaticLifeMovement mov3 = new StaticLifeMovement((int)command, new Point((int)xpos, (int)ypos), 0, (int)newstate3, 0);
                    mov3.setUnk(unk2);
                    res.add(mov3);
                    break;
                }
                case 10: {
                    final byte newstate4 = 0;
                    final short duration4 = 0;
                    final int wui = lea.readByte();
                    final StaticLifeMovement mov4 = new StaticLifeMovement((int)command, null, 0, 0, 0);
                    mov4.setWui(wui);
                    res.add(mov4);
                    break;
                }
                case 14: {
                    final short xwobble2 = lea.readShort();
                    final short ywobble2 = lea.readShort();
                    final short fh2 = lea.readShort();
                    final byte newstate3 = lea.readByte();
                    final short duration3 = lea.readShort();
                    final StaticLifeMovement mov3 = new StaticLifeMovement((int)command, null, (int)duration3, (int)newstate3, 0);
                    mov3.setPixelsPerSecond(new Point((int)xwobble2, (int)ywobble2));
                    mov3.setFh(fh2);
                    res.add(mov3);
                    break;
                }
                default: {
                    final byte newstate4 = lea.readByte();
                    final short duration4 = lea.readShort();
                    final StaticLifeMovement mov5 = new StaticLifeMovement((int)command, null, (int)duration4, (int)newstate4, 0);
                    res.add(mov5);
                    break;
                }
            }
        }
        double skip = (double)lea.readByte();
        skip = Math.ceil(skip / 2.0);
        lea.skip((int)skip);
        if (numCommands != res.size()) {
            System.err.println("error in movement");
            return null;
        }
        return res;
    }
    
    public static final void updatePosition(final List<LifeMovementFragment> movement, final AnimatedMapleMapObject target, final int yoffset) {
        for (final LifeMovementFragment move : movement) {
            if (move instanceof LifeMovement) {
                if (move instanceof StaticLifeMovement) {
                    final Point position = ((StaticLifeMovement)move).getPosition();
                    if (position != null) {
                        final Point point = position;
                        point.y += yoffset;
                        target.setPosition(position);
                    }
                }
                target.setStance(((StaticLifeMovement)move).getNewstate());
            }
        }
    }
}
