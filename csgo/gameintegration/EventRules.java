/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import java.util.ArrayList;

/**
 *
 * @author Christoffer Andersson
 */
public final class EventRules
{
    private final ArrayList<Integer> events;
    private final ICSGOEvent listener;

    public EventRules(Integer[] events, ICSGOEvent listener)
    {
        this.events = new ArrayList<>();
        this.listener = listener;
        for(int i = 0; i < events.length; i++)
        {
            this.events.add(events[i]);
        }
    }
    
    public void addEvents(Integer... event)
    {
        for(int i = 0; i < event.length; i++)
        {
            if(!events.contains(event[i]))
            {
                this.events.add(event[i]);
            }
        }     
    }
    
    public void removeEvents(Integer... event)
    {
        for(int i = 0; i < event.length; i++)
        {
            this.events.remove((Integer)event[i]);
        }
    }
    
    public boolean hasEvents(int... event)
    {
        for(int i = 0; i < event.length; i++)
        {
            if(!events.contains(event[i]))
            {
                return false;
            }
        }
        return true;
    }

    public Integer[] getEvents()
    {
        return events.toArray(new Integer[events.size()]);
    }

    public ICSGOEvent getListener()
    {
        return listener;
    }
    
    public void onEventFired(CSGOEvent event)
    {
        if(events.contains(event.getEvent()))
        {
            if(listener != null)
            {
                this.listener.onCSGOEvent(event.getEvent(), event.getNewValue(), event.getOldValue(), event.getUserData());
            }
        }
    }
}