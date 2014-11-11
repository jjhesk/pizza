/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import org.timothyb89.eventbus.Event;

public class CouponItemAddedEvent extends Event{
    private final MenuItem item;

    public CouponItemAddedEvent(MenuItem item) {
        this.item = item;
    }

    public MenuItem getItem() {
        return item;
    }
    
}
