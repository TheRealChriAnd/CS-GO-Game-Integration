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
public interface ICSGOEvent
{
    void onCSGOEvent(int event, Object newValue, Object lastValue, Object[] userData);
}