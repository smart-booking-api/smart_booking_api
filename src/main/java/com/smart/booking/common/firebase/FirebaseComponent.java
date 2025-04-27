package com.smart.booking.common.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class FirebaseComponent {
    public <T> String updateDocument(String collectionName, String documentId, T model) {
        Firestore firestore = FirestoreClient.getFirestore();
        try {
            ApiFuture<WriteResult> apiFuture =
                firestore.collection(collectionName).document(documentId).set(model);

            // 결과 확인
            log.info("Document written with ID: " + apiFuture.get().getUpdateTime());
            return apiFuture.get().getUpdateTime().toString();
        } catch (Exception e) {
            log.error("firebaseError", e);
            throw new CommonException(ResponseCode.FIREBASE_ERROR, e);
        }
    }
}
