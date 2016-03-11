/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.gruppesex.bodtrd.common.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Morten
 */
public class ActionHandler
{

    private static Map<Action, Boolean> actions = new ConcurrentHashMap();

    public static void setActive(Action action, boolean active)
    {
        actions.put(action, active);
    }

    public static boolean isActive(Action action)
    {
        return actions.get(action);
    }

}
