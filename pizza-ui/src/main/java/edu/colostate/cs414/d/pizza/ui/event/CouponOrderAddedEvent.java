package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import org.timothyb89.eventbus.Event;

public class CouponOrderAddedEvent extends Event {

    private final Coupon coupon;

    public CouponOrderAddedEvent(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }
}
