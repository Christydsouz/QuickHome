package com.example.cabbooking.ui.driver.otp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cabbooking.R;
import com.example.cabbooking.utilities.OTPGenerator;
import com.example.cabbooking.ui.driver.process_booking.DriverProcessBookingViewModel;

public class OTPVerificationFragment extends DialogFragment {

    private EditText otpEditText;
    private TextView errorTextView;
    private Button cancelBtn;
    private Button verifyBtn;
    
    private OTPVerificationViewModel mViewModel;
    private DriverProcessBookingViewModel driverProcessBookingViewModel;

    public static OTPVerificationFragment newInstance() {
        return new OTPVerificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_verification, container, false);
        linkViewElements(view);
        setClickListeners();
        return view;
    }

    private void linkViewElements(View view) {
        otpEditText = view.findViewById(R.id.otpEditText);
        errorTextView = view.findViewById(R.id.errorTextView);
        cancelBtn = view.findViewById(R.id.cancelBtn);
        verifyBtn = view.findViewById(R.id.verifyBtn);
    }

    private void setClickListeners() {
        cancelBtn.setOnClickListener(v -> dismiss());
        
        verifyBtn.setOnClickListener(v -> {
            String enteredOTP = otpEditText.getText().toString().trim();
            
            if (TextUtils.isEmpty(enteredOTP)) {
                showError("Please enter OTP");
                return;
            }
            
            if (!OTPGenerator.isValidOTPFormat(enteredOTP)) {
                showError("Please enter a valid 4-digit OTP");
                return;
            }
            
            // Verify OTP
            mViewModel.verifyOTP(enteredOTP);
        });
    }

    private void showError(String message) {
        errorTextView.setText(message);
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(OTPVerificationViewModel.class);
        driverProcessBookingViewModel = new ViewModelProvider(requireActivity()).get(DriverProcessBookingViewModel.class);
        
        // Observe OTP verification result
        mViewModel.getOtpVerificationResult().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (result) {
                    // OTP verified successfully
                    hideError();
                    // Notify the driver process booking that OTP is verified
                    driverProcessBookingViewModel.setOtpVerified(true);
                    dismiss();
                } else {
                    // OTP verification failed
                    showError("Invalid OTP. Please check with the customer and try again.");
                    // Clear the input field for retry
                    otpEditText.setText("");
                }
                mViewModel.clearVerificationResult();
            }
        });
    }
} 