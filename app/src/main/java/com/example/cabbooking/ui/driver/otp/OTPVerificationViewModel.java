package com.example.cabbooking.ui.driver.otp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import com.example.cabbooking.Constants;
import com.example.cabbooking.model.Booking;
import com.example.cabbooking.utilities.OTPGenerator;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class OTPVerificationViewModel extends ViewModel {
    
    private MutableLiveData<Boolean> otpVerificationResult = new MutableLiveData<>();
    private MutableLiveData<Booking> currentBooking = new MutableLiveData<>();
    private DocumentReference currentBookingDocRef;
    private FirebaseFirestore db;
    
    public OTPVerificationViewModel() {
        db = FirebaseFirestore.getInstance();
    }
    
    public void setCurrentBooking(Booking booking) {
        this.currentBooking.setValue(booking);
    }
    
    public void setCurrentBookingDocRef(DocumentReference docRef) {
        this.currentBookingDocRef = docRef;
    }
    
    public void verifyOTP(String enteredOTP) {
        Booking booking = currentBooking.getValue();
        if (booking == null) {
            otpVerificationResult.setValue(false);
            return;
        }
        
        String actualOTP = booking.getRideOTP();
        if (actualOTP == null || actualOTP.trim().isEmpty()) {
            Log.e("OTPVerification", "Actual OTP is null or empty");
            otpVerificationResult.setValue(false);
            return;
        }
        
        Log.d("OTPVerification", "Entered OTP: '" + enteredOTP + "', Actual OTP: '" + actualOTP + "'");
        boolean isValid = OTPGenerator.validateOTP(enteredOTP, actualOTP);
        
        if (isValid) {
            // Update booking in Firestore to mark OTP as verified
            updateOTPVerificationInFirestore();
        } else {
            otpVerificationResult.setValue(false);
        }
    }
    
    private void updateOTPVerificationInFirestore() {
        if (currentBookingDocRef != null) {
            currentBookingDocRef.update(Constants.FSBooking.otpVerified, true)
                    .addOnSuccessListener(aVoid -> {
                        otpVerificationResult.setValue(true);
                    })
                    .addOnFailureListener(e -> {
                        otpVerificationResult.setValue(false);
                    });
        } else {
            otpVerificationResult.setValue(false);
        }
    }
    
    public MutableLiveData<Boolean> getOtpVerificationResult() {
        return otpVerificationResult;
    }
    
    public void clearVerificationResult() {
        otpVerificationResult.setValue(null);
    }
} 