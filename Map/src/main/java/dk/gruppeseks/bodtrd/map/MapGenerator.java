/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.gruppeseks.bodtrd.map;

import dk.gruppeseks.bodtrd.common.data.Entity;
import dk.gruppeseks.bodtrd.common.data.EntityType;
import dk.gruppeseks.bodtrd.common.data.ViewManager;
import dk.gruppeseks.bodtrd.common.data.entityelements.Body;
import dk.gruppeseks.bodtrd.common.data.entityelements.Body.Geometry;
import dk.gruppeseks.bodtrd.common.data.entityelements.Position;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Thanusaan
 */
public class MapGenerator
{
    private static Random _rand = new Random(); // TODO needs to be able to pick seed

    private static int _pathUp = 20;
    private static int _pathRight = 40;
    private static int _pathDown = 60;
    private static int _pathLeft = 80;

    public static void generateMap(List<Entity> walls, int mapWidth, int mapHeight, int wallSize, int wallAmount)
    {
        Entity[] borderWalls = createBorderWalls(wallSize, mapWidth, mapHeight);
        walls.add(borderWalls[0]);
        walls.add(borderWalls[1]);
        walls.add(borderWalls[2]);
        walls.add(borderWalls[3]);
        
        int mapGridX = mapWidth / wallSize;
        int mapGridY = mapHeight / wallSize;

        Position pos = new Position(0, 0);

        int wallCount = 0;

        while (true)
        {
            while (true)
            {
                pos.setX((_rand.nextInt(mapGridX)) * wallSize);
                pos.setY((_rand.nextInt(mapGridY)) * wallSize);
                if (checkValidWall(pos, walls, mapWidth, mapHeight, wallSize))
                {
                    walls.add(createWall(pos, wallSize));
                    ++wallCount;
                    break;
                }
            }
            while (true)
            {
                Position pos2 = new Position(pos.getX(), pos.getY());
                int r = _rand.nextInt(100);

                if (r < _pathUp)
                {
                    pos2.setY(pos.getY() - wallSize);
                }
                else if (r < _pathRight)
                {
                    pos2.setX(pos.getX() + wallSize);
                }
                else if (r < _pathDown)
                {
                    pos2.setY(pos.getY() + wallSize);
                }
                else if (r < _pathLeft)
                {
                    pos2.setX(pos.getX() - wallSize);
                }
                else
                {
                    break;
                }
                if (checkValidWall(pos2, walls, mapWidth, mapHeight, wallSize))
                {
                    walls.add(createWall(pos2, wallSize));
                    pos = pos2;
                    ++wallCount;
                }

                if (wallCount >= wallAmount)
                {
                    break;
                }
            }
            if (wallCount >= wallAmount)
            {
                break;
            }
        }
    }

    private static boolean checkValidWall(Position newWallPos, List<Entity> walls, int mapWidth, int mapHeight, int wallSize)
    {
        if (newWallPos.getX() <= 0 || newWallPos.getY() <= 0 || newWallPos.getX() >= mapWidth - wallSize || newWallPos.getY() >= mapHeight - wallSize)
        {
            return false;
        }
        for (Entity w : walls)
        {
            if (newWallPos.equals(w.get(Position.class)))
            {
                return false;
            }
        }
        return true;
    }

    private static Entity createWall(Position pos, int wallSize)
    {
        Entity wall = new Entity();
        wall.setType(EntityType.WALL);

        wall.add(new Position(pos.getX(), pos.getY()));
        wall.add(new Body(wallSize, wallSize, Geometry.RECTANGLE));
        wall.add(ViewManager.getView(MapPlugin.WALL_IMAGE_FILE_PATH));

        return wall;
    }
    
    private static Entity[] createBorderWalls(int wallSize, int mapWidth, int mapHeight)
    {
        Entity wallBot = new Entity();
        wallBot.setType(EntityType.WALL);
        wallBot.add(new Position(0, 0));
        wallBot.add(new Body(wallSize, mapWidth, Geometry.RECTANGLE));
        wallBot.add(ViewManager.getView(MapPlugin.BORDER_IMAGE_FILE_PATH));
        
        Entity wallTop = new Entity();
        wallTop.setType(EntityType.WALL);
        wallTop.add(new Position(0, mapHeight - wallSize));
        wallTop.add(new Body(wallSize, mapWidth, Geometry.RECTANGLE));
        wallTop.add(ViewManager.getView(MapPlugin.BORDER_IMAGE_FILE_PATH));
        
        Entity wallLeft = new Entity();
        wallLeft.setType(EntityType.WALL);
        wallLeft.add(new Position(0, wallSize));
        wallLeft.add(new Body(mapHeight - wallSize * 2, wallSize, Geometry.RECTANGLE));
        wallLeft.add(ViewManager.getView(MapPlugin.BORDER_IMAGE_FILE_PATH));
        
        Entity wallRight = new Entity();
        wallRight.setType(EntityType.WALL);
        wallRight.add(new Position(mapWidth - wallSize, wallSize));
        wallRight.add(new Body(mapHeight - wallSize * 2, wallSize, Geometry.RECTANGLE));
        wallRight.add(ViewManager.getView(MapPlugin.BORDER_IMAGE_FILE_PATH));
        
        Entity[] walls = {wallBot, wallTop, wallLeft, wallRight};
        
        return walls;
    }
}
