package com.smart.booking.common.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class FirebaseComponent {
    public <T> String insertDocument(String collectionName, String documentId, T model) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture =
            firestore.collection(collectionName).document(documentId).set(model);
        return apiFuture.get().getUpdateTime().toString();
    }

    public void deleteDocument(String collectionName, String documentId) {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(collectionName).document(documentId).delete();
        log.info("Deleted document Id: {}", documentId);
    }


}
