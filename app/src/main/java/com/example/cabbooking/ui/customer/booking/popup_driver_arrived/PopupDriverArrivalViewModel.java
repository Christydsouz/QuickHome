package com.example.cabbooking.ui.customer.booking.popup_driver_arrived;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cabbooking.model.User;
import com.example.cabbooking.model.Booking;

/**
 * View model for PopupDriverArrivalFragment
 */
public class PopupDriverArrivalViewModel extends ViewModel {
    private MutableLiveData<User> driver;
    private MutableLiveData<Booking> booking;

    public PopupDriverArrivalViewModel() {
        driver = new MutableLiveData<>();
        booking = new MutableLiveData<>();
    }

    public void setDriver(User driver) {
        this.driver.setValue(driver);
    }

    public MutableLiveData<User> getDriver() {
        return driver;
    }

    public void setBooking(Booking booking) {
        this.booking.setValue(booking);
    }

    public MutableLiveData<Booking> getBooking() {
        return booking;
    }
}