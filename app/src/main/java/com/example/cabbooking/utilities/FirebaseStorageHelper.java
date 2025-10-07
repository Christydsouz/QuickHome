package com.example.cabbooking.utilities;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;

public class FirebaseStorageHelper {
    private static final String TAG = "FirebaseStorageHelper";
    private FirebaseStorage storage;
    private StorageReference storageRef;

    public FirebaseStorageHelper() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    /**
     * Upload profile image to Firebase Storage
     * @param imageUri Local image URI
     * @param userId User ID for the file path
     * @param callback Callback for success/failure
     */
    public void uploadProfileImage(Uri imageUri, String userId, StorageCallback callback) {
        StorageReference profileImageRef = storageRef.child("profile_images/" + userId + ".jpg");
        
        profileImageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get download URL
                        profileImageRef.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri downloadUri) {
                                        callback.onSuccess(downloadUri.toString());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        callback.onFailure("Failed to get download URL: " + e.getMessage());
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure("Upload failed: " + e.getMessage());
                    }
                });
    }

    /**
     * Delete profile image from Firebase Storage
     * @param userId User ID for the file path
     * @param callback Callback for success/failure
     */
    public void deleteProfileImage(String userId, StorageCallback callback) {
        StorageReference profileImageRef = storageRef.child("profile_images/" + userId + ".jpg");
        
        profileImageRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess("Image deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure("Delete failed: " + e.getMessage());
                    }
                });
    }

    /**
     * Get download URL for profile image
     * @param userId User ID for the file path
     * @param callback Callback for success/failure
     */
    public void getProfileImageUrl(String userId, StorageCallback callback) {
        StorageReference profileImageRef = storageRef.child("profile_images/" + userId + ".jpg");
        
        profileImageRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUri) {
                        callback.onSuccess(downloadUri.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure("Failed to get image URL: " + e.getMessage());
                    }
                });
    }

    /**
     * Callback interface for Storage operations
     */
    public interface StorageCallback {
        void onSuccess(String result);
        void onFailure(String error);
    }
} 