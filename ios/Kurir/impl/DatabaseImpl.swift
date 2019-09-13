//
//  DatabaseImpl.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex
import Firebase

class DatabaseImpl: Database {
    private lazy var firestore: Firestore = {
        let firestore = Firestore.firestore()
        let settings = firestore.settings
        settings.areTimestampsInSnapshotsEnabled = true
        firestore.settings = settings
        return firestore
    }()
    
    func readData(tableName: String, onSuccess: @escaping ([[String : Any]]) -> Void, onError: @escaping (KotlinThrowable) -> Void) {
        firestore.collection(tableName)
            .getDocuments { snapshot, error in
                if let error = error {
                    onError(error.throwable)
                } else {
                    let result = snapshot!.documents.map{ $0.data() }
                    onSuccess(result)
                }
        }
    }
    
    func writeData(tableName: String, payload: [String : Any], onSuccess: @escaping (String) -> Void, onError: @escaping (KotlinThrowable) -> Void) {
        var ref: DocumentReference? = nil
        ref = firestore.collection(tableName)
            .addDocument(data: payload) { error in
                if let error = error {
                    onError(error.throwable)
                } else {
                    if let documentId = ref?.documentID {
                        onSuccess(documentId)
                    } else {
                        onError(KotlinThrowable(message: "Unknown error"))
                    }
                }
        }
    }
}

extension Error {
    var throwable: KotlinThrowable {
        get {
            return KotlinThrowable(message: localizedDescription)
        }
    }
}
