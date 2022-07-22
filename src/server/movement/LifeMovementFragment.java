package server.movement;

import java.awt.Point;
import tools.data.MaplePacketLittleEndianWriter;

public interface LifeMovementFragment
{
    void serialize(final MaplePacketLittleEndianWriter p0);
    
    Point getPosition();
}
