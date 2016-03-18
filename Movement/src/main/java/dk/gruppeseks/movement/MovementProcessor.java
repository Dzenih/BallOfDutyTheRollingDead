/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.gruppeseks.movement;

import dk.gruppeseks.bodtrd.common.data.Entity;
import dk.gruppeseks.bodtrd.common.data.GameData;
import dk.gruppeseks.bodtrd.common.data.entityelements.Position;
import dk.gruppeseks.bodtrd.common.data.entityelements.Velocity;
import dk.gruppeseks.bodtrd.common.data.util.Vector2;
import dk.gruppeseks.bodtrd.common.interfaces.IEntityProcessor;
import java.util.Map;

/**
 *
 * @author Morten
 */
public class MovementProcessor implements IEntityProcessor
{
    @Override
    public void process(GameData gameData, Map<Integer, Entity> world)
    {
        double dt = gameData.getDeltaTime();

        for (Entity e : world.values())
        {
            Position p = e.get(Position.class);
            Velocity velocity = e.get(Velocity.class);

            if (p == null || velocity == null)
            {
                continue;
            }

            Vector2 v = velocity.getVector();

            p.setX(p.getX() + (v.getX() * dt));
            p.setY(p.getY() + (v.getY() * dt));
        }
    }
}
