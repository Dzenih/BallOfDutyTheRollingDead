/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.gruppeseks.bodtrd.common.data;

import dk.gruppeseks.bodtrd.common.data.entityelements.Position;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Morten
 */
public class ActionHandler
{
    private static Map<Action, Boolean> _actions = new ConcurrentHashMap();
    private static Position _mousePosition = new Position(0, 0);

    public static void setActive(Action action, boolean b)
    {
        _actions.put(action, b);
    }

    public static boolean isActive(Action action)
    {
        Boolean val = _actions.get(action);
        return val != null && val;
    }

    public static Position getMousePosition()
    {
        return _mousePosition;
    }

    public static void setMousePosition(int x, int y)
    {
        _mousePosition.setX(x);
        _mousePosition.setY(y);
    }
}
