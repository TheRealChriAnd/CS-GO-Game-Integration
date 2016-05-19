/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

/**
 *
 * @author Christoffer Andersson
 */
public final class CSGOEvent
{
    private final int event;
    private final Object newValue;
    private final Object oldValue;
    private final Object[] userData;

    public CSGOEvent(int event, Object newValue, Object oldValue, Object[] userData)
    {
        this.event = event;
        this.newValue = newValue;
        this.oldValue = oldValue;
        this.userData = userData;
    }

    public int getEvent()
    {
        return event;
    }

    public Object getNewValue()
    {
        return newValue;
    }

    public Object getOldValue()
    {
        return oldValue;
    }

    public Object[] getUserData()
    {
        return userData;
    }
}